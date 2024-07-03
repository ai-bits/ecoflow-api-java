package com.ecoflow.iot.open.utils;

import cn.hutool.core.net.URLEncoder;
import cn.hutool.json.JSONObject;
import org.apache.commons.lang3.RandomUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpMethod;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author kevin.liu
 * @date 2023/3/20 14:31
 * @description
 */
public class HttpUtil {
    private static String ACCESS_KEY = "accessKey";
    private static String NONCE = "nonce";
    private static String TIMESTAMP = "timestamp";
    private static String SIGN = "sign";
    public static String execute(HttpMethod httpMethod, String url, JSONObject req, String accessKey, String secretKey) {
        CloseableHttpResponse response = null;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            // 执行请求
            response = httpClient.execute(getHttpUriRequest(httpMethod, url, req, accessKey, secretKey));
            int statusCode = response.getStatusLine().getStatusCode();

            // 判断返回状态是否成功
            if (HttpStatus.SC_OK != statusCode) {
                System.out.println(String.format("response status is failed|url=%s,statusCode=%s", url, statusCode));
                throw new RuntimeException("response status is failed");
            }
            String content = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            return content;
        } catch (ClientProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (Exception e) {
                    System.out.println(String.format("response.close() failed|url=%s", url));
                    e.printStackTrace();
                }
            }
        }
    }

    public static HttpUriRequest getHttpUriRequest(HttpMethod httpMethod, String url, JSONObject req, String accessKey, String secretKey) {
        String nonce = RandomUtils.nextInt(10000, 1000000) + "";
        String timestamp = System.currentTimeMillis() + "";

        SortedMap<String, Object> sortKeyValueMap = new TreeMap<>();
        if (!Objects.isNull(req)) {
            sortKeyValueMap = MyMapUtil.getMapFromObject(req);
        }
        String queryString = MyMapUtil.getKeyValueString(sortKeyValueMap);
        String keyValueString = MyMapUtil.appendAccessKey(queryString, accessKey, nonce, timestamp);
        String sign = EncryptUtil.encryptHmacSHA256(keyValueString, secretKey);

        queryString = URLEncoder.createDefault().encode(queryString, StandardCharsets.UTF_8);
        HttpUriRequest httpUriRequest = null;
        switch (httpMethod) {
            case GET:
                httpUriRequest = new HttpGet(String.format("%s?%s", url, queryString));
                break;
            case PUT:
                HttpPut httpPut = new HttpPut(url);
                httpPut.addHeader("Content-Type", "application/json;charset=UTF-8");
                buildBody(httpPut, req);
                httpUriRequest = httpPut;
                break;
            case POST:
                HttpPost httpPost = new HttpPost(url);
                httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
                buildBody(httpPost, req);
                httpUriRequest = httpPost;
                break;
            case DELETE:
                httpUriRequest = new HttpDelete(String.format("%s?%s", url, queryString));
                break;
            default:
                System.out.println(String.format("httMethod not match|url=%s,httpMethod=%s", url, httpMethod));
                throw new RuntimeException("httMethod not match");
        }
        httpUriRequest.addHeader(ACCESS_KEY, accessKey);
        httpUriRequest.addHeader(NONCE, nonce);
        httpUriRequest.addHeader(TIMESTAMP, timestamp);
        httpUriRequest.addHeader(SIGN, sign);
        return httpUriRequest;
    }

    public static void buildBody(HttpEntityEnclosingRequestBase httpEntity, JSONObject req) {
        StringEntity stringEntity = new StringEntity(req.toString(), StandardCharsets.UTF_8);
        stringEntity.setContentEncoding(StandardCharsets.UTF_8.toString());
        httpEntity.setEntity(stringEntity);
    }
}

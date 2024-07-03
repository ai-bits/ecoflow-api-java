package com.ecoflow.iot.open;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ecoflow.iot.open.utils.HttpUtil;
import org.springframework.http.HttpMethod;

public class Main {
    // this should be replaced your accessKey and secretKey from EcoFlow Open Platform
    private static final String ACCESS_KEY = "AccessKey";
    private static final String SECRET_KEY = "SecretKey";
    private static final String HOST = "https://api.ecoflow.com";
    private static final String GET_MQTT_CERTIFICATION_URL = HOST + "/iot-open/sign/certification";
    private static final String DEVICE_LIST_URL = HOST + "/iot-open/sign/device/list";
    private static final String SET_QUOTA_URL = HOST + "/iot-open/sign/device/quota";
    private static final String GET_QUOTA_URL = HOST + "/iot-open/sign/device/quota";
    private static final String GET_ALL_QUOTA_URL = HOST + "/iot-open/sign/device/quota/all";

    public static void main(String[] args) {
        getMQTTCertification();
        deviceList(); //corrected typo
        //setQuota(); //returns "not allowed"
        //getQuota();
        //getAllQuota();
    }

    private static void getMQTTCertification() {
        String response = HttpUtil.execute(HttpMethod.GET, GET_MQTT_CERTIFICATION_URL, null, ACCESS_KEY, SECRET_KEY);
        System.out.println("response: getMQTTCertification|" + response);
    }
    private static void deviceList() {
        String response = HttpUtil.execute(HttpMethod.GET, DEVICE_LIST_URL, null, ACCESS_KEY, SECRET_KEY);
        System.out.println("response: deivceList|" + response);
    }

    private static void getAllQuota() {
        // Get Delta Pro all quota values
        String sn = "DCABZ***";
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("sn", sn);

        String url = GET_ALL_QUOTA_URL + "?sn=" + sn;
        String response = HttpUtil.execute(HttpMethod.GET, url, jsonObject, ACCESS_KEY, SECRET_KEY);
        System.out.println("response: getAllQuota|" + response);
    }

    private static void getQuota() {
        // Delta Pro: Get the X-Boost switch value
        String json = "{\"sn\":\"DCABZ***\",\"params\":{\"cmdSet\":32,\"id\":66,\"quotas\":[\"inv.cfgAcEnabled\"]}}";
        JSONObject jsonObject = JSONUtil.parseObj(json);
        String response = HttpUtil.execute(HttpMethod.POST, GET_QUOTA_URL, jsonObject, ACCESS_KEY, SECRET_KEY);
        System.out.println("response: getQuota|" + response);
    }

    private static void setQuota() {
        // Delta Pro: Setting the X-Boost switch
        String json = "{\"sn\":\"DCABZ***\",\"params\":{\"cmdSet\":32,\"id\":66,\"enabled\":1}}";
        JSONObject jsonObject = JSONUtil.parseObj(json);
        String response = HttpUtil.execute(HttpMethod.PUT, SET_QUOTA_URL, jsonObject, ACCESS_KEY, SECRET_KEY);
        System.out.println("response: setQuota|" + response);
    }

}

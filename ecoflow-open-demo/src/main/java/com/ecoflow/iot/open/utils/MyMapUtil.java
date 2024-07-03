package com.ecoflow.iot.open.utils;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author kevin.liu
 * @date 2022/12/16 14:54
 * @description
 */
public class MyMapUtil {
    public static final String MERGE_CHAR = "&";
    public static final String EQUAL_CHAR = "=";
    public static final String POINT_CHAR = ".";
    public static final String ACCESS_KEY = "accessKey";
    public static final String NONCE = "nonce";
    public static final String TIMESTAMP = "timestamp";
    public static final String SIGN = "sign";

    public static String appendAccessKey(String keyValueString, String accessKey, String nonce, String timestamp) {
        StringBuilder builder = new StringBuilder();
        if (!StringUtils.isBlank(keyValueString)) {
            builder.append(keyValueString).append(MERGE_CHAR);
        }
        return builder.append(ACCESS_KEY).append(EQUAL_CHAR).append(accessKey).append(MERGE_CHAR)
                .append(NONCE).append(EQUAL_CHAR).append(nonce).append(MERGE_CHAR)
                .append(TIMESTAMP).append(EQUAL_CHAR).append(timestamp).toString();
    }

    public static String getKeyValueString(SortedMap<String, Object> sortKeyValueMap) {
        if (MapUtils.isEmpty(sortKeyValueMap)) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        sortKeyValueMap.keySet().forEach(key -> {
            builder.append(key).append(EQUAL_CHAR).append(sortKeyValueMap.get(key)).append(MERGE_CHAR);
        });
        if (builder.length() == 0) {
            return "";
        }
        return builder.substring(0, builder.length() - 1);
    }

    public static SortedMap<String, Object> getMapFromObject(JSONObject jsonObject) {
        if (jsonObject == null || jsonObject.isEmpty()) {
            throw new RuntimeException("parameter invalid");
        }
        SortedMap<String, Object> map = new TreeMap<>();
        for (String key : jsonObject.keySet()) {
            Object value = jsonObject.get(key);
            map.putAll(getByObject(key, value));
        }
        return map;
    }

    private static Map<String, Object> getByObject(String key, Object value) {
        if (StringUtils.isBlank(key) || value == null) {
            throw new RuntimeException("parameter invalid");
        }
        if (value instanceof JSONArray) {
            return getByJsonArray(key, (JSONArray) value);
        } else if (value instanceof JSONObject) {
            return getByJSONObject(key, (JSONObject) value);
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put(key, value);
            return map;
        }
    }

    private static Map<String, Object> getByJsonArray(String key, JSONArray value) {
        if (StringUtils.isBlank(key) || value == null || value.size() == 0) {
            throw new RuntimeException("parameter invalid");
        }
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < value.size(); i++) {
            map.putAll(getByObject(getArrayKey(key, i), value.get(i)));
        }
        return map;
    }

    private static Map<String, Object> getByJSONObject(String key, JSONObject value) {
        if (StringUtils.isBlank(key) || value == null || value.isEmpty()) {
            throw new RuntimeException("parameter invalid");
        }
        Map<String, Object> map = new HashMap<>();
        for (String innerKey : value.keySet()) {
            map.putAll(getByObject(getObjectKey(key, innerKey), value.get(innerKey)));
        }
        return map;
    }

    private static String getObjectKey(String key, String innerKey) {
        return new StringBuilder().append(key).append(POINT_CHAR).append(innerKey).toString();
    }

    private static String getArrayKey(String key, int index) {
        return new StringBuilder().append(key).append("[").append(index).append("]").toString();
    }
}

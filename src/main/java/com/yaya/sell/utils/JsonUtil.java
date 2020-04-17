package com.yaya.sell.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 * @author changhr2013
 * @date 2020/4/11
 */
public class JsonUtil {

    private JsonUtil() {
    }

    private static GsonBuilder builder;

    static {
        builder = new GsonBuilder();
        builder.setPrettyPrinting();
    }

    public static String toJson(Object object) {
        if (object == null) {
            return new JsonObject().toString();
        }
        Gson gson = builder.create();
        return gson.toJson(object);
    }
}

package com.diffey.view.rxzhihu.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Gson封装类
 * Created by diff on 2016/2/3.
 */
public class GsonUtils {
    private static Gson gson;

    static {
        if (gson == null) {
            gson = new Gson();
        }
    }

    /**
     * 对象转Json字符串
     *
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        checkGson();
        return gson.toJson(object);
    }

    /**
     * 字符串转Json对象
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        checkGson();
        return gson.fromJson(json, clazz);
    }

    public static <T> T fromJson(String json, TypeToken<T> token) {
        checkGson();
        return gson.fromJson(json, token.getType());
    }

    private static void checkGson() {
        if (gson == null) {
            gson = new Gson();
        }
    }

}

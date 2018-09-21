package com.fly.postop.code.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * <pre>
 *           .----.
 *        _.'__    `.
 *    .--(Q)(OK)---/$\
 *  .' @          /$$$\
 *  :         ,   $$$$$
 *   `-..__.-' _.-\$$/
 *         `;_:    `"'
 *       .'"""""`.
 *      /,  FLY  ,\
 *     //         \\
 *     `-._______.-'
 *     ___`. | .'___
 *    (______|______)
 * </pre>
 * 包    名 : com.shangyi.postop.doctor.android.newframe.utils
 * 作    者 : FLY
 * 创建时间 : 2018/9/18
 * 描述: Gson帮助类
 */
public class GsonUtil {
    public static <T> T fromJson(String json, Type classOfT) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Long.class, new JsonDeserializer<Long>() {
            @Override
            public Long deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                try {
                    return json.getAsJsonPrimitive().getAsLong();
                } catch (Exception e) {
                }

                try {
                    String data = json.getAsJsonPrimitive().getAsString();
                    long d = CommitUtils.getLong(data);
                    if (d != 0) {
                        return d;
                    }
                    try {
                        return TimeUtil.parse(data, TimeUtil.FORMAT_YYYYMMDDHHMMSS);
                    } catch (Exception e1) {
                    }
                    try {
                        return TimeUtil.parse(data, TimeUtil.FORMAT_YYYYMMDD);
                    } catch (Exception e1) {
                    }
                } catch (Exception e) {
                }
                return 0l;
            }
        }).create();
        return gson.fromJson(json, classOfT);
    }

    public static String toJson(Object object) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(object);
    }
}

package com.shangyi.android.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

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
public class GsonUtils {
    private static Gson gson;

    protected static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }

    public static <T> T toDomain(String json, Class<T> clz) {
        if (gson == null) {
            gson = new Gson();
        }
        T t = gson.fromJson(json, clz);
        return t;
    }

    public static <T> String toJson(T t) {
        if (gson == null) {
            gson = new Gson();
        }
        String json = gson.toJson(t);
        return json;
    }

    /**
     * @param json
     * @param type new TypeToken<List<Object>>(){}.getType()
     * @param <T>
     * @return
     */
    public static <T> List<T> toList(String json, Type type) {
        if (gson == null) {
            gson = new Gson();
        }

        List list = (List) gson.fromJson(json, type);
        return list;
    }

    public static <T> Map<String, T> toMap(String json, Type type) {
        if (gson == null) {
            gson = new Gson();
        }

        Map map = (Map) gson.fromJson(json, type);
        return map;
    }


    public static <T> Map<String, T> toMap(String json, Class<T> clz) {
        if (gson == null)
            gson = new Gson();

        Map<String, T> map = gson.fromJson(json,
                new TypeToken<Map<String, T>>() {
                }.getType());

        return map;
    }

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
                        return TimeUtils.parse(data, TimeUtils.FORMAT_YYYYMMDDHHMMSS);
                    } catch (Exception e1) {
                    }
                    try {
                        return TimeUtils.parse(data, TimeUtils.FORMAT_YYYYMMDD);
                    } catch (Exception e1) {
                    }
                } catch (Exception e) {
                }
                return 0l;
            }
        }).create();
        return gson.fromJson(json, classOfT);
    }
}

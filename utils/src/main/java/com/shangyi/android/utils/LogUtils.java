package com.shangyi.android.utils;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

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
 * 包    名 : com.fly.postop.code.utils
 * 作    者 : FLY
 * 创建时间 : 2018/9/26
 * 描述: log 工具类
 */
public class LogUtils {

    protected static boolean isDebug = Utils.getInstance().getIsDebug();

    private static final String TAG = "Postop-";

    public static int d(String msg) {
        if (null == msg)
            return 0;
        if (isDebug) {
            return Log.d(TAG, msg);
        } else {
            return 0;
        }
    }


    public static int d(String subTAG, String msg) {
        if (null == msg)
            return 0;
        if (isDebug) {
            return Log.d(TAG + subTAG, msg);
        } else {
            return 0;
        }
    }

    public static int i(String subTAG, String msg) {
        if (null == msg)
            return 0;
        if (isDebug) {
            return Log.i(TAG + subTAG, msg);
        } else {
            return 0;
        }
    }

    public static int i(String msg) {
        if (null == msg)
            return 0;
        if (isDebug) {
            return Log.i(TAG, msg);
        } else {
            return 0;
        }
    }

    public static int e(String subTAG, String msg) {
        if (null == msg)
            return 0;
        if (isDebug) {
            return Log.e(TAG + subTAG, msg);
        } else {
            return 0;
        }
    }

    public static int e(String msg) {
        if (null == msg)
            return 0;
        if (isDebug) {
            return Log.e(TAG, msg);
        } else {
            return 0;
        }
    }

    public static int w(String subTAG, String msg) {
        if (null == msg)
            return 0;
        if (isDebug) {
            return Log.w(TAG + subTAG, msg);
        } else {
            return 0;
        }
    }

    public static int w(String msg) {
        if (null == msg)
            return 0;
        if (isDebug) {
            return Log.w(TAG, msg);
        } else {
            return 0;
        }
    }

    public static int v(String subTAG, String msg) {
        if (null == msg)
            return 0;
        if (isDebug) {
            return Log.v(TAG + subTAG, msg);
        } else {
            return 0;
        }
    }

    public static int w(String subTAG, Exception e) {
        if (null == e)
            return 0;
        if (isDebug) {
            return Log.w(TAG + subTAG, e.getMessage());
        } else {
            return 0;
        }
    }

    public static int e(String subTAG, String msg, Exception e) {
        if (null == msg && null == e)
            return 0;
        if (isDebug) {
            return Log.e(TAG + subTAG, msg + "." + e.getMessage());
        } else {
            return 0;
        }
    }

    public static int e(String subTAG, String msg, Throwable e) {
        if (null == msg && null == e)
            return 0;
        if (isDebug) {
            return Log.e(TAG + subTAG, msg, e);
        } else {
            return 0;
        }
    }

    public static void jsonFormatterLog(String tag, String s) {
        if (!isDebug) return;
        if (TextUtils.isEmpty(s)) {
            Log.e(tag, "" + s);
            return;
        }
        String json = s;
        String tagJson = null;
        if (s.indexOf("{") > 0) {
            tagJson = s.substring(0, s.indexOf("{"));
            json = s.substring(s.indexOf("{"));
        }
        if (s.indexOf("{") > -1) {
            try {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonParser jsonParser = new JsonParser();
                JsonElement jsonElement = jsonParser.parse(json);
                json = gson.toJson(jsonElement);
                if (!TextUtils.isEmpty(tagJson)) {
                    json = tagJson + "\n" + json;
                }
                Log.e(TAG + tag, "" + json);
                return;
            } catch (Exception e) {
            }
        }
        Log.e(TAG + tag, "" + s);
    }
}

package com.shangyi.postop.utils;

import android.text.TextUtils;

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
 * 创建时间 : 2018/9/21
 * 描述:
 */
public class UrlUtils {

    public static boolean isEndEquals(String s0, String s1) {
        if (TextUtils.isEmpty(s0)) return false;
        if (s0.length() == 1) return s0.equals(s1);
        return s0.substring(s0.length() - 1).equals(s1);
    }

    public static String addEndSeparator(String url) {
        if (isEndEquals(url, "/")) {
            return url;
        } else {
            if (TextUtils.isEmpty(url)) return url;
            return url + "/";
        }
    }
}

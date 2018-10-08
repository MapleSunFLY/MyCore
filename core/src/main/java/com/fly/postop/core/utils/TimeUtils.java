package com.fly.postop.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
 * 描述: 时间转换帮助类
 */
public class TimeUtils {
    public static final String FORMAT_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_YYYYMMDD = "yyyy-MM-dd";

    public static String format(long date, String formatStr) {
        if (date == 0l) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(new Date(date));
    }

    public static long parse(String str, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        try {
            return format.parse(str).getTime();
        } catch (ParseException e) {
            return 0;
        }
    }
}

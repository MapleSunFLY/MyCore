package com.shangyi.android.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.DisplayMetrics;

import java.math.BigDecimal;
import java.text.DecimalFormat;

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
 * 描述: 常用数据转换或判断帮助类
 */
public class CommitUtils {

    public static final String regularExpression = "[a-zA-Z0-9\\s]";
    public static final String regularExpressionPunctuation = "[\\p{P}+~$`^=|<>～｀＄＾＋＝｜＜＞￥×°]";

    public static String getDistance(double distance) {
        distance = distance / 1000d;
        DecimalFormat fmt = new DecimalFormat("0.##");
        String ds = fmt.format(distance) + "千米 ";
        return ds;
    }

    public static String getDistance(double lat, double lon, double lat1, double lon1) {
        String ds = "";
        double distance = distanceBetween(
                lat, lon,
                lat1, lon1);
        distance = Math.abs(distance);
        if (distance >= 0) {
            distance = distance / 1000d;
            DecimalFormat fmt = new DecimalFormat("0.##");
            ds = fmt.format(distance) + "千米 ";
        } else {
            DecimalFormat fmt = new DecimalFormat("0.##");
            ds = fmt.format((int) distance) + "米 ";
        }

        return ds;
    }


    public static double distanceBetween(double startLat, double startLon, double endLat, double endLon) {
        double er = 6378137d; // 6378700.0f;
        double radlat1 = Math.PI * startLat / 180.0d;
        double radlat2 = Math.PI * endLat / 180.0d;
        //now long.
        double radlong1 = Math.PI * startLon / 180.0d;
        double radlong2 = Math.PI * endLon / 180.0d;
        if (radlat1 < 0) radlat1 = Math.PI / 2 + Math.abs(radlat1);// south
        if (radlat1 > 0) radlat1 = Math.PI / 2 - Math.abs(radlat1);// north
        if (radlong1 < 0) radlong1 = Math.PI * 2 - Math.abs(radlong1);//west
        if (radlat2 < 0) radlat2 = Math.PI / 2 + Math.abs(radlat2);// south
        if (radlat2 > 0) radlat2 = Math.PI / 2 - Math.abs(radlat2);// north
        if (radlong2 < 0) radlong2 = Math.PI * 2 - Math.abs(radlong2);// west
        double x1 = er * Math.cos(radlong1) * Math.sin(radlat1);
        double y1 = er * Math.sin(radlong1) * Math.sin(radlat1);
        double z1 = er * Math.cos(radlat1);
        double x2 = er * Math.cos(radlong2) * Math.sin(radlat2);
        double y2 = er * Math.sin(radlong2) * Math.sin(radlat2);
        double z2 = er * Math.cos(radlat2);
        double d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) + (z1 - z2) * (z1 - z2));
        double theta = Math.acos((er * er + er * er - d * d) / (2 * er * er));
        double dist = theta * er;
        return dist;
    }

    public static Long getLong(String s) {
        try {
            return Long.parseLong(s.trim());
        } catch (Exception e) {
            return 0L;
        }
    }

    public static Long getLong(Long s) {
        try {
            return (long) s;
        } catch (Exception e) {
            return 0L;
        }
    }

    public static boolean getBoolean(String s) {
        try {
            return Boolean.parseBoolean(s.trim());
        } catch (Exception e) {
            return false;
        }
    }

    public static Float getFloat(String s) {
        try {
            return Float.parseFloat(s.trim());
        } catch (Exception e) {
            return 0f;
        }
    }

    public static Integer getInteger(String s) {
        try {
            return Integer.parseInt(s.trim());
        } catch (Exception e) {
            return 0;
        }
    }

    public static Integer getInteger(float s) {
        try {
            BigDecimal bigDecimal = new BigDecimal(s);
            return bigDecimal.intValue();
        } catch (Exception e) {
            return 0;
        }
    }

    public static int getInteger(Integer s) {
        if (s == null) return 0;
        return s;
    }

    public static Double getDouble(String s) {
        try {
            return Double.parseDouble(s);
        } catch (Exception e) {
            return 0D;
        }
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        // 取 drawable 的长宽
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();

        // 取 drawable 的颜色格式
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        // 建立对应 bitmap
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        // 建立对应 bitmap 的画布
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        // 把 drawable 内容画到画布中
        drawable.draw(canvas);
        return bitmap;
    }

    public static DisplayMetrics getDisplayMetrics(Context context) {
        Resources mResources;
        if (context == null) {
            mResources = Resources.getSystem();

        } else {
            mResources = context.getResources();
        }
        DisplayMetrics mDisplayMetrics = mResources.getDisplayMetrics();
        return mDisplayMetrics;
    }

    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否有网络连接
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断WIFI网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断MOBILE网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isMobileConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    public static Bitmap drawable2Bitmap(Drawable drawable) {
        Bitmap stickerBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(stickerBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return stickerBitmap;
    }

    public static int getStatusBarHeight(Activity context) {
        if (Build.VERSION.SDK_INT >= 21) {
            int statusBarHeight = -1;
            //获取status_bar_height资源的ID
            int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                //根据资源ID获取响应的尺寸值
                statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
            }
            return statusBarHeight == 0 ? ViewUtils.dip2px(24) : statusBarHeight;
        } else
            return 0;
    }
}

package com.shangyi.postop.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.util.Random;

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
 * 描述: 网络帮助类
 */
public class NetUtils {

    public static final int STATA_NO_NET = -1;
    public static final int STATA_2G3G4G = 0;
    public static final int STATA_WIFI = 1;


    public static boolean NetworkAvailable(Context context) {
        try {
            ConnectivityManager e = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = e.getActiveNetworkInfo();
            if (info == null || !info.isConnected()) {
                return false;
            }

            if (info.isRoaming()) {
                return true;
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }
        return true;
    }

    /**
     * 判断是否有网络
     *
     * @return 返回值
     */
    public static boolean isNetworkConnected() {
        Context context = Utils.getContext();
        if (context != null) {
            try {
                ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
                if (mNetworkInfo != null) {
                    return mNetworkInfo.isAvailable();
                }
            } catch (Exception var3) {
                var3.printStackTrace();
            }
        }
        return false;
    }
    public static String networkType(Context context) {
        try {
            String networkType = "NONetwork";  //默认值为无网络.
            ConnectivityManager e = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = e.getActiveNetworkInfo();
            String type = info.getTypeName();
            if (type.equalsIgnoreCase("WIFI")) {
                networkType = "WIFI";
            } else if (type.equalsIgnoreCase("MOBILE")) {
                networkType = "MOBILE";
            }
            return networkType;
        } catch (Exception var3) {
            var3.printStackTrace();
            return "";
        }
    }

    public static boolean GprsNetworkAvailable(Context context) {
        try {
            ConnectivityManager e = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            if (e == null) {
                return false;
            } else {
                NetworkInfo info = e.getActiveNetworkInfo();
                return info == null ? false : info.isAvailable();
            }
        } catch (NullPointerException var3) {
            var3.printStackTrace();
            return false;
        } catch (RuntimeException var4) {
            var4.printStackTrace();
            return false;
        }
    }

    public static boolean WifiNetworkAvailable(Context context) {
        try {
            WifiManager e = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = e.getConnectionInfo();
            int ipAddress = wifiInfo == null ? 0 : wifiInfo.getIpAddress();
            return e.isWifiEnabled() && ipAddress != 0;
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }
    }

    public static String getMac(Context context) {
        try {
            WifiManager e = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = e.getConnectionInfo();
            String macTmpString = wifiInfo.getMacAddress();
            if (TextUtils.isEmpty(macTmpString) || macTmpString.equals("00:00:00:00:00:00")) {
                TelephonyManager num1 = (TelephonyManager) context.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
                if (num1.getSimSerialNumber() != null) {
                    macTmpString = num1.getSimSerialNumber();
                }
            }

            if (TextUtils.isEmpty(macTmpString) || macTmpString.equals("00:00:00:00:00:00")) {
                macTmpString = SPUtils.get("mac_id", "");
                if (TextUtils.isEmpty(macTmpString)) {
                    String num1 = getRandom() + ":";
                    String num2 = getRandom() + ":";
                    String num3 = getRandom() + ":";
                    String num4 = getRandom() + ":";
                    String num5 = getRandom() + ":";
                    macTmpString = num1 + num2 + num3 + num4 + num5 + getRandom();
                    SPUtils.put("mac_id", macTmpString);
                }
            }

            LogUtils.d("mac" + macTmpString);
            return macTmpString;
        } catch (Exception var9) {
            LogUtils.e("mac" + var9.toString());
            return null;
        }
    }

    public static int getRandom() {
        boolean result = false;
        Random rdom = new Random();
        double rdomTmp1 = rdom.nextDouble() * 100.0D;
        int result1;
        if (rdomTmp1 == 0.0D) {
            result1 = getRandom();
        } else {
            result1 = (int) Math.ceil(rdomTmp1);
        }

        return result1;
    }

}

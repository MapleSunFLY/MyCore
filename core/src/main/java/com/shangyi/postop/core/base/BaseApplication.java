package com.shangyi.postop.core.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.shangyi.postop.http.HttpUtils;
import com.shangyi.postop.utils.BuildConfig;
import com.shangyi.postop.utils.LogUtils;
import com.shangyi.postop.utils.Utils;

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
 * 包    名 : com.fly.postop.code.base
 * 作    者 : FLY
 * 创建时间 : 2018/9/20
 * 描述: Application 基类
 */
public class BaseApplication extends Application {

    private static BaseApplication httpApplication;

    public static BaseApplication getAppContext() {
        return httpApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.httpApplication = this;
        HttpUtils.getInstance().init(this).setDebug(BuildConfig.DEBUG);
        Utils.getInstance().init(this);
        LogUtils.setIsDebug(BuildConfig.DEBUG);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    /**
     * 获得当前进程的名字
     *
     * @param context
     * @return
     */
    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {

                return appProcess.processName;
            }
        }
        return null;
    }
}

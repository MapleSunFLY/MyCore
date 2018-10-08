package com.shangyi.postop.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

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
 * 包    名 : com.fly.postop.utils
 * 作    者 : FLY
 * 创建时间 : 2018/10/8
 * 描述:
 */
public class Utils {
    @SuppressLint("StaticFieldLeak")
    private static Utils instance;
    @SuppressLint("StaticFieldLeak")
    private static Application context;

    public static Utils getInstance() {
        if (instance == null) {
            synchronized (Utils.class) {
                if (instance == null) {
                    instance = new Utils();
                }
            }

        }
        return instance;
    }

    /**
     * 获取全局上下文
     */
    public static Context getContext() {
        checkInitialize();
        return context;
    }

    /**
     * 必须在全局Application先调用，获取context上下文，否则缓存无法使用
     *
     * @param app Application
     */
    public Utils init(Application app) {
        context = app;
        return this;
    }

    /**
     * 检测是否调用初始化方法
     */
    private static void checkInitialize() {
        if (context == null) {
            throw new ExceptionInInitializerError("请先在全局Application中调用 RxHttpUtils.getInstance().init(this) 初始化！");
        }
    }
}

package com.fly.postop.core.http;

import com.fly.postop.core.utils.GsonUtils;
import com.google.gson.annotations.Expose;

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
 * 包    名 : com.fly.postop.code.http
 * 作    者 : FLY
 * 创建时间 : 2018/9/20
 * 描述: 返回数据的外包装类
 */
public class ResponseJson<T> {

    public static final int SUCCESS_CODE = 0;

    /**
     * 调用返回状态码
     */
    @Expose
    public int apiStatus = -1;

    /**
     * 调用返回Token状态
     */
    @Expose
    public int sysStatus;

    /**
     * 返回的错误消息
     */
    public String info;
    /**
     * 服务器当前时间戳
     */
    @Expose
    public long timestamp;
    /**
     * 接口返回具体数据内容 JSONObject
     */
    @Expose
    public T data;

    public long execTime;

    public boolean isSuccess() {
        return sysStatus == SUCCESS_CODE && apiStatus == SUCCESS_CODE;
    }

    public String toJsonString() {
        return GsonUtils.toJson(this);
    }
}
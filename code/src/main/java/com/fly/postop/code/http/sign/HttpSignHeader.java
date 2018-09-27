package com.fly.postop.code.http.sign;

import com.fly.postop.code.base.BaseApplication;

import java.util.HashMap;
import java.util.UUID;

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
 * 包    名 : com.fly.postop.code.http.sign
 * 作    者 : FLY
 * 创建时间 : 2018/9/26
 * 描述:
 */
public class HttpSignHeader {

    public static final String X_CA_SIGNATURE = "X-Ca-Signature";
    public static final String X_CA_DEVICE_TOKEN = "X-Ca-Device-Token";
    private static String X_CA_TIMESTAMP = "X-Ca-Timestamp";
    private static String X_CA_NONCE = "X-Ca-Nonce";
    private static String X_CA_STAGE = "X-Ca-Stage";
    private static String X_CA_APP_MARKET = "X-Ca-App-Market";
    private static String X_CA_APP_VERSION = "X-Ca-App-Version";
    private static String X_CA_NETWORK = "X-Ca-Network";
    private static String X_CA_TOKEN = "X-Ca-Token";
    private static String X_CA_APP = "X-Ca-App";
    private static String X_CA_OS = "X-Ca-OS";

    public static HashMap<String, Object> getBaseHeaders() {
        HashMap<String, Object> headers = new HashMap<>();
        headers.put(X_CA_TIMESTAMP, String.valueOf(System.currentTimeMillis()));
        headers.put(X_CA_NONCE, UUID.randomUUID().toString());
        return headers;
    }

    public static HashMap<String, Object> getSignAndTokenHeaders() {
        HashMap<String, Object> headers = new HashMap<>();
        headers.put(X_CA_TIMESTAMP, String.valueOf(System.currentTimeMillis()));
        headers.put(X_CA_NONCE, UUID.randomUUID().toString());
        return headers;
    }
}

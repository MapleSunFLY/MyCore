package com.shangyi.postop.http.exception;

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
 * 包    名 : com.fly.postop.code.http.exception
 * 作    者 : FLY
 * 创建时间 : 2018/9/25
 * 描述: des:服务器请求异常
 */
public class ServerException extends Exception {

    public int sysStatus;
    public int apiStatus;
    public int exceptionCode;

    public ServerException(String msg, int sysStatus, int apiStatus) {
        super(msg);
        this.apiStatus = apiStatus;
        this.sysStatus = sysStatus;
    }

    public ServerException(String msg, int sysStatus, int apiStatus, int exceptionCode) {
        super(msg);
        this.apiStatus = apiStatus;
        this.sysStatus = sysStatus;
        this.exceptionCode = exceptionCode;
    }

    public boolean isSuccess() {
        if (sysStatus == 0 && apiStatus == 0) {
            return true;
        }
        return false;
    }

}

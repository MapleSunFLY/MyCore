package com.fly.postop.code.base;

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
 * 创建时间 : 2018/9/21
 * 描述:
 */
public interface BaseConfig {

    /**
     * 下载TAG
     */
    String HTTP_COMMIT_TAG = "TagCommit";

    /**
     * 下载TAG
     */
    String HTTP_DOWNLOAD_TAG = "TagDownload";

    /**
     * 上传TAG
     */
    String HTTP_UPLOAD_TAG = "TagUpload";

    /**
     * 保存cookie
     */
    String HTTP_COOKIE = "cookie";

    /**
     * 保存TOKEN
     */
    String USER_TOKEN = "UserToken";
}

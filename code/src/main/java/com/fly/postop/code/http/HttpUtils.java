package com.fly.postop.code.http;

import android.annotation.SuppressLint;

import com.fly.postop.code.http.cookie.CookieJarImpl;
import com.fly.postop.code.http.cookie.store.CookieStore;
import com.fly.postop.code.http.download.DownloadRetrofit;
import com.fly.postop.code.http.manage.RxHttpManager;
import com.fly.postop.code.http.upload.UploadRetrofit;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.Cookie;
import okhttp3.HttpUrl;
import okhttp3.ResponseBody;

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
 * 创建时间 : 2018/9/21
 * 描述:
 */
public class HttpUtils {

    @SuppressLint("StaticFieldLeak")
    private static HttpUtils instance;

    public HttpUtils() {

    }

    public static HttpUtils getInstance() {
        if (instance == null) {
            synchronized (HttpUtils.class) {
                if (instance == null) {
                    instance = new HttpUtils();
                }
            }

        }
        return instance;
    }


    /**
     * 使用全局参数创建请求
     *
     * @param cls Class
     * @param <K> K
     * @return 返回
     */
    public static <K> K createApi(Class<K> cls) {
        return GlobalRxHttp.createGApi(cls);
    }


    /**
     * 下载文件
     *
     * @param fileUrl 地址
     * @return ResponseBody
     */
    public static Observable<ResponseBody> downloadFile(String fileUrl) {
        return DownloadRetrofit.downloadFile(fileUrl);
    }

    /**
     * 上传单张图片
     *
     * @param uploadUrl 地址
     * @param filePath  文件路径
     * @return ResponseBody
     */
    public static Observable<ResponseBody> uploadImg(String uploadUrl, String filePath) {
        return UploadRetrofit.uploadImage(uploadUrl, filePath);
    }

    /**
     * 上传多张图片
     *
     * @param uploadUrl 地址
     * @param filePaths 文件路径
     * @return ResponseBody
     */
    public static Observable<ResponseBody> uploadImages(String uploadUrl, List<String> filePaths) {
        return UploadRetrofit.uploadImages(uploadUrl, filePaths);
    }

    /**
     * 上传多张图片
     *
     * @param uploadUrl 地址
     * @param fileName  后台接收文件流的参数名
     * @param paramsMap 参数
     * @param filePaths 文件路径
     * @return ResponseBody
     */
    public static Observable<ResponseBody> uploadImagesWithParams(String uploadUrl, String fileName, Map<String, Object> paramsMap, List<String> filePaths) {
        return UploadRetrofit.uploadFilesWithParams(uploadUrl, fileName, paramsMap, filePaths);
    }

    /**
     * 获取全局的CookieJarImpl实例
     */
    private static CookieJarImpl getCookieJar() {
        return (CookieJarImpl) OkHttpConfig.getOkHttpClient().cookieJar();
    }

    /**
     * 获取全局的CookieStore实例
     */
    private static CookieStore getCookieStore() {
        return getCookieJar().getCookieStore();
    }

    /**
     * 获取所有cookie
     */
    public static List<Cookie> getAllCookie() {
        CookieStore cookieStore = getCookieStore();
        List<Cookie> allCookie = cookieStore.getAllCookie();
        return allCookie;
    }

    /**
     * 获取某个url所对应的全部cookie
     */
    public static List<Cookie> getCookieByUrl(String url) {
        CookieStore cookieStore = getCookieStore();
        HttpUrl httpUrl = HttpUrl.parse(url);
        List<Cookie> cookies = cookieStore.getCookie(httpUrl);
        return cookies;
    }


    /**
     * 移除全部cookie
     */
    public static void removeAllCookie() {
        CookieStore cookieStore = getCookieStore();
        cookieStore.removeAllCookie();
    }

    /**
     * 移除某个url下的全部cookie
     */
    public static void removeCookieByUrl(String url) {
        HttpUrl httpUrl = HttpUrl.parse(url);
        CookieStore cookieStore = getCookieStore();
        cookieStore.removeCookie(httpUrl);
    }

    /**
     * 取消所有请求
     */
    public static void cancelAll() {
        RxHttpManager.get().cancelAll();
    }

    /**
     * 取消某个或某些请求
     */
    public static void cancel(Object... tag) {
        RxHttpManager.get().cancel(tag);
    }
}

package com.fly.postop.code.http.download;

import com.fly.postop.code.R;
import com.fly.postop.code.base.BaseApplication;
import com.fly.postop.code.http.HttpUtils;
import com.fly.postop.code.http.RetrofitClient;
import com.fly.postop.code.http.interceptor.Transformer;
import com.fly.postop.code.utils.ListUtils;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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
 * 包    名 : com.fly.postop.code.http.download
 * 作    者 : FLY
 * 创建时间 : 2018/9/21
 * 描述: 为下载单独建一个retrofit
 */
public class DownloadRetrofit {

    public static String DOWNLOAD_TAG = "download";
    private static DownloadRetrofit instance;
    private Retrofit mRetrofit;

    private String baseUrl = "";

    public DownloadRetrofit() {
        baseUrl = BaseApplication.getAppContext().getString(R.string.http_url_head);
        mRetrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }

    public static DownloadRetrofit getInstance() {

        if (instance == null) {
            synchronized (RetrofitClient.class) {
                if (instance == null) {
                    instance = new DownloadRetrofit();
                }
            }

        }
        return instance;
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public static Observable<ResponseBody> downloadFile(String fileUrl) {
        return DownloadRetrofit
                .getInstance()
                .getRetrofit()
                .create(DownloadApi.class)
                .downloadFile(fileUrl)
                .compose(Transformer.<ResponseBody>switchSchedulers());
    }
}

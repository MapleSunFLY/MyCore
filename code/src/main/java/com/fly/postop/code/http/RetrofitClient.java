package com.fly.postop.code.http;

import android.support.multidex.BuildConfig;

import com.fly.postop.code.R;
import com.fly.postop.code.base.BaseApplication;
import com.fly.postop.code.http.cookie.store.SPCookieStore;
import com.fly.postop.code.http.gson.GsonAdapter;
import com.fly.postop.code.http.sign.HttpSignHeader;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

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
 * 描述: RetrofitClient 创建
 */
public class RetrofitClient {
    private static RetrofitClient instance;

    private Retrofit.Builder mRetrofitBuilder;

    public RetrofitClient() {
        mRetrofitBuilder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonAdapter.buildGson()))
                .baseUrl( BaseApplication.getAppContext().getString(R.string.http_url_head));
    }


    public static RetrofitClient getInstance() {

        if (instance == null) {
            synchronized (RetrofitClient.class) {
                if (instance == null) {
                    instance = new RetrofitClient();
                }
            }

        }
        return instance;
    }


    public Retrofit.Builder getRetrofitBuilder() {
        return mRetrofitBuilder;
    }

    public Retrofit getRetrofit() {
        if (null == OkHttpConfig.getOkHttpClient()) {
            OkHttpClient okHttpClient = new OkHttpConfig.Builder()
                    //.setCache(true)
                    //.setCookieType(new SPCookieStore(BaseApplication.getAppContext()))
                    .setDebug(BuildConfig.DEBUG)
                   // .setHeaders(HttpSignHeader.getBaseHeaders())
                    .build();
            return mRetrofitBuilder.client(okHttpClient).build();
        } else {
            return mRetrofitBuilder.client(OkHttpConfig.getOkHttpClient()).build();
        }
    }
}

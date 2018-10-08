package com.shangyi.postop.http;

import android.text.TextUtils;
import com.shangyi.postop.http.cookie.CookieJarImpl;
import com.shangyi.postop.http.cookie.store.CookieStore;
import com.shangyi.postop.http.interceptor.HeaderInterceptor;
import com.shangyi.postop.http.interceptor.NetCacheInterceptor;
import com.shangyi.postop.http.interceptor.NoNetCacheInterceptor;
import com.shangyi.postop.http.interceptor.RxHttpLogger;

import java.io.File;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

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
 * 描述: 统一OkHttp配置信息
 */
public class OkHttpConfig {
    private static String defaultCachePath;
    private static final long defaultCacheSize = 1024 * 1024 * 100;
    private static final long defaultTimeout = 10;


    private static OkHttpConfig instance;

    private static OkHttpClient.Builder okHttpClientBuilder;

    private static OkHttpClient okHttpClient;

    public OkHttpConfig() {
        okHttpClientBuilder = new OkHttpClient.Builder();
    }

    public static OkHttpConfig getInstance() {

        if (instance == null) {
            synchronized (HttpClient.class) {
                if (instance == null) {
                    instance = new OkHttpConfig();
                }
            }

        }
        return instance;
    }

    public static OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    /**
     * 全局的请求头信息 .setHeaders(headerMaps)
     * 开启缓存策略(默认false).setCache(true)
     * 1、在有网络的时候，先去读缓存，缓存时间到了，再去访问网络获取数据；
     * 2、在没有网络的时候，去读缓存中的数据。
     * 全局持久话cookie .setCookieType(new SPCookieStore(this))
     * 保存到内存（new MemoryCookieStore()）
     * 或者保存到本地（new SPCookieStore(this)）默认不对cookie做处理
     * 可以添加自己的拦截器(比如使用自己熟悉三方的缓存库等等) .setAddInterceptor(null)
     * 全局ssl证书认证
     * 1、信任所有证书,不安全有风险（默认信任所有证书）
     * .setSslSocketFactory()
     * 2、使用预埋证书，校验服务端证书（自签名证书）
     * .setSslSocketFactory(cerInputStream)
     * 3、使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
     * .setSslSocketFactory(bksInputStream,"123456",cerInputStream)
     * 全局超时配置 .setReadTimeout(10)
     * 全局超时配置 .setWriteTimeout(10)
     * 全局超时配置 .setConnectTimeout(10)
     * 全局是否打开请求log日志 .setDebug(BuildConfig.DEBUG)
     */

    public static class Builder {
        private Map<String, Object> headerMaps;
        private boolean isDebug;
        private boolean isCache;
        private String cachePath;
        private long cacheMaxSize;
        private CookieStore cookieStore;
        private long readTimeout;
        private long writeTimeout;
        private long connectTimeout;
        private InputStream bksFile;
        private String password;
        private InputStream[] certificates;
        private Interceptor[] interceptors;

        public Builder setHeaders(Map<String, Object> headerMaps) {
            this.headerMaps = headerMaps;
            return this;
        }

        public Builder setDebug(boolean isDebug) {
            this.isDebug = isDebug;
            return this;
        }

        public Builder setCache(boolean isCache) {
            this.isCache = isCache;
            return this;
        }

        public Builder setCachePath(String cachePath) {
            this.cachePath = cachePath;
            return this;
        }

        public Builder setCacheMaxSize(long cacheMaxSize) {
            this.cacheMaxSize = cacheMaxSize;
            return this;
        }

        public Builder setCookieType(CookieStore cookieStore) {
            this.cookieStore = cookieStore;
            return this;
        }

        public Builder setReadTimeout(long readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        public Builder setWriteTimeout(long writeTimeout) {
            this.writeTimeout = writeTimeout;
            return this;
        }

        public Builder setConnectTimeout(long connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public Builder setAddInterceptor(Interceptor... interceptors) {
            this.interceptors = interceptors;
            return this;
        }

        public Builder setSslSocketFactory(InputStream... certificates) {
            this.certificates = certificates;
            return this;
        }

        public Builder setSslSocketFactory(InputStream bksFile, String password, InputStream... certificates) {
            this.bksFile = bksFile;
            this.password = password;
            this.certificates = certificates;
            return this;
        }


        public OkHttpClient build() {

            OkHttpConfig.getInstance();
            setCookieConfig();
            setCacheConfig();
            setHeadersConfig();
            setSslConfig();
            addInterceptors();
            setTimeout();
            setDebugConfig();

            okHttpClient = okHttpClientBuilder.build();
            return okHttpClient;
        }

        private void addInterceptors() {
            if (null != interceptors) {
                for (Interceptor interceptor : interceptors) {
                    okHttpClientBuilder.addInterceptor(interceptor);
                }
            }
        }

        /**
         * 配置开发环境
         */
        private void setDebugConfig() {
            if (isDebug) {
                HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new RxHttpLogger());
                logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                okHttpClientBuilder.addInterceptor(logInterceptor);
            }
        }


        /**
         * 配置headers
         */
        private void setHeadersConfig() {
            okHttpClientBuilder.addInterceptor(new HeaderInterceptor(headerMaps));
        }

        /**
         * 配饰cookie保存到sp文件中
         */
        private void setCookieConfig() {
            if (null != cookieStore) {
                okHttpClientBuilder.cookieJar(new CookieJarImpl(cookieStore));
            }
        }

        /**
         * 配置缓存
         */
        private void setCacheConfig() {
            defaultCachePath = HttpUtils.getContext().getExternalCacheDir().getPath() + "/RxHttpCacheData";
            if (isCache) {
                Cache cache;
                if (!TextUtils.isEmpty(cachePath) && cacheMaxSize > 0) {
                    cache = new Cache(new File(cachePath), cacheMaxSize);
                } else {
                    cache = new Cache(new File(defaultCachePath), defaultCacheSize);
                }
                okHttpClientBuilder
                        .cache(cache)
                        .addInterceptor(new NoNetCacheInterceptor())
                        .addNetworkInterceptor(new NetCacheInterceptor());
            }
        }

        /**
         * 配置超时信息
         */
        private void setTimeout() {
            okHttpClientBuilder.readTimeout(readTimeout == 0 ? defaultTimeout : readTimeout, TimeUnit.SECONDS);
            okHttpClientBuilder.writeTimeout(writeTimeout == 0 ? defaultTimeout : writeTimeout, TimeUnit.SECONDS);
            okHttpClientBuilder.connectTimeout(connectTimeout == 0 ? defaultTimeout : connectTimeout, TimeUnit.SECONDS);
            okHttpClientBuilder.retryOnConnectionFailure(true);
        }

        /**
         * 配置证书
         */
        private void setSslConfig() {
            SSLUtils.SSLParams sslParams = null;

            if (null == certificates) {
                //信任所有证书,不安全有风险
                sslParams = SSLUtils.getSslSocketFactory();
            } else {
                if (null != bksFile && !TextUtils.isEmpty(password)) {
                    //使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
                    sslParams = SSLUtils.getSslSocketFactory(bksFile, password, certificates);
                } else {
                    //使用预埋证书，校验服务端证书（自签名证书）
                    sslParams = SSLUtils.getSslSocketFactory(certificates);
                }
            }

            okHttpClientBuilder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);

        }
    }
}

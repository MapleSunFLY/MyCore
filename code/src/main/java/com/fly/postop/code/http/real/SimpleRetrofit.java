package com.fly.postop.code.http.real;

import android.support.v4.util.ArrayMap;
import android.text.TextUtils;

import com.fly.postop.code.common.CommonObserver;
import com.fly.postop.code.http.GlobalRxHttp;
import com.fly.postop.code.http.ResponseJson;
import com.fly.postop.code.http.interceptor.Transformer;
import com.fly.postop.code.http.interf.ApiService;
import com.fly.postop.code.http.interf.IResultHelper;
import com.fly.postop.code.http.interf.RestMethod;

import java.lang.reflect.Type;
import java.util.Map;

import io.reactivex.Observable;

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
 * 包    名 : com.fly.postop.code.http.real
 * 作    者 : FLY
 * 创建时间 : 2018/9/25
 * 描述: 常用的请求操作
 */
public class SimpleRetrofit<T> {

    public static Class resultHelperClass = ResultHelper.class;

    public static <T> SimpleRetrofit<T> builder() {
        SimpleRetrofit<T> request = new SimpleRetrofit<T>();
        return request;
    }

    public Observable<ResponseJson<T>> doHttp(String url, String restMethod, CommonObserver<ResponseJson<T>> commonObserver, Type toJsonType) {
        return this.doHttp(url, restMethod, null, null, null, commonObserver, toJsonType);
    }

    public Observable<ResponseJson<T>> doHttp(String url, String restMethod, Object object, CommonObserver<ResponseJson<T>> commonObserver, Type toJsonType) {
        return this.doHttp(url, restMethod, null, object, null, commonObserver, toJsonType);
    }

    public Observable<ResponseJson<T>> doHttp(String url, String restMethod, Map<String, String> params, CommonObserver<ResponseJson<T>> commonObserver, Type toJsonType) {
        return this.doHttp(url, restMethod, params, null, null, commonObserver, toJsonType);
    }

    public Observable<ResponseJson<T>> doHttp(String url, String restMethod, Object object, Map<String, String> headers, CommonObserver<ResponseJson<T>> commonObserver, Type toJsonType) {
        return this.doHttp(url, restMethod, null, object, headers, commonObserver, toJsonType);
    }

    public Observable<ResponseJson<T>> doHttp(String url, String restMethod, Map<String, String> params, Map<String, String> headers, CommonObserver<ResponseJson<T>> commonObserver, Type toJsonType) {
        return this.doHttp(url, restMethod, params, null, headers, commonObserver, toJsonType);
    }

    public Observable<ResponseJson<T>> doHttp(String url, String restMethod, Map<String, String> params, Object object, Map<String, String> headers, CommonObserver<ResponseJson<T>> commonObserver, Type toJsonType) {
        if (RestMethod.POST.equalsIgnoreCase(restMethod)) {
            if (object != null) this.doPost(url, object, headers, commonObserver, toJsonType);
            return this.doPost(url, params, headers, commonObserver, toJsonType);
        } else if (RestMethod.PUT.equalsIgnoreCase(restMethod)) {
            if (object != null) this.doPut(url, object, headers, commonObserver, toJsonType);
            return this.doPut(url, params, headers, commonObserver, toJsonType);
        } else if (RestMethod.DELETE.equalsIgnoreCase(restMethod)) {
            if (object != null) this.doDelete(url, object, headers, commonObserver, toJsonType);
            return this.doDelete(url, params, headers, commonObserver, toJsonType);
        } else {
            return this.doGet(url, params, headers, commonObserver, toJsonType);
        }
    }

    public Observable<ResponseJson<T>> doGet(String url, CommonObserver<ResponseJson<T>> commonObserver, Type toJsonType) {
        return this.doGet(url, null, commonObserver, toJsonType);
    }

    public Observable<ResponseJson<T>> doGet(String url, Map<String, String> params, CommonObserver<ResponseJson<T>> commonObserver, Type toJsonType) {
        return this.doGet(url, params, null, commonObserver, toJsonType);
    }

    public Observable<ResponseJson<T>> doGet(String url, Map<String, String> params, Map<String, String> headers, CommonObserver<ResponseJson<T>> commonObserver, Type toJsonType) {

        if (!TextUtils.isEmpty(url) && url.startsWith("/")) {
            url = url.substring(1);
        }
        if (params == null) params = new ArrayMap<>();


        if (headers == null) headers = new ArrayMap<>();

        //headers.put(HttpSignHeader.X_CA_DEVICE_TOKEN, SPUtils.get(BaseConfig.TOKEN, ""));
        Observable<ResponseJson<T>> observable = GlobalRxHttp.createGApi(ApiService.class)
                .doGet(url, params, headers)
                .compose(getIResultHelper().<T>transfomer(toJsonType));
        observable.compose(Transformer.<ResponseJson<T>>switchSchedulers())
                .subscribe(commonObserver);
        return observable;
    }

    public Observable<ResponseJson<T>> doPost(String url, CommonObserver<ResponseJson<T>> commonObserver, Type toJsonType) {
        return this.doPost(url, null, commonObserver, toJsonType);
    }

    public Observable<ResponseJson<T>> doPost(String url, Map<String, String> params, CommonObserver<ResponseJson<T>> commonObserver, Type toJsonType) {
        return this.doPost(url, params, null, commonObserver, toJsonType);
    }

    public Observable<ResponseJson<T>> doPost(String url, Object object, CommonObserver<ResponseJson<T>> commonObserver, Type toJsonType) {
        return this.doPost(url, object, null, commonObserver, toJsonType);
    }

    public Observable<ResponseJson<T>> doPost(String url, Map<String, String> params, Map<String, String> headers, CommonObserver<ResponseJson<T>> commonObserver, Type toJsonType) {
        if (params == null) {
            params = new ArrayMap<>();
        }

        if (headers == null) {
            headers = new ArrayMap<>();
        }
        Observable<ResponseJson<T>> observable = GlobalRxHttp.createGApi(ApiService.class)
                .doPost(url, params, headers)
                .compose(getIResultHelper().<T>transfomer(toJsonType));
        observable.compose(Transformer.<ResponseJson<T>>switchSchedulers())
                .subscribe(commonObserver);
        return observable;
    }


    public Observable<ResponseJson<T>> doPost(String url, Object object, Map<String, String> headers, CommonObserver<ResponseJson<T>> commonObserver, Type toJsonType) {
        if (object == null) {
            object = new Object();
        }
        if (headers == null) {
            headers = new ArrayMap<>();
        }
        Observable<ResponseJson<T>> observable = GlobalRxHttp.createGApi(ApiService.class)
                .doPost(url, object, headers)
                .compose(getIResultHelper().<T>transfomer(toJsonType));
        observable.compose(Transformer.<ResponseJson<T>>switchSchedulers())
                .subscribe(commonObserver);
        return observable;
    }

    public Observable<ResponseJson<T>> doPut(String url, CommonObserver<ResponseJson<T>> commonObserver, Type toJsonType) {
        return this.doPut(url, null, commonObserver, toJsonType);
    }

    public Observable<ResponseJson<T>> doPut(String url, Map<String, String> params, CommonObserver<ResponseJson<T>> commonObserver, Type toJsonType) {
        return this.doPut(url, params, null, commonObserver, toJsonType);
    }

    public Observable<ResponseJson<T>> doPut(String url, Object object, CommonObserver<ResponseJson<T>> commonObserver, Type toJsonType) {
        return this.doPut(url, object, null, commonObserver, toJsonType);
    }

    public Observable<ResponseJson<T>> doPut(String url, Map<String, String> params, Map<String, String> headers, CommonObserver<ResponseJson<T>> commonObserver, Type toJsonType) {
        if (params == null) {
            params = new ArrayMap<>();
        }

        if (headers == null) {
            headers = new ArrayMap<>();
        }
        Observable<ResponseJson<T>> observable = GlobalRxHttp.createGApi(ApiService.class)
                .doPut(url, params, headers)
                .compose(getIResultHelper().<T>transfomer(toJsonType));
        observable.compose(Transformer.<ResponseJson<T>>switchSchedulers())
                .subscribe(commonObserver);
        return observable;
    }


    public Observable<ResponseJson<T>> doPut(String url, Object object, Map<String, String> headers, CommonObserver<ResponseJson<T>> commonObserver, Type toJsonType) {
        if (object == null) {
            object = new Object();
        }
        if (headers == null) {
            headers = new ArrayMap<>();
        }
        Observable<ResponseJson<T>> observable = GlobalRxHttp.createGApi(ApiService.class)
                .doPut(url, object, headers)
                .compose(getIResultHelper().<T>transfomer(toJsonType));
        observable.compose(Transformer.<ResponseJson<T>>switchSchedulers())
                .subscribe(commonObserver);
        return observable;
    }

    public Observable<ResponseJson<T>> doDelete(String url, CommonObserver<ResponseJson<T>> commonObserver, Type toJsonType) {
        return this.doDelete(url, null, commonObserver, toJsonType);
    }

    public Observable<ResponseJson<T>> doDelete(String url, Map<String, String> params, CommonObserver<ResponseJson<T>> commonObserver, Type toJsonType) {
        return this.doDelete(url, params, null, commonObserver, toJsonType);
    }

    public Observable<ResponseJson<T>> doDelete(String url, Object object, CommonObserver<ResponseJson<T>> commonObserver, Type toJsonType) {
        return this.doDelete(url, object, null, commonObserver, toJsonType);
    }

    public Observable<ResponseJson<T>> doDelete(String url, Map<String, String> params, Map<String, String> headers, CommonObserver<ResponseJson<T>> commonObserver, Type toJsonType) {
        if (params == null) {
            params = new ArrayMap<>();
        }

        if (headers == null) {
            headers = new ArrayMap<>();
        }
        Observable<ResponseJson<T>> observable = GlobalRxHttp.createGApi(ApiService.class)
                .doDelete(url, params, headers)
                .compose(getIResultHelper().<T>transfomer(toJsonType));
        observable.compose(Transformer.<ResponseJson<T>>switchSchedulers())
                .subscribe(commonObserver);
        return observable;
    }


    public Observable<ResponseJson<T>> doDelete(String url, Object object, Map<String, String> headers, CommonObserver<ResponseJson<T>> commonObserver, Type toJsonType) {
        if (object == null) {
            object = new Object();
        }
        if (headers == null) {
            headers = new ArrayMap<>();
        }
        Observable<ResponseJson<T>> observable = GlobalRxHttp.createGApi(ApiService.class)
                .doDelete(url, object, headers)
                .compose(getIResultHelper().<T>transfomer(toJsonType));
        observable.compose(Transformer.<ResponseJson<T>>switchSchedulers())
                .subscribe(commonObserver);
        return observable;
    }

    private IResultHelper getIResultHelper() {
        IResultHelper helper = null;
        try {
            if (resultHelperClass.newInstance() instanceof IResultHelper) {
                helper = (IResultHelper) resultHelperClass.newInstance();
            }
            if (helper == null) {
                helper = new ResultHelper();
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return helper;
    }
}

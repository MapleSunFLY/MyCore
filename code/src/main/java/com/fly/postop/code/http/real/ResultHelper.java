package com.fly.postop.code.http.real;

import com.fly.postop.code.http.ResponseJson;
import com.fly.postop.code.http.interf.IResultHelper;
import com.fly.postop.code.utils.GsonUtils;

import java.lang.reflect.Type;
import java.util.logging.Logger;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

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
 * 创建时间 : 2018/9/25
 * 描述: 数据解析实现
 */
public class ResultHelper implements IResultHelper {

    @Override
    public <T> ObservableTransformer<String, ResponseJson<T>> transfomer(final Type toJsonType) {
        return new ObservableTransformer<String, ResponseJson<T>>() {
            @Override
            public ObservableSource<ResponseJson<T>> apply(Observable<String> upstream) {
                return upstream.flatMap(new Function<String, ObservableSource<ResponseJson<T>>>() {
                    @Override
                    public ObservableSource<ResponseJson<T>> apply(String result) throws Exception {
                        ResponseJson<T> responseJson = GsonUtils.fromJson(result, toJsonType);
                        Logger.getLogger(responseJson.toJsonString());
                        return Observable.just(responseJson);
                    }
                });
            }
        };
    }

}

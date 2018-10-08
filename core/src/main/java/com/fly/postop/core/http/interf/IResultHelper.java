package com.fly.postop.core.http.interf;

import com.fly.postop.core.http.ResponseJson;

import java.lang.reflect.Type;

import io.reactivex.ObservableTransformer;

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
 * 包    名 : com.fly.postop.code.http.interf
 * 作    者 : FLY
 * 创建时间 : 2018/9/25
 * 描述: 数据解析接口
 */
public interface IResultHelper {

    <T> ObservableTransformer<String, ResponseJson<T>> transfomer(Type toJsonType);
}

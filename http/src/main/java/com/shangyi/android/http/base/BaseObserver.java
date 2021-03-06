package com.shangyi.android.http.base;

import android.text.TextUtils;

import com.shangyi.android.http.HttpUtils;
import com.shangyi.android.http.exception.ApiException;
import com.shangyi.android.http.interf.ISubscriber;
import com.shangyi.android.http.manage.RxHttpManager;
import com.shangyi.android.utils.GsonUtils;
import com.shangyi.android.utils.LogUtils;
import com.shangyi.android.utils.ToastUtils;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

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
 * 创建时间 : 2018/9/25
 * 描述:
 */
public abstract class BaseObserver<T> implements Observer<T>, ISubscriber<T> {

    /**
     * 是否隐藏toast
     *
     * @return
     */
    protected boolean isHideToast() {
        return false;
    }

    /**
     * 标记网络请求的tag
     * tag下的一组或一个请求，用来处理一个页面的所以请求或者某个请求
     * 设置一个tag就行就可以取消当前页面所有请求或者某个请求了
     *
     * @return string
     */
    protected String setTag() {
        return BaseConfig.HTTP_COMMIT_TAG;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        RxHttpManager.get().add(setTag(), d);
        doOnSubscribe(d);
    }

    @Override
    public void onNext(@NonNull T t) {
        LogUtils.jsonFormatterLog("FLY-Next：", GsonUtils.toJson(t));
        doOnNext(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        String error = ApiException.handleException(e).getMessage();
        LogUtils.e("FLY-Error：", error);
        if (!isHideToast() && !TextUtils.isEmpty(error)) {
            ToastUtils.showErrorTost(HttpUtils.getContext(), error);
        }
        doOnError(error);
    }


    @Override
    public void onComplete() {
        doOnCompleted();
    }
}

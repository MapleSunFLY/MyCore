package com.fly.postop.code.common;

import android.text.TextUtils;

import com.fly.postop.code.base.BaseApplication;
import com.fly.postop.code.base.BaseObserver;
import com.fly.postop.code.utils.ToastUtils;

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
 * 描述: 常用回调
 */
public abstract class CommonObserver<T> extends BaseObserver<T> {


    /**
     * 失败回调
     *
     * @param errorMsg
     */
    protected abstract void onError(String errorMsg);

    /**
     * 成功回调
     *
     * @param t
     */
    protected abstract void onSuccess(T t);


    @Override
    public void doOnSubscribe(Disposable d) {
    }

    @Override
    public void doOnError(String errorMsg) {
        if (!isHideToast() && !TextUtils.isEmpty(errorMsg)) {
            ToastUtils.showErrorTost(BaseApplication.getAppContext(), errorMsg);
        }
        onError(errorMsg);
    }

    @Override
    public void doOnNext(T t) {
        onSuccess(t);
    }

    @Override
    public void doOnCompleted() {
    }

}


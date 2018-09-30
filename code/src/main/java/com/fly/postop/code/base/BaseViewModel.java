package com.fly.postop.code.base;

import android.arch.lifecycle.ViewModel;
import android.text.TextUtils;

import com.fly.postop.code.http.HttpUtils;
import com.fly.postop.code.http.ResponseJson;
import com.fly.postop.code.utils.ToastUtils;

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
 * 创建时间 : 2018/9/16
 * 描述:
 */

public class BaseViewModel extends ViewModel {
    protected BaseActivity mActivity;

    protected String mHttpTag;

    @Override
    protected void onCleared() {
        super.onCleared();
        onDestroy();
    }

    protected void sendError(String e) {
        ToastUtils.showErrorTost(BaseApplication.getAppContext(), e);
    }

    public static String getStringValue(String s) {
        return TextUtils.isEmpty(s) ? "" : s;
    }

    public static String getString(Integer s) {
        return BaseApplication.getAppContext().getString(s);
    }

    public void setActivity(BaseActivity mActivity) {
        this.mActivity = mActivity;
    }

    public boolean checkSuccess(ResponseJson r) {
        if (mActivity != null) mActivity.setProgressVisible(false);
        if (r.isSuccess()) {
            return true;
        } else {
            sendError(r.info);

        }
        return false;
    }

    public void onDestroy() {
        HttpUtils.cancel(TextUtils.isEmpty(mHttpTag) ? BaseConfig.HTTP_COMMIT_TAG : mHttpTag);
    }
}

package com.fly.postop.core.base;

import android.arch.lifecycle.ViewModelProviders;

import com.fly.postop.core.utils.LogUtils;

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
 * 创建时间 : 2018/9/29
 * 描述: LiveDataActivity 基类
 */
public class BaseLiveDataActivity<T extends BaseViewModel> extends BaseActivity {
    protected T mViewModel;

    public void initViewModel(Class<T> modelClass) {
        this.mViewModel = registerViewModel(modelClass, false);
    }

    public void initViewModel(Class<T> modelClass, String viewModelName) {
        this.mViewModel = registerViewModel(modelClass, viewModelName);
    }

    public void initViewModel(Class<T> modelClass, boolean isSingle) {
        this.mViewModel = registerViewModel(modelClass, isSingle);
    }

    public <Q extends BaseViewModel> Q registerViewModel(Class<Q> modelClass, boolean isSingle) {
        String key = modelClass.getCanonicalName();
        if (key == null) {
            key = getClass().getCanonicalName();
        }
        return registerViewModel(modelClass, isSingle ? (this.toString() + ":" + key) : key);
    }

    public <Q extends BaseViewModel> Q registerViewModel(Class<Q> modelClass, String viewModelName) {
        Q mViewModel = ViewModelProviders.of(this).get(viewModelName, modelClass);
        LogUtils.d("-----------------A-----------------------");
        LogUtils.d("-A--modelClass:" + modelClass + " viewModelName:" + viewModelName + " obj:" + (mViewModel != null ? mViewModel.toString() : null));
        LogUtils.d("----------------------------------------");
        mViewModel.setActivity(getActivity());
        return mViewModel;
    }

    public <Q extends BaseViewModel> Q registerViewModel(Class<Q> modelClass) {
        return registerViewModel(modelClass, true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

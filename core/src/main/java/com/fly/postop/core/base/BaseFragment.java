package com.fly.postop.core.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.fly.postop.core.R;
import com.fly.postop.core.utils.LogUtils;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.lang.reflect.Field;

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
 * 描述:Fragment 基类
 */
public class BaseFragment extends Fragment {

    @Nullable
    protected Toolbar mToolbar;
    @Nullable
    protected AppBarLayout mAppBarLayout;

    protected BaseActivity baseActivity;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            baseActivity = (BaseActivity) context;
        }
    }

    @Override
    @Subscribe
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册EventBus
        EventBus.getDefault().register(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mToolbar = view.findViewById(R.id.toolbar);
        if (mToolbar == null) {
            mToolbar = getActivity().findViewById(R.id.toolbar);
        }

        mAppBarLayout = view.findViewById(R.id.appbar);
        if (mAppBarLayout == null) {
            mAppBarLayout = getActivity().findViewById(R.id.appbar);
        }

        if (getActivity().getIntent() != null && getActivity().getIntent().hasExtra(Intent.EXTRA_TITLE)) {
            setTitle(getActivity().getIntent().getStringExtra(Intent.EXTRA_TITLE));
        }
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void onDestroy() {
        //反注册EventBus
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    protected int getColor(@ColorRes int resId) {
        return getResources().getColor(resId);
    }

    public void setProgressVisible(boolean isVisible) {
        BaseActivity baseActivity = (BaseActivity) getActivity();
        if (baseActivity != null)
            baseActivity.setProgressVisible(isVisible);
    }

    public void setProgressVisible(boolean isVisible, String content) {
        if (baseActivity != null)
            baseActivity.setProgressVisible(isVisible, content);
    }

    public BaseActivity getBaseActivity() {
        return baseActivity;
    }

    public void setTitle(@StringRes int resId) {
        if (null != mToolbar)
            mToolbar.setTitle(resId);
    }

    public void setTitle(String resId) {
        if (null != mToolbar)
            mToolbar.setTitle(resId);
    }

    protected void addItemDecorationLine(RecyclerView recyclerView) {
        this.addItemDecorationLine(recyclerView, 1);
    }

    protected void addItemDecorationLine(RecyclerView recyclerView, int size) {
        this.addItemDecorationLine(recyclerView, size, R.color.common_background_color);
    }

    protected void addItemDecorationLine(RecyclerView recyclerView, int size, @ColorRes int colorId) {
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(recyclerView.getContext())
                .colorResId(colorId).size(size)
                .showLastDivider().build());
    }

    public void onResume() {
        super.onResume();
        // MobclickAgent.onPageStart(this.getClass().getName());
        LogUtils.jsonFormatterLog("", this.getClass().getSimpleName().toString());
    }

    public void onPause() {
        super.onPause();
        //  MobclickAgent.onPageEnd(this.getClass().getName());
    }


    public <T extends View> T findViewById(@IdRes int id) {
        if (getView() != null)
            return getView().findViewById(id);
        return null;
    }

    protected <T extends View> T getView(Activity view, @IdRes int resId) {
        T t = (T) view.findViewById(resId);
        if (t == null) {
            return null;
        }
        return t;
    }

    public boolean onBackPressed() {
        return true;
    }
}

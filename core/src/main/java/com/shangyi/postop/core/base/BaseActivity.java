package com.shangyi.postop.core.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shangyi.postop.core.R;
import com.shangyi.postop.utils.LogUtils;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import io.reactivex.functions.Consumer;

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
 * 创建时间 : 2018/9/28
 * 描述: activity基类
 */

@SuppressWarnings("deprecation")
public class BaseActivity extends AppCompatActivity {

    protected final String TAG = "FLY." + this.getClass().getSimpleName();

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private ViewGroup rootView;
    protected View mProgressView;

    protected AppBarLayout mAppBarLayout;
    @Nullable
    protected Toolbar mToolbar;
    private RxPermissions mRxPermission;


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        mToolbar = findViewById(R.id.toolbar);
        setToolbarBackDrawable(mToolbar);
        rootView = (ViewGroup) getWindow().getDecorView();
        initProgressLayout();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        mToolbar = findViewById(R.id.toolbar);
        setToolbarBackDrawable(mToolbar);
        rootView = (ViewGroup) getWindow().getDecorView();
        initProgressLayout();
    }

    @Override
    @Subscribe
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册EventBus
        EventBus.getDefault().register(this);
        //umeng配置
//        MobclickAgent.setDebugMode(true);
//        MobclickAgent.openActivityDurationTrack(false);
//        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPageEnd(this.getClass().getName());
//        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        MobclickAgent.onPageStart(this.getClass().getName());
//        MobclickAgent.onResume(this);
    }

    @Override
    protected void onDestroy() {
        //反注册EventBus
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public RxPermissions getRxPermission() {
        if (mRxPermission == null)
            mRxPermission = new RxPermissions(getActivity());
        return mRxPermission;
    }

    public void requestPermission(String[] strings) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            getRxPermission().requestEach(strings).subscribe(new Consumer<Permission>() {

                @Override
                public void accept(Permission permission) throws Exception {
                    if (permission.granted) {
                        // 用户已经同意该权限
                        LogUtils.d(TAG, permission.name + " is granted.");
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                        LogUtils.d(TAG, permission.name + " is denied. More info should be provided.");
                    } else {
                        // 用户拒绝了该权限，并且选中『不再询问』
                        LogUtils.d(TAG, permission.name + " is denied.");
                    }
                }
            });

        }
    }

    public void setToolbarBackDrawable(Toolbar mToolbar) {
        mAppBarLayout = findViewById(R.id.appbar);
        if (null != mToolbar) {
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
        if (null != mAppBarLayout && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            View.inflate(getActivity(), R.layout.commit_line_dark, mAppBarLayout);
        }
    }

    protected void initProgressLayout() {
        if (mProgressView == null) {
            mProgressView = getLayoutInflater().inflate(R.layout.base_loading_layout, rootView
                    , false);
            mProgressView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            setProgressVisible(false);
            rootView.addView(mProgressView);
        }
    }

    public void setProgressVisible(boolean show) {
        this.setProgressVisible(show, "");
    }

    public void setProgressVisible(boolean show, String content) {
        if (mProgressView != null) {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            ((TextView) mProgressView.findViewById(R.id.tvLoading)).setText(content);
        }
    }

    public int getColors(@ColorRes int resId) {
        return getResources().getColor(resId);
    }

    public void setRootView(ViewGroup rootView) {
        this.rootView = rootView;
    }

    public BaseActivity getActivity() {
        return this;
    }
}

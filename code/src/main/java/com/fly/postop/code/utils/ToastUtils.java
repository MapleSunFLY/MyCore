package com.fly.postop.code.utils;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fly.postop.code.R;
import com.fly.postop.code.base.BaseApplication;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;

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
 * 包    名 : com.fly.postop.code.utils
 * 作    者 : FLY
 * 创建时间 : 2018/9/21
 * 描述: Toast工具类
 */
public class ToastUtils extends Toast {

    private static TextView mTV;
    private static View mIVIcon;

    public ToastUtils(Context context) {
        super(context);
    }

    /**
     * Make a standard toast that just contains a text view.
     *
     * @param context  The context to use. Usually your
     *                 {@link android.app.Application} or
     *                 {@link android.app.Activity} object.
     * @param text     The text to show. Can be formatted text.
     * @param duration How long to display the message. Either {@link #LENGTH_SHORT}
     *                 or {@link #LENGTH_LONG}
     */
    public static ToastUtils makeText(Context context, CharSequence text,
                                      int duration) {

        ToastUtils result = new ToastUtils(context.getApplicationContext());

        // 由layout文件创建一个View对象
        View layout = View.inflate(context, R.layout.utils_toast_layout, null);

        mTV = (TextView) layout.findViewById(R.id.tvContent);

        mIVIcon = layout.findViewById(R.id.ivIcon);

        mTV.setText(text);
        mTV.setTextColor(Color.WHITE);

        result.setView(layout);
        result.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        result.setDuration(duration);

        return result;
    }

    public void setText(CharSequence s) {
        mTV.setText(s);
    }

    public static ToastUtils mToast = null;

    public static void showTost(Context ct, String content) {

        if (ct == null) {
            return;
        }

        if (mToast == null) {
            mToast = ToastUtils.makeText(ct.getApplicationContext(), "", Toast.LENGTH_SHORT);
        }

        if (!TextUtils.isEmpty(content)) {
            mToast.setText(content);
            mIVIcon.setVisibility(View.GONE);
            mToast.show();
        }

    }


    public static void showTost(String content) {
        if (mToast == null) {
            mToast = ToastUtils.makeText(BaseApplication.getAppContext(), "", Toast.LENGTH_SHORT);
        }

        if (!TextUtils.isEmpty(content)) {
            mToast.setText(content);
            mIVIcon.setVisibility(View.GONE);
            mToast.show();
        }

    }

    public static void showTost(Context ct, String content, int flag) {

        if (mToast == null) {
            mToast = ToastUtils.makeText(ct.getApplicationContext(), "", Toast.LENGTH_SHORT);
        }

        if (!TextUtils.isEmpty(content)) {
            mToast.setText(content);
            mIVIcon.setVisibility(View.VISIBLE);
            mToast.show();
        }
    }

    public static void showErrorTost(Context ct, String content) {
        if (mToast == null) {
            mToast = ToastUtils.makeText(ct.getApplicationContext(), "", Toast.LENGTH_SHORT);
        }

        if (!TextUtils.isEmpty(content)) {
            mToast.setText(content);
            mIVIcon.setVisibility(View.VISIBLE);
            mToast.show();
        }
    }
}
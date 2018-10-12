package com.shangyi.android.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v7.content.res.AppCompatResources;

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
 * 创建时间 : 2018/9/29
 * 描述: Drawable 工具类
 */
public class DrawableUtils {

    private static Context getContent() {
        return Utils.getInstance().getContext();
    }

    static final int[] CHECKED_STATE_SET = new int[]{android.R.attr.state_checked};
    static final int[] EMPTY_STATE_SET = new int[0];

    private static final String VECTOR_DRAWABLE_CLAZZ_NAME
            = "android.graphics.drawable.VectorDrawable";

    static void fixDrawable(@NonNull final Drawable drawable) {
        if (Build.VERSION.SDK_INT == 21
                && VECTOR_DRAWABLE_CLAZZ_NAME.equals(drawable.getClass().getName())) {
            fixVectorDrawableTinting(drawable);
        }
    }

    private static void fixVectorDrawableTinting(final Drawable drawable) {
        final int[] originalState = drawable.getState();
        if (originalState == null || originalState.length == 0) {
            // The drawable doesn't have a state, so set it to be checked
            drawable.setState(CHECKED_STATE_SET);
        } else {
            // Else the drawable does have a state, so clear it
            drawable.setState(EMPTY_STATE_SET);
        }
        // Now set the original state
        drawable.setState(originalState);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static Drawable getDrawable(Context context, int resId) {
        Drawable d;
        try {
            d = AppCompatResources.getDrawable(context, resId);
            DrawableUtils.fixDrawable(d);
        } catch (Resources.NotFoundException e) {
            d = context.getResources().getDrawable(resId);
        }
        return d;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static Drawable getDrawableWithBounds(Context context, int resId) {
        Drawable d;
        try {
            d = AppCompatResources.getDrawable(context, resId);
            DrawableUtils.fixDrawable(d);
        } catch (Resources.NotFoundException e) {
            d = context.getResources().getDrawable(resId);
        }
        d.setBounds(0, 0, d.getMinimumWidth(), d.getMinimumHeight());
        return d;
    }


    public static GradientDrawable createShapeDrawable(int color, int corner) {
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(color);
        gd.setCornerRadius(ViewUtils.dip2px(getContent(), corner));
        return gd;
    }

    public static GradientDrawable createShapeDrawableResource(@ColorRes int color, int corner) {
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(getContent().getResources().getColor(color));
        gd.setCornerRadius(ViewUtils.dip2px(getContent(), corner));
        return gd;
    }

    public static GradientDrawable createLineDrawable(int color, int height) {
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(color);
        gd.setStroke(height, color);
        gd.setShape(GradientDrawable.LINE);
        return gd;
    }

    public static GradientDrawable createShapeStrokeDrawable(@ColorRes int color, @ColorRes int strokeColor, int corner) {
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(getContent().getResources().getColor(color));
        gd.setStroke(2, getContent().getResources().getColor(strokeColor));
        gd.setCornerRadius(ViewUtils.dip2px(getContent(), corner));
        return gd;
    }

    public static GradientDrawable createShapeStrokeDrawable(@ColorRes int color, @ColorRes int strokeColor, int width, int corner) {
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(getContent().getResources().getColor(color));
        gd.setStroke(width, getContent().getResources().getColor(strokeColor));
        gd.setCornerRadius(ViewUtils.dip2px(getContent(), corner));
        return gd;
    }


    public static GradientDrawable createShapeWithStrokeDrawable(int color, int strokeColor, int corner) {
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(color);
        gd.setStroke(2, strokeColor);
        gd.setCornerRadius(ViewUtils.dip2px(getContent(), corner));
        return gd;
    }

    public static GradientDrawable createCycleShapeWithStrokeDrawable(int color, int strokeColor, int corner) {
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(color);
        gd.setShape(GradientDrawable.OVAL);
        gd.setSize(
                ViewUtils.dip2px(getContent(), corner) * 2,
                ViewUtils.dip2px(getContent(), corner) * 2);
        gd.setStroke(2, strokeColor);
        gd.setCornerRadius(ViewUtils.dip2px(getContent(), corner));
        return gd;
    }

    public static StateListDrawable newSelector(Drawable idNormal, Drawable idPressed) {
        StateListDrawable bg = new StateListDrawable();
        //Drawable disable = getDrawable(R.drawable.shape_btn_background_disable);
        bg.addState(new int[]{android.R.attr.state_selected, android.R.attr.state_enabled}, idPressed);
        bg.addState(new int[]{android.R.attr.state_checked, android.R.attr.state_enabled}, idPressed);

        bg.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled}, idPressed);
        //bg.addState(new int[]{-android.R.attr.state_enabled}, disable);
        bg.addState(new int[]{}, idNormal);
        return bg;
    }

}

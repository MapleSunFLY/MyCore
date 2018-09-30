package com.fly.postop.code.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.fly.postop.code.R;

import java.text.DecimalFormat;

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
 * 包    名 : com.fly.postop.code.view
 * 作    者 : FLY
 * 创建时间 : 2018/9/29
 * 描述: 更新进度条
 */
public class UpdateAPPProgressBar extends View {

    /**
     * 进度条最大值
     */
    private float mMax = 100;

    /**
     * 进度条当前进度值
     */
    private float mProgress = 0;

    /**
     * 默认已完成颜色
     */
    private final int DEFAULT_FINISHED_COLOR = getResources().getColor(R.color.update_app_progress_bar_default_finished_color);

    /**
     * 默认未完成颜色
     */
    private final int DEFAULT_UNFINISHED_COLOR = getResources().getColor(R.color.update_app_progress_bar_default_unfinished_color);

    /**
     * 已完成进度颜色
     */
    private int mReachedBarColor;

    /**
     * 未完成进度颜色
     */
    private int mUnreachedBarColor;

    /**
     * 进度条高度
     */
    private float mBarHeight;

    /**
     * the progress text color.
     */
    private int mTextColor;

    /**
     * 后缀
     */
    private String mSuffix = "%";

    /**
     * 前缀
     */
    private String mPrefix = "";

    /**
     * 未完成进度条所在矩形区域
     */
    private RectF mUnreachedRectF = new RectF(0, 0, 0, 0);
    /**
     * 已完成进度条所在矩形区域
     */
    private RectF mReachedRectF = new RectF(0, 0, 0, 0);

    /**
     * 画笔
     */
    private Paint mPaint;

    private boolean mTextVisibility;


    public UpdateAPPProgressBar(Context context) {
        this(context, null);
    }

    public UpdateAPPProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UpdateAPPProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        initPainters();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.UpdateAPPProgressBar);
        mMax = typedArray.getInteger(R.styleable.UpdateAPPProgressBar_updataAPPMax, (int) mMax);
        mProgress = typedArray.getInteger(R.styleable.UpdateAPPProgressBar_updataAPPProgress, (int) mProgress);
        mReachedBarColor = typedArray.getColor(R.styleable.UpdateAPPProgressBar_updataAPPReachedBarColor, DEFAULT_FINISHED_COLOR);
        mUnreachedBarColor = typedArray.getColor(R.styleable.UpdateAPPProgressBar_updataAPPUnreachedBarColor, DEFAULT_UNFINISHED_COLOR);
        mTextColor = typedArray.getColor(R.styleable.UpdateAPPProgressBar_updataAPPTextColor, DEFAULT_UNFINISHED_COLOR);
        mSuffix = TextUtils.isEmpty(typedArray.getString(R.styleable.UpdateAPPProgressBar_updataAPPSuffix)) ? mSuffix : typedArray.getString(R.styleable.UpdateAPPProgressBar_updataAPPSuffix);
        mPrefix = TextUtils.isEmpty(typedArray.getString(R.styleable.UpdateAPPProgressBar_updataAPPPrefix)) ? mPrefix : typedArray.getString(R.styleable.UpdateAPPProgressBar_updataAPPPrefix);
        mBarHeight = typedArray.getDimension(R.styleable.UpdateAPPProgressBar_updataAPPBarHeight, dp2px(2f));
        mTextVisibility = typedArray.getBoolean(R.styleable.UpdateAPPProgressBar_updataAPPTextVisibility, true);
        typedArray.recycle();
    }

    private void initPainters() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//抗锯齿
        mPaint.setAntiAlias(true);//防抖动
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        calculateDrawRectFWithoutProgressText();
        mPaint.setColor(mUnreachedBarColor);
        canvas.drawRoundRect(mUnreachedRectF, mBarHeight / 2, mBarHeight / 2, mPaint);

        mPaint.setColor(mReachedBarColor);
        canvas.drawRoundRect(mReachedRectF, mBarHeight / 2, mBarHeight / 2, mPaint);

        if (mTextVisibility && getProgress() > 0) {
            mPaint.setColor(mTextColor);
            mPaint.setTextSize(mBarHeight * 0.6f);
            String mCurrentDrawText = new DecimalFormat("#").format(getProgress() * 100 / getMax());
            mCurrentDrawText = mPrefix + mCurrentDrawText + mSuffix;
            float mDrawTextWidth = mPaint.measureText(mCurrentDrawText);
            canvas.drawText(mCurrentDrawText, mReachedRectF.right - mDrawTextWidth - mBarHeight * 0.4f, (int) ((getHeight() / 2.0f) - ((mPaint.descent() + mPaint.ascent()) / 2.0f)), mPaint);
        }


    }

    private void calculateDrawRectFWithoutProgressText() {
        mReachedRectF.left = getPaddingLeft();
        mReachedRectF.top = getHeight() / 2.0f - mBarHeight / 2.0f;
        mReachedRectF.right = (getWidth() - getPaddingLeft() - getPaddingRight()) / (getMax() * 1.0f) * getProgress() + getPaddingLeft();
        mReachedRectF.bottom = getHeight() / 2.0f + mBarHeight / 2.0f;

        mUnreachedRectF.left = getPaddingLeft();
        mUnreachedRectF.top = getHeight() / 2.0f + -mBarHeight / 2.0f;
        mUnreachedRectF.right = getWidth()  - getPaddingRight() ;
        mUnreachedRectF.bottom = getHeight() / 2.0f + mBarHeight / 2.0f;
    }

    public float getMax() {
        return mMax;
    }

    public float getProgress() {
        return mProgress;
    }

    public void setMax(int max) {
        this.mMax = max;
        invalidate();
    }

    public void setProgress(float progress) {
        this.mProgress = checkProgress(progress);
        invalidate();
    }


    private float checkProgress(float progress) {
        if (progress < 0) return 0;
        return progress > mMax ? mMax : progress;
    }

    private int dp2px(float dpValue) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}

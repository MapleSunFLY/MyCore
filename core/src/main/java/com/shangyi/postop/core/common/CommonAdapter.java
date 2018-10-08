package com.shangyi.postop.core.common;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

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
 * 包    名 : com.fly.postop.code.common
 * 作    者 : FLY
 * 创建时间 : 2018/9/30
 * 描述: 常用的 Adapter
 */
public class CommonAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {

    private OnItemConvertable mConvertable;

    public CommonAdapter(@LayoutRes int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    public CommonAdapter(@LayoutRes int layoutResId, OnItemConvertable<T> convertable) {
        super(layoutResId);
        mConvertable = convertable;
    }

    public CommonAdapter(@LayoutRes int layoutResId, @Nullable List<T> data, OnItemConvertable<T> convertable) {
        super(layoutResId, data);
        mConvertable = convertable;
    }

    @Override
    protected void convert(BaseViewHolder helper, T item) {
        if (mConvertable != null)
            mConvertable.convert(helper, item);
    }


    public interface OnItemConvertable<V> {
        void convert(BaseViewHolder helper, V item);
    }
}

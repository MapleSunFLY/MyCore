package com.shangyi.android.mycode.viewmodel;

import android.arch.lifecycle.MutableLiveData;

import com.shangyi.android.core.base.BaseViewModel;
import com.shangyi.android.http.common.CommonObserver;
import com.shangyi.android.http.ResponseJson;
import com.shangyi.android.http.real.SimpleRetrofit;
import com.shangyi.android.mycode.base.PathApi;
import com.shangyi.android.mycode.entity.PositionEntity;
import com.google.gson.reflect.TypeToken;

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
 * 包    名 : com.fly.postop.mycode.viewmodel
 * 作    者 : FLY
 * 创建时间 : 2018/9/30
 * 描述:
 */
public class MainViewModel extends BaseViewModel {
    private MutableLiveData<ResponseJson<List<PositionEntity>>> positionList = new MutableLiveData<>();

    public MutableLiveData<ResponseJson<List<PositionEntity>>> getPositionList() {
        return positionList;
    }

    public void getPositionListInfo() {
        if (mActivity != null) mActivity.setProgressVisible(true,"加载中.....");
        SimpleRetrofit.<List<PositionEntity>>builder().doGet(PathApi.POSITION_LIST_URL, new CommonObserver<ResponseJson<List<PositionEntity>>>() {

            @Override
            protected void onError(String errorMsg) {
                if (mActivity != null) mActivity.setProgressVisible(false);
            }

            @Override
            protected void onSuccess(ResponseJson<List<PositionEntity>> listResponseJson) {
                if (checkSuccess(listResponseJson)) {
                    positionList.postValue(listResponseJson);
                } else sendError(listResponseJson.info);
            }
        }, new TypeToken<ResponseJson<List<PositionEntity>>>() {
        }.getType());
    }

}

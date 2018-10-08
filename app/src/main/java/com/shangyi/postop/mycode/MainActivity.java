package com.shangyi.postop.mycode;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.shangyi.postop.core.base.BaseLiveDataActivity;
import com.shangyi.postop.http.ResponseJson;
import com.shangyi.postop.mycode.entity.PositionEntity;
import com.shangyi.postop.mycode.viewmodel.MainViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseLiveDataActivity<MainViewModel> {

    @BindView(R.id.btnText)
    Button mIvBack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewModel(MainViewModel.class);
        ButterKnife.bind(this);
        //mIvBack.setVisibility(View.INVISIBLE);

        mViewModel.getPositionList().observe(this, new Observer<ResponseJson<List<PositionEntity>>>() {
            @Override
            public void onChanged(@Nullable ResponseJson<List<PositionEntity>> listResponseJson) {
            }
        });
    }

    @OnClick({R.id.btnText})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnText:
                mViewModel.getPositionListInfo();
                break;
        }
    }

}

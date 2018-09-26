package com.fly.postop.mycode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.fly.postop.code.base.CommonObserver;
import com.fly.postop.code.http.ResponseJson;
import com.fly.postop.code.http.real.SimpleRetrofit;
import com.fly.postop.code.utils.GsonUtils;
import com.fly.postop.code.utils.LogUtils;
import com.fly.postop.mycode.entity.PositionEntity;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnText)
    Button mIvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //mIvBack.setVisibility(View.INVISIBLE);
    }

    @OnClick({R.id.btnText})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnText:
                textRetrofit("/services/third/api/config/title/all");
                break;
        }
    }

    private void textRetrofit(String url) {
        SimpleRetrofit.<List<PositionEntity>>builder().doGet(url, new CommonObserver<ResponseJson<List<PositionEntity>>>() {
            @Override
            protected void onError(String errorMsg) {

            }

            @Override
            protected void onSuccess(ResponseJson<List<PositionEntity>> listResponseJson) {

            }
        }, new TypeToken<ResponseJson<List<PositionEntity>>>() {
        }.getType());
    }
}

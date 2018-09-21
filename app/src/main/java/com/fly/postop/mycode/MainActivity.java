package com.fly.postop.mycode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
                Toast.makeText(MainActivity.this,"点击了",Toast.LENGTH_LONG).show();
                break;
        }
    }
}

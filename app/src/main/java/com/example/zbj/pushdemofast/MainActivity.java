package com.example.zbj.pushdemofast;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.igexin.sdk.PushManager;

public class MainActivity extends AppCompatActivity  {
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = (TextView) findViewById(R.id.tv);
        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DemoIntentService service = new DemoIntentService(MainActivity.this);

                // com.getui.demo.DemoPushService 为第三方自定义推送服务
                PushManager.getInstance().initialize(getApplicationContext(), DemoPushService.class);
                // com.getui.demo.DemoIntentService 为第三方自定义的推送服务事件接收类
                PushManager.getInstance().registerPushIntentService(getApplicationContext(), DemoIntentService.class);
            }
        });

    }



    public void showToast(String str) {
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
        mTv.setText(str);

    }
}

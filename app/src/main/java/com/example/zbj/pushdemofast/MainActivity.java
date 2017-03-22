package com.example.zbj.pushdemofast;

import android.Manifest;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.igexin.sdk.PushManager;

public class MainActivity extends AppCompatActivity {
    private TextView mTv;
    private static final int REQUEST_CODE = 0; // 请求码
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = (TextView) findViewById(R.id.tv);
        PermissionChecker checker = new PermissionChecker(this);
        if (checker.lacksPermissions(PERMISSIONS)) {
            startPermissionsActivity();
        }
    }

    private void startPermissionsActivity() {
        PermissionsActivity.startActivityForResult(this, REQUEST_CODE, PERMISSIONS);
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 拒绝时, 关闭页面, 缺少主要权限, 无法运行
        if (requestCode == REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DemoIntentService.setRefer(MainActivity.this);
                // com.getui.demo.DemoPushService 为第三方自定义推送服务
                PushManager.getInstance().initialize(getApplicationContext(), DemoPushService.class);
                // com.getui.demo.DemoIntentService 为第三方自定义的推送服务事件接收类
                PushManager.getInstance().registerPushIntentService(getApplicationContext(), DemoIntentService.class);
            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String s = (String) msg.obj;
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            mTv.setText(s);
        }
    };


    public void showToast(String str) {
        Message message = new Message();
        message.obj = str;
        handler.sendMessage(message);
    }
}

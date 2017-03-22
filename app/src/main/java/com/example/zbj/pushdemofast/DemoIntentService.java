package com.example.zbj.pushdemofast;

/**
 * Created by zbj on 2017/3/21.
 */

import android.content.Context;
import android.util.Log;

import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTTransmitMessage;


/**
 * 继承 GTIntentService 接收来自个推的消息, 所有消息在线程中回调, 如果注册了该服务, 则务必要在 AndroidManifest中声明, 否则无法接受消息<br>
 * onReceiveMessageData 处理透传消息<br>
 * onReceiveClientId 接收 cid <br>
 * onReceiveOnlineState cid 离线上线通知 <br>
 * onReceiveCommandResult 各种事件处理回执 <br>
 */
public class DemoIntentService extends GTIntentService {
    private static MainActivity Activity;


    public DemoIntentService() {

    }

    @Override
    public void onReceiveServicePid(Context context, int pid) {
    }

    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage msg) {
        Log.i("tag", "============ 收到透传消息 ============");
        Log.i("tag", "------msgId: " + msg.getMessageId());
        Log.i("tag", "------cid: " + msg.getClientId());
        Log.i("tag", "------tid: " + msg.getTaskId());
        Log.i("tag", "------pid: " + msg.getPayloadId());
        String ss = new String(msg.getPayload());
        Activity.showToast(ss);
    }


    @Override
    public void onReceiveClientId(Context context, String clientid) {
        Log.e(TAG, "onReceiveClientId -> " + "clientid = " + clientid);
    }

    @Override
    public void onReceiveOnlineState(Context context, boolean online) {
    }

    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage cmdMessage) {
    }

    public static void setRefer(MainActivity mainActivity) {
        Activity = mainActivity;
    }
}

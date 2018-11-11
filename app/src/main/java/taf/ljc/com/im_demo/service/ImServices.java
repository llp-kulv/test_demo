package taf.ljc.com.im_demo.service;

import android.accounts.Account;
import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

import java.net.InetAddress;

public class ImServices extends IntentService implements Actions, Params {
    private static final String TAG = "ImServices";

    public ImServices() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent == null) {
            return;
        }

        final String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            return;
        }

        if (TextUtils.equals(ACTION_LOGIN, action)) {
            String account = intent.getStringExtra(PARAM_ACCOUNT);
            String pwd = intent.getStringExtra(PARAM_PASSWORD);
            login(account, pwd);
        }
    }

    private void login(@NonNull String account, @NonNull String pwd) {
        buildConnect();
    }

    private void buildConnect() {
        try {
            XMPPTCPConnectionConfiguration.Builder builder = XMPPTCPConnectionConfiguration.builder();
            builder.setXmppDomain("ljcopenfire");
            builder.setHostAddress(InetAddress.getByName("192.168.1.11"));
            //default port 5222
            builder.setPort(5222);
            builder.setDebuggerEnabled(true);
            builder.setCompressionEnabled(true);
            builder.setSendPresence(false);
            builder.setUsernameAndPassword("test1", "123456");
        }catch (Exception e){

        }
    }
}

package taf.ljc.com.im_demo.service;

import android.accounts.Account;
import android.app.IntentService;
import android.content.Intent;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

import java.net.InetAddress;

import taf.ljc.com.im_demo.service.conn.XmppManager;
import taf.ljc.com.im_demo.util.Flog;

public class ImServices extends IntentService implements Actions, Params {
    private static final String TAG = "ImServices";
    private XmppManager mManager;

    public ImServices() {
        super(TAG);
        mManager = new XmppManager();
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

        final ResultReceiver callBackListener = (ResultReceiver) intent.getExtras().get(PARAM_RESULT_LISTENER);

        if (TextUtils.equals(ACTION_LOGIN, action)) {
            String account = intent.getStringExtra(PARAM_ACCOUNT);
            String pwd = intent.getStringExtra(PARAM_PASSWORD);
            login(account, pwd, callBackListener);
        } else if (TextUtils.equals(ACTION_LOGOUT, action)) {
            logout(callBackListener);
        } else if (TextUtils.equals(ACTION_SEND_MESSAGE, action)) {
            String content = intent.getStringExtra(PARAM_CONTENT);
            String to = intent.getStringExtra(PARAM_TO);
            sendMessage(content, to, callBackListener);
        }
    }

    private void sendMessage(String content, @NonNull String to, ResultReceiver resultReceiver) {
        mManager.sendMessage(content, to, resultReceiver);
    }

    private void logout(ResultReceiver resultReceiver) {
        mManager.logout(resultReceiver);
    }

    private void login(@NonNull String account, @NonNull String pwd, ResultReceiver resultReceiver) {
        mManager.login(account, pwd, new ConnectionListener() {
            @Override
            public void connected(XMPPConnection connection) {
                Flog.d(TAG, "connected isConnected" + connection.isConnected());
            }

            @Override
            public void authenticated(XMPPConnection connection, boolean resumed) {
                Flog.d(TAG, "connected resumed" + resumed);
            }

            @Override
            public void connectionClosed() {
                Flog.d(TAG, "connectionClosed");
            }

            @Override
            public void connectionClosedOnError(Exception e) {
                Flog.d(TAG, "connectionClosedOnError");
            }

            @Override
            public void reconnectionSuccessful() {
                Flog.d(TAG, "reconnectionSuccessful");
            }

            @Override
            public void reconnectingIn(int seconds) {
                Flog.d(TAG, "reconnectingIn");
            }

            @Override
            public void reconnectionFailed(Exception e) {
                Flog.d(TAG, "reconnectionFailed e:" + e);
            }
        });
    }
}

package taf.ljc.com.im_demo.service.conn;

import android.os.ResultReceiver;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatManagerListener;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

import taf.ljc.com.im_demo.kernel.BaseResultCode;
import taf.ljc.com.im_demo.util.Flog;

public class XmppManager {
    private static final String TAG = "XmppManager";
    private XMPPTCPConnection mConnect;
    private ChatManager mChatManager;
    private static final String DOMAIN = "ljcopenfire";

    public void login(String account, String pwd, ConnectionListener listener) {
        try {
            if (mConnect == null) {
                initConnect();
            }
            mConnect.login(account, pwd);
            Flog.d(TAG, "login ok");
            if (mConnect.isConnected()) {
                mConnect.addConnectionListener(listener);
            }

            addChatListener();
        } catch (Exception e) {
            Flog.e(TAG, "login err:" + e);
        }
    }

    private void addChatListener() {
        mChatManager = ChatManager.getInstanceFor(mConnect);
        mChatManager.addChatListener(new ChatManagerListener() {
            @Override
            public void chatCreated(Chat chat, boolean createdLocally) {
                Flog.d(TAG, "chatCreated from:" + chat.toString() + " createdLocally:" + createdLocally);
            }
        });
    }

    public void initConnect() {
        if (mConnect == null) {
            try {
                //设置连接配置
                XMPPTCPConnectionConfiguration.Builder builder = XMPPTCPConnectionConfiguration.builder();
                builder.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
                //builder.setCompressionEnabled(false);//连接套将使用流压缩。
                builder.setDebuggerEnabled(true);
                builder.setSendPresence(true);

                builder.setHost("192.168.0.121");
                builder.setPort(5222);
//                builder.setXmppDomain("ljcopenfire");
                mConnect = new XMPPTCPConnection(builder.build());
                mConnect.connect();

//                XMPPTCPConnectionConfiguration.Builder builder = XMPPTCPConnectionConfiguration.builder();
//                builder.setXmppDomain("ljcopenfire");
//                builder.setResource("mobile");
//                builder.setHostAddress(InetAddress.getByName("192.168.0.121"));
//                //default port 5222
//                builder.setPort(5222);
//                builder.setDebuggerEnabled(true);
//                builder.setCompressionEnabled(false);
//                builder.setSendPresence(false);
//                builder.setUsernameAndPassword("test1", "123456");
//
//                mConnect = new XMPPTCPConnection(builder.build());
//                mConnect.setUseStreamManagement(true);
//                mConnect.setUseStreamManagementDefault(true);
//                mConnect.setUseStreamManagementResumption(true);
//                mConnect.setPreferredResumptionTime(6000);
//                mConnect.setPacketReplyTimeout(20000);
                Flog.d(TAG, "initConnect ok");
            } catch (Exception e) {
                Flog.e(TAG, "buildConnect e:" + e);
            }
        }
    }

    public void logout(ResultReceiver resultReceiver) {
        if (mConnect == null) {
            if (resultReceiver != null) {
                resultReceiver.send(BaseResultCode.RESULT_ERROR, null);
            }
            return;
        }

        if (mConnect.isConnected()) {
            mConnect.disconnect();
        }
        if (resultReceiver != null) {
            resultReceiver.send(BaseResultCode.RESULT_OK, null);
        }
    }

    public void sendMessage(String content, String to, ResultReceiver resultReceiver) {
        if (mConnect == null) {
            if (resultReceiver != null) {
                resultReceiver.send(BaseResultCode.RESULT_ERROR, null);
            }
            return;
        }

        if (!mConnect.isConnected()) {
            //TODO reconnect
        }

        if (mChatManager == null) {
            mChatManager = ChatManager.getInstanceFor(mConnect);
        }

        String userJid = to + "@" + DOMAIN;

        Chat chat = mChatManager.createChat(userJid, new ChatMessageListener() {
            @Override
            public void processMessage(Chat chat, Message message) {

            }
        });

        try {
            chat.sendMessage(content);
            if (resultReceiver != null) {
                resultReceiver.send(BaseResultCode.RESULT_OK, null);
            }
            Flog.d(TAG, "sendMessage OK");
        } catch (Exception e) {
            if (resultReceiver != null) {
                resultReceiver.send(BaseResultCode.RESULT_ERROR, null);
            }
        }

    }
}

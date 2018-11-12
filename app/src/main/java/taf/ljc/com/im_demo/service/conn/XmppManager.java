package taf.ljc.com.im_demo.service.conn;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

import java.net.InetAddress;

import taf.ljc.com.im_demo.util.Flog;

public class XmppManager {
    private static final String TAG = "XmppManager";
    private XMPPTCPConnection mConnect;

    public void login(String account, String pwd, ConnectionListener listener) {
        try {
            if (mConnect == null) {
                initConnect();
            }
            mConnect.login(account, pwd);

            if (mConnect.isConnected()) {
                mConnect.addConnectionListener(listener);
            }
        } catch (Exception e) {
            Flog.e(TAG, "login err:" + e);
        }
    }

    public void initConnect() {
        if (mConnect == null) {
            try {
                //设置连接配置
                XMPPTCPConnectionConfiguration.Builder builder= XMPPTCPConnectionConfiguration.builder();
                builder.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
                //builder.setCompressionEnabled(false);//连接套将使用流压缩。
                builder.setDebuggerEnabled(true);
                builder.setSendPresence(true);

                builder.setHost("192.168.0.121");
                builder.setPort(5222);
                builder.setXmppDomain("ljcopenfire");
                mConnect=new XMPPTCPConnection(builder.build());
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
            } catch (Exception e) {
                Flog.e(TAG, "buildConnect e:" + e);
            }
        }
    }
}

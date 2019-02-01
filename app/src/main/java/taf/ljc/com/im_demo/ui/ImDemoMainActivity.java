package taf.ljc.com.im_demo.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import taf.ljc.com.im_demo.R;
import taf.ljc.com.im_demo.service.Actions;
import taf.ljc.com.im_demo.service.ImServices;
import taf.ljc.com.im_demo.util.ImIntentUtil;

public class ImDemoMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im_demo_main);

        findViewById(R.id.chat_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chatIntent = new Intent(ImDemoMainActivity.this, ConversationActivity.class);
                startActivity(chatIntent);
            }
        });

        findViewById(R.id.logout_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }

    private void logout() {
        Intent logoutIntent = ImIntentUtil.createImService(getApplicationContext());
        logoutIntent.setAction(Actions.ACTION_LOGOUT);
        startService(logoutIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        logout();
    }
}

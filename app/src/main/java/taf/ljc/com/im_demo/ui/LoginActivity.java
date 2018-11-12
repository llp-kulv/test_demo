package taf.ljc.com.im_demo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import taf.ljc.com.im_demo.R;
import taf.ljc.com.im_demo.service.Actions;
import taf.ljc.com.im_demo.service.ImServices;
import taf.ljc.com.im_demo.service.Params;

public class LoginActivity extends BaseActivity {
    private TextView mAccountTv, mPwdTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAccountTv = findViewById(R.id.account_et);
        mPwdTv = findViewById(R.id.pwd_et);
        findViewById(R.id.login_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        // default set
        mAccountTv.setText("test1");
        mPwdTv.setText("123456");
    }

    private void login() {
        final String account = mAccountTv.getText().toString();
        final String pwd = mPwdTv.getText().toString();

        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(pwd)) {
            return;
        }

        Intent loginIntent = new Intent(getApplicationContext(), ImServices.class);
        loginIntent.setAction(Actions.ACTION_LOGIN);
        loginIntent.putExtra(Params.PARAM_ACCOUNT, account);
        loginIntent.putExtra(Params.PARAM_PASSWORD, pwd);
        startService(loginIntent);
    }

}

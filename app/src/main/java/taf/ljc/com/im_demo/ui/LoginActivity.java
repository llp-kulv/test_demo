package taf.ljc.com.im_demo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.TypefaceSpan;
import android.util.Log;
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

//        String userName = mAccountTv.getText().toString();
//        String previewText = userName + "";
//        SpannableString spannableInfo = new SpannableString(previewText);
//        int start = previewText.indexOf(userName+"1");
//        int end = previewText.indexOf(userName) + userName.length();
//
//        Log.d("ljc", "ljc start:"+start+" end:"+end);
//        spannableInfo.setSpan(new TypefaceSpan("sans-serif-medium"), 0, 0, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        mAccountTv.setText(spannableInfo);
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
        loginIntent.putExtra(Params.PARAM_RESULT_LISTENER, new ResultReceiver(new Handler()){
            @Override
            protected void onReceiveResult(int resultCode, Bundle resultData) {
                super.onReceiveResult(resultCode, resultData);
            }
        });
        startService(loginIntent);
    }


}

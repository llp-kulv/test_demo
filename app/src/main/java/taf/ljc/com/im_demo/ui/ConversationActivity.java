package taf.ljc.com.im_demo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import taf.ljc.com.im_demo.R;
import taf.ljc.com.im_demo.service.Actions;
import taf.ljc.com.im_demo.service.Params;
import taf.ljc.com.im_demo.util.ImIntentUtil;

public class ConversationActivity extends BaseActivity {
    private EditText mEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_conversation);

        mEditText = findViewById(R.id.input_content_et);
        findViewById(R.id.send_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendContent(mEditText.getText().toString());
            }
        });
    }

    private void sendContent(String content) {
        if (TextUtils.isEmpty(content)) {
            return;
        }

        Intent sendIntent = ImIntentUtil.createImService(getApplicationContext());
        sendIntent.setAction(Actions.ACTION_SEND_MESSAGE);
        sendIntent.putExtra(Params.PARAM_CONTENT, content);
        startService(sendIntent);
    }
}

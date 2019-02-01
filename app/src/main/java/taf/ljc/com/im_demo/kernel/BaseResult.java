package taf.ljc.com.im_demo.kernel;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

public abstract class BaseResult extends ResultReceiver {
    /**
     * Create a new ResultReceive to receive results.  Your
     * {@link #onReceiveResult} method will be called from the thread running
     * <var>handler</var> if given, or from an arbitrary thread if null.
     *
     * @param handler
     */
    public BaseResult(Handler handler) {
        super(handler);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        super.onReceiveResult(resultCode, resultData);


    }
}

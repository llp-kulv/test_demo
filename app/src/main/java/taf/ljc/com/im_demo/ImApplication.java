package taf.ljc.com.im_demo;

import android.app.Application;
import android.content.pm.ApplicationInfo;

public class ImApplication extends Application {
    private static ImApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;



    }

    public static ImApplication getApp() {
        return mInstance;
    }
}

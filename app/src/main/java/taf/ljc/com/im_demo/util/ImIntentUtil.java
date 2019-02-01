package taf.ljc.com.im_demo.util;

import android.content.Context;
import android.content.Intent;

import taf.ljc.com.im_demo.service.ImServices;

public class ImIntentUtil {
    public static Intent createImService(Context context) {
        return new Intent(context, ImServices.class);
    }
}

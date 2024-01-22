package com.mappls.sdk.maps.session;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
/* loaded from: classes11.dex */
public class SessionConstants {
    public static final String AUTO_SESSION = "auto";
    public static final String GLOBAL_SESSION = "global";

    public static String deviceFingerPrint(Context context) {
        String str = Build.BRAND;
        String str2 = Build.MODEL;
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        return str + ':' + str2 + ':' + string;
    }

    public static String getOSName() {
        return Build.VERSION_CODES.class.getFields()[Build.VERSION.SDK_INT - 1].getName();
    }
}

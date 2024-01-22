package com.realsil.sdk.core.utility;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import java.util.Locale;
/* loaded from: classes12.dex */
public final class DeviceUtils {
    public static boolean isZh(Context context) {
        Locale locale;
        Configuration configuration = context.getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= 24) {
            locale = configuration.getLocales().get(0);
        } else {
            locale = configuration.locale;
        }
        return locale.getLanguage().endsWith("zh");
    }
}

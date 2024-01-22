package com.ido.ble.common;

import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import java.io.File;
/* loaded from: classes11.dex */
public class f {

    /* renamed from: a  reason: collision with root package name */
    private static String f12148a;

    public static String a() {
        File externalFilesDir;
        if (TextUtils.isEmpty(f12148a)) {
            String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            if (Build.VERSION.SDK_INT >= 29 && (externalFilesDir = com.ido.ble.b.b().getExternalFilesDir("")) != null) {
                absolutePath = externalFilesDir.getAbsolutePath();
            }
            StringBuilder sb = new StringBuilder();
            sb.append(absolutePath);
            String str = File.separator;
            sb.append(str);
            sb.append("IDO_BLE_SDK");
            sb.append(str);
            sb.append(com.ido.ble.b.b().getPackageName());
            sb.append(str);
            f12148a = sb.toString();
        }
        return f12148a;
    }
}

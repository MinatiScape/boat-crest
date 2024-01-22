package com.abupdate.iot_libs.engine;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;
import android.os.StatFs;
import com.abupdate.trace.Trace;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
/* loaded from: classes.dex */
public class g {

    /* renamed from: a  reason: collision with root package name */
    public static g f1896a;

    public static g a() {
        if (f1896a == null) {
            f1896a = new g();
        }
        return f1896a;
    }

    public Calendar b() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getDefault(), Locale.getDefault());
        Trace.d("xxxxx", "getCalendar() false");
        return gregorianCalendar;
    }

    public long a(String str) {
        long blockSize;
        long blockCount;
        String absolutePath = new File(str).getParentFile().getAbsolutePath();
        if (com.abupdate.iot_libs.utils.c.b(absolutePath)) {
            StatFs statFs = new StatFs(absolutePath);
            if (Build.VERSION.SDK_INT >= 18) {
                blockSize = statFs.getBlockSizeLong();
                blockCount = statFs.getAvailableBlocksLong();
            } else {
                blockSize = statFs.getBlockSize();
                blockCount = statFs.getBlockCount();
            }
            return blockSize * blockCount;
        }
        throw new IllegalArgumentException("Invalid path:" + str);
    }

    public int a(Context context) {
        return context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")).getIntExtra(FirebaseAnalytics.Param.LEVEL, -1);
    }
}

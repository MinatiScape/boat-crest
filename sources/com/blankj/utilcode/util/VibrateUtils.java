package com.blankj.utilcode.util;

import android.os.Vibrator;
import androidx.annotation.RequiresPermission;
/* loaded from: classes.dex */
public final class VibrateUtils {

    /* renamed from: a  reason: collision with root package name */
    public static Vibrator f2301a;

    public VibrateUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static Vibrator a() {
        if (f2301a == null) {
            f2301a = (Vibrator) Utils.getApp().getSystemService("vibrator");
        }
        return f2301a;
    }

    @RequiresPermission("android.permission.VIBRATE")
    public static void cancel() {
        Vibrator a2 = a();
        if (a2 == null) {
            return;
        }
        a2.cancel();
    }

    @RequiresPermission("android.permission.VIBRATE")
    public static void vibrate(long j) {
        Vibrator a2 = a();
        if (a2 == null) {
            return;
        }
        a2.vibrate(j);
    }

    @RequiresPermission("android.permission.VIBRATE")
    public static void vibrate(long[] jArr, int i) {
        Vibrator a2 = a();
        if (a2 == null) {
            return;
        }
        a2.vibrate(jArr, i);
    }
}

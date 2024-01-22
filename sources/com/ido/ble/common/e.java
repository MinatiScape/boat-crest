package com.ido.ble.common;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.ble.protocol.model.SystemTime;
import java.util.Calendar;
/* loaded from: classes11.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private static Handler f12147a = new Handler(Looper.getMainLooper());

    public static Context a() {
        return com.ido.ble.b.b().getApplicationContext();
    }

    public static SystemTime a(boolean z) {
        SystemTime systemTime = new SystemTime();
        Calendar calendar = Calendar.getInstance();
        systemTime.year = calendar.get(1);
        systemTime.monuth = calendar.get(2) + 1;
        systemTime.day = calendar.get(5);
        systemTime.hour = calendar.get(z ? 11 : 10);
        systemTime.minute = calendar.get(12);
        systemTime.second = calendar.get(13);
        systemTime.week = calendar.get(7) == 1 ? 6 : calendar.get(7) - 2;
        return systemTime;
    }

    public static void a(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            b().post(runnable);
        }
    }

    public static void a(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null) {
            return;
        }
        int length = bArr.length;
        int length2 = bArr2.length;
        if (length >= length2) {
            length = length2;
        }
        for (int i = 0; i < length; i++) {
            bArr2[i] = bArr[i];
        }
    }

    private static Handler b() {
        if (f12147a == null) {
            f12147a = new Handler(Looper.getMainLooper());
        }
        return f12147a;
    }

    public static boolean c() {
        SupportFunctionInfo Z = com.ido.ble.f.a.f.a.g0().Z();
        if (Z == null) {
            return false;
        }
        return Z.fastSync;
    }
}

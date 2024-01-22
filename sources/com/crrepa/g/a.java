package com.crrepa.g;

import android.os.Handler;
import android.os.Looper;
/* loaded from: classes9.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static Handler f7718a = new Handler(Looper.getMainLooper());

    public static void a(Runnable runnable, long j) {
        f7718a.postDelayed(runnable, j);
    }
}

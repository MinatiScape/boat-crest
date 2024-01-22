package com.google.android.gms.internal.phenotype;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
/* loaded from: classes6.dex */
public final class zzh<T> {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f9960a = new Object();
    @SuppressLint({"StaticFieldLeak"})
    public static Context b;

    public static void init(Context context) {
        Context applicationContext;
        synchronized (f9960a) {
            if ((Build.VERSION.SDK_INT < 24 || !context.isDeviceProtectedStorage()) && (applicationContext = context.getApplicationContext()) != null) {
                context = applicationContext;
            }
            Context context2 = b;
            b = context;
        }
    }

    public static void maybeInit(Context context) {
        if (b == null) {
            init(context);
        }
    }
}

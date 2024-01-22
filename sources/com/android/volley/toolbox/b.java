package com.android.volley.toolbox;

import android.os.Looper;
/* loaded from: classes.dex */
public final class b {
    public static void a() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("Must be invoked from the main thread.");
        }
    }
}

package com.google.android.gms.internal.common;

import android.os.Handler;
import android.os.Looper;
/* loaded from: classes7.dex */
public class zzi extends Handler {
    public zzi() {
        Looper.getMainLooper();
    }

    public zzi(Looper looper) {
        super(looper);
        Looper.getMainLooper();
    }

    public zzi(Looper looper, Handler.Callback callback) {
        super(looper, callback);
        Looper.getMainLooper();
    }
}

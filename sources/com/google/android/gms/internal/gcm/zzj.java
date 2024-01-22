package com.google.android.gms.internal.gcm;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
/* loaded from: classes8.dex */
public class zzj extends Handler {
    public zzj() {
    }

    @Override // android.os.Handler
    public final void dispatchMessage(Message message) {
        super.dispatchMessage(message);
    }

    public zzj(Looper looper) {
        super(looper);
    }

    public zzj(Looper looper, Handler.Callback callback) {
        super(looper, callback);
    }
}

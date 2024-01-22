package com.google.android.gms.internal.auth;

import android.database.ContentObserver;
import android.os.Handler;
/* loaded from: classes6.dex */
public final class y extends ContentObserver {
    public y(z zVar, Handler handler) {
        super(null);
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        zzdc.zzd();
    }
}

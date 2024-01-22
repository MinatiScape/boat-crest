package com.google.android.gms.common.api.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.Nullable;
/* loaded from: classes6.dex */
public final class zabx extends BroadcastReceiver {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Context f8304a;
    public final zabw b;

    public zabx(zabw zabwVar) {
        this.b = zabwVar;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        Uri data = intent.getData();
        if ("com.google.android.gms".equals(data != null ? data.getSchemeSpecificPart() : null)) {
            this.b.zaa();
            zab();
        }
    }

    public final void zaa(Context context) {
        this.f8304a = context;
    }

    public final synchronized void zab() {
        Context context = this.f8304a;
        if (context != null) {
            context.unregisterReceiver(this);
        }
        this.f8304a = null;
    }
}

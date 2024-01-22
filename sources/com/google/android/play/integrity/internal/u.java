package com.google.android.play.integrity.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
/* loaded from: classes10.dex */
public final class u implements ServiceConnection {
    public final /* synthetic */ v h;

    public /* synthetic */ u(v vVar, t tVar) {
        this.h = vVar;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        v.f(this.h).d("ServiceConnectionImpl.onServiceConnected(%s)", componentName);
        this.h.c().post(new r(this, iBinder));
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        v.f(this.h).d("ServiceConnectionImpl.onServiceDisconnected(%s)", componentName);
        this.h.c().post(new s(this));
    }
}

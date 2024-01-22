package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import androidx.work.PeriodicWorkRequest;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.util.HashMap;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes6.dex */
public final class q extends GmsClientSupervisor {
    @GuardedBy("connectionStatus")
    public final HashMap f = new HashMap();
    public final Context g;
    public volatile Handler h;
    public final p i;
    public final ConnectionTracker j;
    public final long k;
    public final long l;
    @Nullable
    public volatile Executor m;

    public q(Context context, Looper looper, @Nullable Executor executor) {
        p pVar = new p(this, null);
        this.i = pVar;
        this.g = context.getApplicationContext();
        this.h = new com.google.android.gms.internal.common.zzi(looper, pVar);
        this.j = ConnectionTracker.getInstance();
        this.k = 5000L;
        this.l = PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS;
        this.m = executor;
    }

    public final void f(@Nullable Executor executor) {
        synchronized (this.f) {
            this.m = executor;
        }
    }

    public final void g(Looper looper) {
        synchronized (this.f) {
            this.h = new com.google.android.gms.internal.common.zzi(looper, this.i);
        }
    }

    @Override // com.google.android.gms.common.internal.GmsClientSupervisor
    public final void zza(zzo zzoVar, ServiceConnection serviceConnection, String str) {
        Preconditions.checkNotNull(serviceConnection, "ServiceConnection must not be null");
        synchronized (this.f) {
            o oVar = (o) this.f.get(zzoVar);
            if (oVar != null) {
                if (oVar.h(serviceConnection)) {
                    oVar.f(serviceConnection, str);
                    if (oVar.i()) {
                        this.h.sendMessageDelayed(this.h.obtainMessage(0, zzoVar), this.k);
                    }
                } else {
                    String obj = zzoVar.toString();
                    throw new IllegalStateException("Trying to unbind a GmsServiceConnection  that was not bound before.  config=" + obj);
                }
            } else {
                String obj2 = zzoVar.toString();
                throw new IllegalStateException("Nonexistent connection status for service config: " + obj2);
            }
        }
    }

    @Override // com.google.android.gms.common.internal.GmsClientSupervisor
    public final boolean zzc(zzo zzoVar, ServiceConnection serviceConnection, String str, @Nullable Executor executor) {
        boolean j;
        Preconditions.checkNotNull(serviceConnection, "ServiceConnection must not be null");
        synchronized (this.f) {
            o oVar = (o) this.f.get(zzoVar);
            if (executor == null) {
                executor = this.m;
            }
            if (oVar == null) {
                oVar = new o(this, zzoVar);
                oVar.d(serviceConnection, serviceConnection, str);
                oVar.e(str, executor);
                this.f.put(zzoVar, oVar);
            } else {
                this.h.removeMessages(0, zzoVar);
                if (!oVar.h(serviceConnection)) {
                    oVar.d(serviceConnection, serviceConnection, str);
                    int a2 = oVar.a();
                    if (a2 == 1) {
                        serviceConnection.onServiceConnected(oVar.b(), oVar.c());
                    } else if (a2 == 2) {
                        oVar.e(str, executor);
                    }
                } else {
                    String obj = zzoVar.toString();
                    throw new IllegalStateException("Trying to bind a GmsServiceConnection that was already connected before.  config=" + obj);
                }
            }
            j = oVar.j();
        }
        return j;
    }
}

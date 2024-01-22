package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import androidx.annotation.MainThread;
/* loaded from: classes10.dex */
public final class zzez implements ServiceConnection {
    public final String h;
    public final /* synthetic */ zzfa i;

    public zzez(zzfa zzfaVar, String str) {
        this.i = zzfaVar;
        this.h = str;
    }

    @Override // android.content.ServiceConnection
    @MainThread
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (iBinder != null) {
            try {
                com.google.android.gms.internal.measurement.zzbr zzb = com.google.android.gms.internal.measurement.zzbq.zzb(iBinder);
                if (zzb == null) {
                    this.i.f10152a.zzay().zzk().zza("Install Referrer Service implementation was not found");
                    return;
                }
                this.i.f10152a.zzay().zzj().zza("Install Referrer Service connected");
                this.i.f10152a.zzaz().zzp(new w(this, zzb, this));
                return;
            } catch (RuntimeException e) {
                this.i.f10152a.zzay().zzk().zzb("Exception occurred while calling Install Referrer API", e);
                return;
            }
        }
        this.i.f10152a.zzay().zzk().zza("Install Referrer connection returned with null binder");
    }

    @Override // android.content.ServiceConnection
    @MainThread
    public final void onServiceDisconnected(ComponentName componentName) {
        this.i.f10152a.zzay().zzj().zza("Install Referrer Service disconnected");
    }
}

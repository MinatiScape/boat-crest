package com.google.android.gms.measurement.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.annotation.MainThread;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes10.dex */
public final class u extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final zzkn f10131a;
    public boolean b;
    public boolean c;

    public u(zzkn zzknVar) {
        Preconditions.checkNotNull(zzknVar);
        this.f10131a = zzknVar;
    }

    @WorkerThread
    public final void b() {
        this.f10131a.b();
        this.f10131a.zzaz().zzg();
        if (this.b) {
            return;
        }
        this.f10131a.zzau().registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        this.c = this.f10131a.zzl().zzc();
        this.f10131a.zzay().zzj().zzb("Registering connectivity change receiver. Network connected", Boolean.valueOf(this.c));
        this.b = true;
    }

    @WorkerThread
    public final void c() {
        this.f10131a.b();
        this.f10131a.zzaz().zzg();
        this.f10131a.zzaz().zzg();
        if (this.b) {
            this.f10131a.zzay().zzj().zza("Unregistering connectivity change receiver");
            this.b = false;
            this.c = false;
            try {
                this.f10131a.zzau().unregisterReceiver(this);
            } catch (IllegalArgumentException e) {
                this.f10131a.zzay().zzd().zzb("Failed to unregister the network broadcast receiver", e);
            }
        }
    }

    @Override // android.content.BroadcastReceiver
    @MainThread
    public final void onReceive(Context context, Intent intent) {
        this.f10131a.b();
        String action = intent.getAction();
        this.f10131a.zzay().zzj().zzb("NetworkBroadcastReceiver received action", action);
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            boolean zzc = this.f10131a.zzl().zzc();
            if (this.c != zzc) {
                this.c = zzc;
                this.f10131a.zzaz().zzp(new t(this, zzc));
                return;
            }
            return;
        }
        this.f10131a.zzay().zzk().zzb("NetworkBroadcastReceiver received unknown action", action);
    }
}

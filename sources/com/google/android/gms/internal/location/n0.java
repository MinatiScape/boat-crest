package com.google.android.gms.internal.location;

import android.location.Location;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.zzt;
/* loaded from: classes8.dex */
public final class n0 extends zzt {

    /* renamed from: a  reason: collision with root package name */
    public final zzcs f8893a;

    public n0(zzcs zzcsVar) {
        this.f8893a = zzcsVar;
    }

    public final n0 b(ListenerHolder listenerHolder) {
        this.f8893a.zzc(listenerHolder);
        return this;
    }

    @Override // com.google.android.gms.location.zzu
    public final void zzd(Location location) {
        this.f8893a.zza().notifyListener(new l0(this, location));
    }

    @Override // com.google.android.gms.location.zzu
    public final void zze() {
        this.f8893a.zza().notifyListener(new m0(this));
    }

    public final void zzg() {
        this.f8893a.zza().clear();
    }
}

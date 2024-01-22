package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationResult;
/* loaded from: classes8.dex */
public final class k0 extends com.google.android.gms.location.zzq {

    /* renamed from: a  reason: collision with root package name */
    public final zzcs f8889a;

    public k0(zzcs zzcsVar) {
        this.f8889a = zzcsVar;
    }

    public final k0 b(ListenerHolder listenerHolder) {
        this.f8889a.zzc(listenerHolder);
        return this;
    }

    @Override // com.google.android.gms.location.zzr
    public final void zzd(LocationAvailability locationAvailability) throws RemoteException {
        this.f8889a.zza().notifyListener(new i0(this, locationAvailability));
    }

    @Override // com.google.android.gms.location.zzr
    public final void zze(LocationResult locationResult) throws RemoteException {
        this.f8889a.zza().notifyListener(new h0(this, locationResult));
    }

    @Override // com.google.android.gms.location.zzr
    public final void zzf() {
        this.f8889a.zza().notifyListener(new j0(this));
    }

    public final void zzh() {
        this.f8889a.zza().clear();
    }
}

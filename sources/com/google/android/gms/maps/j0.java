package com.google.android.gms.maps;

import android.location.Location;
import android.os.RemoteException;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.internal.zzaj;
import com.google.android.gms.maps.model.RuntimeRemoteException;
/* loaded from: classes10.dex */
public final class j0 implements LocationSource.OnLocationChangedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ zzaj f10064a;

    public j0(q0 q0Var, zzaj zzajVar) {
        this.f10064a = zzajVar;
    }

    @Override // com.google.android.gms.maps.LocationSource.OnLocationChangedListener
    public final void onLocationChanged(Location location) {
        try {
            this.f10064a.zzd(location);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}

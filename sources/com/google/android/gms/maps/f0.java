package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzaw;
/* loaded from: classes10.dex */
public final class f0 extends zzaw {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ GoogleMap.OnMyLocationButtonClickListener f10056a;

    public f0(GoogleMap googleMap, GoogleMap.OnMyLocationButtonClickListener onMyLocationButtonClickListener) {
        this.f10056a = onMyLocationButtonClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzax
    public final boolean zzb() throws RemoteException {
        return this.f10056a.onMyLocationButtonClick();
    }
}

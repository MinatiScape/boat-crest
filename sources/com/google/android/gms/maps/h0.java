package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzam;
/* loaded from: classes10.dex */
public final class h0 extends zzam {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ GoogleMap.OnMapLoadedCallback f10059a;

    public h0(GoogleMap googleMap, GoogleMap.OnMapLoadedCallback onMapLoadedCallback) {
        this.f10059a = onMapLoadedCallback;
    }

    @Override // com.google.android.gms.maps.internal.zzan
    public final void zzb() throws RemoteException {
        this.f10059a.onMapLoaded();
    }
}

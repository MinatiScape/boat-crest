package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.zzaq;
/* loaded from: classes10.dex */
public final class c extends zzaq {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ OnMapReadyCallback f10050a;

    public c(d dVar, OnMapReadyCallback onMapReadyCallback) {
        this.f10050a = onMapReadyCallback;
    }

    @Override // com.google.android.gms.maps.internal.zzar
    public final void zzb(IGoogleMapDelegate iGoogleMapDelegate) throws RemoteException {
        this.f10050a.onMapReady(new GoogleMap(iGoogleMapDelegate));
    }
}

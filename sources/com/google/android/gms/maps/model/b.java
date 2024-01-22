package com.google.android.gms.maps.model;

import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.internal.maps.zzaj;
/* loaded from: classes10.dex */
public final class b implements TileProvider {

    /* renamed from: a  reason: collision with root package name */
    public final zzaj f10085a;
    public final /* synthetic */ TileOverlayOptions b;

    public b(TileOverlayOptions tileOverlayOptions) {
        zzaj zzajVar;
        this.b = tileOverlayOptions;
        zzajVar = tileOverlayOptions.h;
        this.f10085a = zzajVar;
    }

    @Override // com.google.android.gms.maps.model.TileProvider
    @Nullable
    public final Tile getTile(int i, int i2, int i3) {
        try {
            return this.f10085a.zzb(i, i2, i3);
        } catch (RemoteException unused) {
            return null;
        }
    }
}

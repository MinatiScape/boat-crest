package com.google.android.gms.maps.model;

import androidx.annotation.Nullable;
import com.google.android.gms.internal.maps.zzai;
/* loaded from: classes10.dex */
public final class c extends zzai {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TileProvider f10086a;

    public c(TileOverlayOptions tileOverlayOptions, TileProvider tileProvider) {
        this.f10086a = tileProvider;
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    @Nullable
    public final Tile zzb(int i, int i2, int i3) {
        return this.f10086a.getTile(i, i2, i3);
    }
}

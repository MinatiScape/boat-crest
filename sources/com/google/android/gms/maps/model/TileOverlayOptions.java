package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.maps.zzai;
import com.google.android.gms.internal.maps.zzaj;
@SafeParcelable.Class(creator = "TileOverlayOptionsCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes10.dex */
public final class TileOverlayOptions extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<TileOverlayOptions> CREATOR = new zzv();
    @Nullable
    @SafeParcelable.Field(getter = "getTileProviderDelegate", id = 2, type = "android.os.IBinder")
    public zzaj h;
    @Nullable
    public TileProvider i;
    @SafeParcelable.Field(getter = "isVisible", id = 3)
    public boolean j;
    @SafeParcelable.Field(getter = "getZIndex", id = 4)
    public float k;
    @SafeParcelable.Field(defaultValue = "true", getter = "getFadeIn", id = 5)
    public boolean l;
    @SafeParcelable.Field(getter = "getTransparency", id = 6)
    public float m;

    public TileOverlayOptions() {
        this.j = true;
        this.l = true;
        this.m = 0.0f;
    }

    @NonNull
    public TileOverlayOptions fadeIn(boolean z) {
        this.l = z;
        return this;
    }

    public boolean getFadeIn() {
        return this.l;
    }

    @Nullable
    public TileProvider getTileProvider() {
        return this.i;
    }

    public float getTransparency() {
        return this.m;
    }

    public float getZIndex() {
        return this.k;
    }

    public boolean isVisible() {
        return this.j;
    }

    @NonNull
    public TileOverlayOptions tileProvider(@NonNull TileProvider tileProvider) {
        this.i = (TileProvider) Preconditions.checkNotNull(tileProvider, "tileProvider must not be null.");
        this.h = new c(this, tileProvider);
        return this;
    }

    @NonNull
    public TileOverlayOptions transparency(float f) {
        boolean z = false;
        if (f >= 0.0f && f <= 1.0f) {
            z = true;
        }
        Preconditions.checkArgument(z, "Transparency must be in the range [0..1]");
        this.m = f;
        return this;
    }

    @NonNull
    public TileOverlayOptions visible(boolean z) {
        this.j = z;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        zzaj zzajVar = this.h;
        SafeParcelWriter.writeIBinder(parcel, 2, zzajVar == null ? null : zzajVar.asBinder(), false);
        SafeParcelWriter.writeBoolean(parcel, 3, isVisible());
        SafeParcelWriter.writeFloat(parcel, 4, getZIndex());
        SafeParcelWriter.writeBoolean(parcel, 5, getFadeIn());
        SafeParcelWriter.writeFloat(parcel, 6, getTransparency());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @NonNull
    public TileOverlayOptions zIndex(float f) {
        this.k = f;
        return this;
    }

    @SafeParcelable.Constructor
    public TileOverlayOptions(@SafeParcelable.Param(id = 2) IBinder iBinder, @SafeParcelable.Param(id = 3) boolean z, @SafeParcelable.Param(id = 4) float f, @SafeParcelable.Param(id = 5) boolean z2, @SafeParcelable.Param(id = 6) float f2) {
        this.j = true;
        this.l = true;
        this.m = 0.0f;
        zzaj zzc = zzai.zzc(iBinder);
        this.h = zzc;
        this.i = zzc == null ? null : new b(this);
        this.j = z;
        this.k = f;
        this.l = z2;
        this.m = f2;
    }
}

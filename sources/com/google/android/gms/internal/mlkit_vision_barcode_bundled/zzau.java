package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "GeoPointParcelCreator")
/* loaded from: classes8.dex */
public final class zzau extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzau> CREATOR = new zzbj();
    @SafeParcelable.Field(getter = "getLat", id = 1)
    public final double h;
    @SafeParcelable.Field(getter = "getLng", id = 2)
    public final double i;

    @SafeParcelable.Constructor
    public zzau(@SafeParcelable.Param(id = 1) double d, @SafeParcelable.Param(id = 2) double d2) {
        this.h = d;
        this.i = d2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeDouble(parcel, 1, this.h);
        SafeParcelWriter.writeDouble(parcel, 2, this.i);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

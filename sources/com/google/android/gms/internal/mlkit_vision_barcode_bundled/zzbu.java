package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "ImageMetadataParcelCreator")
/* loaded from: classes8.dex */
public final class zzbu extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbu> CREATOR = new zzbv();
    @SafeParcelable.Field(getter = "getImageFormat", id = 1)
    public final int h;
    @SafeParcelable.Field(getter = "getWidth", id = 2)
    public final int i;
    @SafeParcelable.Field(getter = "getHeight", id = 3)
    public final int j;
    @SafeParcelable.Field(getter = "getRotation", id = 4)
    public final int k;
    @SafeParcelable.Field(getter = "getTimestampMs", id = 5)
    public final long l;

    @SafeParcelable.Constructor
    public zzbu(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) int i2, @SafeParcelable.Param(id = 3) int i3, @SafeParcelable.Param(id = 4) int i4, @SafeParcelable.Param(id = 5) long j) {
        this.h = i;
        this.i = i2;
        this.j = i3;
        this.k = i4;
        this.l = j;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.h);
        SafeParcelWriter.writeInt(parcel, 2, this.i);
        SafeParcelWriter.writeInt(parcel, 3, this.j);
        SafeParcelWriter.writeInt(parcel, 4, this.k);
        SafeParcelWriter.writeLong(parcel, 5, this.l);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final int zza() {
        return this.j;
    }

    public final int zzb() {
        return this.h;
    }

    public final int zzc() {
        return this.k;
    }

    public final int zzd() {
        return this.i;
    }
}

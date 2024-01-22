package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "BarcodeScannerOptionsParcelCreator")
/* loaded from: classes8.dex */
public final class zzbc extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbc> CREATOR = new zzbd();
    @SafeParcelable.Field(getter = "getSupportedFormats", id = 1)
    public final int h;
    @SafeParcelable.Field(getter = "isAllPotentialBarcodesEnabled", id = 2)
    public final boolean i;

    @SafeParcelable.Constructor
    public zzbc(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) boolean z) {
        this.h = i;
        this.i = z;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.h);
        SafeParcelWriter.writeBoolean(parcel, 2, this.i);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final int zza() {
        return this.h;
    }

    public final boolean zzb() {
        return this.i;
    }
}

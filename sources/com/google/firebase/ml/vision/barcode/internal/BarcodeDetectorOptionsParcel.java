package com.google.firebase.ml.vision.barcode.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "BarcodeDetectorOptionsParcelCreator")
/* loaded from: classes10.dex */
public class BarcodeDetectorOptionsParcel extends AbstractSafeParcelable {
    public static final Parcelable.Creator<BarcodeDetectorOptionsParcel> CREATOR = new zza();
    @SafeParcelable.Field(id = 1)
    public final int zzbqo;

    @SafeParcelable.Constructor
    public BarcodeDetectorOptionsParcel(@SafeParcelable.Param(id = 1) int i) {
        this.zzbqo = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzbqo);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

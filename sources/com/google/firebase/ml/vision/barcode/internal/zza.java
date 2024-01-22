package com.google.firebase.ml.vision.barcode.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
/* loaded from: classes10.dex */
public final class zza implements Parcelable.Creator<BarcodeDetectorOptionsParcel> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ BarcodeDetectorOptionsParcel createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            if (SafeParcelReader.getFieldId(readHeader) != 1) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                i = SafeParcelReader.readInt(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new BarcodeDetectorOptionsParcel(i);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ BarcodeDetectorOptionsParcel[] newArray(int i) {
        return new BarcodeDetectorOptionsParcel[i];
    }
}

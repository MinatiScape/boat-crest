package com.google.firebase.ml.vision.automl.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
/* loaded from: classes10.dex */
public final class zzm implements Parcelable.Creator<OnDeviceAutoMLImageLabelerOptionsParcel> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ OnDeviceAutoMLImageLabelerOptionsParcel createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        String str2 = null;
        float f = 0.0f;
        String str3 = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                f = SafeParcelReader.readFloat(parcel, readHeader);
            } else if (fieldId == 2) {
                str = SafeParcelReader.createString(parcel, readHeader);
            } else if (fieldId == 3) {
                str3 = SafeParcelReader.createString(parcel, readHeader);
            } else if (fieldId != 4) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                str2 = SafeParcelReader.createString(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new OnDeviceAutoMLImageLabelerOptionsParcel(f, str, str3, str2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ OnDeviceAutoMLImageLabelerOptionsParcel[] newArray(int i) {
        return new OnDeviceAutoMLImageLabelerOptionsParcel[i];
    }
}

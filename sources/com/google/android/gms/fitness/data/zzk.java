package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
/* loaded from: classes6.dex */
public final class zzk implements Parcelable.Creator<DataSource> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DataSource createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        DataType dataType = null;
        Device device = null;
        zza zzaVar = null;
        String str = null;
        int i = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                dataType = (DataType) SafeParcelReader.createParcelable(parcel, readHeader, DataType.CREATOR);
            } else if (fieldId == 3) {
                i = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 4) {
                device = (Device) SafeParcelReader.createParcelable(parcel, readHeader, Device.CREATOR);
            } else if (fieldId == 5) {
                zzaVar = (zza) SafeParcelReader.createParcelable(parcel, readHeader, zza.CREATOR);
            } else if (fieldId != 6) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                str = SafeParcelReader.createString(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new DataSource(dataType, i, device, zzaVar, str);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DataSource[] newArray(int i) {
        return new DataSource[i];
    }
}

package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.BleDevice;
import java.util.ArrayList;
/* loaded from: classes6.dex */
public final class zza implements Parcelable.Creator<BleDevicesResult> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ BleDevicesResult createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList arrayList = null;
        Status status = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                arrayList = SafeParcelReader.createTypedList(parcel, readHeader, BleDevice.CREATOR);
            } else if (fieldId != 2) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                status = (Status) SafeParcelReader.createParcelable(parcel, readHeader, Status.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new BleDevicesResult(arrayList, status);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ BleDevicesResult[] newArray(int i) {
        return new BleDevicesResult[i];
    }
}

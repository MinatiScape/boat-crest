package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;
/* loaded from: classes6.dex */
public final class zzbh implements Parcelable.Creator<StartBleScanRequest> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ StartBleScanRequest createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList arrayList = null;
        IBinder iBinder = null;
        int i = 0;
        IBinder iBinder2 = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                arrayList = SafeParcelReader.createTypedList(parcel, readHeader, DataType.CREATOR);
            } else if (fieldId == 2) {
                iBinder2 = SafeParcelReader.readIBinder(parcel, readHeader);
            } else if (fieldId == 3) {
                i = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId != 4) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                iBinder = SafeParcelReader.readIBinder(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new StartBleScanRequest(arrayList, iBinder2, i, iBinder);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ StartBleScanRequest[] newArray(int i) {
        return new StartBleScanRequest[i];
    }
}

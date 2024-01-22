package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;
/* loaded from: classes6.dex */
public final class zzp implements Parcelable.Creator<DataSourcesRequest> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DataSourcesRequest createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList arrayList = null;
        IBinder iBinder = null;
        boolean z = false;
        ArrayList<Integer> arrayList2 = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                arrayList = SafeParcelReader.createTypedList(parcel, readHeader, DataType.CREATOR);
            } else if (fieldId == 2) {
                arrayList2 = SafeParcelReader.createIntegerList(parcel, readHeader);
            } else if (fieldId == 3) {
                z = SafeParcelReader.readBoolean(parcel, readHeader);
            } else if (fieldId != 4) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                iBinder = SafeParcelReader.readIBinder(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new DataSourcesRequest(arrayList, arrayList2, z, iBinder);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DataSourcesRequest[] newArray(int i) {
        return new DataSourcesRequest[i];
    }
}

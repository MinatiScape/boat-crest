package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.DataSource;
import java.util.ArrayList;
/* loaded from: classes6.dex */
public final class zzc implements Parcelable.Creator<DataReadResult> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DataReadResult createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = null;
        int i = 0;
        Status status = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                SafeParcelReader.readList(parcel, readHeader, arrayList, zzc.class.getClassLoader());
            } else if (fieldId == 2) {
                status = (Status) SafeParcelReader.createParcelable(parcel, readHeader, Status.CREATOR);
            } else if (fieldId == 3) {
                SafeParcelReader.readList(parcel, readHeader, arrayList2, zzc.class.getClassLoader());
            } else if (fieldId == 5) {
                i = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId != 6) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                arrayList3 = SafeParcelReader.createTypedList(parcel, readHeader, DataSource.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new DataReadResult(arrayList, status, arrayList2, i, arrayList3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DataReadResult[] newArray(int i) {
        return new DataReadResult[i];
    }
}

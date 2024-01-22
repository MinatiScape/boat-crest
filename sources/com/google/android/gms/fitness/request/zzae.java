package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
/* loaded from: classes6.dex */
public final class zzae implements Parcelable.Creator<GoalsReadRequest> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ GoalsReadRequest createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        IBinder iBinder = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                iBinder = SafeParcelReader.readIBinder(parcel, readHeader);
            } else if (fieldId == 2) {
                SafeParcelReader.readList(parcel, readHeader, arrayList, zzae.class.getClassLoader());
            } else if (fieldId == 3) {
                SafeParcelReader.readList(parcel, readHeader, arrayList2, zzae.class.getClassLoader());
            } else if (fieldId != 4) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                SafeParcelReader.readList(parcel, readHeader, arrayList3, zzae.class.getClassLoader());
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new GoalsReadRequest(iBinder, arrayList, arrayList2, arrayList3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ GoalsReadRequest[] newArray(int i) {
        return new GoalsReadRequest[i];
    }
}

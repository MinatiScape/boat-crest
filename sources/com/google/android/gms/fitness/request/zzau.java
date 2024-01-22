package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.Session;
import java.util.ArrayList;
/* loaded from: classes6.dex */
public final class zzau implements Parcelable.Creator<SessionInsertRequest> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ SessionInsertRequest createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Session session = null;
        ArrayList arrayList = null;
        ArrayList arrayList2 = null;
        IBinder iBinder = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                session = (Session) SafeParcelReader.createParcelable(parcel, readHeader, Session.CREATOR);
            } else if (fieldId == 2) {
                arrayList = SafeParcelReader.createTypedList(parcel, readHeader, DataSet.CREATOR);
            } else if (fieldId == 3) {
                arrayList2 = SafeParcelReader.createTypedList(parcel, readHeader, DataPoint.CREATOR);
            } else if (fieldId != 4) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                iBinder = SafeParcelReader.readIBinder(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new SessionInsertRequest(session, arrayList, arrayList2, iBinder);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ SessionInsertRequest[] newArray(int i) {
        return new SessionInsertRequest[i];
    }
}

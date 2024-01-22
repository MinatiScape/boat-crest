package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
/* loaded from: classes6.dex */
public final class zze implements Parcelable.Creator<Bucket> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Bucket createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        int i2 = 0;
        Session session = null;
        ArrayList arrayList = null;
        long j = 0;
        long j2 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    j = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 2:
                    j2 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 3:
                    session = (Session) SafeParcelReader.createParcelable(parcel, readHeader, Session.CREATOR);
                    break;
                case 4:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 5:
                    arrayList = SafeParcelReader.createTypedList(parcel, readHeader, DataSet.CREATOR);
                    break;
                case 6:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new Bucket(j, j2, session, i, arrayList, i2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Bucket[] newArray(int i) {
        return new Bucket[i];
    }
}

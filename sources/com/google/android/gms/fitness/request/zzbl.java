package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.Subscription;
/* loaded from: classes6.dex */
public final class zzbl implements Parcelable.Creator<zzbi> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzbi createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Subscription subscription = null;
        boolean z = false;
        IBinder iBinder = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                subscription = (Subscription) SafeParcelReader.createParcelable(parcel, readHeader, Subscription.CREATOR);
            } else if (fieldId == 2) {
                z = SafeParcelReader.readBoolean(parcel, readHeader);
            } else if (fieldId != 3) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                iBinder = SafeParcelReader.readIBinder(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzbi(subscription, z, iBinder);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzbi[] newArray(int i) {
        return new zzbi[i];
    }
}

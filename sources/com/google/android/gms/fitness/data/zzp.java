package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.Goal;
/* loaded from: classes6.dex */
public final class zzp implements Parcelable.Creator<Goal.DurationObjective> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Goal.DurationObjective createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        long j = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            if (SafeParcelReader.getFieldId(readHeader) != 1) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                j = SafeParcelReader.readLong(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new Goal.DurationObjective(j);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Goal.DurationObjective[] newArray(int i) {
        return new Goal.DurationObjective[i];
    }
}

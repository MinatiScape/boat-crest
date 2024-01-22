package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.Goal;
/* loaded from: classes6.dex */
public final class zzs implements Parcelable.Creator<Goal.FrequencyObjective> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Goal.FrequencyObjective createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            if (SafeParcelReader.getFieldId(readHeader) != 1) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                i = SafeParcelReader.readInt(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new Goal.FrequencyObjective(i);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Goal.FrequencyObjective[] newArray(int i) {
        return new Goal.FrequencyObjective[i];
    }
}

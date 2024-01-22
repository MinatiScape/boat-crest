package com.google.android.gms.internal.fitness;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSource;
@SafeParcelable.Class(creator = "FitnessUnregistrationRequestCreator")
@SafeParcelable.Reserved({1000})
/* loaded from: classes8.dex */
public final class zzet extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzet> CREATOR = new zzes();
    @SafeParcelable.Field(getter = "getDataSource", id = 1)
    public final DataSource h;

    @SafeParcelable.Constructor
    public zzet(@SafeParcelable.Param(id = 1) DataSource dataSource) {
        this.h = dataSource;
    }

    public final DataSource getDataSource() {
        return this.h;
    }

    public final String toString() {
        return String.format("ApplicationUnregistrationRequest{%s}", this.h);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.h, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

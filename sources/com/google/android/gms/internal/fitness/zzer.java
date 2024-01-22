package com.google.android.gms.internal.fitness;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataType;
import java.util.Collections;
import java.util.List;
@SafeParcelable.Class(creator = "FitnessDataSourcesRequestCreator")
@SafeParcelable.Reserved({1000})
/* loaded from: classes8.dex */
public final class zzer extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzer> CREATOR = new zzeq();
    @SafeParcelable.Field(getter = "getDataTypes", id = 1)
    public final List<DataType> h;

    @SafeParcelable.Constructor
    public zzer(@SafeParcelable.Param(id = 1) List<DataType> list) {
        this.h = list;
    }

    public final List<DataType> getDataTypes() {
        return Collections.unmodifiableList(this.h);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("dataTypes", this.h).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, Collections.unmodifiableList(this.h), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

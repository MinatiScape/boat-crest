package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.fitness.zzh;
import java.util.List;
@KeepName
@SafeParcelable.Class(creator = "RawDataSetCreator")
@SafeParcelable.Reserved({2, 4, 1000})
/* loaded from: classes6.dex */
public final class RawDataSet extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<RawDataSet> CREATOR = new zzab();
    @SafeParcelable.Field(id = 1)
    public final int zzoa;
    @NonNull
    @SafeParcelable.Field(id = 3)
    public final List<RawDataPoint> zzoc;

    @SafeParcelable.Constructor
    public RawDataSet(@SafeParcelable.Param(id = 1) int i, @NonNull @SafeParcelable.Param(id = 3) List<RawDataPoint> list) {
        this.zzoa = i;
        this.zzoc = list;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof RawDataSet) {
            RawDataSet rawDataSet = (RawDataSet) obj;
            return this.zzoa == rawDataSet.zzoa && Objects.equal(this.zzoc, rawDataSet.zzoc);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzoa));
    }

    @NonNull
    public final String toString() {
        return String.format("RawDataSet{%s@[%s]}", Integer.valueOf(this.zzoa), this.zzoc);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzoa);
        SafeParcelWriter.writeTypedList(parcel, 3, this.zzoc, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public RawDataSet(@NonNull DataSet dataSet, @NonNull List<DataSource> list) {
        this.zzoc = dataSet.a(list);
        this.zzoa = zzh.zza(dataSet.getDataSource(), list);
    }
}

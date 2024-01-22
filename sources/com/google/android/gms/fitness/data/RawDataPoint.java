package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.fitness.zzh;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
@SafeParcelable.Class(creator = "RawDataPointCreator")
@SafeParcelable.Reserved({1000, 7})
@ShowFirstParty
@KeepName
/* loaded from: classes6.dex */
public final class RawDataPoint extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<RawDataPoint> CREATOR = new zzaa();
    @SafeParcelable.Field(getter = "getEndTimeNanos", id = 1)
    public final long h;
    @SafeParcelable.Field(getter = "getStartTimeNanos", id = 2)
    public final long i;
    @SafeParcelable.Field(getter = "getValues", id = 3)
    public final Value[] j;
    @SafeParcelable.Field(getter = "getDataSourceIndex", id = 4)
    public final int k;
    @SafeParcelable.Field(getter = "getOriginalDataSourceIndex", id = 5)
    public final int l;
    @SafeParcelable.Field(getter = "getRawTimestamp", id = 6)
    public final long m;

    @SafeParcelable.Constructor
    public RawDataPoint(@SafeParcelable.Param(id = 1) long j, @SafeParcelable.Param(id = 2) long j2, @NonNull @SafeParcelable.Param(id = 3) Value[] valueArr, @SafeParcelable.Param(id = 4) int i, @SafeParcelable.Param(id = 5) int i2, @SafeParcelable.Param(id = 6) long j3) {
        this.h = j;
        this.i = j2;
        this.k = i;
        this.l = i2;
        this.m = j3;
        this.j = valueArr;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof RawDataPoint) {
            RawDataPoint rawDataPoint = (RawDataPoint) obj;
            return this.h == rawDataPoint.h && this.i == rawDataPoint.i && Arrays.equals(this.j, rawDataPoint.j) && this.k == rawDataPoint.k && this.l == rawDataPoint.l && this.m == rawDataPoint.m;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(Long.valueOf(this.h), Long.valueOf(this.i));
    }

    @NonNull
    public final String toString() {
        return String.format(Locale.US, "RawDataPoint{%s@[%s, %s](%d,%d)}", Arrays.toString(this.j), Long.valueOf(this.i), Long.valueOf(this.h), Integer.valueOf(this.k), Integer.valueOf(this.l));
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.h);
        SafeParcelWriter.writeLong(parcel, 2, this.i);
        SafeParcelWriter.writeTypedArray(parcel, 3, this.j, i, false);
        SafeParcelWriter.writeInt(parcel, 4, this.k);
        SafeParcelWriter.writeInt(parcel, 5, this.l);
        SafeParcelWriter.writeLong(parcel, 6, this.m);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @NonNull
    public final Value[] zze() {
        return this.j;
    }

    public final long zzg() {
        return this.m;
    }

    public final long zzn() {
        return this.h;
    }

    public final long zzo() {
        return this.i;
    }

    public final int zzp() {
        return this.k;
    }

    public final int zzq() {
        return this.l;
    }

    public RawDataPoint(DataPoint dataPoint, List<DataSource> list) {
        TimeUnit timeUnit = TimeUnit.NANOSECONDS;
        this.h = dataPoint.getTimestamp(timeUnit);
        this.i = dataPoint.getStartTime(timeUnit);
        this.j = dataPoint.zze();
        this.k = zzh.zza(dataPoint.getDataSource(), list);
        this.l = zzh.zza(dataPoint.zzf(), list);
        this.m = dataPoint.zzg();
    }
}

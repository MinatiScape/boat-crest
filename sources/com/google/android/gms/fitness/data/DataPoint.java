package com.google.android.gms.fitness.data;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.internal.fitness.zzko;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
@SafeParcelable.Class(creator = "DataPointCreator")
@SafeParcelable.Reserved({1000, 8})
/* loaded from: classes6.dex */
public final class DataPoint extends AbstractSafeParcelable implements ReflectedParcelable {
    @NonNull
    @ShowFirstParty
    public static final Parcelable.Creator<DataPoint> CREATOR = new zzg();
    @SafeParcelable.Field(getter = "getDataSource", id = 1)
    public final DataSource h;
    @SafeParcelable.Field(getter = "getTimestampNanos", id = 3)
    public long i;
    @SafeParcelable.Field(getter = "getStartTimeNanos", id = 4)
    public long j;
    @SafeParcelable.Field(getter = "getValues", id = 5)
    public final Value[] k;
    @Nullable
    @SafeParcelable.Field(getter = "getOriginalDataSourceIfSet", id = 6)
    public DataSource l;
    @SafeParcelable.Field(getter = "getRawTimestamp", id = 7)
    public final long m;

    @SafeParcelable.Constructor
    public DataPoint(@NonNull @SafeParcelable.Param(id = 1) DataSource dataSource, @SafeParcelable.Param(id = 3) long j, @SafeParcelable.Param(id = 4) long j2, @NonNull @SafeParcelable.Param(id = 5) Value[] valueArr, @Nullable @SafeParcelable.Param(id = 6) DataSource dataSource2, @SafeParcelable.Param(id = 7) long j3) {
        this.h = dataSource;
        this.l = dataSource2;
        this.i = j;
        this.j = j2;
        this.k = valueArr;
        this.m = j3;
    }

    @Nullable
    public static DataSource a(List<DataSource> list, int i) {
        if (i < 0 || i >= list.size()) {
            return null;
        }
        return list.get(i);
    }

    @NonNull
    public static Builder builder(@NonNull DataSource dataSource) {
        Preconditions.checkNotNull(dataSource, "DataSource should be specified");
        return new Builder(dataSource);
    }

    @NonNull
    @Deprecated
    public static DataPoint create(@NonNull DataSource dataSource) {
        return new DataPoint(dataSource);
    }

    @Nullable
    public static DataPoint extract(@NonNull Intent intent) {
        if (intent == null) {
            return null;
        }
        return (DataPoint) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "com.google.android.gms.fitness.EXTRA_DATA_POINT", CREATOR);
    }

    public final void b(int i) {
        List<Field> fields = getDataType().getFields();
        int size = fields.size();
        Preconditions.checkArgument(i == size, "Attempting to insert %s values, but needed %s: %s", Integer.valueOf(i), Integer.valueOf(size), fields);
    }

    public final boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DataPoint) {
            DataPoint dataPoint = (DataPoint) obj;
            return Objects.equal(this.h, dataPoint.h) && this.i == dataPoint.i && this.j == dataPoint.j && Arrays.equals(this.k, dataPoint.k) && Objects.equal(getOriginalDataSource(), dataPoint.getOriginalDataSource());
        }
        return false;
    }

    @NonNull
    public final DataSource getDataSource() {
        return this.h;
    }

    @NonNull
    public final DataType getDataType() {
        return this.h.getDataType();
    }

    public final long getEndTime(@NonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.i, TimeUnit.NANOSECONDS);
    }

    @NonNull
    public final DataSource getOriginalDataSource() {
        DataSource dataSource = this.l;
        return dataSource != null ? dataSource : this.h;
    }

    public final long getStartTime(@NonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.j, TimeUnit.NANOSECONDS);
    }

    public final long getTimestamp(@NonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.i, TimeUnit.NANOSECONDS);
    }

    @NonNull
    public final Value getValue(@NonNull Field field) {
        return this.k[getDataType().indexOf(field)];
    }

    public final int hashCode() {
        return Objects.hashCode(this.h, Long.valueOf(this.i), Long.valueOf(this.j));
    }

    @NonNull
    @Deprecated
    public final DataPoint setFloatValues(@NonNull float... fArr) {
        b(fArr.length);
        for (int i = 0; i < fArr.length; i++) {
            this.k[i].setFloat(fArr[i]);
        }
        return this;
    }

    @NonNull
    @Deprecated
    public final DataPoint setIntValues(@NonNull int... iArr) {
        b(iArr.length);
        for (int i = 0; i < iArr.length; i++) {
            this.k[i].setInt(iArr[i]);
        }
        return this;
    }

    @NonNull
    @Deprecated
    public final DataPoint setTimeInterval(long j, long j2, @NonNull TimeUnit timeUnit) {
        this.j = timeUnit.toNanos(j);
        this.i = timeUnit.toNanos(j2);
        return this;
    }

    @NonNull
    @Deprecated
    public final DataPoint setTimestamp(long j, @NonNull TimeUnit timeUnit) {
        this.i = timeUnit.toNanos(j);
        return this;
    }

    @NonNull
    public final String toString() {
        Object[] objArr = new Object[6];
        objArr[0] = Arrays.toString(this.k);
        objArr[1] = Long.valueOf(this.j);
        objArr[2] = Long.valueOf(this.i);
        objArr[3] = Long.valueOf(this.m);
        objArr[4] = this.h.toDebugString();
        DataSource dataSource = this.l;
        objArr[5] = dataSource != null ? dataSource.toDebugString() : "N/A";
        return String.format("DataPoint{%s@[%s, %s,raw=%s](%s %s)}", objArr);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getDataSource(), i, false);
        SafeParcelWriter.writeLong(parcel, 3, this.i);
        SafeParcelWriter.writeLong(parcel, 4, this.j);
        SafeParcelWriter.writeTypedArray(parcel, 5, this.k, i, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.l, i, false);
        SafeParcelWriter.writeLong(parcel, 7, this.m);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @NonNull
    public final Value zzb(int i) {
        DataType dataType = getDataType();
        Preconditions.checkArgument(i >= 0 && i < dataType.getFields().size(), "fieldIndex %s is out of range for %s", Integer.valueOf(i), dataType);
        return this.k[i];
    }

    @NonNull
    @ShowFirstParty
    public final Value[] zze() {
        return this.k;
    }

    @Nullable
    @ShowFirstParty
    public final DataSource zzf() {
        return this.l;
    }

    @ShowFirstParty
    public final long zzg() {
        return this.m;
    }

    public final void zzh() {
        Preconditions.checkArgument(getDataType().getName().equals(getDataSource().getDataType().getName()), "Conflicting data types found %s vs %s", getDataType(), getDataType());
        Preconditions.checkArgument(this.i > 0, "Data point does not have the timestamp set: %s", this);
        Preconditions.checkArgument(this.j <= this.i, "Data point with start time greater than end time found: %s", this);
    }

    /* loaded from: classes6.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final DataPoint f8429a;
        public boolean b;

        public Builder(DataSource dataSource) {
            this.b = false;
            this.f8429a = DataPoint.create(dataSource);
        }

        @NonNull
        public DataPoint build() {
            Preconditions.checkState(!this.b, "DataPoint#build should not be called multiple times.");
            this.b = true;
            return this.f8429a;
        }

        @NonNull
        public Builder setActivityField(@NonNull Field field, @NonNull String str) {
            Preconditions.checkState(!this.b, "Builder should not be mutated after calling #build.");
            this.f8429a.getValue(field).setInt(zzko.zzo(str));
            return this;
        }

        @NonNull
        public Builder setField(@NonNull Field field, int i) {
            Preconditions.checkState(!this.b, "Builder should not be mutated after calling #build.");
            this.f8429a.getValue(field).setInt(i);
            return this;
        }

        @NonNull
        public Builder setFloatValues(@NonNull float... fArr) {
            Preconditions.checkState(!this.b, "Builder should not be mutated after calling #build.");
            this.f8429a.setFloatValues(fArr);
            return this;
        }

        @NonNull
        public Builder setIntValues(@NonNull int... iArr) {
            Preconditions.checkState(!this.b, "Builder should not be mutated after calling #build.");
            this.f8429a.setIntValues(iArr);
            return this;
        }

        @NonNull
        public Builder setTimeInterval(long j, long j2, @NonNull TimeUnit timeUnit) {
            Preconditions.checkState(!this.b, "Builder should not be mutated after calling #build.");
            this.f8429a.setTimeInterval(j, j2, timeUnit);
            return this;
        }

        @NonNull
        public Builder setTimestamp(long j, @NonNull TimeUnit timeUnit) {
            Preconditions.checkState(!this.b, "Builder should not be mutated after calling #build.");
            this.f8429a.setTimestamp(j, timeUnit);
            return this;
        }

        @NonNull
        public Builder setField(@NonNull Field field, float f) {
            Preconditions.checkState(!this.b, "Builder should not be mutated after calling #build.");
            this.f8429a.getValue(field).setFloat(f);
            return this;
        }

        @NonNull
        public Builder setField(@NonNull Field field, @NonNull String str) {
            Preconditions.checkState(!this.b, "Builder should not be mutated after calling #build.");
            this.f8429a.getValue(field).setString(str);
            return this;
        }

        @NonNull
        public Builder setField(@NonNull Field field, @NonNull Map<String, Float> map) {
            Preconditions.checkState(!this.b, "Builder should not be mutated after calling #build.");
            this.f8429a.getValue(field).zza(map);
            return this;
        }
    }

    public DataPoint(List<DataSource> list, RawDataPoint rawDataPoint) {
        this((DataSource) Preconditions.checkNotNull(a(list, rawDataPoint.zzp())), a(list, rawDataPoint.zzq()), rawDataPoint);
    }

    @ShowFirstParty
    public DataPoint(DataSource dataSource, @Nullable DataSource dataSource2, RawDataPoint rawDataPoint) {
        this(dataSource, rawDataPoint.zzn(), rawDataPoint.zzo(), rawDataPoint.zze(), dataSource2, rawDataPoint.zzg());
    }

    public DataPoint(DataSource dataSource) {
        this.h = (DataSource) Preconditions.checkNotNull(dataSource, "Data source cannot be null");
        List<Field> fields = dataSource.getDataType().getFields();
        this.k = new Value[fields.size()];
        int i = 0;
        for (Field field : fields) {
            this.k[i] = new Value(field.getFormat());
            i++;
        }
        this.m = 0L;
    }
}

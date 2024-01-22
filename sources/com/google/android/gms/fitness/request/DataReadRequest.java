package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.fitness.zzbf;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
@SafeParcelable.Class(creator = "DataReadRequestCreator")
@SafeParcelable.Reserved({11, 15, 16, 17, 1000})
/* loaded from: classes6.dex */
public class DataReadRequest extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<DataReadRequest> CREATOR = new zzn();
    public static final int NO_LIMIT = 0;
    @SafeParcelable.Field(getter = "getDataTypes", id = 1)
    public final List<DataType> h;
    @SafeParcelable.Field(getter = "getDataSources", id = 2)
    public final List<DataSource> i;
    @SafeParcelable.Field(getter = "getStartTimeMillis", id = 3)
    public final long j;
    @SafeParcelable.Field(getter = "getEndTimeMillis", id = 4)
    public final long k;
    @SafeParcelable.Field(getter = "getAggregatedDataTypes", id = 5)
    public final List<DataType> l;
    @SafeParcelable.Field(getter = "getAggregatedDataSources", id = 6)
    public final List<DataSource> m;
    @SafeParcelable.Field(getter = "getBucketType", id = 7)
    public final int n;
    @SafeParcelable.Field(getter = "getBucketDurationMillis", id = 8)
    public final long o;
    @SafeParcelable.Field(getter = "getActivityDataSource", id = 9)
    public final DataSource p;
    @SafeParcelable.Field(getter = "getLimit", id = 10)
    public final int q;
    @SafeParcelable.Field(getter = "flushBufferBeforeRead", id = 12)
    public final boolean r;
    @SafeParcelable.Field(getter = "areServerQueriesEnabled", id = 13)
    public final boolean s;
    @Nullable
    @SafeParcelable.Field(getter = "getCallbackBinder", id = 14, type = "android.os.IBinder")
    public final com.google.android.gms.internal.fitness.zzbc t;
    @SafeParcelable.Field(getter = "getIntervalStartTimesNanos", id = 18)
    public final List<Long> u;
    @SafeParcelable.Field(getter = "getIntervalEndTimesNanos", id = 19)
    public final List<Long> v;

    @SafeParcelable.Constructor
    public DataReadRequest(@SafeParcelable.Param(id = 1) List<DataType> list, @SafeParcelable.Param(id = 2) List<DataSource> list2, @SafeParcelable.Param(id = 3) long j, @SafeParcelable.Param(id = 4) long j2, @SafeParcelable.Param(id = 5) List<DataType> list3, @SafeParcelable.Param(id = 6) List<DataSource> list4, @SafeParcelable.Param(id = 7) int i, @SafeParcelable.Param(id = 8) long j3, @SafeParcelable.Param(id = 9) DataSource dataSource, @SafeParcelable.Param(id = 10) int i2, @SafeParcelable.Param(id = 12) boolean z, @SafeParcelable.Param(id = 13) boolean z2, @Nullable @SafeParcelable.Param(id = 14) IBinder iBinder, @SafeParcelable.Param(id = 18) List<Long> list5, @SafeParcelable.Param(id = 19) List<Long> list6) {
        this.h = list;
        this.i = list2;
        this.j = j;
        this.k = j2;
        this.l = list3;
        this.m = list4;
        this.n = i;
        this.o = j3;
        this.p = dataSource;
        this.q = i2;
        this.r = z;
        this.s = z2;
        this.t = iBinder == null ? null : zzbf.zzc(iBinder);
        List<Long> emptyList = list5 == null ? Collections.emptyList() : list5;
        this.u = emptyList;
        List<Long> emptyList2 = list6 == null ? Collections.emptyList() : list6;
        this.v = emptyList2;
        Preconditions.checkArgument(emptyList.size() == emptyList2.size(), "Unequal number of interval start and end times.");
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof DataReadRequest) {
                DataReadRequest dataReadRequest = (DataReadRequest) obj;
                if (this.h.equals(dataReadRequest.h) && this.i.equals(dataReadRequest.i) && this.j == dataReadRequest.j && this.k == dataReadRequest.k && this.n == dataReadRequest.n && this.m.equals(dataReadRequest.m) && this.l.equals(dataReadRequest.l) && Objects.equal(this.p, dataReadRequest.p) && this.o == dataReadRequest.o && this.s == dataReadRequest.s && this.q == dataReadRequest.q && this.r == dataReadRequest.r && Objects.equal(this.t, dataReadRequest.t)) {
                }
            }
            return false;
        }
        return true;
    }

    @Nullable
    public DataSource getActivityDataSource() {
        return this.p;
    }

    @NonNull
    public List<DataSource> getAggregatedDataSources() {
        return this.m;
    }

    @NonNull
    public List<DataType> getAggregatedDataTypes() {
        return this.l;
    }

    public long getBucketDuration(@NonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.o, TimeUnit.MILLISECONDS);
    }

    public int getBucketType() {
        return this.n;
    }

    @NonNull
    public List<DataSource> getDataSources() {
        return this.i;
    }

    @NonNull
    public List<DataType> getDataTypes() {
        return this.h;
    }

    public long getEndTime(@NonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.k, TimeUnit.MILLISECONDS);
    }

    public int getLimit() {
        return this.q;
    }

    public long getStartTime(@NonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.j, TimeUnit.MILLISECONDS);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.n), Long.valueOf(this.j), Long.valueOf(this.k));
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DataReadRequest{");
        if (!this.h.isEmpty()) {
            for (DataType dataType : this.h) {
                sb.append(dataType.zzm());
                sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
            }
        }
        if (!this.i.isEmpty()) {
            for (DataSource dataSource : this.i) {
                sb.append(dataSource.toDebugString());
                sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
            }
        }
        if (this.n != 0) {
            sb.append("bucket by ");
            sb.append(Bucket.zza(this.n));
            if (this.o > 0) {
                sb.append(" >");
                sb.append(this.o);
                sb.append("ms");
            }
            sb.append(": ");
        }
        if (!this.l.isEmpty()) {
            for (DataType dataType2 : this.l) {
                sb.append(dataType2.zzm());
                sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
            }
        }
        if (!this.m.isEmpty()) {
            for (DataSource dataSource2 : this.m) {
                sb.append(dataSource2.toDebugString());
                sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
            }
        }
        sb.append(String.format(Locale.US, "(%tF %tT - %tF %tT)", Long.valueOf(this.j), Long.valueOf(this.j), Long.valueOf(this.k), Long.valueOf(this.k)));
        if (this.p != null) {
            sb.append("activities: ");
            sb.append(this.p.toDebugString());
        }
        if (this.s) {
            sb.append(" +server");
        }
        sb.append("}");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getDataTypes(), false);
        SafeParcelWriter.writeTypedList(parcel, 2, getDataSources(), false);
        SafeParcelWriter.writeLong(parcel, 3, this.j);
        SafeParcelWriter.writeLong(parcel, 4, this.k);
        SafeParcelWriter.writeTypedList(parcel, 5, getAggregatedDataTypes(), false);
        SafeParcelWriter.writeTypedList(parcel, 6, getAggregatedDataSources(), false);
        SafeParcelWriter.writeInt(parcel, 7, getBucketType());
        SafeParcelWriter.writeLong(parcel, 8, this.o);
        SafeParcelWriter.writeParcelable(parcel, 9, getActivityDataSource(), i, false);
        SafeParcelWriter.writeInt(parcel, 10, getLimit());
        SafeParcelWriter.writeBoolean(parcel, 12, this.r);
        SafeParcelWriter.writeBoolean(parcel, 13, this.s);
        com.google.android.gms.internal.fitness.zzbc zzbcVar = this.t;
        SafeParcelWriter.writeIBinder(parcel, 14, zzbcVar == null ? null : zzbcVar.asBinder(), false);
        SafeParcelWriter.writeLongList(parcel, 18, this.u, false);
        SafeParcelWriter.writeLongList(parcel, 19, this.v, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    /* loaded from: classes6.dex */
    public static class Builder {
        public DataSource e;
        public long f;
        public long g;

        /* renamed from: a  reason: collision with root package name */
        public final List<DataType> f8448a = new ArrayList();
        public final List<DataSource> b = new ArrayList();
        public final List<DataType> c = new ArrayList();
        public final List<DataSource> d = new ArrayList();
        public final List<Long> h = new ArrayList();
        public final List<Long> i = new ArrayList();
        public int j = 0;
        public long k = 0;
        public int l = 0;
        public boolean m = false;

        @NonNull
        @Deprecated
        public Builder aggregate(@NonNull DataSource dataSource, @NonNull DataType dataType) {
            Preconditions.checkNotNull(dataSource, "Attempting to add a null data source");
            Preconditions.checkState(!this.b.contains(dataSource), "Cannot add the same data source for aggregated and detailed");
            DataType dataType2 = dataSource.getDataType();
            DataType aggregateType = dataType2.getAggregateType();
            if (aggregateType != null) {
                Preconditions.checkArgument(aggregateType.equals(dataType), "Invalid output aggregate data type specified: %s -> %s", dataType2, dataType);
                if (!this.d.contains(dataSource)) {
                    this.d.add(dataSource);
                }
                return this;
            }
            String valueOf = String.valueOf(dataType2);
            StringBuilder sb = new StringBuilder(valueOf.length() + 55);
            sb.append("Unsupported input data type specified for aggregation: ");
            sb.append(valueOf);
            throw new IllegalArgumentException(sb.toString());
        }

        @NonNull
        public Builder bucketByActivitySegment(int i, @NonNull TimeUnit timeUnit) {
            int i2 = this.j;
            Preconditions.checkArgument(i2 == 0, "Bucketing strategy already set to %s", Integer.valueOf(i2));
            Preconditions.checkArgument(i > 0, "Must specify a valid minimum duration for an activity segment: %d", Integer.valueOf(i));
            this.j = 4;
            this.k = timeUnit.toMillis(i);
            return this;
        }

        @NonNull
        public Builder bucketByActivityType(int i, @NonNull TimeUnit timeUnit) {
            int i2 = this.j;
            Preconditions.checkArgument(i2 == 0, "Bucketing strategy already set to %s", Integer.valueOf(i2));
            Preconditions.checkArgument(i > 0, "Must specify a valid minimum duration for an activity segment: %d", Integer.valueOf(i));
            this.j = 3;
            this.k = timeUnit.toMillis(i);
            return this;
        }

        @NonNull
        public Builder bucketBySession(int i, @NonNull TimeUnit timeUnit) {
            int i2 = this.j;
            Preconditions.checkArgument(i2 == 0, "Bucketing strategy already set to %s", Integer.valueOf(i2));
            Preconditions.checkArgument(i > 0, "Must specify a valid minimum duration for a session: %d", Integer.valueOf(i));
            this.j = 2;
            this.k = timeUnit.toMillis(i);
            return this;
        }

        @NonNull
        public Builder bucketByTime(int i, @NonNull TimeUnit timeUnit) {
            int i2 = this.j;
            Preconditions.checkArgument(i2 == 0, "Bucketing strategy already set to %s", Integer.valueOf(i2));
            Preconditions.checkArgument(i > 0, "Must specify a valid minimum duration: %d", Integer.valueOf(i));
            this.j = 1;
            this.k = timeUnit.toMillis(i);
            return this;
        }

        @NonNull
        public DataReadRequest build() {
            Preconditions.checkState((this.b.isEmpty() && this.f8448a.isEmpty() && this.d.isEmpty() && this.c.isEmpty()) ? false : true, "Must add at least one data source (aggregated or detailed)");
            if (this.j != 5) {
                long j = this.f;
                Preconditions.checkState(j > 0, "Invalid start time: %s", Long.valueOf(j));
                long j2 = this.g;
                Preconditions.checkState(j2 > 0 && j2 > this.f, "Invalid end time: %s", Long.valueOf(j2));
            }
            boolean z = this.d.isEmpty() && this.c.isEmpty();
            if (this.j == 0) {
                Preconditions.checkState(z, "Must specify a valid bucketing strategy while requesting aggregation");
            }
            if (!z) {
                Preconditions.checkState(this.j != 0, "Must specify a valid bucketing strategy while requesting aggregation");
            }
            return new DataReadRequest(this);
        }

        @NonNull
        public Builder enableServerQueries() {
            this.m = true;
            return this;
        }

        @NonNull
        public Builder read(@NonNull DataSource dataSource) {
            Preconditions.checkNotNull(dataSource, "Attempting to add a null data source");
            Preconditions.checkArgument(!this.d.contains(dataSource), "Cannot add the same data source as aggregated and detailed");
            if (!this.b.contains(dataSource)) {
                this.b.add(dataSource);
            }
            return this;
        }

        @NonNull
        public Builder setLimit(int i) {
            Preconditions.checkArgument(i > 0, "Invalid limit %d is specified", Integer.valueOf(i));
            this.l = i;
            return this;
        }

        @NonNull
        public Builder setTimeRange(long j, long j2, @NonNull TimeUnit timeUnit) {
            this.f = timeUnit.toMillis(j);
            this.g = timeUnit.toMillis(j2);
            return this;
        }

        @NonNull
        public Builder read(@NonNull DataType dataType) {
            Preconditions.checkNotNull(dataType, "Attempting to use a null data type");
            Preconditions.checkState(!this.c.contains(dataType), "Cannot add the same data type as aggregated and detailed");
            if (!this.f8448a.contains(dataType)) {
                this.f8448a.add(dataType);
            }
            return this;
        }

        @NonNull
        public Builder bucketByActivitySegment(int i, @NonNull TimeUnit timeUnit, @NonNull DataSource dataSource) {
            int i2 = this.j;
            Preconditions.checkArgument(i2 == 0, "Bucketing strategy already set to %s", Integer.valueOf(i2));
            Preconditions.checkArgument(i > 0, "Must specify a valid minimum duration for an activity segment: %d", Integer.valueOf(i));
            Preconditions.checkArgument(dataSource != null, "Invalid activity data source specified");
            Preconditions.checkArgument(dataSource.getDataType().equals(DataType.TYPE_ACTIVITY_SEGMENT), "Invalid activity data source specified: %s", dataSource);
            this.e = dataSource;
            this.j = 4;
            this.k = timeUnit.toMillis(i);
            return this;
        }

        @NonNull
        public Builder bucketByActivityType(int i, @NonNull TimeUnit timeUnit, @NonNull DataSource dataSource) {
            int i2 = this.j;
            Preconditions.checkArgument(i2 == 0, "Bucketing strategy already set to %s", Integer.valueOf(i2));
            Preconditions.checkArgument(i > 0, "Must specify a valid minimum duration for an activity segment: %d", Integer.valueOf(i));
            Preconditions.checkArgument(dataSource != null, "Invalid activity data source specified");
            Preconditions.checkArgument(dataSource.getDataType().equals(DataType.TYPE_ACTIVITY_SEGMENT), "Invalid activity data source specified: %s", dataSource);
            this.e = dataSource;
            this.j = 3;
            this.k = timeUnit.toMillis(i);
            return this;
        }

        @NonNull
        public Builder aggregate(@NonNull DataSource dataSource) {
            Preconditions.checkNotNull(dataSource, "Attempting to add a null data source");
            Preconditions.checkState(!this.b.contains(dataSource), "Cannot add the same data source for aggregated and detailed");
            DataType dataType = dataSource.getDataType();
            Preconditions.checkArgument(dataType.getAggregateType() != null, "Unsupported input data type specified for aggregation: %s", dataType);
            if (!this.d.contains(dataSource)) {
                this.d.add(dataSource);
            }
            return this;
        }

        @NonNull
        @Deprecated
        public Builder aggregate(@NonNull DataType dataType, @NonNull DataType dataType2) {
            Preconditions.checkNotNull(dataType, "Attempting to use a null data type");
            Preconditions.checkState(!this.f8448a.contains(dataType), "Cannot add the same data type as aggregated and detailed");
            DataType aggregateType = dataType.getAggregateType();
            if (aggregateType != null) {
                Preconditions.checkArgument(aggregateType.equals(dataType2), "Invalid output aggregate data type specified: %s -> %s", dataType, dataType2);
                if (!this.c.contains(dataType)) {
                    this.c.add(dataType);
                }
                return this;
            }
            String valueOf = String.valueOf(dataType);
            StringBuilder sb = new StringBuilder(valueOf.length() + 55);
            sb.append("Unsupported input data type specified for aggregation: ");
            sb.append(valueOf);
            throw new IllegalArgumentException(sb.toString());
        }

        @NonNull
        public Builder aggregate(@NonNull DataType dataType) {
            Preconditions.checkNotNull(dataType, "Attempting to use a null data type");
            Preconditions.checkState(!this.f8448a.contains(dataType), "Cannot add the same data type as aggregated and detailed");
            Preconditions.checkArgument(dataType.getAggregateType() != null, "Unsupported input data type specified for aggregation: %s", dataType);
            if (!this.c.contains(dataType)) {
                this.c.add(dataType);
            }
            return this;
        }
    }

    public DataReadRequest(Builder builder) {
        this((List<DataType>) builder.f8448a, (List<DataSource>) builder.b, builder.f, builder.g, (List<DataType>) builder.c, (List<DataSource>) builder.d, builder.j, builder.k, builder.e, builder.l, false, builder.m, (com.google.android.gms.internal.fitness.zzbc) null, (List<Long>) builder.h, (List<Long>) builder.i);
    }

    public DataReadRequest(DataReadRequest dataReadRequest, com.google.android.gms.internal.fitness.zzbc zzbcVar) {
        this(dataReadRequest.h, dataReadRequest.i, dataReadRequest.j, dataReadRequest.k, dataReadRequest.l, dataReadRequest.m, dataReadRequest.n, dataReadRequest.o, dataReadRequest.p, dataReadRequest.q, dataReadRequest.r, dataReadRequest.s, zzbcVar, dataReadRequest.u, dataReadRequest.v);
    }

    public DataReadRequest(List<DataType> list, List<DataSource> list2, long j, long j2, List<DataType> list3, List<DataSource> list4, int i, long j3, DataSource dataSource, int i2, boolean z, boolean z2, @Nullable com.google.android.gms.internal.fitness.zzbc zzbcVar, List<Long> list5, List<Long> list6) {
        this(list, list2, j, j2, list3, list4, i, j3, dataSource, i2, z, z2, zzbcVar == null ? null : zzbcVar.asBinder(), list5, list6);
    }
}

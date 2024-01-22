package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.internal.fitness.zzcm;
import com.google.android.gms.internal.fitness.zzcn;
import java.util.concurrent.TimeUnit;
@SafeParcelable.Class(creator = "DataUpdateRequestCreator")
@SafeParcelable.Reserved({1000})
/* loaded from: classes6.dex */
public class DataUpdateRequest extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<DataUpdateRequest> CREATOR = new zzaa();
    @SafeParcelable.Field(getter = "getStartTimeMillis", id = 1)
    public final long h;
    @SafeParcelable.Field(getter = "getEndTimeMillis", id = 2)
    public final long i;
    @SafeParcelable.Field(getter = "getDataSet", id = 3)
    public final DataSet j;
    @Nullable
    @SafeParcelable.Field(getter = "getCallbackBinder", id = 4, type = "android.os.IBinder")
    public final zzcn k;

    /* loaded from: classes6.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public long f8452a;
        public long b;
        public DataSet c;

        @NonNull
        public DataUpdateRequest build() {
            int i;
            Preconditions.checkNotZero(this.f8452a, "Must set a non-zero value for startTimeMillis/startTime");
            Preconditions.checkNotZero(this.b, "Must set a non-zero value for endTimeMillis/endTime");
            Preconditions.checkNotNull(this.c, "Must set the data set");
            for (DataPoint dataPoint : this.c.getDataPoints()) {
                TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                long startTime = dataPoint.getStartTime(timeUnit);
                long endTime = dataPoint.getEndTime(timeUnit);
                Preconditions.checkState(!(startTime > endTime || (startTime != 0 && startTime < this.f8452a) || ((i != 0 && startTime > this.b) || endTime > this.b || endTime < this.f8452a)), "Data Point's startTimeMillis %d, endTimeMillis %d should lie between timeRange provided in the request. StartTimeMillis %d, EndTimeMillis: %d", Long.valueOf(startTime), Long.valueOf(endTime), Long.valueOf(this.f8452a), Long.valueOf(this.b));
            }
            return new DataUpdateRequest(this);
        }

        @NonNull
        public Builder setDataSet(@NonNull DataSet dataSet) {
            Preconditions.checkNotNull(dataSet, "Must set the data set");
            this.c = dataSet;
            return this;
        }

        @NonNull
        public Builder setTimeInterval(long j, long j2, @NonNull TimeUnit timeUnit) {
            Preconditions.checkArgument(j > 0, "Invalid start time :%d", Long.valueOf(j));
            Preconditions.checkArgument(j2 >= j, "Invalid end time :%d", Long.valueOf(j2));
            this.f8452a = timeUnit.toMillis(j);
            this.b = timeUnit.toMillis(j2);
            return this;
        }
    }

    @SafeParcelable.Constructor
    public DataUpdateRequest(@SafeParcelable.Param(id = 1) long j, @SafeParcelable.Param(id = 2) long j2, @NonNull @SafeParcelable.Param(id = 3) DataSet dataSet, @Nullable @SafeParcelable.Param(id = 4) IBinder iBinder) {
        this.h = j;
        this.i = j2;
        this.j = dataSet;
        this.k = iBinder == null ? null : zzcm.zzj(iBinder);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DataUpdateRequest) {
            DataUpdateRequest dataUpdateRequest = (DataUpdateRequest) obj;
            return this.h == dataUpdateRequest.h && this.i == dataUpdateRequest.i && Objects.equal(this.j, dataUpdateRequest.j);
        }
        return false;
    }

    @NonNull
    public DataSet getDataSet() {
        return this.j;
    }

    public long getEndTime(@NonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.i, TimeUnit.MILLISECONDS);
    }

    public long getStartTime(@NonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.h, TimeUnit.MILLISECONDS);
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.h), Long.valueOf(this.i), this.j);
    }

    @NonNull
    public String toString() {
        return Objects.toStringHelper(this).add("startTimeMillis", Long.valueOf(this.h)).add("endTimeMillis", Long.valueOf(this.i)).add("dataSet", this.j).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.h);
        SafeParcelWriter.writeLong(parcel, 2, this.i);
        SafeParcelWriter.writeParcelable(parcel, 3, getDataSet(), i, false);
        zzcn zzcnVar = this.k;
        SafeParcelWriter.writeIBinder(parcel, 4, zzcnVar == null ? null : zzcnVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final long zzv() {
        return this.h;
    }

    public final long zzw() {
        return this.i;
    }

    public DataUpdateRequest(Builder builder) {
        this(builder.f8452a, builder.b, builder.c, null);
    }

    public DataUpdateRequest(@NonNull DataUpdateRequest dataUpdateRequest, @NonNull IBinder iBinder) {
        this(dataUpdateRequest.h, dataUpdateRequest.i, dataUpdateRequest.getDataSet(), iBinder);
    }
}

package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.internal.fitness.zzcm;
import com.google.android.gms.internal.fitness.zzcn;
import com.google.android.gms.internal.fitness.zzi;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
@SafeParcelable.Class(creator = "SessionInsertRequestCreator")
@SafeParcelable.Reserved({5, 1000})
/* loaded from: classes6.dex */
public class SessionInsertRequest extends AbstractSafeParcelable {
    @SafeParcelable.Field(getter = "getSession", id = 1)
    public final Session h;
    @SafeParcelable.Field(getter = "getDataSets", id = 2)
    public final List<DataSet> i;
    @SafeParcelable.Field(getter = "getAggregateDataPoints", id = 3)
    public final List<DataPoint> j;
    @Nullable
    @SafeParcelable.Field(getter = "getCallbackBinder", id = 4, type = "android.os.IBinder")
    public final zzcn k;
    public static final TimeUnit l = TimeUnit.MILLISECONDS;
    @NonNull
    public static final Parcelable.Creator<SessionInsertRequest> CREATOR = new zzau();

    /* loaded from: classes6.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Session f8456a;
        public final List<DataSet> b = new ArrayList();
        public final List<DataPoint> c = new ArrayList();
        public final List<DataSource> d = new ArrayList();

        @NonNull
        public Builder addAggregateDataPoint(@NonNull DataPoint dataPoint) {
            Preconditions.checkArgument(dataPoint != null, "Must specify a valid aggregate data point.");
            DataSource dataSource = dataPoint.getDataSource();
            Preconditions.checkState(!this.d.contains(dataSource), "Data set/Aggregate data point for this data source %s is already added.", dataSource);
            DataSet.zzb(dataPoint);
            this.d.add(dataSource);
            this.c.add(dataPoint);
            return this;
        }

        @NonNull
        public Builder addDataSet(@NonNull DataSet dataSet) {
            Preconditions.checkArgument(dataSet != null, "Must specify a valid data set.");
            DataSource dataSource = dataSet.getDataSource();
            Preconditions.checkState(!this.d.contains(dataSource), "Data set for this data source %s is already added.", dataSource);
            Preconditions.checkArgument(!dataSet.getDataPoints().isEmpty(), "No data points specified in the input data set.");
            this.d.add(dataSource);
            this.b.add(dataSet);
            return this;
        }

        @NonNull
        public SessionInsertRequest build() {
            Preconditions.checkState(this.f8456a != null, "Must specify a valid session.");
            Preconditions.checkState(this.f8456a.getEndTime(TimeUnit.MILLISECONDS) != 0, "Must specify a valid end time, cannot insert a continuing session.");
            for (DataSet dataSet : this.b) {
                for (DataPoint dataPoint : dataSet.getDataPoints()) {
                    d(dataPoint);
                }
            }
            for (DataPoint dataPoint2 : this.c) {
                d(dataPoint2);
            }
            return new SessionInsertRequest(this);
        }

        public final void d(DataPoint dataPoint) {
            Session session = this.f8456a;
            TimeUnit timeUnit = TimeUnit.NANOSECONDS;
            long startTime = session.getStartTime(timeUnit);
            long endTime = this.f8456a.getEndTime(timeUnit);
            long timestamp = dataPoint.getTimestamp(timeUnit);
            if (timestamp != 0) {
                if (timestamp < startTime || timestamp > endTime) {
                    timestamp = zzi.zza(timestamp, timeUnit, SessionInsertRequest.l);
                }
                Preconditions.checkState(timestamp >= startTime && timestamp <= endTime, "Data point %s has time stamp outside session interval [%d, %d]", dataPoint, Long.valueOf(startTime), Long.valueOf(endTime));
                if (dataPoint.getTimestamp(timeUnit) != timestamp) {
                    Log.w("Fitness", String.format("Data point timestamp [%d] is truncated to [%d] to match the precision [%s] of the session start and end time", Long.valueOf(dataPoint.getTimestamp(timeUnit)), Long.valueOf(timestamp), SessionInsertRequest.l));
                    dataPoint.setTimestamp(timestamp, timeUnit);
                }
            }
            long startTime2 = this.f8456a.getStartTime(timeUnit);
            long endTime2 = this.f8456a.getEndTime(timeUnit);
            long startTime3 = dataPoint.getStartTime(timeUnit);
            long endTime3 = dataPoint.getEndTime(timeUnit);
            if (startTime3 == 0 || endTime3 == 0) {
                return;
            }
            if (endTime3 > endTime2) {
                endTime3 = zzi.zza(endTime3, timeUnit, SessionInsertRequest.l);
            }
            Preconditions.checkState(startTime3 >= startTime2 && endTime3 <= endTime2, "Data point %s has start and end times outside session interval [%d, %d]", dataPoint, Long.valueOf(startTime2), Long.valueOf(endTime2));
            if (endTime3 != dataPoint.getEndTime(timeUnit)) {
                Log.w("Fitness", String.format("Data point end time [%d] is truncated to [%d] to match the precision [%s] of the session start and end time", Long.valueOf(dataPoint.getEndTime(timeUnit)), Long.valueOf(endTime3), SessionInsertRequest.l));
                dataPoint.setTimeInterval(startTime3, endTime3, timeUnit);
            }
        }

        @NonNull
        public Builder setSession(@NonNull Session session) {
            this.f8456a = session;
            return this;
        }
    }

    @SafeParcelable.Constructor
    public SessionInsertRequest(@SafeParcelable.Param(id = 1) Session session, @SafeParcelable.Param(id = 2) List<DataSet> list, @SafeParcelable.Param(id = 3) List<DataPoint> list2, @Nullable @SafeParcelable.Param(id = 4) IBinder iBinder) {
        this.h = session;
        this.i = Collections.unmodifiableList(list);
        this.j = Collections.unmodifiableList(list2);
        this.k = iBinder == null ? null : zzcm.zzj(iBinder);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj != this) {
            if (obj instanceof SessionInsertRequest) {
                SessionInsertRequest sessionInsertRequest = (SessionInsertRequest) obj;
                if (Objects.equal(this.h, sessionInsertRequest.h) && Objects.equal(this.i, sessionInsertRequest.i) && Objects.equal(this.j, sessionInsertRequest.j)) {
                }
            }
            return false;
        }
        return true;
    }

    @NonNull
    public List<DataPoint> getAggregateDataPoints() {
        return this.j;
    }

    @NonNull
    public List<DataSet> getDataSets() {
        return this.i;
    }

    @NonNull
    public Session getSession() {
        return this.h;
    }

    public int hashCode() {
        return Objects.hashCode(this.h, this.i, this.j);
    }

    @NonNull
    public String toString() {
        return Objects.toStringHelper(this).add("session", this.h).add("dataSets", this.i).add("aggregateDataPoints", this.j).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getSession(), i, false);
        SafeParcelWriter.writeTypedList(parcel, 2, getDataSets(), false);
        SafeParcelWriter.writeTypedList(parcel, 3, getAggregateDataPoints(), false);
        zzcn zzcnVar = this.k;
        SafeParcelWriter.writeIBinder(parcel, 4, zzcnVar == null ? null : zzcnVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public SessionInsertRequest(Builder builder) {
        this(builder.f8456a, builder.b, builder.c, (zzcn) null);
    }

    public SessionInsertRequest(SessionInsertRequest sessionInsertRequest, zzcn zzcnVar) {
        this(sessionInsertRequest.h, sessionInsertRequest.i, sessionInsertRequest.j, zzcnVar);
    }

    public SessionInsertRequest(Session session, List<DataSet> list, List<DataPoint> list2, @Nullable zzcn zzcnVar) {
        this.h = session;
        this.i = Collections.unmodifiableList(list);
        this.j = Collections.unmodifiableList(list2);
        this.k = zzcnVar;
    }
}

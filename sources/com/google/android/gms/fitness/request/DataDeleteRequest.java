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
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.internal.fitness.zzcm;
import com.google.android.gms.internal.fitness.zzcn;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
@SafeParcelable.Class(creator = "DataDeleteRequestCreator")
@SafeParcelable.Reserved({9, 1000})
/* loaded from: classes6.dex */
public class DataDeleteRequest extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<DataDeleteRequest> CREATOR = new zzk();
    @SafeParcelable.Field(getter = "getStartTimeMillis", id = 1)
    public final long h;
    @SafeParcelable.Field(getter = "getEndTimeMillis", id = 2)
    public final long i;
    @SafeParcelable.Field(getter = "getDataSources", id = 3)
    public final List<DataSource> j;
    @SafeParcelable.Field(getter = "getDataTypes", id = 4)
    public final List<DataType> k;
    @SafeParcelable.Field(getter = "getSessions", id = 5)
    public final List<Session> l;
    @SafeParcelable.Field(getter = "deleteAllData", id = 6)
    public final boolean m;
    @SafeParcelable.Field(getter = "deleteAllSessions", id = 7)
    public final boolean n;
    @Nullable
    @SafeParcelable.Field(getter = "getCallbackBinder", id = 8, type = "android.os.IBinder")
    public final zzcn o;
    @SafeParcelable.Field(getter = "deleteByTimeRange", id = 10)
    public final boolean p;
    @SafeParcelable.Field(getter = "enableLocationCleanup", id = 11)
    public final boolean q;

    /* loaded from: classes6.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public long f8447a;
        public long b;
        public final List<DataSource> c = new ArrayList();
        public final List<DataType> d = new ArrayList();
        public final List<Session> e = new ArrayList();
        public boolean f = false;
        public boolean g = false;

        @NonNull
        public Builder addDataSource(@NonNull DataSource dataSource) {
            Preconditions.checkArgument(!this.f, "All data is already marked for deletion.  addDataSource() cannot be combined with deleteAllData()");
            Preconditions.checkArgument(dataSource != null, "Must specify a valid data source");
            if (!this.c.contains(dataSource)) {
                this.c.add(dataSource);
            }
            return this;
        }

        @NonNull
        public Builder addDataType(@NonNull DataType dataType) {
            Preconditions.checkArgument(!this.f, "All data is already marked for deletion.  addDataType() cannot be combined with deleteAllData()");
            Preconditions.checkArgument(dataType != null, "Must specify a valid data type");
            if (!this.d.contains(dataType)) {
                this.d.add(dataType);
            }
            return this;
        }

        @NonNull
        public Builder addSession(@NonNull Session session) {
            Preconditions.checkArgument(!this.g, "All sessions already marked for deletion.  addSession() cannot be combined with deleteAllSessions()");
            Preconditions.checkArgument(session != null, "Must specify a valid session");
            Preconditions.checkArgument(session.getEndTime(TimeUnit.MILLISECONDS) > 0, "Cannot delete an ongoing session. Please stop the session prior to deleting it");
            this.e.add(session);
            return this;
        }

        @NonNull
        public DataDeleteRequest build() {
            long j = this.f8447a;
            Preconditions.checkState(j > 0 && this.b > j, "Must specify a valid time interval");
            Preconditions.checkState((this.f || !this.c.isEmpty() || !this.d.isEmpty()) || (this.g || !this.e.isEmpty()), "No data or session marked for deletion");
            if (!this.e.isEmpty()) {
                for (Session session : this.e) {
                    TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                    Preconditions.checkState(session.getStartTime(timeUnit) >= this.f8447a && session.getEndTime(timeUnit) <= this.b, "Session %s is outside the time interval [%d, %d]", session, Long.valueOf(this.f8447a), Long.valueOf(this.b));
                }
            }
            return new DataDeleteRequest(this);
        }

        @NonNull
        public Builder deleteAllData() {
            Preconditions.checkArgument(this.d.isEmpty(), "Specific data type already added for deletion. deleteAllData() will delete all data types and cannot be combined with addDataType()");
            Preconditions.checkArgument(this.c.isEmpty(), "Specific data source already added for deletion. deleteAllData() will delete all data sources and cannot be combined with addDataSource()");
            this.f = true;
            return this;
        }

        @NonNull
        public Builder deleteAllSessions() {
            Preconditions.checkArgument(this.e.isEmpty(), "Specific session already added for deletion. deleteAllData() will delete all sessions and cannot be combined with addSession()");
            this.g = true;
            return this;
        }

        @NonNull
        public Builder setTimeInterval(long j, long j2, @NonNull TimeUnit timeUnit) {
            Preconditions.checkArgument(j > 0, "Invalid start time: %d", Long.valueOf(j));
            Preconditions.checkArgument(j2 > j, "Invalid end time: %d", Long.valueOf(j2));
            this.f8447a = timeUnit.toMillis(j);
            this.b = timeUnit.toMillis(j2);
            return this;
        }
    }

    @SafeParcelable.Constructor
    public DataDeleteRequest(@SafeParcelable.Param(id = 1) long j, @SafeParcelable.Param(id = 2) long j2, @SafeParcelable.Param(id = 3) List<DataSource> list, @SafeParcelable.Param(id = 4) List<DataType> list2, @SafeParcelable.Param(id = 5) List<Session> list3, @SafeParcelable.Param(id = 6) boolean z, @SafeParcelable.Param(id = 7) boolean z2, @SafeParcelable.Param(id = 10) boolean z3, @SafeParcelable.Param(id = 11) boolean z4, @Nullable @SafeParcelable.Param(id = 8) IBinder iBinder) {
        this.h = j;
        this.i = j2;
        this.j = Collections.unmodifiableList(list);
        this.k = Collections.unmodifiableList(list2);
        this.l = list3;
        this.m = z;
        this.n = z2;
        this.p = z3;
        this.q = z4;
        this.o = iBinder == null ? null : zzcm.zzj(iBinder);
    }

    public boolean deleteAllData() {
        return this.m;
    }

    public boolean deleteAllSessions() {
        return this.n;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DataDeleteRequest) {
            DataDeleteRequest dataDeleteRequest = (DataDeleteRequest) obj;
            return this.h == dataDeleteRequest.h && this.i == dataDeleteRequest.i && Objects.equal(this.j, dataDeleteRequest.j) && Objects.equal(this.k, dataDeleteRequest.k) && Objects.equal(this.l, dataDeleteRequest.l) && this.m == dataDeleteRequest.m && this.n == dataDeleteRequest.n && this.p == dataDeleteRequest.p && this.q == dataDeleteRequest.q;
        }
        return false;
    }

    @NonNull
    public List<DataSource> getDataSources() {
        return this.j;
    }

    @NonNull
    public List<DataType> getDataTypes() {
        return this.k;
    }

    public long getEndTime(@NonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.i, TimeUnit.MILLISECONDS);
    }

    @NonNull
    public List<Session> getSessions() {
        return this.l;
    }

    public long getStartTime(@NonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.h, TimeUnit.MILLISECONDS);
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.h), Long.valueOf(this.i));
    }

    @NonNull
    public String toString() {
        Objects.ToStringHelper add = Objects.toStringHelper(this).add("startTimeMillis", Long.valueOf(this.h)).add("endTimeMillis", Long.valueOf(this.i)).add("dataSources", this.j).add("dateTypes", this.k).add("sessions", this.l).add("deleteAllData", Boolean.valueOf(this.m)).add("deleteAllSessions", Boolean.valueOf(this.n));
        boolean z = this.p;
        if (z) {
            add.add("deleteByTimeRange", Boolean.valueOf(z));
        }
        return add.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.h);
        SafeParcelWriter.writeLong(parcel, 2, this.i);
        SafeParcelWriter.writeTypedList(parcel, 3, getDataSources(), false);
        SafeParcelWriter.writeTypedList(parcel, 4, getDataTypes(), false);
        SafeParcelWriter.writeTypedList(parcel, 5, getSessions(), false);
        SafeParcelWriter.writeBoolean(parcel, 6, deleteAllData());
        SafeParcelWriter.writeBoolean(parcel, 7, deleteAllSessions());
        zzcn zzcnVar = this.o;
        SafeParcelWriter.writeIBinder(parcel, 8, zzcnVar == null ? null : zzcnVar.asBinder(), false);
        SafeParcelWriter.writeBoolean(parcel, 10, this.p);
        SafeParcelWriter.writeBoolean(parcel, 11, this.q);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public DataDeleteRequest(Builder builder) {
        this(builder.f8447a, builder.b, (List<DataSource>) builder.c, (List<DataType>) builder.d, (List<Session>) builder.e, builder.f, builder.g, false, false, (zzcn) null);
    }

    public DataDeleteRequest(DataDeleteRequest dataDeleteRequest, zzcn zzcnVar) {
        this(dataDeleteRequest.h, dataDeleteRequest.i, dataDeleteRequest.j, dataDeleteRequest.k, dataDeleteRequest.l, dataDeleteRequest.m, dataDeleteRequest.n, dataDeleteRequest.p, dataDeleteRequest.q, zzcnVar);
    }

    public DataDeleteRequest(long j, long j2, List<DataSource> list, List<DataType> list2, List<Session> list3, boolean z, boolean z2, boolean z3, boolean z4, @Nullable zzcn zzcnVar) {
        this.h = j;
        this.i = j2;
        this.j = Collections.unmodifiableList(list);
        this.k = Collections.unmodifiableList(list2);
        this.l = list3;
        this.m = z;
        this.n = z2;
        this.p = z3;
        this.q = z4;
        this.o = zzcnVar;
    }
}

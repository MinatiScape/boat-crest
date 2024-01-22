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
import com.google.android.gms.internal.fitness.zzcg;
import com.google.android.gms.internal.fitness.zzch;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
@SafeParcelable.Class(creator = "SessionReadRequestCreator")
@SafeParcelable.Reserved({11, 1000})
/* loaded from: classes6.dex */
public class SessionReadRequest extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<SessionReadRequest> CREATOR = new zzax();
    @SafeParcelable.Field(getter = "getSessionName", id = 1)
    public final String h;
    @SafeParcelable.Field(getter = "getSessionId", id = 2)
    public final String i;
    @SafeParcelable.Field(getter = "getStartTimeMillis", id = 3)
    public final long j;
    @SafeParcelable.Field(getter = "getEndTimeMillis", id = 4)
    public final long k;
    @SafeParcelable.Field(getter = "getDataTypes", id = 5)
    public final List<DataType> l;
    @SafeParcelable.Field(getter = "getDataSources", id = 6)
    public final List<DataSource> m;
    @SafeParcelable.Field(getter = "includeSessionsFromAllApps", id = 7)
    public final boolean n;
    @SafeParcelable.Field(getter = "areServerQueriesEnabled", id = 8)
    public final boolean o;
    @SafeParcelable.Field(getter = "getExcludedPackages", id = 9)
    public final List<String> p;
    @Nullable
    @SafeParcelable.Field(getter = "getCallbackBinder", id = 10, type = "android.os.IBinder")
    public final zzch q;
    @SafeParcelable.Field(defaultValue = "true", getter = "areActivitySessionsIncluded", id = 12)
    public final boolean r;
    @SafeParcelable.Field(defaultValue = "false", getter = "areSleepSessionsIncluded", id = 13)
    public final boolean s;

    @SafeParcelable.Constructor
    public SessionReadRequest(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) String str2, @SafeParcelable.Param(id = 3) long j, @SafeParcelable.Param(id = 4) long j2, @SafeParcelable.Param(id = 5) List<DataType> list, @SafeParcelable.Param(id = 6) List<DataSource> list2, @SafeParcelable.Param(id = 7) boolean z, @SafeParcelable.Param(id = 8) boolean z2, @SafeParcelable.Param(id = 9) List<String> list3, @Nullable @SafeParcelable.Param(id = 10) IBinder iBinder, @SafeParcelable.Param(id = 12) boolean z3, @SafeParcelable.Param(id = 13) boolean z4) {
        this.h = str;
        this.i = str2;
        this.j = j;
        this.k = j2;
        this.l = list;
        this.m = list2;
        this.n = z;
        this.o = z2;
        this.p = list3;
        this.q = iBinder == null ? null : zzcg.zzh(iBinder);
        this.r = z3;
        this.s = z4;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SessionReadRequest) {
            SessionReadRequest sessionReadRequest = (SessionReadRequest) obj;
            return Objects.equal(this.h, sessionReadRequest.h) && this.i.equals(sessionReadRequest.i) && this.j == sessionReadRequest.j && this.k == sessionReadRequest.k && Objects.equal(this.l, sessionReadRequest.l) && Objects.equal(this.m, sessionReadRequest.m) && this.n == sessionReadRequest.n && this.p.equals(sessionReadRequest.p) && this.o == sessionReadRequest.o && this.r == sessionReadRequest.r && this.s == sessionReadRequest.s;
        }
        return false;
    }

    @NonNull
    public List<DataSource> getDataSources() {
        return this.m;
    }

    @NonNull
    public List<DataType> getDataTypes() {
        return this.l;
    }

    public long getEndTime(@NonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.k, TimeUnit.MILLISECONDS);
    }

    @NonNull
    public List<String> getExcludedPackages() {
        return this.p;
    }

    @Nullable
    public String getSessionId() {
        return this.i;
    }

    @Nullable
    public String getSessionName() {
        return this.h;
    }

    public long getStartTime(@NonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.j, TimeUnit.MILLISECONDS);
    }

    public int hashCode() {
        return Objects.hashCode(this.h, this.i, Long.valueOf(this.j), Long.valueOf(this.k));
    }

    public boolean includeSessionsFromAllApps() {
        return this.n;
    }

    @NonNull
    public String toString() {
        return Objects.toStringHelper(this).add("sessionName", this.h).add("sessionId", this.i).add("startTimeMillis", Long.valueOf(this.j)).add("endTimeMillis", Long.valueOf(this.k)).add("dataTypes", this.l).add("dataSources", this.m).add("sessionsFromAllApps", Boolean.valueOf(this.n)).add("excludedPackages", this.p).add("useServer", Boolean.valueOf(this.o)).add("activitySessionsIncluded", Boolean.valueOf(this.r)).add("sleepSessionsIncluded", Boolean.valueOf(this.s)).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getSessionName(), false);
        SafeParcelWriter.writeString(parcel, 2, getSessionId(), false);
        SafeParcelWriter.writeLong(parcel, 3, this.j);
        SafeParcelWriter.writeLong(parcel, 4, this.k);
        SafeParcelWriter.writeTypedList(parcel, 5, getDataTypes(), false);
        SafeParcelWriter.writeTypedList(parcel, 6, getDataSources(), false);
        SafeParcelWriter.writeBoolean(parcel, 7, includeSessionsFromAllApps());
        SafeParcelWriter.writeBoolean(parcel, 8, this.o);
        SafeParcelWriter.writeStringList(parcel, 9, getExcludedPackages(), false);
        zzch zzchVar = this.q;
        SafeParcelWriter.writeIBinder(parcel, 10, zzchVar == null ? null : zzchVar.asBinder(), false);
        SafeParcelWriter.writeBoolean(parcel, 12, this.r);
        SafeParcelWriter.writeBoolean(parcel, 13, this.s);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    /* loaded from: classes6.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f8457a;
        public String b;
        public long c = 0;
        public long d = 0;
        public final List<DataType> e = new ArrayList();
        public final List<DataSource> f = new ArrayList();
        public boolean g = false;
        public boolean h = false;
        public final List<String> i = new ArrayList();
        public boolean j = false;
        public boolean k = false;
        public boolean l = false;

        @NonNull
        public SessionReadRequest build() {
            long j = this.c;
            Preconditions.checkArgument(j > 0, "Invalid start time: %s", Long.valueOf(j));
            long j2 = this.d;
            Preconditions.checkArgument(j2 > 0 && j2 > this.c, "Invalid end time: %s", Long.valueOf(j2));
            if (!this.l) {
                this.j = true;
            }
            return new SessionReadRequest(this);
        }

        @NonNull
        public Builder enableServerQueries() {
            this.h = true;
            return this;
        }

        @NonNull
        public Builder excludePackage(@NonNull String str) {
            Preconditions.checkNotNull(str, "Attempting to use a null package name");
            if (!this.i.contains(str)) {
                this.i.add(str);
            }
            return this;
        }

        @NonNull
        public Builder includeActivitySessions() {
            this.j = true;
            this.l = true;
            return this;
        }

        @NonNull
        public Builder includeSleepSessions() {
            this.k = true;
            this.l = true;
            return this;
        }

        @NonNull
        public Builder read(@NonNull DataSource dataSource) {
            Preconditions.checkNotNull(dataSource, "Attempting to add a null data source");
            if (!this.f.contains(dataSource)) {
                this.f.add(dataSource);
            }
            return this;
        }

        @NonNull
        public Builder readSessionsFromAllApps() {
            this.g = true;
            return this;
        }

        @NonNull
        public Builder setSessionId(@NonNull String str) {
            this.b = str;
            return this;
        }

        @NonNull
        public Builder setSessionName(@NonNull String str) {
            this.f8457a = str;
            return this;
        }

        @NonNull
        public Builder setTimeInterval(long j, long j2, @NonNull TimeUnit timeUnit) {
            this.c = timeUnit.toMillis(j);
            this.d = timeUnit.toMillis(j2);
            return this;
        }

        @NonNull
        public Builder read(@NonNull DataType dataType) {
            Preconditions.checkNotNull(dataType, "Attempting to use a null data type");
            if (!this.e.contains(dataType)) {
                this.e.add(dataType);
            }
            return this;
        }
    }

    public SessionReadRequest(Builder builder) {
        this(builder.f8457a, builder.b, builder.c, builder.d, builder.e, builder.f, builder.g, builder.h, builder.i, null, builder.j, builder.k);
    }

    public SessionReadRequest(SessionReadRequest sessionReadRequest, zzch zzchVar) {
        this(sessionReadRequest.h, sessionReadRequest.i, sessionReadRequest.j, sessionReadRequest.k, sessionReadRequest.l, sessionReadRequest.m, sessionReadRequest.n, sessionReadRequest.o, sessionReadRequest.p, zzchVar.asBinder(), sessionReadRequest.r, sessionReadRequest.s);
    }
}

package com.google.android.gms.fitness.data;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.internal.fitness.zzkn;
import com.google.android.gms.internal.fitness.zzko;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
import java.util.concurrent.TimeUnit;
@SafeParcelable.Class(creator = "SessionCreator")
@SafeParcelable.Reserved({1000})
/* loaded from: classes6.dex */
public class Session extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<Session> CREATOR = new zzaf();
    @NonNull
    public static final String EXTRA_SESSION = "vnd.google.fitness.session";
    @NonNull
    public static final String MIME_TYPE_PREFIX = "vnd.google.fitness.session/";
    @SafeParcelable.Field(getter = "getStartTimeMillis", id = 1)
    public final long h;
    @SafeParcelable.Field(getter = "getEndTimeMillis", id = 2)
    public final long i;
    @Nullable
    @SafeParcelable.Field(getter = "getName", id = 3)
    public final String j;
    @SafeParcelable.Field(getter = "getIdentifier", id = 4)
    public final String k;
    @SafeParcelable.Field(getter = "getDescription", id = 5)
    public final String l;
    @SafeParcelable.Field(getter = "getActivityType", id = 7)
    public final int m;
    @SafeParcelable.Field(getter = "getApplication", id = 8)
    public final zza n;
    @Nullable
    @SafeParcelable.Field(getter = "getActiveTimeMillis", id = 9)
    public final Long o;

    /* loaded from: classes6.dex */
    public static class Builder {
        public String d;
        @Nullable
        public Long g;

        /* renamed from: a  reason: collision with root package name */
        public long f8432a = 0;
        public long b = 0;
        @Nullable
        public String c = null;
        public String e = "";
        public int f = 4;

        @NonNull
        public Session build() {
            boolean z = true;
            Preconditions.checkState(this.f8432a > 0, "Start time should be specified.");
            long j = this.b;
            if (j != 0 && j <= this.f8432a) {
                z = false;
            }
            Preconditions.checkState(z, "End time should be later than start time.");
            if (this.d == null) {
                String str = this.c;
                if (str == null) {
                    str = "";
                }
                long j2 = this.f8432a;
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 20);
                sb.append(str);
                sb.append(j2);
                this.d = sb.toString();
            }
            return new Session(this);
        }

        @NonNull
        public Builder setActiveTime(long j, @NonNull TimeUnit timeUnit) {
            this.g = Long.valueOf(timeUnit.toMillis(j));
            return this;
        }

        @NonNull
        public Builder setActivity(@NonNull String str) {
            int zzo = zzko.zzo(str);
            zzkn zza = zzkn.zza(zzo, zzkn.UNKNOWN);
            Preconditions.checkArgument(!(zza.zzdz() && !zza.equals(zzkn.SLEEP)), "Unsupported session activity type %s.", Integer.valueOf(zzo));
            this.f = zzo;
            return this;
        }

        @NonNull
        public Builder setDescription(@NonNull String str) {
            Preconditions.checkArgument(str.length() <= 1000, "Session description cannot exceed %d characters", 1000);
            this.e = str;
            return this;
        }

        @NonNull
        public Builder setEndTime(long j, @NonNull TimeUnit timeUnit) {
            Preconditions.checkState(j >= 0, "End time should be positive.");
            this.b = timeUnit.toMillis(j);
            return this;
        }

        @NonNull
        public Builder setIdentifier(@NonNull String str) {
            Preconditions.checkArgument(str != null && TextUtils.getTrimmedLength(str) > 0);
            this.d = str;
            return this;
        }

        @NonNull
        public Builder setName(@NonNull String str) {
            Preconditions.checkArgument(str.length() <= 100, "Session name cannot exceed %d characters", 100);
            this.c = str;
            return this;
        }

        @NonNull
        public Builder setStartTime(long j, @NonNull TimeUnit timeUnit) {
            Preconditions.checkState(j > 0, "Start time should be positive.");
            this.f8432a = timeUnit.toMillis(j);
            return this;
        }
    }

    @SafeParcelable.Constructor
    public Session(@SafeParcelable.Param(id = 1) long j, @SafeParcelable.Param(id = 2) long j2, @Nullable @SafeParcelable.Param(id = 3) String str, @SafeParcelable.Param(id = 4) String str2, @SafeParcelable.Param(id = 5) String str3, @SafeParcelable.Param(id = 7) int i, @SafeParcelable.Param(id = 8) zza zzaVar, @Nullable @SafeParcelable.Param(id = 9) Long l) {
        this.h = j;
        this.i = j2;
        this.j = str;
        this.k = str2;
        this.l = str3;
        this.m = i;
        this.n = zzaVar;
        this.o = l;
    }

    @Nullable
    public static Session extract(@NonNull Intent intent) {
        if (intent == null) {
            return null;
        }
        return (Session) SafeParcelableSerializer.deserializeFromIntentExtra(intent, EXTRA_SESSION, CREATOR);
    }

    @NonNull
    public static String getMimeType(@NonNull String str) {
        String valueOf = String.valueOf(str);
        return valueOf.length() != 0 ? MIME_TYPE_PREFIX.concat(valueOf) : new String(MIME_TYPE_PREFIX);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Session) {
            Session session = (Session) obj;
            return this.h == session.h && this.i == session.i && Objects.equal(this.j, session.j) && Objects.equal(this.k, session.k) && Objects.equal(this.l, session.l) && Objects.equal(this.n, session.n) && this.m == session.m;
        }
        return false;
    }

    public long getActiveTime(@NonNull TimeUnit timeUnit) {
        Long l = this.o;
        if (l != null) {
            return timeUnit.convert(l.longValue(), TimeUnit.MILLISECONDS);
        }
        throw new IllegalStateException("Active time is not set");
    }

    @NonNull
    public String getActivity() {
        return zzko.getName(this.m);
    }

    @Nullable
    public String getAppPackageName() {
        zza zzaVar = this.n;
        if (zzaVar == null) {
            return null;
        }
        return zzaVar.getPackageName();
    }

    @NonNull
    public String getDescription() {
        return this.l;
    }

    public long getEndTime(@NonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.i, TimeUnit.MILLISECONDS);
    }

    @NonNull
    public String getIdentifier() {
        return this.k;
    }

    @Nullable
    public String getName() {
        return this.j;
    }

    public long getStartTime(@NonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.h, TimeUnit.MILLISECONDS);
    }

    public boolean hasActiveTime() {
        return this.o != null;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.h), Long.valueOf(this.i), this.k);
    }

    public boolean isOngoing() {
        return this.i == 0;
    }

    @NonNull
    public String toString() {
        return Objects.toStringHelper(this).add("startTime", Long.valueOf(this.h)).add("endTime", Long.valueOf(this.i)).add(AppMeasurementSdk.ConditionalUserProperty.NAME, this.j).add("identifier", this.k).add(SavingTrackHelper.POINT_COL_DESCRIPTION, this.l).add("activity", Integer.valueOf(this.m)).add("application", this.n).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.h);
        SafeParcelWriter.writeLong(parcel, 2, this.i);
        SafeParcelWriter.writeString(parcel, 3, getName(), false);
        SafeParcelWriter.writeString(parcel, 4, getIdentifier(), false);
        SafeParcelWriter.writeString(parcel, 5, getDescription(), false);
        SafeParcelWriter.writeInt(parcel, 7, this.m);
        SafeParcelWriter.writeParcelable(parcel, 8, this.n, i, false);
        SafeParcelWriter.writeLongObject(parcel, 9, this.o, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public Session(Builder builder) {
        this(builder.f8432a, builder.b, builder.c, builder.d, builder.e, builder.f, null, builder.g);
    }
}

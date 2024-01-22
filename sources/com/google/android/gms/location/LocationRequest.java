package com.google.android.gms.location;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.WorkSource;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.WorkSourceUtil;
import com.google.android.gms.internal.location.zzdj;
import com.jstyle.blesdk1860.constant.BleConst;
import org.checkerframework.dataflow.qual.Pure;
import org.eclipse.paho.client.mqttv3.MqttTopic;
@SafeParcelable.Class(creator = "LocationRequestCreator")
@SafeParcelable.Reserved({4, 5, 1000})
/* loaded from: classes10.dex */
public final class LocationRequest extends AbstractSafeParcelable implements ReflectedParcelable {
    @NonNull
    public static final Parcelable.Creator<LocationRequest> CREATOR = new zzx();
    @Deprecated
    public static final int PRIORITY_BALANCED_POWER_ACCURACY = 102;
    @Deprecated
    public static final int PRIORITY_HIGH_ACCURACY = 100;
    @Deprecated
    public static final int PRIORITY_LOW_POWER = 104;
    @Deprecated
    public static final int PRIORITY_NO_POWER = 105;
    @SafeParcelable.Field(defaultValueUnchecked = "Priority.PRIORITY_BALANCED_POWER_ACCURACY", getter = "getPriority", id = 1)
    public int h;
    @SafeParcelable.Field(defaultValue = "3600000", getter = "getIntervalMillis", id = 2)
    public long i;
    @SafeParcelable.Field(defaultValue = "600000", getter = "getMinUpdateIntervalMillis", id = 3)
    public long j;
    @SafeParcelable.Field(defaultValue = BleConst.GetDeviceTime, getter = "getMaxUpdateDelayMillis", id = 8)
    public long k;
    @SafeParcelable.Field(defaultValueUnchecked = "Long.MAX_VALUE", getter = "getDurationMillis", id = 10)
    public long l;
    @SafeParcelable.Field(defaultValueUnchecked = "Integer.MAX_VALUE", getter = "getMaxUpdates", id = 6)
    public int m;
    @SafeParcelable.Field(defaultValue = BleConst.GetDeviceTime, getter = "getMinUpdateDistanceMeters", id = 7)
    public float n;
    @SafeParcelable.Field(defaultValue = "false", getter = "isWaitForAccurateLocation", id = 9)
    public boolean o;
    @SafeParcelable.Field(defaultValueUnchecked = "-1", getter = "getMaxUpdateAgeMillis", id = 11)
    public long p;
    @SafeParcelable.Field(defaultValueUnchecked = "Granularity.GRANULARITY_PERMISSION_LEVEL", getter = "getGranularity", id = 12)
    public final int q;
    @SafeParcelable.Field(defaultValueUnchecked = "ThrottleBehavior.THROTTLE_BACKGROUND", getter = "getThrottleBehavior", id = 13)
    public final int r;
    @Nullable
    @SafeParcelable.Field(getter = "getModuleId", id = 14)
    public final String s;
    @SafeParcelable.Field(defaultValue = "false", getter = "isBypass", id = 15)
    public final boolean t;
    @SafeParcelable.Field(defaultValueUnchecked = "new android.os.WorkSource()", getter = "getWorkSource", id = 16)
    public final WorkSource u;
    @Nullable
    @SafeParcelable.Field(getter = "getImpersonation", id = 17)
    public final com.google.android.gms.internal.location.zzd v;

    @Deprecated
    public LocationRequest() {
        this(102, 3600000L, 600000L, 0L, Long.MAX_VALUE, Long.MAX_VALUE, Integer.MAX_VALUE, 0.0f, true, 3600000L, 0, 0, null, false, new WorkSource(), null);
    }

    public static String a(long j) {
        return j == Long.MAX_VALUE ? "∞" : zzdj.zza(j);
    }

    @NonNull
    @Deprecated
    public static LocationRequest create() {
        return new LocationRequest(102, 3600000L, 600000L, 0L, Long.MAX_VALUE, Long.MAX_VALUE, Integer.MAX_VALUE, 0.0f, true, 3600000L, 0, 0, null, false, new WorkSource(), null);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof LocationRequest) {
            LocationRequest locationRequest = (LocationRequest) obj;
            if (this.h == locationRequest.h && ((isPassive() || this.i == locationRequest.i) && this.j == locationRequest.j && isBatched() == locationRequest.isBatched() && ((!isBatched() || this.k == locationRequest.k) && this.l == locationRequest.l && this.m == locationRequest.m && this.n == locationRequest.n && this.o == locationRequest.o && this.q == locationRequest.q && this.r == locationRequest.r && this.t == locationRequest.t && this.u.equals(locationRequest.u) && Objects.equal(this.s, locationRequest.s) && Objects.equal(this.v, locationRequest.v)))) {
                return true;
            }
        }
        return false;
    }

    @Pure
    public long getDurationMillis() {
        return this.l;
    }

    @Deprecated
    @Pure
    public long getExpirationTime() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j = this.l;
        long j2 = elapsedRealtime + j;
        if (((elapsedRealtime ^ j2) & (j ^ j2)) < 0) {
            return Long.MAX_VALUE;
        }
        return j2;
    }

    @Deprecated
    @Pure
    public long getFastestInterval() {
        return getMinUpdateIntervalMillis();
    }

    @Pure
    public int getGranularity() {
        return this.q;
    }

    @Deprecated
    @Pure
    public long getInterval() {
        return getIntervalMillis();
    }

    @Pure
    public long getIntervalMillis() {
        return this.i;
    }

    @Pure
    public long getMaxUpdateAgeMillis() {
        return this.p;
    }

    @Pure
    public long getMaxUpdateDelayMillis() {
        return this.k;
    }

    @Pure
    public int getMaxUpdates() {
        return this.m;
    }

    @Deprecated
    @Pure
    public long getMaxWaitTime() {
        return Math.max(this.k, this.i);
    }

    @Pure
    public float getMinUpdateDistanceMeters() {
        return this.n;
    }

    @Pure
    public long getMinUpdateIntervalMillis() {
        return this.j;
    }

    @Deprecated
    @Pure
    public int getNumUpdates() {
        return getMaxUpdates();
    }

    @Pure
    public int getPriority() {
        return this.h;
    }

    @Deprecated
    @Pure
    public float getSmallestDisplacement() {
        return getMinUpdateDistanceMeters();
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.h), Long.valueOf(this.i), Long.valueOf(this.j), this.u);
    }

    @Pure
    public boolean isBatched() {
        long j = this.k;
        return j > 0 && (j >> 1) >= this.i;
    }

    @Deprecated
    @Pure
    public boolean isFastestIntervalExplicitlySet() {
        return true;
    }

    @Pure
    public boolean isPassive() {
        return this.h == 105;
    }

    public boolean isWaitForAccurateLocation() {
        return this.o;
    }

    @NonNull
    @Deprecated
    public LocationRequest setExpirationDuration(long j) {
        Preconditions.checkArgument(j > 0, "durationMillis must be greater than 0");
        this.l = j;
        return this;
    }

    @NonNull
    @Deprecated
    public LocationRequest setExpirationTime(long j) {
        this.l = Math.max(1L, j - SystemClock.elapsedRealtime());
        return this;
    }

    @NonNull
    @Deprecated
    public LocationRequest setFastestInterval(long j) {
        Preconditions.checkArgument(j >= 0, "illegal fastest interval: %d", Long.valueOf(j));
        this.j = j;
        return this;
    }

    @NonNull
    @Deprecated
    public LocationRequest setInterval(long j) {
        Preconditions.checkArgument(j >= 0, "intervalMillis must be greater than or equal to 0");
        long j2 = this.j;
        long j3 = this.i;
        if (j2 == j3 / 6) {
            this.j = j / 6;
        }
        if (this.p == j3) {
            this.p = j;
        }
        this.i = j;
        return this;
    }

    @NonNull
    @Deprecated
    public LocationRequest setMaxWaitTime(long j) {
        Preconditions.checkArgument(j >= 0, "illegal max wait time: %d", Long.valueOf(j));
        this.k = j;
        return this;
    }

    @NonNull
    @Deprecated
    public LocationRequest setNumUpdates(int i) {
        if (i > 0) {
            this.m = i;
            return this;
        }
        throw new IllegalArgumentException("invalid numUpdates: " + i);
    }

    @NonNull
    @Deprecated
    public LocationRequest setPriority(int i) {
        zzae.zza(i);
        this.h = i;
        return this;
    }

    @NonNull
    @Deprecated
    public LocationRequest setSmallestDisplacement(float f) {
        if (f >= 0.0f) {
            this.n = f;
            return this;
        }
        throw new IllegalArgumentException("invalid displacement: " + f);
    }

    @NonNull
    @Deprecated
    public LocationRequest setWaitForAccurateLocation(boolean z) {
        this.o = z;
        return this;
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Request[");
        if (isPassive()) {
            sb.append(zzae.zzb(this.h));
        } else {
            sb.append("@");
            if (isBatched()) {
                zzdj.zzb(this.i, sb);
                sb.append(MqttTopic.TOPIC_LEVEL_SEPARATOR);
                zzdj.zzb(this.k, sb);
            } else {
                zzdj.zzb(this.i, sb);
            }
            sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
            sb.append(zzae.zzb(this.h));
        }
        if (isPassive() || this.j != this.i) {
            sb.append(", minUpdateInterval=");
            sb.append(a(this.j));
        }
        if (this.n > 0.0d) {
            sb.append(", minUpdateDistance=");
            sb.append(this.n);
        }
        if (!isPassive() ? this.p != this.i : this.p != Long.MAX_VALUE) {
            sb.append(", maxUpdateAge=");
            sb.append(a(this.p));
        }
        if (this.l != Long.MAX_VALUE) {
            sb.append(", duration=");
            zzdj.zzb(this.l, sb);
        }
        if (this.m != Integer.MAX_VALUE) {
            sb.append(", maxUpdates=");
            sb.append(this.m);
        }
        if (this.r != 0) {
            sb.append(", ");
            sb.append(zzai.zza(this.r));
        }
        if (this.q != 0) {
            sb.append(", ");
            sb.append(zzo.zzb(this.q));
        }
        if (this.o) {
            sb.append(", waitForAccurateLocation");
        }
        if (this.t) {
            sb.append(", bypass");
        }
        if (this.s != null) {
            sb.append(", moduleId=");
            sb.append(this.s);
        }
        if (!WorkSourceUtil.isEmpty(this.u)) {
            sb.append(", ");
            sb.append(this.u);
        }
        if (this.v != null) {
            sb.append(", impersonation=");
            sb.append(this.v);
        }
        sb.append(']');
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, getPriority());
        SafeParcelWriter.writeLong(parcel, 2, getIntervalMillis());
        SafeParcelWriter.writeLong(parcel, 3, getMinUpdateIntervalMillis());
        SafeParcelWriter.writeInt(parcel, 6, getMaxUpdates());
        SafeParcelWriter.writeFloat(parcel, 7, getMinUpdateDistanceMeters());
        SafeParcelWriter.writeLong(parcel, 8, getMaxUpdateDelayMillis());
        SafeParcelWriter.writeBoolean(parcel, 9, isWaitForAccurateLocation());
        SafeParcelWriter.writeLong(parcel, 10, getDurationMillis());
        SafeParcelWriter.writeLong(parcel, 11, getMaxUpdateAgeMillis());
        SafeParcelWriter.writeInt(parcel, 12, getGranularity());
        SafeParcelWriter.writeInt(parcel, 13, this.r);
        SafeParcelWriter.writeString(parcel, 14, this.s, false);
        SafeParcelWriter.writeBoolean(parcel, 15, this.t);
        SafeParcelWriter.writeParcelable(parcel, 16, this.u, i, false);
        SafeParcelWriter.writeParcelable(parcel, 17, this.v, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Pure
    public final int zza() {
        return this.r;
    }

    @NonNull
    @Pure
    public final WorkSource zzb() {
        return this.u;
    }

    @Nullable
    @Pure
    public final com.google.android.gms.internal.location.zzd zzc() {
        return this.v;
    }

    @Nullable
    @Deprecated
    @Pure
    public final String zzd() {
        return this.s;
    }

    @Pure
    public final boolean zze() {
        return this.t;
    }

    /* loaded from: classes10.dex */
    public static final class Builder {
        public static final long IMPLICIT_MAX_UPDATE_AGE = -1;
        public static final long IMPLICIT_MIN_UPDATE_INTERVAL = -1;

        /* renamed from: a  reason: collision with root package name */
        public int f10036a;
        public long b;
        public long c;
        public long d;
        public long e;
        public int f;
        public float g;
        public boolean h;
        public long i;
        public int j;
        public int k;
        @Nullable
        public String l;
        public boolean m;
        @Nullable
        public WorkSource n;
        @Nullable
        public com.google.android.gms.internal.location.zzd o;

        public Builder(int i, long j) {
            Preconditions.checkArgument(j >= 0, "intervalMillis must be greater than or equal to 0");
            zzae.zza(i);
            this.f10036a = i;
            this.b = j;
            this.c = -1L;
            this.d = 0L;
            this.e = Long.MAX_VALUE;
            this.f = Integer.MAX_VALUE;
            this.g = 0.0f;
            this.h = true;
            this.i = -1L;
            this.j = 0;
            this.k = 0;
            this.l = null;
            this.m = false;
            this.n = null;
            this.o = null;
        }

        @NonNull
        public LocationRequest build() {
            int i = this.f10036a;
            long j = this.b;
            long j2 = this.c;
            if (j2 == -1) {
                j2 = j;
            } else if (i != 105) {
                j2 = Math.min(j2, j);
            }
            long max = Math.max(this.d, this.b);
            long j3 = this.e;
            int i2 = this.f;
            float f = this.g;
            boolean z = this.h;
            long j4 = this.i;
            return new LocationRequest(i, j, j2, max, Long.MAX_VALUE, j3, i2, f, z, j4 == -1 ? this.b : j4, this.j, this.k, this.l, this.m, new WorkSource(this.n), this.o);
        }

        @NonNull
        public Builder setDurationMillis(long j) {
            Preconditions.checkArgument(j > 0, "durationMillis must be greater than 0");
            this.e = j;
            return this;
        }

        @NonNull
        public Builder setGranularity(int i) {
            zzo.zza(i);
            this.j = i;
            return this;
        }

        @NonNull
        public Builder setIntervalMillis(long j) {
            Preconditions.checkArgument(j >= 0, "intervalMillis must be greater than or equal to 0");
            this.b = j;
            return this;
        }

        @NonNull
        public Builder setMaxUpdateAgeMillis(long j) {
            boolean z = true;
            if (j != -1 && j < 0) {
                z = false;
            }
            Preconditions.checkArgument(z, "maxUpdateAgeMillis must be greater than or equal to 0, or IMPLICIT_MAX_UPDATE_AGE");
            this.i = j;
            return this;
        }

        @NonNull
        public Builder setMaxUpdateDelayMillis(long j) {
            Preconditions.checkArgument(j >= 0, "maxUpdateDelayMillis must be greater than or equal to 0");
            this.d = j;
            return this;
        }

        @NonNull
        public Builder setMaxUpdates(int i) {
            Preconditions.checkArgument(i > 0, "maxUpdates must be greater than 0");
            this.f = i;
            return this;
        }

        @NonNull
        public Builder setMinUpdateDistanceMeters(float f) {
            Preconditions.checkArgument(f >= 0.0f, "minUpdateDistanceMeters must be greater than or equal to 0");
            this.g = f;
            return this;
        }

        @NonNull
        public Builder setMinUpdateIntervalMillis(long j) {
            boolean z = true;
            if (j != -1 && j < 0) {
                z = false;
            }
            Preconditions.checkArgument(z, "minUpdateIntervalMillis must be greater than or equal to 0, or IMPLICIT_MIN_UPDATE_INTERVAL");
            this.c = j;
            return this;
        }

        @NonNull
        public Builder setPriority(int i) {
            zzae.zza(i);
            this.f10036a = i;
            return this;
        }

        @NonNull
        public Builder setWaitForAccurateLocation(boolean z) {
            this.h = z;
            return this;
        }

        @NonNull
        @RequiresPermission(anyOf = {"android.permission.WRITE_SECURE_SETTINGS", "android.permission.LOCATION_BYPASS"})
        public final Builder zza(boolean z) {
            this.m = z;
            return this;
        }

        @NonNull
        @Deprecated
        public final Builder zzb(@Nullable String str) {
            if (Build.VERSION.SDK_INT < 30) {
                this.l = str;
            }
            return this;
        }

        @NonNull
        public final Builder zzc(int i) {
            boolean z;
            int i2 = 2;
            if (i == 0 || i == 1) {
                i2 = i;
            } else if (i != 2) {
                i2 = i;
                z = false;
                Preconditions.checkArgument(z, "throttle behavior %d must be a ThrottleBehavior.THROTTLE_* constant", Integer.valueOf(i));
                this.k = i2;
                return this;
            } else {
                i = 2;
            }
            z = true;
            Preconditions.checkArgument(z, "throttle behavior %d must be a ThrottleBehavior.THROTTLE_* constant", Integer.valueOf(i));
            this.k = i2;
            return this;
        }

        @NonNull
        @RequiresPermission("android.permission.UPDATE_DEVICE_STATS")
        public final Builder zzd(@Nullable WorkSource workSource) {
            this.n = workSource;
            return this;
        }

        public Builder(long j) {
            Preconditions.checkArgument(j >= 0, "intervalMillis must be greater than or equal to 0");
            this.b = j;
            this.f10036a = 102;
            this.c = -1L;
            this.d = 0L;
            this.e = Long.MAX_VALUE;
            this.f = Integer.MAX_VALUE;
            this.g = 0.0f;
            this.h = true;
            this.i = -1L;
            this.j = 0;
            this.k = 0;
            this.l = null;
            this.m = false;
            this.n = null;
            this.o = null;
        }

        public Builder(@NonNull LocationRequest locationRequest) {
            this.f10036a = locationRequest.getPriority();
            this.b = locationRequest.getIntervalMillis();
            this.c = locationRequest.getMinUpdateIntervalMillis();
            this.d = locationRequest.getMaxUpdateDelayMillis();
            this.e = locationRequest.getDurationMillis();
            this.f = locationRequest.getMaxUpdates();
            this.g = locationRequest.getMinUpdateDistanceMeters();
            this.h = locationRequest.isWaitForAccurateLocation();
            this.i = locationRequest.getMaxUpdateAgeMillis();
            this.j = locationRequest.getGranularity();
            this.k = locationRequest.zza();
            this.l = locationRequest.zzd();
            this.m = locationRequest.zze();
            this.n = locationRequest.zzb();
            this.o = locationRequest.zzc();
        }
    }

    @SafeParcelable.Constructor
    public LocationRequest(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) long j, @SafeParcelable.Param(id = 3) long j2, @SafeParcelable.Param(id = 8) long j3, @SafeParcelable.RemovedParam(defaultValueUnchecked = "Long.MAX_VALUE", id = 5) long j4, @SafeParcelable.Param(id = 10) long j5, @SafeParcelable.Param(id = 6) int i2, @SafeParcelable.Param(id = 7) float f, @SafeParcelable.Param(id = 9) boolean z, @SafeParcelable.Param(id = 11) long j6, @SafeParcelable.Param(id = 12) int i3, @SafeParcelable.Param(id = 13) int i4, @Nullable @SafeParcelable.Param(id = 14) String str, @SafeParcelable.Param(id = 15) boolean z2, @SafeParcelable.Param(id = 16) WorkSource workSource, @Nullable @SafeParcelable.Param(id = 17) com.google.android.gms.internal.location.zzd zzdVar) {
        this.h = i;
        long j7 = j;
        this.i = j7;
        this.j = j2;
        this.k = j3;
        this.l = j4 == Long.MAX_VALUE ? j5 : Math.min(Math.max(1L, j4 - SystemClock.elapsedRealtime()), j5);
        this.m = i2;
        this.n = f;
        this.o = z;
        this.p = j6 != -1 ? j6 : j7;
        this.q = i3;
        this.r = i4;
        this.s = str;
        this.t = z2;
        this.u = workSource;
        this.v = zzdVar;
    }
}

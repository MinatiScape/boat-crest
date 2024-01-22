package com.google.android.gms.location;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.WorkSource;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.WorkSourceUtil;
import com.google.android.gms.internal.location.zzdj;
import org.checkerframework.dataflow.qual.Pure;
@SafeParcelable.Class(creator = "CurrentLocationRequestCreator")
/* loaded from: classes10.dex */
public final class CurrentLocationRequest extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<CurrentLocationRequest> CREATOR = new zzj();
    @SafeParcelable.Field(defaultValueUnchecked = "Long.MAX_VALUE", getter = "getMaxUpdateAgeMillis", id = 1)
    public final long h;
    @SafeParcelable.Field(defaultValueUnchecked = "Granularity.GRANULARITY_PERMISSION_LEVEL", getter = "getGranularity", id = 2)
    public final int i;
    @SafeParcelable.Field(defaultValueUnchecked = "Priority.PRIORITY_BALANCED_POWER_ACCURACY", getter = "getPriority", id = 3)
    public final int j;
    @SafeParcelable.Field(defaultValueUnchecked = "Long.MAX_VALUE", getter = "getDurationMillis", id = 4)
    public final long k;
    @SafeParcelable.Field(defaultValue = "false", getter = "isBypass", id = 5)
    public final boolean l;
    @SafeParcelable.Field(defaultValueUnchecked = "ThrottleBehavior.THROTTLE_BACKGROUND", getter = "getThrottleBehavior", id = 7)
    public final int m;
    @Nullable
    @SafeParcelable.Field(getter = "getModuleId", id = 8)
    public final String n;
    @SafeParcelable.Field(defaultValueUnchecked = "new android.os.WorkSource()", getter = "getWorkSource", id = 6)
    public final WorkSource o;
    @Nullable
    @SafeParcelable.Field(getter = "getImpersonation", id = 9)
    public final com.google.android.gms.internal.location.zzd p;

    /* loaded from: classes10.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public long f10031a;
        public int b;
        public int c;
        public long d;
        public boolean e;
        public int f;
        @Nullable
        public String g;
        @Nullable
        public WorkSource h;
        @Nullable
        public com.google.android.gms.internal.location.zzd i;

        public Builder() {
            this.f10031a = Constants.ONE_MIN_IN_MILLIS;
            this.b = 0;
            this.c = 102;
            this.d = Long.MAX_VALUE;
            this.e = false;
            this.f = 0;
            this.g = null;
            this.h = null;
            this.i = null;
        }

        public Builder(@NonNull CurrentLocationRequest currentLocationRequest) {
            this.f10031a = currentLocationRequest.getMaxUpdateAgeMillis();
            this.b = currentLocationRequest.getGranularity();
            this.c = currentLocationRequest.getPriority();
            this.d = currentLocationRequest.getDurationMillis();
            this.e = currentLocationRequest.zze();
            this.f = currentLocationRequest.zza();
            this.g = currentLocationRequest.zzd();
            this.h = new WorkSource(currentLocationRequest.zzb());
            this.i = currentLocationRequest.zzc();
        }

        @NonNull
        public CurrentLocationRequest build() {
            return new CurrentLocationRequest(this.f10031a, this.b, this.c, this.d, this.e, this.f, this.g, new WorkSource(this.h), this.i);
        }

        @NonNull
        public Builder setDurationMillis(long j) {
            Preconditions.checkArgument(j > 0, "durationMillis must be greater than 0");
            this.d = j;
            return this;
        }

        @NonNull
        public Builder setGranularity(int i) {
            zzo.zza(i);
            this.b = i;
            return this;
        }

        @NonNull
        public Builder setMaxUpdateAgeMillis(long j) {
            Preconditions.checkArgument(j >= 0, "maxUpdateAgeMillis must be greater than or equal to 0");
            this.f10031a = j;
            return this;
        }

        @NonNull
        public Builder setPriority(int i) {
            zzae.zza(i);
            this.c = i;
            return this;
        }
    }

    @SafeParcelable.Constructor
    public CurrentLocationRequest(@SafeParcelable.Param(id = 1) long j, @SafeParcelable.Param(id = 2) int i, @SafeParcelable.Param(id = 3) int i2, @SafeParcelable.Param(id = 4) long j2, @SafeParcelable.Param(id = 5) boolean z, @SafeParcelable.Param(id = 7) int i3, @Nullable @SafeParcelable.Param(id = 8) String str, @SafeParcelable.Param(id = 6) WorkSource workSource, @Nullable @SafeParcelable.Param(id = 9) com.google.android.gms.internal.location.zzd zzdVar) {
        boolean z2 = true;
        if (Build.VERSION.SDK_INT >= 30 && str != null) {
            z2 = false;
        }
        Preconditions.checkArgument(z2);
        this.h = j;
        this.i = i;
        this.j = i2;
        this.k = j2;
        this.l = z;
        this.m = i3;
        this.n = str;
        this.o = workSource;
        this.p = zzdVar;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof CurrentLocationRequest) {
            CurrentLocationRequest currentLocationRequest = (CurrentLocationRequest) obj;
            return this.h == currentLocationRequest.h && this.i == currentLocationRequest.i && this.j == currentLocationRequest.j && this.k == currentLocationRequest.k && this.l == currentLocationRequest.l && this.m == currentLocationRequest.m && Objects.equal(this.n, currentLocationRequest.n) && Objects.equal(this.o, currentLocationRequest.o) && Objects.equal(this.p, currentLocationRequest.p);
        }
        return false;
    }

    @Pure
    public long getDurationMillis() {
        return this.k;
    }

    @Pure
    public int getGranularity() {
        return this.i;
    }

    @Pure
    public long getMaxUpdateAgeMillis() {
        return this.h;
    }

    @Pure
    public int getPriority() {
        return this.j;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.h), Integer.valueOf(this.i), Integer.valueOf(this.j), Long.valueOf(this.k));
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CurrentLocationRequest[");
        sb.append(zzae.zzb(this.j));
        if (this.h != Long.MAX_VALUE) {
            sb.append(", maxAge=");
            zzdj.zzb(this.h, sb);
        }
        if (this.k != Long.MAX_VALUE) {
            sb.append(", duration=");
            sb.append(this.k);
            sb.append("ms");
        }
        if (this.i != 0) {
            sb.append(", ");
            sb.append(zzo.zzb(this.i));
        }
        if (this.l) {
            sb.append(", bypass");
        }
        if (this.m != 0) {
            sb.append(", ");
            sb.append(zzai.zza(this.m));
        }
        if (this.n != null) {
            sb.append(", moduleId=");
            sb.append(this.n);
        }
        if (!WorkSourceUtil.isEmpty(this.o)) {
            sb.append(", workSource=");
            sb.append(this.o);
        }
        if (this.p != null) {
            sb.append(", impersonation=");
            sb.append(this.p);
        }
        sb.append(']');
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, getMaxUpdateAgeMillis());
        SafeParcelWriter.writeInt(parcel, 2, getGranularity());
        SafeParcelWriter.writeInt(parcel, 3, getPriority());
        SafeParcelWriter.writeLong(parcel, 4, getDurationMillis());
        SafeParcelWriter.writeBoolean(parcel, 5, this.l);
        SafeParcelWriter.writeParcelable(parcel, 6, this.o, i, false);
        SafeParcelWriter.writeInt(parcel, 7, this.m);
        SafeParcelWriter.writeString(parcel, 8, this.n, false);
        SafeParcelWriter.writeParcelable(parcel, 9, this.p, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Pure
    public final int zza() {
        return this.m;
    }

    @NonNull
    @Pure
    public final WorkSource zzb() {
        return this.o;
    }

    @Nullable
    @Pure
    public final com.google.android.gms.internal.location.zzd zzc() {
        return this.p;
    }

    @Nullable
    @Deprecated
    @Pure
    public final String zzd() {
        return this.n;
    }

    @Pure
    public final boolean zze() {
        return this.l;
    }
}

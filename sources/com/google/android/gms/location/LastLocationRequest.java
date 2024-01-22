package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.location.zzdj;
import org.checkerframework.dataflow.qual.Pure;
@SafeParcelable.Class(creator = "LastLocationRequestCreator")
/* loaded from: classes10.dex */
public final class LastLocationRequest extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<LastLocationRequest> CREATOR = new zzv();
    @SafeParcelable.Field(defaultValueUnchecked = "Long.MAX_VALUE", getter = "getMaxUpdateAgeMillis", id = 1)
    public final long h;
    @SafeParcelable.Field(defaultValueUnchecked = "com.google.android.gms.location.Granularity.GRANULARITY_PERMISSION_LEVEL", getter = "getGranularity", id = 2)
    public final int i;
    @SafeParcelable.Field(defaultValue = "false", getter = "isBypass", id = 3)
    public final boolean j;
    @Nullable
    @SafeParcelable.Field(getter = "getModuleId", id = 4)
    public final String k;
    @Nullable
    @SafeParcelable.Field(getter = "getImpersonation", id = 5)
    public final com.google.android.gms.internal.location.zzd l;

    /* loaded from: classes10.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public long f10035a;
        public int b;
        public boolean c;
        @Nullable
        public String d;
        @Nullable
        public com.google.android.gms.internal.location.zzd e;

        public Builder() {
            this.f10035a = Long.MAX_VALUE;
            this.b = 0;
            this.c = false;
            this.d = null;
            this.e = null;
        }

        public Builder(@NonNull LastLocationRequest lastLocationRequest) {
            this.f10035a = lastLocationRequest.getMaxUpdateAgeMillis();
            this.b = lastLocationRequest.getGranularity();
            this.c = lastLocationRequest.zzc();
            this.d = lastLocationRequest.zzb();
            this.e = lastLocationRequest.zza();
        }

        @NonNull
        public LastLocationRequest build() {
            return new LastLocationRequest(this.f10035a, this.b, this.c, this.d, this.e);
        }

        @NonNull
        public Builder setGranularity(int i) {
            zzo.zza(i);
            this.b = i;
            return this;
        }

        @NonNull
        public Builder setMaxUpdateAgeMillis(long j) {
            Preconditions.checkArgument(j > 0, "maxUpdateAgeMillis must be greater than 0");
            this.f10035a = j;
            return this;
        }
    }

    @SafeParcelable.Constructor
    public LastLocationRequest(@SafeParcelable.Param(id = 1) long j, @SafeParcelable.Param(id = 2) int i, @SafeParcelable.Param(id = 3) boolean z, @Nullable @SafeParcelable.Param(id = 4) String str, @Nullable @SafeParcelable.Param(id = 5) com.google.android.gms.internal.location.zzd zzdVar) {
        this.h = j;
        this.i = i;
        this.j = z;
        this.k = str;
        this.l = zzdVar;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof LastLocationRequest) {
            LastLocationRequest lastLocationRequest = (LastLocationRequest) obj;
            return this.h == lastLocationRequest.h && this.i == lastLocationRequest.i && this.j == lastLocationRequest.j && Objects.equal(this.k, lastLocationRequest.k) && Objects.equal(this.l, lastLocationRequest.l);
        }
        return false;
    }

    @Pure
    public int getGranularity() {
        return this.i;
    }

    @Pure
    public long getMaxUpdateAgeMillis() {
        return this.h;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.h), Integer.valueOf(this.i), Boolean.valueOf(this.j));
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LastLocationRequest[");
        if (this.h != Long.MAX_VALUE) {
            sb.append("maxAge=");
            zzdj.zzb(this.h, sb);
        }
        if (this.i != 0) {
            sb.append(", ");
            sb.append(zzo.zzb(this.i));
        }
        if (this.j) {
            sb.append(", bypass");
        }
        if (this.k != null) {
            sb.append(", moduleId=");
            sb.append(this.k);
        }
        if (this.l != null) {
            sb.append(", impersonation=");
            sb.append(this.l);
        }
        sb.append(']');
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, getMaxUpdateAgeMillis());
        SafeParcelWriter.writeInt(parcel, 2, getGranularity());
        SafeParcelWriter.writeBoolean(parcel, 3, this.j);
        SafeParcelWriter.writeString(parcel, 4, this.k, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.l, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Nullable
    @Pure
    public final com.google.android.gms.internal.location.zzd zza() {
        return this.l;
    }

    @Nullable
    @Deprecated
    @Pure
    public final String zzb() {
        return this.k;
    }

    @Pure
    public final boolean zzc() {
        return this.j;
    }
}

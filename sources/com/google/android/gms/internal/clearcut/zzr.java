package com.google.android.gms.internal.clearcut;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.clearcut.zzge;
@SafeParcelable.Class(creator = "PlayLoggerContextCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes7.dex */
public final class zzr extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzr> CREATOR = new zzs();
    @SafeParcelable.Field(id = 2)
    public final String h;
    @SafeParcelable.Field(id = 3)
    public final int i;
    @SafeParcelable.Field(id = 5)
    public final String j;
    @SafeParcelable.Field(id = 6)
    public final String k;
    @SafeParcelable.Field(defaultValue = "true", id = 7)
    public final boolean l;
    @SafeParcelable.Field(id = 9)
    public final boolean m;
    @SafeParcelable.Field(id = 10)
    public final int n;
    @SafeParcelable.Field(id = 8)
    public final String zzj;
    @SafeParcelable.Field(id = 4)
    public final int zzk;

    public zzr(String str, int i, int i2, String str2, String str3, String str4, boolean z, zzge.zzv.zzb zzbVar) {
        this.h = (String) Preconditions.checkNotNull(str);
        this.i = i;
        this.zzk = i2;
        this.zzj = str2;
        this.j = str3;
        this.k = str4;
        this.l = !z;
        this.m = z;
        this.n = zzbVar.zzc();
    }

    @SafeParcelable.Constructor
    public zzr(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) int i, @SafeParcelable.Param(id = 4) int i2, @SafeParcelable.Param(id = 5) String str2, @SafeParcelable.Param(id = 6) String str3, @SafeParcelable.Param(id = 7) boolean z, @SafeParcelable.Param(id = 8) String str4, @SafeParcelable.Param(id = 9) boolean z2, @SafeParcelable.Param(id = 10) int i3) {
        this.h = str;
        this.i = i;
        this.zzk = i2;
        this.j = str2;
        this.k = str3;
        this.l = z;
        this.zzj = str4;
        this.m = z2;
        this.n = i3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzr) {
            zzr zzrVar = (zzr) obj;
            if (Objects.equal(this.h, zzrVar.h) && this.i == zzrVar.i && this.zzk == zzrVar.zzk && Objects.equal(this.zzj, zzrVar.zzj) && Objects.equal(this.j, zzrVar.j) && Objects.equal(this.k, zzrVar.k) && this.l == zzrVar.l && this.m == zzrVar.m && this.n == zzrVar.n) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.h, Integer.valueOf(this.i), Integer.valueOf(this.zzk), this.zzj, this.j, this.k, Boolean.valueOf(this.l), Boolean.valueOf(this.m), Integer.valueOf(this.n));
    }

    public final String toString() {
        return "PlayLoggerContext[package=" + this.h + ",packageVersionCode=" + this.i + ",logSource=" + this.zzk + ",logSourceName=" + this.zzj + ",uploadAccount=" + this.j + ",loggingId=" + this.k + ",logAndroidId=" + this.l + ",isAnonymous=" + this.m + ",qosTier=" + this.n + "]";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.h, false);
        SafeParcelWriter.writeInt(parcel, 3, this.i);
        SafeParcelWriter.writeInt(parcel, 4, this.zzk);
        SafeParcelWriter.writeString(parcel, 5, this.j, false);
        SafeParcelWriter.writeString(parcel, 6, this.k, false);
        SafeParcelWriter.writeBoolean(parcel, 7, this.l);
        SafeParcelWriter.writeString(parcel, 8, this.zzj, false);
        SafeParcelWriter.writeBoolean(parcel, 9, this.m);
        SafeParcelWriter.writeInt(parcel, 10, this.n);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

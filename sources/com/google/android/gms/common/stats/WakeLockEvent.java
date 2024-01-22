package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;
import javax.annotation.Nullable;
@KeepForSdk
@SafeParcelable.Class(creator = "WakeLockEventCreator")
@Deprecated
/* loaded from: classes6.dex */
public final class WakeLockEvent extends StatsEvent {
    @NonNull
    public static final Parcelable.Creator<WakeLockEvent> CREATOR = new zza();
    @SafeParcelable.VersionField(id = 1)
    public final int h;
    @SafeParcelable.Field(getter = "getTimeMillis", id = 2)
    public final long i;
    @SafeParcelable.Field(getter = "getEventType", id = 11)
    public final int j;
    @SafeParcelable.Field(getter = "getWakeLockName", id = 4)
    public final String k;
    @SafeParcelable.Field(getter = "getSecondaryWakeLockName", id = 10)
    public final String l;
    @SafeParcelable.Field(getter = "getCodePackage", id = 17)
    public final String m;
    @SafeParcelable.Field(getter = "getWakeLockType", id = 5)
    public final int n;
    @Nullable
    @SafeParcelable.Field(getter = "getCallingPackages", id = 6)
    public final List o;
    @SafeParcelable.Field(getter = "getEventKey", id = 12)
    public final String p;
    @SafeParcelable.Field(getter = "getElapsedRealtime", id = 8)
    public final long q;
    @SafeParcelable.Field(getter = "getDeviceState", id = 14)
    public final int r;
    @SafeParcelable.Field(getter = "getHostPackage", id = 13)
    public final String s;
    @SafeParcelable.Field(getter = "getBeginPowerPercentage", id = 15)
    public final float t;
    @SafeParcelable.Field(getter = "getTimeout", id = 16)
    public final long u;
    @SafeParcelable.Field(getter = "getAcquiredWithTimeout", id = 18)
    public final boolean v;

    @SafeParcelable.Constructor
    public WakeLockEvent(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) long j, @SafeParcelable.Param(id = 11) int i2, @SafeParcelable.Param(id = 4) String str, @SafeParcelable.Param(id = 5) int i3, @SafeParcelable.Param(id = 6) @Nullable List list, @SafeParcelable.Param(id = 12) String str2, @SafeParcelable.Param(id = 8) long j2, @SafeParcelable.Param(id = 14) int i4, @SafeParcelable.Param(id = 10) String str3, @SafeParcelable.Param(id = 13) String str4, @SafeParcelable.Param(id = 15) float f, @SafeParcelable.Param(id = 16) long j3, @SafeParcelable.Param(id = 17) String str5, @SafeParcelable.Param(id = 18) boolean z) {
        this.h = i;
        this.i = j;
        this.j = i2;
        this.k = str;
        this.l = str3;
        this.m = str5;
        this.n = i3;
        this.o = list;
        this.p = str2;
        this.q = j2;
        this.r = i4;
        this.s = str4;
        this.t = f;
        this.u = j3;
        this.v = z;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.h);
        SafeParcelWriter.writeLong(parcel, 2, this.i);
        SafeParcelWriter.writeString(parcel, 4, this.k, false);
        SafeParcelWriter.writeInt(parcel, 5, this.n);
        SafeParcelWriter.writeStringList(parcel, 6, this.o, false);
        SafeParcelWriter.writeLong(parcel, 8, this.q);
        SafeParcelWriter.writeString(parcel, 10, this.l, false);
        SafeParcelWriter.writeInt(parcel, 11, this.j);
        SafeParcelWriter.writeString(parcel, 12, this.p, false);
        SafeParcelWriter.writeString(parcel, 13, this.s, false);
        SafeParcelWriter.writeInt(parcel, 14, this.r);
        SafeParcelWriter.writeFloat(parcel, 15, this.t);
        SafeParcelWriter.writeLong(parcel, 16, this.u);
        SafeParcelWriter.writeString(parcel, 17, this.m, false);
        SafeParcelWriter.writeBoolean(parcel, 18, this.v);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Override // com.google.android.gms.common.stats.StatsEvent
    public final int zza() {
        return this.j;
    }

    @Override // com.google.android.gms.common.stats.StatsEvent
    public final long zzb() {
        return this.i;
    }

    @Override // com.google.android.gms.common.stats.StatsEvent
    @NonNull
    public final String zzc() {
        List list = this.o;
        String str = this.k;
        int i = this.n;
        String join = list == null ? "" : TextUtils.join(Constants.SEPARATOR_COMMA, list);
        int i2 = this.r;
        String str2 = this.l;
        if (str2 == null) {
            str2 = "";
        }
        String str3 = this.s;
        if (str3 == null) {
            str3 = "";
        }
        float f = this.t;
        String str4 = this.m;
        String str5 = str4 != null ? str4 : "";
        boolean z = this.v;
        return "\t" + str + "\t" + i + "\t" + join + "\t" + i2 + "\t" + str2 + "\t" + str3 + "\t" + f + "\t" + str5 + "\t" + z;
    }
}

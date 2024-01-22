package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.WorkSource;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@ShowFirstParty
@SafeParcelable.Class(creator = "ActivityRecognitionRequestCreator")
@SafeParcelable.Reserved({1000})
/* loaded from: classes10.dex */
public final class zzb extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzb> CREATOR = new zzc();
    @SafeParcelable.Field(getter = "getIntervalMillis", id = 1)
    public final long h;
    @SafeParcelable.Field(getter = "getTriggerUpdate", id = 2)
    public final boolean i;
    @Nullable
    @SafeParcelable.Field(getter = "getWorkSource", id = 3)
    public final WorkSource j;
    @Nullable
    @SafeParcelable.Field(getter = "getTag", id = 4)
    public final String k;
    @Nullable
    @SafeParcelable.Field(getter = "getNondefaultActivities", id = 5)
    public final int[] l;
    @SafeParcelable.Field(getter = "getRequestSensorData", id = 6)
    public final boolean m;
    @Nullable
    @SafeParcelable.Field(getter = "getAccountName", id = 7)
    public final String n;
    @SafeParcelable.Field(defaultValueUnchecked = "ActivityRecognitionRequest.DEFAULT_MAX_REPORT_LATENCY_MILLIS", getter = "getMaxReportLatencyMillis", id = 8)
    public final long o;
    @Nullable
    @SafeParcelable.Field(getter = "getContextAttributionTag", id = 9)
    public String p;

    @SafeParcelable.Constructor
    public zzb(@SafeParcelable.Param(id = 1) long j, @SafeParcelable.Param(id = 2) boolean z, @Nullable @SafeParcelable.Param(id = 3) WorkSource workSource, @Nullable @SafeParcelable.Param(id = 4) String str, @Nullable @SafeParcelable.Param(id = 5) int[] iArr, @SafeParcelable.Param(id = 6) boolean z2, @Nullable @SafeParcelable.Param(id = 7) String str2, @SafeParcelable.Param(id = 8) long j2, @Nullable @SafeParcelable.Param(id = 9) String str3) {
        this.h = j;
        this.i = z;
        this.j = workSource;
        this.k = str;
        this.l = iArr;
        this.m = z2;
        this.n = str2;
        this.o = j2;
        this.p = str3;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        Preconditions.checkNotNull(parcel);
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.h);
        SafeParcelWriter.writeBoolean(parcel, 2, this.i);
        SafeParcelWriter.writeParcelable(parcel, 3, this.j, i, false);
        SafeParcelWriter.writeString(parcel, 4, this.k, false);
        SafeParcelWriter.writeIntArray(parcel, 5, this.l, false);
        SafeParcelWriter.writeBoolean(parcel, 6, this.m);
        SafeParcelWriter.writeString(parcel, 7, this.n, false);
        SafeParcelWriter.writeLong(parcel, 8, this.o);
        SafeParcelWriter.writeString(parcel, 9, this.p, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final zzb zza(@Nullable String str) {
        this.p = str;
        return this;
    }
}

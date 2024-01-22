package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.zzu;
import com.google.android.gms.internal.fitness.zzcm;
import com.google.android.gms.internal.fitness.zzcn;
import java.util.Collections;
import java.util.List;
@ShowFirstParty
@SafeParcelable.Class(creator = "SensorRegistrationRequestCreator")
@SafeParcelable.Reserved({4, 5, 11, 14, 1000})
/* loaded from: classes6.dex */
public final class zzap extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzap> CREATOR = new zzao();
    @Nullable
    @SafeParcelable.Field(getter = "getDataSource", id = 1)
    public DataSource h;
    @Nullable
    @SafeParcelable.Field(getter = "getDataType", id = 2)
    public DataType i;
    @Nullable
    @SafeParcelable.Field(getter = "getListenerBinder", id = 3, type = "android.os.IBinder")
    public final com.google.android.gms.fitness.data.zzv j;
    @SafeParcelable.Field(getter = "getSamplingRateMicros", id = 6)
    public final long k;
    @SafeParcelable.Field(getter = "getMaxDeliveryLatencyMicros", id = 7)
    public final long l;
    @Nullable
    @SafeParcelable.Field(getter = "getIntent", id = 8)
    public final PendingIntent m;
    @SafeParcelable.Field(getter = "getFastestRateMicros", id = 9)
    public final long n;
    @SafeParcelable.Field(getter = "getAccuracyMode", id = 10)
    public final int o;
    @SafeParcelable.Field(getter = "getRegistrationTimeOutMicros", id = 12)
    public final long p;
    public final List<ClientIdentity> q;
    @Nullable
    @SafeParcelable.Field(getter = "getCallbackBinder", id = 13, type = "android.os.IBinder")
    public final zzcn r;

    @SafeParcelable.Constructor
    public zzap(@Nullable @SafeParcelable.Param(id = 1) DataSource dataSource, @Nullable @SafeParcelable.Param(id = 2) DataType dataType, @Nullable @SafeParcelable.Param(id = 3) IBinder iBinder, @SafeParcelable.Param(id = 6) long j, @SafeParcelable.Param(id = 7) long j2, @Nullable @SafeParcelable.Param(id = 8) PendingIntent pendingIntent, @SafeParcelable.Param(id = 9) long j3, @SafeParcelable.Param(id = 10) int i, @SafeParcelable.Param(id = 12) long j4, @Nullable @SafeParcelable.Param(id = 13) IBinder iBinder2) {
        this.h = dataSource;
        this.i = dataType;
        this.j = iBinder == null ? null : zzu.zza(iBinder);
        this.k = j;
        this.n = j3;
        this.l = j2;
        this.m = pendingIntent;
        this.o = i;
        this.q = Collections.emptyList();
        this.p = j4;
        this.r = iBinder2 != null ? zzcm.zzj(iBinder2) : null;
    }

    public final boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzap) {
            zzap zzapVar = (zzap) obj;
            return Objects.equal(this.h, zzapVar.h) && Objects.equal(this.i, zzapVar.i) && Objects.equal(this.j, zzapVar.j) && this.k == zzapVar.k && this.n == zzapVar.n && this.l == zzapVar.l && this.o == zzapVar.o;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.h, this.i, this.j, Long.valueOf(this.k), Long.valueOf(this.n), Long.valueOf(this.l), Integer.valueOf(this.o));
    }

    public final String toString() {
        return String.format("SensorRegistrationRequest{type %s source %s interval %s fastest %s latency %s}", this.i, this.h, Long.valueOf(this.k), Long.valueOf(this.n), Long.valueOf(this.l));
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.h, i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.i, i, false);
        com.google.android.gms.fitness.data.zzv zzvVar = this.j;
        SafeParcelWriter.writeIBinder(parcel, 3, zzvVar == null ? null : zzvVar.asBinder(), false);
        SafeParcelWriter.writeLong(parcel, 6, this.k);
        SafeParcelWriter.writeLong(parcel, 7, this.l);
        SafeParcelWriter.writeParcelable(parcel, 8, this.m, i, false);
        SafeParcelWriter.writeLong(parcel, 9, this.n);
        SafeParcelWriter.writeInt(parcel, 10, this.o);
        SafeParcelWriter.writeLong(parcel, 12, this.p);
        zzcn zzcnVar = this.r;
        SafeParcelWriter.writeIBinder(parcel, 13, zzcnVar != null ? zzcnVar.asBinder() : null, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public zzap(com.google.android.gms.fitness.request.SensorRequest r17, @androidx.annotation.Nullable com.google.android.gms.fitness.data.zzv r18, @androidx.annotation.Nullable android.app.PendingIntent r19, com.google.android.gms.internal.fitness.zzcn r20) {
        /*
            r16 = this;
            r0 = r17
            com.google.android.gms.fitness.data.DataSource r1 = r17.getDataSource()
            com.google.android.gms.fitness.data.DataType r2 = r17.getDataType()
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.MICROSECONDS
            long r5 = r0.getSamplingRate(r3)
            long r7 = r0.getFastestRate(r3)
            long r9 = r0.getMaxDeliveryLatency(r3)
            int r11 = r17.getAccuracyMode()
            java.util.List r12 = java.util.Collections.emptyList()
            long r13 = r17.zzy()
            r0 = r16
            r3 = r18
            r4 = r19
            r15 = r20
            r0.<init>(r1, r2, r3, r4, r5, r7, r9, r11, r12, r13, r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fitness.request.zzap.<init>(com.google.android.gms.fitness.request.SensorRequest, com.google.android.gms.fitness.data.zzv, android.app.PendingIntent, com.google.android.gms.internal.fitness.zzcn):void");
    }

    public zzap(@Nullable DataSource dataSource, @Nullable DataType dataType, @Nullable com.google.android.gms.fitness.data.zzv zzvVar, @Nullable PendingIntent pendingIntent, long j, long j2, long j3, int i, List<ClientIdentity> list, long j4, @Nullable zzcn zzcnVar) {
        this.h = dataSource;
        this.i = dataType;
        this.j = zzvVar;
        this.m = pendingIntent;
        this.k = j;
        this.n = j2;
        this.l = j3;
        this.o = i;
        this.q = list;
        this.p = j4;
        this.r = zzcnVar;
    }
}

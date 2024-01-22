package com.google.android.gms.fitness.service;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.zzu;
import com.google.android.gms.fitness.data.zzv;
import java.util.concurrent.TimeUnit;
@SafeParcelable.Class(creator = "FitnessSensorServiceRequestCreator")
@SafeParcelable.Reserved({1000})
/* loaded from: classes6.dex */
public class FitnessSensorServiceRequest extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<FitnessSensorServiceRequest> CREATOR = new zzb();
    public static final int UNSPECIFIED = -1;
    @SafeParcelable.Field(getter = "getDataSource", id = 1)
    public final DataSource h;
    @SafeParcelable.Field(getter = "getListenerBinder", id = 2, type = "android.os.IBinder")
    public final zzv i;
    @SafeParcelable.Field(getter = "getSamplingRateMicros", id = 3)
    public final long j;
    @SafeParcelable.Field(getter = "getBatchIntervalMicros", id = 4)
    public final long k;

    @SafeParcelable.Constructor
    public FitnessSensorServiceRequest(@SafeParcelable.Param(id = 1) DataSource dataSource, @SafeParcelable.Param(id = 2) IBinder iBinder, @SafeParcelable.Param(id = 3) long j, @SafeParcelable.Param(id = 4) long j2) {
        this.h = dataSource;
        this.i = zzu.zza(iBinder);
        this.j = j;
        this.k = j2;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FitnessSensorServiceRequest) {
            FitnessSensorServiceRequest fitnessSensorServiceRequest = (FitnessSensorServiceRequest) obj;
            return Objects.equal(this.h, fitnessSensorServiceRequest.h) && this.j == fitnessSensorServiceRequest.j && this.k == fitnessSensorServiceRequest.k;
        }
        return false;
    }

    public long getBatchInterval(@NonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.k, TimeUnit.MICROSECONDS);
    }

    @NonNull
    public DataSource getDataSource() {
        return this.h;
    }

    @NonNull
    public SensorEventDispatcher getDispatcher() {
        return new b(this.i);
    }

    public long getSamplingRate(@NonNull TimeUnit timeUnit) {
        long j = this.j;
        if (j == -1) {
            return -1L;
        }
        return timeUnit.convert(j, TimeUnit.MICROSECONDS);
    }

    public int hashCode() {
        return Objects.hashCode(this.h, Long.valueOf(this.j), Long.valueOf(this.k));
    }

    @NonNull
    public String toString() {
        return String.format("FitnessSensorServiceRequest{%s}", this.h);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getDataSource(), i, false);
        SafeParcelWriter.writeIBinder(parcel, 2, this.i.asBinder(), false);
        SafeParcelWriter.writeLong(parcel, 3, this.j);
        SafeParcelWriter.writeLong(parcel, 4, this.k);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

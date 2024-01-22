package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.fitness.zzcm;
import com.google.android.gms.internal.fitness.zzcn;
import java.util.Collections;
import java.util.List;
@SafeParcelable.Class(creator = "StartBleScanRequestCreator")
@SafeParcelable.Reserved({5, 1000})
@Deprecated
/* loaded from: classes6.dex */
public class StartBleScanRequest extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<StartBleScanRequest> CREATOR = new zzbh();
    @SafeParcelable.Field(getter = "getDataTypes", id = 1)
    public final List<DataType> h;
    @Nullable
    @SafeParcelable.Field(getter = "getBleScanCallbackBinder", id = 2, type = "android.os.IBinder")
    public final zzad i;
    @SafeParcelable.Field(getter = "getTimeoutSecs", id = 3)
    public final int j;
    @Nullable
    @SafeParcelable.Field(getter = "getCallbackBinder", id = 4, type = "android.os.IBinder")
    public final zzcn k;
    @Nullable
    public final BleScanCallback l;

    /* loaded from: classes6.dex */
    public static class Builder {
        public BleScanCallback b;

        /* renamed from: a  reason: collision with root package name */
        public DataType[] f8458a = new DataType[0];
        public int c = 10;

        @NonNull
        public StartBleScanRequest build() {
            Preconditions.checkState(this.b != null, "Must set BleScanCallback");
            return new StartBleScanRequest(ArrayUtils.toArrayList(this.f8458a), this.b, this.c);
        }

        @NonNull
        public Builder setBleScanCallback(@NonNull BleScanCallback bleScanCallback) {
            this.b = bleScanCallback;
            return this;
        }

        @NonNull
        public Builder setDataTypes(@NonNull DataType... dataTypeArr) {
            this.f8458a = dataTypeArr;
            return this;
        }

        @NonNull
        public Builder setTimeoutSecs(int i) {
            Preconditions.checkArgument(i > 0, "Stop time must be greater than zero");
            Preconditions.checkArgument(i <= 60, "Stop time must be less than 1 minute");
            this.c = i;
            return this;
        }
    }

    @SafeParcelable.Constructor
    public StartBleScanRequest(@SafeParcelable.Param(id = 1) List<DataType> list, @Nullable @SafeParcelable.Param(id = 2) IBinder iBinder, @SafeParcelable.Param(id = 3) int i, @Nullable @SafeParcelable.Param(id = 4) IBinder iBinder2) {
        zzad zzafVar;
        this.h = list;
        if (iBinder == null) {
            zzafVar = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.request.IBleScanCallback");
            if (queryLocalInterface instanceof zzad) {
                zzafVar = (zzad) queryLocalInterface;
            } else {
                zzafVar = new zzaf(iBinder);
            }
        }
        this.i = zzafVar;
        this.j = i;
        this.k = iBinder2 == null ? null : zzcm.zzj(iBinder2);
        this.l = null;
    }

    @NonNull
    public List<DataType> getDataTypes() {
        return Collections.unmodifiableList(this.h);
    }

    public int getTimeoutSecs() {
        return this.j;
    }

    @NonNull
    public String toString() {
        return Objects.toStringHelper(this).add("dataTypes", this.h).add("timeoutSecs", Integer.valueOf(this.j)).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getDataTypes(), false);
        zzad zzadVar = this.i;
        SafeParcelWriter.writeIBinder(parcel, 2, zzadVar == null ? null : zzadVar.asBinder(), false);
        SafeParcelWriter.writeInt(parcel, 3, getTimeoutSecs());
        zzcn zzcnVar = this.k;
        SafeParcelWriter.writeIBinder(parcel, 4, zzcnVar != null ? zzcnVar.asBinder() : null, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Nullable
    public final BleScanCallback zzaa() {
        return this.l;
    }

    public StartBleScanRequest(List<DataType> list, BleScanCallback bleScanCallback, int i) {
        this.h = list;
        this.i = null;
        this.j = i;
        this.k = null;
        this.l = bleScanCallback;
    }

    public StartBleScanRequest(List<DataType> list, @Nullable zzad zzadVar, int i, @Nullable zzcn zzcnVar) {
        this.h = list;
        this.i = zzadVar;
        this.j = i;
        this.k = zzcnVar;
        this.l = null;
    }
}

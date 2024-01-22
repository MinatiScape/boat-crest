package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataType;
@ShowFirstParty
@SafeParcelable.Class(creator = "DailyTotalRequestCreator")
@SafeParcelable.Reserved({3, 1000})
/* loaded from: classes6.dex */
public final class zzf extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzf> CREATOR = new zzh();
    @SafeParcelable.Field(getter = "getCallbackBinder", id = 1, type = "android.os.IBinder")
    public final com.google.android.gms.internal.fitness.zzbb h;
    @Nullable
    @SafeParcelable.Field(getter = "getDataType", id = 2)
    public final DataType i;
    @SafeParcelable.Field(getter = "getLocalDataOnly", id = 4)
    public final boolean j;

    @SafeParcelable.Constructor
    public zzf(@SafeParcelable.Param(id = 1) IBinder iBinder, @Nullable @SafeParcelable.Param(id = 2) DataType dataType, @SafeParcelable.Param(id = 4) boolean z) {
        this.h = com.google.android.gms.internal.fitness.zzba.zzb(iBinder);
        this.i = dataType;
        this.j = z;
    }

    public final String toString() {
        Object[] objArr = new Object[1];
        DataType dataType = this.i;
        objArr[0] = dataType == null ? "null" : dataType.zzm();
        return String.format("DailyTotalRequest{%s}", objArr);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 1, this.h.asBinder(), false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.i, i, false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.j);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public zzf(com.google.android.gms.internal.fitness.zzbb zzbbVar, @Nullable DataType dataType, boolean z) {
        this.h = zzbbVar;
        this.i = dataType;
        this.j = z;
    }
}

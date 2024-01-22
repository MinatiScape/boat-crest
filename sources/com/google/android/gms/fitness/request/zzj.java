package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.internal.fitness.zzcm;
import com.google.android.gms.internal.fitness.zzcn;
@ShowFirstParty
@SafeParcelable.Class(creator = "DataInsertRequestCreator")
@SafeParcelable.Reserved({3, 1000})
/* loaded from: classes6.dex */
public final class zzj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzj> CREATOR = new zzl();
    @SafeParcelable.Field(getter = "getDataSet", id = 1)
    public final DataSet h;
    @Nullable
    @SafeParcelable.Field(getter = "getCallbackBinder", id = 2, type = "android.os.IBinder")
    public final zzcn i;
    @SafeParcelable.Field(getter = "shouldSkipSync", id = 4)
    public final boolean j;

    @SafeParcelable.Constructor
    public zzj(@SafeParcelable.Param(id = 1) DataSet dataSet, @Nullable @SafeParcelable.Param(id = 2) IBinder iBinder, @SafeParcelable.Param(id = 4) boolean z) {
        this.h = dataSet;
        this.i = iBinder == null ? null : zzcm.zzj(iBinder);
        this.j = z;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj != this) {
            return (obj instanceof zzj) && Objects.equal(this.h, ((zzj) obj).h);
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(this.h);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("dataSet", this.h).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.h, i, false);
        zzcn zzcnVar = this.i;
        SafeParcelWriter.writeIBinder(parcel, 2, zzcnVar == null ? null : zzcnVar.asBinder(), false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.j);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public zzj(DataSet dataSet, zzcn zzcnVar, boolean z) {
        this.h = dataSet;
        this.i = zzcnVar;
        this.j = z;
    }
}

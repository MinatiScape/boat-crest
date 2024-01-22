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
import com.google.android.gms.measurement.api.AppMeasurementSdk;
@SafeParcelable.Class(creator = "DataTypeReadRequestCreator")
@SafeParcelable.Reserved({4, 1000})
@Deprecated
@ShowFirstParty
/* loaded from: classes6.dex */
public final class zzr extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzr> CREATOR = new zzt();
    @SafeParcelable.Field(getter = "getName", id = 1)
    public final String h;
    @SafeParcelable.Field(getter = "getCallbackBinder", id = 3, type = "android.os.IBinder")
    public final com.google.android.gms.internal.fitness.zzbi i;

    @SafeParcelable.Constructor
    public zzr(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 3) IBinder iBinder) {
        this.h = str;
        this.i = com.google.android.gms.internal.fitness.zzbl.zze(iBinder);
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj != this) {
            return (obj instanceof zzr) && Objects.equal(this.h, ((zzr) obj).h);
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(this.h);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add(AppMeasurementSdk.ConditionalUserProperty.NAME, this.h).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.h, false);
        SafeParcelWriter.writeIBinder(parcel, 3, this.i.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public zzr(String str, com.google.android.gms.internal.fitness.zzbi zzbiVar) {
        this.h = str;
        this.i = zzbiVar;
    }
}

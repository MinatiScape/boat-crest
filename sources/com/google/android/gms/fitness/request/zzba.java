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
import com.google.android.gms.internal.fitness.zzci;
import com.google.android.gms.internal.fitness.zzcl;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
@ShowFirstParty
@SafeParcelable.Class(creator = "SessionStopRequestCreator")
@SafeParcelable.Reserved({4, 1000})
/* loaded from: classes6.dex */
public final class zzba extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzba> CREATOR = new zzbd();
    @Nullable
    @SafeParcelable.Field(getter = "getName", id = 1)
    public final String h;
    @Nullable
    @SafeParcelable.Field(getter = "getIdentifier", id = 2)
    public final String i;
    @Nullable
    @SafeParcelable.Field(getter = "getCallbackBinder", id = 3, type = "android.os.IBinder")
    public final zzci j;

    @SafeParcelable.Constructor
    public zzba(@Nullable @SafeParcelable.Param(id = 1) String str, @Nullable @SafeParcelable.Param(id = 2) String str2, @Nullable @SafeParcelable.Param(id = 3) IBinder iBinder) {
        this.h = str;
        this.i = str2;
        this.j = iBinder == null ? null : zzcl.zzi(iBinder);
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzba) {
            zzba zzbaVar = (zzba) obj;
            return Objects.equal(this.h, zzbaVar.h) && Objects.equal(this.i, zzbaVar.i);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.h, this.i);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add(AppMeasurementSdk.ConditionalUserProperty.NAME, this.h).add("identifier", this.i).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.h, false);
        SafeParcelWriter.writeString(parcel, 2, this.i, false);
        zzci zzciVar = this.j;
        SafeParcelWriter.writeIBinder(parcel, 3, zzciVar == null ? null : zzciVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public zzba(@Nullable String str, @Nullable String str2, @Nullable zzci zzciVar) {
        this.h = str;
        this.i = str2;
        this.j = zzciVar;
    }
}

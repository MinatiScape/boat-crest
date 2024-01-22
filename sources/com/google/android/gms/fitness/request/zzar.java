package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.zzu;
import com.google.android.gms.internal.fitness.zzcm;
import com.google.android.gms.internal.fitness.zzcn;
@ShowFirstParty
@SafeParcelable.Class(creator = "SensorUnregistrationRequestCreator")
@SafeParcelable.Reserved({4, 1000})
/* loaded from: classes6.dex */
public final class zzar extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzar> CREATOR = new zzas();
    @Nullable
    @SafeParcelable.Field(getter = "getListenerBinder", id = 1, type = "android.os.IBinder")
    public final com.google.android.gms.fitness.data.zzv h;
    @Nullable
    @SafeParcelable.Field(getter = "getIntent", id = 2)
    public final PendingIntent i;
    @Nullable
    @SafeParcelable.Field(getter = "getCallbackBinder", id = 3, type = "android.os.IBinder")
    public final zzcn j;

    @SafeParcelable.Constructor
    public zzar(@Nullable @SafeParcelable.Param(id = 1) IBinder iBinder, @Nullable @SafeParcelable.Param(id = 2) PendingIntent pendingIntent, @Nullable @SafeParcelable.Param(id = 3) IBinder iBinder2) {
        this.h = iBinder == null ? null : zzu.zza(iBinder);
        this.i = pendingIntent;
        this.j = iBinder2 != null ? zzcm.zzj(iBinder2) : null;
    }

    public final String toString() {
        return String.format("SensorUnregistrationRequest{%s}", this.h);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        com.google.android.gms.fitness.data.zzv zzvVar = this.h;
        SafeParcelWriter.writeIBinder(parcel, 1, zzvVar == null ? null : zzvVar.asBinder(), false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.i, i, false);
        zzcn zzcnVar = this.j;
        SafeParcelWriter.writeIBinder(parcel, 3, zzcnVar != null ? zzcnVar.asBinder() : null, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public zzar(@Nullable com.google.android.gms.fitness.data.zzv zzvVar, @Nullable PendingIntent pendingIntent, @Nullable zzcn zzcnVar) {
        this.h = zzvVar;
        this.i = pendingIntent;
        this.j = zzcnVar;
    }
}

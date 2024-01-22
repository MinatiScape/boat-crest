package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.fitness.zzcm;
import com.google.android.gms.internal.fitness.zzcn;
@SafeParcelable.Class(creator = "StopBleScanRequestCreator")
@SafeParcelable.Reserved({3, 1000})
@Deprecated
@ShowFirstParty
/* loaded from: classes6.dex */
public final class zzbg extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbg> CREATOR = new zzbj();
    @SafeParcelable.Field(getter = "getBleScanCallbackBinder", id = 1, type = "android.os.IBinder")
    public final zzad h;
    @Nullable
    @SafeParcelable.Field(getter = "getCallbackBinder", id = 2, type = "android.os.IBinder")
    public final zzcn i;

    @SafeParcelable.Constructor
    public zzbg(@SafeParcelable.Param(id = 1) IBinder iBinder, @Nullable @SafeParcelable.Param(id = 2) IBinder iBinder2) {
        zzad zzafVar;
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
        this.h = zzafVar;
        this.i = iBinder2 != null ? zzcm.zzj(iBinder2) : null;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 1, this.h.asBinder(), false);
        zzcn zzcnVar = this.i;
        SafeParcelWriter.writeIBinder(parcel, 2, zzcnVar == null ? null : zzcnVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public zzbg(zzad zzadVar, @Nullable zzcn zzcnVar) {
        this.h = zzadVar;
        this.i = zzcnVar;
    }
}

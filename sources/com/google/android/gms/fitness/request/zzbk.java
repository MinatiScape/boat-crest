package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.fitness.zzcm;
import com.google.android.gms.internal.fitness.zzcn;
@ShowFirstParty
@SafeParcelable.Class(creator = "UnclaimBleDeviceRequestCreator")
@SafeParcelable.Reserved({1, 4, 1000})
/* loaded from: classes6.dex */
public final class zzbk extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbk> CREATOR = new zzbn();
    @SafeParcelable.Field(getter = "getDeviceAddress", id = 2)
    public final String h;
    @Nullable
    @SafeParcelable.Field(getter = "getCallbackBinder", id = 3, type = "android.os.IBinder")
    public final zzcn i;

    @SafeParcelable.Constructor
    public zzbk(@SafeParcelable.Param(id = 2) String str, @Nullable @SafeParcelable.Param(id = 3) IBinder iBinder) {
        this.h = str;
        this.i = iBinder == null ? null : zzcm.zzj(iBinder);
    }

    public final String toString() {
        return String.format("UnclaimBleDeviceRequest{%s}", this.h);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.h, false);
        zzcn zzcnVar = this.i;
        SafeParcelWriter.writeIBinder(parcel, 3, zzcnVar == null ? null : zzcnVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public zzbk(String str, @Nullable zzcn zzcnVar) {
        this.h = str;
        this.i = zzcnVar;
    }
}

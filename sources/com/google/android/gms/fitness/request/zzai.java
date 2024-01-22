package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.fitness.zzem;
import com.google.android.gms.internal.fitness.zzep;
@SafeParcelable.Class(creator = "ListClaimedBleDevicesRequestCreator")
@SafeParcelable.Reserved({2, 1000})
@Deprecated
@ShowFirstParty
/* loaded from: classes6.dex */
public final class zzai extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzai> CREATOR = new zzah();
    @SafeParcelable.Field(getter = "getCallbackBinder", id = 1, type = "android.os.IBinder")
    public final zzem h;

    @SafeParcelable.Constructor
    public zzai(@SafeParcelable.Param(id = 1) IBinder iBinder) {
        this.h = zzep.zzk(iBinder);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 1, this.h.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public zzai(zzem zzemVar) {
        this.h = zzemVar;
    }
}

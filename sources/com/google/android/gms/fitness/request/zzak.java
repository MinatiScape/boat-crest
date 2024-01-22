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
import com.google.android.gms.internal.fitness.zzcc;
import com.google.android.gms.internal.fitness.zzcf;
@ShowFirstParty
@SafeParcelable.Class(creator = "ListSubscriptionsRequestCreator")
@SafeParcelable.Reserved({3, 1000})
/* loaded from: classes6.dex */
public final class zzak extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzak> CREATOR = new zzaj();
    @Nullable
    @SafeParcelable.Field(getter = "getDataType", id = 1)
    public final DataType h;
    @Nullable
    @SafeParcelable.Field(getter = "getCallbackBinder", id = 2, type = "android.os.IBinder")
    public final zzcc i;

    @SafeParcelable.Constructor
    public zzak(@Nullable @SafeParcelable.Param(id = 1) DataType dataType, @Nullable @SafeParcelable.Param(id = 2) IBinder iBinder) {
        this.h = dataType;
        this.i = iBinder == null ? null : zzcf.zzg(iBinder);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.h, i, false);
        zzcc zzccVar = this.i;
        SafeParcelWriter.writeIBinder(parcel, 2, zzccVar == null ? null : zzccVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public zzak(@Nullable DataType dataType, zzcc zzccVar) {
        this.h = dataType;
        this.i = zzccVar;
    }
}

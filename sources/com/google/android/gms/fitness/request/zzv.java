package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.fitness.zzcm;
import com.google.android.gms.internal.fitness.zzcn;
@SafeParcelable.Class(creator = "DataUpdateListenerUnregistrationRequestCreator")
@SafeParcelable.Reserved({1000})
/* loaded from: classes6.dex */
public final class zzv extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzv> CREATOR = new zzx();
    @SafeParcelable.Field(getter = "getIntent", id = 1)
    public final PendingIntent h;
    @Nullable
    @SafeParcelable.Field(getter = "getCallbackBinder", id = 2, type = "android.os.IBinder")
    public final zzcn i;

    @SafeParcelable.Constructor
    public zzv(@SafeParcelable.Param(id = 1) PendingIntent pendingIntent, @Nullable @SafeParcelable.Param(id = 2) IBinder iBinder) {
        this.h = pendingIntent;
        this.i = iBinder == null ? null : zzcm.zzj(iBinder);
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj != this) {
            return (obj instanceof zzv) && Objects.equal(this.h, ((zzv) obj).h);
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(this.h);
    }

    public final String toString() {
        return "DataUpdateListenerUnregistrationRequest";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.h, i, false);
        zzcn zzcnVar = this.i;
        SafeParcelWriter.writeIBinder(parcel, 2, zzcnVar == null ? null : zzcnVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

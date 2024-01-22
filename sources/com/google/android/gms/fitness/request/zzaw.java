package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.fitness.zzcm;
import com.google.android.gms.internal.fitness.zzcn;
@ShowFirstParty
@SafeParcelable.Class(creator = "SessionRegistrationRequestCreator")
@SafeParcelable.Reserved({3, 4, 1000})
/* loaded from: classes6.dex */
public final class zzaw extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzaw> CREATOR = new zzaz();
    @SafeParcelable.Field(getter = "getIntent", id = 1)
    public final PendingIntent h;
    @Nullable
    @SafeParcelable.Field(getter = "getCallbackBinder", id = 2, type = "android.os.IBinder")
    public final zzcn i;

    @SafeParcelable.Constructor
    public zzaw(@SafeParcelable.Param(id = 1) PendingIntent pendingIntent, @Nullable @SafeParcelable.Param(id = 2) IBinder iBinder) {
        this.h = pendingIntent;
        this.i = iBinder == null ? null : zzcm.zzj(iBinder);
    }

    public final boolean equals(@Nullable Object obj) {
        if (this != obj) {
            return (obj instanceof zzaw) && Objects.equal(this.h, ((zzaw) obj).h);
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(this.h);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add(BaseGmsClient.KEY_PENDING_INTENT, this.h).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.h, i, false);
        zzcn zzcnVar = this.i;
        SafeParcelWriter.writeIBinder(parcel, 2, zzcnVar == null ? null : zzcnVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public zzaw(PendingIntent pendingIntent, @Nullable zzcn zzcnVar) {
        this.h = pendingIntent;
        this.i = zzcnVar;
    }
}

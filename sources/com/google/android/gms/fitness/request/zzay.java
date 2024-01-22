package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.internal.fitness.zzcm;
import com.google.android.gms.internal.fitness.zzcn;
import java.util.concurrent.TimeUnit;
@ShowFirstParty
@SafeParcelable.Class(creator = "SessionStartRequestCreator")
@SafeParcelable.Reserved({3, 1000})
/* loaded from: classes6.dex */
public final class zzay extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzay> CREATOR = new zzbb();
    @SafeParcelable.Field(getter = "getSession", id = 1)
    public final Session h;
    @Nullable
    @SafeParcelable.Field(getter = "getCallbackBinder", id = 2, type = "android.os.IBinder")
    public final zzcn i;

    @SafeParcelable.Constructor
    public zzay(@SafeParcelable.Param(id = 1) Session session, @Nullable @SafeParcelable.Param(id = 2) IBinder iBinder) {
        this.h = session;
        this.i = iBinder == null ? null : zzcm.zzj(iBinder);
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj != this) {
            return (obj instanceof zzay) && Objects.equal(this.h, ((zzay) obj).h);
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(this.h);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("session", this.h).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.h, i, false);
        zzcn zzcnVar = this.i;
        SafeParcelWriter.writeIBinder(parcel, 2, zzcnVar == null ? null : zzcnVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public zzay(Session session, @Nullable zzcn zzcnVar) {
        Preconditions.checkArgument(session.getStartTime(TimeUnit.MILLISECONDS) <= System.currentTimeMillis(), "Cannot start a session in the future");
        Preconditions.checkArgument(session.isOngoing(), "Cannot start a session which has already ended");
        this.h = session;
        this.i = zzcnVar;
    }
}

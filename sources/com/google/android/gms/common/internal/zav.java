package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "ResolveAccountResponseCreator")
/* loaded from: classes6.dex */
public final class zav extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zav> CREATOR = new zaw();
    @SafeParcelable.VersionField(id = 1)
    public final int h;
    @Nullable
    @SafeParcelable.Field(id = 2)
    public final IBinder i;
    @SafeParcelable.Field(getter = "getConnectionResult", id = 3)
    public final ConnectionResult j;
    @SafeParcelable.Field(getter = "getSaveDefaultAccount", id = 4)
    public final boolean k;
    @SafeParcelable.Field(getter = "isFromCrossClientAuth", id = 5)
    public final boolean l;

    @SafeParcelable.Constructor
    public zav(@SafeParcelable.Param(id = 1) int i, @Nullable @SafeParcelable.Param(id = 2) IBinder iBinder, @SafeParcelable.Param(id = 3) ConnectionResult connectionResult, @SafeParcelable.Param(id = 4) boolean z, @SafeParcelable.Param(id = 5) boolean z2) {
        this.h = i;
        this.i = iBinder;
        this.j = connectionResult;
        this.k = z;
        this.l = z2;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof zav) {
            zav zavVar = (zav) obj;
            return this.j.equals(zavVar.j) && Objects.equal(zab(), zavVar.zab());
        }
        return false;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.h);
        SafeParcelWriter.writeIBinder(parcel, 2, this.i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.j, i, false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.k);
        SafeParcelWriter.writeBoolean(parcel, 5, this.l);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final ConnectionResult zaa() {
        return this.j;
    }

    @Nullable
    public final IAccountAccessor zab() {
        IBinder iBinder = this.i;
        if (iBinder == null) {
            return null;
        }
        return IAccountAccessor.Stub.asInterface(iBinder);
    }

    public final boolean zac() {
        return this.k;
    }

    public final boolean zad() {
        return this.l;
    }
}

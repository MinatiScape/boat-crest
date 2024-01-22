package com.google.android.gms.internal.auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@ShowFirstParty
@SafeParcelable.Class(creator = "ClearTokenRequestCreator")
/* loaded from: classes6.dex */
public final class zzbw extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbw> CREATOR = new zzbx();
    @SafeParcelable.VersionField(id = 1)
    public final int h;
    @SafeParcelable.Field(id = 2)
    public String i;

    public zzbw() {
        this.h = 1;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.h);
        SafeParcelWriter.writeString(parcel, 2, this.i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final zzbw zza(String str) {
        this.i = str;
        return this;
    }

    @SafeParcelable.Constructor
    public zzbw(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) String str) {
        this.h = i;
        this.i = str;
    }
}

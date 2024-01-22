package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import javax.annotation.Nullable;
@SafeParcelable.Class(creator = "GoogleCertificatesLookupResponseCreator")
/* loaded from: classes6.dex */
public final class zzq extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzq> CREATOR = new zzr();
    @SafeParcelable.Field(getter = "getResult", id = 1)
    public final boolean h;
    @Nullable
    @SafeParcelable.Field(getter = "getErrorMessage", id = 2)
    public final String i;
    @SafeParcelable.Field(getter = "getStatusValue", id = 3)
    public final int j;
    @SafeParcelable.Field(getter = "getFirstPartyStatusValue", id = 4)
    public final int k;

    @SafeParcelable.Constructor
    public zzq(@SafeParcelable.Param(id = 1) boolean z, @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) int i, @SafeParcelable.Param(id = 4) int i2) {
        this.h = z;
        this.i = str;
        this.j = p.a(i) - 1;
        this.k = zzd.a(i2) - 1;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, this.h);
        SafeParcelWriter.writeString(parcel, 2, this.i, false);
        SafeParcelWriter.writeInt(parcel, 3, this.j);
        SafeParcelWriter.writeInt(parcel, 4, this.k);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Nullable
    public final String zza() {
        return this.i;
    }

    public final boolean zzb() {
        return this.h;
    }

    public final int zzc() {
        return zzd.a(this.k);
    }

    public final int zzd() {
        return p.a(this.j);
    }
}

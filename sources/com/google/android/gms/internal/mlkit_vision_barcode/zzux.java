package com.google.android.gms.internal.mlkit_vision_barcode;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "AddressParcelCreator")
/* loaded from: classes9.dex */
public final class zzux extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzux> CREATOR = new zzuw();
    @SafeParcelable.Field(getter = "getType", id = 1)
    public final int h;
    @SafeParcelable.Field(getter = "getAddressLines", id = 2)
    public final String[] i;

    @SafeParcelable.Constructor
    public zzux(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) String[] strArr) {
        this.h = i;
        this.i = strArr;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.h);
        SafeParcelWriter.writeStringArray(parcel, 2, this.i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final int zza() {
        return this.h;
    }

    public final String[] zzb() {
        return this.i;
    }
}

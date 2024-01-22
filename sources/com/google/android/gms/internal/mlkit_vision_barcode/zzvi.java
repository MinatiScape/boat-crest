package com.google.android.gms.internal.mlkit_vision_barcode;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "WiFiParcelCreator")
/* loaded from: classes9.dex */
public final class zzvi extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzvi> CREATOR = new zzwb();
    @Nullable
    @SafeParcelable.Field(getter = "getSsid", id = 1)
    public final String h;
    @Nullable
    @SafeParcelable.Field(getter = "getPassword", id = 2)
    public final String i;
    @SafeParcelable.Field(getter = "getEncryptionType", id = 3)
    public final int j;

    @SafeParcelable.Constructor
    public zzvi(@Nullable @SafeParcelable.Param(id = 1) String str, @Nullable @SafeParcelable.Param(id = 2) String str2, @SafeParcelable.Param(id = 3) int i) {
        this.h = str;
        this.i = str2;
        this.j = i;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.h, false);
        SafeParcelWriter.writeString(parcel, 2, this.i, false);
        SafeParcelWriter.writeInt(parcel, 3, this.j);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final int zza() {
        return this.j;
    }

    @Nullable
    public final String zzb() {
        return this.i;
    }

    @Nullable
    public final String zzc() {
        return this.h;
    }
}

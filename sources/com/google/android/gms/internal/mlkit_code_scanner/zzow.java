package com.google.android.gms.internal.mlkit_code_scanner;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "SmsParcelCreator")
/* loaded from: classes8.dex */
public final class zzow extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzow> CREATOR = new zzpj();
    @Nullable
    @SafeParcelable.Field(getter = "getMessage", id = 1)
    public final String h;
    @Nullable
    @SafeParcelable.Field(getter = "getPhoneNumber", id = 2)
    public final String i;

    @SafeParcelable.Constructor
    public zzow(@Nullable @SafeParcelable.Param(id = 1) String str, @Nullable @SafeParcelable.Param(id = 2) String str2) {
        this.h = str;
        this.i = str2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.h, false);
        SafeParcelWriter.writeString(parcel, 2, this.i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Nullable
    public final String zza() {
        return this.h;
    }

    @Nullable
    public final String zzb() {
        return this.i;
    }
}

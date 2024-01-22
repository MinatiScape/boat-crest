package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "PersonNameParcelCreator")
/* loaded from: classes8.dex */
public final class zzav extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzav> CREATOR = new zzbp();
    @Nullable
    @SafeParcelable.Field(getter = "getFormattedName", id = 1)
    public final String h;
    @Nullable
    @SafeParcelable.Field(getter = "getPronunciation", id = 2)
    public final String i;
    @Nullable
    @SafeParcelable.Field(getter = "getPrefix", id = 3)
    public final String j;
    @Nullable
    @SafeParcelable.Field(getter = "getFirst", id = 4)
    public final String k;
    @Nullable
    @SafeParcelable.Field(getter = "getMiddle", id = 5)
    public final String l;
    @Nullable
    @SafeParcelable.Field(getter = "getLast", id = 6)
    public final String m;
    @Nullable
    @SafeParcelable.Field(getter = "getSuffix", id = 7)
    public final String n;

    @SafeParcelable.Constructor
    public zzav(@Nullable @SafeParcelable.Param(id = 1) String str, @Nullable @SafeParcelable.Param(id = 2) String str2, @Nullable @SafeParcelable.Param(id = 3) String str3, @Nullable @SafeParcelable.Param(id = 4) String str4, @Nullable @SafeParcelable.Param(id = 5) String str5, @Nullable @SafeParcelable.Param(id = 6) String str6, @Nullable @SafeParcelable.Param(id = 7) String str7) {
        this.h = str;
        this.i = str2;
        this.j = str3;
        this.k = str4;
        this.l = str5;
        this.m = str6;
        this.n = str7;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.h, false);
        SafeParcelWriter.writeString(parcel, 2, this.i, false);
        SafeParcelWriter.writeString(parcel, 3, this.j, false);
        SafeParcelWriter.writeString(parcel, 4, this.k, false);
        SafeParcelWriter.writeString(parcel, 5, this.l, false);
        SafeParcelWriter.writeString(parcel, 6, this.m, false);
        SafeParcelWriter.writeString(parcel, 7, this.n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

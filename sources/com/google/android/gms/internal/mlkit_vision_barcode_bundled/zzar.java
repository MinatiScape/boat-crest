package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "ContactInfoParcelCreator")
/* loaded from: classes8.dex */
public final class zzar extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzar> CREATOR = new zzbg();
    @Nullable
    @SafeParcelable.Field(getter = "getName", id = 1)
    public final zzav h;
    @Nullable
    @SafeParcelable.Field(getter = "getOrganization", id = 2)
    public final String i;
    @Nullable
    @SafeParcelable.Field(getter = "getTitle", id = 3)
    public final String j;
    @Nullable
    @SafeParcelable.Field(getter = "getPhones", id = 4)
    public final zzaw[] k;
    @Nullable
    @SafeParcelable.Field(getter = "getEmails", id = 5)
    public final zzat[] l;
    @Nullable
    @SafeParcelable.Field(getter = "getUrls", id = 6)
    public final String[] m;
    @Nullable
    @SafeParcelable.Field(getter = "getAddresses", id = 7)
    public final zzao[] n;

    @SafeParcelable.Constructor
    public zzar(@Nullable @SafeParcelable.Param(id = 1) zzav zzavVar, @Nullable @SafeParcelable.Param(id = 2) String str, @Nullable @SafeParcelable.Param(id = 3) String str2, @Nullable @SafeParcelable.Param(id = 4) zzaw[] zzawVarArr, @Nullable @SafeParcelable.Param(id = 5) zzat[] zzatVarArr, @Nullable @SafeParcelable.Param(id = 6) String[] strArr, @Nullable @SafeParcelable.Param(id = 7) zzao[] zzaoVarArr) {
        this.h = zzavVar;
        this.i = str;
        this.j = str2;
        this.k = zzawVarArr;
        this.l = zzatVarArr;
        this.m = strArr;
        this.n = zzaoVarArr;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.h, i, false);
        SafeParcelWriter.writeString(parcel, 2, this.i, false);
        SafeParcelWriter.writeString(parcel, 3, this.j, false);
        SafeParcelWriter.writeTypedArray(parcel, 4, this.k, i, false);
        SafeParcelWriter.writeTypedArray(parcel, 5, this.l, i, false);
        SafeParcelWriter.writeStringArray(parcel, 6, this.m, false);
        SafeParcelWriter.writeTypedArray(parcel, 7, this.n, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

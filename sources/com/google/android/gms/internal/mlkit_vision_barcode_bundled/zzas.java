package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "DriverLicenseParcelCreator")
/* loaded from: classes8.dex */
public final class zzas extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzas> CREATOR = new zzbh();
    @Nullable
    @SafeParcelable.Field(getter = "getDocumentType", id = 1)
    public final String h;
    @Nullable
    @SafeParcelable.Field(getter = "getFirstName", id = 2)
    public final String i;
    @Nullable
    @SafeParcelable.Field(getter = "getMiddleName", id = 3)
    public final String j;
    @Nullable
    @SafeParcelable.Field(getter = "getLastName", id = 4)
    public final String k;
    @Nullable
    @SafeParcelable.Field(getter = "getGender", id = 5)
    public final String l;
    @Nullable
    @SafeParcelable.Field(getter = "getAddressStreet", id = 6)
    public final String m;
    @Nullable
    @SafeParcelable.Field(getter = "getAddressCity", id = 7)
    public final String n;
    @Nullable
    @SafeParcelable.Field(getter = "getAddressState", id = 8)
    public final String o;
    @Nullable
    @SafeParcelable.Field(getter = "getAddressZip", id = 9)
    public final String p;
    @Nullable
    @SafeParcelable.Field(getter = "getLicenseNumber", id = 10)
    public final String q;
    @Nullable
    @SafeParcelable.Field(getter = "getIssueDate", id = 11)
    public final String r;
    @Nullable
    @SafeParcelable.Field(getter = "getExpiryDate", id = 12)
    public final String s;
    @Nullable
    @SafeParcelable.Field(getter = "getBirthDate", id = 13)
    public final String t;
    @Nullable
    @SafeParcelable.Field(getter = "getIssuingCountry", id = 14)
    public final String u;

    @SafeParcelable.Constructor
    public zzas(@Nullable @SafeParcelable.Param(id = 1) String str, @Nullable @SafeParcelable.Param(id = 2) String str2, @Nullable @SafeParcelable.Param(id = 3) String str3, @Nullable @SafeParcelable.Param(id = 4) String str4, @Nullable @SafeParcelable.Param(id = 5) String str5, @Nullable @SafeParcelable.Param(id = 6) String str6, @Nullable @SafeParcelable.Param(id = 7) String str7, @Nullable @SafeParcelable.Param(id = 8) String str8, @Nullable @SafeParcelable.Param(id = 9) String str9, @Nullable @SafeParcelable.Param(id = 10) String str10, @Nullable @SafeParcelable.Param(id = 11) String str11, @Nullable @SafeParcelable.Param(id = 12) String str12, @Nullable @SafeParcelable.Param(id = 13) String str13, @Nullable @SafeParcelable.Param(id = 14) String str14) {
        this.h = str;
        this.i = str2;
        this.j = str3;
        this.k = str4;
        this.l = str5;
        this.m = str6;
        this.n = str7;
        this.o = str8;
        this.p = str9;
        this.q = str10;
        this.r = str11;
        this.s = str12;
        this.t = str13;
        this.u = str14;
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
        SafeParcelWriter.writeString(parcel, 8, this.o, false);
        SafeParcelWriter.writeString(parcel, 9, this.p, false);
        SafeParcelWriter.writeString(parcel, 10, this.q, false);
        SafeParcelWriter.writeString(parcel, 11, this.r, false);
        SafeParcelWriter.writeString(parcel, 12, this.s, false);
        SafeParcelWriter.writeString(parcel, 13, this.t, false);
        SafeParcelWriter.writeString(parcel, 14, this.u, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

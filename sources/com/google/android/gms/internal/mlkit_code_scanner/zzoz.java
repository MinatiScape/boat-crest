package com.google.android.gms.internal.mlkit_code_scanner;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "BarcodeParcelCreator")
/* loaded from: classes8.dex */
public final class zzoz extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzoz> CREATOR = new zzpa();
    @SafeParcelable.Field(getter = "getFormat", id = 1)
    public final int h;
    @Nullable
    @SafeParcelable.Field(getter = "getDisplayValue", id = 2)
    public final String i;
    @Nullable
    @SafeParcelable.Field(getter = "getRawValue", id = 3)
    public final String j;
    @Nullable
    @SafeParcelable.Field(getter = "getRawBytes", id = 4)
    public final byte[] k;
    @Nullable
    @SafeParcelable.Field(getter = "getCornerPoints", id = 5)
    public final Point[] l;
    @SafeParcelable.Field(getter = "getValueType", id = 6)
    public final int m;
    @Nullable
    @SafeParcelable.Field(getter = "getEmailParcel", id = 7)
    public final zzos n;
    @Nullable
    @SafeParcelable.Field(getter = "getPhoneParcel", id = 8)
    public final zzov o;
    @Nullable
    @SafeParcelable.Field(getter = "getSmsParcel", id = 9)
    public final zzow p;
    @Nullable
    @SafeParcelable.Field(getter = "getWiFiParcel", id = 10)
    public final zzoy q;
    @Nullable
    @SafeParcelable.Field(getter = "getUrlBookmarkParcel", id = 11)
    public final zzox r;
    @Nullable
    @SafeParcelable.Field(getter = "getGeoPointParcel", id = 12)
    public final zzot s;
    @Nullable
    @SafeParcelable.Field(getter = "getCalendarEventParcel", id = 13)
    public final zzop t;
    @Nullable
    @SafeParcelable.Field(getter = "getContactInfoParcel", id = 14)
    public final zzoq u;
    @Nullable
    @SafeParcelable.Field(getter = "getDriverLicenseParcel", id = 15)
    public final zzor v;

    @SafeParcelable.Constructor
    public zzoz(@SafeParcelable.Param(id = 1) int i, @Nullable @SafeParcelable.Param(id = 2) String str, @Nullable @SafeParcelable.Param(id = 3) String str2, @Nullable @SafeParcelable.Param(id = 4) byte[] bArr, @Nullable @SafeParcelable.Param(id = 5) Point[] pointArr, @SafeParcelable.Param(id = 6) int i2, @Nullable @SafeParcelable.Param(id = 7) zzos zzosVar, @Nullable @SafeParcelable.Param(id = 8) zzov zzovVar, @Nullable @SafeParcelable.Param(id = 9) zzow zzowVar, @Nullable @SafeParcelable.Param(id = 10) zzoy zzoyVar, @Nullable @SafeParcelable.Param(id = 11) zzox zzoxVar, @Nullable @SafeParcelable.Param(id = 12) zzot zzotVar, @Nullable @SafeParcelable.Param(id = 13) zzop zzopVar, @Nullable @SafeParcelable.Param(id = 14) zzoq zzoqVar, @Nullable @SafeParcelable.Param(id = 15) zzor zzorVar) {
        this.h = i;
        this.i = str;
        this.j = str2;
        this.k = bArr;
        this.l = pointArr;
        this.m = i2;
        this.n = zzosVar;
        this.o = zzovVar;
        this.p = zzowVar;
        this.q = zzoyVar;
        this.r = zzoxVar;
        this.s = zzotVar;
        this.t = zzopVar;
        this.u = zzoqVar;
        this.v = zzorVar;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.h);
        SafeParcelWriter.writeString(parcel, 2, this.i, false);
        SafeParcelWriter.writeString(parcel, 3, this.j, false);
        SafeParcelWriter.writeByteArray(parcel, 4, this.k, false);
        SafeParcelWriter.writeTypedArray(parcel, 5, this.l, i, false);
        SafeParcelWriter.writeInt(parcel, 6, this.m);
        SafeParcelWriter.writeParcelable(parcel, 7, this.n, i, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.o, i, false);
        SafeParcelWriter.writeParcelable(parcel, 9, this.p, i, false);
        SafeParcelWriter.writeParcelable(parcel, 10, this.q, i, false);
        SafeParcelWriter.writeParcelable(parcel, 11, this.r, i, false);
        SafeParcelWriter.writeParcelable(parcel, 12, this.s, i, false);
        SafeParcelWriter.writeParcelable(parcel, 13, this.t, i, false);
        SafeParcelWriter.writeParcelable(parcel, 14, this.u, i, false);
        SafeParcelWriter.writeParcelable(parcel, 15, this.v, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final int zza() {
        return this.h;
    }

    public final int zzb() {
        return this.m;
    }

    @Nullable
    public final zzop zzc() {
        return this.t;
    }

    @Nullable
    public final zzoq zzd() {
        return this.u;
    }

    @Nullable
    public final zzor zze() {
        return this.v;
    }

    @Nullable
    public final zzos zzf() {
        return this.n;
    }

    @Nullable
    public final zzot zzg() {
        return this.s;
    }

    @Nullable
    public final zzov zzh() {
        return this.o;
    }

    @Nullable
    public final zzow zzi() {
        return this.p;
    }

    @Nullable
    public final zzox zzj() {
        return this.r;
    }

    @Nullable
    public final zzoy zzk() {
        return this.q;
    }

    @Nullable
    public final String zzl() {
        return this.i;
    }

    @Nullable
    public final String zzm() {
        return this.j;
    }

    @Nullable
    public final byte[] zzn() {
        return this.k;
    }
}

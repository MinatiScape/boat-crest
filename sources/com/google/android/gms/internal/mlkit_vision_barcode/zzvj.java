package com.google.android.gms.internal.mlkit_vision_barcode;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "BarcodeParcelCreator")
/* loaded from: classes9.dex */
public final class zzvj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzvj> CREATOR = new zzvk();
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
    public final zzvc n;
    @Nullable
    @SafeParcelable.Field(getter = "getPhoneParcel", id = 8)
    public final zzvf o;
    @Nullable
    @SafeParcelable.Field(getter = "getSmsParcel", id = 9)
    public final zzvg p;
    @Nullable
    @SafeParcelable.Field(getter = "getWiFiParcel", id = 10)
    public final zzvi q;
    @Nullable
    @SafeParcelable.Field(getter = "getUrlBookmarkParcel", id = 11)
    public final zzvh r;
    @Nullable
    @SafeParcelable.Field(getter = "getGeoPointParcel", id = 12)
    public final zzvd s;
    @Nullable
    @SafeParcelable.Field(getter = "getCalendarEventParcel", id = 13)
    public final zzuz t;
    @Nullable
    @SafeParcelable.Field(getter = "getContactInfoParcel", id = 14)
    public final zzva u;
    @Nullable
    @SafeParcelable.Field(getter = "getDriverLicenseParcel", id = 15)
    public final zzvb v;

    @SafeParcelable.Constructor
    public zzvj(@SafeParcelable.Param(id = 1) int i, @Nullable @SafeParcelable.Param(id = 2) String str, @Nullable @SafeParcelable.Param(id = 3) String str2, @Nullable @SafeParcelable.Param(id = 4) byte[] bArr, @Nullable @SafeParcelable.Param(id = 5) Point[] pointArr, @SafeParcelable.Param(id = 6) int i2, @Nullable @SafeParcelable.Param(id = 7) zzvc zzvcVar, @Nullable @SafeParcelable.Param(id = 8) zzvf zzvfVar, @Nullable @SafeParcelable.Param(id = 9) zzvg zzvgVar, @Nullable @SafeParcelable.Param(id = 10) zzvi zzviVar, @Nullable @SafeParcelable.Param(id = 11) zzvh zzvhVar, @Nullable @SafeParcelable.Param(id = 12) zzvd zzvdVar, @Nullable @SafeParcelable.Param(id = 13) zzuz zzuzVar, @Nullable @SafeParcelable.Param(id = 14) zzva zzvaVar, @Nullable @SafeParcelable.Param(id = 15) zzvb zzvbVar) {
        this.h = i;
        this.i = str;
        this.j = str2;
        this.k = bArr;
        this.l = pointArr;
        this.m = i2;
        this.n = zzvcVar;
        this.o = zzvfVar;
        this.p = zzvgVar;
        this.q = zzviVar;
        this.r = zzvhVar;
        this.s = zzvdVar;
        this.t = zzuzVar;
        this.u = zzvaVar;
        this.v = zzvbVar;
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
    public final zzuz zzc() {
        return this.t;
    }

    @Nullable
    public final zzva zzd() {
        return this.u;
    }

    @Nullable
    public final zzvb zze() {
        return this.v;
    }

    @Nullable
    public final zzvc zzf() {
        return this.n;
    }

    @Nullable
    public final zzvd zzg() {
        return this.s;
    }

    @Nullable
    public final zzvf zzh() {
        return this.o;
    }

    @Nullable
    public final zzvg zzi() {
        return this.p;
    }

    @Nullable
    public final zzvh zzj() {
        return this.r;
    }

    @Nullable
    public final zzvi zzk() {
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

    @Nullable
    public final Point[] zzo() {
        return this.l;
    }
}

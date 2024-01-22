package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "BarcodeParcelCreator")
/* loaded from: classes8.dex */
public final class zzba extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzba> CREATOR = new zzbb();
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
    public final zzat n;
    @Nullable
    @SafeParcelable.Field(getter = "getPhoneParcel", id = 8)
    public final zzaw o;
    @Nullable
    @SafeParcelable.Field(getter = "getSmsParcel", id = 9)
    public final zzax p;
    @Nullable
    @SafeParcelable.Field(getter = "getWiFiParcel", id = 10)
    public final zzaz q;
    @Nullable
    @SafeParcelable.Field(getter = "getUrlBookmarkParcel", id = 11)
    public final zzay r;
    @Nullable
    @SafeParcelable.Field(getter = "getGeoPointParcel", id = 12)
    public final zzau s;
    @Nullable
    @SafeParcelable.Field(getter = "getCalendarEventParcel", id = 13)
    public final zzaq t;
    @Nullable
    @SafeParcelable.Field(getter = "getContactInfoParcel", id = 14)
    public final zzar u;
    @Nullable
    @SafeParcelable.Field(getter = "getDriverLicenseParcel", id = 15)
    public final zzas v;

    @SafeParcelable.Constructor
    public zzba(@SafeParcelable.Param(id = 1) int i, @Nullable @SafeParcelable.Param(id = 2) String str, @Nullable @SafeParcelable.Param(id = 3) String str2, @Nullable @SafeParcelable.Param(id = 4) byte[] bArr, @Nullable @SafeParcelable.Param(id = 5) Point[] pointArr, @SafeParcelable.Param(id = 6) int i2, @Nullable @SafeParcelable.Param(id = 7) zzat zzatVar, @Nullable @SafeParcelable.Param(id = 8) zzaw zzawVar, @Nullable @SafeParcelable.Param(id = 9) zzax zzaxVar, @Nullable @SafeParcelable.Param(id = 10) zzaz zzazVar, @Nullable @SafeParcelable.Param(id = 11) zzay zzayVar, @Nullable @SafeParcelable.Param(id = 12) zzau zzauVar, @Nullable @SafeParcelable.Param(id = 13) zzaq zzaqVar, @Nullable @SafeParcelable.Param(id = 14) zzar zzarVar, @Nullable @SafeParcelable.Param(id = 15) zzas zzasVar) {
        this.h = i;
        this.i = str;
        this.j = str2;
        this.k = bArr;
        this.l = pointArr;
        this.m = i2;
        this.n = zzatVar;
        this.o = zzawVar;
        this.p = zzaxVar;
        this.q = zzazVar;
        this.r = zzayVar;
        this.s = zzauVar;
        this.t = zzaqVar;
        this.u = zzarVar;
        this.v = zzasVar;
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
}

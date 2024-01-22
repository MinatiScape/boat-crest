package com.google.android.gms.internal.mlkit_vision_barcode;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "CalendarDateTimeParcelCreator")
/* loaded from: classes9.dex */
public final class zzuy extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzuy> CREATOR = new zzvn();
    @SafeParcelable.Field(getter = "getYear", id = 1)
    public final int h;
    @SafeParcelable.Field(getter = "getMonth", id = 2)
    public final int i;
    @SafeParcelable.Field(getter = "getDay", id = 3)
    public final int j;
    @SafeParcelable.Field(getter = "getHours", id = 4)
    public final int k;
    @SafeParcelable.Field(getter = "getMinutes", id = 5)
    public final int l;
    @SafeParcelable.Field(getter = "getSeconds", id = 6)
    public final int m;
    @SafeParcelable.Field(getter = "isUtc", id = 7)
    public final boolean n;
    @Nullable
    @SafeParcelable.Field(getter = "getRawValue", id = 8)
    public final String o;

    @SafeParcelable.Constructor
    public zzuy(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) int i2, @SafeParcelable.Param(id = 3) int i3, @SafeParcelable.Param(id = 4) int i4, @SafeParcelable.Param(id = 5) int i5, @SafeParcelable.Param(id = 6) int i6, @SafeParcelable.Param(id = 7) boolean z, @Nullable @SafeParcelable.Param(id = 8) String str) {
        this.h = i;
        this.i = i2;
        this.j = i3;
        this.k = i4;
        this.l = i5;
        this.m = i6;
        this.n = z;
        this.o = str;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.h);
        SafeParcelWriter.writeInt(parcel, 2, this.i);
        SafeParcelWriter.writeInt(parcel, 3, this.j);
        SafeParcelWriter.writeInt(parcel, 4, this.k);
        SafeParcelWriter.writeInt(parcel, 5, this.l);
        SafeParcelWriter.writeInt(parcel, 6, this.m);
        SafeParcelWriter.writeBoolean(parcel, 7, this.n);
        SafeParcelWriter.writeString(parcel, 8, this.o, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final int zza() {
        return this.j;
    }

    public final int zzb() {
        return this.k;
    }

    public final int zzc() {
        return this.l;
    }

    public final int zzd() {
        return this.i;
    }

    public final int zze() {
        return this.m;
    }

    public final int zzf() {
        return this.h;
    }

    @Nullable
    public final String zzg() {
        return this.o;
    }

    public final boolean zzh() {
        return this.n;
    }
}

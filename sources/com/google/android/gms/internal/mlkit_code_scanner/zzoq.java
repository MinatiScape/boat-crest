package com.google.android.gms.internal.mlkit_code_scanner;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "ContactInfoParcelCreator")
/* loaded from: classes8.dex */
public final class zzoq extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzoq> CREATOR = new zzpd();
    @Nullable
    @SafeParcelable.Field(getter = "getName", id = 1)
    public final zzou h;
    @Nullable
    @SafeParcelable.Field(getter = "getOrganization", id = 2)
    public final String i;
    @Nullable
    @SafeParcelable.Field(getter = "getTitle", id = 3)
    public final String j;
    @Nullable
    @SafeParcelable.Field(getter = "getPhones", id = 4)
    public final zzov[] k;
    @Nullable
    @SafeParcelable.Field(getter = "getEmails", id = 5)
    public final zzos[] l;
    @Nullable
    @SafeParcelable.Field(getter = "getUrls", id = 6)
    public final String[] m;
    @Nullable
    @SafeParcelable.Field(getter = "getAddresses", id = 7)
    public final zzon[] n;

    @SafeParcelable.Constructor
    public zzoq(@Nullable @SafeParcelable.Param(id = 1) zzou zzouVar, @Nullable @SafeParcelable.Param(id = 2) String str, @Nullable @SafeParcelable.Param(id = 3) String str2, @Nullable @SafeParcelable.Param(id = 4) zzov[] zzovVarArr, @Nullable @SafeParcelable.Param(id = 5) zzos[] zzosVarArr, @Nullable @SafeParcelable.Param(id = 6) String[] strArr, @Nullable @SafeParcelable.Param(id = 7) zzon[] zzonVarArr) {
        this.h = zzouVar;
        this.i = str;
        this.j = str2;
        this.k = zzovVarArr;
        this.l = zzosVarArr;
        this.m = strArr;
        this.n = zzonVarArr;
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

    @Nullable
    public final zzou zza() {
        return this.h;
    }

    @Nullable
    public final String zzb() {
        return this.i;
    }

    @Nullable
    public final String zzc() {
        return this.j;
    }

    @Nullable
    public final zzon[] zzd() {
        return this.n;
    }

    @Nullable
    public final zzos[] zze() {
        return this.l;
    }

    @Nullable
    public final zzov[] zzf() {
        return this.k;
    }

    @Nullable
    public final String[] zzg() {
        return this.m;
    }
}

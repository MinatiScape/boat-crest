package com.google.android.gms.internal.mlkit_vision_barcode;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "ContactInfoParcelCreator")
/* loaded from: classes9.dex */
public final class zzva extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzva> CREATOR = new zzvp();
    @Nullable
    @SafeParcelable.Field(getter = "getName", id = 1)
    public final zzve h;
    @Nullable
    @SafeParcelable.Field(getter = "getOrganization", id = 2)
    public final String i;
    @Nullable
    @SafeParcelable.Field(getter = "getTitle", id = 3)
    public final String j;
    @Nullable
    @SafeParcelable.Field(getter = "getPhones", id = 4)
    public final zzvf[] k;
    @Nullable
    @SafeParcelable.Field(getter = "getEmails", id = 5)
    public final zzvc[] l;
    @Nullable
    @SafeParcelable.Field(getter = "getUrls", id = 6)
    public final String[] m;
    @Nullable
    @SafeParcelable.Field(getter = "getAddresses", id = 7)
    public final zzux[] n;

    @SafeParcelable.Constructor
    public zzva(@Nullable @SafeParcelable.Param(id = 1) zzve zzveVar, @Nullable @SafeParcelable.Param(id = 2) String str, @Nullable @SafeParcelable.Param(id = 3) String str2, @Nullable @SafeParcelable.Param(id = 4) zzvf[] zzvfVarArr, @Nullable @SafeParcelable.Param(id = 5) zzvc[] zzvcVarArr, @Nullable @SafeParcelable.Param(id = 6) String[] strArr, @Nullable @SafeParcelable.Param(id = 7) zzux[] zzuxVarArr) {
        this.h = zzveVar;
        this.i = str;
        this.j = str2;
        this.k = zzvfVarArr;
        this.l = zzvcVarArr;
        this.m = strArr;
        this.n = zzuxVarArr;
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
    public final zzve zza() {
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
    public final zzux[] zzd() {
        return this.n;
    }

    @Nullable
    public final zzvc[] zze() {
        return this.l;
    }

    @Nullable
    public final zzvf[] zzf() {
        return this.k;
    }

    @Nullable
    public final String[] zzg() {
        return this.m;
    }
}

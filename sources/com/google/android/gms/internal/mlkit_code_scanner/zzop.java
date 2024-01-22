package com.google.android.gms.internal.mlkit_code_scanner;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "CalendarEventParcelCreator")
/* loaded from: classes8.dex */
public final class zzop extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzop> CREATOR = new zzpc();
    @Nullable
    @SafeParcelable.Field(getter = "getSummary", id = 1)
    public final String h;
    @Nullable
    @SafeParcelable.Field(getter = "getDescription", id = 2)
    public final String i;
    @Nullable
    @SafeParcelable.Field(getter = "getLocation", id = 3)
    public final String j;
    @Nullable
    @SafeParcelable.Field(getter = "getOrganizer", id = 4)
    public final String k;
    @Nullable
    @SafeParcelable.Field(getter = "getStatus", id = 5)
    public final String l;
    @Nullable
    @SafeParcelable.Field(getter = "getStart", id = 6)
    public final zzoo m;
    @Nullable
    @SafeParcelable.Field(getter = "getEnd", id = 7)
    public final zzoo n;

    @SafeParcelable.Constructor
    public zzop(@Nullable @SafeParcelable.Param(id = 1) String str, @Nullable @SafeParcelable.Param(id = 2) String str2, @Nullable @SafeParcelable.Param(id = 3) String str3, @Nullable @SafeParcelable.Param(id = 4) String str4, @Nullable @SafeParcelable.Param(id = 5) String str5, @Nullable @SafeParcelable.Param(id = 6) zzoo zzooVar, @Nullable @SafeParcelable.Param(id = 7) zzoo zzooVar2) {
        this.h = str;
        this.i = str2;
        this.j = str3;
        this.k = str4;
        this.l = str5;
        this.m = zzooVar;
        this.n = zzooVar2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.h, false);
        SafeParcelWriter.writeString(parcel, 2, this.i, false);
        SafeParcelWriter.writeString(parcel, 3, this.j, false);
        SafeParcelWriter.writeString(parcel, 4, this.k, false);
        SafeParcelWriter.writeString(parcel, 5, this.l, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.m, i, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.n, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Nullable
    public final zzoo zza() {
        return this.n;
    }

    @Nullable
    public final zzoo zzb() {
        return this.m;
    }

    @Nullable
    public final String zzc() {
        return this.i;
    }

    @Nullable
    public final String zzd() {
        return this.j;
    }

    @Nullable
    public final String zze() {
        return this.k;
    }

    @Nullable
    public final String zzf() {
        return this.l;
    }

    @Nullable
    public final String zzg() {
        return this.h;
    }
}

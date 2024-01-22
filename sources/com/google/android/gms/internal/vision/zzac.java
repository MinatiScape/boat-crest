package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "LineBoxParcelCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes10.dex */
public final class zzac extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzac> CREATOR = new zzab();
    @SafeParcelable.Field(id = 4)
    public final zzw h;
    @SafeParcelable.Field(id = 5)
    public final zzw i;
    @SafeParcelable.Field(id = 7)
    public final float j;
    @SafeParcelable.Field(id = 9)
    public final int k;
    @SafeParcelable.Field(id = 8)
    public final String zzed;
    @SafeParcelable.Field(id = 2)
    public final zzaj[] zzei;
    @SafeParcelable.Field(id = 3)
    public final zzw zzej;
    @SafeParcelable.Field(id = 6)
    public final String zzem;
    @SafeParcelable.Field(id = 10)
    public final boolean zzeo;
    @SafeParcelable.Field(id = 11)
    public final int zzep;
    @SafeParcelable.Field(id = 12)
    public final int zzeq;

    @SafeParcelable.Constructor
    public zzac(@SafeParcelable.Param(id = 2) zzaj[] zzajVarArr, @SafeParcelable.Param(id = 3) zzw zzwVar, @SafeParcelable.Param(id = 4) zzw zzwVar2, @SafeParcelable.Param(id = 5) zzw zzwVar3, @SafeParcelable.Param(id = 6) String str, @SafeParcelable.Param(id = 7) float f, @SafeParcelable.Param(id = 8) String str2, @SafeParcelable.Param(id = 9) int i, @SafeParcelable.Param(id = 10) boolean z, @SafeParcelable.Param(id = 11) int i2, @SafeParcelable.Param(id = 12) int i3) {
        this.zzei = zzajVarArr;
        this.zzej = zzwVar;
        this.h = zzwVar2;
        this.i = zzwVar3;
        this.zzem = str;
        this.j = f;
        this.zzed = str2;
        this.k = i;
        this.zzeo = z;
        this.zzep = i2;
        this.zzeq = i3;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedArray(parcel, 2, this.zzei, i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzej, i, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.h, i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.i, i, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzem, false);
        SafeParcelWriter.writeFloat(parcel, 7, this.j);
        SafeParcelWriter.writeString(parcel, 8, this.zzed, false);
        SafeParcelWriter.writeInt(parcel, 9, this.k);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzeo);
        SafeParcelWriter.writeInt(parcel, 11, this.zzep);
        SafeParcelWriter.writeInt(parcel, 12, this.zzeq);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

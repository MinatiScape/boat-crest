package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "WordBoxParcelCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes10.dex */
public final class zzaj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzaj> CREATOR = new zzam();
    @SafeParcelable.Field(id = 2)
    public final zzag[] h;
    @SafeParcelable.Field(id = 4)
    public final zzw i;
    @SafeParcelable.Field(id = 6)
    public final float j;
    @SafeParcelable.Field(id = 8)
    public final boolean k;
    @SafeParcelable.Field(id = 7)
    public final String zzed;
    @SafeParcelable.Field(id = 3)
    public final zzw zzej;
    @SafeParcelable.Field(id = 5)
    public final String zzem;

    @SafeParcelable.Constructor
    public zzaj(@SafeParcelable.Param(id = 2) zzag[] zzagVarArr, @SafeParcelable.Param(id = 3) zzw zzwVar, @SafeParcelable.Param(id = 4) zzw zzwVar2, @SafeParcelable.Param(id = 5) String str, @SafeParcelable.Param(id = 6) float f, @SafeParcelable.Param(id = 7) String str2, @SafeParcelable.Param(id = 8) boolean z) {
        this.h = zzagVarArr;
        this.zzej = zzwVar;
        this.i = zzwVar2;
        this.zzem = str;
        this.j = f;
        this.zzed = str2;
        this.k = z;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedArray(parcel, 2, this.h, i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzej, i, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.i, i, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzem, false);
        SafeParcelWriter.writeFloat(parcel, 6, this.j);
        SafeParcelWriter.writeString(parcel, 7, this.zzed, false);
        SafeParcelWriter.writeBoolean(parcel, 8, this.k);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

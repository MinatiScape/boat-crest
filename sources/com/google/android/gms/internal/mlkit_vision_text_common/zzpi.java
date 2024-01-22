package com.google.android.gms.internal.mlkit_vision_text_common;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;
@SafeParcelable.Class(creator = "TextSymbolParcelCreator")
/* loaded from: classes6.dex */
public final class zzpi extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzpi> CREATOR = new zzpj();
    @SafeParcelable.Field(getter = "getText", id = 1)
    public final String h;
    @SafeParcelable.Field(getter = "getBoundingBox", id = 2)
    public final Rect i;
    @SafeParcelable.Field(getter = "getCornerPointList", id = 3)
    public final List j;
    @SafeParcelable.Field(getter = "getConfidence", id = 4)
    public final float k;
    @SafeParcelable.Field(getter = "getAngle", id = 5)
    public final float l;

    @SafeParcelable.Constructor
    public zzpi(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) Rect rect, @SafeParcelable.Param(id = 3) List list, @SafeParcelable.Param(id = 4) float f, @SafeParcelable.Param(id = 5) float f2) {
        this.h = str;
        this.i = rect;
        this.j = list;
        this.k = f;
        this.l = f2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.h, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.i, i, false);
        SafeParcelWriter.writeTypedList(parcel, 3, this.j, false);
        SafeParcelWriter.writeFloat(parcel, 4, this.k);
        SafeParcelWriter.writeFloat(parcel, 5, this.l);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final float zza() {
        return this.l;
    }

    public final float zzb() {
        return this.k;
    }

    public final Rect zzc() {
        return this.i;
    }

    public final String zzd() {
        return this.h;
    }

    public final List zze() {
        return this.j;
    }
}

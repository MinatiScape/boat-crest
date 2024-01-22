package com.google.android.gms.internal.mlkit_vision_text_common;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;
@SafeParcelable.Class(creator = "TextElementParcelCreator")
/* loaded from: classes6.dex */
public final class zzpc extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzpc> CREATOR = new zzpd();
    @SafeParcelable.Field(getter = "getText", id = 1)
    public final String h;
    @SafeParcelable.Field(getter = "getBoundingBox", id = 2)
    public final Rect i;
    @SafeParcelable.Field(getter = "getCornerPointList", id = 3)
    public final List j;
    @SafeParcelable.Field(getter = "getRecognizedLanguage", id = 4)
    public final String k;
    @SafeParcelable.Field(getter = "getConfidence", id = 5)
    public final float l;
    @SafeParcelable.Field(getter = "getAngle", id = 6)
    public final float m;
    @SafeParcelable.Field(getter = "getTextSymbolList", id = 7)
    public final List n;

    @SafeParcelable.Constructor
    public zzpc(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) Rect rect, @SafeParcelable.Param(id = 3) List list, @SafeParcelable.Param(id = 4) String str2, @SafeParcelable.Param(id = 5) float f, @SafeParcelable.Param(id = 6) float f2, @SafeParcelable.Param(id = 7) List list2) {
        this.h = str;
        this.i = rect;
        this.j = list;
        this.k = str2;
        this.l = f;
        this.m = f2;
        this.n = list2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.h, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.i, i, false);
        SafeParcelWriter.writeTypedList(parcel, 3, this.j, false);
        SafeParcelWriter.writeString(parcel, 4, this.k, false);
        SafeParcelWriter.writeFloat(parcel, 5, this.l);
        SafeParcelWriter.writeFloat(parcel, 6, this.m);
        SafeParcelWriter.writeTypedList(parcel, 7, this.n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final float zza() {
        return this.m;
    }

    public final float zzb() {
        return this.l;
    }

    public final Rect zzc() {
        return this.i;
    }

    public final String zzd() {
        return this.k;
    }

    public final String zze() {
        return this.h;
    }

    public final List zzf() {
        return this.j;
    }

    public final List zzg() {
        return this.n;
    }
}

package com.google.android.gms.internal.mlkit_vision_text_common;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;
@SafeParcelable.Class(creator = "TextBlockParcelCreator")
/* loaded from: classes6.dex */
public final class zzpa extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzpa> CREATOR = new zzpb();
    @SafeParcelable.Field(getter = "getText", id = 1)
    public final String h;
    @SafeParcelable.Field(getter = "getBoundingBox", id = 2)
    public final Rect i;
    @SafeParcelable.Field(getter = "getCornerPointList", id = 3)
    public final List j;
    @SafeParcelable.Field(getter = "getRecognizedLanguage", id = 4)
    public final String k;
    @SafeParcelable.Field(getter = "getTextLineList", id = 5)
    public final List l;

    @SafeParcelable.Constructor
    public zzpa(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) Rect rect, @SafeParcelable.Param(id = 3) List list, @SafeParcelable.Param(id = 4) String str2, @SafeParcelable.Param(id = 5) List list2) {
        this.h = str;
        this.i = rect;
        this.j = list;
        this.k = str2;
        this.l = list2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.h, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.i, i, false);
        SafeParcelWriter.writeTypedList(parcel, 3, this.j, false);
        SafeParcelWriter.writeString(parcel, 4, this.k, false);
        SafeParcelWriter.writeTypedList(parcel, 5, this.l, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final Rect zza() {
        return this.i;
    }

    public final String zzb() {
        return this.k;
    }

    public final String zzc() {
        return this.h;
    }

    public final List zzd() {
        return this.j;
    }

    public final List zze() {
        return this.l;
    }
}

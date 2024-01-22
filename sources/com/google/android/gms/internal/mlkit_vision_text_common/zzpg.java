package com.google.android.gms.internal.mlkit_vision_text_common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;
@SafeParcelable.Class(creator = "TextParcelCreator")
/* loaded from: classes6.dex */
public final class zzpg extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzpg> CREATOR = new zzph();
    @SafeParcelable.Field(getter = "getText", id = 1)
    public final String h;
    @SafeParcelable.Field(getter = "getTextBlockList", id = 2)
    public final List i;

    @SafeParcelable.Constructor
    public zzpg(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) List list) {
        this.h = str;
        this.i = list;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.h, false);
        SafeParcelWriter.writeTypedList(parcel, 2, this.i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String zza() {
        return this.h;
    }

    public final List zzb() {
        return this.i;
    }
}

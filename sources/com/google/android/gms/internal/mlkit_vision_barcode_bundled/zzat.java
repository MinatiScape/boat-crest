package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "EmailParcelCreator")
/* loaded from: classes8.dex */
public final class zzat extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzat> CREATOR = new zzbi();
    @SafeParcelable.Field(getter = "getType", id = 1)
    public final int h;
    @Nullable
    @SafeParcelable.Field(getter = "getAddress", id = 2)
    public final String i;
    @Nullable
    @SafeParcelable.Field(getter = "getSubject", id = 3)
    public final String j;
    @Nullable
    @SafeParcelable.Field(getter = "getBody", id = 4)
    public final String k;

    @SafeParcelable.Constructor
    public zzat(@SafeParcelable.Param(id = 1) int i, @Nullable @SafeParcelable.Param(id = 2) String str, @Nullable @SafeParcelable.Param(id = 3) String str2, @Nullable @SafeParcelable.Param(id = 4) String str3) {
        this.h = i;
        this.i = str;
        this.j = str2;
        this.k = str3;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.h);
        SafeParcelWriter.writeString(parcel, 2, this.i, false);
        SafeParcelWriter.writeString(parcel, 3, this.j, false);
        SafeParcelWriter.writeString(parcel, 4, this.k, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

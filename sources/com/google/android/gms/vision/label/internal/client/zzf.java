package com.google.android.gms.vision.label.internal.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "ImageLabelParcelCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes10.dex */
public final class zzf extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzf> CREATOR = new zze();
    @SafeParcelable.Field(id = 2)
    public final String label;
    @SafeParcelable.Field(id = 4)
    public final String zzdo;
    @SafeParcelable.Field(id = 3)
    public final float zzdp;

    @SafeParcelable.Constructor
    public zzf(@SafeParcelable.Param(id = 4) String str, @SafeParcelable.Param(id = 2) String str2, @SafeParcelable.Param(id = 3) float f) {
        this.label = str2;
        this.zzdp = f;
        this.zzdo = str;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.label, false);
        SafeParcelWriter.writeFloat(parcel, 3, this.zzdp);
        SafeParcelWriter.writeString(parcel, 4, this.zzdo, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

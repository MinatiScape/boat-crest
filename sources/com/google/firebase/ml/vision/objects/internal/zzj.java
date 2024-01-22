package com.google.firebase.ml.vision.objects.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "ObjectParcelCreator")
/* loaded from: classes10.dex */
public final class zzj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzj> CREATOR = new zzi();
    @SafeParcelable.Field(id = 5)
    public final int category;
    @SafeParcelable.Field(id = 3)
    public final Float confidence;
    @SafeParcelable.Field(id = 4)
    public final String h;
    @SafeParcelable.Field(id = 2)
    public final Integer zzbud;
    @SafeParcelable.Field(id = 1)
    public final int[] zzbur;

    @SafeParcelable.Constructor
    public zzj(@SafeParcelable.Param(id = 1) int[] iArr, @SafeParcelable.Param(id = 2) Integer num, @SafeParcelable.Param(id = 3) Float f, @SafeParcelable.Param(id = 4) String str, @SafeParcelable.Param(id = 5) int i) {
        this.zzbur = iArr;
        this.zzbud = num;
        this.confidence = f;
        this.h = str;
        this.category = i;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIntArray(parcel, 1, this.zzbur, false);
        SafeParcelWriter.writeIntegerObject(parcel, 2, this.zzbud, false);
        SafeParcelWriter.writeFloatObject(parcel, 3, this.confidence, false);
        SafeParcelWriter.writeString(parcel, 4, this.h, false);
        SafeParcelWriter.writeInt(parcel, 5, this.category);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

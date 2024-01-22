package com.google.firebase.ml.vision.objects.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "ObjectDetectorOptionsParcelCreator")
/* loaded from: classes10.dex */
public class ObjectDetectorOptionsParcel extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ObjectDetectorOptionsParcel> CREATOR = new zzf();
    @SafeParcelable.Field(id = 1)
    public final int zzbue;
    @SafeParcelable.Field(id = 2)
    public final boolean zzbuf;
    @SafeParcelable.Field(id = 3)
    public final boolean zzbug;

    @SafeParcelable.Constructor
    public ObjectDetectorOptionsParcel(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) boolean z, @SafeParcelable.Param(id = 3) boolean z2) {
        this.zzbue = i;
        this.zzbuf = z;
        this.zzbug = z2;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzbue);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzbuf);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzbug);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

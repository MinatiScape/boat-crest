package com.google.firebase.ml.vision.automl.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "OnDeviceAutoMLImageLabelerOptionsParcelCreator")
/* loaded from: classes10.dex */
public class OnDeviceAutoMLImageLabelerOptionsParcel extends AbstractSafeParcelable {
    public static final Parcelable.Creator<OnDeviceAutoMLImageLabelerOptionsParcel> CREATOR = new zzm();
    @SafeParcelable.Field(id = 1)
    public final float confidenceThreshold;
    @SafeParcelable.Field(id = 2)
    public final String zzbpw;
    @SafeParcelable.Field(id = 3)
    public final String zzbpx;
    @SafeParcelable.Field(id = 4)
    public final String zzbpy;

    @SafeParcelable.Constructor
    public OnDeviceAutoMLImageLabelerOptionsParcel(@SafeParcelable.Param(id = 1) float f, @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) String str3) {
        this.confidenceThreshold = f;
        this.zzbpw = str;
        this.zzbpx = str2;
        this.zzbpy = str3;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeFloat(parcel, 1, this.confidenceThreshold);
        SafeParcelWriter.writeString(parcel, 2, this.zzbpw, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzbpx, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzbpy, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

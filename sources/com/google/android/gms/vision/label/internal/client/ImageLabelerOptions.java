package com.google.android.gms.vision.label.internal.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Locale;
@SafeParcelable.Class(creator = "ImageLabelerOptionsCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes10.dex */
public class ImageLabelerOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ImageLabelerOptions> CREATOR = new zzg();
    @SafeParcelable.Field(id = 2)
    public int h;
    @SafeParcelable.Field(id = 3)
    public int zzdu;
    @SafeParcelable.Field(id = 4)
    public float zzdv;
    @SafeParcelable.Field(id = 5)
    public int zzdw;

    @SafeParcelable.Constructor
    public ImageLabelerOptions(@SafeParcelable.Param(id = 2) int i, @SafeParcelable.Param(id = 3) int i2, @SafeParcelable.Param(id = 4) float f, @SafeParcelable.Param(id = 5) int i3) {
        if (i == 1) {
            this.h = i;
            this.zzdu = i2;
            this.zzdv = f;
            this.zzdw = i3;
            return;
        }
        throw new IllegalArgumentException("Unknown language.");
    }

    public static int zza(String str) {
        str.equals(Locale.ENGLISH.getLanguage());
        return 1;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.h);
        SafeParcelWriter.writeInt(parcel, 3, this.zzdu);
        SafeParcelWriter.writeFloat(parcel, 4, this.zzdv);
        SafeParcelWriter.writeInt(parcel, 5, this.zzdw);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

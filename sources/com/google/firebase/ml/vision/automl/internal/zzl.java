package com.google.firebase.ml.vision.automl.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "ImageLabelParcelCreator")
/* loaded from: classes10.dex */
public final class zzl extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzl> CREATOR = new zzk();
    @SafeParcelable.Field(id = 2)
    public final String text;
    @SafeParcelable.Field(id = 1)
    public final String zzbpu;
    @SafeParcelable.Field(id = 3)
    public final float zzbpv;

    @SafeParcelable.Constructor
    public zzl(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) String str2, @SafeParcelable.Param(id = 3) float f) {
        this.zzbpu = str;
        this.text = str2;
        this.zzbpv = f;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzl) {
            zzl zzlVar = (zzl) obj;
            return Objects.equal(this.zzbpu, zzlVar.zzbpu) && Objects.equal(this.text, zzlVar.text) && Float.compare(this.zzbpv, zzlVar.zzbpv) == 0;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzbpu, this.text, Float.valueOf(this.zzbpv));
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzbpu, false);
        SafeParcelWriter.writeString(parcel, 2, this.text, false);
        SafeParcelWriter.writeFloat(parcel, 3, this.zzbpv);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

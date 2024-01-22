package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "StreetViewSourceCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes10.dex */
public final class StreetViewSource extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<StreetViewSource> CREATOR = new zzr();
    @NonNull
    public static final StreetViewSource DEFAULT = new StreetViewSource(0);
    @NonNull
    public static final StreetViewSource OUTDOOR = new StreetViewSource(1);
    @SafeParcelable.Field(getter = "getType", id = 2)
    public final int h;

    @SafeParcelable.Constructor
    public StreetViewSource(@SafeParcelable.Param(id = 2) int i) {
        this.h = i;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof StreetViewSource) && this.h == ((StreetViewSource) obj).h;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.h));
    }

    @NonNull
    public String toString() {
        int i = this.h;
        return String.format("StreetViewSource:%s", i != 0 ? i != 1 ? String.format("UNKNOWN(%s)", Integer.valueOf(i)) : "OUTDOOR" : "DEFAULT");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.h);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

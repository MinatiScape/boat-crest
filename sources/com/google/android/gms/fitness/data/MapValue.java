package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@ShowFirstParty
@SafeParcelable.Class(creator = "MapValueCreator")
@SafeParcelable.Reserved({1000})
/* loaded from: classes6.dex */
public class MapValue extends AbstractSafeParcelable implements ReflectedParcelable {
    @NonNull
    public static final Parcelable.Creator<MapValue> CREATOR = new zzy();
    @SafeParcelable.Field(getter = "getFormat", id = 1)
    public final int h;
    @SafeParcelable.Field(getter = "getValue", id = 2)
    public final float i;

    @SafeParcelable.Constructor
    public MapValue(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) float f) {
        this.h = i;
        this.i = f;
    }

    @NonNull
    public static MapValue zza(float f) {
        return new MapValue(2, f);
    }

    public final float asFloat() {
        Preconditions.checkState(this.h == 2, "Value is not in float format");
        return this.i;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapValue) {
            MapValue mapValue = (MapValue) obj;
            int i = this.h;
            if (i == mapValue.h) {
                if (i != 2) {
                    return this.i == mapValue.i;
                } else if (asFloat() == mapValue.asFloat()) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        return (int) this.i;
    }

    @NonNull
    public String toString() {
        return this.h != 2 ? "unknown" : Float.toString(asFloat());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.h);
        SafeParcelWriter.writeFloat(parcel, 2, this.i);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

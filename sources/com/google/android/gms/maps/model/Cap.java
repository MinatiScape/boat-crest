package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
@SafeParcelable.Class(creator = "CapCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes10.dex */
public class Cap extends AbstractSafeParcelable {
    @SafeParcelable.Field(getter = "getType", id = 2)
    public final int h;
    @Nullable
    @SafeParcelable.Field(getter = "getWrappedBitmapDescriptorImplBinder", id = 3, type = "android.os.IBinder")
    public final BitmapDescriptor i;
    @Nullable
    @SafeParcelable.Field(getter = "getBitmapRefWidth", id = 4)
    public final Float j;
    public static final String k = Cap.class.getSimpleName();
    @NonNull
    public static final Parcelable.Creator<Cap> CREATOR = new zzb();

    public Cap(int i) {
        this(i, (BitmapDescriptor) null, (Float) null);
    }

    public final Cap a() {
        int i = this.h;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        String str = k;
                        StringBuilder sb = new StringBuilder(29);
                        sb.append("Unknown Cap type: ");
                        sb.append(i);
                        Log.w(str, sb.toString());
                        return this;
                    }
                    Preconditions.checkState(this.i != null, "bitmapDescriptor must not be null");
                    Preconditions.checkState(this.j != null, "bitmapRefWidth must not be null");
                    return new CustomCap(this.i, this.j.floatValue());
                }
                return new RoundCap();
            }
            return new SquareCap();
        }
        return new ButtCap();
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Cap) {
            Cap cap = (Cap) obj;
            return this.h == cap.h && Objects.equal(this.i, cap.i) && Objects.equal(this.j, cap.j);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.h), this.i, this.j);
    }

    @NonNull
    public String toString() {
        int i = this.h;
        StringBuilder sb = new StringBuilder(23);
        sb.append("[Cap: type=");
        sb.append(i);
        sb.append("]");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.h);
        BitmapDescriptor bitmapDescriptor = this.i;
        SafeParcelWriter.writeIBinder(parcel, 3, bitmapDescriptor == null ? null : bitmapDescriptor.zza().asBinder(), false);
        SafeParcelWriter.writeFloatObject(parcel, 4, this.j, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @SafeParcelable.Constructor
    public Cap(@SafeParcelable.Param(id = 2) int i, @Nullable @SafeParcelable.Param(id = 3) IBinder iBinder, @Nullable @SafeParcelable.Param(id = 4) Float f) {
        this(i, iBinder == null ? null : new BitmapDescriptor(IObjectWrapper.Stub.asInterface(iBinder)), f);
    }

    public Cap(int i, @Nullable BitmapDescriptor bitmapDescriptor, @Nullable Float f) {
        boolean z;
        boolean z2 = f != null && f.floatValue() > 0.0f;
        if (i == 3) {
            z = bitmapDescriptor != null && z2;
            i = 3;
        } else {
            z = true;
        }
        Preconditions.checkArgument(z, String.format("Invalid Cap: type=%s bitmapDescriptor=%s bitmapRefWidth=%s", Integer.valueOf(i), bitmapDescriptor, f));
        this.h = i;
        this.i = bitmapDescriptor;
        this.j = f;
    }

    public Cap(@NonNull BitmapDescriptor bitmapDescriptor, float f) {
        this(3, bitmapDescriptor, Float.valueOf(f));
    }
}

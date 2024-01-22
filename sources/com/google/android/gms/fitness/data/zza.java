package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@ShowFirstParty
@SafeParcelable.Class(creator = "ApplicationCreator")
@SafeParcelable.Reserved({2, 3, 1000})
/* loaded from: classes6.dex */
public final class zza extends AbstractSafeParcelable {
    @SafeParcelable.Field(getter = "getPackageName", id = 1)
    public final String h;
    public static final zza zzlb = new zza("com.google.android.gms");
    public static final Parcelable.Creator<zza> CREATOR = new zzc();

    @SafeParcelable.Constructor
    public zza(@SafeParcelable.Param(id = 1) String str) {
        this.h = (String) Preconditions.checkNotNull(str);
    }

    public static zza zza(String str) {
        if ("com.google.android.gms".equals(str)) {
            return zzlb;
        }
        return new zza(str);
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zza) {
            return this.h.equals(((zza) obj).h);
        }
        return false;
    }

    public final String getPackageName() {
        return this.h;
    }

    public final int hashCode() {
        return this.h.hashCode();
    }

    public final String toString() {
        return String.format("Application{%s}", this.h);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.h, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

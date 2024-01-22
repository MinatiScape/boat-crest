package com.google.android.gms.clearcut;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "CollectForDebugParcelableCreator")
/* loaded from: classes6.dex */
public final class zzc extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzc> CREATOR = new zzd();
    @SafeParcelable.Field(defaultValue = "false", id = 1)
    public final boolean h;
    @SafeParcelable.Field(id = 3)
    public final long i;
    @SafeParcelable.Field(id = 2)
    public final long j;

    @SafeParcelable.Constructor
    public zzc(@SafeParcelable.Param(id = 1) boolean z, @SafeParcelable.Param(id = 3) long j, @SafeParcelable.Param(id = 2) long j2) {
        this.h = z;
        this.i = j;
        this.j = j2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzc) {
            zzc zzcVar = (zzc) obj;
            if (this.h == zzcVar.h && this.i == zzcVar.i && this.j == zzcVar.j) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.h), Long.valueOf(this.i), Long.valueOf(this.j));
    }

    public final String toString() {
        return "CollectForDebugParcelable[skipPersistentStorage: " + this.h + ",collectForDebugStartTimeMillis: " + this.i + ",collectForDebugExpiryTimeMillis: " + this.j + "]";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, this.h);
        SafeParcelWriter.writeLong(parcel, 2, this.j);
        SafeParcelWriter.writeLong(parcel, 3, this.i);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

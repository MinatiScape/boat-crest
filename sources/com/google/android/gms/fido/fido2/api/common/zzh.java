package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Arrays;
@SafeParcelable.Class(creator = "AuthenticationExtensionsPrfOutputsCreator")
/* loaded from: classes6.dex */
public final class zzh extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzh> CREATOR = new zzi();
    @SafeParcelable.Field(getter = "getSupported", id = 1)
    public final boolean h;
    @Nullable
    @SafeParcelable.Field(getter = "getOutputs", id = 2)
    public final byte[] i;

    @SafeParcelable.Constructor
    public zzh(@NonNull @SafeParcelable.Param(id = 1) boolean z, @Nullable @SafeParcelable.Param(id = 2) byte[] bArr) {
        this.h = z;
        this.i = bArr;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj instanceof zzh) {
            zzh zzhVar = (zzh) obj;
            return this.h == zzhVar.h && Arrays.equals(this.i, zzhVar.i);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.h), this.i);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, this.h);
        SafeParcelWriter.writeByteArray(parcel, 2, this.i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

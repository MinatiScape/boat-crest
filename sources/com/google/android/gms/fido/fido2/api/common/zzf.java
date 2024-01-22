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
@SafeParcelable.Class(creator = "AuthenticationExtensionsDevicePublicKeyOutputsCreator")
/* loaded from: classes6.dex */
public final class zzf extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzf> CREATOR = new zzg();
    @Nullable
    @SafeParcelable.Field(getter = "getSignature", id = 1)
    public final byte[] h;
    @Nullable
    @SafeParcelable.Field(getter = "getAuthenticatorOutput", id = 2)
    public final byte[] i;

    @SafeParcelable.Constructor
    public zzf(@Nullable @SafeParcelable.Param(id = 1) byte[] bArr, @Nullable @SafeParcelable.Param(id = 2) byte[] bArr2) {
        this.h = bArr;
        this.i = bArr2;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj instanceof zzf) {
            zzf zzfVar = (zzf) obj;
            return Arrays.equals(this.h, zzfVar.h) && Arrays.equals(this.i, zzfVar.i);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.h, this.i);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeByteArray(parcel, 1, this.h, false);
        SafeParcelWriter.writeByteArray(parcel, 2, this.i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

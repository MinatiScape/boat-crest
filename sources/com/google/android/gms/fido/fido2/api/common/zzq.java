package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Arrays;
@SafeParcelable.Class(creator = "CableAuthenticationDataCreator")
/* loaded from: classes6.dex */
public final class zzq extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzq> CREATOR = new zzr();
    @SafeParcelable.Field(getter = "getVersion", id = 1)
    public final long h;
    @NonNull
    @SafeParcelable.Field(getter = "getClientEid", id = 2)
    public final byte[] i;
    @NonNull
    @SafeParcelable.Field(getter = "getAuthenticatorEid", id = 3)
    public final byte[] j;
    @NonNull
    @SafeParcelable.Field(getter = "getSessionPreKey", id = 4)
    public final byte[] k;

    @SafeParcelable.Constructor
    public zzq(@SafeParcelable.Param(id = 1) long j, @NonNull @SafeParcelable.Param(id = 2) byte[] bArr, @NonNull @SafeParcelable.Param(id = 3) byte[] bArr2, @NonNull @SafeParcelable.Param(id = 4) byte[] bArr3) {
        this.h = j;
        this.i = (byte[]) Preconditions.checkNotNull(bArr);
        this.j = (byte[]) Preconditions.checkNotNull(bArr2);
        this.k = (byte[]) Preconditions.checkNotNull(bArr3);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzq) {
            zzq zzqVar = (zzq) obj;
            return this.h == zzqVar.h && Arrays.equals(this.i, zzqVar.i) && Arrays.equals(this.j, zzqVar.j) && Arrays.equals(this.k, zzqVar.k);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(Long.valueOf(this.h), this.i, this.j, this.k);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.h);
        SafeParcelWriter.writeByteArray(parcel, 2, this.i, false);
        SafeParcelWriter.writeByteArray(parcel, 3, this.j, false);
        SafeParcelWriter.writeByteArray(parcel, 4, this.k, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

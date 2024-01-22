package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.fido.u2f.api.common.SignResponseData;
import com.google.android.gms.internal.fido.zzbf;
import java.util.Arrays;
@SafeParcelable.Class(creator = "AuthenticatorAssertionResponseCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes6.dex */
public class AuthenticatorAssertionResponse extends AuthenticatorResponse {
    @NonNull
    public static final Parcelable.Creator<AuthenticatorAssertionResponse> CREATOR = new zzj();
    @NonNull
    @SafeParcelable.Field(getter = "getKeyHandle", id = 2)
    public final byte[] h;
    @NonNull
    @SafeParcelable.Field(getter = "getClientDataJSON", id = 3)
    public final byte[] i;
    @NonNull
    @SafeParcelable.Field(getter = "getAuthenticatorData", id = 4)
    public final byte[] j;
    @NonNull
    @SafeParcelable.Field(getter = "getSignature", id = 5)
    public final byte[] k;
    @Nullable
    @SafeParcelable.Field(getter = "getUserHandle", id = 6)
    public final byte[] l;

    @SafeParcelable.Constructor
    public AuthenticatorAssertionResponse(@NonNull @SafeParcelable.Param(id = 2) byte[] bArr, @NonNull @SafeParcelable.Param(id = 3) byte[] bArr2, @NonNull @SafeParcelable.Param(id = 4) byte[] bArr3, @NonNull @SafeParcelable.Param(id = 5) byte[] bArr4, @Nullable @SafeParcelable.Param(id = 6) byte[] bArr5) {
        this.h = (byte[]) Preconditions.checkNotNull(bArr);
        this.i = (byte[]) Preconditions.checkNotNull(bArr2);
        this.j = (byte[]) Preconditions.checkNotNull(bArr3);
        this.k = (byte[]) Preconditions.checkNotNull(bArr4);
        this.l = bArr5;
    }

    @NonNull
    public static AuthenticatorAssertionResponse deserializeFromBytes(@NonNull byte[] bArr) {
        return (AuthenticatorAssertionResponse) SafeParcelableSerializer.deserializeFromBytes(bArr, CREATOR);
    }

    public boolean equals(@NonNull Object obj) {
        if (obj instanceof AuthenticatorAssertionResponse) {
            AuthenticatorAssertionResponse authenticatorAssertionResponse = (AuthenticatorAssertionResponse) obj;
            return Arrays.equals(this.h, authenticatorAssertionResponse.h) && Arrays.equals(this.i, authenticatorAssertionResponse.i) && Arrays.equals(this.j, authenticatorAssertionResponse.j) && Arrays.equals(this.k, authenticatorAssertionResponse.k) && Arrays.equals(this.l, authenticatorAssertionResponse.l);
        }
        return false;
    }

    @NonNull
    public byte[] getAuthenticatorData() {
        return this.j;
    }

    @Override // com.google.android.gms.fido.fido2.api.common.AuthenticatorResponse
    @NonNull
    public byte[] getClientDataJSON() {
        return this.i;
    }

    @NonNull
    @Deprecated
    public byte[] getKeyHandle() {
        return this.h;
    }

    @NonNull
    public byte[] getSignature() {
        return this.k;
    }

    @Nullable
    public byte[] getUserHandle() {
        return this.l;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(Arrays.hashCode(this.h)), Integer.valueOf(Arrays.hashCode(this.i)), Integer.valueOf(Arrays.hashCode(this.j)), Integer.valueOf(Arrays.hashCode(this.k)), Integer.valueOf(Arrays.hashCode(this.l)));
    }

    @Override // com.google.android.gms.fido.fido2.api.common.AuthenticatorResponse
    @NonNull
    public byte[] serializeToBytes() {
        return SafeParcelableSerializer.serializeToBytes(this);
    }

    @NonNull
    public String toString() {
        com.google.android.gms.internal.fido.zzaj zza = com.google.android.gms.internal.fido.zzak.zza(this);
        zzbf zzd = zzbf.zzd();
        byte[] bArr = this.h;
        zza.zzb(SignResponseData.JSON_RESPONSE_DATA_KEY_HANDLE, zzd.zze(bArr, 0, bArr.length));
        zzbf zzd2 = zzbf.zzd();
        byte[] bArr2 = this.i;
        zza.zzb("clientDataJSON", zzd2.zze(bArr2, 0, bArr2.length));
        zzbf zzd3 = zzbf.zzd();
        byte[] bArr3 = this.j;
        zza.zzb("authenticatorData", zzd3.zze(bArr3, 0, bArr3.length));
        zzbf zzd4 = zzbf.zzd();
        byte[] bArr4 = this.k;
        zza.zzb("signature", zzd4.zze(bArr4, 0, bArr4.length));
        byte[] bArr5 = this.l;
        if (bArr5 != null) {
            zza.zzb("userHandle", zzbf.zzd().zze(bArr5, 0, bArr5.length));
        }
        return zza.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeByteArray(parcel, 2, getKeyHandle(), false);
        SafeParcelWriter.writeByteArray(parcel, 3, getClientDataJSON(), false);
        SafeParcelWriter.writeByteArray(parcel, 4, getAuthenticatorData(), false);
        SafeParcelWriter.writeByteArray(parcel, 5, getSignature(), false);
        SafeParcelWriter.writeByteArray(parcel, 6, getUserHandle(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

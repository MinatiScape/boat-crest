package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.fido.fido2.api.common.ErrorCode;
import com.jstyle.blesdk1860.constant.BleConst;
@SafeParcelable.Class(creator = "AuthenticatorErrorResponseCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes6.dex */
public class AuthenticatorErrorResponse extends AuthenticatorResponse {
    @NonNull
    public static final Parcelable.Creator<AuthenticatorErrorResponse> CREATOR = new zzl();
    @NonNull
    @SafeParcelable.Field(getter = "getErrorCodeAsInt", id = 2, type = "int")
    public final ErrorCode h;
    @Nullable
    @SafeParcelable.Field(getter = "getErrorMessage", id = 3)
    public final String i;
    @SafeParcelable.Field(defaultValue = BleConst.GetDeviceTime, getter = "getInternalErrorCode", id = 4, type = "int")
    public final int j;

    @SafeParcelable.Constructor
    public AuthenticatorErrorResponse(@NonNull @SafeParcelable.Param(id = 2) int i, @Nullable @SafeParcelable.Param(id = 3) String str, @SafeParcelable.Param(id = 4) int i2) {
        try {
            this.h = ErrorCode.toErrorCode(i);
            this.i = str;
            this.j = i2;
        } catch (ErrorCode.UnsupportedErrorCodeException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @NonNull
    public static AuthenticatorErrorResponse deserializeFromBytes(@NonNull byte[] bArr) {
        return (AuthenticatorErrorResponse) SafeParcelableSerializer.deserializeFromBytes(bArr, CREATOR);
    }

    public boolean equals(@NonNull Object obj) {
        if (obj instanceof AuthenticatorErrorResponse) {
            AuthenticatorErrorResponse authenticatorErrorResponse = (AuthenticatorErrorResponse) obj;
            return Objects.equal(this.h, authenticatorErrorResponse.h) && Objects.equal(this.i, authenticatorErrorResponse.i) && Objects.equal(Integer.valueOf(this.j), Integer.valueOf(authenticatorErrorResponse.j));
        }
        return false;
    }

    @Override // com.google.android.gms.fido.fido2.api.common.AuthenticatorResponse
    @NonNull
    public byte[] getClientDataJSON() {
        throw new UnsupportedOperationException();
    }

    @NonNull
    public ErrorCode getErrorCode() {
        return this.h;
    }

    public int getErrorCodeAsInt() {
        return this.h.getCode();
    }

    @Nullable
    public String getErrorMessage() {
        return this.i;
    }

    public int hashCode() {
        return Objects.hashCode(this.h, this.i, Integer.valueOf(this.j));
    }

    @Override // com.google.android.gms.fido.fido2.api.common.AuthenticatorResponse
    @NonNull
    public byte[] serializeToBytes() {
        return SafeParcelableSerializer.serializeToBytes(this);
    }

    @NonNull
    public String toString() {
        com.google.android.gms.internal.fido.zzaj zza = com.google.android.gms.internal.fido.zzak.zza(this);
        zza.zza("errorCode", this.h.getCode());
        String str = this.i;
        if (str != null) {
            zza.zzb("errorMessage", str);
        }
        return zza.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, getErrorCodeAsInt());
        SafeParcelWriter.writeString(parcel, 3, getErrorMessage(), false);
        SafeParcelWriter.writeInt(parcel, 4, this.j);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

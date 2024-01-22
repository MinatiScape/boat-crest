package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import java.util.Arrays;
@SafeParcelable.Class(creator = "PublicKeyCredentialCreator")
/* loaded from: classes6.dex */
public class PublicKeyCredential extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<PublicKeyCredential> CREATOR = new zzal();
    @NonNull
    @SafeParcelable.Field(getter = "getId", id = 1)
    public final String h;
    @NonNull
    @SafeParcelable.Field(getter = "getType", id = 2)
    public final String i;
    @NonNull
    @SafeParcelable.Field(getter = "getRawId", id = 3)
    public final byte[] j;
    @Nullable
    @SafeParcelable.Field(getter = "getRegisterResponse", id = 4)
    public final AuthenticatorAttestationResponse k;
    @Nullable
    @SafeParcelable.Field(getter = "getSignResponse", id = 5)
    public final AuthenticatorAssertionResponse l;
    @Nullable
    @SafeParcelable.Field(getter = "getErrorResponse", id = 6)
    public final AuthenticatorErrorResponse m;
    @Nullable
    @SafeParcelable.Field(getter = "getClientExtensionResults", id = 7)
    public final AuthenticationExtensionsClientOutputs n;
    @Nullable
    @SafeParcelable.Field(getter = "getAuthenticatorAttachment", id = 8)
    public final String o;

    /* loaded from: classes6.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f8400a;
        public byte[] b;
        public AuthenticatorResponse c;
        public AuthenticationExtensionsClientOutputs d;
        public String e;

        @NonNull
        public PublicKeyCredential build() {
            AuthenticatorResponse authenticatorResponse = this.c;
            return new PublicKeyCredential(this.f8400a, PublicKeyCredentialType.PUBLIC_KEY.toString(), this.b, authenticatorResponse instanceof AuthenticatorAttestationResponse ? (AuthenticatorAttestationResponse) authenticatorResponse : null, authenticatorResponse instanceof AuthenticatorAssertionResponse ? (AuthenticatorAssertionResponse) authenticatorResponse : null, authenticatorResponse instanceof AuthenticatorErrorResponse ? (AuthenticatorErrorResponse) authenticatorResponse : null, this.d, this.e);
        }

        @NonNull
        public Builder setAuthenticationExtensionsClientOutputs(@Nullable AuthenticationExtensionsClientOutputs authenticationExtensionsClientOutputs) {
            this.d = authenticationExtensionsClientOutputs;
            return this;
        }

        @NonNull
        public Builder setAuthenticatorAttachment(@NonNull String str) {
            this.e = str;
            return this;
        }

        @NonNull
        public Builder setId(@NonNull String str) {
            this.f8400a = str;
            return this;
        }

        @NonNull
        public Builder setRawId(@NonNull byte[] bArr) {
            this.b = bArr;
            return this;
        }

        @NonNull
        public Builder setResponse(@NonNull AuthenticatorResponse authenticatorResponse) {
            this.c = authenticatorResponse;
            return this;
        }
    }

    @SafeParcelable.Constructor
    public PublicKeyCredential(@NonNull @SafeParcelable.Param(id = 1) String str, @NonNull @SafeParcelable.Param(id = 2) String str2, @NonNull @SafeParcelable.Param(id = 3) byte[] bArr, @Nullable @SafeParcelable.Param(id = 4) AuthenticatorAttestationResponse authenticatorAttestationResponse, @Nullable @SafeParcelable.Param(id = 5) AuthenticatorAssertionResponse authenticatorAssertionResponse, @Nullable @SafeParcelable.Param(id = 6) AuthenticatorErrorResponse authenticatorErrorResponse, @Nullable @SafeParcelable.Param(id = 7) AuthenticationExtensionsClientOutputs authenticationExtensionsClientOutputs, @Nullable @SafeParcelable.Param(id = 8) String str3) {
        boolean z = true;
        if ((authenticatorAttestationResponse == null || authenticatorAssertionResponse != null || authenticatorErrorResponse != null) && ((authenticatorAttestationResponse != null || authenticatorAssertionResponse == null || authenticatorErrorResponse != null) && (authenticatorAttestationResponse != null || authenticatorAssertionResponse != null || authenticatorErrorResponse == null))) {
            z = false;
        }
        Preconditions.checkArgument(z);
        this.h = str;
        this.i = str2;
        this.j = bArr;
        this.k = authenticatorAttestationResponse;
        this.l = authenticatorAssertionResponse;
        this.m = authenticatorErrorResponse;
        this.n = authenticationExtensionsClientOutputs;
        this.o = str3;
    }

    @NonNull
    public static PublicKeyCredential deserializeFromBytes(@NonNull byte[] bArr) {
        return (PublicKeyCredential) SafeParcelableSerializer.deserializeFromBytes(bArr, CREATOR);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof PublicKeyCredential) {
            PublicKeyCredential publicKeyCredential = (PublicKeyCredential) obj;
            return Objects.equal(this.h, publicKeyCredential.h) && Objects.equal(this.i, publicKeyCredential.i) && Arrays.equals(this.j, publicKeyCredential.j) && Objects.equal(this.k, publicKeyCredential.k) && Objects.equal(this.l, publicKeyCredential.l) && Objects.equal(this.m, publicKeyCredential.m) && Objects.equal(this.n, publicKeyCredential.n) && Objects.equal(this.o, publicKeyCredential.o);
        }
        return false;
    }

    @Nullable
    public String getAuthenticatorAttachment() {
        return this.o;
    }

    @Nullable
    public AuthenticationExtensionsClientOutputs getClientExtensionResults() {
        return this.n;
    }

    @NonNull
    public String getId() {
        return this.h;
    }

    @NonNull
    public byte[] getRawId() {
        return this.j;
    }

    @NonNull
    public AuthenticatorResponse getResponse() {
        AuthenticatorAttestationResponse authenticatorAttestationResponse = this.k;
        if (authenticatorAttestationResponse != null) {
            return authenticatorAttestationResponse;
        }
        AuthenticatorAssertionResponse authenticatorAssertionResponse = this.l;
        if (authenticatorAssertionResponse != null) {
            return authenticatorAssertionResponse;
        }
        AuthenticatorErrorResponse authenticatorErrorResponse = this.m;
        if (authenticatorErrorResponse != null) {
            return authenticatorErrorResponse;
        }
        throw new IllegalStateException("No response set.");
    }

    @NonNull
    public String getType() {
        return this.i;
    }

    public int hashCode() {
        return Objects.hashCode(this.h, this.i, this.j, this.l, this.k, this.m, this.n, this.o);
    }

    @NonNull
    public byte[] serializeToBytes() {
        return SafeParcelableSerializer.serializeToBytes(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getId(), false);
        SafeParcelWriter.writeString(parcel, 2, getType(), false);
        SafeParcelWriter.writeByteArray(parcel, 3, getRawId(), false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.k, i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.l, i, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.m, i, false);
        SafeParcelWriter.writeParcelable(parcel, 7, getClientExtensionResults(), i, false);
        SafeParcelWriter.writeString(parcel, 8, getAuthenticatorAttachment(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

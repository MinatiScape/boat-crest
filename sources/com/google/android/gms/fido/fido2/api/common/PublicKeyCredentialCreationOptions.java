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
import com.google.android.gms.fido.fido2.api.common.AttestationConveyancePreference;
import java.util.Arrays;
import java.util.List;
@SafeParcelable.Class(creator = "PublicKeyCredentialCreationOptionsCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes6.dex */
public class PublicKeyCredentialCreationOptions extends RequestOptions {
    @NonNull
    public static final Parcelable.Creator<PublicKeyCredentialCreationOptions> CREATOR = new zzak();
    @NonNull
    @SafeParcelable.Field(getter = "getRp", id = 2)
    public final PublicKeyCredentialRpEntity h;
    @NonNull
    @SafeParcelable.Field(getter = "getUser", id = 3)
    public final PublicKeyCredentialUserEntity i;
    @NonNull
    @SafeParcelable.Field(getter = "getChallenge", id = 4)
    public final byte[] j;
    @NonNull
    @SafeParcelable.Field(getter = "getParameters", id = 5)
    public final List k;
    @Nullable
    @SafeParcelable.Field(getter = "getTimeoutSeconds", id = 6)
    public final Double l;
    @Nullable
    @SafeParcelable.Field(getter = "getExcludeList", id = 7)
    public final List m;
    @Nullable
    @SafeParcelable.Field(getter = "getAuthenticatorSelection", id = 8)
    public final AuthenticatorSelectionCriteria n;
    @Nullable
    @SafeParcelable.Field(getter = "getRequestId", id = 9)
    public final Integer o;
    @Nullable
    @SafeParcelable.Field(getter = "getTokenBinding", id = 10)
    public final TokenBinding p;
    @Nullable
    @SafeParcelable.Field(getter = "getAttestationConveyancePreferenceAsString", id = 11, type = "java.lang.String")
    public final AttestationConveyancePreference q;
    @Nullable
    @SafeParcelable.Field(getter = "getAuthenticationExtensions", id = 12)
    public final AuthenticationExtensions r;

    /* loaded from: classes6.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public PublicKeyCredentialRpEntity f8401a;
        public PublicKeyCredentialUserEntity b;
        public byte[] c;
        public List d;
        public Double e;
        public List f;
        public AuthenticatorSelectionCriteria g;
        public Integer h;
        public TokenBinding i;
        public AttestationConveyancePreference j;
        public AuthenticationExtensions k;

        @NonNull
        public PublicKeyCredentialCreationOptions build() {
            PublicKeyCredentialRpEntity publicKeyCredentialRpEntity = this.f8401a;
            PublicKeyCredentialUserEntity publicKeyCredentialUserEntity = this.b;
            byte[] bArr = this.c;
            List list = this.d;
            Double d = this.e;
            List list2 = this.f;
            AuthenticatorSelectionCriteria authenticatorSelectionCriteria = this.g;
            Integer num = this.h;
            TokenBinding tokenBinding = this.i;
            AttestationConveyancePreference attestationConveyancePreference = this.j;
            return new PublicKeyCredentialCreationOptions(publicKeyCredentialRpEntity, publicKeyCredentialUserEntity, bArr, list, d, list2, authenticatorSelectionCriteria, num, tokenBinding, attestationConveyancePreference == null ? null : attestationConveyancePreference.toString(), this.k);
        }

        @NonNull
        public Builder setAttestationConveyancePreference(@Nullable AttestationConveyancePreference attestationConveyancePreference) {
            this.j = attestationConveyancePreference;
            return this;
        }

        @NonNull
        public Builder setAuthenticationExtensions(@Nullable AuthenticationExtensions authenticationExtensions) {
            this.k = authenticationExtensions;
            return this;
        }

        @NonNull
        public Builder setAuthenticatorSelection(@Nullable AuthenticatorSelectionCriteria authenticatorSelectionCriteria) {
            this.g = authenticatorSelectionCriteria;
            return this;
        }

        @NonNull
        public Builder setChallenge(@NonNull byte[] bArr) {
            this.c = (byte[]) Preconditions.checkNotNull(bArr);
            return this;
        }

        @NonNull
        public Builder setExcludeList(@Nullable List<PublicKeyCredentialDescriptor> list) {
            this.f = list;
            return this;
        }

        @NonNull
        public Builder setParameters(@NonNull List<PublicKeyCredentialParameters> list) {
            this.d = (List) Preconditions.checkNotNull(list);
            return this;
        }

        @NonNull
        public Builder setRequestId(@Nullable Integer num) {
            this.h = num;
            return this;
        }

        @NonNull
        public Builder setRp(@NonNull PublicKeyCredentialRpEntity publicKeyCredentialRpEntity) {
            this.f8401a = (PublicKeyCredentialRpEntity) Preconditions.checkNotNull(publicKeyCredentialRpEntity);
            return this;
        }

        @NonNull
        public Builder setTimeoutSeconds(@Nullable Double d) {
            this.e = d;
            return this;
        }

        @NonNull
        public Builder setTokenBinding(@Nullable TokenBinding tokenBinding) {
            this.i = tokenBinding;
            return this;
        }

        @NonNull
        public Builder setUser(@NonNull PublicKeyCredentialUserEntity publicKeyCredentialUserEntity) {
            this.b = (PublicKeyCredentialUserEntity) Preconditions.checkNotNull(publicKeyCredentialUserEntity);
            return this;
        }
    }

    @SafeParcelable.Constructor
    public PublicKeyCredentialCreationOptions(@NonNull @SafeParcelable.Param(id = 2) PublicKeyCredentialRpEntity publicKeyCredentialRpEntity, @NonNull @SafeParcelable.Param(id = 3) PublicKeyCredentialUserEntity publicKeyCredentialUserEntity, @NonNull @SafeParcelable.Param(id = 4) byte[] bArr, @NonNull @SafeParcelable.Param(id = 5) List list, @Nullable @SafeParcelable.Param(id = 6) Double d, @Nullable @SafeParcelable.Param(id = 7) List list2, @Nullable @SafeParcelable.Param(id = 8) AuthenticatorSelectionCriteria authenticatorSelectionCriteria, @Nullable @SafeParcelable.Param(id = 9) Integer num, @Nullable @SafeParcelable.Param(id = 10) TokenBinding tokenBinding, @Nullable @SafeParcelable.Param(id = 11) String str, @Nullable @SafeParcelable.Param(id = 12) AuthenticationExtensions authenticationExtensions) {
        this.h = (PublicKeyCredentialRpEntity) Preconditions.checkNotNull(publicKeyCredentialRpEntity);
        this.i = (PublicKeyCredentialUserEntity) Preconditions.checkNotNull(publicKeyCredentialUserEntity);
        this.j = (byte[]) Preconditions.checkNotNull(bArr);
        this.k = (List) Preconditions.checkNotNull(list);
        this.l = d;
        this.m = list2;
        this.n = authenticatorSelectionCriteria;
        this.o = num;
        this.p = tokenBinding;
        if (str != null) {
            try {
                this.q = AttestationConveyancePreference.fromString(str);
            } catch (AttestationConveyancePreference.UnsupportedAttestationConveyancePreferenceException e) {
                throw new IllegalArgumentException(e);
            }
        } else {
            this.q = null;
        }
        this.r = authenticationExtensions;
    }

    @NonNull
    public static PublicKeyCredentialCreationOptions deserializeFromBytes(@NonNull byte[] bArr) {
        return (PublicKeyCredentialCreationOptions) SafeParcelableSerializer.deserializeFromBytes(bArr, CREATOR);
    }

    public boolean equals(@NonNull Object obj) {
        List list;
        List list2;
        if (obj instanceof PublicKeyCredentialCreationOptions) {
            PublicKeyCredentialCreationOptions publicKeyCredentialCreationOptions = (PublicKeyCredentialCreationOptions) obj;
            return Objects.equal(this.h, publicKeyCredentialCreationOptions.h) && Objects.equal(this.i, publicKeyCredentialCreationOptions.i) && Arrays.equals(this.j, publicKeyCredentialCreationOptions.j) && Objects.equal(this.l, publicKeyCredentialCreationOptions.l) && this.k.containsAll(publicKeyCredentialCreationOptions.k) && publicKeyCredentialCreationOptions.k.containsAll(this.k) && (((list = this.m) == null && publicKeyCredentialCreationOptions.m == null) || (list != null && (list2 = publicKeyCredentialCreationOptions.m) != null && list.containsAll(list2) && publicKeyCredentialCreationOptions.m.containsAll(this.m))) && Objects.equal(this.n, publicKeyCredentialCreationOptions.n) && Objects.equal(this.o, publicKeyCredentialCreationOptions.o) && Objects.equal(this.p, publicKeyCredentialCreationOptions.p) && Objects.equal(this.q, publicKeyCredentialCreationOptions.q) && Objects.equal(this.r, publicKeyCredentialCreationOptions.r);
        }
        return false;
    }

    @Nullable
    public AttestationConveyancePreference getAttestationConveyancePreference() {
        return this.q;
    }

    @Nullable
    public String getAttestationConveyancePreferenceAsString() {
        AttestationConveyancePreference attestationConveyancePreference = this.q;
        if (attestationConveyancePreference == null) {
            return null;
        }
        return attestationConveyancePreference.toString();
    }

    @Override // com.google.android.gms.fido.fido2.api.common.RequestOptions
    @Nullable
    public AuthenticationExtensions getAuthenticationExtensions() {
        return this.r;
    }

    @Nullable
    public AuthenticatorSelectionCriteria getAuthenticatorSelection() {
        return this.n;
    }

    @Override // com.google.android.gms.fido.fido2.api.common.RequestOptions
    @NonNull
    public byte[] getChallenge() {
        return this.j;
    }

    @Nullable
    public List<PublicKeyCredentialDescriptor> getExcludeList() {
        return this.m;
    }

    @NonNull
    public List<PublicKeyCredentialParameters> getParameters() {
        return this.k;
    }

    @Override // com.google.android.gms.fido.fido2.api.common.RequestOptions
    @Nullable
    public Integer getRequestId() {
        return this.o;
    }

    @NonNull
    public PublicKeyCredentialRpEntity getRp() {
        return this.h;
    }

    @Override // com.google.android.gms.fido.fido2.api.common.RequestOptions
    @Nullable
    public Double getTimeoutSeconds() {
        return this.l;
    }

    @Override // com.google.android.gms.fido.fido2.api.common.RequestOptions
    @Nullable
    public TokenBinding getTokenBinding() {
        return this.p;
    }

    @NonNull
    public PublicKeyCredentialUserEntity getUser() {
        return this.i;
    }

    public int hashCode() {
        return Objects.hashCode(this.h, this.i, Integer.valueOf(Arrays.hashCode(this.j)), this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.r);
    }

    @Override // com.google.android.gms.fido.fido2.api.common.RequestOptions
    @NonNull
    public byte[] serializeToBytes() {
        return SafeParcelableSerializer.serializeToBytes(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getRp(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, getUser(), i, false);
        SafeParcelWriter.writeByteArray(parcel, 4, getChallenge(), false);
        SafeParcelWriter.writeTypedList(parcel, 5, getParameters(), false);
        SafeParcelWriter.writeDoubleObject(parcel, 6, getTimeoutSeconds(), false);
        SafeParcelWriter.writeTypedList(parcel, 7, getExcludeList(), false);
        SafeParcelWriter.writeParcelable(parcel, 8, getAuthenticatorSelection(), i, false);
        SafeParcelWriter.writeIntegerObject(parcel, 9, getRequestId(), false);
        SafeParcelWriter.writeParcelable(parcel, 10, getTokenBinding(), i, false);
        SafeParcelWriter.writeString(parcel, 11, getAttestationConveyancePreferenceAsString(), false);
        SafeParcelWriter.writeParcelable(parcel, 12, getAuthenticationExtensions(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

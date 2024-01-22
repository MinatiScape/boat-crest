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
import java.util.Arrays;
import java.util.List;
@SafeParcelable.Class(creator = "PublicKeyCredentialRequestOptionsCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes6.dex */
public class PublicKeyCredentialRequestOptions extends RequestOptions {
    @NonNull
    public static final Parcelable.Creator<PublicKeyCredentialRequestOptions> CREATOR = new zzao();
    @NonNull
    @SafeParcelable.Field(getter = "getChallenge", id = 2)
    public final byte[] h;
    @Nullable
    @SafeParcelable.Field(getter = "getTimeoutSeconds", id = 3)
    public final Double i;
    @NonNull
    @SafeParcelable.Field(getter = "getRpId", id = 4)
    public final String j;
    @Nullable
    @SafeParcelable.Field(getter = "getAllowList", id = 5)
    public final List k;
    @Nullable
    @SafeParcelable.Field(getter = "getRequestId", id = 6)
    public final Integer l;
    @Nullable
    @SafeParcelable.Field(getter = "getTokenBinding", id = 7)
    public final TokenBinding m;
    @Nullable
    @SafeParcelable.Field(getter = "getUserVerificationAsString", id = 8, type = "java.lang.String")
    public final zzay n;
    @Nullable
    @SafeParcelable.Field(getter = "getAuthenticationExtensions", id = 9)
    public final AuthenticationExtensions o;
    @Nullable
    @SafeParcelable.Field(getter = "getLongRequestId", id = 10)
    public final Long p;

    /* loaded from: classes6.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public byte[] f8402a;
        public Double b;
        public String c;
        public List d;
        public Integer e;
        public TokenBinding f;
        public zzay g;
        public AuthenticationExtensions h;

        public Builder() {
        }

        public Builder(@Nullable PublicKeyCredentialRequestOptions publicKeyCredentialRequestOptions) {
            if (publicKeyCredentialRequestOptions != null) {
                this.f8402a = publicKeyCredentialRequestOptions.getChallenge();
                this.b = publicKeyCredentialRequestOptions.getTimeoutSeconds();
                this.c = publicKeyCredentialRequestOptions.getRpId();
                this.d = publicKeyCredentialRequestOptions.getAllowList();
                this.e = publicKeyCredentialRequestOptions.getRequestId();
                this.f = publicKeyCredentialRequestOptions.getTokenBinding();
                this.g = publicKeyCredentialRequestOptions.zza();
                this.h = publicKeyCredentialRequestOptions.getAuthenticationExtensions();
            }
        }

        @NonNull
        public PublicKeyCredentialRequestOptions build() {
            byte[] bArr = this.f8402a;
            Double d = this.b;
            String str = this.c;
            List list = this.d;
            Integer num = this.e;
            TokenBinding tokenBinding = this.f;
            zzay zzayVar = this.g;
            return new PublicKeyCredentialRequestOptions(bArr, d, str, list, num, tokenBinding, zzayVar == null ? null : zzayVar.toString(), this.h, null);
        }

        @NonNull
        public Builder setAllowList(@Nullable List<PublicKeyCredentialDescriptor> list) {
            this.d = list;
            return this;
        }

        @NonNull
        public Builder setAuthenticationExtensions(@Nullable AuthenticationExtensions authenticationExtensions) {
            this.h = authenticationExtensions;
            return this;
        }

        @NonNull
        public Builder setChallenge(@NonNull byte[] bArr) {
            this.f8402a = (byte[]) Preconditions.checkNotNull(bArr);
            return this;
        }

        @NonNull
        public Builder setRequestId(@Nullable Integer num) {
            this.e = num;
            return this;
        }

        @NonNull
        public Builder setRpId(@NonNull String str) {
            this.c = (String) Preconditions.checkNotNull(str);
            return this;
        }

        @NonNull
        public Builder setTimeoutSeconds(@Nullable Double d) {
            this.b = d;
            return this;
        }

        @NonNull
        public Builder setTokenBinding(@Nullable TokenBinding tokenBinding) {
            this.f = tokenBinding;
            return this;
        }
    }

    @SafeParcelable.Constructor
    public PublicKeyCredentialRequestOptions(@NonNull @SafeParcelable.Param(id = 2) byte[] bArr, @Nullable @SafeParcelable.Param(id = 3) Double d, @NonNull @SafeParcelable.Param(id = 4) String str, @Nullable @SafeParcelable.Param(id = 5) List list, @Nullable @SafeParcelable.Param(id = 6) Integer num, @Nullable @SafeParcelable.Param(id = 7) TokenBinding tokenBinding, @Nullable @SafeParcelable.Param(id = 8) String str2, @Nullable @SafeParcelable.Param(id = 9) AuthenticationExtensions authenticationExtensions, @Nullable @SafeParcelable.Param(id = 10) Long l) {
        this.h = (byte[]) Preconditions.checkNotNull(bArr);
        this.i = d;
        this.j = (String) Preconditions.checkNotNull(str);
        this.k = list;
        this.l = num;
        this.m = tokenBinding;
        this.p = l;
        if (str2 != null) {
            try {
                this.n = zzay.zza(str2);
            } catch (zzax e) {
                throw new IllegalArgumentException(e);
            }
        } else {
            this.n = null;
        }
        this.o = authenticationExtensions;
    }

    @NonNull
    public static PublicKeyCredentialRequestOptions deserializeFromBytes(@Nullable byte[] bArr) {
        return (PublicKeyCredentialRequestOptions) SafeParcelableSerializer.deserializeFromBytes(bArr, CREATOR);
    }

    public boolean equals(@NonNull Object obj) {
        List list;
        List list2;
        if (obj instanceof PublicKeyCredentialRequestOptions) {
            PublicKeyCredentialRequestOptions publicKeyCredentialRequestOptions = (PublicKeyCredentialRequestOptions) obj;
            return Arrays.equals(this.h, publicKeyCredentialRequestOptions.h) && Objects.equal(this.i, publicKeyCredentialRequestOptions.i) && Objects.equal(this.j, publicKeyCredentialRequestOptions.j) && (((list = this.k) == null && publicKeyCredentialRequestOptions.k == null) || (list != null && (list2 = publicKeyCredentialRequestOptions.k) != null && list.containsAll(list2) && publicKeyCredentialRequestOptions.k.containsAll(this.k))) && Objects.equal(this.l, publicKeyCredentialRequestOptions.l) && Objects.equal(this.m, publicKeyCredentialRequestOptions.m) && Objects.equal(this.n, publicKeyCredentialRequestOptions.n) && Objects.equal(this.o, publicKeyCredentialRequestOptions.o) && Objects.equal(this.p, publicKeyCredentialRequestOptions.p);
        }
        return false;
    }

    @Nullable
    public List<PublicKeyCredentialDescriptor> getAllowList() {
        return this.k;
    }

    @Override // com.google.android.gms.fido.fido2.api.common.RequestOptions
    @Nullable
    public AuthenticationExtensions getAuthenticationExtensions() {
        return this.o;
    }

    @Override // com.google.android.gms.fido.fido2.api.common.RequestOptions
    @NonNull
    public byte[] getChallenge() {
        return this.h;
    }

    @Override // com.google.android.gms.fido.fido2.api.common.RequestOptions
    @Nullable
    public Integer getRequestId() {
        return this.l;
    }

    @NonNull
    public String getRpId() {
        return this.j;
    }

    @Override // com.google.android.gms.fido.fido2.api.common.RequestOptions
    @Nullable
    public Double getTimeoutSeconds() {
        return this.i;
    }

    @Override // com.google.android.gms.fido.fido2.api.common.RequestOptions
    @Nullable
    public TokenBinding getTokenBinding() {
        return this.m;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(Arrays.hashCode(this.h)), this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p);
    }

    @Override // com.google.android.gms.fido.fido2.api.common.RequestOptions
    @NonNull
    public byte[] serializeToBytes() {
        return SafeParcelableSerializer.serializeToBytes(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeByteArray(parcel, 2, getChallenge(), false);
        SafeParcelWriter.writeDoubleObject(parcel, 3, getTimeoutSeconds(), false);
        SafeParcelWriter.writeString(parcel, 4, getRpId(), false);
        SafeParcelWriter.writeTypedList(parcel, 5, getAllowList(), false);
        SafeParcelWriter.writeIntegerObject(parcel, 6, getRequestId(), false);
        SafeParcelWriter.writeParcelable(parcel, 7, getTokenBinding(), i, false);
        zzay zzayVar = this.n;
        SafeParcelWriter.writeString(parcel, 8, zzayVar == null ? null : zzayVar.toString(), false);
        SafeParcelWriter.writeParcelable(parcel, 9, getAuthenticationExtensions(), i, false);
        SafeParcelWriter.writeLongObject(parcel, 10, this.p, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Nullable
    public final zzay zza() {
        return this.n;
    }
}

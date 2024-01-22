package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
@SafeParcelable.Class(creator = "AuthenticationExtensionsClientOutputsCreator")
/* loaded from: classes6.dex */
public class AuthenticationExtensionsClientOutputs extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<AuthenticationExtensionsClientOutputs> CREATOR = new zzc();
    @Nullable
    @SafeParcelable.Field(getter = "getUvmEntries", id = 1)
    public final UvmEntries h;
    @Nullable
    @SafeParcelable.Field(getter = "getDevicePubKey", id = 2)
    public final zzf i;
    @Nullable
    @SafeParcelable.Field(getter = "getCredProps", id = 3)
    public final AuthenticationExtensionsCredPropsOutputs j;
    @Nullable
    @SafeParcelable.Field(getter = "getPrf", id = 4)
    public final zzh k;

    /* loaded from: classes6.dex */
    public static final class Builder {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public UvmEntries f8396a;
        @Nullable
        public AuthenticationExtensionsCredPropsOutputs b;

        @NonNull
        public AuthenticationExtensionsClientOutputs build() {
            return new AuthenticationExtensionsClientOutputs(this.f8396a, null, this.b, null);
        }

        @NonNull
        public Builder setCredProps(@Nullable AuthenticationExtensionsCredPropsOutputs authenticationExtensionsCredPropsOutputs) {
            this.b = authenticationExtensionsCredPropsOutputs;
            return this;
        }

        @NonNull
        public Builder setUserVerificationMethodEntries(@Nullable UvmEntries uvmEntries) {
            this.f8396a = uvmEntries;
            return this;
        }
    }

    @SafeParcelable.Constructor
    public AuthenticationExtensionsClientOutputs(@Nullable @SafeParcelable.Param(id = 1) UvmEntries uvmEntries, @Nullable @SafeParcelable.Param(id = 2) zzf zzfVar, @Nullable @SafeParcelable.Param(id = 3) AuthenticationExtensionsCredPropsOutputs authenticationExtensionsCredPropsOutputs, @Nullable @SafeParcelable.Param(id = 4) zzh zzhVar) {
        this.h = uvmEntries;
        this.i = zzfVar;
        this.j = authenticationExtensionsCredPropsOutputs;
        this.k = zzhVar;
    }

    @NonNull
    public static AuthenticationExtensionsClientOutputs deserializeFromBytes(@NonNull byte[] bArr) {
        return (AuthenticationExtensionsClientOutputs) SafeParcelableSerializer.deserializeFromBytes(bArr, CREATOR);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof AuthenticationExtensionsClientOutputs) {
            AuthenticationExtensionsClientOutputs authenticationExtensionsClientOutputs = (AuthenticationExtensionsClientOutputs) obj;
            return Objects.equal(this.h, authenticationExtensionsClientOutputs.h) && Objects.equal(this.i, authenticationExtensionsClientOutputs.i) && Objects.equal(this.j, authenticationExtensionsClientOutputs.j) && Objects.equal(this.k, authenticationExtensionsClientOutputs.k);
        }
        return false;
    }

    @Nullable
    public AuthenticationExtensionsCredPropsOutputs getCredProps() {
        return this.j;
    }

    @Nullable
    public UvmEntries getUvmEntries() {
        return this.h;
    }

    public int hashCode() {
        return Objects.hashCode(this.h, this.i, this.j, this.k);
    }

    @NonNull
    public byte[] serializeToBytes() {
        return SafeParcelableSerializer.serializeToBytes(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getUvmEntries(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.i, i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, getCredProps(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.k, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

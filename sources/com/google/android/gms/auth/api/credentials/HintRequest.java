package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.auth.api.credentials.CredentialPickerConfig;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "HintRequestCreator")
@Deprecated
/* loaded from: classes6.dex */
public final class HintRequest extends AbstractSafeParcelable implements ReflectedParcelable {
    @NonNull
    public static final Parcelable.Creator<HintRequest> CREATOR = new zbe();
    @SafeParcelable.Field(id = 1000)
    public final int h;
    @SafeParcelable.Field(getter = "getHintPickerConfig", id = 1)
    public final CredentialPickerConfig i;
    @SafeParcelable.Field(getter = "isEmailAddressIdentifierSupported", id = 2)
    public final boolean j;
    @SafeParcelable.Field(getter = "isPhoneNumberIdentifierSupported", id = 3)
    public final boolean k;
    @SafeParcelable.Field(getter = "getAccountTypes", id = 4)
    public final String[] l;
    @SafeParcelable.Field(getter = "isIdTokenRequested", id = 5)
    public final boolean m;
    @Nullable
    @SafeParcelable.Field(getter = "getServerClientId", id = 6)
    public final String n;
    @Nullable
    @SafeParcelable.Field(getter = "getIdTokenNonce", id = 7)
    public final String o;

    /* loaded from: classes6.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f8197a;
        public boolean b;
        public String[] c;
        public CredentialPickerConfig d = new CredentialPickerConfig.Builder().build();
        public boolean e = false;
        @Nullable
        public String f;
        @Nullable
        public String g;

        @NonNull
        public HintRequest build() {
            if (this.c == null) {
                this.c = new String[0];
            }
            if (!this.f8197a && !this.b && this.c.length == 0) {
                throw new IllegalStateException("At least one authentication method must be specified");
            }
            return new HintRequest(2, this.d, this.f8197a, this.b, this.c, this.e, this.f, this.g);
        }

        @NonNull
        public Builder setAccountTypes(@NonNull String... strArr) {
            if (strArr == null) {
                strArr = new String[0];
            }
            this.c = strArr;
            return this;
        }

        @NonNull
        public Builder setEmailAddressIdentifierSupported(boolean z) {
            this.f8197a = z;
            return this;
        }

        @NonNull
        public Builder setHintPickerConfig(@NonNull CredentialPickerConfig credentialPickerConfig) {
            this.d = (CredentialPickerConfig) Preconditions.checkNotNull(credentialPickerConfig);
            return this;
        }

        @NonNull
        public Builder setIdTokenNonce(@Nullable String str) {
            this.g = str;
            return this;
        }

        @NonNull
        public Builder setIdTokenRequested(boolean z) {
            this.e = z;
            return this;
        }

        @NonNull
        public Builder setPhoneNumberIdentifierSupported(boolean z) {
            this.b = z;
            return this;
        }

        @NonNull
        public Builder setServerClientId(@Nullable String str) {
            this.f = str;
            return this;
        }
    }

    @SafeParcelable.Constructor
    public HintRequest(@SafeParcelable.Param(id = 1000) int i, @SafeParcelable.Param(id = 1) CredentialPickerConfig credentialPickerConfig, @SafeParcelable.Param(id = 2) boolean z, @SafeParcelable.Param(id = 3) boolean z2, @SafeParcelable.Param(id = 4) String[] strArr, @SafeParcelable.Param(id = 5) boolean z3, @Nullable @SafeParcelable.Param(id = 6) String str, @Nullable @SafeParcelable.Param(id = 7) String str2) {
        this.h = i;
        this.i = (CredentialPickerConfig) Preconditions.checkNotNull(credentialPickerConfig);
        this.j = z;
        this.k = z2;
        this.l = (String[]) Preconditions.checkNotNull(strArr);
        if (i < 2) {
            this.m = true;
            this.n = null;
            this.o = null;
            return;
        }
        this.m = z3;
        this.n = str;
        this.o = str2;
    }

    @NonNull
    public String[] getAccountTypes() {
        return this.l;
    }

    @NonNull
    public CredentialPickerConfig getHintPickerConfig() {
        return this.i;
    }

    @Nullable
    public String getIdTokenNonce() {
        return this.o;
    }

    @Nullable
    public String getServerClientId() {
        return this.n;
    }

    public boolean isEmailAddressIdentifierSupported() {
        return this.j;
    }

    public boolean isIdTokenRequested() {
        return this.m;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getHintPickerConfig(), i, false);
        SafeParcelWriter.writeBoolean(parcel, 2, isEmailAddressIdentifierSupported());
        SafeParcelWriter.writeBoolean(parcel, 3, this.k);
        SafeParcelWriter.writeStringArray(parcel, 4, getAccountTypes(), false);
        SafeParcelWriter.writeBoolean(parcel, 5, isIdTokenRequested());
        SafeParcelWriter.writeString(parcel, 6, getServerClientId(), false);
        SafeParcelWriter.writeString(parcel, 7, getIdTokenNonce(), false);
        SafeParcelWriter.writeInt(parcel, 1000, this.h);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

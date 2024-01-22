package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.auth.api.credentials.CredentialPickerConfig;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
@SafeParcelable.Class(creator = "CredentialRequestCreator")
@Deprecated
/* loaded from: classes6.dex */
public final class CredentialRequest extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<CredentialRequest> CREATOR = new zbc();
    @SafeParcelable.Field(id = 1000)
    public final int h;
    @SafeParcelable.Field(getter = "isPasswordLoginSupported", id = 1)
    public final boolean i;
    @SafeParcelable.Field(getter = "getAccountTypes", id = 2)
    public final String[] j;
    @SafeParcelable.Field(getter = "getCredentialPickerConfig", id = 3)
    public final CredentialPickerConfig k;
    @SafeParcelable.Field(getter = "getCredentialHintPickerConfig", id = 4)
    public final CredentialPickerConfig l;
    @SafeParcelable.Field(getter = "isIdTokenRequested", id = 5)
    public final boolean m;
    @Nullable
    @SafeParcelable.Field(getter = "getServerClientId", id = 6)
    public final String n;
    @Nullable
    @SafeParcelable.Field(getter = "getIdTokenNonce", id = 7)
    public final String o;
    @SafeParcelable.Field(getter = "getRequireUserMediation", id = 8)
    public final boolean p;

    /* loaded from: classes6.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f8196a;
        public String[] b;
        public CredentialPickerConfig c;
        public CredentialPickerConfig d;
        public boolean e = false;
        @Nullable
        public String f = null;
        @Nullable
        public String g;

        @NonNull
        public CredentialRequest build() {
            if (this.b == null) {
                this.b = new String[0];
            }
            if (!this.f8196a && this.b.length == 0) {
                throw new IllegalStateException("At least one authentication method must be specified");
            }
            return new CredentialRequest(4, this.f8196a, this.b, this.c, this.d, this.e, this.f, this.g, false);
        }

        @NonNull
        public Builder setAccountTypes(@NonNull String... strArr) {
            if (strArr == null) {
                strArr = new String[0];
            }
            this.b = strArr;
            return this;
        }

        @NonNull
        public Builder setCredentialHintPickerConfig(@NonNull CredentialPickerConfig credentialPickerConfig) {
            this.d = credentialPickerConfig;
            return this;
        }

        @NonNull
        public Builder setCredentialPickerConfig(@NonNull CredentialPickerConfig credentialPickerConfig) {
            this.c = credentialPickerConfig;
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
        public Builder setPasswordLoginSupported(boolean z) {
            this.f8196a = z;
            return this;
        }

        @NonNull
        public Builder setServerClientId(@Nullable String str) {
            this.f = str;
            return this;
        }

        @NonNull
        @Deprecated
        public Builder setSupportsPasswordLogin(boolean z) {
            setPasswordLoginSupported(z);
            return this;
        }
    }

    @SafeParcelable.Constructor
    public CredentialRequest(@SafeParcelable.Param(id = 1000) int i, @SafeParcelable.Param(id = 1) boolean z, @SafeParcelable.Param(id = 2) String[] strArr, @Nullable @SafeParcelable.Param(id = 3) CredentialPickerConfig credentialPickerConfig, @Nullable @SafeParcelable.Param(id = 4) CredentialPickerConfig credentialPickerConfig2, @SafeParcelable.Param(id = 5) boolean z2, @Nullable @SafeParcelable.Param(id = 6) String str, @Nullable @SafeParcelable.Param(id = 7) String str2, @SafeParcelable.Param(id = 8) boolean z3) {
        this.h = i;
        this.i = z;
        this.j = (String[]) Preconditions.checkNotNull(strArr);
        this.k = credentialPickerConfig == null ? new CredentialPickerConfig.Builder().build() : credentialPickerConfig;
        this.l = credentialPickerConfig2 == null ? new CredentialPickerConfig.Builder().build() : credentialPickerConfig2;
        if (i < 3) {
            this.m = true;
            this.n = null;
            this.o = null;
        } else {
            this.m = z2;
            this.n = str;
            this.o = str2;
        }
        this.p = z3;
    }

    @NonNull
    public String[] getAccountTypes() {
        return this.j;
    }

    @NonNull
    public Set<String> getAccountTypesSet() {
        return new HashSet(Arrays.asList(this.j));
    }

    @NonNull
    public CredentialPickerConfig getCredentialHintPickerConfig() {
        return this.l;
    }

    @NonNull
    public CredentialPickerConfig getCredentialPickerConfig() {
        return this.k;
    }

    @Nullable
    public String getIdTokenNonce() {
        return this.o;
    }

    @Nullable
    public String getServerClientId() {
        return this.n;
    }

    @Deprecated
    public boolean getSupportsPasswordLogin() {
        return isPasswordLoginSupported();
    }

    public boolean isIdTokenRequested() {
        return this.m;
    }

    public boolean isPasswordLoginSupported() {
        return this.i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, isPasswordLoginSupported());
        SafeParcelWriter.writeStringArray(parcel, 2, getAccountTypes(), false);
        SafeParcelWriter.writeParcelable(parcel, 3, getCredentialPickerConfig(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 4, getCredentialHintPickerConfig(), i, false);
        SafeParcelWriter.writeBoolean(parcel, 5, isIdTokenRequested());
        SafeParcelWriter.writeString(parcel, 6, getServerClientId(), false);
        SafeParcelWriter.writeString(parcel, 7, getIdTokenNonce(), false);
        SafeParcelWriter.writeBoolean(parcel, 8, this.p);
        SafeParcelWriter.writeInt(parcel, 1000, this.h);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

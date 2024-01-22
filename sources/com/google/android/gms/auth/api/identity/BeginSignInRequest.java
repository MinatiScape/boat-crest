package com.google.android.gms.auth.api.identity;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
@SafeParcelable.Class(creator = "BeginSignInRequestCreator")
/* loaded from: classes6.dex */
public final class BeginSignInRequest extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<BeginSignInRequest> CREATOR = new zba();
    @SafeParcelable.Field(getter = "getPasswordRequestOptions", id = 1)
    public final PasswordRequestOptions h;
    @SafeParcelable.Field(getter = "getGoogleIdTokenRequestOptions", id = 2)
    public final GoogleIdTokenRequestOptions i;
    @Nullable
    @SafeParcelable.Field(getter = "getSessionId", id = 3)
    public final String j;
    @SafeParcelable.Field(getter = "isAutoSelectEnabled", id = 4)
    public final boolean k;
    @SafeParcelable.Field(getter = "getTheme", id = 5)
    public final int l;
    @SafeParcelable.Field(getter = "getPasskeysRequestOptions", id = 6)
    public final PasskeysRequestOptions m;
    @SafeParcelable.Field(getter = "getPasskeyJsonRequestOptions", id = 7)
    public final PasskeyJsonRequestOptions n;

    /* loaded from: classes6.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public PasswordRequestOptions f8198a;
        public GoogleIdTokenRequestOptions b;
        public PasskeysRequestOptions c;
        public PasskeyJsonRequestOptions d;
        @Nullable
        public String e;
        public boolean f;
        public int g;

        public Builder() {
            PasswordRequestOptions.Builder builder = PasswordRequestOptions.builder();
            builder.setSupported(false);
            this.f8198a = builder.build();
            GoogleIdTokenRequestOptions.Builder builder2 = GoogleIdTokenRequestOptions.builder();
            builder2.setSupported(false);
            this.b = builder2.build();
            PasskeysRequestOptions.Builder builder3 = PasskeysRequestOptions.builder();
            builder3.setSupported(false);
            this.c = builder3.build();
            PasskeyJsonRequestOptions.Builder builder4 = PasskeyJsonRequestOptions.builder();
            builder4.setSupported(false);
            this.d = builder4.build();
        }

        @NonNull
        public BeginSignInRequest build() {
            return new BeginSignInRequest(this.f8198a, this.b, this.e, this.f, this.g, this.c, this.d);
        }

        @NonNull
        public Builder setAutoSelectEnabled(boolean z) {
            this.f = z;
            return this;
        }

        @NonNull
        public Builder setGoogleIdTokenRequestOptions(@NonNull GoogleIdTokenRequestOptions googleIdTokenRequestOptions) {
            this.b = (GoogleIdTokenRequestOptions) Preconditions.checkNotNull(googleIdTokenRequestOptions);
            return this;
        }

        @NonNull
        public Builder setPasskeyJsonSignInRequestOptions(@NonNull PasskeyJsonRequestOptions passkeyJsonRequestOptions) {
            this.d = (PasskeyJsonRequestOptions) Preconditions.checkNotNull(passkeyJsonRequestOptions);
            return this;
        }

        @NonNull
        @Deprecated
        public Builder setPasskeysSignInRequestOptions(@NonNull PasskeysRequestOptions passkeysRequestOptions) {
            this.c = (PasskeysRequestOptions) Preconditions.checkNotNull(passkeysRequestOptions);
            return this;
        }

        @NonNull
        public Builder setPasswordRequestOptions(@NonNull PasswordRequestOptions passwordRequestOptions) {
            this.f8198a = (PasswordRequestOptions) Preconditions.checkNotNull(passwordRequestOptions);
            return this;
        }

        @NonNull
        public final Builder zba(@NonNull String str) {
            this.e = str;
            return this;
        }

        @NonNull
        public final Builder zbb(int i) {
            this.g = i;
            return this;
        }
    }

    @SafeParcelable.Class(creator = "GoogleIdTokenRequestOptionsCreator")
    /* loaded from: classes6.dex */
    public static final class GoogleIdTokenRequestOptions extends AbstractSafeParcelable {
        @NonNull
        public static final Parcelable.Creator<GoogleIdTokenRequestOptions> CREATOR = new zbg();
        @SafeParcelable.Field(getter = "isSupported", id = 1)
        public final boolean h;
        @Nullable
        @SafeParcelable.Field(getter = "getServerClientId", id = 2)
        public final String i;
        @Nullable
        @SafeParcelable.Field(getter = "getNonce", id = 3)
        public final String j;
        @SafeParcelable.Field(getter = "filterByAuthorizedAccounts", id = 4)
        public final boolean k;
        @Nullable
        @SafeParcelable.Field(getter = "getLinkedServiceId", id = 5)
        public final String l;
        @Nullable
        @SafeParcelable.Field(getter = "getIdTokenDepositionScopes", id = 6)
        public final List m;
        @SafeParcelable.Field(getter = "requestVerifiedPhoneNumber", id = 7)
        public final boolean n;

        /* loaded from: classes6.dex */
        public static final class Builder {

            /* renamed from: a  reason: collision with root package name */
            public boolean f8199a = false;
            @Nullable
            public String b = null;
            @Nullable
            public String c = null;
            public boolean d = true;
            @Nullable
            public String e = null;
            @Nullable
            public List f = null;
            public boolean g = false;

            @NonNull
            public Builder associateLinkedAccounts(@NonNull String str, @Nullable List<String> list) {
                this.e = (String) Preconditions.checkNotNull(str, "linkedServiceId must be provided if you want to associate linked accounts.");
                this.f = list;
                return this;
            }

            @NonNull
            public GoogleIdTokenRequestOptions build() {
                return new GoogleIdTokenRequestOptions(this.f8199a, this.b, this.c, this.d, this.e, this.f, this.g);
            }

            @NonNull
            public Builder setFilterByAuthorizedAccounts(boolean z) {
                this.d = z;
                return this;
            }

            @NonNull
            public Builder setNonce(@Nullable String str) {
                this.c = str;
                return this;
            }

            @NonNull
            @Deprecated
            public Builder setRequestVerifiedPhoneNumber(boolean z) {
                this.g = z;
                return this;
            }

            @NonNull
            public Builder setServerClientId(@NonNull String str) {
                this.b = Preconditions.checkNotEmpty(str);
                return this;
            }

            @NonNull
            public Builder setSupported(boolean z) {
                this.f8199a = z;
                return this;
            }
        }

        @SafeParcelable.Constructor
        public GoogleIdTokenRequestOptions(@SafeParcelable.Param(id = 1) boolean z, @Nullable @SafeParcelable.Param(id = 2) String str, @Nullable @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) boolean z2, @Nullable @SafeParcelable.Param(id = 5) String str3, @Nullable @SafeParcelable.Param(id = 6) List list, @SafeParcelable.Param(id = 7) boolean z3) {
            boolean z4 = true;
            if (z2 && z3) {
                z4 = false;
            }
            Preconditions.checkArgument(z4, "filterByAuthorizedAccounts and requestVerifiedPhoneNumber must not both be true; the Verified Phone Number feature only works in sign-ups.");
            this.h = z;
            if (z) {
                Preconditions.checkNotNull(str, "serverClientId must be provided if Google ID tokens are requested");
            }
            this.i = str;
            this.j = str2;
            this.k = z2;
            Parcelable.Creator<BeginSignInRequest> creator = BeginSignInRequest.CREATOR;
            ArrayList arrayList = null;
            if (list != null && !list.isEmpty()) {
                arrayList = new ArrayList(list);
                Collections.sort(arrayList);
            }
            this.m = arrayList;
            this.l = str3;
            this.n = z3;
        }

        @NonNull
        public static Builder builder() {
            return new Builder();
        }

        public boolean equals(@Nullable Object obj) {
            if (obj instanceof GoogleIdTokenRequestOptions) {
                GoogleIdTokenRequestOptions googleIdTokenRequestOptions = (GoogleIdTokenRequestOptions) obj;
                return this.h == googleIdTokenRequestOptions.h && Objects.equal(this.i, googleIdTokenRequestOptions.i) && Objects.equal(this.j, googleIdTokenRequestOptions.j) && this.k == googleIdTokenRequestOptions.k && Objects.equal(this.l, googleIdTokenRequestOptions.l) && Objects.equal(this.m, googleIdTokenRequestOptions.m) && this.n == googleIdTokenRequestOptions.n;
            }
            return false;
        }

        public boolean filterByAuthorizedAccounts() {
            return this.k;
        }

        @Nullable
        public List<String> getIdTokenDepositionScopes() {
            return this.m;
        }

        @Nullable
        public String getLinkedServiceId() {
            return this.l;
        }

        @Nullable
        public String getNonce() {
            return this.j;
        }

        @Nullable
        public String getServerClientId() {
            return this.i;
        }

        public int hashCode() {
            return Objects.hashCode(Boolean.valueOf(this.h), this.i, this.j, Boolean.valueOf(this.k), this.l, this.m, Boolean.valueOf(this.n));
        }

        public boolean isSupported() {
            return this.h;
        }

        @Deprecated
        public boolean requestVerifiedPhoneNumber() {
            return this.n;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(@NonNull Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeBoolean(parcel, 1, isSupported());
            SafeParcelWriter.writeString(parcel, 2, getServerClientId(), false);
            SafeParcelWriter.writeString(parcel, 3, getNonce(), false);
            SafeParcelWriter.writeBoolean(parcel, 4, filterByAuthorizedAccounts());
            SafeParcelWriter.writeString(parcel, 5, getLinkedServiceId(), false);
            SafeParcelWriter.writeStringList(parcel, 6, getIdTokenDepositionScopes(), false);
            SafeParcelWriter.writeBoolean(parcel, 7, requestVerifiedPhoneNumber());
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    @SafeParcelable.Class(creator = "PasskeyJsonRequestOptionsCreator")
    /* loaded from: classes6.dex */
    public static final class PasskeyJsonRequestOptions extends AbstractSafeParcelable {
        @NonNull
        public static final Parcelable.Creator<PasskeyJsonRequestOptions> CREATOR = new zbh();
        @SafeParcelable.Field(getter = "isSupported", id = 1)
        public final boolean h;
        @SafeParcelable.Field(getter = "getRequestJson", id = 2)
        public final String i;

        /* loaded from: classes6.dex */
        public static final class Builder {

            /* renamed from: a  reason: collision with root package name */
            public boolean f8200a = false;
            public String b;

            @NonNull
            public PasskeyJsonRequestOptions build() {
                return new PasskeyJsonRequestOptions(this.f8200a, this.b);
            }

            @NonNull
            public Builder setRequestJson(@NonNull String str) {
                this.b = str;
                return this;
            }

            @NonNull
            public Builder setSupported(boolean z) {
                this.f8200a = z;
                return this;
            }
        }

        @SafeParcelable.Constructor
        public PasskeyJsonRequestOptions(@SafeParcelable.Param(id = 1) boolean z, @SafeParcelable.Param(id = 2) String str) {
            if (z) {
                Preconditions.checkNotNull(str);
            }
            this.h = z;
            this.i = str;
        }

        @NonNull
        public static Builder builder() {
            return new Builder();
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof PasskeyJsonRequestOptions) {
                PasskeyJsonRequestOptions passkeyJsonRequestOptions = (PasskeyJsonRequestOptions) obj;
                return this.h == passkeyJsonRequestOptions.h && Objects.equal(this.i, passkeyJsonRequestOptions.i);
            }
            return false;
        }

        @NonNull
        public String getRequestJson() {
            return this.i;
        }

        public int hashCode() {
            return Objects.hashCode(Boolean.valueOf(this.h), this.i);
        }

        public boolean isSupported() {
            return this.h;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(@NonNull Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeBoolean(parcel, 1, isSupported());
            SafeParcelWriter.writeString(parcel, 2, getRequestJson(), false);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    @SafeParcelable.Class(creator = "PasskeysRequestOptionsCreator")
    @Deprecated
    /* loaded from: classes6.dex */
    public static final class PasskeysRequestOptions extends AbstractSafeParcelable {
        @NonNull
        public static final Parcelable.Creator<PasskeysRequestOptions> CREATOR = new zbi();
        @SafeParcelable.Field(getter = "isSupported", id = 1)
        public final boolean h;
        @SafeParcelable.Field(getter = "getChallenge", id = 2)
        public final byte[] i;
        @SafeParcelable.Field(getter = "getRpId", id = 3)
        public final String j;

        /* loaded from: classes6.dex */
        public static final class Builder {

            /* renamed from: a  reason: collision with root package name */
            public boolean f8201a = false;
            public byte[] b;
            public String c;

            @NonNull
            public PasskeysRequestOptions build() {
                return new PasskeysRequestOptions(this.f8201a, this.b, this.c);
            }

            @NonNull
            public Builder setChallenge(@NonNull byte[] bArr) {
                this.b = bArr;
                return this;
            }

            @NonNull
            public Builder setRpId(@NonNull String str) {
                this.c = str;
                return this;
            }

            @NonNull
            public Builder setSupported(boolean z) {
                this.f8201a = z;
                return this;
            }
        }

        @SafeParcelable.Constructor
        public PasskeysRequestOptions(@SafeParcelable.Param(id = 1) boolean z, @SafeParcelable.Param(id = 2) byte[] bArr, @SafeParcelable.Param(id = 3) String str) {
            if (z) {
                Preconditions.checkNotNull(bArr);
                Preconditions.checkNotNull(str);
            }
            this.h = z;
            this.i = bArr;
            this.j = str;
        }

        @NonNull
        public static Builder builder() {
            return new Builder();
        }

        public boolean equals(@Nullable Object obj) {
            String str;
            String str2;
            if (this == obj) {
                return true;
            }
            if (obj instanceof PasskeysRequestOptions) {
                PasskeysRequestOptions passkeysRequestOptions = (PasskeysRequestOptions) obj;
                return this.h == passkeysRequestOptions.h && Arrays.equals(this.i, passkeysRequestOptions.i) && ((str = this.j) == (str2 = passkeysRequestOptions.j) || (str != null && str.equals(str2)));
            }
            return false;
        }

        @NonNull
        public byte[] getChallenge() {
            return this.i;
        }

        @NonNull
        public String getRpId() {
            return this.j;
        }

        public int hashCode() {
            return (Arrays.hashCode(new Object[]{Boolean.valueOf(this.h), this.j}) * 31) + Arrays.hashCode(this.i);
        }

        public boolean isSupported() {
            return this.h;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(@NonNull Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeBoolean(parcel, 1, isSupported());
            SafeParcelWriter.writeByteArray(parcel, 2, getChallenge(), false);
            SafeParcelWriter.writeString(parcel, 3, getRpId(), false);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    @SafeParcelable.Class(creator = "PasswordRequestOptionsCreator")
    /* loaded from: classes6.dex */
    public static final class PasswordRequestOptions extends AbstractSafeParcelable {
        @NonNull
        public static final Parcelable.Creator<PasswordRequestOptions> CREATOR = new zbj();
        @SafeParcelable.Field(getter = "isSupported", id = 1)
        public final boolean h;

        /* loaded from: classes6.dex */
        public static final class Builder {

            /* renamed from: a  reason: collision with root package name */
            public boolean f8202a = false;

            @NonNull
            public PasswordRequestOptions build() {
                return new PasswordRequestOptions(this.f8202a);
            }

            @NonNull
            public Builder setSupported(boolean z) {
                this.f8202a = z;
                return this;
            }
        }

        @SafeParcelable.Constructor
        public PasswordRequestOptions(@SafeParcelable.Param(id = 1) boolean z) {
            this.h = z;
        }

        @NonNull
        public static Builder builder() {
            return new Builder();
        }

        public boolean equals(@Nullable Object obj) {
            return (obj instanceof PasswordRequestOptions) && this.h == ((PasswordRequestOptions) obj).h;
        }

        public int hashCode() {
            return Objects.hashCode(Boolean.valueOf(this.h));
        }

        public boolean isSupported() {
            return this.h;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(@NonNull Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeBoolean(parcel, 1, isSupported());
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    @SafeParcelable.Constructor
    public BeginSignInRequest(@SafeParcelable.Param(id = 1) PasswordRequestOptions passwordRequestOptions, @SafeParcelable.Param(id = 2) GoogleIdTokenRequestOptions googleIdTokenRequestOptions, @Nullable @SafeParcelable.Param(id = 3) String str, @SafeParcelable.Param(id = 4) boolean z, @SafeParcelable.Param(id = 5) int i, @Nullable @SafeParcelable.Param(id = 6) PasskeysRequestOptions passkeysRequestOptions, @Nullable @SafeParcelable.Param(id = 7) PasskeyJsonRequestOptions passkeyJsonRequestOptions) {
        this.h = (PasswordRequestOptions) Preconditions.checkNotNull(passwordRequestOptions);
        this.i = (GoogleIdTokenRequestOptions) Preconditions.checkNotNull(googleIdTokenRequestOptions);
        this.j = str;
        this.k = z;
        this.l = i;
        if (passkeysRequestOptions == null) {
            PasskeysRequestOptions.Builder builder = PasskeysRequestOptions.builder();
            builder.setSupported(false);
            passkeysRequestOptions = builder.build();
        }
        this.m = passkeysRequestOptions;
        if (passkeyJsonRequestOptions == null) {
            PasskeyJsonRequestOptions.Builder builder2 = PasskeyJsonRequestOptions.builder();
            builder2.setSupported(false);
            passkeyJsonRequestOptions = builder2.build();
        }
        this.n = passkeyJsonRequestOptions;
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    @NonNull
    public static Builder zba(@NonNull BeginSignInRequest beginSignInRequest) {
        Preconditions.checkNotNull(beginSignInRequest);
        Builder builder = builder();
        builder.setGoogleIdTokenRequestOptions(beginSignInRequest.getGoogleIdTokenRequestOptions());
        builder.setPasswordRequestOptions(beginSignInRequest.getPasswordRequestOptions());
        builder.setPasskeysSignInRequestOptions(beginSignInRequest.getPasskeysRequestOptions());
        builder.setPasskeyJsonSignInRequestOptions(beginSignInRequest.getPasskeyJsonRequestOptions());
        builder.setAutoSelectEnabled(beginSignInRequest.k);
        builder.zbb(beginSignInRequest.l);
        String str = beginSignInRequest.j;
        if (str != null) {
            builder.zba(str);
        }
        return builder;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof BeginSignInRequest) {
            BeginSignInRequest beginSignInRequest = (BeginSignInRequest) obj;
            return Objects.equal(this.h, beginSignInRequest.h) && Objects.equal(this.i, beginSignInRequest.i) && Objects.equal(this.m, beginSignInRequest.m) && Objects.equal(this.n, beginSignInRequest.n) && Objects.equal(this.j, beginSignInRequest.j) && this.k == beginSignInRequest.k && this.l == beginSignInRequest.l;
        }
        return false;
    }

    @NonNull
    public GoogleIdTokenRequestOptions getGoogleIdTokenRequestOptions() {
        return this.i;
    }

    @NonNull
    public PasskeyJsonRequestOptions getPasskeyJsonRequestOptions() {
        return this.n;
    }

    @NonNull
    public PasskeysRequestOptions getPasskeysRequestOptions() {
        return this.m;
    }

    @NonNull
    public PasswordRequestOptions getPasswordRequestOptions() {
        return this.h;
    }

    public int hashCode() {
        return Objects.hashCode(this.h, this.i, this.m, this.n, this.j, Boolean.valueOf(this.k));
    }

    public boolean isAutoSelectEnabled() {
        return this.k;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getPasswordRequestOptions(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getGoogleIdTokenRequestOptions(), i, false);
        SafeParcelWriter.writeString(parcel, 3, this.j, false);
        SafeParcelWriter.writeBoolean(parcel, 4, isAutoSelectEnabled());
        SafeParcelWriter.writeInt(parcel, 5, this.l);
        SafeParcelWriter.writeParcelable(parcel, 6, getPasskeysRequestOptions(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 7, getPasskeyJsonRequestOptions(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

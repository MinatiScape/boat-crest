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
@SafeParcelable.Class(creator = "GetSignInIntentRequestCreator")
/* loaded from: classes6.dex */
public class GetSignInIntentRequest extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<GetSignInIntentRequest> CREATOR = new zbf();
    @SafeParcelable.Field(getter = "getServerClientId", id = 1)
    public final String h;
    @Nullable
    @SafeParcelable.Field(getter = "getHostedDomainFilter", id = 2)
    public final String i;
    @Nullable
    @SafeParcelable.Field(getter = "getSessionId", id = 3)
    public final String j;
    @Nullable
    @SafeParcelable.Field(getter = "getNonce", id = 4)
    public final String k;
    @SafeParcelable.Field(getter = "requestVerifiedPhoneNumber", id = 5)
    public final boolean l;
    @SafeParcelable.Field(getter = "getTheme", id = 6)
    public final int m;

    /* loaded from: classes6.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f8203a;
        @Nullable
        public String b;
        @Nullable
        public String c;
        @Nullable
        public String d;
        public boolean e;
        public int f;

        @NonNull
        public GetSignInIntentRequest build() {
            return new GetSignInIntentRequest(this.f8203a, this.b, this.c, this.d, this.e, this.f);
        }

        @NonNull
        public Builder filterByHostedDomain(@Nullable String str) {
            this.b = str;
            return this;
        }

        @NonNull
        public Builder setNonce(@Nullable String str) {
            this.d = str;
            return this;
        }

        @NonNull
        @Deprecated
        public Builder setRequestVerifiedPhoneNumber(boolean z) {
            this.e = z;
            return this;
        }

        @NonNull
        public Builder setServerClientId(@NonNull String str) {
            Preconditions.checkNotNull(str);
            this.f8203a = str;
            return this;
        }

        @NonNull
        public final Builder zba(@Nullable String str) {
            this.c = str;
            return this;
        }

        @NonNull
        public final Builder zbb(int i) {
            this.f = i;
            return this;
        }
    }

    @SafeParcelable.Constructor
    public GetSignInIntentRequest(@SafeParcelable.Param(id = 1) String str, @Nullable @SafeParcelable.Param(id = 2) String str2, @Nullable @SafeParcelable.Param(id = 3) String str3, @Nullable @SafeParcelable.Param(id = 4) String str4, @SafeParcelable.Param(id = 5) boolean z, @SafeParcelable.Param(id = 6) int i) {
        Preconditions.checkNotNull(str);
        this.h = str;
        this.i = str2;
        this.j = str3;
        this.k = str4;
        this.l = z;
        this.m = i;
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    @NonNull
    public static Builder zba(@NonNull GetSignInIntentRequest getSignInIntentRequest) {
        Preconditions.checkNotNull(getSignInIntentRequest);
        Builder builder = builder();
        builder.setServerClientId(getSignInIntentRequest.getServerClientId());
        builder.setNonce(getSignInIntentRequest.getNonce());
        builder.filterByHostedDomain(getSignInIntentRequest.getHostedDomainFilter());
        builder.setRequestVerifiedPhoneNumber(getSignInIntentRequest.l);
        builder.zbb(getSignInIntentRequest.m);
        String str = getSignInIntentRequest.j;
        if (str != null) {
            builder.zba(str);
        }
        return builder;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof GetSignInIntentRequest) {
            GetSignInIntentRequest getSignInIntentRequest = (GetSignInIntentRequest) obj;
            return Objects.equal(this.h, getSignInIntentRequest.h) && Objects.equal(this.k, getSignInIntentRequest.k) && Objects.equal(this.i, getSignInIntentRequest.i) && Objects.equal(Boolean.valueOf(this.l), Boolean.valueOf(getSignInIntentRequest.l)) && this.m == getSignInIntentRequest.m;
        }
        return false;
    }

    @Nullable
    public String getHostedDomainFilter() {
        return this.i;
    }

    @Nullable
    public String getNonce() {
        return this.k;
    }

    @NonNull
    public String getServerClientId() {
        return this.h;
    }

    public int hashCode() {
        return Objects.hashCode(this.h, this.i, this.k, Boolean.valueOf(this.l), Integer.valueOf(this.m));
    }

    @Deprecated
    public boolean requestVerifiedPhoneNumber() {
        return this.l;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getServerClientId(), false);
        SafeParcelWriter.writeString(parcel, 2, getHostedDomainFilter(), false);
        SafeParcelWriter.writeString(parcel, 3, this.j, false);
        SafeParcelWriter.writeString(parcel, 4, getNonce(), false);
        SafeParcelWriter.writeBoolean(parcel, 5, requestVerifiedPhoneNumber());
        SafeParcelWriter.writeInt(parcel, 6, this.m);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

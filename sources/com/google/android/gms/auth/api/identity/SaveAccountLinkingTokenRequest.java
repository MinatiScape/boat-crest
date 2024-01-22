package com.google.android.gms.auth.api.identity;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.List;
@SafeParcelable.Class(creator = "SaveAccountLinkingTokenRequestCreator")
/* loaded from: classes6.dex */
public class SaveAccountLinkingTokenRequest extends AbstractSafeParcelable implements ReflectedParcelable {
    @NonNull
    public static final Parcelable.Creator<SaveAccountLinkingTokenRequest> CREATOR = new zbk();
    @NonNull
    public static final String EXTRA_TOKEN = "extra_token";
    @NonNull
    public static final String TOKEN_TYPE_AUTH_CODE = "auth_code";
    @SafeParcelable.Field(getter = "getConsentPendingIntent", id = 1)
    public final PendingIntent h;
    @SafeParcelable.Field(getter = "getTokenType", id = 2)
    public final String i;
    @SafeParcelable.Field(getter = "getServiceId", id = 3)
    public final String j;
    @SafeParcelable.Field(getter = "getScopes", id = 4)
    public final List k;
    @Nullable
    @SafeParcelable.Field(getter = "getSessionId", id = 5)
    public final String l;
    @SafeParcelable.Field(getter = "getTheme", id = 6)
    public final int m;

    /* loaded from: classes6.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public PendingIntent f8204a;
        public String b;
        public String c;
        public List d = new ArrayList();
        public String e;
        public int f;

        @NonNull
        public SaveAccountLinkingTokenRequest build() {
            Preconditions.checkArgument(this.f8204a != null, "Consent PendingIntent cannot be null");
            Preconditions.checkArgument(SaveAccountLinkingTokenRequest.TOKEN_TYPE_AUTH_CODE.equals(this.b), "Invalid tokenType");
            Preconditions.checkArgument(!TextUtils.isEmpty(this.c), "serviceId cannot be null or empty");
            Preconditions.checkArgument(this.d != null, "scopes cannot be null");
            return new SaveAccountLinkingTokenRequest(this.f8204a, this.b, this.c, this.d, this.e, this.f);
        }

        @NonNull
        public Builder setConsentPendingIntent(@NonNull PendingIntent pendingIntent) {
            this.f8204a = pendingIntent;
            return this;
        }

        @NonNull
        public Builder setScopes(@NonNull List<String> list) {
            this.d = list;
            return this;
        }

        @NonNull
        public Builder setServiceId(@NonNull String str) {
            this.c = str;
            return this;
        }

        @NonNull
        public Builder setTokenType(@NonNull String str) {
            this.b = str;
            return this;
        }

        @NonNull
        public final Builder zba(@NonNull String str) {
            this.e = str;
            return this;
        }

        @NonNull
        public final Builder zbb(int i) {
            this.f = i;
            return this;
        }
    }

    @SafeParcelable.Constructor
    public SaveAccountLinkingTokenRequest(@SafeParcelable.Param(id = 1) PendingIntent pendingIntent, @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) List list, @Nullable @SafeParcelable.Param(id = 5) String str3, @SafeParcelable.Param(id = 6) int i) {
        this.h = pendingIntent;
        this.i = str;
        this.j = str2;
        this.k = list;
        this.l = str3;
        this.m = i;
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    @NonNull
    public static Builder zba(@NonNull SaveAccountLinkingTokenRequest saveAccountLinkingTokenRequest) {
        Preconditions.checkNotNull(saveAccountLinkingTokenRequest);
        Builder builder = builder();
        builder.setScopes(saveAccountLinkingTokenRequest.getScopes());
        builder.setServiceId(saveAccountLinkingTokenRequest.getServiceId());
        builder.setConsentPendingIntent(saveAccountLinkingTokenRequest.getConsentPendingIntent());
        builder.setTokenType(saveAccountLinkingTokenRequest.getTokenType());
        builder.zbb(saveAccountLinkingTokenRequest.m);
        String str = saveAccountLinkingTokenRequest.l;
        if (!TextUtils.isEmpty(str)) {
            builder.zba(str);
        }
        return builder;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof SaveAccountLinkingTokenRequest) {
            SaveAccountLinkingTokenRequest saveAccountLinkingTokenRequest = (SaveAccountLinkingTokenRequest) obj;
            return this.k.size() == saveAccountLinkingTokenRequest.k.size() && this.k.containsAll(saveAccountLinkingTokenRequest.k) && Objects.equal(this.h, saveAccountLinkingTokenRequest.h) && Objects.equal(this.i, saveAccountLinkingTokenRequest.i) && Objects.equal(this.j, saveAccountLinkingTokenRequest.j) && Objects.equal(this.l, saveAccountLinkingTokenRequest.l) && this.m == saveAccountLinkingTokenRequest.m;
        }
        return false;
    }

    @NonNull
    public PendingIntent getConsentPendingIntent() {
        return this.h;
    }

    @NonNull
    public List<String> getScopes() {
        return this.k;
    }

    @NonNull
    public String getServiceId() {
        return this.j;
    }

    @NonNull
    public String getTokenType() {
        return this.i;
    }

    public int hashCode() {
        return Objects.hashCode(this.h, this.i, this.j, this.k, this.l);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getConsentPendingIntent(), i, false);
        SafeParcelWriter.writeString(parcel, 2, getTokenType(), false);
        SafeParcelWriter.writeString(parcel, 3, getServiceId(), false);
        SafeParcelWriter.writeStringList(parcel, 4, getScopes(), false);
        SafeParcelWriter.writeString(parcel, 5, this.l, false);
        SafeParcelWriter.writeInt(parcel, 6, this.m);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

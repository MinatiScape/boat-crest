package com.google.android.gms.auth.api;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.internal.zbd;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.internal.p002authapi.zbl;
/* loaded from: classes6.dex */
public final class Auth {
    @NonNull
    public static final Api<AuthCredentialsOptions> CREDENTIALS_API;
    @NonNull
    public static final CredentialsApi CredentialsApi;
    @NonNull
    public static final Api<GoogleSignInOptions> GOOGLE_SIGN_IN_API;
    @NonNull
    public static final GoogleSignInApi GoogleSignInApi;
    @NonNull
    @Deprecated
    @ShowFirstParty
    @KeepForSdk
    public static final Api<AuthProxyOptions> PROXY_API;
    @NonNull
    @Deprecated
    @ShowFirstParty
    @KeepForSdk
    public static final ProxyApi ProxyApi;

    /* renamed from: a  reason: collision with root package name */
    public static final Api.AbstractClientBuilder f8189a;
    public static final Api.AbstractClientBuilder b;
    @NonNull
    public static final Api.ClientKey zba;
    @NonNull
    public static final Api.ClientKey zbb;

    @Deprecated
    /* loaded from: classes6.dex */
    public static class AuthCredentialsOptions implements Api.ApiOptions.Optional {
        @NonNull
        public static final AuthCredentialsOptions zba = new AuthCredentialsOptions(new Builder());
        public final String h = null;
        public final boolean i;
        @Nullable
        public final String j;

        @Deprecated
        /* loaded from: classes6.dex */
        public static class Builder {
            @NonNull
            public Boolean zba;
            @Nullable
            public String zbb;

            public Builder() {
                this.zba = Boolean.FALSE;
            }

            @NonNull
            public Builder forceEnableSaveDialog() {
                this.zba = Boolean.TRUE;
                return this;
            }

            @NonNull
            @ShowFirstParty
            public final Builder zba(@NonNull String str) {
                this.zbb = str;
                return this;
            }

            @ShowFirstParty
            public Builder(@NonNull AuthCredentialsOptions authCredentialsOptions) {
                this.zba = Boolean.FALSE;
                AuthCredentialsOptions.a(authCredentialsOptions);
                this.zba = Boolean.valueOf(authCredentialsOptions.i);
                this.zbb = authCredentialsOptions.j;
            }
        }

        public AuthCredentialsOptions(@NonNull Builder builder) {
            this.i = builder.zba.booleanValue();
            this.j = builder.zbb;
        }

        public static /* bridge */ /* synthetic */ String a(AuthCredentialsOptions authCredentialsOptions) {
            String str = authCredentialsOptions.h;
            return null;
        }

        public boolean equals(@Nullable Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof AuthCredentialsOptions) {
                AuthCredentialsOptions authCredentialsOptions = (AuthCredentialsOptions) obj;
                String str = authCredentialsOptions.h;
                return Objects.equal(null, null) && this.i == authCredentialsOptions.i && Objects.equal(this.j, authCredentialsOptions.j);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hashCode(null, Boolean.valueOf(this.i), this.j);
        }

        @NonNull
        public final Bundle zba() {
            Bundle bundle = new Bundle();
            bundle.putString("consumer_package", null);
            bundle.putBoolean("force_save_dialog", this.i);
            bundle.putString("log_session_id", this.j);
            return bundle;
        }

        @Nullable
        public final String zbd() {
            return this.j;
        }
    }

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        zba = clientKey;
        Api.ClientKey clientKey2 = new Api.ClientKey();
        zbb = clientKey2;
        a aVar = new a();
        f8189a = aVar;
        b bVar = new b();
        b = bVar;
        PROXY_API = AuthProxy.API;
        CREDENTIALS_API = new Api<>("Auth.CREDENTIALS_API", aVar, clientKey);
        GOOGLE_SIGN_IN_API = new Api<>("Auth.GOOGLE_SIGN_IN_API", bVar, clientKey2);
        ProxyApi = AuthProxy.ProxyApi;
        CredentialsApi = new zbl();
        GoogleSignInApi = new zbd();
    }
}

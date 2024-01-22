package com.google.firebase.installations.remote;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.installations.remote.TokenResult;
/* loaded from: classes10.dex */
public final class b extends TokenResult {

    /* renamed from: a  reason: collision with root package name */
    public final String f11322a;
    public final long b;
    public final TokenResult.ResponseCode c;

    /* renamed from: com.google.firebase.installations.remote.b$b  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public static final class C0544b extends TokenResult.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f11323a;
        public Long b;
        public TokenResult.ResponseCode c;

        @Override // com.google.firebase.installations.remote.TokenResult.Builder
        public TokenResult build() {
            String str = "";
            if (this.b == null) {
                str = " tokenExpirationTimestamp";
            }
            if (str.isEmpty()) {
                return new b(this.f11323a, this.b.longValue(), this.c);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.google.firebase.installations.remote.TokenResult.Builder
        public TokenResult.Builder setResponseCode(TokenResult.ResponseCode responseCode) {
            this.c = responseCode;
            return this;
        }

        @Override // com.google.firebase.installations.remote.TokenResult.Builder
        public TokenResult.Builder setToken(String str) {
            this.f11323a = str;
            return this;
        }

        @Override // com.google.firebase.installations.remote.TokenResult.Builder
        public TokenResult.Builder setTokenExpirationTimestamp(long j) {
            this.b = Long.valueOf(j);
            return this;
        }

        public C0544b() {
        }

        public C0544b(TokenResult tokenResult) {
            this.f11323a = tokenResult.getToken();
            this.b = Long.valueOf(tokenResult.getTokenExpirationTimestamp());
            this.c = tokenResult.getResponseCode();
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TokenResult) {
            TokenResult tokenResult = (TokenResult) obj;
            String str = this.f11322a;
            if (str != null ? str.equals(tokenResult.getToken()) : tokenResult.getToken() == null) {
                if (this.b == tokenResult.getTokenExpirationTimestamp()) {
                    TokenResult.ResponseCode responseCode = this.c;
                    if (responseCode == null) {
                        if (tokenResult.getResponseCode() == null) {
                            return true;
                        }
                    } else if (responseCode.equals(tokenResult.getResponseCode())) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    @Override // com.google.firebase.installations.remote.TokenResult
    @Nullable
    public TokenResult.ResponseCode getResponseCode() {
        return this.c;
    }

    @Override // com.google.firebase.installations.remote.TokenResult
    @Nullable
    public String getToken() {
        return this.f11322a;
    }

    @Override // com.google.firebase.installations.remote.TokenResult
    @NonNull
    public long getTokenExpirationTimestamp() {
        return this.b;
    }

    public int hashCode() {
        String str = this.f11322a;
        int hashCode = str == null ? 0 : str.hashCode();
        long j = this.b;
        int i = (((hashCode ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003;
        TokenResult.ResponseCode responseCode = this.c;
        return i ^ (responseCode != null ? responseCode.hashCode() : 0);
    }

    @Override // com.google.firebase.installations.remote.TokenResult
    public TokenResult.Builder toBuilder() {
        return new C0544b(this);
    }

    public String toString() {
        return "TokenResult{token=" + this.f11322a + ", tokenExpirationTimestamp=" + this.b + ", responseCode=" + this.c + "}";
    }

    public b(@Nullable String str, long j, @Nullable TokenResult.ResponseCode responseCode) {
        this.f11322a = str;
        this.b = j;
        this.c = responseCode;
    }
}

package com.google.firebase.installations;

import androidx.annotation.NonNull;
import com.google.firebase.installations.InstallationTokenResult;
import java.util.Objects;
/* loaded from: classes10.dex */
public final class a extends InstallationTokenResult {

    /* renamed from: a  reason: collision with root package name */
    public final String f11309a;
    public final long b;
    public final long c;

    /* loaded from: classes10.dex */
    public static final class b extends InstallationTokenResult.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f11310a;
        public Long b;
        public Long c;

        @Override // com.google.firebase.installations.InstallationTokenResult.Builder
        public InstallationTokenResult build() {
            String str = "";
            if (this.f11310a == null) {
                str = " token";
            }
            if (this.b == null) {
                str = str + " tokenExpirationTimestamp";
            }
            if (this.c == null) {
                str = str + " tokenCreationTimestamp";
            }
            if (str.isEmpty()) {
                return new a(this.f11310a, this.b.longValue(), this.c.longValue());
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.google.firebase.installations.InstallationTokenResult.Builder
        public InstallationTokenResult.Builder setToken(String str) {
            Objects.requireNonNull(str, "Null token");
            this.f11310a = str;
            return this;
        }

        @Override // com.google.firebase.installations.InstallationTokenResult.Builder
        public InstallationTokenResult.Builder setTokenCreationTimestamp(long j) {
            this.c = Long.valueOf(j);
            return this;
        }

        @Override // com.google.firebase.installations.InstallationTokenResult.Builder
        public InstallationTokenResult.Builder setTokenExpirationTimestamp(long j) {
            this.b = Long.valueOf(j);
            return this;
        }

        public b() {
        }

        public b(InstallationTokenResult installationTokenResult) {
            this.f11310a = installationTokenResult.getToken();
            this.b = Long.valueOf(installationTokenResult.getTokenExpirationTimestamp());
            this.c = Long.valueOf(installationTokenResult.getTokenCreationTimestamp());
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof InstallationTokenResult) {
            InstallationTokenResult installationTokenResult = (InstallationTokenResult) obj;
            return this.f11309a.equals(installationTokenResult.getToken()) && this.b == installationTokenResult.getTokenExpirationTimestamp() && this.c == installationTokenResult.getTokenCreationTimestamp();
        }
        return false;
    }

    @Override // com.google.firebase.installations.InstallationTokenResult
    @NonNull
    public String getToken() {
        return this.f11309a;
    }

    @Override // com.google.firebase.installations.InstallationTokenResult
    @NonNull
    public long getTokenCreationTimestamp() {
        return this.c;
    }

    @Override // com.google.firebase.installations.InstallationTokenResult
    @NonNull
    public long getTokenExpirationTimestamp() {
        return this.b;
    }

    public int hashCode() {
        long j = this.b;
        long j2 = this.c;
        return ((((this.f11309a.hashCode() ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)));
    }

    @Override // com.google.firebase.installations.InstallationTokenResult
    public InstallationTokenResult.Builder toBuilder() {
        return new b(this);
    }

    public String toString() {
        return "InstallationTokenResult{token=" + this.f11309a + ", tokenExpirationTimestamp=" + this.b + ", tokenCreationTimestamp=" + this.c + "}";
    }

    public a(String str, long j, long j2) {
        this.f11309a = str;
        this.b = j;
        this.c = j2;
    }
}

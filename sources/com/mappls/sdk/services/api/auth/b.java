package com.mappls.sdk.services.api.auth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.services.api.auth.MapplsAuthentication;
import java.util.Objects;
/* loaded from: classes11.dex */
public final class b extends MapplsAuthentication {

    /* renamed from: a  reason: collision with root package name */
    public final String f13154a;
    public final String b;
    public final String c;

    /* renamed from: com.mappls.sdk.services.api.auth.b$b  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static final class C0665b extends MapplsAuthentication.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13155a;
        public String b;
        public String c;

        @Override // com.mappls.sdk.services.api.auth.MapplsAuthentication.Builder
        public MapplsAuthentication autoBuild() {
            String str = "";
            if (this.f13155a == null) {
                str = " baseUrl";
            }
            if (this.b == null) {
                str = str + " grantType";
            }
            if (str.isEmpty()) {
                return new b(this.f13155a, this.b, this.c);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.auth.MapplsAuthentication.Builder
        public MapplsAuthentication.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f13155a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.auth.MapplsAuthentication.Builder
        public MapplsAuthentication.Builder grantType(String str) {
            Objects.requireNonNull(str, "Null grantType");
            this.b = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.auth.MapplsAuthentication.Builder
        public MapplsAuthentication.Builder refreshToken(@Nullable String str) {
            this.c = str;
            return this;
        }
    }

    @Override // com.mappls.sdk.services.api.auth.MapplsAuthentication, com.mappls.sdk.services.api.MapplsService
    @NonNull
    public String baseUrl() {
        return this.f13154a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsAuthentication) {
            MapplsAuthentication mapplsAuthentication = (MapplsAuthentication) obj;
            if (this.f13154a.equals(mapplsAuthentication.baseUrl()) && this.b.equals(mapplsAuthentication.grantType())) {
                String str = this.c;
                if (str == null) {
                    if (mapplsAuthentication.refreshToken() == null) {
                        return true;
                    }
                } else if (str.equals(mapplsAuthentication.refreshToken())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override // com.mappls.sdk.services.api.auth.MapplsAuthentication
    @NonNull
    public String grantType() {
        return this.b;
    }

    public int hashCode() {
        int hashCode = (((this.f13154a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003;
        String str = this.c;
        return hashCode ^ (str == null ? 0 : str.hashCode());
    }

    @Override // com.mappls.sdk.services.api.auth.MapplsAuthentication
    @Nullable
    public String refreshToken() {
        return this.c;
    }

    public String toString() {
        return "MapplsAuthentication{baseUrl=" + this.f13154a + ", grantType=" + this.b + ", refreshToken=" + this.c + "}";
    }

    public b(String str, String str2, @Nullable String str3) {
        this.f13154a = str;
        this.b = str2;
        this.c = str3;
    }
}

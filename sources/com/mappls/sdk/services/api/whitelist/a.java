package com.mappls.sdk.services.api.whitelist;

import androidx.annotation.NonNull;
import com.mappls.sdk.services.api.whitelist.MapplsWhitelist;
import java.util.Objects;
/* loaded from: classes8.dex */
public final class a extends MapplsWhitelist {

    /* renamed from: a  reason: collision with root package name */
    public final String f13304a;
    public final String b;
    public final String c;
    public final String d;

    /* loaded from: classes8.dex */
    public static final class b extends MapplsWhitelist.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13305a;
        public String b;
        public String c;
        public String d;

        @Override // com.mappls.sdk.services.api.whitelist.MapplsWhitelist.Builder
        public MapplsWhitelist.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f13305a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.whitelist.MapplsWhitelist.Builder
        public MapplsWhitelist build() {
            String str = "";
            if (this.f13305a == null) {
                str = " baseUrl";
            }
            if (this.b == null) {
                str = str + " userHandle";
            }
            if (this.c == null) {
                str = str + " refLocation";
            }
            if (this.d == null) {
                str = str + " otp";
            }
            if (str.isEmpty()) {
                return new a(this.f13305a, this.b, this.c, this.d);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.whitelist.MapplsWhitelist.Builder
        public MapplsWhitelist.Builder otp(String str) {
            Objects.requireNonNull(str, "Null otp");
            this.d = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.whitelist.MapplsWhitelist.Builder
        public MapplsWhitelist.Builder refLocation(String str) {
            Objects.requireNonNull(str, "Null refLocation");
            this.c = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.whitelist.MapplsWhitelist.Builder
        public MapplsWhitelist.Builder userHandle(String str) {
            Objects.requireNonNull(str, "Null userHandle");
            this.b = str;
            return this;
        }
    }

    @Override // com.mappls.sdk.services.api.whitelist.MapplsWhitelist, com.mappls.sdk.services.api.MapplsService
    @NonNull
    public String baseUrl() {
        return this.f13304a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsWhitelist) {
            MapplsWhitelist mapplsWhitelist = (MapplsWhitelist) obj;
            return this.f13304a.equals(mapplsWhitelist.baseUrl()) && this.b.equals(mapplsWhitelist.userHandle()) && this.c.equals(mapplsWhitelist.refLocation()) && this.d.equals(mapplsWhitelist.otp());
        }
        return false;
    }

    public int hashCode() {
        return ((((((this.f13304a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d.hashCode();
    }

    @Override // com.mappls.sdk.services.api.whitelist.MapplsWhitelist
    @NonNull
    public String otp() {
        return this.d;
    }

    @Override // com.mappls.sdk.services.api.whitelist.MapplsWhitelist
    @NonNull
    public String refLocation() {
        return this.c;
    }

    public String toString() {
        return "MapplsWhitelist{baseUrl=" + this.f13304a + ", userHandle=" + this.b + ", refLocation=" + this.c + ", otp=" + this.d + "}";
    }

    @Override // com.mappls.sdk.services.api.whitelist.MapplsWhitelist
    @NonNull
    public String userHandle() {
        return this.b;
    }

    public a(String str, String str2, String str3, String str4) {
        this.f13304a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
    }
}

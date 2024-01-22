package com.mappls.sdk.services.api.sdkconfig;

import androidx.annotation.NonNull;
import com.mappls.sdk.services.api.sdkconfig.MapplsSDKConfig;
import java.util.Objects;
/* loaded from: classes7.dex */
public final class a extends MapplsSDKConfig {

    /* renamed from: a  reason: collision with root package name */
    public final String f13266a;
    public final String b;

    /* loaded from: classes7.dex */
    public static final class b extends MapplsSDKConfig.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13267a;
        public String b;

        @Override // com.mappls.sdk.services.api.sdkconfig.MapplsSDKConfig.Builder
        public MapplsSDKConfig.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f13267a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.sdkconfig.MapplsSDKConfig.Builder
        public MapplsSDKConfig build() {
            String str = "";
            if (this.f13267a == null) {
                str = " baseUrl";
            }
            if (this.b == null) {
                str = str + " configUrl";
            }
            if (str.isEmpty()) {
                return new a(this.f13267a, this.b);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.sdkconfig.MapplsSDKConfig.Builder
        public MapplsSDKConfig.Builder configUrl(String str) {
            Objects.requireNonNull(str, "Null configUrl");
            this.b = str;
            return this;
        }
    }

    @Override // com.mappls.sdk.services.api.sdkconfig.MapplsSDKConfig, com.mappls.sdk.services.api.MapplsService
    @NonNull
    public String baseUrl() {
        return this.f13266a;
    }

    @Override // com.mappls.sdk.services.api.sdkconfig.MapplsSDKConfig
    @NonNull
    public String configUrl() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsSDKConfig) {
            MapplsSDKConfig mapplsSDKConfig = (MapplsSDKConfig) obj;
            return this.f13266a.equals(mapplsSDKConfig.baseUrl()) && this.b.equals(mapplsSDKConfig.configUrl());
        }
        return false;
    }

    public int hashCode() {
        return ((this.f13266a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode();
    }

    public String toString() {
        return "MapplsSDKConfig{baseUrl=" + this.f13266a + ", configUrl=" + this.b + "}";
    }

    public a(String str, String str2) {
        this.f13266a = str;
        this.b = str2;
    }
}

package com.mappls.sdk.services.api.whoami;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.services.api.whoami.MapplsLicensing;
import java.util.Objects;
/* loaded from: classes8.dex */
public final class a extends MapplsLicensing {

    /* renamed from: a  reason: collision with root package name */
    public final String f13306a;
    public final String b;

    /* loaded from: classes8.dex */
    public static final class b extends MapplsLicensing.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13307a;
        public String b;

        @Override // com.mappls.sdk.services.api.whoami.MapplsLicensing.Builder
        public MapplsLicensing a() {
            String str = "";
            if (this.f13307a == null) {
                str = " baseUrl";
            }
            if (str.isEmpty()) {
                return new a(this.f13307a, this.b);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        public MapplsLicensing.Builder b(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f13307a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.whoami.MapplsLicensing.Builder
        public MapplsLicensing.Builder deviceId(@Nullable String str) {
            this.b = str;
            return this;
        }
    }

    @Override // com.mappls.sdk.services.api.whoami.MapplsLicensing, com.mappls.sdk.services.api.MapplsService
    @NonNull
    public String baseUrl() {
        return this.f13306a;
    }

    @Override // com.mappls.sdk.services.api.whoami.MapplsLicensing
    @Nullable
    public String deviceId() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsLicensing) {
            MapplsLicensing mapplsLicensing = (MapplsLicensing) obj;
            if (this.f13306a.equals(mapplsLicensing.baseUrl())) {
                String str = this.b;
                if (str == null) {
                    if (mapplsLicensing.deviceId() == null) {
                        return true;
                    }
                } else if (str.equals(mapplsLicensing.deviceId())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (this.f13306a.hashCode() ^ 1000003) * 1000003;
        String str = this.b;
        return hashCode ^ (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "MapplsLicensing{baseUrl=" + this.f13306a + ", deviceId=" + this.b + "}";
    }

    public a(String str, @Nullable String str2) {
        this.f13306a = str;
        this.b = str2;
    }
}

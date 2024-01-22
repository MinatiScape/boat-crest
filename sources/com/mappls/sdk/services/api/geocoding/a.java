package com.mappls.sdk.services.api.geocoding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.services.api.geocoding.MapplsGeoCoding;
import java.util.Objects;
/* loaded from: classes6.dex */
public final class a extends MapplsGeoCoding {

    /* renamed from: a  reason: collision with root package name */
    public final String f13239a;
    public final String b;
    public final Integer c;
    public final Integer d;
    public final String e;
    public final String f;
    public final String g;
    public final Boolean h;

    /* loaded from: classes6.dex */
    public static final class b extends MapplsGeoCoding.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13240a;
        public String b;
        public Integer c;
        public Integer d;
        public String e;
        public String f;
        public String g;
        public Boolean h;

        @Override // com.mappls.sdk.services.api.geocoding.MapplsGeoCoding.Builder
        public MapplsGeoCoding.Builder address(String str) {
            Objects.requireNonNull(str, "Null address");
            this.b = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.geocoding.MapplsGeoCoding.Builder
        public MapplsGeoCoding autoBuild() {
            String str = "";
            if (this.f13240a == null) {
                str = " baseUrl";
            }
            if (this.b == null) {
                str = str + " address";
            }
            if (str.isEmpty()) {
                return new a(this.f13240a, this.b, this.c, this.d, this.e, this.f, this.g, this.h);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.geocoding.MapplsGeoCoding.Builder
        public MapplsGeoCoding.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f13240a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.geocoding.MapplsGeoCoding.Builder
        public MapplsGeoCoding.Builder bias(@Nullable Integer num) {
            this.d = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.geocoding.MapplsGeoCoding.Builder
        public MapplsGeoCoding.Builder bound(@Nullable String str) {
            this.g = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.geocoding.MapplsGeoCoding.Builder
        public MapplsGeoCoding.Builder clientAppName(@Nullable String str) {
            this.e = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.geocoding.MapplsGeoCoding.Builder
        public MapplsGeoCoding.Builder itemCount(@Nullable Integer num) {
            this.c = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.geocoding.MapplsGeoCoding.Builder
        public MapplsGeoCoding.Builder podFilter(@Nullable String str) {
            this.f = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.geocoding.MapplsGeoCoding.Builder
        public MapplsGeoCoding.Builder scores(@Nullable Boolean bool) {
            this.h = bool;
            return this;
        }
    }

    @Override // com.mappls.sdk.services.api.geocoding.MapplsGeoCoding
    @NonNull
    public String address() {
        return this.b;
    }

    @Override // com.mappls.sdk.services.api.geocoding.MapplsGeoCoding, com.mappls.sdk.services.api.MapplsService
    @NonNull
    public String baseUrl() {
        return this.f13239a;
    }

    @Override // com.mappls.sdk.services.api.geocoding.MapplsGeoCoding
    @Nullable
    public Integer bias() {
        return this.d;
    }

    @Override // com.mappls.sdk.services.api.geocoding.MapplsGeoCoding
    @Nullable
    public String bound() {
        return this.g;
    }

    @Override // com.mappls.sdk.services.api.geocoding.MapplsGeoCoding
    @Nullable
    public String clientAppName() {
        return this.e;
    }

    public boolean equals(Object obj) {
        Integer num;
        Integer num2;
        String str;
        String str2;
        String str3;
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsGeoCoding) {
            MapplsGeoCoding mapplsGeoCoding = (MapplsGeoCoding) obj;
            if (this.f13239a.equals(mapplsGeoCoding.baseUrl()) && this.b.equals(mapplsGeoCoding.address()) && ((num = this.c) != null ? num.equals(mapplsGeoCoding.itemCount()) : mapplsGeoCoding.itemCount() == null) && ((num2 = this.d) != null ? num2.equals(mapplsGeoCoding.bias()) : mapplsGeoCoding.bias() == null) && ((str = this.e) != null ? str.equals(mapplsGeoCoding.clientAppName()) : mapplsGeoCoding.clientAppName() == null) && ((str2 = this.f) != null ? str2.equals(mapplsGeoCoding.podFilter()) : mapplsGeoCoding.podFilter() == null) && ((str3 = this.g) != null ? str3.equals(mapplsGeoCoding.bound()) : mapplsGeoCoding.bound() == null)) {
                Boolean bool = this.h;
                if (bool == null) {
                    if (mapplsGeoCoding.scores() == null) {
                        return true;
                    }
                } else if (bool.equals(mapplsGeoCoding.scores())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((this.f13239a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003;
        Integer num = this.c;
        int hashCode2 = (hashCode ^ (num == null ? 0 : num.hashCode())) * 1000003;
        Integer num2 = this.d;
        int hashCode3 = (hashCode2 ^ (num2 == null ? 0 : num2.hashCode())) * 1000003;
        String str = this.e;
        int hashCode4 = (hashCode3 ^ (str == null ? 0 : str.hashCode())) * 1000003;
        String str2 = this.f;
        int hashCode5 = (hashCode4 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.g;
        int hashCode6 = (hashCode5 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003;
        Boolean bool = this.h;
        return hashCode6 ^ (bool != null ? bool.hashCode() : 0);
    }

    @Override // com.mappls.sdk.services.api.geocoding.MapplsGeoCoding
    @Nullable
    public Integer itemCount() {
        return this.c;
    }

    @Override // com.mappls.sdk.services.api.geocoding.MapplsGeoCoding
    @Nullable
    public String podFilter() {
        return this.f;
    }

    @Override // com.mappls.sdk.services.api.geocoding.MapplsGeoCoding
    @Nullable
    public Boolean scores() {
        return this.h;
    }

    public String toString() {
        return "MapplsGeoCoding{baseUrl=" + this.f13239a + ", address=" + this.b + ", itemCount=" + this.c + ", bias=" + this.d + ", clientAppName=" + this.e + ", podFilter=" + this.f + ", bound=" + this.g + ", scores=" + this.h + "}";
    }

    public a(String str, String str2, @Nullable Integer num, @Nullable Integer num2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable Boolean bool) {
        this.f13239a = str;
        this.b = str2;
        this.c = num;
        this.d = num2;
        this.e = str3;
        this.f = str4;
        this.g = str5;
        this.h = bool;
    }
}

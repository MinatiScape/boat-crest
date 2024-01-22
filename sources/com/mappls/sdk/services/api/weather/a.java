package com.mappls.sdk.services.api.weather;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.services.api.weather.MapplsWeather;
import java.util.Objects;
/* loaded from: classes8.dex */
public final class a extends MapplsWeather {

    /* renamed from: a  reason: collision with root package name */
    public final String f13301a;
    public final Double b;
    public final Double c;
    public final String d;
    public final String e;
    public final String f;
    public final String g;
    public final Integer h;

    /* loaded from: classes8.dex */
    public static final class b extends MapplsWeather.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13302a;
        public Double b;
        public Double c;
        public String d;
        public String e;
        public String f;
        public String g;
        public Integer h;

        @Override // com.mappls.sdk.services.api.weather.MapplsWeather.Builder
        public MapplsWeather autoBuild() {
            String str = "";
            if (this.f13302a == null) {
                str = " baseUrl";
            }
            if (this.b == null) {
                str = str + " latitude";
            }
            if (this.c == null) {
                str = str + " longitude";
            }
            if (this.d == null) {
                str = str + " theme";
            }
            if (this.e == null) {
                str = str + " size";
            }
            if (str.isEmpty()) {
                return new a(this.f13302a, this.b, this.c, this.d, this.e, this.f, this.g, this.h);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.weather.MapplsWeather.Builder
        public MapplsWeather.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f13302a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.weather.MapplsWeather.Builder
        public MapplsWeather.Builder latitude(Double d) {
            Objects.requireNonNull(d, "Null latitude");
            this.b = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.weather.MapplsWeather.Builder
        public MapplsWeather.Builder longitude(Double d) {
            Objects.requireNonNull(d, "Null longitude");
            this.c = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.weather.MapplsWeather.Builder
        public MapplsWeather.Builder size(String str) {
            Objects.requireNonNull(str, "Null size");
            this.e = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.weather.MapplsWeather.Builder
        public MapplsWeather.Builder tempUnit(@Nullable String str) {
            this.f = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.weather.MapplsWeather.Builder
        public MapplsWeather.Builder theme(String str) {
            Objects.requireNonNull(str, "Null theme");
            this.d = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.weather.MapplsWeather.Builder
        public MapplsWeather.Builder unit(@Nullable Integer num) {
            this.h = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.weather.MapplsWeather.Builder
        public MapplsWeather.Builder unitType(@Nullable String str) {
            this.g = str;
            return this;
        }
    }

    @Override // com.mappls.sdk.services.api.weather.MapplsWeather, com.mappls.sdk.services.api.MapplsService
    @NonNull
    public String baseUrl() {
        return this.f13301a;
    }

    public boolean equals(Object obj) {
        String str;
        String str2;
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsWeather) {
            MapplsWeather mapplsWeather = (MapplsWeather) obj;
            if (this.f13301a.equals(mapplsWeather.baseUrl()) && this.b.equals(mapplsWeather.latitude()) && this.c.equals(mapplsWeather.longitude()) && this.d.equals(mapplsWeather.theme()) && this.e.equals(mapplsWeather.size()) && ((str = this.f) != null ? str.equals(mapplsWeather.tempUnit()) : mapplsWeather.tempUnit() == null) && ((str2 = this.g) != null ? str2.equals(mapplsWeather.unitType()) : mapplsWeather.unitType() == null)) {
                Integer num = this.h;
                if (num == null) {
                    if (mapplsWeather.unit() == null) {
                        return true;
                    }
                } else if (num.equals(mapplsWeather.unit())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((((((((this.f13301a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d.hashCode()) * 1000003) ^ this.e.hashCode()) * 1000003;
        String str = this.f;
        int hashCode2 = (hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003;
        String str2 = this.g;
        int hashCode3 = (hashCode2 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        Integer num = this.h;
        return hashCode3 ^ (num != null ? num.hashCode() : 0);
    }

    @Override // com.mappls.sdk.services.api.weather.MapplsWeather
    @NonNull
    public Double latitude() {
        return this.b;
    }

    @Override // com.mappls.sdk.services.api.weather.MapplsWeather
    @NonNull
    public Double longitude() {
        return this.c;
    }

    @Override // com.mappls.sdk.services.api.weather.MapplsWeather
    @NonNull
    public String size() {
        return this.e;
    }

    @Override // com.mappls.sdk.services.api.weather.MapplsWeather
    @Nullable
    public String tempUnit() {
        return this.f;
    }

    @Override // com.mappls.sdk.services.api.weather.MapplsWeather
    @NonNull
    public String theme() {
        return this.d;
    }

    public String toString() {
        return "MapplsWeather{baseUrl=" + this.f13301a + ", latitude=" + this.b + ", longitude=" + this.c + ", theme=" + this.d + ", size=" + this.e + ", tempUnit=" + this.f + ", unitType=" + this.g + ", unit=" + this.h + "}";
    }

    @Override // com.mappls.sdk.services.api.weather.MapplsWeather
    @Nullable
    public Integer unit() {
        return this.h;
    }

    @Override // com.mappls.sdk.services.api.weather.MapplsWeather
    @Nullable
    public String unitType() {
        return this.g;
    }

    public a(String str, Double d, Double d2, String str2, String str3, @Nullable String str4, @Nullable String str5, @Nullable Integer num) {
        this.f13301a = str;
        this.b = d;
        this.c = d2;
        this.d = str2;
        this.e = str3;
        this.f = str4;
        this.g = str5;
        this.h = num;
    }
}

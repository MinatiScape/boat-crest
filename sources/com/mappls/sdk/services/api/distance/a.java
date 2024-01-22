package com.mappls.sdk.services.api.distance;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.services.api.distance.MapplsDistanceMatrix;
import java.util.Objects;
/* loaded from: classes11.dex */
public final class a extends MapplsDistanceMatrix {

    /* renamed from: a  reason: collision with root package name */
    public final String f13210a;
    public final String b;
    public final String c;
    public final Integer d;
    public final String e;
    public final String f;
    public final Double g;
    public final String h;
    public final String i;

    /* loaded from: classes11.dex */
    public static final class b extends MapplsDistanceMatrix.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13211a;
        public String b;
        public String c;
        public Integer d;
        public String e;
        public String f;
        public Double g;
        public String h;
        public String i;

        @Override // com.mappls.sdk.services.api.distance.MapplsDistanceMatrix.Builder
        public MapplsDistanceMatrix autoBuild() {
            String str = "";
            if (this.f13211a == null) {
                str = " coordinates";
            }
            if (this.b == null) {
                str = str + " profile";
            }
            if (this.c == null) {
                str = str + " resource";
            }
            if (this.i == null) {
                str = str + " baseUrl";
            }
            if (str.isEmpty()) {
                return new a(this.f13211a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.distance.MapplsDistanceMatrix.Builder
        public MapplsDistanceMatrix.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.i = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.distance.MapplsDistanceMatrix.Builder
        public MapplsDistanceMatrix.Builder coordinates(String str) {
            Objects.requireNonNull(str, "Null coordinates");
            this.f13211a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.distance.MapplsDistanceMatrix.Builder
        public MapplsDistanceMatrix.Builder destinations(@Nullable String str) {
            this.f = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.distance.MapplsDistanceMatrix.Builder
        public MapplsDistanceMatrix.Builder fallbackCoordinate(@Nullable String str) {
            this.h = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.distance.MapplsDistanceMatrix.Builder
        public MapplsDistanceMatrix.Builder fallbackSpeed(@Nullable Double d) {
            this.g = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.distance.MapplsDistanceMatrix.Builder
        public MapplsDistanceMatrix.Builder profile(String str) {
            Objects.requireNonNull(str, "Null profile");
            this.b = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.distance.MapplsDistanceMatrix.Builder
        public MapplsDistanceMatrix.Builder resource(String str) {
            Objects.requireNonNull(str, "Null resource");
            this.c = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.distance.MapplsDistanceMatrix.Builder
        public MapplsDistanceMatrix.Builder routeType(@Nullable Integer num) {
            this.d = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.distance.MapplsDistanceMatrix.Builder
        public MapplsDistanceMatrix.Builder sources(@Nullable String str) {
            this.e = str;
            return this;
        }
    }

    @Override // com.mappls.sdk.services.api.distance.MapplsDistanceMatrix, com.mappls.sdk.services.api.MapplsService
    @NonNull
    public String baseUrl() {
        return this.i;
    }

    @Override // com.mappls.sdk.services.api.distance.MapplsDistanceMatrix
    @NonNull
    public String coordinates() {
        return this.f13210a;
    }

    @Override // com.mappls.sdk.services.api.distance.MapplsDistanceMatrix
    @Nullable
    public String destinations() {
        return this.f;
    }

    public boolean equals(Object obj) {
        Integer num;
        String str;
        String str2;
        Double d;
        String str3;
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsDistanceMatrix) {
            MapplsDistanceMatrix mapplsDistanceMatrix = (MapplsDistanceMatrix) obj;
            return this.f13210a.equals(mapplsDistanceMatrix.coordinates()) && this.b.equals(mapplsDistanceMatrix.profile()) && this.c.equals(mapplsDistanceMatrix.resource()) && ((num = this.d) != null ? num.equals(mapplsDistanceMatrix.routeType()) : mapplsDistanceMatrix.routeType() == null) && ((str = this.e) != null ? str.equals(mapplsDistanceMatrix.sources()) : mapplsDistanceMatrix.sources() == null) && ((str2 = this.f) != null ? str2.equals(mapplsDistanceMatrix.destinations()) : mapplsDistanceMatrix.destinations() == null) && ((d = this.g) != null ? d.equals(mapplsDistanceMatrix.fallbackSpeed()) : mapplsDistanceMatrix.fallbackSpeed() == null) && ((str3 = this.h) != null ? str3.equals(mapplsDistanceMatrix.fallbackCoordinate()) : mapplsDistanceMatrix.fallbackCoordinate() == null) && this.i.equals(mapplsDistanceMatrix.baseUrl());
        }
        return false;
    }

    @Override // com.mappls.sdk.services.api.distance.MapplsDistanceMatrix
    @Nullable
    public String fallbackCoordinate() {
        return this.h;
    }

    @Override // com.mappls.sdk.services.api.distance.MapplsDistanceMatrix
    @Nullable
    public Double fallbackSpeed() {
        return this.g;
    }

    public int hashCode() {
        int hashCode = (((((this.f13210a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode()) * 1000003;
        Integer num = this.d;
        int hashCode2 = (hashCode ^ (num == null ? 0 : num.hashCode())) * 1000003;
        String str = this.e;
        int hashCode3 = (hashCode2 ^ (str == null ? 0 : str.hashCode())) * 1000003;
        String str2 = this.f;
        int hashCode4 = (hashCode3 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        Double d = this.g;
        int hashCode5 = (hashCode4 ^ (d == null ? 0 : d.hashCode())) * 1000003;
        String str3 = this.h;
        return ((hashCode5 ^ (str3 != null ? str3.hashCode() : 0)) * 1000003) ^ this.i.hashCode();
    }

    @Override // com.mappls.sdk.services.api.distance.MapplsDistanceMatrix
    @NonNull
    public String profile() {
        return this.b;
    }

    @Override // com.mappls.sdk.services.api.distance.MapplsDistanceMatrix
    @NonNull
    public String resource() {
        return this.c;
    }

    @Override // com.mappls.sdk.services.api.distance.MapplsDistanceMatrix
    @Nullable
    public Integer routeType() {
        return this.d;
    }

    @Override // com.mappls.sdk.services.api.distance.MapplsDistanceMatrix
    @Nullable
    public String sources() {
        return this.e;
    }

    public String toString() {
        return "MapplsDistanceMatrix{coordinates=" + this.f13210a + ", profile=" + this.b + ", resource=" + this.c + ", routeType=" + this.d + ", sources=" + this.e + ", destinations=" + this.f + ", fallbackSpeed=" + this.g + ", fallbackCoordinate=" + this.h + ", baseUrl=" + this.i + "}";
    }

    public a(String str, String str2, String str3, @Nullable Integer num, @Nullable String str4, @Nullable String str5, @Nullable Double d, @Nullable String str6, String str7) {
        this.f13210a = str;
        this.b = str2;
        this.c = str3;
        this.d = num;
        this.e = str4;
        this.f = str5;
        this.g = d;
        this.h = str6;
        this.i = str7;
    }
}

package com.mappls.sdk.services.api.costestimation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.services.api.costestimation.MapplsCostEstimation;
import java.util.Objects;
/* loaded from: classes11.dex */
public final class a extends MapplsCostEstimation {

    /* renamed from: a  reason: collision with root package name */
    public final String f13160a;
    public final String b;
    public final Integer c;
    public final String d;
    public final Boolean e;
    public final String f;
    public final Integer g;
    public final String h;
    public final Double i;
    public final Double j;
    public final Double k;
    public final Double l;

    /* loaded from: classes11.dex */
    public static final class b extends MapplsCostEstimation.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13161a;
        public String b;
        public Integer c;
        public String d;
        public Boolean e;
        public String f;
        public Integer g;
        public String h;
        public Double i;
        public Double j;
        public Double k;
        public Double l;

        @Override // com.mappls.sdk.services.api.costestimation.MapplsCostEstimation.Builder
        public MapplsCostEstimation autoBuild() {
            String str = "";
            if (this.f13161a == null) {
                str = " baseUrl";
            }
            if (str.isEmpty()) {
                return new a(this.f13161a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.costestimation.MapplsCostEstimation.Builder
        public MapplsCostEstimation.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f13161a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.costestimation.MapplsCostEstimation.Builder
        public MapplsCostEstimation.Builder distance(@Nullable Double d) {
            this.j = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.costestimation.MapplsCostEstimation.Builder
        public MapplsCostEstimation.Builder fuelEfficiency(@Nullable Integer num) {
            this.g = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.costestimation.MapplsCostEstimation.Builder
        public MapplsCostEstimation.Builder fuelEfficiencyUnit(@Nullable String str) {
            this.h = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.costestimation.MapplsCostEstimation.Builder
        public MapplsCostEstimation.Builder fuelPrice(@Nullable Double d) {
            this.i = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.costestimation.MapplsCostEstimation.Builder
        public MapplsCostEstimation.Builder isTollEnabled(@Nullable Boolean bool) {
            this.e = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.costestimation.MapplsCostEstimation.Builder
        public MapplsCostEstimation.Builder latitude(@Nullable Double d) {
            this.k = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.costestimation.MapplsCostEstimation.Builder
        public MapplsCostEstimation.Builder longitude(@Nullable Double d) {
            this.l = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.costestimation.MapplsCostEstimation.Builder
        public MapplsCostEstimation.Builder routeId(@Nullable String str) {
            this.b = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.costestimation.MapplsCostEstimation.Builder
        public MapplsCostEstimation.Builder routeIndex(@Nullable Integer num) {
            this.c = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.costestimation.MapplsCostEstimation.Builder
        public MapplsCostEstimation.Builder vehicleFuelType(@Nullable String str) {
            this.f = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.costestimation.MapplsCostEstimation.Builder
        public MapplsCostEstimation.Builder vehicleType(@Nullable String str) {
            this.d = str;
            return this;
        }
    }

    @Override // com.mappls.sdk.services.api.costestimation.MapplsCostEstimation, com.mappls.sdk.services.api.MapplsService
    @NonNull
    public String baseUrl() {
        return this.f13160a;
    }

    @Override // com.mappls.sdk.services.api.costestimation.MapplsCostEstimation
    @Nullable
    public Double distance() {
        return this.j;
    }

    public boolean equals(Object obj) {
        String str;
        Integer num;
        String str2;
        Boolean bool;
        String str3;
        Integer num2;
        String str4;
        Double d;
        Double d2;
        Double d3;
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsCostEstimation) {
            MapplsCostEstimation mapplsCostEstimation = (MapplsCostEstimation) obj;
            if (this.f13160a.equals(mapplsCostEstimation.baseUrl()) && ((str = this.b) != null ? str.equals(mapplsCostEstimation.routeId()) : mapplsCostEstimation.routeId() == null) && ((num = this.c) != null ? num.equals(mapplsCostEstimation.routeIndex()) : mapplsCostEstimation.routeIndex() == null) && ((str2 = this.d) != null ? str2.equals(mapplsCostEstimation.vehicleType()) : mapplsCostEstimation.vehicleType() == null) && ((bool = this.e) != null ? bool.equals(mapplsCostEstimation.isTollEnabled()) : mapplsCostEstimation.isTollEnabled() == null) && ((str3 = this.f) != null ? str3.equals(mapplsCostEstimation.vehicleFuelType()) : mapplsCostEstimation.vehicleFuelType() == null) && ((num2 = this.g) != null ? num2.equals(mapplsCostEstimation.fuelEfficiency()) : mapplsCostEstimation.fuelEfficiency() == null) && ((str4 = this.h) != null ? str4.equals(mapplsCostEstimation.fuelEfficiencyUnit()) : mapplsCostEstimation.fuelEfficiencyUnit() == null) && ((d = this.i) != null ? d.equals(mapplsCostEstimation.fuelPrice()) : mapplsCostEstimation.fuelPrice() == null) && ((d2 = this.j) != null ? d2.equals(mapplsCostEstimation.distance()) : mapplsCostEstimation.distance() == null) && ((d3 = this.k) != null ? d3.equals(mapplsCostEstimation.latitude()) : mapplsCostEstimation.latitude() == null)) {
                Double d4 = this.l;
                if (d4 == null) {
                    if (mapplsCostEstimation.longitude() == null) {
                        return true;
                    }
                } else if (d4.equals(mapplsCostEstimation.longitude())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override // com.mappls.sdk.services.api.costestimation.MapplsCostEstimation
    @Nullable
    public Integer fuelEfficiency() {
        return this.g;
    }

    @Override // com.mappls.sdk.services.api.costestimation.MapplsCostEstimation
    @Nullable
    public String fuelEfficiencyUnit() {
        return this.h;
    }

    @Override // com.mappls.sdk.services.api.costestimation.MapplsCostEstimation
    @Nullable
    public Double fuelPrice() {
        return this.i;
    }

    public int hashCode() {
        int hashCode = (this.f13160a.hashCode() ^ 1000003) * 1000003;
        String str = this.b;
        int hashCode2 = (hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003;
        Integer num = this.c;
        int hashCode3 = (hashCode2 ^ (num == null ? 0 : num.hashCode())) * 1000003;
        String str2 = this.d;
        int hashCode4 = (hashCode3 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        Boolean bool = this.e;
        int hashCode5 = (hashCode4 ^ (bool == null ? 0 : bool.hashCode())) * 1000003;
        String str3 = this.f;
        int hashCode6 = (hashCode5 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003;
        Integer num2 = this.g;
        int hashCode7 = (hashCode6 ^ (num2 == null ? 0 : num2.hashCode())) * 1000003;
        String str4 = this.h;
        int hashCode8 = (hashCode7 ^ (str4 == null ? 0 : str4.hashCode())) * 1000003;
        Double d = this.i;
        int hashCode9 = (hashCode8 ^ (d == null ? 0 : d.hashCode())) * 1000003;
        Double d2 = this.j;
        int hashCode10 = (hashCode9 ^ (d2 == null ? 0 : d2.hashCode())) * 1000003;
        Double d3 = this.k;
        int hashCode11 = (hashCode10 ^ (d3 == null ? 0 : d3.hashCode())) * 1000003;
        Double d4 = this.l;
        return hashCode11 ^ (d4 != null ? d4.hashCode() : 0);
    }

    @Override // com.mappls.sdk.services.api.costestimation.MapplsCostEstimation
    @Nullable
    public Boolean isTollEnabled() {
        return this.e;
    }

    @Override // com.mappls.sdk.services.api.costestimation.MapplsCostEstimation
    @Nullable
    public Double latitude() {
        return this.k;
    }

    @Override // com.mappls.sdk.services.api.costestimation.MapplsCostEstimation
    @Nullable
    public Double longitude() {
        return this.l;
    }

    @Override // com.mappls.sdk.services.api.costestimation.MapplsCostEstimation
    @Nullable
    public String routeId() {
        return this.b;
    }

    @Override // com.mappls.sdk.services.api.costestimation.MapplsCostEstimation
    @Nullable
    public Integer routeIndex() {
        return this.c;
    }

    public String toString() {
        return "MapplsCostEstimation{baseUrl=" + this.f13160a + ", routeId=" + this.b + ", routeIndex=" + this.c + ", vehicleType=" + this.d + ", isTollEnabled=" + this.e + ", vehicleFuelType=" + this.f + ", fuelEfficiency=" + this.g + ", fuelEfficiencyUnit=" + this.h + ", fuelPrice=" + this.i + ", distance=" + this.j + ", latitude=" + this.k + ", longitude=" + this.l + "}";
    }

    @Override // com.mappls.sdk.services.api.costestimation.MapplsCostEstimation
    @Nullable
    public String vehicleFuelType() {
        return this.f;
    }

    @Override // com.mappls.sdk.services.api.costestimation.MapplsCostEstimation
    @Nullable
    public String vehicleType() {
        return this.d;
    }

    public a(String str, @Nullable String str2, @Nullable Integer num, @Nullable String str3, @Nullable Boolean bool, @Nullable String str4, @Nullable Integer num2, @Nullable String str5, @Nullable Double d, @Nullable Double d2, @Nullable Double d3, @Nullable Double d4) {
        this.f13160a = str;
        this.b = str2;
        this.c = num;
        this.d = str3;
        this.e = bool;
        this.f = str4;
        this.g = num2;
        this.h = str5;
        this.i = d;
        this.j = d2;
        this.k = d3;
        this.l = d4;
    }
}

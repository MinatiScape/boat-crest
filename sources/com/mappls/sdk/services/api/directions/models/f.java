package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.Nullable;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import java.util.List;
/* loaded from: classes11.dex */
public abstract class f extends DirectionsRoute {
    private final List<DirectionsRoute> alternatives;
    private final String betterRouteId;
    private final Double distance;
    private final Double duration;
    private final String geometry;
    private final List<RouteLeg> legs;
    private final RouteClasses routeClasses;
    private final String routeId;
    private final Integer routeIndex;
    private final RouteOptions routeOptions;
    private final String summary;
    private final Double weight;
    private final String weightName;

    /* loaded from: classes11.dex */
    public static class b extends DirectionsRoute.Builder {

        /* renamed from: a  reason: collision with root package name */
        public Double f13191a;
        public Double b;
        public String c;
        public Double d;
        public String e;
        public List<RouteLeg> f;
        public RouteOptions g;
        public Integer h;
        public RouteClasses i;
        public String j;
        public List<DirectionsRoute> k;
        public String l;
        public String m;

        @Override // com.mappls.sdk.services.api.directions.models.DirectionsRoute.Builder
        public DirectionsRoute.Builder alternatives(@Nullable List<DirectionsRoute> list) {
            this.k = list;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.DirectionsRoute.Builder
        public DirectionsRoute.Builder betterRouteId(@Nullable String str) {
            this.l = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.DirectionsRoute.Builder
        public DirectionsRoute build() {
            return new AutoValue_DirectionsRoute(this.f13191a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m);
        }

        @Override // com.mappls.sdk.services.api.directions.models.DirectionsRoute.Builder
        public DirectionsRoute.Builder distance(@Nullable Double d) {
            this.f13191a = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.DirectionsRoute.Builder
        public DirectionsRoute.Builder duration(@Nullable Double d) {
            this.b = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.DirectionsRoute.Builder
        public DirectionsRoute.Builder geometry(@Nullable String str) {
            this.c = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.DirectionsRoute.Builder
        public DirectionsRoute.Builder legs(@Nullable List<RouteLeg> list) {
            this.f = list;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.DirectionsRoute.Builder
        public DirectionsRoute.Builder routeClasses(@Nullable RouteClasses routeClasses) {
            this.i = routeClasses;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.DirectionsRoute.Builder
        public DirectionsRoute.Builder routeId(@Nullable String str) {
            this.m = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.DirectionsRoute.Builder
        public DirectionsRoute.Builder routeIndex(Integer num) {
            this.h = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.DirectionsRoute.Builder
        public DirectionsRoute.Builder routeOptions(@Nullable RouteOptions routeOptions) {
            this.g = routeOptions;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.DirectionsRoute.Builder
        public DirectionsRoute.Builder summary(@Nullable String str) {
            this.j = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.DirectionsRoute.Builder
        public DirectionsRoute.Builder weight(@Nullable Double d) {
            this.d = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.DirectionsRoute.Builder
        public DirectionsRoute.Builder weightName(@Nullable String str) {
            this.e = str;
            return this;
        }

        public b() {
        }

        public b(DirectionsRoute directionsRoute) {
            this.f13191a = directionsRoute.distance();
            this.b = directionsRoute.duration();
            this.c = directionsRoute.geometry();
            this.d = directionsRoute.weight();
            this.e = directionsRoute.weightName();
            this.f = directionsRoute.legs();
            this.g = directionsRoute.routeOptions();
            this.h = directionsRoute.routeIndex();
            this.i = directionsRoute.routeClasses();
            this.j = directionsRoute.summary();
            this.k = directionsRoute.alternatives();
            this.l = directionsRoute.betterRouteId();
            this.m = directionsRoute.routeId();
        }
    }

    public f(@Nullable Double d, @Nullable Double d2, @Nullable String str, @Nullable Double d3, @Nullable String str2, @Nullable List<RouteLeg> list, @Nullable RouteOptions routeOptions, @Nullable Integer num, @Nullable RouteClasses routeClasses, @Nullable String str3, @Nullable List<DirectionsRoute> list2, @Nullable String str4, @Nullable String str5) {
        this.distance = d;
        this.duration = d2;
        this.geometry = str;
        this.weight = d3;
        this.weightName = str2;
        this.legs = list;
        this.routeOptions = routeOptions;
        this.routeIndex = num;
        this.routeClasses = routeClasses;
        this.summary = str3;
        this.alternatives = list2;
        this.betterRouteId = str4;
        this.routeId = str5;
    }

    @Override // com.mappls.sdk.services.api.directions.models.DirectionsRoute
    @Nullable
    public List<DirectionsRoute> alternatives() {
        return this.alternatives;
    }

    @Override // com.mappls.sdk.services.api.directions.models.DirectionsRoute
    @Nullable
    public String betterRouteId() {
        return this.betterRouteId;
    }

    @Override // com.mappls.sdk.services.api.directions.models.DirectionsRoute
    @Nullable
    public Double distance() {
        return this.distance;
    }

    @Override // com.mappls.sdk.services.api.directions.models.DirectionsRoute
    @Nullable
    public Double duration() {
        return this.duration;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DirectionsRoute) {
            DirectionsRoute directionsRoute = (DirectionsRoute) obj;
            Double d = this.distance;
            if (d != null ? d.equals(directionsRoute.distance()) : directionsRoute.distance() == null) {
                Double d2 = this.duration;
                if (d2 != null ? d2.equals(directionsRoute.duration()) : directionsRoute.duration() == null) {
                    String str = this.geometry;
                    if (str != null ? str.equals(directionsRoute.geometry()) : directionsRoute.geometry() == null) {
                        Double d3 = this.weight;
                        if (d3 != null ? d3.equals(directionsRoute.weight()) : directionsRoute.weight() == null) {
                            String str2 = this.weightName;
                            if (str2 != null ? str2.equals(directionsRoute.weightName()) : directionsRoute.weightName() == null) {
                                List<RouteLeg> list = this.legs;
                                if (list != null ? list.equals(directionsRoute.legs()) : directionsRoute.legs() == null) {
                                    RouteOptions routeOptions = this.routeOptions;
                                    if (routeOptions != null ? routeOptions.equals(directionsRoute.routeOptions()) : directionsRoute.routeOptions() == null) {
                                        Integer num = this.routeIndex;
                                        if (num != null ? num.equals(directionsRoute.routeIndex()) : directionsRoute.routeIndex() == null) {
                                            RouteClasses routeClasses = this.routeClasses;
                                            if (routeClasses != null ? routeClasses.equals(directionsRoute.routeClasses()) : directionsRoute.routeClasses() == null) {
                                                String str3 = this.summary;
                                                if (str3 != null ? str3.equals(directionsRoute.summary()) : directionsRoute.summary() == null) {
                                                    List<DirectionsRoute> list2 = this.alternatives;
                                                    if (list2 != null ? list2.equals(directionsRoute.alternatives()) : directionsRoute.alternatives() == null) {
                                                        String str4 = this.betterRouteId;
                                                        if (str4 != null ? str4.equals(directionsRoute.betterRouteId()) : directionsRoute.betterRouteId() == null) {
                                                            String str5 = this.routeId;
                                                            if (str5 == null) {
                                                                if (directionsRoute.routeId() == null) {
                                                                    return true;
                                                                }
                                                            } else if (str5.equals(directionsRoute.routeId())) {
                                                                return true;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }
        return false;
    }

    @Override // com.mappls.sdk.services.api.directions.models.DirectionsRoute
    @Nullable
    public String geometry() {
        return this.geometry;
    }

    public int hashCode() {
        Double d = this.distance;
        int hashCode = ((d == null ? 0 : d.hashCode()) ^ 1000003) * 1000003;
        Double d2 = this.duration;
        int hashCode2 = (hashCode ^ (d2 == null ? 0 : d2.hashCode())) * 1000003;
        String str = this.geometry;
        int hashCode3 = (hashCode2 ^ (str == null ? 0 : str.hashCode())) * 1000003;
        Double d3 = this.weight;
        int hashCode4 = (hashCode3 ^ (d3 == null ? 0 : d3.hashCode())) * 1000003;
        String str2 = this.weightName;
        int hashCode5 = (hashCode4 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        List<RouteLeg> list = this.legs;
        int hashCode6 = (hashCode5 ^ (list == null ? 0 : list.hashCode())) * 1000003;
        RouteOptions routeOptions = this.routeOptions;
        int hashCode7 = (hashCode6 ^ (routeOptions == null ? 0 : routeOptions.hashCode())) * 1000003;
        Integer num = this.routeIndex;
        int hashCode8 = (hashCode7 ^ (num == null ? 0 : num.hashCode())) * 1000003;
        RouteClasses routeClasses = this.routeClasses;
        int hashCode9 = (hashCode8 ^ (routeClasses == null ? 0 : routeClasses.hashCode())) * 1000003;
        String str3 = this.summary;
        int hashCode10 = (hashCode9 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003;
        List<DirectionsRoute> list2 = this.alternatives;
        int hashCode11 = (hashCode10 ^ (list2 == null ? 0 : list2.hashCode())) * 1000003;
        String str4 = this.betterRouteId;
        int hashCode12 = (hashCode11 ^ (str4 == null ? 0 : str4.hashCode())) * 1000003;
        String str5 = this.routeId;
        return hashCode12 ^ (str5 != null ? str5.hashCode() : 0);
    }

    @Override // com.mappls.sdk.services.api.directions.models.DirectionsRoute
    @Nullable
    public List<RouteLeg> legs() {
        return this.legs;
    }

    @Override // com.mappls.sdk.services.api.directions.models.DirectionsRoute
    @Nullable
    @SerializedName("contains_classes")
    public RouteClasses routeClasses() {
        return this.routeClasses;
    }

    @Override // com.mappls.sdk.services.api.directions.models.DirectionsRoute
    @Nullable
    public String routeId() {
        return this.routeId;
    }

    @Override // com.mappls.sdk.services.api.directions.models.DirectionsRoute
    @Nullable
    public Integer routeIndex() {
        return this.routeIndex;
    }

    @Override // com.mappls.sdk.services.api.directions.models.DirectionsRoute
    @Nullable
    public RouteOptions routeOptions() {
        return this.routeOptions;
    }

    @Override // com.mappls.sdk.services.api.directions.models.DirectionsRoute
    @Nullable
    public String summary() {
        return this.summary;
    }

    @Override // com.mappls.sdk.services.api.directions.models.DirectionsRoute
    public DirectionsRoute.Builder toBuilder() {
        return new b(this);
    }

    public String toString() {
        return "DirectionsRoute{distance=" + this.distance + ", duration=" + this.duration + ", geometry=" + this.geometry + ", weight=" + this.weight + ", weightName=" + this.weightName + ", legs=" + this.legs + ", routeOptions=" + this.routeOptions + ", routeIndex=" + this.routeIndex + ", routeClasses=" + this.routeClasses + ", summary=" + this.summary + ", alternatives=" + this.alternatives + ", betterRouteId=" + this.betterRouteId + ", routeId=" + this.routeId + "}";
    }

    @Override // com.mappls.sdk.services.api.directions.models.DirectionsRoute
    @Nullable
    public Double weight() {
        return this.weight;
    }

    @Override // com.mappls.sdk.services.api.directions.models.DirectionsRoute
    @Nullable
    @SerializedName("weight_name")
    public String weightName() {
        return this.weightName;
    }
}

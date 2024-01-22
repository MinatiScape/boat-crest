package com.mappls.sdk.services.api.predictive.directions;

import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections;
import java.util.List;
import java.util.Objects;
/* loaded from: classes7.dex */
public final class a extends MapplsPredictiveDirections {

    /* renamed from: a  reason: collision with root package name */
    public final String f13256a;
    public final List<String> b;
    public final String c;
    public final String d;
    public final String e;
    public final Integer f;
    public final Integer g;
    public final String h;
    public final Integer i;
    public final List<String> j;
    public final List<String> k;
    public final String l;
    public final Double m;
    public final Double n;
    public final Integer o;
    public final Boolean p;
    public final Boolean q;
    public final Boolean r;
    public final String s;
    public final String t;
    public final Double u;
    public final Double v;
    public final Double w;
    public final Double x;
    public final Double y;
    public final Boolean z;

    /* loaded from: classes7.dex */
    public static final class b extends MapplsPredictiveDirections.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13257a;
        public List<String> b;
        public String c;
        public String d;
        public String e;
        public Integer f;
        public Integer g;
        public String h;
        public Integer i;
        public List<String> j;
        public List<String> k;
        public String l;
        public Double m;
        public Double n;
        public Integer o;
        public Boolean p;
        public Boolean q;
        public Boolean r;
        public String s;
        public String t;
        public Double u;
        public Double v;
        public Double w;
        public Double x;
        public Double y;
        public Boolean z;

        @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections.Builder
        public MapplsPredictiveDirections.Builder alternatives(@Nullable Integer num) {
            this.o = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections.Builder
        public MapplsPredictiveDirections autoBuild() {
            String str = "";
            if (this.f13257a == null) {
                str = " baseUrl";
            }
            if (this.b == null) {
                str = str + " coordinates";
            }
            if (this.c == null) {
                str = str + " profile";
            }
            if (str.isEmpty()) {
                return new a(this.f13257a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.r, this.s, this.t, this.u, this.v, this.w, this.x, this.y, this.z);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections.Builder
        public MapplsPredictiveDirections.Builder avoidLocations(List<String> list) {
            this.j = list;
            return this;
        }

        @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections.Builder
        public MapplsPredictiveDirections.Builder avoidTolls(@Nullable Boolean bool) {
            this.r = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections.Builder
        public MapplsPredictiveDirections.Builder axleLoad(@Nullable Double d) {
            this.y = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections.Builder
        public MapplsPredictiveDirections.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f13257a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections.Builder
        public MapplsPredictiveDirections.Builder coordinates(List<String> list) {
            Objects.requireNonNull(list, "Null coordinates");
            this.b = list;
            return this;
        }

        @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections.Builder
        public MapplsPredictiveDirections.Builder excludeBridge(@Nullable Boolean bool) {
            this.q = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections.Builder
        public MapplsPredictiveDirections.Builder excludeTunnel(@Nullable Boolean bool) {
            this.p = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections.Builder
        public MapplsPredictiveDirections.Builder hazmat(@Nullable Boolean bool) {
            this.z = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections.Builder
        public MapplsPredictiveDirections.Builder heading(@Nullable Integer num) {
            this.f = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections.Builder
        public MapplsPredictiveDirections.Builder headingTolerance(@Nullable Integer num) {
            this.g = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections.Builder
        public MapplsPredictiveDirections.Builder height(@Nullable Double d) {
            this.u = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections.Builder
        public MapplsPredictiveDirections.Builder internalAvoidPolygons(List<String> list) {
            this.k = list;
            return this;
        }

        @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections.Builder
        public MapplsPredictiveDirections.Builder internalDateTime(@Nullable String str) {
            this.e = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections.Builder
        public MapplsPredictiveDirections.Builder internalSpeedType(@Nullable String str) {
            this.d = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections.Builder
        public MapplsPredictiveDirections.Builder length(@Nullable Double d) {
            this.w = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections.Builder
        public MapplsPredictiveDirections.Builder maxRouteClass(@Nullable String str) {
            this.t = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections.Builder
        public MapplsPredictiveDirections.Builder minRouteClass(@Nullable String str) {
            this.s = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections.Builder
        public MapplsPredictiveDirections.Builder preferredSide(@Nullable String str) {
            this.h = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections.Builder
        public MapplsPredictiveDirections.Builder profile(String str) {
            Objects.requireNonNull(str, "Null profile");
            this.c = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections.Builder
        public MapplsPredictiveDirections.Builder routeName(@Nullable String str) {
            this.l = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections.Builder
        public MapplsPredictiveDirections.Builder searchCutoff(@Nullable Integer num) {
            this.i = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections.Builder
        public MapplsPredictiveDirections.Builder useFerry(@Nullable Double d) {
            this.m = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections.Builder
        public MapplsPredictiveDirections.Builder useHighway(@Nullable Double d) {
            this.n = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections.Builder
        public MapplsPredictiveDirections.Builder weight(@Nullable Double d) {
            this.x = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections.Builder
        public MapplsPredictiveDirections.Builder width(@Nullable Double d) {
            this.v = d;
            return this;
        }
    }

    @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections
    @Nullable
    public Integer alternatives() {
        return this.o;
    }

    @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections
    @Nullable
    public List<String> avoidLocations() {
        return this.j;
    }

    @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections
    @Nullable
    public Boolean avoidTolls() {
        return this.r;
    }

    @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections
    @Nullable
    public Double axleLoad() {
        return this.y;
    }

    @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections, com.mappls.sdk.services.api.MapplsService
    @NonNull
    public String baseUrl() {
        return this.f13256a;
    }

    @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections
    @NonNull
    public List<String> coordinates() {
        return this.b;
    }

    public boolean equals(Object obj) {
        String str;
        String str2;
        Integer num;
        Integer num2;
        String str3;
        Integer num3;
        List<String> list;
        List<String> list2;
        String str4;
        Double d;
        Double d2;
        Integer num4;
        Boolean bool;
        Boolean bool2;
        Boolean bool3;
        String str5;
        String str6;
        Double d3;
        Double d4;
        Double d5;
        Double d6;
        Double d7;
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsPredictiveDirections) {
            MapplsPredictiveDirections mapplsPredictiveDirections = (MapplsPredictiveDirections) obj;
            if (this.f13256a.equals(mapplsPredictiveDirections.baseUrl()) && this.b.equals(mapplsPredictiveDirections.coordinates()) && this.c.equals(mapplsPredictiveDirections.profile()) && ((str = this.d) != null ? str.equals(mapplsPredictiveDirections.internalSpeedType()) : mapplsPredictiveDirections.internalSpeedType() == null) && ((str2 = this.e) != null ? str2.equals(mapplsPredictiveDirections.internalDateTime()) : mapplsPredictiveDirections.internalDateTime() == null) && ((num = this.f) != null ? num.equals(mapplsPredictiveDirections.heading()) : mapplsPredictiveDirections.heading() == null) && ((num2 = this.g) != null ? num2.equals(mapplsPredictiveDirections.headingTolerance()) : mapplsPredictiveDirections.headingTolerance() == null) && ((str3 = this.h) != null ? str3.equals(mapplsPredictiveDirections.preferredSide()) : mapplsPredictiveDirections.preferredSide() == null) && ((num3 = this.i) != null ? num3.equals(mapplsPredictiveDirections.searchCutoff()) : mapplsPredictiveDirections.searchCutoff() == null) && ((list = this.j) != null ? list.equals(mapplsPredictiveDirections.avoidLocations()) : mapplsPredictiveDirections.avoidLocations() == null) && ((list2 = this.k) != null ? list2.equals(mapplsPredictiveDirections.internalAvoidPolygons()) : mapplsPredictiveDirections.internalAvoidPolygons() == null) && ((str4 = this.l) != null ? str4.equals(mapplsPredictiveDirections.routeName()) : mapplsPredictiveDirections.routeName() == null) && ((d = this.m) != null ? d.equals(mapplsPredictiveDirections.useFerry()) : mapplsPredictiveDirections.useFerry() == null) && ((d2 = this.n) != null ? d2.equals(mapplsPredictiveDirections.useHighway()) : mapplsPredictiveDirections.useHighway() == null) && ((num4 = this.o) != null ? num4.equals(mapplsPredictiveDirections.alternatives()) : mapplsPredictiveDirections.alternatives() == null) && ((bool = this.p) != null ? bool.equals(mapplsPredictiveDirections.excludeTunnel()) : mapplsPredictiveDirections.excludeTunnel() == null) && ((bool2 = this.q) != null ? bool2.equals(mapplsPredictiveDirections.excludeBridge()) : mapplsPredictiveDirections.excludeBridge() == null) && ((bool3 = this.r) != null ? bool3.equals(mapplsPredictiveDirections.avoidTolls()) : mapplsPredictiveDirections.avoidTolls() == null) && ((str5 = this.s) != null ? str5.equals(mapplsPredictiveDirections.minRouteClass()) : mapplsPredictiveDirections.minRouteClass() == null) && ((str6 = this.t) != null ? str6.equals(mapplsPredictiveDirections.maxRouteClass()) : mapplsPredictiveDirections.maxRouteClass() == null) && ((d3 = this.u) != null ? d3.equals(mapplsPredictiveDirections.height()) : mapplsPredictiveDirections.height() == null) && ((d4 = this.v) != null ? d4.equals(mapplsPredictiveDirections.width()) : mapplsPredictiveDirections.width() == null) && ((d5 = this.w) != null ? d5.equals(mapplsPredictiveDirections.length()) : mapplsPredictiveDirections.length() == null) && ((d6 = this.x) != null ? d6.equals(mapplsPredictiveDirections.weight()) : mapplsPredictiveDirections.weight() == null) && ((d7 = this.y) != null ? d7.equals(mapplsPredictiveDirections.axleLoad()) : mapplsPredictiveDirections.axleLoad() == null)) {
                Boolean bool4 = this.z;
                if (bool4 == null) {
                    if (mapplsPredictiveDirections.hazmat() == null) {
                        return true;
                    }
                } else if (bool4.equals(mapplsPredictiveDirections.hazmat())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections
    @Nullable
    public Boolean excludeBridge() {
        return this.q;
    }

    @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections
    @Nullable
    public Boolean excludeTunnel() {
        return this.p;
    }

    public int hashCode() {
        int hashCode = (((((this.f13256a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode()) * 1000003;
        String str = this.d;
        int hashCode2 = (hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003;
        String str2 = this.e;
        int hashCode3 = (hashCode2 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        Integer num = this.f;
        int hashCode4 = (hashCode3 ^ (num == null ? 0 : num.hashCode())) * 1000003;
        Integer num2 = this.g;
        int hashCode5 = (hashCode4 ^ (num2 == null ? 0 : num2.hashCode())) * 1000003;
        String str3 = this.h;
        int hashCode6 = (hashCode5 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003;
        Integer num3 = this.i;
        int hashCode7 = (hashCode6 ^ (num3 == null ? 0 : num3.hashCode())) * 1000003;
        List<String> list = this.j;
        int hashCode8 = (hashCode7 ^ (list == null ? 0 : list.hashCode())) * 1000003;
        List<String> list2 = this.k;
        int hashCode9 = (hashCode8 ^ (list2 == null ? 0 : list2.hashCode())) * 1000003;
        String str4 = this.l;
        int hashCode10 = (hashCode9 ^ (str4 == null ? 0 : str4.hashCode())) * 1000003;
        Double d = this.m;
        int hashCode11 = (hashCode10 ^ (d == null ? 0 : d.hashCode())) * 1000003;
        Double d2 = this.n;
        int hashCode12 = (hashCode11 ^ (d2 == null ? 0 : d2.hashCode())) * 1000003;
        Integer num4 = this.o;
        int hashCode13 = (hashCode12 ^ (num4 == null ? 0 : num4.hashCode())) * 1000003;
        Boolean bool = this.p;
        int hashCode14 = (hashCode13 ^ (bool == null ? 0 : bool.hashCode())) * 1000003;
        Boolean bool2 = this.q;
        int hashCode15 = (hashCode14 ^ (bool2 == null ? 0 : bool2.hashCode())) * 1000003;
        Boolean bool3 = this.r;
        int hashCode16 = (hashCode15 ^ (bool3 == null ? 0 : bool3.hashCode())) * 1000003;
        String str5 = this.s;
        int hashCode17 = (hashCode16 ^ (str5 == null ? 0 : str5.hashCode())) * 1000003;
        String str6 = this.t;
        int hashCode18 = (hashCode17 ^ (str6 == null ? 0 : str6.hashCode())) * 1000003;
        Double d3 = this.u;
        int hashCode19 = (hashCode18 ^ (d3 == null ? 0 : d3.hashCode())) * 1000003;
        Double d4 = this.v;
        int hashCode20 = (hashCode19 ^ (d4 == null ? 0 : d4.hashCode())) * 1000003;
        Double d5 = this.w;
        int hashCode21 = (hashCode20 ^ (d5 == null ? 0 : d5.hashCode())) * 1000003;
        Double d6 = this.x;
        int hashCode22 = (hashCode21 ^ (d6 == null ? 0 : d6.hashCode())) * 1000003;
        Double d7 = this.y;
        int hashCode23 = (hashCode22 ^ (d7 == null ? 0 : d7.hashCode())) * 1000003;
        Boolean bool4 = this.z;
        return hashCode23 ^ (bool4 != null ? bool4.hashCode() : 0);
    }

    @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections
    @Nullable
    public Boolean hazmat() {
        return this.z;
    }

    @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections
    @Nullable
    public Integer heading() {
        return this.f;
    }

    @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections
    @Nullable
    public Integer headingTolerance() {
        return this.g;
    }

    @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections
    @Nullable
    public Double height() {
        return this.u;
    }

    @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections
    @Nullable
    public List<String> internalAvoidPolygons() {
        return this.k;
    }

    @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections
    @Nullable
    public String internalDateTime() {
        return this.e;
    }

    @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections
    @Nullable
    public String internalSpeedType() {
        return this.d;
    }

    @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections
    @Nullable
    public Double length() {
        return this.w;
    }

    @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections
    @Nullable
    public String maxRouteClass() {
        return this.t;
    }

    @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections
    @Nullable
    public String minRouteClass() {
        return this.s;
    }

    @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections
    @Nullable
    public String preferredSide() {
        return this.h;
    }

    @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections
    @NonNull
    public String profile() {
        return this.c;
    }

    @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections
    @Nullable
    public String routeName() {
        return this.l;
    }

    @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections
    @Nullable
    public Integer searchCutoff() {
        return this.i;
    }

    public String toString() {
        return "MapplsPredictiveDirections{baseUrl=" + this.f13256a + ", coordinates=" + this.b + ", profile=" + this.c + ", internalSpeedType=" + this.d + ", internalDateTime=" + this.e + ", heading=" + this.f + ", headingTolerance=" + this.g + ", preferredSide=" + this.h + ", searchCutoff=" + this.i + ", avoidLocations=" + this.j + ", internalAvoidPolygons=" + this.k + ", routeName=" + this.l + ", useFerry=" + this.m + ", useHighway=" + this.n + ", alternatives=" + this.o + ", excludeTunnel=" + this.p + ", excludeBridge=" + this.q + ", avoidTolls=" + this.r + ", minRouteClass=" + this.s + ", maxRouteClass=" + this.t + ", height=" + this.u + ", width=" + this.v + ", length=" + this.w + ", weight=" + this.x + ", axleLoad=" + this.y + ", hazmat=" + this.z + "}";
    }

    @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections
    @Nullable
    @FloatRange(from = 0.0d, to = 1.0d)
    public Double useFerry() {
        return this.m;
    }

    @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections
    @Nullable
    @FloatRange(from = 0.0d, to = 1.0d)
    public Double useHighway() {
        return this.n;
    }

    @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections
    @Nullable
    public Double weight() {
        return this.x;
    }

    @Override // com.mappls.sdk.services.api.predictive.directions.MapplsPredictiveDirections
    @Nullable
    public Double width() {
        return this.v;
    }

    public a(String str, List<String> list, String str2, @Nullable String str3, @Nullable String str4, @Nullable Integer num, @Nullable Integer num2, @Nullable String str5, @Nullable Integer num3, @Nullable List<String> list2, @Nullable List<String> list3, @Nullable String str6, @Nullable Double d, @Nullable Double d2, @Nullable Integer num4, @Nullable Boolean bool, @Nullable Boolean bool2, @Nullable Boolean bool3, @Nullable String str7, @Nullable String str8, @Nullable Double d3, @Nullable Double d4, @Nullable Double d5, @Nullable Double d6, @Nullable Double d7, @Nullable Boolean bool4) {
        this.f13256a = str;
        this.b = list;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.f = num;
        this.g = num2;
        this.h = str5;
        this.i = num3;
        this.j = list2;
        this.k = list3;
        this.l = str6;
        this.m = d;
        this.n = d2;
        this.o = num4;
        this.p = bool;
        this.q = bool2;
        this.r = bool3;
        this.s = str7;
        this.t = str8;
        this.u = d3;
        this.v = d4;
        this.w = d5;
        this.x = d6;
        this.y = d7;
        this.z = bool4;
    }
}

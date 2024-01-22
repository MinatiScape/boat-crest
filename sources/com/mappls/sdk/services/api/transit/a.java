package com.mappls.sdk.services.api.transit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.services.api.transit.MapplsTransitPlanner;
import java.util.Objects;
/* loaded from: classes8.dex */
public final class a extends MapplsTransitPlanner {

    /* renamed from: a  reason: collision with root package name */
    public final String f13291a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final Boolean g;
    public final Boolean h;
    public final Integer i;
    public final Integer j;
    public final Boolean k;
    public final Double l;

    /* loaded from: classes8.dex */
    public static final class b extends MapplsTransitPlanner.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13292a;
        public String b;
        public String c;
        public String d;
        public String e;
        public String f;
        public Boolean g;
        public Boolean h;
        public Integer i;
        public Integer j;
        public Boolean k;
        public Double l;

        @Override // com.mappls.sdk.services.api.transit.MapplsTransitPlanner.Builder
        public MapplsTransitPlanner.Builder arriveBy(@Nullable Boolean bool) {
            this.g = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.transit.MapplsTransitPlanner.Builder
        public MapplsTransitPlanner autoBuild() {
            String str = "";
            if (this.f13292a == null) {
                str = " baseUrl";
            }
            if (this.b == null) {
                str = str + " mode";
            }
            if (this.c == null) {
                str = str + " origin";
            }
            if (this.d == null) {
                str = str + " destination";
            }
            if (str.isEmpty()) {
                return new a(this.f13292a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.transit.MapplsTransitPlanner.Builder
        public MapplsTransitPlanner.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f13292a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.transit.MapplsTransitPlanner.Builder
        public MapplsTransitPlanner.Builder date(@Nullable String str) {
            this.e = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.transit.MapplsTransitPlanner.Builder
        public MapplsTransitPlanner.Builder destination(String str) {
            Objects.requireNonNull(str, "Null destination");
            this.d = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.transit.MapplsTransitPlanner.Builder
        public MapplsTransitPlanner.Builder maxTransfers(@Nullable Integer num) {
            this.j = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.transit.MapplsTransitPlanner.Builder
        public MapplsTransitPlanner.Builder mode(String str) {
            Objects.requireNonNull(str, "Null mode");
            this.b = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.transit.MapplsTransitPlanner.Builder
        public MapplsTransitPlanner.Builder optimalRoute(@Nullable Boolean bool) {
            this.h = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.transit.MapplsTransitPlanner.Builder
        public MapplsTransitPlanner.Builder origin(String str) {
            Objects.requireNonNull(str, "Null origin");
            this.c = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.transit.MapplsTransitPlanner.Builder
        public MapplsTransitPlanner.Builder searchWindow(@Nullable Integer num) {
            this.i = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.transit.MapplsTransitPlanner.Builder
        public MapplsTransitPlanner.Builder showIntermediateStops(@Nullable Boolean bool) {
            this.k = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.transit.MapplsTransitPlanner.Builder
        public MapplsTransitPlanner.Builder time(@Nullable String str) {
            this.f = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.transit.MapplsTransitPlanner.Builder
        public MapplsTransitPlanner.Builder walkSpeed(@Nullable Double d) {
            this.l = d;
            return this;
        }
    }

    @Override // com.mappls.sdk.services.api.transit.MapplsTransitPlanner
    @Nullable
    public Boolean arriveBy() {
        return this.g;
    }

    @Override // com.mappls.sdk.services.api.transit.MapplsTransitPlanner, com.mappls.sdk.services.api.MapplsService
    public String baseUrl() {
        return this.f13291a;
    }

    @Override // com.mappls.sdk.services.api.transit.MapplsTransitPlanner
    @Nullable
    public String date() {
        return this.e;
    }

    @Override // com.mappls.sdk.services.api.transit.MapplsTransitPlanner
    @NonNull
    public String destination() {
        return this.d;
    }

    public boolean equals(Object obj) {
        String str;
        String str2;
        Boolean bool;
        Boolean bool2;
        Integer num;
        Integer num2;
        Boolean bool3;
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsTransitPlanner) {
            MapplsTransitPlanner mapplsTransitPlanner = (MapplsTransitPlanner) obj;
            if (this.f13291a.equals(mapplsTransitPlanner.baseUrl()) && this.b.equals(mapplsTransitPlanner.mode()) && this.c.equals(mapplsTransitPlanner.origin()) && this.d.equals(mapplsTransitPlanner.destination()) && ((str = this.e) != null ? str.equals(mapplsTransitPlanner.date()) : mapplsTransitPlanner.date() == null) && ((str2 = this.f) != null ? str2.equals(mapplsTransitPlanner.time()) : mapplsTransitPlanner.time() == null) && ((bool = this.g) != null ? bool.equals(mapplsTransitPlanner.arriveBy()) : mapplsTransitPlanner.arriveBy() == null) && ((bool2 = this.h) != null ? bool2.equals(mapplsTransitPlanner.optimalRoute()) : mapplsTransitPlanner.optimalRoute() == null) && ((num = this.i) != null ? num.equals(mapplsTransitPlanner.searchWindow()) : mapplsTransitPlanner.searchWindow() == null) && ((num2 = this.j) != null ? num2.equals(mapplsTransitPlanner.maxTransfers()) : mapplsTransitPlanner.maxTransfers() == null) && ((bool3 = this.k) != null ? bool3.equals(mapplsTransitPlanner.showIntermediateStops()) : mapplsTransitPlanner.showIntermediateStops() == null)) {
                Double d = this.l;
                if (d == null) {
                    if (mapplsTransitPlanner.walkSpeed() == null) {
                        return true;
                    }
                } else if (d.equals(mapplsTransitPlanner.walkSpeed())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((((((this.f13291a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d.hashCode()) * 1000003;
        String str = this.e;
        int hashCode2 = (hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003;
        String str2 = this.f;
        int hashCode3 = (hashCode2 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        Boolean bool = this.g;
        int hashCode4 = (hashCode3 ^ (bool == null ? 0 : bool.hashCode())) * 1000003;
        Boolean bool2 = this.h;
        int hashCode5 = (hashCode4 ^ (bool2 == null ? 0 : bool2.hashCode())) * 1000003;
        Integer num = this.i;
        int hashCode6 = (hashCode5 ^ (num == null ? 0 : num.hashCode())) * 1000003;
        Integer num2 = this.j;
        int hashCode7 = (hashCode6 ^ (num2 == null ? 0 : num2.hashCode())) * 1000003;
        Boolean bool3 = this.k;
        int hashCode8 = (hashCode7 ^ (bool3 == null ? 0 : bool3.hashCode())) * 1000003;
        Double d = this.l;
        return hashCode8 ^ (d != null ? d.hashCode() : 0);
    }

    @Override // com.mappls.sdk.services.api.transit.MapplsTransitPlanner
    @Nullable
    public Integer maxTransfers() {
        return this.j;
    }

    @Override // com.mappls.sdk.services.api.transit.MapplsTransitPlanner
    @NonNull
    public String mode() {
        return this.b;
    }

    @Override // com.mappls.sdk.services.api.transit.MapplsTransitPlanner
    @Nullable
    public Boolean optimalRoute() {
        return this.h;
    }

    @Override // com.mappls.sdk.services.api.transit.MapplsTransitPlanner
    @NonNull
    public String origin() {
        return this.c;
    }

    @Override // com.mappls.sdk.services.api.transit.MapplsTransitPlanner
    @Nullable
    public Integer searchWindow() {
        return this.i;
    }

    @Override // com.mappls.sdk.services.api.transit.MapplsTransitPlanner
    @Nullable
    public Boolean showIntermediateStops() {
        return this.k;
    }

    @Override // com.mappls.sdk.services.api.transit.MapplsTransitPlanner
    @Nullable
    public String time() {
        return this.f;
    }

    public String toString() {
        return "MapplsTransitPlanner{baseUrl=" + this.f13291a + ", mode=" + this.b + ", origin=" + this.c + ", destination=" + this.d + ", date=" + this.e + ", time=" + this.f + ", arriveBy=" + this.g + ", optimalRoute=" + this.h + ", searchWindow=" + this.i + ", maxTransfers=" + this.j + ", showIntermediateStops=" + this.k + ", walkSpeed=" + this.l + "}";
    }

    @Override // com.mappls.sdk.services.api.transit.MapplsTransitPlanner
    @Nullable
    public Double walkSpeed() {
        return this.l;
    }

    public a(String str, String str2, String str3, String str4, @Nullable String str5, @Nullable String str6, @Nullable Boolean bool, @Nullable Boolean bool2, @Nullable Integer num, @Nullable Integer num2, @Nullable Boolean bool3, @Nullable Double d) {
        this.f13291a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = str6;
        this.g = bool;
        this.h = bool2;
        this.i = num;
        this.j = num2;
        this.k = bool3;
        this.l = d;
    }
}

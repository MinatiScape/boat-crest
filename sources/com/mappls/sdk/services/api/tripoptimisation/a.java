package com.mappls.sdk.services.api.tripoptimisation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.services.api.tripoptimisation.MapplsTripOptimisation;
import java.util.List;
import java.util.Objects;
/* loaded from: classes8.dex */
public final class a extends MapplsTripOptimisation {

    /* renamed from: a  reason: collision with root package name */
    public final String f13294a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final List<String> f;
    public final String g;
    public final String h;
    public final Boolean i;
    public final String j;
    public final String k;
    public final Boolean l;
    public final Boolean m;
    public final Boolean n;

    /* loaded from: classes8.dex */
    public static final class b extends MapplsTripOptimisation.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13295a;
        public String b;
        public String c;
        public String d;
        public String e;
        public List<String> f;
        public String g;
        public String h;
        public Boolean i;
        public String j;
        public String k;
        public Boolean l;
        public Boolean m;
        public Boolean n;

        @Override // com.mappls.sdk.services.api.tripoptimisation.MapplsTripOptimisation.Builder
        public MapplsTripOptimisation autoBuild() {
            String str = "";
            if (this.f13295a == null) {
                str = " baseUrl";
            }
            if (this.b == null) {
                str = str + " profile";
            }
            if (this.c == null) {
                str = str + " resource";
            }
            if (this.d == null) {
                str = str + " origin";
            }
            if (this.e == null) {
                str = str + " destination";
            }
            if (this.f == null) {
                str = str + " waypoints";
            }
            if (this.g == null) {
                str = str + " overview";
            }
            if (this.h == null) {
                str = str + " geometries";
            }
            if (str.isEmpty()) {
                return new a(this.f13295a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.tripoptimisation.MapplsTripOptimisation.Builder
        public MapplsTripOptimisation.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f13295a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.tripoptimisation.MapplsTripOptimisation.Builder
        public MapplsTripOptimisation.Builder continueStraight(@Nullable Boolean bool) {
            this.n = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.tripoptimisation.MapplsTripOptimisation.Builder
        public MapplsTripOptimisation.Builder destination(String str) {
            Objects.requireNonNull(str, "Null destination");
            this.e = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.tripoptimisation.MapplsTripOptimisation.Builder
        public MapplsTripOptimisation.Builder destinationType(@Nullable String str) {
            this.k = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.tripoptimisation.MapplsTripOptimisation.Builder
        public MapplsTripOptimisation.Builder geometries(String str) {
            Objects.requireNonNull(str, "Null geometries");
            this.h = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.tripoptimisation.MapplsTripOptimisation.Builder
        public MapplsTripOptimisation.Builder lessVerbose(@Nullable Boolean bool) {
            this.m = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.tripoptimisation.MapplsTripOptimisation.Builder
        public MapplsTripOptimisation.Builder origin(String str) {
            Objects.requireNonNull(str, "Null origin");
            this.d = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.tripoptimisation.MapplsTripOptimisation.Builder
        public MapplsTripOptimisation.Builder overview(String str) {
            Objects.requireNonNull(str, "Null overview");
            this.g = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.tripoptimisation.MapplsTripOptimisation.Builder
        public MapplsTripOptimisation.Builder profile(String str) {
            Objects.requireNonNull(str, "Null profile");
            this.b = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.tripoptimisation.MapplsTripOptimisation.Builder
        public MapplsTripOptimisation.Builder resource(String str) {
            Objects.requireNonNull(str, "Null resource");
            this.c = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.tripoptimisation.MapplsTripOptimisation.Builder
        public MapplsTripOptimisation.Builder roundTrip(@Nullable Boolean bool) {
            this.l = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.tripoptimisation.MapplsTripOptimisation.Builder
        public MapplsTripOptimisation.Builder sourceType(@Nullable String str) {
            this.j = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.tripoptimisation.MapplsTripOptimisation.Builder
        public MapplsTripOptimisation.Builder steps(@Nullable Boolean bool) {
            this.i = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.tripoptimisation.MapplsTripOptimisation.Builder
        public MapplsTripOptimisation.Builder waypoints(List<String> list) {
            Objects.requireNonNull(list, "Null waypoints");
            this.f = list;
            return this;
        }
    }

    @Override // com.mappls.sdk.services.api.tripoptimisation.MapplsTripOptimisation, com.mappls.sdk.services.api.MapplsService
    @NonNull
    public String baseUrl() {
        return this.f13294a;
    }

    @Override // com.mappls.sdk.services.api.tripoptimisation.MapplsTripOptimisation
    @Nullable
    public Boolean continueStraight() {
        return this.n;
    }

    @Override // com.mappls.sdk.services.api.tripoptimisation.MapplsTripOptimisation
    @NonNull
    public String destination() {
        return this.e;
    }

    @Override // com.mappls.sdk.services.api.tripoptimisation.MapplsTripOptimisation
    @Nullable
    public String destinationType() {
        return this.k;
    }

    public boolean equals(Object obj) {
        Boolean bool;
        String str;
        String str2;
        Boolean bool2;
        Boolean bool3;
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsTripOptimisation) {
            MapplsTripOptimisation mapplsTripOptimisation = (MapplsTripOptimisation) obj;
            if (this.f13294a.equals(mapplsTripOptimisation.baseUrl()) && this.b.equals(mapplsTripOptimisation.profile()) && this.c.equals(mapplsTripOptimisation.resource()) && this.d.equals(mapplsTripOptimisation.origin()) && this.e.equals(mapplsTripOptimisation.destination()) && this.f.equals(mapplsTripOptimisation.waypoints()) && this.g.equals(mapplsTripOptimisation.overview()) && this.h.equals(mapplsTripOptimisation.geometries()) && ((bool = this.i) != null ? bool.equals(mapplsTripOptimisation.steps()) : mapplsTripOptimisation.steps() == null) && ((str = this.j) != null ? str.equals(mapplsTripOptimisation.sourceType()) : mapplsTripOptimisation.sourceType() == null) && ((str2 = this.k) != null ? str2.equals(mapplsTripOptimisation.destinationType()) : mapplsTripOptimisation.destinationType() == null) && ((bool2 = this.l) != null ? bool2.equals(mapplsTripOptimisation.roundTrip()) : mapplsTripOptimisation.roundTrip() == null) && ((bool3 = this.m) != null ? bool3.equals(mapplsTripOptimisation.lessVerbose()) : mapplsTripOptimisation.lessVerbose() == null)) {
                Boolean bool4 = this.n;
                if (bool4 == null) {
                    if (mapplsTripOptimisation.continueStraight() == null) {
                        return true;
                    }
                } else if (bool4.equals(mapplsTripOptimisation.continueStraight())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override // com.mappls.sdk.services.api.tripoptimisation.MapplsTripOptimisation
    @NonNull
    public String geometries() {
        return this.h;
    }

    public int hashCode() {
        int hashCode = (((((((((((((((this.f13294a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d.hashCode()) * 1000003) ^ this.e.hashCode()) * 1000003) ^ this.f.hashCode()) * 1000003) ^ this.g.hashCode()) * 1000003) ^ this.h.hashCode()) * 1000003;
        Boolean bool = this.i;
        int hashCode2 = (hashCode ^ (bool == null ? 0 : bool.hashCode())) * 1000003;
        String str = this.j;
        int hashCode3 = (hashCode2 ^ (str == null ? 0 : str.hashCode())) * 1000003;
        String str2 = this.k;
        int hashCode4 = (hashCode3 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        Boolean bool2 = this.l;
        int hashCode5 = (hashCode4 ^ (bool2 == null ? 0 : bool2.hashCode())) * 1000003;
        Boolean bool3 = this.m;
        int hashCode6 = (hashCode5 ^ (bool3 == null ? 0 : bool3.hashCode())) * 1000003;
        Boolean bool4 = this.n;
        return hashCode6 ^ (bool4 != null ? bool4.hashCode() : 0);
    }

    @Override // com.mappls.sdk.services.api.tripoptimisation.MapplsTripOptimisation
    @Nullable
    public Boolean lessVerbose() {
        return this.m;
    }

    @Override // com.mappls.sdk.services.api.tripoptimisation.MapplsTripOptimisation
    @NonNull
    public String origin() {
        return this.d;
    }

    @Override // com.mappls.sdk.services.api.tripoptimisation.MapplsTripOptimisation
    @NonNull
    public String overview() {
        return this.g;
    }

    @Override // com.mappls.sdk.services.api.tripoptimisation.MapplsTripOptimisation
    @NonNull
    public String profile() {
        return this.b;
    }

    @Override // com.mappls.sdk.services.api.tripoptimisation.MapplsTripOptimisation
    @NonNull
    public String resource() {
        return this.c;
    }

    @Override // com.mappls.sdk.services.api.tripoptimisation.MapplsTripOptimisation
    @Nullable
    public Boolean roundTrip() {
        return this.l;
    }

    @Override // com.mappls.sdk.services.api.tripoptimisation.MapplsTripOptimisation
    @Nullable
    public String sourceType() {
        return this.j;
    }

    @Override // com.mappls.sdk.services.api.tripoptimisation.MapplsTripOptimisation
    @Nullable
    public Boolean steps() {
        return this.i;
    }

    public String toString() {
        return "MapplsTripOptimisation{baseUrl=" + this.f13294a + ", profile=" + this.b + ", resource=" + this.c + ", origin=" + this.d + ", destination=" + this.e + ", waypoints=" + this.f + ", overview=" + this.g + ", geometries=" + this.h + ", steps=" + this.i + ", sourceType=" + this.j + ", destinationType=" + this.k + ", roundTrip=" + this.l + ", lessVerbose=" + this.m + ", continueStraight=" + this.n + "}";
    }

    @Override // com.mappls.sdk.services.api.tripoptimisation.MapplsTripOptimisation
    @NonNull
    public List<String> waypoints() {
        return this.f;
    }

    public a(String str, String str2, String str3, String str4, String str5, List<String> list, String str6, String str7, @Nullable Boolean bool, @Nullable String str8, @Nullable String str9, @Nullable Boolean bool2, @Nullable Boolean bool3, @Nullable Boolean bool4) {
        this.f13294a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = list;
        this.g = str6;
        this.h = str7;
        this.i = bool;
        this.j = str8;
        this.k = str9;
        this.l = bool2;
        this.m = bool3;
        this.n = bool4;
    }
}

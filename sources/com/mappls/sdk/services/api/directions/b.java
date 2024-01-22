package com.mappls.sdk.services.api.directions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.services.api.directions.MapplsDirections;
import java.util.List;
import java.util.Objects;
/* loaded from: classes11.dex */
public final class b extends MapplsDirections {
    public final Boolean A;
    public final String B;
    public final Boolean C;
    public final Boolean D;
    public final Boolean E;
    public final Integer F;

    /* renamed from: a  reason: collision with root package name */
    public final String f13167a;
    public final String b;
    public final String c;
    public final List<String> d;
    public final String e;
    public final Boolean f;
    public final String g;
    public final String h;
    public final String i;
    public final String j;
    public final Boolean k;
    public final Boolean l;
    public final String m;
    public final String n;
    public final Boolean o;
    public final String p;
    public final Boolean q;
    public final Boolean r;
    public final String s;
    public final String t;
    public final String u;
    public final String v;
    public final String w;
    public final Boolean x;
    public final WalkingOptions y;
    public final String z;

    /* renamed from: com.mappls.sdk.services.api.directions.b$b  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static final class C0669b extends MapplsDirections.Builder {
        public Boolean A;
        public String B;
        public Boolean C;
        public Boolean D;
        public Boolean E;
        public Integer F;

        /* renamed from: a  reason: collision with root package name */
        public String f13168a;
        public String b;
        public String c;
        public List<String> d;
        public String e;
        public Boolean f;
        public String g;
        public String h;
        public String i;
        public String j;
        public Boolean k;
        public Boolean l;
        public String m;
        public String n;
        public Boolean o;
        public String p;
        public Boolean q;
        public Boolean r;
        public String s;
        public String t;
        public String u;
        public String v;
        public String w;
        public Boolean x;
        public WalkingOptions y;
        public String z;

        @Override // com.mappls.sdk.services.api.directions.MapplsDirections.Builder
        public MapplsDirections.Builder alternatives(@Nullable Boolean bool) {
            this.f = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.MapplsDirections.Builder
        public MapplsDirections.Builder annotation(@Nullable String str) {
            this.m = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.MapplsDirections.Builder
        public MapplsDirections.Builder approaches(@Nullable String str) {
            this.t = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.MapplsDirections.Builder
        public MapplsDirections autoBuild() {
            String str = "";
            if (this.f13168a == null) {
                str = " user";
            }
            if (this.b == null) {
                str = str + " profile";
            }
            if (this.c == null) {
                str = str + " resource";
            }
            if (this.d == null) {
                str = str + " coordinates";
            }
            if (this.e == null) {
                str = str + " baseUrl";
            }
            if (str.isEmpty()) {
                return new b(this.f13168a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.r, this.s, this.t, this.u, this.v, this.w, this.x, this.y, this.z, this.A, this.B, this.C, this.D, this.E, this.F);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.directions.MapplsDirections.Builder
        public MapplsDirections.Builder bannerInstructions(@Nullable Boolean bool) {
            this.r = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.MapplsDirections.Builder
        public MapplsDirections.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.e = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.MapplsDirections.Builder
        public MapplsDirections.Builder bearing(@Nullable String str) {
            this.j = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.MapplsDirections.Builder
        public MapplsDirections.Builder clientAppName(String str) {
            this.p = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.MapplsDirections.Builder
        public MapplsDirections.Builder continueStraight(@Nullable Boolean bool) {
            this.q = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.MapplsDirections.Builder
        public MapplsDirections.Builder coordinates(List<String> list) {
            Objects.requireNonNull(list, "Null coordinates");
            this.d = list;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.MapplsDirections.Builder
        public MapplsDirections.Builder deviceId(@Nullable String str) {
            this.z = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.MapplsDirections.Builder
        public MapplsDirections.Builder exclude(String str) {
            this.s = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.MapplsDirections.Builder
        public MapplsDirections.Builder geometries(String str) {
            this.g = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.MapplsDirections.Builder
        public MapplsDirections.Builder instructions(@Nullable Boolean bool) {
            this.E = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.MapplsDirections.Builder
        public MapplsDirections.Builder isSort(@Nullable Boolean bool) {
            this.C = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.MapplsDirections.Builder
        public MapplsDirections.Builder language(@Nullable String str) {
            this.n = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.MapplsDirections.Builder
        public MapplsDirections.Builder lessVerbose(@Nullable Boolean bool) {
            this.l = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.MapplsDirections.Builder
        public MapplsDirections.Builder overview(@Nullable String str) {
            this.h = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.MapplsDirections.Builder
        public MapplsDirections.Builder profile(String str) {
            Objects.requireNonNull(str, "Null profile");
            this.b = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.MapplsDirections.Builder
        public MapplsDirections.Builder radius(@Nullable String str) {
            this.i = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.MapplsDirections.Builder
        public MapplsDirections.Builder resource(String str) {
            Objects.requireNonNull(str, "Null resource");
            this.c = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.MapplsDirections.Builder
        public MapplsDirections.Builder roundaboutExits(@Nullable Boolean bool) {
            this.o = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.MapplsDirections.Builder
        public MapplsDirections.Builder routeRefresh(@Nullable Boolean bool) {
            this.A = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.MapplsDirections.Builder
        public MapplsDirections.Builder routeType(@Nullable Integer num) {
            this.F = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.MapplsDirections.Builder
        public MapplsDirections.Builder sessionId(@Nullable String str) {
            this.B = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.MapplsDirections.Builder
        public MapplsDirections.Builder skipWaypoints(@Nullable Boolean bool) {
            this.D = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.MapplsDirections.Builder
        public MapplsDirections.Builder steps(@Nullable Boolean bool) {
            this.k = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.MapplsDirections.Builder
        public MapplsDirections.Builder usePostMethod(Boolean bool) {
            this.x = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.MapplsDirections.Builder
        public MapplsDirections.Builder user(String str) {
            Objects.requireNonNull(str, "Null user");
            this.f13168a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.MapplsDirections.Builder
        public MapplsDirections.Builder walkingOptions(WalkingOptions walkingOptions) {
            this.y = walkingOptions;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.MapplsDirections.Builder
        public MapplsDirections.Builder waypointIndices(@Nullable String str) {
            this.u = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.MapplsDirections.Builder
        public MapplsDirections.Builder waypointNames(@Nullable String str) {
            this.v = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.MapplsDirections.Builder
        public MapplsDirections.Builder waypointTargets(@Nullable String str) {
            this.w = str;
            return this;
        }

        public C0669b() {
        }

        @Override // com.mappls.sdk.services.api.directions.MapplsDirections.Builder
        @Nullable
        public Boolean usePostMethod() {
            return this.x;
        }

        @Override // com.mappls.sdk.services.api.directions.MapplsDirections.Builder
        @Nullable
        public WalkingOptions walkingOptions() {
            return this.y;
        }

        public C0669b(MapplsDirections mapplsDirections) {
            this.f13168a = mapplsDirections.user();
            this.b = mapplsDirections.profile();
            this.c = mapplsDirections.resource();
            this.d = mapplsDirections.coordinates();
            this.e = mapplsDirections.baseUrl();
            this.f = mapplsDirections.alternatives();
            this.g = mapplsDirections.geometries();
            this.h = mapplsDirections.overview();
            this.i = mapplsDirections.radius();
            this.j = mapplsDirections.bearing();
            this.k = mapplsDirections.steps();
            this.l = mapplsDirections.lessVerbose();
            this.m = mapplsDirections.annotation();
            this.n = mapplsDirections.language();
            this.o = mapplsDirections.roundaboutExits();
            this.p = mapplsDirections.clientAppName();
            this.q = mapplsDirections.continueStraight();
            this.r = mapplsDirections.bannerInstructions();
            this.s = mapplsDirections.exclude();
            this.t = mapplsDirections.approaches();
            this.u = mapplsDirections.waypointIndices();
            this.v = mapplsDirections.waypointNames();
            this.w = mapplsDirections.waypointTargets();
            this.x = mapplsDirections.usePostMethod();
            this.y = mapplsDirections.walkingOptions();
            this.z = mapplsDirections.deviceId();
            this.A = mapplsDirections.routeRefresh();
            this.B = mapplsDirections.sessionId();
            this.C = mapplsDirections.isSort();
            this.D = mapplsDirections.skipWaypoints();
            this.E = mapplsDirections.instructions();
            this.F = mapplsDirections.routeType();
        }
    }

    @Override // com.mappls.sdk.services.api.directions.MapplsDirections
    @Nullable
    public Boolean alternatives() {
        return this.f;
    }

    @Override // com.mappls.sdk.services.api.directions.MapplsDirections
    @Nullable
    public String annotation() {
        return this.m;
    }

    @Override // com.mappls.sdk.services.api.directions.MapplsDirections
    @Nullable
    public String approaches() {
        return this.t;
    }

    @Override // com.mappls.sdk.services.api.directions.MapplsDirections
    @Nullable
    public Boolean bannerInstructions() {
        return this.r;
    }

    @Override // com.mappls.sdk.services.api.directions.MapplsDirections, com.mappls.sdk.services.api.MapplsService
    @NonNull
    public String baseUrl() {
        return this.e;
    }

    @Override // com.mappls.sdk.services.api.directions.MapplsDirections
    @Nullable
    public String bearing() {
        return this.j;
    }

    @Override // com.mappls.sdk.services.api.directions.MapplsDirections
    @Nullable
    public String clientAppName() {
        return this.p;
    }

    @Override // com.mappls.sdk.services.api.directions.MapplsDirections
    @Nullable
    public Boolean continueStraight() {
        return this.q;
    }

    @Override // com.mappls.sdk.services.api.directions.MapplsDirections
    @NonNull
    public List<String> coordinates() {
        return this.d;
    }

    @Override // com.mappls.sdk.services.api.directions.MapplsDirections
    @Nullable
    public String deviceId() {
        return this.z;
    }

    public boolean equals(Object obj) {
        Boolean bool;
        String str;
        String str2;
        String str3;
        String str4;
        Boolean bool2;
        Boolean bool3;
        String str5;
        String str6;
        Boolean bool4;
        String str7;
        Boolean bool5;
        Boolean bool6;
        String str8;
        String str9;
        String str10;
        String str11;
        String str12;
        Boolean bool7;
        WalkingOptions walkingOptions;
        String str13;
        Boolean bool8;
        String str14;
        Boolean bool9;
        Boolean bool10;
        Boolean bool11;
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsDirections) {
            MapplsDirections mapplsDirections = (MapplsDirections) obj;
            if (this.f13167a.equals(mapplsDirections.user()) && this.b.equals(mapplsDirections.profile()) && this.c.equals(mapplsDirections.resource()) && this.d.equals(mapplsDirections.coordinates()) && this.e.equals(mapplsDirections.baseUrl()) && ((bool = this.f) != null ? bool.equals(mapplsDirections.alternatives()) : mapplsDirections.alternatives() == null) && ((str = this.g) != null ? str.equals(mapplsDirections.geometries()) : mapplsDirections.geometries() == null) && ((str2 = this.h) != null ? str2.equals(mapplsDirections.overview()) : mapplsDirections.overview() == null) && ((str3 = this.i) != null ? str3.equals(mapplsDirections.radius()) : mapplsDirections.radius() == null) && ((str4 = this.j) != null ? str4.equals(mapplsDirections.bearing()) : mapplsDirections.bearing() == null) && ((bool2 = this.k) != null ? bool2.equals(mapplsDirections.steps()) : mapplsDirections.steps() == null) && ((bool3 = this.l) != null ? bool3.equals(mapplsDirections.lessVerbose()) : mapplsDirections.lessVerbose() == null) && ((str5 = this.m) != null ? str5.equals(mapplsDirections.annotation()) : mapplsDirections.annotation() == null) && ((str6 = this.n) != null ? str6.equals(mapplsDirections.language()) : mapplsDirections.language() == null) && ((bool4 = this.o) != null ? bool4.equals(mapplsDirections.roundaboutExits()) : mapplsDirections.roundaboutExits() == null) && ((str7 = this.p) != null ? str7.equals(mapplsDirections.clientAppName()) : mapplsDirections.clientAppName() == null) && ((bool5 = this.q) != null ? bool5.equals(mapplsDirections.continueStraight()) : mapplsDirections.continueStraight() == null) && ((bool6 = this.r) != null ? bool6.equals(mapplsDirections.bannerInstructions()) : mapplsDirections.bannerInstructions() == null) && ((str8 = this.s) != null ? str8.equals(mapplsDirections.exclude()) : mapplsDirections.exclude() == null) && ((str9 = this.t) != null ? str9.equals(mapplsDirections.approaches()) : mapplsDirections.approaches() == null) && ((str10 = this.u) != null ? str10.equals(mapplsDirections.waypointIndices()) : mapplsDirections.waypointIndices() == null) && ((str11 = this.v) != null ? str11.equals(mapplsDirections.waypointNames()) : mapplsDirections.waypointNames() == null) && ((str12 = this.w) != null ? str12.equals(mapplsDirections.waypointTargets()) : mapplsDirections.waypointTargets() == null) && ((bool7 = this.x) != null ? bool7.equals(mapplsDirections.usePostMethod()) : mapplsDirections.usePostMethod() == null) && ((walkingOptions = this.y) != null ? walkingOptions.equals(mapplsDirections.walkingOptions()) : mapplsDirections.walkingOptions() == null) && ((str13 = this.z) != null ? str13.equals(mapplsDirections.deviceId()) : mapplsDirections.deviceId() == null) && ((bool8 = this.A) != null ? bool8.equals(mapplsDirections.routeRefresh()) : mapplsDirections.routeRefresh() == null) && ((str14 = this.B) != null ? str14.equals(mapplsDirections.sessionId()) : mapplsDirections.sessionId() == null) && ((bool9 = this.C) != null ? bool9.equals(mapplsDirections.isSort()) : mapplsDirections.isSort() == null) && ((bool10 = this.D) != null ? bool10.equals(mapplsDirections.skipWaypoints()) : mapplsDirections.skipWaypoints() == null) && ((bool11 = this.E) != null ? bool11.equals(mapplsDirections.instructions()) : mapplsDirections.instructions() == null)) {
                Integer num = this.F;
                if (num == null) {
                    if (mapplsDirections.routeType() == null) {
                        return true;
                    }
                } else if (num.equals(mapplsDirections.routeType())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override // com.mappls.sdk.services.api.directions.MapplsDirections
    @Nullable
    public String exclude() {
        return this.s;
    }

    @Override // com.mappls.sdk.services.api.directions.MapplsDirections
    @Nullable
    public String geometries() {
        return this.g;
    }

    public int hashCode() {
        int hashCode = (((((((((this.f13167a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d.hashCode()) * 1000003) ^ this.e.hashCode()) * 1000003;
        Boolean bool = this.f;
        int hashCode2 = (hashCode ^ (bool == null ? 0 : bool.hashCode())) * 1000003;
        String str = this.g;
        int hashCode3 = (hashCode2 ^ (str == null ? 0 : str.hashCode())) * 1000003;
        String str2 = this.h;
        int hashCode4 = (hashCode3 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.i;
        int hashCode5 = (hashCode4 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003;
        String str4 = this.j;
        int hashCode6 = (hashCode5 ^ (str4 == null ? 0 : str4.hashCode())) * 1000003;
        Boolean bool2 = this.k;
        int hashCode7 = (hashCode6 ^ (bool2 == null ? 0 : bool2.hashCode())) * 1000003;
        Boolean bool3 = this.l;
        int hashCode8 = (hashCode7 ^ (bool3 == null ? 0 : bool3.hashCode())) * 1000003;
        String str5 = this.m;
        int hashCode9 = (hashCode8 ^ (str5 == null ? 0 : str5.hashCode())) * 1000003;
        String str6 = this.n;
        int hashCode10 = (hashCode9 ^ (str6 == null ? 0 : str6.hashCode())) * 1000003;
        Boolean bool4 = this.o;
        int hashCode11 = (hashCode10 ^ (bool4 == null ? 0 : bool4.hashCode())) * 1000003;
        String str7 = this.p;
        int hashCode12 = (hashCode11 ^ (str7 == null ? 0 : str7.hashCode())) * 1000003;
        Boolean bool5 = this.q;
        int hashCode13 = (hashCode12 ^ (bool5 == null ? 0 : bool5.hashCode())) * 1000003;
        Boolean bool6 = this.r;
        int hashCode14 = (hashCode13 ^ (bool6 == null ? 0 : bool6.hashCode())) * 1000003;
        String str8 = this.s;
        int hashCode15 = (hashCode14 ^ (str8 == null ? 0 : str8.hashCode())) * 1000003;
        String str9 = this.t;
        int hashCode16 = (hashCode15 ^ (str9 == null ? 0 : str9.hashCode())) * 1000003;
        String str10 = this.u;
        int hashCode17 = (hashCode16 ^ (str10 == null ? 0 : str10.hashCode())) * 1000003;
        String str11 = this.v;
        int hashCode18 = (hashCode17 ^ (str11 == null ? 0 : str11.hashCode())) * 1000003;
        String str12 = this.w;
        int hashCode19 = (hashCode18 ^ (str12 == null ? 0 : str12.hashCode())) * 1000003;
        Boolean bool7 = this.x;
        int hashCode20 = (hashCode19 ^ (bool7 == null ? 0 : bool7.hashCode())) * 1000003;
        WalkingOptions walkingOptions = this.y;
        int hashCode21 = (hashCode20 ^ (walkingOptions == null ? 0 : walkingOptions.hashCode())) * 1000003;
        String str13 = this.z;
        int hashCode22 = (hashCode21 ^ (str13 == null ? 0 : str13.hashCode())) * 1000003;
        Boolean bool8 = this.A;
        int hashCode23 = (hashCode22 ^ (bool8 == null ? 0 : bool8.hashCode())) * 1000003;
        String str14 = this.B;
        int hashCode24 = (hashCode23 ^ (str14 == null ? 0 : str14.hashCode())) * 1000003;
        Boolean bool9 = this.C;
        int hashCode25 = (hashCode24 ^ (bool9 == null ? 0 : bool9.hashCode())) * 1000003;
        Boolean bool10 = this.D;
        int hashCode26 = (hashCode25 ^ (bool10 == null ? 0 : bool10.hashCode())) * 1000003;
        Boolean bool11 = this.E;
        int hashCode27 = (hashCode26 ^ (bool11 == null ? 0 : bool11.hashCode())) * 1000003;
        Integer num = this.F;
        return hashCode27 ^ (num != null ? num.hashCode() : 0);
    }

    @Override // com.mappls.sdk.services.api.directions.MapplsDirections
    @Nullable
    public Boolean instructions() {
        return this.E;
    }

    @Override // com.mappls.sdk.services.api.directions.MapplsDirections
    @Nullable
    public Boolean isSort() {
        return this.C;
    }

    @Override // com.mappls.sdk.services.api.directions.MapplsDirections
    @Nullable
    public String language() {
        return this.n;
    }

    @Override // com.mappls.sdk.services.api.directions.MapplsDirections
    @Nullable
    public Boolean lessVerbose() {
        return this.l;
    }

    @Override // com.mappls.sdk.services.api.directions.MapplsDirections
    @Nullable
    public String overview() {
        return this.h;
    }

    @Override // com.mappls.sdk.services.api.directions.MapplsDirections
    @NonNull
    public String profile() {
        return this.b;
    }

    @Override // com.mappls.sdk.services.api.directions.MapplsDirections
    @Nullable
    public String radius() {
        return this.i;
    }

    @Override // com.mappls.sdk.services.api.directions.MapplsDirections
    @NonNull
    public String resource() {
        return this.c;
    }

    @Override // com.mappls.sdk.services.api.directions.MapplsDirections
    @Nullable
    public Boolean roundaboutExits() {
        return this.o;
    }

    @Override // com.mappls.sdk.services.api.directions.MapplsDirections
    @Nullable
    public Boolean routeRefresh() {
        return this.A;
    }

    @Override // com.mappls.sdk.services.api.directions.MapplsDirections
    @Nullable
    public Integer routeType() {
        return this.F;
    }

    @Override // com.mappls.sdk.services.api.directions.MapplsDirections
    @Nullable
    public String sessionId() {
        return this.B;
    }

    @Override // com.mappls.sdk.services.api.directions.MapplsDirections
    @Nullable
    public Boolean skipWaypoints() {
        return this.D;
    }

    @Override // com.mappls.sdk.services.api.directions.MapplsDirections
    @Nullable
    public Boolean steps() {
        return this.k;
    }

    @Override // com.mappls.sdk.services.api.directions.MapplsDirections
    public MapplsDirections.Builder toBuilder() {
        return new C0669b(this);
    }

    public String toString() {
        return "MapplsDirections{user=" + this.f13167a + ", profile=" + this.b + ", resource=" + this.c + ", coordinates=" + this.d + ", baseUrl=" + this.e + ", alternatives=" + this.f + ", geometries=" + this.g + ", overview=" + this.h + ", radius=" + this.i + ", bearing=" + this.j + ", steps=" + this.k + ", lessVerbose=" + this.l + ", annotation=" + this.m + ", language=" + this.n + ", roundaboutExits=" + this.o + ", clientAppName=" + this.p + ", continueStraight=" + this.q + ", bannerInstructions=" + this.r + ", exclude=" + this.s + ", approaches=" + this.t + ", waypointIndices=" + this.u + ", waypointNames=" + this.v + ", waypointTargets=" + this.w + ", usePostMethod=" + this.x + ", walkingOptions=" + this.y + ", deviceId=" + this.z + ", routeRefresh=" + this.A + ", sessionId=" + this.B + ", isSort=" + this.C + ", skipWaypoints=" + this.D + ", instructions=" + this.E + ", routeType=" + this.F + "}";
    }

    @Override // com.mappls.sdk.services.api.directions.MapplsDirections
    @Nullable
    public Boolean usePostMethod() {
        return this.x;
    }

    @Override // com.mappls.sdk.services.api.directions.MapplsDirections
    @NonNull
    public String user() {
        return this.f13167a;
    }

    @Override // com.mappls.sdk.services.api.directions.MapplsDirections
    @Nullable
    public WalkingOptions walkingOptions() {
        return this.y;
    }

    @Override // com.mappls.sdk.services.api.directions.MapplsDirections
    @Nullable
    public String waypointIndices() {
        return this.u;
    }

    @Override // com.mappls.sdk.services.api.directions.MapplsDirections
    @Nullable
    public String waypointNames() {
        return this.v;
    }

    @Override // com.mappls.sdk.services.api.directions.MapplsDirections
    @Nullable
    public String waypointTargets() {
        return this.w;
    }

    public b(String str, String str2, String str3, List<String> list, String str4, @Nullable Boolean bool, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable Boolean bool2, @Nullable Boolean bool3, @Nullable String str9, @Nullable String str10, @Nullable Boolean bool4, @Nullable String str11, @Nullable Boolean bool5, @Nullable Boolean bool6, @Nullable String str12, @Nullable String str13, @Nullable String str14, @Nullable String str15, @Nullable String str16, @Nullable Boolean bool7, @Nullable WalkingOptions walkingOptions, @Nullable String str17, @Nullable Boolean bool8, @Nullable String str18, @Nullable Boolean bool9, @Nullable Boolean bool10, @Nullable Boolean bool11, @Nullable Integer num) {
        this.f13167a = str;
        this.b = str2;
        this.c = str3;
        this.d = list;
        this.e = str4;
        this.f = bool;
        this.g = str5;
        this.h = str6;
        this.i = str7;
        this.j = str8;
        this.k = bool2;
        this.l = bool3;
        this.m = str9;
        this.n = str10;
        this.o = bool4;
        this.p = str11;
        this.q = bool5;
        this.r = bool6;
        this.s = str12;
        this.t = str13;
        this.u = str14;
        this.v = str15;
        this.w = str16;
        this.x = bool7;
        this.y = walkingOptions;
        this.z = str17;
        this.A = bool8;
        this.B = str18;
        this.C = bool9;
        this.D = bool10;
        this.E = bool11;
        this.F = num;
    }
}

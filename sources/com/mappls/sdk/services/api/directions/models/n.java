package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.services.api.directions.WalkingOptions;
import com.mappls.sdk.services.api.directions.models.RouteOptions;
import java.util.List;
import java.util.Objects;
/* loaded from: classes11.dex */
public abstract class n extends RouteOptions {
    private final String accessToken;
    private final Boolean alternatives;
    private final String annotations;
    private final String approaches;
    private final Boolean bannerInstructions;
    private final String baseUrl;
    private final String bearings;
    private final Boolean continueStraight;
    private final List<String> coordinates;
    private final String deviceID;
    private final String exclude;
    private final String geometries;
    private final Boolean instructions;
    private final Boolean isSort;
    private final String language;
    private final Boolean lessVerbose;
    private final String overview;
    private final String profile;
    private final String radiuses;
    private final String requestUuid;
    private final String resource;
    private final Boolean roundaboutExits;
    private final Boolean routeRefresh;
    private final Integer routeType;
    private final String sessionId;
    private final Boolean skipWaypoints;
    private final Boolean steps;
    private final String user;
    private final WalkingOptions walkingOptions;
    private final String waypointIndices;
    private final String waypointNames;
    private final String waypointTargets;

    /* loaded from: classes11.dex */
    public static class b extends RouteOptions.Builder {
        public String A;
        public WalkingOptions B;
        public Integer C;
        public Boolean D;
        public Boolean E;
        public Boolean F;

        /* renamed from: a  reason: collision with root package name */
        public String f13199a;
        public String b;
        public String c;
        public String d;
        public String e;
        public List<String> f;
        public Boolean g;
        public String h;
        public String i;
        public String j;
        public Boolean k;
        public String l;
        public String m;
        public Boolean n;
        public String o;
        public String p;
        public Boolean q;
        public Boolean r;
        public Boolean s;
        public Boolean t;
        public String u;
        public String v;
        public String w;
        public String x;
        public String y;
        public String z;

        @Override // com.mappls.sdk.services.api.directions.models.RouteOptions.Builder
        public RouteOptions.Builder accessToken(@Nullable String str) {
            this.u = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteOptions.Builder
        public RouteOptions.Builder alternatives(@Nullable Boolean bool) {
            this.g = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteOptions.Builder
        public RouteOptions.Builder annotations(@Nullable String str) {
            this.o = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteOptions.Builder
        public RouteOptions.Builder approaches(String str) {
            this.x = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteOptions.Builder
        public RouteOptions.Builder bannerInstructions(Boolean bool) {
            this.r = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteOptions.Builder
        public RouteOptions.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f13199a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteOptions.Builder
        public RouteOptions.Builder bearings(String str) {
            this.j = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteOptions.Builder
        public RouteOptions build() {
            String str = "";
            if (this.f13199a == null) {
                str = " baseUrl";
            }
            if (this.c == null) {
                str = str + " user";
            }
            if (this.d == null) {
                str = str + " profile";
            }
            if (this.e == null) {
                str = str + " resource";
            }
            if (this.f == null) {
                str = str + " coordinates";
            }
            if (this.l == null) {
                str = str + " geometries";
            }
            if (str.isEmpty()) {
                return new AutoValue_RouteOptions(this.f13199a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.r, this.s, this.t, this.u, this.v, this.w, this.x, this.y, this.z, this.A, this.B, this.C, this.D, this.E, this.F);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteOptions.Builder
        public RouteOptions.Builder continueStraight(Boolean bool) {
            this.q = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteOptions.Builder
        public RouteOptions.Builder coordinates(List<String> list) {
            Objects.requireNonNull(list, "Null coordinates");
            this.f = list;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteOptions.Builder
        public RouteOptions.Builder deviceID(@Nullable String str) {
            this.b = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteOptions.Builder
        public RouteOptions.Builder exclude(@Nullable String str) {
            this.p = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteOptions.Builder
        public RouteOptions.Builder geometries(String str) {
            Objects.requireNonNull(str, "Null geometries");
            this.l = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteOptions.Builder
        public RouteOptions.Builder instructions(Boolean bool) {
            this.s = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteOptions.Builder
        public RouteOptions.Builder isSort(@Nullable Boolean bool) {
            this.D = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteOptions.Builder
        public RouteOptions.Builder language(String str) {
            this.h = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteOptions.Builder
        public RouteOptions.Builder lessVerbose(Boolean bool) {
            this.k = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteOptions.Builder
        public RouteOptions.Builder overview(@Nullable String str) {
            this.m = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteOptions.Builder
        public RouteOptions.Builder profile(String str) {
            Objects.requireNonNull(str, "Null profile");
            this.d = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteOptions.Builder
        public RouteOptions.Builder radiuses(String str) {
            this.i = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteOptions.Builder
        public RouteOptions.Builder requestUuid(@Nullable String str) {
            this.v = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteOptions.Builder
        public RouteOptions.Builder resource(String str) {
            Objects.requireNonNull(str, "Null resource");
            this.e = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteOptions.Builder
        public RouteOptions.Builder roundaboutExits(@Nullable Boolean bool) {
            this.F = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteOptions.Builder
        public RouteOptions.Builder routeRefresh(@Nullable Boolean bool) {
            this.E = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteOptions.Builder
        public RouteOptions.Builder routeType(@Nullable Integer num) {
            this.C = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteOptions.Builder
        public RouteOptions.Builder sessionId(@Nullable String str) {
            this.w = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteOptions.Builder
        public RouteOptions.Builder skipWaypoints(@Nullable Boolean bool) {
            this.t = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteOptions.Builder
        public RouteOptions.Builder steps(@Nullable Boolean bool) {
            this.n = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteOptions.Builder
        public RouteOptions.Builder user(String str) {
            Objects.requireNonNull(str, "Null user");
            this.c = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteOptions.Builder
        public RouteOptions.Builder walkingOptions(@Nullable WalkingOptions walkingOptions) {
            this.B = walkingOptions;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteOptions.Builder
        public RouteOptions.Builder waypointIndices(@Nullable String str) {
            this.y = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteOptions.Builder
        public RouteOptions.Builder waypointNames(@Nullable String str) {
            this.z = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.RouteOptions.Builder
        public RouteOptions.Builder waypointTargets(@Nullable String str) {
            this.A = str;
            return this;
        }

        public b() {
        }

        public b(RouteOptions routeOptions) {
            this.f13199a = routeOptions.baseUrl();
            this.b = routeOptions.deviceID();
            this.c = routeOptions.user();
            this.d = routeOptions.profile();
            this.e = routeOptions.resource();
            this.f = routeOptions.coordinates();
            this.g = routeOptions.alternatives();
            this.h = routeOptions.language();
            this.i = routeOptions.radiuses();
            this.j = routeOptions.bearings();
            this.k = routeOptions.lessVerbose();
            this.l = routeOptions.geometries();
            this.m = routeOptions.overview();
            this.n = routeOptions.steps();
            this.o = routeOptions.annotations();
            this.p = routeOptions.exclude();
            this.q = routeOptions.continueStraight();
            this.r = routeOptions.bannerInstructions();
            this.s = routeOptions.instructions();
            this.t = routeOptions.skipWaypoints();
            this.u = routeOptions.accessToken();
            this.v = routeOptions.requestUuid();
            this.w = routeOptions.sessionId();
            this.x = routeOptions.approaches();
            this.y = routeOptions.waypointIndices();
            this.z = routeOptions.waypointNames();
            this.A = routeOptions.waypointTargets();
            this.B = routeOptions.walkingOptions();
            this.C = routeOptions.routeType();
            this.D = routeOptions.isSort();
            this.E = routeOptions.routeRefresh();
            this.F = routeOptions.roundaboutExits();
        }
    }

    public n(String str, @Nullable String str2, String str3, String str4, String str5, List<String> list, @Nullable Boolean bool, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable Boolean bool2, String str9, @Nullable String str10, @Nullable Boolean bool3, @Nullable String str11, @Nullable String str12, @Nullable Boolean bool4, @Nullable Boolean bool5, @Nullable Boolean bool6, @Nullable Boolean bool7, @Nullable String str13, @Nullable String str14, @Nullable String str15, @Nullable String str16, @Nullable String str17, @Nullable String str18, @Nullable String str19, @Nullable WalkingOptions walkingOptions, @Nullable Integer num, @Nullable Boolean bool8, @Nullable Boolean bool9, @Nullable Boolean bool10) {
        Objects.requireNonNull(str, "Null baseUrl");
        this.baseUrl = str;
        this.deviceID = str2;
        Objects.requireNonNull(str3, "Null user");
        this.user = str3;
        Objects.requireNonNull(str4, "Null profile");
        this.profile = str4;
        Objects.requireNonNull(str5, "Null resource");
        this.resource = str5;
        Objects.requireNonNull(list, "Null coordinates");
        this.coordinates = list;
        this.alternatives = bool;
        this.language = str6;
        this.radiuses = str7;
        this.bearings = str8;
        this.lessVerbose = bool2;
        Objects.requireNonNull(str9, "Null geometries");
        this.geometries = str9;
        this.overview = str10;
        this.steps = bool3;
        this.annotations = str11;
        this.exclude = str12;
        this.continueStraight = bool4;
        this.bannerInstructions = bool5;
        this.instructions = bool6;
        this.skipWaypoints = bool7;
        this.accessToken = str13;
        this.requestUuid = str14;
        this.sessionId = str15;
        this.approaches = str16;
        this.waypointIndices = str17;
        this.waypointNames = str18;
        this.waypointTargets = str19;
        this.walkingOptions = walkingOptions;
        this.routeType = num;
        this.isSort = bool8;
        this.routeRefresh = bool9;
        this.roundaboutExits = bool10;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteOptions
    @Nullable
    @SerializedName("access_token")
    public String accessToken() {
        return this.accessToken;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteOptions
    @Nullable
    public Boolean alternatives() {
        return this.alternatives;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteOptions
    @Nullable
    public String annotations() {
        return this.annotations;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteOptions
    @Nullable
    public String approaches() {
        return this.approaches;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteOptions
    @Nullable
    @SerializedName("banner_instructions")
    public Boolean bannerInstructions() {
        return this.bannerInstructions;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteOptions
    @NonNull
    public String baseUrl() {
        return this.baseUrl;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteOptions
    @Nullable
    public String bearings() {
        return this.bearings;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteOptions
    @Nullable
    @SerializedName("continueStraight")
    public Boolean continueStraight() {
        return this.continueStraight;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteOptions
    @NonNull
    public List<String> coordinates() {
        return this.coordinates;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteOptions
    @Nullable
    public String deviceID() {
        return this.deviceID;
    }

    public boolean equals(Object obj) {
        String str;
        Boolean bool;
        String str2;
        String str3;
        String str4;
        Boolean bool2;
        String str5;
        Boolean bool3;
        String str6;
        String str7;
        Boolean bool4;
        Boolean bool5;
        Boolean bool6;
        Boolean bool7;
        String str8;
        String str9;
        String str10;
        String str11;
        String str12;
        String str13;
        String str14;
        WalkingOptions walkingOptions;
        Integer num;
        Boolean bool8;
        Boolean bool9;
        if (obj == this) {
            return true;
        }
        if (obj instanceof RouteOptions) {
            RouteOptions routeOptions = (RouteOptions) obj;
            if (this.baseUrl.equals(routeOptions.baseUrl()) && ((str = this.deviceID) != null ? str.equals(routeOptions.deviceID()) : routeOptions.deviceID() == null) && this.user.equals(routeOptions.user()) && this.profile.equals(routeOptions.profile()) && this.resource.equals(routeOptions.resource()) && this.coordinates.equals(routeOptions.coordinates()) && ((bool = this.alternatives) != null ? bool.equals(routeOptions.alternatives()) : routeOptions.alternatives() == null) && ((str2 = this.language) != null ? str2.equals(routeOptions.language()) : routeOptions.language() == null) && ((str3 = this.radiuses) != null ? str3.equals(routeOptions.radiuses()) : routeOptions.radiuses() == null) && ((str4 = this.bearings) != null ? str4.equals(routeOptions.bearings()) : routeOptions.bearings() == null) && ((bool2 = this.lessVerbose) != null ? bool2.equals(routeOptions.lessVerbose()) : routeOptions.lessVerbose() == null) && this.geometries.equals(routeOptions.geometries()) && ((str5 = this.overview) != null ? str5.equals(routeOptions.overview()) : routeOptions.overview() == null) && ((bool3 = this.steps) != null ? bool3.equals(routeOptions.steps()) : routeOptions.steps() == null) && ((str6 = this.annotations) != null ? str6.equals(routeOptions.annotations()) : routeOptions.annotations() == null) && ((str7 = this.exclude) != null ? str7.equals(routeOptions.exclude()) : routeOptions.exclude() == null) && ((bool4 = this.continueStraight) != null ? bool4.equals(routeOptions.continueStraight()) : routeOptions.continueStraight() == null) && ((bool5 = this.bannerInstructions) != null ? bool5.equals(routeOptions.bannerInstructions()) : routeOptions.bannerInstructions() == null) && ((bool6 = this.instructions) != null ? bool6.equals(routeOptions.instructions()) : routeOptions.instructions() == null) && ((bool7 = this.skipWaypoints) != null ? bool7.equals(routeOptions.skipWaypoints()) : routeOptions.skipWaypoints() == null) && ((str8 = this.accessToken) != null ? str8.equals(routeOptions.accessToken()) : routeOptions.accessToken() == null) && ((str9 = this.requestUuid) != null ? str9.equals(routeOptions.requestUuid()) : routeOptions.requestUuid() == null) && ((str10 = this.sessionId) != null ? str10.equals(routeOptions.sessionId()) : routeOptions.sessionId() == null) && ((str11 = this.approaches) != null ? str11.equals(routeOptions.approaches()) : routeOptions.approaches() == null) && ((str12 = this.waypointIndices) != null ? str12.equals(routeOptions.waypointIndices()) : routeOptions.waypointIndices() == null) && ((str13 = this.waypointNames) != null ? str13.equals(routeOptions.waypointNames()) : routeOptions.waypointNames() == null) && ((str14 = this.waypointTargets) != null ? str14.equals(routeOptions.waypointTargets()) : routeOptions.waypointTargets() == null) && ((walkingOptions = this.walkingOptions) != null ? walkingOptions.equals(routeOptions.walkingOptions()) : routeOptions.walkingOptions() == null) && ((num = this.routeType) != null ? num.equals(routeOptions.routeType()) : routeOptions.routeType() == null) && ((bool8 = this.isSort) != null ? bool8.equals(routeOptions.isSort()) : routeOptions.isSort() == null) && ((bool9 = this.routeRefresh) != null ? bool9.equals(routeOptions.routeRefresh()) : routeOptions.routeRefresh() == null)) {
                Boolean bool10 = this.roundaboutExits;
                if (bool10 == null) {
                    if (routeOptions.roundaboutExits() == null) {
                        return true;
                    }
                } else if (bool10.equals(routeOptions.roundaboutExits())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteOptions
    @Nullable
    public String exclude() {
        return this.exclude;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteOptions
    public String geometries() {
        return this.geometries;
    }

    public int hashCode() {
        int hashCode = (this.baseUrl.hashCode() ^ 1000003) * 1000003;
        String str = this.deviceID;
        int hashCode2 = (((((((((hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003) ^ this.user.hashCode()) * 1000003) ^ this.profile.hashCode()) * 1000003) ^ this.resource.hashCode()) * 1000003) ^ this.coordinates.hashCode()) * 1000003;
        Boolean bool = this.alternatives;
        int hashCode3 = (hashCode2 ^ (bool == null ? 0 : bool.hashCode())) * 1000003;
        String str2 = this.language;
        int hashCode4 = (hashCode3 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.radiuses;
        int hashCode5 = (hashCode4 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003;
        String str4 = this.bearings;
        int hashCode6 = (hashCode5 ^ (str4 == null ? 0 : str4.hashCode())) * 1000003;
        Boolean bool2 = this.lessVerbose;
        int hashCode7 = (((hashCode6 ^ (bool2 == null ? 0 : bool2.hashCode())) * 1000003) ^ this.geometries.hashCode()) * 1000003;
        String str5 = this.overview;
        int hashCode8 = (hashCode7 ^ (str5 == null ? 0 : str5.hashCode())) * 1000003;
        Boolean bool3 = this.steps;
        int hashCode9 = (hashCode8 ^ (bool3 == null ? 0 : bool3.hashCode())) * 1000003;
        String str6 = this.annotations;
        int hashCode10 = (hashCode9 ^ (str6 == null ? 0 : str6.hashCode())) * 1000003;
        String str7 = this.exclude;
        int hashCode11 = (hashCode10 ^ (str7 == null ? 0 : str7.hashCode())) * 1000003;
        Boolean bool4 = this.continueStraight;
        int hashCode12 = (hashCode11 ^ (bool4 == null ? 0 : bool4.hashCode())) * 1000003;
        Boolean bool5 = this.bannerInstructions;
        int hashCode13 = (hashCode12 ^ (bool5 == null ? 0 : bool5.hashCode())) * 1000003;
        Boolean bool6 = this.instructions;
        int hashCode14 = (hashCode13 ^ (bool6 == null ? 0 : bool6.hashCode())) * 1000003;
        Boolean bool7 = this.skipWaypoints;
        int hashCode15 = (hashCode14 ^ (bool7 == null ? 0 : bool7.hashCode())) * 1000003;
        String str8 = this.accessToken;
        int hashCode16 = (hashCode15 ^ (str8 == null ? 0 : str8.hashCode())) * 1000003;
        String str9 = this.requestUuid;
        int hashCode17 = (hashCode16 ^ (str9 == null ? 0 : str9.hashCode())) * 1000003;
        String str10 = this.sessionId;
        int hashCode18 = (hashCode17 ^ (str10 == null ? 0 : str10.hashCode())) * 1000003;
        String str11 = this.approaches;
        int hashCode19 = (hashCode18 ^ (str11 == null ? 0 : str11.hashCode())) * 1000003;
        String str12 = this.waypointIndices;
        int hashCode20 = (hashCode19 ^ (str12 == null ? 0 : str12.hashCode())) * 1000003;
        String str13 = this.waypointNames;
        int hashCode21 = (hashCode20 ^ (str13 == null ? 0 : str13.hashCode())) * 1000003;
        String str14 = this.waypointTargets;
        int hashCode22 = (hashCode21 ^ (str14 == null ? 0 : str14.hashCode())) * 1000003;
        WalkingOptions walkingOptions = this.walkingOptions;
        int hashCode23 = (hashCode22 ^ (walkingOptions == null ? 0 : walkingOptions.hashCode())) * 1000003;
        Integer num = this.routeType;
        int hashCode24 = (hashCode23 ^ (num == null ? 0 : num.hashCode())) * 1000003;
        Boolean bool8 = this.isSort;
        int hashCode25 = (hashCode24 ^ (bool8 == null ? 0 : bool8.hashCode())) * 1000003;
        Boolean bool9 = this.routeRefresh;
        int hashCode26 = (hashCode25 ^ (bool9 == null ? 0 : bool9.hashCode())) * 1000003;
        Boolean bool10 = this.roundaboutExits;
        return hashCode26 ^ (bool10 != null ? bool10.hashCode() : 0);
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteOptions
    @Nullable
    @SerializedName("instructions")
    public Boolean instructions() {
        return this.instructions;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteOptions
    @Nullable
    public Boolean isSort() {
        return this.isSort;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteOptions
    @Nullable
    public String language() {
        return this.language;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteOptions
    @Nullable
    @SerializedName("lessverbose")
    public Boolean lessVerbose() {
        return this.lessVerbose;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteOptions
    @Nullable
    public String overview() {
        return this.overview;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteOptions
    @NonNull
    public String profile() {
        return this.profile;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteOptions
    @Nullable
    public String radiuses() {
        return this.radiuses;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteOptions
    @Nullable
    @SerializedName("uuid")
    public String requestUuid() {
        return this.requestUuid;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteOptions
    @NonNull
    public String resource() {
        return this.resource;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteOptions
    @Nullable
    public Boolean roundaboutExits() {
        return this.roundaboutExits;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteOptions
    @Nullable
    public Boolean routeRefresh() {
        return this.routeRefresh;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteOptions
    @Nullable
    public Integer routeType() {
        return this.routeType;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteOptions
    @Nullable
    @SerializedName("sessionId")
    public String sessionId() {
        return this.sessionId;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteOptions
    @Nullable
    @SerializedName("skip_waypoints")
    public Boolean skipWaypoints() {
        return this.skipWaypoints;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteOptions
    @Nullable
    public Boolean steps() {
        return this.steps;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteOptions
    public RouteOptions.Builder toBuilder() {
        return new b(this);
    }

    public String toString() {
        return "RouteOptions{baseUrl=" + this.baseUrl + ", deviceID=" + this.deviceID + ", user=" + this.user + ", profile=" + this.profile + ", resource=" + this.resource + ", coordinates=" + this.coordinates + ", alternatives=" + this.alternatives + ", language=" + this.language + ", radiuses=" + this.radiuses + ", bearings=" + this.bearings + ", lessVerbose=" + this.lessVerbose + ", geometries=" + this.geometries + ", overview=" + this.overview + ", steps=" + this.steps + ", annotations=" + this.annotations + ", exclude=" + this.exclude + ", continueStraight=" + this.continueStraight + ", bannerInstructions=" + this.bannerInstructions + ", instructions=" + this.instructions + ", skipWaypoints=" + this.skipWaypoints + ", accessToken=" + this.accessToken + ", requestUuid=" + this.requestUuid + ", sessionId=" + this.sessionId + ", approaches=" + this.approaches + ", waypointIndices=" + this.waypointIndices + ", waypointNames=" + this.waypointNames + ", waypointTargets=" + this.waypointTargets + ", walkingOptions=" + this.walkingOptions + ", routeType=" + this.routeType + ", isSort=" + this.isSort + ", routeRefresh=" + this.routeRefresh + ", roundaboutExits=" + this.roundaboutExits + "}";
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteOptions
    @NonNull
    public String user() {
        return this.user;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteOptions
    @Nullable
    public WalkingOptions walkingOptions() {
        return this.walkingOptions;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteOptions
    @Nullable
    @SerializedName("waypoints")
    public String waypointIndices() {
        return this.waypointIndices;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteOptions
    @Nullable
    @SerializedName("waypoint_names")
    public String waypointNames() {
        return this.waypointNames;
    }

    @Override // com.mappls.sdk.services.api.directions.models.RouteOptions
    @Nullable
    @SerializedName("waypoint_targets")
    public String waypointTargets() {
        return this.waypointTargets;
    }
}

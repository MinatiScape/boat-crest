package com.mappls.sdk.direction.ui.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import com.mappls.sdk.direction.ui.model.DirectionOptions;
import com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions;
import java.util.List;
import java.util.Objects;
/* loaded from: classes11.dex */
public abstract class b extends DirectionOptions {
    public final DirectionPoint A;
    public final Boolean B;
    public final Boolean C;
    public final Integer D;
    public final Integer E;
    public final Integer F;
    public final Integer G;
    public final Integer H;
    public final Integer I;
    public final Integer J;
    public final Integer K;
    public final Integer L;
    public final Integer M;
    public final Integer N;
    public final Integer O;
    public final Boolean P;
    public final Boolean Q;
    public final Boolean R;
    public final Boolean S;
    public final Boolean h;
    public final Boolean i;
    public final Integer j;
    public final Integer k;
    public final Integer l;
    public final String m;
    public final String n;
    public final String o;
    public final List<String> p;
    public final Boolean q;
    public final Boolean r;
    public final List<String> s;
    public final Integer t;
    public final Boolean u;
    public final String v;
    public final Boolean w;
    public final Boolean x;
    public final PlaceOptions y;
    public final DirectionPoint z;

    /* loaded from: classes11.dex */
    public static class a extends DirectionOptions.Builder {
        public Integer A;
        public Integer B;
        public Integer C;
        public Integer D;
        public Integer E;
        public Integer F;
        public Integer G;
        public Integer H;
        public Boolean I;
        public Boolean J;
        public Boolean K;
        public Boolean L;

        /* renamed from: a  reason: collision with root package name */
        public Boolean f12601a;
        public Boolean b;
        public Integer c;
        public Integer d;
        public Integer e;
        public String f;
        public String g;
        public String h;
        public List<String> i;
        public Boolean j;
        public Boolean k;
        public List<String> l;
        public Integer m;
        public Boolean n;
        public String o;
        public Boolean p;
        public Boolean q;
        public PlaceOptions r;
        public DirectionPoint s;
        public DirectionPoint t;
        public Boolean u;
        public Boolean v;
        public Integer w;
        public Integer x;
        public Integer y;
        public Integer z;

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions.Builder alongRouteBuffer(@Nullable Integer num) {
            this.w = num;
            return this;
        }

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions.Builder alongRouteDarkTheme(Integer num) {
            Objects.requireNonNull(num, "Null alongRouteDarkTheme");
            this.y = num;
            return this;
        }

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions.Builder alongRouteDayTheme(Integer num) {
            Objects.requireNonNull(num, "Null alongRouteDayTheme");
            this.x = num;
            return this;
        }

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions.Builder alternateCasingRouteColor(Integer num) {
            Objects.requireNonNull(num, "Null alternateCasingRouteColor");
            this.C = num;
            return this;
        }

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions.Builder alternateRouteColor(Integer num) {
            Objects.requireNonNull(num, "Null alternateRouteColor");
            this.B = num;
            return this;
        }

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions.Builder annotation(@Nullable List<String> list) {
            this.i = list;
            return this;
        }

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions build() {
            String str = this.f12601a == null ? " showProfileOption" : "";
            if (this.b == null) {
                str = com.mappls.sdk.direction.ui.model.a.a(str, " showAlternative");
            }
            if (this.c == null) {
                str = com.mappls.sdk.direction.ui.model.a.a(str, " theme");
            }
            if (this.d == null) {
                str = com.mappls.sdk.direction.ui.model.a.a(str, " directionDayTheme");
            }
            if (this.e == null) {
                str = com.mappls.sdk.direction.ui.model.a.a(str, " directionDarkTheme");
            }
            if (this.f == null) {
                str = com.mappls.sdk.direction.ui.model.a.a(str, " resource");
            }
            if (this.g == null) {
                str = com.mappls.sdk.direction.ui.model.a.a(str, " profile");
            }
            if (this.h == null) {
                str = com.mappls.sdk.direction.ui.model.a.a(str, " overview");
            }
            if (this.j == null) {
                str = com.mappls.sdk.direction.ui.model.a.a(str, " steps");
            }
            if (this.o == null) {
                str = com.mappls.sdk.direction.ui.model.a.a(str, " geometries");
            }
            if (this.q == null) {
                str = com.mappls.sdk.direction.ui.model.a.a(str, " showStartNavigation");
            }
            if (this.r == null) {
                str = com.mappls.sdk.direction.ui.model.a.a(str, " searchPlaceOption");
            }
            if (this.u == null) {
                str = com.mappls.sdk.direction.ui.model.a.a(str, " showDefaultMap");
            }
            if (this.v == null) {
                str = com.mappls.sdk.direction.ui.model.a.a(str, " searchAlongRoute");
            }
            if (this.x == null) {
                str = com.mappls.sdk.direction.ui.model.a.a(str, " alongRouteDayTheme");
            }
            if (this.y == null) {
                str = com.mappls.sdk.direction.ui.model.a.a(str, " alongRouteDarkTheme");
            }
            if (this.z == null) {
                str = com.mappls.sdk.direction.ui.model.a.a(str, " selectedRouteColor");
            }
            if (this.A == null) {
                str = com.mappls.sdk.direction.ui.model.a.a(str, " selectedCasingRouteColor");
            }
            if (this.B == null) {
                str = com.mappls.sdk.direction.ui.model.a.a(str, " alternateRouteColor");
            }
            if (this.C == null) {
                str = com.mappls.sdk.direction.ui.model.a.a(str, " alternateCasingRouteColor");
            }
            if (this.D == null) {
                str = com.mappls.sdk.direction.ui.model.a.a(str, " destinationMarker");
            }
            if (this.E == null) {
                str = com.mappls.sdk.direction.ui.model.a.a(str, " sourceMarker");
            }
            if (this.F == null) {
                str = com.mappls.sdk.direction.ui.model.a.a(str, " firstWayPointMarker");
            }
            if (this.G == null) {
                str = com.mappls.sdk.direction.ui.model.a.a(str, " secondWayPointMarker");
            }
            if (this.H == null) {
                str = com.mappls.sdk.direction.ui.model.a.a(str, " thirdWayPointMarker");
            }
            if (this.I == null) {
                str = com.mappls.sdk.direction.ui.model.a.a(str, " showAddWaypointOption");
            }
            if (this.J == null) {
                str = com.mappls.sdk.direction.ui.model.a.a(str, " showRouteReportSummary");
            }
            if (this.K == null) {
                str = com.mappls.sdk.direction.ui.model.a.a(str, " showRouteReportSummaryOnMap");
            }
            if (this.L == null) {
                str = com.mappls.sdk.direction.ui.model.a.a(str, " showTripCostSummary");
            }
            if (str.isEmpty()) {
                return new c(this.f12601a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.r, this.s, this.t, this.u, this.v, this.w, this.x, this.y, this.z, this.A, this.B, this.C, this.D, this.E, this.F, this.G, this.H, this.I, this.J, this.K, this.L);
            }
            throw new IllegalStateException("Missing required properties:".concat(str));
        }

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions.Builder destination(@Nullable DirectionPoint directionPoint) {
            this.s = directionPoint;
            return this;
        }

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions.Builder destinationMarker(Integer num) {
            Objects.requireNonNull(num, "Null destinationMarker");
            this.D = num;
            return this;
        }

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions.Builder directionDarkTheme(Integer num) {
            Objects.requireNonNull(num, "Null directionDarkTheme");
            this.e = num;
            return this;
        }

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions.Builder directionDayTheme(Integer num) {
            Objects.requireNonNull(num, "Null directionDayTheme");
            this.d = num;
            return this;
        }

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions.Builder excludes(@Nullable List<String> list) {
            this.l = list;
            return this;
        }

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions.Builder firstWayPointMarker(Integer num) {
            Objects.requireNonNull(num, "Null firstWayPointMarker");
            this.F = num;
            return this;
        }

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions.Builder geometries(String str) {
            Objects.requireNonNull(str, "Null geometries");
            this.o = str;
            return this;
        }

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions.Builder instructions(@Nullable Boolean bool) {
            this.p = bool;
            return this;
        }

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions.Builder isSort(@Nullable Boolean bool) {
            this.k = bool;
            return this;
        }

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions.Builder lessVerbose(@Nullable Boolean bool) {
            this.n = bool;
            return this;
        }

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions.Builder origin(@Nullable DirectionPoint directionPoint) {
            this.t = directionPoint;
            return this;
        }

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions.Builder overview(String str) {
            Objects.requireNonNull(str, "Null overview");
            this.h = str;
            return this;
        }

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions.Builder profile(String str) {
            Objects.requireNonNull(str, "Null profile");
            this.g = str;
            return this;
        }

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions.Builder resource(String str) {
            Objects.requireNonNull(str, "Null resource");
            this.f = str;
            return this;
        }

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions.Builder routeType(@Nullable Integer num) {
            this.m = num;
            return this;
        }

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions.Builder searchAlongRoute(Boolean bool) {
            Objects.requireNonNull(bool, "Null searchAlongRoute");
            this.v = bool;
            return this;
        }

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions.Builder searchPlaceOption(PlaceOptions placeOptions) {
            Objects.requireNonNull(placeOptions, "Null searchPlaceOption");
            this.r = placeOptions;
            return this;
        }

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions.Builder secondWayPointMarker(Integer num) {
            Objects.requireNonNull(num, "Null secondWayPointMarker");
            this.G = num;
            return this;
        }

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions.Builder selectedCasingRouteColor(Integer num) {
            Objects.requireNonNull(num, "Null selectedCasingRouteColor");
            this.A = num;
            return this;
        }

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions.Builder selectedRouteColor(Integer num) {
            Objects.requireNonNull(num, "Null selectedRouteColor");
            this.z = num;
            return this;
        }

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions.Builder showAddWaypointOption(Boolean bool) {
            Objects.requireNonNull(bool, "Null showAddWaypointOption");
            this.I = bool;
            return this;
        }

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions.Builder showAlternative(Boolean bool) {
            Objects.requireNonNull(bool, "Null showAlternative");
            this.b = bool;
            return this;
        }

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions.Builder showDefaultMap(Boolean bool) {
            Objects.requireNonNull(bool, "Null showDefaultMap");
            this.u = bool;
            return this;
        }

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions.Builder showProfileOption(Boolean bool) {
            Objects.requireNonNull(bool, "Null showProfileOption");
            this.f12601a = bool;
            return this;
        }

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions.Builder showRouteReportSummary(Boolean bool) {
            Objects.requireNonNull(bool, "Null showRouteReportSummary");
            this.J = bool;
            return this;
        }

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions.Builder showRouteReportSummaryOnMap(Boolean bool) {
            Objects.requireNonNull(bool, "Null showRouteReportSummaryOnMap");
            this.K = bool;
            return this;
        }

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions.Builder showStartNavigation(Boolean bool) {
            Objects.requireNonNull(bool, "Null showStartNavigation");
            this.q = bool;
            return this;
        }

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions.Builder showTripCostSummary(Boolean bool) {
            Objects.requireNonNull(bool, "Null showTripCostSummary");
            this.L = bool;
            return this;
        }

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions.Builder sourceMarker(Integer num) {
            Objects.requireNonNull(num, "Null sourceMarker");
            this.E = num;
            return this;
        }

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions.Builder steps(Boolean bool) {
            Objects.requireNonNull(bool, "Null steps");
            this.j = bool;
            return this;
        }

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions.Builder theme(Integer num) {
            Objects.requireNonNull(num, "Null theme");
            this.c = num;
            return this;
        }

        @Override // com.mappls.sdk.direction.ui.model.DirectionOptions.Builder
        public final DirectionOptions.Builder thirdWayPointMarker(Integer num) {
            Objects.requireNonNull(num, "Null thirdWayPointMarker");
            this.H = num;
            return this;
        }
    }

    public b(Boolean bool, Boolean bool2, Integer num, Integer num2, Integer num3, String str, String str2, String str3, @Nullable List<String> list, Boolean bool3, @Nullable Boolean bool4, @Nullable List<String> list2, @Nullable Integer num4, @Nullable Boolean bool5, String str4, @Nullable Boolean bool6, Boolean bool7, PlaceOptions placeOptions, @Nullable DirectionPoint directionPoint, @Nullable DirectionPoint directionPoint2, Boolean bool8, Boolean bool9, @Nullable Integer num5, Integer num6, Integer num7, Integer num8, Integer num9, Integer num10, Integer num11, Integer num12, Integer num13, Integer num14, Integer num15, Integer num16, Boolean bool10, Boolean bool11, Boolean bool12, Boolean bool13) {
        Objects.requireNonNull(bool, "Null showProfileOption");
        this.h = bool;
        Objects.requireNonNull(bool2, "Null showAlternative");
        this.i = bool2;
        Objects.requireNonNull(num, "Null theme");
        this.j = num;
        Objects.requireNonNull(num2, "Null directionDayTheme");
        this.k = num2;
        Objects.requireNonNull(num3, "Null directionDarkTheme");
        this.l = num3;
        Objects.requireNonNull(str, "Null resource");
        this.m = str;
        Objects.requireNonNull(str2, "Null profile");
        this.n = str2;
        Objects.requireNonNull(str3, "Null overview");
        this.o = str3;
        this.p = list;
        Objects.requireNonNull(bool3, "Null steps");
        this.q = bool3;
        this.r = bool4;
        this.s = list2;
        this.t = num4;
        this.u = bool5;
        Objects.requireNonNull(str4, "Null geometries");
        this.v = str4;
        this.w = bool6;
        Objects.requireNonNull(bool7, "Null showStartNavigation");
        this.x = bool7;
        Objects.requireNonNull(placeOptions, "Null searchPlaceOption");
        this.y = placeOptions;
        this.z = directionPoint;
        this.A = directionPoint2;
        Objects.requireNonNull(bool8, "Null showDefaultMap");
        this.B = bool8;
        Objects.requireNonNull(bool9, "Null searchAlongRoute");
        this.C = bool9;
        this.D = num5;
        Objects.requireNonNull(num6, "Null alongRouteDayTheme");
        this.E = num6;
        Objects.requireNonNull(num7, "Null alongRouteDarkTheme");
        this.F = num7;
        Objects.requireNonNull(num8, "Null selectedRouteColor");
        this.G = num8;
        Objects.requireNonNull(num9, "Null selectedCasingRouteColor");
        this.H = num9;
        Objects.requireNonNull(num10, "Null alternateRouteColor");
        this.I = num10;
        Objects.requireNonNull(num11, "Null alternateCasingRouteColor");
        this.J = num11;
        Objects.requireNonNull(num12, "Null destinationMarker");
        this.K = num12;
        Objects.requireNonNull(num13, "Null sourceMarker");
        this.L = num13;
        Objects.requireNonNull(num14, "Null firstWayPointMarker");
        this.M = num14;
        Objects.requireNonNull(num15, "Null secondWayPointMarker");
        this.N = num15;
        Objects.requireNonNull(num16, "Null thirdWayPointMarker");
        this.O = num16;
        Objects.requireNonNull(bool10, "Null showAddWaypointOption");
        this.P = bool10;
        Objects.requireNonNull(bool11, "Null showRouteReportSummary");
        this.Q = bool11;
        Objects.requireNonNull(bool12, "Null showRouteReportSummaryOnMap");
        this.R = bool12;
        Objects.requireNonNull(bool13, "Null showTripCostSummary");
        this.S = bool13;
    }

    @Override // com.mappls.sdk.direction.ui.model.DirectionOptions
    @Nullable
    public final Integer alongRouteBuffer() {
        return this.D;
    }

    @Override // com.mappls.sdk.direction.ui.model.DirectionOptions
    @NonNull
    public final Integer alongRouteDarkTheme() {
        return this.F;
    }

    @Override // com.mappls.sdk.direction.ui.model.DirectionOptions
    @NonNull
    public final Integer alongRouteDayTheme() {
        return this.E;
    }

    @Override // com.mappls.sdk.direction.ui.model.DirectionOptions
    @NonNull
    public final Integer alternateCasingRouteColor() {
        return this.J;
    }

    @Override // com.mappls.sdk.direction.ui.model.DirectionOptions
    @NonNull
    public final Integer alternateRouteColor() {
        return this.I;
    }

    @Override // com.mappls.sdk.direction.ui.model.DirectionOptions
    @Nullable
    public final List<String> annotation() {
        return this.p;
    }

    @Override // com.mappls.sdk.direction.ui.model.DirectionOptions
    @Nullable
    public final DirectionPoint destination() {
        return this.z;
    }

    @Override // com.mappls.sdk.direction.ui.model.DirectionOptions
    @NonNull
    public final Integer destinationMarker() {
        return this.K;
    }

    @Override // com.mappls.sdk.direction.ui.model.DirectionOptions
    @NonNull
    @StyleRes
    public final Integer directionDarkTheme() {
        return this.l;
    }

    @Override // com.mappls.sdk.direction.ui.model.DirectionOptions
    @NonNull
    @StyleRes
    public final Integer directionDayTheme() {
        return this.k;
    }

    public final boolean equals(Object obj) {
        List<String> list;
        Boolean bool;
        List<String> list2;
        Integer num;
        Boolean bool2;
        Boolean bool3;
        DirectionPoint directionPoint;
        DirectionPoint directionPoint2;
        Integer num2;
        if (obj == this) {
            return true;
        }
        if (obj instanceof DirectionOptions) {
            DirectionOptions directionOptions = (DirectionOptions) obj;
            return this.h.equals(directionOptions.showProfileOption()) && this.i.equals(directionOptions.showAlternative()) && this.j.equals(directionOptions.theme()) && this.k.equals(directionOptions.directionDayTheme()) && this.l.equals(directionOptions.directionDarkTheme()) && this.m.equals(directionOptions.resource()) && this.n.equals(directionOptions.profile()) && this.o.equals(directionOptions.overview()) && ((list = this.p) != null ? list.equals(directionOptions.annotation()) : directionOptions.annotation() == null) && this.q.equals(directionOptions.steps()) && ((bool = this.r) != null ? bool.equals(directionOptions.isSort()) : directionOptions.isSort() == null) && ((list2 = this.s) != null ? list2.equals(directionOptions.excludes()) : directionOptions.excludes() == null) && ((num = this.t) != null ? num.equals(directionOptions.routeType()) : directionOptions.routeType() == null) && ((bool2 = this.u) != null ? bool2.equals(directionOptions.lessVerbose()) : directionOptions.lessVerbose() == null) && this.v.equals(directionOptions.geometries()) && ((bool3 = this.w) != null ? bool3.equals(directionOptions.instructions()) : directionOptions.instructions() == null) && this.x.equals(directionOptions.showStartNavigation()) && this.y.equals(directionOptions.searchPlaceOption()) && ((directionPoint = this.z) != null ? directionPoint.equals(directionOptions.destination()) : directionOptions.destination() == null) && ((directionPoint2 = this.A) != null ? directionPoint2.equals(directionOptions.origin()) : directionOptions.origin() == null) && this.B.equals(directionOptions.showDefaultMap()) && this.C.equals(directionOptions.searchAlongRoute()) && ((num2 = this.D) != null ? num2.equals(directionOptions.alongRouteBuffer()) : directionOptions.alongRouteBuffer() == null) && this.E.equals(directionOptions.alongRouteDayTheme()) && this.F.equals(directionOptions.alongRouteDarkTheme()) && this.G.equals(directionOptions.selectedRouteColor()) && this.H.equals(directionOptions.selectedCasingRouteColor()) && this.I.equals(directionOptions.alternateRouteColor()) && this.J.equals(directionOptions.alternateCasingRouteColor()) && this.K.equals(directionOptions.destinationMarker()) && this.L.equals(directionOptions.sourceMarker()) && this.M.equals(directionOptions.firstWayPointMarker()) && this.N.equals(directionOptions.secondWayPointMarker()) && this.O.equals(directionOptions.thirdWayPointMarker()) && this.P.equals(directionOptions.showAddWaypointOption()) && this.Q.equals(directionOptions.showRouteReportSummary()) && this.R.equals(directionOptions.showRouteReportSummaryOnMap()) && this.S.equals(directionOptions.showTripCostSummary());
        }
        return false;
    }

    @Override // com.mappls.sdk.direction.ui.model.DirectionOptions
    @Nullable
    public final List<String> excludes() {
        return this.s;
    }

    @Override // com.mappls.sdk.direction.ui.model.DirectionOptions
    @NonNull
    public final Integer firstWayPointMarker() {
        return this.M;
    }

    @Override // com.mappls.sdk.direction.ui.model.DirectionOptions
    @NonNull
    public final String geometries() {
        return this.v;
    }

    public final int hashCode() {
        int hashCode = (((((((((((((((this.h.hashCode() ^ 1000003) * 1000003) ^ this.i.hashCode()) * 1000003) ^ this.j.hashCode()) * 1000003) ^ this.k.hashCode()) * 1000003) ^ this.l.hashCode()) * 1000003) ^ this.m.hashCode()) * 1000003) ^ this.n.hashCode()) * 1000003) ^ this.o.hashCode()) * 1000003;
        List<String> list = this.p;
        int hashCode2 = (((hashCode ^ (list == null ? 0 : list.hashCode())) * 1000003) ^ this.q.hashCode()) * 1000003;
        Boolean bool = this.r;
        int hashCode3 = (hashCode2 ^ (bool == null ? 0 : bool.hashCode())) * 1000003;
        List<String> list2 = this.s;
        int hashCode4 = (hashCode3 ^ (list2 == null ? 0 : list2.hashCode())) * 1000003;
        Integer num = this.t;
        int hashCode5 = (hashCode4 ^ (num == null ? 0 : num.hashCode())) * 1000003;
        Boolean bool2 = this.u;
        int hashCode6 = (((hashCode5 ^ (bool2 == null ? 0 : bool2.hashCode())) * 1000003) ^ this.v.hashCode()) * 1000003;
        Boolean bool3 = this.w;
        int hashCode7 = (((((hashCode6 ^ (bool3 == null ? 0 : bool3.hashCode())) * 1000003) ^ this.x.hashCode()) * 1000003) ^ this.y.hashCode()) * 1000003;
        DirectionPoint directionPoint = this.z;
        int hashCode8 = (hashCode7 ^ (directionPoint == null ? 0 : directionPoint.hashCode())) * 1000003;
        DirectionPoint directionPoint2 = this.A;
        int hashCode9 = (((((hashCode8 ^ (directionPoint2 == null ? 0 : directionPoint2.hashCode())) * 1000003) ^ this.B.hashCode()) * 1000003) ^ this.C.hashCode()) * 1000003;
        Integer num2 = this.D;
        return ((((((((((((((((((((((((((((((hashCode9 ^ (num2 != null ? num2.hashCode() : 0)) * 1000003) ^ this.E.hashCode()) * 1000003) ^ this.F.hashCode()) * 1000003) ^ this.G.hashCode()) * 1000003) ^ this.H.hashCode()) * 1000003) ^ this.I.hashCode()) * 1000003) ^ this.J.hashCode()) * 1000003) ^ this.K.hashCode()) * 1000003) ^ this.L.hashCode()) * 1000003) ^ this.M.hashCode()) * 1000003) ^ this.N.hashCode()) * 1000003) ^ this.O.hashCode()) * 1000003) ^ this.P.hashCode()) * 1000003) ^ this.Q.hashCode()) * 1000003) ^ this.R.hashCode()) * 1000003) ^ this.S.hashCode();
    }

    @Override // com.mappls.sdk.direction.ui.model.DirectionOptions
    @Nullable
    public final Boolean instructions() {
        return this.w;
    }

    @Override // com.mappls.sdk.direction.ui.model.DirectionOptions
    @Nullable
    public final Boolean isSort() {
        return this.r;
    }

    @Override // com.mappls.sdk.direction.ui.model.DirectionOptions
    @Nullable
    public final Boolean lessVerbose() {
        return this.u;
    }

    @Override // com.mappls.sdk.direction.ui.model.DirectionOptions
    @Nullable
    public final DirectionPoint origin() {
        return this.A;
    }

    @Override // com.mappls.sdk.direction.ui.model.DirectionOptions
    @NonNull
    public final String overview() {
        return this.o;
    }

    @Override // com.mappls.sdk.direction.ui.model.DirectionOptions
    @NonNull
    public final String profile() {
        return this.n;
    }

    @Override // com.mappls.sdk.direction.ui.model.DirectionOptions
    @NonNull
    public final String resource() {
        return this.m;
    }

    @Override // com.mappls.sdk.direction.ui.model.DirectionOptions
    @Nullable
    public final Integer routeType() {
        return this.t;
    }

    @Override // com.mappls.sdk.direction.ui.model.DirectionOptions
    @NonNull
    public final Boolean searchAlongRoute() {
        return this.C;
    }

    @Override // com.mappls.sdk.direction.ui.model.DirectionOptions
    @NonNull
    public final PlaceOptions searchPlaceOption() {
        return this.y;
    }

    @Override // com.mappls.sdk.direction.ui.model.DirectionOptions
    @NonNull
    public final Integer secondWayPointMarker() {
        return this.N;
    }

    @Override // com.mappls.sdk.direction.ui.model.DirectionOptions
    @NonNull
    public final Integer selectedCasingRouteColor() {
        return this.H;
    }

    @Override // com.mappls.sdk.direction.ui.model.DirectionOptions
    @NonNull
    public final Integer selectedRouteColor() {
        return this.G;
    }

    @Override // com.mappls.sdk.direction.ui.model.DirectionOptions
    @NonNull
    public final Boolean showAddWaypointOption() {
        return this.P;
    }

    @Override // com.mappls.sdk.direction.ui.model.DirectionOptions
    @NonNull
    public final Boolean showAlternative() {
        return this.i;
    }

    @Override // com.mappls.sdk.direction.ui.model.DirectionOptions
    @NonNull
    public final Boolean showDefaultMap() {
        return this.B;
    }

    @Override // com.mappls.sdk.direction.ui.model.DirectionOptions
    @NonNull
    public final Boolean showProfileOption() {
        return this.h;
    }

    @Override // com.mappls.sdk.direction.ui.model.DirectionOptions
    @NonNull
    public final Boolean showRouteReportSummary() {
        return this.Q;
    }

    @Override // com.mappls.sdk.direction.ui.model.DirectionOptions
    @NonNull
    public final Boolean showRouteReportSummaryOnMap() {
        return this.R;
    }

    @Override // com.mappls.sdk.direction.ui.model.DirectionOptions
    @NonNull
    public final Boolean showStartNavigation() {
        return this.x;
    }

    @Override // com.mappls.sdk.direction.ui.model.DirectionOptions
    @NonNull
    public final Boolean showTripCostSummary() {
        return this.S;
    }

    @Override // com.mappls.sdk.direction.ui.model.DirectionOptions
    @NonNull
    public final Integer sourceMarker() {
        return this.L;
    }

    @Override // com.mappls.sdk.direction.ui.model.DirectionOptions
    @NonNull
    public final Boolean steps() {
        return this.q;
    }

    @Override // com.mappls.sdk.direction.ui.model.DirectionOptions
    @NonNull
    public final Integer theme() {
        return this.j;
    }

    @Override // com.mappls.sdk.direction.ui.model.DirectionOptions
    @NonNull
    public final Integer thirdWayPointMarker() {
        return this.O;
    }

    public final String toString() {
        return "DirectionOptions{showProfileOption=" + this.h + ", showAlternative=" + this.i + ", theme=" + this.j + ", directionDayTheme=" + this.k + ", directionDarkTheme=" + this.l + ", resource=" + this.m + ", profile=" + this.n + ", overview=" + this.o + ", annotation=" + this.p + ", steps=" + this.q + ", isSort=" + this.r + ", excludes=" + this.s + ", routeType=" + this.t + ", lessVerbose=" + this.u + ", geometries=" + this.v + ", instructions=" + this.w + ", showStartNavigation=" + this.x + ", searchPlaceOption=" + this.y + ", destination=" + this.z + ", origin=" + this.A + ", showDefaultMap=" + this.B + ", searchAlongRoute=" + this.C + ", alongRouteBuffer=" + this.D + ", alongRouteDayTheme=" + this.E + ", alongRouteDarkTheme=" + this.F + ", selectedRouteColor=" + this.G + ", selectedCasingRouteColor=" + this.H + ", alternateRouteColor=" + this.I + ", alternateCasingRouteColor=" + this.J + ", destinationMarker=" + this.K + ", sourceMarker=" + this.L + ", firstWayPointMarker=" + this.M + ", secondWayPointMarker=" + this.N + ", thirdWayPointMarker=" + this.O + ", showAddWaypointOption=" + this.P + ", showRouteReportSummary=" + this.Q + ", showRouteReportSummaryOnMap=" + this.R + ", showTripCostSummary=" + this.S + "}";
    }
}

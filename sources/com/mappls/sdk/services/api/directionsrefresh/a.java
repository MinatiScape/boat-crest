package com.mappls.sdk.services.api.directionsrefresh;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.services.api.directionsrefresh.MapplsDirectionsRefresh;
import java.util.Objects;
/* loaded from: classes11.dex */
public final class a extends MapplsDirectionsRefresh {

    /* renamed from: a  reason: collision with root package name */
    public final String f13205a;
    public final String b;
    public final Integer c;
    public final Boolean d;
    public final Boolean e;
    public final Long f;
    public final String g;
    public final Integer h;
    public final String i;
    public final String j;
    public final Boolean k;
    public final String l;

    /* loaded from: classes11.dex */
    public static final class b extends MapplsDirectionsRefresh.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13206a;
        public String b;
        public Integer c;
        public Boolean d;
        public Boolean e;
        public Long f;
        public String g;
        public Integer h;
        public String i;
        public String j;
        public Boolean k;
        public String l;

        @Override // com.mappls.sdk.services.api.directionsrefresh.MapplsDirectionsRefresh.Builder
        public MapplsDirectionsRefresh.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.l = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directionsrefresh.MapplsDirectionsRefresh.Builder
        public MapplsDirectionsRefresh build() {
            String str = "";
            if (this.f13206a == null) {
                str = " profile";
            }
            if (this.b == null) {
                str = str + " requestId";
            }
            if (this.c == null) {
                str = str + " routeIndex";
            }
            if (this.h == null) {
                str = str + " tripType";
            }
            if (this.l == null) {
                str = str + " baseUrl";
            }
            if (str.isEmpty()) {
                return new a(this.f13206a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.directionsrefresh.MapplsDirectionsRefresh.Builder
        public MapplsDirectionsRefresh.Builder categories(@Nullable String str) {
            this.g = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directionsrefresh.MapplsDirectionsRefresh.Builder
        public MapplsDirectionsRefresh.Builder isNotify(@Nullable Boolean bool) {
            this.e = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directionsrefresh.MapplsDirectionsRefresh.Builder
        public MapplsDirectionsRefresh.Builder isRefresh(Boolean bool) {
            this.d = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directionsrefresh.MapplsDirectionsRefresh.Builder
        public MapplsDirectionsRefresh.Builder isSort(@Nullable Boolean bool) {
            this.k = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directionsrefresh.MapplsDirectionsRefresh.Builder
        public MapplsDirectionsRefresh.Builder nodeIndex(@Nullable Long l) {
            this.f = l;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directionsrefresh.MapplsDirectionsRefresh.Builder
        public MapplsDirectionsRefresh.Builder profile(String str) {
            Objects.requireNonNull(str, "Null profile");
            this.f13206a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directionsrefresh.MapplsDirectionsRefresh.Builder
        public MapplsDirectionsRefresh.Builder requestId(String str) {
            Objects.requireNonNull(str, "Null requestId");
            this.b = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directionsrefresh.MapplsDirectionsRefresh.Builder
        public MapplsDirectionsRefresh.Builder routeIndex(Integer num) {
            Objects.requireNonNull(num, "Null routeIndex");
            this.c = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directionsrefresh.MapplsDirectionsRefresh.Builder
        public MapplsDirectionsRefresh.Builder sessionId(String str) {
            this.i = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directionsrefresh.MapplsDirectionsRefresh.Builder
        public MapplsDirectionsRefresh.Builder sourceInternal(@Nullable String str) {
            this.j = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directionsrefresh.MapplsDirectionsRefresh.Builder
        public MapplsDirectionsRefresh.Builder tripType(Integer num) {
            Objects.requireNonNull(num, "Null tripType");
            this.h = num;
            return this;
        }

        public b() {
        }

        public b(MapplsDirectionsRefresh mapplsDirectionsRefresh) {
            this.f13206a = mapplsDirectionsRefresh.profile();
            this.b = mapplsDirectionsRefresh.requestId();
            this.c = mapplsDirectionsRefresh.routeIndex();
            this.d = mapplsDirectionsRefresh.isRefresh();
            this.e = mapplsDirectionsRefresh.isNotify();
            this.f = mapplsDirectionsRefresh.nodeIndex();
            this.g = mapplsDirectionsRefresh.categories();
            this.h = mapplsDirectionsRefresh.tripType();
            this.i = mapplsDirectionsRefresh.sessionId();
            this.j = mapplsDirectionsRefresh.sourceInternal();
            this.k = mapplsDirectionsRefresh.isSort();
            this.l = mapplsDirectionsRefresh.baseUrl();
        }
    }

    @Override // com.mappls.sdk.services.api.directionsrefresh.MapplsDirectionsRefresh, com.mappls.sdk.services.api.MapplsService
    @NonNull
    public String baseUrl() {
        return this.l;
    }

    @Override // com.mappls.sdk.services.api.directionsrefresh.MapplsDirectionsRefresh
    @Nullable
    public String categories() {
        return this.g;
    }

    public boolean equals(Object obj) {
        Boolean bool;
        Boolean bool2;
        Long l;
        String str;
        String str2;
        String str3;
        Boolean bool3;
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsDirectionsRefresh) {
            MapplsDirectionsRefresh mapplsDirectionsRefresh = (MapplsDirectionsRefresh) obj;
            return this.f13205a.equals(mapplsDirectionsRefresh.profile()) && this.b.equals(mapplsDirectionsRefresh.requestId()) && this.c.equals(mapplsDirectionsRefresh.routeIndex()) && ((bool = this.d) != null ? bool.equals(mapplsDirectionsRefresh.isRefresh()) : mapplsDirectionsRefresh.isRefresh() == null) && ((bool2 = this.e) != null ? bool2.equals(mapplsDirectionsRefresh.isNotify()) : mapplsDirectionsRefresh.isNotify() == null) && ((l = this.f) != null ? l.equals(mapplsDirectionsRefresh.nodeIndex()) : mapplsDirectionsRefresh.nodeIndex() == null) && ((str = this.g) != null ? str.equals(mapplsDirectionsRefresh.categories()) : mapplsDirectionsRefresh.categories() == null) && this.h.equals(mapplsDirectionsRefresh.tripType()) && ((str2 = this.i) != null ? str2.equals(mapplsDirectionsRefresh.sessionId()) : mapplsDirectionsRefresh.sessionId() == null) && ((str3 = this.j) != null ? str3.equals(mapplsDirectionsRefresh.sourceInternal()) : mapplsDirectionsRefresh.sourceInternal() == null) && ((bool3 = this.k) != null ? bool3.equals(mapplsDirectionsRefresh.isSort()) : mapplsDirectionsRefresh.isSort() == null) && this.l.equals(mapplsDirectionsRefresh.baseUrl());
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((((this.f13205a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode()) * 1000003;
        Boolean bool = this.d;
        int hashCode2 = (hashCode ^ (bool == null ? 0 : bool.hashCode())) * 1000003;
        Boolean bool2 = this.e;
        int hashCode3 = (hashCode2 ^ (bool2 == null ? 0 : bool2.hashCode())) * 1000003;
        Long l = this.f;
        int hashCode4 = (hashCode3 ^ (l == null ? 0 : l.hashCode())) * 1000003;
        String str = this.g;
        int hashCode5 = (((hashCode4 ^ (str == null ? 0 : str.hashCode())) * 1000003) ^ this.h.hashCode()) * 1000003;
        String str2 = this.i;
        int hashCode6 = (hashCode5 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.j;
        int hashCode7 = (hashCode6 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003;
        Boolean bool3 = this.k;
        return ((hashCode7 ^ (bool3 != null ? bool3.hashCode() : 0)) * 1000003) ^ this.l.hashCode();
    }

    @Override // com.mappls.sdk.services.api.directionsrefresh.MapplsDirectionsRefresh
    @Nullable
    public Boolean isNotify() {
        return this.e;
    }

    @Override // com.mappls.sdk.services.api.directionsrefresh.MapplsDirectionsRefresh
    @Nullable
    public Boolean isRefresh() {
        return this.d;
    }

    @Override // com.mappls.sdk.services.api.directionsrefresh.MapplsDirectionsRefresh
    @Nullable
    public Boolean isSort() {
        return this.k;
    }

    @Override // com.mappls.sdk.services.api.directionsrefresh.MapplsDirectionsRefresh
    @Nullable
    public Long nodeIndex() {
        return this.f;
    }

    @Override // com.mappls.sdk.services.api.directionsrefresh.MapplsDirectionsRefresh
    @NonNull
    public String profile() {
        return this.f13205a;
    }

    @Override // com.mappls.sdk.services.api.directionsrefresh.MapplsDirectionsRefresh
    public String requestId() {
        return this.b;
    }

    @Override // com.mappls.sdk.services.api.directionsrefresh.MapplsDirectionsRefresh
    public Integer routeIndex() {
        return this.c;
    }

    @Override // com.mappls.sdk.services.api.directionsrefresh.MapplsDirectionsRefresh
    @Nullable
    public String sessionId() {
        return this.i;
    }

    @Override // com.mappls.sdk.services.api.directionsrefresh.MapplsDirectionsRefresh
    @Nullable
    public String sourceInternal() {
        return this.j;
    }

    @Override // com.mappls.sdk.services.api.directionsrefresh.MapplsDirectionsRefresh
    public MapplsDirectionsRefresh.Builder toBuilder() {
        return new b(this);
    }

    public String toString() {
        return "MapplsDirectionsRefresh{profile=" + this.f13205a + ", requestId=" + this.b + ", routeIndex=" + this.c + ", isRefresh=" + this.d + ", isNotify=" + this.e + ", nodeIndex=" + this.f + ", categories=" + this.g + ", tripType=" + this.h + ", sessionId=" + this.i + ", sourceInternal=" + this.j + ", isSort=" + this.k + ", baseUrl=" + this.l + "}";
    }

    @Override // com.mappls.sdk.services.api.directionsrefresh.MapplsDirectionsRefresh
    public Integer tripType() {
        return this.h;
    }

    public a(String str, String str2, Integer num, @Nullable Boolean bool, @Nullable Boolean bool2, @Nullable Long l, @Nullable String str3, Integer num2, @Nullable String str4, @Nullable String str5, @Nullable Boolean bool3, String str6) {
        this.f13205a = str;
        this.b = str2;
        this.c = num;
        this.d = bool;
        this.e = bool2;
        this.f = l;
        this.g = str3;
        this.h = num2;
        this.i = str4;
        this.j = str5;
        this.k = bool3;
        this.l = str6;
    }
}

package com.mappls.sdk.services.api.alongroute;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.services.api.alongroute.MapplsPOIAlongRoute;
import java.util.Objects;
/* loaded from: classes11.dex */
public final class a extends MapplsPOIAlongRoute {

    /* renamed from: a  reason: collision with root package name */
    public final String f13151a;
    public final String b;
    public final String c;
    public final Integer d;
    public final Integer e;
    public final Boolean f;
    public final String g;

    /* loaded from: classes11.dex */
    public static final class b extends MapplsPOIAlongRoute.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13152a;
        public String b;
        public String c;
        public Integer d;
        public Integer e;
        public Boolean f;
        public String g;

        @Override // com.mappls.sdk.services.api.alongroute.MapplsPOIAlongRoute.Builder
        public MapplsPOIAlongRoute autoBuild() {
            String str = "";
            if (this.f13152a == null) {
                str = " baseUrl";
            }
            if (this.b == null) {
                str = str + " path";
            }
            if (this.c == null) {
                str = str + " category";
            }
            if (str.isEmpty()) {
                return new a(this.f13152a, this.b, this.c, this.d, this.e, this.f, this.g);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.alongroute.MapplsPOIAlongRoute.Builder
        public MapplsPOIAlongRoute.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f13152a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.alongroute.MapplsPOIAlongRoute.Builder
        public MapplsPOIAlongRoute.Builder buffer(@Nullable Integer num) {
            this.d = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.alongroute.MapplsPOIAlongRoute.Builder
        public MapplsPOIAlongRoute.Builder category(String str) {
            Objects.requireNonNull(str, "Null category");
            this.c = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.alongroute.MapplsPOIAlongRoute.Builder
        public MapplsPOIAlongRoute.Builder geometries(@Nullable String str) {
            this.g = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.alongroute.MapplsPOIAlongRoute.Builder
        public MapplsPOIAlongRoute.Builder page(@Nullable Integer num) {
            this.e = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.alongroute.MapplsPOIAlongRoute.Builder
        public MapplsPOIAlongRoute.Builder path(String str) {
            Objects.requireNonNull(str, "Null path");
            this.b = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.alongroute.MapplsPOIAlongRoute.Builder
        public MapplsPOIAlongRoute.Builder sort(@Nullable Boolean bool) {
            this.f = bool;
            return this;
        }
    }

    @Override // com.mappls.sdk.services.api.alongroute.MapplsPOIAlongRoute, com.mappls.sdk.services.api.MapplsService
    public String baseUrl() {
        return this.f13151a;
    }

    @Override // com.mappls.sdk.services.api.alongroute.MapplsPOIAlongRoute
    @Nullable
    public Integer buffer() {
        return this.d;
    }

    @Override // com.mappls.sdk.services.api.alongroute.MapplsPOIAlongRoute
    @NonNull
    public String category() {
        return this.c;
    }

    public boolean equals(Object obj) {
        Integer num;
        Integer num2;
        Boolean bool;
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsPOIAlongRoute) {
            MapplsPOIAlongRoute mapplsPOIAlongRoute = (MapplsPOIAlongRoute) obj;
            if (this.f13151a.equals(mapplsPOIAlongRoute.baseUrl()) && this.b.equals(mapplsPOIAlongRoute.path()) && this.c.equals(mapplsPOIAlongRoute.category()) && ((num = this.d) != null ? num.equals(mapplsPOIAlongRoute.buffer()) : mapplsPOIAlongRoute.buffer() == null) && ((num2 = this.e) != null ? num2.equals(mapplsPOIAlongRoute.page()) : mapplsPOIAlongRoute.page() == null) && ((bool = this.f) != null ? bool.equals(mapplsPOIAlongRoute.sort()) : mapplsPOIAlongRoute.sort() == null)) {
                String str = this.g;
                if (str == null) {
                    if (mapplsPOIAlongRoute.geometries() == null) {
                        return true;
                    }
                } else if (str.equals(mapplsPOIAlongRoute.geometries())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override // com.mappls.sdk.services.api.alongroute.MapplsPOIAlongRoute
    @Nullable
    public String geometries() {
        return this.g;
    }

    public int hashCode() {
        int hashCode = (((((this.f13151a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode()) * 1000003;
        Integer num = this.d;
        int hashCode2 = (hashCode ^ (num == null ? 0 : num.hashCode())) * 1000003;
        Integer num2 = this.e;
        int hashCode3 = (hashCode2 ^ (num2 == null ? 0 : num2.hashCode())) * 1000003;
        Boolean bool = this.f;
        int hashCode4 = (hashCode3 ^ (bool == null ? 0 : bool.hashCode())) * 1000003;
        String str = this.g;
        return hashCode4 ^ (str != null ? str.hashCode() : 0);
    }

    @Override // com.mappls.sdk.services.api.alongroute.MapplsPOIAlongRoute
    @Nullable
    public Integer page() {
        return this.e;
    }

    @Override // com.mappls.sdk.services.api.alongroute.MapplsPOIAlongRoute
    @NonNull
    public String path() {
        return this.b;
    }

    @Override // com.mappls.sdk.services.api.alongroute.MapplsPOIAlongRoute
    @Nullable
    public Boolean sort() {
        return this.f;
    }

    public String toString() {
        return "MapplsPOIAlongRoute{baseUrl=" + this.f13151a + ", path=" + this.b + ", category=" + this.c + ", buffer=" + this.d + ", page=" + this.e + ", sort=" + this.f + ", geometries=" + this.g + "}";
    }

    public a(String str, String str2, String str3, @Nullable Integer num, @Nullable Integer num2, @Nullable Boolean bool, @Nullable String str4) {
        this.f13151a = str;
        this.b = str2;
        this.c = str3;
        this.d = num;
        this.e = num2;
        this.f = bool;
        this.g = str4;
    }
}

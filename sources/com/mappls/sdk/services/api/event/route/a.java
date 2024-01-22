package com.mappls.sdk.services.api.event.route;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.services.api.event.route.MapplsRouteSummary;
import java.util.Objects;
/* loaded from: classes4.dex */
public final class a extends MapplsRouteSummary {

    /* renamed from: a  reason: collision with root package name */
    public final String f13223a;
    public final String b;
    public final Integer c;
    public final String d;
    public final Integer e;
    public final String f;
    public final String g;

    /* loaded from: classes4.dex */
    public static final class b extends MapplsRouteSummary.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13224a;
        public String b;
        public Integer c;
        public String d;
        public Integer e;
        public String f;
        public String g;

        @Override // com.mappls.sdk.services.api.event.route.MapplsRouteSummary.Builder
        public MapplsRouteSummary autoBuild() {
            String str = "";
            if (this.f13224a == null) {
                str = " baseUrl";
            }
            if (this.b == null) {
                str = str + " routeId";
            }
            if (str.isEmpty()) {
                return new a(this.f13224a, this.b, this.c, this.d, this.e, this.f, this.g);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.event.route.MapplsRouteSummary.Builder
        public MapplsRouteSummary.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f13224a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.event.route.MapplsRouteSummary.Builder
        public MapplsRouteSummary.Builder currentNode(@Nullable String str) {
            this.d = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.event.route.MapplsRouteSummary.Builder
        public MapplsRouteSummary.Builder internalCategories(@Nullable String str) {
            this.f = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.event.route.MapplsRouteSummary.Builder
        public MapplsRouteSummary.Builder isGroup(@Nullable Integer num) {
            this.e = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.event.route.MapplsRouteSummary.Builder
        public MapplsRouteSummary.Builder routeId(String str) {
            Objects.requireNonNull(str, "Null routeId");
            this.b = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.event.route.MapplsRouteSummary.Builder
        public MapplsRouteSummary.Builder routeIdx(@Nullable Integer num) {
            this.c = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.event.route.MapplsRouteSummary.Builder
        public MapplsRouteSummary.Builder screenName(@Nullable String str) {
            this.g = str;
            return this;
        }
    }

    @Override // com.mappls.sdk.services.api.event.route.MapplsRouteSummary, com.mappls.sdk.services.api.MapplsService
    @NonNull
    public String baseUrl() {
        return this.f13223a;
    }

    @Override // com.mappls.sdk.services.api.event.route.MapplsRouteSummary
    @Nullable
    public String currentNode() {
        return this.d;
    }

    public boolean equals(Object obj) {
        Integer num;
        String str;
        Integer num2;
        String str2;
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsRouteSummary) {
            MapplsRouteSummary mapplsRouteSummary = (MapplsRouteSummary) obj;
            if (this.f13223a.equals(mapplsRouteSummary.baseUrl()) && this.b.equals(mapplsRouteSummary.routeId()) && ((num = this.c) != null ? num.equals(mapplsRouteSummary.routeIdx()) : mapplsRouteSummary.routeIdx() == null) && ((str = this.d) != null ? str.equals(mapplsRouteSummary.currentNode()) : mapplsRouteSummary.currentNode() == null) && ((num2 = this.e) != null ? num2.equals(mapplsRouteSummary.isGroup()) : mapplsRouteSummary.isGroup() == null) && ((str2 = this.f) != null ? str2.equals(mapplsRouteSummary.internalCategories()) : mapplsRouteSummary.internalCategories() == null)) {
                String str3 = this.g;
                if (str3 == null) {
                    if (mapplsRouteSummary.screenName() == null) {
                        return true;
                    }
                } else if (str3.equals(mapplsRouteSummary.screenName())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((this.f13223a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003;
        Integer num = this.c;
        int hashCode2 = (hashCode ^ (num == null ? 0 : num.hashCode())) * 1000003;
        String str = this.d;
        int hashCode3 = (hashCode2 ^ (str == null ? 0 : str.hashCode())) * 1000003;
        Integer num2 = this.e;
        int hashCode4 = (hashCode3 ^ (num2 == null ? 0 : num2.hashCode())) * 1000003;
        String str2 = this.f;
        int hashCode5 = (hashCode4 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.g;
        return hashCode5 ^ (str3 != null ? str3.hashCode() : 0);
    }

    @Override // com.mappls.sdk.services.api.event.route.MapplsRouteSummary
    @Nullable
    public String internalCategories() {
        return this.f;
    }

    @Override // com.mappls.sdk.services.api.event.route.MapplsRouteSummary
    @Nullable
    public Integer isGroup() {
        return this.e;
    }

    @Override // com.mappls.sdk.services.api.event.route.MapplsRouteSummary
    @NonNull
    public String routeId() {
        return this.b;
    }

    @Override // com.mappls.sdk.services.api.event.route.MapplsRouteSummary
    @Nullable
    public Integer routeIdx() {
        return this.c;
    }

    @Override // com.mappls.sdk.services.api.event.route.MapplsRouteSummary
    @Nullable
    public String screenName() {
        return this.g;
    }

    public String toString() {
        return "MapplsRouteSummary{baseUrl=" + this.f13223a + ", routeId=" + this.b + ", routeIdx=" + this.c + ", currentNode=" + this.d + ", isGroup=" + this.e + ", internalCategories=" + this.f + ", screenName=" + this.g + "}";
    }

    public a(String str, String str2, @Nullable Integer num, @Nullable String str3, @Nullable Integer num2, @Nullable String str4, @Nullable String str5) {
        this.f13223a = str;
        this.b = str2;
        this.c = num;
        this.d = str3;
        this.e = num2;
        this.f = str4;
        this.g = str5;
    }
}

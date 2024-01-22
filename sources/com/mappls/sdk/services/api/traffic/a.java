package com.mappls.sdk.services.api.traffic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.services.api.traffic.MapplsRoadTrafficDetail;
import java.util.Objects;
/* loaded from: classes8.dex */
public final class a extends MapplsRoadTrafficDetail {

    /* renamed from: a  reason: collision with root package name */
    public final String f13287a;
    public final Double b;
    public final Double c;
    public final Long d;

    /* loaded from: classes8.dex */
    public static final class b extends MapplsRoadTrafficDetail.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13288a;
        public Double b;
        public Double c;
        public Long d;

        @Override // com.mappls.sdk.services.api.traffic.MapplsRoadTrafficDetail.Builder
        public MapplsRoadTrafficDetail.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f13288a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.traffic.MapplsRoadTrafficDetail.Builder
        public MapplsRoadTrafficDetail build() {
            String str = "";
            if (this.f13288a == null) {
                str = " baseUrl";
            }
            if (this.b == null) {
                str = str + " latitude";
            }
            if (this.c == null) {
                str = str + " longitude";
            }
            if (str.isEmpty()) {
                return new a(this.f13288a, this.b, this.c, this.d);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.traffic.MapplsRoadTrafficDetail.Builder
        public MapplsRoadTrafficDetail.Builder latitude(Double d) {
            Objects.requireNonNull(d, "Null latitude");
            this.b = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.traffic.MapplsRoadTrafficDetail.Builder
        public MapplsRoadTrafficDetail.Builder longitude(Double d) {
            Objects.requireNonNull(d, "Null longitude");
            this.c = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.traffic.MapplsRoadTrafficDetail.Builder
        public MapplsRoadTrafficDetail.Builder radius(@Nullable Long l) {
            this.d = l;
            return this;
        }
    }

    @Override // com.mappls.sdk.services.api.traffic.MapplsRoadTrafficDetail, com.mappls.sdk.services.api.MapplsService
    public String baseUrl() {
        return this.f13287a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsRoadTrafficDetail) {
            MapplsRoadTrafficDetail mapplsRoadTrafficDetail = (MapplsRoadTrafficDetail) obj;
            if (this.f13287a.equals(mapplsRoadTrafficDetail.baseUrl()) && this.b.equals(mapplsRoadTrafficDetail.latitude()) && this.c.equals(mapplsRoadTrafficDetail.longitude())) {
                Long l = this.d;
                if (l == null) {
                    if (mapplsRoadTrafficDetail.radius() == null) {
                        return true;
                    }
                } else if (l.equals(mapplsRoadTrafficDetail.radius())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((((this.f13287a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode()) * 1000003;
        Long l = this.d;
        return hashCode ^ (l == null ? 0 : l.hashCode());
    }

    @Override // com.mappls.sdk.services.api.traffic.MapplsRoadTrafficDetail
    @NonNull
    public Double latitude() {
        return this.b;
    }

    @Override // com.mappls.sdk.services.api.traffic.MapplsRoadTrafficDetail
    @NonNull
    public Double longitude() {
        return this.c;
    }

    @Override // com.mappls.sdk.services.api.traffic.MapplsRoadTrafficDetail
    @Nullable
    public Long radius() {
        return this.d;
    }

    public String toString() {
        return "MapplsRoadTrafficDetail{baseUrl=" + this.f13287a + ", latitude=" + this.b + ", longitude=" + this.c + ", radius=" + this.d + "}";
    }

    public a(String str, Double d, Double d2, @Nullable Long l) {
        this.f13287a = str;
        this.b = d;
        this.c = d2;
        this.d = l;
    }
}

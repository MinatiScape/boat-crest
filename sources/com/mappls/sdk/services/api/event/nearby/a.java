package com.mappls.sdk.services.api.event.nearby;

import androidx.annotation.NonNull;
import com.mappls.sdk.services.api.event.nearby.MapplsNearbyReport;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class a extends MapplsNearbyReport {

    /* renamed from: a  reason: collision with root package name */
    public final String f13220a;
    public final Double b;
    public final Double c;
    public final Double d;
    public final Double e;

    /* loaded from: classes3.dex */
    public static final class b extends MapplsNearbyReport.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13221a;
        public Double b;
        public Double c;
        public Double d;
        public Double e;

        @Override // com.mappls.sdk.services.api.event.nearby.MapplsNearbyReport.Builder
        public MapplsNearbyReport autoBuild() {
            String str = "";
            if (this.f13221a == null) {
                str = " baseUrl";
            }
            if (this.b == null) {
                str = str + " minX";
            }
            if (this.c == null) {
                str = str + " minY";
            }
            if (this.d == null) {
                str = str + " maxX";
            }
            if (this.e == null) {
                str = str + " maxY";
            }
            if (str.isEmpty()) {
                return new a(this.f13221a, this.b, this.c, this.d, this.e);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.event.nearby.MapplsNearbyReport.Builder
        public MapplsNearbyReport.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f13221a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.event.nearby.MapplsNearbyReport.Builder
        public MapplsNearbyReport.Builder maxX(Double d) {
            Objects.requireNonNull(d, "Null maxX");
            this.d = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.event.nearby.MapplsNearbyReport.Builder
        public MapplsNearbyReport.Builder maxY(Double d) {
            Objects.requireNonNull(d, "Null maxY");
            this.e = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.event.nearby.MapplsNearbyReport.Builder
        public MapplsNearbyReport.Builder minX(Double d) {
            Objects.requireNonNull(d, "Null minX");
            this.b = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.event.nearby.MapplsNearbyReport.Builder
        public MapplsNearbyReport.Builder minY(Double d) {
            Objects.requireNonNull(d, "Null minY");
            this.c = d;
            return this;
        }
    }

    @Override // com.mappls.sdk.services.api.event.nearby.MapplsNearbyReport, com.mappls.sdk.services.api.MapplsService
    @NonNull
    public String baseUrl() {
        return this.f13220a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsNearbyReport) {
            MapplsNearbyReport mapplsNearbyReport = (MapplsNearbyReport) obj;
            return this.f13220a.equals(mapplsNearbyReport.baseUrl()) && this.b.equals(mapplsNearbyReport.minX()) && this.c.equals(mapplsNearbyReport.minY()) && this.d.equals(mapplsNearbyReport.maxX()) && this.e.equals(mapplsNearbyReport.maxY());
        }
        return false;
    }

    public int hashCode() {
        return ((((((((this.f13220a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d.hashCode()) * 1000003) ^ this.e.hashCode();
    }

    @Override // com.mappls.sdk.services.api.event.nearby.MapplsNearbyReport
    public Double maxX() {
        return this.d;
    }

    @Override // com.mappls.sdk.services.api.event.nearby.MapplsNearbyReport
    public Double maxY() {
        return this.e;
    }

    @Override // com.mappls.sdk.services.api.event.nearby.MapplsNearbyReport
    public Double minX() {
        return this.b;
    }

    @Override // com.mappls.sdk.services.api.event.nearby.MapplsNearbyReport
    public Double minY() {
        return this.c;
    }

    public String toString() {
        return "MapplsNearbyReport{baseUrl=" + this.f13220a + ", minX=" + this.b + ", minY=" + this.c + ", maxX=" + this.d + ", maxY=" + this.e + "}";
    }

    public a(String str, Double d, Double d2, Double d3, Double d4) {
        this.f13220a = str;
        this.b = d;
        this.c = d2;
        this.d = d3;
        this.e = d4;
    }
}

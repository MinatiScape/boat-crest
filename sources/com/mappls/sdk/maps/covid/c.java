package com.mappls.sdk.maps.covid;

import android.graphics.PointF;
import androidx.annotation.NonNull;
import com.mappls.sdk.maps.covid.MapplsLayerDetail;
import com.mappls.sdk.maps.geometry.LatLngBounds;
import java.util.Objects;
/* loaded from: classes11.dex */
public final class c extends MapplsLayerDetail {

    /* renamed from: a  reason: collision with root package name */
    public final String f12708a;
    public final LatLngBounds b;
    public final String c;
    public final Boolean d;
    public final PointF e;
    public final Integer f;
    public final Integer g;
    public final Integer h;

    /* loaded from: classes11.dex */
    public static final class b extends MapplsLayerDetail.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f12709a;
        public LatLngBounds b;
        public String c;
        public Boolean d;
        public PointF e;
        public Integer f;
        public Integer g;
        public Integer h;

        @Override // com.mappls.sdk.maps.covid.MapplsLayerDetail.Builder
        public MapplsLayerDetail a() {
            String str = "";
            if (this.f12709a == null) {
                str = " baseUrl";
            }
            if (this.b == null) {
                str = str + " visibleRegion";
            }
            if (this.c == null) {
                str = str + " layerType";
            }
            if (this.d == null) {
                str = str + " isStyle";
            }
            if (this.e == null) {
                str = str + " clickedPoint";
            }
            if (this.f == null) {
                str = str + " height";
            }
            if (this.g == null) {
                str = str + " width";
            }
            if (this.h == null) {
                str = str + " buffer";
            }
            if (str.isEmpty()) {
                return new c(this.f12709a, this.b, this.c, this.d, this.e, this.f, this.g, this.h);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.maps.covid.MapplsLayerDetail.Builder
        public MapplsLayerDetail.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f12709a = str;
            return this;
        }

        @Override // com.mappls.sdk.maps.covid.MapplsLayerDetail.Builder
        public MapplsLayerDetail.Builder buffer(Integer num) {
            Objects.requireNonNull(num, "Null buffer");
            this.h = num;
            return this;
        }

        @Override // com.mappls.sdk.maps.covid.MapplsLayerDetail.Builder
        public MapplsLayerDetail.Builder clickedPoint(PointF pointF) {
            Objects.requireNonNull(pointF, "Null clickedPoint");
            this.e = pointF;
            return this;
        }

        @Override // com.mappls.sdk.maps.covid.MapplsLayerDetail.Builder
        public MapplsLayerDetail.Builder height(Integer num) {
            Objects.requireNonNull(num, "Null height");
            this.f = num;
            return this;
        }

        @Override // com.mappls.sdk.maps.covid.MapplsLayerDetail.Builder
        public MapplsLayerDetail.Builder isStyle(Boolean bool) {
            Objects.requireNonNull(bool, "Null isStyle");
            this.d = bool;
            return this;
        }

        @Override // com.mappls.sdk.maps.covid.MapplsLayerDetail.Builder
        public MapplsLayerDetail.Builder layerType(String str) {
            Objects.requireNonNull(str, "Null layerType");
            this.c = str;
            return this;
        }

        @Override // com.mappls.sdk.maps.covid.MapplsLayerDetail.Builder
        public MapplsLayerDetail.Builder visibleRegion(LatLngBounds latLngBounds) {
            Objects.requireNonNull(latLngBounds, "Null visibleRegion");
            this.b = latLngBounds;
            return this;
        }

        @Override // com.mappls.sdk.maps.covid.MapplsLayerDetail.Builder
        public MapplsLayerDetail.Builder width(Integer num) {
            Objects.requireNonNull(num, "Null width");
            this.g = num;
            return this;
        }
    }

    @Override // com.mappls.sdk.maps.covid.MapplsLayerDetail
    @NonNull
    public PointF b() {
        return this.e;
    }

    @Override // com.mappls.sdk.maps.covid.MapplsLayerDetail, com.mappls.sdk.services.api.MapplsService
    @NonNull
    public String baseUrl() {
        return this.f12708a;
    }

    @Override // com.mappls.sdk.maps.covid.MapplsLayerDetail
    @NonNull
    public Integer buffer() {
        return this.h;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsLayerDetail) {
            MapplsLayerDetail mapplsLayerDetail = (MapplsLayerDetail) obj;
            return this.f12708a.equals(mapplsLayerDetail.baseUrl()) && this.b.equals(mapplsLayerDetail.i()) && this.c.equals(mapplsLayerDetail.h()) && this.d.equals(mapplsLayerDetail.g()) && this.e.equals(mapplsLayerDetail.b()) && this.f.equals(mapplsLayerDetail.f()) && this.g.equals(mapplsLayerDetail.j()) && this.h.equals(mapplsLayerDetail.buffer());
        }
        return false;
    }

    @Override // com.mappls.sdk.maps.covid.MapplsLayerDetail
    @NonNull
    public Integer f() {
        return this.f;
    }

    @Override // com.mappls.sdk.maps.covid.MapplsLayerDetail
    @NonNull
    public Boolean g() {
        return this.d;
    }

    @Override // com.mappls.sdk.maps.covid.MapplsLayerDetail
    @NonNull
    public String h() {
        return this.c;
    }

    public int hashCode() {
        return ((((((((((((((this.f12708a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d.hashCode()) * 1000003) ^ this.e.hashCode()) * 1000003) ^ this.f.hashCode()) * 1000003) ^ this.g.hashCode()) * 1000003) ^ this.h.hashCode();
    }

    @Override // com.mappls.sdk.maps.covid.MapplsLayerDetail
    @NonNull
    public LatLngBounds i() {
        return this.b;
    }

    @Override // com.mappls.sdk.maps.covid.MapplsLayerDetail
    @NonNull
    public Integer j() {
        return this.g;
    }

    public String toString() {
        return "MapplsLayerDetail{baseUrl=" + this.f12708a + ", visibleRegion=" + this.b + ", layerType=" + this.c + ", isStyle=" + this.d + ", clickedPoint=" + this.e + ", height=" + this.f + ", width=" + this.g + ", buffer=" + this.h + "}";
    }

    public c(String str, LatLngBounds latLngBounds, String str2, Boolean bool, PointF pointF, Integer num, Integer num2, Integer num3) {
        this.f12708a = str;
        this.b = latLngBounds;
        this.c = str2;
        this.d = bool;
        this.e = pointF;
        this.f = num;
        this.g = num2;
        this.h = num3;
    }
}

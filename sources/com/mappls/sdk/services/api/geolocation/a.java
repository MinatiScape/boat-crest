package com.mappls.sdk.services.api.geolocation;

import androidx.annotation.NonNull;
import com.mappls.sdk.services.api.geolocation.MapplsGeolocation;
import java.util.List;
import java.util.Objects;
/* loaded from: classes6.dex */
public final class a extends MapplsGeolocation {

    /* renamed from: a  reason: collision with root package name */
    public final String f13243a;
    public final List<GeolocationRequest> b;

    /* loaded from: classes6.dex */
    public static final class b extends MapplsGeolocation.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13244a;
        public List<GeolocationRequest> b;

        @Override // com.mappls.sdk.services.api.geolocation.MapplsGeolocation.Builder
        public MapplsGeolocation autoBuild() {
            String str = "";
            if (this.f13244a == null) {
                str = " baseUrl";
            }
            if (this.b == null) {
                str = str + " cellTowers";
            }
            if (str.isEmpty()) {
                return new a(this.f13244a, this.b);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.geolocation.MapplsGeolocation.Builder
        public MapplsGeolocation.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f13244a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.geolocation.MapplsGeolocation.Builder
        public MapplsGeolocation.Builder cellTowers(List<GeolocationRequest> list) {
            Objects.requireNonNull(list, "Null cellTowers");
            this.b = list;
            return this;
        }
    }

    @Override // com.mappls.sdk.services.api.geolocation.MapplsGeolocation, com.mappls.sdk.services.api.MapplsService
    @NonNull
    public String baseUrl() {
        return this.f13243a;
    }

    @Override // com.mappls.sdk.services.api.geolocation.MapplsGeolocation
    @NonNull
    public List<GeolocationRequest> cellTowers() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsGeolocation) {
            MapplsGeolocation mapplsGeolocation = (MapplsGeolocation) obj;
            return this.f13243a.equals(mapplsGeolocation.baseUrl()) && this.b.equals(mapplsGeolocation.cellTowers());
        }
        return false;
    }

    public int hashCode() {
        return ((this.f13243a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode();
    }

    public String toString() {
        return "MapplsGeolocation{baseUrl=" + this.f13243a + ", cellTowers=" + this.b + "}";
    }

    public a(String str, List<GeolocationRequest> list) {
        this.f13243a = str;
        this.b = list;
    }
}

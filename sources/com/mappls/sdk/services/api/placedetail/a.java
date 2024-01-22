package com.mappls.sdk.services.api.placedetail;

import androidx.annotation.NonNull;
import com.mappls.sdk.services.api.placedetail.MapplsPlaceDetail;
import java.util.Objects;
/* loaded from: classes6.dex */
public final class a extends MapplsPlaceDetail {

    /* renamed from: a  reason: collision with root package name */
    public final String f13252a;
    public final String b;

    /* loaded from: classes6.dex */
    public static final class b extends MapplsPlaceDetail.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13253a;
        public String b;

        @Override // com.mappls.sdk.services.api.placedetail.MapplsPlaceDetail.Builder
        public MapplsPlaceDetail autoBuild() {
            String str = "";
            if (this.f13253a == null) {
                str = " baseUrl";
            }
            if (this.b == null) {
                str = str + " mapplsPin";
            }
            if (str.isEmpty()) {
                return new a(this.f13253a, this.b);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.placedetail.MapplsPlaceDetail.Builder
        public MapplsPlaceDetail.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f13253a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.placedetail.MapplsPlaceDetail.Builder
        public MapplsPlaceDetail.Builder mapplsPin(String str) {
            Objects.requireNonNull(str, "Null mapplsPin");
            this.b = str;
            return this;
        }
    }

    @Override // com.mappls.sdk.services.api.placedetail.MapplsPlaceDetail, com.mappls.sdk.services.api.MapplsService
    @NonNull
    public String baseUrl() {
        return this.f13252a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsPlaceDetail) {
            MapplsPlaceDetail mapplsPlaceDetail = (MapplsPlaceDetail) obj;
            return this.f13252a.equals(mapplsPlaceDetail.baseUrl()) && this.b.equals(mapplsPlaceDetail.mapplsPin());
        }
        return false;
    }

    public int hashCode() {
        return ((this.f13252a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode();
    }

    @Override // com.mappls.sdk.services.api.placedetail.MapplsPlaceDetail
    @NonNull
    public String mapplsPin() {
        return this.b;
    }

    public String toString() {
        return "MapplsPlaceDetail{baseUrl=" + this.f13252a + ", mapplsPin=" + this.b + "}";
    }

    public a(String str, String str2) {
        this.f13252a = str;
        this.b = str2;
    }
}

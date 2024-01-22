package com.mappls.sdk.maps;

import androidx.annotation.NonNull;
import com.mappls.sdk.maps.MapplsGetCoordinates;
import java.util.Objects;
/* loaded from: classes11.dex */
public final class e extends MapplsGetCoordinates {

    /* renamed from: a  reason: collision with root package name */
    public final String f12721a;
    public final String b;

    /* loaded from: classes11.dex */
    public static final class b extends MapplsGetCoordinates.Builder {
        public String b;
        public String c;

        @Override // com.mappls.sdk.maps.MapplsGetCoordinates.Builder
        public MapplsGetCoordinates a() {
            String str = "";
            if (this.b == null) {
                str = " baseUrl";
            }
            if (this.c == null) {
                str = str + " formattedMapplsPin";
            }
            if (str.isEmpty()) {
                return new e(this.b, this.c);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.maps.MapplsGetCoordinates.Builder
        public MapplsGetCoordinates.Builder b(String str) {
            Objects.requireNonNull(str, "Null formattedMapplsPin");
            this.c = str;
            return this;
        }

        @Override // com.mappls.sdk.maps.MapplsGetCoordinates.Builder
        public MapplsGetCoordinates.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.b = str;
            return this;
        }
    }

    @Override // com.mappls.sdk.maps.MapplsGetCoordinates
    @NonNull
    public String b() {
        return this.b;
    }

    @Override // com.mappls.sdk.maps.MapplsGetCoordinates, com.mappls.sdk.services.api.MapplsService
    @NonNull
    public String baseUrl() {
        return this.f12721a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsGetCoordinates) {
            MapplsGetCoordinates mapplsGetCoordinates = (MapplsGetCoordinates) obj;
            return this.f12721a.equals(mapplsGetCoordinates.baseUrl()) && this.b.equals(mapplsGetCoordinates.b());
        }
        return false;
    }

    public int hashCode() {
        return ((this.f12721a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode();
    }

    public String toString() {
        return "MapplsGetCoordinates{baseUrl=" + this.f12721a + ", formattedMapplsPin=" + this.b + "}";
    }

    public e(String str, String str2) {
        this.f12721a = str;
        this.b = str2;
    }
}

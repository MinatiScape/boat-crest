package com.mappls.sdk.maps.covid;

import androidx.annotation.NonNull;
import com.mappls.sdk.maps.covid.MapplsContainmentZoneInfo;
import java.util.Objects;
/* loaded from: classes11.dex */
public final class b extends MapplsContainmentZoneInfo {

    /* renamed from: a  reason: collision with root package name */
    public final String f12707a;
    public final String b;
    public final Integer c;
    public final Integer d;
    public final Double e;
    public final Double f;

    /* renamed from: com.mappls.sdk.maps.covid.b$b  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static final class C0628b extends MapplsContainmentZoneInfo.Builder {
        public String c;
        public String d;
        public Integer e;
        public Integer f;
        public Double g;
        public Double h;

        @Override // com.mappls.sdk.maps.covid.MapplsContainmentZoneInfo.Builder
        public MapplsContainmentZoneInfo a() {
            String str = "";
            if (this.c == null) {
                str = " baseUrl";
            }
            if (this.d == null) {
                str = str + " keywords";
            }
            if (this.e == null) {
                str = str + " distance";
            }
            if (this.f == null) {
                str = str + " range";
            }
            if (this.g == null) {
                str = str + " latitude";
            }
            if (this.h == null) {
                str = str + " longitude";
            }
            if (str.isEmpty()) {
                return new b(this.c, this.d, this.e, this.f, this.g, this.h);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.maps.covid.MapplsContainmentZoneInfo.Builder
        public MapplsContainmentZoneInfo.Builder b(Double d) {
            Objects.requireNonNull(d, "Null latitude");
            this.g = d;
            return this;
        }

        @Override // com.mappls.sdk.maps.covid.MapplsContainmentZoneInfo.Builder
        public MapplsContainmentZoneInfo.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.c = str;
            return this;
        }

        @Override // com.mappls.sdk.maps.covid.MapplsContainmentZoneInfo.Builder
        public MapplsContainmentZoneInfo.Builder c(Double d) {
            Objects.requireNonNull(d, "Null longitude");
            this.h = d;
            return this;
        }

        @Override // com.mappls.sdk.maps.covid.MapplsContainmentZoneInfo.Builder
        public MapplsContainmentZoneInfo.Builder distance(Integer num) {
            Objects.requireNonNull(num, "Null distance");
            this.e = num;
            return this;
        }

        @Override // com.mappls.sdk.maps.covid.MapplsContainmentZoneInfo.Builder
        public MapplsContainmentZoneInfo.Builder keywords(String str) {
            Objects.requireNonNull(str, "Null keywords");
            this.d = str;
            return this;
        }

        @Override // com.mappls.sdk.maps.covid.MapplsContainmentZoneInfo.Builder
        public MapplsContainmentZoneInfo.Builder range(Integer num) {
            Objects.requireNonNull(num, "Null range");
            this.f = num;
            return this;
        }
    }

    @Override // com.mappls.sdk.maps.covid.MapplsContainmentZoneInfo
    @NonNull
    public Integer b() {
        return this.c;
    }

    @Override // com.mappls.sdk.maps.covid.MapplsContainmentZoneInfo, com.mappls.sdk.services.api.MapplsService
    @NonNull
    public String baseUrl() {
        return this.f12707a;
    }

    @Override // com.mappls.sdk.maps.covid.MapplsContainmentZoneInfo
    @NonNull
    public String e() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsContainmentZoneInfo) {
            MapplsContainmentZoneInfo mapplsContainmentZoneInfo = (MapplsContainmentZoneInfo) obj;
            return this.f12707a.equals(mapplsContainmentZoneInfo.baseUrl()) && this.b.equals(mapplsContainmentZoneInfo.e()) && this.c.equals(mapplsContainmentZoneInfo.b()) && this.d.equals(mapplsContainmentZoneInfo.f()) && this.e.equals(mapplsContainmentZoneInfo.latitude()) && this.f.equals(mapplsContainmentZoneInfo.longitude());
        }
        return false;
    }

    @Override // com.mappls.sdk.maps.covid.MapplsContainmentZoneInfo
    @NonNull
    public Integer f() {
        return this.d;
    }

    public int hashCode() {
        return ((((((((((this.f12707a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d.hashCode()) * 1000003) ^ this.e.hashCode()) * 1000003) ^ this.f.hashCode();
    }

    @Override // com.mappls.sdk.maps.covid.MapplsContainmentZoneInfo
    @NonNull
    public Double latitude() {
        return this.e;
    }

    @Override // com.mappls.sdk.maps.covid.MapplsContainmentZoneInfo
    @NonNull
    public Double longitude() {
        return this.f;
    }

    public String toString() {
        return "MapplsContainmentZoneInfo{baseUrl=" + this.f12707a + ", keywords=" + this.b + ", distance=" + this.c + ", range=" + this.d + ", latitude=" + this.e + ", longitude=" + this.f + "}";
    }

    public b(String str, String str2, Integer num, Integer num2, Double d, Double d2) {
        this.f12707a = str;
        this.b = str2;
        this.c = num;
        this.d = num2;
        this.e = d;
        this.f = d2;
    }
}

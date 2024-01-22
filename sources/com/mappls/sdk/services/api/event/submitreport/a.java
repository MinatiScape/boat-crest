package com.mappls.sdk.services.api.event.submitreport;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport;
import java.util.Objects;
/* loaded from: classes6.dex */
public final class a extends MapplsSubmitReport {

    /* renamed from: a  reason: collision with root package name */
    public final String f13228a;
    public final String b;
    public final String c;
    public final Double d;
    public final Double e;
    public final Integer f;
    public final Integer g;
    public final String h;
    public final Integer i;
    public final Integer j;
    public final Integer k;
    public final String l;
    public final Integer m;
    public final Double n;
    public final String o;
    public final Long p;
    public final Long q;
    public final String r;
    public final String s;
    public final String t;
    public final String u;

    /* loaded from: classes6.dex */
    public static final class b extends MapplsSubmitReport.Builder {
        public String d;
        public String e;
        public String f;
        public Double g;
        public Double h;
        public Integer i;
        public Integer j;
        public String k;
        public Integer l;
        public Integer m;
        public Integer n;
        public String o;
        public Integer p;
        public Double q;
        public String r;
        public Long s;
        public Long t;
        public String u;
        public String v;
        public String w;
        public String x;

        @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport.Builder
        public MapplsSubmitReport a() {
            String str = "";
            if (this.d == null) {
                str = " baseUrl";
            }
            if (this.e == null) {
                str = str + " placeName";
            }
            if (this.i == null) {
                str = str + " parentCategory";
            }
            if (this.j == null) {
                str = str + " childCategory";
            }
            if (str.isEmpty()) {
                return new a(this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.r, this.s, this.t, this.u, this.v, this.w, this.x);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport.Builder
        public MapplsSubmitReport.Builder accuracy(@Nullable Integer num) {
            this.n = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport.Builder
        public MapplsSubmitReport.Builder altitude(@Nullable Double d) {
            this.q = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport.Builder
        public MapplsSubmitReport.Builder appVersion(@Nullable String str) {
            this.v = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport.Builder
        public MapplsSubmitReport.Builder b(@Nullable Double d) {
            this.g = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport.Builder
        public MapplsSubmitReport.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.d = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport.Builder
        public MapplsSubmitReport.Builder bearing(@Nullable Integer num) {
            this.m = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport.Builder
        public MapplsSubmitReport.Builder c(@Nullable Double d) {
            this.h = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport.Builder
        public MapplsSubmitReport.Builder childCategory(Integer num) {
            Objects.requireNonNull(num, "Null childCategory");
            this.j = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport.Builder
        public MapplsSubmitReport.Builder d(@Nullable String str) {
            this.f = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport.Builder
        public MapplsSubmitReport.Builder description(@Nullable String str) {
            this.k = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport.Builder
        public MapplsSubmitReport.Builder deviceName(@Nullable String str) {
            this.x = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport.Builder
        public MapplsSubmitReport.Builder expiry(@Nullable Long l) {
            this.t = l;
            return this;
        }

        @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport.Builder
        public MapplsSubmitReport.Builder flag(@Nullable Integer num) {
            this.p = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport.Builder
        public MapplsSubmitReport.Builder osVersion(@Nullable String str) {
            this.w = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport.Builder
        public MapplsSubmitReport.Builder parentCategory(Integer num) {
            Objects.requireNonNull(num, "Null parentCategory");
            this.i = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport.Builder
        public MapplsSubmitReport.Builder placeName(String str) {
            Objects.requireNonNull(str, "Null placeName");
            this.e = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport.Builder
        public MapplsSubmitReport.Builder quality(@Nullable String str) {
            this.r = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport.Builder
        public MapplsSubmitReport.Builder speed(@Nullable String str) {
            this.o = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport.Builder
        public MapplsSubmitReport.Builder subChildCategory(@Nullable Integer num) {
            this.l = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport.Builder
        public MapplsSubmitReport.Builder utc(@Nullable Long l) {
            this.s = l;
            return this;
        }

        @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport.Builder
        public MapplsSubmitReport.Builder zeroId(@Nullable String str) {
            this.u = str;
            return this;
        }
    }

    @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport
    @Nullable
    public Integer accuracy() {
        return this.k;
    }

    @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport
    @Nullable
    public Double altitude() {
        return this.n;
    }

    @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport
    @Nullable
    public String appVersion() {
        return this.s;
    }

    @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport, com.mappls.sdk.services.api.MapplsService
    @NonNull
    public String baseUrl() {
        return this.f13228a;
    }

    @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport
    @Nullable
    public Integer bearing() {
        return this.j;
    }

    @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport
    @NonNull
    public Integer childCategory() {
        return this.g;
    }

    @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport
    @Nullable
    public String description() {
        return this.h;
    }

    @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport
    @Nullable
    public String deviceName() {
        return this.u;
    }

    public boolean equals(Object obj) {
        String str;
        Double d;
        Double d2;
        String str2;
        Integer num;
        Integer num2;
        Integer num3;
        String str3;
        Integer num4;
        Double d3;
        String str4;
        Long l;
        Long l2;
        String str5;
        String str6;
        String str7;
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsSubmitReport) {
            MapplsSubmitReport mapplsSubmitReport = (MapplsSubmitReport) obj;
            if (this.f13228a.equals(mapplsSubmitReport.baseUrl()) && this.b.equals(mapplsSubmitReport.placeName()) && ((str = this.c) != null ? str.equals(mapplsSubmitReport.internalMapplsPin()) : mapplsSubmitReport.internalMapplsPin() == null) && ((d = this.d) != null ? d.equals(mapplsSubmitReport.internalLatitude()) : mapplsSubmitReport.internalLatitude() == null) && ((d2 = this.e) != null ? d2.equals(mapplsSubmitReport.internalLongitude()) : mapplsSubmitReport.internalLongitude() == null) && this.f.equals(mapplsSubmitReport.parentCategory()) && this.g.equals(mapplsSubmitReport.childCategory()) && ((str2 = this.h) != null ? str2.equals(mapplsSubmitReport.description()) : mapplsSubmitReport.description() == null) && ((num = this.i) != null ? num.equals(mapplsSubmitReport.subChildCategory()) : mapplsSubmitReport.subChildCategory() == null) && ((num2 = this.j) != null ? num2.equals(mapplsSubmitReport.bearing()) : mapplsSubmitReport.bearing() == null) && ((num3 = this.k) != null ? num3.equals(mapplsSubmitReport.accuracy()) : mapplsSubmitReport.accuracy() == null) && ((str3 = this.l) != null ? str3.equals(mapplsSubmitReport.speed()) : mapplsSubmitReport.speed() == null) && ((num4 = this.m) != null ? num4.equals(mapplsSubmitReport.flag()) : mapplsSubmitReport.flag() == null) && ((d3 = this.n) != null ? d3.equals(mapplsSubmitReport.altitude()) : mapplsSubmitReport.altitude() == null) && ((str4 = this.o) != null ? str4.equals(mapplsSubmitReport.quality()) : mapplsSubmitReport.quality() == null) && ((l = this.p) != null ? l.equals(mapplsSubmitReport.utc()) : mapplsSubmitReport.utc() == null) && ((l2 = this.q) != null ? l2.equals(mapplsSubmitReport.expiry()) : mapplsSubmitReport.expiry() == null) && ((str5 = this.r) != null ? str5.equals(mapplsSubmitReport.zeroId()) : mapplsSubmitReport.zeroId() == null) && ((str6 = this.s) != null ? str6.equals(mapplsSubmitReport.appVersion()) : mapplsSubmitReport.appVersion() == null) && ((str7 = this.t) != null ? str7.equals(mapplsSubmitReport.osVersion()) : mapplsSubmitReport.osVersion() == null)) {
                String str8 = this.u;
                if (str8 == null) {
                    if (mapplsSubmitReport.deviceName() == null) {
                        return true;
                    }
                } else if (str8.equals(mapplsSubmitReport.deviceName())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport
    @Nullable
    public Long expiry() {
        return this.q;
    }

    @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport
    @Nullable
    public Integer flag() {
        return this.m;
    }

    public int hashCode() {
        int hashCode = (((this.f13228a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003;
        String str = this.c;
        int hashCode2 = (hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003;
        Double d = this.d;
        int hashCode3 = (hashCode2 ^ (d == null ? 0 : d.hashCode())) * 1000003;
        Double d2 = this.e;
        int hashCode4 = (((((hashCode3 ^ (d2 == null ? 0 : d2.hashCode())) * 1000003) ^ this.f.hashCode()) * 1000003) ^ this.g.hashCode()) * 1000003;
        String str2 = this.h;
        int hashCode5 = (hashCode4 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        Integer num = this.i;
        int hashCode6 = (hashCode5 ^ (num == null ? 0 : num.hashCode())) * 1000003;
        Integer num2 = this.j;
        int hashCode7 = (hashCode6 ^ (num2 == null ? 0 : num2.hashCode())) * 1000003;
        Integer num3 = this.k;
        int hashCode8 = (hashCode7 ^ (num3 == null ? 0 : num3.hashCode())) * 1000003;
        String str3 = this.l;
        int hashCode9 = (hashCode8 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003;
        Integer num4 = this.m;
        int hashCode10 = (hashCode9 ^ (num4 == null ? 0 : num4.hashCode())) * 1000003;
        Double d3 = this.n;
        int hashCode11 = (hashCode10 ^ (d3 == null ? 0 : d3.hashCode())) * 1000003;
        String str4 = this.o;
        int hashCode12 = (hashCode11 ^ (str4 == null ? 0 : str4.hashCode())) * 1000003;
        Long l = this.p;
        int hashCode13 = (hashCode12 ^ (l == null ? 0 : l.hashCode())) * 1000003;
        Long l2 = this.q;
        int hashCode14 = (hashCode13 ^ (l2 == null ? 0 : l2.hashCode())) * 1000003;
        String str5 = this.r;
        int hashCode15 = (hashCode14 ^ (str5 == null ? 0 : str5.hashCode())) * 1000003;
        String str6 = this.s;
        int hashCode16 = (hashCode15 ^ (str6 == null ? 0 : str6.hashCode())) * 1000003;
        String str7 = this.t;
        int hashCode17 = (hashCode16 ^ (str7 == null ? 0 : str7.hashCode())) * 1000003;
        String str8 = this.u;
        return hashCode17 ^ (str8 != null ? str8.hashCode() : 0);
    }

    @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport
    @Nullable
    public Double internalLatitude() {
        return this.d;
    }

    @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport
    @Nullable
    public Double internalLongitude() {
        return this.e;
    }

    @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport
    @Nullable
    public String internalMapplsPin() {
        return this.c;
    }

    @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport
    @Nullable
    public String osVersion() {
        return this.t;
    }

    @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport
    @NonNull
    public Integer parentCategory() {
        return this.f;
    }

    @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport
    @NonNull
    public String placeName() {
        return this.b;
    }

    @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport
    @Nullable
    public String quality() {
        return this.o;
    }

    @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport
    @Nullable
    public String speed() {
        return this.l;
    }

    @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport
    @Nullable
    public Integer subChildCategory() {
        return this.i;
    }

    public String toString() {
        return "MapplsSubmitReport{baseUrl=" + this.f13228a + ", placeName=" + this.b + ", internalMapplsPin=" + this.c + ", internalLatitude=" + this.d + ", internalLongitude=" + this.e + ", parentCategory=" + this.f + ", childCategory=" + this.g + ", description=" + this.h + ", subChildCategory=" + this.i + ", bearing=" + this.j + ", accuracy=" + this.k + ", speed=" + this.l + ", flag=" + this.m + ", altitude=" + this.n + ", quality=" + this.o + ", utc=" + this.p + ", expiry=" + this.q + ", zeroId=" + this.r + ", appVersion=" + this.s + ", osVersion=" + this.t + ", deviceName=" + this.u + "}";
    }

    @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport
    @Nullable
    public Long utc() {
        return this.p;
    }

    @Override // com.mappls.sdk.services.api.event.submitreport.MapplsSubmitReport
    @Nullable
    public String zeroId() {
        return this.r;
    }

    public a(String str, String str2, @Nullable String str3, @Nullable Double d, @Nullable Double d2, Integer num, Integer num2, @Nullable String str4, @Nullable Integer num3, @Nullable Integer num4, @Nullable Integer num5, @Nullable String str5, @Nullable Integer num6, @Nullable Double d3, @Nullable String str6, @Nullable Long l, @Nullable Long l2, @Nullable String str7, @Nullable String str8, @Nullable String str9, @Nullable String str10) {
        this.f13228a = str;
        this.b = str2;
        this.c = str3;
        this.d = d;
        this.e = d2;
        this.f = num;
        this.g = num2;
        this.h = str4;
        this.i = num3;
        this.j = num4;
        this.k = num5;
        this.l = str5;
        this.m = num6;
        this.n = d3;
        this.o = str6;
        this.p = l;
        this.q = l2;
        this.r = str7;
        this.s = str8;
        this.t = str9;
        this.u = str10;
    }
}

package com.mappls.sdk.maps.covid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.maps.covid.RasterOptions;
import java.util.Objects;
/* loaded from: classes11.dex */
public final class d extends RasterOptions {

    /* renamed from: a  reason: collision with root package name */
    public final String f12710a;
    public final String b;
    public final Float c;
    public final Float d;
    public final Float e;
    public final Boolean f;
    public final Float g;
    public final Float h;
    public final Float i;
    public final String j;
    public final Float k;
    public final Boolean l;

    /* loaded from: classes11.dex */
    public static final class b extends RasterOptions.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f12711a;
        public String b;
        public Float c;
        public Float d;
        public Float e;
        public Boolean f;
        public Float g;
        public Float h;
        public Float i;
        public String j;
        public Float k;
        public Boolean l;

        @Override // com.mappls.sdk.maps.covid.RasterOptions.Builder
        public RasterOptions.Builder belowLayer(String str) {
            this.b = str;
            return this;
        }

        @Override // com.mappls.sdk.maps.covid.RasterOptions.Builder
        public RasterOptions.Builder brightnessMax(Float f) {
            this.c = f;
            return this;
        }

        @Override // com.mappls.sdk.maps.covid.RasterOptions.Builder
        public RasterOptions.Builder brightnessMin(Float f) {
            this.d = f;
            return this;
        }

        @Override // com.mappls.sdk.maps.covid.RasterOptions.Builder
        public RasterOptions build() {
            String str = "";
            if (this.f12711a == null) {
                str = " type";
            }
            if (str.isEmpty()) {
                return new d(this.f12711a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.maps.covid.RasterOptions.Builder
        public RasterOptions.Builder contrast(Float f) {
            this.e = f;
            return this;
        }

        @Override // com.mappls.sdk.maps.covid.RasterOptions.Builder
        public RasterOptions.Builder fadeDuration(Float f) {
            this.g = f;
            return this;
        }

        @Override // com.mappls.sdk.maps.covid.RasterOptions.Builder
        public RasterOptions.Builder hueRotate(Float f) {
            this.i = f;
            return this;
        }

        @Override // com.mappls.sdk.maps.covid.RasterOptions.Builder
        public RasterOptions.Builder opacity(Float f) {
            this.h = f;
            return this;
        }

        @Override // com.mappls.sdk.maps.covid.RasterOptions.Builder
        public RasterOptions.Builder resampling(String str) {
            this.j = str;
            return this;
        }

        @Override // com.mappls.sdk.maps.covid.RasterOptions.Builder
        public RasterOptions.Builder saturation(Float f) {
            this.k = f;
            return this;
        }

        @Override // com.mappls.sdk.maps.covid.RasterOptions.Builder
        public RasterOptions.Builder styles(Boolean bool) {
            this.l = bool;
            return this;
        }

        @Override // com.mappls.sdk.maps.covid.RasterOptions.Builder
        public RasterOptions.Builder type(String str) {
            Objects.requireNonNull(str, "Null type");
            this.f12711a = str;
            return this;
        }

        @Override // com.mappls.sdk.maps.covid.RasterOptions.Builder
        public RasterOptions.Builder visibility(Boolean bool) {
            this.f = bool;
            return this;
        }
    }

    @Override // com.mappls.sdk.maps.covid.RasterOptions
    @Nullable
    public String a() {
        return this.b;
    }

    @Override // com.mappls.sdk.maps.covid.RasterOptions
    @Nullable
    public Float b() {
        return this.c;
    }

    @Override // com.mappls.sdk.maps.covid.RasterOptions
    @Nullable
    public Float c() {
        return this.d;
    }

    @Override // com.mappls.sdk.maps.covid.RasterOptions
    @Nullable
    public Float e() {
        return this.e;
    }

    public boolean equals(Object obj) {
        String str;
        Float f;
        Float f2;
        Float f3;
        Boolean bool;
        Float f4;
        Float f5;
        Float f6;
        String str2;
        Float f7;
        if (obj == this) {
            return true;
        }
        if (obj instanceof RasterOptions) {
            RasterOptions rasterOptions = (RasterOptions) obj;
            if (this.f12710a.equals(rasterOptions.l()) && ((str = this.b) != null ? str.equals(rasterOptions.a()) : rasterOptions.a() == null) && ((f = this.c) != null ? f.equals(rasterOptions.b()) : rasterOptions.b() == null) && ((f2 = this.d) != null ? f2.equals(rasterOptions.c()) : rasterOptions.c() == null) && ((f3 = this.e) != null ? f3.equals(rasterOptions.e()) : rasterOptions.e() == null) && ((bool = this.f) != null ? bool.equals(rasterOptions.m()) : rasterOptions.m() == null) && ((f4 = this.g) != null ? f4.equals(rasterOptions.f()) : rasterOptions.f() == null) && ((f5 = this.h) != null ? f5.equals(rasterOptions.h()) : rasterOptions.h() == null) && ((f6 = this.i) != null ? f6.equals(rasterOptions.g()) : rasterOptions.g() == null) && ((str2 = this.j) != null ? str2.equals(rasterOptions.i()) : rasterOptions.i() == null) && ((f7 = this.k) != null ? f7.equals(rasterOptions.j()) : rasterOptions.j() == null)) {
                Boolean bool2 = this.l;
                if (bool2 == null) {
                    if (rasterOptions.k() == null) {
                        return true;
                    }
                } else if (bool2.equals(rasterOptions.k())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override // com.mappls.sdk.maps.covid.RasterOptions
    @Nullable
    public Float f() {
        return this.g;
    }

    @Override // com.mappls.sdk.maps.covid.RasterOptions
    @Nullable
    public Float g() {
        return this.i;
    }

    @Override // com.mappls.sdk.maps.covid.RasterOptions
    @Nullable
    public Float h() {
        return this.h;
    }

    public int hashCode() {
        int hashCode = (this.f12710a.hashCode() ^ 1000003) * 1000003;
        String str = this.b;
        int hashCode2 = (hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003;
        Float f = this.c;
        int hashCode3 = (hashCode2 ^ (f == null ? 0 : f.hashCode())) * 1000003;
        Float f2 = this.d;
        int hashCode4 = (hashCode3 ^ (f2 == null ? 0 : f2.hashCode())) * 1000003;
        Float f3 = this.e;
        int hashCode5 = (hashCode4 ^ (f3 == null ? 0 : f3.hashCode())) * 1000003;
        Boolean bool = this.f;
        int hashCode6 = (hashCode5 ^ (bool == null ? 0 : bool.hashCode())) * 1000003;
        Float f4 = this.g;
        int hashCode7 = (hashCode6 ^ (f4 == null ? 0 : f4.hashCode())) * 1000003;
        Float f5 = this.h;
        int hashCode8 = (hashCode7 ^ (f5 == null ? 0 : f5.hashCode())) * 1000003;
        Float f6 = this.i;
        int hashCode9 = (hashCode8 ^ (f6 == null ? 0 : f6.hashCode())) * 1000003;
        String str2 = this.j;
        int hashCode10 = (hashCode9 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        Float f7 = this.k;
        int hashCode11 = (hashCode10 ^ (f7 == null ? 0 : f7.hashCode())) * 1000003;
        Boolean bool2 = this.l;
        return hashCode11 ^ (bool2 != null ? bool2.hashCode() : 0);
    }

    @Override // com.mappls.sdk.maps.covid.RasterOptions
    @Nullable
    public String i() {
        return this.j;
    }

    @Override // com.mappls.sdk.maps.covid.RasterOptions
    @Nullable
    public Float j() {
        return this.k;
    }

    @Override // com.mappls.sdk.maps.covid.RasterOptions
    @Nullable
    public Boolean k() {
        return this.l;
    }

    @Override // com.mappls.sdk.maps.covid.RasterOptions
    @NonNull
    public String l() {
        return this.f12710a;
    }

    @Override // com.mappls.sdk.maps.covid.RasterOptions
    @Nullable
    public Boolean m() {
        return this.f;
    }

    public String toString() {
        return "RasterOptions{type=" + this.f12710a + ", belowLayer=" + this.b + ", brightnessMax=" + this.c + ", brightnessMin=" + this.d + ", contrast=" + this.e + ", visibility=" + this.f + ", fadeDuration=" + this.g + ", opacity=" + this.h + ", hueRotate=" + this.i + ", resampling=" + this.j + ", saturation=" + this.k + ", styles=" + this.l + "}";
    }

    public d(String str, @Nullable String str2, @Nullable Float f, @Nullable Float f2, @Nullable Float f3, @Nullable Boolean bool, @Nullable Float f4, @Nullable Float f5, @Nullable Float f6, @Nullable String str3, @Nullable Float f7, @Nullable Boolean bool2) {
        this.f12710a = str;
        this.b = str2;
        this.c = f;
        this.d = f2;
        this.e = f3;
        this.f = bool;
        this.g = f4;
        this.h = f5;
        this.i = f6;
        this.j = str3;
        this.k = f7;
        this.l = bool2;
    }
}

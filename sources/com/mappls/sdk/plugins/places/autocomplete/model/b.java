package com.mappls.sdk.plugins.places.autocomplete.model;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions;
import java.util.List;
import java.util.Objects;
/* loaded from: classes10.dex */
public abstract class b extends PlaceOptions {
    public final int A;
    public final int B;
    public final Integer C;
    public final String D;
    public final Boolean E;
    public final Boolean F;
    public final Boolean G;
    public final Integer H;
    public final Integer I;
    public final Integer J;
    public final String K;
    public final Point h;
    public final Boolean i;
    public final String j;
    public final int k;
    public final Integer l;
    public final Integer m;
    public final Double n;
    public final Boolean o;
    public final Boolean p;
    public final String q;
    public final Boolean r;
    public final List<String> s;
    public final int t;
    public final int u;
    public final int v;
    public final Integer w;
    public final Boolean x;
    public final int y;
    public final int z;

    /* loaded from: classes10.dex */
    public static class a extends PlaceOptions.Builder {
        public Integer A;
        public Integer B;
        public Integer C;
        public String D;

        /* renamed from: a  reason: collision with root package name */
        public Point f13135a;
        public Boolean b;
        public String c;
        public Integer d;
        public Integer e;
        public Integer f;
        public Double g;
        public Boolean h;
        public Boolean i;
        public String j;
        public Boolean k;
        public List<String> l;
        public Integer m;
        public Integer n;
        public Integer o;
        public Integer p;
        public Boolean q;
        public Integer r;
        public Integer s;
        public Integer t;
        public Integer u;
        public Integer v;
        public String w;
        public Boolean x;
        public Boolean y;
        public Boolean z;

        @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions.Builder
        public final PlaceOptions.Builder attributionHorizontalAlignment(int i) {
            this.t = Integer.valueOf(i);
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions.Builder
        public final PlaceOptions.Builder attributionVerticalAlignment(int i) {
            this.s = Integer.valueOf(i);
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions.Builder
        public final PlaceOptions autoBuild() {
            String a2 = this.b == null ? com.mappls.sdk.plugins.places.autocomplete.model.a.a("", " userAddedLocationEnable") : "";
            if (this.d == null) {
                a2 = com.mappls.sdk.plugins.places.autocomplete.model.a.a(a2, " limit");
            }
            if (this.h == null) {
                a2 = com.mappls.sdk.plugins.places.autocomplete.model.a.a(a2, " saveHistory");
            }
            if (this.i == null) {
                a2 = com.mappls.sdk.plugins.places.autocomplete.model.a.a(a2, " enableTextSearch");
            }
            if (this.m == null) {
                a2 = com.mappls.sdk.plugins.places.autocomplete.model.a.a(a2, " viewMode");
            }
            if (this.n == null) {
                a2 = com.mappls.sdk.plugins.places.autocomplete.model.a.a(a2, " backgroundColor");
            }
            if (this.o == null) {
                a2 = com.mappls.sdk.plugins.places.autocomplete.model.a.a(a2, " toolbarColor");
            }
            if (this.q == null) {
                a2 = com.mappls.sdk.plugins.places.autocomplete.model.a.a(a2, " showPoweredByText");
            }
            if (this.r == null) {
                a2 = com.mappls.sdk.plugins.places.autocomplete.model.a.a(a2, " toolbarTintColor");
            }
            if (this.s == null) {
                a2 = com.mappls.sdk.plugins.places.autocomplete.model.a.a(a2, " attributionVerticalAlignment");
            }
            if (this.t == null) {
                a2 = com.mappls.sdk.plugins.places.autocomplete.model.a.a(a2, " attributionHorizontalAlignment");
            }
            if (this.u == null) {
                a2 = com.mappls.sdk.plugins.places.autocomplete.model.a.a(a2, " logoSize");
            }
            if (this.v == null) {
                a2 = com.mappls.sdk.plugins.places.autocomplete.model.a.a(a2, " internalMinCharactersForSearch");
            }
            if (this.z == null) {
                a2 = com.mappls.sdk.plugins.places.autocomplete.model.a.a(a2, " isShowCurrentLocation");
            }
            if (this.A == null) {
                a2 = com.mappls.sdk.plugins.places.autocomplete.model.a.a(a2, " currentLocationTextColor");
            }
            if (this.B == null) {
                a2 = com.mappls.sdk.plugins.places.autocomplete.model.a.a(a2, " internalDebounce");
            }
            if (this.C == null) {
                a2 = com.mappls.sdk.plugins.places.autocomplete.model.a.a(a2, " currentLocationIcon");
            }
            if (a2.isEmpty()) {
                return new c(this.f13135a, this.b, this.c, this.d.intValue(), this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m.intValue(), this.n.intValue(), this.o.intValue(), this.p, this.q, this.r.intValue(), this.s.intValue(), this.t.intValue(), this.u.intValue(), this.v, this.w, this.x, this.y, this.z, this.A, this.B, this.C, this.D);
            }
            throw new IllegalStateException(com.mappls.sdk.plugins.places.autocomplete.model.a.a("Missing required properties:", a2));
        }

        @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions.Builder
        public final PlaceOptions.Builder backgroundColor(int i) {
            this.n = Integer.valueOf(i);
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions.Builder
        public final PlaceOptions.Builder bridge(Boolean bool) {
            this.y = bool;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions.Builder
        public final PlaceOptions.Builder currentLocationIcon(Integer num) {
            Objects.requireNonNull(num, "Null currentLocationIcon");
            this.C = num;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions.Builder
        public final PlaceOptions.Builder currentLocationTextColor(Integer num) {
            Objects.requireNonNull(num, "Null currentLocationTextColor");
            this.A = num;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions.Builder
        public final PlaceOptions.Builder enableTextSearch(Boolean bool) {
            Objects.requireNonNull(bool, "Null enableTextSearch");
            this.i = bool;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions.Builder
        public final PlaceOptions.Builder favoriteCount(@Nullable Integer num) {
            this.f = num;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions.Builder
        public final PlaceOptions.Builder filter(@Nullable String str) {
            this.c = str;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions.Builder
        public final PlaceOptions.Builder hint(@Nullable String str) {
            this.w = str;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions.Builder
        public final PlaceOptions.Builder historyCount(@Nullable Integer num) {
            this.e = num;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions.Builder
        public final PlaceOptions.Builder hyperLocal(Boolean bool) {
            this.x = bool;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions.Builder
        public final PlaceOptions.Builder injectedPlaces(List<String> list) {
            this.l = list;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions.Builder
        public final PlaceOptions.Builder internalDebounce(Integer num) {
            Objects.requireNonNull(num, "Null internalDebounce");
            this.B = num;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions.Builder
        public final PlaceOptions.Builder internalMinCharactersForSearch(Integer num) {
            Objects.requireNonNull(num, "Null internalMinCharactersForSearch");
            this.v = num;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions.Builder
        public final PlaceOptions.Builder isShowCurrentLocation(Boolean bool) {
            Objects.requireNonNull(bool, "Null isShowCurrentLocation");
            this.z = bool;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions.Builder
        public final PlaceOptions.Builder limit(int i) {
            this.d = Integer.valueOf(i);
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions.Builder
        public final PlaceOptions.Builder location(@Nullable Point point) {
            this.f13135a = point;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions.Builder
        public final PlaceOptions.Builder logoSize(int i) {
            this.u = Integer.valueOf(i);
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions.Builder
        public final PlaceOptions.Builder pod(@Nullable String str) {
            this.j = str;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions.Builder
        public final PlaceOptions.Builder responseLang(@Nullable String str) {
            this.D = str;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions.Builder
        public final PlaceOptions.Builder saveHistory(Boolean bool) {
            Objects.requireNonNull(bool, "Null saveHistory");
            this.h = bool;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions.Builder
        public final PlaceOptions.Builder showPoweredByText(Boolean bool) {
            Objects.requireNonNull(bool, "Null showPoweredByText");
            this.q = bool;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions.Builder
        public final PlaceOptions.Builder statusBarColor(@Nullable Integer num) {
            this.p = num;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions.Builder
        public final PlaceOptions.Builder tokenizeAddress(@Nullable Boolean bool) {
            this.k = bool;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions.Builder
        public final PlaceOptions.Builder toolbarColor(int i) {
            this.o = Integer.valueOf(i);
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions.Builder
        public final PlaceOptions.Builder toolbarTintColor(int i) {
            this.r = Integer.valueOf(i);
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions.Builder
        public final PlaceOptions.Builder userAddedLocationEnable(Boolean bool) {
            Objects.requireNonNull(bool, "Null userAddedLocationEnable");
            this.b = bool;
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions.Builder
        public final PlaceOptions.Builder viewMode(int i) {
            this.m = Integer.valueOf(i);
            return this;
        }

        @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions.Builder
        public final PlaceOptions.Builder zoom(@Nullable Double d) {
            this.g = d;
            return this;
        }
    }

    public b(@Nullable Point point, Boolean bool, @Nullable String str, int i, @Nullable Integer num, @Nullable Integer num2, @Nullable Double d, Boolean bool2, Boolean bool3, @Nullable String str2, @Nullable Boolean bool4, @Nullable List<String> list, int i2, int i3, int i4, @Nullable Integer num3, Boolean bool5, int i5, int i6, int i7, int i8, Integer num4, @Nullable String str3, @Nullable Boolean bool6, @Nullable Boolean bool7, Boolean bool8, Integer num5, Integer num6, Integer num7, @Nullable String str4) {
        this.h = point;
        Objects.requireNonNull(bool, "Null userAddedLocationEnable");
        this.i = bool;
        this.j = str;
        this.k = i;
        this.l = num;
        this.m = num2;
        this.n = d;
        Objects.requireNonNull(bool2, "Null saveHistory");
        this.o = bool2;
        Objects.requireNonNull(bool3, "Null enableTextSearch");
        this.p = bool3;
        this.q = str2;
        this.r = bool4;
        this.s = list;
        this.t = i2;
        this.u = i3;
        this.v = i4;
        this.w = num3;
        Objects.requireNonNull(bool5, "Null showPoweredByText");
        this.x = bool5;
        this.y = i5;
        this.z = i6;
        this.A = i7;
        this.B = i8;
        Objects.requireNonNull(num4, "Null internalMinCharactersForSearch");
        this.C = num4;
        this.D = str3;
        this.E = bool6;
        this.F = bool7;
        Objects.requireNonNull(bool8, "Null isShowCurrentLocation");
        this.G = bool8;
        Objects.requireNonNull(num5, "Null currentLocationTextColor");
        this.H = num5;
        Objects.requireNonNull(num6, "Null internalDebounce");
        this.I = num6;
        Objects.requireNonNull(num7, "Null currentLocationIcon");
        this.J = num7;
        this.K = str4;
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions
    @NonNull
    public final int attributionHorizontalAlignment() {
        return this.A;
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions
    @NonNull
    public final int attributionVerticalAlignment() {
        return this.z;
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions
    public final int backgroundColor() {
        return this.u;
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions
    @Nullable
    public final Boolean bridge() {
        return this.F;
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions
    @NonNull
    @DrawableRes
    public final Integer currentLocationIcon() {
        return this.J;
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions
    @NonNull
    public final Integer currentLocationTextColor() {
        return this.H;
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions
    @NonNull
    public final Boolean enableTextSearch() {
        return this.p;
    }

    public final boolean equals(Object obj) {
        String str;
        Integer num;
        Integer num2;
        Double d;
        String str2;
        Boolean bool;
        List<String> list;
        Integer num3;
        String str3;
        Boolean bool2;
        Boolean bool3;
        if (obj == this) {
            return true;
        }
        if (obj instanceof PlaceOptions) {
            PlaceOptions placeOptions = (PlaceOptions) obj;
            Point point = this.h;
            if (point != null ? point.equals(placeOptions.location()) : placeOptions.location() == null) {
                if (this.i.equals(placeOptions.userAddedLocationEnable()) && ((str = this.j) != null ? str.equals(placeOptions.filter()) : placeOptions.filter() == null) && this.k == placeOptions.limit() && ((num = this.l) != null ? num.equals(placeOptions.historyCount()) : placeOptions.historyCount() == null) && ((num2 = this.m) != null ? num2.equals(placeOptions.favoriteCount()) : placeOptions.favoriteCount() == null) && ((d = this.n) != null ? d.equals(placeOptions.zoom()) : placeOptions.zoom() == null) && this.o.equals(placeOptions.saveHistory()) && this.p.equals(placeOptions.enableTextSearch()) && ((str2 = this.q) != null ? str2.equals(placeOptions.pod()) : placeOptions.pod() == null) && ((bool = this.r) != null ? bool.equals(placeOptions.tokenizeAddress()) : placeOptions.tokenizeAddress() == null) && ((list = this.s) != null ? list.equals(placeOptions.injectedPlaces()) : placeOptions.injectedPlaces() == null) && this.t == placeOptions.viewMode() && this.u == placeOptions.backgroundColor() && this.v == placeOptions.toolbarColor() && ((num3 = this.w) != null ? num3.equals(placeOptions.statusBarColor()) : placeOptions.statusBarColor() == null) && this.x.equals(placeOptions.showPoweredByText()) && this.y == placeOptions.toolbarTintColor() && this.z == placeOptions.attributionVerticalAlignment() && this.A == placeOptions.attributionHorizontalAlignment() && this.B == placeOptions.logoSize() && this.C.equals(placeOptions.internalMinCharactersForSearch()) && ((str3 = this.D) != null ? str3.equals(placeOptions.hint()) : placeOptions.hint() == null) && ((bool2 = this.E) != null ? bool2.equals(placeOptions.hyperLocal()) : placeOptions.hyperLocal() == null) && ((bool3 = this.F) != null ? bool3.equals(placeOptions.bridge()) : placeOptions.bridge() == null) && this.G.equals(placeOptions.isShowCurrentLocation()) && this.H.equals(placeOptions.currentLocationTextColor()) && this.I.equals(placeOptions.internalDebounce()) && this.J.equals(placeOptions.currentLocationIcon())) {
                    String str4 = this.K;
                    String responseLang = placeOptions.responseLang();
                    if (str4 == null) {
                        if (responseLang == null) {
                            return true;
                        }
                    } else if (str4.equals(responseLang)) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions
    @Nullable
    public final Integer favoriteCount() {
        return this.m;
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions
    @Nullable
    public final String filter() {
        return this.j;
    }

    public final int hashCode() {
        Point point = this.h;
        int hashCode = ((((point == null ? 0 : point.hashCode()) ^ 1000003) * 1000003) ^ this.i.hashCode()) * 1000003;
        String str = this.j;
        int hashCode2 = (((hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003) ^ this.k) * 1000003;
        Integer num = this.l;
        int hashCode3 = (hashCode2 ^ (num == null ? 0 : num.hashCode())) * 1000003;
        Integer num2 = this.m;
        int hashCode4 = (hashCode3 ^ (num2 == null ? 0 : num2.hashCode())) * 1000003;
        Double d = this.n;
        int hashCode5 = (((((hashCode4 ^ (d == null ? 0 : d.hashCode())) * 1000003) ^ this.o.hashCode()) * 1000003) ^ this.p.hashCode()) * 1000003;
        String str2 = this.q;
        int hashCode6 = (hashCode5 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        Boolean bool = this.r;
        int hashCode7 = (hashCode6 ^ (bool == null ? 0 : bool.hashCode())) * 1000003;
        List<String> list = this.s;
        int hashCode8 = (((((((hashCode7 ^ (list == null ? 0 : list.hashCode())) * 1000003) ^ this.t) * 1000003) ^ this.u) * 1000003) ^ this.v) * 1000003;
        Integer num3 = this.w;
        int hashCode9 = (((((((((((((hashCode8 ^ (num3 == null ? 0 : num3.hashCode())) * 1000003) ^ this.x.hashCode()) * 1000003) ^ this.y) * 1000003) ^ this.z) * 1000003) ^ this.A) * 1000003) ^ this.B) * 1000003) ^ this.C.hashCode()) * 1000003;
        String str3 = this.D;
        int hashCode10 = (hashCode9 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003;
        Boolean bool2 = this.E;
        int hashCode11 = (hashCode10 ^ (bool2 == null ? 0 : bool2.hashCode())) * 1000003;
        Boolean bool3 = this.F;
        int hashCode12 = (((((((((hashCode11 ^ (bool3 == null ? 0 : bool3.hashCode())) * 1000003) ^ this.G.hashCode()) * 1000003) ^ this.H.hashCode()) * 1000003) ^ this.I.hashCode()) * 1000003) ^ this.J.hashCode()) * 1000003;
        String str4 = this.K;
        return hashCode12 ^ (str4 != null ? str4.hashCode() : 0);
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions
    @Nullable
    public final String hint() {
        return this.D;
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions
    @Nullable
    public final Integer historyCount() {
        return this.l;
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions
    @Nullable
    public final Boolean hyperLocal() {
        return this.E;
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions
    @Nullable
    public final List<String> injectedPlaces() {
        return this.s;
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions
    @NonNull
    public final Integer internalDebounce() {
        return this.I;
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions
    @NonNull
    public final Integer internalMinCharactersForSearch() {
        return this.C;
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions
    @NonNull
    public final Boolean isShowCurrentLocation() {
        return this.G;
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions
    public final int limit() {
        return this.k;
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions
    @Nullable
    public final Point location() {
        return this.h;
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions
    @NonNull
    public final int logoSize() {
        return this.B;
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions
    @Nullable
    public final String pod() {
        return this.q;
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions
    @Nullable
    public final String responseLang() {
        return this.K;
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions
    @NonNull
    public final Boolean saveHistory() {
        return this.o;
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions
    @NonNull
    public final Boolean showPoweredByText() {
        return this.x;
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions
    @Nullable
    @ColorInt
    public final Integer statusBarColor() {
        return this.w;
    }

    public final String toString() {
        return "PlaceOptions{location=" + this.h + ", userAddedLocationEnable=" + this.i + ", filter=" + this.j + ", limit=" + this.k + ", historyCount=" + this.l + ", favoriteCount=" + this.m + ", zoom=" + this.n + ", saveHistory=" + this.o + ", enableTextSearch=" + this.p + ", pod=" + this.q + ", tokenizeAddress=" + this.r + ", injectedPlaces=" + this.s + ", viewMode=" + this.t + ", backgroundColor=" + this.u + ", toolbarColor=" + this.v + ", statusBarColor=" + this.w + ", showPoweredByText=" + this.x + ", toolbarTintColor=" + this.y + ", attributionVerticalAlignment=" + this.z + ", attributionHorizontalAlignment=" + this.A + ", logoSize=" + this.B + ", internalMinCharactersForSearch=" + this.C + ", hint=" + this.D + ", hyperLocal=" + this.E + ", bridge=" + this.F + ", isShowCurrentLocation=" + this.G + ", currentLocationTextColor=" + this.H + ", internalDebounce=" + this.I + ", currentLocationIcon=" + this.J + ", responseLang=" + this.K + "}";
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions
    @Nullable
    public final Boolean tokenizeAddress() {
        return this.r;
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions
    public final int toolbarColor() {
        return this.v;
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions
    @ColorInt
    public final int toolbarTintColor() {
        return this.y;
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions
    @NonNull
    public final Boolean userAddedLocationEnable() {
        return this.i;
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions
    public final int viewMode() {
        return this.t;
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions
    @Nullable
    public final Double zoom() {
        return this.n;
    }
}

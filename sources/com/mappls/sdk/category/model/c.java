package com.mappls.sdk.category.model;

import androidx.annotation.NonNull;
import com.mappls.sdk.category.model.SearchCategoryUIOption;
import java.util.Objects;
/* loaded from: classes11.dex */
public abstract class c extends SearchCategoryUIOption {
    public final Integer h;
    public final Integer i;
    public final Integer j;
    public final Integer k;
    public final Integer l;
    public final Integer m;
    public final Integer n;
    public final Integer o;
    public final Integer p;
    public final Integer q;
    public final Integer r;
    public final Integer s;
    public final Integer t;
    public final Float u;
    public final Integer v;

    /* loaded from: classes11.dex */
    public static class a extends SearchCategoryUIOption.Builder {

        /* renamed from: a  reason: collision with root package name */
        public Integer f12546a;
        public Integer b;
        public Integer c;
        public Integer d;
        public Integer e;
        public Integer f;
        public Integer g;
        public Integer h;
        public Integer i;
        public Integer j;
        public Integer k;
        public Integer l;
        public Integer m;
        public Float n;
        public Integer o;

        @Override // com.mappls.sdk.category.model.SearchCategoryUIOption.Builder
        public final SearchCategoryUIOption.Builder backIcon(Integer num) {
            Objects.requireNonNull(num, "Null backIcon");
            this.g = num;
            return this;
        }

        @Override // com.mappls.sdk.category.model.SearchCategoryUIOption.Builder
        public final SearchCategoryUIOption.Builder backgroundColor(Integer num) {
            Objects.requireNonNull(num, "Null backgroundColor");
            this.f = num;
            return this;
        }

        @Override // com.mappls.sdk.category.model.SearchCategoryUIOption.Builder
        public final SearchCategoryUIOption build() {
            String a2 = this.f12546a == null ? com.mappls.sdk.category.model.a.a("", " iconTintColor") : "";
            if (this.b == null) {
                a2 = com.mappls.sdk.category.model.a.a(a2, " itemTextColor");
            }
            if (this.c == null) {
                a2 = com.mappls.sdk.category.model.a.a(a2, " nextButtonTextColor");
            }
            if (this.d == null) {
                a2 = com.mappls.sdk.category.model.a.a(a2, " nextButtonColor");
            }
            if (this.e == null) {
                a2 = com.mappls.sdk.category.model.a.a(a2, " searchTextColor");
            }
            if (this.f == null) {
                a2 = com.mappls.sdk.category.model.a.a(a2, " backgroundColor");
            }
            if (this.g == null) {
                a2 = com.mappls.sdk.category.model.a.a(a2, " backIcon");
            }
            if (this.h == null) {
                a2 = com.mappls.sdk.category.model.a.a(a2, " hintTextColor");
            }
            if (this.i == null) {
                a2 = com.mappls.sdk.category.model.a.a(a2, " resultCountTextColor");
            }
            if (this.j == null) {
                a2 = com.mappls.sdk.category.model.a.a(a2, " resultPlaceNameTextColor");
            }
            if (this.k == null) {
                a2 = com.mappls.sdk.category.model.a.a(a2, " resultPlaceAddressTextColor");
            }
            if (this.l == null) {
                a2 = com.mappls.sdk.category.model.a.a(a2, " resultPlaceDistanceTextColor");
            }
            if (this.m == null) {
                a2 = com.mappls.sdk.category.model.a.a(a2, " resultMessageTextColor");
            }
            if (this.n == null) {
                a2 = com.mappls.sdk.category.model.a.a(a2, " polylineWidth");
            }
            if (this.o == null) {
                a2 = com.mappls.sdk.category.model.a.a(a2, " polylineColor");
            }
            if (a2.isEmpty()) {
                return new e(this.f12546a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o);
            }
            throw new IllegalStateException(com.mappls.sdk.category.model.a.a("Missing required properties:", a2));
        }

        @Override // com.mappls.sdk.category.model.SearchCategoryUIOption.Builder
        public final SearchCategoryUIOption.Builder hintTextColor(Integer num) {
            Objects.requireNonNull(num, "Null hintTextColor");
            this.h = num;
            return this;
        }

        @Override // com.mappls.sdk.category.model.SearchCategoryUIOption.Builder
        public final SearchCategoryUIOption.Builder iconTintColor(Integer num) {
            Objects.requireNonNull(num, "Null iconTintColor");
            this.f12546a = num;
            return this;
        }

        @Override // com.mappls.sdk.category.model.SearchCategoryUIOption.Builder
        public final SearchCategoryUIOption.Builder itemTextColor(Integer num) {
            Objects.requireNonNull(num, "Null itemTextColor");
            this.b = num;
            return this;
        }

        @Override // com.mappls.sdk.category.model.SearchCategoryUIOption.Builder
        public final SearchCategoryUIOption.Builder nextButtonColor(Integer num) {
            Objects.requireNonNull(num, "Null nextButtonColor");
            this.d = num;
            return this;
        }

        @Override // com.mappls.sdk.category.model.SearchCategoryUIOption.Builder
        public final SearchCategoryUIOption.Builder nextButtonTextColor(Integer num) {
            Objects.requireNonNull(num, "Null nextButtonTextColor");
            this.c = num;
            return this;
        }

        @Override // com.mappls.sdk.category.model.SearchCategoryUIOption.Builder
        public final SearchCategoryUIOption.Builder polylineColor(Integer num) {
            Objects.requireNonNull(num, "Null polylineColor");
            this.o = num;
            return this;
        }

        @Override // com.mappls.sdk.category.model.SearchCategoryUIOption.Builder
        public final SearchCategoryUIOption.Builder polylineWidth(Float f) {
            Objects.requireNonNull(f, "Null polylineWidth");
            this.n = f;
            return this;
        }

        @Override // com.mappls.sdk.category.model.SearchCategoryUIOption.Builder
        public final SearchCategoryUIOption.Builder resultCountTextColor(Integer num) {
            Objects.requireNonNull(num, "Null resultCountTextColor");
            this.i = num;
            return this;
        }

        @Override // com.mappls.sdk.category.model.SearchCategoryUIOption.Builder
        public final SearchCategoryUIOption.Builder resultMessageTextColor(Integer num) {
            Objects.requireNonNull(num, "Null resultMessageTextColor");
            this.m = num;
            return this;
        }

        @Override // com.mappls.sdk.category.model.SearchCategoryUIOption.Builder
        public final SearchCategoryUIOption.Builder resultPlaceAddressTextColor(Integer num) {
            Objects.requireNonNull(num, "Null resultPlaceAddressTextColor");
            this.k = num;
            return this;
        }

        @Override // com.mappls.sdk.category.model.SearchCategoryUIOption.Builder
        public final SearchCategoryUIOption.Builder resultPlaceDistanceTextColor(Integer num) {
            Objects.requireNonNull(num, "Null resultPlaceDistanceTextColor");
            this.l = num;
            return this;
        }

        @Override // com.mappls.sdk.category.model.SearchCategoryUIOption.Builder
        public final SearchCategoryUIOption.Builder resultPlaceNameTextColor(Integer num) {
            Objects.requireNonNull(num, "Null resultPlaceNameTextColor");
            this.j = num;
            return this;
        }

        @Override // com.mappls.sdk.category.model.SearchCategoryUIOption.Builder
        public final SearchCategoryUIOption.Builder searchTextColor(Integer num) {
            Objects.requireNonNull(num, "Null searchTextColor");
            this.e = num;
            return this;
        }
    }

    public c(Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, Integer num7, Integer num8, Integer num9, Integer num10, Integer num11, Integer num12, Integer num13, Float f, Integer num14) {
        Objects.requireNonNull(num, "Null iconTintColor");
        this.h = num;
        Objects.requireNonNull(num2, "Null itemTextColor");
        this.i = num2;
        Objects.requireNonNull(num3, "Null nextButtonTextColor");
        this.j = num3;
        Objects.requireNonNull(num4, "Null nextButtonColor");
        this.k = num4;
        Objects.requireNonNull(num5, "Null searchTextColor");
        this.l = num5;
        Objects.requireNonNull(num6, "Null backgroundColor");
        this.m = num6;
        Objects.requireNonNull(num7, "Null backIcon");
        this.n = num7;
        Objects.requireNonNull(num8, "Null hintTextColor");
        this.o = num8;
        Objects.requireNonNull(num9, "Null resultCountTextColor");
        this.p = num9;
        Objects.requireNonNull(num10, "Null resultPlaceNameTextColor");
        this.q = num10;
        Objects.requireNonNull(num11, "Null resultPlaceAddressTextColor");
        this.r = num11;
        Objects.requireNonNull(num12, "Null resultPlaceDistanceTextColor");
        this.s = num12;
        Objects.requireNonNull(num13, "Null resultMessageTextColor");
        this.t = num13;
        Objects.requireNonNull(f, "Null polylineWidth");
        this.u = f;
        Objects.requireNonNull(num14, "Null polylineColor");
        this.v = num14;
    }

    @Override // com.mappls.sdk.category.model.SearchCategoryUIOption
    @NonNull
    public final Integer backIcon() {
        return this.n;
    }

    @Override // com.mappls.sdk.category.model.SearchCategoryUIOption
    @NonNull
    public final Integer backgroundColor() {
        return this.m;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SearchCategoryUIOption) {
            SearchCategoryUIOption searchCategoryUIOption = (SearchCategoryUIOption) obj;
            return this.h.equals(searchCategoryUIOption.iconTintColor()) && this.i.equals(searchCategoryUIOption.itemTextColor()) && this.j.equals(searchCategoryUIOption.nextButtonTextColor()) && this.k.equals(searchCategoryUIOption.nextButtonColor()) && this.l.equals(searchCategoryUIOption.searchTextColor()) && this.m.equals(searchCategoryUIOption.backgroundColor()) && this.n.equals(searchCategoryUIOption.backIcon()) && this.o.equals(searchCategoryUIOption.hintTextColor()) && this.p.equals(searchCategoryUIOption.resultCountTextColor()) && this.q.equals(searchCategoryUIOption.resultPlaceNameTextColor()) && this.r.equals(searchCategoryUIOption.resultPlaceAddressTextColor()) && this.s.equals(searchCategoryUIOption.resultPlaceDistanceTextColor()) && this.t.equals(searchCategoryUIOption.resultMessageTextColor()) && this.u.equals(searchCategoryUIOption.polylineWidth()) && this.v.equals(searchCategoryUIOption.polylineColor());
        }
        return false;
    }

    public final int hashCode() {
        return ((((((((((((((((((((((((((((this.h.hashCode() ^ 1000003) * 1000003) ^ this.i.hashCode()) * 1000003) ^ this.j.hashCode()) * 1000003) ^ this.k.hashCode()) * 1000003) ^ this.l.hashCode()) * 1000003) ^ this.m.hashCode()) * 1000003) ^ this.n.hashCode()) * 1000003) ^ this.o.hashCode()) * 1000003) ^ this.p.hashCode()) * 1000003) ^ this.q.hashCode()) * 1000003) ^ this.r.hashCode()) * 1000003) ^ this.s.hashCode()) * 1000003) ^ this.t.hashCode()) * 1000003) ^ this.u.hashCode()) * 1000003) ^ this.v.hashCode();
    }

    @Override // com.mappls.sdk.category.model.SearchCategoryUIOption
    @NonNull
    public final Integer hintTextColor() {
        return this.o;
    }

    @Override // com.mappls.sdk.category.model.SearchCategoryUIOption
    @NonNull
    public final Integer iconTintColor() {
        return this.h;
    }

    @Override // com.mappls.sdk.category.model.SearchCategoryUIOption
    @NonNull
    public final Integer itemTextColor() {
        return this.i;
    }

    @Override // com.mappls.sdk.category.model.SearchCategoryUIOption
    @NonNull
    public final Integer nextButtonColor() {
        return this.k;
    }

    @Override // com.mappls.sdk.category.model.SearchCategoryUIOption
    @NonNull
    public final Integer nextButtonTextColor() {
        return this.j;
    }

    @Override // com.mappls.sdk.category.model.SearchCategoryUIOption
    @NonNull
    public final Integer polylineColor() {
        return this.v;
    }

    @Override // com.mappls.sdk.category.model.SearchCategoryUIOption
    @NonNull
    public final Float polylineWidth() {
        return this.u;
    }

    @Override // com.mappls.sdk.category.model.SearchCategoryUIOption
    @NonNull
    public final Integer resultCountTextColor() {
        return this.p;
    }

    @Override // com.mappls.sdk.category.model.SearchCategoryUIOption
    @NonNull
    public final Integer resultMessageTextColor() {
        return this.t;
    }

    @Override // com.mappls.sdk.category.model.SearchCategoryUIOption
    @NonNull
    public final Integer resultPlaceAddressTextColor() {
        return this.r;
    }

    @Override // com.mappls.sdk.category.model.SearchCategoryUIOption
    @NonNull
    public final Integer resultPlaceDistanceTextColor() {
        return this.s;
    }

    @Override // com.mappls.sdk.category.model.SearchCategoryUIOption
    @NonNull
    public final Integer resultPlaceNameTextColor() {
        return this.q;
    }

    @Override // com.mappls.sdk.category.model.SearchCategoryUIOption
    @NonNull
    public final Integer searchTextColor() {
        return this.l;
    }

    public final String toString() {
        return "SearchCategoryUIOption{iconTintColor=" + this.h + ", itemTextColor=" + this.i + ", nextButtonTextColor=" + this.j + ", nextButtonColor=" + this.k + ", searchTextColor=" + this.l + ", backgroundColor=" + this.m + ", backIcon=" + this.n + ", hintTextColor=" + this.o + ", resultCountTextColor=" + this.p + ", resultPlaceNameTextColor=" + this.q + ", resultPlaceAddressTextColor=" + this.r + ", resultPlaceDistanceTextColor=" + this.s + ", resultMessageTextColor=" + this.t + ", polylineWidth=" + this.u + ", polylineColor=" + this.v + "}";
    }
}

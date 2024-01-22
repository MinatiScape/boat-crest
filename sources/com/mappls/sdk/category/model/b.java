package com.mappls.sdk.category.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.category.model.SearchCategoryOption;
import java.util.Objects;
/* loaded from: classes11.dex */
public abstract class b extends SearchCategoryOption {
    public final Integer h;
    public final Integer i;
    public final Boolean j;
    public final Integer k;
    public final Integer l;
    public final String m;
    public final String n;
    public final String o;
    public final String p;
    public final String q;
    public final String r;
    public final Boolean s;
    public final Boolean t;
    public final String u;
    public final String v;
    public final String w;
    public final Integer x;
    public final Boolean y;
    public final Boolean z;

    /* loaded from: classes11.dex */
    public static class a extends SearchCategoryOption.Builder {

        /* renamed from: a  reason: collision with root package name */
        public Integer f12545a;
        public Integer b;
        public Boolean c;
        public Integer d;
        public Integer e;
        public String f;
        public String g;
        public String h;
        public String i;
        public String j;
        public String k;
        public Boolean l;
        public Boolean m;
        public String n;
        public String o;
        public String p;
        public Integer q;
        public Boolean r;
        public Boolean s;

        @Override // com.mappls.sdk.category.model.SearchCategoryOption.Builder
        public final SearchCategoryOption.Builder bounds(String str) {
            this.i = str;
            return this;
        }

        @Override // com.mappls.sdk.category.model.SearchCategoryOption.Builder
        public final SearchCategoryOption.Builder buffer(@Nullable Integer num) {
            this.q = num;
            return this;
        }

        @Override // com.mappls.sdk.category.model.SearchCategoryOption.Builder
        public final SearchCategoryOption build() {
            String a2 = this.f12545a == null ? com.mappls.sdk.category.model.a.a("", " maxSelectionCount") : "";
            if (this.b == null) {
                a2 = com.mappls.sdk.category.model.a.a(a2, " searchType");
            }
            if (this.c == null) {
                a2 = com.mappls.sdk.category.model.a.a(a2, " isUsingInternalMap");
            }
            if (this.d == null) {
                a2 = com.mappls.sdk.category.model.a.a(a2, " radius");
            }
            if (this.s == null) {
                a2 = com.mappls.sdk.category.model.a.a(a2, " showRequestOnMap");
            }
            if (a2.isEmpty()) {
                return new d(this.f12545a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.r, this.s);
            }
            throw new IllegalStateException(com.mappls.sdk.category.model.a.a("Missing required properties:", a2));
        }

        @Override // com.mappls.sdk.category.model.SearchCategoryOption.Builder
        public final SearchCategoryOption.Builder explain(Boolean bool) {
            this.l = bool;
            return this;
        }

        @Override // com.mappls.sdk.category.model.SearchCategoryOption.Builder
        public final SearchCategoryOption.Builder filter(String str) {
            this.k = str;
            return this;
        }

        @Override // com.mappls.sdk.category.model.SearchCategoryOption.Builder
        public final SearchCategoryOption.Builder geometries(@Nullable String str) {
            this.p = str;
            return this;
        }

        @Override // com.mappls.sdk.category.model.SearchCategoryOption.Builder
        public final SearchCategoryOption.Builder isSort(@Nullable Boolean bool) {
            this.r = bool;
            return this;
        }

        @Override // com.mappls.sdk.category.model.SearchCategoryOption.Builder
        public final SearchCategoryOption.Builder isUsingInternalMap(Boolean bool) {
            Objects.requireNonNull(bool, "Null isUsingInternalMap");
            this.c = bool;
            return this;
        }

        @Override // com.mappls.sdk.category.model.SearchCategoryOption.Builder
        public final SearchCategoryOption.Builder location(@Nullable String str) {
            this.f = str;
            return this;
        }

        @Override // com.mappls.sdk.category.model.SearchCategoryOption.Builder
        public final SearchCategoryOption.Builder maxSelectionCount(Integer num) {
            Objects.requireNonNull(num, "Null maxSelectionCount");
            this.f12545a = num;
            return this;
        }

        @Override // com.mappls.sdk.category.model.SearchCategoryOption.Builder
        public final SearchCategoryOption.Builder page(@Nullable Integer num) {
            this.e = num;
            return this;
        }

        @Override // com.mappls.sdk.category.model.SearchCategoryOption.Builder
        public final SearchCategoryOption.Builder path(@Nullable String str) {
            this.o = str;
            return this;
        }

        @Override // com.mappls.sdk.category.model.SearchCategoryOption.Builder
        public final SearchCategoryOption.Builder pod(String str) {
            this.j = str;
            return this;
        }

        @Override // com.mappls.sdk.category.model.SearchCategoryOption.Builder
        public final SearchCategoryOption.Builder radius(Integer num) {
            Objects.requireNonNull(num, "Null radius");
            this.d = num;
            return this;
        }

        @Override // com.mappls.sdk.category.model.SearchCategoryOption.Builder
        public final SearchCategoryOption.Builder richData(Boolean bool) {
            this.m = bool;
            return this;
        }

        @Override // com.mappls.sdk.category.model.SearchCategoryOption.Builder
        public final SearchCategoryOption.Builder searchBy(String str) {
            this.h = str;
            return this;
        }

        @Override // com.mappls.sdk.category.model.SearchCategoryOption.Builder
        public final SearchCategoryOption.Builder searchType(Integer num) {
            Objects.requireNonNull(num, "Null searchType");
            this.b = num;
            return this;
        }

        @Override // com.mappls.sdk.category.model.SearchCategoryOption.Builder
        public final SearchCategoryOption.Builder showRequestOnMap(Boolean bool) {
            Objects.requireNonNull(bool, "Null showRequestOnMap");
            this.s = bool;
            return this;
        }

        @Override // com.mappls.sdk.category.model.SearchCategoryOption.Builder
        public final SearchCategoryOption.Builder sortBy(String str) {
            this.g = str;
            return this;
        }

        @Override // com.mappls.sdk.category.model.SearchCategoryOption.Builder
        public final SearchCategoryOption.Builder userName(String str) {
            this.n = str;
            return this;
        }
    }

    public b(Integer num, Integer num2, Boolean bool, Integer num3, @Nullable Integer num4, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable Boolean bool2, @Nullable Boolean bool3, @Nullable String str7, @Nullable String str8, @Nullable String str9, @Nullable Integer num5, @Nullable Boolean bool4, Boolean bool5) {
        Objects.requireNonNull(num, "Null maxSelectionCount");
        this.h = num;
        Objects.requireNonNull(num2, "Null searchType");
        this.i = num2;
        Objects.requireNonNull(bool, "Null isUsingInternalMap");
        this.j = bool;
        Objects.requireNonNull(num3, "Null radius");
        this.k = num3;
        this.l = num4;
        this.m = str;
        this.n = str2;
        this.o = str3;
        this.p = str4;
        this.q = str5;
        this.r = str6;
        this.s = bool2;
        this.t = bool3;
        this.u = str7;
        this.v = str8;
        this.w = str9;
        this.x = num5;
        this.y = bool4;
        Objects.requireNonNull(bool5, "Null showRequestOnMap");
        this.z = bool5;
    }

    @Override // com.mappls.sdk.category.model.SearchCategoryOption
    @Nullable
    public final String bounds() {
        return this.p;
    }

    @Override // com.mappls.sdk.category.model.SearchCategoryOption
    @Nullable
    public final Integer buffer() {
        return this.x;
    }

    public final boolean equals(Object obj) {
        Integer num;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        Boolean bool;
        Boolean bool2;
        String str7;
        String str8;
        String str9;
        Integer num2;
        Boolean bool3;
        if (obj == this) {
            return true;
        }
        if (obj instanceof SearchCategoryOption) {
            SearchCategoryOption searchCategoryOption = (SearchCategoryOption) obj;
            return this.h.equals(searchCategoryOption.maxSelectionCount()) && this.i.equals(searchCategoryOption.searchType()) && this.j.equals(searchCategoryOption.isUsingInternalMap()) && this.k.equals(searchCategoryOption.radius()) && ((num = this.l) != null ? num.equals(searchCategoryOption.page()) : searchCategoryOption.page() == null) && ((str = this.m) != null ? str.equals(searchCategoryOption.location()) : searchCategoryOption.location() == null) && ((str2 = this.n) != null ? str2.equals(searchCategoryOption.sortBy()) : searchCategoryOption.sortBy() == null) && ((str3 = this.o) != null ? str3.equals(searchCategoryOption.searchBy()) : searchCategoryOption.searchBy() == null) && ((str4 = this.p) != null ? str4.equals(searchCategoryOption.bounds()) : searchCategoryOption.bounds() == null) && ((str5 = this.q) != null ? str5.equals(searchCategoryOption.pod()) : searchCategoryOption.pod() == null) && ((str6 = this.r) != null ? str6.equals(searchCategoryOption.filter()) : searchCategoryOption.filter() == null) && ((bool = this.s) != null ? bool.equals(searchCategoryOption.explain()) : searchCategoryOption.explain() == null) && ((bool2 = this.t) != null ? bool2.equals(searchCategoryOption.richData()) : searchCategoryOption.richData() == null) && ((str7 = this.u) != null ? str7.equals(searchCategoryOption.userName()) : searchCategoryOption.userName() == null) && ((str8 = this.v) != null ? str8.equals(searchCategoryOption.path()) : searchCategoryOption.path() == null) && ((str9 = this.w) != null ? str9.equals(searchCategoryOption.geometries()) : searchCategoryOption.geometries() == null) && ((num2 = this.x) != null ? num2.equals(searchCategoryOption.buffer()) : searchCategoryOption.buffer() == null) && ((bool3 = this.y) != null ? bool3.equals(searchCategoryOption.isSort()) : searchCategoryOption.isSort() == null) && this.z.equals(searchCategoryOption.showRequestOnMap());
        }
        return false;
    }

    @Override // com.mappls.sdk.category.model.SearchCategoryOption
    @Nullable
    public final Boolean explain() {
        return this.s;
    }

    @Override // com.mappls.sdk.category.model.SearchCategoryOption
    @Nullable
    public final String filter() {
        return this.r;
    }

    @Override // com.mappls.sdk.category.model.SearchCategoryOption
    @Nullable
    public final String geometries() {
        return this.w;
    }

    public final int hashCode() {
        int hashCode = (((((((this.h.hashCode() ^ 1000003) * 1000003) ^ this.i.hashCode()) * 1000003) ^ this.j.hashCode()) * 1000003) ^ this.k.hashCode()) * 1000003;
        Integer num = this.l;
        int hashCode2 = (hashCode ^ (num == null ? 0 : num.hashCode())) * 1000003;
        String str = this.m;
        int hashCode3 = (hashCode2 ^ (str == null ? 0 : str.hashCode())) * 1000003;
        String str2 = this.n;
        int hashCode4 = (hashCode3 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.o;
        int hashCode5 = (hashCode4 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003;
        String str4 = this.p;
        int hashCode6 = (hashCode5 ^ (str4 == null ? 0 : str4.hashCode())) * 1000003;
        String str5 = this.q;
        int hashCode7 = (hashCode6 ^ (str5 == null ? 0 : str5.hashCode())) * 1000003;
        String str6 = this.r;
        int hashCode8 = (hashCode7 ^ (str6 == null ? 0 : str6.hashCode())) * 1000003;
        Boolean bool = this.s;
        int hashCode9 = (hashCode8 ^ (bool == null ? 0 : bool.hashCode())) * 1000003;
        Boolean bool2 = this.t;
        int hashCode10 = (hashCode9 ^ (bool2 == null ? 0 : bool2.hashCode())) * 1000003;
        String str7 = this.u;
        int hashCode11 = (hashCode10 ^ (str7 == null ? 0 : str7.hashCode())) * 1000003;
        String str8 = this.v;
        int hashCode12 = (hashCode11 ^ (str8 == null ? 0 : str8.hashCode())) * 1000003;
        String str9 = this.w;
        int hashCode13 = (hashCode12 ^ (str9 == null ? 0 : str9.hashCode())) * 1000003;
        Integer num2 = this.x;
        int hashCode14 = (hashCode13 ^ (num2 == null ? 0 : num2.hashCode())) * 1000003;
        Boolean bool3 = this.y;
        return ((hashCode14 ^ (bool3 != null ? bool3.hashCode() : 0)) * 1000003) ^ this.z.hashCode();
    }

    @Override // com.mappls.sdk.category.model.SearchCategoryOption
    @Nullable
    public final Boolean isSort() {
        return this.y;
    }

    @Override // com.mappls.sdk.category.model.SearchCategoryOption
    @NonNull
    public final Boolean isUsingInternalMap() {
        return this.j;
    }

    @Override // com.mappls.sdk.category.model.SearchCategoryOption
    @Nullable
    public final String location() {
        return this.m;
    }

    @Override // com.mappls.sdk.category.model.SearchCategoryOption
    @NonNull
    public final Integer maxSelectionCount() {
        return this.h;
    }

    @Override // com.mappls.sdk.category.model.SearchCategoryOption
    @Nullable
    public final Integer page() {
        return this.l;
    }

    @Override // com.mappls.sdk.category.model.SearchCategoryOption
    @Nullable
    public final String path() {
        return this.v;
    }

    @Override // com.mappls.sdk.category.model.SearchCategoryOption
    @Nullable
    public final String pod() {
        return this.q;
    }

    @Override // com.mappls.sdk.category.model.SearchCategoryOption
    @NonNull
    public final Integer radius() {
        return this.k;
    }

    @Override // com.mappls.sdk.category.model.SearchCategoryOption
    @Nullable
    public final Boolean richData() {
        return this.t;
    }

    @Override // com.mappls.sdk.category.model.SearchCategoryOption
    @Nullable
    public final String searchBy() {
        return this.o;
    }

    @Override // com.mappls.sdk.category.model.SearchCategoryOption
    @NonNull
    public final Integer searchType() {
        return this.i;
    }

    @Override // com.mappls.sdk.category.model.SearchCategoryOption
    @NonNull
    public final Boolean showRequestOnMap() {
        return this.z;
    }

    @Override // com.mappls.sdk.category.model.SearchCategoryOption
    @Nullable
    public final String sortBy() {
        return this.n;
    }

    public final String toString() {
        return "SearchCategoryOption{maxSelectionCount=" + this.h + ", searchType=" + this.i + ", isUsingInternalMap=" + this.j + ", radius=" + this.k + ", page=" + this.l + ", location=" + this.m + ", sortBy=" + this.n + ", searchBy=" + this.o + ", bounds=" + this.p + ", pod=" + this.q + ", filter=" + this.r + ", explain=" + this.s + ", richData=" + this.t + ", userName=" + this.u + ", path=" + this.v + ", geometries=" + this.w + ", buffer=" + this.x + ", isSort=" + this.y + ", showRequestOnMap=" + this.z + "}";
    }

    @Override // com.mappls.sdk.category.model.SearchCategoryOption
    @Nullable
    public final String userName() {
        return this.u;
    }
}

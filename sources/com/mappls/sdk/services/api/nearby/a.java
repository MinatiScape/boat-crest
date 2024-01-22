package com.mappls.sdk.services.api.nearby;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.services.api.nearby.MapplsNearby;
import java.util.Objects;
/* loaded from: classes6.dex */
public final class a extends MapplsNearby {

    /* renamed from: a  reason: collision with root package name */
    public final String f13249a;
    public final String b;
    public final String c;
    public final Integer d;
    public final String e;
    public final String f;
    public final Integer g;
    public final String h;
    public final String i;
    public final String j;
    public final Boolean k;
    public final Boolean l;
    public final String m;
    public final String n;
    public final Boolean o;

    /* loaded from: classes6.dex */
    public static final class b extends MapplsNearby.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13250a;
        public String b;
        public String c;
        public Integer d;
        public String e;
        public String f;
        public Integer g;
        public String h;
        public String i;
        public String j;
        public Boolean k;
        public Boolean l;
        public String m;
        public String n;
        public Boolean o;

        @Override // com.mappls.sdk.services.api.nearby.MapplsNearby.Builder
        public MapplsNearby autoBuild() {
            String str = "";
            if (this.f13250a == null) {
                str = " baseUrl";
            }
            if (this.b == null) {
                str = str + " keywordString";
            }
            if (this.c == null) {
                str = str + " location";
            }
            if (str.isEmpty()) {
                return new a(this.f13250a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.nearby.MapplsNearby.Builder
        public MapplsNearby.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f13250a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.nearby.MapplsNearby.Builder
        public MapplsNearby.Builder bounds(String str) {
            this.h = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.nearby.MapplsNearby.Builder
        public MapplsNearby.Builder explain(Boolean bool) {
            this.k = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.nearby.MapplsNearby.Builder
        public MapplsNearby.Builder filter(String str) {
            this.j = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.nearby.MapplsNearby.Builder
        public MapplsNearby.Builder ignoreAutoExpand(Boolean bool) {
            this.o = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.nearby.MapplsNearby.Builder
        public MapplsNearby.Builder internalIncludes(String str) {
            this.n = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.nearby.MapplsNearby.Builder
        public MapplsNearby.Builder keywordString(String str) {
            Objects.requireNonNull(str, "Null keywordString");
            this.b = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.nearby.MapplsNearby.Builder
        public MapplsNearby.Builder location(String str) {
            Objects.requireNonNull(str, "Null location");
            this.c = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.nearby.MapplsNearby.Builder
        public MapplsNearby.Builder page(Integer num) {
            this.d = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.nearby.MapplsNearby.Builder
        public MapplsNearby.Builder pod(String str) {
            this.i = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.nearby.MapplsNearby.Builder
        public MapplsNearby.Builder radius(Integer num) {
            this.g = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.nearby.MapplsNearby.Builder
        public MapplsNearby.Builder richData(Boolean bool) {
            this.l = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.nearby.MapplsNearby.Builder
        public MapplsNearby.Builder searchBy(String str) {
            this.f = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.nearby.MapplsNearby.Builder
        public MapplsNearby.Builder sortBy(String str) {
            this.e = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.nearby.MapplsNearby.Builder
        public MapplsNearby.Builder userName(String str) {
            this.m = str;
            return this;
        }
    }

    @Override // com.mappls.sdk.services.api.nearby.MapplsNearby, com.mappls.sdk.services.api.MapplsService
    @NonNull
    public String baseUrl() {
        return this.f13249a;
    }

    @Override // com.mappls.sdk.services.api.nearby.MapplsNearby
    @Nullable
    public String bounds() {
        return this.h;
    }

    public boolean equals(Object obj) {
        Integer num;
        String str;
        String str2;
        Integer num2;
        String str3;
        String str4;
        String str5;
        Boolean bool;
        Boolean bool2;
        String str6;
        String str7;
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsNearby) {
            MapplsNearby mapplsNearby = (MapplsNearby) obj;
            if (this.f13249a.equals(mapplsNearby.baseUrl()) && this.b.equals(mapplsNearby.keywordString()) && this.c.equals(mapplsNearby.location()) && ((num = this.d) != null ? num.equals(mapplsNearby.page()) : mapplsNearby.page() == null) && ((str = this.e) != null ? str.equals(mapplsNearby.sortBy()) : mapplsNearby.sortBy() == null) && ((str2 = this.f) != null ? str2.equals(mapplsNearby.searchBy()) : mapplsNearby.searchBy() == null) && ((num2 = this.g) != null ? num2.equals(mapplsNearby.radius()) : mapplsNearby.radius() == null) && ((str3 = this.h) != null ? str3.equals(mapplsNearby.bounds()) : mapplsNearby.bounds() == null) && ((str4 = this.i) != null ? str4.equals(mapplsNearby.pod()) : mapplsNearby.pod() == null) && ((str5 = this.j) != null ? str5.equals(mapplsNearby.filter()) : mapplsNearby.filter() == null) && ((bool = this.k) != null ? bool.equals(mapplsNearby.explain()) : mapplsNearby.explain() == null) && ((bool2 = this.l) != null ? bool2.equals(mapplsNearby.richData()) : mapplsNearby.richData() == null) && ((str6 = this.m) != null ? str6.equals(mapplsNearby.userName()) : mapplsNearby.userName() == null) && ((str7 = this.n) != null ? str7.equals(mapplsNearby.internalIncludes()) : mapplsNearby.internalIncludes() == null)) {
                Boolean bool3 = this.o;
                if (bool3 == null) {
                    if (mapplsNearby.ignoreAutoExpand() == null) {
                        return true;
                    }
                } else if (bool3.equals(mapplsNearby.ignoreAutoExpand())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override // com.mappls.sdk.services.api.nearby.MapplsNearby
    @Nullable
    public Boolean explain() {
        return this.k;
    }

    @Override // com.mappls.sdk.services.api.nearby.MapplsNearby
    @Nullable
    public String filter() {
        return this.j;
    }

    public int hashCode() {
        int hashCode = (((((this.f13249a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode()) * 1000003;
        Integer num = this.d;
        int hashCode2 = (hashCode ^ (num == null ? 0 : num.hashCode())) * 1000003;
        String str = this.e;
        int hashCode3 = (hashCode2 ^ (str == null ? 0 : str.hashCode())) * 1000003;
        String str2 = this.f;
        int hashCode4 = (hashCode3 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        Integer num2 = this.g;
        int hashCode5 = (hashCode4 ^ (num2 == null ? 0 : num2.hashCode())) * 1000003;
        String str3 = this.h;
        int hashCode6 = (hashCode5 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003;
        String str4 = this.i;
        int hashCode7 = (hashCode6 ^ (str4 == null ? 0 : str4.hashCode())) * 1000003;
        String str5 = this.j;
        int hashCode8 = (hashCode7 ^ (str5 == null ? 0 : str5.hashCode())) * 1000003;
        Boolean bool = this.k;
        int hashCode9 = (hashCode8 ^ (bool == null ? 0 : bool.hashCode())) * 1000003;
        Boolean bool2 = this.l;
        int hashCode10 = (hashCode9 ^ (bool2 == null ? 0 : bool2.hashCode())) * 1000003;
        String str6 = this.m;
        int hashCode11 = (hashCode10 ^ (str6 == null ? 0 : str6.hashCode())) * 1000003;
        String str7 = this.n;
        int hashCode12 = (hashCode11 ^ (str7 == null ? 0 : str7.hashCode())) * 1000003;
        Boolean bool3 = this.o;
        return hashCode12 ^ (bool3 != null ? bool3.hashCode() : 0);
    }

    @Override // com.mappls.sdk.services.api.nearby.MapplsNearby
    @Nullable
    public Boolean ignoreAutoExpand() {
        return this.o;
    }

    @Override // com.mappls.sdk.services.api.nearby.MapplsNearby
    @Nullable
    public String internalIncludes() {
        return this.n;
    }

    @Override // com.mappls.sdk.services.api.nearby.MapplsNearby
    @NonNull
    public String keywordString() {
        return this.b;
    }

    @Override // com.mappls.sdk.services.api.nearby.MapplsNearby
    @NonNull
    public String location() {
        return this.c;
    }

    @Override // com.mappls.sdk.services.api.nearby.MapplsNearby
    @Nullable
    public Integer page() {
        return this.d;
    }

    @Override // com.mappls.sdk.services.api.nearby.MapplsNearby
    @Nullable
    public String pod() {
        return this.i;
    }

    @Override // com.mappls.sdk.services.api.nearby.MapplsNearby
    @Nullable
    public Integer radius() {
        return this.g;
    }

    @Override // com.mappls.sdk.services.api.nearby.MapplsNearby
    @Nullable
    public Boolean richData() {
        return this.l;
    }

    @Override // com.mappls.sdk.services.api.nearby.MapplsNearby
    @Nullable
    public String searchBy() {
        return this.f;
    }

    @Override // com.mappls.sdk.services.api.nearby.MapplsNearby
    @Nullable
    public String sortBy() {
        return this.e;
    }

    public String toString() {
        return "MapplsNearby{baseUrl=" + this.f13249a + ", keywordString=" + this.b + ", location=" + this.c + ", page=" + this.d + ", sortBy=" + this.e + ", searchBy=" + this.f + ", radius=" + this.g + ", bounds=" + this.h + ", pod=" + this.i + ", filter=" + this.j + ", explain=" + this.k + ", richData=" + this.l + ", userName=" + this.m + ", internalIncludes=" + this.n + ", ignoreAutoExpand=" + this.o + "}";
    }

    @Override // com.mappls.sdk.services.api.nearby.MapplsNearby
    @Nullable
    public String userName() {
        return this.m;
    }

    public a(String str, String str2, String str3, @Nullable Integer num, @Nullable String str4, @Nullable String str5, @Nullable Integer num2, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable Boolean bool, @Nullable Boolean bool2, @Nullable String str9, @Nullable String str10, @Nullable Boolean bool3) {
        this.f13249a = str;
        this.b = str2;
        this.c = str3;
        this.d = num;
        this.e = str4;
        this.f = str5;
        this.g = num2;
        this.h = str6;
        this.i = str7;
        this.j = str8;
        this.k = bool;
        this.l = bool2;
        this.m = str9;
        this.n = str10;
        this.o = bool3;
    }
}

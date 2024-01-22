package com.mappls.sdk.services.api.textsearch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.services.api.textsearch.MapplsTextSearch;
import java.util.Objects;
/* loaded from: classes8.dex */
public final class a extends MapplsTextSearch {

    /* renamed from: a  reason: collision with root package name */
    public final String f13284a;
    public final String b;
    public final String c;
    public final Boolean d;
    public final Boolean e;
    public final String f;
    public final String g;

    /* loaded from: classes8.dex */
    public static final class b extends MapplsTextSearch.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13285a;
        public String b;
        public String c;
        public Boolean d;
        public Boolean e;
        public String f;
        public String g;

        @Override // com.mappls.sdk.services.api.textsearch.MapplsTextSearch.Builder
        public MapplsTextSearch autoBuild() {
            String str = "";
            if (this.f13285a == null) {
                str = " baseUrl";
            }
            if (this.b == null) {
                str = str + " queryString";
            }
            if (str.isEmpty()) {
                return new a(this.f13285a, this.b, this.c, this.d, this.e, this.f, this.g);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.textsearch.MapplsTextSearch.Builder
        public MapplsTextSearch.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f13285a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.textsearch.MapplsTextSearch.Builder
        public MapplsTextSearch.Builder bridge(@Nullable Boolean bool) {
            this.d = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.textsearch.MapplsTextSearch.Builder
        public MapplsTextSearch.Builder explain(@Nullable Boolean bool) {
            this.e = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.textsearch.MapplsTextSearch.Builder
        public MapplsTextSearch.Builder filter(String str) {
            this.g = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.textsearch.MapplsTextSearch.Builder
        public MapplsTextSearch.Builder location(@Nullable String str) {
            this.c = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.textsearch.MapplsTextSearch.Builder
        public MapplsTextSearch.Builder queryString(String str) {
            Objects.requireNonNull(str, "Null queryString");
            this.b = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.textsearch.MapplsTextSearch.Builder
        public MapplsTextSearch.Builder username(String str) {
            this.f = str;
            return this;
        }
    }

    @Override // com.mappls.sdk.services.api.textsearch.MapplsTextSearch, com.mappls.sdk.services.api.MapplsService
    @NonNull
    public String baseUrl() {
        return this.f13284a;
    }

    @Override // com.mappls.sdk.services.api.textsearch.MapplsTextSearch
    @Nullable
    public Boolean bridge() {
        return this.d;
    }

    public boolean equals(Object obj) {
        String str;
        Boolean bool;
        Boolean bool2;
        String str2;
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsTextSearch) {
            MapplsTextSearch mapplsTextSearch = (MapplsTextSearch) obj;
            if (this.f13284a.equals(mapplsTextSearch.baseUrl()) && this.b.equals(mapplsTextSearch.queryString()) && ((str = this.c) != null ? str.equals(mapplsTextSearch.location()) : mapplsTextSearch.location() == null) && ((bool = this.d) != null ? bool.equals(mapplsTextSearch.bridge()) : mapplsTextSearch.bridge() == null) && ((bool2 = this.e) != null ? bool2.equals(mapplsTextSearch.explain()) : mapplsTextSearch.explain() == null) && ((str2 = this.f) != null ? str2.equals(mapplsTextSearch.username()) : mapplsTextSearch.username() == null)) {
                String str3 = this.g;
                if (str3 == null) {
                    if (mapplsTextSearch.filter() == null) {
                        return true;
                    }
                } else if (str3.equals(mapplsTextSearch.filter())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override // com.mappls.sdk.services.api.textsearch.MapplsTextSearch
    @Nullable
    public Boolean explain() {
        return this.e;
    }

    @Override // com.mappls.sdk.services.api.textsearch.MapplsTextSearch
    @Nullable
    public String filter() {
        return this.g;
    }

    public int hashCode() {
        int hashCode = (((this.f13284a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003;
        String str = this.c;
        int hashCode2 = (hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003;
        Boolean bool = this.d;
        int hashCode3 = (hashCode2 ^ (bool == null ? 0 : bool.hashCode())) * 1000003;
        Boolean bool2 = this.e;
        int hashCode4 = (hashCode3 ^ (bool2 == null ? 0 : bool2.hashCode())) * 1000003;
        String str2 = this.f;
        int hashCode5 = (hashCode4 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.g;
        return hashCode5 ^ (str3 != null ? str3.hashCode() : 0);
    }

    @Override // com.mappls.sdk.services.api.textsearch.MapplsTextSearch
    @Nullable
    public String location() {
        return this.c;
    }

    @Override // com.mappls.sdk.services.api.textsearch.MapplsTextSearch
    @NonNull
    public String queryString() {
        return this.b;
    }

    public String toString() {
        return "MapplsTextSearch{baseUrl=" + this.f13284a + ", queryString=" + this.b + ", location=" + this.c + ", bridge=" + this.d + ", explain=" + this.e + ", username=" + this.f + ", filter=" + this.g + "}";
    }

    @Override // com.mappls.sdk.services.api.textsearch.MapplsTextSearch
    @Nullable
    public String username() {
        return this.f;
    }

    public a(String str, String str2, @Nullable String str3, @Nullable Boolean bool, @Nullable Boolean bool2, @Nullable String str4, @Nullable String str5) {
        this.f13284a = str;
        this.b = str2;
        this.c = str3;
        this.d = bool;
        this.e = bool2;
        this.f = str4;
        this.g = str5;
    }
}

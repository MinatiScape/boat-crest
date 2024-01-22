package com.mappls.sdk.services.api.autosuggest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.services.api.autosuggest.MapplsAutoSuggest;
import java.util.Objects;
/* loaded from: classes11.dex */
public final class a extends MapplsAutoSuggest {

    /* renamed from: a  reason: collision with root package name */
    public final String f13157a;
    public final String b;
    public final String c;
    public final Double d;
    public final Boolean e;
    public final String f;
    public final String g;
    public final Boolean h;
    public final Boolean i;
    public final String j;
    public final String k;
    public final Boolean l;
    public final String m;

    /* loaded from: classes11.dex */
    public static final class b extends MapplsAutoSuggest.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13158a;
        public String b;
        public String c;
        public Double d;
        public Boolean e;
        public String f;
        public String g;
        public Boolean h;
        public Boolean i;
        public String j;
        public String k;
        public Boolean l;
        public String m;

        @Override // com.mappls.sdk.services.api.autosuggest.MapplsAutoSuggest.Builder
        public MapplsAutoSuggest autoBuild() {
            String str = "";
            if (this.f13158a == null) {
                str = " baseUrl";
            }
            if (this.b == null) {
                str = str + " internalQuery";
            }
            if (str.isEmpty()) {
                return new a(this.f13158a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.autosuggest.MapplsAutoSuggest.Builder
        public MapplsAutoSuggest.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f13158a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.autosuggest.MapplsAutoSuggest.Builder
        public MapplsAutoSuggest.Builder bridge(@Nullable Boolean bool) {
            this.h = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.autosuggest.MapplsAutoSuggest.Builder
        public MapplsAutoSuggest.Builder explain(@Nullable Boolean bool) {
            this.l = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.autosuggest.MapplsAutoSuggest.Builder
        public MapplsAutoSuggest.Builder filter(@Nullable String str) {
            this.g = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.autosuggest.MapplsAutoSuggest.Builder
        public MapplsAutoSuggest.Builder hyperLocal(@Nullable Boolean bool) {
            this.i = bool;
            return this;
        }

        @Override // com.mappls.sdk.services.api.autosuggest.MapplsAutoSuggest.Builder
        public MapplsAutoSuggest.Builder internalQuery(String str) {
            Objects.requireNonNull(str, "Null internalQuery");
            this.b = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.autosuggest.MapplsAutoSuggest.Builder
        public MapplsAutoSuggest.Builder internalZoom(@Nullable Double d) {
            this.d = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.autosuggest.MapplsAutoSuggest.Builder
        public MapplsAutoSuggest.Builder isPrimary(@Nullable String str) {
            this.k = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.autosuggest.MapplsAutoSuggest.Builder
        public MapplsAutoSuggest.Builder location(@Nullable String str) {
            this.c = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.autosuggest.MapplsAutoSuggest.Builder
        public MapplsAutoSuggest.Builder mapCentre(@Nullable String str) {
            this.j = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.autosuggest.MapplsAutoSuggest.Builder
        public MapplsAutoSuggest.Builder pod(@Nullable String str) {
            this.f = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.autosuggest.MapplsAutoSuggest.Builder
        public MapplsAutoSuggest.Builder responseLang(@Nullable String str) {
            this.m = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.autosuggest.MapplsAutoSuggest.Builder
        public MapplsAutoSuggest.Builder tokenizeAddress(@Nullable Boolean bool) {
            this.e = bool;
            return this;
        }
    }

    @Override // com.mappls.sdk.services.api.autosuggest.MapplsAutoSuggest, com.mappls.sdk.services.api.MapplsService
    @NonNull
    public String baseUrl() {
        return this.f13157a;
    }

    @Override // com.mappls.sdk.services.api.autosuggest.MapplsAutoSuggest
    @Nullable
    public Boolean bridge() {
        return this.h;
    }

    public boolean equals(Object obj) {
        String str;
        Double d;
        Boolean bool;
        String str2;
        String str3;
        Boolean bool2;
        Boolean bool3;
        String str4;
        String str5;
        Boolean bool4;
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsAutoSuggest) {
            MapplsAutoSuggest mapplsAutoSuggest = (MapplsAutoSuggest) obj;
            if (this.f13157a.equals(mapplsAutoSuggest.baseUrl()) && this.b.equals(mapplsAutoSuggest.internalQuery()) && ((str = this.c) != null ? str.equals(mapplsAutoSuggest.location()) : mapplsAutoSuggest.location() == null) && ((d = this.d) != null ? d.equals(mapplsAutoSuggest.internalZoom()) : mapplsAutoSuggest.internalZoom() == null) && ((bool = this.e) != null ? bool.equals(mapplsAutoSuggest.tokenizeAddress()) : mapplsAutoSuggest.tokenizeAddress() == null) && ((str2 = this.f) != null ? str2.equals(mapplsAutoSuggest.pod()) : mapplsAutoSuggest.pod() == null) && ((str3 = this.g) != null ? str3.equals(mapplsAutoSuggest.filter()) : mapplsAutoSuggest.filter() == null) && ((bool2 = this.h) != null ? bool2.equals(mapplsAutoSuggest.bridge()) : mapplsAutoSuggest.bridge() == null) && ((bool3 = this.i) != null ? bool3.equals(mapplsAutoSuggest.hyperLocal()) : mapplsAutoSuggest.hyperLocal() == null) && ((str4 = this.j) != null ? str4.equals(mapplsAutoSuggest.mapCentre()) : mapplsAutoSuggest.mapCentre() == null) && ((str5 = this.k) != null ? str5.equals(mapplsAutoSuggest.isPrimary()) : mapplsAutoSuggest.isPrimary() == null) && ((bool4 = this.l) != null ? bool4.equals(mapplsAutoSuggest.explain()) : mapplsAutoSuggest.explain() == null)) {
                String str6 = this.m;
                if (str6 == null) {
                    if (mapplsAutoSuggest.responseLang() == null) {
                        return true;
                    }
                } else if (str6.equals(mapplsAutoSuggest.responseLang())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override // com.mappls.sdk.services.api.autosuggest.MapplsAutoSuggest
    @Nullable
    public Boolean explain() {
        return this.l;
    }

    @Override // com.mappls.sdk.services.api.autosuggest.MapplsAutoSuggest
    @Nullable
    public String filter() {
        return this.g;
    }

    public int hashCode() {
        int hashCode = (((this.f13157a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003;
        String str = this.c;
        int hashCode2 = (hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003;
        Double d = this.d;
        int hashCode3 = (hashCode2 ^ (d == null ? 0 : d.hashCode())) * 1000003;
        Boolean bool = this.e;
        int hashCode4 = (hashCode3 ^ (bool == null ? 0 : bool.hashCode())) * 1000003;
        String str2 = this.f;
        int hashCode5 = (hashCode4 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.g;
        int hashCode6 = (hashCode5 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003;
        Boolean bool2 = this.h;
        int hashCode7 = (hashCode6 ^ (bool2 == null ? 0 : bool2.hashCode())) * 1000003;
        Boolean bool3 = this.i;
        int hashCode8 = (hashCode7 ^ (bool3 == null ? 0 : bool3.hashCode())) * 1000003;
        String str4 = this.j;
        int hashCode9 = (hashCode8 ^ (str4 == null ? 0 : str4.hashCode())) * 1000003;
        String str5 = this.k;
        int hashCode10 = (hashCode9 ^ (str5 == null ? 0 : str5.hashCode())) * 1000003;
        Boolean bool4 = this.l;
        int hashCode11 = (hashCode10 ^ (bool4 == null ? 0 : bool4.hashCode())) * 1000003;
        String str6 = this.m;
        return hashCode11 ^ (str6 != null ? str6.hashCode() : 0);
    }

    @Override // com.mappls.sdk.services.api.autosuggest.MapplsAutoSuggest
    @Nullable
    public Boolean hyperLocal() {
        return this.i;
    }

    @Override // com.mappls.sdk.services.api.autosuggest.MapplsAutoSuggest
    @NonNull
    public String internalQuery() {
        return this.b;
    }

    @Override // com.mappls.sdk.services.api.autosuggest.MapplsAutoSuggest
    @Nullable
    public Double internalZoom() {
        return this.d;
    }

    @Override // com.mappls.sdk.services.api.autosuggest.MapplsAutoSuggest
    @Nullable
    public String isPrimary() {
        return this.k;
    }

    @Override // com.mappls.sdk.services.api.autosuggest.MapplsAutoSuggest
    @Nullable
    public String location() {
        return this.c;
    }

    @Override // com.mappls.sdk.services.api.autosuggest.MapplsAutoSuggest
    @Nullable
    public String mapCentre() {
        return this.j;
    }

    @Override // com.mappls.sdk.services.api.autosuggest.MapplsAutoSuggest
    @Nullable
    public String pod() {
        return this.f;
    }

    @Override // com.mappls.sdk.services.api.autosuggest.MapplsAutoSuggest
    @Nullable
    public String responseLang() {
        return this.m;
    }

    public String toString() {
        return "MapplsAutoSuggest{baseUrl=" + this.f13157a + ", internalQuery=" + this.b + ", location=" + this.c + ", internalZoom=" + this.d + ", tokenizeAddress=" + this.e + ", pod=" + this.f + ", filter=" + this.g + ", bridge=" + this.h + ", hyperLocal=" + this.i + ", mapCentre=" + this.j + ", isPrimary=" + this.k + ", explain=" + this.l + ", responseLang=" + this.m + "}";
    }

    @Override // com.mappls.sdk.services.api.autosuggest.MapplsAutoSuggest
    @Nullable
    public Boolean tokenizeAddress() {
        return this.e;
    }

    public a(String str, String str2, @Nullable String str3, @Nullable Double d, @Nullable Boolean bool, @Nullable String str4, @Nullable String str5, @Nullable Boolean bool2, @Nullable Boolean bool3, @Nullable String str6, @Nullable String str7, @Nullable Boolean bool4, @Nullable String str8) {
        this.f13157a = str;
        this.b = str2;
        this.c = str3;
        this.d = d;
        this.e = bool;
        this.f = str4;
        this.g = str5;
        this.h = bool2;
        this.i = bool3;
        this.j = str6;
        this.k = str7;
        this.l = bool4;
        this.m = str8;
    }
}

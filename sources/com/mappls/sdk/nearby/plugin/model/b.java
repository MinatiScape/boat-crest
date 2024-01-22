package com.mappls.sdk.nearby.plugin.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.nearby.plugin.model.NearbyOption;
import java.util.Objects;
/* loaded from: classes10.dex */
public abstract class b extends NearbyOption {
    public final Integer h;
    public final String i;
    public final String j;
    public final String k;
    public final String l;
    public final String m;
    public final Boolean n;
    public final Boolean o;
    public final String p;

    /* loaded from: classes10.dex */
    public static class a extends NearbyOption.Builder {

        /* renamed from: a  reason: collision with root package name */
        public Integer f13065a;
        public String b;
        public String c;
        public String d;
        public String e;
        public String f;
        public Boolean g;
        public Boolean h;
        public String i;

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyOption.Builder
        public final NearbyOption.Builder bounds(String str) {
            this.d = str;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyOption.Builder
        public final NearbyOption build() {
            String a2 = this.f13065a == null ? com.mappls.sdk.nearby.plugin.model.a.a("", " radius") : "";
            if (a2.isEmpty()) {
                return new d(this.f13065a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i);
            }
            throw new IllegalStateException(com.mappls.sdk.nearby.plugin.model.a.a("Missing required properties:", a2));
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyOption.Builder
        public final NearbyOption.Builder explain(Boolean bool) {
            this.g = bool;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyOption.Builder
        public final NearbyOption.Builder filter(String str) {
            this.f = str;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyOption.Builder
        public final NearbyOption.Builder pod(String str) {
            this.e = str;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyOption.Builder
        public final NearbyOption.Builder radius(Integer num) {
            Objects.requireNonNull(num, "Null radius");
            this.f13065a = num;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyOption.Builder
        public final NearbyOption.Builder richData(Boolean bool) {
            this.h = bool;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyOption.Builder
        public final NearbyOption.Builder searchBy(String str) {
            this.c = str;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyOption.Builder
        public final NearbyOption.Builder sortBy(String str) {
            this.b = str;
            return this;
        }

        @Override // com.mappls.sdk.nearby.plugin.model.NearbyOption.Builder
        public final NearbyOption.Builder userName(String str) {
            this.i = str;
            return this;
        }
    }

    public b(Integer num, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable Boolean bool, @Nullable Boolean bool2, @Nullable String str6) {
        Objects.requireNonNull(num, "Null radius");
        this.h = num;
        this.i = str;
        this.j = str2;
        this.k = str3;
        this.l = str4;
        this.m = str5;
        this.n = bool;
        this.o = bool2;
        this.p = str6;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyOption
    @Nullable
    public final String bounds() {
        return this.k;
    }

    public final boolean equals(Object obj) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        Boolean bool;
        Boolean bool2;
        if (obj == this) {
            return true;
        }
        if (obj instanceof NearbyOption) {
            NearbyOption nearbyOption = (NearbyOption) obj;
            if (this.h.equals(nearbyOption.radius()) && ((str = this.i) != null ? str.equals(nearbyOption.sortBy()) : nearbyOption.sortBy() == null) && ((str2 = this.j) != null ? str2.equals(nearbyOption.searchBy()) : nearbyOption.searchBy() == null) && ((str3 = this.k) != null ? str3.equals(nearbyOption.bounds()) : nearbyOption.bounds() == null) && ((str4 = this.l) != null ? str4.equals(nearbyOption.pod()) : nearbyOption.pod() == null) && ((str5 = this.m) != null ? str5.equals(nearbyOption.filter()) : nearbyOption.filter() == null) && ((bool = this.n) != null ? bool.equals(nearbyOption.explain()) : nearbyOption.explain() == null) && ((bool2 = this.o) != null ? bool2.equals(nearbyOption.richData()) : nearbyOption.richData() == null)) {
                String str6 = this.p;
                String userName = nearbyOption.userName();
                if (str6 == null) {
                    if (userName == null) {
                        return true;
                    }
                } else if (str6.equals(userName)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyOption
    @Nullable
    public final Boolean explain() {
        return this.n;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyOption
    @Nullable
    public final String filter() {
        return this.m;
    }

    public final int hashCode() {
        int hashCode = (this.h.hashCode() ^ 1000003) * 1000003;
        String str = this.i;
        int hashCode2 = (hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003;
        String str2 = this.j;
        int hashCode3 = (hashCode2 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.k;
        int hashCode4 = (hashCode3 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003;
        String str4 = this.l;
        int hashCode5 = (hashCode4 ^ (str4 == null ? 0 : str4.hashCode())) * 1000003;
        String str5 = this.m;
        int hashCode6 = (hashCode5 ^ (str5 == null ? 0 : str5.hashCode())) * 1000003;
        Boolean bool = this.n;
        int hashCode7 = (hashCode6 ^ (bool == null ? 0 : bool.hashCode())) * 1000003;
        Boolean bool2 = this.o;
        int hashCode8 = (hashCode7 ^ (bool2 == null ? 0 : bool2.hashCode())) * 1000003;
        String str6 = this.p;
        return hashCode8 ^ (str6 != null ? str6.hashCode() : 0);
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyOption
    @Nullable
    public final String pod() {
        return this.l;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyOption
    @NonNull
    public final Integer radius() {
        return this.h;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyOption
    @Nullable
    public final Boolean richData() {
        return this.o;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyOption
    @Nullable
    public final String searchBy() {
        return this.j;
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyOption
    @Nullable
    public final String sortBy() {
        return this.i;
    }

    public final String toString() {
        return "NearbyOption{radius=" + this.h + ", sortBy=" + this.i + ", searchBy=" + this.j + ", bounds=" + this.k + ", pod=" + this.l + ", filter=" + this.m + ", explain=" + this.n + ", richData=" + this.o + ", userName=" + this.p + "}";
    }

    @Override // com.mappls.sdk.nearby.plugin.model.NearbyOption
    @Nullable
    public final String userName() {
        return this.p;
    }
}

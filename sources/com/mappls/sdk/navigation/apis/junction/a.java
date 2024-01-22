package com.mappls.sdk.navigation.apis.junction;

import androidx.annotation.NonNull;
import com.mappls.sdk.navigation.apis.junction.MapplsGetJunction;
import com.mappls.sdk.navigation.h;
import com.mappls.sdk.navigation.o;
import java.util.Objects;
/* loaded from: classes11.dex */
public final class a extends MapplsGetJunction {

    /* renamed from: a  reason: collision with root package name */
    public final String f12879a;
    public final String b;
    public final String c;
    public final Integer d;
    public final String e;

    /* renamed from: com.mappls.sdk.navigation.apis.junction.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static final class C0638a extends MapplsGetJunction.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f12880a;
        public String b;
        public String c;
        public Integer d;
        public String e;

        @Override // com.mappls.sdk.navigation.apis.junction.MapplsGetJunction.Builder
        public final MapplsGetJunction autoBuild() {
            String a2 = this.f12880a == null ? o.a("", " baseUrl") : "";
            if (this.b == null) {
                a2 = o.a(a2, " routeId");
            }
            if (this.c == null) {
                a2 = o.a(a2, " junctionViewMode");
            }
            if (this.d == null) {
                a2 = o.a(a2, " routeIdx");
            }
            if (this.e == null) {
                a2 = o.a(a2, " size");
            }
            if (a2.isEmpty()) {
                return new a(this.f12880a, this.b, this.c, this.d, this.e, 0);
            }
            throw new IllegalStateException(o.a("Missing required properties:", a2));
        }

        @Override // com.mappls.sdk.navigation.apis.junction.MapplsGetJunction.Builder
        public final MapplsGetJunction.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f12880a = str;
            return this;
        }

        @Override // com.mappls.sdk.navigation.apis.junction.MapplsGetJunction.Builder
        public final MapplsGetJunction.Builder junctionViewMode(String str) {
            Objects.requireNonNull(str, "Null junctionViewMode");
            this.c = str;
            return this;
        }

        @Override // com.mappls.sdk.navigation.apis.junction.MapplsGetJunction.Builder
        public final MapplsGetJunction.Builder routeId(String str) {
            Objects.requireNonNull(str, "Null routeId");
            this.b = str;
            return this;
        }

        @Override // com.mappls.sdk.navigation.apis.junction.MapplsGetJunction.Builder
        public final MapplsGetJunction.Builder routeIdx(Integer num) {
            Objects.requireNonNull(num, "Null routeIdx");
            this.d = num;
            return this;
        }

        @Override // com.mappls.sdk.navigation.apis.junction.MapplsGetJunction.Builder
        public final MapplsGetJunction.Builder size(String str) {
            Objects.requireNonNull(str, "Null size");
            this.e = str;
            return this;
        }
    }

    public a(String str, String str2, String str3, Integer num, String str4) {
        this.f12879a = str;
        this.b = str2;
        this.c = str3;
        this.d = num;
        this.e = str4;
    }

    public /* synthetic */ a(String str, String str2, String str3, Integer num, String str4, int i) {
        this(str, str2, str3, num, str4);
    }

    @Override // com.mappls.sdk.navigation.apis.junction.MapplsGetJunction
    @NonNull
    public final String a() {
        return this.c;
    }

    @Override // com.mappls.sdk.navigation.apis.junction.MapplsGetJunction, com.mappls.sdk.services.api.MapplsService
    public final String baseUrl() {
        return this.f12879a;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsGetJunction) {
            MapplsGetJunction mapplsGetJunction = (MapplsGetJunction) obj;
            return this.f12879a.equals(mapplsGetJunction.baseUrl()) && this.b.equals(mapplsGetJunction.routeId()) && this.c.equals(mapplsGetJunction.a()) && this.d.equals(mapplsGetJunction.routeIdx()) && this.e.equals(mapplsGetJunction.size());
        }
        return false;
    }

    public final int hashCode() {
        return ((((((((this.f12879a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d.hashCode()) * 1000003) ^ this.e.hashCode();
    }

    @Override // com.mappls.sdk.navigation.apis.junction.MapplsGetJunction
    @NonNull
    public final String routeId() {
        return this.b;
    }

    @Override // com.mappls.sdk.navigation.apis.junction.MapplsGetJunction
    @NonNull
    public final Integer routeIdx() {
        return this.d;
    }

    @Override // com.mappls.sdk.navigation.apis.junction.MapplsGetJunction
    @NonNull
    public final String size() {
        return this.e;
    }

    public final String toString() {
        StringBuilder a2 = h.a("MapplsGetJunction{baseUrl=");
        a2.append(this.f12879a);
        a2.append(", routeId=");
        a2.append(this.b);
        a2.append(", junctionViewMode=");
        a2.append(this.c);
        a2.append(", routeIdx=");
        a2.append(this.d);
        a2.append(", size=");
        a2.append(this.e);
        a2.append("}");
        return a2.toString();
    }
}

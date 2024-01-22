package com.mappls.sdk.navigation.apis.junction;

import androidx.annotation.NonNull;
import com.mappls.sdk.navigation.apis.junction.MapplsGetJunctionName;
import com.mappls.sdk.navigation.h;
import com.mappls.sdk.navigation.o;
import java.util.List;
import java.util.Objects;
/* loaded from: classes11.dex */
public final class b extends MapplsGetJunctionName {

    /* renamed from: a  reason: collision with root package name */
    public final String f12881a;
    public final List<String> b;
    public final String c;
    public final String d;

    /* loaded from: classes11.dex */
    public static final class a extends MapplsGetJunctionName.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f12882a;
        public List<String> b;
        public String c;
        public String d;

        @Override // com.mappls.sdk.navigation.apis.junction.MapplsGetJunctionName.Builder
        public final MapplsGetJunctionName autoBuild() {
            String a2 = this.f12882a == null ? o.a("", " baseUrl") : "";
            if (this.b == null) {
                a2 = o.a(a2, " imageName");
            }
            if (this.c == null) {
                a2 = o.a(a2, " junctionViewMode");
            }
            if (this.d == null) {
                a2 = o.a(a2, " size");
            }
            if (a2.isEmpty()) {
                return new b(this.f12882a, this.b, this.c, this.d, 0);
            }
            throw new IllegalStateException(o.a("Missing required properties:", a2));
        }

        @Override // com.mappls.sdk.navigation.apis.junction.MapplsGetJunctionName.Builder
        public final MapplsGetJunctionName.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f12882a = str;
            return this;
        }

        @Override // com.mappls.sdk.navigation.apis.junction.MapplsGetJunctionName.Builder
        public final MapplsGetJunctionName.Builder imageName(List<String> list) {
            Objects.requireNonNull(list, "Null imageName");
            this.b = list;
            return this;
        }

        @Override // com.mappls.sdk.navigation.apis.junction.MapplsGetJunctionName.Builder
        public final MapplsGetJunctionName.Builder junctionViewMode(String str) {
            Objects.requireNonNull(str, "Null junctionViewMode");
            this.c = str;
            return this;
        }

        @Override // com.mappls.sdk.navigation.apis.junction.MapplsGetJunctionName.Builder
        public final MapplsGetJunctionName.Builder size(String str) {
            Objects.requireNonNull(str, "Null size");
            this.d = str;
            return this;
        }
    }

    public b(String str, List<String> list, String str2, String str3) {
        this.f12881a = str;
        this.b = list;
        this.c = str2;
        this.d = str3;
    }

    public /* synthetic */ b(String str, List list, String str2, String str3, int i) {
        this(str, list, str2, str3);
    }

    @Override // com.mappls.sdk.navigation.apis.junction.MapplsGetJunctionName
    @NonNull
    public final List<String> a() {
        return this.b;
    }

    @Override // com.mappls.sdk.navigation.apis.junction.MapplsGetJunctionName
    @NonNull
    public final String b() {
        return this.c;
    }

    @Override // com.mappls.sdk.navigation.apis.junction.MapplsGetJunctionName, com.mappls.sdk.services.api.MapplsService
    public final String baseUrl() {
        return this.f12881a;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsGetJunctionName) {
            MapplsGetJunctionName mapplsGetJunctionName = (MapplsGetJunctionName) obj;
            return this.f12881a.equals(mapplsGetJunctionName.baseUrl()) && this.b.equals(mapplsGetJunctionName.a()) && this.c.equals(mapplsGetJunctionName.b()) && this.d.equals(mapplsGetJunctionName.size());
        }
        return false;
    }

    public final int hashCode() {
        return ((((((this.f12881a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d.hashCode();
    }

    @Override // com.mappls.sdk.navigation.apis.junction.MapplsGetJunctionName
    @NonNull
    public final String size() {
        return this.d;
    }

    public final String toString() {
        StringBuilder a2 = h.a("MapplsGetJunctionName{baseUrl=");
        a2.append(this.f12881a);
        a2.append(", imageName=");
        a2.append(this.b);
        a2.append(", junctionViewMode=");
        a2.append(this.c);
        a2.append(", size=");
        a2.append(this.d);
        a2.append("}");
        return a2.toString();
    }
}

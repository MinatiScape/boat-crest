package com.mappls.sdk.services.api.hateaosnearby;

import androidx.annotation.NonNull;
import com.mappls.sdk.services.api.hateaosnearby.MapplsHateosNearby;
import java.util.Objects;
/* loaded from: classes6.dex */
public final class a extends MapplsHateosNearby {

    /* renamed from: a  reason: collision with root package name */
    public final String f13246a;
    public final String b;

    /* loaded from: classes6.dex */
    public static final class b extends MapplsHateosNearby.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13247a;
        public String b;

        @Override // com.mappls.sdk.services.api.hateaosnearby.MapplsHateosNearby.Builder
        public MapplsHateosNearby autoBuild() {
            String str = "";
            if (this.f13247a == null) {
                str = " baseUrl";
            }
            if (this.b == null) {
                str = str + " hyperlink";
            }
            if (str.isEmpty()) {
                return new a(this.f13247a, this.b);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.hateaosnearby.MapplsHateosNearby.Builder
        public MapplsHateosNearby.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f13247a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.hateaosnearby.MapplsHateosNearby.Builder
        public MapplsHateosNearby.Builder hyperlink(String str) {
            Objects.requireNonNull(str, "Null hyperlink");
            this.b = str;
            return this;
        }
    }

    @Override // com.mappls.sdk.services.api.hateaosnearby.MapplsHateosNearby, com.mappls.sdk.services.api.MapplsService
    @NonNull
    public String baseUrl() {
        return this.f13246a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsHateosNearby) {
            MapplsHateosNearby mapplsHateosNearby = (MapplsHateosNearby) obj;
            return this.f13246a.equals(mapplsHateosNearby.baseUrl()) && this.b.equals(mapplsHateosNearby.hyperlink());
        }
        return false;
    }

    public int hashCode() {
        return ((this.f13246a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode();
    }

    @Override // com.mappls.sdk.services.api.hateaosnearby.MapplsHateosNearby
    public String hyperlink() {
        return this.b;
    }

    public String toString() {
        return "MapplsHateosNearby{baseUrl=" + this.f13246a + ", hyperlink=" + this.b + "}";
    }

    public a(String str, String str2) {
        this.f13246a = str;
        this.b = str2;
    }
}

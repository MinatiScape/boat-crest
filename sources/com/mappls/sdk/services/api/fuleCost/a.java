package com.mappls.sdk.services.api.fuleCost;

import androidx.annotation.NonNull;
import com.mappls.sdk.services.api.fuleCost.MapplsFuelCost;
import java.util.Objects;
/* loaded from: classes6.dex */
public final class a extends MapplsFuelCost {

    /* renamed from: a  reason: collision with root package name */
    public final String f13233a;
    public final Double b;
    public final Double c;

    /* loaded from: classes6.dex */
    public static final class b extends MapplsFuelCost.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13234a;
        public Double b;
        public Double c;

        @Override // com.mappls.sdk.services.api.fuleCost.MapplsFuelCost.Builder
        public MapplsFuelCost autoBuild() {
            String str = "";
            if (this.f13234a == null) {
                str = " baseUrl";
            }
            if (this.b == null) {
                str = str + " latitude";
            }
            if (this.c == null) {
                str = str + " longitude";
            }
            if (str.isEmpty()) {
                return new a(this.f13234a, this.b, this.c);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.fuleCost.MapplsFuelCost.Builder
        public MapplsFuelCost.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f13234a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.fuleCost.MapplsFuelCost.Builder
        public MapplsFuelCost.Builder latitude(Double d) {
            Objects.requireNonNull(d, "Null latitude");
            this.b = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.fuleCost.MapplsFuelCost.Builder
        public MapplsFuelCost.Builder longitude(Double d) {
            Objects.requireNonNull(d, "Null longitude");
            this.c = d;
            return this;
        }
    }

    @Override // com.mappls.sdk.services.api.fuleCost.MapplsFuelCost, com.mappls.sdk.services.api.MapplsService
    @NonNull
    public String baseUrl() {
        return this.f13233a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsFuelCost) {
            MapplsFuelCost mapplsFuelCost = (MapplsFuelCost) obj;
            return this.f13233a.equals(mapplsFuelCost.baseUrl()) && this.b.equals(mapplsFuelCost.latitude()) && this.c.equals(mapplsFuelCost.longitude());
        }
        return false;
    }

    public int hashCode() {
        return ((((this.f13233a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode();
    }

    @Override // com.mappls.sdk.services.api.fuleCost.MapplsFuelCost
    @NonNull
    public Double latitude() {
        return this.b;
    }

    @Override // com.mappls.sdk.services.api.fuleCost.MapplsFuelCost
    @NonNull
    public Double longitude() {
        return this.c;
    }

    public String toString() {
        return "MapplsFuelCost{baseUrl=" + this.f13233a + ", latitude=" + this.b + ", longitude=" + this.c + "}";
    }

    public a(String str, Double d, Double d2) {
        this.f13233a = str;
        this.b = d;
        this.c = d2;
    }
}

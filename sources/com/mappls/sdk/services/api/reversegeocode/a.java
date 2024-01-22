package com.mappls.sdk.services.api.reversegeocode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.services.api.reversegeocode.MapplsReverseGeoCode;
import java.util.Objects;
/* loaded from: classes7.dex */
public final class a extends MapplsReverseGeoCode {

    /* renamed from: a  reason: collision with root package name */
    public final String f13264a;
    public final double b;
    public final double c;
    public final String d;

    /* loaded from: classes7.dex */
    public static final class b extends MapplsReverseGeoCode.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13265a;
        public Double b;
        public Double c;
        public String d;

        @Override // com.mappls.sdk.services.api.reversegeocode.MapplsReverseGeoCode.Builder
        public MapplsReverseGeoCode autoBuild() {
            String str = "";
            if (this.f13265a == null) {
                str = " baseUrl";
            }
            if (this.b == null) {
                str = str + " latitude";
            }
            if (this.c == null) {
                str = str + " longitude";
            }
            if (str.isEmpty()) {
                return new a(this.f13265a, this.b.doubleValue(), this.c.doubleValue(), this.d);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.reversegeocode.MapplsReverseGeoCode.Builder
        public MapplsReverseGeoCode.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f13265a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.reversegeocode.MapplsReverseGeoCode.Builder
        public MapplsReverseGeoCode.Builder lang(@Nullable String str) {
            this.d = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.reversegeocode.MapplsReverseGeoCode.Builder
        public MapplsReverseGeoCode.Builder latitude(double d) {
            this.b = Double.valueOf(d);
            return this;
        }

        @Override // com.mappls.sdk.services.api.reversegeocode.MapplsReverseGeoCode.Builder
        public MapplsReverseGeoCode.Builder longitude(double d) {
            this.c = Double.valueOf(d);
            return this;
        }
    }

    @Override // com.mappls.sdk.services.api.reversegeocode.MapplsReverseGeoCode, com.mappls.sdk.services.api.MapplsService
    @NonNull
    public String baseUrl() {
        return this.f13264a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsReverseGeoCode) {
            MapplsReverseGeoCode mapplsReverseGeoCode = (MapplsReverseGeoCode) obj;
            if (this.f13264a.equals(mapplsReverseGeoCode.baseUrl()) && Double.doubleToLongBits(this.b) == Double.doubleToLongBits(mapplsReverseGeoCode.latitude()) && Double.doubleToLongBits(this.c) == Double.doubleToLongBits(mapplsReverseGeoCode.longitude())) {
                String str = this.d;
                if (str == null) {
                    if (mapplsReverseGeoCode.lang() == null) {
                        return true;
                    }
                } else if (str.equals(mapplsReverseGeoCode.lang())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((((this.f13264a.hashCode() ^ 1000003) * 1000003) ^ ((int) ((Double.doubleToLongBits(this.b) >>> 32) ^ Double.doubleToLongBits(this.b)))) * 1000003) ^ ((int) ((Double.doubleToLongBits(this.c) >>> 32) ^ Double.doubleToLongBits(this.c)))) * 1000003;
        String str = this.d;
        return hashCode ^ (str == null ? 0 : str.hashCode());
    }

    @Override // com.mappls.sdk.services.api.reversegeocode.MapplsReverseGeoCode
    @Nullable
    public String lang() {
        return this.d;
    }

    @Override // com.mappls.sdk.services.api.reversegeocode.MapplsReverseGeoCode
    @NonNull
    public double latitude() {
        return this.b;
    }

    @Override // com.mappls.sdk.services.api.reversegeocode.MapplsReverseGeoCode
    @NonNull
    public double longitude() {
        return this.c;
    }

    public String toString() {
        return "MapplsReverseGeoCode{baseUrl=" + this.f13264a + ", latitude=" + this.b + ", longitude=" + this.c + ", lang=" + this.d + "}";
    }

    public a(String str, double d, double d2, @Nullable String str2) {
        this.f13264a = str;
        this.b = d;
        this.c = d2;
        this.d = str2;
    }
}

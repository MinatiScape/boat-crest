package com.mappls.sdk.services.api.predictive.distance;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.services.api.predictive.distance.MapplsPredictiveDistance;
import java.util.List;
import java.util.Objects;
/* loaded from: classes7.dex */
public final class a extends MapplsPredictiveDistance {

    /* renamed from: a  reason: collision with root package name */
    public final String f13259a;
    public final List<String> b;
    public final List<String> c;
    public final String d;
    public final String e;
    public final String f;

    /* loaded from: classes7.dex */
    public static final class b extends MapplsPredictiveDistance.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13260a;
        public List<String> b;
        public List<String> c;
        public String d;
        public String e;
        public String f;

        @Override // com.mappls.sdk.services.api.predictive.distance.MapplsPredictiveDistance.Builder
        public MapplsPredictiveDistance autoBuild() {
            String str = "";
            if (this.f13260a == null) {
                str = " baseUrl";
            }
            if (this.b == null) {
                str = str + " internalSources";
            }
            if (this.c == null) {
                str = str + " internalDestination";
            }
            if (this.d == null) {
                str = str + " profile";
            }
            if (str.isEmpty()) {
                return new a(this.f13260a, this.b, this.c, this.d, this.e, this.f);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.predictive.distance.MapplsPredictiveDistance.Builder
        public MapplsPredictiveDistance.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f13260a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.predictive.distance.MapplsPredictiveDistance.Builder
        public MapplsPredictiveDistance.Builder internalDateTime(@Nullable String str) {
            this.f = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.predictive.distance.MapplsPredictiveDistance.Builder
        public MapplsPredictiveDistance.Builder internalDestination(List<String> list) {
            Objects.requireNonNull(list, "Null internalDestination");
            this.c = list;
            return this;
        }

        @Override // com.mappls.sdk.services.api.predictive.distance.MapplsPredictiveDistance.Builder
        public MapplsPredictiveDistance.Builder internalSources(List<String> list) {
            Objects.requireNonNull(list, "Null internalSources");
            this.b = list;
            return this;
        }

        @Override // com.mappls.sdk.services.api.predictive.distance.MapplsPredictiveDistance.Builder
        public MapplsPredictiveDistance.Builder internalSpeedType(@Nullable String str) {
            this.e = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.predictive.distance.MapplsPredictiveDistance.Builder
        public MapplsPredictiveDistance.Builder profile(String str) {
            Objects.requireNonNull(str, "Null profile");
            this.d = str;
            return this;
        }
    }

    @Override // com.mappls.sdk.services.api.predictive.distance.MapplsPredictiveDistance, com.mappls.sdk.services.api.MapplsService
    @NonNull
    public String baseUrl() {
        return this.f13259a;
    }

    public boolean equals(Object obj) {
        String str;
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsPredictiveDistance) {
            MapplsPredictiveDistance mapplsPredictiveDistance = (MapplsPredictiveDistance) obj;
            if (this.f13259a.equals(mapplsPredictiveDistance.baseUrl()) && this.b.equals(mapplsPredictiveDistance.internalSources()) && this.c.equals(mapplsPredictiveDistance.internalDestination()) && this.d.equals(mapplsPredictiveDistance.profile()) && ((str = this.e) != null ? str.equals(mapplsPredictiveDistance.internalSpeedType()) : mapplsPredictiveDistance.internalSpeedType() == null)) {
                String str2 = this.f;
                if (str2 == null) {
                    if (mapplsPredictiveDistance.internalDateTime() == null) {
                        return true;
                    }
                } else if (str2.equals(mapplsPredictiveDistance.internalDateTime())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((((((this.f13259a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d.hashCode()) * 1000003;
        String str = this.e;
        int hashCode2 = (hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003;
        String str2 = this.f;
        return hashCode2 ^ (str2 != null ? str2.hashCode() : 0);
    }

    @Override // com.mappls.sdk.services.api.predictive.distance.MapplsPredictiveDistance
    @Nullable
    public String internalDateTime() {
        return this.f;
    }

    @Override // com.mappls.sdk.services.api.predictive.distance.MapplsPredictiveDistance
    @NonNull
    public List<String> internalDestination() {
        return this.c;
    }

    @Override // com.mappls.sdk.services.api.predictive.distance.MapplsPredictiveDistance
    @NonNull
    public List<String> internalSources() {
        return this.b;
    }

    @Override // com.mappls.sdk.services.api.predictive.distance.MapplsPredictiveDistance
    @Nullable
    public String internalSpeedType() {
        return this.e;
    }

    @Override // com.mappls.sdk.services.api.predictive.distance.MapplsPredictiveDistance
    @NonNull
    public String profile() {
        return this.d;
    }

    public String toString() {
        return "MapplsPredictiveDistance{baseUrl=" + this.f13259a + ", internalSources=" + this.b + ", internalDestination=" + this.c + ", profile=" + this.d + ", internalSpeedType=" + this.e + ", internalDateTime=" + this.f + "}";
    }

    public a(String str, List<String> list, List<String> list2, String str2, @Nullable String str3, @Nullable String str4) {
        this.f13259a = str;
        this.b = list;
        this.c = list2;
        this.d = str2;
        this.e = str3;
        this.f = str4;
    }
}

package com.mappls.sdk.services.api.feedback;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.services.api.feedback.MapplsFeedback;
import java.util.Objects;
/* loaded from: classes6.dex */
public final class a extends MapplsFeedback {

    /* renamed from: a  reason: collision with root package name */
    public final String f13230a;
    public final String b;
    public final String c;
    public final String d;
    public final Integer e;
    public final String f;
    public final Double g;
    public final Double h;
    public final String i;

    /* loaded from: classes6.dex */
    public static final class b extends MapplsFeedback.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13231a;
        public String b;
        public String c;
        public String d;
        public Integer e;
        public String f;
        public Double g;
        public Double h;
        public String i;

        @Override // com.mappls.sdk.services.api.feedback.MapplsFeedback.Builder
        public MapplsFeedback.Builder appVersion(String str) {
            Objects.requireNonNull(str, "Null appVersion");
            this.i = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.feedback.MapplsFeedback.Builder
        public MapplsFeedback autoBuild() {
            String str = "";
            if (this.f13231a == null) {
                str = " baseUrl";
            }
            if (this.b == null) {
                str = str + " typedKeyword";
            }
            if (this.d == null) {
                str = str + " locationName";
            }
            if (this.e == null) {
                str = str + " index";
            }
            if (this.f == null) {
                str = str + " userName";
            }
            if (this.i == null) {
                str = str + " appVersion";
            }
            if (str.isEmpty()) {
                return new a(this.f13231a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.feedback.MapplsFeedback.Builder
        public MapplsFeedback.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f13231a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.feedback.MapplsFeedback.Builder
        public MapplsFeedback.Builder index(Integer num) {
            Objects.requireNonNull(num, "Null index");
            this.e = num;
            return this;
        }

        @Override // com.mappls.sdk.services.api.feedback.MapplsFeedback.Builder
        public MapplsFeedback.Builder latitude(@Nullable Double d) {
            this.g = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.feedback.MapplsFeedback.Builder
        public MapplsFeedback.Builder locationName(String str) {
            Objects.requireNonNull(str, "Null locationName");
            this.d = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.feedback.MapplsFeedback.Builder
        public MapplsFeedback.Builder longitude(@Nullable Double d) {
            this.h = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.feedback.MapplsFeedback.Builder
        public MapplsFeedback.Builder mapplsPin(@Nullable String str) {
            this.c = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.feedback.MapplsFeedback.Builder
        public MapplsFeedback.Builder typedKeyword(String str) {
            Objects.requireNonNull(str, "Null typedKeyword");
            this.b = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.feedback.MapplsFeedback.Builder
        public MapplsFeedback.Builder userName(String str) {
            Objects.requireNonNull(str, "Null userName");
            this.f = str;
            return this;
        }
    }

    @Override // com.mappls.sdk.services.api.feedback.MapplsFeedback
    @NonNull
    public String appVersion() {
        return this.i;
    }

    @Override // com.mappls.sdk.services.api.feedback.MapplsFeedback, com.mappls.sdk.services.api.MapplsService
    @NonNull
    public String baseUrl() {
        return this.f13230a;
    }

    public boolean equals(Object obj) {
        String str;
        Double d;
        Double d2;
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsFeedback) {
            MapplsFeedback mapplsFeedback = (MapplsFeedback) obj;
            return this.f13230a.equals(mapplsFeedback.baseUrl()) && this.b.equals(mapplsFeedback.typedKeyword()) && ((str = this.c) != null ? str.equals(mapplsFeedback.mapplsPin()) : mapplsFeedback.mapplsPin() == null) && this.d.equals(mapplsFeedback.locationName()) && this.e.equals(mapplsFeedback.index()) && this.f.equals(mapplsFeedback.userName()) && ((d = this.g) != null ? d.equals(mapplsFeedback.latitude()) : mapplsFeedback.latitude() == null) && ((d2 = this.h) != null ? d2.equals(mapplsFeedback.longitude()) : mapplsFeedback.longitude() == null) && this.i.equals(mapplsFeedback.appVersion());
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((this.f13230a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003;
        String str = this.c;
        int hashCode2 = (((((((hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003) ^ this.d.hashCode()) * 1000003) ^ this.e.hashCode()) * 1000003) ^ this.f.hashCode()) * 1000003;
        Double d = this.g;
        int hashCode3 = (hashCode2 ^ (d == null ? 0 : d.hashCode())) * 1000003;
        Double d2 = this.h;
        return ((hashCode3 ^ (d2 != null ? d2.hashCode() : 0)) * 1000003) ^ this.i.hashCode();
    }

    @Override // com.mappls.sdk.services.api.feedback.MapplsFeedback
    @NonNull
    public Integer index() {
        return this.e;
    }

    @Override // com.mappls.sdk.services.api.feedback.MapplsFeedback
    @Nullable
    public Double latitude() {
        return this.g;
    }

    @Override // com.mappls.sdk.services.api.feedback.MapplsFeedback
    @NonNull
    public String locationName() {
        return this.d;
    }

    @Override // com.mappls.sdk.services.api.feedback.MapplsFeedback
    @Nullable
    public Double longitude() {
        return this.h;
    }

    @Override // com.mappls.sdk.services.api.feedback.MapplsFeedback
    @Nullable
    public String mapplsPin() {
        return this.c;
    }

    public String toString() {
        return "MapplsFeedback{baseUrl=" + this.f13230a + ", typedKeyword=" + this.b + ", mapplsPin=" + this.c + ", locationName=" + this.d + ", index=" + this.e + ", userName=" + this.f + ", latitude=" + this.g + ", longitude=" + this.h + ", appVersion=" + this.i + "}";
    }

    @Override // com.mappls.sdk.services.api.feedback.MapplsFeedback
    @NonNull
    public String typedKeyword() {
        return this.b;
    }

    @Override // com.mappls.sdk.services.api.feedback.MapplsFeedback
    @NonNull
    public String userName() {
        return this.f;
    }

    public a(String str, String str2, @Nullable String str3, String str4, Integer num, String str5, @Nullable Double d, @Nullable Double d2, String str6) {
        this.f13230a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = num;
        this.f = str5;
        this.g = d;
        this.h = d2;
        this.i = str6;
    }
}

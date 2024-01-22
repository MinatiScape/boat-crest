package com.mappls.sdk.services.api.generateotp;

import androidx.annotation.NonNull;
import com.mappls.sdk.services.api.generateotp.MapplsGenerateOTP;
import java.util.Objects;
/* loaded from: classes6.dex */
public final class a extends MapplsGenerateOTP {

    /* renamed from: a  reason: collision with root package name */
    public final String f13236a;
    public final String b;

    /* loaded from: classes6.dex */
    public static final class b extends MapplsGenerateOTP.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13237a;
        public String b;

        @Override // com.mappls.sdk.services.api.generateotp.MapplsGenerateOTP.Builder
        public MapplsGenerateOTP autoBuild() {
            String str = "";
            if (this.f13237a == null) {
                str = " baseUrl";
            }
            if (this.b == null) {
                str = str + " userHandle";
            }
            if (str.isEmpty()) {
                return new a(this.f13237a, this.b);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.generateotp.MapplsGenerateOTP.Builder
        public MapplsGenerateOTP.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f13237a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.generateotp.MapplsGenerateOTP.Builder
        public MapplsGenerateOTP.Builder userHandle(String str) {
            Objects.requireNonNull(str, "Null userHandle");
            this.b = str;
            return this;
        }
    }

    @Override // com.mappls.sdk.services.api.generateotp.MapplsGenerateOTP, com.mappls.sdk.services.api.MapplsService
    @NonNull
    public String baseUrl() {
        return this.f13236a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsGenerateOTP) {
            MapplsGenerateOTP mapplsGenerateOTP = (MapplsGenerateOTP) obj;
            return this.f13236a.equals(mapplsGenerateOTP.baseUrl()) && this.b.equals(mapplsGenerateOTP.userHandle());
        }
        return false;
    }

    public int hashCode() {
        return ((this.f13236a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode();
    }

    public String toString() {
        return "MapplsGenerateOTP{baseUrl=" + this.f13236a + ", userHandle=" + this.b + "}";
    }

    @Override // com.mappls.sdk.services.api.generateotp.MapplsGenerateOTP
    @NonNull
    public String userHandle() {
        return this.b;
    }

    public a(String str, String str2) {
        this.f13236a = str;
        this.b = str2;
    }
}

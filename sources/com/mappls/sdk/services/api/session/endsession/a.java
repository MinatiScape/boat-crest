package com.mappls.sdk.services.api.session.endsession;

import androidx.annotation.NonNull;
import com.mappls.sdk.services.api.session.endsession.MapplsEndSession;
import java.util.Objects;
/* loaded from: classes8.dex */
public final class a extends MapplsEndSession {

    /* renamed from: a  reason: collision with root package name */
    public final String f13276a;
    public final String b;
    public final String c;

    /* loaded from: classes8.dex */
    public static final class b extends MapplsEndSession.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13277a;
        public String b;
        public String c;

        @Override // com.mappls.sdk.services.api.session.endsession.MapplsEndSession.Builder
        public MapplsEndSession autoBuild() {
            String str = "";
            if (this.f13277a == null) {
                str = " baseUrl";
            }
            if (this.b == null) {
                str = str + " sessionType";
            }
            if (this.c == null) {
                str = str + " clusterId";
            }
            if (str.isEmpty()) {
                return new a(this.f13277a, this.b, this.c);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.session.endsession.MapplsEndSession.Builder
        public MapplsEndSession.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f13277a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.session.endsession.MapplsEndSession.Builder
        public MapplsEndSession.Builder clusterId(String str) {
            Objects.requireNonNull(str, "Null clusterId");
            this.c = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.session.endsession.MapplsEndSession.Builder
        public MapplsEndSession.Builder sessionType(String str) {
            Objects.requireNonNull(str, "Null sessionType");
            this.b = str;
            return this;
        }
    }

    @Override // com.mappls.sdk.services.api.session.endsession.MapplsEndSession, com.mappls.sdk.services.api.MapplsService
    public String baseUrl() {
        return this.f13276a;
    }

    @Override // com.mappls.sdk.services.api.session.endsession.MapplsEndSession
    @NonNull
    public String clusterId() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsEndSession) {
            MapplsEndSession mapplsEndSession = (MapplsEndSession) obj;
            return this.f13276a.equals(mapplsEndSession.baseUrl()) && this.b.equals(mapplsEndSession.sessionType()) && this.c.equals(mapplsEndSession.clusterId());
        }
        return false;
    }

    public int hashCode() {
        return ((((this.f13276a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode();
    }

    @Override // com.mappls.sdk.services.api.session.endsession.MapplsEndSession
    @NonNull
    public String sessionType() {
        return this.b;
    }

    public String toString() {
        return "MapplsEndSession{baseUrl=" + this.f13276a + ", sessionType=" + this.b + ", clusterId=" + this.c + "}";
    }

    public a(String str, String str2, String str3) {
        this.f13276a = str;
        this.b = str2;
        this.c = str3;
    }
}

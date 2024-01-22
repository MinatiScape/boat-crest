package com.mappls.sdk.services.api.session.create;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.services.api.session.create.MapplsCreateSession;
import com.mappls.sdk.services.api.session.create.model.SessionRequestModel;
import java.util.Objects;
/* loaded from: classes8.dex */
public final class a extends MapplsCreateSession {

    /* renamed from: a  reason: collision with root package name */
    public final String f13268a;
    public final String b;
    public final SessionRequestModel c;
    public final String d;

    /* loaded from: classes8.dex */
    public static final class b extends MapplsCreateSession.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13269a;
        public String b;
        public SessionRequestModel c;
        public String d;

        @Override // com.mappls.sdk.services.api.session.create.MapplsCreateSession.Builder
        public MapplsCreateSession.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f13269a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.session.create.MapplsCreateSession.Builder
        public MapplsCreateSession build() {
            String str = "";
            if (this.f13269a == null) {
                str = " baseUrl";
            }
            if (this.c == null) {
                str = str + " sessionRequest";
            }
            if (this.d == null) {
                str = str + " sessionType";
            }
            if (str.isEmpty()) {
                return new a(this.f13269a, this.b, this.c, this.d);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.session.create.MapplsCreateSession.Builder
        public MapplsCreateSession.Builder clusterId(@Nullable String str) {
            this.b = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.session.create.MapplsCreateSession.Builder
        public MapplsCreateSession.Builder sessionRequest(SessionRequestModel sessionRequestModel) {
            Objects.requireNonNull(sessionRequestModel, "Null sessionRequest");
            this.c = sessionRequestModel;
            return this;
        }

        @Override // com.mappls.sdk.services.api.session.create.MapplsCreateSession.Builder
        public MapplsCreateSession.Builder sessionType(String str) {
            Objects.requireNonNull(str, "Null sessionType");
            this.d = str;
            return this;
        }
    }

    @Override // com.mappls.sdk.services.api.session.create.MapplsCreateSession, com.mappls.sdk.services.api.MapplsService
    public String baseUrl() {
        return this.f13268a;
    }

    @Override // com.mappls.sdk.services.api.session.create.MapplsCreateSession
    @Nullable
    public String clusterId() {
        return this.b;
    }

    public boolean equals(Object obj) {
        String str;
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsCreateSession) {
            MapplsCreateSession mapplsCreateSession = (MapplsCreateSession) obj;
            return this.f13268a.equals(mapplsCreateSession.baseUrl()) && ((str = this.b) != null ? str.equals(mapplsCreateSession.clusterId()) : mapplsCreateSession.clusterId() == null) && this.c.equals(mapplsCreateSession.sessionRequest()) && this.d.equals(mapplsCreateSession.sessionType());
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (this.f13268a.hashCode() ^ 1000003) * 1000003;
        String str = this.b;
        return ((((hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d.hashCode();
    }

    @Override // com.mappls.sdk.services.api.session.create.MapplsCreateSession
    @NonNull
    public SessionRequestModel sessionRequest() {
        return this.c;
    }

    @Override // com.mappls.sdk.services.api.session.create.MapplsCreateSession
    @NonNull
    public String sessionType() {
        return this.d;
    }

    public String toString() {
        return "MapplsCreateSession{baseUrl=" + this.f13268a + ", clusterId=" + this.b + ", sessionRequest=" + this.c + ", sessionType=" + this.d + "}";
    }

    public a(String str, @Nullable String str2, SessionRequestModel sessionRequestModel, String str3) {
        this.f13268a = str;
        this.b = str2;
        this.c = sessionRequestModel;
        this.d = str3;
    }
}

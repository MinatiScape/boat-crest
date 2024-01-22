package com.mappls.sdk.services.api.session.update;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.services.api.session.create.model.SessionRequestModel;
import com.mappls.sdk.services.api.session.update.MapplsUpdateSession;
import java.util.Objects;
/* loaded from: classes8.dex */
public final class a extends MapplsUpdateSession {

    /* renamed from: a  reason: collision with root package name */
    public final String f13281a;
    public final String b;
    public final SessionRequestModel c;
    public final String d;

    /* loaded from: classes8.dex */
    public static final class b extends MapplsUpdateSession.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13282a;
        public String b;
        public SessionRequestModel c;
        public String d;

        @Override // com.mappls.sdk.services.api.session.update.MapplsUpdateSession.Builder
        public MapplsUpdateSession.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f13282a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.session.update.MapplsUpdateSession.Builder
        public MapplsUpdateSession build() {
            String str = "";
            if (this.f13282a == null) {
                str = " baseUrl";
            }
            if (this.c == null) {
                str = str + " sessionRequest";
            }
            if (this.d == null) {
                str = str + " hyperlink";
            }
            if (str.isEmpty()) {
                return new a(this.f13282a, this.b, this.c, this.d);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.session.update.MapplsUpdateSession.Builder
        public MapplsUpdateSession.Builder clusterId(@Nullable String str) {
            this.b = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.session.update.MapplsUpdateSession.Builder
        public MapplsUpdateSession.Builder hyperlink(String str) {
            Objects.requireNonNull(str, "Null hyperlink");
            this.d = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.session.update.MapplsUpdateSession.Builder
        public MapplsUpdateSession.Builder sessionRequest(SessionRequestModel sessionRequestModel) {
            Objects.requireNonNull(sessionRequestModel, "Null sessionRequest");
            this.c = sessionRequestModel;
            return this;
        }
    }

    @Override // com.mappls.sdk.services.api.session.update.MapplsUpdateSession, com.mappls.sdk.services.api.MapplsService
    public String baseUrl() {
        return this.f13281a;
    }

    @Override // com.mappls.sdk.services.api.session.update.MapplsUpdateSession
    @Nullable
    public String clusterId() {
        return this.b;
    }

    public boolean equals(Object obj) {
        String str;
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsUpdateSession) {
            MapplsUpdateSession mapplsUpdateSession = (MapplsUpdateSession) obj;
            return this.f13281a.equals(mapplsUpdateSession.baseUrl()) && ((str = this.b) != null ? str.equals(mapplsUpdateSession.clusterId()) : mapplsUpdateSession.clusterId() == null) && this.c.equals(mapplsUpdateSession.sessionRequest()) && this.d.equals(mapplsUpdateSession.hyperlink());
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (this.f13281a.hashCode() ^ 1000003) * 1000003;
        String str = this.b;
        return ((((hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d.hashCode();
    }

    @Override // com.mappls.sdk.services.api.session.update.MapplsUpdateSession
    @NonNull
    public String hyperlink() {
        return this.d;
    }

    @Override // com.mappls.sdk.services.api.session.update.MapplsUpdateSession
    @NonNull
    public SessionRequestModel sessionRequest() {
        return this.c;
    }

    public String toString() {
        return "MapplsUpdateSession{baseUrl=" + this.f13281a + ", clusterId=" + this.b + ", sessionRequest=" + this.c + ", hyperlink=" + this.d + "}";
    }

    public a(String str, @Nullable String str2, SessionRequestModel sessionRequestModel, String str3) {
        this.f13281a = str;
        this.b = str2;
        this.c = sessionRequestModel;
        this.d = str3;
    }
}

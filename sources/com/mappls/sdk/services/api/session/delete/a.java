package com.mappls.sdk.services.api.session.delete;

import androidx.annotation.NonNull;
import com.mappls.sdk.services.api.session.delete.MapplsDeleteSession;
import java.util.Objects;
/* loaded from: classes8.dex */
public final class a extends MapplsDeleteSession {

    /* renamed from: a  reason: collision with root package name */
    public final String f13270a;
    public final String b;

    /* loaded from: classes8.dex */
    public static final class b extends MapplsDeleteSession.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13271a;
        public String b;

        @Override // com.mappls.sdk.services.api.session.delete.MapplsDeleteSession.Builder
        public MapplsDeleteSession.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f13271a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.session.delete.MapplsDeleteSession.Builder
        public MapplsDeleteSession build() {
            String str = "";
            if (this.f13271a == null) {
                str = " baseUrl";
            }
            if (this.b == null) {
                str = str + " hyperlink";
            }
            if (str.isEmpty()) {
                return new a(this.f13271a, this.b);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.session.delete.MapplsDeleteSession.Builder
        public MapplsDeleteSession.Builder hyperlink(String str) {
            Objects.requireNonNull(str, "Null hyperlink");
            this.b = str;
            return this;
        }
    }

    @Override // com.mappls.sdk.services.api.session.delete.MapplsDeleteSession, com.mappls.sdk.services.api.MapplsService
    public String baseUrl() {
        return this.f13270a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsDeleteSession) {
            MapplsDeleteSession mapplsDeleteSession = (MapplsDeleteSession) obj;
            return this.f13270a.equals(mapplsDeleteSession.baseUrl()) && this.b.equals(mapplsDeleteSession.hyperlink());
        }
        return false;
    }

    public int hashCode() {
        return ((this.f13270a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode();
    }

    @Override // com.mappls.sdk.services.api.session.delete.MapplsDeleteSession
    @NonNull
    public String hyperlink() {
        return this.b;
    }

    public String toString() {
        return "MapplsDeleteSession{baseUrl=" + this.f13270a + ", hyperlink=" + this.b + "}";
    }

    public a(String str, String str2) {
        this.f13270a = str;
        this.b = str2;
    }
}

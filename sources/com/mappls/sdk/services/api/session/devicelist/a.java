package com.mappls.sdk.services.api.session.devicelist;

import androidx.annotation.NonNull;
import com.mappls.sdk.services.api.session.devicelist.MapplsClusterLinkedDevices;
import java.util.Objects;
/* loaded from: classes8.dex */
public final class a extends MapplsClusterLinkedDevices {

    /* renamed from: a  reason: collision with root package name */
    public final String f13273a;
    public final String b;
    public final String c;

    /* loaded from: classes8.dex */
    public static final class b extends MapplsClusterLinkedDevices.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13274a;
        public String b;
        public String c;

        @Override // com.mappls.sdk.services.api.session.devicelist.MapplsClusterLinkedDevices.Builder
        public MapplsClusterLinkedDevices autoBuild() {
            String str = "";
            if (this.f13274a == null) {
                str = " baseUrl";
            }
            if (this.b == null) {
                str = str + " sessionType";
            }
            if (this.c == null) {
                str = str + " clusterId";
            }
            if (str.isEmpty()) {
                return new a(this.f13274a, this.b, this.c);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.session.devicelist.MapplsClusterLinkedDevices.Builder
        public MapplsClusterLinkedDevices.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f13274a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.session.devicelist.MapplsClusterLinkedDevices.Builder
        public MapplsClusterLinkedDevices.Builder clusterId(String str) {
            Objects.requireNonNull(str, "Null clusterId");
            this.c = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.session.devicelist.MapplsClusterLinkedDevices.Builder
        public MapplsClusterLinkedDevices.Builder sessionType(String str) {
            Objects.requireNonNull(str, "Null sessionType");
            this.b = str;
            return this;
        }
    }

    @Override // com.mappls.sdk.services.api.session.devicelist.MapplsClusterLinkedDevices, com.mappls.sdk.services.api.MapplsService
    public String baseUrl() {
        return this.f13273a;
    }

    @Override // com.mappls.sdk.services.api.session.devicelist.MapplsClusterLinkedDevices
    @NonNull
    public String clusterId() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsClusterLinkedDevices) {
            MapplsClusterLinkedDevices mapplsClusterLinkedDevices = (MapplsClusterLinkedDevices) obj;
            return this.f13273a.equals(mapplsClusterLinkedDevices.baseUrl()) && this.b.equals(mapplsClusterLinkedDevices.sessionType()) && this.c.equals(mapplsClusterLinkedDevices.clusterId());
        }
        return false;
    }

    public int hashCode() {
        return ((((this.f13273a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode();
    }

    @Override // com.mappls.sdk.services.api.session.devicelist.MapplsClusterLinkedDevices
    @NonNull
    public String sessionType() {
        return this.b;
    }

    public String toString() {
        return "MapplsClusterLinkedDevices{baseUrl=" + this.f13273a + ", sessionType=" + this.b + ", clusterId=" + this.c + "}";
    }

    public a(String str, String str2, String str3) {
        this.f13273a = str;
        this.b = str2;
        this.c = str3;
    }
}

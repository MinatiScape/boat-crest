package com.mappls.sdk.services.api.session.removedevice;

import androidx.annotation.NonNull;
import com.mappls.sdk.services.api.session.removedevice.MapplsDeleteClusterLinkedDevice;
import java.util.Objects;
/* loaded from: classes8.dex */
public final class a extends MapplsDeleteClusterLinkedDevice {

    /* renamed from: a  reason: collision with root package name */
    public final String f13279a;
    public final String b;
    public final String c;
    public final String d;

    /* loaded from: classes8.dex */
    public static final class b extends MapplsDeleteClusterLinkedDevice.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13280a;
        public String b;
        public String c;
        public String d;

        @Override // com.mappls.sdk.services.api.session.removedevice.MapplsDeleteClusterLinkedDevice.Builder
        public MapplsDeleteClusterLinkedDevice autoBuild() {
            String str = "";
            if (this.f13280a == null) {
                str = " baseUrl";
            }
            if (this.b == null) {
                str = str + " sessionType";
            }
            if (this.c == null) {
                str = str + " clusterId";
            }
            if (this.d == null) {
                str = str + " linkedDevice";
            }
            if (str.isEmpty()) {
                return new a(this.f13280a, this.b, this.c, this.d);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.mappls.sdk.services.api.session.removedevice.MapplsDeleteClusterLinkedDevice.Builder
        public MapplsDeleteClusterLinkedDevice.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f13280a = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.session.removedevice.MapplsDeleteClusterLinkedDevice.Builder
        public MapplsDeleteClusterLinkedDevice.Builder clusterId(String str) {
            Objects.requireNonNull(str, "Null clusterId");
            this.c = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.session.removedevice.MapplsDeleteClusterLinkedDevice.Builder
        public MapplsDeleteClusterLinkedDevice.Builder linkedDevice(String str) {
            Objects.requireNonNull(str, "Null linkedDevice");
            this.d = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.session.removedevice.MapplsDeleteClusterLinkedDevice.Builder
        public MapplsDeleteClusterLinkedDevice.Builder sessionType(String str) {
            Objects.requireNonNull(str, "Null sessionType");
            this.b = str;
            return this;
        }
    }

    @Override // com.mappls.sdk.services.api.session.removedevice.MapplsDeleteClusterLinkedDevice, com.mappls.sdk.services.api.MapplsService
    public String baseUrl() {
        return this.f13279a;
    }

    @Override // com.mappls.sdk.services.api.session.removedevice.MapplsDeleteClusterLinkedDevice
    @NonNull
    public String clusterId() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsDeleteClusterLinkedDevice) {
            MapplsDeleteClusterLinkedDevice mapplsDeleteClusterLinkedDevice = (MapplsDeleteClusterLinkedDevice) obj;
            return this.f13279a.equals(mapplsDeleteClusterLinkedDevice.baseUrl()) && this.b.equals(mapplsDeleteClusterLinkedDevice.sessionType()) && this.c.equals(mapplsDeleteClusterLinkedDevice.clusterId()) && this.d.equals(mapplsDeleteClusterLinkedDevice.linkedDevice());
        }
        return false;
    }

    public int hashCode() {
        return ((((((this.f13279a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d.hashCode();
    }

    @Override // com.mappls.sdk.services.api.session.removedevice.MapplsDeleteClusterLinkedDevice
    @NonNull
    public String linkedDevice() {
        return this.d;
    }

    @Override // com.mappls.sdk.services.api.session.removedevice.MapplsDeleteClusterLinkedDevice
    @NonNull
    public String sessionType() {
        return this.b;
    }

    public String toString() {
        return "MapplsDeleteClusterLinkedDevice{baseUrl=" + this.f13279a + ", sessionType=" + this.b + ", clusterId=" + this.c + ", linkedDevice=" + this.d + "}";
    }

    public a(String str, String str2, String str3, String str4) {
        this.f13279a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
    }
}

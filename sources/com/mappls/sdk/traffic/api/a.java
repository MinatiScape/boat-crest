package com.mappls.sdk.traffic.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.navigation.h;
import com.mappls.sdk.navigation.o;
import com.mappls.sdk.traffic.api.MapplsBeacon;
import com.mappls.sdk.traffic.model.BeaconPacket;
import java.util.Objects;
/* loaded from: classes8.dex */
public final class a extends MapplsBeacon {

    /* renamed from: a  reason: collision with root package name */
    public final String f13313a;
    public final String b;
    public final BeaconPacket c;

    /* renamed from: com.mappls.sdk.traffic.api.a$a  reason: collision with other inner class name */
    /* loaded from: classes8.dex */
    public static final class C0708a extends MapplsBeacon.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13314a;
        public String b;
        public BeaconPacket c;

        @Override // com.mappls.sdk.traffic.api.MapplsBeacon.Builder
        public final MapplsBeacon autoBuild() {
            String a2 = this.f13314a == null ? o.a("", " baseUrl") : "";
            if (this.c == null) {
                a2 = o.a(a2, " beaconPacket");
            }
            if (a2.isEmpty()) {
                return new a(this.f13314a, this.b, this.c, 0);
            }
            throw new IllegalStateException(o.a("Missing required properties:", a2));
        }

        @Override // com.mappls.sdk.traffic.api.MapplsBeacon.Builder
        public final MapplsBeacon.Builder baseUrl(String str) {
            Objects.requireNonNull(str, "Null baseUrl");
            this.f13314a = str;
            return this;
        }

        @Override // com.mappls.sdk.traffic.api.MapplsBeacon.Builder
        public final MapplsBeacon.Builder beaconKey(@Nullable String str) {
            this.b = str;
            return this;
        }

        @Override // com.mappls.sdk.traffic.api.MapplsBeacon.Builder
        public final MapplsBeacon.Builder beaconPacket(BeaconPacket beaconPacket) {
            Objects.requireNonNull(beaconPacket, "Null beaconPacket");
            this.c = beaconPacket;
            return this;
        }
    }

    public a(String str, @Nullable String str2, BeaconPacket beaconPacket) {
        this.f13313a = str;
        this.b = str2;
        this.c = beaconPacket;
    }

    public /* synthetic */ a(String str, String str2, BeaconPacket beaconPacket, int i) {
        this(str, str2, beaconPacket);
    }

    @Override // com.mappls.sdk.traffic.api.MapplsBeacon
    @Nullable
    public final String a() {
        return this.b;
    }

    @Override // com.mappls.sdk.traffic.api.MapplsBeacon
    @NonNull
    public final BeaconPacket b() {
        return this.c;
    }

    @Override // com.mappls.sdk.traffic.api.MapplsBeacon, com.mappls.sdk.services.api.MapplsService
    public final String baseUrl() {
        return this.f13313a;
    }

    public final boolean equals(Object obj) {
        String str;
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapplsBeacon) {
            MapplsBeacon mapplsBeacon = (MapplsBeacon) obj;
            return this.f13313a.equals(mapplsBeacon.baseUrl()) && ((str = this.b) != null ? str.equals(mapplsBeacon.a()) : mapplsBeacon.a() == null) && this.c.equals(mapplsBeacon.b());
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = (this.f13313a.hashCode() ^ 1000003) * 1000003;
        String str = this.b;
        return ((hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003) ^ this.c.hashCode();
    }

    public final String toString() {
        StringBuilder a2 = h.a("MapplsBeacon{baseUrl=");
        a2.append(this.f13313a);
        a2.append(", beaconKey=");
        a2.append(this.b);
        a2.append(", beaconPacket=");
        a2.append(this.c);
        a2.append("}");
        return a2.toString();
    }
}

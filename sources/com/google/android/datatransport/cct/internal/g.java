package com.google.android.datatransport.cct.internal;

import androidx.annotation.Nullable;
import com.google.android.datatransport.cct.internal.NetworkConnectionInfo;
/* loaded from: classes6.dex */
public final class g extends NetworkConnectionInfo {

    /* renamed from: a  reason: collision with root package name */
    public final NetworkConnectionInfo.NetworkType f8075a;
    public final NetworkConnectionInfo.MobileSubtype b;

    /* loaded from: classes6.dex */
    public static final class b extends NetworkConnectionInfo.Builder {

        /* renamed from: a  reason: collision with root package name */
        public NetworkConnectionInfo.NetworkType f8076a;
        public NetworkConnectionInfo.MobileSubtype b;

        @Override // com.google.android.datatransport.cct.internal.NetworkConnectionInfo.Builder
        public NetworkConnectionInfo build() {
            return new g(this.f8076a, this.b);
        }

        @Override // com.google.android.datatransport.cct.internal.NetworkConnectionInfo.Builder
        public NetworkConnectionInfo.Builder setMobileSubtype(@Nullable NetworkConnectionInfo.MobileSubtype mobileSubtype) {
            this.b = mobileSubtype;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.NetworkConnectionInfo.Builder
        public NetworkConnectionInfo.Builder setNetworkType(@Nullable NetworkConnectionInfo.NetworkType networkType) {
            this.f8076a = networkType;
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof NetworkConnectionInfo) {
            NetworkConnectionInfo networkConnectionInfo = (NetworkConnectionInfo) obj;
            NetworkConnectionInfo.NetworkType networkType = this.f8075a;
            if (networkType != null ? networkType.equals(networkConnectionInfo.getNetworkType()) : networkConnectionInfo.getNetworkType() == null) {
                NetworkConnectionInfo.MobileSubtype mobileSubtype = this.b;
                if (mobileSubtype == null) {
                    if (networkConnectionInfo.getMobileSubtype() == null) {
                        return true;
                    }
                } else if (mobileSubtype.equals(networkConnectionInfo.getMobileSubtype())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override // com.google.android.datatransport.cct.internal.NetworkConnectionInfo
    @Nullable
    public NetworkConnectionInfo.MobileSubtype getMobileSubtype() {
        return this.b;
    }

    @Override // com.google.android.datatransport.cct.internal.NetworkConnectionInfo
    @Nullable
    public NetworkConnectionInfo.NetworkType getNetworkType() {
        return this.f8075a;
    }

    public int hashCode() {
        NetworkConnectionInfo.NetworkType networkType = this.f8075a;
        int hashCode = ((networkType == null ? 0 : networkType.hashCode()) ^ 1000003) * 1000003;
        NetworkConnectionInfo.MobileSubtype mobileSubtype = this.b;
        return hashCode ^ (mobileSubtype != null ? mobileSubtype.hashCode() : 0);
    }

    public String toString() {
        return "NetworkConnectionInfo{networkType=" + this.f8075a + ", mobileSubtype=" + this.b + "}";
    }

    public g(@Nullable NetworkConnectionInfo.NetworkType networkType, @Nullable NetworkConnectionInfo.MobileSubtype mobileSubtype) {
        this.f8075a = networkType;
        this.b = mobileSubtype;
    }
}

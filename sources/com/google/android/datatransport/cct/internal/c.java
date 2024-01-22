package com.google.android.datatransport.cct.internal;

import androidx.annotation.Nullable;
import com.google.android.datatransport.cct.internal.ClientInfo;
/* loaded from: classes6.dex */
public final class c extends ClientInfo {

    /* renamed from: a  reason: collision with root package name */
    public final ClientInfo.ClientType f8068a;
    public final AndroidClientInfo b;

    /* loaded from: classes6.dex */
    public static final class b extends ClientInfo.Builder {

        /* renamed from: a  reason: collision with root package name */
        public ClientInfo.ClientType f8069a;
        public AndroidClientInfo b;

        @Override // com.google.android.datatransport.cct.internal.ClientInfo.Builder
        public ClientInfo build() {
            return new c(this.f8069a, this.b);
        }

        @Override // com.google.android.datatransport.cct.internal.ClientInfo.Builder
        public ClientInfo.Builder setAndroidClientInfo(@Nullable AndroidClientInfo androidClientInfo) {
            this.b = androidClientInfo;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.ClientInfo.Builder
        public ClientInfo.Builder setClientType(@Nullable ClientInfo.ClientType clientType) {
            this.f8069a = clientType;
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ClientInfo) {
            ClientInfo clientInfo = (ClientInfo) obj;
            ClientInfo.ClientType clientType = this.f8068a;
            if (clientType != null ? clientType.equals(clientInfo.getClientType()) : clientInfo.getClientType() == null) {
                AndroidClientInfo androidClientInfo = this.b;
                if (androidClientInfo == null) {
                    if (clientInfo.getAndroidClientInfo() == null) {
                        return true;
                    }
                } else if (androidClientInfo.equals(clientInfo.getAndroidClientInfo())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override // com.google.android.datatransport.cct.internal.ClientInfo
    @Nullable
    public AndroidClientInfo getAndroidClientInfo() {
        return this.b;
    }

    @Override // com.google.android.datatransport.cct.internal.ClientInfo
    @Nullable
    public ClientInfo.ClientType getClientType() {
        return this.f8068a;
    }

    public int hashCode() {
        ClientInfo.ClientType clientType = this.f8068a;
        int hashCode = ((clientType == null ? 0 : clientType.hashCode()) ^ 1000003) * 1000003;
        AndroidClientInfo androidClientInfo = this.b;
        return hashCode ^ (androidClientInfo != null ? androidClientInfo.hashCode() : 0);
    }

    public String toString() {
        return "ClientInfo{clientType=" + this.f8068a + ", androidClientInfo=" + this.b + "}";
    }

    public c(@Nullable ClientInfo.ClientType clientType, @Nullable AndroidClientInfo androidClientInfo) {
        this.f8068a = clientType;
        this.b = androidClientInfo;
    }
}

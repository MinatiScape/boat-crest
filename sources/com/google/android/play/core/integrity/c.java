package com.google.android.play.core.integrity;

import androidx.annotation.Nullable;
/* loaded from: classes10.dex */
final class c extends IntegrityTokenRequest {

    /* renamed from: a  reason: collision with root package name */
    private final String f10451a;
    private final Long b;

    public /* synthetic */ c(String str, Long l, b bVar) {
        this.f10451a = str;
        this.b = l;
    }

    @Override // com.google.android.play.core.integrity.IntegrityTokenRequest
    @Nullable
    public final Long cloudProjectNumber() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        Long l;
        if (obj == this) {
            return true;
        }
        if (obj instanceof IntegrityTokenRequest) {
            IntegrityTokenRequest integrityTokenRequest = (IntegrityTokenRequest) obj;
            if (this.f10451a.equals(integrityTokenRequest.nonce()) && ((l = this.b) != null ? l.equals(integrityTokenRequest.cloudProjectNumber()) : integrityTokenRequest.cloudProjectNumber() == null)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = this.f10451a.hashCode() ^ 1000003;
        Long l = this.b;
        return (hashCode * 1000003) ^ (l == null ? 0 : l.hashCode());
    }

    @Override // com.google.android.play.core.integrity.IntegrityTokenRequest
    public final String nonce() {
        return this.f10451a;
    }

    public final String toString() {
        String str = this.f10451a;
        Long l = this.b;
        return "IntegrityTokenRequest{nonce=" + str + ", cloudProjectNumber=" + l + "}";
    }
}

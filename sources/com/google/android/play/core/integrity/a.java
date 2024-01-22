package com.google.android.play.core.integrity;

import com.google.android.play.core.integrity.IntegrityTokenRequest;
import java.util.Objects;
/* loaded from: classes10.dex */
final class a extends IntegrityTokenRequest.Builder {

    /* renamed from: a  reason: collision with root package name */
    private String f10450a;
    private Long b;

    @Override // com.google.android.play.core.integrity.IntegrityTokenRequest.Builder
    public final IntegrityTokenRequest build() {
        String str = this.f10450a;
        if (str != null) {
            return new c(str, this.b, null);
        }
        throw new IllegalStateException("Missing required properties: nonce");
    }

    @Override // com.google.android.play.core.integrity.IntegrityTokenRequest.Builder
    public final IntegrityTokenRequest.Builder setCloudProjectNumber(long j) {
        this.b = Long.valueOf(j);
        return this;
    }

    @Override // com.google.android.play.core.integrity.IntegrityTokenRequest.Builder
    public final IntegrityTokenRequest.Builder setNonce(String str) {
        Objects.requireNonNull(str, "Null nonce");
        this.f10450a = str;
        return this;
    }
}

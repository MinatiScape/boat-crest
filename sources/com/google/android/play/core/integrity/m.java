package com.google.android.play.core.integrity;

import com.google.android.gms.tasks.Task;
/* loaded from: classes10.dex */
final class m implements IntegrityManager {

    /* renamed from: a  reason: collision with root package name */
    private final t f10457a;

    public m(t tVar) {
        this.f10457a = tVar;
    }

    @Override // com.google.android.play.core.integrity.IntegrityManager
    public final Task<IntegrityTokenResponse> requestIntegrityToken(IntegrityTokenRequest integrityTokenRequest) {
        return this.f10457a.b(integrityTokenRequest);
    }
}

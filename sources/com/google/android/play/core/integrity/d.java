package com.google.android.play.core.integrity;
/* loaded from: classes10.dex */
final class d extends w {

    /* renamed from: a  reason: collision with root package name */
    private String f10452a;

    @Override // com.google.android.play.core.integrity.w
    public final w a(String str) {
        this.f10452a = str;
        return this;
    }

    @Override // com.google.android.play.core.integrity.w
    public final IntegrityTokenResponse b() {
        String str = this.f10452a;
        if (str != null) {
            return new f(str, null);
        }
        throw new IllegalStateException("Missing required properties: token");
    }
}

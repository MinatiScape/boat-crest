package com.google.android.gms.internal.auth;

import com.google.android.gms.auth.api.proxy.ProxyResponse;
/* loaded from: classes6.dex */
public final class p extends zzbd {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ q f8535a;

    public p(q qVar) {
        this.f8535a = qVar;
    }

    @Override // com.google.android.gms.internal.auth.zzbd, com.google.android.gms.internal.auth.zzbg
    public final void zzb(ProxyResponse proxyResponse) {
        this.f8535a.setResult((q) new t(proxyResponse));
    }
}

package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
/* loaded from: classes12.dex */
public final class p6 implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ com.touchgui.sdk.h f13811a;

    public p6(com.touchgui.sdk.h hVar) {
        this.f13811a = hVar;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        this.f13811a.a(th);
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        Void r1 = (Void) obj;
        this.f13811a.a();
    }
}

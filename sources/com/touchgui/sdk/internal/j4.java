package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
/* loaded from: classes12.dex */
public final class j4 implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ k4 f13782a;

    public j4(k4 k4Var) {
        this.f13782a = k4Var;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        this.f13782a.f13765a.a(1);
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        Void r1 = (Void) obj;
        this.f13782a.c();
    }
}

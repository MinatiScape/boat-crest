package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
/* loaded from: classes12.dex */
public final class n4 implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ o4 f13800a;

    public n4(o4 o4Var) {
        this.f13800a = o4Var;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        f4 f4Var = this.f13800a.f13765a;
        if (f4Var != null) {
            f4Var.a(-1);
        }
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        Void r1 = (Void) obj;
        this.f13800a.c();
    }
}

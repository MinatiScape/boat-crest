package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
/* loaded from: classes12.dex */
public final class m1 implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ n1 f13792a;

    public m1(n1 n1Var) {
        this.f13792a = n1Var;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        TGCallback tGCallback = this.f13792a.b;
        if (tGCallback != null) {
            tGCallback.onFailure(th);
        }
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        Integer num = (Integer) obj;
        this.f13792a.a();
    }
}

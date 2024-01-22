package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
import com.touchgui.sdk.TGLogger;
import com.touchgui.sdk.bean.TGDevice;
/* loaded from: classes12.dex */
public final class y implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TGDevice f13844a;
    public final /* synthetic */ a0 b;

    public y(a0 a0Var, TGDevice tGDevice) {
        this.b = a0Var;
        this.f13844a = tGDevice;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        TGLogger.e(this.b.c(), th.getMessage());
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        this.b.a(this.f13844a, (String) obj);
    }
}

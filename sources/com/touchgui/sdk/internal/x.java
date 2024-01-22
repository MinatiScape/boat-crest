package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
import com.touchgui.sdk.TGLogger;
import com.touchgui.sdk.bean.TGDevice;
/* loaded from: classes12.dex */
public final class x implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TGDevice f13841a;
    public final /* synthetic */ a0 b;

    public x(a0 a0Var, TGDevice tGDevice) {
        this.b = a0Var;
        this.f13841a = tGDevice;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        TGLogger.e(this.b.c(), th.getMessage());
        a0.a(this.b, this.f13841a, false);
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        this.b.v = new w3();
        a0 a0Var = this.b;
        a0Var.v.c = (byte[]) obj;
        a0.a(a0Var, this.f13841a, true);
    }
}

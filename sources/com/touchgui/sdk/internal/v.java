package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
import com.touchgui.sdk.TGLogger;
import com.touchgui.sdk.bean.TGDevice;
/* loaded from: classes12.dex */
public final class v implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TGDevice f13832a;
    public final /* synthetic */ a0 b;

    public v(a0 a0Var, TGDevice tGDevice) {
        this.b = a0Var;
        this.f13832a = tGDevice;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        TGLogger.e(this.b.c(), th.getMessage());
        a0.a(this.b, this.f13832a, false);
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        a0 a0Var = this.b;
        a0Var.v = (w3) obj;
        if (a0Var.a(33686848)) {
            a0 a0Var2 = this.b;
            TGDevice tGDevice = this.f13832a;
            a0Var2.getClass();
            new v8(a0Var2, new y7()).execute(new w(a0Var2, tGDevice));
            return;
        }
        a0.a(this.b, this.f13832a, true);
    }
}

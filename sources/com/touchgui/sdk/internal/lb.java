package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
import com.touchgui.sdk.TGLogger;
/* loaded from: classes12.dex */
public final class lb implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ nb f13791a;

    public lb(nb nbVar) {
        this.f13791a = nbVar;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        a0 a0Var = this.f13791a.e.f13809a;
        TGLogger.e(a0Var, "workout detail error:" + th.getMessage());
        nb.a(this.f13791a, (com.touchgui.sdk.bean.a) null);
        nb.a(this.f13791a, false);
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        nb.a(this.f13791a, (com.touchgui.sdk.bean.a) obj);
        nb.a(this.f13791a, true);
    }
}

package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
/* loaded from: classes12.dex */
public final class ab implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ cb f13743a;

    public ab(cb cbVar) {
        this.f13743a = cbVar;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        this.f13743a.f13824a.a(-1);
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        boolean a2 = obj != null ? this.f13743a.a(obj) : false;
        cb cbVar = this.f13743a;
        cbVar.b = a2;
        cbVar.b();
    }
}

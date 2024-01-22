package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
/* loaded from: classes12.dex */
public final class bb implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ cb f13748a;

    public bb(cb cbVar) {
        this.f13748a = cbVar;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        ra raVar = this.f13748a.f13824a;
        if (raVar != null) {
            raVar.a(-1);
        }
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        Void r2 = (Void) obj;
        cb cbVar = this.f13748a;
        if (cbVar.b) {
            cbVar.a();
            return;
        }
        ra raVar = cbVar.f13824a;
        if (raVar != null) {
            raVar.a(1);
        }
    }
}

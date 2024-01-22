package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
/* loaded from: classes12.dex */
public final class kb implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ nb f13787a;

    public kb(nb nbVar) {
        this.f13787a = nbVar;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        nb.a(this.f13787a, false);
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        nb nbVar = this.f13787a;
        nbVar.d = (qb) obj;
        new v8(nbVar.e.f13809a, new e9(nbVar.c, 2)).execute(new jb(nbVar, 2));
    }
}

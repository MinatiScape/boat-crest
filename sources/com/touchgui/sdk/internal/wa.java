package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
/* loaded from: classes12.dex */
public final class wa implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ eb f13840a;

    public wa(eb ebVar) {
        this.f13840a = ebVar;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        this.f13840a.f13824a.a(1);
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        Void r4 = (Void) obj;
        eb ebVar = this.f13840a;
        int i = ebVar.b + 1;
        ebVar.b = i;
        if (i < ebVar.c) {
            ebVar.a();
        } else {
            ebVar.f13824a.a(1);
        }
    }
}

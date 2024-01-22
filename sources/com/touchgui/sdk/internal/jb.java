package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
import com.touchgui.sdk.TGErrorCode;
/* loaded from: classes12.dex */
public final class jb implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f13785a;
    public final /* synthetic */ nb b;

    public jb(nb nbVar, int i) {
        this.b = nbVar;
        this.f13785a = i;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        ob.a(this.b.e, TGErrorCode.ERROR_WORKOUT_QUERY_DATA_SIZE);
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        f8 f8Var = (f8) obj;
        if (!(f8Var.f13762a == 0)) {
            nb.a(this.b, false);
            return;
        }
        int i = this.f13785a;
        if (i == 1) {
            nb nbVar = this.b;
            new g5(nbVar.e.f13809a, new i9(nbVar.c, ((Integer) f8Var.b).intValue())).execute(new kb(nbVar));
        } else if (i == 2) {
            if (((Integer) f8Var.b).intValue() > 0) {
                nb nbVar2 = this.b;
                new g5(nbVar2.e.f13809a, new h9(nbVar2.c, ((Integer) f8Var.b).intValue())).execute(new lb(nbVar2));
                return;
            }
            nb.a(this.b, (com.touchgui.sdk.bean.a) null);
            nb.a(this.b, true);
        }
    }
}

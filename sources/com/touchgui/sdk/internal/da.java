package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
import com.touchgui.sdk.TGLogManager;
import com.touchgui.sdk.TGLogger;
import java.util.Iterator;
/* loaded from: classes12.dex */
public final class da implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ boolean f13756a;
    public final /* synthetic */ com.touchgui.sdk.m b;

    public da(com.touchgui.sdk.m mVar, boolean z) {
        this.b = mVar;
        this.f13756a = z;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        com.touchgui.sdk.m mVar = this.b;
        TGLogger.e(mVar.f13857a, th.getMessage());
        Iterator it = mVar.c.iterator();
        while (it.hasNext()) {
            ((TGLogManager.Listener) it.next()).onError(th);
        }
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        Void r3 = (Void) obj;
        if (this.f13756a) {
            com.touchgui.sdk.m mVar = this.b;
            TGLogger.d(mVar.f13857a, "export log is completed");
            mVar.b.removeCallbacks(mVar.e);
            Iterator it = mVar.c.iterator();
            while (it.hasNext()) {
                ((TGLogManager.Listener) it.next()).onCompleted();
            }
        }
    }
}

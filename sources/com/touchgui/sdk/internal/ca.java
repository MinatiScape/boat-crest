package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
import com.touchgui.sdk.TGLogManager;
import com.touchgui.sdk.TGLogger;
import java.util.Iterator;
/* loaded from: classes12.dex */
public final class ca implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ com.touchgui.sdk.m f13751a;

    public ca(com.touchgui.sdk.m mVar) {
        this.f13751a = mVar;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        com.touchgui.sdk.m mVar = this.f13751a;
        TGLogger.e(mVar.f13857a, th.getMessage());
        Iterator it = mVar.c.iterator();
        while (it.hasNext()) {
            ((TGLogManager.Listener) it.next()).onError(th);
        }
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        Void r5 = (Void) obj;
        com.touchgui.sdk.m mVar = this.f13751a;
        TGLogger.d(mVar.f13857a, "Start to export log");
        Iterator it = mVar.c.iterator();
        while (it.hasNext()) {
            ((TGLogManager.Listener) it.next()).onStart();
        }
        if (mVar.d > 0) {
            mVar.b.removeCallbacks(mVar.e);
            mVar.b.postDelayed(mVar.e, mVar.d);
        }
    }
}

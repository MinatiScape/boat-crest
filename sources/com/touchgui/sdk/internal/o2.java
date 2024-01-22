package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
import com.touchgui.sdk.TGDialManager;
import com.touchgui.sdk.TGLogger;
import com.touchgui.sdk.exception.TGException;
import java.util.Iterator;
/* loaded from: classes12.dex */
public final class o2 implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ com.touchgui.sdk.b f13805a;

    public o2(com.touchgui.sdk.b bVar) {
        this.f13805a = bVar;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        this.f13805a.a(th);
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        Integer num = (Integer) obj;
        if (num.intValue() == 1) {
            com.touchgui.sdk.b bVar = this.f13805a;
            TGLogger.d(bVar.f13731a, "sync dial completed");
            bVar.j.set(false);
            p2 p2Var = bVar.k;
            if (p2Var != null) {
                Iterator it = ((com.touchgui.sdk.i) p2Var).f13738a.b.iterator();
                while (it.hasNext()) {
                    ((TGDialManager.OnSyncDialListener) it.next()).onCompleted();
                }
                return;
            }
            return;
        }
        this.f13805a.a(new TGException("set dail operate failure", 60000, num.intValue()));
    }
}

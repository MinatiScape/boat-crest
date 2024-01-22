package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
import com.touchgui.sdk.TGSyncAgpsFileListener;
import com.touchgui.sdk.bean.TGGpsStatus;
import java.util.Iterator;
/* loaded from: classes12.dex */
public final class b4 implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ com.touchgui.sdk.c f13746a;

    public b4(com.touchgui.sdk.c cVar) {
        this.f13746a = cVar;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        this.f13746a.a(th);
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        TGGpsStatus tGGpsStatus = (TGGpsStatus) obj;
        com.touchgui.sdk.c cVar = this.f13746a;
        Iterator it = cVar.c.iterator();
        while (it.hasNext()) {
            ((TGSyncAgpsFileListener) it.next()).onCompleted();
        }
        cVar.l.set(false);
    }
}

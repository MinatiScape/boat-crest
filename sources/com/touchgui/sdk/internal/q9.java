package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
import com.touchgui.sdk.TGEventListener;
import com.touchgui.sdk.bean.TGSportStatusEvent;
import java.util.Iterator;
/* loaded from: classes12.dex */
public final class q9 implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ t9 f13818a;

    public q9(t9 t9Var) {
        this.f13818a = t9Var;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        TGSportStatusEvent tGSportStatusEvent = (TGSportStatusEvent) obj;
        Iterator it = this.f13818a.b.iterator();
        while (it.hasNext()) {
            ((TGEventListener) it.next()).onSportStatusEvent(tGSportStatusEvent);
        }
    }
}

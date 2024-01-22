package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
import com.touchgui.sdk.TGEventListener;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes12.dex */
public final class n9 implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ t9 f13801a;

    public n9(t9 t9Var) {
        this.f13801a = t9Var;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        List<String> list = (List) obj;
        Iterator it = this.f13801a.b.iterator();
        while (it.hasNext()) {
            ((TGEventListener) it.next()).onRequestIotDeviceIcon(list);
        }
    }
}

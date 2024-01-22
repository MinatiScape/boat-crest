package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
import com.touchgui.sdk.TGEventListener;
import java.util.Iterator;
/* loaded from: classes12.dex */
public final class m9 implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ t9 f13795a;

    public m9(t9 t9Var) {
        this.f13795a = t9Var;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        Integer num = (Integer) obj;
        Iterator it = this.f13795a.b.iterator();
        while (it.hasNext()) {
            ((TGEventListener) it.next()).onEvent(num.intValue());
        }
    }
}

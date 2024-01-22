package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
import com.touchgui.sdk.TGEventListener;
import java.util.Iterator;
/* loaded from: classes12.dex */
public final class p9 implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ t9 f13812a;

    public p9(t9 t9Var) {
        this.f13812a = t9Var;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        Integer num = (Integer) obj;
        Iterator it = this.f13812a.b.iterator();
        while (it.hasNext()) {
            ((TGEventListener) it.next()).onFindPhone(num.intValue());
        }
    }
}

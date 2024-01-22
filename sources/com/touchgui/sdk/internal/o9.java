package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGEventListener;
import com.touchgui.sdk.TGProgressCallback;
import com.touchgui.sdk.bean.TGIotDevice;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes12.dex */
public final class o9 implements TGProgressCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ t9 f13807a;

    public o9(t9 t9Var) {
        this.f13807a = t9Var;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
    }

    @Override // com.touchgui.sdk.TGProgressCallback
    public final void onProgress(int i, int i2, int i3) {
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        List<TGIotDevice> list = (List) obj;
        Iterator it = this.f13807a.b.iterator();
        while (it.hasNext()) {
            ((TGEventListener) it.next()).onControlIotDevice(list);
        }
    }
}

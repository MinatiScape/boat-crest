package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
import com.touchgui.sdk.exception.TGException;
/* loaded from: classes12.dex */
public final class u9 implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TGCallback f13830a;

    public u9(TGCallback tGCallback) {
        this.f13830a = tGCallback;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        this.f13830a.onFailure(th);
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        Integer num = (Integer) obj;
        if (num.intValue() == 0) {
            this.f13830a.onSuccess(null);
        } else {
            this.f13830a.onFailure(TGException.otaSetTotalError(num.intValue()));
        }
    }
}

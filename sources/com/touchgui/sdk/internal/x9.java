package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
import com.touchgui.sdk.exception.TGException;
/* loaded from: classes12.dex */
public final class x9 implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ com.touchgui.sdk.k f13842a;

    public x9(com.touchgui.sdk.k kVar) {
        this.f13842a = kVar;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        this.f13842a.a(th);
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        Integer num = (Integer) obj;
        if (num.intValue() == 0) {
            this.f13842a.a();
        } else {
            this.f13842a.a(TGException.otaEndError(num.intValue()));
        }
    }
}

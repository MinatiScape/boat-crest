package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
import com.touchgui.sdk.exception.TGException;
/* loaded from: classes12.dex */
public final class y9 implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ com.touchgui.sdk.k f13847a;

    public y9(com.touchgui.sdk.k kVar) {
        this.f13847a = kVar;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        this.f13847a.a(th);
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        Integer num = (Integer) obj;
        if (num.intValue() == 0) {
            com.touchgui.sdk.k kVar = this.f13847a;
            int i = kVar.g;
            int i2 = kVar.i;
            kVar.g = i;
            if (kVar.l) {
                kVar.a(TGException.canceled());
                return;
            }
            s3 s3Var = new s3(kVar.d, i2, i);
            s3Var.q = new z9(kVar, kVar);
            m3 m3Var = new m3(kVar.f13855a, s3Var);
            kVar.m = m3Var;
            m3Var.f13752a = 8000;
            m3Var.execute(kVar);
            return;
        }
        this.f13847a.a(TGException.otaRetryError(num.intValue()));
    }
}

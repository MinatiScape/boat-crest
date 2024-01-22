package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
import com.touchgui.sdk.exception.TGException;
/* loaded from: classes12.dex */
public final class w9 implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ com.touchgui.sdk.k f13839a;

    public w9(com.touchgui.sdk.k kVar) {
        this.f13839a = kVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Integer num) {
        com.touchgui.sdk.k kVar = this.f13839a;
        int intValue = num != null ? num.intValue() : 10;
        kVar.g = intValue;
        if (kVar.l) {
            kVar.a(TGException.canceled());
            return;
        }
        s3 s3Var = new s3(kVar.d, 0, intValue);
        s3Var.q = new z9(kVar, kVar);
        m3 m3Var = new m3(kVar.f13855a, s3Var);
        kVar.m = m3Var;
        m3Var.f13752a = 8000;
        m3Var.execute(kVar);
    }

    @Override // com.touchgui.sdk.TGCallback
    /* renamed from: b */
    public final void onSuccess(final Integer num) {
        this.f13839a.b.post(new Runnable() { // from class: com.touchgui.sdk.internal.wc
            @Override // java.lang.Runnable
            public final void run() {
                w9.this.a(num);
            }
        });
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        this.f13839a.a(th);
    }
}

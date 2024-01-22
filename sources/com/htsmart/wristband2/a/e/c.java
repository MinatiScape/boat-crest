package com.htsmart.wristband2.a.e;

import io.reactivex.ObservableEmitter;
/* loaded from: classes11.dex */
public class c extends com.htsmart.wristband2.a.a.f {
    public c(com.htsmart.wristband2.a.d.c cVar) {
        super(cVar);
    }

    @Override // com.htsmart.wristband2.a.a.f
    public void a(ObservableEmitter observableEmitter, com.htsmart.wristband2.a.a.m mVar) throws Throwable {
        try {
            this.f11933a.a(b.f0(), mVar);
            mVar.c();
            observableEmitter.onComplete();
        } catch (Exception e) {
            mVar.c();
            observableEmitter.tryOnError(e);
        }
    }
}

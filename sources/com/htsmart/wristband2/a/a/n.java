package com.htsmart.wristband2.a.a;

import io.reactivex.ObservableEmitter;
/* loaded from: classes11.dex */
public class n<T> extends o<T, T> {
    public n(ObservableEmitter<T> observableEmitter, m mVar) {
        super(observableEmitter, mVar);
    }

    @Override // io.reactivex.Observer
    public void onNext(T t) {
        this.b.onNext(t);
    }
}

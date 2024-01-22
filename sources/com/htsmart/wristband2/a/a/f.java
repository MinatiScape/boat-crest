package com.htsmart.wristband2.a.a;

import com.htsmart.wristband2.utils.WristbandLog;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
/* loaded from: classes11.dex */
public abstract class f<T> implements b<T> {

    /* renamed from: a  reason: collision with root package name */
    public com.htsmart.wristband2.a.d.c f11933a;

    /* loaded from: classes11.dex */
    public class a implements ObservableOnSubscribe<T> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ m f11934a;

        public a(m mVar) {
            this.f11934a = mVar;
        }

        @Override // io.reactivex.ObservableOnSubscribe
        public void subscribe(ObservableEmitter<T> observableEmitter) {
            try {
                f.this.a(observableEmitter, this.f11934a);
            } catch (Throwable th) {
                observableEmitter.tryOnError(th);
                WristbandLog.w(th, "QueueOperation terminated with an unexpected exception", new Object[0]);
            }
        }
    }

    public f(com.htsmart.wristband2.a.d.c cVar) {
        this.f11933a = cVar;
    }

    @Override // com.htsmart.wristband2.a.a.b
    public final Observable<T> a(m mVar) {
        return Observable.create(new a(mVar));
    }

    public abstract void a(ObservableEmitter<T> observableEmitter, m mVar) throws Throwable;
}

package com.htsmart.wristband2.a.a;

import com.htsmart.wristband2.utils.WristbandLog;
import io.reactivex.ObservableEmitter;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
/* loaded from: classes11.dex */
public class r<T> {

    /* renamed from: a  reason: collision with root package name */
    public final b<T> f11936a;
    public final ObservableEmitter<T> b;

    /* loaded from: classes11.dex */
    public class a implements Runnable {
        public final /* synthetic */ m h;
        public final /* synthetic */ Scheduler i;

        /* renamed from: com.htsmart.wristband2.a.a.r$a$a  reason: collision with other inner class name */
        /* loaded from: classes11.dex */
        public class C0554a implements Observer<T> {
            public C0554a() {
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                r.this.b.onComplete();
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                r.this.b.tryOnError(th);
            }

            @Override // io.reactivex.Observer
            public void onNext(T t) {
                r.this.b.onNext(t);
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                r.this.b.setDisposable(disposable);
            }
        }

        public a(m mVar, Scheduler scheduler) {
            this.h = mVar;
            this.i = scheduler;
        }

        @Override // java.lang.Runnable
        public void run() {
            r.this.f11936a.a(this.h).unsubscribeOn(this.i).subscribe(new C0554a());
        }
    }

    public r(b<T> bVar, ObservableEmitter<T> observableEmitter) {
        this.f11936a = bVar;
        this.b = observableEmitter;
    }

    public void a(m mVar, Scheduler scheduler) {
        if (!this.b.isDisposed()) {
            scheduler.scheduleDirect(new a(mVar, scheduler));
            return;
        }
        WristbandLog.i("RunnableEntry", "The operation was about to be run but the observer had been already disposed: " + this.f11936a);
        mVar.c();
    }
}

package com.polidea.rxandroidble2.internal.serialization;

import androidx.annotation.NonNull;
import com.polidea.rxandroidble2.internal.logger.LoggerUtil;
import com.polidea.rxandroidble2.internal.operations.Operation;
import io.reactivex.ObservableEmitter;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes12.dex */
public class a<T> implements Comparable<a> {
    public static final AtomicLong k = new AtomicLong(0);
    public final long h = k.getAndIncrement();
    public final Operation<T> i;
    public final ObservableEmitter<T> j;

    /* renamed from: com.polidea.rxandroidble2.internal.serialization.a$a  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public class RunnableC0713a implements Runnable {
        public final /* synthetic */ c h;
        public final /* synthetic */ Scheduler i;

        /* renamed from: com.polidea.rxandroidble2.internal.serialization.a$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public class C0714a implements Observer<T> {
            public C0714a() {
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                a.this.j.onComplete();
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                a.this.j.tryOnError(th);
            }

            @Override // io.reactivex.Observer
            public void onNext(T t) {
                a.this.j.onNext(t);
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                a.this.j.setDisposable(disposable);
            }
        }

        public RunnableC0713a(c cVar, Scheduler scheduler) {
            this.h = cVar;
            this.i = scheduler;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.i.run(this.h).unsubscribeOn(this.i).subscribe(new C0714a());
        }
    }

    public a(Operation<T> operation, ObservableEmitter<T> observableEmitter) {
        this.i = operation;
        this.j = observableEmitter;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(@NonNull a aVar) {
        int compareTo = this.i.compareTo(aVar.i);
        if (compareTo != 0 || aVar.i == this.i) {
            return compareTo;
        }
        return this.h < aVar.h ? -1 : 1;
    }

    public void b(c cVar, Scheduler scheduler) {
        if (this.j.isDisposed()) {
            LoggerUtil.logOperationSkippedBecauseDisposedWhenAboutToRun(this.i);
            cVar.release();
            return;
        }
        scheduler.scheduleDirect(new RunnableC0713a(cVar, scheduler));
    }
}

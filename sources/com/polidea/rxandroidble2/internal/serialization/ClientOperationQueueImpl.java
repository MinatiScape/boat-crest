package com.polidea.rxandroidble2.internal.serialization;

import androidx.annotation.RestrictTo;
import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble2.internal.RxBleLog;
import com.polidea.rxandroidble2.internal.logger.LoggerUtil;
import com.polidea.rxandroidble2.internal.operations.Operation;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.Action;
/* loaded from: classes12.dex */
public class ClientOperationQueueImpl implements ClientOperationQueue {
    public final com.polidea.rxandroidble2.internal.serialization.b h = new com.polidea.rxandroidble2.internal.serialization.b();

    /* loaded from: classes12.dex */
    public class a implements Runnable {
        public final /* synthetic */ Scheduler h;

        public a(Scheduler scheduler) {
            this.h = scheduler;
        }

        @Override // java.lang.Runnable
        public void run() {
            while (true) {
                try {
                    com.polidea.rxandroidble2.internal.serialization.a<?> d = ClientOperationQueueImpl.this.h.d();
                    Operation<?> operation = d.i;
                    long currentTimeMillis = System.currentTimeMillis();
                    LoggerUtil.logOperationStarted(operation);
                    LoggerUtil.logOperationRunning(operation);
                    c cVar = new c();
                    d.b(cVar, this.h);
                    cVar.awaitRelease();
                    LoggerUtil.logOperationFinished(operation, currentTimeMillis, System.currentTimeMillis());
                } catch (InterruptedException e) {
                    RxBleLog.e(e, "Error while processing client operation queue", new Object[0]);
                }
            }
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes12.dex */
    public class b<T> implements ObservableOnSubscribe<T> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Operation f13492a;

        /* loaded from: classes12.dex */
        public class a implements Action {
            public final /* synthetic */ com.polidea.rxandroidble2.internal.serialization.a h;

            public a(com.polidea.rxandroidble2.internal.serialization.a aVar) {
                this.h = aVar;
            }

            @Override // io.reactivex.functions.Action
            public void run() {
                if (ClientOperationQueueImpl.this.h.c(this.h)) {
                    LoggerUtil.logOperationRemoved(b.this.f13492a);
                }
            }
        }

        public b(Operation operation) {
            this.f13492a = operation;
        }

        @Override // io.reactivex.ObservableOnSubscribe
        public void subscribe(ObservableEmitter<T> observableEmitter) {
            com.polidea.rxandroidble2.internal.serialization.a aVar = new com.polidea.rxandroidble2.internal.serialization.a(this.f13492a, observableEmitter);
            observableEmitter.setDisposable(Disposables.fromAction(new a(aVar)));
            LoggerUtil.logOperationQueued(this.f13492a);
            ClientOperationQueueImpl.this.h.a(aVar);
        }
    }

    @Inject
    public ClientOperationQueueImpl(@Named("bluetooth_interaction") Scheduler scheduler) {
        new Thread(new a(scheduler)).start();
    }

    @Override // com.polidea.rxandroidble2.internal.serialization.ClientOperationQueue
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public <T> Observable<T> queue(Operation<T> operation) {
        return Observable.create(new b(operation));
    }
}

package com.polidea.rxandroidble2.internal.serialization;

import androidx.annotation.RestrictTo;
import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble2.exceptions.BleDisconnectedException;
import com.polidea.rxandroidble2.exceptions.BleException;
import com.polidea.rxandroidble2.internal.RxBleLog;
import com.polidea.rxandroidble2.internal.connection.ConnectionScope;
import com.polidea.rxandroidble2.internal.connection.ConnectionSubscriptionWatcher;
import com.polidea.rxandroidble2.internal.connection.DisconnectionRouterOutput;
import com.polidea.rxandroidble2.internal.logger.LoggerUtil;
import com.polidea.rxandroidble2.internal.operations.Operation;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.functions.Cancellable;
import io.reactivex.observers.DisposableObserver;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
@ConnectionScope
/* loaded from: classes12.dex */
public class ConnectionOperationQueueImpl implements ConnectionOperationQueue, ConnectionSubscriptionWatcher {
    public final String h;
    public final DisconnectionRouterOutput i;
    public DisposableObserver<BleException> j;
    public final Future<?> l;
    public final com.polidea.rxandroidble2.internal.serialization.b k = new com.polidea.rxandroidble2.internal.serialization.b();
    public volatile boolean m = true;
    public BleException n = null;

    /* loaded from: classes12.dex */
    public class a implements Runnable {
        public final /* synthetic */ Scheduler h;
        public final /* synthetic */ String i;

        public a(Scheduler scheduler, String str) {
            this.h = scheduler;
            this.i = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            while (ConnectionOperationQueueImpl.this.m) {
                try {
                    com.polidea.rxandroidble2.internal.serialization.a<?> d = ConnectionOperationQueueImpl.this.k.d();
                    Operation<?> operation = d.i;
                    long currentTimeMillis = System.currentTimeMillis();
                    LoggerUtil.logOperationStarted(operation);
                    LoggerUtil.logOperationRunning(operation);
                    com.polidea.rxandroidble2.internal.serialization.c cVar = new com.polidea.rxandroidble2.internal.serialization.c();
                    d.b(cVar, this.h);
                    cVar.awaitRelease();
                    LoggerUtil.logOperationFinished(operation, currentTimeMillis, System.currentTimeMillis());
                } catch (InterruptedException e) {
                    synchronized (ConnectionOperationQueueImpl.this) {
                        if (!ConnectionOperationQueueImpl.this.m) {
                            break;
                        }
                        RxBleLog.e(e, "Error while processing connection operation queue", new Object[0]);
                    }
                }
            }
            ConnectionOperationQueueImpl.this.a();
            RxBleLog.v("Terminated (%s)", LoggerUtil.commonMacMessage(this.i));
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes12.dex */
    public class b<T> implements ObservableOnSubscribe<T> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Operation f13494a;

        /* loaded from: classes12.dex */
        public class a implements Cancellable {
            public final /* synthetic */ com.polidea.rxandroidble2.internal.serialization.a h;

            public a(com.polidea.rxandroidble2.internal.serialization.a aVar) {
                this.h = aVar;
            }

            @Override // io.reactivex.functions.Cancellable
            public void cancel() {
                if (ConnectionOperationQueueImpl.this.k.c(this.h)) {
                    LoggerUtil.logOperationRemoved(b.this.f13494a);
                }
            }
        }

        public b(Operation operation) {
            this.f13494a = operation;
        }

        @Override // io.reactivex.ObservableOnSubscribe
        public void subscribe(ObservableEmitter<T> observableEmitter) {
            com.polidea.rxandroidble2.internal.serialization.a aVar = new com.polidea.rxandroidble2.internal.serialization.a(this.f13494a, observableEmitter);
            observableEmitter.setCancellable(new a(aVar));
            LoggerUtil.logOperationQueued(this.f13494a);
            ConnectionOperationQueueImpl.this.k.a(aVar);
        }
    }

    /* loaded from: classes12.dex */
    public class c extends DisposableObserver<BleException> {
        public c() {
        }

        @Override // io.reactivex.Observer
        /* renamed from: a */
        public void onNext(BleException bleException) {
            ConnectionOperationQueueImpl.this.terminate(bleException);
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
        }
    }

    @Inject
    public ConnectionOperationQueueImpl(@Named("mac-address") String str, DisconnectionRouterOutput disconnectionRouterOutput, @Named("executor_connection_queue") ExecutorService executorService, @Named("bluetooth_interaction") Scheduler scheduler) {
        this.h = str;
        this.i = disconnectionRouterOutput;
        this.l = executorService.submit(new a(scheduler, str));
    }

    public synchronized void a() {
        while (!this.k.b()) {
            this.k.e().j.tryOnError(this.n);
        }
    }

    @Override // com.polidea.rxandroidble2.internal.connection.ConnectionSubscriptionWatcher
    public void onConnectionSubscribed() {
        this.j = (DisposableObserver) this.i.asValueOnlyObservable().subscribeWith(new c());
    }

    @Override // com.polidea.rxandroidble2.internal.connection.ConnectionSubscriptionWatcher
    public void onConnectionUnsubscribed() {
        this.j.dispose();
        this.j = null;
        terminate(new BleDisconnectedException(this.h, -1));
    }

    @Override // com.polidea.rxandroidble2.internal.serialization.ClientOperationQueue
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public synchronized <T> Observable<T> queue(Operation<T> operation) {
        if (!this.m) {
            return Observable.error(this.n);
        }
        return Observable.create(new b(operation));
    }

    @Override // com.polidea.rxandroidble2.internal.serialization.ConnectionOperationQueue
    public synchronized void terminate(BleException bleException) {
        if (this.n != null) {
            return;
        }
        RxBleLog.d(bleException, "Connection operations queue to be terminated (%s)", LoggerUtil.commonMacMessage(this.h));
        this.m = false;
        this.n = bleException;
        this.l.cancel(true);
    }
}

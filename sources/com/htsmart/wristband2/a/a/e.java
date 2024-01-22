package com.htsmart.wristband2.a.a;

import com.htsmart.wristband2.exceptions.OperationBusyException;
import com.htsmart.wristband2.utils.WristbandLog;
import com.polidea.rxandroidble2.exceptions.BleDisconnectedException;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.functions.Cancellable;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/* loaded from: classes11.dex */
public class e implements d {
    public String d;
    public boolean e;
    public Future<?> f;
    public BleDisconnectedException g = new BleDisconnectedException();

    /* renamed from: a  reason: collision with root package name */
    public ExecutorService f11931a = Executors.newSingleThreadExecutor();
    public Scheduler b = Schedulers.newThread();
    public ArrayBlockingQueue<r> c = new ArrayBlockingQueue<>(100);

    /* loaded from: classes11.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            WristbandLog.i("Operation queue begin, mStarted=%b.", Boolean.valueOf(e.this.e));
            while (e.this.e) {
                try {
                    r rVar = (r) e.this.c.take();
                    com.htsmart.wristband2.a.a.b<T> bVar = rVar.f11936a;
                    long currentTimeMillis = System.currentTimeMillis();
                    c.d(bVar);
                    m mVar = new m();
                    rVar.a(mVar, e.this.b);
                    mVar.a();
                    c.a(bVar, currentTimeMillis, System.currentTimeMillis());
                } catch (InterruptedException e) {
                    WristbandLog.w(e, "Error while processing connection operation queue", new Object[0]);
                    synchronized (e.this) {
                        if (!e.this.e) {
                            break;
                        }
                    }
                }
            }
            e.this.d();
            WristbandLog.i("Operation queue terminated.", new Object[0]);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes11.dex */
    public class b<T> implements ObservableOnSubscribe<T> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ com.htsmart.wristband2.a.a.b f11932a;

        /* loaded from: classes11.dex */
        public class a implements Cancellable {
            public final /* synthetic */ r h;

            public a(r rVar) {
                this.h = rVar;
            }

            @Override // io.reactivex.functions.Cancellable
            public void cancel() throws Exception {
                if (e.this.c.remove(this.h)) {
                    c.c(b.this.f11932a);
                }
            }
        }

        public b(com.htsmart.wristband2.a.a.b bVar) {
            this.f11932a = bVar;
        }

        @Override // io.reactivex.ObservableOnSubscribe
        public void subscribe(ObservableEmitter<T> observableEmitter) throws Exception {
            Throwable th;
            r rVar = new r(this.f11932a, observableEmitter);
            observableEmitter.setCancellable(new a(rVar));
            c.a(this.f11932a);
            synchronized (e.this) {
                if (!e.this.e) {
                    c.b(this.f11932a);
                    th = e.this.g;
                } else if (!e.this.c.offer(rVar)) {
                    c.b(this.f11932a);
                    th = new OperationBusyException(this.f11932a);
                }
                observableEmitter.tryOnError(th);
            }
        }
    }

    @Override // com.htsmart.wristband2.a.a.d
    public synchronized <T> Observable<T> a(com.htsmart.wristband2.a.a.b<T> bVar) {
        if (this.e) {
            return Observable.create(new b(bVar));
        }
        return Observable.error(this.g);
    }

    @Override // com.htsmart.wristband2.a.a.d
    public synchronized void a() {
        if (!this.e) {
            WristbandLog.i("Operation queue already stop!!!", new Object[0]);
            return;
        }
        WristbandLog.i("Stop operation queue!!!", new Object[0]);
        this.e = false;
        this.g = new BleDisconnectedException(this.d);
        this.f.cancel(true);
    }

    @Override // com.htsmart.wristband2.a.a.d
    public synchronized void a(String str) {
        if (this.e) {
            WristbandLog.w("Operation queue already started!!!", new Object[0]);
            return;
        }
        WristbandLog.w("Start operation queue!!!", new Object[0]);
        this.d = str;
        this.e = true;
        Future<?> future = this.f;
        if (future == null) {
            WristbandLog.w("Previous mRunnableFuture = null", new Object[0]);
        } else {
            WristbandLog.w("Previous mRunnableFuture = %s , isDone:%b , isCancelled:%b", future.toString(), Boolean.valueOf(this.f.isDone()), Boolean.valueOf(this.f.isCancelled()));
        }
        this.f = this.f11931a.submit(new a());
    }

    public final synchronized void d() {
        while (!this.c.isEmpty()) {
            this.c.poll().b.tryOnError(this.g);
        }
    }
}

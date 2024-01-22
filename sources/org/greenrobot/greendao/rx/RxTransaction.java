package org.greenrobot.greendao.rx;

import java.util.concurrent.Callable;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.annotation.apihint.Experimental;
import rx.Observable;
import rx.Scheduler;
@Experimental
/* loaded from: classes13.dex */
public class RxTransaction extends org.greenrobot.greendao.rx.a {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractDaoSession f15493a;

    /* loaded from: classes13.dex */
    public class a implements Callable<Void> {
        public final /* synthetic */ Runnable h;

        public a(Runnable runnable) {
            this.h = runnable;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() throws Exception {
            RxTransaction.this.f15493a.runInTx(this.h);
            return null;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes13.dex */
    public class b<T> implements Callable<T> {
        public final /* synthetic */ Callable h;

        public b(Callable callable) {
            this.h = callable;
        }

        @Override // java.util.concurrent.Callable
        public T call() throws Exception {
            return (T) RxTransaction.this.f15493a.callInTx(this.h);
        }
    }

    public RxTransaction(AbstractDaoSession abstractDaoSession) {
        this.f15493a = abstractDaoSession;
    }

    @Experimental
    public <T> Observable<T> call(Callable<T> callable) {
        return wrap(new b(callable));
    }

    @Experimental
    public AbstractDaoSession getDaoSession() {
        return this.f15493a;
    }

    @Override // org.greenrobot.greendao.rx.a
    @Experimental
    public /* bridge */ /* synthetic */ Scheduler getScheduler() {
        return super.getScheduler();
    }

    @Experimental
    public Observable<Void> run(Runnable runnable) {
        return wrap(new a(runnable));
    }

    public RxTransaction(AbstractDaoSession abstractDaoSession, Scheduler scheduler) {
        super(scheduler);
        this.f15493a = abstractDaoSession;
    }
}

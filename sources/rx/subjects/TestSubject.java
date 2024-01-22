package rx.subjects;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Scheduler;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.internal.operators.NotificationLite;
import rx.schedulers.TestScheduler;
import rx.subjects.SubjectSubscriptionManager;
/* loaded from: classes13.dex */
public final class TestSubject<T> extends Subject<T, T> {
    public final SubjectSubscriptionManager<T> i;
    public final Scheduler.Worker j;

    /* loaded from: classes13.dex */
    public static class a implements Action1<SubjectSubscriptionManager.SubjectObserver<T>> {
        public final /* synthetic */ SubjectSubscriptionManager h;

        public a(SubjectSubscriptionManager subjectSubscriptionManager) {
            this.h = subjectSubscriptionManager;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(SubjectSubscriptionManager.SubjectObserver<T> subjectObserver) {
            subjectObserver.b(this.h.getLatest());
        }
    }

    /* loaded from: classes13.dex */
    public class b implements Action0 {
        public b() {
        }

        @Override // rx.functions.Action0
        public void call() {
            TestSubject.this.c();
        }
    }

    /* loaded from: classes13.dex */
    public class c implements Action0 {
        public final /* synthetic */ Throwable h;

        public c(Throwable th) {
            this.h = th;
        }

        @Override // rx.functions.Action0
        public void call() {
            TestSubject.this.d(this.h);
        }
    }

    /* loaded from: classes13.dex */
    public class d implements Action0 {
        public final /* synthetic */ Object h;

        public d(Object obj) {
            this.h = obj;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // rx.functions.Action0
        public void call() {
            TestSubject.this.e(this.h);
        }
    }

    public TestSubject(Observable.OnSubscribe<T> onSubscribe, SubjectSubscriptionManager<T> subjectSubscriptionManager, TestScheduler testScheduler) {
        super(onSubscribe);
        this.i = subjectSubscriptionManager;
        this.j = testScheduler.createWorker();
    }

    public static <T> TestSubject<T> create(TestScheduler testScheduler) {
        SubjectSubscriptionManager subjectSubscriptionManager = new SubjectSubscriptionManager();
        a aVar = new a(subjectSubscriptionManager);
        subjectSubscriptionManager.onAdded = aVar;
        subjectSubscriptionManager.onTerminated = aVar;
        return new TestSubject<>(subjectSubscriptionManager, subjectSubscriptionManager, testScheduler);
    }

    public void c() {
        SubjectSubscriptionManager<T> subjectSubscriptionManager = this.i;
        if (subjectSubscriptionManager.active) {
            for (SubjectSubscriptionManager.SubjectObserver<T> subjectObserver : subjectSubscriptionManager.terminate(NotificationLite.completed())) {
                subjectObserver.onCompleted();
            }
        }
    }

    public void d(Throwable th) {
        SubjectSubscriptionManager<T> subjectSubscriptionManager = this.i;
        if (subjectSubscriptionManager.active) {
            for (SubjectSubscriptionManager.SubjectObserver<T> subjectObserver : subjectSubscriptionManager.terminate(NotificationLite.error(th))) {
                subjectObserver.onError(th);
            }
        }
    }

    public void e(T t) {
        for (SubjectSubscriptionManager.SubjectObserver<T> subjectObserver : this.i.observers()) {
            subjectObserver.onNext(t);
        }
    }

    @Override // rx.subjects.Subject
    public boolean hasObservers() {
        return this.i.observers().length > 0;
    }

    @Override // rx.Observer
    public void onCompleted() {
        onCompleted(0L);
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        onError(th, 0L);
    }

    @Override // rx.Observer
    public void onNext(T t) {
        onNext(t, 0L);
    }

    public void onCompleted(long j) {
        this.j.schedule(new b(), j, TimeUnit.MILLISECONDS);
    }

    public void onError(Throwable th, long j) {
        this.j.schedule(new c(th), j, TimeUnit.MILLISECONDS);
    }

    public void onNext(T t, long j) {
        this.j.schedule(new d(t), j, TimeUnit.MILLISECONDS);
    }
}

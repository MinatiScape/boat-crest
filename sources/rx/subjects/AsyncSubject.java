package rx.subjects;

import java.util.ArrayList;
import rx.Observable;
import rx.exceptions.Exceptions;
import rx.functions.Action1;
import rx.internal.operators.NotificationLite;
import rx.internal.producers.SingleProducer;
import rx.subjects.SubjectSubscriptionManager;
/* loaded from: classes13.dex */
public final class AsyncSubject<T> extends Subject<T, T> {
    public final SubjectSubscriptionManager<T> i;
    public volatile Object j;

    /* loaded from: classes13.dex */
    public static class a implements Action1<SubjectSubscriptionManager.SubjectObserver<T>> {
        public final /* synthetic */ SubjectSubscriptionManager h;

        public a(SubjectSubscriptionManager subjectSubscriptionManager) {
            this.h = subjectSubscriptionManager;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(SubjectSubscriptionManager.SubjectObserver<T> subjectObserver) {
            Object latest = this.h.getLatest();
            if (latest != null && !NotificationLite.isCompleted(latest)) {
                if (NotificationLite.isError(latest)) {
                    subjectObserver.onError(NotificationLite.getError(latest));
                    return;
                } else {
                    subjectObserver.h.setProducer(new SingleProducer(subjectObserver.h, NotificationLite.getValue(latest)));
                    return;
                }
            }
            subjectObserver.onCompleted();
        }
    }

    public AsyncSubject(Observable.OnSubscribe<T> onSubscribe, SubjectSubscriptionManager<T> subjectSubscriptionManager) {
        super(onSubscribe);
        this.i = subjectSubscriptionManager;
    }

    public static <T> AsyncSubject<T> create() {
        SubjectSubscriptionManager subjectSubscriptionManager = new SubjectSubscriptionManager();
        subjectSubscriptionManager.onTerminated = new a(subjectSubscriptionManager);
        return new AsyncSubject<>(subjectSubscriptionManager, subjectSubscriptionManager);
    }

    public Throwable getThrowable() {
        Object latest = this.i.getLatest();
        if (NotificationLite.isError(latest)) {
            return NotificationLite.getError(latest);
        }
        return null;
    }

    public T getValue() {
        Object obj = this.j;
        if (NotificationLite.isError(this.i.getLatest()) || !NotificationLite.isNext(obj)) {
            return null;
        }
        return (T) NotificationLite.getValue(obj);
    }

    public boolean hasCompleted() {
        Object latest = this.i.getLatest();
        return (latest == null || NotificationLite.isError(latest)) ? false : true;
    }

    @Override // rx.subjects.Subject
    public boolean hasObservers() {
        return this.i.observers().length > 0;
    }

    public boolean hasThrowable() {
        return NotificationLite.isError(this.i.getLatest());
    }

    public boolean hasValue() {
        return !NotificationLite.isError(this.i.getLatest()) && NotificationLite.isNext(this.j);
    }

    @Override // rx.Observer
    public void onCompleted() {
        SubjectSubscriptionManager.SubjectObserver<T>[] terminate;
        if (this.i.active) {
            Object obj = this.j;
            if (obj == null) {
                obj = NotificationLite.completed();
            }
            for (SubjectSubscriptionManager.SubjectObserver<T> subjectObserver : this.i.terminate(obj)) {
                if (obj == NotificationLite.completed()) {
                    subjectObserver.onCompleted();
                } else {
                    subjectObserver.h.setProducer(new SingleProducer(subjectObserver.h, NotificationLite.getValue(obj)));
                }
            }
        }
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        if (this.i.active) {
            ArrayList arrayList = null;
            for (SubjectSubscriptionManager.SubjectObserver<T> subjectObserver : this.i.terminate(NotificationLite.error(th))) {
                try {
                    subjectObserver.onError(th);
                } catch (Throwable th2) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(th2);
                }
            }
            Exceptions.throwIfAny(arrayList);
        }
    }

    @Override // rx.Observer
    public void onNext(T t) {
        this.j = NotificationLite.next(t);
    }
}

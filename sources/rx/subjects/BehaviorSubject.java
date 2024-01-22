package rx.subjects;

import java.lang.reflect.Array;
import java.util.ArrayList;
import rx.Observable;
import rx.exceptions.Exceptions;
import rx.functions.Action1;
import rx.internal.operators.NotificationLite;
import rx.subjects.SubjectSubscriptionManager;
/* loaded from: classes13.dex */
public final class BehaviorSubject<T> extends Subject<T, T> {
    public static final Object[] j = new Object[0];
    public final SubjectSubscriptionManager<T> i;

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

    public BehaviorSubject(Observable.OnSubscribe<T> onSubscribe, SubjectSubscriptionManager<T> subjectSubscriptionManager) {
        super(onSubscribe);
        this.i = subjectSubscriptionManager;
    }

    public static <T> BehaviorSubject<T> c(T t, boolean z) {
        SubjectSubscriptionManager subjectSubscriptionManager = new SubjectSubscriptionManager();
        if (z) {
            subjectSubscriptionManager.setLatest(NotificationLite.next(t));
        }
        a aVar = new a(subjectSubscriptionManager);
        subjectSubscriptionManager.onAdded = aVar;
        subjectSubscriptionManager.onTerminated = aVar;
        return new BehaviorSubject<>(subjectSubscriptionManager, subjectSubscriptionManager);
    }

    public static <T> BehaviorSubject<T> create() {
        return c(null, false);
    }

    public Throwable getThrowable() {
        Object latest = this.i.getLatest();
        if (NotificationLite.isError(latest)) {
            return NotificationLite.getError(latest);
        }
        return null;
    }

    public T getValue() {
        Object latest = this.i.getLatest();
        if (NotificationLite.isNext(latest)) {
            return (T) NotificationLite.getValue(latest);
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public T[] getValues(T[] tArr) {
        Object latest = this.i.getLatest();
        if (NotificationLite.isNext(latest)) {
            if (tArr.length == 0) {
                tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), 1));
            }
            tArr[0] = NotificationLite.getValue(latest);
            if (tArr.length > 1) {
                tArr[1] = null;
            }
        } else if (tArr.length > 0) {
            tArr[0] = null;
        }
        return tArr;
    }

    public boolean hasCompleted() {
        return NotificationLite.isCompleted(this.i.getLatest());
    }

    @Override // rx.subjects.Subject
    public boolean hasObservers() {
        return this.i.observers().length > 0;
    }

    public boolean hasThrowable() {
        return NotificationLite.isError(this.i.getLatest());
    }

    public boolean hasValue() {
        return NotificationLite.isNext(this.i.getLatest());
    }

    @Override // rx.Observer
    public void onCompleted() {
        if (this.i.getLatest() == null || this.i.active) {
            Object completed = NotificationLite.completed();
            for (SubjectSubscriptionManager.SubjectObserver<T> subjectObserver : this.i.terminate(completed)) {
                subjectObserver.d(completed);
            }
        }
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        if (this.i.getLatest() == null || this.i.active) {
            Object error = NotificationLite.error(th);
            ArrayList arrayList = null;
            for (SubjectSubscriptionManager.SubjectObserver<T> subjectObserver : this.i.terminate(error)) {
                try {
                    subjectObserver.d(error);
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
        if (this.i.getLatest() == null || this.i.active) {
            Object next = NotificationLite.next(t);
            for (SubjectSubscriptionManager.SubjectObserver<T> subjectObserver : this.i.next(next)) {
                subjectObserver.d(next);
            }
        }
    }

    public static <T> BehaviorSubject<T> create(T t) {
        return c(t, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Object[] getValues() {
        Object[] objArr = j;
        Object[] values = getValues(objArr);
        return values == objArr ? new Object[0] : values;
    }
}

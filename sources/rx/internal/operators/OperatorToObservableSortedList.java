package rx.internal.operators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func2;
import rx.internal.producers.SingleDelayedProducer;
/* loaded from: classes13.dex */
public final class OperatorToObservableSortedList<T> implements Observable.Operator<List<T>, T> {
    public static final Comparator j = new c();
    public final Comparator<? super T> h;
    public final int i;

    /* loaded from: classes13.dex */
    public class a implements Comparator<T> {
        public final /* synthetic */ Func2 h;

        public a(OperatorToObservableSortedList operatorToObservableSortedList, Func2 func2) {
            this.h = func2;
        }

        @Override // java.util.Comparator
        public int compare(T t, T t2) {
            return ((Integer) this.h.call(t, t2)).intValue();
        }
    }

    /* loaded from: classes13.dex */
    public class b extends Subscriber<T> {
        public List<T> l;
        public boolean m;
        public final /* synthetic */ SingleDelayedProducer n;
        public final /* synthetic */ Subscriber o;

        public b(SingleDelayedProducer singleDelayedProducer, Subscriber subscriber) {
            this.n = singleDelayedProducer;
            this.o = subscriber;
            this.l = new ArrayList(OperatorToObservableSortedList.this.i);
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.m) {
                return;
            }
            this.m = true;
            List<T> list = this.l;
            this.l = null;
            try {
                Collections.sort(list, OperatorToObservableSortedList.this.h);
                this.n.setValue(list);
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, this);
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.o.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (this.m) {
                return;
            }
            this.l.add(t);
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void onStart() {
            request(Long.MAX_VALUE);
        }
    }

    /* loaded from: classes13.dex */
    public static final class c implements Comparator<Object> {
        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            return ((Comparable) obj).compareTo((Comparable) obj2);
        }
    }

    public OperatorToObservableSortedList(int i) {
        this.h = j;
        this.i = i;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super List<T>> subscriber) {
        SingleDelayedProducer singleDelayedProducer = new SingleDelayedProducer(subscriber);
        b bVar = new b(singleDelayedProducer, subscriber);
        subscriber.add(bVar);
        subscriber.setProducer(singleDelayedProducer);
        return bVar;
    }

    public OperatorToObservableSortedList(Func2<? super T, ? super T, Integer> func2, int i) {
        this.i = i;
        this.h = new a(this, func2);
    }
}

package rx.internal.operators;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.internal.producers.SingleDelayedProducer;
/* loaded from: classes13.dex */
public final class OperatorToObservableList<T> implements Observable.Operator<List<T>, T> {

    /* loaded from: classes13.dex */
    public class a extends Subscriber<T> {
        public boolean l;
        public List<T> m = new LinkedList();
        public final /* synthetic */ SingleDelayedProducer n;
        public final /* synthetic */ Subscriber o;

        public a(OperatorToObservableList operatorToObservableList, SingleDelayedProducer singleDelayedProducer, Subscriber subscriber) {
            this.n = singleDelayedProducer;
            this.o = subscriber;
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.l) {
                return;
            }
            this.l = true;
            try {
                ArrayList arrayList = new ArrayList(this.m);
                this.m = null;
                this.n.setValue(arrayList);
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
            if (this.l) {
                return;
            }
            this.m.add(t);
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void onStart() {
            request(Long.MAX_VALUE);
        }
    }

    /* loaded from: classes13.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        public static final OperatorToObservableList<Object> f15675a = new OperatorToObservableList<>();
    }

    public static <T> OperatorToObservableList<T> instance() {
        return (OperatorToObservableList<T>) b.f15675a;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super List<T>> subscriber) {
        SingleDelayedProducer singleDelayedProducer = new SingleDelayedProducer(subscriber);
        a aVar = new a(this, singleDelayedProducer, subscriber);
        subscriber.add(aVar);
        subscriber.setProducer(singleDelayedProducer);
        return aVar;
    }
}

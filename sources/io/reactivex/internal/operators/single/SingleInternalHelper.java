package io.reactivex.internal.operators.single;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.Callable;
import org.reactivestreams.Publisher;
/* loaded from: classes12.dex */
public final class SingleInternalHelper {

    /* loaded from: classes12.dex */
    public enum a implements Callable<NoSuchElementException> {
        INSTANCE;

        @Override // java.util.concurrent.Callable
        public NoSuchElementException call() throws Exception {
            return new NoSuchElementException();
        }
    }

    /* loaded from: classes12.dex */
    public enum b implements Function<SingleSource, Publisher> {
        INSTANCE;

        @Override // io.reactivex.functions.Function
        public Publisher apply(SingleSource singleSource) {
            return new SingleToFlowable(singleSource);
        }
    }

    /* loaded from: classes12.dex */
    public static final class c<T> implements Iterable<Flowable<T>> {
        public final Iterable<? extends SingleSource<? extends T>> h;

        public c(Iterable<? extends SingleSource<? extends T>> iterable) {
            this.h = iterable;
        }

        @Override // java.lang.Iterable
        public Iterator<Flowable<T>> iterator() {
            return new d(this.h.iterator());
        }
    }

    /* loaded from: classes12.dex */
    public static final class d<T> implements Iterator<Flowable<T>> {
        public final Iterator<? extends SingleSource<? extends T>> h;

        public d(Iterator<? extends SingleSource<? extends T>> it) {
            this.h = it;
        }

        @Override // java.util.Iterator
        /* renamed from: a */
        public Flowable<T> next() {
            return new SingleToFlowable(this.h.next());
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.h.hasNext();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* loaded from: classes12.dex */
    public enum e implements Function<SingleSource, Observable> {
        INSTANCE;

        @Override // io.reactivex.functions.Function
        public Observable apply(SingleSource singleSource) {
            return new SingleToObservable(singleSource);
        }
    }

    public SingleInternalHelper() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Callable<NoSuchElementException> emptyThrower() {
        return a.INSTANCE;
    }

    public static <T> Iterable<? extends Flowable<T>> iterableToFlowable(Iterable<? extends SingleSource<? extends T>> iterable) {
        return new c(iterable);
    }

    public static <T> Function<SingleSource<? extends T>, Publisher<? extends T>> toFlowable() {
        return b.INSTANCE;
    }

    public static <T> Function<SingleSource<? extends T>, Observable<? extends T>> toObservable() {
        return e.INSTANCE;
    }
}

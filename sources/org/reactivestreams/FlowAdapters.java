package org.reactivestreams;

import java.util.Objects;
import java.util.concurrent.Flow;
/* loaded from: classes13.dex */
public final class FlowAdapters {

    /* loaded from: classes13.dex */
    public static final class a<T> implements Flow.Publisher<T> {

        /* renamed from: a  reason: collision with root package name */
        public final Publisher<? extends T> f15565a;

        public a(Publisher<? extends T> publisher) {
            this.f15565a = publisher;
        }

        @Override // java.util.concurrent.Flow.Publisher
        public void subscribe(Flow.Subscriber<? super T> subscriber) {
            this.f15565a.subscribe(subscriber == null ? null : new g(subscriber));
        }
    }

    /* loaded from: classes13.dex */
    public static final class b<T, U> implements Flow.Processor<T, U> {

        /* renamed from: a  reason: collision with root package name */
        public final Processor<? super T, ? extends U> f15566a;

        public b(Processor<? super T, ? extends U> processor) {
            this.f15566a = processor;
        }

        @Override // java.util.concurrent.Flow.Subscriber
        public void onComplete() {
            this.f15566a.onComplete();
        }

        @Override // java.util.concurrent.Flow.Subscriber
        public void onError(Throwable th) {
            this.f15566a.onError(th);
        }

        @Override // java.util.concurrent.Flow.Subscriber
        public void onNext(T t) {
            this.f15566a.onNext(t);
        }

        @Override // java.util.concurrent.Flow.Subscriber
        public void onSubscribe(Flow.Subscription subscription) {
            this.f15566a.onSubscribe(subscription == null ? null : new h(subscription));
        }

        @Override // java.util.concurrent.Flow.Publisher
        public void subscribe(Flow.Subscriber<? super U> subscriber) {
            this.f15566a.subscribe(subscriber == null ? null : new g(subscriber));
        }
    }

    /* loaded from: classes13.dex */
    public static final class c<T> implements Flow.Subscriber<T> {

        /* renamed from: a  reason: collision with root package name */
        public final Subscriber<? super T> f15567a;

        public c(Subscriber<? super T> subscriber) {
            this.f15567a = subscriber;
        }

        @Override // java.util.concurrent.Flow.Subscriber
        public void onComplete() {
            this.f15567a.onComplete();
        }

        @Override // java.util.concurrent.Flow.Subscriber
        public void onError(Throwable th) {
            this.f15567a.onError(th);
        }

        @Override // java.util.concurrent.Flow.Subscriber
        public void onNext(T t) {
            this.f15567a.onNext(t);
        }

        @Override // java.util.concurrent.Flow.Subscriber
        public void onSubscribe(Flow.Subscription subscription) {
            this.f15567a.onSubscribe(subscription == null ? null : new h(subscription));
        }
    }

    /* loaded from: classes13.dex */
    public static final class d implements Flow.Subscription {

        /* renamed from: a  reason: collision with root package name */
        public final Subscription f15568a;

        public d(Subscription subscription) {
            this.f15568a = subscription;
        }

        @Override // java.util.concurrent.Flow.Subscription
        public void cancel() {
            this.f15568a.cancel();
        }

        @Override // java.util.concurrent.Flow.Subscription
        public void request(long j) {
            this.f15568a.request(j);
        }
    }

    /* loaded from: classes13.dex */
    public static final class e<T> implements Publisher<T> {
        public final Flow.Publisher<? extends T> h;

        public e(Flow.Publisher<? extends T> publisher) {
            this.h = publisher;
        }

        @Override // org.reactivestreams.Publisher
        public void subscribe(Subscriber<? super T> subscriber) {
            this.h.subscribe(subscriber == null ? null : new c(subscriber));
        }
    }

    /* loaded from: classes13.dex */
    public static final class f<T, U> implements Processor<T, U> {
        public final Flow.Processor<? super T, ? extends U> h;

        public f(Flow.Processor<? super T, ? extends U> processor) {
            this.h = processor;
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.h.onComplete();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            this.h.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            this.h.onNext(t);
        }

        @Override // org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            this.h.onSubscribe(subscription == null ? null : new d(subscription));
        }

        @Override // org.reactivestreams.Publisher
        public void subscribe(Subscriber<? super U> subscriber) {
            this.h.subscribe(subscriber == null ? null : new c(subscriber));
        }
    }

    /* loaded from: classes13.dex */
    public static final class g<T> implements Subscriber<T> {
        public final Flow.Subscriber<? super T> h;

        public g(Flow.Subscriber<? super T> subscriber) {
            this.h = subscriber;
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.h.onComplete();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            this.h.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            this.h.onNext(t);
        }

        @Override // org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            this.h.onSubscribe(subscription == null ? null : new d(subscription));
        }
    }

    /* loaded from: classes13.dex */
    public static final class h implements Subscription {
        public final Flow.Subscription h;

        public h(Flow.Subscription subscription) {
            this.h = subscription;
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.h.cancel();
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            this.h.request(j);
        }
    }

    public FlowAdapters() {
        throw new IllegalStateException("No instances!");
    }

    public static <T, U> Flow.Processor<T, U> toFlowProcessor(Processor<? super T, ? extends U> processor) {
        Objects.requireNonNull(processor, "reactiveStreamsProcessor");
        if (processor instanceof f) {
            return (Flow.Processor<? super T, ? extends U>) ((f) processor).h;
        }
        if (processor instanceof Flow.Processor) {
            return (Flow.Processor) processor;
        }
        return new b(processor);
    }

    public static <T> Flow.Publisher<T> toFlowPublisher(Publisher<? extends T> publisher) {
        Objects.requireNonNull(publisher, "reactiveStreamsPublisher");
        if (publisher instanceof e) {
            return (Flow.Publisher<? extends T>) ((e) publisher).h;
        }
        if (publisher instanceof Flow.Publisher) {
            return (Flow.Publisher) publisher;
        }
        return new a(publisher);
    }

    public static <T> Flow.Subscriber<T> toFlowSubscriber(Subscriber<T> subscriber) {
        Objects.requireNonNull(subscriber, "reactiveStreamsSubscriber");
        if (subscriber instanceof g) {
            return (Flow.Subscriber<? super T>) ((g) subscriber).h;
        }
        if (subscriber instanceof Flow.Subscriber) {
            return (Flow.Subscriber) subscriber;
        }
        return new c(subscriber);
    }

    public static <T, U> Processor<T, U> toProcessor(Flow.Processor<? super T, ? extends U> processor) {
        Objects.requireNonNull(processor, "flowProcessor");
        if (processor instanceof b) {
            return (Processor<? super T, ? extends U>) ((b) processor).f15566a;
        }
        if (processor instanceof Processor) {
            return (Processor) processor;
        }
        return new f(processor);
    }

    public static <T> Publisher<T> toPublisher(Flow.Publisher<? extends T> publisher) {
        Objects.requireNonNull(publisher, "flowPublisher");
        if (publisher instanceof a) {
            return (Publisher<? extends T>) ((a) publisher).f15565a;
        }
        if (publisher instanceof Publisher) {
            return (Publisher) publisher;
        }
        return new e(publisher);
    }

    public static <T> Subscriber<T> toSubscriber(Flow.Subscriber<T> subscriber) {
        Objects.requireNonNull(subscriber, "flowSubscriber");
        if (subscriber instanceof c) {
            return (Subscriber<? super T>) ((c) subscriber).f15567a;
        }
        if (subscriber instanceof Subscriber) {
            return (Subscriber) subscriber;
        }
        return new g(subscriber);
    }
}

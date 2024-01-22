package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.annotations.Nullable;
import io.reactivex.functions.BiPredicate;
import io.reactivex.functions.Function;
import io.reactivex.internal.fuseable.ConditionalSubscriber;
import io.reactivex.internal.subscribers.BasicFuseableConditionalSubscriber;
import io.reactivex.internal.subscribers.BasicFuseableSubscriber;
import org.reactivestreams.Subscriber;
/* loaded from: classes12.dex */
public final class FlowableDistinctUntilChanged<T, K> extends io.reactivex.internal.operators.flowable.a<T, T> {
    public final Function<? super T, K> i;
    public final BiPredicate<? super K, ? super K> j;

    /* loaded from: classes12.dex */
    public static final class a<T, K> extends BasicFuseableConditionalSubscriber<T, T> {
        public final Function<? super T, K> h;
        public final BiPredicate<? super K, ? super K> i;
        public K j;
        public boolean k;

        public a(ConditionalSubscriber<? super T> conditionalSubscriber, Function<? super T, K> function, BiPredicate<? super K, ? super K> biPredicate) {
            super(conditionalSubscriber);
            this.h = function;
            this.i = biPredicate;
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (tryOnNext(t)) {
                return;
            }
            this.upstream.request(1L);
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        @Nullable
        public T poll() throws Exception {
            while (true) {
                T poll = this.qs.poll();
                if (poll == null) {
                    return null;
                }
                K apply = this.h.apply(poll);
                if (!this.k) {
                    this.k = true;
                    this.j = apply;
                    return poll;
                } else if (!this.i.test((K) this.j, apply)) {
                    this.j = apply;
                    return poll;
                } else {
                    this.j = apply;
                    if (this.sourceMode != 1) {
                        this.upstream.request(1L);
                    }
                }
            }
        }

        @Override // io.reactivex.internal.fuseable.QueueFuseable
        public int requestFusion(int i) {
            return transitiveBoundaryFusion(i);
        }

        @Override // io.reactivex.internal.fuseable.ConditionalSubscriber
        public boolean tryOnNext(T t) {
            if (this.done) {
                return false;
            }
            if (this.sourceMode != 0) {
                return this.downstream.tryOnNext(t);
            }
            try {
                K apply = this.h.apply(t);
                if (this.k) {
                    boolean test = this.i.test((K) this.j, apply);
                    this.j = apply;
                    if (test) {
                        return false;
                    }
                } else {
                    this.k = true;
                    this.j = apply;
                }
                this.downstream.onNext(t);
                return true;
            } catch (Throwable th) {
                fail(th);
                return true;
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class b<T, K> extends BasicFuseableSubscriber<T, T> implements ConditionalSubscriber<T> {
        public final Function<? super T, K> h;
        public final BiPredicate<? super K, ? super K> i;
        public K j;
        public boolean k;

        public b(Subscriber<? super T> subscriber, Function<? super T, K> function, BiPredicate<? super K, ? super K> biPredicate) {
            super(subscriber);
            this.h = function;
            this.i = biPredicate;
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (tryOnNext(t)) {
                return;
            }
            this.upstream.request(1L);
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        @Nullable
        public T poll() throws Exception {
            while (true) {
                T poll = this.qs.poll();
                if (poll == null) {
                    return null;
                }
                K apply = this.h.apply(poll);
                if (!this.k) {
                    this.k = true;
                    this.j = apply;
                    return poll;
                } else if (!this.i.test((K) this.j, apply)) {
                    this.j = apply;
                    return poll;
                } else {
                    this.j = apply;
                    if (this.sourceMode != 1) {
                        this.upstream.request(1L);
                    }
                }
            }
        }

        @Override // io.reactivex.internal.fuseable.QueueFuseable
        public int requestFusion(int i) {
            return transitiveBoundaryFusion(i);
        }

        @Override // io.reactivex.internal.fuseable.ConditionalSubscriber
        public boolean tryOnNext(T t) {
            if (this.done) {
                return false;
            }
            if (this.sourceMode != 0) {
                this.downstream.onNext(t);
                return true;
            }
            try {
                K apply = this.h.apply(t);
                if (this.k) {
                    boolean test = this.i.test((K) this.j, apply);
                    this.j = apply;
                    if (test) {
                        return false;
                    }
                } else {
                    this.k = true;
                    this.j = apply;
                }
                this.downstream.onNext(t);
                return true;
            } catch (Throwable th) {
                fail(th);
                return true;
            }
        }
    }

    public FlowableDistinctUntilChanged(Flowable<T> flowable, Function<? super T, K> function, BiPredicate<? super K, ? super K> biPredicate) {
        super(flowable);
        this.i = function;
        this.j = biPredicate;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        if (subscriber instanceof ConditionalSubscriber) {
            this.source.subscribe((FlowableSubscriber) new a((ConditionalSubscriber) subscriber, this.i, this.j));
        } else {
            this.source.subscribe((FlowableSubscriber) new b(subscriber, this.i, this.j));
        }
    }
}

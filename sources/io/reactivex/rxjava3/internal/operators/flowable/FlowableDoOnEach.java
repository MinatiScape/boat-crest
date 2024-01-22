package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.internal.fuseable.ConditionalSubscriber;
import io.reactivex.rxjava3.internal.subscribers.BasicFuseableConditionalSubscriber;
import io.reactivex.rxjava3.internal.subscribers.BasicFuseableSubscriber;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
/* loaded from: classes12.dex */
public final class FlowableDoOnEach<T> extends io.reactivex.rxjava3.internal.operators.flowable.a<T, T> {
    public final Consumer<? super T> i;
    public final Consumer<? super Throwable> j;
    public final Action k;
    public final Action l;

    /* loaded from: classes12.dex */
    public static final class a<T> extends BasicFuseableConditionalSubscriber<T, T> {
        public final Consumer<? super T> h;
        public final Consumer<? super Throwable> i;
        public final Action j;
        public final Action k;

        public a(ConditionalSubscriber<? super T> conditionalSubscriber, Consumer<? super T> consumer, Consumer<? super Throwable> consumer2, Action action, Action action2) {
            super(conditionalSubscriber);
            this.h = consumer;
            this.i = consumer2;
            this.j = action;
            this.k = action2;
        }

        @Override // io.reactivex.rxjava3.internal.subscribers.BasicFuseableConditionalSubscriber, org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.done) {
                return;
            }
            try {
                this.j.run();
                this.done = true;
                this.downstream.onComplete();
                try {
                    this.k.run();
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    RxJavaPlugins.onError(th);
                }
            } catch (Throwable th2) {
                fail(th2);
            }
        }

        @Override // io.reactivex.rxjava3.internal.subscribers.BasicFuseableConditionalSubscriber, org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            }
            boolean z = true;
            this.done = true;
            try {
                this.i.accept(th);
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                this.downstream.onError(new CompositeException(th, th2));
                z = false;
            }
            if (z) {
                this.downstream.onError(th);
            }
            try {
                this.k.run();
            } catch (Throwable th3) {
                Exceptions.throwIfFatal(th3);
                RxJavaPlugins.onError(th3);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            if (this.sourceMode != 0) {
                this.downstream.onNext(null);
                return;
            }
            try {
                this.h.accept(t);
                this.downstream.onNext(t);
            } catch (Throwable th) {
                fail(th);
            }
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        @Nullable
        public T poll() throws Throwable {
            try {
                T poll = this.qs.poll();
                if (poll != null) {
                    try {
                        this.h.accept(poll);
                    } catch (Throwable th) {
                        try {
                            Exceptions.throwIfFatal(th);
                            this.i.accept(th);
                            throw ExceptionHelper.throwIfThrowable(th);
                        } finally {
                            this.k.run();
                        }
                    }
                } else if (this.sourceMode == 1) {
                    this.j.run();
                }
                return poll;
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                try {
                    this.i.accept(th2);
                    throw ExceptionHelper.throwIfThrowable(th2);
                } catch (Throwable th3) {
                    Exceptions.throwIfFatal(th3);
                    throw new CompositeException(th2, th3);
                }
            }
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.QueueFuseable
        public int requestFusion(int i) {
            return transitiveBoundaryFusion(i);
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.ConditionalSubscriber
        public boolean tryOnNext(T t) {
            if (this.done) {
                return false;
            }
            try {
                this.h.accept(t);
                return this.downstream.tryOnNext(t);
            } catch (Throwable th) {
                fail(th);
                return false;
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class b<T> extends BasicFuseableSubscriber<T, T> {
        public final Consumer<? super T> h;
        public final Consumer<? super Throwable> i;
        public final Action j;
        public final Action k;

        public b(Subscriber<? super T> subscriber, Consumer<? super T> consumer, Consumer<? super Throwable> consumer2, Action action, Action action2) {
            super(subscriber);
            this.h = consumer;
            this.i = consumer2;
            this.j = action;
            this.k = action2;
        }

        @Override // io.reactivex.rxjava3.internal.subscribers.BasicFuseableSubscriber, org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.done) {
                return;
            }
            try {
                this.j.run();
                this.done = true;
                this.downstream.onComplete();
                try {
                    this.k.run();
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    RxJavaPlugins.onError(th);
                }
            } catch (Throwable th2) {
                fail(th2);
            }
        }

        @Override // io.reactivex.rxjava3.internal.subscribers.BasicFuseableSubscriber, org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            }
            boolean z = true;
            this.done = true;
            try {
                this.i.accept(th);
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                this.downstream.onError(new CompositeException(th, th2));
                z = false;
            }
            if (z) {
                this.downstream.onError(th);
            }
            try {
                this.k.run();
            } catch (Throwable th3) {
                Exceptions.throwIfFatal(th3);
                RxJavaPlugins.onError(th3);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            if (this.sourceMode != 0) {
                this.downstream.onNext(null);
                return;
            }
            try {
                this.h.accept(t);
                this.downstream.onNext(t);
            } catch (Throwable th) {
                fail(th);
            }
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        @Nullable
        public T poll() throws Throwable {
            try {
                T poll = this.qs.poll();
                if (poll != null) {
                    try {
                        this.h.accept(poll);
                    } catch (Throwable th) {
                        try {
                            Exceptions.throwIfFatal(th);
                            this.i.accept(th);
                            throw ExceptionHelper.throwIfThrowable(th);
                        } finally {
                            this.k.run();
                        }
                    }
                } else if (this.sourceMode == 1) {
                    this.j.run();
                }
                return poll;
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                try {
                    this.i.accept(th2);
                    throw ExceptionHelper.throwIfThrowable(th2);
                } catch (Throwable th3) {
                    Exceptions.throwIfFatal(th3);
                    throw new CompositeException(th2, th3);
                }
            }
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.QueueFuseable
        public int requestFusion(int i) {
            return transitiveBoundaryFusion(i);
        }
    }

    public FlowableDoOnEach(Flowable<T> flowable, Consumer<? super T> consumer, Consumer<? super Throwable> consumer2, Action action, Action action2) {
        super(flowable);
        this.i = consumer;
        this.j = consumer2;
        this.k = action;
        this.l = action2;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        if (subscriber instanceof ConditionalSubscriber) {
            this.source.subscribe((FlowableSubscriber) new a((ConditionalSubscriber) subscriber, this.i, this.j, this.k, this.l));
        } else {
            this.source.subscribe((FlowableSubscriber) new b(subscriber, this.i, this.j, this.k, this.l));
        }
    }
}

package io.reactivex.flowables;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.annotations.BackpressureKind;
import io.reactivex.annotations.BackpressureSupport;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.SchedulerSupport;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.operators.flowable.FlowableAutoConnect;
import io.reactivex.internal.operators.flowable.FlowablePublishAlt;
import io.reactivex.internal.operators.flowable.FlowablePublishClassic;
import io.reactivex.internal.operators.flowable.FlowableRefCount;
import io.reactivex.internal.util.ConnectConsumer;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public abstract class ConnectableFlowable<T> extends Flowable<T> {
    @NonNull
    public Flowable<T> autoConnect() {
        return autoConnect(1);
    }

    public final Disposable connect() {
        ConnectConsumer connectConsumer = new ConnectConsumer();
        connect(connectConsumer);
        return connectConsumer.disposable;
    }

    public abstract void connect(@NonNull Consumer<? super Disposable> consumer);

    public final ConnectableFlowable<T> e() {
        if (this instanceof FlowablePublishClassic) {
            FlowablePublishClassic flowablePublishClassic = (FlowablePublishClassic) this;
            return RxJavaPlugins.onAssembly((ConnectableFlowable) new FlowablePublishAlt(flowablePublishClassic.publishSource(), flowablePublishClassic.publishBufferSize()));
        }
        return this;
    }

    @SchedulerSupport("none")
    @BackpressureSupport(BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    @NonNull
    public Flowable<T> refCount() {
        return RxJavaPlugins.onAssembly(new FlowableRefCount(e()));
    }

    @NonNull
    public Flowable<T> autoConnect(int i) {
        return autoConnect(i, Functions.emptyConsumer());
    }

    @SchedulerSupport("none")
    @BackpressureSupport(BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    public final Flowable<T> refCount(int i) {
        return refCount(i, 0L, TimeUnit.NANOSECONDS, Schedulers.trampoline());
    }

    @NonNull
    public Flowable<T> autoConnect(int i, @NonNull Consumer<? super Disposable> consumer) {
        if (i <= 0) {
            connect(consumer);
            return RxJavaPlugins.onAssembly((ConnectableFlowable) this);
        }
        return RxJavaPlugins.onAssembly(new FlowableAutoConnect(this, i, consumer));
    }

    @SchedulerSupport("io.reactivex:computation")
    @BackpressureSupport(BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    public final Flowable<T> refCount(long j, TimeUnit timeUnit) {
        return refCount(1, j, timeUnit, Schedulers.computation());
    }

    @SchedulerSupport("custom")
    @BackpressureSupport(BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    public final Flowable<T> refCount(long j, TimeUnit timeUnit, Scheduler scheduler) {
        return refCount(1, j, timeUnit, scheduler);
    }

    @SchedulerSupport("io.reactivex:computation")
    @BackpressureSupport(BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    public final Flowable<T> refCount(int i, long j, TimeUnit timeUnit) {
        return refCount(i, j, timeUnit, Schedulers.computation());
    }

    @SchedulerSupport("custom")
    @BackpressureSupport(BackpressureKind.PASS_THROUGH)
    @CheckReturnValue
    public final Flowable<T> refCount(int i, long j, TimeUnit timeUnit, Scheduler scheduler) {
        ObjectHelper.verifyPositive(i, "subscriberCount");
        ObjectHelper.requireNonNull(timeUnit, "unit is null");
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new FlowableRefCount(e(), i, j, timeUnit, scheduler));
    }
}

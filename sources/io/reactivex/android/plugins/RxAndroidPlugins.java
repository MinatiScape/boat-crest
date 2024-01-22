package io.reactivex.android.plugins;

import io.reactivex.Scheduler;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import java.util.Objects;
import java.util.concurrent.Callable;
/* loaded from: classes12.dex */
public final class RxAndroidPlugins {

    /* renamed from: a  reason: collision with root package name */
    public static volatile Function<Callable<Scheduler>, Scheduler> f13893a;
    public static volatile Function<Scheduler, Scheduler> b;

    public RxAndroidPlugins() {
        throw new AssertionError("No instances.");
    }

    public static <T, R> R a(Function<T, R> function, T t) {
        try {
            return function.apply(t);
        } catch (Throwable th) {
            throw Exceptions.propagate(th);
        }
    }

    public static Scheduler b(Function<Callable<Scheduler>, Scheduler> function, Callable<Scheduler> callable) {
        Scheduler scheduler = (Scheduler) a(function, callable);
        Objects.requireNonNull(scheduler, "Scheduler Callable returned null");
        return scheduler;
    }

    public static Scheduler c(Callable<Scheduler> callable) {
        try {
            Scheduler call = callable.call();
            if (call != null) {
                return call;
            }
            throw new NullPointerException("Scheduler Callable returned null");
        } catch (Throwable th) {
            throw Exceptions.propagate(th);
        }
    }

    public static Function<Callable<Scheduler>, Scheduler> getInitMainThreadSchedulerHandler() {
        return f13893a;
    }

    public static Function<Scheduler, Scheduler> getOnMainThreadSchedulerHandler() {
        return b;
    }

    public static Scheduler initMainThreadScheduler(Callable<Scheduler> callable) {
        Objects.requireNonNull(callable, "scheduler == null");
        Function<Callable<Scheduler>, Scheduler> function = f13893a;
        if (function == null) {
            return c(callable);
        }
        return b(function, callable);
    }

    public static Scheduler onMainThreadScheduler(Scheduler scheduler) {
        Objects.requireNonNull(scheduler, "scheduler == null");
        Function<Scheduler, Scheduler> function = b;
        return function == null ? scheduler : (Scheduler) a(function, scheduler);
    }

    public static void reset() {
        setInitMainThreadSchedulerHandler(null);
        setMainThreadSchedulerHandler(null);
    }

    public static void setInitMainThreadSchedulerHandler(Function<Callable<Scheduler>, Scheduler> function) {
        f13893a = function;
    }

    public static void setMainThreadSchedulerHandler(Function<Scheduler, Scheduler> function) {
        b = function;
    }
}

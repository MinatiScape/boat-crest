package rx.plugins;

import java.util.Objects;
import java.util.concurrent.ThreadFactory;
import rx.Scheduler;
import rx.functions.Action0;
import rx.internal.schedulers.CachedThreadScheduler;
import rx.internal.schedulers.EventLoopsScheduler;
import rx.internal.schedulers.NewThreadScheduler;
import rx.internal.util.RxThreadFactory;
/* loaded from: classes13.dex */
public class RxJavaSchedulersHook {

    /* renamed from: a  reason: collision with root package name */
    public static final RxJavaSchedulersHook f15693a = new RxJavaSchedulersHook();

    public static Scheduler createComputationScheduler() {
        return createComputationScheduler(new RxThreadFactory("RxComputationScheduler-"));
    }

    public static Scheduler createIoScheduler() {
        return createIoScheduler(new RxThreadFactory("RxIoScheduler-"));
    }

    public static Scheduler createNewThreadScheduler() {
        return createNewThreadScheduler(new RxThreadFactory("RxNewThreadScheduler-"));
    }

    public static RxJavaSchedulersHook getDefaultInstance() {
        return f15693a;
    }

    public Scheduler getComputationScheduler() {
        return null;
    }

    public Scheduler getIOScheduler() {
        return null;
    }

    public Scheduler getNewThreadScheduler() {
        return null;
    }

    @Deprecated
    public Action0 onSchedule(Action0 action0) {
        return action0;
    }

    public static Scheduler createComputationScheduler(ThreadFactory threadFactory) {
        Objects.requireNonNull(threadFactory, "threadFactory == null");
        return new EventLoopsScheduler(threadFactory);
    }

    public static Scheduler createIoScheduler(ThreadFactory threadFactory) {
        Objects.requireNonNull(threadFactory, "threadFactory == null");
        return new CachedThreadScheduler(threadFactory);
    }

    public static Scheduler createNewThreadScheduler(ThreadFactory threadFactory) {
        Objects.requireNonNull(threadFactory, "threadFactory == null");
        return new NewThreadScheduler(threadFactory);
    }
}

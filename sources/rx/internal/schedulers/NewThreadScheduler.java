package rx.internal.schedulers;

import java.util.concurrent.ThreadFactory;
import rx.Scheduler;
/* loaded from: classes13.dex */
public final class NewThreadScheduler extends Scheduler {

    /* renamed from: a  reason: collision with root package name */
    public final ThreadFactory f15682a;

    public NewThreadScheduler(ThreadFactory threadFactory) {
        this.f15682a = threadFactory;
    }

    @Override // rx.Scheduler
    public Scheduler.Worker createWorker() {
        return new NewThreadWorker(this.f15682a);
    }
}

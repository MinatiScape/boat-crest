package rx.schedulers;

import rx.Scheduler;
@Deprecated
/* loaded from: classes13.dex */
public final class ImmediateScheduler extends Scheduler {
    public ImmediateScheduler() {
        throw new IllegalStateException("No instances!");
    }

    @Override // rx.Scheduler
    public Scheduler.Worker createWorker() {
        return null;
    }
}

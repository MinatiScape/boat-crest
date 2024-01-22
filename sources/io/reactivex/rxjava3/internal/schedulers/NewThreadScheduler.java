package io.reactivex.rxjava3.internal.schedulers;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Scheduler;
import java.util.concurrent.ThreadFactory;
/* loaded from: classes12.dex */
public final class NewThreadScheduler extends Scheduler {
    public static final RxThreadFactory j = new RxThreadFactory("RxNewThreadScheduler", Math.max(1, Math.min(10, Integer.getInteger("rx3.newthread-priority", 5).intValue())));
    public final ThreadFactory i;

    public NewThreadScheduler() {
        this(j);
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    @NonNull
    public Scheduler.Worker createWorker() {
        return new NewThreadWorker(this.i);
    }

    public NewThreadScheduler(ThreadFactory threadFactory) {
        this.i = threadFactory;
    }
}

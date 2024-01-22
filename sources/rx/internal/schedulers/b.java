package rx.internal.schedulers;

import rx.Scheduler;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
/* loaded from: classes13.dex */
public class b implements Action0 {
    public final Action0 h;
    public final Scheduler.Worker i;
    public final long j;

    public b(Action0 action0, Scheduler.Worker worker, long j) {
        this.h = action0;
        this.i = worker;
        this.j = j;
    }

    @Override // rx.functions.Action0
    public void call() {
        if (this.i.isUnsubscribed()) {
            return;
        }
        long now = this.j - this.i.now();
        if (now > 0) {
            try {
                Thread.sleep(now);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                Exceptions.propagate(e);
            }
        }
        if (this.i.isUnsubscribed()) {
            return;
        }
        this.h.call();
    }
}

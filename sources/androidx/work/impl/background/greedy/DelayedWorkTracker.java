package androidx.work.impl.background.greedy;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.work.Logger;
import androidx.work.RunnableScheduler;
import androidx.work.impl.model.WorkSpec;
import java.util.HashMap;
import java.util.Map;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class DelayedWorkTracker {
    public static final String d = Logger.tagWithPrefix("DelayedWorkTracker");

    /* renamed from: a  reason: collision with root package name */
    public final GreedyScheduler f1803a;
    public final RunnableScheduler b;
    public final Map<String, Runnable> c = new HashMap();

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public final /* synthetic */ WorkSpec h;

        public a(WorkSpec workSpec) {
            this.h = workSpec;
        }

        @Override // java.lang.Runnable
        public void run() {
            Logger.get().debug(DelayedWorkTracker.d, String.format("Scheduling work %s", this.h.id), new Throwable[0]);
            DelayedWorkTracker.this.f1803a.schedule(this.h);
        }
    }

    public DelayedWorkTracker(@NonNull GreedyScheduler greedyScheduler, @NonNull RunnableScheduler runnableScheduler) {
        this.f1803a = greedyScheduler;
        this.b = runnableScheduler;
    }

    public void schedule(@NonNull WorkSpec workSpec) {
        Runnable remove = this.c.remove(workSpec.id);
        if (remove != null) {
            this.b.cancel(remove);
        }
        a aVar = new a(workSpec);
        this.c.put(workSpec.id, aVar);
        long currentTimeMillis = System.currentTimeMillis();
        this.b.scheduleWithDelay(workSpec.calculateNextRunTime() - currentTimeMillis, aVar);
    }

    public void unschedule(@NonNull String str) {
        Runnable remove = this.c.remove(str);
        if (remove != null) {
            this.b.cancel(remove);
        }
    }
}

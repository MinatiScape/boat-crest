package rx.internal.schedulers;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes13.dex */
public final class GenericScheduledExecutorService implements SchedulerLifecycle {
    public static final GenericScheduledExecutorService INSTANCE;
    public static final ScheduledExecutorService[] i = new ScheduledExecutorService[0];
    public static final ScheduledExecutorService j;
    public static int k;
    public final AtomicReference<ScheduledExecutorService[]> h = new AtomicReference<>(i);

    static {
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(0);
        j = newScheduledThreadPool;
        newScheduledThreadPool.shutdown();
        INSTANCE = new GenericScheduledExecutorService();
    }

    public GenericScheduledExecutorService() {
        start();
    }

    public static ScheduledExecutorService getInstance() {
        ScheduledExecutorService[] scheduledExecutorServiceArr = INSTANCE.h.get();
        if (scheduledExecutorServiceArr == i) {
            return j;
        }
        int i2 = k + 1;
        if (i2 >= scheduledExecutorServiceArr.length) {
            i2 = 0;
        }
        k = i2;
        return scheduledExecutorServiceArr[i2];
    }

    @Override // rx.internal.schedulers.SchedulerLifecycle
    public void shutdown() {
        ScheduledExecutorService[] scheduledExecutorServiceArr;
        ScheduledExecutorService[] scheduledExecutorServiceArr2;
        do {
            scheduledExecutorServiceArr = this.h.get();
            scheduledExecutorServiceArr2 = i;
            if (scheduledExecutorServiceArr == scheduledExecutorServiceArr2) {
                return;
            }
        } while (!this.h.compareAndSet(scheduledExecutorServiceArr, scheduledExecutorServiceArr2));
        for (ScheduledExecutorService scheduledExecutorService : scheduledExecutorServiceArr) {
            NewThreadWorker.deregisterExecutor(scheduledExecutorService);
            scheduledExecutorService.shutdownNow();
        }
    }

    @Override // rx.internal.schedulers.SchedulerLifecycle
    public void start() {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        if (availableProcessors > 4) {
            availableProcessors /= 2;
        }
        if (availableProcessors > 8) {
            availableProcessors = 8;
        }
        ScheduledExecutorService[] scheduledExecutorServiceArr = new ScheduledExecutorService[availableProcessors];
        int i2 = 0;
        for (int i3 = 0; i3 < availableProcessors; i3++) {
            scheduledExecutorServiceArr[i3] = a.create();
        }
        if (!this.h.compareAndSet(i, scheduledExecutorServiceArr)) {
            while (i2 < availableProcessors) {
                scheduledExecutorServiceArr[i2].shutdownNow();
                i2++;
            }
            return;
        }
        while (i2 < availableProcessors) {
            ScheduledExecutorService scheduledExecutorService = scheduledExecutorServiceArr[i2];
            if (!NewThreadWorker.tryEnableCancelPolicy(scheduledExecutorService) && (scheduledExecutorService instanceof ScheduledThreadPoolExecutor)) {
                NewThreadWorker.registerExecutor((ScheduledThreadPoolExecutor) scheduledExecutorService);
            }
            i2++;
        }
    }
}

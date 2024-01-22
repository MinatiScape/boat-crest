package androidx.camera.core;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.camera.core.impl.CameraFactory;
import androidx.core.util.Preconditions;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class i implements Executor {
    public static final ThreadFactory j = new a();
    public final Object h = new Object();
    @NonNull
    @GuardedBy("mExecutorLock")
    public ThreadPoolExecutor i = a();

    /* loaded from: classes.dex */
    public class a implements ThreadFactory {
        public final AtomicInteger h = new AtomicInteger(0);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(@NonNull Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName(String.format(Locale.US, "CameraX-core_camera_%d", Integer.valueOf(this.h.getAndIncrement())));
            return thread;
        }
    }

    public static ThreadPoolExecutor a() {
        return new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), j);
    }

    public void b() {
        synchronized (this.h) {
            if (!this.i.isShutdown()) {
                this.i.shutdown();
            }
        }
    }

    public void c(@NonNull CameraFactory cameraFactory) {
        ThreadPoolExecutor threadPoolExecutor;
        Preconditions.checkNotNull(cameraFactory);
        synchronized (this.h) {
            if (this.i.isShutdown()) {
                this.i = a();
            }
            threadPoolExecutor = this.i;
        }
        int max = Math.max(1, cameraFactory.getAvailableCameraIds().size());
        threadPoolExecutor.setMaximumPoolSize(max);
        threadPoolExecutor.setCorePoolSize(max);
    }

    @Override // java.util.concurrent.Executor
    public void execute(@NonNull Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        synchronized (this.h) {
            this.i.execute(runnable);
        }
    }
}

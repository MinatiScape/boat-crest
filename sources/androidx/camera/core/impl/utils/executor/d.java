package androidx.camera.core.impl.utils.executor;

import androidx.annotation.NonNull;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public final class d implements Executor {
    public static volatile Executor i;
    public final ExecutorService h = Executors.newFixedThreadPool(2, new a(this));

    /* loaded from: classes.dex */
    public class a implements ThreadFactory {
        public final AtomicInteger h = new AtomicInteger(0);

        public a(d dVar) {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName(String.format(Locale.US, "CameraX-camerax_io_%d", Integer.valueOf(this.h.getAndIncrement())));
            return thread;
        }
    }

    public static Executor a() {
        if (i != null) {
            return i;
        }
        synchronized (d.class) {
            if (i == null) {
                i = new d();
            }
        }
        return i;
    }

    @Override // java.util.concurrent.Executor
    public void execute(@NonNull Runnable runnable) {
        this.h.execute(runnable);
    }
}

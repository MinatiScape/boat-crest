package androidx.camera.core.impl.utils.executor;

import androidx.annotation.NonNull;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
/* loaded from: classes.dex */
public final class c implements Executor {
    public static volatile Executor i;
    public final ExecutorService h = Executors.newSingleThreadExecutor(new a(this));

    /* loaded from: classes.dex */
    public class a implements ThreadFactory {
        public a(c cVar) {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setPriority(10);
            thread.setName("CameraX-camerax_high_priority");
            return thread;
        }
    }

    public static Executor a() {
        if (i != null) {
            return i;
        }
        synchronized (c.class) {
            if (i == null) {
                i = new c();
            }
        }
        return i;
    }

    @Override // java.util.concurrent.Executor
    public void execute(@NonNull Runnable runnable) {
        this.h.execute(runnable);
    }
}

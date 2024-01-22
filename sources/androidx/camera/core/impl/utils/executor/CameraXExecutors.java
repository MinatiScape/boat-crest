package androidx.camera.core.impl.utils.executor;

import android.os.Handler;
import androidx.annotation.NonNull;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
/* loaded from: classes.dex */
public final class CameraXExecutors {
    @NonNull
    public static Executor directExecutor() {
        return a.a();
    }

    @NonNull
    public static Executor highPriorityExecutor() {
        return c.a();
    }

    @NonNull
    public static Executor ioExecutor() {
        return d.a();
    }

    public static boolean isSequentialExecutor(@NonNull Executor executor) {
        return executor instanceof f;
    }

    @NonNull
    public static ScheduledExecutorService mainThreadExecutor() {
        return e.a();
    }

    @NonNull
    public static ScheduledExecutorService myLooperExecutor() {
        return b.b();
    }

    @NonNull
    public static ScheduledExecutorService newHandlerExecutor(@NonNull Handler handler) {
        return new b(handler);
    }

    @NonNull
    public static Executor newSequentialExecutor(@NonNull Executor executor) {
        return new f(executor);
    }
}

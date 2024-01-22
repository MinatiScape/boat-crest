package androidx.core.os;

import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.core.util.Preconditions;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
/* loaded from: classes.dex */
public final class ExecutorCompat {

    /* loaded from: classes.dex */
    public static class a implements Executor {
        public final Handler h;

        public a(@NonNull Handler handler) {
            this.h = (Handler) Preconditions.checkNotNull(handler);
        }

        @Override // java.util.concurrent.Executor
        public void execute(@NonNull Runnable runnable) {
            if (this.h.post((Runnable) Preconditions.checkNotNull(runnable))) {
                return;
            }
            throw new RejectedExecutionException(this.h + " is shutting down");
        }
    }

    private ExecutorCompat() {
    }

    @NonNull
    public static Executor create(@NonNull Handler handler) {
        return new a(handler);
    }
}

package androidx.arch.core.executor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.util.concurrent.Executor;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class ArchTaskExecutor extends TaskExecutor {
    public static volatile ArchTaskExecutor c;
    @NonNull
    public static final Executor d = new a();
    @NonNull
    public static final Executor e = new b();
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public TaskExecutor f484a;
    @NonNull
    public TaskExecutor b;

    /* loaded from: classes.dex */
    public static class a implements Executor {
        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            ArchTaskExecutor.getInstance().postToMainThread(runnable);
        }
    }

    /* loaded from: classes.dex */
    public static class b implements Executor {
        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            ArchTaskExecutor.getInstance().executeOnDiskIO(runnable);
        }
    }

    public ArchTaskExecutor() {
        DefaultTaskExecutor defaultTaskExecutor = new DefaultTaskExecutor();
        this.b = defaultTaskExecutor;
        this.f484a = defaultTaskExecutor;
    }

    @NonNull
    public static Executor getIOThreadExecutor() {
        return e;
    }

    @NonNull
    public static ArchTaskExecutor getInstance() {
        if (c != null) {
            return c;
        }
        synchronized (ArchTaskExecutor.class) {
            if (c == null) {
                c = new ArchTaskExecutor();
            }
        }
        return c;
    }

    @NonNull
    public static Executor getMainThreadExecutor() {
        return d;
    }

    @Override // androidx.arch.core.executor.TaskExecutor
    public void executeOnDiskIO(Runnable runnable) {
        this.f484a.executeOnDiskIO(runnable);
    }

    @Override // androidx.arch.core.executor.TaskExecutor
    public boolean isMainThread() {
        return this.f484a.isMainThread();
    }

    @Override // androidx.arch.core.executor.TaskExecutor
    public void postToMainThread(Runnable runnable) {
        this.f484a.postToMainThread(runnable);
    }

    public void setDelegate(@Nullable TaskExecutor taskExecutor) {
        if (taskExecutor == null) {
            taskExecutor = this.b;
        }
        this.f484a = taskExecutor;
    }
}

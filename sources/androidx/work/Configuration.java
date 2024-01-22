package androidx.work;

import android.annotation.SuppressLint;
import android.os.Build;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.work.impl.DefaultRunnableScheduler;
import com.coveiot.android.camera.utils.ViewExtensionsKt;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public final class Configuration {
    @SuppressLint({"MinMaxConstant"})
    public static final int MIN_SCHEDULER_LIMIT = 20;
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final Executor f1769a;
    @NonNull
    public final Executor b;
    @NonNull
    public final WorkerFactory c;
    @NonNull
    public final InputMergerFactory d;
    @NonNull
    public final RunnableScheduler e;
    @Nullable
    public final InitializationExceptionHandler f;
    @Nullable
    public final String g;
    public final int h;
    public final int i;
    public final int j;
    public final int k;
    public final boolean l;

    /* loaded from: classes.dex */
    public interface Provider {
        @NonNull
        Configuration getWorkManagerConfiguration();
    }

    /* loaded from: classes.dex */
    public class a implements ThreadFactory {
        public final AtomicInteger h = new AtomicInteger(0);
        public final /* synthetic */ boolean i;

        public a(Configuration configuration, boolean z) {
            this.i = z;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            String str = this.i ? "WM.task-" : "androidx.work-";
            return new Thread(runnable, str + this.h.incrementAndGet());
        }
    }

    public Configuration(@NonNull Builder builder) {
        Executor executor = builder.f1770a;
        if (executor == null) {
            this.f1769a = a(false);
        } else {
            this.f1769a = executor;
        }
        Executor executor2 = builder.d;
        if (executor2 == null) {
            this.l = true;
            this.b = a(true);
        } else {
            this.l = false;
            this.b = executor2;
        }
        WorkerFactory workerFactory = builder.b;
        if (workerFactory == null) {
            this.c = WorkerFactory.getDefaultWorkerFactory();
        } else {
            this.c = workerFactory;
        }
        InputMergerFactory inputMergerFactory = builder.c;
        if (inputMergerFactory == null) {
            this.d = InputMergerFactory.getDefaultInputMergerFactory();
        } else {
            this.d = inputMergerFactory;
        }
        RunnableScheduler runnableScheduler = builder.e;
        if (runnableScheduler == null) {
            this.e = new DefaultRunnableScheduler();
        } else {
            this.e = runnableScheduler;
        }
        this.h = builder.h;
        this.i = builder.i;
        this.j = builder.j;
        this.k = builder.k;
        this.f = builder.f;
        this.g = builder.g;
    }

    @NonNull
    public final Executor a(boolean z) {
        return Executors.newFixedThreadPool(Math.max(2, Math.min(Runtime.getRuntime().availableProcessors() - 1, 4)), b(z));
    }

    @NonNull
    public final ThreadFactory b(boolean z) {
        return new a(this, z);
    }

    @Nullable
    public String getDefaultProcessName() {
        return this.g;
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public InitializationExceptionHandler getExceptionHandler() {
        return this.f;
    }

    @NonNull
    public Executor getExecutor() {
        return this.f1769a;
    }

    @NonNull
    public InputMergerFactory getInputMergerFactory() {
        return this.d;
    }

    public int getMaxJobSchedulerId() {
        return this.j;
    }

    @IntRange(from = 20, to = ViewExtensionsKt.ANIMATION_FAST_MILLIS)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getMaxSchedulerLimit() {
        if (Build.VERSION.SDK_INT == 23) {
            return this.k / 2;
        }
        return this.k;
    }

    public int getMinJobSchedulerId() {
        return this.i;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getMinimumLoggingLevel() {
        return this.h;
    }

    @NonNull
    public RunnableScheduler getRunnableScheduler() {
        return this.e;
    }

    @NonNull
    public Executor getTaskExecutor() {
        return this.b;
    }

    @NonNull
    public WorkerFactory getWorkerFactory() {
        return this.c;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean isUsingDefaultTaskExecutor() {
        return this.l;
    }

    /* loaded from: classes.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Executor f1770a;
        public WorkerFactory b;
        public InputMergerFactory c;
        public Executor d;
        public RunnableScheduler e;
        @Nullable
        public InitializationExceptionHandler f;
        @Nullable
        public String g;
        public int h;
        public int i;
        public int j;
        public int k;

        public Builder() {
            this.h = 4;
            this.i = 0;
            this.j = Integer.MAX_VALUE;
            this.k = 20;
        }

        @NonNull
        public Configuration build() {
            return new Configuration(this);
        }

        @NonNull
        public Builder setDefaultProcessName(@NonNull String str) {
            this.g = str;
            return this;
        }

        @NonNull
        public Builder setExecutor(@NonNull Executor executor) {
            this.f1770a = executor;
            return this;
        }

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setInitializationExceptionHandler(@NonNull InitializationExceptionHandler initializationExceptionHandler) {
            this.f = initializationExceptionHandler;
            return this;
        }

        @NonNull
        public Builder setInputMergerFactory(@NonNull InputMergerFactory inputMergerFactory) {
            this.c = inputMergerFactory;
            return this;
        }

        @NonNull
        public Builder setJobSchedulerJobIdRange(int i, int i2) {
            if (i2 - i >= 1000) {
                this.i = i;
                this.j = i2;
                return this;
            }
            throw new IllegalArgumentException("WorkManager needs a range of at least 1000 job ids.");
        }

        @NonNull
        public Builder setMaxSchedulerLimit(int i) {
            if (i >= 20) {
                this.k = Math.min(i, 50);
                return this;
            }
            throw new IllegalArgumentException("WorkManager needs to be able to schedule at least 20 jobs in JobScheduler.");
        }

        @NonNull
        public Builder setMinimumLoggingLevel(int i) {
            this.h = i;
            return this;
        }

        @NonNull
        public Builder setRunnableScheduler(@NonNull RunnableScheduler runnableScheduler) {
            this.e = runnableScheduler;
            return this;
        }

        @NonNull
        public Builder setTaskExecutor(@NonNull Executor executor) {
            this.d = executor;
            return this;
        }

        @NonNull
        public Builder setWorkerFactory(@NonNull WorkerFactory workerFactory) {
            this.b = workerFactory;
            return this;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder(@NonNull Configuration configuration) {
            this.f1770a = configuration.f1769a;
            this.b = configuration.c;
            this.c = configuration.d;
            this.d = configuration.b;
            this.h = configuration.h;
            this.i = configuration.i;
            this.j = configuration.j;
            this.k = configuration.k;
            this.e = configuration.e;
            this.f = configuration.f;
            this.g = configuration.g;
        }
    }
}

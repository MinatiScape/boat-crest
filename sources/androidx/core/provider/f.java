package androidx.core.provider;

import android.os.Handler;
import android.os.Process;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.core.util.Consumer;
import androidx.core.util.Preconditions;
import com.polidea.rxandroidble2.ClientComponent;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* loaded from: classes.dex */
public class f {

    /* loaded from: classes.dex */
    public static class a implements ThreadFactory {
        public String h;
        public int i;

        /* renamed from: androidx.core.provider.f$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static class C0132a extends Thread {
            public final int h;

            public C0132a(Runnable runnable, String str, int i) {
                super(runnable, str);
                this.h = i;
            }

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Process.setThreadPriority(this.h);
                super.run();
            }
        }

        public a(@NonNull String str, int i) {
            this.h = str;
            this.i = i;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new C0132a(runnable, this.h, this.i);
        }
    }

    /* loaded from: classes.dex */
    public static class b implements Executor {
        public final Handler h;

        public b(@NonNull Handler handler) {
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

    /* loaded from: classes.dex */
    public static class c<T> implements Runnable {
        @NonNull
        public Callable<T> h;
        @NonNull
        public Consumer<T> i;
        @NonNull
        public Handler j;

        /* loaded from: classes.dex */
        public class a implements Runnable {
            public final /* synthetic */ Consumer h;
            public final /* synthetic */ Object i;

            public a(c cVar, Consumer consumer, Object obj) {
                this.h = consumer;
                this.i = obj;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                this.h.accept(this.i);
            }
        }

        public c(@NonNull Handler handler, @NonNull Callable<T> callable, @NonNull Consumer<T> consumer) {
            this.h = callable;
            this.i = consumer;
            this.j = handler;
        }

        @Override // java.lang.Runnable
        public void run() {
            T t;
            try {
                t = this.h.call();
            } catch (Exception unused) {
                t = null;
            }
            this.j.post(new a(this, this.i, t));
        }
    }

    public static ThreadPoolExecutor a(@NonNull String str, int i, @IntRange(from = 0) int i2) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, i2, TimeUnit.MILLISECONDS, new LinkedBlockingDeque(), new a(str, i));
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return threadPoolExecutor;
    }

    public static Executor b(@NonNull Handler handler) {
        return new b(handler);
    }

    public static <T> void c(@NonNull Executor executor, @NonNull Callable<T> callable, @NonNull Consumer<T> consumer) {
        executor.execute(new c(androidx.core.provider.b.a(), callable, consumer));
    }

    public static <T> T d(@NonNull ExecutorService executorService, @NonNull Callable<T> callable, @IntRange(from = 0) int i) throws InterruptedException {
        try {
            return executorService.submit(callable).get(i, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw e;
        } catch (ExecutionException e2) {
            throw new RuntimeException(e2);
        } catch (TimeoutException unused) {
            throw new InterruptedException(ClientComponent.NamedSchedulers.TIMEOUT);
        }
    }
}

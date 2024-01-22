package androidx.work.impl.utils;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import java.util.ArrayDeque;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public class SerialExecutor implements Executor {
    public final Executor i;
    public volatile Runnable k;
    public final ArrayDeque<a> h = new ArrayDeque<>();
    public final Object j = new Object();

    /* loaded from: classes.dex */
    public static class a implements Runnable {
        public final SerialExecutor h;
        public final Runnable i;

        public a(@NonNull SerialExecutor serialExecutor, @NonNull Runnable runnable) {
            this.h = serialExecutor;
            this.i = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.i.run();
            } finally {
                this.h.a();
            }
        }
    }

    public SerialExecutor(@NonNull Executor executor) {
        this.i = executor;
    }

    public void a() {
        synchronized (this.j) {
            a poll = this.h.poll();
            this.k = poll;
            if (poll != null) {
                this.i.execute(this.k);
            }
        }
    }

    @Override // java.util.concurrent.Executor
    public void execute(@NonNull Runnable runnable) {
        synchronized (this.j) {
            this.h.add(new a(this, runnable));
            if (this.k == null) {
                a();
            }
        }
    }

    @NonNull
    @VisibleForTesting
    public Executor getDelegatedExecutor() {
        return this.i;
    }

    public boolean hasPendingTasks() {
        boolean z;
        synchronized (this.j) {
            z = !this.h.isEmpty();
        }
        return z;
    }
}

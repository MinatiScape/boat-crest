package androidx.camera.core.impl.utils.executor;

import androidx.annotation.GuardedBy;
import androidx.core.util.Preconditions;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
/* loaded from: classes.dex */
public final class f implements Executor {
    public final Executor i;
    @GuardedBy("mQueue")
    public final Deque<Runnable> h = new ArrayDeque();
    public final b j = new b();
    @GuardedBy("mQueue")
    public c k = c.IDLE;
    @GuardedBy("mQueue")
    public long l = 0;

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public final /* synthetic */ Runnable h;

        public a(f fVar, Runnable runnable) {
            this.h = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.h.run();
        }
    }

    /* loaded from: classes.dex */
    public final class b implements Runnable {
        public b() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:17:0x0039, code lost:
            if (r1 == false) goto L35;
         */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x003b, code lost:
            java.lang.Thread.currentThread().interrupt();
         */
        /* JADX WARN: Code restructure failed: missing block: B:19:0x0042, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:22:0x0048, code lost:
            r1 = r1 | java.lang.Thread.interrupted();
         */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x0049, code lost:
            r3.run();
         */
        /* JADX WARN: Code restructure failed: missing block: B:25:0x004d, code lost:
            r2 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:26:0x004e, code lost:
            androidx.camera.core.Logger.e("SequentialExecutor", "Exception while executing runnable " + r3, r2);
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:?, code lost:
            return;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void a() {
            /*
                r9 = this;
                r0 = 0
                r1 = r0
            L2:
                androidx.camera.core.impl.utils.executor.f r2 = androidx.camera.core.impl.utils.executor.f.this     // Catch: java.lang.Throwable -> L68
                java.util.Deque<java.lang.Runnable> r2 = r2.h     // Catch: java.lang.Throwable -> L68
                monitor-enter(r2)     // Catch: java.lang.Throwable -> L68
                if (r0 != 0) goto L26
                androidx.camera.core.impl.utils.executor.f r0 = androidx.camera.core.impl.utils.executor.f.this     // Catch: java.lang.Throwable -> L65
                androidx.camera.core.impl.utils.executor.f$c r3 = r0.k     // Catch: java.lang.Throwable -> L65
                androidx.camera.core.impl.utils.executor.f$c r4 = androidx.camera.core.impl.utils.executor.f.c.RUNNING     // Catch: java.lang.Throwable -> L65
                if (r3 != r4) goto L1c
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L65
                if (r1 == 0) goto L1b
                java.lang.Thread r0 = java.lang.Thread.currentThread()
                r0.interrupt()
            L1b:
                return
            L1c:
                long r5 = r0.l     // Catch: java.lang.Throwable -> L65
                r7 = 1
                long r5 = r5 + r7
                r0.l = r5     // Catch: java.lang.Throwable -> L65
                r0.k = r4     // Catch: java.lang.Throwable -> L65
                r0 = 1
            L26:
                androidx.camera.core.impl.utils.executor.f r3 = androidx.camera.core.impl.utils.executor.f.this     // Catch: java.lang.Throwable -> L65
                java.util.Deque<java.lang.Runnable> r3 = r3.h     // Catch: java.lang.Throwable -> L65
                java.lang.Object r3 = r3.poll()     // Catch: java.lang.Throwable -> L65
                java.lang.Runnable r3 = (java.lang.Runnable) r3     // Catch: java.lang.Throwable -> L65
                if (r3 != 0) goto L43
                androidx.camera.core.impl.utils.executor.f r0 = androidx.camera.core.impl.utils.executor.f.this     // Catch: java.lang.Throwable -> L65
                androidx.camera.core.impl.utils.executor.f$c r3 = androidx.camera.core.impl.utils.executor.f.c.IDLE     // Catch: java.lang.Throwable -> L65
                r0.k = r3     // Catch: java.lang.Throwable -> L65
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L65
                if (r1 == 0) goto L42
                java.lang.Thread r0 = java.lang.Thread.currentThread()
                r0.interrupt()
            L42:
                return
            L43:
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L65
                boolean r2 = java.lang.Thread.interrupted()     // Catch: java.lang.Throwable -> L68
                r1 = r1 | r2
                r3.run()     // Catch: java.lang.RuntimeException -> L4d java.lang.Throwable -> L68
                goto L2
            L4d:
                r2 = move-exception
                java.lang.String r4 = "SequentialExecutor"
                java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L68
                r5.<init>()     // Catch: java.lang.Throwable -> L68
                java.lang.String r6 = "Exception while executing runnable "
                r5.append(r6)     // Catch: java.lang.Throwable -> L68
                r5.append(r3)     // Catch: java.lang.Throwable -> L68
                java.lang.String r3 = r5.toString()     // Catch: java.lang.Throwable -> L68
                androidx.camera.core.Logger.e(r4, r3, r2)     // Catch: java.lang.Throwable -> L68
                goto L2
            L65:
                r0 = move-exception
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L65
                throw r0     // Catch: java.lang.Throwable -> L68
            L68:
                r0 = move-exception
                if (r1 == 0) goto L72
                java.lang.Thread r1 = java.lang.Thread.currentThread()
                r1.interrupt()
            L72:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.impl.utils.executor.f.b.a():void");
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                a();
            } catch (Error e) {
                synchronized (f.this.h) {
                    f.this.k = c.IDLE;
                    throw e;
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public enum c {
        IDLE,
        QUEUING,
        QUEUED,
        RUNNING
    }

    public f(Executor executor) {
        this.i = (Executor) Preconditions.checkNotNull(executor);
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        c cVar;
        Preconditions.checkNotNull(runnable);
        synchronized (this.h) {
            c cVar2 = this.k;
            if (cVar2 != c.RUNNING && cVar2 != (cVar = c.QUEUED)) {
                long j = this.l;
                a aVar = new a(this, runnable);
                this.h.add(aVar);
                c cVar3 = c.QUEUING;
                this.k = cVar3;
                try {
                    this.i.execute(this.j);
                    if (this.k != cVar3) {
                        return;
                    }
                    synchronized (this.h) {
                        if (this.l == j && this.k == cVar3) {
                            this.k = cVar;
                        }
                    }
                    return;
                } catch (Error | RuntimeException e) {
                    synchronized (this.h) {
                        c cVar4 = this.k;
                        if ((cVar4 != c.IDLE && cVar4 != c.QUEUING) || !this.h.removeLastOccurrence(aVar)) {
                            r0 = false;
                        }
                        if (!(e instanceof RejectedExecutionException) || r0) {
                            throw e;
                        }
                    }
                    return;
                }
            }
            this.h.add(runnable);
        }
    }
}

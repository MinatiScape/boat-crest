package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import com.google.j2objc.annotations.RetainedWith;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Logger;
@GwtIncompatible
/* loaded from: classes10.dex */
public final class t implements Executor {
    public static final Logger m = Logger.getLogger(t.class.getName());
    public final Executor h;
    @GuardedBy("queue")
    public final Deque<Runnable> i = new ArrayDeque();
    @GuardedBy("queue")
    public c j = c.IDLE;
    @GuardedBy("queue")
    public long k = 0;
    @RetainedWith
    public final b l = new b(this, null);

    /* loaded from: classes10.dex */
    public class a implements Runnable {
        public final /* synthetic */ Runnable h;

        public a(t tVar, Runnable runnable) {
            this.h = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.h.run();
        }

        public String toString() {
            return this.h.toString();
        }
    }

    /* loaded from: classes10.dex */
    public final class b implements Runnable {
        public Runnable h;

        public b() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:17:0x0043, code lost:
            if (r1 == false) goto L35;
         */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x0045, code lost:
            java.lang.Thread.currentThread().interrupt();
         */
        /* JADX WARN: Code restructure failed: missing block: B:19:0x004c, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:22:0x0052, code lost:
            r1 = r1 | java.lang.Thread.interrupted();
         */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x0054, code lost:
            r9.h.run();
         */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x005e, code lost:
            r3 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:29:0x005f, code lost:
            r4 = com.google.common.util.concurrent.t.m;
            r5 = java.util.logging.Level.SEVERE;
            r6 = java.lang.String.valueOf(r9.h);
            r8 = new java.lang.StringBuilder(r6.length() + 35);
            r8.append("Exception while executing runnable ");
            r8.append(r6);
            r4.log(r5, r8.toString(), (java.lang.Throwable) r3);
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:?, code lost:
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
                com.google.common.util.concurrent.t r2 = com.google.common.util.concurrent.t.this     // Catch: java.lang.Throwable -> L8c
                java.util.Deque r2 = com.google.common.util.concurrent.t.a(r2)     // Catch: java.lang.Throwable -> L8c
                monitor-enter(r2)     // Catch: java.lang.Throwable -> L8c
                if (r0 != 0) goto L2b
                com.google.common.util.concurrent.t r0 = com.google.common.util.concurrent.t.this     // Catch: java.lang.Throwable -> L89
                com.google.common.util.concurrent.t$c r0 = com.google.common.util.concurrent.t.b(r0)     // Catch: java.lang.Throwable -> L89
                com.google.common.util.concurrent.t$c r3 = com.google.common.util.concurrent.t.c.RUNNING     // Catch: java.lang.Throwable -> L89
                if (r0 != r3) goto L20
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L89
                if (r1 == 0) goto L1f
                java.lang.Thread r0 = java.lang.Thread.currentThread()
                r0.interrupt()
            L1f:
                return
            L20:
                com.google.common.util.concurrent.t r0 = com.google.common.util.concurrent.t.this     // Catch: java.lang.Throwable -> L89
                com.google.common.util.concurrent.t.d(r0)     // Catch: java.lang.Throwable -> L89
                com.google.common.util.concurrent.t r0 = com.google.common.util.concurrent.t.this     // Catch: java.lang.Throwable -> L89
                com.google.common.util.concurrent.t.c(r0, r3)     // Catch: java.lang.Throwable -> L89
                r0 = 1
            L2b:
                com.google.common.util.concurrent.t r3 = com.google.common.util.concurrent.t.this     // Catch: java.lang.Throwable -> L89
                java.util.Deque r3 = com.google.common.util.concurrent.t.a(r3)     // Catch: java.lang.Throwable -> L89
                java.lang.Object r3 = r3.poll()     // Catch: java.lang.Throwable -> L89
                java.lang.Runnable r3 = (java.lang.Runnable) r3     // Catch: java.lang.Throwable -> L89
                r9.h = r3     // Catch: java.lang.Throwable -> L89
                if (r3 != 0) goto L4d
                com.google.common.util.concurrent.t r0 = com.google.common.util.concurrent.t.this     // Catch: java.lang.Throwable -> L89
                com.google.common.util.concurrent.t$c r3 = com.google.common.util.concurrent.t.c.IDLE     // Catch: java.lang.Throwable -> L89
                com.google.common.util.concurrent.t.c(r0, r3)     // Catch: java.lang.Throwable -> L89
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L89
                if (r1 == 0) goto L4c
                java.lang.Thread r0 = java.lang.Thread.currentThread()
                r0.interrupt()
            L4c:
                return
            L4d:
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L89
                boolean r2 = java.lang.Thread.interrupted()     // Catch: java.lang.Throwable -> L8c
                r1 = r1 | r2
                r2 = 0
                java.lang.Runnable r3 = r9.h     // Catch: java.lang.Throwable -> L5c java.lang.RuntimeException -> L5e
                r3.run()     // Catch: java.lang.Throwable -> L5c java.lang.RuntimeException -> L5e
            L59:
                r9.h = r2     // Catch: java.lang.Throwable -> L8c
                goto L2
            L5c:
                r0 = move-exception
                goto L86
            L5e:
                r3 = move-exception
                java.util.logging.Logger r4 = com.google.common.util.concurrent.t.e()     // Catch: java.lang.Throwable -> L5c
                java.util.logging.Level r5 = java.util.logging.Level.SEVERE     // Catch: java.lang.Throwable -> L5c
                java.lang.Runnable r6 = r9.h     // Catch: java.lang.Throwable -> L5c
                java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch: java.lang.Throwable -> L5c
                int r7 = r6.length()     // Catch: java.lang.Throwable -> L5c
                int r7 = r7 + 35
                java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L5c
                r8.<init>(r7)     // Catch: java.lang.Throwable -> L5c
                java.lang.String r7 = "Exception while executing runnable "
                r8.append(r7)     // Catch: java.lang.Throwable -> L5c
                r8.append(r6)     // Catch: java.lang.Throwable -> L5c
                java.lang.String r6 = r8.toString()     // Catch: java.lang.Throwable -> L5c
                r4.log(r5, r6, r3)     // Catch: java.lang.Throwable -> L5c
                goto L59
            L86:
                r9.h = r2     // Catch: java.lang.Throwable -> L8c
                throw r0     // Catch: java.lang.Throwable -> L8c
            L89:
                r0 = move-exception
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L89
                throw r0     // Catch: java.lang.Throwable -> L8c
            L8c:
                r0 = move-exception
                if (r1 == 0) goto L96
                java.lang.Thread r1 = java.lang.Thread.currentThread()
                r1.interrupt()
            L96:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.t.b.a():void");
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                a();
            } catch (Error e) {
                synchronized (t.this.i) {
                    t.this.j = c.IDLE;
                    throw e;
                }
            }
        }

        public String toString() {
            Runnable runnable = this.h;
            if (runnable == null) {
                String valueOf = String.valueOf(t.this.j);
                StringBuilder sb = new StringBuilder(valueOf.length() + 32);
                sb.append("SequentialExecutorWorker{state=");
                sb.append(valueOf);
                sb.append("}");
                return sb.toString();
            }
            String valueOf2 = String.valueOf(runnable);
            StringBuilder sb2 = new StringBuilder(valueOf2.length() + 34);
            sb2.append("SequentialExecutorWorker{running=");
            sb2.append(valueOf2);
            sb2.append("}");
            return sb2.toString();
        }

        public /* synthetic */ b(t tVar, a aVar) {
            this();
        }
    }

    /* loaded from: classes10.dex */
    public enum c {
        IDLE,
        QUEUING,
        QUEUED,
        RUNNING
    }

    public t(Executor executor) {
        this.h = (Executor) Preconditions.checkNotNull(executor);
    }

    public static /* synthetic */ long d(t tVar) {
        long j = tVar.k;
        tVar.k = 1 + j;
        return j;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        c cVar;
        Preconditions.checkNotNull(runnable);
        synchronized (this.i) {
            c cVar2 = this.j;
            if (cVar2 != c.RUNNING && cVar2 != (cVar = c.QUEUED)) {
                long j = this.k;
                a aVar = new a(this, runnable);
                this.i.add(aVar);
                c cVar3 = c.QUEUING;
                this.j = cVar3;
                try {
                    this.h.execute(this.l);
                    if (this.j != cVar3) {
                        return;
                    }
                    synchronized (this.i) {
                        if (this.k == j && this.j == cVar3) {
                            this.j = cVar;
                        }
                    }
                    return;
                } catch (Error | RuntimeException e) {
                    synchronized (this.i) {
                        c cVar4 = this.j;
                        if ((cVar4 != c.IDLE && cVar4 != c.QUEUING) || !this.i.removeLastOccurrence(aVar)) {
                            r0 = false;
                        }
                        if (!(e instanceof RejectedExecutionException) || r0) {
                            throw e;
                        }
                    }
                    return;
                }
            }
            this.i.add(runnable);
        }
    }

    public String toString() {
        int identityHashCode = System.identityHashCode(this);
        String valueOf = String.valueOf(this.h);
        StringBuilder sb = new StringBuilder(valueOf.length() + 32);
        sb.append("SequentialExecutor@");
        sb.append(identityHashCode);
        sb.append("{");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}

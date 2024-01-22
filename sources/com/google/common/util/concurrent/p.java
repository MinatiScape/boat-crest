package com.google.common.util.concurrent;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Queues;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import com.google.firebase.messaging.Constants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;
@GwtIncompatible
/* loaded from: classes10.dex */
public final class p<L> {
    public static final Logger b = Logger.getLogger(p.class.getName());

    /* renamed from: a  reason: collision with root package name */
    public final List<b<L>> f10811a = Collections.synchronizedList(new ArrayList());

    /* loaded from: classes10.dex */
    public interface a<L> {
        void call(L l);
    }

    /* loaded from: classes10.dex */
    public static final class b<L> implements Runnable {
        public final L h;
        public final Executor i;
        @GuardedBy("this")
        public final Queue<a<L>> j = Queues.newArrayDeque();
        @GuardedBy("this")
        public final Queue<Object> k = Queues.newArrayDeque();
        @GuardedBy("this")
        public boolean l;

        public b(L l, Executor executor) {
            this.h = (L) Preconditions.checkNotNull(l);
            this.i = (Executor) Preconditions.checkNotNull(executor);
        }

        public synchronized void a(a<L> aVar, Object obj) {
            this.j.add(aVar);
            this.k.add(obj);
        }

        public void b() {
            boolean z;
            synchronized (this) {
                z = true;
                if (this.l) {
                    z = false;
                } else {
                    this.l = true;
                }
            }
            if (z) {
                try {
                    this.i.execute(this);
                } catch (RuntimeException e) {
                    synchronized (this) {
                        this.l = false;
                        Logger logger = p.b;
                        Level level = Level.SEVERE;
                        String valueOf = String.valueOf(this.h);
                        String valueOf2 = String.valueOf(this.i);
                        StringBuilder sb = new StringBuilder(valueOf.length() + 42 + valueOf2.length());
                        sb.append("Exception while running callbacks for ");
                        sb.append(valueOf);
                        sb.append(" on ");
                        sb.append(valueOf2);
                        logger.log(level, sb.toString(), (Throwable) e);
                        throw e;
                    }
                }
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:12:0x0020, code lost:
            r2.call(r10.h);
         */
        /* JADX WARN: Code restructure failed: missing block: B:14:0x0026, code lost:
            r2 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:15:0x0027, code lost:
            r4 = com.google.common.util.concurrent.p.b;
            r5 = java.util.logging.Level.SEVERE;
            r6 = java.lang.String.valueOf(r10.h);
            r3 = java.lang.String.valueOf(r3);
            r8 = new java.lang.StringBuilder((r6.length() + 37) + r3.length());
            r8.append("Exception while executing callback: ");
            r8.append(r6);
            r8.append(com.goodix.ble.libcomx.util.HexStringBuilder.DEFAULT_SEPARATOR);
            r8.append(r3);
            r4.log(r5, r8.toString(), (java.lang.Throwable) r2);
         */
        /* JADX WARN: Removed duplicated region for block: B:27:0x006f  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                r10 = this;
            L0:
                r0 = 0
                r1 = 1
                monitor-enter(r10)     // Catch: java.lang.Throwable -> L6c
                boolean r2 = r10.l     // Catch: java.lang.Throwable -> L5f
                com.google.common.base.Preconditions.checkState(r2)     // Catch: java.lang.Throwable -> L5f
                java.util.Queue<com.google.common.util.concurrent.p$a<L>> r2 = r10.j     // Catch: java.lang.Throwable -> L5f
                java.lang.Object r2 = r2.poll()     // Catch: java.lang.Throwable -> L5f
                com.google.common.util.concurrent.p$a r2 = (com.google.common.util.concurrent.p.a) r2     // Catch: java.lang.Throwable -> L5f
                java.util.Queue<java.lang.Object> r3 = r10.k     // Catch: java.lang.Throwable -> L5f
                java.lang.Object r3 = r3.poll()     // Catch: java.lang.Throwable -> L5f
                if (r2 != 0) goto L1f
                r10.l = r0     // Catch: java.lang.Throwable -> L5f
                monitor-exit(r10)     // Catch: java.lang.Throwable -> L1c
                return
            L1c:
                r1 = move-exception
                r2 = r0
                goto L63
            L1f:
                monitor-exit(r10)     // Catch: java.lang.Throwable -> L5f
                L r4 = r10.h     // Catch: java.lang.RuntimeException -> L26 java.lang.Throwable -> L6c
                r2.call(r4)     // Catch: java.lang.RuntimeException -> L26 java.lang.Throwable -> L6c
                goto L0
            L26:
                r2 = move-exception
                java.util.logging.Logger r4 = com.google.common.util.concurrent.p.a()     // Catch: java.lang.Throwable -> L6c
                java.util.logging.Level r5 = java.util.logging.Level.SEVERE     // Catch: java.lang.Throwable -> L6c
                L r6 = r10.h     // Catch: java.lang.Throwable -> L6c
                java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch: java.lang.Throwable -> L6c
                java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch: java.lang.Throwable -> L6c
                int r7 = r6.length()     // Catch: java.lang.Throwable -> L6c
                int r7 = r7 + 37
                int r8 = r3.length()     // Catch: java.lang.Throwable -> L6c
                int r7 = r7 + r8
                java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6c
                r8.<init>(r7)     // Catch: java.lang.Throwable -> L6c
                java.lang.String r7 = "Exception while executing callback: "
                r8.append(r7)     // Catch: java.lang.Throwable -> L6c
                r8.append(r6)     // Catch: java.lang.Throwable -> L6c
                java.lang.String r6 = " "
                r8.append(r6)     // Catch: java.lang.Throwable -> L6c
                r8.append(r3)     // Catch: java.lang.Throwable -> L6c
                java.lang.String r3 = r8.toString()     // Catch: java.lang.Throwable -> L6c
                r4.log(r5, r3, r2)     // Catch: java.lang.Throwable -> L6c
                goto L0
            L5f:
                r2 = move-exception
                r9 = r2
                r2 = r1
                r1 = r9
            L63:
                monitor-exit(r10)     // Catch: java.lang.Throwable -> L6a
                throw r1     // Catch: java.lang.Throwable -> L65
            L65:
                r1 = move-exception
                r9 = r2
                r2 = r1
                r1 = r9
                goto L6d
            L6a:
                r1 = move-exception
                goto L63
            L6c:
                r2 = move-exception
            L6d:
                if (r1 == 0) goto L77
                monitor-enter(r10)
                r10.l = r0     // Catch: java.lang.Throwable -> L74
                monitor-exit(r10)     // Catch: java.lang.Throwable -> L74
                goto L77
            L74:
                r0 = move-exception
                monitor-exit(r10)     // Catch: java.lang.Throwable -> L74
                throw r0
            L77:
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.p.b.run():void");
        }
    }

    public void b(L l, Executor executor) {
        Preconditions.checkNotNull(l, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        Preconditions.checkNotNull(executor, "executor");
        this.f10811a.add(new b<>(l, executor));
    }

    public void c() {
        for (int i = 0; i < this.f10811a.size(); i++) {
            this.f10811a.get(i).b();
        }
    }

    public void d(a<L> aVar) {
        e(aVar, aVar);
    }

    public final void e(a<L> aVar, Object obj) {
        Preconditions.checkNotNull(aVar, "event");
        Preconditions.checkNotNull(obj, Constants.ScionAnalytics.PARAM_LABEL);
        synchronized (this.f10811a) {
            for (b<L> bVar : this.f10811a) {
                bVar.a(aVar, obj);
            }
        }
    }
}

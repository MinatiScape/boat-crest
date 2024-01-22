package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Longs;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import com.google.j2objc.annotations.Weak;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
@GwtIncompatible
/* loaded from: classes10.dex */
public final class Monitor {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f10792a;
    public final ReentrantLock b;
    @GuardedBy("lock")
    public Guard c;

    @Beta
    /* loaded from: classes10.dex */
    public static abstract class Guard {
        @Weak

        /* renamed from: a  reason: collision with root package name */
        public final Monitor f10793a;
        public final Condition b;
        @GuardedBy("monitor.lock")
        public int c = 0;
        @NullableDecl
        @GuardedBy("monitor.lock")
        public Guard d;

        public Guard(Monitor monitor) {
            this.f10793a = (Monitor) Preconditions.checkNotNull(monitor, "monitor");
            this.b = monitor.b.newCondition();
        }

        public abstract boolean isSatisfied();
    }

    public Monitor() {
        this(false);
    }

    public static long g(long j) {
        if (j <= 0) {
            return 0L;
        }
        long nanoTime = System.nanoTime();
        if (nanoTime == 0) {
            return 1L;
        }
        return nanoTime;
    }

    public static long i(long j, long j2) {
        if (j2 <= 0) {
            return 0L;
        }
        return j2 - (System.nanoTime() - j);
    }

    public static long l(long j, TimeUnit timeUnit) {
        return Longs.constrainToRange(timeUnit.toNanos(j), 0L, 6917529027641081853L);
    }

    @GuardedBy("lock")
    public final void b(Guard guard, boolean z) throws InterruptedException {
        if (z) {
            k();
        }
        e(guard);
        do {
            try {
                guard.b.await();
            } finally {
                f(guard);
            }
        } while (!guard.isSatisfied());
    }

    @GuardedBy("lock")
    public final boolean c(Guard guard, long j, boolean z) throws InterruptedException {
        boolean z2 = true;
        while (j > 0) {
            if (z2) {
                if (z) {
                    try {
                        k();
                    } finally {
                        if (!z2) {
                            f(guard);
                        }
                    }
                }
                e(guard);
                z2 = false;
            }
            j = guard.b.awaitNanos(j);
            if (guard.isSatisfied()) {
                if (!z2) {
                    f(guard);
                }
                return true;
            }
        }
        return false;
    }

    @GuardedBy("lock")
    public final void d(Guard guard, boolean z) {
        if (z) {
            k();
        }
        e(guard);
        do {
            try {
                guard.b.awaitUninterruptibly();
            } finally {
                f(guard);
            }
        } while (!guard.isSatisfied());
    }

    @GuardedBy("lock")
    public final void e(Guard guard) {
        int i = guard.c;
        guard.c = i + 1;
        if (i == 0) {
            guard.d = this.c;
            this.c = guard;
        }
    }

    public void enter() {
        this.b.lock();
    }

    public boolean enterIf(Guard guard) {
        if (guard.f10793a == this) {
            ReentrantLock reentrantLock = this.b;
            reentrantLock.lock();
            try {
                boolean isSatisfied = guard.isSatisfied();
                if (!isSatisfied) {
                }
                return isSatisfied;
            } finally {
                reentrantLock.unlock();
            }
        }
        throw new IllegalMonitorStateException();
    }

    public boolean enterIfInterruptibly(Guard guard) throws InterruptedException {
        if (guard.f10793a == this) {
            ReentrantLock reentrantLock = this.b;
            reentrantLock.lockInterruptibly();
            try {
                boolean isSatisfied = guard.isSatisfied();
                if (!isSatisfied) {
                }
                return isSatisfied;
            } finally {
                reentrantLock.unlock();
            }
        }
        throw new IllegalMonitorStateException();
    }

    public void enterInterruptibly() throws InterruptedException {
        this.b.lockInterruptibly();
    }

    public void enterWhen(Guard guard) throws InterruptedException {
        if (guard.f10793a == this) {
            ReentrantLock reentrantLock = this.b;
            boolean isHeldByCurrentThread = reentrantLock.isHeldByCurrentThread();
            reentrantLock.lockInterruptibly();
            try {
                if (guard.isSatisfied()) {
                    return;
                }
                b(guard, isHeldByCurrentThread);
                return;
            } catch (Throwable th) {
                leave();
                throw th;
            }
        }
        throw new IllegalMonitorStateException();
    }

    public void enterWhenUninterruptibly(Guard guard) {
        if (guard.f10793a == this) {
            ReentrantLock reentrantLock = this.b;
            boolean isHeldByCurrentThread = reentrantLock.isHeldByCurrentThread();
            reentrantLock.lock();
            try {
                if (guard.isSatisfied()) {
                    return;
                }
                d(guard, isHeldByCurrentThread);
                return;
            } catch (Throwable th) {
                leave();
                throw th;
            }
        }
        throw new IllegalMonitorStateException();
    }

    @GuardedBy("lock")
    public final void f(Guard guard) {
        int i = guard.c - 1;
        guard.c = i;
        if (i == 0) {
            Guard guard2 = this.c;
            Guard guard3 = null;
            while (guard2 != guard) {
                guard3 = guard2;
                guard2 = guard2.d;
            }
            if (guard3 == null) {
                this.c = guard2.d;
            } else {
                guard3.d = guard2.d;
            }
            guard2.d = null;
        }
    }

    public int getOccupiedDepth() {
        return this.b.getHoldCount();
    }

    public int getQueueLength() {
        return this.b.getQueueLength();
    }

    public int getWaitQueueLength(Guard guard) {
        if (guard.f10793a == this) {
            this.b.lock();
            try {
                return guard.c;
            } finally {
                this.b.unlock();
            }
        }
        throw new IllegalMonitorStateException();
    }

    @GuardedBy("lock")
    public final boolean h(Guard guard) {
        try {
            return guard.isSatisfied();
        } catch (Throwable th) {
            j();
            throw th;
        }
    }

    public boolean hasQueuedThread(Thread thread) {
        return this.b.hasQueuedThread(thread);
    }

    public boolean hasQueuedThreads() {
        return this.b.hasQueuedThreads();
    }

    public boolean hasWaiters(Guard guard) {
        return getWaitQueueLength(guard) > 0;
    }

    public boolean isFair() {
        return this.f10792a;
    }

    public boolean isOccupied() {
        return this.b.isLocked();
    }

    public boolean isOccupiedByCurrentThread() {
        return this.b.isHeldByCurrentThread();
    }

    @GuardedBy("lock")
    public final void j() {
        for (Guard guard = this.c; guard != null; guard = guard.d) {
            guard.b.signalAll();
        }
    }

    @GuardedBy("lock")
    public final void k() {
        for (Guard guard = this.c; guard != null; guard = guard.d) {
            if (h(guard)) {
                guard.b.signal();
                return;
            }
        }
    }

    public void leave() {
        ReentrantLock reentrantLock = this.b;
        try {
            if (reentrantLock.getHoldCount() == 1) {
                k();
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean tryEnter() {
        return this.b.tryLock();
    }

    public boolean tryEnterIf(Guard guard) {
        if (guard.f10793a == this) {
            ReentrantLock reentrantLock = this.b;
            if (reentrantLock.tryLock()) {
                try {
                    boolean isSatisfied = guard.isSatisfied();
                    if (!isSatisfied) {
                    }
                    return isSatisfied;
                } finally {
                    reentrantLock.unlock();
                }
            }
            return false;
        }
        throw new IllegalMonitorStateException();
    }

    public void waitFor(Guard guard) throws InterruptedException {
        if ((guard.f10793a == this) & this.b.isHeldByCurrentThread()) {
            if (guard.isSatisfied()) {
                return;
            }
            b(guard, true);
            return;
        }
        throw new IllegalMonitorStateException();
    }

    public void waitForUninterruptibly(Guard guard) {
        if ((guard.f10793a == this) & this.b.isHeldByCurrentThread()) {
            if (guard.isSatisfied()) {
                return;
            }
            d(guard, true);
            return;
        }
        throw new IllegalMonitorStateException();
    }

    public Monitor(boolean z) {
        this.c = null;
        this.f10792a = z;
        this.b = new ReentrantLock(z);
    }

    public boolean enter(long j, TimeUnit timeUnit) {
        boolean tryLock;
        long l = l(j, timeUnit);
        ReentrantLock reentrantLock = this.b;
        boolean z = true;
        if (!this.f10792a && reentrantLock.tryLock()) {
            return true;
        }
        boolean interrupted = Thread.interrupted();
        try {
            long nanoTime = System.nanoTime();
            long j2 = l;
            while (true) {
                try {
                    try {
                        tryLock = reentrantLock.tryLock(j2, TimeUnit.NANOSECONDS);
                        break;
                    } catch (Throwable th) {
                        th = th;
                        if (z) {
                            Thread.currentThread().interrupt();
                        }
                        throw th;
                    }
                } catch (InterruptedException unused) {
                    j2 = i(nanoTime, l);
                    interrupted = true;
                }
            }
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
            return tryLock;
        } catch (Throwable th2) {
            th = th2;
            z = interrupted;
        }
    }

    public boolean enterInterruptibly(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.b.tryLock(j, timeUnit);
    }

    public boolean waitFor(Guard guard, long j, TimeUnit timeUnit) throws InterruptedException {
        long l = l(j, timeUnit);
        if ((guard.f10793a == this) & this.b.isHeldByCurrentThread()) {
            if (guard.isSatisfied()) {
                return true;
            }
            if (!Thread.interrupted()) {
                return c(guard, l, true);
            }
            throw new InterruptedException();
        }
        throw new IllegalMonitorStateException();
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean waitForUninterruptibly(com.google.common.util.concurrent.Monitor.Guard r8, long r9, java.util.concurrent.TimeUnit r11) {
        /*
            r7 = this;
            long r9 = l(r9, r11)
            com.google.common.util.concurrent.Monitor r11 = r8.f10793a
            r0 = 0
            r1 = 1
            if (r11 != r7) goto Lc
            r11 = r1
            goto Ld
        Lc:
            r11 = r0
        Ld:
            java.util.concurrent.locks.ReentrantLock r2 = r7.b
            boolean r2 = r2.isHeldByCurrentThread()
            r11 = r11 & r2
            if (r11 == 0) goto L58
            boolean r11 = r8.isSatisfied()
            if (r11 == 0) goto L1d
            return r1
        L1d:
            long r2 = g(r9)
            boolean r11 = java.lang.Thread.interrupted()
            r4 = r9
            r6 = r1
        L27:
            boolean r8 = r7.c(r8, r4, r6)     // Catch: java.lang.Throwable -> L35 java.lang.InterruptedException -> L38
            if (r11 == 0) goto L34
            java.lang.Thread r9 = java.lang.Thread.currentThread()
            r9.interrupt()
        L34:
            return r8
        L35:
            r8 = move-exception
            r1 = r11
            goto L4e
        L38:
            boolean r11 = r8.isSatisfied()     // Catch: java.lang.Throwable -> L4d
            if (r11 == 0) goto L46
            java.lang.Thread r8 = java.lang.Thread.currentThread()
            r8.interrupt()
            return r1
        L46:
            long r4 = i(r2, r9)     // Catch: java.lang.Throwable -> L4d
            r6 = r0
            r11 = r1
            goto L27
        L4d:
            r8 = move-exception
        L4e:
            if (r1 == 0) goto L57
            java.lang.Thread r9 = java.lang.Thread.currentThread()
            r9.interrupt()
        L57:
            throw r8
        L58:
            java.lang.IllegalMonitorStateException r8 = new java.lang.IllegalMonitorStateException
            r8.<init>()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.Monitor.waitForUninterruptibly(com.google.common.util.concurrent.Monitor$Guard, long, java.util.concurrent.TimeUnit):boolean");
    }

    public boolean enterIf(Guard guard, long j, TimeUnit timeUnit) {
        if (guard.f10793a == this) {
            if (enter(j, timeUnit)) {
                try {
                    boolean isSatisfied = guard.isSatisfied();
                    if (!isSatisfied) {
                    }
                    return isSatisfied;
                } finally {
                    this.b.unlock();
                }
            }
            return false;
        }
        throw new IllegalMonitorStateException();
    }

    public boolean enterIfInterruptibly(Guard guard, long j, TimeUnit timeUnit) throws InterruptedException {
        if (guard.f10793a == this) {
            ReentrantLock reentrantLock = this.b;
            if (reentrantLock.tryLock(j, timeUnit)) {
                try {
                    boolean isSatisfied = guard.isSatisfied();
                    if (!isSatisfied) {
                    }
                    return isSatisfied;
                } finally {
                    reentrantLock.unlock();
                }
            }
            return false;
        }
        throw new IllegalMonitorStateException();
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0047, code lost:
        if (c(r11, r0, r3) != false) goto L24;
     */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004c A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean enterWhen(com.google.common.util.concurrent.Monitor.Guard r11, long r12, java.util.concurrent.TimeUnit r14) throws java.lang.InterruptedException {
        /*
            r10 = this;
            long r0 = l(r12, r14)
            com.google.common.util.concurrent.Monitor r2 = r11.f10793a
            if (r2 != r10) goto L60
            java.util.concurrent.locks.ReentrantLock r2 = r10.b
            boolean r3 = r2.isHeldByCurrentThread()
            boolean r4 = r10.f10792a
            r5 = 0
            r6 = 0
            if (r4 != 0) goto L29
            boolean r4 = java.lang.Thread.interrupted()
            if (r4 != 0) goto L23
            boolean r4 = r2.tryLock()
            if (r4 == 0) goto L29
            r8 = r6
            goto L34
        L23:
            java.lang.InterruptedException r11 = new java.lang.InterruptedException
            r11.<init>()
            throw r11
        L29:
            long r8 = g(r0)
            boolean r12 = r2.tryLock(r12, r14)
            if (r12 != 0) goto L34
            return r5
        L34:
            boolean r12 = r11.isSatisfied()     // Catch: java.lang.Throwable -> L50
            if (r12 != 0) goto L49
            int r12 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r12 != 0) goto L3f
            goto L43
        L3f:
            long r0 = i(r8, r0)     // Catch: java.lang.Throwable -> L50
        L43:
            boolean r11 = r10.c(r11, r0, r3)     // Catch: java.lang.Throwable -> L50
            if (r11 == 0) goto L4a
        L49:
            r5 = 1
        L4a:
            if (r5 != 0) goto L4f
            r2.unlock()
        L4f:
            return r5
        L50:
            r11 = move-exception
            if (r3 != 0) goto L5c
            r10.k()     // Catch: java.lang.Throwable -> L57
            goto L5c
        L57:
            r11 = move-exception
            r2.unlock()
            throw r11
        L5c:
            r2.unlock()
            throw r11
        L60:
            java.lang.IllegalMonitorStateException r11 = new java.lang.IllegalMonitorStateException
            r11.<init>()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.Monitor.enterWhen(com.google.common.util.concurrent.Monitor$Guard, long, java.util.concurrent.TimeUnit):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004b A[Catch: all -> 0x0073, TRY_ENTER, TRY_LEAVE, TryCatch #1 {all -> 0x0073, blocks: (B:5:0x0013, B:7:0x001a, B:24:0x004b, B:11:0x0023, B:13:0x0028, B:15:0x0030, B:20:0x003b, B:22:0x0045, B:21:0x0041), top: B:45:0x0013 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean enterWhenUninterruptibly(com.google.common.util.concurrent.Monitor.Guard r12, long r13, java.util.concurrent.TimeUnit r15) {
        /*
            r11 = this;
            long r13 = l(r13, r15)
            com.google.common.util.concurrent.Monitor r15 = r12.f10793a
            if (r15 != r11) goto L7e
            java.util.concurrent.locks.ReentrantLock r15 = r11.b
            boolean r0 = r15.isHeldByCurrentThread()
            boolean r1 = java.lang.Thread.interrupted()
            r2 = 1
            boolean r3 = r11.f10792a     // Catch: java.lang.Throwable -> L73
            r4 = 0
            r5 = 0
            if (r3 != 0) goto L23
            boolean r3 = r15.tryLock()     // Catch: java.lang.Throwable -> L73
            if (r3 != 0) goto L21
            goto L23
        L21:
            r7 = r5
            goto L30
        L23:
            long r7 = g(r13)     // Catch: java.lang.Throwable -> L73
            r9 = r13
        L28:
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.NANOSECONDS     // Catch: java.lang.InterruptedException -> L6a java.lang.Throwable -> L73
            boolean r3 = r15.tryLock(r9, r3)     // Catch: java.lang.InterruptedException -> L6a java.lang.Throwable -> L73
            if (r3 == 0) goto L60
        L30:
            boolean r3 = r12.isSatisfied()     // Catch: java.lang.Throwable -> L58 java.lang.InterruptedException -> L5d
            if (r3 == 0) goto L37
            goto L49
        L37:
            int r3 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r3 != 0) goto L41
            long r7 = g(r13)     // Catch: java.lang.Throwable -> L58 java.lang.InterruptedException -> L5d
            r9 = r13
            goto L45
        L41:
            long r9 = i(r7, r13)     // Catch: java.lang.Throwable -> L58 java.lang.InterruptedException -> L5d
        L45:
            boolean r2 = r11.c(r12, r9, r0)     // Catch: java.lang.Throwable -> L58 java.lang.InterruptedException -> L5d
        L49:
            if (r2 != 0) goto L4e
            r15.unlock()     // Catch: java.lang.Throwable -> L73
        L4e:
            if (r1 == 0) goto L57
            java.lang.Thread r12 = java.lang.Thread.currentThread()
            r12.interrupt()
        L57:
            return r2
        L58:
            r12 = move-exception
            r15.unlock()     // Catch: java.lang.Throwable -> L73
            throw r12     // Catch: java.lang.Throwable -> L73
        L5d:
            r1 = r2
            r0 = r4
            goto L30
        L60:
            if (r1 == 0) goto L69
            java.lang.Thread r12 = java.lang.Thread.currentThread()
            r12.interrupt()
        L69:
            return r4
        L6a:
            long r9 = i(r7, r13)     // Catch: java.lang.Throwable -> L70
            r1 = r2
            goto L28
        L70:
            r12 = move-exception
            r1 = r2
            goto L74
        L73:
            r12 = move-exception
        L74:
            if (r1 == 0) goto L7d
            java.lang.Thread r13 = java.lang.Thread.currentThread()
            r13.interrupt()
        L7d:
            throw r12
        L7e:
            java.lang.IllegalMonitorStateException r12 = new java.lang.IllegalMonitorStateException
            r12.<init>()
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.Monitor.enterWhenUninterruptibly(com.google.common.util.concurrent.Monitor$Guard, long, java.util.concurrent.TimeUnit):boolean");
    }
}

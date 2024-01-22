package rx.internal.util;

import java.util.concurrent.atomic.AtomicLong;
import rx.Producer;
/* loaded from: classes13.dex */
public final class BackpressureDrainManager extends AtomicLong implements Producer {
    private static final long serialVersionUID = 2826241102729529449L;
    public final BackpressureQueueCallback actual;
    public boolean emitting;
    public Throwable exception;
    public volatile boolean terminated;

    /* loaded from: classes13.dex */
    public interface BackpressureQueueCallback {
        boolean accept(Object obj);

        void complete(Throwable th);

        Object peek();

        Object poll();
    }

    public BackpressureDrainManager(BackpressureQueueCallback backpressureQueueCallback) {
        this.actual = backpressureQueueCallback;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x002e, code lost:
        if (r9 == 0) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0037, code lost:
        monitor-enter(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0038, code lost:
        r1 = r13.terminated;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x003e, code lost:
        if (r5.peek() == null) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0040, code lost:
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0042, code lost:
        r2 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x004e, code lost:
        if (get() != Long.MAX_VALUE) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0050, code lost:
        if (r2 != false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0052, code lost:
        if (r1 != false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0054, code lost:
        r13.emitting = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0056, code lost:
        monitor-exit(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0057, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0058, code lost:
        r2 = Long.MAX_VALUE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x005c, code lost:
        r9 = addAndGet(-r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0062, code lost:
        if (r9 == 0) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0064, code lost:
        if (r2 != false) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0066, code lost:
        if (r1 == false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0068, code lost:
        if (r2 == false) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x006b, code lost:
        r2 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x006e, code lost:
        r13.emitting = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0070, code lost:
        monitor-exit(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0071, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0072, code lost:
        r1 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0074, code lost:
        monitor-exit(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0075, code lost:
        throw r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void drain() {
        /*
            r13 = this;
            monitor-enter(r13)
            boolean r0 = r13.emitting     // Catch: java.lang.Throwable -> L92
            if (r0 == 0) goto L7
            monitor-exit(r13)     // Catch: java.lang.Throwable -> L92
            return
        L7:
            r0 = 1
            r13.emitting = r0     // Catch: java.lang.Throwable -> L92
            boolean r1 = r13.terminated     // Catch: java.lang.Throwable -> L92
            monitor-exit(r13)     // Catch: java.lang.Throwable -> L92
            long r2 = r13.get()
            r4 = 0
            rx.internal.util.BackpressureDrainManager$BackpressureQueueCallback r5 = r13.actual     // Catch: java.lang.Throwable -> L85
        L14:
            r6 = r4
        L15:
            r7 = 0
            int r9 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r9 > 0) goto L1d
            if (r1 == 0) goto L37
        L1d:
            if (r1 == 0) goto L31
            java.lang.Object r10 = r5.peek()     // Catch: java.lang.Throwable -> L85
            if (r10 != 0) goto L2e
            java.lang.Throwable r1 = r13.exception     // Catch: java.lang.Throwable -> L2b
            r5.complete(r1)     // Catch: java.lang.Throwable -> L2b
            return
        L2b:
            r1 = move-exception
            goto L87
        L2e:
            if (r9 != 0) goto L31
            goto L37
        L31:
            java.lang.Object r9 = r5.poll()     // Catch: java.lang.Throwable -> L85
            if (r9 != 0) goto L78
        L37:
            monitor-enter(r13)     // Catch: java.lang.Throwable -> L85
            boolean r1 = r13.terminated     // Catch: java.lang.Throwable -> L72
            java.lang.Object r2 = r5.peek()     // Catch: java.lang.Throwable -> L72
            if (r2 == 0) goto L42
            r2 = r0
            goto L43
        L42:
            r2 = r4
        L43:
            long r9 = r13.get()     // Catch: java.lang.Throwable -> L72
            r11 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            int r3 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r3 != 0) goto L5a
            if (r2 != 0) goto L58
            if (r1 != 0) goto L58
            r13.emitting = r4     // Catch: java.lang.Throwable -> L76
            monitor-exit(r13)     // Catch: java.lang.Throwable -> L76
            return
        L58:
            r2 = r11
            goto L6c
        L5a:
            int r3 = -r6
            long r9 = (long) r3
            long r9 = r13.addAndGet(r9)     // Catch: java.lang.Throwable -> L72
            int r3 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r3 == 0) goto L66
            if (r2 != 0) goto L6b
        L66:
            if (r1 == 0) goto L6e
            if (r2 == 0) goto L6b
            goto L6e
        L6b:
            r2 = r9
        L6c:
            monitor-exit(r13)     // Catch: java.lang.Throwable -> L72
            goto L14
        L6e:
            r13.emitting = r4     // Catch: java.lang.Throwable -> L76
            monitor-exit(r13)     // Catch: java.lang.Throwable -> L76
            return
        L72:
            r1 = move-exception
            r0 = r4
        L74:
            monitor-exit(r13)     // Catch: java.lang.Throwable -> L76
            throw r1     // Catch: java.lang.Throwable -> L2b
        L76:
            r1 = move-exception
            goto L74
        L78:
            boolean r7 = r5.accept(r9)     // Catch: java.lang.Throwable -> L85
            if (r7 == 0) goto L7f
            return
        L7f:
            r7 = 1
            long r2 = r2 - r7
            int r6 = r6 + 1
            goto L15
        L85:
            r1 = move-exception
            r0 = r4
        L87:
            if (r0 != 0) goto L91
            monitor-enter(r13)
            r13.emitting = r4     // Catch: java.lang.Throwable -> L8e
            monitor-exit(r13)     // Catch: java.lang.Throwable -> L8e
            goto L91
        L8e:
            r0 = move-exception
            monitor-exit(r13)     // Catch: java.lang.Throwable -> L8e
            throw r0
        L91:
            throw r1
        L92:
            r0 = move-exception
            monitor-exit(r13)     // Catch: java.lang.Throwable -> L92
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.internal.util.BackpressureDrainManager.drain():void");
    }

    public boolean isTerminated() {
        return this.terminated;
    }

    @Override // rx.Producer
    public void request(long j) {
        boolean z;
        long j2;
        if (j == 0) {
            return;
        }
        while (true) {
            long j3 = get();
            boolean z2 = true;
            z = j3 == 0;
            if (j3 == Long.MAX_VALUE) {
                break;
            }
            if (j == Long.MAX_VALUE) {
                j2 = j;
            } else {
                j2 = j3 <= Long.MAX_VALUE - j ? j3 + j : Long.MAX_VALUE;
                z2 = z;
            }
            if (compareAndSet(j3, j2)) {
                z = z2;
                break;
            }
        }
        if (z) {
            drain();
        }
    }

    public void terminate() {
        this.terminated = true;
    }

    public void terminateAndDrain() {
        this.terminated = true;
        drain();
    }

    public void terminate(Throwable th) {
        if (this.terminated) {
            return;
        }
        this.exception = th;
        this.terminated = true;
    }

    public void terminateAndDrain(Throwable th) {
        if (this.terminated) {
            return;
        }
        this.exception = th;
        this.terminated = true;
        drain();
    }
}

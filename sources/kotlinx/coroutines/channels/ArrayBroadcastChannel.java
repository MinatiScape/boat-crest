package kotlinx.coroutines.channels;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Unit;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.internal.ConcurrentKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class ArrayBroadcastChannel<E> extends AbstractSendChannel<E> implements BroadcastChannel<E> {
    @NotNull
    private volatile /* synthetic */ long _head;
    @NotNull
    private volatile /* synthetic */ int _size;
    @NotNull
    private volatile /* synthetic */ long _tail;
    public final int j;
    @NotNull
    public final ReentrantLock k;
    @NotNull
    public final Object[] l;
    @NotNull
    public final List<a<E>> m;

    /* loaded from: classes12.dex */
    public static final class a<E> extends AbstractChannel<E> implements ReceiveChannel<E> {
        @NotNull
        private volatile /* synthetic */ long _subHead;
        @NotNull
        public final ArrayBroadcastChannel<E> j;
        @NotNull
        public final ReentrantLock k;

        public a(@NotNull ArrayBroadcastChannel<E> arrayBroadcastChannel) {
            super(null);
            this.j = arrayBroadcastChannel;
            this.k = new ReentrantLock();
            this._subHead = 0L;
        }

        @Override // kotlinx.coroutines.channels.AbstractSendChannel, kotlinx.coroutines.channels.SendChannel
        public boolean close(@Nullable Throwable th) {
            boolean close = super.close(th);
            if (close) {
                ArrayBroadcastChannel.v(this.j, null, this, 1, null);
                ReentrantLock reentrantLock = this.k;
                reentrantLock.lock();
                try {
                    u(this.j.q());
                    Unit unit = Unit.INSTANCE;
                } finally {
                    reentrantLock.unlock();
                }
            }
            return close;
        }

        @Override // kotlinx.coroutines.channels.AbstractChannel
        public boolean isBufferAlwaysEmpty() {
            return false;
        }

        @Override // kotlinx.coroutines.channels.AbstractSendChannel
        public boolean isBufferAlwaysFull() {
            throw new IllegalStateException("Should not be used".toString());
        }

        @Override // kotlinx.coroutines.channels.AbstractChannel
        public boolean isBufferEmpty() {
            return r() >= this.j.q();
        }

        @Override // kotlinx.coroutines.channels.AbstractSendChannel
        public boolean isBufferFull() {
            throw new IllegalStateException("Should not be used".toString());
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x0028  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x002c  */
        /* JADX WARN: Removed duplicated region for block: B:17:0x0030  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x003c  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x003f  */
        @Override // kotlinx.coroutines.channels.AbstractChannel
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.Object pollInternal() {
            /*
                r8 = this;
                java.util.concurrent.locks.ReentrantLock r0 = r8.k
                r0.lock()
                java.lang.Object r1 = r8.t()     // Catch: java.lang.Throwable -> L46
                boolean r2 = r1 instanceof kotlinx.coroutines.channels.Closed     // Catch: java.lang.Throwable -> L46
                r3 = 1
                if (r2 == 0) goto Lf
                goto L13
            Lf:
                kotlinx.coroutines.internal.Symbol r2 = kotlinx.coroutines.channels.AbstractChannelKt.POLL_FAILED     // Catch: java.lang.Throwable -> L46
                if (r1 != r2) goto L15
            L13:
                r2 = 0
                goto L20
            L15:
                long r4 = r8.r()     // Catch: java.lang.Throwable -> L46
                r6 = 1
                long r4 = r4 + r6
                r8.u(r4)     // Catch: java.lang.Throwable -> L46
                r2 = r3
            L20:
                r0.unlock()
                boolean r0 = r1 instanceof kotlinx.coroutines.channels.Closed
                r4 = 0
                if (r0 == 0) goto L2c
                r0 = r1
                kotlinx.coroutines.channels.Closed r0 = (kotlinx.coroutines.channels.Closed) r0
                goto L2d
            L2c:
                r0 = r4
            L2d:
                if (r0 != 0) goto L30
                goto L35
            L30:
                java.lang.Throwable r0 = r0.closeCause
                r8.close(r0)
            L35:
                boolean r0 = r8.q()
                if (r0 == 0) goto L3c
                goto L3d
            L3c:
                r3 = r2
            L3d:
                if (r3 == 0) goto L45
                kotlinx.coroutines.channels.ArrayBroadcastChannel<E> r0 = r8.j
                r2 = 3
                kotlinx.coroutines.channels.ArrayBroadcastChannel.v(r0, r4, r4, r2, r4)
            L45:
                return r1
            L46:
                r1 = move-exception
                r0.unlock()
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ArrayBroadcastChannel.a.pollInternal():java.lang.Object");
        }

        @Override // kotlinx.coroutines.channels.AbstractChannel
        @Nullable
        public Object pollSelectInternal(@NotNull SelectInstance<?> selectInstance) {
            ReentrantLock reentrantLock = this.k;
            reentrantLock.lock();
            try {
                Object t = t();
                boolean z = false;
                if (!(t instanceof Closed) && t != AbstractChannelKt.POLL_FAILED) {
                    if (!selectInstance.trySelect()) {
                        t = SelectKt.getALREADY_SELECTED();
                    } else {
                        u(r() + 1);
                        z = true;
                    }
                }
                reentrantLock.unlock();
                Closed closed = t instanceof Closed ? (Closed) t : null;
                if (closed != null) {
                    close(closed.closeCause);
                }
                if (q() ? true : z) {
                    ArrayBroadcastChannel.v(this.j, null, null, 3, null);
                }
                return t;
            } catch (Throwable th) {
                reentrantLock.unlock();
                throw th;
            }
        }

        public final boolean q() {
            Closed closed;
            boolean z = false;
            while (true) {
                closed = null;
                if (!s() || !this.k.tryLock()) {
                    break;
                }
                try {
                    E e = (E) t();
                    if (e != AbstractChannelKt.POLL_FAILED) {
                        if (e instanceof Closed) {
                            closed = (Closed) e;
                            break;
                        }
                        ReceiveOrClosed<E> takeFirstReceiveOrPeekClosed = takeFirstReceiveOrPeekClosed();
                        if (takeFirstReceiveOrPeekClosed != null && !(takeFirstReceiveOrPeekClosed instanceof Closed)) {
                            Symbol tryResumeReceive = takeFirstReceiveOrPeekClosed.tryResumeReceive(e, null);
                            if (tryResumeReceive != null) {
                                if (DebugKt.getASSERTIONS_ENABLED()) {
                                    if (!(tryResumeReceive == CancellableContinuationImplKt.RESUME_TOKEN)) {
                                        throw new AssertionError();
                                    }
                                }
                                u(r() + 1);
                                this.k.unlock();
                                takeFirstReceiveOrPeekClosed.completeResumeReceive(e);
                                z = true;
                            }
                        }
                    }
                } finally {
                    this.k.unlock();
                }
            }
            if (closed != null) {
                close(closed.closeCause);
            }
            return z;
        }

        public final long r() {
            return this._subHead;
        }

        public final boolean s() {
            if (getClosedForReceive() != null) {
                return false;
            }
            return (isBufferEmpty() && this.j.getClosedForReceive() == null) ? false : true;
        }

        public final Object t() {
            long r = r();
            Closed<?> closedForReceive = this.j.getClosedForReceive();
            if (r >= this.j.q()) {
                if (closedForReceive == null) {
                    closedForReceive = getClosedForReceive();
                }
                return closedForReceive == null ? AbstractChannelKt.POLL_FAILED : closedForReceive;
            }
            Object n = this.j.n(r);
            Closed<?> closedForReceive2 = getClosedForReceive();
            return closedForReceive2 != null ? closedForReceive2 : n;
        }

        public final void u(long j) {
            this._subHead = j;
        }
    }

    public ArrayBroadcastChannel(int i) {
        super(null);
        this.j = i;
        if (i >= 1) {
            this.k = new ReentrantLock();
            this.l = new Object[i];
            this._head = 0L;
            this._tail = 0L;
            this._size = 0;
            this.m = ConcurrentKt.subscriberList();
            return;
        }
        throw new IllegalArgumentException(("ArrayBroadcastChannel capacity must be at least 1, but " + getCapacity() + " was specified").toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void v(ArrayBroadcastChannel arrayBroadcastChannel, a aVar, a aVar2, int i, Object obj) {
        if ((i & 1) != 0) {
            aVar = null;
        }
        if ((i & 2) != 0) {
            aVar2 = null;
        }
        arrayBroadcastChannel.u(aVar, aVar2);
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel, kotlinx.coroutines.channels.SendChannel
    public boolean close(@Nullable Throwable th) {
        if (super.close(th)) {
            l();
            return true;
        }
        return false;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    @NotNull
    public String getBufferDebugString() {
        return "(buffer:capacity=" + this.l.length + ",size=" + p() + HexStringBuilder.COMMENT_END_CHAR;
    }

    public final int getCapacity() {
        return this.j;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public boolean isBufferAlwaysFull() {
        return false;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public boolean isBufferFull() {
        return p() >= this.j;
    }

    @Override // kotlinx.coroutines.channels.BroadcastChannel
    /* renamed from: k */
    public final boolean cancel(Throwable th) {
        boolean close = close(th);
        for (a<E> aVar : this.m) {
            aVar.cancel(th);
        }
        return close;
    }

    public final void l() {
        boolean z;
        boolean z2 = false;
        loop0: while (true) {
            z = z2;
            for (a<E> aVar : this.m) {
                if (aVar.q()) {
                    break;
                }
                z = true;
            }
            z2 = true;
        }
        if (z2 || !z) {
            v(this, null, null, 3, null);
        }
    }

    public final long m() {
        long j = Long.MAX_VALUE;
        for (a<E> aVar : this.m) {
            j = kotlin.ranges.h.coerceAtMost(j, aVar.r());
        }
        return j;
    }

    public final E n(long j) {
        return (E) this.l[(int) (j % this.j)];
    }

    public final long o() {
        return this._head;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    @NotNull
    public Object offerInternal(E e) {
        ReentrantLock reentrantLock = this.k;
        reentrantLock.lock();
        try {
            Closed<?> closedForSend = getClosedForSend();
            if (closedForSend == null) {
                int p = p();
                if (p >= getCapacity()) {
                    return AbstractChannelKt.OFFER_FAILED;
                }
                long q = q();
                this.l[(int) (q % getCapacity())] = e;
                s(p + 1);
                t(q + 1);
                Unit unit = Unit.INSTANCE;
                reentrantLock.unlock();
                l();
                return AbstractChannelKt.OFFER_SUCCESS;
            }
            return closedForSend;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    @NotNull
    public Object offerSelectInternal(E e, @NotNull SelectInstance<?> selectInstance) {
        ReentrantLock reentrantLock = this.k;
        reentrantLock.lock();
        try {
            Closed<?> closedForSend = getClosedForSend();
            if (closedForSend == null) {
                int p = p();
                if (p >= getCapacity()) {
                    return AbstractChannelKt.OFFER_FAILED;
                }
                if (!selectInstance.trySelect()) {
                    return SelectKt.getALREADY_SELECTED();
                }
                long q = q();
                this.l[(int) (q % getCapacity())] = e;
                s(p + 1);
                t(q + 1);
                Unit unit = Unit.INSTANCE;
                reentrantLock.unlock();
                l();
                return AbstractChannelKt.OFFER_SUCCESS;
            }
            return closedForSend;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.BroadcastChannel
    @NotNull
    public ReceiveChannel<E> openSubscription() {
        a aVar = new a(this);
        v(this, aVar, null, 2, null);
        return aVar;
    }

    public final int p() {
        return this._size;
    }

    public final long q() {
        return this._tail;
    }

    public final void r(long j) {
        this._head = j;
    }

    public final void s(int i) {
        this._size = i;
    }

    public final void t(long j) {
        this._tail = j;
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x00a3, code lost:
        throw new java.lang.AssertionError();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void u(kotlinx.coroutines.channels.ArrayBroadcastChannel.a<E> r14, kotlinx.coroutines.channels.ArrayBroadcastChannel.a<E> r15) {
        /*
            r13 = this;
            r0 = 0
        L1:
            java.util.concurrent.locks.ReentrantLock r1 = r13.k
            r1.lock()
            if (r14 == 0) goto L23
            long r2 = r13.q()     // Catch: java.lang.Throwable -> L20
            r14.u(r2)     // Catch: java.lang.Throwable -> L20
            java.util.List<kotlinx.coroutines.channels.ArrayBroadcastChannel$a<E>> r2 = r13.m     // Catch: java.lang.Throwable -> L20
            boolean r2 = r2.isEmpty()     // Catch: java.lang.Throwable -> L20
            java.util.List<kotlinx.coroutines.channels.ArrayBroadcastChannel$a<E>> r3 = r13.m     // Catch: java.lang.Throwable -> L20
            r3.add(r14)     // Catch: java.lang.Throwable -> L20
            if (r2 != 0) goto L23
            r1.unlock()
            return
        L20:
            r14 = move-exception
            goto Ld0
        L23:
            if (r15 == 0) goto L3a
            java.util.List<kotlinx.coroutines.channels.ArrayBroadcastChannel$a<E>> r14 = r13.m     // Catch: java.lang.Throwable -> L20
            r14.remove(r15)     // Catch: java.lang.Throwable -> L20
            long r2 = r13.o()     // Catch: java.lang.Throwable -> L20
            long r14 = r15.r()     // Catch: java.lang.Throwable -> L20
            int r14 = (r2 > r14 ? 1 : (r2 == r14 ? 0 : -1))
            if (r14 == 0) goto L3a
            r1.unlock()
            return
        L3a:
            long r14 = r13.m()     // Catch: java.lang.Throwable -> L20
            long r2 = r13.q()     // Catch: java.lang.Throwable -> L20
            long r4 = r13.o()     // Catch: java.lang.Throwable -> L20
            long r14 = kotlin.ranges.h.coerceAtMost(r14, r2)     // Catch: java.lang.Throwable -> L20
            int r6 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r6 > 0) goto L52
            r1.unlock()
            return
        L52:
            int r6 = r13.p()     // Catch: java.lang.Throwable -> L20
        L56:
            int r7 = (r4 > r14 ? 1 : (r4 == r14 ? 0 : -1))
            if (r7 >= 0) goto Lcc
            java.lang.Object[] r7 = r13.l     // Catch: java.lang.Throwable -> L20
            int r8 = r13.getCapacity()     // Catch: java.lang.Throwable -> L20
            long r8 = (long) r8     // Catch: java.lang.Throwable -> L20
            long r8 = r4 % r8
            int r8 = (int) r8     // Catch: java.lang.Throwable -> L20
            r7[r8] = r0     // Catch: java.lang.Throwable -> L20
            int r7 = r13.getCapacity()     // Catch: java.lang.Throwable -> L20
            r8 = 0
            r9 = 1
            if (r6 < r7) goto L70
            r7 = r9
            goto L71
        L70:
            r7 = r8
        L71:
            r10 = 1
            long r4 = r4 + r10
            r13.r(r4)     // Catch: java.lang.Throwable -> L20
            int r6 = r6 + (-1)
            r13.s(r6)     // Catch: java.lang.Throwable -> L20
            if (r7 == 0) goto L56
        L7e:
            kotlinx.coroutines.channels.Send r7 = r13.takeFirstSendOrPeekClosed()     // Catch: java.lang.Throwable -> L20
            if (r7 != 0) goto L85
            goto L56
        L85:
            boolean r12 = r7 instanceof kotlinx.coroutines.channels.Closed     // Catch: java.lang.Throwable -> L20
            if (r12 == 0) goto L8a
            goto L56
        L8a:
            kotlinx.coroutines.internal.Symbol r12 = r7.tryResumeSend(r0)     // Catch: java.lang.Throwable -> L20
            if (r12 == 0) goto L7e
            boolean r14 = kotlinx.coroutines.DebugKt.getASSERTIONS_ENABLED()     // Catch: java.lang.Throwable -> L20
            if (r14 == 0) goto La4
            kotlinx.coroutines.internal.Symbol r14 = kotlinx.coroutines.CancellableContinuationImplKt.RESUME_TOKEN     // Catch: java.lang.Throwable -> L20
            if (r12 != r14) goto L9b
            r8 = r9
        L9b:
            if (r8 == 0) goto L9e
            goto La4
        L9e:
            java.lang.AssertionError r14 = new java.lang.AssertionError     // Catch: java.lang.Throwable -> L20
            r14.<init>()     // Catch: java.lang.Throwable -> L20
            throw r14     // Catch: java.lang.Throwable -> L20
        La4:
            java.lang.Object[] r14 = r13.l     // Catch: java.lang.Throwable -> L20
            int r15 = r13.getCapacity()     // Catch: java.lang.Throwable -> L20
            long r4 = (long) r15     // Catch: java.lang.Throwable -> L20
            long r4 = r2 % r4
            int r15 = (int) r4     // Catch: java.lang.Throwable -> L20
            java.lang.Object r4 = r7.getPollResult()     // Catch: java.lang.Throwable -> L20
            r14[r15] = r4     // Catch: java.lang.Throwable -> L20
            int r6 = r6 + 1
            r13.s(r6)     // Catch: java.lang.Throwable -> L20
            long r2 = r2 + r10
            r13.t(r2)     // Catch: java.lang.Throwable -> L20
            kotlin.Unit r14 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L20
            r1.unlock()
            r7.completeResumeSend()
            r13.l()
            r14 = r0
            r15 = r14
            goto L1
        Lcc:
            r1.unlock()
            return
        Ld0:
            r1.unlock()
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ArrayBroadcastChannel.u(kotlinx.coroutines.channels.ArrayBroadcastChannel$a, kotlinx.coroutines.channels.ArrayBroadcastChannel$a):void");
    }

    @Override // kotlinx.coroutines.channels.BroadcastChannel
    public void cancel(@Nullable CancellationException cancellationException) {
        cancel(cancellationException);
    }
}

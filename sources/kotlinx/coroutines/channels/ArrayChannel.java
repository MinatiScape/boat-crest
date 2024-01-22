package kotlinx.coroutines.channels;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.channels.AbstractSendChannel;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.UndeliveredElementException;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public class ArrayChannel<E> extends AbstractChannel<E> {
    public final int j;
    @NotNull
    public final BufferOverflow k;
    @NotNull
    public final ReentrantLock l;
    @NotNull
    public Object[] m;
    public int n;
    @NotNull
    private volatile /* synthetic */ int size;

    /* loaded from: classes12.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[BufferOverflow.values().length];
            iArr[BufferOverflow.SUSPEND.ordinal()] = 1;
            iArr[BufferOverflow.DROP_LATEST.ordinal()] = 2;
            iArr[BufferOverflow.DROP_OLDEST.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public ArrayChannel(int i, @NotNull BufferOverflow bufferOverflow, @Nullable Function1<? super E, Unit> function1) {
        super(function1);
        this.j = i;
        this.k = bufferOverflow;
        if (i >= 1) {
            this.l = new ReentrantLock();
            Object[] objArr = new Object[Math.min(i, 8)];
            ArraysKt___ArraysJvmKt.fill$default(objArr, AbstractChannelKt.EMPTY, 0, 0, 6, (Object) null);
            this.m = objArr;
            this.size = 0;
            return;
        }
        throw new IllegalArgumentException(("ArrayChannel capacity must be at least 1, but " + i + " was specified").toString());
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    public boolean enqueueReceiveInternal(@NotNull Receive<? super E> receive) {
        ReentrantLock reentrantLock = this.l;
        reentrantLock.lock();
        try {
            return super.enqueueReceiveInternal(receive);
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    @Nullable
    public Object enqueueSend(@NotNull Send send) {
        ReentrantLock reentrantLock = this.l;
        reentrantLock.lock();
        try {
            return super.enqueueSend(send);
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    @NotNull
    public String getBufferDebugString() {
        return "(buffer:capacity=" + this.j + ",size=" + this.size + HexStringBuilder.COMMENT_END_CHAR;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    public final boolean isBufferAlwaysEmpty() {
        return false;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public final boolean isBufferAlwaysFull() {
        return false;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    public final boolean isBufferEmpty() {
        return this.size == 0;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public final boolean isBufferFull() {
        return this.size == this.j && this.k == BufferOverflow.SUSPEND;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel, kotlinx.coroutines.channels.ReceiveChannel
    public boolean isClosedForReceive() {
        ReentrantLock reentrantLock = this.l;
        reentrantLock.lock();
        try {
            return super.isClosedForReceive();
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel, kotlinx.coroutines.channels.ReceiveChannel
    public boolean isEmpty() {
        ReentrantLock reentrantLock = this.l;
        reentrantLock.lock();
        try {
            return isEmptyImpl();
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    @NotNull
    public Object offerInternal(E e) {
        ReceiveOrClosed<E> takeFirstReceiveOrPeekClosed;
        Symbol tryResumeReceive;
        ReentrantLock reentrantLock = this.l;
        reentrantLock.lock();
        try {
            int i = this.size;
            Closed<?> closedForSend = getClosedForSend();
            if (closedForSend == null) {
                Symbol s = s(i);
                if (s == null) {
                    if (i == 0) {
                        do {
                            takeFirstReceiveOrPeekClosed = takeFirstReceiveOrPeekClosed();
                            if (takeFirstReceiveOrPeekClosed != null) {
                                if (takeFirstReceiveOrPeekClosed instanceof Closed) {
                                    this.size = i;
                                    return takeFirstReceiveOrPeekClosed;
                                }
                                tryResumeReceive = takeFirstReceiveOrPeekClosed.tryResumeReceive(e, null);
                            }
                        } while (tryResumeReceive == null);
                        if (DebugKt.getASSERTIONS_ENABLED()) {
                            if (!(tryResumeReceive == CancellableContinuationImplKt.RESUME_TOKEN)) {
                                throw new AssertionError();
                            }
                        }
                        this.size = i;
                        Unit unit = Unit.INSTANCE;
                        reentrantLock.unlock();
                        takeFirstReceiveOrPeekClosed.completeResumeReceive(e);
                        return takeFirstReceiveOrPeekClosed.getOfferResult();
                    }
                    q(i, e);
                    return AbstractChannelKt.OFFER_SUCCESS;
                }
                return s;
            }
            return closedForSend;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    @NotNull
    public Object offerSelectInternal(E e, @NotNull SelectInstance<?> selectInstance) {
        ReentrantLock reentrantLock = this.l;
        reentrantLock.lock();
        try {
            int i = this.size;
            Closed<?> closedForSend = getClosedForSend();
            if (closedForSend == null) {
                Symbol s = s(i);
                if (s == null) {
                    if (i == 0) {
                        while (true) {
                            AbstractSendChannel.TryOfferDesc<E> describeTryOffer = describeTryOffer(e);
                            Object performAtomicTrySelect = selectInstance.performAtomicTrySelect(describeTryOffer);
                            if (performAtomicTrySelect == null) {
                                this.size = i;
                                ReceiveOrClosed<? super E> result = describeTryOffer.getResult();
                                Unit unit = Unit.INSTANCE;
                                reentrantLock.unlock();
                                Intrinsics.checkNotNull(result);
                                ReceiveOrClosed<? super E> receiveOrClosed = result;
                                receiveOrClosed.completeResumeReceive(e);
                                return receiveOrClosed.getOfferResult();
                            } else if (performAtomicTrySelect == AbstractChannelKt.OFFER_FAILED) {
                                break;
                            } else if (performAtomicTrySelect != AtomicKt.RETRY_ATOMIC) {
                                if (performAtomicTrySelect != SelectKt.getALREADY_SELECTED() && !(performAtomicTrySelect instanceof Closed)) {
                                    throw new IllegalStateException(Intrinsics.stringPlus("performAtomicTrySelect(describeTryOffer) returned ", performAtomicTrySelect).toString());
                                }
                                this.size = i;
                                return performAtomicTrySelect;
                            }
                        }
                    }
                    if (!selectInstance.trySelect()) {
                        this.size = i;
                        return SelectKt.getALREADY_SELECTED();
                    }
                    q(i, e);
                    return AbstractChannelKt.OFFER_SUCCESS;
                }
                return s;
            }
            return closedForSend;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    public void onCancelIdempotent(boolean z) {
        Function1<E, Unit> function1 = this.onUndeliveredElement;
        ReentrantLock reentrantLock = this.l;
        reentrantLock.lock();
        try {
            int i = this.size;
            UndeliveredElementException undeliveredElementException = null;
            int i2 = 0;
            while (i2 < i) {
                i2++;
                Object obj = this.m[this.n];
                if (function1 != null && obj != AbstractChannelKt.EMPTY) {
                    undeliveredElementException = OnUndeliveredElementKt.callUndeliveredElementCatchingException(function1, obj, undeliveredElementException);
                }
                Object[] objArr = this.m;
                int i3 = this.n;
                objArr[i3] = AbstractChannelKt.EMPTY;
                this.n = (i3 + 1) % objArr.length;
            }
            this.size = 0;
            Unit unit = Unit.INSTANCE;
            reentrantLock.unlock();
            super.onCancelIdempotent(z);
            if (undeliveredElementException != null) {
                throw undeliveredElementException;
            }
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    @Nullable
    public Object pollInternal() {
        ReentrantLock reentrantLock = this.l;
        reentrantLock.lock();
        try {
            int i = this.size;
            if (i == 0) {
                Object closedForSend = getClosedForSend();
                if (closedForSend == null) {
                    closedForSend = AbstractChannelKt.POLL_FAILED;
                }
                return closedForSend;
            }
            Object[] objArr = this.m;
            int i2 = this.n;
            Object obj = objArr[i2];
            Send send = null;
            objArr[i2] = null;
            this.size = i - 1;
            Object obj2 = AbstractChannelKt.POLL_FAILED;
            if (i == this.j) {
                Send send2 = null;
                while (true) {
                    Send takeFirstSendOrPeekClosed = takeFirstSendOrPeekClosed();
                    if (takeFirstSendOrPeekClosed == null) {
                        send = send2;
                        break;
                    }
                    Symbol tryResumeSend = takeFirstSendOrPeekClosed.tryResumeSend(null);
                    if (tryResumeSend != null) {
                        if (DebugKt.getASSERTIONS_ENABLED()) {
                            if (!(tryResumeSend == CancellableContinuationImplKt.RESUME_TOKEN)) {
                                throw new AssertionError();
                            }
                        }
                        obj2 = takeFirstSendOrPeekClosed.getPollResult();
                        r6 = true;
                        send = takeFirstSendOrPeekClosed;
                    } else {
                        takeFirstSendOrPeekClosed.undeliveredElement();
                        send2 = takeFirstSendOrPeekClosed;
                    }
                }
            }
            if (obj2 != AbstractChannelKt.POLL_FAILED && !(obj2 instanceof Closed)) {
                this.size = i;
                Object[] objArr2 = this.m;
                objArr2[(this.n + i) % objArr2.length] = obj2;
            }
            this.n = (this.n + 1) % this.m.length;
            Unit unit = Unit.INSTANCE;
            if (r6) {
                Intrinsics.checkNotNull(send);
                send.completeResumeSend();
            }
            return obj;
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0092 A[Catch: all -> 0x00bb, TRY_LEAVE, TryCatch #0 {all -> 0x00bb, blocks: (B:3:0x0005, B:5:0x0009, B:7:0x000f, B:10:0x0015, B:12:0x0029, B:14:0x0033, B:32:0x0078, B:34:0x007c, B:36:0x0080, B:42:0x00a2, B:37:0x008c, B:39:0x0092, B:15:0x0043, B:18:0x0048, B:21:0x004d, B:23:0x0053, B:26:0x005f, B:29:0x0067, B:30:0x0076), top: B:50:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00b2  */
    @Override // kotlinx.coroutines.channels.AbstractChannel
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object pollSelectInternal(@org.jetbrains.annotations.NotNull kotlinx.coroutines.selects.SelectInstance<?> r9) {
        /*
            r8 = this;
            java.util.concurrent.locks.ReentrantLock r0 = r8.l
            r0.lock()
            int r1 = r8.size     // Catch: java.lang.Throwable -> Lbb
            if (r1 != 0) goto L15
            kotlinx.coroutines.channels.Closed r9 = r8.getClosedForSend()     // Catch: java.lang.Throwable -> Lbb
            if (r9 != 0) goto L11
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.channels.AbstractChannelKt.POLL_FAILED     // Catch: java.lang.Throwable -> Lbb
        L11:
            r0.unlock()
            return r9
        L15:
            java.lang.Object[] r2 = r8.m     // Catch: java.lang.Throwable -> Lbb
            int r3 = r8.n     // Catch: java.lang.Throwable -> Lbb
            r4 = r2[r3]     // Catch: java.lang.Throwable -> Lbb
            r5 = 0
            r2[r3] = r5     // Catch: java.lang.Throwable -> Lbb
            int r2 = r1 + (-1)
            r8.size = r2     // Catch: java.lang.Throwable -> Lbb
            kotlinx.coroutines.internal.Symbol r2 = kotlinx.coroutines.channels.AbstractChannelKt.POLL_FAILED     // Catch: java.lang.Throwable -> Lbb
            int r3 = r8.j     // Catch: java.lang.Throwable -> Lbb
            r6 = 1
            if (r1 != r3) goto L77
        L29:
            kotlinx.coroutines.channels.AbstractChannel$TryPollDesc r3 = r8.describeTryPoll()     // Catch: java.lang.Throwable -> Lbb
            java.lang.Object r7 = r9.performAtomicTrySelect(r3)     // Catch: java.lang.Throwable -> Lbb
            if (r7 != 0) goto L43
            java.lang.Object r5 = r3.getResult()     // Catch: java.lang.Throwable -> Lbb
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)     // Catch: java.lang.Throwable -> Lbb
            r2 = r5
            kotlinx.coroutines.channels.Send r2 = (kotlinx.coroutines.channels.Send) r2     // Catch: java.lang.Throwable -> Lbb
            java.lang.Object r2 = r2.getPollResult()     // Catch: java.lang.Throwable -> Lbb
            r3 = r6
            goto L78
        L43:
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.AbstractChannelKt.POLL_FAILED     // Catch: java.lang.Throwable -> Lbb
            if (r7 != r3) goto L48
            goto L77
        L48:
            java.lang.Object r3 = kotlinx.coroutines.internal.AtomicKt.RETRY_ATOMIC     // Catch: java.lang.Throwable -> Lbb
            if (r7 != r3) goto L4d
            goto L29
        L4d:
            java.lang.Object r2 = kotlinx.coroutines.selects.SelectKt.getALREADY_SELECTED()     // Catch: java.lang.Throwable -> Lbb
            if (r7 != r2) goto L5f
            r8.size = r1     // Catch: java.lang.Throwable -> Lbb
            java.lang.Object[] r9 = r8.m     // Catch: java.lang.Throwable -> Lbb
            int r1 = r8.n     // Catch: java.lang.Throwable -> Lbb
            r9[r1] = r4     // Catch: java.lang.Throwable -> Lbb
            r0.unlock()
            return r7
        L5f:
            boolean r2 = r7 instanceof kotlinx.coroutines.channels.Closed     // Catch: java.lang.Throwable -> Lbb
            if (r2 == 0) goto L67
            r3 = r6
            r2 = r7
            r5 = r2
            goto L78
        L67:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> Lbb
            java.lang.String r1 = "performAtomicTrySelect(describeTryOffer) returned "
            java.lang.String r1 = kotlin.jvm.internal.Intrinsics.stringPlus(r1, r7)     // Catch: java.lang.Throwable -> Lbb
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> Lbb
            r9.<init>(r1)     // Catch: java.lang.Throwable -> Lbb
            throw r9     // Catch: java.lang.Throwable -> Lbb
        L77:
            r3 = 0
        L78:
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.channels.AbstractChannelKt.POLL_FAILED     // Catch: java.lang.Throwable -> Lbb
            if (r2 == r7) goto L8c
            boolean r7 = r2 instanceof kotlinx.coroutines.channels.Closed     // Catch: java.lang.Throwable -> Lbb
            if (r7 != 0) goto L8c
            r8.size = r1     // Catch: java.lang.Throwable -> Lbb
            java.lang.Object[] r9 = r8.m     // Catch: java.lang.Throwable -> Lbb
            int r7 = r8.n     // Catch: java.lang.Throwable -> Lbb
            int r7 = r7 + r1
            int r1 = r9.length     // Catch: java.lang.Throwable -> Lbb
            int r7 = r7 % r1
            r9[r7] = r2     // Catch: java.lang.Throwable -> Lbb
            goto La2
        L8c:
            boolean r9 = r9.trySelect()     // Catch: java.lang.Throwable -> Lbb
            if (r9 != 0) goto La2
            r8.size = r1     // Catch: java.lang.Throwable -> Lbb
            java.lang.Object[] r9 = r8.m     // Catch: java.lang.Throwable -> Lbb
            int r1 = r8.n     // Catch: java.lang.Throwable -> Lbb
            r9[r1] = r4     // Catch: java.lang.Throwable -> Lbb
            java.lang.Object r9 = kotlinx.coroutines.selects.SelectKt.getALREADY_SELECTED()     // Catch: java.lang.Throwable -> Lbb
            r0.unlock()
            return r9
        La2:
            int r9 = r8.n     // Catch: java.lang.Throwable -> Lbb
            int r9 = r9 + r6
            java.lang.Object[] r1 = r8.m     // Catch: java.lang.Throwable -> Lbb
            int r1 = r1.length     // Catch: java.lang.Throwable -> Lbb
            int r9 = r9 % r1
            r8.n = r9     // Catch: java.lang.Throwable -> Lbb
            kotlin.Unit r9 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> Lbb
            r0.unlock()
            if (r3 == 0) goto Lba
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
            kotlinx.coroutines.channels.Send r5 = (kotlinx.coroutines.channels.Send) r5
            r5.completeResumeSend()
        Lba:
            return r4
        Lbb:
            r9 = move-exception
            r0.unlock()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ArrayChannel.pollSelectInternal(kotlinx.coroutines.selects.SelectInstance):java.lang.Object");
    }

    public final void q(int i, E e) {
        if (i < this.j) {
            r(i);
            Object[] objArr = this.m;
            objArr[(this.n + i) % objArr.length] = e;
            return;
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(this.k == BufferOverflow.DROP_OLDEST)) {
                throw new AssertionError();
            }
        }
        Object[] objArr2 = this.m;
        int i2 = this.n;
        objArr2[i2 % objArr2.length] = null;
        objArr2[(i + i2) % objArr2.length] = e;
        this.n = (i2 + 1) % objArr2.length;
    }

    public final void r(int i) {
        Object[] objArr = this.m;
        if (i >= objArr.length) {
            int min = Math.min(objArr.length * 2, this.j);
            Object[] objArr2 = new Object[min];
            for (int i2 = 0; i2 < i; i2++) {
                Object[] objArr3 = this.m;
                objArr2[i2] = objArr3[(this.n + i2) % objArr3.length];
            }
            ArraysKt___ArraysJvmKt.fill((Symbol[]) objArr2, AbstractChannelKt.EMPTY, i, min);
            this.m = objArr2;
            this.n = 0;
        }
    }

    public final Symbol s(int i) {
        if (i < this.j) {
            this.size = i + 1;
            return null;
        }
        int i2 = WhenMappings.$EnumSwitchMapping$0[this.k.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 == 3) {
                    return null;
                }
                throw new NoWhenBranchMatchedException();
            }
            return AbstractChannelKt.OFFER_SUCCESS;
        }
        return AbstractChannelKt.OFFER_FAILED;
    }
}

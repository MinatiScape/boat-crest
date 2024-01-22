package kotlinx.coroutines.channels;

import com.coveiot.android.leonardo.utils.MusicConstants;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.internal.LowPriorityInOverloadResolution;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BeforeResumeCancelHandler;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelIterator;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.InlineList;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.LockFreeLinkedList_commonKt;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public abstract class AbstractChannel<E> extends AbstractSendChannel<E> implements Channel<E> {

    /* loaded from: classes12.dex */
    public static final class TryPollDesc<E> extends LockFreeLinkedListNode.RemoveFirstDesc<Send> {
        public TryPollDesc(@NotNull LockFreeLinkedListHead lockFreeLinkedListHead) {
            super(lockFreeLinkedListHead);
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.RemoveFirstDesc, kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        @Nullable
        public Object failure(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode) {
            if (lockFreeLinkedListNode instanceof Closed) {
                return lockFreeLinkedListNode;
            }
            if (lockFreeLinkedListNode instanceof Send) {
                return null;
            }
            return AbstractChannelKt.POLL_FAILED;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        @Nullable
        public Object onPrepare(@NotNull LockFreeLinkedListNode.PrepareOp prepareOp) {
            Symbol tryResumeSend = ((Send) prepareOp.affected).tryResumeSend(prepareOp);
            if (tryResumeSend == null) {
                return LockFreeLinkedList_commonKt.REMOVE_PREPARED;
            }
            Object obj = AtomicKt.RETRY_ATOMIC;
            if (tryResumeSend == obj) {
                return obj;
            }
            if (DebugKt.getASSERTIONS_ENABLED()) {
                if (tryResumeSend == CancellableContinuationImplKt.RESUME_TOKEN) {
                    return null;
                }
                throw new AssertionError();
            }
            return null;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public void onRemoved(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode) {
            ((Send) lockFreeLinkedListNode).undeliveredElement();
        }
    }

    /* loaded from: classes12.dex */
    public static final class a<E> implements ChannelIterator<E> {
        @JvmField
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final AbstractChannel<E> f14144a;
        @Nullable
        public Object b = AbstractChannelKt.POLL_FAILED;

        public a(@NotNull AbstractChannel<E> abstractChannel) {
            this.f14144a = abstractChannel;
        }

        @Nullable
        public final Object a() {
            return this.b;
        }

        public final boolean b(Object obj) {
            if (obj instanceof Closed) {
                Closed closed = (Closed) obj;
                if (closed.closeCause == null) {
                    return false;
                }
                throw StackTraceRecoveryKt.recoverStackTrace(closed.getReceiveException());
            }
            return true;
        }

        public final Object c(Continuation<? super Boolean> continuation) {
            CancellableContinuationImpl orCreateCancellableContinuation = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation));
            d dVar = new d(this, orCreateCancellableContinuation);
            while (true) {
                if (this.f14144a.k(dVar)) {
                    this.f14144a.o(orCreateCancellableContinuation, dVar);
                    break;
                }
                Object pollInternal = this.f14144a.pollInternal();
                d(pollInternal);
                if (pollInternal instanceof Closed) {
                    Closed closed = (Closed) pollInternal;
                    if (closed.closeCause == null) {
                        Result.Companion companion = Result.Companion;
                        orCreateCancellableContinuation.resumeWith(Result.m123constructorimpl(Boxing.boxBoolean(false)));
                    } else {
                        Result.Companion companion2 = Result.Companion;
                        orCreateCancellableContinuation.resumeWith(Result.m123constructorimpl(ResultKt.createFailure(closed.getReceiveException())));
                    }
                } else if (pollInternal != AbstractChannelKt.POLL_FAILED) {
                    Boolean boxBoolean = Boxing.boxBoolean(true);
                    Function1<E, Unit> function1 = this.f14144a.onUndeliveredElement;
                    orCreateCancellableContinuation.resume(boxBoolean, function1 == null ? null : OnUndeliveredElementKt.bindCancellationFun(function1, pollInternal, orCreateCancellableContinuation.getContext()));
                }
            }
            Object result = orCreateCancellableContinuation.getResult();
            if (result == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return result;
        }

        public final void d(@Nullable Object obj) {
            this.b = obj;
        }

        @Override // kotlinx.coroutines.channels.ChannelIterator
        @Nullable
        public Object hasNext(@NotNull Continuation<? super Boolean> continuation) {
            Object a2 = a();
            Symbol symbol = AbstractChannelKt.POLL_FAILED;
            if (a2 != symbol) {
                return Boxing.boxBoolean(b(a()));
            }
            d(this.f14144a.pollInternal());
            return a() != symbol ? Boxing.boxBoolean(b(a())) : c(continuation);
        }

        @Override // kotlinx.coroutines.channels.ChannelIterator
        @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.3.0, binary compatibility with versions <= 1.2.x")
        @JvmName(name = MusicConstants.CMDNEXT)
        public /* synthetic */ Object next(Continuation continuation) {
            return ChannelIterator.DefaultImpls.next(this, continuation);
        }

        @Override // kotlinx.coroutines.channels.ChannelIterator
        public E next() {
            E e = (E) this.b;
            if (!(e instanceof Closed)) {
                Symbol symbol = AbstractChannelKt.POLL_FAILED;
                if (e != symbol) {
                    this.b = symbol;
                    return e;
                }
                throw new IllegalStateException("'hasNext' should be called prior to 'next' invocation");
            }
            throw StackTraceRecoveryKt.recoverStackTrace(((Closed) e).getReceiveException());
        }
    }

    /* loaded from: classes12.dex */
    public static class b<E> extends Receive<E> {
        @JvmField
        @NotNull
        public final CancellableContinuation<Object> k;
        @JvmField
        public final int l;

        public b(@NotNull CancellableContinuation<Object> cancellableContinuation, int i) {
            this.k = cancellableContinuation;
            this.l = i;
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        public void completeResumeReceive(E e) {
            this.k.completeResume(CancellableContinuationImplKt.RESUME_TOKEN);
        }

        @Nullable
        public final Object e(E e) {
            return this.l == 1 ? ChannelResult.m740boximpl(ChannelResult.Companion.m755successJP2dKIU(e)) : e;
        }

        @Override // kotlinx.coroutines.channels.Receive
        public void resumeReceiveClosed(@NotNull Closed<?> closed) {
            if (this.l == 1) {
                CancellableContinuation<Object> cancellableContinuation = this.k;
                Result.Companion companion = Result.Companion;
                cancellableContinuation.resumeWith(Result.m123constructorimpl(ChannelResult.m740boximpl(ChannelResult.Companion.m753closedJP2dKIU(closed.closeCause))));
                return;
            }
            CancellableContinuation<Object> cancellableContinuation2 = this.k;
            Result.Companion companion2 = Result.Companion;
            cancellableContinuation2.resumeWith(Result.m123constructorimpl(ResultKt.createFailure(closed.getReceiveException())));
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        @NotNull
        public String toString() {
            return "ReceiveElement@" + DebugStringsKt.getHexAddress(this) + "[receiveMode=" + this.l + ']';
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        @Nullable
        public Symbol tryResumeReceive(E e, @Nullable LockFreeLinkedListNode.PrepareOp prepareOp) {
            Object tryResume = this.k.tryResume(e(e), prepareOp == null ? null : prepareOp.desc, resumeOnCancellationFun(e));
            if (tryResume == null) {
                return null;
            }
            if (DebugKt.getASSERTIONS_ENABLED()) {
                if (!(tryResume == CancellableContinuationImplKt.RESUME_TOKEN)) {
                    throw new AssertionError();
                }
            }
            if (prepareOp != null) {
                prepareOp.finishPrepare();
            }
            return CancellableContinuationImplKt.RESUME_TOKEN;
        }
    }

    /* loaded from: classes12.dex */
    public static final class c<E> extends b<E> {
        @JvmField
        @NotNull
        public final Function1<E, Unit> m;

        /* JADX WARN: Multi-variable type inference failed */
        public c(@NotNull CancellableContinuation<Object> cancellableContinuation, int i, @NotNull Function1<? super E, Unit> function1) {
            super(cancellableContinuation, i);
            this.m = function1;
        }

        @Override // kotlinx.coroutines.channels.Receive
        @Nullable
        public Function1<Throwable, Unit> resumeOnCancellationFun(E e) {
            return OnUndeliveredElementKt.bindCancellationFun(this.m, e, this.k.getContext());
        }
    }

    /* loaded from: classes12.dex */
    public static class d<E> extends Receive<E> {
        @JvmField
        @NotNull
        public final a<E> k;
        @JvmField
        @NotNull
        public final CancellableContinuation<Boolean> l;

        /* JADX WARN: Multi-variable type inference failed */
        public d(@NotNull a<E> aVar, @NotNull CancellableContinuation<? super Boolean> cancellableContinuation) {
            this.k = aVar;
            this.l = cancellableContinuation;
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        public void completeResumeReceive(E e) {
            this.k.d(e);
            this.l.completeResume(CancellableContinuationImplKt.RESUME_TOKEN);
        }

        @Override // kotlinx.coroutines.channels.Receive
        @Nullable
        public Function1<Throwable, Unit> resumeOnCancellationFun(E e) {
            Function1<E, Unit> function1 = this.k.f14144a.onUndeliveredElement;
            if (function1 == null) {
                return null;
            }
            return OnUndeliveredElementKt.bindCancellationFun(function1, e, this.l.getContext());
        }

        @Override // kotlinx.coroutines.channels.Receive
        public void resumeReceiveClosed(@NotNull Closed<?> closed) {
            Object tryResumeWithException;
            if (closed.closeCause == null) {
                tryResumeWithException = CancellableContinuation.DefaultImpls.tryResume$default(this.l, Boolean.FALSE, null, 2, null);
            } else {
                tryResumeWithException = this.l.tryResumeWithException(closed.getReceiveException());
            }
            if (tryResumeWithException != null) {
                this.k.d(closed);
                this.l.completeResume(tryResumeWithException);
            }
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        @NotNull
        public String toString() {
            return Intrinsics.stringPlus("ReceiveHasNext@", DebugStringsKt.getHexAddress(this));
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        @Nullable
        public Symbol tryResumeReceive(E e, @Nullable LockFreeLinkedListNode.PrepareOp prepareOp) {
            Object tryResume = this.l.tryResume(Boolean.TRUE, prepareOp == null ? null : prepareOp.desc, resumeOnCancellationFun(e));
            if (tryResume == null) {
                return null;
            }
            if (DebugKt.getASSERTIONS_ENABLED()) {
                if (!(tryResume == CancellableContinuationImplKt.RESUME_TOKEN)) {
                    throw new AssertionError();
                }
            }
            if (prepareOp != null) {
                prepareOp.finishPrepare();
            }
            return CancellableContinuationImplKt.RESUME_TOKEN;
        }
    }

    /* loaded from: classes12.dex */
    public static final class e<R, E> extends Receive<E> implements DisposableHandle {
        @JvmField
        @NotNull
        public final AbstractChannel<E> k;
        @JvmField
        @NotNull
        public final SelectInstance<R> l;
        @JvmField
        @NotNull
        public final Function2<Object, Continuation<? super R>, Object> m;
        @JvmField
        public final int n;

        /* JADX WARN: Multi-variable type inference failed */
        public e(@NotNull AbstractChannel<E> abstractChannel, @NotNull SelectInstance<? super R> selectInstance, @NotNull Function2<Object, ? super Continuation<? super R>, ? extends Object> function2, int i) {
            this.k = abstractChannel;
            this.l = selectInstance;
            this.m = function2;
            this.n = i;
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        public void completeResumeReceive(E e) {
            CancellableKt.startCoroutineCancellable(this.m, this.n == 1 ? ChannelResult.m740boximpl(ChannelResult.Companion.m755successJP2dKIU(e)) : e, this.l.getCompletion(), resumeOnCancellationFun(e));
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public void dispose() {
            if (mo775remove()) {
                this.k.onReceiveDequeued();
            }
        }

        @Override // kotlinx.coroutines.channels.Receive
        @Nullable
        public Function1<Throwable, Unit> resumeOnCancellationFun(E e) {
            Function1<E, Unit> function1 = this.k.onUndeliveredElement;
            if (function1 == null) {
                return null;
            }
            return OnUndeliveredElementKt.bindCancellationFun(function1, e, this.l.getCompletion().getContext());
        }

        @Override // kotlinx.coroutines.channels.Receive
        public void resumeReceiveClosed(@NotNull Closed<?> closed) {
            if (this.l.trySelect()) {
                int i = this.n;
                if (i == 0) {
                    this.l.resumeSelectWithException(closed.getReceiveException());
                } else if (i != 1) {
                } else {
                    CancellableKt.startCoroutineCancellable$default(this.m, ChannelResult.m740boximpl(ChannelResult.Companion.m753closedJP2dKIU(closed.closeCause)), this.l.getCompletion(), null, 4, null);
                }
            }
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        @NotNull
        public String toString() {
            return "ReceiveSelect@" + DebugStringsKt.getHexAddress(this) + '[' + this.l + ",receiveMode=" + this.n + ']';
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        @Nullable
        public Symbol tryResumeReceive(E e, @Nullable LockFreeLinkedListNode.PrepareOp prepareOp) {
            return (Symbol) this.l.trySelectOther(prepareOp);
        }
    }

    /* loaded from: classes12.dex */
    public final class f extends BeforeResumeCancelHandler {
        @NotNull
        public final Receive<?> h;

        public f(@NotNull Receive<?> receive) {
            this.h = receive;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        @NotNull
        public String toString() {
            return "RemoveReceiveOnCancel[" + this.h + ']';
        }

        @Override // kotlinx.coroutines.CancelHandlerBase
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public void invoke2(@Nullable Throwable th) {
            if (this.h.mo775remove()) {
                AbstractChannel.this.onReceiveDequeued();
            }
        }
    }

    @DebugMetadata(c = "kotlinx.coroutines.channels.AbstractChannel", f = "AbstractChannel.kt", i = {}, l = {633}, m = "receiveCatching-JP2dKIU", n = {}, s = {})
    /* loaded from: classes12.dex */
    public static final class g extends ContinuationImpl {
        public int label;
        public /* synthetic */ Object result;
        public final /* synthetic */ AbstractChannel<E> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public g(AbstractChannel<E> abstractChannel, Continuation<? super g> continuation) {
            super(continuation);
            this.this$0 = abstractChannel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            Object mo734receiveCatchingJP2dKIU = this.this$0.mo734receiveCatchingJP2dKIU(this);
            return mo734receiveCatchingJP2dKIU == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? mo734receiveCatchingJP2dKIU : ChannelResult.m740boximpl(mo734receiveCatchingJP2dKIU);
        }
    }

    public AbstractChannel(@Nullable Function1<? super E, Unit> function1) {
        super(function1);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public /* synthetic */ void cancel() {
        cancel((CancellationException) null);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    /* renamed from: cancelInternal$kotlinx_coroutines_core */
    public final boolean cancel(@Nullable Throwable th) {
        boolean close = close(th);
        onCancelIdempotent(close);
        return close;
    }

    @NotNull
    public final TryPollDesc<E> describeTryPoll() {
        return new TryPollDesc<>(getQueue());
    }

    public boolean enqueueReceiveInternal(@NotNull final Receive<? super E> receive) {
        int tryCondAddNext;
        LockFreeLinkedListNode prevNode;
        if (isBufferAlwaysEmpty()) {
            LockFreeLinkedListNode queue = getQueue();
            do {
                prevNode = queue.getPrevNode();
                if (!(!(prevNode instanceof Send))) {
                    return false;
                }
            } while (!prevNode.addNext(receive, queue));
        } else {
            LockFreeLinkedListNode queue2 = getQueue();
            LockFreeLinkedListNode.CondAddOp condAddOp = new LockFreeLinkedListNode.CondAddOp(receive) { // from class: kotlinx.coroutines.channels.AbstractChannel$enqueueReceiveInternal$$inlined$addLastIfPrevAndIf$1
                @Override // kotlinx.coroutines.internal.AtomicOp
                @Nullable
                public Object prepare(@NotNull LockFreeLinkedListNode lockFreeLinkedListNode) {
                    if (this.isBufferEmpty()) {
                        return null;
                    }
                    return LockFreeLinkedListKt.getCONDITION_FALSE();
                }
            };
            do {
                LockFreeLinkedListNode prevNode2 = queue2.getPrevNode();
                if (!(!(prevNode2 instanceof Send))) {
                    return false;
                }
                tryCondAddNext = prevNode2.tryCondAddNext(receive, queue2, condAddOp);
                if (tryCondAddNext != 1) {
                }
            } while (tryCondAddNext != 2);
            return false;
        }
        return true;
    }

    public final boolean getHasReceiveOrClosed() {
        return getQueue().getNextNode() instanceof ReceiveOrClosed;
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @NotNull
    public final SelectClause1<E> getOnReceive() {
        return new SelectClause1<E>(this) { // from class: kotlinx.coroutines.channels.AbstractChannel$onReceive$1
            public final /* synthetic */ AbstractChannel<E> h;

            {
                this.h = this;
            }

            @Override // kotlinx.coroutines.selects.SelectClause1
            public <R> void registerSelectClause1(@NotNull SelectInstance<? super R> selectInstance, @NotNull Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2) {
                this.h.n(selectInstance, 0, function2);
            }
        };
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @NotNull
    public final SelectClause1<ChannelResult<E>> getOnReceiveCatching() {
        return (SelectClause1<ChannelResult<? extends E>>) new SelectClause1<ChannelResult<? extends E>>(this) { // from class: kotlinx.coroutines.channels.AbstractChannel$onReceiveCatching$1
            public final /* synthetic */ AbstractChannel<E> h;

            {
                this.h = this;
            }

            @Override // kotlinx.coroutines.selects.SelectClause1
            public <R> void registerSelectClause1(@NotNull SelectInstance<? super R> selectInstance, @NotNull Function2<? super ChannelResult<? extends E>, ? super Continuation<? super R>, ? extends Object> function2) {
                this.h.n(selectInstance, 1, function2);
            }
        };
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @NotNull
    public SelectClause1<E> getOnReceiveOrNull() {
        return Channel.DefaultImpls.getOnReceiveOrNull(this);
    }

    public abstract boolean isBufferAlwaysEmpty();

    public abstract boolean isBufferEmpty();

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public boolean isClosedForReceive() {
        return getClosedForReceive() != null && isBufferEmpty();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public boolean isEmpty() {
        return isEmptyImpl();
    }

    public final boolean isEmptyImpl() {
        return !(getQueue().getNextNode() instanceof Send) && isBufferEmpty();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @NotNull
    public final ChannelIterator<E> iterator() {
        return new a(this);
    }

    public final boolean k(Receive<? super E> receive) {
        boolean enqueueReceiveInternal = enqueueReceiveInternal(receive);
        if (enqueueReceiveInternal) {
            onReceiveEnqueued();
        }
        return enqueueReceiveInternal;
    }

    public final <R> boolean l(SelectInstance<? super R> selectInstance, Function2<Object, ? super Continuation<? super R>, ? extends Object> function2, int i) {
        e eVar = new e(this, selectInstance, function2, i);
        boolean k = k(eVar);
        if (k) {
            selectInstance.disposeOnSelect(eVar);
        }
        return k;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v3, types: [kotlinx.coroutines.channels.AbstractChannel$b] */
    public final <R> Object m(int i, Continuation<? super R> continuation) {
        c cVar;
        CancellableContinuationImpl orCreateCancellableContinuation = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation));
        if (this.onUndeliveredElement == null) {
            cVar = new b(orCreateCancellableContinuation, i);
        } else {
            cVar = new c(orCreateCancellableContinuation, i, this.onUndeliveredElement);
        }
        while (true) {
            if (k(cVar)) {
                o(orCreateCancellableContinuation, cVar);
                break;
            }
            Object pollInternal = pollInternal();
            if (pollInternal instanceof Closed) {
                cVar.resumeReceiveClosed((Closed) pollInternal);
                break;
            } else if (pollInternal != AbstractChannelKt.POLL_FAILED) {
                orCreateCancellableContinuation.resume(cVar.e(pollInternal), cVar.resumeOnCancellationFun(pollInternal));
                break;
            }
        }
        Object result = orCreateCancellableContinuation.getResult();
        if (result == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public final <R> void n(SelectInstance<? super R> selectInstance, int i, Function2<Object, ? super Continuation<? super R>, ? extends Object> function2) {
        while (!selectInstance.isSelected()) {
            if (isEmptyImpl()) {
                if (l(selectInstance, function2, i)) {
                    return;
                }
            } else {
                Object pollSelectInternal = pollSelectInternal(selectInstance);
                if (pollSelectInternal == SelectKt.getALREADY_SELECTED()) {
                    return;
                }
                if (pollSelectInternal != AbstractChannelKt.POLL_FAILED && pollSelectInternal != AtomicKt.RETRY_ATOMIC) {
                    p(function2, selectInstance, i, pollSelectInternal);
                }
            }
        }
    }

    public final void o(CancellableContinuation<?> cancellableContinuation, Receive<?> receive) {
        cancellableContinuation.invokeOnCancellation(new f(receive));
    }

    public void onCancelIdempotent(boolean z) {
        Closed<?> closedForSend = getClosedForSend();
        if (closedForSend != null) {
            Object m767constructorimpl$default = InlineList.m767constructorimpl$default(null, 1, null);
            while (true) {
                LockFreeLinkedListNode prevNode = closedForSend.getPrevNode();
                if (prevNode instanceof LockFreeLinkedListHead) {
                    mo733onCancelIdempotentListww6eGU(m767constructorimpl$default, closedForSend);
                    return;
                } else if (DebugKt.getASSERTIONS_ENABLED() && !(prevNode instanceof Send)) {
                    throw new AssertionError();
                } else {
                    if (!prevNode.mo775remove()) {
                        prevNode.helpRemove();
                    } else {
                        m767constructorimpl$default = InlineList.m772plusFjFbRPM(m767constructorimpl$default, (Send) prevNode);
                    }
                }
            }
        } else {
            throw new IllegalStateException("Cannot happen".toString());
        }
    }

    /* renamed from: onCancelIdempotentList-w-w6eGU  reason: not valid java name */
    public void mo733onCancelIdempotentListww6eGU(@NotNull Object obj, @NotNull Closed<?> closed) {
        if (obj == null) {
            return;
        }
        if (!(obj instanceof ArrayList)) {
            ((Send) obj).resumeSendClosed(closed);
            return;
        }
        ArrayList arrayList = (ArrayList) obj;
        int size = arrayList.size() - 1;
        if (size < 0) {
            return;
        }
        while (true) {
            int i = size - 1;
            ((Send) arrayList.get(size)).resumeSendClosed(closed);
            if (i < 0) {
                return;
            }
            size = i;
        }
    }

    public void onReceiveDequeued() {
    }

    public void onReceiveEnqueued() {
    }

    public final <R> void p(Function2<Object, ? super Continuation<? super R>, ? extends Object> function2, SelectInstance<? super R> selectInstance, int i, Object obj) {
        boolean z = obj instanceof Closed;
        if (!z) {
            if (i == 1) {
                ChannelResult.Companion companion = ChannelResult.Companion;
                UndispatchedKt.startCoroutineUnintercepted(function2, ChannelResult.m740boximpl(z ? companion.m753closedJP2dKIU(((Closed) obj).closeCause) : companion.m755successJP2dKIU(obj)), selectInstance.getCompletion());
                return;
            }
            UndispatchedKt.startCoroutineUnintercepted(function2, obj, selectInstance.getCompletion());
        } else if (i != 0) {
            if (i == 1 && selectInstance.trySelect()) {
                UndispatchedKt.startCoroutineUnintercepted(function2, ChannelResult.m740boximpl(ChannelResult.Companion.m753closedJP2dKIU(((Closed) obj).closeCause)), selectInstance.getCompletion());
            }
        } else {
            throw StackTraceRecoveryKt.recoverStackTrace(((Closed) obj).getReceiveException());
        }
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of 'tryReceive'. Please note that the provided replacement does not rethrow channel's close cause as 'poll' did, for the precise replacement please refer to the 'poll' documentation", replaceWith = @ReplaceWith(expression = "tryReceive().getOrNull()", imports = {}))
    @Nullable
    public E poll() {
        return (E) Channel.DefaultImpls.poll(this);
    }

    @Nullable
    public Object pollInternal() {
        while (true) {
            Send takeFirstSendOrPeekClosed = takeFirstSendOrPeekClosed();
            if (takeFirstSendOrPeekClosed == null) {
                return AbstractChannelKt.POLL_FAILED;
            }
            Symbol tryResumeSend = takeFirstSendOrPeekClosed.tryResumeSend(null);
            if (tryResumeSend != null) {
                if (DebugKt.getASSERTIONS_ENABLED()) {
                    if (!(tryResumeSend == CancellableContinuationImplKt.RESUME_TOKEN)) {
                        throw new AssertionError();
                    }
                }
                takeFirstSendOrPeekClosed.completeResumeSend();
                return takeFirstSendOrPeekClosed.getPollResult();
            }
            takeFirstSendOrPeekClosed.undeliveredElement();
        }
    }

    @Nullable
    public Object pollSelectInternal(@NotNull SelectInstance<?> selectInstance) {
        TryPollDesc<E> describeTryPoll = describeTryPoll();
        Object performAtomicTrySelect = selectInstance.performAtomicTrySelect(describeTryPoll);
        if (performAtomicTrySelect != null) {
            return performAtomicTrySelect;
        }
        describeTryPoll.getResult().completeResumeSend();
        return describeTryPoll.getResult().getPollResult();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Nullable
    public final Object receive(@NotNull Continuation<? super E> continuation) {
        Object pollInternal = pollInternal();
        return (pollInternal == AbstractChannelKt.POLL_FAILED || (pollInternal instanceof Closed)) ? m(0, continuation) : pollInternal;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @org.jetbrains.annotations.Nullable
    /* renamed from: receiveCatching-JP2dKIU  reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object mo734receiveCatchingJP2dKIU(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlinx.coroutines.channels.ChannelResult<? extends E>> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof kotlinx.coroutines.channels.AbstractChannel.g
            if (r0 == 0) goto L13
            r0 = r5
            kotlinx.coroutines.channels.AbstractChannel$g r0 = (kotlinx.coroutines.channels.AbstractChannel.g) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.AbstractChannel$g r0 = new kotlinx.coroutines.channels.AbstractChannel$g
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            kotlin.ResultKt.throwOnFailure(r5)
            goto L5b
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L31:
            kotlin.ResultKt.throwOnFailure(r5)
            java.lang.Object r5 = r4.pollInternal()
            kotlinx.coroutines.internal.Symbol r2 = kotlinx.coroutines.channels.AbstractChannelKt.POLL_FAILED
            if (r5 == r2) goto L52
            boolean r0 = r5 instanceof kotlinx.coroutines.channels.Closed
            if (r0 == 0) goto L4b
            kotlinx.coroutines.channels.ChannelResult$Companion r0 = kotlinx.coroutines.channels.ChannelResult.Companion
            kotlinx.coroutines.channels.Closed r5 = (kotlinx.coroutines.channels.Closed) r5
            java.lang.Throwable r5 = r5.closeCause
            java.lang.Object r5 = r0.m753closedJP2dKIU(r5)
            goto L51
        L4b:
            kotlinx.coroutines.channels.ChannelResult$Companion r0 = kotlinx.coroutines.channels.ChannelResult.Companion
            java.lang.Object r5 = r0.m755successJP2dKIU(r5)
        L51:
            return r5
        L52:
            r0.label = r3
            java.lang.Object r5 = r4.m(r3, r0)
            if (r5 != r1) goto L5b
            return r1
        L5b:
            kotlinx.coroutines.channels.ChannelResult r5 = (kotlinx.coroutines.channels.ChannelResult) r5
            java.lang.Object r5 = r5.m752unboximpl()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.AbstractChannel.mo734receiveCatchingJP2dKIU(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in favor of 'receiveCatching'. Please note that the provided replacement does not rethrow channel's close cause as 'receiveOrNull' did, for the detailed replacement please refer to the 'receiveOrNull' documentation", replaceWith = @ReplaceWith(expression = "receiveCatching().getOrNull()", imports = {}))
    @LowPriorityInOverloadResolution
    @Nullable
    public Object receiveOrNull(@NotNull Continuation<? super E> continuation) {
        return Channel.DefaultImpls.receiveOrNull(this, continuation);
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    @Nullable
    public ReceiveOrClosed<E> takeFirstReceiveOrPeekClosed() {
        ReceiveOrClosed<E> takeFirstReceiveOrPeekClosed = super.takeFirstReceiveOrPeekClosed();
        if (takeFirstReceiveOrPeekClosed != null && !(takeFirstReceiveOrPeekClosed instanceof Closed)) {
            onReceiveDequeued();
        }
        return takeFirstReceiveOrPeekClosed;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @NotNull
    /* renamed from: tryReceive-PtdJZtk  reason: not valid java name */
    public final Object mo735tryReceivePtdJZtk() {
        Object pollInternal = pollInternal();
        return pollInternal == AbstractChannelKt.POLL_FAILED ? ChannelResult.Companion.m754failurePtdJZtk() : pollInternal instanceof Closed ? ChannelResult.Companion.m753closedJP2dKIU(((Closed) pollInternal).closeCause) : ChannelResult.Companion.m755successJP2dKIU(pollInternal);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final void cancel(@Nullable CancellationException cancellationException) {
        if (isClosedForReceive()) {
            return;
        }
        if (cancellationException == null) {
            cancellationException = new CancellationException(Intrinsics.stringPlus(DebugStringsKt.getClassSimpleName(this), " was cancelled"));
        }
        cancel(cancellationException);
    }
}

package kotlinx.coroutines.selects;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.PublishedApi;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.CompletedExceptionally;
import kotlinx.coroutines.CompletionStateKt;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobCancellingNode;
import kotlinx.coroutines.internal.AtomicDesc;
import kotlinx.coroutines.internal.AtomicOp;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.OpDescriptor;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@PublishedApi
/* loaded from: classes12.dex */
public final class SelectBuilderImpl<R> extends LockFreeLinkedListHead implements SelectBuilder<R>, SelectInstance<R>, Continuation<R>, CoroutineStackFrame {
    public static final /* synthetic */ AtomicReferenceFieldUpdater l = AtomicReferenceFieldUpdater.newUpdater(SelectBuilderImpl.class, Object.class, "_state");
    public static final /* synthetic */ AtomicReferenceFieldUpdater m = AtomicReferenceFieldUpdater.newUpdater(SelectBuilderImpl.class, Object.class, "_result");
    @NotNull
    public final Continuation<R> k;
    @NotNull
    public volatile /* synthetic */ Object _state = SelectKt.getNOT_SELECTED();
    @NotNull
    private volatile /* synthetic */ Object _result = SelectKt.access$getUNDECIDED$p();
    @NotNull
    private volatile /* synthetic */ Object _parentHandle = null;

    /* loaded from: classes12.dex */
    public static final class a extends AtomicOp<Object> {
        @JvmField
        @NotNull
        public final SelectBuilderImpl<?> b;
        @JvmField
        @NotNull
        public final AtomicDesc c;
        public final long d = SelectKt.access$getSelectOpSequenceNumber$p().next();

        public a(@NotNull SelectBuilderImpl<?> selectBuilderImpl, @NotNull AtomicDesc atomicDesc) {
            this.b = selectBuilderImpl;
            this.c = atomicDesc;
            atomicDesc.setAtomicOp(this);
        }

        public final void a(Object obj) {
            boolean z = obj == null;
            if (SelectBuilderImpl.l.compareAndSet(this.b, this, z ? null : SelectKt.getNOT_SELECTED()) && z) {
                this.b.e();
            }
        }

        public final Object b() {
            SelectBuilderImpl<?> selectBuilderImpl = this.b;
            while (true) {
                Object obj = selectBuilderImpl._state;
                if (obj == this) {
                    return null;
                }
                if (obj instanceof OpDescriptor) {
                    ((OpDescriptor) obj).perform(this.b);
                } else if (obj == SelectKt.getNOT_SELECTED()) {
                    if (SelectBuilderImpl.l.compareAndSet(this.b, SelectKt.getNOT_SELECTED(), this)) {
                        return null;
                    }
                } else {
                    return SelectKt.getALREADY_SELECTED();
                }
            }
        }

        public final void c() {
            SelectBuilderImpl.l.compareAndSet(this.b, this, SelectKt.getNOT_SELECTED());
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        public void complete(@Nullable Object obj, @Nullable Object obj2) {
            a(obj2);
            this.c.complete(this, obj2);
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        public long getOpSequence() {
            return this.d;
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        @Nullable
        public Object prepare(@Nullable Object obj) {
            Object b;
            if (obj != null || (b = b()) == null) {
                try {
                    return this.c.prepare(this);
                } catch (Throwable th) {
                    if (obj == null) {
                        c();
                    }
                    throw th;
                }
            }
            return b;
        }

        @Override // kotlinx.coroutines.internal.OpDescriptor
        @NotNull
        public String toString() {
            return "AtomicSelectOp(sequence=" + getOpSequence() + HexStringBuilder.COMMENT_END_CHAR;
        }
    }

    /* loaded from: classes12.dex */
    public static final class b extends LockFreeLinkedListNode {
        @JvmField
        @NotNull
        public final DisposableHandle k;

        public b(@NotNull DisposableHandle disposableHandle) {
            this.k = disposableHandle;
        }
    }

    /* loaded from: classes12.dex */
    public static final class c extends OpDescriptor {
        @JvmField
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final LockFreeLinkedListNode.PrepareOp f14194a;

        public c(@NotNull LockFreeLinkedListNode.PrepareOp prepareOp) {
            this.f14194a = prepareOp;
        }

        @Override // kotlinx.coroutines.internal.OpDescriptor
        @NotNull
        public AtomicOp<?> getAtomicOp() {
            return this.f14194a.getAtomicOp();
        }

        @Override // kotlinx.coroutines.internal.OpDescriptor
        @Nullable
        public Object perform(@Nullable Object obj) {
            Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.selects.SelectBuilderImpl<*>");
            SelectBuilderImpl selectBuilderImpl = (SelectBuilderImpl) obj;
            this.f14194a.finishPrepare();
            Object decide = this.f14194a.getAtomicOp().decide(null);
            SelectBuilderImpl.l.compareAndSet(selectBuilderImpl, this, decide == null ? this.f14194a.desc : SelectKt.getNOT_SELECTED());
            return decide;
        }
    }

    /* loaded from: classes12.dex */
    public final class d extends JobCancellingNode {
        public d() {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        @Override // kotlinx.coroutines.CompletionHandlerBase
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public void invoke2(@Nullable Throwable th) {
            if (SelectBuilderImpl.this.trySelect()) {
                SelectBuilderImpl.this.resumeSelectWithException(getJob().getCancellationException());
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SelectBuilderImpl(@NotNull Continuation<? super R> continuation) {
        this.k = continuation;
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public void disposeOnSelect(@NotNull DisposableHandle disposableHandle) {
        b bVar = new b(disposableHandle);
        if (!isSelected()) {
            addLast(bVar);
            if (!isSelected()) {
                return;
            }
        }
        disposableHandle.dispose();
    }

    public final void e() {
        DisposableHandle f = f();
        if (f != null) {
            f.dispose();
        }
        for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) getNext(); !Intrinsics.areEqual(lockFreeLinkedListNode, this); lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode()) {
            if (lockFreeLinkedListNode instanceof b) {
                ((b) lockFreeLinkedListNode).k.dispose();
            }
        }
    }

    public final DisposableHandle f() {
        return (DisposableHandle) this._parentHandle;
    }

    public final void g(DisposableHandle disposableHandle) {
        this._parentHandle = disposableHandle;
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    @Nullable
    public CoroutineStackFrame getCallerFrame() {
        Continuation<R> continuation = this.k;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    @NotNull
    public Continuation<R> getCompletion() {
        return this;
    }

    @Override // kotlin.coroutines.Continuation
    @NotNull
    public CoroutineContext getContext() {
        return this.k.getContext();
    }

    @PublishedApi
    @Nullable
    public final Object getResult() {
        if (!isSelected()) {
            initCancellability();
        }
        Object obj = this._result;
        if (obj == SelectKt.access$getUNDECIDED$p()) {
            if (m.compareAndSet(this, SelectKt.access$getUNDECIDED$p(), kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED())) {
                return kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            }
            obj = this._result;
        }
        if (obj != SelectKt.access$getRESUMED$p()) {
            if (obj instanceof CompletedExceptionally) {
                throw ((CompletedExceptionally) obj).cause;
            }
            return obj;
        }
        throw new IllegalStateException("Already resumed");
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    @Nullable
    public StackTraceElement getStackTraceElement() {
        return null;
    }

    @PublishedApi
    public final void handleBuilderException(@NotNull Throwable th) {
        if (trySelect()) {
            Result.Companion companion = Result.Companion;
            resumeWith(Result.m123constructorimpl(ResultKt.createFailure(th)));
        } else if (th instanceof CancellationException) {
        } else {
            Object result = getResult();
            if (result instanceof CompletedExceptionally) {
                Throwable th2 = ((CompletedExceptionally) result).cause;
                if (DebugKt.getRECOVER_STACK_TRACES()) {
                    th2 = StackTraceRecoveryKt.unwrapImpl(th2);
                }
                if (th2 == (!DebugKt.getRECOVER_STACK_TRACES() ? th : StackTraceRecoveryKt.unwrapImpl(th))) {
                    return;
                }
            }
            CoroutineExceptionHandlerKt.handleCoroutineException(getContext(), th);
        }
    }

    public final void initCancellability() {
        Job job = (Job) getContext().get(Job.Key);
        if (job == null) {
            return;
        }
        DisposableHandle invokeOnCompletion$default = Job.DefaultImpls.invokeOnCompletion$default(job, true, false, new d(), 2, null);
        g(invokeOnCompletion$default);
        if (isSelected()) {
            invokeOnCompletion$default.dispose();
        }
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public <P, Q> void invoke(@NotNull SelectClause2<? super P, ? extends Q> selectClause2, @NotNull Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2) {
        SelectBuilder.DefaultImpls.invoke(this, selectClause2, function2);
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public boolean isSelected() {
        while (true) {
            Object obj = this._state;
            if (obj == SelectKt.getNOT_SELECTED()) {
                return false;
            }
            if (!(obj instanceof OpDescriptor)) {
                return true;
            }
            ((OpDescriptor) obj).perform(this);
        }
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public void onTimeout(long j, @NotNull final Function1<? super Continuation<? super R>, ? extends Object> function1) {
        if (j <= 0) {
            if (trySelect()) {
                UndispatchedKt.startCoroutineUnintercepted(function1, getCompletion());
                return;
            }
            return;
        }
        disposeOnSelect(DelayKt.getDelay(getContext()).invokeOnTimeout(j, new Runnable() { // from class: kotlinx.coroutines.selects.SelectBuilderImpl$onTimeout$$inlined$Runnable$1
            @Override // java.lang.Runnable
            public final void run() {
                if (SelectBuilderImpl.this.trySelect()) {
                    CancellableKt.startCoroutineCancellable(function1, SelectBuilderImpl.this.getCompletion());
                }
            }
        }, getContext()));
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    @Nullable
    public Object performAtomicTrySelect(@NotNull AtomicDesc atomicDesc) {
        return new a(this, atomicDesc).perform(null);
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public void resumeSelectWithException(@NotNull Throwable th) {
        if (DebugKt.getASSERTIONS_ENABLED() && !isSelected()) {
            throw new AssertionError();
        }
        while (true) {
            Object obj = this._result;
            if (obj == SelectKt.access$getUNDECIDED$p()) {
                Continuation<R> continuation = this.k;
                if (m.compareAndSet(this, SelectKt.access$getUNDECIDED$p(), new CompletedExceptionally((DebugKt.getRECOVER_STACK_TRACES() && (continuation instanceof CoroutineStackFrame)) ? StackTraceRecoveryKt.g(th, (CoroutineStackFrame) continuation) : th, false, 2, null))) {
                    return;
                }
            } else if (obj != kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()) {
                throw new IllegalStateException("Already resumed");
            } else {
                if (m.compareAndSet(this, kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED(), SelectKt.access$getRESUMED$p())) {
                    Continuation intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(this.k);
                    Result.Companion companion = Result.Companion;
                    intercepted.resumeWith(Result.m123constructorimpl(ResultKt.createFailure(th)));
                    return;
                }
            }
        }
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(@NotNull Object obj) {
        if (DebugKt.getASSERTIONS_ENABLED() && !isSelected()) {
            throw new AssertionError();
        }
        while (true) {
            Object obj2 = this._result;
            if (obj2 == SelectKt.access$getUNDECIDED$p()) {
                if (m.compareAndSet(this, SelectKt.access$getUNDECIDED$p(), CompletionStateKt.toState$default(obj, null, 1, null))) {
                    return;
                }
            } else if (obj2 != kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()) {
                throw new IllegalStateException("Already resumed");
            } else {
                if (m.compareAndSet(this, kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED(), SelectKt.access$getRESUMED$p())) {
                    if (Result.m129isFailureimpl(obj)) {
                        Continuation<R> continuation = this.k;
                        Throwable m126exceptionOrNullimpl = Result.m126exceptionOrNullimpl(obj);
                        Intrinsics.checkNotNull(m126exceptionOrNullimpl);
                        Result.Companion companion = Result.Companion;
                        if (DebugKt.getRECOVER_STACK_TRACES() && (continuation instanceof CoroutineStackFrame)) {
                            m126exceptionOrNullimpl = StackTraceRecoveryKt.g(m126exceptionOrNullimpl, (CoroutineStackFrame) continuation);
                        }
                        continuation.resumeWith(Result.m123constructorimpl(ResultKt.createFailure(m126exceptionOrNullimpl)));
                        return;
                    }
                    this.k.resumeWith(obj);
                    return;
                }
            }
        }
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    @NotNull
    public String toString() {
        return "SelectInstance(state=" + this._state + ", result=" + this._result + HexStringBuilder.COMMENT_END_CHAR;
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public boolean trySelect() {
        Object trySelectOther = trySelectOther(null);
        if (trySelectOther == CancellableContinuationImplKt.RESUME_TOKEN) {
            return true;
        }
        if (trySelectOther == null) {
            return false;
        }
        throw new IllegalStateException(Intrinsics.stringPlus("Unexpected trySelectIdempotent result ", trySelectOther).toString());
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0031, code lost:
        e();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0036, code lost:
        return kotlinx.coroutines.CancellableContinuationImplKt.RESUME_TOKEN;
     */
    @Override // kotlinx.coroutines.selects.SelectInstance
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object trySelectOther(@org.jetbrains.annotations.Nullable kotlinx.coroutines.internal.LockFreeLinkedListNode.PrepareOp r4) {
        /*
            r3 = this;
        L0:
            java.lang.Object r0 = r3._state
            java.lang.Object r1 = kotlinx.coroutines.selects.SelectKt.getNOT_SELECTED()
            r2 = 0
            if (r0 != r1) goto L37
            if (r4 != 0) goto L18
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = kotlinx.coroutines.selects.SelectBuilderImpl.l
            java.lang.Object r1 = kotlinx.coroutines.selects.SelectKt.getNOT_SELECTED()
            boolean r0 = r0.compareAndSet(r3, r1, r2)
            if (r0 != 0) goto L31
            goto L0
        L18:
            kotlinx.coroutines.selects.SelectBuilderImpl$c r0 = new kotlinx.coroutines.selects.SelectBuilderImpl$c
            r0.<init>(r4)
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r1 = kotlinx.coroutines.selects.SelectBuilderImpl.l
            java.lang.Object r2 = kotlinx.coroutines.selects.SelectKt.getNOT_SELECTED()
            boolean r1 = r1.compareAndSet(r3, r2, r0)
            if (r1 != 0) goto L2a
            goto L0
        L2a:
            java.lang.Object r4 = r0.perform(r3)
            if (r4 == 0) goto L31
            return r4
        L31:
            r3.e()
            kotlinx.coroutines.internal.Symbol r4 = kotlinx.coroutines.CancellableContinuationImplKt.RESUME_TOKEN
            return r4
        L37:
            boolean r1 = r0 instanceof kotlinx.coroutines.internal.OpDescriptor
            if (r1 == 0) goto L6b
            if (r4 == 0) goto L65
            kotlinx.coroutines.internal.AtomicOp r1 = r4.getAtomicOp()
            boolean r2 = r1 instanceof kotlinx.coroutines.selects.SelectBuilderImpl.a
            if (r2 == 0) goto L59
            r2 = r1
            kotlinx.coroutines.selects.SelectBuilderImpl$a r2 = (kotlinx.coroutines.selects.SelectBuilderImpl.a) r2
            kotlinx.coroutines.selects.SelectBuilderImpl<?> r2 = r2.b
            if (r2 == r3) goto L4d
            goto L59
        L4d:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r0 = "Cannot use matching select clauses on the same object"
            java.lang.String r0 = r0.toString()
            r4.<init>(r0)
            throw r4
        L59:
            r2 = r0
            kotlinx.coroutines.internal.OpDescriptor r2 = (kotlinx.coroutines.internal.OpDescriptor) r2
            boolean r1 = r1.isEarlierThan(r2)
            if (r1 == 0) goto L65
            java.lang.Object r4 = kotlinx.coroutines.internal.AtomicKt.RETRY_ATOMIC
            return r4
        L65:
            kotlinx.coroutines.internal.OpDescriptor r0 = (kotlinx.coroutines.internal.OpDescriptor) r0
            r0.perform(r3)
            goto L0
        L6b:
            if (r4 != 0) goto L6e
            return r2
        L6e:
            kotlinx.coroutines.internal.LockFreeLinkedListNode$AbstractAtomicDesc r4 = r4.desc
            if (r0 != r4) goto L75
            kotlinx.coroutines.internal.Symbol r4 = kotlinx.coroutines.CancellableContinuationImplKt.RESUME_TOKEN
            return r4
        L75:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.selects.SelectBuilderImpl.trySelectOther(kotlinx.coroutines.internal.LockFreeLinkedListNode$PrepareOp):java.lang.Object");
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public void invoke(@NotNull SelectClause0 selectClause0, @NotNull Function1<? super Continuation<? super R>, ? extends Object> function1) {
        selectClause0.registerSelectClause0(this, function1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.selects.SelectBuilder
    public <Q> void invoke(@NotNull SelectClause1<? extends Q> selectClause1, @NotNull Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2) {
        selectClause1.registerSelectClause1(this, function2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.selects.SelectBuilder
    public <P, Q> void invoke(@NotNull SelectClause2<? super P, ? extends Q> selectClause2, P p, @NotNull Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2) {
        selectClause2.registerSelectClause2(this, p, function2);
    }
}

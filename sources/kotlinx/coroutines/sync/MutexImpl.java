package kotlinx.coroutines.sync;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.internal.AtomicDesc;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.AtomicOp;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.OpDescriptor;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class MutexImpl implements Mutex, SelectClause2<Object, Mutex> {
    public static final /* synthetic */ AtomicReferenceFieldUpdater h = AtomicReferenceFieldUpdater.newUpdater(MutexImpl.class, Object.class, "_state");
    @NotNull
    public volatile /* synthetic */ Object _state;

    /* loaded from: classes12.dex */
    public final class a extends c {
        @NotNull
        public final CancellableContinuation<Unit> m;

        /* renamed from: kotlinx.coroutines.sync.MutexImpl$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public static final class C0897a extends Lambda implements Function1<Throwable, Unit> {
            public final /* synthetic */ MutexImpl this$0;
            public final /* synthetic */ a this$1;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0897a(MutexImpl mutexImpl, a aVar) {
                super(1);
                this.this$0 = mutexImpl;
                this.this$1 = aVar;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Throwable th) {
                this.this$0.unlock(this.this$1.k);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public a(@Nullable Object obj, @NotNull CancellableContinuation<? super Unit> cancellableContinuation) {
            super(MutexImpl.this, obj);
            this.m = cancellableContinuation;
        }

        @Override // kotlinx.coroutines.sync.MutexImpl.c
        public void e() {
            this.m.completeResume(CancellableContinuationImplKt.RESUME_TOKEN);
        }

        @Override // kotlinx.coroutines.sync.MutexImpl.c
        public boolean g() {
            return f() && this.m.tryResume(Unit.INSTANCE, null, new C0897a(MutexImpl.this, this)) != null;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        @NotNull
        public String toString() {
            return "LockCont[" + this.k + ", " + this.m + "] for " + MutexImpl.this;
        }
    }

    /* loaded from: classes12.dex */
    public final class b<R> extends c {
        @JvmField
        @NotNull
        public final SelectInstance<R> m;
        @JvmField
        @NotNull
        public final Function2<Mutex, Continuation<? super R>, Object> n;

        /* loaded from: classes12.dex */
        public static final class a extends Lambda implements Function1<Throwable, Unit> {
            public final /* synthetic */ MutexImpl this$0;
            public final /* synthetic */ b<R> this$1;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(MutexImpl mutexImpl, b<R> bVar) {
                super(1);
                this.this$0 = mutexImpl;
                this.this$1 = bVar;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Throwable th) {
                this.this$0.unlock(this.this$1.k);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public b(@Nullable Object obj, @NotNull SelectInstance<? super R> selectInstance, @NotNull Function2<? super Mutex, ? super Continuation<? super R>, ? extends Object> function2) {
            super(MutexImpl.this, obj);
            this.m = selectInstance;
            this.n = function2;
        }

        @Override // kotlinx.coroutines.sync.MutexImpl.c
        public void e() {
            CancellableKt.startCoroutineCancellable(this.n, MutexImpl.this, this.m.getCompletion(), new a(MutexImpl.this, this));
        }

        @Override // kotlinx.coroutines.sync.MutexImpl.c
        public boolean g() {
            return f() && this.m.trySelect();
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        @NotNull
        public String toString() {
            return "LockSelect[" + this.k + ", " + this.m + "] for " + MutexImpl.this;
        }
    }

    /* loaded from: classes12.dex */
    public abstract class c extends LockFreeLinkedListNode implements DisposableHandle {
        public static final /* synthetic */ AtomicIntegerFieldUpdater l = AtomicIntegerFieldUpdater.newUpdater(c.class, "isTaken");
        @NotNull
        private volatile /* synthetic */ int isTaken = 0;
        @JvmField
        @Nullable
        public final Object k;

        public c(@Nullable MutexImpl mutexImpl, Object obj) {
            this.k = obj;
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public final void dispose() {
            mo775remove();
        }

        public abstract void e();

        public final boolean f() {
            return l.compareAndSet(this, 0, 1);
        }

        public abstract boolean g();
    }

    /* loaded from: classes12.dex */
    public static final class d extends LockFreeLinkedListHead {
        @JvmField
        @NotNull
        public Object k;

        public d(@NotNull Object obj) {
            this.k = obj;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        @NotNull
        public String toString() {
            return "LockedQueue[" + this.k + ']';
        }
    }

    /* loaded from: classes12.dex */
    public static final class e extends AtomicDesc {
        @JvmField
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final MutexImpl f14197a;
        @JvmField
        @Nullable
        public final Object b;

        /* loaded from: classes12.dex */
        public final class a extends OpDescriptor {
            @NotNull

            /* renamed from: a  reason: collision with root package name */
            public final AtomicOp<?> f14198a;

            public a(@NotNull e eVar, AtomicOp<?> atomicOp) {
                this.f14198a = atomicOp;
            }

            @Override // kotlinx.coroutines.internal.OpDescriptor
            @NotNull
            public AtomicOp<?> getAtomicOp() {
                return this.f14198a;
            }

            @Override // kotlinx.coroutines.internal.OpDescriptor
            @Nullable
            public Object perform(@Nullable Object obj) {
                Object atomicOp = getAtomicOp().isDecided() ? MutexKt.f : getAtomicOp();
                Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.sync.MutexImpl");
                MutexImpl.h.compareAndSet((MutexImpl) obj, this, atomicOp);
                return null;
            }
        }

        public e(@NotNull MutexImpl mutexImpl, @Nullable Object obj) {
            this.f14197a = mutexImpl;
            this.b = obj;
        }

        @Override // kotlinx.coroutines.internal.AtomicDesc
        public void complete(@NotNull AtomicOp<?> atomicOp, @Nullable Object obj) {
            kotlinx.coroutines.sync.b bVar;
            if (obj != null) {
                bVar = MutexKt.f;
            } else {
                Object obj2 = this.b;
                bVar = obj2 == null ? MutexKt.e : new kotlinx.coroutines.sync.b(obj2);
            }
            MutexImpl.h.compareAndSet(this.f14197a, atomicOp, bVar);
        }

        @Override // kotlinx.coroutines.internal.AtomicDesc
        @Nullable
        public Object prepare(@NotNull AtomicOp<?> atomicOp) {
            kotlinx.coroutines.sync.b bVar;
            Symbol symbol;
            a aVar = new a(this, atomicOp);
            MutexImpl mutexImpl = this.f14197a;
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = MutexImpl.h;
            bVar = MutexKt.f;
            if (!atomicReferenceFieldUpdater.compareAndSet(mutexImpl, bVar, aVar)) {
                symbol = MutexKt.f14199a;
                return symbol;
            }
            return aVar.perform(this.f14197a);
        }
    }

    /* loaded from: classes12.dex */
    public static final class f extends AtomicOp<MutexImpl> {
        @JvmField
        @NotNull
        public final d b;

        public f(@NotNull d dVar) {
            this.b = dVar;
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        /* renamed from: a */
        public void complete(@NotNull MutexImpl mutexImpl, @Nullable Object obj) {
            MutexImpl.h.compareAndSet(mutexImpl, this, obj == null ? MutexKt.f : this.b);
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        @Nullable
        /* renamed from: b */
        public Object prepare(@NotNull MutexImpl mutexImpl) {
            Symbol symbol;
            if (this.b.isEmpty()) {
                return null;
            }
            symbol = MutexKt.b;
            return symbol;
        }
    }

    /* loaded from: classes12.dex */
    public static final class g extends Lambda implements Function1<Throwable, Unit> {
        public final /* synthetic */ Object $owner;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public g(Object obj) {
            super(1);
            this.$owner = obj;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull Throwable th) {
            MutexImpl.this.unlock(this.$owner);
        }
    }

    public MutexImpl(boolean z) {
        this._state = z ? MutexKt.e : MutexKt.f;
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x006e, code lost:
        kotlinx.coroutines.CancellableContinuationKt.removeOnCancellation(r0, r1);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object a(java.lang.Object r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r6 = this;
            kotlin.coroutines.Continuation r0 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.intercepted(r8)
            kotlinx.coroutines.CancellableContinuationImpl r0 = kotlinx.coroutines.CancellableContinuationKt.getOrCreateCancellableContinuation(r0)
            kotlinx.coroutines.sync.MutexImpl$a r1 = new kotlinx.coroutines.sync.MutexImpl$a
            r1.<init>(r7, r0)
        Ld:
            java.lang.Object r2 = r6._state
            boolean r3 = r2 instanceof kotlinx.coroutines.sync.b
            if (r3 == 0) goto L4a
            r3 = r2
            kotlinx.coroutines.sync.b r3 = (kotlinx.coroutines.sync.b) r3
            java.lang.Object r4 = r3.f14201a
            kotlinx.coroutines.internal.Symbol r5 = kotlinx.coroutines.sync.MutexKt.access$getUNLOCKED$p()
            if (r4 == r5) goto L2b
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r4 = kotlinx.coroutines.sync.MutexImpl.h
            kotlinx.coroutines.sync.MutexImpl$d r5 = new kotlinx.coroutines.sync.MutexImpl$d
            java.lang.Object r3 = r3.f14201a
            r5.<init>(r3)
            r4.compareAndSet(r6, r2, r5)
            goto Ld
        L2b:
            if (r7 != 0) goto L32
            kotlinx.coroutines.sync.b r3 = kotlinx.coroutines.sync.MutexKt.access$getEMPTY_LOCKED$p()
            goto L37
        L32:
            kotlinx.coroutines.sync.b r3 = new kotlinx.coroutines.sync.b
            r3.<init>(r7)
        L37:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r4 = kotlinx.coroutines.sync.MutexImpl.h
            boolean r2 = r4.compareAndSet(r6, r2, r3)
            if (r2 == 0) goto Ld
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            kotlinx.coroutines.sync.MutexImpl$g r2 = new kotlinx.coroutines.sync.MutexImpl$g
            r2.<init>(r7)
            r0.resume(r1, r2)
            goto L71
        L4a:
            boolean r3 = r2 instanceof kotlinx.coroutines.sync.MutexImpl.d
            if (r3 == 0) goto L98
            r3 = r2
            kotlinx.coroutines.sync.MutexImpl$d r3 = (kotlinx.coroutines.sync.MutexImpl.d) r3
            java.lang.Object r4 = r3.k
            if (r4 == r7) goto L57
            r4 = 1
            goto L58
        L57:
            r4 = 0
        L58:
            if (r4 == 0) goto L88
            r3.addLast(r1)
            java.lang.Object r3 = r6._state
            if (r3 == r2) goto L6e
            boolean r2 = r1.f()
            if (r2 != 0) goto L68
            goto L6e
        L68:
            kotlinx.coroutines.sync.MutexImpl$a r1 = new kotlinx.coroutines.sync.MutexImpl$a
            r1.<init>(r7, r0)
            goto Ld
        L6e:
            kotlinx.coroutines.CancellableContinuationKt.removeOnCancellation(r0, r1)
        L71:
            java.lang.Object r7 = r0.getResult()
            java.lang.Object r0 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            if (r7 != r0) goto L7e
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r8)
        L7e:
            java.lang.Object r8 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            if (r7 != r8) goto L85
            return r7
        L85:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L88:
            java.lang.String r8 = "Already locked by "
            java.lang.String r7 = kotlin.jvm.internal.Intrinsics.stringPlus(r8, r7)
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r7 = r7.toString()
            r8.<init>(r7)
            throw r8
        L98:
            boolean r3 = r2 instanceof kotlinx.coroutines.internal.OpDescriptor
            if (r3 == 0) goto La3
            kotlinx.coroutines.internal.OpDescriptor r2 = (kotlinx.coroutines.internal.OpDescriptor) r2
            r2.perform(r6)
            goto Ld
        La3:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "Illegal state "
            java.lang.String r8 = kotlin.jvm.internal.Intrinsics.stringPlus(r8, r2)
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.sync.MutexImpl.a(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.sync.Mutex
    @NotNull
    public SelectClause2<Object, Mutex> getOnLock() {
        return this;
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public boolean holdsLock(@NotNull Object obj) {
        Object obj2 = this._state;
        if (obj2 instanceof kotlinx.coroutines.sync.b) {
            if (((kotlinx.coroutines.sync.b) obj2).f14201a == obj) {
                return true;
            }
        } else if ((obj2 instanceof d) && ((d) obj2).k == obj) {
            return true;
        }
        return false;
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public boolean isLocked() {
        Symbol symbol;
        while (true) {
            Object obj = this._state;
            if (obj instanceof kotlinx.coroutines.sync.b) {
                Object obj2 = ((kotlinx.coroutines.sync.b) obj).f14201a;
                symbol = MutexKt.d;
                return obj2 != symbol;
            } else if (obj instanceof d) {
                return true;
            } else {
                if (!(obj instanceof OpDescriptor)) {
                    throw new IllegalStateException(Intrinsics.stringPlus("Illegal state ", obj).toString());
                }
                ((OpDescriptor) obj).perform(this);
            }
        }
    }

    public final boolean isLockedEmptyQueueState$kotlinx_coroutines_core() {
        Object obj = this._state;
        return (obj instanceof d) && ((d) obj).isEmpty();
    }

    @Override // kotlinx.coroutines.sync.Mutex
    @Nullable
    public Object lock(@Nullable Object obj, @NotNull Continuation<? super Unit> continuation) {
        Object a2;
        return (!tryLock(obj) && (a2 = a(obj, continuation)) == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()) ? a2 : Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.selects.SelectClause2
    public <R> void registerSelectClause2(@NotNull SelectInstance<? super R> selectInstance, @Nullable Object obj, @NotNull Function2<? super Mutex, ? super Continuation<? super R>, ? extends Object> function2) {
        Symbol symbol;
        Symbol symbol2;
        while (!selectInstance.isSelected()) {
            Object obj2 = this._state;
            if (obj2 instanceof kotlinx.coroutines.sync.b) {
                kotlinx.coroutines.sync.b bVar = (kotlinx.coroutines.sync.b) obj2;
                Object obj3 = bVar.f14201a;
                symbol = MutexKt.d;
                if (obj3 != symbol) {
                    h.compareAndSet(this, obj2, new d(bVar.f14201a));
                } else {
                    Object performAtomicTrySelect = selectInstance.performAtomicTrySelect(new e(this, obj));
                    if (performAtomicTrySelect == null) {
                        UndispatchedKt.startCoroutineUnintercepted(function2, this, selectInstance.getCompletion());
                        return;
                    } else if (performAtomicTrySelect == SelectKt.getALREADY_SELECTED()) {
                        return;
                    } else {
                        symbol2 = MutexKt.f14199a;
                        if (performAtomicTrySelect != symbol2 && performAtomicTrySelect != AtomicKt.RETRY_ATOMIC) {
                            throw new IllegalStateException(Intrinsics.stringPlus("performAtomicTrySelect(TryLockDesc) returned ", performAtomicTrySelect).toString());
                        }
                    }
                }
            } else if (obj2 instanceof d) {
                d dVar = (d) obj2;
                if (dVar.k != obj) {
                    b bVar2 = new b(obj, selectInstance, function2);
                    dVar.addLast(bVar2);
                    if (this._state == obj2 || !bVar2.f()) {
                        selectInstance.disposeOnSelect(bVar2);
                        return;
                    }
                } else {
                    throw new IllegalStateException(Intrinsics.stringPlus("Already locked by ", obj).toString());
                }
            } else if (!(obj2 instanceof OpDescriptor)) {
                throw new IllegalStateException(Intrinsics.stringPlus("Illegal state ", obj2).toString());
            } else {
                ((OpDescriptor) obj2).perform(this);
            }
        }
    }

    @NotNull
    public String toString() {
        while (true) {
            Object obj = this._state;
            if (obj instanceof kotlinx.coroutines.sync.b) {
                return "Mutex[" + ((kotlinx.coroutines.sync.b) obj).f14201a + ']';
            } else if (!(obj instanceof OpDescriptor)) {
                if (obj instanceof d) {
                    return "Mutex[" + ((d) obj).k + ']';
                }
                throw new IllegalStateException(Intrinsics.stringPlus("Illegal state ", obj).toString());
            } else {
                ((OpDescriptor) obj).perform(this);
            }
        }
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public boolean tryLock(@Nullable Object obj) {
        Symbol symbol;
        while (true) {
            Object obj2 = this._state;
            if (obj2 instanceof kotlinx.coroutines.sync.b) {
                Object obj3 = ((kotlinx.coroutines.sync.b) obj2).f14201a;
                symbol = MutexKt.d;
                if (obj3 != symbol) {
                    return false;
                }
                if (h.compareAndSet(this, obj2, obj == null ? MutexKt.e : new kotlinx.coroutines.sync.b(obj))) {
                    return true;
                }
            } else if (obj2 instanceof d) {
                if (((d) obj2).k != obj) {
                    return false;
                }
                throw new IllegalStateException(Intrinsics.stringPlus("Already locked by ", obj).toString());
            } else if (!(obj2 instanceof OpDescriptor)) {
                throw new IllegalStateException(Intrinsics.stringPlus("Illegal state ", obj2).toString());
            } else {
                ((OpDescriptor) obj2).perform(this);
            }
        }
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public void unlock(@Nullable Object obj) {
        kotlinx.coroutines.sync.b bVar;
        Symbol symbol;
        while (true) {
            Object obj2 = this._state;
            if (obj2 instanceof kotlinx.coroutines.sync.b) {
                if (obj == null) {
                    Object obj3 = ((kotlinx.coroutines.sync.b) obj2).f14201a;
                    symbol = MutexKt.d;
                    if (!(obj3 != symbol)) {
                        throw new IllegalStateException("Mutex is not locked".toString());
                    }
                } else {
                    kotlinx.coroutines.sync.b bVar2 = (kotlinx.coroutines.sync.b) obj2;
                    if (!(bVar2.f14201a == obj)) {
                        throw new IllegalStateException(("Mutex is locked by " + bVar2.f14201a + " but expected " + obj).toString());
                    }
                }
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = h;
                bVar = MutexKt.f;
                if (atomicReferenceFieldUpdater.compareAndSet(this, obj2, bVar)) {
                    return;
                }
            } else if (obj2 instanceof OpDescriptor) {
                ((OpDescriptor) obj2).perform(this);
            } else if (obj2 instanceof d) {
                if (obj != null) {
                    d dVar = (d) obj2;
                    if (!(dVar.k == obj)) {
                        throw new IllegalStateException(("Mutex is locked by " + dVar.k + " but expected " + obj).toString());
                    }
                }
                d dVar2 = (d) obj2;
                LockFreeLinkedListNode removeFirstOrNull = dVar2.removeFirstOrNull();
                if (removeFirstOrNull == null) {
                    f fVar = new f(dVar2);
                    if (h.compareAndSet(this, obj2, fVar) && fVar.perform(this) == null) {
                        return;
                    }
                } else {
                    c cVar = (c) removeFirstOrNull;
                    if (cVar.g()) {
                        Object obj4 = cVar.k;
                        if (obj4 == null) {
                            obj4 = MutexKt.c;
                        }
                        dVar2.k = obj4;
                        cVar.e();
                        return;
                    }
                }
            } else {
                throw new IllegalStateException(Intrinsics.stringPlus("Illegal state ", obj2).toString());
            }
        }
    }
}

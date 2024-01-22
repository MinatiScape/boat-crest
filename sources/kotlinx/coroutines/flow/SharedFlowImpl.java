package kotlinx.coroutines.flow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import kotlin.Result;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowKt;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;
import kotlinx.coroutines.flow.internal.FusibleFlow;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public class SharedFlowImpl<T> extends AbstractSharedFlow<SharedFlowSlot> implements MutableSharedFlow<T>, CancellableFlow<T>, FusibleFlow<T> {
    public final int l;
    public final int m;
    @NotNull
    public final BufferOverflow n;
    @Nullable
    public Object[] o;
    public long p;
    public long q;
    public int r;
    public int s;

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

    /* loaded from: classes12.dex */
    public static final class a implements DisposableHandle {
        @JvmField
        @NotNull
        public final SharedFlowImpl<?> h;
        @JvmField
        public long i;
        @JvmField
        @Nullable
        public final Object j;
        @JvmField
        @NotNull
        public final Continuation<Unit> k;

        /* JADX WARN: Multi-variable type inference failed */
        public a(@NotNull SharedFlowImpl<?> sharedFlowImpl, long j, @Nullable Object obj, @NotNull Continuation<? super Unit> continuation) {
            this.h = sharedFlowImpl;
            this.i = j;
            this.j = obj;
            this.k = continuation;
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public void dispose() {
            this.h.b(this);
        }
    }

    @DebugMetadata(c = "kotlinx.coroutines.flow.SharedFlowImpl", f = "SharedFlow.kt", i = {0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2}, l = {373, 380, 383}, m = "collect$suspendImpl", n = {"this", "collector", "slot", "this", "collector", "slot", "collectorJob", "this", "collector", "slot", "collectorJob"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3"})
    /* loaded from: classes12.dex */
    public static final class b extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public Object L$3;
        public int label;
        public /* synthetic */ Object result;
        public final /* synthetic */ SharedFlowImpl<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(SharedFlowImpl<T> sharedFlowImpl, Continuation<? super b> continuation) {
            super(continuation);
            this.this$0 = sharedFlowImpl;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SharedFlowImpl.d(this.this$0, null, this);
        }
    }

    public SharedFlowImpl(int i, int i2, @NotNull BufferOverflow bufferOverflow) {
        this.l = i;
        this.m = i2;
        this.n = bufferOverflow;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(9:1|(2:3|(7:5|6|(3:(6:(1:(1:11)(2:42|43))(1:44)|12|13|14|15|(3:16|(4:26|(1:28)(1:34)|29|(2:31|32)(1:33))(3:18|19|(2:21|22)(1:24))|25))(4:45|46|47|48)|38|39)(5:54|55|56|(2:58|(1:60))|62)|49|50|15|(3:16|(0)(0)|25)))|65|6|(0)(0)|49|50|15|(3:16|(0)(0)|25)) */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00d3, code lost:
        r10 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00d4, code lost:
        r5 = r8;
        r8 = r10;
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00c2 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00ab A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object d(kotlinx.coroutines.flow.SharedFlowImpl r8, kotlinx.coroutines.flow.FlowCollector r9, kotlin.coroutines.Continuation r10) {
        /*
            Method dump skipped, instructions count: 223
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.SharedFlowImpl.d(kotlinx.coroutines.flow.SharedFlowImpl, kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object g(SharedFlowImpl sharedFlowImpl, Object obj, Continuation continuation) {
        Object h;
        return (!sharedFlowImpl.tryEmit(obj) && (h = sharedFlowImpl.h(obj, continuation)) == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()) ? h : Unit.INSTANCE;
    }

    public static /* synthetic */ void getLastReplayedLocked$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long l() {
        return Math.min(this.q, this.p);
    }

    public final Object a(SharedFlowSlot sharedFlowSlot, Continuation<? super Unit> continuation) {
        Unit unit;
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        synchronized (this) {
            if (t(sharedFlowSlot) < 0) {
                sharedFlowSlot.cont = cancellableContinuationImpl;
                sharedFlowSlot.cont = cancellableContinuationImpl;
            } else {
                Result.Companion companion = Result.Companion;
                cancellableContinuationImpl.resumeWith(Result.m123constructorimpl(Unit.INSTANCE));
            }
            unit = Unit.INSTANCE;
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? result : unit;
    }

    public final void b(a aVar) {
        synchronized (this) {
            if (aVar.i < l()) {
                return;
            }
            Object[] objArr = this.o;
            Intrinsics.checkNotNull(objArr);
            if (SharedFlowKt.access$getBufferAt(objArr, aVar.i) != aVar) {
                return;
            }
            SharedFlowKt.access$setBufferAt(objArr, aVar.i, SharedFlowKt.NO_VALUE);
            c();
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void c() {
        if (this.m != 0 || this.s > 1) {
            Object[] objArr = this.o;
            Intrinsics.checkNotNull(objArr);
            while (this.s > 0 && SharedFlowKt.access$getBufferAt(objArr, (l() + p()) - 1) == SharedFlowKt.NO_VALUE) {
                this.s--;
                SharedFlowKt.access$setBufferAt(objArr, l() + p(), null);
            }
        }
    }

    @Override // kotlinx.coroutines.flow.SharedFlow, kotlinx.coroutines.flow.Flow
    @Nullable
    public Object collect(@NotNull FlowCollector<? super T> flowCollector, @NotNull Continuation<?> continuation) {
        return d(this, flowCollector, continuation);
    }

    public final void e(long j) {
        AbstractSharedFlowSlot[] access$getSlots;
        if (AbstractSharedFlow.access$getNCollectors(this) != 0 && (access$getSlots = AbstractSharedFlow.access$getSlots(this)) != null) {
            int i = 0;
            int length = access$getSlots.length;
            while (i < length) {
                AbstractSharedFlowSlot abstractSharedFlowSlot = access$getSlots[i];
                i++;
                if (abstractSharedFlowSlot != null) {
                    SharedFlowSlot sharedFlowSlot = (SharedFlowSlot) abstractSharedFlowSlot;
                    long j2 = sharedFlowSlot.index;
                    if (j2 >= 0 && j2 < j) {
                        sharedFlowSlot.index = j;
                    }
                }
            }
        }
        this.q = j;
    }

    @Override // kotlinx.coroutines.flow.MutableSharedFlow, kotlinx.coroutines.flow.FlowCollector
    @Nullable
    public Object emit(T t, @NotNull Continuation<? super Unit> continuation) {
        return g(this, t, continuation);
    }

    public final void f() {
        Object[] objArr = this.o;
        Intrinsics.checkNotNull(objArr);
        SharedFlowKt.access$setBufferAt(objArr, l(), null);
        this.r--;
        long l = l() + 1;
        if (this.p < l) {
            this.p = l;
        }
        if (this.q < l) {
            e(l);
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(l() == l)) {
                throw new AssertionError();
            }
        }
    }

    @Override // kotlinx.coroutines.flow.internal.FusibleFlow
    @NotNull
    public Flow<T> fuse(@NotNull CoroutineContext coroutineContext, int i, @NotNull BufferOverflow bufferOverflow) {
        return SharedFlowKt.fuseSharedFlow(this, coroutineContext, i, bufferOverflow);
    }

    public final T getLastReplayedLocked() {
        Object[] objArr = this.o;
        Intrinsics.checkNotNull(objArr);
        return (T) SharedFlowKt.access$getBufferAt(objArr, (this.p + o()) - 1);
    }

    @Override // kotlinx.coroutines.flow.SharedFlow
    @NotNull
    public List<T> getReplayCache() {
        synchronized (this) {
            int o = o();
            if (o == 0) {
                return CollectionsKt__CollectionsKt.emptyList();
            }
            ArrayList arrayList = new ArrayList(o);
            Object[] objArr = this.o;
            Intrinsics.checkNotNull(objArr);
            int i = 0;
            while (i < o) {
                int i2 = i + 1;
                arrayList.add(SharedFlowKt.access$getBufferAt(objArr, this.p + i));
                i = i2;
            }
            return arrayList;
        }
    }

    public final Object h(T t, Continuation<? super Unit> continuation) {
        Continuation<Unit>[] continuationArr;
        a aVar;
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        Continuation<Unit>[] continuationArr2 = AbstractSharedFlowKt.EMPTY_RESUMES;
        synchronized (this) {
            if (r(t)) {
                Result.Companion companion = Result.Companion;
                cancellableContinuationImpl.resumeWith(Result.m123constructorimpl(Unit.INSTANCE));
                continuationArr = j(continuationArr2);
                aVar = null;
            } else {
                a aVar2 = new a(this, p() + l(), t, cancellableContinuationImpl);
                i(aVar2);
                this.s++;
                if (this.m == 0) {
                    continuationArr2 = j(continuationArr2);
                }
                continuationArr = continuationArr2;
                aVar = aVar2;
            }
        }
        if (aVar != null) {
            CancellableContinuationKt.disposeOnCancellation(cancellableContinuationImpl, aVar);
        }
        int i = 0;
        int length = continuationArr.length;
        while (i < length) {
            Continuation<Unit> continuation2 = continuationArr[i];
            i++;
            if (continuation2 != null) {
                Result.Companion companion2 = Result.Companion;
                continuation2.resumeWith(Result.m123constructorimpl(Unit.INSTANCE));
            }
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? result : Unit.INSTANCE;
    }

    public final void i(Object obj) {
        int p = p();
        Object[] objArr = this.o;
        if (objArr == null) {
            objArr = q(null, 0, 2);
        } else if (p >= objArr.length) {
            objArr = q(objArr, p, objArr.length * 2);
        }
        SharedFlowKt.access$setBufferAt(objArr, l() + p, obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v6, types: [java.lang.Object[], java.lang.Object] */
    public final Continuation<Unit>[] j(Continuation<Unit>[] continuationArr) {
        AbstractSharedFlowSlot[] access$getSlots;
        SharedFlowSlot sharedFlowSlot;
        Continuation<? super Unit> continuation;
        int length = continuationArr.length;
        if (AbstractSharedFlow.access$getNCollectors(this) != 0 && (access$getSlots = AbstractSharedFlow.access$getSlots(this)) != null) {
            int i = 0;
            int length2 = access$getSlots.length;
            while (i < length2) {
                AbstractSharedFlowSlot abstractSharedFlowSlot = access$getSlots[i];
                i++;
                if (abstractSharedFlowSlot != null && (continuation = (sharedFlowSlot = (SharedFlowSlot) abstractSharedFlowSlot).cont) != null && t(sharedFlowSlot) >= 0) {
                    int length3 = continuationArr.length;
                    continuationArr = continuationArr;
                    if (length >= length3) {
                        ?? copyOf = Arrays.copyOf(continuationArr, Math.max(2, continuationArr.length * 2));
                        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
                        continuationArr = copyOf;
                    }
                    continuationArr[length] = continuation;
                    sharedFlowSlot.cont = null;
                    length++;
                }
            }
        }
        return continuationArr;
    }

    public final long k() {
        return l() + this.r;
    }

    public final Object m(long j) {
        Object[] objArr = this.o;
        Intrinsics.checkNotNull(objArr);
        Object access$getBufferAt = SharedFlowKt.access$getBufferAt(objArr, j);
        return access$getBufferAt instanceof a ? ((a) access$getBufferAt).j : access$getBufferAt;
    }

    public final long n() {
        return l() + this.r + this.s;
    }

    public final int o() {
        return (int) ((l() + this.r) - this.p);
    }

    public final int p() {
        return this.r + this.s;
    }

    public final Object[] q(Object[] objArr, int i, int i2) {
        if (i2 > 0) {
            Object[] objArr2 = new Object[i2];
            this.o = objArr2;
            if (objArr == null) {
                return objArr2;
            }
            long l = l();
            for (int i3 = 0; i3 < i; i3++) {
                long j = i3 + l;
                SharedFlowKt.access$setBufferAt(objArr2, j, SharedFlowKt.access$getBufferAt(objArr, j));
            }
            return objArr2;
        }
        throw new IllegalStateException("Buffer size overflow".toString());
    }

    public final boolean r(T t) {
        if (getNCollectors() == 0) {
            return s(t);
        }
        if (this.r >= this.m && this.q <= this.p) {
            int i = WhenMappings.$EnumSwitchMapping$0[this.n.ordinal()];
            if (i == 1) {
                return false;
            }
            if (i == 2) {
                return true;
            }
        }
        i(t);
        int i2 = this.r + 1;
        this.r = i2;
        if (i2 > this.m) {
            f();
        }
        if (o() > this.l) {
            v(this.p + 1, this.q, k(), n());
        }
        return true;
    }

    @Override // kotlinx.coroutines.flow.MutableSharedFlow
    public void resetReplayCache() {
        synchronized (this) {
            v(k(), this.q, k(), n());
            Unit unit = Unit.INSTANCE;
        }
    }

    public final boolean s(T t) {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(getNCollectors() == 0)) {
                throw new AssertionError();
            }
        }
        if (this.l == 0) {
            return true;
        }
        i(t);
        int i = this.r + 1;
        this.r = i;
        if (i > this.l) {
            f();
        }
        this.q = l() + this.r;
        return true;
    }

    public final long t(SharedFlowSlot sharedFlowSlot) {
        long j = sharedFlowSlot.index;
        if (j < k()) {
            return j;
        }
        if (this.m <= 0 && j <= l() && this.s != 0) {
            return j;
        }
        return -1L;
    }

    @Override // kotlinx.coroutines.flow.MutableSharedFlow
    public boolean tryEmit(T t) {
        int i;
        boolean z;
        Continuation<Unit>[] continuationArr = AbstractSharedFlowKt.EMPTY_RESUMES;
        synchronized (this) {
            i = 0;
            if (r(t)) {
                continuationArr = j(continuationArr);
                z = true;
            } else {
                z = false;
            }
        }
        int length = continuationArr.length;
        while (i < length) {
            Continuation<Unit> continuation = continuationArr[i];
            i++;
            if (continuation != null) {
                Result.Companion companion = Result.Companion;
                continuation.resumeWith(Result.m123constructorimpl(Unit.INSTANCE));
            }
        }
        return z;
    }

    public final Object u(SharedFlowSlot sharedFlowSlot) {
        Object obj;
        Continuation<Unit>[] continuationArr = AbstractSharedFlowKt.EMPTY_RESUMES;
        synchronized (this) {
            long t = t(sharedFlowSlot);
            if (t < 0) {
                obj = SharedFlowKt.NO_VALUE;
            } else {
                long j = sharedFlowSlot.index;
                Object m = m(t);
                sharedFlowSlot.index = t + 1;
                continuationArr = updateCollectorIndexLocked$kotlinx_coroutines_core(j);
                obj = m;
            }
        }
        int i = 0;
        int length = continuationArr.length;
        while (i < length) {
            Continuation<Unit> continuation = continuationArr[i];
            i++;
            if (continuation != null) {
                Result.Companion companion = Result.Companion;
                continuation.resumeWith(Result.m123constructorimpl(Unit.INSTANCE));
            }
        }
        return obj;
    }

    @NotNull
    public final Continuation<Unit>[] updateCollectorIndexLocked$kotlinx_coroutines_core(long j) {
        int i;
        long j2;
        AbstractSharedFlowSlot[] access$getSlots;
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(j >= this.q)) {
                throw new AssertionError();
            }
        }
        if (j > this.q) {
            return AbstractSharedFlowKt.EMPTY_RESUMES;
        }
        long l = l();
        long j3 = this.r + l;
        long j4 = 1;
        if (this.m == 0 && this.s > 0) {
            j3++;
        }
        if (AbstractSharedFlow.access$getNCollectors(this) != 0 && (access$getSlots = AbstractSharedFlow.access$getSlots(this)) != null) {
            int length = access$getSlots.length;
            int i2 = 0;
            while (i2 < length) {
                AbstractSharedFlowSlot abstractSharedFlowSlot = access$getSlots[i2];
                i2++;
                if (abstractSharedFlowSlot != null) {
                    long j5 = ((SharedFlowSlot) abstractSharedFlowSlot).index;
                    if (j5 >= 0 && j5 < j3) {
                        j3 = j5;
                    }
                }
            }
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(j3 >= this.q)) {
                throw new AssertionError();
            }
        }
        if (j3 <= this.q) {
            return AbstractSharedFlowKt.EMPTY_RESUMES;
        }
        long k = k();
        if (getNCollectors() > 0) {
            i = Math.min(this.s, this.m - ((int) (k - j3)));
        } else {
            i = this.s;
        }
        Continuation<Unit>[] continuationArr = AbstractSharedFlowKt.EMPTY_RESUMES;
        long j6 = this.s + k;
        if (i > 0) {
            continuationArr = new Continuation[i];
            Object[] objArr = this.o;
            Intrinsics.checkNotNull(objArr);
            long j7 = k;
            int i3 = 0;
            while (true) {
                if (k >= j6) {
                    j2 = j3;
                    break;
                }
                long j8 = k + j4;
                Object access$getBufferAt = SharedFlowKt.access$getBufferAt(objArr, k);
                Symbol symbol = SharedFlowKt.NO_VALUE;
                if (access$getBufferAt != symbol) {
                    j2 = j3;
                    Objects.requireNonNull(access$getBufferAt, "null cannot be cast to non-null type kotlinx.coroutines.flow.SharedFlowImpl.Emitter");
                    a aVar = (a) access$getBufferAt;
                    int i4 = i3 + 1;
                    continuationArr[i3] = aVar.k;
                    SharedFlowKt.access$setBufferAt(objArr, k, symbol);
                    SharedFlowKt.access$setBufferAt(objArr, j7, aVar.j);
                    j7++;
                    if (i4 >= i) {
                        break;
                    }
                    i3 = i4;
                    k = j8;
                    j3 = j2;
                } else {
                    k = j8;
                }
                j4 = 1;
            }
            k = j7;
        } else {
            j2 = j3;
        }
        int i5 = (int) (k - l);
        long j9 = getNCollectors() == 0 ? k : j2;
        long max = Math.max(this.p, k - Math.min(this.l, i5));
        if (this.m == 0 && max < j6) {
            Object[] objArr2 = this.o;
            Intrinsics.checkNotNull(objArr2);
            if (Intrinsics.areEqual(SharedFlowKt.access$getBufferAt(objArr2, max), SharedFlowKt.NO_VALUE)) {
                k++;
                max++;
            }
        }
        v(max, j9, k, j6);
        c();
        return true ^ (continuationArr.length == 0) ? j(continuationArr) : continuationArr;
    }

    public final long updateNewCollectorIndexLocked$kotlinx_coroutines_core() {
        long j = this.p;
        if (j < this.q) {
            this.q = j;
        }
        return j;
    }

    public final void v(long j, long j2, long j3, long j4) {
        long min = Math.min(j2, j);
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(min >= l())) {
                throw new AssertionError();
            }
        }
        for (long l = l(); l < min; l = 1 + l) {
            Object[] objArr = this.o;
            Intrinsics.checkNotNull(objArr);
            SharedFlowKt.access$setBufferAt(objArr, l, null);
        }
        this.p = j;
        this.q = j2;
        this.r = (int) (j3 - min);
        this.s = (int) (j4 - j3);
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(this.r >= 0)) {
                throw new AssertionError();
            }
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(this.s >= 0)) {
                throw new AssertionError();
            }
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(this.p <= l() + ((long) this.r))) {
                throw new AssertionError();
            }
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlow
    @NotNull
    public SharedFlowSlot createSlot() {
        return new SharedFlowSlot();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlow
    @NotNull
    public SharedFlowSlot[] createSlotArray(int i) {
        return new SharedFlowSlot[i];
    }
}

package kotlinx.coroutines.flow;

import com.goodix.ble.libcomx.task.ITask;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final /* synthetic */ class FlowKt__ShareKt {

    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharingDeferred$1", f = "Share.kt", i = {}, l = {ITask.EVT_START}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes12.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ CompletableDeferred<StateFlow<T>> $result;
        public final /* synthetic */ Flow<T> $upstream;
        private /* synthetic */ Object L$0;
        public int label;

        /* renamed from: kotlinx.coroutines.flow.FlowKt__ShareKt$a$a */
        /* loaded from: classes12.dex */
        public static final class C0877a<T> implements FlowCollector, SuspendFunction {
            public final /* synthetic */ Ref.ObjectRef<MutableStateFlow<T>> h;
            public final /* synthetic */ CoroutineScope i;
            public final /* synthetic */ CompletableDeferred<StateFlow<T>> j;

            public C0877a(Ref.ObjectRef<MutableStateFlow<T>> objectRef, CoroutineScope coroutineScope, CompletableDeferred<StateFlow<T>> completableDeferred) {
                this.h = objectRef;
                this.i = coroutineScope;
                this.j = completableDeferred;
            }

            /* JADX WARN: Type inference failed for: r4v2, types: [T, kotlinx.coroutines.flow.MutableStateFlow, kotlinx.coroutines.flow.StateFlow] */
            @Override // kotlinx.coroutines.flow.FlowCollector
            @Nullable
            public final Object emit(T t, @NotNull Continuation<? super Unit> continuation) {
                Unit unit;
                MutableStateFlow<T> mutableStateFlow = this.h.element;
                if (mutableStateFlow == null) {
                    unit = null;
                } else {
                    mutableStateFlow.setValue(t);
                    unit = Unit.INSTANCE;
                }
                if (unit == null) {
                    CoroutineScope coroutineScope = this.i;
                    Ref.ObjectRef<MutableStateFlow<T>> objectRef = this.h;
                    CompletableDeferred<StateFlow<T>> completableDeferred = this.j;
                    ?? r4 = (T) StateFlowKt.MutableStateFlow(t);
                    completableDeferred.complete(new m(r4, JobKt.getJob(coroutineScope.getCoroutineContext())));
                    objectRef.element = r4;
                }
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public a(Flow<? extends T> flow, CompletableDeferred<StateFlow<T>> completableDeferred, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$upstream = flow;
            this.$result = completableDeferred;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            a aVar = new a(this.$upstream, this.$result, continuation);
            aVar.L$0 = obj;
            return aVar;
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                    Ref.ObjectRef objectRef = new Ref.ObjectRef();
                    Flow<T> flow = this.$upstream;
                    C0877a c0877a = new C0877a(objectRef, coroutineScope, this.$result);
                    this.label = 1;
                    if (flow.collect(c0877a, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            } catch (Throwable th) {
                this.$result.completeExceptionally(th);
                throw th;
            }
        }
    }

    @NotNull
    public static final <T> SharedFlow<T> a(@NotNull MutableSharedFlow<T> mutableSharedFlow) {
        return new l(mutableSharedFlow, null);
    }

    @NotNull
    public static final <T> StateFlow<T> b(@NotNull MutableStateFlow<T> mutableStateFlow) {
        return new m(mutableStateFlow, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:59:0x0047, code lost:
        if (r9 == 0) goto L20;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final <T> kotlinx.coroutines.flow.o<T> c(kotlinx.coroutines.flow.Flow<? extends T> r8, int r9) {
        /*
            boolean r0 = kotlinx.coroutines.DebugKt.getASSERTIONS_ENABLED()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L16
            if (r9 < 0) goto Lc
            r0 = r1
            goto Ld
        Lc:
            r0 = r2
        Ld:
            if (r0 == 0) goto L10
            goto L16
        L10:
            java.lang.AssertionError r8 = new java.lang.AssertionError
            r8.<init>()
            throw r8
        L16:
            kotlinx.coroutines.channels.Channel$Factory r0 = kotlinx.coroutines.channels.Channel.Factory
            int r0 = r0.getCHANNEL_DEFAULT_CAPACITY$kotlinx_coroutines_core()
            int r0 = kotlin.ranges.h.coerceAtLeast(r9, r0)
            int r0 = r0 - r9
            boolean r3 = r8 instanceof kotlinx.coroutines.flow.internal.ChannelFlow
            if (r3 == 0) goto L53
            r3 = r8
            kotlinx.coroutines.flow.internal.ChannelFlow r3 = (kotlinx.coroutines.flow.internal.ChannelFlow) r3
            kotlinx.coroutines.flow.Flow r4 = r3.dropChannelOperators()
            if (r4 == 0) goto L53
            kotlinx.coroutines.flow.o r8 = new kotlinx.coroutines.flow.o
            int r5 = r3.capacity
            r6 = -3
            if (r5 == r6) goto L3c
            r6 = -2
            if (r5 == r6) goto L3c
            if (r5 == 0) goto L3c
            r1 = r5
            goto L4b
        L3c:
            kotlinx.coroutines.channels.BufferOverflow r6 = r3.onBufferOverflow
            kotlinx.coroutines.channels.BufferOverflow r7 = kotlinx.coroutines.channels.BufferOverflow.SUSPEND
            if (r6 != r7) goto L47
            if (r5 != 0) goto L45
            goto L4a
        L45:
            r1 = r0
            goto L4b
        L47:
            if (r9 != 0) goto L4a
            goto L4b
        L4a:
            r1 = r2
        L4b:
            kotlinx.coroutines.channels.BufferOverflow r9 = r3.onBufferOverflow
            kotlin.coroutines.CoroutineContext r0 = r3.context
            r8.<init>(r4, r1, r9, r0)
            return r8
        L53:
            kotlinx.coroutines.flow.o r9 = new kotlinx.coroutines.flow.o
            kotlinx.coroutines.channels.BufferOverflow r1 = kotlinx.coroutines.channels.BufferOverflow.SUSPEND
            kotlin.coroutines.EmptyCoroutineContext r2 = kotlin.coroutines.EmptyCoroutineContext.INSTANCE
            r9.<init>(r8, r0, r1, r2)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ShareKt.c(kotlinx.coroutines.flow.Flow, int):kotlinx.coroutines.flow.o");
    }

    public static final <T> Job d(CoroutineScope coroutineScope, CoroutineContext coroutineContext, Flow<? extends T> flow, MutableSharedFlow<T> mutableSharedFlow, SharingStarted sharingStarted, T t) {
        return BuildersKt.launch(coroutineScope, coroutineContext, Intrinsics.areEqual(sharingStarted, SharingStarted.Companion.getEagerly()) ? CoroutineStart.DEFAULT : CoroutineStart.UNDISPATCHED, new FlowKt__ShareKt$launchSharing$1(sharingStarted, flow, mutableSharedFlow, t, null));
    }

    public static final <T> void e(CoroutineScope coroutineScope, CoroutineContext coroutineContext, Flow<? extends T> flow, CompletableDeferred<StateFlow<T>> completableDeferred) {
        kotlinx.coroutines.e.e(coroutineScope, coroutineContext, null, new a(flow, completableDeferred, null), 2, null);
    }

    @NotNull
    public static final <T> SharedFlow<T> f(@NotNull SharedFlow<? extends T> sharedFlow, @NotNull Function2<? super FlowCollector<? super T>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        return new u(sharedFlow, function2);
    }

    @NotNull
    public static final <T> SharedFlow<T> g(@NotNull Flow<? extends T> flow, @NotNull CoroutineScope coroutineScope, @NotNull SharingStarted sharingStarted, int i) {
        o c = c(flow, i);
        MutableSharedFlow MutableSharedFlow = SharedFlowKt.MutableSharedFlow(i, c.b, c.c);
        return new l(MutableSharedFlow, d(coroutineScope, c.d, c.f14163a, MutableSharedFlow, sharingStarted, SharedFlowKt.NO_VALUE));
    }

    public static /* synthetic */ SharedFlow h(Flow flow, CoroutineScope coroutineScope, SharingStarted sharingStarted, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return FlowKt.shareIn(flow, coroutineScope, sharingStarted, i);
    }

    @Nullable
    public static final <T> Object i(@NotNull Flow<? extends T> flow, @NotNull CoroutineScope coroutineScope, @NotNull Continuation<? super StateFlow<? extends T>> continuation) {
        o c = c(flow, 1);
        CompletableDeferred CompletableDeferred$default = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        e(coroutineScope, c.d, c.f14163a, CompletableDeferred$default);
        return CompletableDeferred$default.await(continuation);
    }

    @NotNull
    public static final <T> StateFlow<T> j(@NotNull Flow<? extends T> flow, @NotNull CoroutineScope coroutineScope, @NotNull SharingStarted sharingStarted, T t) {
        o c = c(flow, 1);
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(t);
        return new m(MutableStateFlow, d(coroutineScope, c.d, c.f14163a, MutableStateFlow, sharingStarted, t));
    }
}

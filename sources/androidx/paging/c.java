package androidx.paging;

import androidx.paging.ActiveFlowTracker;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class c<T> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final CoroutineScope f1565a;
    @NotNull
    public final PagingData<T> b;
    @Nullable
    public final ActiveFlowTracker c;
    @NotNull
    public final CachedPageEventFlow<T> d;

    @DebugMetadata(c = "androidx.paging.MulticastedPagingData$accumulated$1", f = "CachedPagingData.kt", i = {}, l = {44}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes.dex */
    public static final class a extends SuspendLambda implements Function2<FlowCollector<? super PageEvent<T>>, Continuation<? super Unit>, Object> {
        public int label;
        public final /* synthetic */ c<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(c<T> cVar, Continuation<? super a> continuation) {
            super(2, continuation);
            this.this$0 = cVar;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Continuation<? super Unit> continuation) {
            return invoke((FlowCollector) ((FlowCollector) obj), continuation);
        }

        @Nullable
        public final Object invoke(@NotNull FlowCollector<? super PageEvent<T>> flowCollector, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ActiveFlowTracker c = this.this$0.c();
                if (c != null) {
                    ActiveFlowTracker.FlowType flowType = ActiveFlowTracker.FlowType.PAGE_EVENT_FLOW;
                    this.label = 1;
                    if (c.onStart(flowType, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(c = "androidx.paging.MulticastedPagingData$accumulated$2", f = "CachedPagingData.kt", i = {}, l = {46}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes.dex */
    public static final class b extends SuspendLambda implements Function3<FlowCollector<? super PageEvent<T>>, Throwable, Continuation<? super Unit>, Object> {
        public int label;
        public final /* synthetic */ c<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(c<T> cVar, Continuation<? super b> continuation) {
            super(3, continuation);
            this.this$0 = cVar;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Throwable th, Continuation<? super Unit> continuation) {
            return invoke((FlowCollector) ((FlowCollector) obj), th, continuation);
        }

        @Nullable
        public final Object invoke(@NotNull FlowCollector<? super PageEvent<T>> flowCollector, @Nullable Throwable th, @Nullable Continuation<? super Unit> continuation) {
            return new b(this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ActiveFlowTracker c = this.this$0.c();
                if (c != null) {
                    ActiveFlowTracker.FlowType flowType = ActiveFlowTracker.FlowType.PAGE_EVENT_FLOW;
                    this.label = 1;
                    if (c.onComplete(flowType, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public c(@NotNull CoroutineScope scope, @NotNull PagingData<T> parent, @Nullable ActiveFlowTracker activeFlowTracker) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        Intrinsics.checkNotNullParameter(parent, "parent");
        this.f1565a = scope;
        this.b = parent;
        this.c = activeFlowTracker;
        this.d = new CachedPageEventFlow<>(FlowKt.onCompletion(FlowKt.onStart(parent.getFlow$paging_common(), new a(this, null)), new b(this, null)), scope);
    }

    @NotNull
    public final PagingData<T> a() {
        return new PagingData<>(this.d.getDownstreamFlow(), this.b.getReceiver$paging_common());
    }

    @Nullable
    public final Object b(@NotNull Continuation<? super Unit> continuation) {
        this.d.close();
        return Unit.INSTANCE;
    }

    @Nullable
    public final ActiveFlowTracker c() {
        return this.c;
    }

    public /* synthetic */ c(CoroutineScope coroutineScope, PagingData pagingData, ActiveFlowTracker activeFlowTracker, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(coroutineScope, pagingData, (i & 4) != 0 ? null : activeFlowTracker);
    }
}

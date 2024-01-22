package androidx.paging;

import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.e;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0001B#\u0012\u0012\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00060\u0005\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000f\u0010\u0010J\u0006\u0010\u0004\u001a\u00020\u0003R%\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00060\u00058\u0006@\u0006¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n¨\u0006\u0011"}, d2 = {"Landroidx/paging/CachedPageEventFlow;", "", ExifInterface.GPS_DIRECTION_TRUE, "", Constants.KEY_HIDE_CLOSE, "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/PageEvent;", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "Lkotlinx/coroutines/flow/Flow;", "getDownstreamFlow", "()Lkotlinx/coroutines/flow/Flow;", "downstreamFlow", "src", "Lkotlinx/coroutines/CoroutineScope;", "scope", "<init>", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/CoroutineScope;)V", "paging-common"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class CachedPageEventFlow<T> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final androidx.paging.b<T> f1476a;
    @NotNull
    public final MutableSharedFlow<IndexedValue<PageEvent<T>>> b;
    @NotNull
    public final SharedFlow<IndexedValue<PageEvent<T>>> c;
    @NotNull
    public final Job d;
    @NotNull
    public final Flow<PageEvent<T>> e;

    /* loaded from: classes.dex */
    public static final class a extends Lambda implements Function1<Throwable, Unit> {
        public final /* synthetic */ CachedPageEventFlow<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(CachedPageEventFlow<T> cachedPageEventFlow) {
            super(1);
            this.this$0 = cachedPageEventFlow;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable Throwable th) {
            this.this$0.b.tryEmit(null);
        }
    }

    @DebugMetadata(c = "androidx.paging.CachedPageEventFlow$sharedForDownstream$1", f = "CachedPageEventFlow.kt", i = {0, 1}, l = {63, 68}, m = "invokeSuspend", n = {"$this$onSubscription", "$this$onSubscription"}, s = {"L$0", "L$0"})
    /* loaded from: classes.dex */
    public static final class b extends SuspendLambda implements Function2<FlowCollector<? super IndexedValue<? extends PageEvent<T>>>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        public Object L$1;
        public int label;
        public final /* synthetic */ CachedPageEventFlow<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(CachedPageEventFlow<T> cachedPageEventFlow, Continuation<? super b> continuation) {
            super(2, continuation);
            this.this$0 = cachedPageEventFlow;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            b bVar = new b(this.this$0, continuation);
            bVar.L$0 = obj;
            return bVar;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Continuation<? super Unit> continuation) {
            return invoke((FlowCollector) ((FlowCollector) obj), continuation);
        }

        @Nullable
        public final Object invoke(@NotNull FlowCollector<? super IndexedValue<? extends PageEvent<T>>> flowCollector, @Nullable Continuation<? super Unit> continuation) {
            return ((b) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x005b  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r6) {
            /*
                r5 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
                int r1 = r5.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L2a
                if (r1 == r3) goto L22
                if (r1 != r2) goto L1a
                java.lang.Object r1 = r5.L$1
                java.util.Iterator r1 = (java.util.Iterator) r1
                java.lang.Object r3 = r5.L$0
                kotlinx.coroutines.flow.FlowCollector r3 = (kotlinx.coroutines.flow.FlowCollector) r3
                kotlin.ResultKt.throwOnFailure(r6)
                goto L54
            L1a:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r0)
                throw r6
            L22:
                java.lang.Object r1 = r5.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r6)
                goto L43
            L2a:
                kotlin.ResultKt.throwOnFailure(r6)
                java.lang.Object r6 = r5.L$0
                r1 = r6
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                androidx.paging.CachedPageEventFlow<T> r6 = r5.this$0
                androidx.paging.b r6 = androidx.paging.CachedPageEventFlow.access$getPageController$p(r6)
                r5.L$0 = r1
                r5.label = r3
                java.lang.Object r6 = r6.a(r5)
                if (r6 != r0) goto L43
                return r0
            L43:
                java.util.List r6 = (java.util.List) r6
                androidx.paging.CachedPageEventFlow<T> r3 = r5.this$0
                kotlinx.coroutines.Job r3 = androidx.paging.CachedPageEventFlow.access$getJob$p(r3)
                r3.start()
                java.util.Iterator r6 = r6.iterator()
                r3 = r1
                r1 = r6
            L54:
                r6 = r5
            L55:
                boolean r4 = r1.hasNext()
                if (r4 == 0) goto L6e
                java.lang.Object r4 = r1.next()
                kotlin.collections.IndexedValue r4 = (kotlin.collections.IndexedValue) r4
                r6.L$0 = r3
                r6.L$1 = r1
                r6.label = r2
                java.lang.Object r4 = r3.emit(r4, r6)
                if (r4 != r0) goto L55
                return r0
            L6e:
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.CachedPageEventFlow.b.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public CachedPageEventFlow(@NotNull Flow<? extends PageEvent<T>> src, @NotNull CoroutineScope scope) {
        Job e;
        Intrinsics.checkNotNullParameter(src, "src");
        Intrinsics.checkNotNullParameter(scope, "scope");
        this.f1476a = new androidx.paging.b<>();
        MutableSharedFlow<IndexedValue<PageEvent<T>>> MutableSharedFlow = SharedFlowKt.MutableSharedFlow(1, Integer.MAX_VALUE, BufferOverflow.SUSPEND);
        this.b = MutableSharedFlow;
        this.c = FlowKt.onSubscription(MutableSharedFlow, new b(this, null));
        e = e.e(scope, null, CoroutineStart.LAZY, new CachedPageEventFlow$job$1(src, this, null), 1, null);
        e.invokeOnCompletion(new a(this));
        Unit unit = Unit.INSTANCE;
        this.d = e;
        this.e = FlowKt.flow(new CachedPageEventFlow$downstreamFlow$1(this, null));
    }

    public final void close() {
        Job.DefaultImpls.cancel$default(this.d, (CancellationException) null, 1, (Object) null);
    }

    @NotNull
    public final Flow<PageEvent<T>> getDownstreamFlow() {
        return this.e;
    }
}

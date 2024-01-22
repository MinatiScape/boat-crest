package androidx.paging;

import androidx.paging.PagingDataDiffer$collectFrom$2;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "androidx.paging.PagingDataDiffer$collectFrom$2", f = "PagingDataDiffer.kt", i = {}, l = {467}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
public final class PagingDataDiffer$collectFrom$2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    public final /* synthetic */ PagingData<T> $pagingData;
    public int label;
    public final /* synthetic */ PagingDataDiffer<T> this$0;

    @DebugMetadata(c = "androidx.paging.PagingDataDiffer$collectFrom$2$1$1", f = "PagingDataDiffer.kt", i = {0, 0}, l = {151, 193}, m = "invokeSuspend", n = {"newPresenter", "onListPresentableCalled"}, s = {"L$0", "L$1"})
    /* loaded from: classes.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ PageEvent<T> $event;
        public Object L$0;
        public Object L$1;
        public int label;
        public final /* synthetic */ PagingDataDiffer<T> this$0;

        /* renamed from: androidx.paging.PagingDataDiffer$collectFrom$2$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0162a extends Lambda implements Function0<Unit> {
            public final /* synthetic */ PagePresenter<T> $newPresenter;
            public final /* synthetic */ Ref.BooleanRef $onListPresentableCalled;
            public final /* synthetic */ PagingDataDiffer<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0162a(PagingDataDiffer<T> pagingDataDiffer, PagePresenter<T> pagePresenter, Ref.BooleanRef booleanRef) {
                super(0);
                this.this$0 = pagingDataDiffer;
                this.$newPresenter = pagePresenter;
                this.$onListPresentableCalled = booleanRef;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2() {
                this.this$0.c = this.$newPresenter;
                this.$onListPresentableCalled.element = true;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(PageEvent<T> pageEvent, PagingDataDiffer<T> pagingDataDiffer, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$event = pageEvent;
            this.this$0 = pagingDataDiffer;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$event, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x0078  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x00c2  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x00f6  */
        /* JADX WARN: Removed duplicated region for block: B:42:0x0101  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r12) {
            /*
                Method dump skipped, instructions count: 516
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PagingDataDiffer$collectFrom$2.a.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PagingDataDiffer$collectFrom$2(PagingDataDiffer<T> pagingDataDiffer, PagingData<T> pagingData, Continuation<? super PagingDataDiffer$collectFrom$2> continuation) {
        super(1, continuation);
        this.this$0 = pagingDataDiffer;
        this.$pagingData = pagingData;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@NotNull Continuation<?> continuation) {
        return new PagingDataDiffer$collectFrom$2(this.this$0, this.$pagingData, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    @Nullable
    public final Object invoke(@Nullable Continuation<? super Unit> continuation) {
        return ((PagingDataDiffer$collectFrom$2) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.d = this.$pagingData.getReceiver$paging_common();
            Flow flow$paging_common = this.$pagingData.getFlow$paging_common();
            final PagingDataDiffer<T> pagingDataDiffer = this.this$0;
            Object obj2 = new FlowCollector<PageEvent<T>>() { // from class: androidx.paging.PagingDataDiffer$collectFrom$2$invokeSuspend$$inlined$collect$1
                @Override // kotlinx.coroutines.flow.FlowCollector
                @Nullable
                public Object emit(PageEvent<T> pageEvent, @NotNull Continuation<? super Unit> continuation) {
                    CoroutineDispatcher coroutineDispatcher;
                    coroutineDispatcher = PagingDataDiffer.this.b;
                    Object withContext = BuildersKt.withContext(coroutineDispatcher, new PagingDataDiffer$collectFrom$2.a(pageEvent, PagingDataDiffer.this, null), continuation);
                    return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (flow$paging_common.collect(obj2, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}

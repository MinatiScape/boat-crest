package androidx.paging;

import androidx.recyclerview.widget.DiffUtil;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001JG\u0010\n\u001a\u0004\u0018\u00010\u00052\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u0006\u001a\u00020\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0096@ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\b\u0010\r\u001a\u00020\fH\u0016\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"androidx/paging/AsyncPagingDataDiffer$differBase$1", "Landroidx/paging/PagingDataDiffer;", "Landroidx/paging/NullPaddedList;", "previousList", "newList", "", "lastAccessedIndex", "Lkotlin/Function0;", "", "onListPresentable", "presentNewList", "(Landroidx/paging/NullPaddedList;Landroidx/paging/NullPaddedList;ILkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "postEvents", "paging-runtime_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class AsyncPagingDataDiffer$differBase$1<T> extends PagingDataDiffer<T> {
    public final /* synthetic */ AsyncPagingDataDiffer<T> m;

    @DebugMetadata(c = "androidx.paging.AsyncPagingDataDiffer$differBase$1", f = "AsyncPagingDataDiffer.kt", i = {0, 0, 0, 0, 0}, l = {98}, m = "presentNewList", n = {"this", "previousList", "newList", "onListPresentable", "lastAccessedIndex"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0"})
    /* loaded from: classes.dex */
    public static final class a extends ContinuationImpl {
        public int I$0;
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public Object L$3;
        public int label;
        public /* synthetic */ Object result;

        public a(Continuation<? super a> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AsyncPagingDataDiffer$differBase$1.this.presentNewList(null, null, 0, null, this);
        }
    }

    @DebugMetadata(c = "androidx.paging.AsyncPagingDataDiffer$differBase$1$presentNewList$diffResult$1", f = "AsyncPagingDataDiffer.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super NullPaddedDiffResult>, Object> {
        public final /* synthetic */ NullPaddedList<T> $newList;
        public final /* synthetic */ NullPaddedList<T> $previousList;
        public int label;
        public final /* synthetic */ AsyncPagingDataDiffer<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(NullPaddedList<T> nullPaddedList, NullPaddedList<T> nullPaddedList2, AsyncPagingDataDiffer<T> asyncPagingDataDiffer, Continuation<? super b> continuation) {
            super(2, continuation);
            this.$previousList = nullPaddedList;
            this.$newList = nullPaddedList2;
            this.this$0 = asyncPagingDataDiffer;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(this.$previousList, this.$newList, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super NullPaddedDiffResult> continuation) {
            return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            DiffUtil.ItemCallback itemCallback;
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                NullPaddedList<T> nullPaddedList = this.$previousList;
                NullPaddedList<T> nullPaddedList2 = this.$newList;
                itemCallback = this.this$0.f1474a;
                return NullPaddedListDiffHelperKt.computeDiff(nullPaddedList, nullPaddedList2, itemCallback);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AsyncPagingDataDiffer$differBase$1(AsyncPagingDataDiffer<T> asyncPagingDataDiffer, DifferCallback differCallback, CoroutineDispatcher coroutineDispatcher) {
        super(differCallback, coroutineDispatcher);
        this.m = asyncPagingDataDiffer;
    }

    @Override // androidx.paging.PagingDataDiffer
    public boolean postEvents() {
        return this.m.getInGetItem$paging_runtime_release();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0046  */
    @Override // androidx.paging.PagingDataDiffer
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object presentNewList(@org.jetbrains.annotations.NotNull androidx.paging.NullPaddedList<T> r7, @org.jetbrains.annotations.NotNull androidx.paging.NullPaddedList<T> r8, int r9, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function0<kotlin.Unit> r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Integer> r11) {
        /*
            r6 = this;
            boolean r0 = r11 instanceof androidx.paging.AsyncPagingDataDiffer$differBase$1.a
            if (r0 == 0) goto L13
            r0 = r11
            androidx.paging.AsyncPagingDataDiffer$differBase$1$a r0 = (androidx.paging.AsyncPagingDataDiffer$differBase$1.a) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.paging.AsyncPagingDataDiffer$differBase$1$a r0 = new androidx.paging.AsyncPagingDataDiffer$differBase$1$a
            r0.<init>(r11)
        L18:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L46
            if (r2 != r3) goto L3e
            int r9 = r0.I$0
            java.lang.Object r7 = r0.L$3
            r10 = r7
            kotlin.jvm.functions.Function0 r10 = (kotlin.jvm.functions.Function0) r10
            java.lang.Object r7 = r0.L$2
            r8 = r7
            androidx.paging.NullPaddedList r8 = (androidx.paging.NullPaddedList) r8
            java.lang.Object r7 = r0.L$1
            androidx.paging.NullPaddedList r7 = (androidx.paging.NullPaddedList) r7
            java.lang.Object r0 = r0.L$0
            androidx.paging.AsyncPagingDataDiffer$differBase$1 r0 = (androidx.paging.AsyncPagingDataDiffer$differBase$1) r0
            kotlin.ResultKt.throwOnFailure(r11)
            goto L99
        L3e:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L46:
            kotlin.ResultKt.throwOnFailure(r11)
            int r11 = r7.getSize()
            r2 = 0
            if (r11 != 0) goto L61
            r10.invoke()
            androidx.paging.AsyncPagingDataDiffer<T> r7 = r6.m
            androidx.paging.DifferCallback r7 = r7.getDifferCallback$paging_runtime_release()
            int r8 = r8.getSize()
            r7.onInserted(r2, r8)
            goto Laf
        L61:
            int r11 = r8.getSize()
            if (r11 != 0) goto L78
            r10.invoke()
            androidx.paging.AsyncPagingDataDiffer<T> r8 = r6.m
            androidx.paging.DifferCallback r8 = r8.getDifferCallback$paging_runtime_release()
            int r7 = r7.getSize()
            r8.onRemoved(r2, r7)
            goto Laf
        L78:
            androidx.paging.AsyncPagingDataDiffer<T> r11 = r6.m
            kotlinx.coroutines.CoroutineDispatcher r11 = androidx.paging.AsyncPagingDataDiffer.access$getWorkerDispatcher$p(r11)
            androidx.paging.AsyncPagingDataDiffer$differBase$1$b r2 = new androidx.paging.AsyncPagingDataDiffer$differBase$1$b
            androidx.paging.AsyncPagingDataDiffer<T> r5 = r6.m
            r2.<init>(r7, r8, r5, r4)
            r0.L$0 = r6
            r0.L$1 = r7
            r0.L$2 = r8
            r0.L$3 = r10
            r0.I$0 = r9
            r0.label = r3
            java.lang.Object r11 = kotlinx.coroutines.BuildersKt.withContext(r11, r2, r0)
            if (r11 != r1) goto L98
            return r1
        L98:
            r0 = r6
        L99:
            androidx.paging.NullPaddedDiffResult r11 = (androidx.paging.NullPaddedDiffResult) r11
            r10.invoke()
            androidx.paging.AsyncPagingDataDiffer<T> r10 = r0.m
            androidx.recyclerview.widget.ListUpdateCallback r10 = androidx.paging.AsyncPagingDataDiffer.access$getUpdateCallback$p(r10)
            androidx.paging.NullPaddedListDiffHelperKt.dispatchDiff(r7, r10, r8, r11)
            int r7 = androidx.paging.NullPaddedListDiffHelperKt.transformAnchorIndex(r7, r11, r8, r9)
            java.lang.Integer r4 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r7)
        Laf:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.AsyncPagingDataDiffer$differBase$1.presentNewList(androidx.paging.NullPaddedList, androidx.paging.NullPaddedList, int, kotlin.jvm.functions.Function0, kotlin.coroutines.Continuation):java.lang.Object");
    }
}

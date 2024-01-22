package androidx.paging;

import androidx.annotation.IntRange;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleKt;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListUpdateCallback;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.e;
import kotlinx.coroutines.flow.Flow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0001B3\b\u0007\u0012\f\u00109\u001a\b\u0012\u0004\u0012\u00028\u000008\u0012\u0006\u0010;\u001a\u00020:\u0012\b\b\u0002\u0010=\u001a\u00020<\u0012\b\b\u0002\u0010>\u001a\u00020<¢\u0006\u0004\b?\u0010@J!\u0010\u0006\u001a\u00020\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007J\u001c\u0010\u0006\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\b2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003J\u0006\u0010\n\u001a\u00020\u0005J\u0006\u0010\u000b\u001a\u00020\u0005J\u0019\u0010\u000e\u001a\u0004\u0018\u00018\u00002\b\b\u0001\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fJ\u0019\u0010\u0010\u001a\u0004\u0018\u00018\u00002\b\b\u0001\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u0010\u0010\u000fJ\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011J\u0014\u0010\u0015\u001a\u00020\u00052\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u0013J\u0014\u0010\u0016\u001a\u00020\u00052\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u0013J\u001a\u0010\u0019\u001a\u00020\u00052\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00050\u0017J\u001a\u0010\u001a\u001a\u00020\u00052\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00050\u0017R\"\u0010\"\u001a\u00020\u001b8\u0000@\u0000X\u0080\u0004¢\u0006\u0012\n\u0004\b\u001c\u0010\u001d\u0012\u0004\b \u0010!\u001a\u0004\b\u001e\u0010\u001fR(\u0010+\u001a\u00020#8\u0000@\u0000X\u0080\u000e¢\u0006\u0018\n\u0004\b$\u0010%\u0012\u0004\b*\u0010!\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u001f\u00101\u001a\b\u0012\u0004\u0012\u00020\u00180,8\u0006@\u0006¢\u0006\f\n\u0004\b-\u0010.\u001a\u0004\b/\u00100R\u001f\u00104\u001a\b\u0012\u0004\u0012\u00020\u00050,8\u0006@\u0006¢\u0006\f\n\u0004\b2\u0010.\u001a\u0004\b3\u00100R\u0013\u00107\u001a\u00020\f8F@\u0006¢\u0006\u0006\u001a\u0004\b5\u00106\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006A"}, d2 = {"Landroidx/paging/AsyncPagingDataDiffer;", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/paging/PagingData;", "pagingData", "", "submitData", "(Landroidx/paging/PagingData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/lifecycle/Lifecycle;", "lifecycle", "retry", "refresh", "", FirebaseAnalytics.Param.INDEX, "getItem", "(I)Ljava/lang/Object;", "peek", "Landroidx/paging/ItemSnapshotList;", "snapshot", "Lkotlin/Function0;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "addOnPagesUpdatedListener", "removeOnPagesUpdatedListener", "Lkotlin/Function1;", "Landroidx/paging/CombinedLoadStates;", "addLoadStateListener", "removeLoadStateListener", "Landroidx/paging/DifferCallback;", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "Landroidx/paging/DifferCallback;", "getDifferCallback$paging_runtime_release", "()Landroidx/paging/DifferCallback;", "getDifferCallback$paging_runtime_release$annotations", "()V", "differCallback", "", "f", "Z", "getInGetItem$paging_runtime_release", "()Z", "setInGetItem$paging_runtime_release", "(Z)V", "getInGetItem$paging_runtime_release$annotations", "inGetItem", "Lkotlinx/coroutines/flow/Flow;", "i", "Lkotlinx/coroutines/flow/Flow;", "getLoadStateFlow", "()Lkotlinx/coroutines/flow/Flow;", "loadStateFlow", "j", "getOnPagesUpdatedFlow", "onPagesUpdatedFlow", "getItemCount", "()I", "itemCount", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "diffCallback", "Landroidx/recyclerview/widget/ListUpdateCallback;", "updateCallback", "Lkotlinx/coroutines/CoroutineDispatcher;", "mainDispatcher", "workerDispatcher", "<init>", "(Landroidx/recyclerview/widget/DiffUtil$ItemCallback;Landroidx/recyclerview/widget/ListUpdateCallback;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;)V", "paging-runtime_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class AsyncPagingDataDiffer<T> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final DiffUtil.ItemCallback<T> f1474a;
    @NotNull
    public final ListUpdateCallback b;
    @NotNull
    public final CoroutineDispatcher c;
    @NotNull
    public final CoroutineDispatcher d;
    @NotNull
    public final DifferCallback e;
    public boolean f;
    @NotNull
    public final AsyncPagingDataDiffer$differBase$1 g;
    @NotNull
    public final AtomicInteger h;
    @NotNull
    public final Flow<CombinedLoadStates> i;
    @NotNull
    public final Flow<Unit> j;

    @DebugMetadata(c = "androidx.paging.AsyncPagingDataDiffer$submitData$2", f = "AsyncPagingDataDiffer.kt", i = {}, l = {163}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ int $id;
        public final /* synthetic */ PagingData<T> $pagingData;
        public int label;
        public final /* synthetic */ AsyncPagingDataDiffer<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(AsyncPagingDataDiffer<T> asyncPagingDataDiffer, int i, PagingData<T> pagingData, Continuation<? super a> continuation) {
            super(2, continuation);
            this.this$0 = asyncPagingDataDiffer;
            this.$id = i;
            this.$pagingData = pagingData;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.this$0, this.$id, this.$pagingData, continuation);
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
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.this$0.h.get() == this.$id) {
                    AsyncPagingDataDiffer$differBase$1 asyncPagingDataDiffer$differBase$1 = this.this$0.g;
                    PagingData<T> pagingData = this.$pagingData;
                    this.label = 1;
                    if (asyncPagingDataDiffer$differBase$1.collectFrom(pagingData, this) == coroutine_suspended) {
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

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public AsyncPagingDataDiffer(@NotNull DiffUtil.ItemCallback<T> diffCallback, @NotNull ListUpdateCallback updateCallback) {
        this(diffCallback, updateCallback, null, null, 12, null);
        Intrinsics.checkNotNullParameter(diffCallback, "diffCallback");
        Intrinsics.checkNotNullParameter(updateCallback, "updateCallback");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public AsyncPagingDataDiffer(@NotNull DiffUtil.ItemCallback<T> diffCallback, @NotNull ListUpdateCallback updateCallback, @NotNull CoroutineDispatcher mainDispatcher) {
        this(diffCallback, updateCallback, mainDispatcher, null, 8, null);
        Intrinsics.checkNotNullParameter(diffCallback, "diffCallback");
        Intrinsics.checkNotNullParameter(updateCallback, "updateCallback");
        Intrinsics.checkNotNullParameter(mainDispatcher, "mainDispatcher");
    }

    @JvmOverloads
    public AsyncPagingDataDiffer(@NotNull DiffUtil.ItemCallback<T> diffCallback, @NotNull ListUpdateCallback updateCallback, @NotNull CoroutineDispatcher mainDispatcher, @NotNull CoroutineDispatcher workerDispatcher) {
        Intrinsics.checkNotNullParameter(diffCallback, "diffCallback");
        Intrinsics.checkNotNullParameter(updateCallback, "updateCallback");
        Intrinsics.checkNotNullParameter(mainDispatcher, "mainDispatcher");
        Intrinsics.checkNotNullParameter(workerDispatcher, "workerDispatcher");
        this.f1474a = diffCallback;
        this.b = updateCallback;
        this.c = mainDispatcher;
        this.d = workerDispatcher;
        DifferCallback differCallback = new DifferCallback(this) { // from class: androidx.paging.AsyncPagingDataDiffer$differCallback$1

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ AsyncPagingDataDiffer<T> f1475a;

            {
                this.f1475a = this;
            }

            @Override // androidx.paging.DifferCallback
            public void onChanged(int i, int i2) {
                ListUpdateCallback listUpdateCallback;
                if (i2 > 0) {
                    listUpdateCallback = this.f1475a.b;
                    listUpdateCallback.onChanged(i, i2, null);
                }
            }

            @Override // androidx.paging.DifferCallback
            public void onInserted(int i, int i2) {
                ListUpdateCallback listUpdateCallback;
                if (i2 > 0) {
                    listUpdateCallback = this.f1475a.b;
                    listUpdateCallback.onInserted(i, i2);
                }
            }

            @Override // androidx.paging.DifferCallback
            public void onRemoved(int i, int i2) {
                ListUpdateCallback listUpdateCallback;
                if (i2 > 0) {
                    listUpdateCallback = this.f1475a.b;
                    listUpdateCallback.onRemoved(i, i2);
                }
            }
        };
        this.e = differCallback;
        AsyncPagingDataDiffer$differBase$1 asyncPagingDataDiffer$differBase$1 = new AsyncPagingDataDiffer$differBase$1(this, differCallback, mainDispatcher);
        this.g = asyncPagingDataDiffer$differBase$1;
        this.h = new AtomicInteger(0);
        this.i = asyncPagingDataDiffer$differBase$1.getLoadStateFlow();
        this.j = asyncPagingDataDiffer$differBase$1.getOnPagesUpdatedFlow();
    }

    public static /* synthetic */ void getDifferCallback$paging_runtime_release$annotations() {
    }

    public static /* synthetic */ void getInGetItem$paging_runtime_release$annotations() {
    }

    public final void addLoadStateListener(@NotNull Function1<? super CombinedLoadStates, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.g.addLoadStateListener(listener);
    }

    public final void addOnPagesUpdatedListener(@NotNull Function0<Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.g.addOnPagesUpdatedListener(listener);
    }

    @NotNull
    public final DifferCallback getDifferCallback$paging_runtime_release() {
        return this.e;
    }

    public final boolean getInGetItem$paging_runtime_release() {
        return this.f;
    }

    @Nullable
    public final T getItem(@IntRange(from = 0) int i) {
        try {
            this.f = true;
            return this.g.get(i);
        } finally {
            this.f = false;
        }
    }

    public final int getItemCount() {
        return this.g.getSize();
    }

    @NotNull
    public final Flow<CombinedLoadStates> getLoadStateFlow() {
        return this.i;
    }

    @NotNull
    public final Flow<Unit> getOnPagesUpdatedFlow() {
        return this.j;
    }

    @Nullable
    public final T peek(@IntRange(from = 0) int i) {
        return this.g.peek(i);
    }

    public final void refresh() {
        this.g.refresh();
    }

    public final void removeLoadStateListener(@NotNull Function1<? super CombinedLoadStates, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.g.removeLoadStateListener(listener);
    }

    public final void removeOnPagesUpdatedListener(@NotNull Function0<Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.g.removeOnPagesUpdatedListener(listener);
    }

    public final void retry() {
        this.g.retry();
    }

    public final void setInGetItem$paging_runtime_release(boolean z) {
        this.f = z;
    }

    @NotNull
    public final ItemSnapshotList<T> snapshot() {
        return this.g.snapshot();
    }

    @Nullable
    public final Object submitData(@NotNull PagingData<T> pagingData, @NotNull Continuation<? super Unit> continuation) {
        this.h.incrementAndGet();
        Object collectFrom = this.g.collectFrom(pagingData, continuation);
        return collectFrom == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? collectFrom : Unit.INSTANCE;
    }

    public final void submitData(@NotNull Lifecycle lifecycle, @NotNull PagingData<T> pagingData) {
        Intrinsics.checkNotNullParameter(lifecycle, "lifecycle");
        Intrinsics.checkNotNullParameter(pagingData, "pagingData");
        e.e(LifecycleKt.getCoroutineScope(lifecycle), null, null, new a(this, this.h.incrementAndGet(), pagingData, null), 3, null);
    }

    public /* synthetic */ AsyncPagingDataDiffer(DiffUtil.ItemCallback itemCallback, ListUpdateCallback listUpdateCallback, CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(itemCallback, listUpdateCallback, (i & 4) != 0 ? Dispatchers.getMain() : coroutineDispatcher, (i & 8) != 0 ? Dispatchers.getDefault() : coroutineDispatcher2);
    }
}

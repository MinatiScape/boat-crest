package androidx.paging;

import androidx.annotation.IntRange;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.Lifecycle;
import androidx.paging.LoadState;
import androidx.recyclerview.widget.AdapterListUpdateCallback;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.jstyle.blesdk1860.constant.DeviceKey;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0004*\u00020\u00032\b\u0012\u0004\u0012\u00028\u00010\u0005B+\b\u0007\u0012\f\u00109\u001a\b\u0012\u0004\u0012\u00028\u000008\u0012\b\b\u0002\u0010;\u001a\u00020:\u0012\b\b\u0002\u0010<\u001a\u00020:¢\u0006\u0004\b=\u0010>J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u000e\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nJ\u000e\u0010\u0010\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u000eJ!\u0010\u0013\u001a\u00020\b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014J\u001c\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u00152\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011J\u0006\u0010\u0017\u001a\u00020\bJ\u0006\u0010\u0018\u001a\u00020\bJ\u001b\u0010\u0019\u001a\u0004\u0018\u00018\u00002\b\b\u0001\u0010\u000b\u001a\u00020\nH\u0004¢\u0006\u0004\b\u0019\u0010\u001aJ\u0019\u0010\u001c\u001a\u0004\u0018\u00018\u00002\b\b\u0001\u0010\u001b\u001a\u00020\n¢\u0006\u0004\b\u001c\u0010\u001aJ\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001dJ\b\u0010\u001f\u001a\u00020\nH\u0016J\u001a\u0010#\u001a\u00020\b2\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\b0 J\u001a\u0010$\u001a\u00020\b2\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\b0 J\u0014\u0010&\u001a\u00020\b2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\b0%J\u0014\u0010'\u001a\u00020\b2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\b0%J\u0012\u0010+\u001a\u00020*2\n\u0010)\u001a\u0006\u0012\u0002\b\u00030(J\u0012\u0010-\u001a\u00020*2\n\u0010,\u001a\u0006\u0012\u0002\b\u00030(J\u001e\u0010.\u001a\u00020*2\n\u0010)\u001a\u0006\u0012\u0002\b\u00030(2\n\u0010,\u001a\u0006\u0012\u0002\b\u00030(R\u001f\u00104\u001a\b\u0012\u0004\u0012\u00020!0/8\u0006@\u0006¢\u0006\f\n\u0004\b0\u00101\u001a\u0004\b2\u00103R\u001f\u00107\u001a\b\u0012\u0004\u0012\u00020\b0/8\u0006@\u0006¢\u0006\f\n\u0004\b5\u00101\u001a\u0004\b6\u00103\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006?"}, d2 = {"Landroidx/paging/PagingDataAdapter;", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "VH", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter$StateRestorationPolicy;", "strategy", "", "setStateRestorationPolicy", "", DeviceKey.position, "", "getItemId", "", "hasStableIds", "setHasStableIds", "Landroidx/paging/PagingData;", "pagingData", "submitData", "(Landroidx/paging/PagingData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/lifecycle/Lifecycle;", "lifecycle", "retry", "refresh", "getItem", "(I)Ljava/lang/Object;", FirebaseAnalytics.Param.INDEX, "peek", "Landroidx/paging/ItemSnapshotList;", "snapshot", "getItemCount", "Lkotlin/Function1;", "Landroidx/paging/CombinedLoadStates;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "addLoadStateListener", "removeLoadStateListener", "Lkotlin/Function0;", "addOnPagesUpdatedListener", "removeOnPagesUpdatedListener", "Landroidx/paging/LoadStateAdapter;", "header", "Landroidx/recyclerview/widget/ConcatAdapter;", "withLoadStateHeader", "footer", "withLoadStateFooter", "withLoadStateHeaderAndFooter", "Lkotlinx/coroutines/flow/Flow;", com.google.android.material.color.c.f10260a, "Lkotlinx/coroutines/flow/Flow;", "getLoadStateFlow", "()Lkotlinx/coroutines/flow/Flow;", "loadStateFlow", "d", "getOnPagesUpdatedFlow", "onPagesUpdatedFlow", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "diffCallback", "Lkotlinx/coroutines/CoroutineDispatcher;", "mainDispatcher", "workerDispatcher", "<init>", "(Landroidx/recyclerview/widget/DiffUtil$ItemCallback;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;)V", "paging-runtime_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public abstract class PagingDataAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    /* renamed from: a  reason: collision with root package name */
    public boolean f1531a;
    @NotNull
    public final AsyncPagingDataDiffer<T> b;
    @NotNull
    public final Flow<CombinedLoadStates> c;
    @NotNull
    public final Flow<Unit> d;

    /* loaded from: classes.dex */
    public static final class a extends Lambda implements Function1<CombinedLoadStates, Unit> {
        public final /* synthetic */ LoadStateAdapter<?> $footer;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(LoadStateAdapter<?> loadStateAdapter) {
            super(1);
            this.$footer = loadStateAdapter;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(CombinedLoadStates combinedLoadStates) {
            invoke2(combinedLoadStates);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull CombinedLoadStates loadStates) {
            Intrinsics.checkNotNullParameter(loadStates, "loadStates");
            this.$footer.setLoadState(loadStates.getAppend());
        }
    }

    /* loaded from: classes.dex */
    public static final class b extends Lambda implements Function1<CombinedLoadStates, Unit> {
        public final /* synthetic */ LoadStateAdapter<?> $header;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(LoadStateAdapter<?> loadStateAdapter) {
            super(1);
            this.$header = loadStateAdapter;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(CombinedLoadStates combinedLoadStates) {
            invoke2(combinedLoadStates);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull CombinedLoadStates loadStates) {
            Intrinsics.checkNotNullParameter(loadStates, "loadStates");
            this.$header.setLoadState(loadStates.getPrepend());
        }
    }

    /* loaded from: classes.dex */
    public static final class c extends Lambda implements Function1<CombinedLoadStates, Unit> {
        public final /* synthetic */ LoadStateAdapter<?> $footer;
        public final /* synthetic */ LoadStateAdapter<?> $header;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(LoadStateAdapter<?> loadStateAdapter, LoadStateAdapter<?> loadStateAdapter2) {
            super(1);
            this.$header = loadStateAdapter;
            this.$footer = loadStateAdapter2;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(CombinedLoadStates combinedLoadStates) {
            invoke2(combinedLoadStates);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull CombinedLoadStates loadStates) {
            Intrinsics.checkNotNullParameter(loadStates, "loadStates");
            this.$header.setLoadState(loadStates.getPrepend());
            this.$footer.setLoadState(loadStates.getAppend());
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public PagingDataAdapter(@NotNull DiffUtil.ItemCallback<T> diffCallback) {
        this(diffCallback, null, null, 6, null);
        Intrinsics.checkNotNullParameter(diffCallback, "diffCallback");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public PagingDataAdapter(@NotNull DiffUtil.ItemCallback<T> diffCallback, @NotNull CoroutineDispatcher mainDispatcher) {
        this(diffCallback, mainDispatcher, null, 4, null);
        Intrinsics.checkNotNullParameter(diffCallback, "diffCallback");
        Intrinsics.checkNotNullParameter(mainDispatcher, "mainDispatcher");
    }

    public /* synthetic */ PagingDataAdapter(DiffUtil.ItemCallback itemCallback, CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(itemCallback, (i & 2) != 0 ? Dispatchers.getMain() : coroutineDispatcher, (i & 4) != 0 ? Dispatchers.getDefault() : coroutineDispatcher2);
    }

    public static final <T, VH extends RecyclerView.ViewHolder> void a(PagingDataAdapter<T, VH> pagingDataAdapter) {
        if (pagingDataAdapter.getStateRestorationPolicy() != RecyclerView.Adapter.StateRestorationPolicy.PREVENT || pagingDataAdapter.f1531a) {
            return;
        }
        pagingDataAdapter.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.ALLOW);
    }

    public final void addLoadStateListener(@NotNull Function1<? super CombinedLoadStates, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.b.addLoadStateListener(listener);
    }

    public final void addOnPagesUpdatedListener(@NotNull Function0<Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.b.addOnPagesUpdatedListener(listener);
    }

    @Nullable
    public final T getItem(@IntRange(from = 0) int i) {
        return this.b.getItem(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.getItemCount();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final long getItemId(int i) {
        return super.getItemId(i);
    }

    @NotNull
    public final Flow<CombinedLoadStates> getLoadStateFlow() {
        return this.c;
    }

    @NotNull
    public final Flow<Unit> getOnPagesUpdatedFlow() {
        return this.d;
    }

    @Nullable
    public final T peek(@IntRange(from = 0) int i) {
        return this.b.peek(i);
    }

    public final void refresh() {
        this.b.refresh();
    }

    public final void removeLoadStateListener(@NotNull Function1<? super CombinedLoadStates, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.b.removeLoadStateListener(listener);
    }

    public final void removeOnPagesUpdatedListener(@NotNull Function0<Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.b.removeOnPagesUpdatedListener(listener);
    }

    public final void retry() {
        this.b.retry();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void setHasStableIds(boolean z) {
        throw new UnsupportedOperationException("Stable ids are unsupported on PagingDataAdapter.");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void setStateRestorationPolicy(@NotNull RecyclerView.Adapter.StateRestorationPolicy strategy) {
        Intrinsics.checkNotNullParameter(strategy, "strategy");
        this.f1531a = true;
        super.setStateRestorationPolicy(strategy);
    }

    @NotNull
    public final ItemSnapshotList<T> snapshot() {
        return this.b.snapshot();
    }

    @Nullable
    public final Object submitData(@NotNull PagingData<T> pagingData, @NotNull Continuation<? super Unit> continuation) {
        Object submitData = this.b.submitData(pagingData, continuation);
        return submitData == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? submitData : Unit.INSTANCE;
    }

    @NotNull
    public final ConcatAdapter withLoadStateFooter(@NotNull LoadStateAdapter<?> footer) {
        Intrinsics.checkNotNullParameter(footer, "footer");
        addLoadStateListener(new a(footer));
        return new ConcatAdapter(this, footer);
    }

    @NotNull
    public final ConcatAdapter withLoadStateHeader(@NotNull LoadStateAdapter<?> header) {
        Intrinsics.checkNotNullParameter(header, "header");
        addLoadStateListener(new b(header));
        return new ConcatAdapter(header, this);
    }

    @NotNull
    public final ConcatAdapter withLoadStateHeaderAndFooter(@NotNull LoadStateAdapter<?> header, @NotNull LoadStateAdapter<?> footer) {
        Intrinsics.checkNotNullParameter(header, "header");
        Intrinsics.checkNotNullParameter(footer, "footer");
        addLoadStateListener(new c(header, footer));
        return new ConcatAdapter(header, this, footer);
    }

    public final void submitData(@NotNull Lifecycle lifecycle, @NotNull PagingData<T> pagingData) {
        Intrinsics.checkNotNullParameter(lifecycle, "lifecycle");
        Intrinsics.checkNotNullParameter(pagingData, "pagingData");
        this.b.submitData(lifecycle, pagingData);
    }

    @JvmOverloads
    public PagingDataAdapter(@NotNull DiffUtil.ItemCallback<T> diffCallback, @NotNull CoroutineDispatcher mainDispatcher, @NotNull CoroutineDispatcher workerDispatcher) {
        Intrinsics.checkNotNullParameter(diffCallback, "diffCallback");
        Intrinsics.checkNotNullParameter(mainDispatcher, "mainDispatcher");
        Intrinsics.checkNotNullParameter(workerDispatcher, "workerDispatcher");
        AsyncPagingDataDiffer<T> asyncPagingDataDiffer = new AsyncPagingDataDiffer<>(diffCallback, new AdapterListUpdateCallback(this), mainDispatcher, workerDispatcher);
        this.b = asyncPagingDataDiffer;
        super.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.PREVENT);
        registerAdapterDataObserver(new RecyclerView.AdapterDataObserver(this) { // from class: androidx.paging.PagingDataAdapter.1

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ PagingDataAdapter<T, VH> f1532a;

            {
                this.f1532a = this;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeInserted(int i, int i2) {
                PagingDataAdapter.a(this.f1532a);
                this.f1532a.unregisterAdapterDataObserver(this);
                super.onItemRangeInserted(i, i2);
            }
        });
        addLoadStateListener(new Function1<CombinedLoadStates, Unit>(this) { // from class: androidx.paging.PagingDataAdapter.2
            public boolean h = true;
            public final /* synthetic */ PagingDataAdapter<T, VH> i;

            {
                this.i = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(CombinedLoadStates combinedLoadStates) {
                invoke2(combinedLoadStates);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public void invoke2(@NotNull CombinedLoadStates loadStates) {
                Intrinsics.checkNotNullParameter(loadStates, "loadStates");
                if (this.h) {
                    this.h = false;
                } else if (loadStates.getSource().getRefresh() instanceof LoadState.NotLoading) {
                    PagingDataAdapter.a(this.i);
                    this.i.removeLoadStateListener(this);
                }
            }
        });
        this.c = asyncPagingDataDiffer.getLoadStateFlow();
        this.d = asyncPagingDataDiffer.getOnPagesUpdatedFlow();
    }
}

package androidx.paging;

import androidx.annotation.IntRange;
import androidx.annotation.RestrictTo;
import androidx.exifinterface.media.ExifInterface;
import androidx.paging.PagePresenter;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b'\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0001B\u0019\u0012\u0006\u00104\u001a\u000203\u0012\b\b\u0002\u00106\u001a\u000205¢\u0006\u0004\b7\u00108J!\u0010\t\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003H\u0000¢\u0006\u0004\b\u0007\u0010\bJG\u0010\u0011\u001a\u0004\u0018\u00010\r2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\n2\u0006\u0010\u000e\u001a\u00020\r2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u000fH¦@ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\b\u0010\u0014\u001a\u00020\u0013H\u0016J!\u0010\u0017\u001a\u00020\u00062\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u0015H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u001c\u0010\u001a\u001a\u0004\u0018\u00018\u00002\b\b\u0001\u0010\u0019\u001a\u00020\rH\u0086\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u0019\u0010\u001c\u001a\u0004\u0018\u00018\u00002\b\b\u0001\u0010\u0019\u001a\u00020\r¢\u0006\u0004\b\u001c\u0010\u001bJ\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001dJ\u0006\u0010\u001f\u001a\u00020\u0006J\u0006\u0010 \u001a\u00020\u0006J\u0014\u0010\"\u001a\u00020\u00062\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00060\u000fJ\u0014\u0010#\u001a\u00020\u00062\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00060\u000fJ\u001a\u0010&\u001a\u00020\u00062\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u00060$J\u001a\u0010'\u001a\u00020\u00062\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u00060$R\u001f\u0010-\u001a\b\u0012\u0004\u0012\u00020%0(8\u0006@\u0006¢\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,R\u0013\u00100\u001a\u00020\r8F@\u0006¢\u0006\u0006\u001a\u0004\b.\u0010/R\u0019\u00102\u001a\b\u0012\u0004\u0012\u00020\u00060(8F@\u0006¢\u0006\u0006\u001a\u0004\b1\u0010,\u0082\u0002\u0004\n\u0002\b\u0019¨\u00069"}, d2 = {"Landroidx/paging/PagingDataDiffer;", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/paging/LoadStates;", "source", "mediator", "", "dispatchLoadStates$paging_common", "(Landroidx/paging/LoadStates;Landroidx/paging/LoadStates;)V", "dispatchLoadStates", "Landroidx/paging/NullPaddedList;", "previousList", "newList", "", "lastAccessedIndex", "Lkotlin/Function0;", "onListPresentable", "presentNewList", "(Landroidx/paging/NullPaddedList;Landroidx/paging/NullPaddedList;ILkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "postEvents", "Landroidx/paging/PagingData;", "pagingData", "collectFrom", "(Landroidx/paging/PagingData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", FirebaseAnalytics.Param.INDEX, "get", "(I)Ljava/lang/Object;", "peek", "Landroidx/paging/ItemSnapshotList;", "snapshot", "retry", "refresh", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "addOnPagesUpdatedListener", "removeOnPagesUpdatedListener", "Lkotlin/Function1;", "Landroidx/paging/CombinedLoadStates;", "addLoadStateListener", "removeLoadStateListener", "Lkotlinx/coroutines/flow/Flow;", OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME, "Lkotlinx/coroutines/flow/Flow;", "getLoadStateFlow", "()Lkotlinx/coroutines/flow/Flow;", "loadStateFlow", "getSize", "()I", "size", "getOnPagesUpdatedFlow", "onPagesUpdatedFlow", "Landroidx/paging/DifferCallback;", "differCallback", "Lkotlinx/coroutines/CoroutineDispatcher;", "mainDispatcher", "<init>", "(Landroidx/paging/DifferCallback;Lkotlinx/coroutines/CoroutineDispatcher;)V", "paging-common"}, k = 1, mv = {1, 5, 1})
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public abstract class PagingDataDiffer<T> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final DifferCallback f1533a;
    @NotNull
    public final CoroutineDispatcher b;
    @NotNull
    public PagePresenter<T> c;
    @Nullable
    public UiReceiver d;
    @NotNull
    public final MutableCombinedLoadStateCollection e;
    @NotNull
    public final CopyOnWriteArrayList<Function0<Unit>> f;
    @NotNull
    public final SingleRunner g;
    public volatile boolean h;
    public volatile int i;
    @NotNull
    public final PagingDataDiffer$processPageEventCallback$1 j;
    @NotNull
    public final Flow<CombinedLoadStates> k;
    @NotNull
    public final MutableSharedFlow<Unit> l;

    /* loaded from: classes.dex */
    public static final class a extends Lambda implements Function0<Unit> {
        public final /* synthetic */ PagingDataDiffer<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(PagingDataDiffer<T> pagingDataDiffer) {
            super(0);
            this.this$0 = pagingDataDiffer;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2() {
            this.this$0.l.tryEmit(Unit.INSTANCE);
        }
    }

    /* JADX WARN: Type inference failed for: r5v3, types: [androidx.paging.PagingDataDiffer$processPageEventCallback$1] */
    public PagingDataDiffer(@NotNull DifferCallback differCallback, @NotNull CoroutineDispatcher mainDispatcher) {
        Intrinsics.checkNotNullParameter(differCallback, "differCallback");
        Intrinsics.checkNotNullParameter(mainDispatcher, "mainDispatcher");
        this.f1533a = differCallback;
        this.b = mainDispatcher;
        this.c = PagePresenter.Companion.initial$paging_common();
        MutableCombinedLoadStateCollection mutableCombinedLoadStateCollection = new MutableCombinedLoadStateCollection();
        this.e = mutableCombinedLoadStateCollection;
        this.f = new CopyOnWriteArrayList<>();
        this.g = new SingleRunner(false, 1, null);
        this.j = new PagePresenter.ProcessPageEventCallback(this) { // from class: androidx.paging.PagingDataDiffer$processPageEventCallback$1

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ PagingDataDiffer<T> f1534a;

            {
                this.f1534a = this;
            }

            @Override // androidx.paging.PagePresenter.ProcessPageEventCallback
            public void onChanged(int i, int i2) {
                DifferCallback differCallback2;
                differCallback2 = this.f1534a.f1533a;
                differCallback2.onChanged(i, i2);
            }

            @Override // androidx.paging.PagePresenter.ProcessPageEventCallback
            public void onInserted(int i, int i2) {
                DifferCallback differCallback2;
                differCallback2 = this.f1534a.f1533a;
                differCallback2.onInserted(i, i2);
            }

            @Override // androidx.paging.PagePresenter.ProcessPageEventCallback
            public void onRemoved(int i, int i2) {
                DifferCallback differCallback2;
                differCallback2 = this.f1534a.f1533a;
                differCallback2.onRemoved(i, i2);
            }

            @Override // androidx.paging.PagePresenter.ProcessPageEventCallback
            public void onStateUpdate(@NotNull LoadStates source, @Nullable LoadStates loadStates) {
                Intrinsics.checkNotNullParameter(source, "source");
                this.f1534a.dispatchLoadStates$paging_common(source, loadStates);
            }

            @Override // androidx.paging.PagePresenter.ProcessPageEventCallback
            public void onStateUpdate(@NotNull LoadType loadType, boolean z, @NotNull LoadState loadState) {
                MutableCombinedLoadStateCollection mutableCombinedLoadStateCollection2;
                MutableCombinedLoadStateCollection mutableCombinedLoadStateCollection3;
                Intrinsics.checkNotNullParameter(loadType, "loadType");
                Intrinsics.checkNotNullParameter(loadState, "loadState");
                mutableCombinedLoadStateCollection2 = this.f1534a.e;
                if (Intrinsics.areEqual(mutableCombinedLoadStateCollection2.get(loadType, z), loadState)) {
                    return;
                }
                mutableCombinedLoadStateCollection3 = this.f1534a.e;
                mutableCombinedLoadStateCollection3.set(loadType, z, loadState);
            }
        };
        this.k = mutableCombinedLoadStateCollection.getFlow();
        this.l = SharedFlowKt.MutableSharedFlow(0, 64, BufferOverflow.DROP_OLDEST);
        addOnPagesUpdatedListener(new a(this));
    }

    public final void addLoadStateListener(@NotNull Function1<? super CombinedLoadStates, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.e.addListener(listener);
    }

    public final void addOnPagesUpdatedListener(@NotNull Function0<Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f.add(listener);
    }

    @Nullable
    public final Object collectFrom(@NotNull PagingData<T> pagingData, @NotNull Continuation<? super Unit> continuation) {
        Object runInIsolation$default = SingleRunner.runInIsolation$default(this.g, 0, new PagingDataDiffer$collectFrom$2(this, pagingData, null), continuation, 1, null);
        return runInIsolation$default == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? runInIsolation$default : Unit.INSTANCE;
    }

    public final void dispatchLoadStates$paging_common(@NotNull LoadStates source, @Nullable LoadStates loadStates) {
        Intrinsics.checkNotNullParameter(source, "source");
        if (Intrinsics.areEqual(this.e.getSource(), source) && Intrinsics.areEqual(this.e.getMediator(), loadStates)) {
            return;
        }
        this.e.set(source, loadStates);
    }

    @Nullable
    public final T get(@IntRange(from = 0) int i) {
        this.h = true;
        this.i = i;
        UiReceiver uiReceiver = this.d;
        if (uiReceiver != null) {
            uiReceiver.accessHint(this.c.accessHintForPresenterIndex(i));
        }
        return this.c.get(i);
    }

    @NotNull
    public final Flow<CombinedLoadStates> getLoadStateFlow() {
        return this.k;
    }

    @NotNull
    public final Flow<Unit> getOnPagesUpdatedFlow() {
        return FlowKt.asSharedFlow(this.l);
    }

    public final int getSize() {
        return this.c.getSize();
    }

    @Nullable
    public final T peek(@IntRange(from = 0) int i) {
        return this.c.get(i);
    }

    public boolean postEvents() {
        return false;
    }

    @Nullable
    public abstract Object presentNewList(@NotNull NullPaddedList<T> nullPaddedList, @NotNull NullPaddedList<T> nullPaddedList2, int i, @NotNull Function0<Unit> function0, @NotNull Continuation<? super Integer> continuation);

    public final void refresh() {
        UiReceiver uiReceiver = this.d;
        if (uiReceiver == null) {
            return;
        }
        uiReceiver.refresh();
    }

    public final void removeLoadStateListener(@NotNull Function1<? super CombinedLoadStates, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.e.removeListener(listener);
    }

    public final void removeOnPagesUpdatedListener(@NotNull Function0<Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f.remove(listener);
    }

    public final void retry() {
        UiReceiver uiReceiver = this.d;
        if (uiReceiver == null) {
            return;
        }
        uiReceiver.retry();
    }

    @NotNull
    public final ItemSnapshotList<T> snapshot() {
        return this.c.snapshot();
    }

    public /* synthetic */ PagingDataDiffer(DifferCallback differCallback, CoroutineDispatcher coroutineDispatcher, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(differCallback, (i & 2) != 0 ? Dispatchers.getMain() : coroutineDispatcher);
    }
}

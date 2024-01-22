package androidx.paging;

import androidx.paging.LoadState;
import androidx.paging.PageEvent;
import androidx.paging.PageFetcherSnapshotState;
import androidx.paging.PagingSource;
import com.clevertap.android.sdk.Constants;
import com.realsil.sdk.dfu.DfuException;
import com.szabh.smable3.entity.BleNewsFeed;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.u;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0003*\u00020\u00012\u00020\u0001B\u0085\u0001\u0012\b\u0010\u0010\u001a\u0004\u0018\u00018\u0000\u0012\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0011\u0012\u0006\u0010%\u001a\u00020$\u0012\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00060\u001d\u0012\b\b\u0002\u0010(\u001a\u00020'\u0012\u0016\b\u0002\u0010\u001c\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0017\u0012\u0016\b\u0002\u0010)\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\t\u0012\u000e\b\u0002\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00060*¢\u0006\u0004\b,\u0010-J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004J\u0006\u0010\b\u001a\u00020\u0006J\u001f\u0010\n\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\tH\u0086@ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bR\u001e\u0010\u0010\u001a\u0004\u0018\u00018\u00008\u0000@\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR(\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00118\u0000@\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R'\u0010\u001c\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00178\u0006@\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR%\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u001e0\u001d8\u0006@\u0006¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006."}, d2 = {"Landroidx/paging/PageFetcherSnapshot;", "", "Key", "Value", "Landroidx/paging/ViewportHint;", "viewportHint", "", "accessHint", Constants.KEY_HIDE_CLOSE, "Landroidx/paging/PagingState;", "currentPagingState", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "a", "Ljava/lang/Object;", "getInitialKey$paging_common", "()Ljava/lang/Object;", "initialKey", "Landroidx/paging/PagingSource;", "b", "Landroidx/paging/PagingSource;", "getPagingSource$paging_common", "()Landroidx/paging/PagingSource;", "pagingSource", "Landroidx/paging/RemoteMediatorConnection;", "f", "Landroidx/paging/RemoteMediatorConnection;", "getRemoteMediatorConnection", "()Landroidx/paging/RemoteMediatorConnection;", "remoteMediatorConnection", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/PageEvent;", "n", "Lkotlinx/coroutines/flow/Flow;", "getPageEventFlow", "()Lkotlinx/coroutines/flow/Flow;", "pageEventFlow", "Landroidx/paging/PagingConfig;", Constants.KEY_CONFIG, "retryFlow", "", "triggerRemoteRefresh", "previousPagingState", "Lkotlin/Function0;", "invalidate", "<init>", "(Ljava/lang/Object;Landroidx/paging/PagingSource;Landroidx/paging/PagingConfig;Lkotlinx/coroutines/flow/Flow;ZLandroidx/paging/RemoteMediatorConnection;Landroidx/paging/PagingState;Lkotlin/jvm/functions/Function0;)V", "paging-common"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class PageFetcherSnapshot<Key, Value> {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Key f1517a;
    @NotNull
    public final PagingSource<Key, Value> b;
    @NotNull
    public final PagingConfig c;
    @NotNull
    public final Flow<Unit> d;
    public final boolean e;
    @Nullable
    public final RemoteMediatorConnection<Key, Value> f;
    @Nullable
    public final PagingState<Key, Value> g;
    @NotNull
    public final Function0<Unit> h;
    @NotNull
    public final HintHandler i;
    @NotNull
    public final AtomicBoolean j;
    @NotNull
    public final Channel<PageEvent<Value>> k;
    @NotNull
    public final PageFetcherSnapshotState.Holder<Key, Value> l;
    @NotNull
    public final CompletableJob m;
    @NotNull
    public final Flow<PageEvent<Value>> n;

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LoadType.values().length];
            iArr[LoadType.REFRESH.ordinal()] = 1;
            iArr[LoadType.PREPEND.ordinal()] = 2;
            iArr[LoadType.APPEND.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* loaded from: classes.dex */
    public static final class a extends Lambda implements Function0<Unit> {
        public static final a INSTANCE = new a();

        public a() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2() {
        }
    }

    @DebugMetadata(c = "androidx.paging.PageFetcherSnapshot$collectAsGenerationalViewportHints$3", f = "PageFetcherSnapshot.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes.dex */
    public static final class b extends SuspendLambda implements Function3<GenerationalViewportHint, GenerationalViewportHint, Continuation<? super GenerationalViewportHint>, Object> {
        public final /* synthetic */ LoadType $loadType;
        public /* synthetic */ Object L$0;
        public /* synthetic */ Object L$1;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(LoadType loadType, Continuation<? super b> continuation) {
            super(3, continuation);
            this.$loadType = loadType;
        }

        @Override // kotlin.jvm.functions.Function3
        @Nullable
        public final Object invoke(@NotNull GenerationalViewportHint generationalViewportHint, @NotNull GenerationalViewportHint generationalViewportHint2, @Nullable Continuation<? super GenerationalViewportHint> continuation) {
            b bVar = new b(this.$loadType, continuation);
            bVar.L$0 = generationalViewportHint;
            bVar.L$1 = generationalViewportHint2;
            return bVar.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                GenerationalViewportHint generationalViewportHint = (GenerationalViewportHint) this.L$0;
                GenerationalViewportHint generationalViewportHint2 = (GenerationalViewportHint) this.L$1;
                return PageFetcherSnapshotKt.shouldPrioritizeOver(generationalViewportHint2, generationalViewportHint, this.$loadType) ? generationalViewportHint2 : generationalViewportHint;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "androidx.paging.PageFetcherSnapshot", f = "PageFetcherSnapshot.kt", i = {0, 0, 0}, l = {608}, m = "currentPagingState", n = {"this", "this_$iv", "$this$withLock_u24default$iv$iv"}, s = {"L$0", "L$1", "L$2"})
    /* loaded from: classes.dex */
    public static final class c extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public int label;
        public /* synthetic */ Object result;
        public final /* synthetic */ PageFetcherSnapshot<Key, Value> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(PageFetcherSnapshot<Key, Value> pageFetcherSnapshot, Continuation<? super c> continuation) {
            super(continuation);
            this.this$0 = pageFetcherSnapshot;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.currentPagingState(this);
        }
    }

    @DebugMetadata(c = "androidx.paging.PageFetcherSnapshot", f = "PageFetcherSnapshot.kt", i = {0, 0, 0, 1, 1, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7, 8}, l = {608, DfuException.ERROR_ENTER_OTA_MODE_FAILED, 283, 619, 630, 317, 641, com.veryfit.multi.nativeprotocol.b.Y2, 341}, m = "doInitialLoad", n = {"this", "this_$iv", "$this$withLock_u24default$iv$iv", "this", "$this$withLock_u24default$iv$iv", "this", "this", "result", "this_$iv", "$this$withLock_u24default$iv$iv", "this", "result", "this_$iv", "$this$withLock_u24default$iv$iv", "this", "result", "$this$withLock_u24default$iv$iv", "this", "result", "this_$iv", "$this$withLock_u24default$iv$iv", "this", "result", "this_$iv", "$this$withLock_u24default$iv$iv", "$this$withLock_u24default$iv$iv"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$0", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$0"})
    /* loaded from: classes.dex */
    public static final class d extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public Object L$3;
        public int label;
        public /* synthetic */ Object result;
        public final /* synthetic */ PageFetcherSnapshot<Key, Value> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(PageFetcherSnapshot<Key, Value> pageFetcherSnapshot, Continuation<? super d> continuation) {
            super(continuation);
            this.this$0 = pageFetcherSnapshot;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.b(this);
        }
    }

    @DebugMetadata(c = "androidx.paging.PageFetcherSnapshot", f = "PageFetcherSnapshot.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10}, l = {609, com.veryfit.multi.nativeprotocol.b.M2, 398, 406, 631, 642, 448, com.veryfit.multi.nativeprotocol.b.Z2, BleNewsFeed.CONTENT_MAX_LENGTH, 496, 664}, m = "doLoad", n = {"this", "loadType", "generationalHint", "itemsLoaded", "this_$iv", "$this$withLock_u24default$iv$iv", "this", "loadType", "generationalHint", "itemsLoaded", "loadKey", "this_$iv", "$this$withLock_u24default$iv$iv", "this", "loadType", "generationalHint", "itemsLoaded", "loadKey", "$this$withLock_u24default$iv$iv", "this", "loadType", "generationalHint", "itemsLoaded", "loadKey", "endOfPaginationReached", "params", "this", "loadType", "generationalHint", "itemsLoaded", "loadKey", "endOfPaginationReached", "params", "result", "this_$iv", "$this$withLock_u24default$iv$iv", "this", "loadType", "generationalHint", "result", "this_$iv", "$this$withLock_u24default$iv$iv", "loadType", "generationalHint", "$this$withLock_u24default$iv$iv", "state", "this", "loadType", "generationalHint", "itemsLoaded", "loadKey", "endOfPaginationReached", "params", "result", "dropType", "this_$iv", "$this$withLock_u24default$iv$iv", "this", "loadType", "generationalHint", "itemsLoaded", "loadKey", "endOfPaginationReached", "params", "result", "$this$withLock_u24default$iv$iv", "state", "this", "loadType", "generationalHint", "itemsLoaded", "loadKey", "endOfPaginationReached", "params", "result", "$this$withLock_u24default$iv$iv", "this", "loadType", "generationalHint", "itemsLoaded", "loadKey", "endOfPaginationReached", "this_$iv", "$this$withLock_u24default$iv$iv", "endsPrepend", "endsAppend"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "I$0", "I$1"})
    /* loaded from: classes.dex */
    public static final class e extends ContinuationImpl {
        public int I$0;
        public int I$1;
        public Object L$0;
        public Object L$1;
        public Object L$10;
        public Object L$2;
        public Object L$3;
        public Object L$4;
        public Object L$5;
        public Object L$6;
        public Object L$7;
        public Object L$8;
        public Object L$9;
        public int label;
        public /* synthetic */ Object result;
        public final /* synthetic */ PageFetcherSnapshot<Key, Value> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e(PageFetcherSnapshot<Key, Value> pageFetcherSnapshot, Continuation<? super e> continuation) {
            super(continuation);
            this.this$0 = pageFetcherSnapshot;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.c(null, null, this);
        }
    }

    @DebugMetadata(c = "androidx.paging.PageFetcherSnapshot$pageEventFlow$2", f = "PageFetcherSnapshot.kt", i = {0, 0}, l = {608, 174}, m = "invokeSuspend", n = {"this_$iv", "$this$withLock_u24default$iv$iv"}, s = {"L$0", "L$1"})
    /* loaded from: classes.dex */
    public static final class f extends SuspendLambda implements Function2<FlowCollector<? super PageEvent<Value>>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        public Object L$1;
        public Object L$2;
        public int label;
        public final /* synthetic */ PageFetcherSnapshot<Key, Value> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public f(PageFetcherSnapshot<Key, Value> pageFetcherSnapshot, Continuation<? super f> continuation) {
            super(2, continuation);
            this.this$0 = pageFetcherSnapshot;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            f fVar = new f(this.this$0, continuation);
            fVar.L$0 = obj;
            return fVar;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Continuation<? super Unit> continuation) {
            return invoke((FlowCollector) ((FlowCollector) obj), continuation);
        }

        @Nullable
        public final Object invoke(@NotNull FlowCollector<? super PageEvent<Value>> flowCollector, @Nullable Continuation<? super Unit> continuation) {
            return ((f) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            FlowCollector flowCollector;
            PageFetcherSnapshotState.Holder holder;
            Mutex mutex;
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    flowCollector = (FlowCollector) this.L$0;
                    holder = this.this$0.l;
                    Mutex access$getLock$p = PageFetcherSnapshotState.Holder.access$getLock$p(holder);
                    this.L$0 = holder;
                    this.L$1 = access$getLock$p;
                    this.L$2 = flowCollector;
                    this.label = 1;
                    if (access$getLock$p.lock(null, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    mutex = access$getLock$p;
                } else if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                } else {
                    flowCollector = (FlowCollector) this.L$2;
                    mutex = (Mutex) this.L$1;
                    holder = (PageFetcherSnapshotState.Holder) this.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                LoadStates snapshot = PageFetcherSnapshotState.Holder.access$getState$p(holder).getSourceLoadStates$paging_common().snapshot();
                mutex.unlock(null);
                PageEvent.LoadStateUpdate loadStateUpdate = new PageEvent.LoadStateUpdate(snapshot, null, 2, null);
                this.L$0 = null;
                this.L$1 = null;
                this.L$2 = null;
                this.label = 2;
                if (flowCollector.emit(loadStateUpdate, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            } catch (Throwable th) {
                mutex.unlock(null);
                throw th;
            }
        }
    }

    @DebugMetadata(c = "androidx.paging.PageFetcherSnapshot$startConsumingHints$2", f = "PageFetcherSnapshot.kt", i = {0, 0}, l = {608, 229}, m = "invokeSuspend", n = {"this_$iv", "$this$withLock_u24default$iv$iv"}, s = {"L$0", "L$1"})
    /* loaded from: classes.dex */
    public static final class g extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public int label;
        public final /* synthetic */ PageFetcherSnapshot<Key, Value> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public g(PageFetcherSnapshot<Key, Value> pageFetcherSnapshot, Continuation<? super g> continuation) {
            super(2, continuation);
            this.this$0 = pageFetcherSnapshot;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new g(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((g) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            PageFetcherSnapshot<Key, Value> pageFetcherSnapshot;
            PageFetcherSnapshotState.Holder holder;
            Mutex mutex;
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    pageFetcherSnapshot = this.this$0;
                    holder = pageFetcherSnapshot.l;
                    Mutex access$getLock$p = PageFetcherSnapshotState.Holder.access$getLock$p(holder);
                    this.L$0 = holder;
                    this.L$1 = access$getLock$p;
                    this.L$2 = pageFetcherSnapshot;
                    this.label = 1;
                    if (access$getLock$p.lock(null, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    mutex = access$getLock$p;
                } else if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                } else {
                    pageFetcherSnapshot = (PageFetcherSnapshot) this.L$2;
                    mutex = (Mutex) this.L$1;
                    holder = (PageFetcherSnapshotState.Holder) this.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                Flow<Integer> consumePrependGenerationIdAsFlow = PageFetcherSnapshotState.Holder.access$getState$p(holder).consumePrependGenerationIdAsFlow();
                mutex.unlock(null);
                LoadType loadType = LoadType.PREPEND;
                this.L$0 = null;
                this.L$1 = null;
                this.L$2 = null;
                this.label = 2;
                if (pageFetcherSnapshot.a(consumePrependGenerationIdAsFlow, loadType, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            } catch (Throwable th) {
                mutex.unlock(null);
                throw th;
            }
        }
    }

    @DebugMetadata(c = "androidx.paging.PageFetcherSnapshot$startConsumingHints$3", f = "PageFetcherSnapshot.kt", i = {0, 0}, l = {608, 234}, m = "invokeSuspend", n = {"this_$iv", "$this$withLock_u24default$iv$iv"}, s = {"L$0", "L$1"})
    /* loaded from: classes.dex */
    public static final class h extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public int label;
        public final /* synthetic */ PageFetcherSnapshot<Key, Value> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public h(PageFetcherSnapshot<Key, Value> pageFetcherSnapshot, Continuation<? super h> continuation) {
            super(2, continuation);
            this.this$0 = pageFetcherSnapshot;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new h(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((h) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            PageFetcherSnapshot<Key, Value> pageFetcherSnapshot;
            PageFetcherSnapshotState.Holder holder;
            Mutex mutex;
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    pageFetcherSnapshot = this.this$0;
                    holder = pageFetcherSnapshot.l;
                    Mutex access$getLock$p = PageFetcherSnapshotState.Holder.access$getLock$p(holder);
                    this.L$0 = holder;
                    this.L$1 = access$getLock$p;
                    this.L$2 = pageFetcherSnapshot;
                    this.label = 1;
                    if (access$getLock$p.lock(null, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    mutex = access$getLock$p;
                } else if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                } else {
                    pageFetcherSnapshot = (PageFetcherSnapshot) this.L$2;
                    mutex = (Mutex) this.L$1;
                    holder = (PageFetcherSnapshotState.Holder) this.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                Flow<Integer> consumeAppendGenerationIdAsFlow = PageFetcherSnapshotState.Holder.access$getState$p(holder).consumeAppendGenerationIdAsFlow();
                mutex.unlock(null);
                LoadType loadType = LoadType.APPEND;
                this.L$0 = null;
                this.L$1 = null;
                this.L$2 = null;
                this.label = 2;
                if (pageFetcherSnapshot.a(consumeAppendGenerationIdAsFlow, loadType, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            } catch (Throwable th) {
                mutex.unlock(null);
                throw th;
            }
        }
    }

    public PageFetcherSnapshot(@Nullable Key key, @NotNull PagingSource<Key, Value> pagingSource, @NotNull PagingConfig config, @NotNull Flow<Unit> retryFlow, boolean z, @Nullable RemoteMediatorConnection<Key, Value> remoteMediatorConnection, @Nullable PagingState<Key, Value> pagingState, @NotNull Function0<Unit> invalidate) {
        CompletableJob c2;
        Intrinsics.checkNotNullParameter(pagingSource, "pagingSource");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(retryFlow, "retryFlow");
        Intrinsics.checkNotNullParameter(invalidate, "invalidate");
        this.f1517a = key;
        this.b = pagingSource;
        this.c = config;
        this.d = retryFlow;
        this.e = z;
        this.f = remoteMediatorConnection;
        this.g = pagingState;
        this.h = invalidate;
        if (config.jumpThreshold == Integer.MIN_VALUE || pagingSource.getJumpingSupported()) {
            this.i = new HintHandler();
            this.j = new AtomicBoolean(false);
            this.k = ChannelKt.Channel$default(-2, null, null, 6, null);
            this.l = new PageFetcherSnapshotState.Holder<>(config);
            c2 = u.c(null, 1, null);
            this.m = c2;
            this.n = FlowKt.onStart(CancelableChannelFlowKt.cancelableChannelFlow(c2, new PageFetcherSnapshot$pageEventFlow$1(this, null)), new f(this, null));
            return;
        }
        throw new IllegalArgumentException("PagingConfig.jumpThreshold was set, but the associated PagingSource has not marked support for jumps by overriding PagingSource.jumpingSupported to true.".toString());
    }

    public final Object a(Flow<Integer> flow, final LoadType loadType, Continuation<? super Unit> continuation) {
        Object collect = FlowKt.conflate(FlowExtKt.simpleRunningReduce(FlowExtKt.simpleTransformLatest(flow, new PageFetcherSnapshot$collectAsGenerationalViewportHints$$inlined$simpleFlatMapLatest$1(null, this, loadType)), new b(loadType, null))).collect(new FlowCollector<GenerationalViewportHint>() { // from class: androidx.paging.PageFetcherSnapshot$collectAsGenerationalViewportHints$$inlined$collect$1
            @Override // kotlinx.coroutines.flow.FlowCollector
            @Nullable
            public Object emit(GenerationalViewportHint generationalViewportHint, @NotNull Continuation<? super Unit> continuation2) {
                Object c2 = PageFetcherSnapshot.this.c(loadType, generationalViewportHint, continuation2);
                return c2 == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? c2 : Unit.INSTANCE;
            }
        }, continuation);
        return collect == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
    }

    public final void accessHint(@NotNull ViewportHint viewportHint) {
        Intrinsics.checkNotNullParameter(viewportHint, "viewportHint");
        this.i.processHint(viewportHint);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:102:0x027c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:103:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00fa A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x011c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0161 A[Catch: all -> 0x0237, TryCatch #5 {all -> 0x0237, blocks: (B:51:0x013d, B:53:0x0161, B:54:0x016e, B:56:0x0177), top: B:127:0x013d }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0177 A[Catch: all -> 0x0237, TRY_LEAVE, TryCatch #5 {all -> 0x0237, blocks: (B:51:0x013d, B:53:0x0161, B:54:0x016e, B:56:0x0177), top: B:127:0x013d }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01c2 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0228  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x023c  */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r2v2, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r2v30, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r2v42 */
    /* JADX WARN: Type inference failed for: r2v43 */
    /* JADX WARN: Type inference failed for: r2v44 */
    /* JADX WARN: Type inference failed for: r2v45 */
    /* JADX WARN: Type inference failed for: r2v9, types: [kotlinx.coroutines.sync.Mutex] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object b(kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            Method dump skipped, instructions count: 690
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PageFetcherSnapshot.b(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x043e  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0478  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x04c6 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:154:0x04c7  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x04f6  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x04f9  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0524 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0525  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x053e  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0542 A[Catch: all -> 0x0687, TRY_LEAVE, TryCatch #7 {all -> 0x0687, blocks: (B:174:0x0530, B:177:0x0542, B:181:0x0560), top: B:269:0x0530 }] */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0591 A[Catch: all -> 0x00bc, TryCatch #10 {all -> 0x00bc, blocks: (B:186:0x057a, B:188:0x0591, B:190:0x059d, B:192:0x05a5, B:194:0x05b2, B:193:0x05ac, B:195:0x05b5, B:199:0x05d7, B:203:0x05ea, B:185:0x0572, B:14:0x0086, B:17:0x00b7), top: B:267:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:192:0x05a5 A[Catch: all -> 0x00bc, TryCatch #10 {all -> 0x00bc, blocks: (B:186:0x057a, B:188:0x0591, B:190:0x059d, B:192:0x05a5, B:194:0x05b2, B:193:0x05ac, B:195:0x05b5, B:199:0x05d7, B:203:0x05ea, B:185:0x0572, B:14:0x0086, B:17:0x00b7), top: B:267:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:193:0x05ac A[Catch: all -> 0x00bc, TryCatch #10 {all -> 0x00bc, blocks: (B:186:0x057a, B:188:0x0591, B:190:0x059d, B:192:0x05a5, B:194:0x05b2, B:193:0x05ac, B:195:0x05b5, B:199:0x05d7, B:203:0x05ea, B:185:0x0572, B:14:0x0086, B:17:0x00b7), top: B:267:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:201:0x05df A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:202:0x05e0  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x065b  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x0666  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x069a A[Catch: all -> 0x06a0, TRY_ENTER, TryCatch #0 {all -> 0x06a0, blocks: (B:47:0x0222, B:72:0x02d3, B:54:0x0239, B:56:0x024a, B:57:0x0256, B:59:0x0260, B:63:0x027e, B:65:0x0297, B:68:0x02b5, B:249:0x069a, B:250:0x069f), top: B:256:0x0222 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x01f1  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0231  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0323  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0325 A[Catch: all -> 0x0692, TRY_LEAVE, TryCatch #2 {all -> 0x0692, blocks: (B:77:0x030a, B:80:0x0325), top: B:259:0x030a }] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0355  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0387  */
    /* JADX WARN: Type inference failed for: r12v41, types: [androidx.paging.PageFetcherSnapshot, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v18, types: [T, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15, types: [T] */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v6, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r5v0, types: [int] */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v44, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v51 */
    /* JADX WARN: Type inference failed for: r5v83 */
    /* JADX WARN: Type inference failed for: r7v34, types: [java.lang.Object] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:223:0x0641 -> B:265:0x0647). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:234:0x067a -> B:86:0x0351). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object c(androidx.paging.LoadType r18, androidx.paging.GenerationalViewportHint r19, kotlin.coroutines.Continuation<? super kotlin.Unit> r20) {
        /*
            Method dump skipped, instructions count: 1740
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PageFetcherSnapshot.c(androidx.paging.LoadType, androidx.paging.GenerationalViewportHint, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void close() {
        Job.DefaultImpls.cancel$default((Job) this.m, (CancellationException) null, 1, (Object) null);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003e  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object currentPagingState(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super androidx.paging.PagingState<Key, Value>> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof androidx.paging.PageFetcherSnapshot.c
            if (r0 == 0) goto L13
            r0 = r6
            androidx.paging.PageFetcherSnapshot$c r0 = (androidx.paging.PageFetcherSnapshot.c) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.paging.PageFetcherSnapshot$c r0 = new androidx.paging.PageFetcherSnapshot$c
            r0.<init>(r5, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L3e
            if (r2 != r4) goto L36
            java.lang.Object r1 = r0.L$2
            kotlinx.coroutines.sync.Mutex r1 = (kotlinx.coroutines.sync.Mutex) r1
            java.lang.Object r2 = r0.L$1
            androidx.paging.PageFetcherSnapshotState$Holder r2 = (androidx.paging.PageFetcherSnapshotState.Holder) r2
            java.lang.Object r0 = r0.L$0
            androidx.paging.PageFetcherSnapshot r0 = (androidx.paging.PageFetcherSnapshot) r0
            kotlin.ResultKt.throwOnFailure(r6)
            goto L58
        L36:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L3e:
            kotlin.ResultKt.throwOnFailure(r6)
            androidx.paging.PageFetcherSnapshotState$Holder<Key, Value> r2 = r5.l
            kotlinx.coroutines.sync.Mutex r6 = androidx.paging.PageFetcherSnapshotState.Holder.access$getLock$p(r2)
            r0.L$0 = r5
            r0.L$1 = r2
            r0.L$2 = r6
            r0.label = r4
            java.lang.Object r0 = r6.lock(r3, r0)
            if (r0 != r1) goto L56
            return r1
        L56:
            r0 = r5
            r1 = r6
        L58:
            androidx.paging.PageFetcherSnapshotState r6 = androidx.paging.PageFetcherSnapshotState.Holder.access$getState$p(r2)     // Catch: java.lang.Throwable -> L6a
            androidx.paging.HintHandler r0 = r0.i     // Catch: java.lang.Throwable -> L6a
            androidx.paging.ViewportHint$Access r0 = r0.getLastAccessHint()     // Catch: java.lang.Throwable -> L6a
            androidx.paging.PagingState r6 = r6.currentPagingState$paging_common(r0)     // Catch: java.lang.Throwable -> L6a
            r1.unlock(r3)
            return r6
        L6a:
            r6 = move-exception
            r1.unlock(r3)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PageFetcherSnapshot.currentPagingState(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final PagingSource.LoadParams<Key> d(LoadType loadType, Key key) {
        return PagingSource.LoadParams.Companion.create(loadType, key, loadType == LoadType.REFRESH ? this.c.initialLoadSize : this.c.pageSize, this.c.enablePlaceholders);
    }

    public final Key e(PageFetcherSnapshotState<Key, Value> pageFetcherSnapshotState, LoadType loadType, int i, int i2) {
        if (i == pageFetcherSnapshotState.generationId$paging_common(loadType) && !(pageFetcherSnapshotState.getSourceLoadStates$paging_common().get(loadType) instanceof LoadState.Error) && i2 < this.c.prefetchDistance) {
            if (loadType == LoadType.PREPEND) {
                return (Key) ((PagingSource.LoadResult.Page) CollectionsKt___CollectionsKt.first((List<? extends Object>) pageFetcherSnapshotState.getPages$paging_common())).getPrevKey();
            }
            return (Key) ((PagingSource.LoadResult.Page) CollectionsKt___CollectionsKt.last((List<? extends Object>) pageFetcherSnapshotState.getPages$paging_common())).getNextKey();
        }
        return null;
    }

    public final void f() {
        close();
        this.b.invalidate();
    }

    public final Object g(LoadType loadType, ViewportHint viewportHint, Continuation<? super Unit> continuation) {
        if (WhenMappings.$EnumSwitchMapping$0[loadType.ordinal()] == 1) {
            Object b2 = b(continuation);
            return b2 == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? b2 : Unit.INSTANCE;
        }
        if (viewportHint != null) {
            this.i.forceSetHint(loadType, viewportHint);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("Cannot retry APPEND / PREPEND load on PagingSource without ViewportHint".toString());
    }

    @Nullable
    public final Key getInitialKey$paging_common() {
        return this.f1517a;
    }

    @NotNull
    public final Flow<PageEvent<Value>> getPageEventFlow() {
        return this.n;
    }

    @NotNull
    public final PagingSource<Key, Value> getPagingSource$paging_common() {
        return this.b;
    }

    @Nullable
    public final RemoteMediatorConnection<Key, Value> getRemoteMediatorConnection() {
        return this.f;
    }

    public final Object h(PageFetcherSnapshotState<Key, Value> pageFetcherSnapshotState, LoadType loadType, LoadState.Error error, Continuation<? super Unit> continuation) {
        if (!Intrinsics.areEqual(pageFetcherSnapshotState.getSourceLoadStates$paging_common().get(loadType), error)) {
            pageFetcherSnapshotState.getSourceLoadStates$paging_common().set(loadType, error);
            Object send = this.k.send(new PageEvent.LoadStateUpdate(pageFetcherSnapshotState.getSourceLoadStates$paging_common().snapshot(), null), continuation);
            return send == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? send : Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }

    public final Object i(PageFetcherSnapshotState<Key, Value> pageFetcherSnapshotState, LoadType loadType, Continuation<? super Unit> continuation) {
        LoadState loadState = pageFetcherSnapshotState.getSourceLoadStates$paging_common().get(loadType);
        LoadState.Loading loading = LoadState.Loading.INSTANCE;
        if (!Intrinsics.areEqual(loadState, loading)) {
            pageFetcherSnapshotState.getSourceLoadStates$paging_common().set(loadType, loading);
            Object send = this.k.send(new PageEvent.LoadStateUpdate(pageFetcherSnapshotState.getSourceLoadStates$paging_common().snapshot(), null), continuation);
            return send == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? send : Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }

    public final void j(CoroutineScope coroutineScope) {
        if (this.c.jumpThreshold != Integer.MIN_VALUE) {
            for (LoadType loadType : CollectionsKt__CollectionsKt.listOf((Object[]) new LoadType[]{LoadType.APPEND, LoadType.PREPEND})) {
                kotlinx.coroutines.e.e(coroutineScope, null, null, new PageFetcherSnapshot$startConsumingHints$1$1(this, loadType, null), 3, null);
            }
        }
        kotlinx.coroutines.e.e(coroutineScope, null, null, new g(this, null), 3, null);
        kotlinx.coroutines.e.e(coroutineScope, null, null, new h(this, null), 3, null);
    }

    public /* synthetic */ PageFetcherSnapshot(Object obj, PagingSource pagingSource, PagingConfig pagingConfig, Flow flow, boolean z, RemoteMediatorConnection remoteMediatorConnection, PagingState pagingState, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, pagingSource, pagingConfig, flow, (i & 16) != 0 ? false : z, (i & 32) != 0 ? null : remoteMediatorConnection, (i & 64) != 0 ? null : pagingState, (i & 128) != 0 ? a.INSTANCE : function0);
    }
}

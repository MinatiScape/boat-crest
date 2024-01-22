package androidx.paging;

import androidx.exifinterface.media.ExifInterface;
import androidx.paging.LoadState;
import androidx.paging.PagedList;
import androidx.paging.PagingSource;
import com.clevertap.android.sdk.Constants;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0003*\u00020\u00012\u00020\u0001:\u000212BW\u0012\u0006\u0010)\u001a\u00020(\u0012\u0006\u0010\u000e\u001a\u00020\t\u0012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000f\u0012\u0006\u0010+\u001a\u00020*\u0012\u0006\u0010,\u001a\u00020*\u0012\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00010\u0015\u0012\f\u0010.\u001a\b\u0012\u0004\u0012\u00028\u00000-¢\u0006\u0004\b/\u00100J\u0006\u0010\u0005\u001a\u00020\u0004J\u0006\u0010\u0006\u001a\u00020\u0004J\u0006\u0010\u0007\u001a\u00020\u0004J\u0006\u0010\b\u001a\u00020\u0004R\u0019\u0010\u000e\u001a\u00020\t8\u0006@\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR%\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000f8\u0006@\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00010\u00158\u0006@\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R(\u0010$\u001a\u00020\u001b8\u0006@\u0006X\u0086\u000e¢\u0006\u0018\n\u0004\b\u001c\u0010\u001d\u0012\u0004\b\"\u0010#\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u0013\u0010&\u001a\u00020%8F@\u0006¢\u0006\u0006\u001a\u0004\b&\u0010'¨\u00063"}, d2 = {"Landroidx/paging/LegacyPageFetcher;", "", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "", "trySchedulePrepend", "tryScheduleAppend", "retry", "detach", "Landroidx/paging/PagedList$Config;", "b", "Landroidx/paging/PagedList$Config;", "getConfig", "()Landroidx/paging/PagedList$Config;", Constants.KEY_CONFIG, "Landroidx/paging/PagingSource;", com.google.android.material.color.c.f10260a, "Landroidx/paging/PagingSource;", "getSource", "()Landroidx/paging/PagingSource;", "source", "Landroidx/paging/LegacyPageFetcher$PageConsumer;", "f", "Landroidx/paging/LegacyPageFetcher$PageConsumer;", "getPageConsumer", "()Landroidx/paging/LegacyPageFetcher$PageConsumer;", "pageConsumer", "Landroidx/paging/PagedList$LoadStateManager;", "i", "Landroidx/paging/PagedList$LoadStateManager;", "getLoadStateManager", "()Landroidx/paging/PagedList$LoadStateManager;", "setLoadStateManager", "(Landroidx/paging/PagedList$LoadStateManager;)V", "getLoadStateManager$annotations", "()V", "loadStateManager", "", "isDetached", "()Z", "Lkotlinx/coroutines/CoroutineScope;", "pagedListScope", "Lkotlinx/coroutines/CoroutineDispatcher;", "notifyDispatcher", "fetchDispatcher", "Landroidx/paging/LegacyPageFetcher$KeyProvider;", "keyProvider", "<init>", "(Lkotlinx/coroutines/CoroutineScope;Landroidx/paging/PagedList$Config;Landroidx/paging/PagingSource;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Landroidx/paging/LegacyPageFetcher$PageConsumer;Landroidx/paging/LegacyPageFetcher$KeyProvider;)V", "KeyProvider", "PageConsumer", "paging-common"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class LegacyPageFetcher<K, V> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final CoroutineScope f1500a;
    @NotNull
    public final PagedList.Config b;
    @NotNull
    public final PagingSource<K, V> c;
    @NotNull
    public final CoroutineDispatcher d;
    @NotNull
    public final CoroutineDispatcher e;
    @NotNull
    public final PageConsumer<V> f;
    @NotNull
    public final KeyProvider<K> g;
    @NotNull
    public final AtomicBoolean h;
    @NotNull
    public PagedList.LoadStateManager i;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\b`\u0018\u0000*\b\b\u0002\u0010\u0002*\u00020\u00012\u00020\u0001R\u0018\u0010\u0005\u001a\u0004\u0018\u00018\u00028&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004R\u0018\u0010\u0007\u001a\u0004\u0018\u00018\u00028&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004¨\u0006\b"}, d2 = {"Landroidx/paging/LegacyPageFetcher$KeyProvider;", "", "K", "getPrevKey", "()Ljava/lang/Object;", "prevKey", "getNextKey", "nextKey", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public interface KeyProvider<K> {
        @Nullable
        K getNextKey();

        @Nullable
        K getPrevKey();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b`\u0018\u0000*\b\b\u0002\u0010\u0002*\u00020\u00012\u00020\u0001J\"\u0010\b\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00032\u0010\u0010\u0006\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00020\u0005H&J\u0018\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\tH&¨\u0006\r"}, d2 = {"Landroidx/paging/LegacyPageFetcher$PageConsumer;", "", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Landroidx/paging/LoadType;", "type", "Landroidx/paging/PagingSource$LoadResult$Page;", "page", "", "onPageResult", "Landroidx/paging/LoadState;", "state", "", "onStateChanged", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public interface PageConsumer<V> {
        boolean onPageResult(@NotNull LoadType loadType, @NotNull PagingSource.LoadResult.Page<?, V> page);

        void onStateChanged(@NotNull LoadType loadType, @NotNull LoadState loadState);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LoadType.values().length];
            iArr[LoadType.PREPEND.ordinal()] = 1;
            iArr[LoadType.APPEND.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @DebugMetadata(c = "androidx.paging.LegacyPageFetcher$scheduleLoad$1", f = "LegacyPageFetcher.kt", i = {0}, l = {53}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
    /* loaded from: classes.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ PagingSource.LoadParams<K> $params;
        public final /* synthetic */ LoadType $type;
        private /* synthetic */ Object L$0;
        public int label;
        public final /* synthetic */ LegacyPageFetcher<K, V> this$0;

        @DebugMetadata(c = "androidx.paging.LegacyPageFetcher$scheduleLoad$1$1", f = "LegacyPageFetcher.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: androidx.paging.LegacyPageFetcher$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0160a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ LoadType $type;
            public final /* synthetic */ PagingSource.LoadResult<K, V> $value;
            public int label;
            public final /* synthetic */ LegacyPageFetcher<K, V> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0160a(PagingSource.LoadResult<K, V> loadResult, LegacyPageFetcher<K, V> legacyPageFetcher, LoadType loadType, Continuation<? super C0160a> continuation) {
                super(2, continuation);
                this.$value = loadResult;
                this.this$0 = legacyPageFetcher;
                this.$type = loadType;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new C0160a(this.$value, this.this$0, this.$type, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((C0160a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    PagingSource.LoadResult<K, V> loadResult = this.$value;
                    if (loadResult instanceof PagingSource.LoadResult.Page) {
                        this.this$0.c(this.$type, (PagingSource.LoadResult.Page) loadResult);
                    } else if (loadResult instanceof PagingSource.LoadResult.Error) {
                        this.this$0.a(this.$type, ((PagingSource.LoadResult.Error) loadResult).getThrowable());
                    } else if (loadResult instanceof PagingSource.LoadResult.Invalid) {
                        this.this$0.b();
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(LegacyPageFetcher<K, V> legacyPageFetcher, PagingSource.LoadParams<K> loadParams, LoadType loadType, Continuation<? super a> continuation) {
            super(2, continuation);
            this.this$0 = legacyPageFetcher;
            this.$params = loadParams;
            this.$type = loadType;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            a aVar = new a(this.this$0, this.$params, this.$type, continuation);
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
            CoroutineScope coroutineScope;
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
                PagingSource<K, V> source = this.this$0.getSource();
                PagingSource.LoadParams<K> loadParams = this.$params;
                this.L$0 = coroutineScope2;
                this.label = 1;
                Object load = source.load(loadParams, this);
                if (load == coroutine_suspended) {
                    return coroutine_suspended;
                }
                coroutineScope = coroutineScope2;
                obj = load;
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            PagingSource.LoadResult loadResult = (PagingSource.LoadResult) obj;
            if (!this.this$0.getSource().getInvalid()) {
                e.e(coroutineScope, this.this$0.d, null, new C0160a(loadResult, this.this$0, this.$type, null), 2, null);
                return Unit.INSTANCE;
            }
            this.this$0.detach();
            return Unit.INSTANCE;
        }
    }

    public LegacyPageFetcher(@NotNull CoroutineScope pagedListScope, @NotNull PagedList.Config config, @NotNull PagingSource<K, V> source, @NotNull CoroutineDispatcher notifyDispatcher, @NotNull CoroutineDispatcher fetchDispatcher, @NotNull PageConsumer<V> pageConsumer, @NotNull KeyProvider<K> keyProvider) {
        Intrinsics.checkNotNullParameter(pagedListScope, "pagedListScope");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(notifyDispatcher, "notifyDispatcher");
        Intrinsics.checkNotNullParameter(fetchDispatcher, "fetchDispatcher");
        Intrinsics.checkNotNullParameter(pageConsumer, "pageConsumer");
        Intrinsics.checkNotNullParameter(keyProvider, "keyProvider");
        this.f1500a = pagedListScope;
        this.b = config;
        this.c = source;
        this.d = notifyDispatcher;
        this.e = fetchDispatcher;
        this.f = pageConsumer;
        this.g = keyProvider;
        this.h = new AtomicBoolean(false);
        this.i = new PagedList.LoadStateManager(this) { // from class: androidx.paging.LegacyPageFetcher$loadStateManager$1
            public final /* synthetic */ LegacyPageFetcher<K, V> d;

            {
                this.d = this;
            }

            @Override // androidx.paging.PagedList.LoadStateManager
            public void onStateChanged(@NotNull LoadType type, @NotNull LoadState state) {
                Intrinsics.checkNotNullParameter(type, "type");
                Intrinsics.checkNotNullParameter(state, "state");
                this.d.getPageConsumer().onStateChanged(type, state);
            }
        };
    }

    public static /* synthetic */ void getLoadStateManager$annotations() {
    }

    public final void a(LoadType loadType, Throwable th) {
        if (isDetached()) {
            return;
        }
        this.i.setState(loadType, new LoadState.Error(th));
    }

    public final void b() {
        this.c.invalidate();
        detach();
    }

    public final void c(LoadType loadType, PagingSource.LoadResult.Page<K, V> page) {
        if (isDetached()) {
            return;
        }
        if (this.f.onPageResult(loadType, page)) {
            int i = WhenMappings.$EnumSwitchMapping$0[loadType.ordinal()];
            if (i == 1) {
                f();
                return;
            } else if (i == 2) {
                d();
                return;
            } else {
                throw new IllegalStateException("Can only fetch more during append/prepend");
            }
        }
        this.i.setState(loadType, page.getData().isEmpty() ? LoadState.NotLoading.Companion.getComplete$paging_common() : LoadState.NotLoading.Companion.getIncomplete$paging_common());
    }

    public final void d() {
        K nextKey = this.g.getNextKey();
        if (nextKey == null) {
            c(LoadType.APPEND, PagingSource.LoadResult.Page.Companion.empty$paging_common());
            return;
        }
        PagedList.LoadStateManager loadStateManager = this.i;
        LoadType loadType = LoadType.APPEND;
        loadStateManager.setState(loadType, LoadState.Loading.INSTANCE);
        PagedList.Config config = this.b;
        e(loadType, new PagingSource.LoadParams.Append(nextKey, config.pageSize, config.enablePlaceholders));
    }

    public final void detach() {
        this.h.set(true);
    }

    public final void e(LoadType loadType, PagingSource.LoadParams<K> loadParams) {
        e.e(this.f1500a, this.e, null, new a(this, loadParams, loadType, null), 2, null);
    }

    public final void f() {
        K prevKey = this.g.getPrevKey();
        if (prevKey == null) {
            c(LoadType.PREPEND, PagingSource.LoadResult.Page.Companion.empty$paging_common());
            return;
        }
        PagedList.LoadStateManager loadStateManager = this.i;
        LoadType loadType = LoadType.PREPEND;
        loadStateManager.setState(loadType, LoadState.Loading.INSTANCE);
        PagedList.Config config = this.b;
        e(loadType, new PagingSource.LoadParams.Prepend(prevKey, config.pageSize, config.enablePlaceholders));
    }

    @NotNull
    public final PagedList.Config getConfig() {
        return this.b;
    }

    @NotNull
    public final PagedList.LoadStateManager getLoadStateManager() {
        return this.i;
    }

    @NotNull
    public final PageConsumer<V> getPageConsumer() {
        return this.f;
    }

    @NotNull
    public final PagingSource<K, V> getSource() {
        return this.c;
    }

    public final boolean isDetached() {
        return this.h.get();
    }

    public final void retry() {
        if (this.i.getStartState() instanceof LoadState.Error) {
            f();
        }
        if (this.i.getEndState() instanceof LoadState.Error) {
            d();
        }
    }

    public final void setLoadStateManager(@NotNull PagedList.LoadStateManager loadStateManager) {
        Intrinsics.checkNotNullParameter(loadStateManager, "<set-?>");
        this.i = loadStateManager;
    }

    public final void tryScheduleAppend() {
        LoadState endState = this.i.getEndState();
        if (!(endState instanceof LoadState.NotLoading) || endState.getEndOfPaginationReached()) {
            return;
        }
        d();
    }

    public final void trySchedulePrepend() {
        LoadState startState = this.i.getStartState();
        if (!(startState instanceof LoadState.NotLoading) || startState.getEndOfPaginationReached()) {
            return;
        }
        f();
    }
}

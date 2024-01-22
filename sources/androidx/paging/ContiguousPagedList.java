package androidx.paging;

import androidx.annotation.AnyThread;
import androidx.annotation.MainThread;
import androidx.annotation.RestrictTo;
import androidx.exifinterface.media.ExifInterface;
import androidx.paging.LegacyPageFetcher;
import androidx.paging.LoadState;
import androidx.paging.PagedList;
import androidx.paging.PagedStorage;
import androidx.paging.PagingSource;
import com.clevertap.android.sdk.Constants;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0017\u0018\u0000 K*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0003*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00010\u00042\u00020\u00052\b\u0012\u0004\u0012\u00028\u00010\u0006:\u0001KBi\u0012\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010-\u0012\u0006\u0010A\u001a\u00020@\u0012\u0006\u0010C\u001a\u00020B\u0012\u0006\u0010D\u001a\u00020B\u0012\u000e\u00108\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u000103\u0012\u0006\u0010F\u001a\u00020E\u0012\u0012\u0010G\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t\u0012\b\u0010H\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\bI\u0010JJ\"\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00072\u0010\u0010\n\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00010\tH\u0016J\u0018\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\rH\u0016J'\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u000bH\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\b\u0010\u0017\u001a\u00020\u000fH\u0016J\"\u0010\u001a\u001a\u00020\u000f2\u0018\u0010\u0019\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000f0\u0018H\u0016J\u0018\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\rH\u0016J\u0010\u0010 \u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020\u001eH\u0017J\b\u0010!\u001a\u00020\u000fH\u0016J\u0010\u0010#\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020\u001eH\u0017J \u0010'\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020\u001eH\u0017J \u0010)\u001a\u00020\u000f2\u0006\u0010(\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020\u001eH\u0017J\u0018\u0010+\u001a\u00020\u000f2\u0006\u0010*\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\u001eH\u0016J\u0018\u0010,\u001a\u00020\u000f2\u0006\u0010*\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\u001eH\u0016R%\u00102\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010-8\u0006@\u0006¢\u0006\f\n\u0004\b.\u0010/\u001a\u0004\b0\u00101R$\u00108\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u0001038\u0000@\u0000X\u0080\u0004¢\u0006\f\n\u0004\b4\u00105\u001a\u0004\b6\u00107R\u001e\u0010=\u001a\u0004\u0018\u00018\u00008V@\u0016X\u0096\u0004¢\u0006\f\u0012\u0004\b;\u0010<\u001a\u0004\b9\u0010:R\u0016\u0010>\u001a\u00020\u000b8V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b>\u0010?¨\u0006L"}, d2 = {"Landroidx/paging/ContiguousPagedList;", "", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Landroidx/paging/PagedList;", "Landroidx/paging/PagedStorage$Callback;", "Landroidx/paging/LegacyPageFetcher$PageConsumer;", "Landroidx/paging/LoadType;", "type", "Landroidx/paging/PagingSource$LoadResult$Page;", "page", "", "onPageResult", "Landroidx/paging/LoadState;", "state", "", "onStateChanged", "deferEmpty", "deferBegin", "deferEnd", "deferBoundaryCallbacks$paging_common", "(ZZZ)V", "deferBoundaryCallbacks", "retry", "Lkotlin/Function2;", "callback", "dispatchCurrentLoadState", "loadType", "loadState", "setInitialLoadState", "", FirebaseAnalytics.Param.INDEX, "loadAroundInternal", "detach", "count", "onInitialized", "leadingNulls", "changed", "added", "onPagePrepended", "endPosition", "onPageAppended", "startOfDrops", "onPagesRemoved", "onPagesSwappedToPlaceholder", "Landroidx/paging/PagingSource;", RsaJsonWebKey.SECOND_PRIME_FACTOR_MEMBER_NAME, "Landroidx/paging/PagingSource;", "getPagingSource", "()Landroidx/paging/PagingSource;", "pagingSource", "Landroidx/paging/PagedList$BoundaryCallback;", RsaJsonWebKey.PRIME_FACTOR_OTHER_MEMBER_NAME, "Landroidx/paging/PagedList$BoundaryCallback;", "getBoundaryCallback$paging_common", "()Landroidx/paging/PagedList$BoundaryCallback;", "boundaryCallback", "getLastKey", "()Ljava/lang/Object;", "getLastKey$annotations", "()V", "lastKey", "isDetached", "()Z", "Lkotlinx/coroutines/CoroutineScope;", "coroutineScope", "Lkotlinx/coroutines/CoroutineDispatcher;", "notifyDispatcher", "backgroundDispatcher", "Landroidx/paging/PagedList$Config;", Constants.KEY_CONFIG, "initialPage", "initialLastKey", "<init>", "(Landroidx/paging/PagingSource;Lkotlinx/coroutines/CoroutineScope;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Landroidx/paging/PagedList$BoundaryCallback;Landroidx/paging/PagedList$Config;Landroidx/paging/PagingSource$LoadResult$Page;Ljava/lang/Object;)V", "Companion", "paging-common"}, k = 1, mv = {1, 5, 1})
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class ContiguousPagedList<K, V> extends PagedList<V> implements PagedStorage.Callback, LegacyPageFetcher.PageConsumer<V> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public final boolean A;
    @NotNull
    public final LegacyPageFetcher<K, V> B;
    @NotNull
    public final PagingSource<K, V> q;
    @Nullable
    public final PagedList.BoundaryCallback<V> r;
    @Nullable
    public final K s;
    public int t;
    public int u;
    public boolean v;
    public boolean w;
    public int x;
    public int y;
    public boolean z;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\f\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\f\u0010\rJ'\u0010\b\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0006\u0010\u0007J'\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\n\u0010\u0007¨\u0006\u000e"}, d2 = {"Landroidx/paging/ContiguousPagedList$Companion;", "", "", "prefetchDistance", FirebaseAnalytics.Param.INDEX, "leadingNulls", "getPrependItemsRequested$paging_common", "(III)I", "getPrependItemsRequested", "itemsBeforeTrailingNulls", "getAppendItemsRequested$paging_common", "getAppendItemsRequested", "<init>", "()V", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int getAppendItemsRequested$paging_common(int i, int i2, int i3) {
            return ((i2 + i) + 1) - i3;
        }

        public final int getPrependItemsRequested$paging_common(int i, int i2, int i3) {
            return i - (i2 - i3);
        }
    }

    @DebugMetadata(c = "androidx.paging.ContiguousPagedList$deferBoundaryCallbacks$1", f = "ContiguousPagedList.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ boolean $deferBegin;
        public final /* synthetic */ boolean $deferEmpty;
        public final /* synthetic */ boolean $deferEnd;
        public int label;
        public final /* synthetic */ ContiguousPagedList<K, V> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(boolean z, ContiguousPagedList<K, V> contiguousPagedList, boolean z2, boolean z3, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$deferEmpty = z;
            this.this$0 = contiguousPagedList;
            this.$deferBegin = z2;
            this.$deferEnd = z3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$deferEmpty, this.this$0, this.$deferBegin, this.$deferEnd, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$deferEmpty) {
                    this.this$0.getBoundaryCallback$paging_common().onZeroItemsLoaded();
                }
                if (this.$deferBegin) {
                    this.this$0.v = true;
                }
                if (this.$deferEnd) {
                    this.this$0.w = true;
                }
                this.this$0.c(false);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "androidx.paging.ContiguousPagedList$tryDispatchBoundaryCallbacks$1", f = "ContiguousPagedList.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ boolean $dispatchBegin;
        public final /* synthetic */ boolean $dispatchEnd;
        public int label;
        public final /* synthetic */ ContiguousPagedList<K, V> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(ContiguousPagedList<K, V> contiguousPagedList, boolean z, boolean z2, Continuation<? super b> continuation) {
            super(2, continuation);
            this.this$0 = contiguousPagedList;
            this.$dispatchBegin = z;
            this.$dispatchEnd = z2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(this.this$0, this.$dispatchBegin, this.$dispatchEnd, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                this.this$0.a(this.$dispatchBegin, this.$dispatchEnd);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ContiguousPagedList(@NotNull PagingSource<K, V> pagingSource, @NotNull CoroutineScope coroutineScope, @NotNull CoroutineDispatcher notifyDispatcher, @NotNull CoroutineDispatcher backgroundDispatcher, @Nullable PagedList.BoundaryCallback<V> boundaryCallback, @NotNull PagedList.Config config, @NotNull PagingSource.LoadResult.Page<K, V> initialPage, @Nullable K k) {
        super(pagingSource, coroutineScope, notifyDispatcher, new PagedStorage(), config);
        Intrinsics.checkNotNullParameter(pagingSource, "pagingSource");
        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
        Intrinsics.checkNotNullParameter(notifyDispatcher, "notifyDispatcher");
        Intrinsics.checkNotNullParameter(backgroundDispatcher, "backgroundDispatcher");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(initialPage, "initialPage");
        this.q = pagingSource;
        this.r = boundaryCallback;
        this.s = k;
        this.x = Integer.MAX_VALUE;
        this.y = Integer.MIN_VALUE;
        this.A = config.maxSize != Integer.MAX_VALUE;
        this.B = new LegacyPageFetcher<>(coroutineScope, config, pagingSource, notifyDispatcher, backgroundDispatcher, this, getStorage$paging_common());
        if (config.enablePlaceholders) {
            getStorage$paging_common().init(initialPage.getItemsBefore() != Integer.MIN_VALUE ? initialPage.getItemsBefore() : 0, initialPage, initialPage.getItemsAfter() != Integer.MIN_VALUE ? initialPage.getItemsAfter() : 0, 0, this, (initialPage.getItemsBefore() == Integer.MIN_VALUE || initialPage.getItemsAfter() == Integer.MIN_VALUE) ? false : true);
        } else {
            getStorage$paging_common().init(0, initialPage, 0, initialPage.getItemsBefore() != Integer.MIN_VALUE ? initialPage.getItemsBefore() : 0, this, false);
        }
        b(LoadType.REFRESH, initialPage.getData());
    }

    public static /* synthetic */ void getLastKey$annotations() {
    }

    public final void a(boolean z, boolean z2) {
        if (z) {
            PagedList.BoundaryCallback<V> boundaryCallback = this.r;
            Intrinsics.checkNotNull(boundaryCallback);
            boundaryCallback.onItemAtFrontLoaded(getStorage$paging_common().getFirstLoadedItem$paging_common());
        }
        if (z2) {
            PagedList.BoundaryCallback<V> boundaryCallback2 = this.r;
            Intrinsics.checkNotNull(boundaryCallback2);
            boundaryCallback2.onItemAtEndLoaded(getStorage$paging_common().getLastLoadedItem$paging_common());
        }
    }

    public final void b(LoadType loadType, List<? extends V> list) {
        if (this.r != null) {
            boolean z = true;
            boolean z2 = getStorage$paging_common().size() == 0;
            boolean z3 = !z2 && loadType == LoadType.PREPEND && list.isEmpty();
            if (z2 || loadType != LoadType.APPEND || !list.isEmpty()) {
                z = false;
            }
            deferBoundaryCallbacks$paging_common(z2, z3, z);
        }
    }

    public final void c(boolean z) {
        boolean z2 = true;
        boolean z3 = this.v && this.x <= getConfig().prefetchDistance;
        if (!this.w || this.y < (size() - 1) - getConfig().prefetchDistance) {
            z2 = false;
        }
        if (z3 || z2) {
            if (z3) {
                this.v = false;
            }
            if (z2) {
                this.w = false;
            }
            if (z) {
                e.e(getCoroutineScope$paging_common(), getNotifyDispatcher$paging_common(), null, new b(this, z3, z2, null), 2, null);
            } else {
                a(z3, z2);
            }
        }
    }

    @AnyThread
    public final void deferBoundaryCallbacks$paging_common(boolean z, boolean z2, boolean z3) {
        if (this.r != null) {
            if (this.x == Integer.MAX_VALUE) {
                this.x = getStorage$paging_common().size();
            }
            if (this.y == Integer.MIN_VALUE) {
                this.y = 0;
            }
            if (z || z2 || z3) {
                e.e(getCoroutineScope$paging_common(), getNotifyDispatcher$paging_common(), null, new a(z, this, z2, z3, null), 2, null);
                return;
            }
            return;
        }
        throw new IllegalStateException("Can't defer BoundaryCallback, no instance");
    }

    @Override // androidx.paging.PagedList
    public void detach() {
        this.B.detach();
    }

    @Override // androidx.paging.PagedList
    public void dispatchCurrentLoadState(@NotNull Function2<? super LoadType, ? super LoadState, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.B.getLoadStateManager().dispatchCurrentLoadState(callback);
    }

    @Nullable
    public final PagedList.BoundaryCallback<V> getBoundaryCallback$paging_common() {
        return this.r;
    }

    @Override // androidx.paging.PagedList
    @Nullable
    public K getLastKey() {
        PagingState<K, V> refreshKeyInfo = getStorage$paging_common().getRefreshKeyInfo(getConfig());
        K refreshKey = refreshKeyInfo == null ? null : getPagingSource().getRefreshKey(refreshKeyInfo);
        return refreshKey == null ? this.s : refreshKey;
    }

    @Override // androidx.paging.PagedList
    @NotNull
    public final PagingSource<K, V> getPagingSource() {
        return this.q;
    }

    @Override // androidx.paging.PagedList
    public boolean isDetached() {
        return this.B.isDetached();
    }

    @Override // androidx.paging.PagedList
    @MainThread
    public void loadAroundInternal(int i) {
        Companion companion = Companion;
        int prependItemsRequested$paging_common = companion.getPrependItemsRequested$paging_common(getConfig().prefetchDistance, i, getStorage$paging_common().getPlaceholdersBefore());
        int appendItemsRequested$paging_common = companion.getAppendItemsRequested$paging_common(getConfig().prefetchDistance, i, getStorage$paging_common().getPlaceholdersBefore() + getStorage$paging_common().getStorageCount());
        int max = Math.max(prependItemsRequested$paging_common, this.t);
        this.t = max;
        if (max > 0) {
            this.B.trySchedulePrepend();
        }
        int max2 = Math.max(appendItemsRequested$paging_common, this.u);
        this.u = max2;
        if (max2 > 0) {
            this.B.tryScheduleAppend();
        }
        this.x = Math.min(this.x, i);
        this.y = Math.max(this.y, i);
        c(true);
    }

    @Override // androidx.paging.PagedStorage.Callback
    @MainThread
    public void onInitialized(int i) {
        boolean z = false;
        notifyInserted$paging_common(0, i);
        this.z = (getStorage$paging_common().getPlaceholdersBefore() > 0 || getStorage$paging_common().getPlaceholdersAfter() > 0) ? true : true;
    }

    @Override // androidx.paging.PagedStorage.Callback
    @MainThread
    public void onPageAppended(int i, int i2, int i3) {
        notifyChanged(i, i2);
        notifyInserted$paging_common(i + i2, i3);
    }

    @Override // androidx.paging.PagedStorage.Callback
    @MainThread
    public void onPagePrepended(int i, int i2, int i3) {
        notifyChanged(i, i2);
        notifyInserted$paging_common(0, i3);
        this.x += i3;
        this.y += i3;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0062, code lost:
        if ((!r0.isEmpty()) != false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0064, code lost:
        r3 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0088, code lost:
        if ((!r0.isEmpty()) != false) goto L34;
     */
    @Override // androidx.paging.LegacyPageFetcher.PageConsumer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onPageResult(@org.jetbrains.annotations.NotNull androidx.paging.LoadType r9, @org.jetbrains.annotations.NotNull androidx.paging.PagingSource.LoadResult.Page<?, V> r10) {
        /*
            Method dump skipped, instructions count: 266
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.ContiguousPagedList.onPageResult(androidx.paging.LoadType, androidx.paging.PagingSource$LoadResult$Page):boolean");
    }

    @Override // androidx.paging.PagedStorage.Callback
    public void onPagesRemoved(int i, int i2) {
        notifyRemoved(i, i2);
    }

    @Override // androidx.paging.PagedStorage.Callback
    public void onPagesSwappedToPlaceholder(int i, int i2) {
        notifyChanged(i, i2);
    }

    @Override // androidx.paging.LegacyPageFetcher.PageConsumer
    public void onStateChanged(@NotNull LoadType type, @NotNull LoadState state) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(state, "state");
        dispatchStateChangeAsync$paging_common(type, state);
    }

    @Override // androidx.paging.PagedList
    public void retry() {
        Runnable refreshRetryCallback$paging_common;
        super.retry();
        this.B.retry();
        if (!(this.B.getLoadStateManager().getRefreshState() instanceof LoadState.Error) || (refreshRetryCallback$paging_common = getRefreshRetryCallback$paging_common()) == null) {
            return;
        }
        refreshRetryCallback$paging_common.run();
    }

    @Override // androidx.paging.PagedList
    public void setInitialLoadState(@NotNull LoadType loadType, @NotNull LoadState loadState) {
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        Intrinsics.checkNotNullParameter(loadState, "loadState");
        this.B.getLoadStateManager().setState(loadType, loadState);
    }
}

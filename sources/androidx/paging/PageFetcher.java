package androidx.paging;

import androidx.annotation.VisibleForTesting;
import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0003*\u00020\u00012\u00020\u0001:\u0002\u0018\u0019B^\u0012(\u0010\u0010\u001a$\b\u0001\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000f0\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00010\r\u0012\b\u0010\u0011\u001a\u0004\u0018\u00018\u0000\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\u0016\b\u0002\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0014ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017J\u0006\u0010\u0005\u001a\u00020\u0004R%\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00070\u00068\u0006@\u0006¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, d2 = {"Landroidx/paging/PageFetcher;", "", "Key", "Value", "", "refresh", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/PagingData;", "f", "Lkotlinx/coroutines/flow/Flow;", "getFlow", "()Lkotlinx/coroutines/flow/Flow;", "flow", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "Landroidx/paging/PagingSource;", "pagingSourceFactory", "initialKey", "Landroidx/paging/PagingConfig;", Constants.KEY_CONFIG, "Landroidx/paging/RemoteMediator;", "remoteMediator", "<init>", "(Lkotlin/jvm/functions/Function1;Ljava/lang/Object;Landroidx/paging/PagingConfig;Landroidx/paging/RemoteMediator;)V", "a", "PagerUiReceiver", "paging-common"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class PageFetcher<Key, Value> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Function1<Continuation<? super PagingSource<Key, Value>>, Object> f1514a;
    @Nullable
    public final Key b;
    @NotNull
    public final PagingConfig c;
    @NotNull
    public final ConflatedEventBus<Boolean> d;
    @NotNull
    public final ConflatedEventBus<Unit> e;
    @NotNull
    public final Flow<PagingData<Value>> f;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0004\u0018\u0000*\b\b\u0002\u0010\u0002*\u00020\u0001*\b\b\u0003\u0010\u0003*\u00020\u00012\u00020\u0004B+\u0012\u0014\b\u0001\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u000b\u0012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u0011¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\t\u001a\u00020\u0007H\u0016J\b\u0010\n\u001a\u00020\u0007H\u0016R(\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u000b8\u0000@\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0015"}, d2 = {"Landroidx/paging/PageFetcher$PagerUiReceiver;", "", "Key", "Value", "Landroidx/paging/UiReceiver;", "Landroidx/paging/ViewportHint;", "viewportHint", "", "accessHint", "retry", "refresh", "Landroidx/paging/PageFetcherSnapshot;", "a", "Landroidx/paging/PageFetcherSnapshot;", "getPageFetcherSnapshot$paging_common", "()Landroidx/paging/PageFetcherSnapshot;", "pageFetcherSnapshot", "Landroidx/paging/ConflatedEventBus;", "retryEventBus", "<init>", "(Landroidx/paging/PageFetcher;Landroidx/paging/PageFetcherSnapshot;Landroidx/paging/ConflatedEventBus;)V", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public final class PagerUiReceiver<Key, Value> implements UiReceiver {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final PageFetcherSnapshot<Key, Value> f1515a;
        @NotNull
        public final ConflatedEventBus<Unit> b;
        public final /* synthetic */ PageFetcher<Key, Value> c;

        public PagerUiReceiver(@VisibleForTesting(otherwise = 2) @NotNull PageFetcher this$0, @NotNull PageFetcherSnapshot<Key, Value> pageFetcherSnapshot, ConflatedEventBus<Unit> retryEventBus) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(pageFetcherSnapshot, "pageFetcherSnapshot");
            Intrinsics.checkNotNullParameter(retryEventBus, "retryEventBus");
            this.c = this$0;
            this.f1515a = pageFetcherSnapshot;
            this.b = retryEventBus;
        }

        @Override // androidx.paging.UiReceiver
        public void accessHint(@NotNull ViewportHint viewportHint) {
            Intrinsics.checkNotNullParameter(viewportHint, "viewportHint");
            this.f1515a.accessHint(viewportHint);
        }

        @NotNull
        public final PageFetcherSnapshot<Key, Value> getPageFetcherSnapshot$paging_common() {
            return this.f1515a;
        }

        @Override // androidx.paging.UiReceiver
        public void refresh() {
            this.c.refresh();
        }

        @Override // androidx.paging.UiReceiver
        public void retry() {
            this.b.send(Unit.INSTANCE);
        }
    }

    /* loaded from: classes.dex */
    public static final class a<Key, Value> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final PageFetcherSnapshot<Key, Value> f1516a;
        @Nullable
        public final PagingState<Key, Value> b;
        @NotNull
        public final Job c;

        public a(@NotNull PageFetcherSnapshot<Key, Value> snapshot, @Nullable PagingState<Key, Value> pagingState, @NotNull Job job) {
            Intrinsics.checkNotNullParameter(snapshot, "snapshot");
            Intrinsics.checkNotNullParameter(job, "job");
            this.f1516a = snapshot;
            this.b = pagingState;
            this.c = job;
        }

        @NotNull
        public final Job a() {
            return this.c;
        }

        @NotNull
        public final PageFetcherSnapshot<Key, Value> b() {
            return this.f1516a;
        }

        @Nullable
        public final PagingState<Key, Value> c() {
            return this.b;
        }
    }

    @DebugMetadata(c = "androidx.paging.PageFetcher", f = "PageFetcher.kt", i = {0, 0}, l = {188}, m = "generateNewPagingSource", n = {"this", "previousPagingSource"}, s = {"L$0", "L$1"})
    /* loaded from: classes.dex */
    public static final class b extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
        public int label;
        public /* synthetic */ Object result;
        public final /* synthetic */ PageFetcher<Key, Value> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(PageFetcher<Key, Value> pageFetcher, Continuation<? super b> continuation) {
            super(continuation);
            this.this$0 = pageFetcher;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.a(null, this);
        }
    }

    /* loaded from: classes.dex */
    public /* synthetic */ class c extends FunctionReferenceImpl implements Function0<Unit> {
        public c(Object obj) {
            super(0, obj, PageFetcher.class, "invalidate", "invalidate()V", 0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2() {
            ((PageFetcher) this.receiver).c();
        }
    }

    /* loaded from: classes.dex */
    public /* synthetic */ class d extends FunctionReferenceImpl implements Function0<Unit> {
        public d(Object obj) {
            super(0, obj, PageFetcher.class, "invalidate", "invalidate()V", 0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2() {
            ((PageFetcher) this.receiver).c();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public PageFetcher(@NotNull Function1<? super Continuation<? super PagingSource<Key, Value>>, ? extends Object> pagingSourceFactory, @Nullable Key key, @NotNull PagingConfig config, @Nullable RemoteMediator<Key, Value> remoteMediator) {
        Intrinsics.checkNotNullParameter(pagingSourceFactory, "pagingSourceFactory");
        Intrinsics.checkNotNullParameter(config, "config");
        this.f1514a = pagingSourceFactory;
        this.b = key;
        this.c = config;
        this.d = new ConflatedEventBus<>(null, 1, null);
        this.e = new ConflatedEventBus<>(null, 1, null);
        this.f = SimpleChannelFlowKt.simpleChannelFlow(new PageFetcher$flow$1(remoteMediator, this, null));
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x007c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object a(androidx.paging.PagingSource<Key, Value> r5, kotlin.coroutines.Continuation<? super androidx.paging.PagingSource<Key, Value>> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof androidx.paging.PageFetcher.b
            if (r0 == 0) goto L13
            r0 = r6
            androidx.paging.PageFetcher$b r0 = (androidx.paging.PageFetcher.b) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.paging.PageFetcher$b r0 = new androidx.paging.PageFetcher$b
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L39
            if (r2 != r3) goto L31
            java.lang.Object r5 = r0.L$1
            androidx.paging.PagingSource r5 = (androidx.paging.PagingSource) r5
            java.lang.Object r0 = r0.L$0
            androidx.paging.PageFetcher r0 = (androidx.paging.PageFetcher) r0
            kotlin.ResultKt.throwOnFailure(r6)
            goto L4c
        L31:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L39:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlin.jvm.functions.Function1<kotlin.coroutines.Continuation<? super androidx.paging.PagingSource<Key, Value>>, java.lang.Object> r6 = r4.f1514a
            r0.L$0 = r4
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r6 = r6.invoke(r0)
            if (r6 != r1) goto L4b
            return r1
        L4b:
            r0 = r4
        L4c:
            androidx.paging.PagingSource r6 = (androidx.paging.PagingSource) r6
            boolean r1 = r6 instanceof androidx.paging.LegacyPagingSource
            if (r1 == 0) goto L5c
            r1 = r6
            androidx.paging.LegacyPagingSource r1 = (androidx.paging.LegacyPagingSource) r1
            androidx.paging.PagingConfig r2 = r0.c
            int r2 = r2.pageSize
            r1.setPageSize(r2)
        L5c:
            if (r6 == r5) goto L5f
            goto L60
        L5f:
            r3 = 0
        L60:
            if (r3 == 0) goto L7c
            androidx.paging.PageFetcher$c r1 = new androidx.paging.PageFetcher$c
            r1.<init>(r0)
            r6.registerInvalidatedCallback(r1)
            if (r5 != 0) goto L6d
            goto L75
        L6d:
            androidx.paging.PageFetcher$d r1 = new androidx.paging.PageFetcher$d
            r1.<init>(r0)
            r5.unregisterInvalidatedCallback(r1)
        L75:
            if (r5 != 0) goto L78
            goto L7b
        L78:
            r5.invalidate()
        L7b:
            return r6
        L7c:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "An instance of PagingSource was re-used when Pager expected to create a new\ninstance. Ensure that the pagingSourceFactory passed to Pager always returns a\nnew instance of PagingSource."
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PageFetcher.a(androidx.paging.PagingSource, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Flow<PageEvent<Value>> b(PageFetcherSnapshot<Key, Value> pageFetcherSnapshot, Job job, RemoteMediatorAccessor<Key, Value> remoteMediatorAccessor) {
        if (remoteMediatorAccessor == null) {
            return pageFetcherSnapshot.getPageEventFlow();
        }
        return CancelableChannelFlowKt.cancelableChannelFlow(job, new PageFetcher$injectRemoteEvents$1(remoteMediatorAccessor, pageFetcherSnapshot, new MutableLoadStateCollection(), null));
    }

    public final void c() {
        this.d.send(Boolean.FALSE);
    }

    @NotNull
    public final Flow<PagingData<Value>> getFlow() {
        return this.f;
    }

    public final void refresh() {
        this.d.send(Boolean.TRUE);
    }

    public /* synthetic */ PageFetcher(Function1 function1, Object obj, PagingConfig pagingConfig, RemoteMediator remoteMediator, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(function1, obj, pagingConfig, (i & 8) != 0 ? null : remoteMediator);
    }
}

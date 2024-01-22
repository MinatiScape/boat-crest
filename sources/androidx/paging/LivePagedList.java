package androidx.paging;

import androidx.lifecycle.LiveData;
import androidx.paging.LoadState;
import androidx.paging.PagedList;
import com.clevertap.android.sdk.Constants;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0003*\u00020\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00050\u0004B[\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\u0010\n\u001a\u0004\u0018\u00018\u0000\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\r\u0012\u0018\u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00100\u000f\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010\u0014\u001a\u00020\u0012¢\u0006\u0004\b\u0015\u0010\u0016J\b\u0010\u0007\u001a\u00020\u0006H\u0014¨\u0006\u0017"}, d2 = {"Landroidx/paging/LivePagedList;", "", "Key", "Value", "Landroidx/lifecycle/LiveData;", "Landroidx/paging/PagedList;", "", "onActive", "Lkotlinx/coroutines/CoroutineScope;", "coroutineScope", "initialKey", "Landroidx/paging/PagedList$Config;", Constants.KEY_CONFIG, "Landroidx/paging/PagedList$BoundaryCallback;", "boundaryCallback", "Lkotlin/Function0;", "Landroidx/paging/PagingSource;", "pagingSourceFactory", "Lkotlinx/coroutines/CoroutineDispatcher;", "notifyDispatcher", "fetchDispatcher", "<init>", "(Lkotlinx/coroutines/CoroutineScope;Ljava/lang/Object;Landroidx/paging/PagedList$Config;Landroidx/paging/PagedList$BoundaryCallback;Lkotlin/jvm/functions/Function0;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;)V", "paging-runtime_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class LivePagedList<Key, Value> extends LiveData<PagedList<Value>> {
    @NotNull
    public final CoroutineScope l;
    @NotNull
    public final PagedList.Config m;
    @Nullable
    public final PagedList.BoundaryCallback<Value> n;
    @NotNull
    public final Function0<PagingSource<Key, Value>> o;
    @NotNull
    public final CoroutineDispatcher p;
    @NotNull
    public final CoroutineDispatcher q;
    @NotNull
    public PagedList<Value> r;
    @Nullable
    public Job s;
    @NotNull
    public final Function0<Unit> t;
    @NotNull
    public final Runnable u;

    /* loaded from: classes.dex */
    public static final class a extends Lambda implements Function0<Unit> {
        public final /* synthetic */ LivePagedList<Key, Value> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(LivePagedList<Key, Value> livePagedList) {
            super(0);
            this.this$0 = livePagedList;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2() {
            this.this$0.f(true);
        }
    }

    @DebugMetadata(c = "androidx.paging.LivePagedList$invalidate$1", f = "LivePagedList.kt", i = {0, 1, 1}, l = {82, 90}, m = "invokeSuspend", n = {"pagingSource", "pagingSource", "lastKey"}, s = {"L$0", "L$0", "L$1"})
    /* loaded from: classes.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public Object L$0;
        public Object L$1;
        public int label;
        public final /* synthetic */ LivePagedList<Key, Value> this$0;

        @DebugMetadata(c = "androidx.paging.LivePagedList$invalidate$1$1", f = "LivePagedList.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ LivePagedList<Key, Value> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(LivePagedList<Key, Value> livePagedList, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = livePagedList;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.this$0, continuation);
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
                    this.this$0.r.setInitialLoadState(LoadType.REFRESH, LoadState.Loading.INSTANCE);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(LivePagedList<Key, Value> livePagedList, Continuation<? super b> continuation) {
            super(2, continuation);
            this.this$0 = livePagedList;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:23:0x00aa  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x00bf  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r10) {
            /*
                Method dump skipped, instructions count: 283
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.LivePagedList.b.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* loaded from: classes.dex */
    public static final class c implements Runnable {
        public final /* synthetic */ LivePagedList<Key, Value> h;

        public c(LivePagedList<Key, Value> livePagedList) {
            this.h = livePagedList;
        }

        @Override // java.lang.Runnable
        public final void run() {
            this.h.f(true);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public LivePagedList(@NotNull CoroutineScope coroutineScope, @Nullable Key key, @NotNull PagedList.Config config, @Nullable PagedList.BoundaryCallback<Value> boundaryCallback, @NotNull Function0<? extends PagingSource<Key, Value>> pagingSourceFactory, @NotNull CoroutineDispatcher notifyDispatcher, @NotNull CoroutineDispatcher fetchDispatcher) {
        super(new InitialPagedList(coroutineScope, notifyDispatcher, fetchDispatcher, config, key));
        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(pagingSourceFactory, "pagingSourceFactory");
        Intrinsics.checkNotNullParameter(notifyDispatcher, "notifyDispatcher");
        Intrinsics.checkNotNullParameter(fetchDispatcher, "fetchDispatcher");
        this.l = coroutineScope;
        this.m = config;
        this.n = boundaryCallback;
        this.o = pagingSourceFactory;
        this.p = notifyDispatcher;
        this.q = fetchDispatcher;
        this.t = new a(this);
        c cVar = new c(this);
        this.u = cVar;
        PagedList<Value> value = getValue();
        Intrinsics.checkNotNull(value);
        Intrinsics.checkNotNullExpressionValue(value, "value!!");
        PagedList<Value> pagedList = value;
        this.r = pagedList;
        pagedList.setRetryCallback(cVar);
    }

    public final void f(boolean z) {
        Job e;
        Job job = this.s;
        if (job == null || z) {
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            e = e.e(this.l, this.q, null, new b(this, null), 2, null);
            this.s = e;
        }
    }

    public final void g(PagedList<Value> pagedList, PagedList<Value> pagedList2) {
        pagedList.setRetryCallback(null);
        pagedList2.setRetryCallback(this.u);
    }

    @Override // androidx.lifecycle.LiveData
    public void onActive() {
        super.onActive();
        f(false);
    }
}

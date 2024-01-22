package androidx.paging;

import androidx.annotation.RestrictTo;
import androidx.paging.DataSource;
import androidx.paging.PagingSource;
import java.util.List;
import kotlin.Function;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionAdapter;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0003*\u00020\u00012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004:\u0001\u0013B#\u0012\u0006\u0010\u001d\u001a\u00020\u001c\u0012\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0012¢\u0006\u0004\b\u001e\u0010\u001fJ\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0007J-\u0010\f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0096@ø\u0001\u0000¢\u0006\u0004\b\f\u0010\rJ%\u0010\u0010\u001a\u0004\u0018\u00018\u00002\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000eH\u0016¢\u0006\u0004\b\u0010\u0010\u0011R(\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00128\u0000@\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\u001b\u001a\u00020\u00188V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006 "}, d2 = {"Landroidx/paging/LegacyPagingSource;", "", "Key", "Value", "Landroidx/paging/PagingSource;", "", "pageSize", "", "setPageSize", "Landroidx/paging/PagingSource$LoadParams;", "params", "Landroidx/paging/PagingSource$LoadResult;", "load", "(Landroidx/paging/PagingSource$LoadParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/paging/PagingState;", "state", "getRefreshKey", "(Landroidx/paging/PagingState;)Ljava/lang/Object;", "Landroidx/paging/DataSource;", com.google.android.material.color.c.f10260a, "Landroidx/paging/DataSource;", "getDataSource$paging_common", "()Landroidx/paging/DataSource;", "dataSource", "", "getJumpingSupported", "()Z", "jumpingSupported", "Lkotlinx/coroutines/CoroutineDispatcher;", "fetchDispatcher", "<init>", "(Lkotlinx/coroutines/CoroutineDispatcher;Landroidx/paging/DataSource;)V", "paging-common"}, k = 1, mv = {1, 5, 1})
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public final class LegacyPagingSource<Key, Value> extends PagingSource<Key, Value> {
    @NotNull
    public final CoroutineDispatcher b;
    @NotNull
    public final DataSource<Key, Value> c;
    public int d;

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DataSource.KeyType.values().length];
            iArr[DataSource.KeyType.POSITIONAL.ordinal()] = 1;
            iArr[DataSource.KeyType.PAGE_KEYED.ordinal()] = 2;
            iArr[DataSource.KeyType.ITEM_KEYED.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* loaded from: classes.dex */
    public /* synthetic */ class a implements DataSource.InvalidatedCallback, FunctionAdapter {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ LegacyPagingSource<Key, Value> f1501a;

        public a(LegacyPagingSource<Key, Value> legacyPagingSource) {
            this.f1501a = legacyPagingSource;
        }

        public final boolean equals(@Nullable Object obj) {
            if ((obj instanceof DataSource.InvalidatedCallback) && (obj instanceof FunctionAdapter)) {
                return Intrinsics.areEqual(getFunctionDelegate(), ((FunctionAdapter) obj).getFunctionDelegate());
            }
            return false;
        }

        @Override // kotlin.jvm.internal.FunctionAdapter
        @NotNull
        public final Function<?> getFunctionDelegate() {
            return new FunctionReferenceImpl(0, this.f1501a, LegacyPagingSource.class, "invalidate", "invalidate()V", 0);
        }

        public final int hashCode() {
            return getFunctionDelegate().hashCode();
        }

        @Override // androidx.paging.DataSource.InvalidatedCallback
        public final void onInvalidated() {
            this.f1501a.invalidate();
        }
    }

    /* loaded from: classes.dex */
    public static final class b extends Lambda implements Function0<Unit> {
        public final /* synthetic */ LegacyPagingSource<Key, Value> this$0;

        /* loaded from: classes.dex */
        public /* synthetic */ class a implements DataSource.InvalidatedCallback, FunctionAdapter {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ LegacyPagingSource<Key, Value> f1502a;

            public a(LegacyPagingSource<Key, Value> legacyPagingSource) {
                this.f1502a = legacyPagingSource;
            }

            public final boolean equals(@Nullable Object obj) {
                if ((obj instanceof DataSource.InvalidatedCallback) && (obj instanceof FunctionAdapter)) {
                    return Intrinsics.areEqual(getFunctionDelegate(), ((FunctionAdapter) obj).getFunctionDelegate());
                }
                return false;
            }

            @Override // kotlin.jvm.internal.FunctionAdapter
            @NotNull
            public final Function<?> getFunctionDelegate() {
                return new FunctionReferenceImpl(0, this.f1502a, LegacyPagingSource.class, "invalidate", "invalidate()V", 0);
            }

            public final int hashCode() {
                return getFunctionDelegate().hashCode();
            }

            @Override // androidx.paging.DataSource.InvalidatedCallback
            public final void onInvalidated() {
                this.f1502a.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(LegacyPagingSource<Key, Value> legacyPagingSource) {
            super(0);
            this.this$0 = legacyPagingSource;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2() {
            this.this$0.getDataSource$paging_common().removeInvalidatedCallback(new a(this.this$0));
            this.this$0.getDataSource$paging_common().invalidate();
        }
    }

    /* loaded from: classes.dex */
    public static final class c {
        public c() {
        }

        public /* synthetic */ c(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @DebugMetadata(c = "androidx.paging.LegacyPagingSource$load$2", f = "LegacyPagingSource.kt", i = {}, l = {111}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes.dex */
    public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PagingSource.LoadResult.Page<Key, Value>>, Object> {
        public final /* synthetic */ DataSource.Params<Key> $dataSourceParams;
        public final /* synthetic */ PagingSource.LoadParams<Key> $params;
        public int label;
        public final /* synthetic */ LegacyPagingSource<Key, Value> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(LegacyPagingSource<Key, Value> legacyPagingSource, DataSource.Params<Key> params, PagingSource.LoadParams<Key> loadParams, Continuation<? super d> continuation) {
            super(2, continuation);
            this.this$0 = legacyPagingSource;
            this.$dataSourceParams = params;
            this.$params = loadParams;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new d(this.this$0, this.$dataSourceParams, this.$params, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Object obj) {
            return invoke(coroutineScope, (Continuation) ((Continuation) obj));
        }

        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super PagingSource.LoadResult.Page<Key, Value>> continuation) {
            return ((d) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                DataSource<Key, Value> dataSource$paging_common = this.this$0.getDataSource$paging_common();
                DataSource.Params<Key> params = this.$dataSourceParams;
                this.label = 1;
                obj = dataSource$paging_common.load$paging_common(params, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            PagingSource.LoadParams<Key> loadParams = this.$params;
            DataSource.BaseResult baseResult = (DataSource.BaseResult) obj;
            List<Value> list = baseResult.data;
            return new PagingSource.LoadResult.Page(list, (list.isEmpty() && (loadParams instanceof PagingSource.LoadParams.Prepend)) ? null : baseResult.getPrevKey(), (baseResult.data.isEmpty() && (loadParams instanceof PagingSource.LoadParams.Append)) ? null : baseResult.getNextKey(), baseResult.getItemsBefore(), baseResult.getItemsAfter());
        }
    }

    static {
        new c(null);
    }

    public LegacyPagingSource(@NotNull CoroutineDispatcher fetchDispatcher, @NotNull DataSource<Key, Value> dataSource) {
        Intrinsics.checkNotNullParameter(fetchDispatcher, "fetchDispatcher");
        Intrinsics.checkNotNullParameter(dataSource, "dataSource");
        this.b = fetchDispatcher;
        this.c = dataSource;
        this.d = Integer.MIN_VALUE;
        dataSource.addInvalidatedCallback(new a(this));
        registerInvalidatedCallback(new b(this));
    }

    public final int a(PagingSource.LoadParams<Key> loadParams) {
        if ((loadParams instanceof PagingSource.LoadParams.Refresh) && loadParams.getLoadSize() % 3 == 0) {
            return loadParams.getLoadSize() / 3;
        }
        return loadParams.getLoadSize();
    }

    @NotNull
    public final DataSource<Key, Value> getDataSource$paging_common() {
        return this.c;
    }

    @Override // androidx.paging.PagingSource
    public boolean getJumpingSupported() {
        return this.c.getType$paging_common() == DataSource.KeyType.POSITIONAL;
    }

    @Override // androidx.paging.PagingSource
    @Nullable
    public Key getRefreshKey(@NotNull PagingState<Key, Value> state) {
        Key prevKey;
        Value closestItemToPosition;
        Intrinsics.checkNotNullParameter(state, "state");
        int i = WhenMappings.$EnumSwitchMapping$0[this.c.getType$paging_common().ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    Integer anchorPosition = state.getAnchorPosition();
                    if (anchorPosition == null || (closestItemToPosition = state.closestItemToPosition(anchorPosition.intValue())) == null) {
                        return null;
                    }
                    return getDataSource$paging_common().getKeyInternal$paging_common(closestItemToPosition);
                }
                throw new NoWhenBranchMatchedException();
            }
            return null;
        }
        Integer anchorPosition2 = state.getAnchorPosition();
        if (anchorPosition2 == null) {
            return null;
        }
        int intValue = anchorPosition2.intValue();
        int i2 = intValue - state.d;
        for (int i3 = 0; i3 < CollectionsKt__CollectionsKt.getLastIndex(state.getPages()) && i2 > CollectionsKt__CollectionsKt.getLastIndex(state.getPages().get(i3).getData()); i3++) {
            i2 -= state.getPages().get(i3).getData().size();
        }
        PagingSource.LoadResult.Page<Key, Value> closestPageToPosition = state.closestPageToPosition(intValue);
        if (closestPageToPosition == null || (prevKey = closestPageToPosition.getPrevKey()) == null) {
            prevKey = (Key) 0;
        }
        return (Key) Integer.valueOf(((Integer) prevKey).intValue() + i2);
    }

    @Override // androidx.paging.PagingSource
    @Nullable
    public Object load(@NotNull PagingSource.LoadParams<Key> loadParams, @NotNull Continuation<? super PagingSource.LoadResult<Key, Value>> continuation) {
        LoadType loadType;
        if (loadParams instanceof PagingSource.LoadParams.Refresh) {
            loadType = LoadType.REFRESH;
        } else if (loadParams instanceof PagingSource.LoadParams.Append) {
            loadType = LoadType.APPEND;
        } else if (!(loadParams instanceof PagingSource.LoadParams.Prepend)) {
            throw new NoWhenBranchMatchedException();
        } else {
            loadType = LoadType.PREPEND;
        }
        LoadType loadType2 = loadType;
        if (this.d == Integer.MIN_VALUE) {
            System.out.println((Object) "WARNING: pageSize on the LegacyPagingSource is not set.\nWhen using legacy DataSource / DataSourceFactory with Paging3, page size\nshould've been set by the paging library but it is not set yet.\n\nIf you are seeing this message in tests where you are testing DataSource\nin isolation (without a Pager), it is expected and page size will be estimated\nbased on parameters.\n\nIf you are seeing this message despite using a Pager, please file a bug:\nhttps://issuetracker.google.com/issues/new?component=413106");
            this.d = a(loadParams);
        }
        return BuildersKt.withContext(this.b, new d(this, new DataSource.Params(loadType2, loadParams.getKey(), loadParams.getLoadSize(), loadParams.getPlaceholdersEnabled(), this.d), loadParams, null), continuation);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final void setPageSize(int i) {
        int i2 = this.d;
        if (i2 == Integer.MIN_VALUE || i == i2) {
            this.d = i;
            return;
        }
        throw new IllegalStateException(("Page size is already set to " + this.d + '.').toString());
    }
}

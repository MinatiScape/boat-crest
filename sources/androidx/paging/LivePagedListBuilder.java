package androidx.paging;

import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;
import com.clevertap.android.sdk.Constants;
import java.util.concurrent.Executor;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Deprecated(message = "PagedList is deprecated and has been replaced by PagingData")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0003*\u00020\u00012\u00020\u0001B%\b\u0017\u0012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0013\u0012\u0006\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u0017\u0010\u0018B%\b\u0017\u0012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0013\u0012\u0006\u0010\u001a\u001a\u00020\u0019¢\u0006\u0004\b\u0017\u0010\u001bB+\b\u0017\u0012\u0018\u0010\u001e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001d0\u001c\u0012\u0006\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u0017\u0010\u001fB+\b\u0017\u0012\u0018\u0010\u001e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001d0\u001c\u0012\u0006\u0010\u001a\u001a\u00020\u0019¢\u0006\u0004\b\u0017\u0010 J\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u0005\u001a\u00020\u0004J#\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\b\u0010\u0007\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\b\u0010\tJ\"\u0010\f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\nJ\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u000e\u001a\u00020\rJ\u0012\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00110\u0010¨\u0006!"}, d2 = {"Landroidx/paging/LivePagedListBuilder;", "", "Key", "Value", "Lkotlinx/coroutines/CoroutineScope;", "coroutineScope", "setCoroutineScope", Constants.KEY_KEY, "setInitialLoadKey", "(Ljava/lang/Object;)Landroidx/paging/LivePagedListBuilder;", "Landroidx/paging/PagedList$BoundaryCallback;", "boundaryCallback", "setBoundaryCallback", "Ljava/util/concurrent/Executor;", "fetchExecutor", "setFetchExecutor", "Landroidx/lifecycle/LiveData;", "Landroidx/paging/PagedList;", "build", "Landroidx/paging/DataSource$Factory;", "dataSourceFactory", "Landroidx/paging/PagedList$Config;", Constants.KEY_CONFIG, "<init>", "(Landroidx/paging/DataSource$Factory;Landroidx/paging/PagedList$Config;)V", "", "pageSize", "(Landroidx/paging/DataSource$Factory;I)V", "Lkotlin/Function0;", "Landroidx/paging/PagingSource;", "pagingSourceFactory", "(Lkotlin/jvm/functions/Function0;Landroidx/paging/PagedList$Config;)V", "(Lkotlin/jvm/functions/Function0;I)V", "paging-runtime_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class LivePagedListBuilder<Key, Value> {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Function0<PagingSource<Key, Value>> f1503a;
    @Nullable
    public final DataSource.Factory<Key, Value> b;
    @NotNull
    public final PagedList.Config c;
    @NotNull
    public CoroutineScope d;
    @Nullable
    public Key e;
    @Nullable
    public PagedList.BoundaryCallback<Value> f;
    @NotNull
    public CoroutineDispatcher g;

    @Deprecated(message = "PagedList is deprecated and has been replaced by PagingData", replaceWith = @ReplaceWith(expression = "Pager(\n                PagingConfig(\n                    config.pageSize,\n                    config.prefetchDistance,\n                    config.enablePlaceholders,\n                    config.initialLoadSizeHint,\n                    config.maxSize\n                ),\n                initialLoadKey,\n                dataSourceFactory.asPagingSourceFactory(Dispatchers.IO)\n            ).liveData", imports = {"androidx.paging.Pager", "androidx.paging.PagingConfig", "androidx.paging.liveData", "kotlinx.coroutines.Dispatchers"}))
    public LivePagedListBuilder(@NotNull DataSource.Factory<Key, Value> dataSourceFactory, @NotNull PagedList.Config config) {
        Intrinsics.checkNotNullParameter(dataSourceFactory, "dataSourceFactory");
        Intrinsics.checkNotNullParameter(config, "config");
        this.d = GlobalScope.INSTANCE;
        Executor iOThreadExecutor = ArchTaskExecutor.getIOThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(iOThreadExecutor, "getIOThreadExecutor()");
        this.g = ExecutorsKt.from(iOThreadExecutor);
        this.f1503a = null;
        this.b = dataSourceFactory;
        this.c = config;
    }

    @NotNull
    public final LiveData<PagedList<Value>> build() {
        Function0<PagingSource<Key, Value>> function0 = this.f1503a;
        if (function0 == null) {
            DataSource.Factory<Key, Value> factory = this.b;
            function0 = factory == null ? null : factory.asPagingSourceFactory(this.g);
        }
        Function0<PagingSource<Key, Value>> function02 = function0;
        if (function02 != null) {
            CoroutineScope coroutineScope = this.d;
            Key key = this.e;
            PagedList.Config config = this.c;
            PagedList.BoundaryCallback<Value> boundaryCallback = this.f;
            Executor mainThreadExecutor = ArchTaskExecutor.getMainThreadExecutor();
            Intrinsics.checkNotNullExpressionValue(mainThreadExecutor, "getMainThreadExecutor()");
            return new LivePagedList(coroutineScope, key, config, boundaryCallback, function02, ExecutorsKt.from(mainThreadExecutor), this.g);
        }
        throw new IllegalStateException("LivePagedList cannot be built without a PagingSourceFactory or DataSource.Factory".toString());
    }

    @NotNull
    public final LivePagedListBuilder<Key, Value> setBoundaryCallback(@Nullable PagedList.BoundaryCallback<Value> boundaryCallback) {
        this.f = boundaryCallback;
        return this;
    }

    @NotNull
    public final LivePagedListBuilder<Key, Value> setCoroutineScope(@NotNull CoroutineScope coroutineScope) {
        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
        this.d = coroutineScope;
        return this;
    }

    @NotNull
    public final LivePagedListBuilder<Key, Value> setFetchExecutor(@NotNull Executor fetchExecutor) {
        Intrinsics.checkNotNullParameter(fetchExecutor, "fetchExecutor");
        this.g = ExecutorsKt.from(fetchExecutor);
        return this;
    }

    @NotNull
    public final LivePagedListBuilder<Key, Value> setInitialLoadKey(@Nullable Key key) {
        this.e = key;
        return this;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Deprecated(message = "PagedList is deprecated and has been replaced by PagingData", replaceWith = @ReplaceWith(expression = "Pager(\n                PagingConfig(pageSize),\n                initialLoadKey,\n                dataSourceFactory.asPagingSourceFactory(Dispatchers.IO)\n            ).liveData", imports = {"androidx.paging.Pager", "androidx.paging.PagingConfig", "androidx.paging.liveData", "kotlinx.coroutines.Dispatchers"}))
    public LivePagedListBuilder(@NotNull DataSource.Factory<Key, Value> dataSourceFactory, int i) {
        this(dataSourceFactory, new PagedList.Config.Builder().setPageSize(i).build());
        Intrinsics.checkNotNullParameter(dataSourceFactory, "dataSourceFactory");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated(message = "PagedList is deprecated and has been replaced by PagingData", replaceWith = @ReplaceWith(expression = "Pager(\n                PagingConfig(\n                    config.pageSize,\n                    config.prefetchDistance,\n                    config.enablePlaceholders,\n                    config.initialLoadSizeHint,\n                    config.maxSize\n                ),\n                initialLoadKey,\n                this\n            ).liveData", imports = {"androidx.paging.Pager", "androidx.paging.PagingConfig", "androidx.paging.liveData"}))
    public LivePagedListBuilder(@NotNull Function0<? extends PagingSource<Key, Value>> pagingSourceFactory, @NotNull PagedList.Config config) {
        Intrinsics.checkNotNullParameter(pagingSourceFactory, "pagingSourceFactory");
        Intrinsics.checkNotNullParameter(config, "config");
        this.d = GlobalScope.INSTANCE;
        Executor iOThreadExecutor = ArchTaskExecutor.getIOThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(iOThreadExecutor, "getIOThreadExecutor()");
        this.g = ExecutorsKt.from(iOThreadExecutor);
        this.f1503a = pagingSourceFactory;
        this.b = null;
        this.c = config;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Deprecated(message = "PagedList is deprecated and has been replaced by PagingData", replaceWith = @ReplaceWith(expression = "Pager(\n                PagingConfig(pageSize),\n                initialLoadKey,\n                this\n            ).liveData", imports = {"androidx.paging.Pager", "androidx.paging.PagingConfig", "androidx.paging.liveData"}))
    public LivePagedListBuilder(@NotNull Function0<? extends PagingSource<Key, Value>> pagingSourceFactory, int i) {
        this(pagingSourceFactory, new PagedList.Config.Builder().setPageSize(i).build());
        Intrinsics.checkNotNullParameter(pagingSourceFactory, "pagingSourceFactory");
    }
}

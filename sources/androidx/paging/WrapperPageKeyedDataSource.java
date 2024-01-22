package androidx.paging;

import androidx.arch.core.util.Function;
import androidx.exifinterface.media.ExifInterface;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0004\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0003*\u00020\u0001*\b\b\u0002\u0010\u0004*\u00020\u00012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00020\u0005B;\u0012\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005\u0012\u001e\u0010\u001b\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u001a0\u0019¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u0010\u0010\n\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\b\u0010\u000b\u001a\u00020\bH\u0016J*\u0010\u0010\u001a\u00020\b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\f2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00020\u000eH\u0016J*\u0010\u0013\u001a\u00020\b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u00112\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00020\u0012H\u0016J*\u0010\u0014\u001a\u00020\b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u00112\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00020\u0012H\u0016R\u0016\u0010\u0016\u001a\u00020\u00158V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001e"}, d2 = {"Landroidx/paging/WrapperPageKeyedDataSource;", "", "K", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "B", "Landroidx/paging/PageKeyedDataSource;", "Landroidx/paging/DataSource$InvalidatedCallback;", "onInvalidatedCallback", "", "addInvalidatedCallback", "removeInvalidatedCallback", "invalidate", "Landroidx/paging/PageKeyedDataSource$LoadInitialParams;", "params", "Landroidx/paging/PageKeyedDataSource$LoadInitialCallback;", "callback", "loadInitial", "Landroidx/paging/PageKeyedDataSource$LoadParams;", "Landroidx/paging/PageKeyedDataSource$LoadCallback;", "loadBefore", "loadAfter", "", "isInvalid", "()Z", "source", "Landroidx/arch/core/util/Function;", "", "listFunction", "<init>", "(Landroidx/paging/PageKeyedDataSource;Landroidx/arch/core/util/Function;)V", "paging-common"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class WrapperPageKeyedDataSource<K, A, B> extends PageKeyedDataSource<K, B> {
    @NotNull
    public final PageKeyedDataSource<K, A> f;
    @NotNull
    public final Function<List<A>, List<B>> g;

    public WrapperPageKeyedDataSource(@NotNull PageKeyedDataSource<K, A> source, @NotNull Function<List<A>, List<B>> listFunction) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(listFunction, "listFunction");
        this.f = source;
        this.g = listFunction;
    }

    @Override // androidx.paging.DataSource
    public void addInvalidatedCallback(@NotNull DataSource.InvalidatedCallback onInvalidatedCallback) {
        Intrinsics.checkNotNullParameter(onInvalidatedCallback, "onInvalidatedCallback");
        this.f.addInvalidatedCallback(onInvalidatedCallback);
    }

    @Override // androidx.paging.DataSource
    public void invalidate() {
        this.f.invalidate();
    }

    @Override // androidx.paging.DataSource
    public boolean isInvalid() {
        return this.f.isInvalid();
    }

    @Override // androidx.paging.PageKeyedDataSource
    public void loadAfter(@NotNull PageKeyedDataSource.LoadParams<K> params, @NotNull final PageKeyedDataSource.LoadCallback<K, B> callback) {
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.f.loadAfter(params, new PageKeyedDataSource.LoadCallback<K, A>() { // from class: androidx.paging.WrapperPageKeyedDataSource$loadAfter$1
            @Override // androidx.paging.PageKeyedDataSource.LoadCallback
            public void onResult(@NotNull List<? extends A> data, @Nullable K k) {
                Function function;
                Intrinsics.checkNotNullParameter(data, "data");
                PageKeyedDataSource.LoadCallback<K, B> loadCallback = callback;
                DataSource.Companion companion = DataSource.Companion;
                function = this.g;
                loadCallback.onResult(companion.convert$paging_common(function, data), k);
            }
        });
    }

    @Override // androidx.paging.PageKeyedDataSource
    public void loadBefore(@NotNull PageKeyedDataSource.LoadParams<K> params, @NotNull final PageKeyedDataSource.LoadCallback<K, B> callback) {
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.f.loadBefore(params, new PageKeyedDataSource.LoadCallback<K, A>() { // from class: androidx.paging.WrapperPageKeyedDataSource$loadBefore$1
            @Override // androidx.paging.PageKeyedDataSource.LoadCallback
            public void onResult(@NotNull List<? extends A> data, @Nullable K k) {
                Function function;
                Intrinsics.checkNotNullParameter(data, "data");
                PageKeyedDataSource.LoadCallback<K, B> loadCallback = callback;
                DataSource.Companion companion = DataSource.Companion;
                function = this.g;
                loadCallback.onResult(companion.convert$paging_common(function, data), k);
            }
        });
    }

    @Override // androidx.paging.PageKeyedDataSource
    public void loadInitial(@NotNull PageKeyedDataSource.LoadInitialParams<K> params, @NotNull final PageKeyedDataSource.LoadInitialCallback<K, B> callback) {
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.f.loadInitial(params, new PageKeyedDataSource.LoadInitialCallback<K, A>(this) { // from class: androidx.paging.WrapperPageKeyedDataSource$loadInitial$1

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ WrapperPageKeyedDataSource<K, A, B> f1560a;

            {
                this.f1560a = this;
            }

            @Override // androidx.paging.PageKeyedDataSource.LoadInitialCallback
            public void onResult(@NotNull List<? extends A> data, int i, int i2, @Nullable K k, @Nullable K k2) {
                Function function;
                Intrinsics.checkNotNullParameter(data, "data");
                DataSource.Companion companion = DataSource.Companion;
                function = this.f1560a.g;
                callback.onResult(companion.convert$paging_common(function, data), i, i2, k, k2);
            }

            @Override // androidx.paging.PageKeyedDataSource.LoadInitialCallback
            public void onResult(@NotNull List<? extends A> data, @Nullable K k, @Nullable K k2) {
                Function function;
                Intrinsics.checkNotNullParameter(data, "data");
                DataSource.Companion companion = DataSource.Companion;
                function = this.f1560a.g;
                callback.onResult(companion.convert$paging_common(function, data), k, k2);
            }
        });
    }

    @Override // androidx.paging.DataSource
    public void removeInvalidatedCallback(@NotNull DataSource.InvalidatedCallback onInvalidatedCallback) {
        Intrinsics.checkNotNullParameter(onInvalidatedCallback, "onInvalidatedCallback");
        this.f.removeInvalidatedCallback(onInvalidatedCallback);
    }
}

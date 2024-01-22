package androidx.paging;

import androidx.exifinterface.media.ExifInterface;
import androidx.paging.PageKeyedDataSource;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0003*\u00020\u00012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004B\u0007¢\u0006\u0004\b\u000f\u0010\u0010J*\u0010\n\u001a\u00020\t2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00052\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0007H\u0016J*\u0010\r\u001a\u00020\t2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b2\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\fH\u0016J*\u0010\u000e\u001a\u00020\t2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b2\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\fH\u0016¨\u0006\u0011"}, d2 = {"Landroidx/paging/InitialDataSource;", "", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Landroidx/paging/PageKeyedDataSource;", "Landroidx/paging/PageKeyedDataSource$LoadInitialParams;", "params", "Landroidx/paging/PageKeyedDataSource$LoadInitialCallback;", "callback", "", "loadInitial", "Landroidx/paging/PageKeyedDataSource$LoadParams;", "Landroidx/paging/PageKeyedDataSource$LoadCallback;", "loadBefore", "loadAfter", "<init>", "()V", "paging-common"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class InitialDataSource<K, V> extends PageKeyedDataSource<K, V> {
    @Override // androidx.paging.PageKeyedDataSource
    public void loadAfter(@NotNull PageKeyedDataSource.LoadParams<K> params, @NotNull PageKeyedDataSource.LoadCallback<K, V> callback) {
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(callback, "callback");
        callback.onResult(CollectionsKt__CollectionsKt.emptyList(), null);
    }

    @Override // androidx.paging.PageKeyedDataSource
    public void loadBefore(@NotNull PageKeyedDataSource.LoadParams<K> params, @NotNull PageKeyedDataSource.LoadCallback<K, V> callback) {
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(callback, "callback");
        callback.onResult(CollectionsKt__CollectionsKt.emptyList(), null);
    }

    @Override // androidx.paging.PageKeyedDataSource
    public void loadInitial(@NotNull PageKeyedDataSource.LoadInitialParams<K> params, @NotNull PageKeyedDataSource.LoadInitialCallback<K, V> callback) {
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(callback, "callback");
        callback.onResult(CollectionsKt__CollectionsKt.emptyList(), 0, 0, null, null);
    }
}

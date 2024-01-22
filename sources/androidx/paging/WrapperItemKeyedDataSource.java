package androidx.paging;

import androidx.arch.core.util.Function;
import androidx.exifinterface.media.ExifInterface;
import androidx.paging.DataSource;
import androidx.paging.ItemKeyedDataSource;
import com.clevertap.android.sdk.leanplum.Constants;
import java.util.IdentityHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0003*\u00020\u0001*\b\b\u0002\u0010\u0004*\u00020\u00012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00020\u0005B;\u0012\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005\u0012\u001e\u0010\u001f\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\f0\u001e¢\u0006\u0004\b \u0010!J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u0010\u0010\n\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\b\u0010\u000b\u001a\u00020\bH\u0016J\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00010\fJ$\u0010\u0013\u001a\u00020\b2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00020\u0011H\u0016J$\u0010\u0016\u001a\u00020\b2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u00142\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00020\u0015H\u0016J$\u0010\u0017\u001a\u00020\b2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u00142\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00020\u0015H\u0016J\u0017\u0010\u0019\u001a\u00028\u00002\u0006\u0010\u0018\u001a\u00028\u0002H\u0016¢\u0006\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001c\u001a\u00020\u001b8V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001d¨\u0006\""}, d2 = {"Landroidx/paging/WrapperItemKeyedDataSource;", "", "K", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "B", "Landroidx/paging/ItemKeyedDataSource;", "Landroidx/paging/DataSource$InvalidatedCallback;", "onInvalidatedCallback", "", "addInvalidatedCallback", "removeInvalidatedCallback", "invalidate", "", "source", "convertWithStashedKeys", "Landroidx/paging/ItemKeyedDataSource$LoadInitialParams;", "params", "Landroidx/paging/ItemKeyedDataSource$LoadInitialCallback;", "callback", "loadInitial", "Landroidx/paging/ItemKeyedDataSource$LoadParams;", "Landroidx/paging/ItemKeyedDataSource$LoadCallback;", "loadAfter", "loadBefore", Constants.IAP_ITEM_PARAM, "getKey", "(Ljava/lang/Object;)Ljava/lang/Object;", "", "isInvalid", "()Z", "Landroidx/arch/core/util/Function;", "listFunction", "<init>", "(Landroidx/paging/ItemKeyedDataSource;Landroidx/arch/core/util/Function;)V", "paging-common"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class WrapperItemKeyedDataSource<K, A, B> extends ItemKeyedDataSource<K, B> {
    @NotNull
    public final ItemKeyedDataSource<K, A> e;
    @NotNull
    public final Function<List<A>, List<B>> f;
    @NotNull
    public final IdentityHashMap<B, K> g;

    public WrapperItemKeyedDataSource(@NotNull ItemKeyedDataSource<K, A> source, @NotNull Function<List<A>, List<B>> listFunction) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(listFunction, "listFunction");
        this.e = source;
        this.f = listFunction;
        this.g = new IdentityHashMap<>();
    }

    @Override // androidx.paging.DataSource
    public void addInvalidatedCallback(@NotNull DataSource.InvalidatedCallback onInvalidatedCallback) {
        Intrinsics.checkNotNullParameter(onInvalidatedCallback, "onInvalidatedCallback");
        this.e.addInvalidatedCallback(onInvalidatedCallback);
    }

    @NotNull
    public final List<B> convertWithStashedKeys(@NotNull List<? extends A> source) {
        Intrinsics.checkNotNullParameter(source, "source");
        List<B> convert$paging_common = DataSource.Companion.convert$paging_common(this.f, source);
        synchronized (this.g) {
            int i = 0;
            int size = convert$paging_common.size() - 1;
            if (size >= 0) {
                while (true) {
                    int i2 = i + 1;
                    this.g.put(convert$paging_common.get(i), this.e.getKey(source.get(i)));
                    if (i2 > size) {
                        break;
                    }
                    i = i2;
                }
            }
            Unit unit = Unit.INSTANCE;
        }
        return convert$paging_common;
    }

    @Override // androidx.paging.ItemKeyedDataSource
    @NotNull
    public K getKey(@NotNull B item) {
        K k;
        Intrinsics.checkNotNullParameter(item, "item");
        synchronized (this.g) {
            k = this.g.get(item);
            Intrinsics.checkNotNull(k);
            Intrinsics.checkNotNullExpressionValue(k, "keyMap[item]!!");
        }
        return k;
    }

    @Override // androidx.paging.DataSource
    public void invalidate() {
        this.e.invalidate();
    }

    @Override // androidx.paging.DataSource
    public boolean isInvalid() {
        return this.e.isInvalid();
    }

    @Override // androidx.paging.ItemKeyedDataSource
    public void loadAfter(@NotNull ItemKeyedDataSource.LoadParams<K> params, @NotNull final ItemKeyedDataSource.LoadCallback<B> callback) {
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.e.loadAfter(params, new ItemKeyedDataSource.LoadCallback<A>() { // from class: androidx.paging.WrapperItemKeyedDataSource$loadAfter$1
            @Override // androidx.paging.ItemKeyedDataSource.LoadCallback
            public void onResult(@NotNull List<? extends A> data) {
                Intrinsics.checkNotNullParameter(data, "data");
                callback.onResult(this.convertWithStashedKeys(data));
            }
        });
    }

    @Override // androidx.paging.ItemKeyedDataSource
    public void loadBefore(@NotNull ItemKeyedDataSource.LoadParams<K> params, @NotNull final ItemKeyedDataSource.LoadCallback<B> callback) {
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.e.loadBefore(params, new ItemKeyedDataSource.LoadCallback<A>() { // from class: androidx.paging.WrapperItemKeyedDataSource$loadBefore$1
            @Override // androidx.paging.ItemKeyedDataSource.LoadCallback
            public void onResult(@NotNull List<? extends A> data) {
                Intrinsics.checkNotNullParameter(data, "data");
                callback.onResult(this.convertWithStashedKeys(data));
            }
        });
    }

    @Override // androidx.paging.ItemKeyedDataSource
    public void loadInitial(@NotNull ItemKeyedDataSource.LoadInitialParams<K> params, @NotNull final ItemKeyedDataSource.LoadInitialCallback<B> callback) {
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.e.loadInitial(params, new ItemKeyedDataSource.LoadInitialCallback<A>() { // from class: androidx.paging.WrapperItemKeyedDataSource$loadInitial$1
            @Override // androidx.paging.ItemKeyedDataSource.LoadInitialCallback
            public void onResult(@NotNull List<? extends A> data, int i, int i2) {
                Intrinsics.checkNotNullParameter(data, "data");
                callback.onResult(this.convertWithStashedKeys(data), i, i2);
            }

            @Override // androidx.paging.ItemKeyedDataSource.LoadCallback
            public void onResult(@NotNull List<? extends A> data) {
                Intrinsics.checkNotNullParameter(data, "data");
                callback.onResult(this.convertWithStashedKeys(data));
            }
        });
    }

    @Override // androidx.paging.DataSource
    public void removeInvalidatedCallback(@NotNull DataSource.InvalidatedCallback onInvalidatedCallback) {
        Intrinsics.checkNotNullParameter(onInvalidatedCallback, "onInvalidatedCallback");
        this.e.removeInvalidatedCallback(onInvalidatedCallback);
    }
}

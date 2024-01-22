package androidx.paging;

import androidx.arch.core.util.Function;
import androidx.exifinterface.media.ExifInterface;
import androidx.paging.DataSource;
import androidx.paging.PositionalDataSource;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0003*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00010\u0004B5\u0012\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u001e\u0010\u0019\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00140\u0013¢\u0006\u0004\b\u001e\u0010\u001fJ\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0010\u0010\t\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\n\u001a\u00020\u0007H\u0016J\u001e\u0010\u000f\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00010\rH\u0016J\u001e\u0010\u0012\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00102\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00010\u0011H\u0016R1\u0010\u0019\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00140\u00138\u0006@\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\u001b\u001a\u00020\u001a8V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u0006 "}, d2 = {"Landroidx/paging/WrapperPositionalDataSource;", "", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "B", "Landroidx/paging/PositionalDataSource;", "Landroidx/paging/DataSource$InvalidatedCallback;", "onInvalidatedCallback", "", "addInvalidatedCallback", "removeInvalidatedCallback", "invalidate", "Landroidx/paging/PositionalDataSource$LoadInitialParams;", "params", "Landroidx/paging/PositionalDataSource$LoadInitialCallback;", "callback", "loadInitial", "Landroidx/paging/PositionalDataSource$LoadRangeParams;", "Landroidx/paging/PositionalDataSource$LoadRangeCallback;", "loadRange", "Landroidx/arch/core/util/Function;", "", "g", "Landroidx/arch/core/util/Function;", "getListFunction", "()Landroidx/arch/core/util/Function;", "listFunction", "", "isInvalid", "()Z", "source", "<init>", "(Landroidx/paging/PositionalDataSource;Landroidx/arch/core/util/Function;)V", "paging-common"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class WrapperPositionalDataSource<A, B> extends PositionalDataSource<B> {
    @NotNull
    public final PositionalDataSource<A> f;
    @NotNull
    public final Function<List<A>, List<B>> g;

    public WrapperPositionalDataSource(@NotNull PositionalDataSource<A> source, @NotNull Function<List<A>, List<B>> listFunction) {
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

    @NotNull
    public final Function<List<A>, List<B>> getListFunction() {
        return this.g;
    }

    @Override // androidx.paging.DataSource
    public void invalidate() {
        this.f.invalidate();
    }

    @Override // androidx.paging.DataSource
    public boolean isInvalid() {
        return this.f.isInvalid();
    }

    @Override // androidx.paging.PositionalDataSource
    public void loadInitial(@NotNull PositionalDataSource.LoadInitialParams params, @NotNull final PositionalDataSource.LoadInitialCallback<B> callback) {
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.f.loadInitial(params, new PositionalDataSource.LoadInitialCallback<A>() { // from class: androidx.paging.WrapperPositionalDataSource$loadInitial$1
            @Override // androidx.paging.PositionalDataSource.LoadInitialCallback
            public void onResult(@NotNull List<? extends A> data, int i, int i2) {
                Intrinsics.checkNotNullParameter(data, "data");
                callback.onResult(DataSource.Companion.convert$paging_common(this.getListFunction(), data), i, i2);
            }

            @Override // androidx.paging.PositionalDataSource.LoadInitialCallback
            public void onResult(@NotNull List<? extends A> data, int i) {
                Intrinsics.checkNotNullParameter(data, "data");
                callback.onResult(DataSource.Companion.convert$paging_common(this.getListFunction(), data), i);
            }
        });
    }

    @Override // androidx.paging.PositionalDataSource
    public void loadRange(@NotNull PositionalDataSource.LoadRangeParams params, @NotNull final PositionalDataSource.LoadRangeCallback<B> callback) {
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.f.loadRange(params, new PositionalDataSource.LoadRangeCallback<A>() { // from class: androidx.paging.WrapperPositionalDataSource$loadRange$1
            @Override // androidx.paging.PositionalDataSource.LoadRangeCallback
            public void onResult(@NotNull List<? extends A> data) {
                Intrinsics.checkNotNullParameter(data, "data");
                callback.onResult(DataSource.Companion.convert$paging_common(this.getListFunction(), data));
            }
        });
    }

    @Override // androidx.paging.DataSource
    public void removeInvalidatedCallback(@NotNull DataSource.InvalidatedCallback onInvalidatedCallback) {
        Intrinsics.checkNotNullParameter(onInvalidatedCallback, "onInvalidatedCallback");
        this.f.removeInvalidatedCallback(onInvalidatedCallback);
    }
}

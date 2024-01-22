package androidx.paging;

import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.FlowLiveDataConversions;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleKt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a4\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u0002\"\b\b\u0000\u0010\u0001*\u00020\u0000*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0004\u001a4\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u0002\"\b\b\u0000\u0010\u0001*\u00020\u0000*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u00022\u0006\u0010\b\u001a\u00020\u0007\u001a4\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u0002\"\b\b\u0000\u0010\u0001*\u00020\u0000*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u00022\u0006\u0010\n\u001a\u00020\t\"C\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00030\u0002\"\b\b\u0000\u0010\u000b*\u00020\u0000\"\b\b\u0001\u0010\f*\u00020\u0000*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\r8F@\u0006¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/lifecycle/LiveData;", "Landroidx/paging/PagingData;", "Landroidx/lifecycle/Lifecycle;", "lifecycle", "cachedIn", "Landroidx/lifecycle/ViewModel;", "viewModel", "Lkotlinx/coroutines/CoroutineScope;", "scope", "Key", "Value", "Landroidx/paging/Pager;", "getLiveData", "(Landroidx/paging/Pager;)Landroidx/lifecycle/LiveData;", "liveData", "paging-runtime_release"}, k = 2, mv = {1, 5, 1})
@JvmName(name = "PagingLiveData")
/* loaded from: classes.dex */
public final class PagingLiveData {
    @NotNull
    public static final <T> LiveData<PagingData<T>> cachedIn(@NotNull LiveData<PagingData<T>> liveData, @NotNull Lifecycle lifecycle) {
        Intrinsics.checkNotNullParameter(liveData, "<this>");
        Intrinsics.checkNotNullParameter(lifecycle, "lifecycle");
        return FlowLiveDataConversions.asLiveData$default(CachedPagingDataKt.cachedIn(FlowLiveDataConversions.asFlow(liveData), LifecycleKt.getCoroutineScope(lifecycle)), (CoroutineContext) null, 0L, 3, (Object) null);
    }

    @NotNull
    public static final <Key, Value> LiveData<PagingData<Value>> getLiveData(@NotNull Pager<Key, Value> pager) {
        Intrinsics.checkNotNullParameter(pager, "<this>");
        return FlowLiveDataConversions.asLiveData$default(pager.getFlow(), (CoroutineContext) null, 0L, 3, (Object) null);
    }

    @NotNull
    public static final <T> LiveData<PagingData<T>> cachedIn(@NotNull LiveData<PagingData<T>> liveData, @NotNull ViewModel viewModel) {
        Intrinsics.checkNotNullParameter(liveData, "<this>");
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        return FlowLiveDataConversions.asLiveData$default(CachedPagingDataKt.cachedIn(FlowLiveDataConversions.asFlow(liveData), ViewModelKt.getViewModelScope(viewModel)), (CoroutineContext) null, 0L, 3, (Object) null);
    }

    @NotNull
    public static final <T> LiveData<PagingData<T>> cachedIn(@NotNull LiveData<PagingData<T>> liveData, @NotNull CoroutineScope scope) {
        Intrinsics.checkNotNullParameter(liveData, "<this>");
        Intrinsics.checkNotNullParameter(scope, "scope");
        return FlowLiveDataConversions.asLiveData$default(CachedPagingDataKt.cachedIn(FlowLiveDataConversions.asFlow(liveData), scope), (CoroutineContext) null, 0L, 3, (Object) null);
    }
}

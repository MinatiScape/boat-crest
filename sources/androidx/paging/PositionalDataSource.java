package androidx.paging;

import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import androidx.arch.core.util.Function;
import androidx.exifinterface.media.ExifInterface;
import androidx.paging.DataSource;
import androidx.paging.PositionalDataSource;
import com.clevertap.android.sdk.leanplum.Constants;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.ArrayList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Result;
import kotlin.collections.f;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
@Deprecated(message = "PositionalDataSource is deprecated and has been replaced by PagingSource", replaceWith = @ReplaceWith(expression = "PagingSource<Int, T>", imports = {"androidx.paging.PagingSource"}))
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\b'\u0018\u0000 )*\b\b\u0000\u0010\u0002*\u00020\u00012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00028\u00000\u0003:\u0005)*+,-B\u0007¢\u0006\u0004\b(\u0010&J'\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0005H\u0080@ø\u0001\u0000¢\u0006\u0004\b\b\u0010\tJ!\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\u0006\u0010\u0006\u001a\u00020\u000bH\u0081@ø\u0001\u0000¢\u0006\u0004\b\f\u0010\rJ\u001e\u0010\u000e\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u000b2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fH'J\u001e\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u00122\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013H'J\u0017\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00028\u0000H\u0000¢\u0006\u0004\b\u0016\u0010\u0017J6\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00010\u0000\"\b\b\u0001\u0010\u0019*\u00020\u00012\u001e\u0010\u001c\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u001b0\u001aJ6\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00010\u0000\"\b\b\u0001\u0010\u0019*\u00020\u00012\u001e\u0010\u001c\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u001b0\u001eJ*\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00010\u0000\"\b\b\u0001\u0010\u0019*\u00020\u00012\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001aJ*\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00010\u0000\"\b\b\u0001\u0010\u0019*\u00020\u00012\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001eR\"\u0010'\u001a\u00020 8\u0010@\u0010X\u0090D¢\u0006\u0012\n\u0004\b!\u0010\"\u0012\u0004\b%\u0010&\u001a\u0004\b#\u0010$\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006."}, d2 = {"Landroidx/paging/PositionalDataSource;", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/paging/DataSource;", "", "Landroidx/paging/DataSource$Params;", "params", "Landroidx/paging/DataSource$BaseResult;", "load$paging_common", "(Landroidx/paging/DataSource$Params;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "load", "Landroidx/paging/PositionalDataSource$LoadInitialParams;", "loadInitial$paging_common", "(Landroidx/paging/PositionalDataSource$LoadInitialParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadInitial", "Landroidx/paging/PositionalDataSource$LoadInitialCallback;", "callback", "", "Landroidx/paging/PositionalDataSource$LoadRangeParams;", "Landroidx/paging/PositionalDataSource$LoadRangeCallback;", "loadRange", Constants.IAP_ITEM_PARAM, "getKeyInternal$paging_common", "(Ljava/lang/Object;)Ljava/lang/Integer;", "getKeyInternal", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Landroidx/arch/core/util/Function;", "", "function", "mapByPage", "Lkotlin/Function1;", "map", "", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "Z", "isContiguous$paging_common", "()Z", "isContiguous$paging_common$annotations", "()V", "isContiguous", "<init>", "Companion", "LoadInitialCallback", "LoadInitialParams", "LoadRangeCallback", "LoadRangeParams", "paging-common"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public abstract class PositionalDataSource<T> extends DataSource<Integer, T> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public final boolean e;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\t\u0010\nJ\u0018\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0007J \u0010\b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¨\u0006\u000b"}, d2 = {"Landroidx/paging/PositionalDataSource$Companion;", "", "Landroidx/paging/PositionalDataSource$LoadInitialParams;", "params", "", "totalCount", "computeInitialLoadPosition", "initialLoadPosition", "computeInitialLoadSize", "<init>", "()V", "paging-common"}, k = 1, mv = {1, 5, 1})
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final int computeInitialLoadPosition(@NotNull LoadInitialParams params, int i) {
            Intrinsics.checkNotNullParameter(params, "params");
            int i2 = params.requestedStartPosition;
            int i3 = params.requestedLoadSize;
            int i4 = params.pageSize;
            return Math.max(0, Math.min(((((i - i3) + i4) - 1) / i4) * i4, (i2 / i4) * i4));
        }

        @JvmStatic
        public final int computeInitialLoadSize(@NotNull LoadInitialParams params, int i, int i2) {
            Intrinsics.checkNotNullParameter(params, "params");
            return Math.min(i2 - i, params.requestedLoadSize);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b&\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\n\u0010\u000bJ&\u0010\t\u001a\u00020\b2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u00032\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H&J\u001e\u0010\t\u001a\u00020\b2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u00032\u0006\u0010\u0006\u001a\u00020\u0005H&¨\u0006\f"}, d2 = {"Landroidx/paging/PositionalDataSource$LoadInitialCallback;", ExifInterface.GPS_DIRECTION_TRUE, "", "", "data", "", DeviceKey.position, "totalCount", "", "onResult", "<init>", "()V", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public static abstract class LoadInitialCallback<T> {
        public abstract void onResult(@NotNull List<? extends T> list, int i);

        public abstract void onResult(@NotNull List<? extends T> list, int i, int i2);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000bR\u0016\u0010\u0003\u001a\u00020\u00028\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u0016\u0010\u0005\u001a\u00020\u00028\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0004R\u0016\u0010\u0006\u001a\u00020\u00028\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0004R\u0016\u0010\b\u001a\u00020\u00078\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"Landroidx/paging/PositionalDataSource$LoadInitialParams;", "", "", "requestedStartPosition", "I", "requestedLoadSize", "pageSize", "", "placeholdersEnabled", "Z", "<init>", "(IIIZ)V", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public static class LoadInitialParams {
        @JvmField
        public final int pageSize;
        @JvmField
        public final boolean placeholdersEnabled;
        @JvmField
        public final int requestedLoadSize;
        @JvmField
        public final int requestedStartPosition;

        public LoadInitialParams(int i, int i2, int i3, boolean z) {
            this.requestedStartPosition = i;
            this.requestedLoadSize = i2;
            this.pageSize = i3;
            this.placeholdersEnabled = z;
            if (!(i >= 0)) {
                throw new IllegalStateException(Intrinsics.stringPlus("invalid start position: ", Integer.valueOf(i)).toString());
            }
            if (!(i2 >= 0)) {
                throw new IllegalStateException(Intrinsics.stringPlus("invalid load size: ", Integer.valueOf(i2)).toString());
            }
            if (!(i3 >= 0)) {
                throw new IllegalStateException(Intrinsics.stringPlus("invalid page size: ", Integer.valueOf(i3)).toString());
            }
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b&\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0007\u0010\bJ\u0016\u0010\u0006\u001a\u00020\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003H&¨\u0006\t"}, d2 = {"Landroidx/paging/PositionalDataSource$LoadRangeCallback;", ExifInterface.GPS_DIRECTION_TRUE, "", "", "data", "", "onResult", "<init>", "()V", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public static abstract class LoadRangeCallback<T> {
        public abstract void onResult(@NotNull List<? extends T> list);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0004\b\u0006\u0010\u0007R\u0016\u0010\u0003\u001a\u00020\u00028\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u0016\u0010\u0005\u001a\u00020\u00028\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0004¨\u0006\b"}, d2 = {"Landroidx/paging/PositionalDataSource$LoadRangeParams;", "", "", "startPosition", "I", "loadSize", "<init>", "(II)V", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public static class LoadRangeParams {
        @JvmField
        public final int loadSize;
        @JvmField
        public final int startPosition;

        public LoadRangeParams(int i, int i2) {
            this.startPosition = i;
            this.loadSize = i2;
        }
    }

    /* loaded from: classes.dex */
    public static final class a<I, O> implements Function {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Function<T, V> f1540a;

        public a(Function<T, V> function) {
            this.f1540a = function;
        }

        @Override // androidx.arch.core.util.Function
        /* renamed from: a */
        public final List<V> apply(List<? extends T> list) {
            Intrinsics.checkNotNullExpressionValue(list, "list");
            Function<T, V> function = this.f1540a;
            ArrayList arrayList = new ArrayList(f.collectionSizeOrDefault(list, 10));
            for (T t : list) {
                arrayList.add(function.apply(t));
            }
            return arrayList;
        }
    }

    /* loaded from: classes.dex */
    public static final class b<I, O> implements Function {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Function1<T, V> f1541a;

        /* JADX WARN: Multi-variable type inference failed */
        public b(Function1<? super T, ? extends V> function1) {
            this.f1541a = function1;
        }

        @Override // androidx.arch.core.util.Function
        /* renamed from: a */
        public final List<V> apply(List<? extends T> list) {
            Intrinsics.checkNotNullExpressionValue(list, "list");
            Function1<T, V> function1 = this.f1541a;
            ArrayList arrayList = new ArrayList(f.collectionSizeOrDefault(list, 10));
            for (T t : list) {
                arrayList.add(function1.invoke(t));
            }
            return arrayList;
        }
    }

    /* loaded from: classes.dex */
    public static final class c<I, O> implements Function {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Function1<List<? extends T>, List<V>> f1542a;

        /* JADX WARN: Multi-variable type inference failed */
        public c(Function1<? super List<? extends T>, ? extends List<? extends V>> function1) {
            this.f1542a = function1;
        }

        @Override // androidx.arch.core.util.Function
        /* renamed from: a */
        public final List<V> apply(List<? extends T> it) {
            Function1<List<? extends T>, List<V>> function1 = this.f1542a;
            Intrinsics.checkNotNullExpressionValue(it, "it");
            return (List) function1.invoke(it);
        }
    }

    public PositionalDataSource() {
        super(DataSource.KeyType.POSITIONAL);
    }

    @JvmStatic
    public static final int computeInitialLoadPosition(@NotNull LoadInitialParams loadInitialParams, int i) {
        return Companion.computeInitialLoadPosition(loadInitialParams, i);
    }

    @JvmStatic
    public static final int computeInitialLoadSize(@NotNull LoadInitialParams loadInitialParams, int i, int i2) {
        return Companion.computeInitialLoadSize(loadInitialParams, i, i2);
    }

    public static /* synthetic */ void isContiguous$paging_common$annotations() {
    }

    public final Object a(final LoadRangeParams loadRangeParams, Continuation<? super DataSource.BaseResult<T>> continuation) {
        final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        loadRange(loadRangeParams, new LoadRangeCallback<T>() { // from class: androidx.paging.PositionalDataSource$loadRange$2$1
            @Override // androidx.paging.PositionalDataSource.LoadRangeCallback
            public void onResult(@NotNull List<? extends T> data) {
                Intrinsics.checkNotNullParameter(data, "data");
                int i = PositionalDataSource.LoadRangeParams.this.startPosition;
                Integer valueOf = i == 0 ? null : Integer.valueOf(i);
                if (this.isInvalid()) {
                    CancellableContinuation<DataSource.BaseResult<T>> cancellableContinuation = cancellableContinuationImpl;
                    DataSource.BaseResult<T> empty$paging_common = DataSource.BaseResult.Companion.empty$paging_common();
                    Result.Companion companion = Result.Companion;
                    cancellableContinuation.resumeWith(Result.m123constructorimpl(empty$paging_common));
                    return;
                }
                CancellableContinuation<DataSource.BaseResult<T>> cancellableContinuation2 = cancellableContinuationImpl;
                DataSource.BaseResult baseResult = new DataSource.BaseResult(data, valueOf, Integer.valueOf(PositionalDataSource.LoadRangeParams.this.startPosition + data.size()), 0, 0, 24, null);
                Result.Companion companion2 = Result.Companion;
                cancellableContinuation2.resumeWith(Result.m123constructorimpl(baseResult));
            }
        });
        Object result = cancellableContinuationImpl.getResult();
        if (result == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.paging.DataSource
    public /* bridge */ /* synthetic */ Integer getKeyInternal$paging_common(Object obj) {
        return getKeyInternal$paging_common((PositionalDataSource<T>) obj);
    }

    @Override // androidx.paging.DataSource
    public boolean isContiguous$paging_common() {
        return this.e;
    }

    @Override // androidx.paging.DataSource
    @Nullable
    public final Object load$paging_common(@NotNull DataSource.Params<Integer> params, @NotNull Continuation<? super DataSource.BaseResult<T>> continuation) {
        if (params.getType$paging_common() == LoadType.REFRESH) {
            int initialLoadSize = params.getInitialLoadSize();
            int i = 0;
            if (params.getKey() != null) {
                int intValue = params.getKey().intValue();
                if (params.getPlaceholdersEnabled()) {
                    initialLoadSize = Math.max(initialLoadSize / params.getPageSize(), 2) * params.getPageSize();
                    i = Math.max(0, ((intValue - (initialLoadSize / 2)) / params.getPageSize()) * params.getPageSize());
                } else {
                    i = Math.max(0, intValue - (initialLoadSize / 2));
                }
            }
            return loadInitial$paging_common(new LoadInitialParams(i, initialLoadSize, params.getPageSize(), params.getPlaceholdersEnabled()), continuation);
        }
        Integer key = params.getKey();
        Intrinsics.checkNotNull(key);
        int intValue2 = key.intValue();
        int pageSize = params.getPageSize();
        if (params.getType$paging_common() == LoadType.PREPEND) {
            pageSize = Math.min(pageSize, intValue2);
            intValue2 -= pageSize;
        }
        return a(new LoadRangeParams(intValue2, pageSize), continuation);
    }

    @WorkerThread
    public abstract void loadInitial(@NotNull LoadInitialParams loadInitialParams, @NotNull LoadInitialCallback<T> loadInitialCallback);

    @VisibleForTesting
    @Nullable
    public final Object loadInitial$paging_common(@NotNull final LoadInitialParams loadInitialParams, @NotNull Continuation<? super DataSource.BaseResult<T>> continuation) {
        final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        loadInitial(loadInitialParams, new LoadInitialCallback<T>(this) { // from class: androidx.paging.PositionalDataSource$loadInitial$2$1

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ PositionalDataSource<T> f1543a;

            /* JADX WARN: Multi-variable type inference failed */
            {
                this.f1543a = this;
            }

            public final void a(PositionalDataSource.LoadInitialParams loadInitialParams2, DataSource.BaseResult<T> baseResult) {
                if (loadInitialParams2.placeholdersEnabled) {
                    baseResult.validateForInitialTiling$paging_common(loadInitialParams2.pageSize);
                }
                CancellableContinuation<DataSource.BaseResult<T>> cancellableContinuation = cancellableContinuationImpl;
                Result.Companion companion = Result.Companion;
                cancellableContinuation.resumeWith(Result.m123constructorimpl(baseResult));
            }

            @Override // androidx.paging.PositionalDataSource.LoadInitialCallback
            public void onResult(@NotNull List<? extends T> data, int i, int i2) {
                Intrinsics.checkNotNullParameter(data, "data");
                if (this.f1543a.isInvalid()) {
                    CancellableContinuation<DataSource.BaseResult<T>> cancellableContinuation = cancellableContinuationImpl;
                    DataSource.BaseResult<T> empty$paging_common = DataSource.BaseResult.Companion.empty$paging_common();
                    Result.Companion companion = Result.Companion;
                    cancellableContinuation.resumeWith(Result.m123constructorimpl(empty$paging_common));
                    return;
                }
                int size = data.size() + i;
                a(loadInitialParams, new DataSource.BaseResult<>(data, i == 0 ? null : Integer.valueOf(i), size == i2 ? null : Integer.valueOf(size), i, (i2 - data.size()) - i));
            }

            @Override // androidx.paging.PositionalDataSource.LoadInitialCallback
            public void onResult(@NotNull List<? extends T> data, int i) {
                Intrinsics.checkNotNullParameter(data, "data");
                if (this.f1543a.isInvalid()) {
                    CancellableContinuation<DataSource.BaseResult<T>> cancellableContinuation = cancellableContinuationImpl;
                    DataSource.BaseResult<T> empty$paging_common = DataSource.BaseResult.Companion.empty$paging_common();
                    Result.Companion companion = Result.Companion;
                    cancellableContinuation.resumeWith(Result.m123constructorimpl(empty$paging_common));
                    return;
                }
                a(loadInitialParams, new DataSource.BaseResult<>(data, i == 0 ? null : Integer.valueOf(i), Integer.valueOf(data.size() + i), i, Integer.MIN_VALUE));
            }
        });
        Object result = cancellableContinuationImpl.getResult();
        if (result == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    @WorkerThread
    public abstract void loadRange(@NotNull LoadRangeParams loadRangeParams, @NotNull LoadRangeCallback<T> loadRangeCallback);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.paging.DataSource
    @NotNull
    public final Integer getKeyInternal$paging_common(@NotNull T item) {
        Intrinsics.checkNotNullParameter(item, "item");
        throw new IllegalStateException("Cannot get key by item in positionalDataSource");
    }

    @Override // androidx.paging.DataSource
    @NotNull
    public final <V> PositionalDataSource<V> map(@NotNull Function<T, V> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return mapByPage((Function) new a(function));
    }

    @Override // androidx.paging.DataSource
    @NotNull
    public final <V> PositionalDataSource<V> mapByPage(@NotNull Function<List<T>, List<V>> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return new WrapperPositionalDataSource(this, function);
    }

    @Override // androidx.paging.DataSource
    @NotNull
    public final <V> PositionalDataSource<V> map(@NotNull Function1<? super T, ? extends V> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return mapByPage((Function) new b(function));
    }

    @Override // androidx.paging.DataSource
    @NotNull
    public final <V> PositionalDataSource<V> mapByPage(@NotNull Function1<? super List<? extends T>, ? extends List<? extends V>> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return mapByPage((Function) new c(function));
    }
}

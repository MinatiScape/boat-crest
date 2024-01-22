package androidx.paging;

import androidx.arch.core.util.Function;
import androidx.paging.DataSource;
import com.clevertap.android.sdk.leanplum.Constants;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.ArrayList;
import java.util.Iterator;
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
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
@Deprecated(message = "PageKeyedDataSource is deprecated and has been replaced by PagingSource", replaceWith = @ReplaceWith(expression = "PagingSource<Key, Value>", imports = {"androidx.paging.PagingSource"}))
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\b'\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0003*\u00020\u00012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004:\u0004()*+B\u0007¢\u0006\u0004\b'\u0010%J'\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00010\u00072\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0080@ø\u0001\u0000¢\u0006\u0004\b\b\u0010\tJ*\u0010\u000f\u001a\u00020\u000e2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\fH&J*\u0010\u0012\u001a\u00020\u000e2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00102\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0011H&J*\u0010\u0013\u001a\u00020\u000e2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00102\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0011H&J\u0017\u0010\u0017\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00028\u0001H\u0010¢\u0006\u0004\b\u0015\u0010\u0016J<\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00020\u0000\"\b\b\u0002\u0010\u0018*\u00020\u00012\u001e\u0010\u001b\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u001a0\u0019J<\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00020\u0000\"\b\b\u0002\u0010\u0018*\u00020\u00012\u001e\u0010\u001b\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u001a0\u001dJ0\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00020\u0000\"\b\b\u0002\u0010\u0018*\u00020\u00012\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0019J0\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00020\u0000\"\b\b\u0002\u0010\u0018*\u00020\u00012\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u001dR\"\u0010&\u001a\u00020\u001f8\u0010@\u0010X\u0090D¢\u0006\u0012\n\u0004\b \u0010!\u0012\u0004\b$\u0010%\u001a\u0004\b\"\u0010#\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006,"}, d2 = {"Landroidx/paging/PageKeyedDataSource;", "", "Key", "Value", "Landroidx/paging/DataSource;", "Landroidx/paging/DataSource$Params;", "params", "Landroidx/paging/DataSource$BaseResult;", "load$paging_common", "(Landroidx/paging/DataSource$Params;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "load", "Landroidx/paging/PageKeyedDataSource$LoadInitialParams;", "Landroidx/paging/PageKeyedDataSource$LoadInitialCallback;", "callback", "", "loadInitial", "Landroidx/paging/PageKeyedDataSource$LoadParams;", "Landroidx/paging/PageKeyedDataSource$LoadCallback;", "loadBefore", "loadAfter", Constants.IAP_ITEM_PARAM, "getKeyInternal$paging_common", "(Ljava/lang/Object;)Ljava/lang/Object;", "getKeyInternal", "ToValue", "Landroidx/arch/core/util/Function;", "", "function", "mapByPage", "Lkotlin/Function1;", "map", "", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "Z", "getSupportsPageDropping$paging_common", "()Z", "getSupportsPageDropping$paging_common$annotations", "()V", "supportsPageDropping", "<init>", "LoadCallback", "LoadInitialCallback", "LoadInitialParams", "LoadParams", "paging-common"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public abstract class PageKeyedDataSource<Key, Value> extends DataSource<Key, Value> {
    public final boolean e;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\b&\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\n\u0010\u000bJ'\u0010\b\u001a\u00020\u00072\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00030\u00042\b\u0010\u0006\u001a\u0004\u0018\u00018\u0002H&¢\u0006\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"Landroidx/paging/PageKeyedDataSource$LoadCallback;", "Key", "Value", "", "", "data", "adjacentPageKey", "", "onResult", "(Ljava/util/List;Ljava/lang/Object;)V", "<init>", "()V", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public static abstract class LoadCallback<Key, Value> {
        public abstract void onResult(@NotNull List<? extends Value> list, @Nullable Key key);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\b&\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u000f\u0010\u0010JA\u0010\f\u001a\u00020\u000b2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00030\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00018\u00022\b\u0010\n\u001a\u0004\u0018\u00018\u0002H&¢\u0006\u0004\b\f\u0010\rJ1\u0010\f\u001a\u00020\u000b2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00030\u00042\b\u0010\t\u001a\u0004\u0018\u00018\u00022\b\u0010\n\u001a\u0004\u0018\u00018\u0002H&¢\u0006\u0004\b\f\u0010\u000e¨\u0006\u0011"}, d2 = {"Landroidx/paging/PageKeyedDataSource$LoadInitialCallback;", "Key", "Value", "", "", "data", "", DeviceKey.position, "totalCount", "previousPageKey", "nextPageKey", "", "onResult", "(Ljava/util/List;IILjava/lang/Object;Ljava/lang/Object;)V", "(Ljava/util/List;Ljava/lang/Object;Ljava/lang/Object;)V", "<init>", "()V", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public static abstract class LoadInitialCallback<Key, Value> {
        public abstract void onResult(@NotNull List<? extends Value> list, int i, int i2, @Nullable Key key, @Nullable Key key2);

        public abstract void onResult(@NotNull List<? extends Value> list, @Nullable Key key, @Nullable Key key2);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0016\u0018\u0000*\b\b\u0002\u0010\u0002*\u00020\u00012\u00020\u0001B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nR\u0016\u0010\u0004\u001a\u00020\u00038\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0005R\u0016\u0010\u0007\u001a\u00020\u00068\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Landroidx/paging/PageKeyedDataSource$LoadInitialParams;", "", "Key", "", "requestedLoadSize", "I", "", "placeholdersEnabled", "Z", "<init>", "(IZ)V", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public static class LoadInitialParams<Key> {
        @JvmField
        public final boolean placeholdersEnabled;
        @JvmField
        public final int requestedLoadSize;

        public LoadInitialParams(int i, boolean z) {
            this.requestedLoadSize = i;
            this.placeholdersEnabled = z;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\b\u0016\u0018\u0000*\b\b\u0002\u0010\u0002*\u00020\u00012\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00028\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tR\u0016\u0010\u0003\u001a\u00028\u00028\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u0016\u0010\u0006\u001a\u00020\u00058\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Landroidx/paging/PageKeyedDataSource$LoadParams;", "", "Key", com.clevertap.android.sdk.Constants.KEY_KEY, "Ljava/lang/Object;", "", "requestedLoadSize", "I", "<init>", "(Ljava/lang/Object;I)V", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public static class LoadParams<Key> {
        @JvmField
        @NotNull
        public final Key key;
        @JvmField
        public final int requestedLoadSize;

        public LoadParams(@NotNull Key key, int i) {
            Intrinsics.checkNotNullParameter(key, "key");
            this.key = key;
            this.requestedLoadSize = i;
        }
    }

    /* loaded from: classes.dex */
    public static final class a<I, O> implements Function {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Function<Value, ToValue> f1520a;

        public a(Function<Value, ToValue> function) {
            this.f1520a = function;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.arch.core.util.Function
        /* renamed from: a */
        public final List<ToValue> apply(List<? extends Value> list) {
            Intrinsics.checkNotNullExpressionValue(list, "list");
            Function<Value, ToValue> function = this.f1520a;
            ArrayList arrayList = new ArrayList(f.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(function.apply(it.next()));
            }
            return arrayList;
        }
    }

    /* loaded from: classes.dex */
    public static final class b<I, O> implements Function {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Function1<Value, ToValue> f1521a;

        /* JADX WARN: Multi-variable type inference failed */
        public b(Function1<? super Value, ? extends ToValue> function1) {
            this.f1521a = function1;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.arch.core.util.Function
        /* renamed from: a */
        public final List<ToValue> apply(List<? extends Value> list) {
            Intrinsics.checkNotNullExpressionValue(list, "list");
            Function1<Value, ToValue> function1 = this.f1521a;
            ArrayList arrayList = new ArrayList(f.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(function1.invoke(it.next()));
            }
            return arrayList;
        }
    }

    /* loaded from: classes.dex */
    public static final class c<I, O> implements Function {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Function1<List<? extends Value>, List<ToValue>> f1522a;

        /* JADX WARN: Multi-variable type inference failed */
        public c(Function1<? super List<? extends Value>, ? extends List<? extends ToValue>> function1) {
            this.f1522a = function1;
        }

        @Override // androidx.arch.core.util.Function
        /* renamed from: a */
        public final List<ToValue> apply(List<? extends Value> it) {
            Function1<List<? extends Value>, List<ToValue>> function1 = this.f1522a;
            Intrinsics.checkNotNullExpressionValue(it, "it");
            return (List) function1.invoke(it);
        }
    }

    public PageKeyedDataSource() {
        super(DataSource.KeyType.PAGE_KEYED);
    }

    public static /* synthetic */ void getSupportsPageDropping$paging_common$annotations() {
    }

    public final LoadCallback<Key, Value> a(final CancellableContinuation<? super DataSource.BaseResult<Value>> cancellableContinuation, final boolean z) {
        return new LoadCallback<Key, Value>() { // from class: androidx.paging.PageKeyedDataSource$continuationAsCallback$1
            @Override // androidx.paging.PageKeyedDataSource.LoadCallback
            public void onResult(@NotNull List<? extends Value> data, @Nullable Key key) {
                Intrinsics.checkNotNullParameter(data, "data");
                CancellableContinuation<DataSource.BaseResult<Value>> cancellableContinuation2 = cancellableContinuation;
                boolean z2 = z;
                DataSource.BaseResult baseResult = new DataSource.BaseResult(data, z2 ? null : key, z2 ? key : null, 0, 0, 24, null);
                Result.Companion companion = Result.Companion;
                cancellableContinuation2.resumeWith(Result.m123constructorimpl(baseResult));
            }
        };
    }

    public final Object b(LoadParams<Key> loadParams, Continuation<? super DataSource.BaseResult<Value>> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        loadAfter(loadParams, a(cancellableContinuationImpl, true));
        Object result = cancellableContinuationImpl.getResult();
        if (result == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public final Object c(LoadParams<Key> loadParams, Continuation<? super DataSource.BaseResult<Value>> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        loadBefore(loadParams, a(cancellableContinuationImpl, false));
        Object result = cancellableContinuationImpl.getResult();
        if (result == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public final Object d(LoadInitialParams<Key> loadInitialParams, Continuation<? super DataSource.BaseResult<Value>> continuation) {
        final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        loadInitial(loadInitialParams, new LoadInitialCallback<Key, Value>() { // from class: androidx.paging.PageKeyedDataSource$loadInitial$2$1
            @Override // androidx.paging.PageKeyedDataSource.LoadInitialCallback
            public void onResult(@NotNull List<? extends Value> data, int i, int i2, @Nullable Key key, @Nullable Key key2) {
                Intrinsics.checkNotNullParameter(data, "data");
                CancellableContinuation<DataSource.BaseResult<Value>> cancellableContinuation = cancellableContinuationImpl;
                DataSource.BaseResult baseResult = new DataSource.BaseResult(data, key, key2, i, (i2 - data.size()) - i);
                Result.Companion companion = Result.Companion;
                cancellableContinuation.resumeWith(Result.m123constructorimpl(baseResult));
            }

            @Override // androidx.paging.PageKeyedDataSource.LoadInitialCallback
            public void onResult(@NotNull List<? extends Value> data, @Nullable Key key, @Nullable Key key2) {
                Intrinsics.checkNotNullParameter(data, "data");
                CancellableContinuation<DataSource.BaseResult<Value>> cancellableContinuation = cancellableContinuationImpl;
                DataSource.BaseResult baseResult = new DataSource.BaseResult(data, key, key2, 0, 0, 24, null);
                Result.Companion companion = Result.Companion;
                cancellableContinuation.resumeWith(Result.m123constructorimpl(baseResult));
            }
        });
        Object result = cancellableContinuationImpl.getResult();
        if (result == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    @Override // androidx.paging.DataSource
    @NotNull
    public Key getKeyInternal$paging_common(@NotNull Value item) {
        Intrinsics.checkNotNullParameter(item, "item");
        throw new IllegalStateException("Cannot get key by item in pageKeyedDataSource");
    }

    @Override // androidx.paging.DataSource
    public boolean getSupportsPageDropping$paging_common() {
        return this.e;
    }

    @Override // androidx.paging.DataSource
    @Nullable
    public final Object load$paging_common(@NotNull DataSource.Params<Key> params, @NotNull Continuation<? super DataSource.BaseResult<Value>> continuation) {
        if (params.getType$paging_common() == LoadType.REFRESH) {
            return d(new LoadInitialParams<>(params.getInitialLoadSize(), params.getPlaceholdersEnabled()), continuation);
        }
        if (params.getKey() == null) {
            return DataSource.BaseResult.Companion.empty$paging_common();
        }
        if (params.getType$paging_common() == LoadType.PREPEND) {
            return c(new LoadParams<>(params.getKey(), params.getPageSize()), continuation);
        }
        if (params.getType$paging_common() == LoadType.APPEND) {
            return b(new LoadParams<>(params.getKey(), params.getPageSize()), continuation);
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("Unsupported type ", params.getType$paging_common()));
    }

    public abstract void loadAfter(@NotNull LoadParams<Key> loadParams, @NotNull LoadCallback<Key, Value> loadCallback);

    public abstract void loadBefore(@NotNull LoadParams<Key> loadParams, @NotNull LoadCallback<Key, Value> loadCallback);

    public abstract void loadInitial(@NotNull LoadInitialParams<Key> loadInitialParams, @NotNull LoadInitialCallback<Key, Value> loadInitialCallback);

    @Override // androidx.paging.DataSource
    @NotNull
    public final <ToValue> PageKeyedDataSource<Key, ToValue> map(@NotNull Function<Value, ToValue> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return mapByPage((Function) new a(function));
    }

    @Override // androidx.paging.DataSource
    @NotNull
    public final <ToValue> PageKeyedDataSource<Key, ToValue> mapByPage(@NotNull Function<List<Value>, List<ToValue>> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return new WrapperPageKeyedDataSource(this, function);
    }

    @Override // androidx.paging.DataSource
    @NotNull
    public final <ToValue> PageKeyedDataSource<Key, ToValue> map(@NotNull Function1<? super Value, ? extends ToValue> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return mapByPage((Function) new b(function));
    }

    @Override // androidx.paging.DataSource
    @NotNull
    public final <ToValue> PageKeyedDataSource<Key, ToValue> mapByPage(@NotNull Function1<? super List<? extends Value>, ? extends List<? extends ToValue>> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return mapByPage((Function) new c(function));
    }
}

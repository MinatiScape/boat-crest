package androidx.paging;

import androidx.annotation.VisibleForTesting;
import androidx.arch.core.util.Function;
import androidx.paging.DataSource;
import com.clevertap.android.sdk.leanplum.Constants;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ReplaceWith;
import kotlin.Result;
import kotlin.collections.CollectionsKt___CollectionsKt;
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
@Deprecated(message = "ItemKeyedDataSource is deprecated and has been replaced by PagingSource", replaceWith = @ReplaceWith(expression = "PagingSource<Key, Value>", imports = {"androidx.paging.PagingSource"}))
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b'\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0003*\u00020\u00012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004:\u0004,-./B\u0007¢\u0006\u0004\b*\u0010+J'\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00010\u00072\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0080@ø\u0001\u0000¢\u0006\u0004\b\b\u0010\tJ\u001b\u0010\u000e\u001a\u0004\u0018\u00018\u0000*\b\u0012\u0004\u0012\u00028\u00010\u000bH\u0000¢\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u0010\u001a\u0004\u0018\u00018\u0000*\b\u0012\u0004\u0012\u00028\u00010\u000bH\u0000¢\u0006\u0004\b\u000f\u0010\rJ'\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00010\u00072\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011H\u0081@ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J'\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00010\u00072\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0015H\u0081@ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017J'\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00010\u00072\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0015H\u0081@ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u0017J$\u0010\u0014\u001a\u00020\u001d2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00112\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00010\u001bH&J$\u0010\u001a\u001a\u00020\u001d2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00152\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00010\u001eH&J$\u0010\u0018\u001a\u00020\u001d2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00152\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00010\u001eH&J\u0017\u0010 \u001a\u00028\u00002\u0006\u0010\u001f\u001a\u00028\u0001H&¢\u0006\u0004\b \u0010!J\u0017\u0010#\u001a\u00028\u00002\u0006\u0010\u001f\u001a\u00028\u0001H\u0010¢\u0006\u0004\b\"\u0010!J<\u0010'\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00020\u0000\"\b\b\u0002\u0010$*\u00020\u00012\u001e\u0010&\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u000b0%J<\u0010'\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00020\u0000\"\b\b\u0002\u0010$*\u00020\u00012\u001e\u0010&\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u000b0(J0\u0010)\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00020\u0000\"\b\b\u0002\u0010$*\u00020\u00012\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020%J0\u0010)\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00020\u0000\"\b\b\u0002\u0010$*\u00020\u00012\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020(\u0082\u0002\u0004\n\u0002\b\u0019¨\u00060"}, d2 = {"Landroidx/paging/ItemKeyedDataSource;", "", "Key", "Value", "Landroidx/paging/DataSource;", "Landroidx/paging/DataSource$Params;", "params", "Landroidx/paging/DataSource$BaseResult;", "load$paging_common", "(Landroidx/paging/DataSource$Params;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "load", "", "getPrevKey$paging_common", "(Ljava/util/List;)Ljava/lang/Object;", "getPrevKey", "getNextKey$paging_common", "getNextKey", "Landroidx/paging/ItemKeyedDataSource$LoadInitialParams;", "loadInitial$paging_common", "(Landroidx/paging/ItemKeyedDataSource$LoadInitialParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadInitial", "Landroidx/paging/ItemKeyedDataSource$LoadParams;", "loadBefore$paging_common", "(Landroidx/paging/ItemKeyedDataSource$LoadParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadBefore", "loadAfter$paging_common", "loadAfter", "Landroidx/paging/ItemKeyedDataSource$LoadInitialCallback;", "callback", "", "Landroidx/paging/ItemKeyedDataSource$LoadCallback;", Constants.IAP_ITEM_PARAM, "getKey", "(Ljava/lang/Object;)Ljava/lang/Object;", "getKeyInternal$paging_common", "getKeyInternal", "ToValue", "Landroidx/arch/core/util/Function;", "function", "mapByPage", "Lkotlin/Function1;", "map", "<init>", "()V", "LoadCallback", "LoadInitialCallback", "LoadInitialParams", "LoadParams", "paging-common"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public abstract class ItemKeyedDataSource<Key, Value> extends DataSource<Key, Value> {

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b&\u0018\u0000*\u0004\b\u0002\u0010\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0007\u0010\bJ\u0016\u0010\u0006\u001a\u00020\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00020\u0003H&¨\u0006\t"}, d2 = {"Landroidx/paging/ItemKeyedDataSource$LoadCallback;", "Value", "", "", "data", "", "onResult", "<init>", "()V", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public static abstract class LoadCallback<Value> {
        public abstract void onResult(@NotNull List<? extends Value> list);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b&\u0018\u0000*\u0004\b\u0002\u0010\u00012\b\u0012\u0004\u0012\u00028\u00020\u0002B\u0007¢\u0006\u0004\b\n\u0010\u000bJ&\u0010\t\u001a\u00020\b2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H&¨\u0006\f"}, d2 = {"Landroidx/paging/ItemKeyedDataSource$LoadInitialCallback;", "Value", "Landroidx/paging/ItemKeyedDataSource$LoadCallback;", "", "data", "", DeviceKey.position, "totalCount", "", "onResult", "<init>", "()V", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public static abstract class LoadInitialCallback<Value> extends LoadCallback<Value> {
        public abstract void onResult(@NotNull List<? extends Value> list, int i, int i2);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0016\u0018\u0000*\b\b\u0002\u0010\u0002*\u00020\u00012\u00020\u0001B!\u0012\b\u0010\u0003\u001a\u0004\u0018\u00018\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\fR\u0018\u0010\u0003\u001a\u0004\u0018\u00018\u00028\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u0016\u0010\u0006\u001a\u00020\u00058\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0007R\u0016\u0010\t\u001a\u00020\b8\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"Landroidx/paging/ItemKeyedDataSource$LoadInitialParams;", "", "Key", "requestedInitialKey", "Ljava/lang/Object;", "", "requestedLoadSize", "I", "", "placeholdersEnabled", "Z", "<init>", "(Ljava/lang/Object;IZ)V", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public static class LoadInitialParams<Key> {
        @JvmField
        public final boolean placeholdersEnabled;
        @JvmField
        @Nullable
        public final Key requestedInitialKey;
        @JvmField
        public final int requestedLoadSize;

        public LoadInitialParams(@Nullable Key key, int i, boolean z) {
            this.requestedInitialKey = key;
            this.requestedLoadSize = i;
            this.placeholdersEnabled = z;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\b\u0016\u0018\u0000*\b\b\u0002\u0010\u0002*\u00020\u00012\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00028\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tR\u0016\u0010\u0003\u001a\u00028\u00028\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u0016\u0010\u0006\u001a\u00020\u00058\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Landroidx/paging/ItemKeyedDataSource$LoadParams;", "", "Key", com.clevertap.android.sdk.Constants.KEY_KEY, "Ljava/lang/Object;", "", "requestedLoadSize", "I", "<init>", "(Ljava/lang/Object;I)V", "paging-common"}, k = 1, mv = {1, 5, 1})
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

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LoadType.values().length];
            iArr[LoadType.REFRESH.ordinal()] = 1;
            iArr[LoadType.PREPEND.ordinal()] = 2;
            iArr[LoadType.APPEND.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* loaded from: classes.dex */
    public static final class a<I, O> implements Function {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Function<Value, ToValue> f1495a;

        public a(Function<Value, ToValue> function) {
            this.f1495a = function;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.arch.core.util.Function
        /* renamed from: a */
        public final List<ToValue> apply(List<? extends Value> list) {
            Intrinsics.checkNotNullExpressionValue(list, "list");
            Function<Value, ToValue> function = this.f1495a;
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
        public final /* synthetic */ Function1<Value, ToValue> f1497a;

        /* JADX WARN: Multi-variable type inference failed */
        public b(Function1<? super Value, ? extends ToValue> function1) {
            this.f1497a = function1;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.arch.core.util.Function
        /* renamed from: a */
        public final List<ToValue> apply(List<? extends Value> list) {
            Intrinsics.checkNotNullExpressionValue(list, "list");
            Function1<Value, ToValue> function1 = this.f1497a;
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
        public final /* synthetic */ Function1<List<? extends Value>, List<ToValue>> f1498a;

        /* JADX WARN: Multi-variable type inference failed */
        public c(Function1<? super List<? extends Value>, ? extends List<? extends ToValue>> function1) {
            this.f1498a = function1;
        }

        @Override // androidx.arch.core.util.Function
        /* renamed from: a */
        public final List<ToValue> apply(List<? extends Value> it) {
            Function1<List<? extends Value>, List<ToValue>> function1 = this.f1498a;
            Intrinsics.checkNotNullExpressionValue(it, "it");
            return (List) function1.invoke(it);
        }
    }

    public ItemKeyedDataSource() {
        super(DataSource.KeyType.ITEM_KEYED);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.paging.ItemKeyedDataSource$asCallback$1] */
    public final ItemKeyedDataSource$asCallback$1 a(final CancellableContinuation<? super DataSource.BaseResult<Value>> cancellableContinuation) {
        return new LoadCallback<Value>() { // from class: androidx.paging.ItemKeyedDataSource$asCallback$1
            @Override // androidx.paging.ItemKeyedDataSource.LoadCallback
            public void onResult(@NotNull List<? extends Value> data) {
                Intrinsics.checkNotNullParameter(data, "data");
                CancellableContinuation<DataSource.BaseResult<Value>> cancellableContinuation2 = cancellableContinuation;
                DataSource.BaseResult baseResult = new DataSource.BaseResult(data, this.getPrevKey$paging_common(data), this.getNextKey$paging_common(data), 0, 0, 24, null);
                Result.Companion companion = Result.Companion;
                cancellableContinuation2.resumeWith(Result.m123constructorimpl(baseResult));
            }
        };
    }

    @NotNull
    public abstract Key getKey(@NotNull Value value);

    @Override // androidx.paging.DataSource
    @NotNull
    public Key getKeyInternal$paging_common(@NotNull Value item) {
        Intrinsics.checkNotNullParameter(item, "item");
        return getKey(item);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public final Key getNextKey$paging_common(@NotNull List<? extends Value> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Object lastOrNull = CollectionsKt___CollectionsKt.lastOrNull((List<? extends Object>) list);
        if (lastOrNull == null) {
            return null;
        }
        return (Key) getKey(lastOrNull);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public final Key getPrevKey$paging_common(@NotNull List<? extends Value> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Object firstOrNull = CollectionsKt___CollectionsKt.firstOrNull((List<? extends Object>) list);
        if (firstOrNull == null) {
            return null;
        }
        return (Key) getKey(firstOrNull);
    }

    @Override // androidx.paging.DataSource
    @Nullable
    public final Object load$paging_common(@NotNull DataSource.Params<Key> params, @NotNull Continuation<? super DataSource.BaseResult<Value>> continuation) {
        int i = WhenMappings.$EnumSwitchMapping$0[params.getType$paging_common().ordinal()];
        if (i != 1) {
            if (i == 2) {
                Key key = params.getKey();
                Intrinsics.checkNotNull(key);
                return loadBefore$paging_common(new LoadParams<>(key, params.getPageSize()), continuation);
            } else if (i == 3) {
                Key key2 = params.getKey();
                Intrinsics.checkNotNull(key2);
                return loadAfter$paging_common(new LoadParams<>(key2, params.getPageSize()), continuation);
            } else {
                throw new NoWhenBranchMatchedException();
            }
        }
        return loadInitial$paging_common(new LoadInitialParams<>(params.getKey(), params.getInitialLoadSize(), params.getPlaceholdersEnabled()), continuation);
    }

    public abstract void loadAfter(@NotNull LoadParams<Key> loadParams, @NotNull LoadCallback<Value> loadCallback);

    @VisibleForTesting
    @Nullable
    public final Object loadAfter$paging_common(@NotNull LoadParams<Key> loadParams, @NotNull Continuation<? super DataSource.BaseResult<Value>> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        loadAfter(loadParams, a(cancellableContinuationImpl));
        Object result = cancellableContinuationImpl.getResult();
        if (result == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public abstract void loadBefore(@NotNull LoadParams<Key> loadParams, @NotNull LoadCallback<Value> loadCallback);

    @VisibleForTesting
    @Nullable
    public final Object loadBefore$paging_common(@NotNull LoadParams<Key> loadParams, @NotNull Continuation<? super DataSource.BaseResult<Value>> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        loadBefore(loadParams, a(cancellableContinuationImpl));
        Object result = cancellableContinuationImpl.getResult();
        if (result == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public abstract void loadInitial(@NotNull LoadInitialParams<Key> loadInitialParams, @NotNull LoadInitialCallback<Value> loadInitialCallback);

    @VisibleForTesting
    @Nullable
    public final Object loadInitial$paging_common(@NotNull LoadInitialParams<Key> loadInitialParams, @NotNull Continuation<? super DataSource.BaseResult<Value>> continuation) {
        final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        loadInitial(loadInitialParams, new LoadInitialCallback<Value>() { // from class: androidx.paging.ItemKeyedDataSource$loadInitial$2$1
            @Override // androidx.paging.ItemKeyedDataSource.LoadInitialCallback
            public void onResult(@NotNull List<? extends Value> data, int i, int i2) {
                Intrinsics.checkNotNullParameter(data, "data");
                CancellableContinuation<DataSource.BaseResult<Value>> cancellableContinuation = cancellableContinuationImpl;
                DataSource.BaseResult baseResult = new DataSource.BaseResult(data, this.getPrevKey$paging_common(data), this.getNextKey$paging_common(data), i, (i2 - data.size()) - i);
                Result.Companion companion = Result.Companion;
                cancellableContinuation.resumeWith(Result.m123constructorimpl(baseResult));
            }

            @Override // androidx.paging.ItemKeyedDataSource.LoadCallback
            public void onResult(@NotNull List<? extends Value> data) {
                Intrinsics.checkNotNullParameter(data, "data");
                CancellableContinuation<DataSource.BaseResult<Value>> cancellableContinuation = cancellableContinuationImpl;
                DataSource.BaseResult baseResult = new DataSource.BaseResult(data, this.getPrevKey$paging_common(data), this.getNextKey$paging_common(data), 0, 0, 24, null);
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
    public final <ToValue> ItemKeyedDataSource<Key, ToValue> map(@NotNull Function<Value, ToValue> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return mapByPage((Function) new a(function));
    }

    @Override // androidx.paging.DataSource
    @NotNull
    public final <ToValue> ItemKeyedDataSource<Key, ToValue> mapByPage(@NotNull Function<List<Value>, List<ToValue>> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return new WrapperItemKeyedDataSource(this, function);
    }

    @Override // androidx.paging.DataSource
    @NotNull
    public final <ToValue> ItemKeyedDataSource<Key, ToValue> map(@NotNull Function1<? super Value, ? extends ToValue> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return mapByPage((Function) new b(function));
    }

    @Override // androidx.paging.DataSource
    @NotNull
    public final <ToValue> ItemKeyedDataSource<Key, ToValue> mapByPage(@NotNull Function1<? super List<? extends Value>, ? extends List<? extends ToValue>> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return mapByPage((Function) new c(function));
    }
}

package androidx.paging;

import androidx.annotation.IntRange;
import androidx.annotation.MainThread;
import androidx.annotation.RestrictTo;
import androidx.exifinterface.media.ExifInterface;
import androidx.paging.LoadState;
import androidx.paging.PagingSource;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.lang.ref.WeakReference;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.i;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
@Deprecated(message = "PagedList is deprecated and has been replaced by PagingData")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0010\b'\u0018\u0000 n*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003:\u0006opqnrsBA\b\u0000\u0012\u0010\u00105\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u000000\u0012\u0006\u0010;\u001a\u000206\u0012\u0006\u0010A\u001a\u00020<\u0012\f\u0010G\u001a\b\u0012\u0004\u0012\u00028\u00000B\u0012\u0006\u0010M\u001a\u00020H¢\u0006\u0004\bl\u0010mJ\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004H\u0007J\b\u0010\u0007\u001a\u00020\u0006H\u0007J\"\u0010\r\u001a\u00020\u000b2\u0018\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\bH'J\u0010\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u0006H'J\b\u0010\u0010\u001a\u00020\u000bH&J\u0018\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\nH\u0017J\b\u0010\u0014\u001a\u00020\u000bH\u0016J\u0012\u0010\u0017\u001a\u00020\u000b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0007J\u001f\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\nH\u0000¢\u0006\u0004\b\u001a\u0010\u001bJ\u001a\u0010\u001d\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u000e\u001a\u00020\u0006H\u0096\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ\u000e\u0010\u001f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u0006J\f\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00000 J \u0010#\u001a\u00020\u000b2\u0018\u0010\"\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\bJ \u0010$\u001a\u00020\u000b2\u0018\u0010\"\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\bJ \u0010'\u001a\u00020\u000b2\u000e\u0010%\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010 2\u0006\u0010\f\u001a\u00020&H\u0007J\u000e\u0010'\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020&J\u000e\u0010(\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020&J\u001f\u0010-\u001a\u00020\u000b2\u0006\u0010)\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\u0006H\u0000¢\u0006\u0004\b+\u0010,J\u0018\u0010.\u001a\u00020\u000b2\u0006\u0010)\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\u0006H\u0007J\u0018\u0010/\u001a\u00020\u000b2\u0006\u0010)\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\u0006H\u0007R&\u00105\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u0000008\u0016@\u0017X\u0097\u0004¢\u0006\f\n\u0004\b1\u00102\u001a\u0004\b3\u00104R\u001c\u0010;\u001a\u0002068\u0000@\u0000X\u0080\u0004¢\u0006\f\n\u0004\b7\u00108\u001a\u0004\b9\u0010:R\u001c\u0010A\u001a\u00020<8\u0000@\u0000X\u0080\u0004¢\u0006\f\n\u0004\b=\u0010>\u001a\u0004\b?\u0010@R\"\u0010G\u001a\b\u0012\u0004\u0012\u00028\u00000B8\u0000@\u0000X\u0080\u0004¢\u0006\f\n\u0004\bC\u0010D\u001a\u0004\bE\u0010FR\u0019\u0010M\u001a\u00020H8\u0006@\u0006¢\u0006\f\n\u0004\bI\u0010J\u001a\u0004\bK\u0010LR$\u0010\u0016\u001a\u0004\u0018\u00010\u00158\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bN\u0010O\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010SR\u001c\u0010X\u001a\u00020\u00068\u0000@\u0000X\u0080\u0004¢\u0006\f\n\u0004\bT\u0010U\u001a\u0004\bV\u0010WR\u0016\u0010Z\u001a\u00020\u00068V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\bY\u0010WR&\u0010`\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00000[8F@\u0007X\u0087\u0004¢\u0006\f\u0012\u0004\b^\u0010_\u001a\u0004\b\\\u0010]R\u0018\u0010c\u001a\u0004\u0018\u00010\u00018&@&X¦\u0004¢\u0006\u0006\u001a\u0004\ba\u0010bR\u0016\u0010e\u001a\u00020d8&@&X¦\u0004¢\u0006\u0006\u001a\u0004\be\u0010fR\u0013\u0010h\u001a\u00020\u00068F@\u0006¢\u0006\u0006\u001a\u0004\bg\u0010WR\u0016\u0010i\u001a\u00020d8V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\bi\u0010fR\u0013\u0010k\u001a\u00020\u00068F@\u0006¢\u0006\u0006\u001a\u0004\bj\u0010W¨\u0006t"}, d2 = {"Landroidx/paging/PagedList;", "", ExifInterface.GPS_DIRECTION_TRUE, "Ljava/util/AbstractList;", "Landroidx/paging/NullPaddedList;", "getNullPaddedList", "", "lastLoad", "Lkotlin/Function2;", "Landroidx/paging/LoadType;", "Landroidx/paging/LoadState;", "", "callback", "dispatchCurrentLoadState", FirebaseAnalytics.Param.INDEX, "loadAroundInternal", "detach", "loadType", "loadState", "setInitialLoadState", "retry", "Ljava/lang/Runnable;", "refreshRetryCallback", "setRetryCallback", "type", "state", "dispatchStateChangeAsync$paging_common", "(Landroidx/paging/LoadType;Landroidx/paging/LoadState;)V", "dispatchStateChangeAsync", "get", "(I)Ljava/lang/Object;", "loadAround", "", "snapshot", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "addWeakLoadStateListener", "removeWeakLoadStateListener", "previousSnapshot", "Landroidx/paging/PagedList$Callback;", "addWeakCallback", "removeWeakCallback", DeviceKey.position, "count", "notifyInserted$paging_common", "(II)V", "notifyInserted", "notifyChanged", "notifyRemoved", "Landroidx/paging/PagingSource;", "h", "Landroidx/paging/PagingSource;", "getPagingSource", "()Landroidx/paging/PagingSource;", "pagingSource", "Lkotlinx/coroutines/CoroutineScope;", "i", "Lkotlinx/coroutines/CoroutineScope;", "getCoroutineScope$paging_common", "()Lkotlinx/coroutines/CoroutineScope;", "coroutineScope", "Lkotlinx/coroutines/CoroutineDispatcher;", "j", "Lkotlinx/coroutines/CoroutineDispatcher;", "getNotifyDispatcher$paging_common", "()Lkotlinx/coroutines/CoroutineDispatcher;", "notifyDispatcher", "Landroidx/paging/PagedStorage;", OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME, "Landroidx/paging/PagedStorage;", "getStorage$paging_common", "()Landroidx/paging/PagedStorage;", "storage", "Landroidx/paging/PagedList$Config;", "l", "Landroidx/paging/PagedList$Config;", "getConfig", "()Landroidx/paging/PagedList$Config;", Constants.KEY_CONFIG, "m", "Ljava/lang/Runnable;", "getRefreshRetryCallback$paging_common", "()Ljava/lang/Runnable;", "setRefreshRetryCallback$paging_common", "(Ljava/lang/Runnable;)V", "n", "I", "getRequiredRemainder$paging_common", "()I", "requiredRemainder", "getSize", "size", "Landroidx/paging/DataSource;", "getDataSource", "()Landroidx/paging/DataSource;", "getDataSource$annotations", "()V", "dataSource", "getLastKey", "()Ljava/lang/Object;", "lastKey", "", "isDetached", "()Z", "getLoadedCount", "loadedCount", "isImmutable", "getPositionOffset", "positionOffset", "<init>", "(Landroidx/paging/PagingSource;Lkotlinx/coroutines/CoroutineScope;Lkotlinx/coroutines/CoroutineDispatcher;Landroidx/paging/PagedStorage;Landroidx/paging/PagedList$Config;)V", "Companion", "BoundaryCallback", "Builder", "Callback", "Config", "LoadStateManager", "paging-common"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public abstract class PagedList<T> extends AbstractList<T> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @NotNull
    public final PagingSource<?, T> h;
    @NotNull
    public final CoroutineScope i;
    @NotNull
    public final CoroutineDispatcher j;
    @NotNull
    public final PagedStorage<T> k;
    @NotNull
    public final Config l;
    @Nullable
    public Runnable m;
    public final int n;
    @NotNull
    public final List<WeakReference<Callback>> o;
    @NotNull
    public final List<WeakReference<Function2<LoadType, LoadState, Unit>>> p;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\b'\u0018\u0000*\b\b\u0001\u0010\u0002*\u00020\u00012\u00020\u0001B\u0007¢\u0006\u0004\b\n\u0010\u000bJ\b\u0010\u0004\u001a\u00020\u0003H\u0016J\u0017\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\t\u001a\u00020\u00032\u0006\u0010\b\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\t\u0010\u0007¨\u0006\f"}, d2 = {"Landroidx/paging/PagedList$BoundaryCallback;", "", ExifInterface.GPS_DIRECTION_TRUE, "", "onZeroItemsLoaded", "itemAtFront", "onItemAtFrontLoaded", "(Ljava/lang/Object;)V", "itemAtEnd", "onItemAtEndLoaded", "<init>", "()V", "paging-common"}, k = 1, mv = {1, 5, 1})
    @MainThread
    /* loaded from: classes.dex */
    public static abstract class BoundaryCallback<T> {
        public void onItemAtEndLoaded(@NotNull T itemAtEnd) {
            Intrinsics.checkNotNullParameter(itemAtEnd, "itemAtEnd");
        }

        public void onItemAtFrontLoaded(@NotNull T itemAtFront) {
            Intrinsics.checkNotNullParameter(itemAtFront, "itemAtFront");
        }

        public void onZeroItemsLoaded() {
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\t\u0010\nJ\u0018\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H&J\u0018\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H&J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H&¨\u0006\u000b"}, d2 = {"Landroidx/paging/PagedList$Callback;", "", "", DeviceKey.position, "count", "", "onChanged", "onInserted", "onRemoved", "<init>", "()V", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public static abstract class Callback {
        public abstract void onChanged(int i, int i2);

        public abstract void onInserted(int i, int i2);

        public abstract void onRemoved(int i, int i2);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u001e\u0010\u001fJ\u008d\u0001\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00020\u0012\"\b\b\u0001\u0010\u0002*\u00020\u0001\"\b\b\u0002\u0010\u0003*\u00020\u00012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00042\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00028\u0002\u0018\u00010\r2\u0006\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00018\u0001H\u0007¢\u0006\u0004\b\u0013\u0010\u0014J'\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u0018H\u0000¢\u0006\u0004\b\u001b\u0010\u001c¨\u0006 "}, d2 = {"Landroidx/paging/PagedList$Companion;", "", "K", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/paging/PagingSource;", "pagingSource", "Landroidx/paging/PagingSource$LoadResult$Page;", "initialPage", "Lkotlinx/coroutines/CoroutineScope;", "coroutineScope", "Lkotlinx/coroutines/CoroutineDispatcher;", "notifyDispatcher", "fetchDispatcher", "Landroidx/paging/PagedList$BoundaryCallback;", "boundaryCallback", "Landroidx/paging/PagedList$Config;", Constants.KEY_CONFIG, Constants.KEY_KEY, "Landroidx/paging/PagedList;", "create", "(Landroidx/paging/PagingSource;Landroidx/paging/PagingSource$LoadResult$Page;Lkotlinx/coroutines/CoroutineScope;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Landroidx/paging/PagedList$BoundaryCallback;Landroidx/paging/PagedList$Config;Ljava/lang/Object;)Landroidx/paging/PagedList;", "", "currentSize", "snapshotSize", "Landroidx/paging/PagedList$Callback;", "callback", "", "dispatchNaiveUpdatesSinceSnapshot$paging_common", "(IILandroidx/paging/PagedList$Callback;)V", "dispatchNaiveUpdatesSinceSnapshot", "<init>", "()V", "paging-common"}, k = 1, mv = {1, 5, 1})
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public static final class Companion {

        /* JADX INFO: Add missing generic type declarations: [K] */
        @DebugMetadata(c = "androidx.paging.PagedList$Companion$create$resolvedInitialPage$1", f = "PagedList.kt", i = {}, l = {184}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes.dex */
        public static final class a<K> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PagingSource.LoadResult.Page<K, T>>, Object> {
            public final /* synthetic */ PagingSource<K, T> $pagingSource;
            public final /* synthetic */ PagingSource.LoadParams.Refresh<K> $params;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(PagingSource<K, T> pagingSource, PagingSource.LoadParams.Refresh<K> refresh, Continuation<? super a> continuation) {
                super(2, continuation);
                this.$pagingSource = pagingSource;
                this.$params = refresh;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.$pagingSource, this.$params, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Object obj) {
                return invoke(coroutineScope, (Continuation) ((Continuation) obj));
            }

            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super PagingSource.LoadResult.Page<K, T>> continuation) {
                return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    PagingSource<K, T> pagingSource = this.$pagingSource;
                    PagingSource.LoadParams.Refresh<K> refresh = this.$params;
                    this.label = 1;
                    obj = pagingSource.load(refresh, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    ResultKt.throwOnFailure(obj);
                }
                PagingSource.LoadResult loadResult = (PagingSource.LoadResult) obj;
                if (loadResult instanceof PagingSource.LoadResult.Page) {
                    return (PagingSource.LoadResult.Page) loadResult;
                }
                if (!(loadResult instanceof PagingSource.LoadResult.Error)) {
                    if (loadResult instanceof PagingSource.LoadResult.Invalid) {
                        throw new IllegalStateException("Failed to create PagedList. The provided PagingSource returned LoadResult.Invalid, but a LoadResult.Page was expected. To use a PagingSource which supports invalidation, use a PagedList builder that accepts a factory method for PagingSource or DataSource.Factory, such as LivePagedList.");
                    }
                    throw new NoWhenBranchMatchedException();
                }
                throw ((PagingSource.LoadResult.Error) loadResult).getThrowable();
            }
        }

        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        @NotNull
        public final <K, T> PagedList<T> create(@NotNull PagingSource<K, T> pagingSource, @Nullable PagingSource.LoadResult.Page<K, T> page, @NotNull CoroutineScope coroutineScope, @NotNull CoroutineDispatcher notifyDispatcher, @NotNull CoroutineDispatcher fetchDispatcher, @Nullable BoundaryCallback<T> boundaryCallback, @NotNull Config config, @Nullable K k) {
            PagingSource.LoadResult.Page<K, T> page2;
            Object b;
            Intrinsics.checkNotNullParameter(pagingSource, "pagingSource");
            Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
            Intrinsics.checkNotNullParameter(notifyDispatcher, "notifyDispatcher");
            Intrinsics.checkNotNullParameter(fetchDispatcher, "fetchDispatcher");
            Intrinsics.checkNotNullParameter(config, "config");
            if (page == null) {
                b = kotlinx.coroutines.d.b(null, new a(pagingSource, new PagingSource.LoadParams.Refresh(k, config.initialLoadSizeHint, config.enablePlaceholders), null), 1, null);
                page2 = (PagingSource.LoadResult.Page) b;
            } else {
                page2 = page;
            }
            return new ContiguousPagedList(pagingSource, coroutineScope, notifyDispatcher, fetchDispatcher, boundaryCallback, config, page2, k);
        }

        public final void dispatchNaiveUpdatesSinceSnapshot$paging_common(int i, int i2, @NotNull Callback callback) {
            Intrinsics.checkNotNullParameter(callback, "callback");
            if (i2 < i) {
                if (i2 > 0) {
                    callback.onChanged(0, i2);
                }
                int i3 = i - i2;
                if (i3 > 0) {
                    callback.onInserted(i2, i3);
                    return;
                }
                return;
            }
            if (i > 0) {
                callback.onChanged(0, i);
            }
            int i4 = i2 - i;
            if (i4 != 0) {
                callback.onRemoved(i, i4);
            }
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u0000 \r2\u00020\u0001:\u0002\u000e\rB1\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002¢\u0006\u0004\b\u000b\u0010\fR\u0016\u0010\u0003\u001a\u00020\u00028\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u0016\u0010\u0005\u001a\u00020\u00028\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0004R\u0016\u0010\u0007\u001a\u00020\u00068\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\bR\u0016\u0010\t\u001a\u00020\u00028\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\t\u0010\u0004R\u0016\u0010\n\u001a\u00020\u00028\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u0004¨\u0006\u000f"}, d2 = {"Landroidx/paging/PagedList$Config;", "", "", "pageSize", "I", "prefetchDistance", "", "enablePlaceholders", "Z", "initialLoadSizeHint", "maxSize", "<init>", "(IIZII)V", "Companion", "Builder", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public static final class Config {
        @NotNull
        public static final Companion Companion = new Companion(null);
        public static final int MAX_SIZE_UNBOUNDED = Integer.MAX_VALUE;
        @JvmField
        public final boolean enablePlaceholders;
        @JvmField
        public final int initialLoadSizeHint;
        @JvmField
        public final int maxSize;
        @JvmField
        public final int pageSize;
        @JvmField
        public final int prefetchDistance;

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0007¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0004\u001a\u00020\u00002\b\b\u0001\u0010\u0003\u001a\u00020\u0002J\u0010\u0010\u0006\u001a\u00020\u00002\b\b\u0001\u0010\u0005\u001a\u00020\u0002J\u000e\u0010\t\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0007J\u0010\u0010\u000b\u001a\u00020\u00002\b\b\u0001\u0010\n\u001a\u00020\u0002J\u0010\u0010\r\u001a\u00020\u00002\b\b\u0001\u0010\f\u001a\u00020\u0002J\u0006\u0010\u000f\u001a\u00020\u000e¨\u0006\u0013"}, d2 = {"Landroidx/paging/PagedList$Config$Builder;", "", "", "pageSize", "setPageSize", "prefetchDistance", "setPrefetchDistance", "", "enablePlaceholders", "setEnablePlaceholders", "initialLoadSizeHint", "setInitialLoadSizeHint", "maxSize", "setMaxSize", "Landroidx/paging/PagedList$Config;", "build", "<init>", "()V", "Companion", "paging-common"}, k = 1, mv = {1, 5, 1})
        /* loaded from: classes.dex */
        public static final class Builder {
            @NotNull
            public static final Companion Companion = new Companion(null);
            public static final int DEFAULT_INITIAL_PAGE_MULTIPLIER = 3;

            /* renamed from: a  reason: collision with root package name */
            public int f1526a = -1;
            public int b = -1;
            public int c = -1;
            public boolean d = true;
            public int e = Integer.MAX_VALUE;

            @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0005\u0010\u0006R\u0016\u0010\u0003\u001a\u00020\u00028\u0000@\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004¨\u0006\u0007"}, d2 = {"Landroidx/paging/PagedList$Config$Builder$Companion;", "", "", "DEFAULT_INITIAL_PAGE_MULTIPLIER", "I", "<init>", "()V", "paging-common"}, k = 1, mv = {1, 5, 1})
            /* loaded from: classes.dex */
            public static final class Companion {
                public Companion() {
                }

                public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }
            }

            @NotNull
            public final Config build() {
                if (this.b < 0) {
                    this.b = this.f1526a;
                }
                if (this.c < 0) {
                    this.c = this.f1526a * 3;
                }
                if (!this.d && this.b == 0) {
                    throw new IllegalArgumentException("Placeholders and prefetch are the only ways to trigger loading of more data in the PagedList, so either placeholders must be enabled, or prefetch distance must be > 0.");
                }
                int i = this.e;
                if (i != Integer.MAX_VALUE && i < this.f1526a + (this.b * 2)) {
                    throw new IllegalArgumentException("Maximum size must be at least pageSize + 2*prefetchDist, pageSize=" + this.f1526a + ", prefetchDist=" + this.b + ", maxSize=" + this.e);
                }
                return new Config(this.f1526a, this.b, this.d, this.c, this.e);
            }

            @NotNull
            public final Builder setEnablePlaceholders(boolean z) {
                this.d = z;
                return this;
            }

            @NotNull
            public final Builder setInitialLoadSizeHint(@IntRange(from = 1) int i) {
                this.c = i;
                return this;
            }

            @NotNull
            public final Builder setMaxSize(@IntRange(from = 2) int i) {
                this.e = i;
                return this;
            }

            @NotNull
            public final Builder setPageSize(@IntRange(from = 1) int i) {
                if (i >= 1) {
                    this.f1526a = i;
                    return this;
                }
                throw new IllegalArgumentException("Page size must be a positive number");
            }

            @NotNull
            public final Builder setPrefetchDistance(@IntRange(from = 0) int i) {
                this.b = i;
                return this;
            }
        }

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0007\u0010\u0006R\u001c\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086T¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u0012\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Landroidx/paging/PagedList$Config$Companion;", "", "", "MAX_SIZE_UNBOUNDED", "I", "getMAX_SIZE_UNBOUNDED$annotations", "()V", "<init>", "paging-common"}, k = 1, mv = {1, 5, 1})
        /* loaded from: classes.dex */
        public static final class Companion {
            public Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public static /* synthetic */ void getMAX_SIZE_UNBOUNDED$annotations() {
            }
        }

        public Config(int i, int i2, boolean z, int i3, int i4) {
            this.pageSize = i;
            this.prefetchDistance = i2;
            this.enablePlaceholders = z;
            this.initialLoadSizeHint = i3;
            this.maxSize = i4;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\b'\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u001b\u0010\u001cJ\u0016\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004J\u0018\u0010\b\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H'J \u0010\u000b\u001a\u00020\u00062\u0018\u0010\n\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\tR\"\u0010\u0012\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\"\u0010\u0016\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010\r\u001a\u0004\b\u0014\u0010\u000f\"\u0004\b\u0015\u0010\u0011R\"\u0010\u001a\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\r\u001a\u0004\b\u0018\u0010\u000f\"\u0004\b\u0019\u0010\u0011¨\u0006\u001d"}, d2 = {"Landroidx/paging/PagedList$LoadStateManager;", "", "Landroidx/paging/LoadType;", "type", "Landroidx/paging/LoadState;", "state", "", "setState", "onStateChanged", "Lkotlin/Function2;", "callback", "dispatchCurrentLoadState", "a", "Landroidx/paging/LoadState;", "getRefreshState", "()Landroidx/paging/LoadState;", "setRefreshState", "(Landroidx/paging/LoadState;)V", "refreshState", "b", "getStartState", "setStartState", "startState", com.google.android.material.color.c.f10260a, "getEndState", "setEndState", "endState", "<init>", "()V", "paging-common"}, k = 1, mv = {1, 5, 1})
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public static abstract class LoadStateManager {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public LoadState f1527a;
        @NotNull
        public LoadState b;
        @NotNull
        public LoadState c;

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

        public LoadStateManager() {
            LoadState.NotLoading.Companion companion = LoadState.NotLoading.Companion;
            this.f1527a = companion.getIncomplete$paging_common();
            this.b = companion.getIncomplete$paging_common();
            this.c = companion.getIncomplete$paging_common();
        }

        public final void dispatchCurrentLoadState(@NotNull Function2<? super LoadType, ? super LoadState, Unit> callback) {
            Intrinsics.checkNotNullParameter(callback, "callback");
            callback.invoke(LoadType.REFRESH, this.f1527a);
            callback.invoke(LoadType.PREPEND, this.b);
            callback.invoke(LoadType.APPEND, this.c);
        }

        @NotNull
        public final LoadState getEndState() {
            return this.c;
        }

        @NotNull
        public final LoadState getRefreshState() {
            return this.f1527a;
        }

        @NotNull
        public final LoadState getStartState() {
            return this.b;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public abstract void onStateChanged(@NotNull LoadType loadType, @NotNull LoadState loadState);

        public final void setEndState(@NotNull LoadState loadState) {
            Intrinsics.checkNotNullParameter(loadState, "<set-?>");
            this.c = loadState;
        }

        public final void setRefreshState(@NotNull LoadState loadState) {
            Intrinsics.checkNotNullParameter(loadState, "<set-?>");
            this.f1527a = loadState;
        }

        public final void setStartState(@NotNull LoadState loadState) {
            Intrinsics.checkNotNullParameter(loadState, "<set-?>");
            this.b = loadState;
        }

        public final void setState(@NotNull LoadType type, @NotNull LoadState state) {
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(state, "state");
            int i = WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        if (Intrinsics.areEqual(this.c, state)) {
                            return;
                        }
                        this.c = state;
                    }
                } else if (Intrinsics.areEqual(this.b, state)) {
                    return;
                } else {
                    this.b = state;
                }
            } else if (Intrinsics.areEqual(this.f1527a, state)) {
                return;
            } else {
                this.f1527a = state;
            }
            onStateChanged(type, state);
        }
    }

    /* loaded from: classes.dex */
    public static final class a extends Lambda implements Function1<WeakReference<Callback>, Boolean> {
        public static final a INSTANCE = new a();

        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        @NotNull
        public final Boolean invoke(@NotNull WeakReference<Callback> it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return Boolean.valueOf(it.get() == null);
        }
    }

    /* loaded from: classes.dex */
    public static final class b extends Lambda implements Function1<WeakReference<Function2<? super LoadType, ? super LoadState, ? extends Unit>>, Boolean> {
        public static final b INSTANCE = new b();

        public b() {
            super(1);
        }

        @NotNull
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Boolean invoke2(@NotNull WeakReference<Function2<LoadType, LoadState, Unit>> it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return Boolean.valueOf(it.get() == null);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Boolean invoke(WeakReference<Function2<? super LoadType, ? super LoadState, ? extends Unit>> weakReference) {
            return invoke2((WeakReference<Function2<LoadType, LoadState, Unit>>) weakReference);
        }
    }

    @DebugMetadata(c = "androidx.paging.PagedList$dispatchStateChangeAsync$1", f = "PagedList.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes.dex */
    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ LoadState $state;
        public final /* synthetic */ LoadType $type;
        public int label;
        public final /* synthetic */ PagedList<T> this$0;

        /* loaded from: classes.dex */
        public static final class a extends Lambda implements Function1<WeakReference<Function2<? super LoadType, ? super LoadState, ? extends Unit>>, Boolean> {
            public static final a INSTANCE = new a();

            public a() {
                super(1);
            }

            @NotNull
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final Boolean invoke2(@NotNull WeakReference<Function2<LoadType, LoadState, Unit>> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Boolean.valueOf(it.get() == null);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(WeakReference<Function2<? super LoadType, ? super LoadState, ? extends Unit>> weakReference) {
                return invoke2((WeakReference<Function2<LoadType, LoadState, Unit>>) weakReference);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(PagedList<T> pagedList, LoadType loadType, LoadState loadState, Continuation<? super c> continuation) {
            super(2, continuation);
            this.this$0 = pagedList;
            this.$type = loadType;
            this.$state = loadState;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new c(this.this$0, this.$type, this.$state, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((c) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                i.removeAll(this.this$0.p, (Function1) a.INSTANCE);
                List<WeakReference> list = this.this$0.p;
                LoadType loadType = this.$type;
                LoadState loadState = this.$state;
                for (WeakReference weakReference : list) {
                    Function2 function2 = (Function2) weakReference.get();
                    if (function2 != null) {
                        function2.invoke(loadType, loadState);
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* loaded from: classes.dex */
    public static final class d extends Lambda implements Function1<WeakReference<Callback>, Boolean> {
        public final /* synthetic */ Callback $callback;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(Callback callback) {
            super(1);
            this.$callback = callback;
        }

        @Override // kotlin.jvm.functions.Function1
        @NotNull
        public final Boolean invoke(@NotNull WeakReference<Callback> it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return Boolean.valueOf(it.get() == null || it.get() == this.$callback);
        }
    }

    /* loaded from: classes.dex */
    public static final class e extends Lambda implements Function1<WeakReference<Function2<? super LoadType, ? super LoadState, ? extends Unit>>, Boolean> {
        public final /* synthetic */ Function2<LoadType, LoadState, Unit> $listener;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public e(Function2<? super LoadType, ? super LoadState, Unit> function2) {
            super(1);
            this.$listener = function2;
        }

        @NotNull
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Boolean invoke2(@NotNull WeakReference<Function2<LoadType, LoadState, Unit>> it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return Boolean.valueOf(it.get() == null || it.get() == this.$listener);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Boolean invoke(WeakReference<Function2<? super LoadType, ? super LoadState, ? extends Unit>> weakReference) {
            return invoke2((WeakReference<Function2<LoadType, LoadState, Unit>>) weakReference);
        }
    }

    public PagedList(@NotNull PagingSource<?, T> pagingSource, @NotNull CoroutineScope coroutineScope, @NotNull CoroutineDispatcher notifyDispatcher, @NotNull PagedStorage<T> storage, @NotNull Config config) {
        Intrinsics.checkNotNullParameter(pagingSource, "pagingSource");
        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
        Intrinsics.checkNotNullParameter(notifyDispatcher, "notifyDispatcher");
        Intrinsics.checkNotNullParameter(storage, "storage");
        Intrinsics.checkNotNullParameter(config, "config");
        this.h = pagingSource;
        this.i = coroutineScope;
        this.j = notifyDispatcher;
        this.k = storage;
        this.l = config;
        this.n = (config.prefetchDistance * 2) + config.pageSize;
        this.o = new ArrayList();
        this.p = new ArrayList();
    }

    @JvmStatic
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @NotNull
    public static final <K, T> PagedList<T> create(@NotNull PagingSource<K, T> pagingSource, @Nullable PagingSource.LoadResult.Page<K, T> page, @NotNull CoroutineScope coroutineScope, @NotNull CoroutineDispatcher coroutineDispatcher, @NotNull CoroutineDispatcher coroutineDispatcher2, @Nullable BoundaryCallback<T> boundaryCallback, @NotNull Config config, @Nullable K k) {
        return Companion.create(pagingSource, page, coroutineScope, coroutineDispatcher, coroutineDispatcher2, boundaryCallback, config, k);
    }

    @Deprecated(message = "DataSource is deprecated and has been replaced by PagingSource. PagedList offers indirect ways of controlling fetch ('loadAround()', 'retry()') so that you should not need to access the DataSource/PagingSource.")
    public static /* synthetic */ void getDataSource$annotations() {
    }

    @Deprecated(message = "Dispatching a diff since snapshot created is behavior that can be instead tracked by attaching a Callback to the PagedList that is mutating, and tracking changes since calling PagedList.snapshot().")
    public final void addWeakCallback(@Nullable List<? extends T> list, @NotNull Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (list != null && list != this) {
            Companion.dispatchNaiveUpdatesSinceSnapshot$paging_common(size(), list.size(), callback);
        }
        addWeakCallback(callback);
    }

    public final void addWeakLoadStateListener(@NotNull Function2<? super LoadType, ? super LoadState, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        i.removeAll((List) this.p, (Function1) b.INSTANCE);
        this.p.add(new WeakReference<>(listener));
        dispatchCurrentLoadState(listener);
    }

    public abstract void detach();

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public abstract void dispatchCurrentLoadState(@NotNull Function2<? super LoadType, ? super LoadState, Unit> function2);

    public final void dispatchStateChangeAsync$paging_common(@NotNull LoadType type, @NotNull LoadState state) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(state, "state");
        kotlinx.coroutines.e.e(this.i, this.j, null, new c(this, type, state, null), 2, null);
    }

    @Override // java.util.AbstractList, java.util.List
    @Nullable
    public T get(int i) {
        return this.k.get(i);
    }

    @NotNull
    public final Config getConfig() {
        return this.l;
    }

    @NotNull
    public final CoroutineScope getCoroutineScope$paging_common() {
        return this.i;
    }

    @NotNull
    public final DataSource<?, T> getDataSource() {
        PagingSource<?, T> pagingSource = getPagingSource();
        if (pagingSource instanceof LegacyPagingSource) {
            return ((LegacyPagingSource) pagingSource).getDataSource$paging_common();
        }
        throw new IllegalStateException("Attempt to access dataSource on a PagedList that was instantiated with a " + ((Object) pagingSource.getClass().getSimpleName()) + " instead of a DataSource");
    }

    @Nullable
    public abstract Object getLastKey();

    public final int getLoadedCount() {
        return this.k.getStorageCount();
    }

    @NotNull
    public final CoroutineDispatcher getNotifyDispatcher$paging_common() {
        return this.j;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @NotNull
    public final NullPaddedList<T> getNullPaddedList() {
        return this.k;
    }

    @NotNull
    public PagingSource<?, T> getPagingSource() {
        return this.h;
    }

    public final int getPositionOffset() {
        return this.k.getPositionOffset();
    }

    @Nullable
    public final Runnable getRefreshRetryCallback$paging_common() {
        return this.m;
    }

    public final int getRequiredRemainder$paging_common() {
        return this.n;
    }

    public int getSize() {
        return this.k.size();
    }

    @NotNull
    public final PagedStorage<T> getStorage$paging_common() {
        return this.k;
    }

    public abstract boolean isDetached();

    public boolean isImmutable() {
        return isDetached();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final int lastLoad() {
        return this.k.getLastLoadAroundIndex();
    }

    public final void loadAround(int i) {
        if (i >= 0 && i < size()) {
            this.k.setLastLoadAroundIndex(i);
            loadAroundInternal(i);
            return;
        }
        throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + size());
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public abstract void loadAroundInternal(int i);

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public final void notifyChanged(int i, int i2) {
        if (i2 == 0) {
            return;
        }
        for (WeakReference weakReference : CollectionsKt___CollectionsKt.reversed(this.o)) {
            Callback callback = (Callback) weakReference.get();
            if (callback != null) {
                callback.onChanged(i, i2);
            }
        }
    }

    public final void notifyInserted$paging_common(int i, int i2) {
        if (i2 == 0) {
            return;
        }
        for (WeakReference weakReference : CollectionsKt___CollectionsKt.reversed(this.o)) {
            Callback callback = (Callback) weakReference.get();
            if (callback != null) {
                callback.onInserted(i, i2);
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public final void notifyRemoved(int i, int i2) {
        if (i2 == 0) {
            return;
        }
        for (WeakReference weakReference : CollectionsKt___CollectionsKt.reversed(this.o)) {
            Callback callback = (Callback) weakReference.get();
            if (callback != null) {
                callback.onRemoved(i, i2);
            }
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* bridge */ T remove(int i) {
        return (T) removeAt(i);
    }

    public /* bridge */ Object removeAt(int i) {
        return super.remove(i);
    }

    public final void removeWeakCallback(@NotNull Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        i.removeAll((List) this.o, (Function1) new d(callback));
    }

    public final void removeWeakLoadStateListener(@NotNull Function2<? super LoadType, ? super LoadState, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        i.removeAll((List) this.p, (Function1) new e(listener));
    }

    public void retry() {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setInitialLoadState(@NotNull LoadType loadType, @NotNull LoadState loadState) {
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        Intrinsics.checkNotNullParameter(loadState, "loadState");
    }

    public final void setRefreshRetryCallback$paging_common(@Nullable Runnable runnable) {
        this.m = runnable;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public final void setRetryCallback(@Nullable Runnable runnable) {
        this.m = runnable;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ int size() {
        return getSize();
    }

    @NotNull
    public final List<T> snapshot() {
        return isImmutable() ? this : new SnapshotPagedList(this);
    }

    public final void addWeakCallback(@NotNull Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        i.removeAll((List) this.o, (Function1) a.INSTANCE);
        this.o.add(new WeakReference<>(callback));
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000*\b\b\u0001\u0010\u0002*\u00020\u0001*\b\b\u0002\u0010\u0003*\u00020\u00012\u00020\u0001B%\b\u0016\u0012\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0019\u0012\u0006\u0010\u001c\u001a\u00020\u001b¢\u0006\u0004\b\u001d\u0010\u001eB%\b\u0016\u0012\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0019\u0012\u0006\u0010 \u001a\u00020\u001f¢\u0006\u0004\b\u001d\u0010!B9\b\u0016\u0012\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\"\u0012\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020$\u0012\u0006\u0010\u001c\u001a\u00020\u001b¢\u0006\u0004\b\u001d\u0010&B9\b\u0016\u0012\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\"\u0012\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020$\u0012\u0006\u0010 \u001a\u00020\u001f¢\u0006\u0004\b\u001d\u0010'J\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004J\u001c\u0010\t\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00002\u0006\u0010\b\u001a\u00020\u0007H\u0007J\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00002\u0006\u0010\u000b\u001a\u00020\nJ\u001c\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00002\u0006\u0010\r\u001a\u00020\u0007H\u0007J\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00002\u0006\u0010\u000f\u001a\u00020\nJ\"\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00002\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00028\u0002\u0018\u00010\u0011J#\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00002\b\u0010\u0014\u001a\u0004\u0018\u00018\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00020\u0017¨\u0006("}, d2 = {"Landroidx/paging/PagedList$Builder;", "", "Key", "Value", "Lkotlinx/coroutines/CoroutineScope;", "coroutineScope", "setCoroutineScope", "Ljava/util/concurrent/Executor;", "notifyExecutor", "setNotifyExecutor", "Lkotlinx/coroutines/CoroutineDispatcher;", "notifyDispatcher", "setNotifyDispatcher", "fetchExecutor", "setFetchExecutor", "fetchDispatcher", "setFetchDispatcher", "Landroidx/paging/PagedList$BoundaryCallback;", "boundaryCallback", "setBoundaryCallback", "initialKey", "setInitialKey", "(Ljava/lang/Object;)Landroidx/paging/PagedList$Builder;", "Landroidx/paging/PagedList;", "build", "Landroidx/paging/DataSource;", "dataSource", "Landroidx/paging/PagedList$Config;", Constants.KEY_CONFIG, "<init>", "(Landroidx/paging/DataSource;Landroidx/paging/PagedList$Config;)V", "", "pageSize", "(Landroidx/paging/DataSource;I)V", "Landroidx/paging/PagingSource;", "pagingSource", "Landroidx/paging/PagingSource$LoadResult$Page;", "initialPage", "(Landroidx/paging/PagingSource;Landroidx/paging/PagingSource$LoadResult$Page;Landroidx/paging/PagedList$Config;)V", "(Landroidx/paging/PagingSource;Landroidx/paging/PagingSource$LoadResult$Page;I)V", "paging-common"}, k = 1, mv = {1, 5, 1})
    @Deprecated(message = "PagedList is deprecated and has been replaced by PagingData, which no longer supports constructing snapshots of loaded data manually.", replaceWith = @ReplaceWith(expression = "Pager.flow", imports = {"androidx.paging.Pager"}))
    /* loaded from: classes.dex */
    public static final class Builder<Key, Value> {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public final PagingSource<Key, Value> f1525a;
        @Nullable
        public DataSource<Key, Value> b;
        @Nullable
        public final PagingSource.LoadResult.Page<Key, Value> c;
        @NotNull
        public final Config d;
        @NotNull
        public CoroutineScope e;
        @Nullable
        public CoroutineDispatcher f;
        @Nullable
        public CoroutineDispatcher g;
        @Nullable
        public BoundaryCallback<Value> h;
        @Nullable
        public Key i;

        public Builder(@NotNull DataSource<Key, Value> dataSource, @NotNull Config config) {
            Intrinsics.checkNotNullParameter(dataSource, "dataSource");
            Intrinsics.checkNotNullParameter(config, "config");
            this.e = GlobalScope.INSTANCE;
            this.f1525a = null;
            this.b = dataSource;
            this.c = null;
            this.d = config;
        }

        @NotNull
        public final PagedList<Value> build() {
            CoroutineDispatcher coroutineDispatcher = this.g;
            if (coroutineDispatcher == null) {
                coroutineDispatcher = Dispatchers.getIO();
            }
            CoroutineDispatcher coroutineDispatcher2 = coroutineDispatcher;
            PagingSource<Key, Value> pagingSource = this.f1525a;
            if (pagingSource == null) {
                DataSource<Key, Value> dataSource = this.b;
                pagingSource = dataSource == null ? null : new LegacyPagingSource(coroutineDispatcher2, dataSource);
            }
            PagingSource<Key, Value> pagingSource2 = pagingSource;
            if (pagingSource2 instanceof LegacyPagingSource) {
                ((LegacyPagingSource) pagingSource2).setPageSize(this.d.pageSize);
            }
            if (pagingSource2 != null) {
                Companion companion = PagedList.Companion;
                PagingSource.LoadResult.Page<Key, Value> page = this.c;
                CoroutineScope coroutineScope = this.e;
                CoroutineDispatcher coroutineDispatcher3 = this.f;
                if (coroutineDispatcher3 == null) {
                    coroutineDispatcher3 = Dispatchers.getMain().getImmediate();
                }
                return companion.create(pagingSource2, page, coroutineScope, coroutineDispatcher3, coroutineDispatcher2, this.h, this.d, this.i);
            }
            throw new IllegalStateException("PagedList cannot be built without a PagingSource or DataSource".toString());
        }

        @NotNull
        public final Builder<Key, Value> setBoundaryCallback(@Nullable BoundaryCallback<Value> boundaryCallback) {
            this.h = boundaryCallback;
            return this;
        }

        @NotNull
        public final Builder<Key, Value> setCoroutineScope(@NotNull CoroutineScope coroutineScope) {
            Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
            this.e = coroutineScope;
            return this;
        }

        @NotNull
        public final Builder<Key, Value> setFetchDispatcher(@NotNull CoroutineDispatcher fetchDispatcher) {
            Intrinsics.checkNotNullParameter(fetchDispatcher, "fetchDispatcher");
            this.g = fetchDispatcher;
            return this;
        }

        @Deprecated(message = "Passing an executor will cause it get wrapped as a CoroutineDispatcher, consider passing a CoroutineDispatcher directly", replaceWith = @ReplaceWith(expression = "setFetchDispatcher(fetchExecutor.asCoroutineDispatcher())", imports = {"kotlinx.coroutines.asCoroutineDispatcher"}))
        @NotNull
        public final Builder<Key, Value> setFetchExecutor(@NotNull Executor fetchExecutor) {
            Intrinsics.checkNotNullParameter(fetchExecutor, "fetchExecutor");
            this.g = ExecutorsKt.from(fetchExecutor);
            return this;
        }

        @NotNull
        public final Builder<Key, Value> setInitialKey(@Nullable Key key) {
            this.i = key;
            return this;
        }

        @NotNull
        public final Builder<Key, Value> setNotifyDispatcher(@NotNull CoroutineDispatcher notifyDispatcher) {
            Intrinsics.checkNotNullParameter(notifyDispatcher, "notifyDispatcher");
            this.f = notifyDispatcher;
            return this;
        }

        @Deprecated(message = "Passing an executor will cause it get wrapped as a CoroutineDispatcher, consider passing a CoroutineDispatcher directly", replaceWith = @ReplaceWith(expression = "setNotifyDispatcher(fetchExecutor.asCoroutineDispatcher())", imports = {"kotlinx.coroutines.asCoroutineDispatcher"}))
        @NotNull
        public final Builder<Key, Value> setNotifyExecutor(@NotNull Executor notifyExecutor) {
            Intrinsics.checkNotNullParameter(notifyExecutor, "notifyExecutor");
            this.f = ExecutorsKt.from(notifyExecutor);
            return this;
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public Builder(@NotNull DataSource<Key, Value> dataSource, int i) {
            this(dataSource, PagedListConfigKt.Config$default(i, 0, false, 0, 0, 30, null));
            Intrinsics.checkNotNullParameter(dataSource, "dataSource");
        }

        public Builder(@NotNull PagingSource<Key, Value> pagingSource, @NotNull PagingSource.LoadResult.Page<Key, Value> initialPage, @NotNull Config config) {
            Intrinsics.checkNotNullParameter(pagingSource, "pagingSource");
            Intrinsics.checkNotNullParameter(initialPage, "initialPage");
            Intrinsics.checkNotNullParameter(config, "config");
            this.e = GlobalScope.INSTANCE;
            this.f1525a = pagingSource;
            this.b = null;
            this.c = initialPage;
            this.d = config;
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public Builder(@NotNull PagingSource<Key, Value> pagingSource, @NotNull PagingSource.LoadResult.Page<Key, Value> initialPage, int i) {
            this(pagingSource, initialPage, PagedListConfigKt.Config$default(i, 0, false, 0, 0, 30, null));
            Intrinsics.checkNotNullParameter(pagingSource, "pagingSource");
            Intrinsics.checkNotNullParameter(initialPage, "initialPage");
        }
    }
}

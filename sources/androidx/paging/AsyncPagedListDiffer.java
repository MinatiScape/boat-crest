package androidx.paging;

import androidx.annotation.VisibleForTesting;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.exifinterface.media.ExifInterface;
import androidx.paging.LoadState;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.AdapterListUpdateCallback;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListUpdateCallback;
import androidx.recyclerview.widget.RecyclerView;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.i;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.ranges.h;
import kotlin.reflect.KFunction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Deprecated(message = "AsyncPagedListDiffer is deprecated and has been replaced by AsyncPagingDataDiffer", replaceWith = @ReplaceWith(expression = "AsyncPagingDataDiffer<T>", imports = {"androidx.paging.AsyncPagingDataDiffer"}))
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010!\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0017\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0001:\u0002)[B#\b\u0017\u0012\n\u0010T\u001a\u0006\u0012\u0002\b\u00030S\u0012\f\u0010V\u001a\b\u0012\u0004\u0012\u00028\u00000U¢\u0006\u0004\bW\u0010XB\u001f\b\u0017\u0012\u0006\u0010Y\u001a\u00020!\u0012\f\u0010/\u001a\b\u0012\u0004\u0012\u00028\u00000(¢\u0006\u0004\bW\u0010ZJ\u0019\u0010\u0005\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0004\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\n\u001a\u00020\t2\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0007H\u0016J\"\u0010\n\u001a\u00020\t2\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00072\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0016JM\u0010\u0016\u001a\u00020\t2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u0016\u0010\u0019\u001a\u00020\t2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u0017H\u0016J0\u0010\u0019\u001a\u00020\t2(\u0010\u001b\u001a$\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0007\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0007\u0012\u0004\u0012\u00020\t0\u001aJ\u0016\u0010\u001c\u001a\u00020\t2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u0017H\u0016J0\u0010\u001c\u001a\u00020\t2(\u0010\u001b\u001a$\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0007\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0007\u0012\u0004\u0012\u00020\t0\u001aJ\"\u0010\u001f\u001a\u00020\t2\u0018\u0010\u0018\u001a\u0014\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\t0\u001aH\u0016J\"\u0010 \u001a\u00020\t2\u0018\u0010\u0018\u001a\u0014\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\t0\u001aH\u0016R\"\u0010\"\u001a\u00020!8\u0000@\u0000X\u0080.¢\u0006\u0012\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R(\u0010/\u001a\b\u0012\u0004\u0012\u00028\u00000(8\u0000@\u0000X\u0080\u0004¢\u0006\u0012\n\u0004\b)\u0010*\u0012\u0004\b-\u0010.\u001a\u0004\b+\u0010,R\"\u00107\u001a\u0002008\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b1\u00102\u001a\u0004\b3\u00104\"\u0004\b5\u00106R.\u0010>\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0017088\u0000@\u0001X\u0081\u0004¢\u0006\u0012\n\u0004\b9\u0010:\u0012\u0004\b=\u0010.\u001a\u0004\b;\u0010<R(\u0010F\u001a\u00020\u00038\u0000@\u0000X\u0080\u000e¢\u0006\u0018\n\u0004\b?\u0010@\u0012\u0004\bE\u0010.\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR4\u0010L\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\t0\u001a0G8\u0000@\u0000X\u0080\u0004¢\u0006\f\n\u0004\bH\u0010I\u001a\u0004\bJ\u0010KR\u0016\u0010N\u001a\u00020\u00038V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\bM\u0010BR$\u0010R\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00078V@\u0016X\u0096\u0004¢\u0006\f\u0012\u0004\bQ\u0010.\u001a\u0004\bO\u0010P¨\u0006\\"}, d2 = {"Landroidx/paging/AsyncPagedListDiffer;", "", ExifInterface.GPS_DIRECTION_TRUE, "", FirebaseAnalytics.Param.INDEX, "getItem", "(I)Ljava/lang/Object;", "Landroidx/paging/PagedList;", "pagedList", "", "submitList", "Ljava/lang/Runnable;", "commitCallback", "newList", "diffSnapshot", "Landroidx/paging/NullPaddedDiffResult;", "diffResult", "Landroidx/paging/RecordingCallback;", "recordingCallback", "lastAccessIndex", "latchPagedList$paging_runtime_release", "(Landroidx/paging/PagedList;Landroidx/paging/PagedList;Landroidx/paging/NullPaddedDiffResult;Landroidx/paging/RecordingCallback;ILjava/lang/Runnable;)V", "latchPagedList", "Landroidx/paging/AsyncPagedListDiffer$PagedListListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "addPagedListListener", "Lkotlin/Function2;", "callback", "removePagedListListener", "Landroidx/paging/LoadType;", "Landroidx/paging/LoadState;", "addLoadStateListener", "removeLoadStateListener", "Landroidx/recyclerview/widget/ListUpdateCallback;", "updateCallback", "Landroidx/recyclerview/widget/ListUpdateCallback;", "getUpdateCallback$paging_runtime_release", "()Landroidx/recyclerview/widget/ListUpdateCallback;", "setUpdateCallback$paging_runtime_release", "(Landroidx/recyclerview/widget/ListUpdateCallback;)V", "Landroidx/recyclerview/widget/AsyncDifferConfig;", "a", "Landroidx/recyclerview/widget/AsyncDifferConfig;", "getConfig$paging_runtime_release", "()Landroidx/recyclerview/widget/AsyncDifferConfig;", "getConfig$paging_runtime_release$annotations", "()V", Constants.KEY_CONFIG, "Ljava/util/concurrent/Executor;", "b", "Ljava/util/concurrent/Executor;", "getMainThreadExecutor$paging_runtime_release", "()Ljava/util/concurrent/Executor;", "setMainThreadExecutor$paging_runtime_release", "(Ljava/util/concurrent/Executor;)V", "mainThreadExecutor", "Ljava/util/concurrent/CopyOnWriteArrayList;", com.google.android.material.color.c.f10260a, "Ljava/util/concurrent/CopyOnWriteArrayList;", "getListeners$paging_runtime_release", "()Ljava/util/concurrent/CopyOnWriteArrayList;", "getListeners$paging_runtime_release$annotations", "listeners", "f", "I", "getMaxScheduledGeneration$paging_runtime_release", "()I", "setMaxScheduledGeneration$paging_runtime_release", "(I)V", "getMaxScheduledGeneration$paging_runtime_release$annotations", "maxScheduledGeneration", "", "i", "Ljava/util/List;", "getLoadStateListeners$paging_runtime_release", "()Ljava/util/List;", "loadStateListeners", "getItemCount", "itemCount", "getCurrentList", "()Landroidx/paging/PagedList;", "getCurrentList$annotations", "currentList", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "adapter", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "diffCallback", "<init>", "(Landroidx/recyclerview/widget/RecyclerView$Adapter;Landroidx/recyclerview/widget/DiffUtil$ItemCallback;)V", "listUpdateCallback", "(Landroidx/recyclerview/widget/ListUpdateCallback;Landroidx/recyclerview/widget/AsyncDifferConfig;)V", "PagedListListener", "paging-runtime_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public class AsyncPagedListDiffer<T> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final AsyncDifferConfig<T> f1471a;
    @NotNull
    public Executor b;
    @NotNull
    public final CopyOnWriteArrayList<PagedListListener<T>> c;
    @Nullable
    public PagedList<T> d;
    @Nullable
    public PagedList<T> e;
    public int f;
    @NotNull
    public final PagedList.LoadStateManager g;
    @NotNull
    public final KFunction<Unit> h;
    @NotNull
    public final List<Function2<LoadType, LoadState, Unit>> i;
    @NotNull
    public final PagedList.Callback j;
    public ListUpdateCallback updateCallback;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\bg\u0018\u0000*\b\b\u0001\u0010\u0002*\u00020\u00012\u00020\u0001J(\u0010\u0007\u001a\u00020\u00062\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00032\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0003H&¨\u0006\b"}, d2 = {"Landroidx/paging/AsyncPagedListDiffer$PagedListListener;", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/paging/PagedList;", "previousList", "currentList", "", "onCurrentListChanged", "paging-runtime_release"}, k = 1, mv = {1, 5, 1})
    @Deprecated(message = "PagedList is deprecated and has been replaced by PagingData")
    /* loaded from: classes.dex */
    public interface PagedListListener<T> {
        void onCurrentListChanged(@Nullable PagedList<T> pagedList, @Nullable PagedList<T> pagedList2);
    }

    /* loaded from: classes.dex */
    public static final class a<T> implements PagedListListener<T> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final Function2<PagedList<T>, PagedList<T>, Unit> f1472a;

        /* JADX WARN: Multi-variable type inference failed */
        public a(@NotNull Function2<? super PagedList<T>, ? super PagedList<T>, Unit> callback) {
            Intrinsics.checkNotNullParameter(callback, "callback");
            this.f1472a = callback;
        }

        @NotNull
        public final Function2<PagedList<T>, PagedList<T>, Unit> a() {
            return this.f1472a;
        }

        @Override // androidx.paging.AsyncPagedListDiffer.PagedListListener
        public void onCurrentListChanged(@Nullable PagedList<T> pagedList, @Nullable PagedList<T> pagedList2) {
            this.f1472a.invoke(pagedList, pagedList2);
        }
    }

    /* loaded from: classes.dex */
    public /* synthetic */ class b extends FunctionReferenceImpl implements Function2<LoadType, LoadState, Unit> {
        public b(Object obj) {
            super(2, obj, PagedList.LoadStateManager.class, "setState", "setState(Landroidx/paging/LoadType;Landroidx/paging/LoadState;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(LoadType loadType, LoadState loadState) {
            invoke2(loadType, loadState);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull LoadType p0, @NotNull LoadState p1) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            Intrinsics.checkNotNullParameter(p1, "p1");
            ((PagedList.LoadStateManager) this.receiver).setState(p0, p1);
        }
    }

    /* loaded from: classes.dex */
    public static final class c extends Lambda implements Function1<PagedListListener<T>, Boolean> {
        public final /* synthetic */ Function2<PagedList<T>, PagedList<T>, Unit> $callback;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public c(Function2<? super PagedList<T>, ? super PagedList<T>, Unit> function2) {
            super(1);
            this.$callback = function2;
        }

        @NotNull
        public final Boolean invoke(PagedListListener<T> pagedListListener) {
            return Boolean.valueOf((pagedListListener instanceof a) && ((a) pagedListListener).a() == this.$callback);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Boolean invoke(Object obj) {
            return invoke((PagedListListener) ((PagedListListener) obj));
        }
    }

    /* loaded from: classes.dex */
    public static final class d implements Runnable {
        public final /* synthetic */ PagedList<T> h;
        public final /* synthetic */ PagedList<T> i;
        public final /* synthetic */ AsyncPagedListDiffer<T> j;
        public final /* synthetic */ int k;
        public final /* synthetic */ PagedList<T> l;
        public final /* synthetic */ RecordingCallback m;
        public final /* synthetic */ Runnable n;

        /* loaded from: classes.dex */
        public static final class a implements Runnable {
            public final /* synthetic */ AsyncPagedListDiffer<T> h;
            public final /* synthetic */ int i;
            public final /* synthetic */ PagedList<T> j;
            public final /* synthetic */ PagedList<T> k;
            public final /* synthetic */ NullPaddedDiffResult l;
            public final /* synthetic */ RecordingCallback m;
            public final /* synthetic */ PagedList<T> n;
            public final /* synthetic */ Runnable o;

            public a(AsyncPagedListDiffer<T> asyncPagedListDiffer, int i, PagedList<T> pagedList, PagedList<T> pagedList2, NullPaddedDiffResult nullPaddedDiffResult, RecordingCallback recordingCallback, PagedList<T> pagedList3, Runnable runnable) {
                this.h = asyncPagedListDiffer;
                this.i = i;
                this.j = pagedList;
                this.k = pagedList2;
                this.l = nullPaddedDiffResult;
                this.m = recordingCallback;
                this.n = pagedList3;
                this.o = runnable;
            }

            @Override // java.lang.Runnable
            public final void run() {
                if (this.h.getMaxScheduledGeneration$paging_runtime_release() == this.i) {
                    this.h.latchPagedList$paging_runtime_release(this.j, this.k, this.l, this.m, this.n.lastLoad(), this.o);
                }
            }
        }

        public d(PagedList<T> pagedList, PagedList<T> pagedList2, AsyncPagedListDiffer<T> asyncPagedListDiffer, int i, PagedList<T> pagedList3, RecordingCallback recordingCallback, Runnable runnable) {
            this.h = pagedList;
            this.i = pagedList2;
            this.j = asyncPagedListDiffer;
            this.k = i;
            this.l = pagedList3;
            this.m = recordingCallback;
            this.n = runnable;
        }

        @Override // java.lang.Runnable
        public final void run() {
            NullPaddedList<T> nullPaddedList = this.h.getNullPaddedList();
            NullPaddedList<T> nullPaddedList2 = this.i.getNullPaddedList();
            DiffUtil.ItemCallback<T> diffCallback = this.j.getConfig$paging_runtime_release().getDiffCallback();
            Intrinsics.checkNotNullExpressionValue(diffCallback, "config.diffCallback");
            this.j.getMainThreadExecutor$paging_runtime_release().execute(new a(this.j, this.k, this.l, this.i, NullPaddedListDiffHelperKt.computeDiff(nullPaddedList, nullPaddedList2, diffCallback), this.m, this.h, this.n));
        }
    }

    @Deprecated(message = "PagedList is deprecated and has been replaced by PagingData", replaceWith = @ReplaceWith(expression = "AsyncPagingDataDiffer(\n                Dispatchers.Main,\n                Dispatchers.IO,\n                diffCallback,\n                listUpdateCallback\n            )", imports = {"androidx.paging.AsyncPagingDataDiffer", "kotlinx.coroutines.Dispatchers"}))
    public AsyncPagedListDiffer(@NotNull RecyclerView.Adapter<?> adapter, @NotNull DiffUtil.ItemCallback<T> diffCallback) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(diffCallback, "diffCallback");
        Executor mainThreadExecutor = ArchTaskExecutor.getMainThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(mainThreadExecutor, "getMainThreadExecutor()");
        this.b = mainThreadExecutor;
        this.c = new CopyOnWriteArrayList<>();
        PagedList.LoadStateManager loadStateManager = new PagedList.LoadStateManager(this) { // from class: androidx.paging.AsyncPagedListDiffer$loadStateManager$1
            public final /* synthetic */ AsyncPagedListDiffer<T> d;

            {
                this.d = this;
            }

            @Override // androidx.paging.PagedList.LoadStateManager
            public void onStateChanged(@NotNull LoadType type, @NotNull LoadState state) {
                Intrinsics.checkNotNullParameter(type, "type");
                Intrinsics.checkNotNullParameter(state, "state");
                Iterator<T> it = this.d.getLoadStateListeners$paging_runtime_release().iterator();
                while (it.hasNext()) {
                    ((Function2) it.next()).invoke(type, state);
                }
            }
        };
        this.g = loadStateManager;
        this.h = new b(loadStateManager);
        this.i = new CopyOnWriteArrayList();
        this.j = new PagedList.Callback(this) { // from class: androidx.paging.AsyncPagedListDiffer$pagedListCallback$1

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ AsyncPagedListDiffer<T> f1473a;

            {
                this.f1473a = this;
            }

            @Override // androidx.paging.PagedList.Callback
            public void onChanged(int i, int i2) {
                this.f1473a.getUpdateCallback$paging_runtime_release().onChanged(i, i2, null);
            }

            @Override // androidx.paging.PagedList.Callback
            public void onInserted(int i, int i2) {
                this.f1473a.getUpdateCallback$paging_runtime_release().onInserted(i, i2);
            }

            @Override // androidx.paging.PagedList.Callback
            public void onRemoved(int i, int i2) {
                this.f1473a.getUpdateCallback$paging_runtime_release().onRemoved(i, i2);
            }
        };
        setUpdateCallback$paging_runtime_release(new AdapterListUpdateCallback(adapter));
        AsyncDifferConfig<T> build = new AsyncDifferConfig.Builder(diffCallback).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder(diffCallback).build()");
        this.f1471a = build;
    }

    public static /* synthetic */ void getConfig$paging_runtime_release$annotations() {
    }

    public static /* synthetic */ void getCurrentList$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void getListeners$paging_runtime_release$annotations() {
    }

    public static /* synthetic */ void getMaxScheduledGeneration$paging_runtime_release$annotations() {
    }

    public final void a(PagedList<T> pagedList, PagedList<T> pagedList2, Runnable runnable) {
        Iterator<T> it = this.c.iterator();
        while (it.hasNext()) {
            ((PagedListListener) it.next()).onCurrentListChanged(pagedList, pagedList2);
        }
        if (runnable == null) {
            return;
        }
        runnable.run();
    }

    public void addLoadStateListener(@NotNull Function2<? super LoadType, ? super LoadState, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        PagedList<T> pagedList = this.d;
        if (pagedList != null) {
            pagedList.addWeakLoadStateListener(listener);
        } else {
            this.g.dispatchCurrentLoadState(listener);
        }
        this.i.add(listener);
    }

    public void addPagedListListener(@NotNull PagedListListener<T> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.c.add(listener);
    }

    @NotNull
    public final AsyncDifferConfig<T> getConfig$paging_runtime_release() {
        return this.f1471a;
    }

    @Nullable
    public PagedList<T> getCurrentList() {
        PagedList<T> pagedList = this.e;
        return pagedList == null ? this.d : pagedList;
    }

    @Nullable
    public T getItem(int i) {
        PagedList<T> pagedList = this.e;
        PagedList<T> pagedList2 = this.d;
        if (pagedList != null) {
            return pagedList.get(i);
        }
        if (pagedList2 != null) {
            pagedList2.loadAround(i);
            return pagedList2.get(i);
        }
        throw new IndexOutOfBoundsException("Item count is zero, getItem() call is invalid");
    }

    public int getItemCount() {
        PagedList<T> currentList = getCurrentList();
        if (currentList == null) {
            return 0;
        }
        return currentList.size();
    }

    @NotNull
    public final CopyOnWriteArrayList<PagedListListener<T>> getListeners$paging_runtime_release() {
        return this.c;
    }

    @NotNull
    public final List<Function2<LoadType, LoadState, Unit>> getLoadStateListeners$paging_runtime_release() {
        return this.i;
    }

    @NotNull
    public final Executor getMainThreadExecutor$paging_runtime_release() {
        return this.b;
    }

    public final int getMaxScheduledGeneration$paging_runtime_release() {
        return this.f;
    }

    @NotNull
    public final ListUpdateCallback getUpdateCallback$paging_runtime_release() {
        ListUpdateCallback listUpdateCallback = this.updateCallback;
        if (listUpdateCallback != null) {
            return listUpdateCallback;
        }
        Intrinsics.throwUninitializedPropertyAccessException("updateCallback");
        return null;
    }

    public final void latchPagedList$paging_runtime_release(@NotNull PagedList<T> newList, @NotNull PagedList<T> diffSnapshot, @NotNull NullPaddedDiffResult diffResult, @NotNull RecordingCallback recordingCallback, int i, @Nullable Runnable runnable) {
        Intrinsics.checkNotNullParameter(newList, "newList");
        Intrinsics.checkNotNullParameter(diffSnapshot, "diffSnapshot");
        Intrinsics.checkNotNullParameter(diffResult, "diffResult");
        Intrinsics.checkNotNullParameter(recordingCallback, "recordingCallback");
        PagedList<T> pagedList = this.e;
        if (pagedList != null && this.d == null) {
            this.d = newList;
            newList.addWeakLoadStateListener((Function2) this.h);
            this.e = null;
            NullPaddedListDiffHelperKt.dispatchDiff(pagedList.getNullPaddedList(), getUpdateCallback$paging_runtime_release(), diffSnapshot.getNullPaddedList(), diffResult);
            recordingCallback.dispatchRecordingTo(this.j);
            newList.addWeakCallback(this.j);
            if (!newList.isEmpty()) {
                newList.loadAround(h.coerceIn(NullPaddedListDiffHelperKt.transformAnchorIndex(pagedList.getNullPaddedList(), diffResult, diffSnapshot.getNullPaddedList(), i), 0, newList.size() - 1));
            }
            a(pagedList, this.d, runnable);
            return;
        }
        throw new IllegalStateException("must be in snapshot state to apply diff");
    }

    public void removeLoadStateListener(@NotNull Function2<? super LoadType, ? super LoadState, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.i.remove(listener);
        PagedList<T> pagedList = this.d;
        if (pagedList == null) {
            return;
        }
        pagedList.removeWeakLoadStateListener(listener);
    }

    public void removePagedListListener(@NotNull PagedListListener<T> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.c.remove(listener);
    }

    public final void setMainThreadExecutor$paging_runtime_release(@NotNull Executor executor) {
        Intrinsics.checkNotNullParameter(executor, "<set-?>");
        this.b = executor;
    }

    public final void setMaxScheduledGeneration$paging_runtime_release(int i) {
        this.f = i;
    }

    public final void setUpdateCallback$paging_runtime_release(@NotNull ListUpdateCallback listUpdateCallback) {
        Intrinsics.checkNotNullParameter(listUpdateCallback, "<set-?>");
        this.updateCallback = listUpdateCallback;
    }

    public void submitList(@Nullable PagedList<T> pagedList) {
        submitList(pagedList, null);
    }

    public final void addPagedListListener(@NotNull Function2<? super PagedList<T>, ? super PagedList<T>, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.c.add(new a(callback));
    }

    public final void removePagedListListener(@NotNull Function2<? super PagedList<T>, ? super PagedList<T>, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        i.removeAll((List) this.c, (Function1) new c(callback));
    }

    public void submitList(@Nullable PagedList<T> pagedList, @Nullable Runnable runnable) {
        int i = this.f + 1;
        this.f = i;
        PagedList<T> pagedList2 = this.d;
        if (pagedList == pagedList2) {
            if (runnable == null) {
                return;
            }
            runnable.run();
        } else if (pagedList2 != null && (pagedList instanceof InitialPagedList)) {
            pagedList2.removeWeakCallback(this.j);
            pagedList2.removeWeakLoadStateListener((Function2) this.h);
            this.g.setState(LoadType.REFRESH, LoadState.Loading.INSTANCE);
            this.g.setState(LoadType.PREPEND, new LoadState.NotLoading(false));
            this.g.setState(LoadType.APPEND, new LoadState.NotLoading(false));
            if (runnable == null) {
                return;
            }
            runnable.run();
        } else {
            PagedList<T> currentList = getCurrentList();
            if (pagedList == null) {
                int itemCount = getItemCount();
                if (pagedList2 != null) {
                    pagedList2.removeWeakCallback(this.j);
                    pagedList2.removeWeakLoadStateListener((Function2) this.h);
                    this.d = null;
                } else if (this.e != null) {
                    this.e = null;
                }
                getUpdateCallback$paging_runtime_release().onRemoved(0, itemCount);
                a(currentList, null, runnable);
            } else if (getCurrentList() == null) {
                this.d = pagedList;
                pagedList.addWeakLoadStateListener((Function2) this.h);
                pagedList.addWeakCallback(this.j);
                getUpdateCallback$paging_runtime_release().onInserted(0, pagedList.size());
                a(null, pagedList, runnable);
            } else {
                PagedList<T> pagedList3 = this.d;
                if (pagedList3 != null) {
                    pagedList3.removeWeakCallback(this.j);
                    pagedList3.removeWeakLoadStateListener((Function2) this.h);
                    this.e = (PagedList) pagedList3.snapshot();
                    this.d = null;
                }
                PagedList<T> pagedList4 = this.e;
                if (pagedList4 != null && this.d == null) {
                    PagedList pagedList5 = (PagedList) pagedList.snapshot();
                    RecordingCallback recordingCallback = new RecordingCallback();
                    pagedList.addWeakCallback(recordingCallback);
                    this.f1471a.getBackgroundThreadExecutor().execute(new d(pagedList4, pagedList5, this, i, pagedList, recordingCallback, runnable));
                    return;
                }
                throw new IllegalStateException("must be in snapshot state to diff");
            }
        }
    }

    @Deprecated(message = "PagedList is deprecated and has been replaced by PagingData", replaceWith = @ReplaceWith(expression = "AsyncPagingDataDiffer(\n                Dispatchers.Main,\n                Dispatchers.IO,\n                config.diffCallback,\n                listUpdateCallback\n            )", imports = {"androidx.paging.AsyncPagingDataDiffer", "kotlinx.coroutines.Dispatchers"}))
    public AsyncPagedListDiffer(@NotNull ListUpdateCallback listUpdateCallback, @NotNull AsyncDifferConfig<T> config) {
        Intrinsics.checkNotNullParameter(listUpdateCallback, "listUpdateCallback");
        Intrinsics.checkNotNullParameter(config, "config");
        Executor mainThreadExecutor = ArchTaskExecutor.getMainThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(mainThreadExecutor, "getMainThreadExecutor()");
        this.b = mainThreadExecutor;
        this.c = new CopyOnWriteArrayList<>();
        PagedList.LoadStateManager loadStateManager = new PagedList.LoadStateManager(this) { // from class: androidx.paging.AsyncPagedListDiffer$loadStateManager$1
            public final /* synthetic */ AsyncPagedListDiffer<T> d;

            {
                this.d = this;
            }

            @Override // androidx.paging.PagedList.LoadStateManager
            public void onStateChanged(@NotNull LoadType type, @NotNull LoadState state) {
                Intrinsics.checkNotNullParameter(type, "type");
                Intrinsics.checkNotNullParameter(state, "state");
                Iterator<T> it = this.d.getLoadStateListeners$paging_runtime_release().iterator();
                while (it.hasNext()) {
                    ((Function2) it.next()).invoke(type, state);
                }
            }
        };
        this.g = loadStateManager;
        this.h = new b(loadStateManager);
        this.i = new CopyOnWriteArrayList();
        this.j = new PagedList.Callback(this) { // from class: androidx.paging.AsyncPagedListDiffer$pagedListCallback$1

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ AsyncPagedListDiffer<T> f1473a;

            {
                this.f1473a = this;
            }

            @Override // androidx.paging.PagedList.Callback
            public void onChanged(int i, int i2) {
                this.f1473a.getUpdateCallback$paging_runtime_release().onChanged(i, i2, null);
            }

            @Override // androidx.paging.PagedList.Callback
            public void onInserted(int i, int i2) {
                this.f1473a.getUpdateCallback$paging_runtime_release().onInserted(i, i2);
            }

            @Override // androidx.paging.PagedList.Callback
            public void onRemoved(int i, int i2) {
                this.f1473a.getUpdateCallback$paging_runtime_release().onRemoved(i, i2);
            }
        };
        setUpdateCallback$paging_runtime_release(listUpdateCallback);
        this.f1471a = config;
    }
}

package androidx.paging;

import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.AdapterListUpdateCallback;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.jstyle.blesdk1860.constant.DeviceKey;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Deprecated(message = "PagedListAdapter is deprecated and has been replaced by PagingDataAdapter", replaceWith = @ReplaceWith(expression = "PagingDataAdapter<T, VH>", imports = {"androidx.paging.PagingDataAdapter"}))
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b'\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0004*\u00020\u00032\b\u0012\u0004\u0012\u00028\u00010\u0005B\u0017\b\u0014\u0012\f\u0010-\u001a\b\u0012\u0004\u0012\u00028\u00000,¢\u0006\u0004\b.\u0010/B\u0017\b\u0014\u0012\f\u00101\u001a\b\u0012\u0004\u0012\u00028\u000000¢\u0006\u0004\b.\u00102J\u0018\u0010\t\u001a\u00020\b2\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006H\u0016J\"\u0010\t\u001a\u00020\b2\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016J\u0019\u0010\u000e\u001a\u0004\u0018\u00018\u00002\u0006\u0010\r\u001a\u00020\fH\u0014¢\u0006\u0004\b\u000e\u0010\u000fJ\b\u0010\u0010\u001a\u00020\fH\u0016J\u0018\u0010\u0012\u001a\u00020\b2\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006H\u0017J(\u0010\u0012\u001a\u00020\b2\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00062\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006H\u0016J\"\u0010\u0018\u001a\u00020\b2\u0018\u0010\u0017\u001a\u0014\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\b0\u0014H\u0016J\"\u0010\u0019\u001a\u00020\b2\u0018\u0010\u0017\u001a\u0014\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\b0\u0014H\u0016J\u0012\u0010\u001d\u001a\u00020\u001c2\n\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u001aJ\u0012\u0010\u001f\u001a\u00020\u001c2\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001aJ\u001e\u0010 \u001a\u00020\u001c2\n\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u001a2\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001aR(\u0010(\u001a\b\u0012\u0004\u0012\u00028\u00000!8\u0000@\u0000X\u0080\u0004¢\u0006\u0012\n\u0004\b\"\u0010#\u0012\u0004\b&\u0010'\u001a\u0004\b$\u0010%R$\u0010\u0011\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00068V@\u0016X\u0096\u0004¢\u0006\f\u0012\u0004\b+\u0010'\u001a\u0004\b)\u0010*¨\u00063"}, d2 = {"Landroidx/paging/PagedListAdapter;", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "VH", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/paging/PagedList;", "pagedList", "", "submitList", "Ljava/lang/Runnable;", "commitCallback", "", DeviceKey.position, "getItem", "(I)Ljava/lang/Object;", "getItemCount", "currentList", "onCurrentListChanged", "previousList", "Lkotlin/Function2;", "Landroidx/paging/LoadType;", "Landroidx/paging/LoadState;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "addLoadStateListener", "removeLoadStateListener", "Landroidx/paging/LoadStateAdapter;", "header", "Landroidx/recyclerview/widget/ConcatAdapter;", "withLoadStateHeader", "footer", "withLoadStateFooter", "withLoadStateHeaderAndFooter", "Landroidx/paging/AsyncPagedListDiffer;", "a", "Landroidx/paging/AsyncPagedListDiffer;", "getDiffer$paging_runtime_release", "()Landroidx/paging/AsyncPagedListDiffer;", "getDiffer$paging_runtime_release$annotations", "()V", "differ", "getCurrentList", "()Landroidx/paging/PagedList;", "getCurrentList$annotations", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "diffCallback", "<init>", "(Landroidx/recyclerview/widget/DiffUtil$ItemCallback;)V", "Landroidx/recyclerview/widget/AsyncDifferConfig;", Constants.KEY_CONFIG, "(Landroidx/recyclerview/widget/AsyncDifferConfig;)V", "paging-runtime_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public abstract class PagedListAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final AsyncPagedListDiffer<T> f1528a;
    @NotNull
    public final Function2<PagedList<T>, PagedList<T>, Unit> b;

    /* loaded from: classes.dex */
    public static final class a extends Lambda implements Function2<PagedList<T>, PagedList<T>, Unit> {
        public final /* synthetic */ PagedListAdapter<T, VH> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(PagedListAdapter<T, VH> pagedListAdapter) {
            super(2);
            this.this$0 = pagedListAdapter;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Object obj, Object obj2) {
            invoke((PagedList) ((PagedList) obj), (PagedList) ((PagedList) obj2));
            return Unit.INSTANCE;
        }

        public final void invoke(@Nullable PagedList<T> pagedList, @Nullable PagedList<T> pagedList2) {
            this.this$0.onCurrentListChanged(pagedList2);
            this.this$0.onCurrentListChanged(pagedList, pagedList2);
        }
    }

    /* loaded from: classes.dex */
    public static final class b extends Lambda implements Function2<LoadType, LoadState, Unit> {
        public final /* synthetic */ LoadStateAdapter<?> $footer;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(LoadStateAdapter<?> loadStateAdapter) {
            super(2);
            this.$footer = loadStateAdapter;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(LoadType loadType, LoadState loadState) {
            invoke2(loadType, loadState);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull LoadType loadType, @NotNull LoadState loadState) {
            Intrinsics.checkNotNullParameter(loadType, "loadType");
            Intrinsics.checkNotNullParameter(loadState, "loadState");
            if (loadType == LoadType.APPEND) {
                this.$footer.setLoadState(loadState);
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class c extends Lambda implements Function2<LoadType, LoadState, Unit> {
        public final /* synthetic */ LoadStateAdapter<?> $header;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(LoadStateAdapter<?> loadStateAdapter) {
            super(2);
            this.$header = loadStateAdapter;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(LoadType loadType, LoadState loadState) {
            invoke2(loadType, loadState);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull LoadType loadType, @NotNull LoadState loadState) {
            Intrinsics.checkNotNullParameter(loadType, "loadType");
            Intrinsics.checkNotNullParameter(loadState, "loadState");
            if (loadType == LoadType.PREPEND) {
                this.$header.setLoadState(loadState);
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class d extends Lambda implements Function2<LoadType, LoadState, Unit> {
        public final /* synthetic */ LoadStateAdapter<?> $footer;
        public final /* synthetic */ LoadStateAdapter<?> $header;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(LoadStateAdapter<?> loadStateAdapter, LoadStateAdapter<?> loadStateAdapter2) {
            super(2);
            this.$header = loadStateAdapter;
            this.$footer = loadStateAdapter2;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(LoadType loadType, LoadState loadState) {
            invoke2(loadType, loadState);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull LoadType loadType, @NotNull LoadState loadState) {
            Intrinsics.checkNotNullParameter(loadType, "loadType");
            Intrinsics.checkNotNullParameter(loadState, "loadState");
            if (loadType == LoadType.PREPEND) {
                this.$header.setLoadState(loadState);
            } else if (loadType == LoadType.APPEND) {
                this.$footer.setLoadState(loadState);
            }
        }
    }

    public PagedListAdapter(@NotNull DiffUtil.ItemCallback<T> diffCallback) {
        Intrinsics.checkNotNullParameter(diffCallback, "diffCallback");
        a aVar = new a(this);
        this.b = aVar;
        AsyncPagedListDiffer<T> asyncPagedListDiffer = new AsyncPagedListDiffer<>(this, diffCallback);
        this.f1528a = asyncPagedListDiffer;
        asyncPagedListDiffer.addPagedListListener(aVar);
    }

    public static /* synthetic */ void getCurrentList$annotations() {
    }

    public static /* synthetic */ void getDiffer$paging_runtime_release$annotations() {
    }

    public void addLoadStateListener(@NotNull Function2<? super LoadType, ? super LoadState, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f1528a.addLoadStateListener(listener);
    }

    @Nullable
    public PagedList<T> getCurrentList() {
        return this.f1528a.getCurrentList();
    }

    @NotNull
    public final AsyncPagedListDiffer<T> getDiffer$paging_runtime_release() {
        return this.f1528a;
    }

    @Nullable
    public T getItem(int i) {
        return this.f1528a.getItem(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f1528a.getItemCount();
    }

    @Deprecated(message = "Use the two argument variant instead.", replaceWith = @ReplaceWith(expression = "onCurrentListChanged(previousList, currentList)", imports = {}))
    public void onCurrentListChanged(@Nullable PagedList<T> pagedList) {
    }

    public void onCurrentListChanged(@Nullable PagedList<T> pagedList, @Nullable PagedList<T> pagedList2) {
    }

    public void removeLoadStateListener(@NotNull Function2<? super LoadType, ? super LoadState, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f1528a.removeLoadStateListener(listener);
    }

    public void submitList(@Nullable PagedList<T> pagedList) {
        this.f1528a.submitList(pagedList);
    }

    @NotNull
    public final ConcatAdapter withLoadStateFooter(@NotNull LoadStateAdapter<?> footer) {
        Intrinsics.checkNotNullParameter(footer, "footer");
        addLoadStateListener(new b(footer));
        return new ConcatAdapter(this, footer);
    }

    @NotNull
    public final ConcatAdapter withLoadStateHeader(@NotNull LoadStateAdapter<?> header) {
        Intrinsics.checkNotNullParameter(header, "header");
        addLoadStateListener(new c(header));
        return new ConcatAdapter(header, this);
    }

    @NotNull
    public final ConcatAdapter withLoadStateHeaderAndFooter(@NotNull LoadStateAdapter<?> header, @NotNull LoadStateAdapter<?> footer) {
        Intrinsics.checkNotNullParameter(header, "header");
        Intrinsics.checkNotNullParameter(footer, "footer");
        addLoadStateListener(new d(header, footer));
        return new ConcatAdapter(header, this, footer);
    }

    public void submitList(@Nullable PagedList<T> pagedList, @Nullable Runnable runnable) {
        this.f1528a.submitList(pagedList, runnable);
    }

    public PagedListAdapter(@NotNull AsyncDifferConfig<T> config) {
        Intrinsics.checkNotNullParameter(config, "config");
        a aVar = new a(this);
        this.b = aVar;
        AsyncPagedListDiffer<T> asyncPagedListDiffer = new AsyncPagedListDiffer<>(new AdapterListUpdateCallback(this), config);
        this.f1528a = asyncPagedListDiffer;
        asyncPagedListDiffer.addPagedListListener(aVar);
    }
}

package androidx.paging;

import androidx.annotation.RestrictTo;
import androidx.exifinterface.media.ExifInterface;
import androidx.paging.LegacyPageFetcher;
import androidx.paging.PagedList;
import androidx.paging.PagingSource;
import com.clevertap.android.sdk.Constants;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.ble.event.stat.one.d;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.h;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000e\n\u0002\b(\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u00032\b\u0012\u0004\u0012\u00020\u00010\u00042\b\u0012\u0004\u0012\u00028\u00000\u0005:\u0001TB\t\b\u0016¢\u0006\u0004\bO\u0010PB+\b\u0016\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0010\u0010\n\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00000\t\u0012\u0006\u0010\u000b\u001a\u00020\u0007¢\u0006\u0004\bO\u0010QB\u0017\b\u0012\u0012\f\u0010R\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000¢\u0006\u0004\bO\u0010SJ\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000JD\u0010\u0012\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u00072\u0010\u0010\n\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00000\t2\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\u000fH\u0007J\u0017\u0010\u0014\u001a\u00028\u00002\u0006\u0010\u0013\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u0019\u001a\u000e\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00182\u0006\u0010\u0017\u001a\u00020\u0016J\u001a\u0010\u001b\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u001a\u001a\u00020\u0007H\u0096\u0002¢\u0006\u0004\b\u001b\u0010\u0015J\u0016\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0007J\u0016\u0010\u001f\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0007J\u001e\u0010!\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u0007J/\u0010%\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\rH\u0000¢\u0006\u0004\b#\u0010$J/\u0010'\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\rH\u0000¢\u0006\u0004\b&\u0010$J-\u0010*\u001a\u00020\u00112\u0010\u0010\n\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00000\t2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0000¢\u0006\u0004\b(\u0010)J-\u0010,\u001a\u00020\u00112\u0010\u0010\n\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00000\t2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0000¢\u0006\u0004\b+\u0010)J\b\u0010.\u001a\u00020-H\u0016R$\u00104\u001a\u00020\u00072\u0006\u0010/\u001a\u00020\u00078\u0016@RX\u0096\u000e¢\u0006\f\n\u0004\b0\u00101\u001a\u0004\b2\u00103R$\u00107\u001a\u00020\u00072\u0006\u0010/\u001a\u00020\u00078\u0016@RX\u0096\u000e¢\u0006\f\n\u0004\b5\u00101\u001a\u0004\b6\u00103R$\u0010\f\u001a\u00020\u00072\u0006\u0010/\u001a\u00020\u00078\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b8\u00101\u001a\u0004\b9\u00103R$\u0010<\u001a\u00020\u00072\u0006\u0010/\u001a\u00020\u00078\u0016@RX\u0096\u000e¢\u0006\f\n\u0004\b:\u00101\u001a\u0004\b;\u00103R\u0016\u0010?\u001a\u00028\u00008@@\u0000X\u0080\u0004¢\u0006\u0006\u001a\u0004\b=\u0010>R\u0016\u0010A\u001a\u00028\u00008@@\u0000X\u0080\u0004¢\u0006\u0006\u001a\u0004\b@\u0010>R$\u0010F\u001a\u00020\u00072\u0006\u0010B\u001a\u00020\u00078F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bC\u00103\"\u0004\bD\u0010ER\u0013\u0010H\u001a\u00020\u00078F@\u0006¢\u0006\u0006\u001a\u0004\bG\u00103R\u0018\u0010J\u001a\u0004\u0018\u00010\u00018V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\bI\u0010>R\u0018\u0010L\u001a\u0004\u0018\u00010\u00018V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\bK\u0010>R\u0016\u0010N\u001a\u00020\u00078V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\bM\u00103¨\u0006U"}, d2 = {"Landroidx/paging/PagedStorage;", "", ExifInterface.GPS_DIRECTION_TRUE, "Ljava/util/AbstractList;", "Landroidx/paging/LegacyPageFetcher$KeyProvider;", "Landroidx/paging/NullPaddedList;", "snapshot", "", "leadingNulls", "Landroidx/paging/PagingSource$LoadResult$Page;", "page", "trailingNulls", "positionOffset", "Landroidx/paging/PagedStorage$Callback;", "callback", "", "counted", "", d.m, "localIndex", "getFromStorage", "(I)Ljava/lang/Object;", "Landroidx/paging/PagedList$Config;", Constants.KEY_CONFIG, "Landroidx/paging/PagingState;", "getRefreshKeyInfo", FirebaseAnalytics.Param.INDEX, "get", "maxSize", "requiredRemaining", "needsTrimFromFront", "needsTrimFromEnd", "countToBeAdded", "shouldPreTrimNewPage", "insertNulls", "trimFromFront$paging_common", "(ZIILandroidx/paging/PagedStorage$Callback;)Z", "trimFromFront", "trimFromEnd$paging_common", "trimFromEnd", "prependPage$paging_common", "(Landroidx/paging/PagingSource$LoadResult$Page;Landroidx/paging/PagedStorage$Callback;)V", "prependPage", "appendPage$paging_common", "appendPage", "", "toString", "<set-?>", "i", "I", "getPlaceholdersBefore", "()I", "placeholdersBefore", "j", "getPlaceholdersAfter", "placeholdersAfter", OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME, "getPositionOffset", "m", "getStorageCount", "storageCount", "getFirstLoadedItem$paging_common", "()Ljava/lang/Object;", "firstLoadedItem", "getLastLoadedItem$paging_common", "lastLoadedItem", "value", "getLastLoadAroundIndex", "setLastLoadAroundIndex", "(I)V", "lastLoadAroundIndex", "getMiddleOfLoadedRange", "middleOfLoadedRange", "getPrevKey", "prevKey", "getNextKey", "nextKey", "getSize", "size", "<init>", "()V", "(ILandroidx/paging/PagingSource$LoadResult$Page;I)V", FitnessActivities.OTHER, "(Landroidx/paging/PagedStorage;)V", "Callback", "paging-common"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class PagedStorage<T> extends AbstractList<T> implements LegacyPageFetcher.KeyProvider<Object>, NullPaddedList<T> {
    @NotNull
    public final List<PagingSource.LoadResult.Page<?, T>> h;
    public int i;
    public int j;
    public int k;
    public boolean l;
    public int m;
    public int n;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&J \u0010\t\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H&J \u0010\u000b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H&J\u0018\u0010\r\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H&J\u0018\u0010\u000e\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H&¨\u0006\u000f"}, d2 = {"Landroidx/paging/PagedStorage$Callback;", "", "", "count", "", "onInitialized", "leadingNulls", "changed", "added", "onPagePrepended", "endPosition", "onPageAppended", "startOfDrops", "onPagesRemoved", "onPagesSwappedToPlaceholder", "paging-common"}, k = 1, mv = {1, 5, 1})
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public interface Callback {
        void onInitialized(int i);

        void onPageAppended(int i, int i2, int i3);

        void onPagePrepended(int i, int i2, int i3);

        void onPagesRemoved(int i, int i2);

        void onPagesSwappedToPlaceholder(int i, int i2);
    }

    public PagedStorage() {
        this.h = new ArrayList();
        this.l = true;
    }

    public static /* synthetic */ void appendPage$paging_common$default(PagedStorage pagedStorage, PagingSource.LoadResult.Page page, Callback callback, int i, Object obj) {
        if ((i & 2) != 0) {
            callback = null;
        }
        pagedStorage.appendPage$paging_common(page, callback);
    }

    public static /* synthetic */ void init$default(PagedStorage pagedStorage, int i, PagingSource.LoadResult.Page page, int i2, int i3, Callback callback, boolean z, int i4, Object obj) {
        if ((i4 & 32) != 0) {
            z = true;
        }
        pagedStorage.init(i, page, i2, i3, callback, z);
    }

    public static /* synthetic */ void prependPage$paging_common$default(PagedStorage pagedStorage, PagingSource.LoadResult.Page page, Callback callback, int i, Object obj) {
        if ((i & 2) != 0) {
            callback = null;
        }
        pagedStorage.prependPage$paging_common(page, callback);
    }

    public final void a(int i, PagingSource.LoadResult.Page<?, T> page, int i2, int i3, boolean z) {
        this.i = i;
        this.h.clear();
        this.h.add(page);
        this.j = i2;
        this.k = i3;
        this.m = page.getData().size();
        this.l = z;
        this.n = page.getData().size() / 2;
    }

    public final void appendPage$paging_common(@NotNull PagingSource.LoadResult.Page<?, T> page, @Nullable Callback callback) {
        Intrinsics.checkNotNullParameter(page, "page");
        int size = page.getData().size();
        if (size == 0) {
            return;
        }
        this.h.add(page);
        this.m = getStorageCount() + size;
        int min = Math.min(getPlaceholdersAfter(), size);
        int i = size - min;
        if (min != 0) {
            this.j = getPlaceholdersAfter() - min;
        }
        if (callback == null) {
            return;
        }
        callback.onPageAppended((getPlaceholdersBefore() + getStorageCount()) - size, min, i);
    }

    public final boolean b(int i, int i2, int i3) {
        return getStorageCount() > i && this.h.size() > 2 && getStorageCount() - this.h.get(i3).getData().size() >= i2;
    }

    @Override // java.util.AbstractList, java.util.List
    @Nullable
    public T get(int i) {
        int placeholdersBefore = i - getPlaceholdersBefore();
        if (i >= 0 && i < size()) {
            if (placeholdersBefore < 0 || placeholdersBefore >= getStorageCount()) {
                return null;
            }
            return getFromStorage(placeholdersBefore);
        }
        throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + size());
    }

    @NotNull
    public final T getFirstLoadedItem$paging_common() {
        return (T) CollectionsKt___CollectionsKt.first((List<? extends Object>) ((PagingSource.LoadResult.Page) CollectionsKt___CollectionsKt.first((List<? extends Object>) this.h)).getData());
    }

    @Override // androidx.paging.NullPaddedList
    @NotNull
    public T getFromStorage(int i) {
        int size = this.h.size();
        int i2 = 0;
        while (i2 < size) {
            int size2 = ((PagingSource.LoadResult.Page) this.h.get(i2)).getData().size();
            if (size2 > i) {
                break;
            }
            i -= size2;
            i2++;
        }
        return (T) ((PagingSource.LoadResult.Page) this.h.get(i2)).getData().get(i);
    }

    public final int getLastLoadAroundIndex() {
        return getPlaceholdersBefore() + this.n;
    }

    @NotNull
    public final T getLastLoadedItem$paging_common() {
        return (T) CollectionsKt___CollectionsKt.last((List<? extends Object>) ((PagingSource.LoadResult.Page) CollectionsKt___CollectionsKt.last((List<? extends Object>) this.h)).getData());
    }

    public final int getMiddleOfLoadedRange() {
        return getPlaceholdersBefore() + (getStorageCount() / 2);
    }

    @Override // androidx.paging.LegacyPageFetcher.KeyProvider
    @Nullable
    public Object getNextKey() {
        if (!this.l || getPlaceholdersAfter() > 0) {
            return ((PagingSource.LoadResult.Page) CollectionsKt___CollectionsKt.last((List<? extends Object>) this.h)).getNextKey();
        }
        return null;
    }

    @Override // androidx.paging.NullPaddedList
    public int getPlaceholdersAfter() {
        return this.j;
    }

    @Override // androidx.paging.NullPaddedList
    public int getPlaceholdersBefore() {
        return this.i;
    }

    public final int getPositionOffset() {
        return this.k;
    }

    @Override // androidx.paging.LegacyPageFetcher.KeyProvider
    @Nullable
    public Object getPrevKey() {
        if (!this.l || getPlaceholdersBefore() + this.k > 0) {
            return ((PagingSource.LoadResult.Page) CollectionsKt___CollectionsKt.first((List<? extends Object>) this.h)).getPrevKey();
        }
        return null;
    }

    @Nullable
    public final PagingState<?, T> getRefreshKeyInfo(@NotNull PagedList.Config config) {
        Intrinsics.checkNotNullParameter(config, "config");
        if (this.h.isEmpty()) {
            return null;
        }
        return new PagingState<>(CollectionsKt___CollectionsKt.toList(this.h), Integer.valueOf(getLastLoadAroundIndex()), new PagingConfig(config.pageSize, config.prefetchDistance, config.enablePlaceholders, config.initialLoadSizeHint, config.maxSize, 0, 32, null), getPlaceholdersBefore());
    }

    @Override // androidx.paging.NullPaddedList
    public int getSize() {
        return getPlaceholdersBefore() + getStorageCount() + getPlaceholdersAfter();
    }

    @Override // androidx.paging.NullPaddedList
    public int getStorageCount() {
        return this.m;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public final void init(int i, @NotNull PagingSource.LoadResult.Page<?, T> page, int i2, int i3, @NotNull Callback callback, boolean z) {
        Intrinsics.checkNotNullParameter(page, "page");
        Intrinsics.checkNotNullParameter(callback, "callback");
        a(i, page, i2, i3, z);
        callback.onInitialized(size());
    }

    public final boolean needsTrimFromEnd(int i, int i2) {
        return b(i, i2, this.h.size() - 1);
    }

    public final boolean needsTrimFromFront(int i, int i2) {
        return b(i, i2, 0);
    }

    public final void prependPage$paging_common(@NotNull PagingSource.LoadResult.Page<?, T> page, @Nullable Callback callback) {
        Intrinsics.checkNotNullParameter(page, "page");
        int size = page.getData().size();
        if (size == 0) {
            return;
        }
        this.h.add(0, page);
        this.m = getStorageCount() + size;
        int min = Math.min(getPlaceholdersBefore(), size);
        int i = size - min;
        if (min != 0) {
            this.i = getPlaceholdersBefore() - min;
        }
        this.k -= i;
        if (callback == null) {
            return;
        }
        callback.onPagePrepended(getPlaceholdersBefore(), min, i);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* bridge */ T remove(int i) {
        return (T) removeAt(i);
    }

    public /* bridge */ Object removeAt(int i) {
        return super.remove(i);
    }

    public final void setLastLoadAroundIndex(int i) {
        this.n = h.coerceIn(i - getPlaceholdersBefore(), 0, getStorageCount() - 1);
    }

    public final boolean shouldPreTrimNewPage(int i, int i2, int i3) {
        return getStorageCount() + i3 > i && this.h.size() > 1 && getStorageCount() >= i2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ int size() {
        return getSize();
    }

    @NotNull
    public final PagedStorage<T> snapshot() {
        return new PagedStorage<>(this);
    }

    @Override // java.util.AbstractCollection
    @NotNull
    public String toString() {
        return "leading " + getPlaceholdersBefore() + ", storage " + getStorageCount() + ", trailing " + getPlaceholdersAfter() + ' ' + CollectionsKt___CollectionsKt.joinToString$default(this.h, HexStringBuilder.DEFAULT_SEPARATOR, null, null, 0, null, null, 62, null);
    }

    public final boolean trimFromEnd$paging_common(boolean z, int i, int i2, @NotNull Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        int i3 = 0;
        while (needsTrimFromEnd(i, i2)) {
            List<PagingSource.LoadResult.Page<?, T>> list = this.h;
            int size = list.remove(list.size() - 1).getData().size();
            i3 += size;
            this.m = getStorageCount() - size;
        }
        this.n = h.coerceAtMost(this.n, getStorageCount() - 1);
        if (i3 > 0) {
            int placeholdersBefore = getPlaceholdersBefore() + getStorageCount();
            if (z) {
                this.j = getPlaceholdersAfter() + i3;
                callback.onPagesSwappedToPlaceholder(placeholdersBefore, i3);
            } else {
                callback.onPagesRemoved(placeholdersBefore, i3);
            }
        }
        return i3 > 0;
    }

    public final boolean trimFromFront$paging_common(boolean z, int i, int i2, @NotNull Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        int i3 = 0;
        while (needsTrimFromFront(i, i2)) {
            int size = this.h.remove(0).getData().size();
            i3 += size;
            this.m = getStorageCount() - size;
        }
        this.n = h.coerceAtLeast(this.n - i3, 0);
        if (i3 > 0) {
            if (z) {
                int placeholdersBefore = getPlaceholdersBefore();
                this.i = getPlaceholdersBefore() + i3;
                callback.onPagesSwappedToPlaceholder(placeholdersBefore, i3);
            } else {
                this.k += i3;
                callback.onPagesRemoved(getPlaceholdersBefore(), i3);
            }
        }
        return i3 > 0;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public PagedStorage(int i, @NotNull PagingSource.LoadResult.Page<?, T> page, int i2) {
        this();
        Intrinsics.checkNotNullParameter(page, "page");
        a(i, page, i2, 0, true);
    }

    public PagedStorage(PagedStorage<T> pagedStorage) {
        ArrayList arrayList = new ArrayList();
        this.h = arrayList;
        this.l = true;
        arrayList.addAll(pagedStorage.h);
        this.i = pagedStorage.getPlaceholdersBefore();
        this.j = pagedStorage.getPlaceholdersAfter();
        this.k = pagedStorage.k;
        this.l = pagedStorage.l;
        this.m = pagedStorage.getStorageCount();
        this.n = pagedStorage.n;
    }
}

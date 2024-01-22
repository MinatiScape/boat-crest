package androidx.paging;

import androidx.annotation.IntRange;
import androidx.exifinterface.media.ExifInterface;
import androidx.paging.PagingSource;
import com.clevertap.android.sdk.Constants;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0003*\u00020\u00012\u00020\u0001B=\u0012\u0018\u0010#\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\f0\u001e\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010-\u001a\u00020(\u0012\b\b\u0001\u0010.\u001a\u00020\u0007¢\u0006\u0004\b/\u00100J\u0013\u0010\u0006\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\b\u001a\u00020\u0007H\u0016J\u0017\u0010\n\u001a\u0004\u0018\u00018\u00012\u0006\u0010\t\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u001c\u0010\r\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\f2\u0006\u0010\t\u001a\u00020\u0007J\u0006\u0010\u000e\u001a\u00020\u0005J\u000f\u0010\u000f\u001a\u0004\u0018\u00018\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u0004\u0018\u00018\u0001¢\u0006\u0004\b\u0011\u0010\u0010J\b\u0010\u0013\u001a\u00020\u0012H\u0016JY\u0010\u001d\u001a\u00028\u0002\"\u0004\b\u0002\u0010\u00142\u0006\u0010\t\u001a\u00020\u000726\u0010\u001a\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00028\u00020\u0015H\u0080\bø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001cR+\u0010#\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\f0\u001e8\u0006@\u0006¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"R\u001b\u0010\t\u001a\u0004\u0018\u00010\u00078\u0006@\u0006¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'R\u0019\u0010-\u001a\u00020(8\u0006@\u0006¢\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u00061"}, d2 = {"Landroidx/paging/PagingState;", "", "Key", "Value", FitnessActivities.OTHER, "", "equals", "", "hashCode", "anchorPosition", "closestItemToPosition", "(I)Ljava/lang/Object;", "Landroidx/paging/PagingSource$LoadResult$Page;", "closestPageToPosition", "isEmpty", "firstItemOrNull", "()Ljava/lang/Object;", "lastItemOrNull", "", "toString", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlin/Function2;", "Lkotlin/ParameterName;", AppMeasurementSdk.ConditionalUserProperty.NAME, "pageIndex", FirebaseAnalytics.Param.INDEX, "block", "anchorPositionToPagedIndices$paging_common", "(ILkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "anchorPositionToPagedIndices", "", "a", "Ljava/util/List;", "getPages", "()Ljava/util/List;", "pages", "b", "Ljava/lang/Integer;", "getAnchorPosition", "()Ljava/lang/Integer;", "Landroidx/paging/PagingConfig;", com.google.android.material.color.c.f10260a, "Landroidx/paging/PagingConfig;", "getConfig", "()Landroidx/paging/PagingConfig;", Constants.KEY_CONFIG, "leadingPlaceholderCount", "<init>", "(Ljava/util/List;Ljava/lang/Integer;Landroidx/paging/PagingConfig;I)V", "paging-common"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class PagingState<Key, Value> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final List<PagingSource.LoadResult.Page<Key, Value>> f1539a;
    @Nullable
    public final Integer b;
    @NotNull
    public final PagingConfig c;
    public final int d;

    public PagingState(@NotNull List<PagingSource.LoadResult.Page<Key, Value>> pages, @Nullable Integer num, @NotNull PagingConfig config, @IntRange(from = 0) int i) {
        Intrinsics.checkNotNullParameter(pages, "pages");
        Intrinsics.checkNotNullParameter(config, "config");
        this.f1539a = pages;
        this.b = num;
        this.c = config;
        this.d = i;
    }

    public final <T> T anchorPositionToPagedIndices$paging_common(int i, @NotNull Function2<? super Integer, ? super Integer, ? extends T> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        int i2 = i - this.d;
        int i3 = 0;
        while (i3 < CollectionsKt__CollectionsKt.getLastIndex(getPages()) && i2 > CollectionsKt__CollectionsKt.getLastIndex(getPages().get(i3).getData())) {
            i2 -= getPages().get(i3).getData().size();
            i3++;
        }
        return block.invoke(Integer.valueOf(i3), Integer.valueOf(i2));
    }

    @Nullable
    public final Value closestItemToPosition(int i) {
        boolean z;
        List<PagingSource.LoadResult.Page<Key, Value>> list = this.f1539a;
        int i2 = 0;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                if (!((PagingSource.LoadResult.Page) it.next()).getData().isEmpty()) {
                    z = false;
                    break;
                }
            }
        }
        z = true;
        if (z) {
            return null;
        }
        int i3 = i - this.d;
        while (i2 < CollectionsKt__CollectionsKt.getLastIndex(getPages()) && i3 > CollectionsKt__CollectionsKt.getLastIndex(getPages().get(i2).getData())) {
            i3 -= getPages().get(i2).getData().size();
            i2++;
        }
        Iterator<T> it2 = getPages().iterator();
        while (it2.hasNext()) {
            PagingSource.LoadResult.Page page = (PagingSource.LoadResult.Page) it2.next();
            if (!page.getData().isEmpty()) {
                List<PagingSource.LoadResult.Page<Key, Value>> pages = getPages();
                ListIterator<PagingSource.LoadResult.Page<Key, Value>> listIterator = pages.listIterator(pages.size());
                while (listIterator.hasPrevious()) {
                    PagingSource.LoadResult.Page<Key, Value> previous = listIterator.previous();
                    if (!previous.getData().isEmpty()) {
                        if (i3 < 0) {
                            return (Value) CollectionsKt___CollectionsKt.first((List<? extends Object>) page.getData());
                        }
                        if (i2 == CollectionsKt__CollectionsKt.getLastIndex(getPages()) && i3 > CollectionsKt__CollectionsKt.getLastIndex(((PagingSource.LoadResult.Page) CollectionsKt___CollectionsKt.last((List<? extends Object>) getPages())).getData())) {
                            return (Value) CollectionsKt___CollectionsKt.last((List<? extends Object>) previous.getData());
                        }
                        return getPages().get(i2).getData().get(i3);
                    }
                }
                throw new NoSuchElementException("List contains no element matching the predicate.");
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    @Nullable
    public final PagingSource.LoadResult.Page<Key, Value> closestPageToPosition(int i) {
        List<PagingSource.LoadResult.Page<Key, Value>> list = this.f1539a;
        int i2 = 0;
        boolean z = true;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (!((PagingSource.LoadResult.Page) it.next()).getData().isEmpty()) {
                    z = false;
                    break;
                }
            }
        }
        if (z) {
            return null;
        }
        int i3 = i - this.d;
        while (i2 < CollectionsKt__CollectionsKt.getLastIndex(getPages()) && i3 > CollectionsKt__CollectionsKt.getLastIndex(getPages().get(i2).getData())) {
            i3 -= getPages().get(i2).getData().size();
            i2++;
        }
        if (i3 < 0) {
            return (PagingSource.LoadResult.Page) CollectionsKt___CollectionsKt.first((List<? extends Object>) getPages());
        }
        return getPages().get(i2);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof PagingState) {
            PagingState pagingState = (PagingState) obj;
            if (Intrinsics.areEqual(this.f1539a, pagingState.f1539a) && Intrinsics.areEqual(this.b, pagingState.b) && Intrinsics.areEqual(this.c, pagingState.c) && this.d == pagingState.d) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public final Value firstItemOrNull() {
        Object obj;
        List<Value> data;
        Iterator<T> it = this.f1539a.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (!((PagingSource.LoadResult.Page) obj).getData().isEmpty()) {
                break;
            }
        }
        PagingSource.LoadResult.Page page = (PagingSource.LoadResult.Page) obj;
        if (page == null || (data = page.getData()) == null) {
            return null;
        }
        return (Value) CollectionsKt___CollectionsKt.firstOrNull((List<? extends Object>) data);
    }

    @Nullable
    public final Integer getAnchorPosition() {
        return this.b;
    }

    @NotNull
    public final PagingConfig getConfig() {
        return this.c;
    }

    @NotNull
    public final List<PagingSource.LoadResult.Page<Key, Value>> getPages() {
        return this.f1539a;
    }

    public int hashCode() {
        int hashCode = this.f1539a.hashCode();
        Integer num = this.b;
        return hashCode + (num != null ? num.hashCode() : 0) + this.c.hashCode() + Integer.hashCode(this.d);
    }

    public final boolean isEmpty() {
        List<PagingSource.LoadResult.Page<Key, Value>> list = this.f1539a;
        if ((list instanceof Collection) && list.isEmpty()) {
            return true;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (!((PagingSource.LoadResult.Page) it.next()).getData().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Nullable
    public final Value lastItemOrNull() {
        PagingSource.LoadResult.Page<Key, Value> page;
        List<Value> data;
        List<PagingSource.LoadResult.Page<Key, Value>> list = this.f1539a;
        ListIterator<PagingSource.LoadResult.Page<Key, Value>> listIterator = list.listIterator(list.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                page = null;
                break;
            }
            page = listIterator.previous();
            if (!page.getData().isEmpty()) {
                break;
            }
        }
        PagingSource.LoadResult.Page<Key, Value> page2 = page;
        if (page2 == null || (data = page2.getData()) == null) {
            return null;
        }
        return (Value) CollectionsKt___CollectionsKt.lastOrNull((List<? extends Object>) data);
    }

    @NotNull
    public String toString() {
        return "PagingState(pages=" + this.f1539a + ", anchorPosition=" + this.b + ", config=" + this.c + ", leadingPlaceholderCount=" + this.d + HexStringBuilder.COMMENT_END_CHAR;
    }
}

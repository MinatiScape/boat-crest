package androidx.paging;

import androidx.annotation.IntRange;
import androidx.exifinterface.media.ExifInterface;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.AbstractList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\b\n\u0018\u0000*\u0004\b\u0000\u0010\u00012\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0002B)\u0012\b\b\u0001\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u000e\u001a\u00020\u0003\u0012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f¢\u0006\u0004\b\u0017\u0010\u0018J\u001a\u0010\u0005\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0004\u001a\u00020\u0003H\u0096\u0002¢\u0006\u0004\b\u0005\u0010\u0006R\u0019\u0010\u000b\u001a\u00020\u00038\u0006@\u0006¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\nR\u0019\u0010\u000e\u001a\u00020\u00038\u0006@\u0006¢\u0006\f\n\u0004\b\f\u0010\b\u001a\u0004\b\r\u0010\nR\u001f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f8\u0006@\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0016\u001a\u00020\u00038V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\n¨\u0006\u0019"}, d2 = {"Landroidx/paging/ItemSnapshotList;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlin/collections/AbstractList;", "", FirebaseAnalytics.Param.INDEX, "get", "(I)Ljava/lang/Object;", "h", "I", "getPlaceholdersBefore", "()I", "placeholdersBefore", "i", "getPlaceholdersAfter", "placeholdersAfter", "", "j", "Ljava/util/List;", "getItems", "()Ljava/util/List;", FirebaseAnalytics.Param.ITEMS, "getSize", "size", "<init>", "(IILjava/util/List;)V", "paging-common"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class ItemSnapshotList<T> extends AbstractList<T> {
    public final int h;
    public final int i;
    @NotNull
    public final List<T> j;

    /* JADX WARN: Multi-variable type inference failed */
    public ItemSnapshotList(@IntRange(from = 0) int i, @IntRange(from = 0) int i2, @NotNull List<? extends T> items) {
        Intrinsics.checkNotNullParameter(items, "items");
        this.h = i;
        this.i = i2;
        this.j = items;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    @Nullable
    public T get(int i) {
        boolean z = true;
        if (i >= 0 && i < this.h) {
            return null;
        }
        int i2 = this.h;
        if (i < this.j.size() + i2 && i2 <= i) {
            return this.j.get(i - this.h);
        }
        int size = this.h + this.j.size();
        if (i >= size() || size > i) {
            z = false;
        }
        if (z) {
            return null;
        }
        throw new IndexOutOfBoundsException("Illegal attempt to access index " + i + " in ItemSnapshotList of size " + size());
    }

    @NotNull
    public final List<T> getItems() {
        return this.j;
    }

    public final int getPlaceholdersAfter() {
        return this.i;
    }

    public final int getPlaceholdersBefore() {
        return this.h;
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return this.h + this.j.size() + this.i;
    }
}

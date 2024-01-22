package androidx.paging;

import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.ListUpdateCallback;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.h;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\n\u0010\u000bJ4\u0010\t\u001a\u00020\b\"\b\b\u0000\u0010\u0002*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¨\u0006\f"}, d2 = {"Landroidx/paging/DistinctListsDiffDispatcher;", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/recyclerview/widget/ListUpdateCallback;", "callback", "Landroidx/paging/NullPaddedList;", "oldList", "newList", "", "dispatchDiff", "<init>", "()V", "paging-runtime_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class DistinctListsDiffDispatcher {
    @NotNull
    public static final DistinctListsDiffDispatcher INSTANCE = new DistinctListsDiffDispatcher();

    public final void a(ListUpdateCallback listUpdateCallback, int i, int i2, int i3, int i4, Object obj) {
        int i5 = i - i3;
        if (i5 > 0) {
            listUpdateCallback.onChanged(i3, i5, obj);
        }
        int i6 = i4 - i2;
        if (i6 > 0) {
            listUpdateCallback.onChanged(i2, i6, obj);
        }
    }

    public final <T> void dispatchDiff(@NotNull ListUpdateCallback callback, @NotNull NullPaddedList<T> oldList, @NotNull NullPaddedList<T> newList) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        Intrinsics.checkNotNullParameter(oldList, "oldList");
        Intrinsics.checkNotNullParameter(newList, "newList");
        int max = Math.max(oldList.getPlaceholdersBefore(), newList.getPlaceholdersBefore());
        int min = Math.min(oldList.getPlaceholdersBefore() + oldList.getStorageCount(), newList.getPlaceholdersBefore() + newList.getStorageCount());
        int i = min - max;
        if (i > 0) {
            callback.onRemoved(max, i);
            callback.onInserted(max, i);
        }
        int min2 = Math.min(max, min);
        int max2 = Math.max(max, min);
        a(callback, min2, max2, h.coerceAtMost(oldList.getPlaceholdersBefore(), newList.getSize()), h.coerceAtMost(oldList.getPlaceholdersBefore() + oldList.getStorageCount(), newList.getSize()), DiffingChangePayload.ITEM_TO_PLACEHOLDER);
        a(callback, min2, max2, h.coerceAtMost(newList.getPlaceholdersBefore(), oldList.getSize()), h.coerceAtMost(newList.getPlaceholdersBefore() + newList.getStorageCount(), oldList.getSize()), DiffingChangePayload.PLACEHOLDER_TO_ITEM);
        int size = newList.getSize() - oldList.getSize();
        if (size > 0) {
            callback.onInserted(oldList.getSize(), size);
        } else if (size < 0) {
            callback.onRemoved(oldList.getSize() + size, -size);
        }
    }
}

package androidx.paging;

import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListUpdateCallback;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.IntRange;
import kotlin.ranges.h;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u001a8\u0010\u0007\u001a\u00020\u0006\"\b\b\u0000\u0010\u0001*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004H\u0000\u001a:\u0010\f\u001a\u00020\u000b\"\b\b\u0000\u0010\u0001*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\t\u001a\u00020\b2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\n\u001a\u00020\u0006H\u0000\u001a,\u0010\u000f\u001a\u00020\r*\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\n\u001a\u00020\u00062\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u000e\u001a\u00020\rH\u0000¨\u0006\u0010"}, d2 = {"", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/paging/NullPaddedList;", "newList", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "diffCallback", "Landroidx/paging/NullPaddedDiffResult;", "computeDiff", "Landroidx/recyclerview/widget/ListUpdateCallback;", "callback", "diffResult", "", "dispatchDiff", "", "oldPosition", "transformAnchorIndex", "paging-runtime_release"}, k = 2, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class NullPaddedListDiffHelperKt {
    @NotNull
    public static final <T> NullPaddedDiffResult computeDiff(@NotNull final NullPaddedList<T> nullPaddedList, @NotNull final NullPaddedList<T> newList, @NotNull final DiffUtil.ItemCallback<T> diffCallback) {
        boolean z;
        Intrinsics.checkNotNullParameter(nullPaddedList, "<this>");
        Intrinsics.checkNotNullParameter(newList, "newList");
        Intrinsics.checkNotNullParameter(diffCallback, "diffCallback");
        final int storageCount = nullPaddedList.getStorageCount();
        final int storageCount2 = newList.getStorageCount();
        DiffUtil.Callback callback = new DiffUtil.Callback() { // from class: androidx.paging.NullPaddedListDiffHelperKt$computeDiff$diffResult$1
            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public boolean areContentsTheSame(int i, int i2) {
                Object fromStorage = nullPaddedList.getFromStorage(i);
                Object fromStorage2 = newList.getFromStorage(i2);
                if (fromStorage == fromStorage2) {
                    return true;
                }
                return diffCallback.areContentsTheSame(fromStorage, fromStorage2);
            }

            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public boolean areItemsTheSame(int i, int i2) {
                Object fromStorage = nullPaddedList.getFromStorage(i);
                Object fromStorage2 = newList.getFromStorage(i2);
                if (fromStorage == fromStorage2) {
                    return true;
                }
                return diffCallback.areItemsTheSame(fromStorage, fromStorage2);
            }

            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            @Nullable
            public Object getChangePayload(int i, int i2) {
                Object fromStorage = nullPaddedList.getFromStorage(i);
                Object fromStorage2 = newList.getFromStorage(i2);
                if (fromStorage == fromStorage2) {
                    return Boolean.TRUE;
                }
                return diffCallback.getChangePayload(fromStorage, fromStorage2);
            }

            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public int getNewListSize() {
                return storageCount2;
            }

            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public int getOldListSize() {
                return storageCount;
            }
        };
        boolean z2 = true;
        DiffUtil.DiffResult calculateDiff = DiffUtil.calculateDiff(callback, true);
        Intrinsics.checkNotNullExpressionValue(calculateDiff, "NullPaddedList<T>.comput…    },\n        true\n    )");
        IntRange until = h.until(0, nullPaddedList.getStorageCount());
        if (!(until instanceof Collection) || !((Collection) until).isEmpty()) {
            Iterator<Integer> it = until.iterator();
            while (it.hasNext()) {
                if (calculateDiff.convertOldPositionToNew(((IntIterator) it).nextInt()) != -1) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
                if (z) {
                    break;
                }
            }
        }
        z2 = false;
        return new NullPaddedDiffResult(calculateDiff, z2);
    }

    public static final <T> void dispatchDiff(@NotNull NullPaddedList<T> nullPaddedList, @NotNull ListUpdateCallback callback, @NotNull NullPaddedList<T> newList, @NotNull NullPaddedDiffResult diffResult) {
        Intrinsics.checkNotNullParameter(nullPaddedList, "<this>");
        Intrinsics.checkNotNullParameter(callback, "callback");
        Intrinsics.checkNotNullParameter(newList, "newList");
        Intrinsics.checkNotNullParameter(diffResult, "diffResult");
        if (diffResult.getHasOverlap()) {
            OverlappingListsDiffDispatcher.INSTANCE.dispatchDiff(nullPaddedList, newList, callback, diffResult);
        } else {
            DistinctListsDiffDispatcher.INSTANCE.dispatchDiff(callback, nullPaddedList, newList);
        }
    }

    public static final int transformAnchorIndex(@NotNull NullPaddedList<?> nullPaddedList, @NotNull NullPaddedDiffResult diffResult, @NotNull NullPaddedList<?> newList, int i) {
        int convertOldPositionToNew;
        Intrinsics.checkNotNullParameter(nullPaddedList, "<this>");
        Intrinsics.checkNotNullParameter(diffResult, "diffResult");
        Intrinsics.checkNotNullParameter(newList, "newList");
        if (!diffResult.getHasOverlap()) {
            return h.coerceIn(i, (ClosedRange<Integer>) h.until(0, newList.getSize()));
        }
        int placeholdersBefore = i - nullPaddedList.getPlaceholdersBefore();
        if (placeholdersBefore >= 0 && placeholdersBefore < nullPaddedList.getStorageCount()) {
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                int i4 = ((i2 / 2) * (i2 % 2 == 1 ? -1 : 1)) + placeholdersBefore;
                if (i4 >= 0 && i4 < nullPaddedList.getStorageCount() && (convertOldPositionToNew = diffResult.getDiff().convertOldPositionToNew(i4)) != -1) {
                    return convertOldPositionToNew + newList.getPlaceholdersBefore();
                }
                if (i3 > 29) {
                    break;
                }
                i2 = i3;
            }
        }
        return h.coerceIn(i, (ClosedRange<Integer>) h.until(0, newList.getSize()));
    }
}

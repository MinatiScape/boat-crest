package androidx.paging;

import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.ListUpdateCallback;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.h;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\bÀ\u0002\u0018\u00002\u00020\u0001:\u0001\u000eB\t\b\u0002¢\u0006\u0004\b\f\u0010\rJ8\u0010\u000b\u001a\u00020\n\"\u0004\b\u0000\u0010\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b¨\u0006\u000f"}, d2 = {"Landroidx/paging/OverlappingListsDiffDispatcher;", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/paging/NullPaddedList;", "oldList", "newList", "Landroidx/recyclerview/widget/ListUpdateCallback;", "callback", "Landroidx/paging/NullPaddedDiffResult;", "diffResult", "", "dispatchDiff", "<init>", "()V", "PlaceholderUsingUpdateCallback", "paging-runtime_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class OverlappingListsDiffDispatcher {
    @NotNull
    public static final OverlappingListsDiffDispatcher INSTANCE = new OverlappingListsDiffDispatcher();

    /* loaded from: classes.dex */
    public static final class PlaceholderUsingUpdateCallback<T> implements ListUpdateCallback {
        @NotNull
        public final NullPaddedList<T> h;
        @NotNull
        public final NullPaddedList<T> i;
        @NotNull
        public final ListUpdateCallback j;
        public int k;
        public int l;
        public int m;
        public int n;
        public int o;

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0007\u0010\bR\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u0016\u0010\u0005\u001a\u00020\u00028\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0005\u0010\u0004R\u0016\u0010\u0006\u001a\u00020\u00028\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0006\u0010\u0004¨\u0006\t"}, d2 = {"Landroidx/paging/OverlappingListsDiffDispatcher$PlaceholderUsingUpdateCallback$Companion;", "", "", "UNUSED", "I", "USED_FOR_ADDITION", "USED_FOR_REMOVAL", "<init>", "()V", "paging-runtime_release"}, k = 1, mv = {1, 5, 1})
        /* loaded from: classes.dex */
        public static final class Companion {
            public Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        static {
            new Companion(null);
        }

        public PlaceholderUsingUpdateCallback(@NotNull NullPaddedList<T> oldList, @NotNull NullPaddedList<T> newList, @NotNull ListUpdateCallback callback) {
            Intrinsics.checkNotNullParameter(oldList, "oldList");
            Intrinsics.checkNotNullParameter(newList, "newList");
            Intrinsics.checkNotNullParameter(callback, "callback");
            this.h = oldList;
            this.i = newList;
            this.j = callback;
            this.k = oldList.getPlaceholdersBefore();
            this.l = oldList.getPlaceholdersAfter();
            this.m = oldList.getStorageCount();
            this.n = 1;
            this.o = 1;
        }

        public final boolean b(int i, int i2) {
            if (i >= this.m && this.o != 2) {
                int min = Math.min(i2, this.l);
                if (min > 0) {
                    this.o = 3;
                    this.j.onChanged(this.k + i, min, DiffingChangePayload.PLACEHOLDER_TO_ITEM);
                    this.l -= min;
                }
                int i3 = i2 - min;
                if (i3 > 0) {
                    this.j.onInserted(i + min + this.k, i3);
                    return true;
                }
                return true;
            }
            return false;
        }

        public final boolean c(int i, int i2) {
            if (i <= 0 && this.n != 2) {
                int min = Math.min(i2, this.k);
                if (min > 0) {
                    this.n = 3;
                    this.j.onChanged((0 - min) + this.k, min, DiffingChangePayload.PLACEHOLDER_TO_ITEM);
                    this.k -= min;
                }
                int i3 = i2 - min;
                if (i3 > 0) {
                    this.j.onInserted(this.k + 0, i3);
                    return true;
                }
                return true;
            }
            return false;
        }

        public final boolean d(int i, int i2) {
            if (i + i2 >= this.m && this.o != 3) {
                int coerceAtLeast = h.coerceAtLeast(Math.min(this.i.getPlaceholdersAfter() - this.l, i2), 0);
                int i3 = i2 - coerceAtLeast;
                if (coerceAtLeast > 0) {
                    this.o = 2;
                    this.j.onChanged(this.k + i, coerceAtLeast, DiffingChangePayload.ITEM_TO_PLACEHOLDER);
                    this.l += coerceAtLeast;
                }
                if (i3 > 0) {
                    this.j.onRemoved(i + coerceAtLeast + this.k, i3);
                    return true;
                }
                return true;
            }
            return false;
        }

        public final boolean e(int i, int i2) {
            if (i <= 0 && this.n != 3) {
                int coerceAtLeast = h.coerceAtLeast(Math.min(this.i.getPlaceholdersBefore() - this.k, i2), 0);
                int i3 = i2 - coerceAtLeast;
                if (i3 > 0) {
                    this.j.onRemoved(this.k + 0, i3);
                }
                if (coerceAtLeast > 0) {
                    this.n = 2;
                    this.j.onChanged(this.k + 0, coerceAtLeast, DiffingChangePayload.ITEM_TO_PLACEHOLDER);
                    this.k += coerceAtLeast;
                    return true;
                }
                return true;
            }
            return false;
        }

        public final void f() {
            int min = Math.min(this.h.getPlaceholdersBefore(), this.k);
            int placeholdersBefore = this.i.getPlaceholdersBefore() - this.k;
            if (placeholdersBefore > 0) {
                if (min > 0) {
                    this.j.onChanged(0, min, DiffingChangePayload.PLACEHOLDER_POSITION_CHANGE);
                }
                this.j.onInserted(0, placeholdersBefore);
            } else if (placeholdersBefore < 0) {
                this.j.onRemoved(0, -placeholdersBefore);
                int i = min + placeholdersBefore;
                if (i > 0) {
                    this.j.onChanged(0, i, DiffingChangePayload.PLACEHOLDER_POSITION_CHANGE);
                }
            }
            this.k = this.i.getPlaceholdersBefore();
        }

        public final void g() {
            f();
            h();
        }

        public final void h() {
            int min = Math.min(this.h.getPlaceholdersAfter(), this.l);
            int placeholdersAfter = this.i.getPlaceholdersAfter();
            int i = this.l;
            int i2 = placeholdersAfter - i;
            int i3 = this.k + this.m + i;
            int i4 = i3 - min;
            boolean z = i4 != this.h.getSize() - min;
            if (i2 > 0) {
                this.j.onInserted(i3, i2);
            } else if (i2 < 0) {
                this.j.onRemoved(i3 + i2, -i2);
                min += i2;
            }
            if (min > 0 && z) {
                this.j.onChanged(i4, min, DiffingChangePayload.PLACEHOLDER_POSITION_CHANGE);
            }
            this.l = this.i.getPlaceholdersAfter();
        }

        @Override // androidx.recyclerview.widget.ListUpdateCallback
        public void onChanged(int i, int i2, @Nullable Object obj) {
            this.j.onChanged(i + this.k, i2, obj);
        }

        @Override // androidx.recyclerview.widget.ListUpdateCallback
        public void onInserted(int i, int i2) {
            if (!b(i, i2) && !c(i, i2)) {
                this.j.onInserted(i + this.k, i2);
            }
            this.m += i2;
        }

        @Override // androidx.recyclerview.widget.ListUpdateCallback
        public void onMoved(int i, int i2) {
            this.j.onMoved(i + this.k, i2 + this.k);
        }

        @Override // androidx.recyclerview.widget.ListUpdateCallback
        public void onRemoved(int i, int i2) {
            if (!d(i, i2) && !e(i, i2)) {
                this.j.onRemoved(i + this.k, i2);
            }
            this.m -= i2;
        }
    }

    public final <T> void dispatchDiff(@NotNull NullPaddedList<T> oldList, @NotNull NullPaddedList<T> newList, @NotNull ListUpdateCallback callback, @NotNull NullPaddedDiffResult diffResult) {
        Intrinsics.checkNotNullParameter(oldList, "oldList");
        Intrinsics.checkNotNullParameter(newList, "newList");
        Intrinsics.checkNotNullParameter(callback, "callback");
        Intrinsics.checkNotNullParameter(diffResult, "diffResult");
        PlaceholderUsingUpdateCallback placeholderUsingUpdateCallback = new PlaceholderUsingUpdateCallback(oldList, newList, callback);
        diffResult.getDiff().dispatchUpdatesTo(placeholderUsingUpdateCallback);
        placeholderUsingUpdateCallback.g();
    }
}

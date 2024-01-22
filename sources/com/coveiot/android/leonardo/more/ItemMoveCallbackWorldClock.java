package com.coveiot.android.leonardo.more;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.leonardo.more.adapters.WorldClockAdapter;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ItemMoveCallbackWorldClock extends ItemTouchHelper.Callback {
    @NotNull
    public final ItemTouchHelperContract d;

    /* loaded from: classes5.dex */
    public interface ItemTouchHelperContract {
        void onRowClear(@Nullable WorldClockAdapter.WorldClockViewHolder worldClockViewHolder);

        void onRowMoved(int i, int i2);

        void onRowSelected(@Nullable WorldClockAdapter.WorldClockViewHolder worldClockViewHolder);
    }

    public ItemMoveCallbackWorldClock(@NotNull ItemTouchHelperContract mAdapter) {
        Intrinsics.checkNotNullParameter(mAdapter, "mAdapter");
        this.d = mAdapter;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void clearView(@NotNull RecyclerView recyclerView, @NotNull RecyclerView.ViewHolder viewHolder) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        super.clearView(recyclerView, viewHolder);
        if (viewHolder instanceof WorldClockAdapter.WorldClockViewHolder) {
            this.d.onRowClear((WorldClockAdapter.WorldClockViewHolder) viewHolder);
        }
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public int getMovementFlags(@NotNull RecyclerView recyclerView, @NotNull RecyclerView.ViewHolder viewHolder) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        return ItemTouchHelper.Callback.makeMovementFlags(3, 0);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean onMove(@NotNull RecyclerView recyclerView, @NotNull RecyclerView.ViewHolder viewHolder, @NotNull RecyclerView.ViewHolder target) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        Intrinsics.checkNotNullParameter(target, "target");
        this.d.onRowMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int i) {
        if (i != 0 && (viewHolder instanceof WorldClockAdapter.WorldClockViewHolder)) {
            this.d.onRowSelected((WorldClockAdapter.WorldClockViewHolder) viewHolder);
        }
        super.onSelectedChanged(viewHolder, i);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSwiped(@NotNull RecyclerView.ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
    }
}

package com.h6ah4i.android.widget.advrecyclerview.draggable;

import android.util.Log;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.h6ah4i.android.widget.advrecyclerview.adapter.SimpleWrapperAdapter;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultAction;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultActionDefault;
import com.h6ah4i.android.widget.advrecyclerview.utils.CustomRecyclerViewUtils;
import com.h6ah4i.android.widget.advrecyclerview.utils.WrapperAdapterUtils;
import java.util.List;
/* loaded from: classes11.dex */
public class c<VH extends RecyclerView.ViewHolder> extends SimpleWrapperAdapter<VH> implements SwipeableItemAdapter<VH> {
    public RecyclerViewDragDropManager j;
    public DraggableItemAdapter k;
    public RecyclerView.ViewHolder l;
    public DraggingItemInfo m;
    public ItemDraggableRange n;
    public int o;
    public int p;
    public int q;
    public boolean r;

    public c(RecyclerViewDragDropManager recyclerViewDragDropManager, RecyclerView.Adapter<VH> adapter) {
        super(adapter);
        this.o = -1;
        this.p = -1;
        if (recyclerViewDragDropManager != null) {
            this.j = recyclerViewDragDropManager;
            return;
        }
        throw new IllegalArgumentException("manager cannot be null");
    }

    public static int d(int i, int i2, int i3, int i4) {
        if (i2 < 0 || i3 < 0) {
            return i;
        }
        if (i4 == 0) {
            return i2 != i3 ? (i >= i2 || i >= i3) ? (i <= i2 || i <= i3) ? i3 < i2 ? i == i3 ? i2 : i - 1 : i == i3 ? i2 : i + 1 : i : i : i;
        } else if (i4 == 1) {
            return i == i3 ? i2 : i == i2 ? i3 : i;
        } else {
            throw new IllegalStateException("unexpected state");
        }
    }

    public static void m(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof DraggableItemViewHolder) {
            DraggableItemViewHolder draggableItemViewHolder = (DraggableItemViewHolder) viewHolder;
            int dragStateFlags = draggableItemViewHolder.getDragStateFlags();
            if (dragStateFlags == -1 || ((dragStateFlags ^ i) & Integer.MAX_VALUE) != 0) {
                i |= Integer.MIN_VALUE;
            }
            draggableItemViewHolder.setDragStateFlags(i);
        }
    }

    public boolean a(int i, int i2) {
        return this.k.onCheckCanDrop(i, i2);
    }

    public boolean b(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3) {
        DraggableItemAdapter draggableItemAdapter = (DraggableItemAdapter) WrapperAdapterUtils.findWrappedAdapter(this, DraggableItemAdapter.class, i);
        if (draggableItemAdapter == null) {
            return false;
        }
        return draggableItemAdapter.onCheckCanStartDrag(viewHolder, i, i2, i3);
    }

    public final void c() {
        RecyclerViewDragDropManager recyclerViewDragDropManager = this.j;
        if (recyclerViewDragDropManager != null) {
            recyclerViewDragDropManager.cancelDrag();
        }
    }

    public int e() {
        return this.p;
    }

    public int f() {
        return this.o;
    }

    public ItemDraggableRange g(RecyclerView.ViewHolder viewHolder, int i) {
        DraggableItemAdapter draggableItemAdapter = (DraggableItemAdapter) WrapperAdapterUtils.findWrappedAdapter(this, DraggableItemAdapter.class, i);
        if (draggableItemAdapter == null) {
            return null;
        }
        return draggableItemAdapter.onGetItemDraggableRange(viewHolder, i);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.SimpleWrapperAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        if (i()) {
            return super.getItemId(d(i, this.o, this.p, this.q));
        }
        return super.getItemId(i);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.SimpleWrapperAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (i()) {
            return super.getItemViewType(d(i, this.o, this.p, this.q));
        }
        return super.getItemViewType(i);
    }

    public final int h(int i) {
        return i() ? d(i, this.o, this.p, this.q) : i;
    }

    public boolean i() {
        return this.m != null;
    }

    public void j(int i, int i2, int i3) {
        int d = d(i, this.o, this.p, this.q);
        if (d == this.o) {
            this.p = i2;
            if (this.q == 0 && CustomRecyclerViewUtils.isLinearLayout(i3)) {
                notifyItemMoved(i, i2);
                return;
            } else {
                notifyDataSetChanged();
                return;
            }
        }
        throw new IllegalStateException("onMoveItem() - may be a bug or has duplicate IDs  --- mDraggingItemInitialPosition = " + this.o + ", mDraggingItemCurrentPosition = " + this.p + ", origFromPosition = " + d + ", fromPosition = " + i + ", toPosition = " + i2);
    }

    public void k(int i, int i2, boolean z) {
        DraggableItemAdapter draggableItemAdapter = this.k;
        this.o = -1;
        this.p = -1;
        this.n = null;
        this.m = null;
        this.l = null;
        this.k = null;
        if (z && i2 != i) {
            draggableItemAdapter.onMoveItem(i, i2);
        }
        draggableItemAdapter.onItemDragFinished(i, i2, z);
    }

    public void l() {
        this.r = true;
        this.k.onItemDragStarted(f());
        this.r = false;
    }

    public final boolean n() {
        return i() && !this.r;
    }

    public void o(DraggingItemInfo draggingItemInfo, RecyclerView.ViewHolder viewHolder, ItemDraggableRange itemDraggableRange, int i, int i2) {
        if (viewHolder.getItemId() != -1) {
            DraggableItemAdapter draggableItemAdapter = (DraggableItemAdapter) WrapperAdapterUtils.findWrappedAdapter(this, DraggableItemAdapter.class, i);
            this.k = draggableItemAdapter;
            if (draggableItemAdapter != null) {
                this.p = i;
                this.o = i;
                this.m = draggingItemInfo;
                this.l = viewHolder;
                this.n = itemDraggableRange;
                this.q = i2;
                return;
            }
            throw new IllegalStateException("DraggableItemAdapter not found!");
        }
        throw new IllegalStateException("dragging target must provides valid ID");
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.SimpleWrapperAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull VH vh, int i, @NonNull List<Object> list) {
        if (i()) {
            long j = this.m.id;
            long itemId = vh.getItemId();
            int d = d(i, this.o, this.p, this.q);
            int i2 = (itemId > j ? 1 : (itemId == j ? 0 : -1));
            if (i2 == 0 && vh != this.l) {
                Log.i("ARVDraggableWrapper", "a new view holder object for the currently dragging item is assigned");
                this.l = vh;
                this.j.I(vh);
            }
            int i3 = i2 == 0 ? 3 : 1;
            if (this.n.checkInRange(i)) {
                i3 |= 4;
            }
            m(vh, i3);
            super.onBindViewHolder(vh, d, list);
            return;
        }
        m(vh, 0);
        super.onBindViewHolder(vh, i, list);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.SimpleWrapperAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        VH vh = (VH) super.onCreateViewHolder(viewGroup, i);
        if (vh instanceof DraggableItemViewHolder) {
            ((DraggableItemViewHolder) vh).setDragStateFlags(-1);
        }
        return vh;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemAdapter
    public int onGetSwipeReactionType(@NonNull VH vh, int i, int i2, int i3) {
        RecyclerView.Adapter<VH> wrappedAdapter = getWrappedAdapter();
        if (wrappedAdapter instanceof SwipeableItemAdapter) {
            return ((SwipeableItemAdapter) wrappedAdapter).onGetSwipeReactionType(vh, h(i), i2, i3);
        }
        return 0;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.SimpleWrapperAdapter
    public void onHandleWrappedAdapterChanged() {
        if (n()) {
            c();
        } else {
            super.onHandleWrappedAdapterChanged();
        }
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.SimpleWrapperAdapter
    public void onHandleWrappedAdapterItemRangeChanged(int i, int i2) {
        if (n()) {
            c();
        } else {
            super.onHandleWrappedAdapterItemRangeChanged(i, i2);
        }
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.SimpleWrapperAdapter
    public void onHandleWrappedAdapterItemRangeInserted(int i, int i2) {
        if (n()) {
            c();
        } else {
            super.onHandleWrappedAdapterItemRangeInserted(i, i2);
        }
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.SimpleWrapperAdapter
    public void onHandleWrappedAdapterItemRangeRemoved(int i, int i2) {
        if (n()) {
            c();
        } else {
            super.onHandleWrappedAdapterItemRangeRemoved(i, i2);
        }
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.SimpleWrapperAdapter
    public void onHandleWrappedAdapterRangeMoved(int i, int i2, int i3) {
        if (n()) {
            c();
        } else {
            super.onHandleWrappedAdapterRangeMoved(i, i2, i3);
        }
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.SimpleWrapperAdapter
    public void onRelease() {
        super.onRelease();
        this.l = null;
        this.k = null;
        this.j = null;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemAdapter
    public void onSetSwipeBackground(@NonNull VH vh, int i, int i2) {
        RecyclerView.Adapter<VH> wrappedAdapter = getWrappedAdapter();
        if (wrappedAdapter instanceof SwipeableItemAdapter) {
            ((SwipeableItemAdapter) wrappedAdapter).onSetSwipeBackground(vh, h(i), i2);
        }
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemAdapter
    public SwipeResultAction onSwipeItem(@NonNull VH vh, int i, int i2) {
        RecyclerView.Adapter<VH> wrappedAdapter = getWrappedAdapter();
        if (!(wrappedAdapter instanceof SwipeableItemAdapter)) {
            return new SwipeResultActionDefault();
        }
        return ((SwipeableItemAdapter) wrappedAdapter).onSwipeItem(vh, h(i), i2);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemAdapter
    public void onSwipeItemStarted(@NonNull VH vh, int i) {
        RecyclerView.Adapter<VH> wrappedAdapter = getWrappedAdapter();
        if (wrappedAdapter instanceof SwipeableItemAdapter) {
            ((SwipeableItemAdapter) wrappedAdapter).onSwipeItemStarted(vh, h(i));
        }
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.SimpleWrapperAdapter, com.h6ah4i.android.widget.advrecyclerview.adapter.WrappedAdapter
    public void onViewRecycled(@NonNull VH vh, int i) {
        if (i()) {
            this.j.H(vh);
            this.l = this.j.o();
        }
        super.onViewRecycled(vh, i);
    }
}

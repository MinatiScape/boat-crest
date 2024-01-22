package com.h6ah4i.android.widget.advrecyclerview.swipeable;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.h6ah4i.android.widget.advrecyclerview.adapter.SimpleWrapperAdapter;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultAction;
import com.h6ah4i.android.widget.advrecyclerview.utils.WrapperAdapterUtils;
import java.util.List;
/* loaded from: classes11.dex */
public class e<VH extends RecyclerView.ViewHolder> extends SimpleWrapperAdapter<VH> {
    public SwipeableItemAdapter j;
    public RecyclerViewSwipeManager k;
    public long l;
    public boolean m;

    public e(RecyclerViewSwipeManager recyclerViewSwipeManager, RecyclerView.Adapter<VH> adapter) {
        super(adapter);
        this.l = -1L;
        SwipeableItemAdapter swipeableItemAdapter = (SwipeableItemAdapter) WrapperAdapterUtils.findWrappedAdapter(adapter, SwipeableItemAdapter.class);
        this.j = swipeableItemAdapter;
        if (swipeableItemAdapter == null) {
            throw new IllegalArgumentException("adapter does not implement SwipeableItemAdapter");
        }
        if (recyclerViewSwipeManager != null) {
            this.k = recyclerViewSwipeManager;
            return;
        }
        throw new IllegalArgumentException("manager cannot be null");
    }

    public static boolean c(int i, int i2, int i3) {
        return i >= i2 && i < i2 + i3;
    }

    public static float d(int i, int i2) {
        if (i2 == 1 || i2 == 2) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        return i != 5 ? 0.0f : 65537.0f;
                    }
                    return 65536.0f;
                }
                return -65537.0f;
            }
            return -65536.0f;
        }
        return 0.0f;
    }

    public static float e(SwipeableItemViewHolder swipeableItemViewHolder, boolean z) {
        if (z) {
            return swipeableItemViewHolder.getSwipeItemHorizontalSlideAmount();
        }
        return swipeableItemViewHolder.getSwipeItemVerticalSlideAmount();
    }

    public static void m(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof SwipeableItemViewHolder) {
            SwipeableItemViewHolder swipeableItemViewHolder = (SwipeableItemViewHolder) viewHolder;
            int swipeStateFlags = swipeableItemViewHolder.getSwipeStateFlags();
            if (swipeStateFlags == -1 || ((swipeStateFlags ^ i) & Integer.MAX_VALUE) != 0) {
                i |= Integer.MIN_VALUE;
            }
            swipeableItemViewHolder.setSwipeStateFlags(i);
        }
    }

    public static void n(SwipeableItemViewHolder swipeableItemViewHolder, float f, boolean z) {
        if (z) {
            swipeableItemViewHolder.setSwipeItemHorizontalSlideAmount(f);
        } else {
            swipeableItemViewHolder.setSwipeItemVerticalSlideAmount(f);
        }
    }

    public final void b() {
        RecyclerViewSwipeManager recyclerViewSwipeManager = this.k;
        if (recyclerViewSwipeManager != null) {
            recyclerViewSwipeManager.cancelSwipe();
        }
    }

    public int f(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3) {
        return this.j.onGetSwipeReactionType(viewHolder, i, i2, i3);
    }

    public boolean g() {
        return this.l != -1;
    }

    public SwipeResultAction h(RecyclerView.ViewHolder viewHolder, int i, int i2) {
        this.l = -1L;
        return this.j.onSwipeItem(viewHolder, i, i2);
    }

    public void i(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, SwipeResultAction swipeResultAction) {
        SwipeableItemViewHolder swipeableItemViewHolder = (SwipeableItemViewHolder) viewHolder;
        swipeableItemViewHolder.setSwipeResult(i2);
        swipeableItemViewHolder.setAfterSwipeReaction(i3);
        if (i3 != 3) {
            n(swipeableItemViewHolder, d(i2, i3), o());
        }
        swipeResultAction.performAction();
        notifyDataSetChanged();
    }

    public void j(RecyclerViewSwipeManager recyclerViewSwipeManager, RecyclerView.ViewHolder viewHolder, int i, long j) {
        this.l = j;
        this.m = true;
        this.j.onSwipeItemStarted(viewHolder, i);
        this.m = false;
    }

    public void k(RecyclerView.ViewHolder viewHolder, int i, float f, boolean z, boolean z2, boolean z3) {
        SwipeableItemViewHolder swipeableItemViewHolder = (SwipeableItemViewHolder) viewHolder;
        float a2 = RecyclerViewSwipeManager.a(swipeableItemViewHolder, z2, f, z, swipeableItemViewHolder.isProportionalSwipeAmountModeEnabled());
        float f2 = z2 ? a2 : 0.0f;
        if (z2) {
            a2 = 0.0f;
        }
        swipeableItemViewHolder.onSlideAmountUpdated(f2, a2, z3);
    }

    public void l(RecyclerView.ViewHolder viewHolder, int i, float f, boolean z, boolean z2, boolean z3, int i2) {
        this.j.onSetSwipeBackground(viewHolder, i, i2);
        k(viewHolder, i, f, z, z2, z3);
    }

    public final boolean o() {
        return this.k.B();
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.SimpleWrapperAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull VH vh, int i, @NonNull List<Object> list) {
        SwipeableItemViewHolder swipeableItemViewHolder = vh instanceof SwipeableItemViewHolder ? (SwipeableItemViewHolder) vh : null;
        float e = swipeableItemViewHolder != null ? e((SwipeableItemViewHolder) vh, o()) : 0.0f;
        if (g()) {
            m(vh, vh.getItemId() == this.l ? 3 : 1);
            super.onBindViewHolder(vh, i, list);
        } else {
            m(vh, 0);
            super.onBindViewHolder(vh, i, list);
        }
        if (swipeableItemViewHolder != null) {
            float e2 = e(swipeableItemViewHolder, o());
            boolean isProportionalSwipeAmountModeEnabled = swipeableItemViewHolder.isProportionalSwipeAmountModeEnabled();
            boolean isSwiping = this.k.isSwiping();
            boolean t = this.k.t(vh);
            if (e == e2 && (isSwiping || t)) {
                return;
            }
            this.k.b(vh, i, e, e2, isProportionalSwipeAmountModeEnabled, o(), true, isSwiping);
        }
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.SimpleWrapperAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        VH vh = (VH) super.onCreateViewHolder(viewGroup, i);
        if (vh instanceof SwipeableItemViewHolder) {
            ((SwipeableItemViewHolder) vh).setSwipeStateFlags(-1);
        }
        return vh;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.SimpleWrapperAdapter
    public void onHandleWrappedAdapterChanged() {
        if (g() && !this.m) {
            b();
        }
        super.onHandleWrappedAdapterChanged();
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.SimpleWrapperAdapter
    public void onHandleWrappedAdapterItemRangeChanged(int i, int i2) {
        super.onHandleWrappedAdapterItemRangeChanged(i, i2);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.SimpleWrapperAdapter
    public void onHandleWrappedAdapterItemRangeInserted(int i, int i2) {
        int k;
        if (g() && (k = this.k.k()) >= i) {
            this.k.D(k + i2);
        }
        super.onHandleWrappedAdapterItemRangeInserted(i, i2);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.SimpleWrapperAdapter
    public void onHandleWrappedAdapterItemRangeRemoved(int i, int i2) {
        if (g()) {
            int k = this.k.k();
            if (c(k, i, i2)) {
                b();
            } else if (i < k) {
                this.k.D(k - i2);
            }
        }
        super.onHandleWrappedAdapterItemRangeRemoved(i, i2);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.SimpleWrapperAdapter
    public void onHandleWrappedAdapterRangeMoved(int i, int i2, int i3) {
        if (g()) {
            this.k.C();
        }
        super.onHandleWrappedAdapterRangeMoved(i, i2, i3);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.SimpleWrapperAdapter
    public void onRelease() {
        super.onRelease();
        this.j = null;
        this.k = null;
        this.l = -1L;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.SimpleWrapperAdapter, com.h6ah4i.android.widget.advrecyclerview.adapter.WrappedAdapter
    public void onViewRecycled(@NonNull VH vh, int i) {
        super.onViewRecycled(vh, i);
        long j = this.l;
        if (j != -1 && j == vh.getItemId()) {
            this.k.cancelSwipe();
        }
        if (vh instanceof SwipeableItemViewHolder) {
            RecyclerViewSwipeManager recyclerViewSwipeManager = this.k;
            if (recyclerViewSwipeManager != null) {
                recyclerViewSwipeManager.c(vh);
            }
            SwipeableItemViewHolder swipeableItemViewHolder = (SwipeableItemViewHolder) vh;
            swipeableItemViewHolder.setSwipeResult(0);
            swipeableItemViewHolder.setAfterSwipeReaction(0);
            swipeableItemViewHolder.setSwipeItemHorizontalSlideAmount(0.0f);
            swipeableItemViewHolder.setSwipeItemVerticalSlideAmount(0.0f);
            swipeableItemViewHolder.setProportionalSwipeAmountModeEnabled(true);
            View b = f.b(swipeableItemViewHolder);
            if (b != null) {
                ViewCompat.animate(b).cancel();
                b.setTranslationX(0.0f);
                b.setTranslationY(0.0f);
            }
        }
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.SimpleWrapperAdapter
    public void onHandleWrappedAdapterItemRangeChanged(int i, int i2, Object obj) {
        super.onHandleWrappedAdapterItemRangeChanged(i, i2, obj);
    }
}

package com.h6ah4i.android.widget.advrecyclerview.adapter;

import android.view.ViewGroup;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.h6ah4i.android.widget.advrecyclerview.adapter.BridgeAdapterDataObserver;
import com.h6ah4i.android.widget.advrecyclerview.utils.WrappedAdapterUtils;
import java.util.Collections;
import java.util.List;
/* loaded from: classes11.dex */
public class SimpleWrapperAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> implements WrapperAdapter<VH>, BridgeAdapterDataObserver.Subscriber {
    public static final List<Object> FULL_UPDATE_PAYLOADS = Collections.emptyList();
    public RecyclerView.Adapter<VH> h;
    public BridgeAdapterDataObserver i;

    public SimpleWrapperAdapter(@NonNull RecyclerView.Adapter<VH> adapter) {
        this.h = adapter;
        BridgeAdapterDataObserver bridgeAdapterDataObserver = new BridgeAdapterDataObserver(this, adapter, null);
        this.i = bridgeAdapterDataObserver;
        this.h.registerAdapterDataObserver(bridgeAdapterDataObserver);
        super.setHasStableIds(this.h.hasStableIds());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (isWrappedAdapterAlive()) {
            return this.h.getItemCount();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return this.h.getItemId(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.h.getItemViewType(i);
    }

    @Nullable
    public RecyclerView.Adapter<VH> getWrappedAdapter() {
        return this.h;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.WrapperAdapter
    public void getWrappedAdapters(@NonNull List<RecyclerView.Adapter> list) {
        RecyclerView.Adapter<VH> adapter = this.h;
        if (adapter != null) {
            list.add(adapter);
        }
    }

    public boolean isWrappedAdapterAlive() {
        return this.h != null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        if (isWrappedAdapterAlive()) {
            this.h.onAttachedToRecyclerView(recyclerView);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull VH vh, int i) {
        onBindViewHolder(vh, i, FULL_UPDATE_PAYLOADS);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.BridgeAdapterDataObserver.Subscriber
    public final void onBridgedAdapterChanged(@NonNull RecyclerView.Adapter adapter, @Nullable Object obj) {
        onHandleWrappedAdapterChanged();
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.BridgeAdapterDataObserver.Subscriber
    public final void onBridgedAdapterItemRangeChanged(@NonNull RecyclerView.Adapter adapter, @Nullable Object obj, int i, int i2) {
        onHandleWrappedAdapterItemRangeChanged(i, i2);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.BridgeAdapterDataObserver.Subscriber
    public final void onBridgedAdapterItemRangeInserted(@NonNull RecyclerView.Adapter adapter, @Nullable Object obj, int i, int i2) {
        onHandleWrappedAdapterItemRangeInserted(i, i2);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.BridgeAdapterDataObserver.Subscriber
    public final void onBridgedAdapterItemRangeRemoved(@NonNull RecyclerView.Adapter adapter, @Nullable Object obj, int i, int i2) {
        onHandleWrappedAdapterItemRangeRemoved(i, i2);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.BridgeAdapterDataObserver.Subscriber
    public final void onBridgedAdapterRangeMoved(@NonNull RecyclerView.Adapter adapter, @Nullable Object obj, int i, int i2, int i3) {
        onHandleWrappedAdapterRangeMoved(i, i2, i3);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return this.h.onCreateViewHolder(viewGroup, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        if (isWrappedAdapterAlive()) {
            this.h.onDetachedFromRecyclerView(recyclerView);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public boolean onFailedToRecycleView(@NonNull VH vh) {
        return onFailedToRecycleView(vh, vh.getItemViewType());
    }

    public void onHandleWrappedAdapterChanged() {
        notifyDataSetChanged();
    }

    public void onHandleWrappedAdapterItemRangeChanged(int i, int i2) {
        notifyItemRangeChanged(i, i2);
    }

    public void onHandleWrappedAdapterItemRangeInserted(int i, int i2) {
        notifyItemRangeInserted(i, i2);
    }

    public void onHandleWrappedAdapterItemRangeRemoved(int i, int i2) {
        notifyItemRangeRemoved(i, i2);
    }

    public void onHandleWrappedAdapterRangeMoved(int i, int i2, int i3) {
        if (i3 == 1) {
            notifyItemMoved(i, i2);
            return;
        }
        throw new IllegalStateException("itemCount should be always 1  (actual: " + i3 + ")");
    }

    @CallSuper
    public void onRelease() {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewAttachedToWindow(@NonNull VH vh) {
        onViewAttachedToWindow(vh, vh.getItemViewType());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewDetachedFromWindow(@NonNull VH vh) {
        onViewDetachedFromWindow(vh, vh.getItemViewType());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewRecycled(@NonNull VH vh) {
        onViewRecycled(vh, vh.getItemViewType());
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.WrapperAdapter
    public void release() {
        BridgeAdapterDataObserver bridgeAdapterDataObserver;
        onRelease();
        RecyclerView.Adapter<VH> adapter = this.h;
        if (adapter != null && (bridgeAdapterDataObserver = this.i) != null) {
            adapter.unregisterAdapterDataObserver(bridgeAdapterDataObserver);
        }
        this.h = null;
        this.i = null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void setHasStableIds(boolean z) {
        super.setHasStableIds(z);
        if (isWrappedAdapterAlive()) {
            this.h.setHasStableIds(z);
        }
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.WrapperAdapter
    public void unwrapPosition(@NonNull UnwrapPositionResult unwrapPositionResult, int i) {
        unwrapPositionResult.adapter = getWrappedAdapter();
        unwrapPositionResult.position = i;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.WrapperAdapter
    public int wrapPosition(@NonNull AdapterPathSegment adapterPathSegment, int i) {
        if (adapterPathSegment.adapter == getWrappedAdapter()) {
            return i;
        }
        return -1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull VH vh, int i, @NonNull List<Object> list) {
        if (isWrappedAdapterAlive()) {
            this.h.onBindViewHolder(vh, i, list);
        }
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.BridgeAdapterDataObserver.Subscriber
    public final void onBridgedAdapterItemRangeChanged(@NonNull RecyclerView.Adapter adapter, @Nullable Object obj, int i, int i2, Object obj2) {
        onHandleWrappedAdapterItemRangeChanged(i, i2, obj2);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.WrappedAdapter
    public boolean onFailedToRecycleView(@NonNull VH vh, int i) {
        if (isWrappedAdapterAlive() ? WrappedAdapterUtils.invokeOnFailedToRecycleView(this.h, vh, i) : false) {
            return true;
        }
        return super.onFailedToRecycleView(vh);
    }

    public void onHandleWrappedAdapterItemRangeChanged(int i, int i2, Object obj) {
        notifyItemRangeChanged(i, i2, obj);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.WrappedAdapter
    public void onViewAttachedToWindow(@NonNull VH vh, int i) {
        if (isWrappedAdapterAlive()) {
            WrappedAdapterUtils.invokeOnViewAttachedToWindow(this.h, vh, i);
        }
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.WrappedAdapter
    public void onViewDetachedFromWindow(@NonNull VH vh, int i) {
        if (isWrappedAdapterAlive()) {
            WrappedAdapterUtils.invokeOnViewDetachedFromWindow(this.h, vh, i);
        }
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.adapter.WrappedAdapter
    public void onViewRecycled(@NonNull VH vh, int i) {
        if (isWrappedAdapterAlive()) {
            WrappedAdapterUtils.invokeOnViewRecycled(this.h, vh, i);
        }
    }
}

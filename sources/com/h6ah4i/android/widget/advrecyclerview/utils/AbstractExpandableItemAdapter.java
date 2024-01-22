package com.h6ah4i.android.widget.advrecyclerview.utils;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemAdapter;
import java.util.List;
/* loaded from: classes11.dex */
public abstract class AbstractExpandableItemAdapter<GVH extends RecyclerView.ViewHolder, CVH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ExpandableItemAdapter<GVH, CVH> {
    @Override // com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemAdapter
    public int getChildItemViewType(int i, int i2) {
        return 0;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemAdapter
    public int getGroupItemViewType(int i) {
        return 0;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemAdapter
    public boolean getInitialGroupExpandedState(int i) {
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemCount() {
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final long getItemId(int i) {
        return -1L;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemViewType(int i) {
        return 0;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemAdapter
    public void onBindChildViewHolder(@NonNull CVH cvh, int i, int i2, int i3, @NonNull List<Object> list) {
        onBindChildViewHolder(cvh, i, i2, i3);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemAdapter
    public void onBindGroupViewHolder(@NonNull GVH gvh, int i, int i2, @NonNull List<Object> list) {
        onBindGroupViewHolder(gvh, i, i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public final RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        throw new IllegalStateException("This method should not be called");
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemAdapter
    public boolean onHookGroupCollapse(int i, boolean z) {
        return true;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemAdapter
    public boolean onHookGroupCollapse(int i, boolean z, @Nullable Object obj) {
        return onHookGroupCollapse(i, z);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemAdapter
    public boolean onHookGroupExpand(int i, boolean z) {
        return true;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemAdapter
    public boolean onHookGroupExpand(int i, boolean z, @Nullable Object obj) {
        return onHookGroupExpand(i, z);
    }
}

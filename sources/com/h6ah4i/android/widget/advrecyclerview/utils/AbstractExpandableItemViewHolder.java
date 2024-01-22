package com.h6ah4i.android.widget.advrecyclerview.utils;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemState;
import com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemViewHolder;
/* loaded from: classes11.dex */
public abstract class AbstractExpandableItemViewHolder extends RecyclerView.ViewHolder implements ExpandableItemViewHolder {
    public final ExpandableItemState h;

    public AbstractExpandableItemViewHolder(@NonNull View view) {
        super(view);
        this.h = new ExpandableItemState();
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemViewHolder
    @NonNull
    public ExpandableItemState getExpandState() {
        return this.h;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemViewHolder
    public int getExpandStateFlags() {
        return this.h.getFlags();
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemViewHolder
    public void setExpandStateFlags(int i) {
        this.h.setFlags(i);
    }
}

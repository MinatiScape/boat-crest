package com.h6ah4i.android.widget.advrecyclerview.utils;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemState;
import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemViewHolder;
/* loaded from: classes11.dex */
public abstract class AbstractDraggableItemViewHolder extends RecyclerView.ViewHolder implements DraggableItemViewHolder {
    public final DraggableItemState h;

    public AbstractDraggableItemViewHolder(@NonNull View view) {
        super(view);
        this.h = new DraggableItemState();
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemViewHolder
    @NonNull
    public DraggableItemState getDragState() {
        return this.h;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemViewHolder
    public int getDragStateFlags() {
        return this.h.getFlags();
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemViewHolder
    public void setDragStateFlags(int i) {
        this.h.setFlags(i);
    }
}

package com.h6ah4i.android.widget.advrecyclerview.utils;

import android.view.View;
import androidx.annotation.NonNull;
import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemState;
import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemViewHolder;
/* loaded from: classes11.dex */
public abstract class AbstractDraggableSwipeableItemViewHolder extends AbstractSwipeableItemViewHolder implements DraggableItemViewHolder {
    public final DraggableItemState r;

    public AbstractDraggableSwipeableItemViewHolder(@NonNull View view) {
        super(view);
        this.r = new DraggableItemState();
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemViewHolder
    @NonNull
    public DraggableItemState getDragState() {
        return this.r;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemViewHolder
    public int getDragStateFlags() {
        return this.r.getFlags();
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemViewHolder
    public void setDragStateFlags(int i) {
        this.r.setFlags(i);
    }
}

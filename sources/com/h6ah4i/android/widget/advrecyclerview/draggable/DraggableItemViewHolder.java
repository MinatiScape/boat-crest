package com.h6ah4i.android.widget.advrecyclerview.draggable;

import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public interface DraggableItemViewHolder {
    @NonNull
    DraggableItemState getDragState();

    int getDragStateFlags();

    void setDragStateFlags(int i);
}

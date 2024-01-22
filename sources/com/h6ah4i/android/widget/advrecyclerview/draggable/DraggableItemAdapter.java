package com.h6ah4i.android.widget.advrecyclerview.draggable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
/* loaded from: classes11.dex */
public interface DraggableItemAdapter<T extends RecyclerView.ViewHolder> {
    boolean onCheckCanDrop(int i, int i2);

    boolean onCheckCanStartDrag(@NonNull T t, int i, int i2, int i3);

    @Nullable
    ItemDraggableRange onGetItemDraggableRange(@NonNull T t, int i);

    void onItemDragFinished(int i, int i2, boolean z);

    void onItemDragStarted(int i);

    void onMoveItem(int i, int i2);
}

package com.h6ah4i.android.widget.advrecyclerview.expandable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;
/* loaded from: classes11.dex */
public interface ExpandableDraggableItemAdapter<GVH extends RecyclerView.ViewHolder, CVH extends RecyclerView.ViewHolder> {
    boolean onCheckChildCanDrop(int i, int i2, int i3, int i4);

    boolean onCheckChildCanStartDrag(@NonNull CVH cvh, int i, int i2, int i3, int i4);

    boolean onCheckGroupCanDrop(int i, int i2);

    boolean onCheckGroupCanStartDrag(@NonNull GVH gvh, int i, int i2, int i3);

    void onChildDragFinished(int i, int i2, int i3, int i4, boolean z);

    void onChildDragStarted(int i, int i2);

    ItemDraggableRange onGetChildItemDraggableRange(@NonNull CVH cvh, int i, int i2);

    ItemDraggableRange onGetGroupItemDraggableRange(@NonNull GVH gvh, int i);

    void onGroupDragFinished(int i, int i2, boolean z);

    void onGroupDragStarted(int i);

    void onMoveChildItem(int i, int i2, int i3, int i4);

    void onMoveGroupItem(int i, int i2);
}

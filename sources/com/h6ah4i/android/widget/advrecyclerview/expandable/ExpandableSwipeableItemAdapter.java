package com.h6ah4i.android.widget.advrecyclerview.expandable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultAction;
/* loaded from: classes11.dex */
public interface ExpandableSwipeableItemAdapter<GVH extends RecyclerView.ViewHolder, CVH extends RecyclerView.ViewHolder> extends BaseExpandableSwipeableItemAdapter<GVH, CVH> {
    SwipeResultAction onSwipeChildItem(@NonNull CVH cvh, int i, int i2, int i3);

    SwipeResultAction onSwipeGroupItem(@NonNull GVH gvh, int i, int i2);
}

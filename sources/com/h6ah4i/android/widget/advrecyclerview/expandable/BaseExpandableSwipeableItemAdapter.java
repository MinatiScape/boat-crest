package com.h6ah4i.android.widget.advrecyclerview.expandable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
/* loaded from: classes11.dex */
public interface BaseExpandableSwipeableItemAdapter<GVH extends RecyclerView.ViewHolder, CVH extends RecyclerView.ViewHolder> {
    int onGetChildItemSwipeReactionType(@NonNull CVH cvh, int i, int i2, int i3, int i4);

    int onGetGroupItemSwipeReactionType(@NonNull GVH gvh, int i, int i2, int i3);

    void onSetChildItemSwipeBackground(@NonNull CVH cvh, int i, int i2, int i3);

    void onSetGroupItemSwipeBackground(@NonNull GVH gvh, int i, int i2);

    void onSwipeChildItemStarted(@NonNull CVH cvh, int i, int i2);

    void onSwipeGroupItemStarted(@NonNull GVH gvh, int i);
}

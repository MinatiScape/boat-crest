package com.h6ah4i.android.widget.advrecyclerview.expandable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultAction;
/* loaded from: classes11.dex */
public class d {
    public static SwipeResultAction a(@NonNull BaseExpandableSwipeableItemAdapter<?, ?> baseExpandableSwipeableItemAdapter, @NonNull RecyclerView.ViewHolder viewHolder, int i, int i2, int i3) {
        if (i2 == -1) {
            return ((ExpandableSwipeableItemAdapter) baseExpandableSwipeableItemAdapter).onSwipeGroupItem(viewHolder, i, i3);
        }
        return ((ExpandableSwipeableItemAdapter) baseExpandableSwipeableItemAdapter).onSwipeChildItem(viewHolder, i, i2, i3);
    }
}

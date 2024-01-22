package com.h6ah4i.android.widget.advrecyclerview.swipeable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultAction;
/* loaded from: classes11.dex */
public interface SwipeableItemAdapter<T extends RecyclerView.ViewHolder> {
    int onGetSwipeReactionType(@NonNull T t, int i, int i2, int i3);

    void onSetSwipeBackground(@NonNull T t, int i, int i2);

    @Nullable
    SwipeResultAction onSwipeItem(@NonNull T t, int i, int i2);

    void onSwipeItemStarted(@NonNull T t, int i);
}

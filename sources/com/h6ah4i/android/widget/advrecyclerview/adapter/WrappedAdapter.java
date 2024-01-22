package com.h6ah4i.android.widget.advrecyclerview.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
/* loaded from: classes11.dex */
public interface WrappedAdapter<VH extends RecyclerView.ViewHolder> {
    boolean onFailedToRecycleView(@NonNull VH vh, int i);

    void onViewAttachedToWindow(@NonNull VH vh, int i);

    void onViewDetachedFromWindow(@NonNull VH vh, int i);

    void onViewRecycled(@NonNull VH vh, int i);
}

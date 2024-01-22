package com.h6ah4i.android.widget.advrecyclerview.animator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
/* loaded from: classes11.dex */
public abstract class BaseItemAnimator extends SimpleItemAnimator {

    /* renamed from: a  reason: collision with root package name */
    public ItemAnimatorListener f11891a;

    /* loaded from: classes11.dex */
    public interface ItemAnimatorListener {
        void onAddFinished(@NonNull RecyclerView.ViewHolder viewHolder);

        void onChangeFinished(@NonNull RecyclerView.ViewHolder viewHolder);

        void onMoveFinished(@NonNull RecyclerView.ViewHolder viewHolder);

        void onRemoveFinished(@NonNull RecyclerView.ViewHolder viewHolder);
    }

    public boolean debugLogEnabled() {
        return false;
    }

    public boolean dispatchFinishedWhenDone() {
        if (isRunning()) {
            return false;
        }
        dispatchAnimationsFinished();
        return true;
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public final void onAddFinished(RecyclerView.ViewHolder viewHolder) {
        onAddFinishedImpl(viewHolder);
        ItemAnimatorListener itemAnimatorListener = this.f11891a;
        if (itemAnimatorListener != null) {
            itemAnimatorListener.onAddFinished(viewHolder);
        }
    }

    public void onAddFinishedImpl(@NonNull RecyclerView.ViewHolder viewHolder) {
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public final void onAddStarting(RecyclerView.ViewHolder viewHolder) {
        onAddStartingImpl(viewHolder);
    }

    public void onAddStartingImpl(@NonNull RecyclerView.ViewHolder viewHolder) {
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public final void onChangeFinished(RecyclerView.ViewHolder viewHolder, boolean z) {
        onChangeFinishedImpl(viewHolder, z);
        ItemAnimatorListener itemAnimatorListener = this.f11891a;
        if (itemAnimatorListener != null) {
            itemAnimatorListener.onChangeFinished(viewHolder);
        }
    }

    public void onChangeFinishedImpl(@NonNull RecyclerView.ViewHolder viewHolder, boolean z) {
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public final void onChangeStarting(RecyclerView.ViewHolder viewHolder, boolean z) {
        onChangeStartingItem(viewHolder, z);
    }

    public void onChangeStartingItem(@NonNull RecyclerView.ViewHolder viewHolder, boolean z) {
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public final void onMoveFinished(RecyclerView.ViewHolder viewHolder) {
        onMoveFinishedImpl(viewHolder);
        ItemAnimatorListener itemAnimatorListener = this.f11891a;
        if (itemAnimatorListener != null) {
            itemAnimatorListener.onMoveFinished(viewHolder);
        }
    }

    public void onMoveFinishedImpl(@NonNull RecyclerView.ViewHolder viewHolder) {
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public final void onMoveStarting(RecyclerView.ViewHolder viewHolder) {
        onMoveStartingImpl(viewHolder);
    }

    public void onMoveStartingImpl(@NonNull RecyclerView.ViewHolder viewHolder) {
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public final void onRemoveFinished(RecyclerView.ViewHolder viewHolder) {
        onRemoveFinishedImpl(viewHolder);
        ItemAnimatorListener itemAnimatorListener = this.f11891a;
        if (itemAnimatorListener != null) {
            itemAnimatorListener.onRemoveFinished(viewHolder);
        }
    }

    public void onRemoveFinishedImpl(@NonNull RecyclerView.ViewHolder viewHolder) {
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public final void onRemoveStarting(RecyclerView.ViewHolder viewHolder) {
        onRemoveStartingImpl(viewHolder);
    }

    public void onRemoveStartingImpl(@NonNull RecyclerView.ViewHolder viewHolder) {
    }

    public void setListener(@Nullable ItemAnimatorListener itemAnimatorListener) {
        this.f11891a = itemAnimatorListener;
    }
}

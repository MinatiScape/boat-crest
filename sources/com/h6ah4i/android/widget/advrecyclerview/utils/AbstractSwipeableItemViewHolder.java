package com.h6ah4i.android.widget.advrecyclerview.utils;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemState;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder;
/* loaded from: classes11.dex */
public abstract class AbstractSwipeableItemViewHolder extends RecyclerView.ViewHolder implements SwipeableItemViewHolder {
    public SwipeableItemState h;
    public int i;
    public int j;
    public boolean k;
    public float l;
    public float m;
    public float n;
    public float o;
    public float p;
    public float q;

    public AbstractSwipeableItemViewHolder(@NonNull View view) {
        super(view);
        this.h = new SwipeableItemState();
        this.i = 0;
        this.j = 0;
        this.k = true;
        this.n = -65536.0f;
        this.o = -65537.0f;
        this.p = 65536.0f;
        this.q = 65537.0f;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder
    public int getAfterSwipeReaction() {
        return this.j;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder
    public float getMaxDownSwipeAmount() {
        return this.q;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder
    public float getMaxLeftSwipeAmount() {
        return this.n;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder
    public float getMaxRightSwipeAmount() {
        return this.p;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder
    public float getMaxUpSwipeAmount() {
        return this.o;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder
    public float getSwipeItemHorizontalSlideAmount() {
        return this.l;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder
    public float getSwipeItemVerticalSlideAmount() {
        return this.m;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder
    public int getSwipeResult() {
        return this.i;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder
    @NonNull
    public SwipeableItemState getSwipeState() {
        return this.h;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder
    public int getSwipeStateFlags() {
        return this.h.getFlags();
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder
    @NonNull
    public abstract View getSwipeableContainerView();

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder
    public boolean isProportionalSwipeAmountModeEnabled() {
        return this.k;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder
    public void onSlideAmountUpdated(float f, float f2, boolean z) {
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder
    public void setAfterSwipeReaction(int i) {
        this.j = i;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder
    public void setMaxDownSwipeAmount(float f) {
        this.q = f;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder
    public void setMaxLeftSwipeAmount(float f) {
        this.n = f;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder
    public void setMaxRightSwipeAmount(float f) {
        this.p = f;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder
    public void setMaxUpSwipeAmount(float f) {
        this.o = f;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder
    public void setProportionalSwipeAmountModeEnabled(boolean z) {
        this.k = z;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder
    public void setSwipeItemHorizontalSlideAmount(float f) {
        this.l = f;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder
    public void setSwipeItemVerticalSlideAmount(float f) {
        this.m = f;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder
    public void setSwipeResult(int i) {
        this.i = i;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder
    public void setSwipeStateFlags(int i) {
        this.h.setFlags(i);
    }
}

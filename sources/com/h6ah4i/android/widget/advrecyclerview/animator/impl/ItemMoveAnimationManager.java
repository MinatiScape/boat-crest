package com.h6ah4i.android.widget.advrecyclerview.animator.impl;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.h6ah4i.android.widget.advrecyclerview.animator.BaseItemAnimator;
/* loaded from: classes11.dex */
public abstract class ItemMoveAnimationManager extends BaseItemAnimationManager<MoveAnimationInfo> {
    public static final String TAG = "ARVItemMoveAnimMgr";

    public ItemMoveAnimationManager(@NonNull BaseItemAnimator baseItemAnimator) {
        super(baseItemAnimator);
    }

    public abstract boolean addPendingAnimation(@NonNull RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, int i4);

    @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
    public long getDuration() {
        return this.mItemAnimator.getMoveDuration();
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
    public void setDuration(long j) {
        this.mItemAnimator.setMoveDuration(j);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
    public void dispatchFinished(@NonNull MoveAnimationInfo moveAnimationInfo, @NonNull RecyclerView.ViewHolder viewHolder) {
        if (debugLogEnabled()) {
            Log.d(TAG, "dispatchMoveFinished(" + viewHolder + ")");
        }
        this.mItemAnimator.dispatchMoveFinished(viewHolder);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
    public void dispatchStarting(@NonNull MoveAnimationInfo moveAnimationInfo, @NonNull RecyclerView.ViewHolder viewHolder) {
        if (debugLogEnabled()) {
            Log.d(TAG, "dispatchMoveStarting(" + viewHolder + ")");
        }
        this.mItemAnimator.dispatchMoveStarting(viewHolder);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
    public boolean endNotStartedAnimation(@NonNull MoveAnimationInfo moveAnimationInfo, @Nullable RecyclerView.ViewHolder viewHolder) {
        RecyclerView.ViewHolder viewHolder2 = moveAnimationInfo.holder;
        if (viewHolder2 != null) {
            if (viewHolder == null || viewHolder2 == viewHolder) {
                onAnimationEndedBeforeStarted(moveAnimationInfo, viewHolder2);
                dispatchFinished(moveAnimationInfo, moveAnimationInfo.holder);
                moveAnimationInfo.clear(moveAnimationInfo.holder);
                return true;
            }
            return false;
        }
        return false;
    }
}

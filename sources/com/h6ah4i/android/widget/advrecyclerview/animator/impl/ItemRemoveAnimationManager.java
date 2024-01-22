package com.h6ah4i.android.widget.advrecyclerview.animator.impl;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.h6ah4i.android.widget.advrecyclerview.animator.BaseItemAnimator;
/* loaded from: classes11.dex */
public abstract class ItemRemoveAnimationManager extends BaseItemAnimationManager<RemoveAnimationInfo> {
    public ItemRemoveAnimationManager(BaseItemAnimator baseItemAnimator) {
        super(baseItemAnimator);
    }

    public abstract boolean addPendingAnimation(RecyclerView.ViewHolder viewHolder);

    @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
    public long getDuration() {
        return this.mItemAnimator.getRemoveDuration();
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
    public void setDuration(long j) {
        this.mItemAnimator.setRemoveDuration(j);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
    public void dispatchFinished(@NonNull RemoveAnimationInfo removeAnimationInfo, @NonNull RecyclerView.ViewHolder viewHolder) {
        if (debugLogEnabled()) {
            Log.d("ARVItemRemoveAnimMgr", "dispatchRemoveFinished(" + viewHolder + ")");
        }
        this.mItemAnimator.dispatchRemoveFinished(viewHolder);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
    public void dispatchStarting(@NonNull RemoveAnimationInfo removeAnimationInfo, @NonNull RecyclerView.ViewHolder viewHolder) {
        if (debugLogEnabled()) {
            Log.d("ARVItemRemoveAnimMgr", "dispatchRemoveStarting(" + viewHolder + ")");
        }
        this.mItemAnimator.dispatchRemoveStarting(viewHolder);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
    public boolean endNotStartedAnimation(@NonNull RemoveAnimationInfo removeAnimationInfo, @Nullable RecyclerView.ViewHolder viewHolder) {
        RecyclerView.ViewHolder viewHolder2 = removeAnimationInfo.holder;
        if (viewHolder2 != null) {
            if (viewHolder == null || viewHolder2 == viewHolder) {
                onAnimationEndedBeforeStarted(removeAnimationInfo, viewHolder2);
                dispatchFinished(removeAnimationInfo, removeAnimationInfo.holder);
                removeAnimationInfo.clear(removeAnimationInfo.holder);
                return true;
            }
            return false;
        }
        return false;
    }
}

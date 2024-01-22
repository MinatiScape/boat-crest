package com.h6ah4i.android.widget.advrecyclerview.animator.impl;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.h6ah4i.android.widget.advrecyclerview.animator.BaseItemAnimator;
/* loaded from: classes11.dex */
public abstract class ItemChangeAnimationManager extends BaseItemAnimationManager<ChangeAnimationInfo> {
    public ItemChangeAnimationManager(@NonNull BaseItemAnimator baseItemAnimator) {
        super(baseItemAnimator);
    }

    public abstract boolean addPendingAnimation(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, int i, int i2, int i3, int i4);

    @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
    public long getDuration() {
        return this.mItemAnimator.getChangeDuration();
    }

    public abstract void onCreateChangeAnimationForNewItem(ChangeAnimationInfo changeAnimationInfo);

    public abstract void onCreateChangeAnimationForOldItem(ChangeAnimationInfo changeAnimationInfo);

    @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
    public void setDuration(long j) {
        this.mItemAnimator.setChangeDuration(j);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
    public void dispatchFinished(@NonNull ChangeAnimationInfo changeAnimationInfo, @NonNull RecyclerView.ViewHolder viewHolder) {
        if (debugLogEnabled()) {
            Log.d("ARVItemChangeAnimMgr", "dispatchChangeFinished(" + viewHolder + ")");
        }
        this.mItemAnimator.dispatchChangeFinished(viewHolder, viewHolder == changeAnimationInfo.oldHolder);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
    public void dispatchStarting(@NonNull ChangeAnimationInfo changeAnimationInfo, @NonNull RecyclerView.ViewHolder viewHolder) {
        if (debugLogEnabled()) {
            Log.d("ARVItemChangeAnimMgr", "dispatchChangeStarting(" + viewHolder + ")");
        }
        this.mItemAnimator.dispatchChangeStarting(viewHolder, viewHolder == changeAnimationInfo.oldHolder);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
    public boolean endNotStartedAnimation(@NonNull ChangeAnimationInfo changeAnimationInfo, @Nullable RecyclerView.ViewHolder viewHolder) {
        RecyclerView.ViewHolder viewHolder2 = changeAnimationInfo.oldHolder;
        if (viewHolder2 != null && (viewHolder == null || viewHolder2 == viewHolder)) {
            onAnimationEndedBeforeStarted(changeAnimationInfo, viewHolder2);
            dispatchFinished(changeAnimationInfo, changeAnimationInfo.oldHolder);
            changeAnimationInfo.clear(changeAnimationInfo.oldHolder);
        }
        RecyclerView.ViewHolder viewHolder3 = changeAnimationInfo.newHolder;
        if (viewHolder3 != null && (viewHolder == null || viewHolder3 == viewHolder)) {
            onAnimationEndedBeforeStarted(changeAnimationInfo, viewHolder3);
            dispatchFinished(changeAnimationInfo, changeAnimationInfo.newHolder);
            changeAnimationInfo.clear(changeAnimationInfo.newHolder);
        }
        return changeAnimationInfo.oldHolder == null && changeAnimationInfo.newHolder == null;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
    public void onCreateAnimation(@NonNull ChangeAnimationInfo changeAnimationInfo) {
        if (changeAnimationInfo.oldHolder != null) {
            onCreateChangeAnimationForOldItem(changeAnimationInfo);
        }
        if (changeAnimationInfo.newHolder != null) {
            onCreateChangeAnimationForNewItem(changeAnimationInfo);
        }
    }
}

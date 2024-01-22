package com.h6ah4i.android.widget.advrecyclerview.animator.impl;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.h6ah4i.android.widget.advrecyclerview.animator.BaseItemAnimator;
/* loaded from: classes11.dex */
public abstract class ItemAddAnimationManager extends BaseItemAnimationManager<AddAnimationInfo> {
    public ItemAddAnimationManager(BaseItemAnimator baseItemAnimator) {
        super(baseItemAnimator);
    }

    public abstract boolean addPendingAnimation(RecyclerView.ViewHolder viewHolder);

    @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
    public long getDuration() {
        return this.mItemAnimator.getAddDuration();
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
    public void setDuration(long j) {
        this.mItemAnimator.setAddDuration(j);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
    public void dispatchFinished(@NonNull AddAnimationInfo addAnimationInfo, @NonNull RecyclerView.ViewHolder viewHolder) {
        if (debugLogEnabled()) {
            Log.d("ARVItemAddAnimMgr", "dispatchAddFinished(" + viewHolder + ")");
        }
        this.mItemAnimator.dispatchAddFinished(viewHolder);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
    public void dispatchStarting(@NonNull AddAnimationInfo addAnimationInfo, @NonNull RecyclerView.ViewHolder viewHolder) {
        if (debugLogEnabled()) {
            Log.d("ARVItemAddAnimMgr", "dispatchAddStarting(" + viewHolder + ")");
        }
        this.mItemAnimator.dispatchAddStarting(viewHolder);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
    public boolean endNotStartedAnimation(@NonNull AddAnimationInfo addAnimationInfo, @Nullable RecyclerView.ViewHolder viewHolder) {
        RecyclerView.ViewHolder viewHolder2 = addAnimationInfo.holder;
        if (viewHolder2 != null) {
            if (viewHolder == null || viewHolder2 == viewHolder) {
                onAnimationEndedBeforeStarted(addAnimationInfo, viewHolder2);
                dispatchFinished(addAnimationInfo, addAnimationInfo.holder);
                addAnimationInfo.clear(addAnimationInfo.holder);
                return true;
            }
            return false;
        }
        return false;
    }
}

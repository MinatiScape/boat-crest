package com.h6ah4i.android.widget.advrecyclerview.animator;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.h6ah4i.android.widget.advrecyclerview.animator.impl.ItemAddAnimationManager;
import com.h6ah4i.android.widget.advrecyclerview.animator.impl.ItemChangeAnimationManager;
import com.h6ah4i.android.widget.advrecyclerview.animator.impl.ItemMoveAnimationManager;
import com.h6ah4i.android.widget.advrecyclerview.animator.impl.ItemRemoveAnimationManager;
/* loaded from: classes11.dex */
public abstract class GeneralItemAnimator extends BaseItemAnimator {
    public boolean b;
    public ItemRemoveAnimationManager c;
    public ItemAddAnimationManager d;
    public ItemChangeAnimationManager e;
    public ItemMoveAnimationManager f;

    public GeneralItemAnimator() {
        a();
    }

    public final void a() {
        onSetup();
        if (this.c == null || this.d == null || this.e == null || this.f == null) {
            throw new IllegalStateException("setup incomplete");
        }
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public boolean animateAdd(RecyclerView.ViewHolder viewHolder) {
        if (this.b) {
            Log.d("ARVGeneralItemAnimator", "animateAdd(id = " + viewHolder.getItemId() + ", position = " + viewHolder.getLayoutPosition() + ")");
        }
        return this.d.addPendingAnimation(viewHolder);
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public boolean animateChange(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, int i, int i2, int i3, int i4) {
        if (viewHolder == viewHolder2) {
            return this.f.addPendingAnimation(viewHolder, i, i2, i3, i4);
        }
        if (this.b) {
            String l = viewHolder != null ? Long.toString(viewHolder.getItemId()) : "-";
            String l2 = viewHolder != null ? Long.toString(viewHolder.getLayoutPosition()) : "-";
            String l3 = viewHolder2 != null ? Long.toString(viewHolder2.getItemId()) : "-";
            String l4 = viewHolder2 != null ? Long.toString(viewHolder2.getLayoutPosition()) : "-";
            Log.d("ARVGeneralItemAnimator", "animateChange(old.id = " + l + ", old.position = " + l2 + ", new.id = " + l3 + ", new.position = " + l4 + ", fromX = " + i + ", fromY = " + i2 + ", toX = " + i3 + ", toY = " + i4 + ")");
        }
        return this.e.addPendingAnimation(viewHolder, viewHolder2, i, i2, i3, i4);
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public boolean animateMove(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, int i4) {
        if (this.b) {
            Log.d("ARVGeneralItemAnimator", "animateMove(id = " + viewHolder.getItemId() + ", position = " + viewHolder.getLayoutPosition() + ", fromX = " + i + ", fromY = " + i2 + ", toX = " + i3 + ", toY = " + i4 + ")");
        }
        return this.f.addPendingAnimation(viewHolder, i, i2, i3, i4);
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public boolean animateRemove(RecyclerView.ViewHolder viewHolder) {
        if (this.b) {
            Log.d("ARVGeneralItemAnimator", "animateRemove(id = " + viewHolder.getItemId() + ", position = " + viewHolder.getLayoutPosition() + ")");
        }
        return this.c.addPendingAnimation(viewHolder);
    }

    public void cancelAnimations(RecyclerView.ViewHolder viewHolder) {
        ViewCompat.animate(viewHolder.itemView).cancel();
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.animator.BaseItemAnimator
    public boolean debugLogEnabled() {
        return this.b;
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.animator.BaseItemAnimator
    public boolean dispatchFinishedWhenDone() {
        if (this.b && !isRunning()) {
            Log.d("ARVGeneralItemAnimator", "dispatchFinishedWhenDone()");
        }
        return super.dispatchFinishedWhenDone();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public void endAnimation(@NonNull RecyclerView.ViewHolder viewHolder) {
        cancelAnimations(viewHolder);
        this.f.endPendingAnimations(viewHolder);
        this.e.endPendingAnimations(viewHolder);
        this.c.endPendingAnimations(viewHolder);
        this.d.endPendingAnimations(viewHolder);
        this.f.endDeferredReadyAnimations(viewHolder);
        this.e.endDeferredReadyAnimations(viewHolder);
        this.c.endDeferredReadyAnimations(viewHolder);
        this.d.endDeferredReadyAnimations(viewHolder);
        if (this.c.removeFromActive(viewHolder) && this.b) {
            throw new IllegalStateException("after animation is cancelled, item should not be in the active animation list [remove]");
        }
        if (this.d.removeFromActive(viewHolder) && this.b) {
            throw new IllegalStateException("after animation is cancelled, item should not be in the active animation list [add]");
        }
        if (this.e.removeFromActive(viewHolder) && this.b) {
            throw new IllegalStateException("after animation is cancelled, item should not be in the active animation list [change]");
        }
        if (this.f.removeFromActive(viewHolder) && this.b) {
            throw new IllegalStateException("after animation is cancelled, item should not be in the active animation list [move]");
        }
        dispatchFinishedWhenDone();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public void endAnimations() {
        this.f.endAllPendingAnimations();
        this.c.endAllPendingAnimations();
        this.d.endAllPendingAnimations();
        this.e.endAllPendingAnimations();
        if (isRunning()) {
            this.f.endAllDeferredReadyAnimations();
            this.d.endAllDeferredReadyAnimations();
            this.e.endAllDeferredReadyAnimations();
            this.c.cancelAllStartedAnimations();
            this.f.cancelAllStartedAnimations();
            this.d.cancelAllStartedAnimations();
            this.e.cancelAllStartedAnimations();
            dispatchAnimationsFinished();
        }
    }

    public ItemAddAnimationManager getItemAddAnimationsManager() {
        return this.d;
    }

    public ItemChangeAnimationManager getItemChangeAnimationsManager() {
        return this.e;
    }

    public ItemMoveAnimationManager getItemMoveAnimationsManager() {
        return this.f;
    }

    public ItemRemoveAnimationManager getRemoveAnimationManager() {
        return this.c;
    }

    public boolean hasPendingAnimations() {
        return this.c.hasPending() || this.f.hasPending() || this.e.hasPending() || this.d.hasPending();
    }

    public boolean isDebug() {
        return this.b;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean isRunning() {
        return this.c.isRunning() || this.d.isRunning() || this.e.isRunning() || this.f.isRunning();
    }

    public void onSchedulePendingAnimations() {
        schedulePendingAnimationsByDefaultRule();
    }

    public abstract void onSetup();

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public void runPendingAnimations() {
        if (hasPendingAnimations()) {
            onSchedulePendingAnimations();
        }
    }

    public void schedulePendingAnimationsByDefaultRule() {
        boolean hasPending = this.c.hasPending();
        boolean hasPending2 = this.f.hasPending();
        boolean hasPending3 = this.e.hasPending();
        boolean hasPending4 = this.d.hasPending();
        long removeDuration = hasPending ? getRemoveDuration() : 0L;
        long moveDuration = hasPending2 ? getMoveDuration() : 0L;
        long changeDuration = hasPending3 ? getChangeDuration() : 0L;
        boolean z = false;
        if (hasPending) {
            this.c.runPendingAnimations(false, 0L);
        }
        if (hasPending2) {
            this.f.runPendingAnimations(hasPending, removeDuration);
        }
        if (hasPending3) {
            this.e.runPendingAnimations(hasPending, removeDuration);
        }
        if (hasPending4) {
            z = (hasPending || hasPending2 || hasPending3) ? true : true;
            this.d.runPendingAnimations(z, z ? removeDuration + Math.max(moveDuration, changeDuration) : 0L);
        }
    }

    public void setDebug(boolean z) {
        this.b = z;
    }

    public void setItemAddAnimationsManager(ItemAddAnimationManager itemAddAnimationManager) {
        this.d = itemAddAnimationManager;
    }

    public void setItemChangeAnimationsManager(ItemChangeAnimationManager itemChangeAnimationManager) {
        this.e = itemChangeAnimationManager;
    }

    public void setItemMoveAnimationsManager(ItemMoveAnimationManager itemMoveAnimationManager) {
        this.f = itemMoveAnimationManager;
    }

    public void setItemRemoveAnimationManager(ItemRemoveAnimationManager itemRemoveAnimationManager) {
        this.c = itemRemoveAnimationManager;
    }
}

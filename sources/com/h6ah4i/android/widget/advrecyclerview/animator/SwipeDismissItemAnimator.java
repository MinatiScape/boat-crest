package com.h6ah4i.android.widget.advrecyclerview.animator;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.h6ah4i.android.widget.advrecyclerview.animator.RefactoredDefaultItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.animator.impl.ItemRemoveAnimationManager;
import com.h6ah4i.android.widget.advrecyclerview.animator.impl.RemoveAnimationInfo;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemViewHolder;
/* loaded from: classes11.dex */
public class SwipeDismissItemAnimator extends DraggableItemAnimator {
    public static final Interpolator MOVE_INTERPOLATOR = new AccelerateDecelerateInterpolator();

    /* loaded from: classes11.dex */
    public static class SwipeDismissItemRemoveAnimationManager extends ItemRemoveAnimationManager {
        public static final Interpolator DEFAULT_INTERPOLATOR = new AccelerateDecelerateInterpolator();

        public SwipeDismissItemRemoveAnimationManager(@NonNull BaseItemAnimator baseItemAnimator) {
            super(baseItemAnimator);
        }

        public static boolean isSwipeDismissed(RecyclerView.ViewHolder viewHolder) {
            if (viewHolder instanceof SwipeableItemViewHolder) {
                SwipeableItemViewHolder swipeableItemViewHolder = (SwipeableItemViewHolder) viewHolder;
                int swipeResult = swipeableItemViewHolder.getSwipeResult();
                return (swipeResult == 2 || swipeResult == 3 || swipeResult == 4 || swipeResult == 5) && swipeableItemViewHolder.getAfterSwipeReaction() == 1;
            }
            return false;
        }

        @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.ItemRemoveAnimationManager
        public boolean addPendingAnimation(@NonNull RecyclerView.ViewHolder viewHolder) {
            if (isSwipeDismissed(viewHolder)) {
                View view = viewHolder.itemView;
                endAnimation(viewHolder);
                view.setTranslationX((int) (view.getTranslationX() + 0.5f));
                view.setTranslationY((int) (view.getTranslationY() + 0.5f));
                enqueuePendingAnimationInfo(new SwipeDismissRemoveAnimationInfo(viewHolder));
                return true;
            }
            endAnimation(viewHolder);
            enqueuePendingAnimationInfo(new RemoveAnimationInfo(viewHolder));
            return true;
        }

        @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
        public void onAnimationCancel(@NonNull RemoveAnimationInfo removeAnimationInfo, @NonNull RecyclerView.ViewHolder viewHolder) {
        }

        @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
        public void onAnimationEndedBeforeStarted(@NonNull RemoveAnimationInfo removeAnimationInfo, @Nullable RecyclerView.ViewHolder viewHolder) {
            View view = viewHolder.itemView;
            if (isSwipeDismissed(removeAnimationInfo)) {
                view.setTranslationX(0.0f);
                view.setTranslationY(0.0f);
                return;
            }
            view.setAlpha(1.0f);
        }

        @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
        public void onAnimationEndedSuccessfully(@NonNull RemoveAnimationInfo removeAnimationInfo, @NonNull RecyclerView.ViewHolder viewHolder) {
            View view = viewHolder.itemView;
            if (isSwipeDismissed(removeAnimationInfo)) {
                view.setTranslationX(0.0f);
                view.setTranslationY(0.0f);
                return;
            }
            view.setAlpha(1.0f);
        }

        @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
        public void onCreateAnimation(@NonNull RemoveAnimationInfo removeAnimationInfo) {
            ViewPropertyAnimatorCompat animate;
            if (isSwipeDismissed(removeAnimationInfo.holder)) {
                animate = ViewCompat.animate(removeAnimationInfo.holder.itemView);
                animate.setDuration(getDuration());
            } else {
                animate = ViewCompat.animate(removeAnimationInfo.holder.itemView);
                animate.setDuration(getDuration());
                animate.setInterpolator(DEFAULT_INTERPOLATOR);
                animate.alpha(0.0f);
            }
            startActiveItemAnimation(removeAnimationInfo, removeAnimationInfo.holder, animate);
        }

        public static boolean isSwipeDismissed(RemoveAnimationInfo removeAnimationInfo) {
            return removeAnimationInfo instanceof SwipeDismissRemoveAnimationInfo;
        }
    }

    /* loaded from: classes11.dex */
    public static class SwipeDismissRemoveAnimationInfo extends RemoveAnimationInfo {
        public SwipeDismissRemoveAnimationInfo(@NonNull RecyclerView.ViewHolder viewHolder) {
            super(viewHolder);
        }
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.animator.DraggableItemAnimator, com.h6ah4i.android.widget.advrecyclerview.animator.RefactoredDefaultItemAnimator, com.h6ah4i.android.widget.advrecyclerview.animator.GeneralItemAnimator
    public void onSetup() {
        setItemAddAnimationsManager(new RefactoredDefaultItemAnimator.DefaultItemAddAnimationManager(this));
        setItemRemoveAnimationManager(new SwipeDismissItemRemoveAnimationManager(this));
        setItemChangeAnimationsManager(new RefactoredDefaultItemAnimator.DefaultItemChangeAnimationManager(this));
        setItemMoveAnimationsManager(new RefactoredDefaultItemAnimator.DefaultItemMoveAnimationManager(this));
        setRemoveDuration(150L);
        setMoveDuration(150L);
    }
}

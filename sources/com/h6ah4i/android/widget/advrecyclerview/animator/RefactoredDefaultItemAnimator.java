package com.h6ah4i.android.widget.advrecyclerview.animator;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.h6ah4i.android.widget.advrecyclerview.animator.impl.AddAnimationInfo;
import com.h6ah4i.android.widget.advrecyclerview.animator.impl.ChangeAnimationInfo;
import com.h6ah4i.android.widget.advrecyclerview.animator.impl.ItemAddAnimationManager;
import com.h6ah4i.android.widget.advrecyclerview.animator.impl.ItemChangeAnimationManager;
import com.h6ah4i.android.widget.advrecyclerview.animator.impl.ItemMoveAnimationManager;
import com.h6ah4i.android.widget.advrecyclerview.animator.impl.ItemRemoveAnimationManager;
import com.h6ah4i.android.widget.advrecyclerview.animator.impl.MoveAnimationInfo;
import com.h6ah4i.android.widget.advrecyclerview.animator.impl.RemoveAnimationInfo;
import java.util.List;
/* loaded from: classes11.dex */
public class RefactoredDefaultItemAnimator extends GeneralItemAnimator {

    /* loaded from: classes11.dex */
    public static class DefaultItemAddAnimationManager extends ItemAddAnimationManager {
        public DefaultItemAddAnimationManager(@NonNull BaseItemAnimator baseItemAnimator) {
            super(baseItemAnimator);
        }

        @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.ItemAddAnimationManager
        public boolean addPendingAnimation(@NonNull RecyclerView.ViewHolder viewHolder) {
            resetAnimation(viewHolder);
            viewHolder.itemView.setAlpha(0.0f);
            enqueuePendingAnimationInfo(new AddAnimationInfo(viewHolder));
            return true;
        }

        @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
        public void onAnimationEndedSuccessfully(@NonNull AddAnimationInfo addAnimationInfo, @NonNull RecyclerView.ViewHolder viewHolder) {
        }

        @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
        public void onAnimationCancel(@NonNull AddAnimationInfo addAnimationInfo, @NonNull RecyclerView.ViewHolder viewHolder) {
            viewHolder.itemView.setAlpha(1.0f);
        }

        @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
        public void onAnimationEndedBeforeStarted(@NonNull AddAnimationInfo addAnimationInfo, @Nullable RecyclerView.ViewHolder viewHolder) {
            viewHolder.itemView.setAlpha(1.0f);
        }

        @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
        public void onCreateAnimation(@NonNull AddAnimationInfo addAnimationInfo) {
            ViewPropertyAnimatorCompat animate = ViewCompat.animate(addAnimationInfo.holder.itemView);
            animate.alpha(1.0f);
            animate.setDuration(getDuration());
            startActiveItemAnimation(addAnimationInfo, addAnimationInfo.holder, animate);
        }
    }

    /* loaded from: classes11.dex */
    public static class DefaultItemChangeAnimationManager extends ItemChangeAnimationManager {
        public DefaultItemChangeAnimationManager(@NonNull BaseItemAnimator baseItemAnimator) {
            super(baseItemAnimator);
        }

        @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.ItemChangeAnimationManager
        public boolean addPendingAnimation(@NonNull RecyclerView.ViewHolder viewHolder, @Nullable RecyclerView.ViewHolder viewHolder2, int i, int i2, int i3, int i4) {
            float translationX = viewHolder.itemView.getTranslationX();
            float translationY = viewHolder.itemView.getTranslationY();
            float alpha = viewHolder.itemView.getAlpha();
            resetAnimation(viewHolder);
            int i5 = (int) ((i3 - i) - translationX);
            int i6 = (int) ((i4 - i2) - translationY);
            viewHolder.itemView.setTranslationX(translationX);
            viewHolder.itemView.setTranslationY(translationY);
            viewHolder.itemView.setAlpha(alpha);
            if (viewHolder2 != null) {
                resetAnimation(viewHolder2);
                viewHolder2.itemView.setTranslationX(-i5);
                viewHolder2.itemView.setTranslationY(-i6);
                viewHolder2.itemView.setAlpha(0.0f);
            }
            enqueuePendingAnimationInfo(new ChangeAnimationInfo(viewHolder, viewHolder2, i, i2, i3, i4));
            return true;
        }

        @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
        public void onAnimationCancel(@NonNull ChangeAnimationInfo changeAnimationInfo, @NonNull RecyclerView.ViewHolder viewHolder) {
        }

        @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.ItemChangeAnimationManager
        public void onCreateChangeAnimationForNewItem(@NonNull ChangeAnimationInfo changeAnimationInfo) {
            ViewPropertyAnimatorCompat animate = ViewCompat.animate(changeAnimationInfo.newHolder.itemView);
            animate.translationX(0.0f);
            animate.translationY(0.0f);
            animate.setDuration(getDuration());
            animate.alpha(1.0f);
            startActiveItemAnimation(changeAnimationInfo, changeAnimationInfo.newHolder, animate);
        }

        @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.ItemChangeAnimationManager
        public void onCreateChangeAnimationForOldItem(@NonNull ChangeAnimationInfo changeAnimationInfo) {
            ViewPropertyAnimatorCompat animate = ViewCompat.animate(changeAnimationInfo.oldHolder.itemView);
            animate.setDuration(getDuration());
            animate.translationX(changeAnimationInfo.toX - changeAnimationInfo.fromX);
            animate.translationY(changeAnimationInfo.toY - changeAnimationInfo.fromY);
            animate.alpha(0.0f);
            startActiveItemAnimation(changeAnimationInfo, changeAnimationInfo.oldHolder, animate);
        }

        @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
        public void onAnimationEndedBeforeStarted(@NonNull ChangeAnimationInfo changeAnimationInfo, @NonNull RecyclerView.ViewHolder viewHolder) {
            View view = viewHolder.itemView;
            view.setAlpha(1.0f);
            view.setTranslationX(0.0f);
            view.setTranslationY(0.0f);
        }

        @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
        public void onAnimationEndedSuccessfully(@NonNull ChangeAnimationInfo changeAnimationInfo, @Nullable RecyclerView.ViewHolder viewHolder) {
            View view = viewHolder.itemView;
            view.setAlpha(1.0f);
            view.setTranslationX(0.0f);
            view.setTranslationY(0.0f);
        }
    }

    /* loaded from: classes11.dex */
    public static class DefaultItemMoveAnimationManager extends ItemMoveAnimationManager {
        public DefaultItemMoveAnimationManager(@NonNull BaseItemAnimator baseItemAnimator) {
            super(baseItemAnimator);
        }

        @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.ItemMoveAnimationManager
        public boolean addPendingAnimation(@NonNull RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, int i4) {
            View view = viewHolder.itemView;
            int translationX = (int) (i + view.getTranslationX());
            int translationY = (int) (i2 + viewHolder.itemView.getTranslationY());
            resetAnimation(viewHolder);
            int i5 = i3 - translationX;
            int i6 = i4 - translationY;
            MoveAnimationInfo moveAnimationInfo = new MoveAnimationInfo(viewHolder, translationX, translationY, i3, i4);
            if (i5 == 0 && i6 == 0) {
                dispatchFinished(moveAnimationInfo, moveAnimationInfo.holder);
                moveAnimationInfo.clear(moveAnimationInfo.holder);
                return false;
            }
            if (i5 != 0) {
                view.setTranslationX(-i5);
            }
            if (i6 != 0) {
                view.setTranslationY(-i6);
            }
            enqueuePendingAnimationInfo(moveAnimationInfo);
            return true;
        }

        @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
        public void onAnimationEndedSuccessfully(@NonNull MoveAnimationInfo moveAnimationInfo, @NonNull RecyclerView.ViewHolder viewHolder) {
        }

        @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
        public void onAnimationCancel(@NonNull MoveAnimationInfo moveAnimationInfo, @NonNull RecyclerView.ViewHolder viewHolder) {
            View view = viewHolder.itemView;
            int i = moveAnimationInfo.toX - moveAnimationInfo.fromX;
            int i2 = moveAnimationInfo.toY - moveAnimationInfo.fromY;
            if (i != 0) {
                ViewCompat.animate(view).translationX(0.0f);
            }
            if (i2 != 0) {
                ViewCompat.animate(view).translationY(0.0f);
            }
            if (i != 0) {
                view.setTranslationX(0.0f);
            }
            if (i2 != 0) {
                view.setTranslationY(0.0f);
            }
        }

        @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
        public void onAnimationEndedBeforeStarted(@NonNull MoveAnimationInfo moveAnimationInfo, @Nullable RecyclerView.ViewHolder viewHolder) {
            View view = viewHolder.itemView;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
        }

        @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
        public void onCreateAnimation(@NonNull MoveAnimationInfo moveAnimationInfo) {
            View view = moveAnimationInfo.holder.itemView;
            int i = moveAnimationInfo.toX - moveAnimationInfo.fromX;
            int i2 = moveAnimationInfo.toY - moveAnimationInfo.fromY;
            if (i != 0) {
                ViewCompat.animate(view).translationX(0.0f);
            }
            if (i2 != 0) {
                ViewCompat.animate(view).translationY(0.0f);
            }
            ViewPropertyAnimatorCompat animate = ViewCompat.animate(view);
            animate.setDuration(getDuration());
            startActiveItemAnimation(moveAnimationInfo, moveAnimationInfo.holder, animate);
        }
    }

    /* loaded from: classes11.dex */
    public static class DefaultItemRemoveAnimationManager extends ItemRemoveAnimationManager {
        public DefaultItemRemoveAnimationManager(@NonNull BaseItemAnimator baseItemAnimator) {
            super(baseItemAnimator);
        }

        @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.ItemRemoveAnimationManager
        public boolean addPendingAnimation(@NonNull RecyclerView.ViewHolder viewHolder) {
            resetAnimation(viewHolder);
            enqueuePendingAnimationInfo(new RemoveAnimationInfo(viewHolder));
            return true;
        }

        @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
        public void onAnimationCancel(@NonNull RemoveAnimationInfo removeAnimationInfo, @NonNull RecyclerView.ViewHolder viewHolder) {
        }

        @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
        public void onAnimationEndedBeforeStarted(@NonNull RemoveAnimationInfo removeAnimationInfo, @Nullable RecyclerView.ViewHolder viewHolder) {
            viewHolder.itemView.setAlpha(1.0f);
        }

        @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
        public void onAnimationEndedSuccessfully(@NonNull RemoveAnimationInfo removeAnimationInfo, @NonNull RecyclerView.ViewHolder viewHolder) {
            viewHolder.itemView.setAlpha(1.0f);
        }

        @Override // com.h6ah4i.android.widget.advrecyclerview.animator.impl.BaseItemAnimationManager
        public void onCreateAnimation(@NonNull RemoveAnimationInfo removeAnimationInfo) {
            ViewPropertyAnimatorCompat animate = ViewCompat.animate(removeAnimationInfo.holder.itemView);
            animate.setDuration(getDuration());
            animate.alpha(0.0f);
            startActiveItemAnimation(removeAnimationInfo, removeAnimationInfo.holder, animate);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean canReuseUpdatedViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull List<Object> list) {
        return !list.isEmpty() || super.canReuseUpdatedViewHolder(viewHolder, list);
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.animator.GeneralItemAnimator
    public void onSchedulePendingAnimations() {
        schedulePendingAnimationsByDefaultRule();
    }

    @Override // com.h6ah4i.android.widget.advrecyclerview.animator.GeneralItemAnimator
    public void onSetup() {
        setItemAddAnimationsManager(new DefaultItemAddAnimationManager(this));
        setItemRemoveAnimationManager(new DefaultItemRemoveAnimationManager(this));
        setItemChangeAnimationsManager(new DefaultItemChangeAnimationManager(this));
        setItemMoveAnimationsManager(new DefaultItemMoveAnimationManager(this));
    }
}

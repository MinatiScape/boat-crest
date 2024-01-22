package com.h6ah4i.android.widget.advrecyclerview.animator.impl;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.recyclerview.widget.RecyclerView;
import com.h6ah4i.android.widget.advrecyclerview.animator.BaseItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.animator.impl.ItemAnimationInfo;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public abstract class BaseItemAnimationManager<T extends ItemAnimationInfo> {

    /* renamed from: a  reason: collision with root package name */
    public static TimeInterpolator f11892a;
    public final BaseItemAnimator mItemAnimator;
    public final List<T> mPending = new ArrayList();
    public final List<RecyclerView.ViewHolder> mActive = new ArrayList();
    public final List<List<T>> mDeferredReadySets = new ArrayList();

    /* loaded from: classes11.dex */
    public static class BaseAnimatorListener implements ViewPropertyAnimatorListener {

        /* renamed from: a  reason: collision with root package name */
        public BaseItemAnimationManager f11893a;
        public ItemAnimationInfo b;
        public RecyclerView.ViewHolder c;
        public ViewPropertyAnimatorCompat d;

        public BaseAnimatorListener(BaseItemAnimationManager baseItemAnimationManager, ItemAnimationInfo itemAnimationInfo, RecyclerView.ViewHolder viewHolder, ViewPropertyAnimatorCompat viewPropertyAnimatorCompat) {
            this.f11893a = baseItemAnimationManager;
            this.b = itemAnimationInfo;
            this.c = viewHolder;
            this.d = viewPropertyAnimatorCompat;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.core.view.ViewPropertyAnimatorListener
        public void onAnimationCancel(@NonNull View view) {
            this.f11893a.onAnimationCancel(this.b, this.c);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.core.view.ViewPropertyAnimatorListener
        public void onAnimationEnd(@NonNull View view) {
            BaseItemAnimationManager baseItemAnimationManager = this.f11893a;
            ItemAnimationInfo itemAnimationInfo = this.b;
            RecyclerView.ViewHolder viewHolder = this.c;
            this.d.setListener(null);
            this.f11893a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            baseItemAnimationManager.onAnimationEndedSuccessfully(itemAnimationInfo, viewHolder);
            baseItemAnimationManager.dispatchFinished(itemAnimationInfo, viewHolder);
            itemAnimationInfo.clear(viewHolder);
            baseItemAnimationManager.mActive.remove(viewHolder);
            baseItemAnimationManager.dispatchFinishedWhenDone();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.core.view.ViewPropertyAnimatorListener
        public void onAnimationStart(@NonNull View view) {
            this.f11893a.dispatchStarting(this.b, this.c);
        }
    }

    /* loaded from: classes11.dex */
    public class a implements Runnable {
        public final /* synthetic */ List h;

        public a(List list) {
            this.h = list;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            for (ItemAnimationInfo itemAnimationInfo : this.h) {
                BaseItemAnimationManager.this.b(itemAnimationInfo);
            }
            this.h.clear();
            BaseItemAnimationManager.this.mDeferredReadySets.remove(this.h);
        }
    }

    public BaseItemAnimationManager(@NonNull BaseItemAnimator baseItemAnimator) {
        this.mItemAnimator = baseItemAnimator;
    }

    public final void a(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder != null) {
            this.mActive.add(viewHolder);
            return;
        }
        throw new IllegalStateException("item is null");
    }

    public void b(@NonNull T t) {
        onCreateAnimation(t);
    }

    public void cancelAllStartedAnimations() {
        List<RecyclerView.ViewHolder> list = this.mActive;
        for (int size = list.size() - 1; size >= 0; size--) {
            ViewCompat.animate(list.get(size).itemView).cancel();
        }
    }

    public final boolean debugLogEnabled() {
        return this.mItemAnimator.debugLogEnabled();
    }

    public abstract void dispatchFinished(@NonNull T t, @NonNull RecyclerView.ViewHolder viewHolder);

    public void dispatchFinishedWhenDone() {
        this.mItemAnimator.dispatchFinishedWhenDone();
    }

    public abstract void dispatchStarting(@NonNull T t, @NonNull RecyclerView.ViewHolder viewHolder);

    public void endAllDeferredReadyAnimations() {
        endDeferredReadyAnimations(null);
    }

    public void endAllPendingAnimations() {
        endPendingAnimations(null);
    }

    public void endAnimation(@NonNull RecyclerView.ViewHolder viewHolder) {
        this.mItemAnimator.endAnimation(viewHolder);
    }

    public void endDeferredReadyAnimations(@Nullable RecyclerView.ViewHolder viewHolder) {
        for (int size = this.mDeferredReadySets.size() - 1; size >= 0; size--) {
            List<T> list = this.mDeferredReadySets.get(size);
            for (int size2 = list.size() - 1; size2 >= 0; size2--) {
                if (endNotStartedAnimation(list.get(size2), viewHolder) && viewHolder != null) {
                    list.remove(size2);
                }
            }
            if (viewHolder == null) {
                list.clear();
            }
            if (list.isEmpty()) {
                this.mDeferredReadySets.remove(list);
            }
        }
    }

    public abstract boolean endNotStartedAnimation(@NonNull T t, @NonNull RecyclerView.ViewHolder viewHolder);

    public void endPendingAnimations(@Nullable RecyclerView.ViewHolder viewHolder) {
        List<T> list = this.mPending;
        for (int size = list.size() - 1; size >= 0; size--) {
            if (endNotStartedAnimation(list.get(size), viewHolder) && viewHolder != null) {
                list.remove(size);
            }
        }
        if (viewHolder == null) {
            list.clear();
        }
    }

    public void enqueuePendingAnimationInfo(@NonNull T t) {
        this.mPending.add(t);
    }

    public abstract long getDuration();

    public boolean hasPending() {
        return !this.mPending.isEmpty();
    }

    public boolean isRunning() {
        return (this.mPending.isEmpty() && this.mActive.isEmpty() && this.mDeferredReadySets.isEmpty()) ? false : true;
    }

    public abstract void onAnimationCancel(@NonNull T t, @NonNull RecyclerView.ViewHolder viewHolder);

    public abstract void onAnimationEndedBeforeStarted(@NonNull T t, @NonNull RecyclerView.ViewHolder viewHolder);

    public abstract void onAnimationEndedSuccessfully(@NonNull T t, @NonNull RecyclerView.ViewHolder viewHolder);

    public abstract void onCreateAnimation(@NonNull T t);

    public boolean removeFromActive(@NonNull RecyclerView.ViewHolder viewHolder) {
        return this.mActive.remove(viewHolder);
    }

    public void resetAnimation(@NonNull RecyclerView.ViewHolder viewHolder) {
        if (f11892a == null) {
            f11892a = new ValueAnimator().getInterpolator();
        }
        viewHolder.itemView.animate().setInterpolator(f11892a);
        endAnimation(viewHolder);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void runPendingAnimations(boolean z, long j) {
        ArrayList<ItemAnimationInfo> arrayList = new ArrayList(this.mPending);
        this.mPending.clear();
        if (z) {
            this.mDeferredReadySets.add(arrayList);
            ViewCompat.postOnAnimationDelayed(((ItemAnimationInfo) arrayList.get(0)).getAvailableViewHolder().itemView, new a(arrayList), j);
            return;
        }
        for (ItemAnimationInfo itemAnimationInfo : arrayList) {
            b(itemAnimationInfo);
        }
        arrayList.clear();
    }

    public abstract void setDuration(long j);

    public void startActiveItemAnimation(@NonNull T t, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull ViewPropertyAnimatorCompat viewPropertyAnimatorCompat) {
        viewPropertyAnimatorCompat.setListener(new BaseAnimatorListener(this, t, viewHolder, viewPropertyAnimatorCompat));
        a(viewHolder);
        viewPropertyAnimatorCompat.start();
    }
}

package com.coveiot.leaderboard.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.leaderboard.R;
/* loaded from: classes9.dex */
public class TopRankersListItemBindingImpl extends TopRankersListItemBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i = null;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.ll_list, 1);
        sparseIntArray.put(R.id.tv_rank, 2);
        sparseIntArray.put(R.id.tv_previous_rank, 3);
        sparseIntArray.put(R.id.iv_ranker_profile, 4);
        sparseIntArray.put(R.id.rankerName, 5);
        sparseIntArray.put(R.id.rankerSteps, 6);
        sparseIntArray.put(R.id.view, 7);
    }

    public TopRankersListItemBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 8, i, j));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        synchronized (this) {
            this.h = 0L;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.h != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.h = 1L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i2, Object obj, int i3) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i2, @Nullable Object obj) {
        return true;
    }

    public TopRankersListItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[0], (ImageView) objArr[4], (LinearLayout) objArr[1], (TextView) objArr[5], (TextView) objArr[6], (TextView) objArr[3], (TextView) objArr[2], (View) objArr[7]);
        this.h = -1L;
        this.badgeBaseLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}

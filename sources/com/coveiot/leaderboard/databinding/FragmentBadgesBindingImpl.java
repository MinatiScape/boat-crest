package com.coveiot.leaderboard.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.leaderboard.R;
/* loaded from: classes9.dex */
public class FragmentBadgesBindingImpl extends FragmentBadgesBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts j = null;
    @Nullable
    public static final SparseIntArray k;
    @NonNull
    public final NestedScrollView h;
    public long i;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        k = sparseIntArray;
        sparseIntArray.put(R.id.cl_badges, 1);
        sparseIntArray.put(R.id.cl_badge, 2);
        sparseIntArray.put(R.id.cl_daily_badges, 3);
        sparseIntArray.put(R.id.tv_daily_badges, 4);
        sparseIntArray.put(R.id.rv_daily_badge_list, 5);
        sparseIntArray.put(R.id.cl_special_badges, 6);
        sparseIntArray.put(R.id.tv_special_badges, 7);
        sparseIntArray.put(R.id.rv_special_badge_list, 8);
        sparseIntArray.put(R.id.cl_no_badge, 9);
        sparseIntArray.put(R.id.iv_no_badge, 10);
        sparseIntArray.put(R.id.tv_speed_runner, 11);
        sparseIntArray.put(R.id.tv_speed_runner_desc, 12);
        sparseIntArray.put(R.id.cl_badges_card, 13);
        sparseIntArray.put(R.id.iv_badges, 14);
        sparseIntArray.put(R.id.iv_badges_banner, 15);
    }

    public FragmentBadgesBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 16, j, k));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        synchronized (this) {
            this.i = 0L;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.i != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.i = 1L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    public FragmentBadgesBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[2], (ConstraintLayout) objArr[1], (ConstraintLayout) objArr[13], (ConstraintLayout) objArr[3], (ConstraintLayout) objArr[9], (ConstraintLayout) objArr[6], (ImageView) objArr[14], (ImageView) objArr[15], (ImageView) objArr[10], (RecyclerView) objArr[5], (RecyclerView) objArr[8], (TextView) objArr[4], (TextView) objArr[7], (TextView) objArr[11], (TextView) objArr[12]);
        this.i = -1L;
        NestedScrollView nestedScrollView = (NestedScrollView) objArr[0];
        this.h = nestedScrollView;
        nestedScrollView.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}

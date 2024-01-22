package com.coveiot.android.fitnessbuddies.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.coveiot.android.fitnessbuddies.BR;
import com.coveiot.android.fitnessbuddies.R;
import com.coveiot.android.theme.utils.RoundedBarChart;
/* loaded from: classes4.dex */
public class ActivityBuddyDetailsBindingImpl extends ActivityBuddyDetailsBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts k;
    @Nullable
    public static final SparseIntArray l;
    @NonNull
    public final ConstraintLayout h;
    @NonNull
    public final ConstraintLayout i;
    public long j;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(22);
        k = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"buddies_list_item_new"}, new int[]{3}, new int[]{R.layout.buddies_list_item_new});
        SparseIntArray sparseIntArray = new SparseIntArray();
        l = sparseIntArray;
        sparseIntArray.put(R.id.toolbar, 2);
        sparseIntArray.put(R.id.nestedScrollView, 4);
        sparseIntArray.put(R.id.iv_profile, 5);
        sparseIntArray.put(R.id.tv_name, 6);
        sparseIntArray.put(R.id.cl_rank_badges, 7);
        sparseIntArray.put(R.id.tv_rank, 8);
        sparseIntArray.put(R.id.tv_badge, 9);
        sparseIntArray.put(R.id.tv_weekly_steps, 10);
        sparseIntArray.put(R.id.tv_daily_avg_steps, 11);
        sparseIntArray.put(R.id.graphBg, 12);
        sparseIntArray.put(R.id.stepsGraph, 13);
        sparseIntArray.put(R.id.tvNoDataFound, 14);
        sparseIntArray.put(R.id.cl_insights, 15);
        sparseIntArray.put(R.id.tv_insights, 16);
        sparseIntArray.put(R.id.view_insights, 17);
        sparseIntArray.put(R.id.tv_insights_desc, 18);
        sparseIntArray.put(R.id.tv_remove_buddy, 19);
        sparseIntArray.put(R.id.clLable, 20);
        sparseIntArray.put(R.id.btnLabel, 21);
    }

    public ActivityBuddyDetailsBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 22, k, l));
    }

    public final boolean a(BuddiesListItemNewBinding buddiesListItemNewBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.j |= 1;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        synchronized (this) {
            this.j = 0L;
        }
        ViewDataBinding.executeBindingsOn(this.goalDetails);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.j != 0) {
                return true;
            }
            return this.goalDetails.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.j = 2L;
        }
        this.goalDetails.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return a((BuddiesListItemNewBinding) obj, i2);
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.goalDetails.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    public ActivityBuddyDetailsBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (Button) objArr[21], (ConstraintLayout) objArr[15], (ConstraintLayout) objArr[20], (ConstraintLayout) objArr[7], (BuddiesListItemNewBinding) objArr[3], (ConstraintLayout) objArr[12], (ImageView) objArr[5], (NestedScrollView) objArr[4], (RoundedBarChart) objArr[13], (View) objArr[2], (TextView) objArr[9], (TextView) objArr[11], (TextView) objArr[16], (TextView) objArr[18], (TextView) objArr[6], (TextView) objArr[14], (TextView) objArr[8], (TextView) objArr[19], (TextView) objArr[10], (View) objArr[17]);
        this.j = -1L;
        setContainedBinding(this.goalDetails);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        ConstraintLayout constraintLayout2 = (ConstraintLayout) objArr[1];
        this.i = constraintLayout2;
        constraintLayout2.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}

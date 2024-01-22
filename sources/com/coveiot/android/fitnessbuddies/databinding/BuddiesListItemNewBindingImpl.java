package com.coveiot.android.fitnessbuddies.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.fitnessbuddies.R;
/* loaded from: classes4.dex */
public class BuddiesListItemNewBindingImpl extends BuddiesListItemNewBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i = null;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.cl_profile_details, 1);
        sparseIntArray.put(R.id.iv_profile, 2);
        sparseIntArray.put(R.id.tv_name, 3);
        sparseIntArray.put(R.id.tv_goal_completion, 4);
        sparseIntArray.put(R.id.view1, 5);
        sparseIntArray.put(R.id.cl_goals, 6);
        sparseIntArray.put(R.id.cl_steps_goal, 7);
        sparseIntArray.put(R.id.progress_bar_steps, 8);
        sparseIntArray.put(R.id.iv_steps_goal, 9);
        sparseIntArray.put(R.id.tv_steps_goal_target, 10);
        sparseIntArray.put(R.id.tv_steps_goal_value, 11);
        sparseIntArray.put(R.id.divider1, 12);
        sparseIntArray.put(R.id.cl_sleep_goal, 13);
        sparseIntArray.put(R.id.progress_bar_sleep, 14);
        sparseIntArray.put(R.id.iv_sleep_goal, 15);
        sparseIntArray.put(R.id.tv_sleep_goal_value, 16);
        sparseIntArray.put(R.id.tv_sleep_goal_target, 17);
        sparseIntArray.put(R.id.divider2, 18);
        sparseIntArray.put(R.id.cl_calorie, 19);
        sparseIntArray.put(R.id.iv_calorie, 20);
        sparseIntArray.put(R.id.tv_calorie_value, 21);
        sparseIntArray.put(R.id.tv_calorie, 22);
        sparseIntArray.put(R.id.cl_label, 23);
        sparseIntArray.put(R.id.tv_label, 24);
    }

    public BuddiesListItemNewBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 25, i, j));
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

    public BuddiesListItemNewBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[19], (ConstraintLayout) objArr[6], (ConstraintLayout) objArr[23], (ConstraintLayout) objArr[0], (ConstraintLayout) objArr[1], (ConstraintLayout) objArr[13], (ConstraintLayout) objArr[7], (View) objArr[12], (View) objArr[18], (ImageView) objArr[20], (ImageView) objArr[2], (ImageView) objArr[15], (ImageView) objArr[9], (ProgressBar) objArr[14], (ProgressBar) objArr[8], (TextView) objArr[22], (TextView) objArr[21], (TextView) objArr[4], (TextView) objArr[24], (TextView) objArr[3], (TextView) objArr[17], (TextView) objArr[16], (TextView) objArr[10], (TextView) objArr[11], (View) objArr[5]);
        this.h = -1L;
        this.clMain.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}

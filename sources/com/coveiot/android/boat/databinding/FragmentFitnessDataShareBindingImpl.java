package com.coveiot.android.boat.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.theme.databinding.ArcCircularProgressBarBinding;
/* loaded from: classes3.dex */
public class FragmentFitnessDataShareBindingImpl extends FragmentFitnessDataShareBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(27);
        i = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"arc_circular_progress_bar"}, new int[]{2}, new int[]{R.layout.arc_circular_progress_bar});
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.iv_app_logo, 3);
        sparseIntArray.put(R.id.iv_powered_cove, 4);
        sparseIntArray.put(R.id.user_pic, 5);
        sparseIntArray.put(R.id.user_name, 6);
        sparseIntArray.put(R.id.week, 7);
        sparseIntArray.put(R.id.four_guideline, 8);
        sparseIntArray.put(R.id.gl_middle, 9);
        sparseIntArray.put(R.id.calories_layout, 10);
        sparseIntArray.put(R.id.iv_minimum, 11);
        sparseIntArray.put(R.id.min_icon, 12);
        sparseIntArray.put(R.id.calories_value, 13);
        sparseIntArray.put(R.id.calories_unit, 14);
        sparseIntArray.put(R.id.distance_layout, 15);
        sparseIntArray.put(R.id.iv_maximum, 16);
        sparseIntArray.put(R.id.dist_icon, 17);
        sparseIntArray.put(R.id.distance_value, 18);
        sparseIntArray.put(R.id.distance_unit, 19);
        sparseIntArray.put(R.id.tv_progress_value, 20);
        sparseIntArray.put(R.id.tv_goal_acheived, 21);
        sparseIntArray.put(R.id.five_guideline, 22);
        sparseIntArray.put(R.id.cl_goals, 23);
        sparseIntArray.put(R.id.rv_goals, 24);
        sparseIntArray.put(R.id.view, 25);
        sparseIntArray.put(R.id.disclaimer_info, 26);
    }

    public FragmentFitnessDataShareBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 27, i, j));
    }

    public final boolean a(ArcCircularProgressBarBinding arcCircularProgressBarBinding, int i2) {
        if (i2 == 0) {
            synchronized (this) {
                this.h |= 1;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        synchronized (this) {
            this.h = 0L;
        }
        ViewDataBinding.executeBindingsOn(this.arcProgressBar);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.h != 0) {
                return true;
            }
            return this.arcProgressBar.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.h = 2L;
        }
        this.arcProgressBar.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i2, Object obj, int i3) {
        if (i2 != 0) {
            return false;
        }
        return a((ArcCircularProgressBarBinding) obj, i3);
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.arcProgressBar.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i2, @Nullable Object obj) {
        return true;
    }

    public FragmentFitnessDataShareBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (ArcCircularProgressBarBinding) objArr[2], (ConstraintLayout) objArr[10], (TextView) objArr[14], (TextView) objArr[13], (ConstraintLayout) objArr[23], (ConstraintLayout) objArr[1], (TextView) objArr[26], (ImageView) objArr[17], (ConstraintLayout) objArr[15], (TextView) objArr[19], (TextView) objArr[18], (Guideline) objArr[22], (Guideline) objArr[8], (Guideline) objArr[9], (ImageView) objArr[3], (ImageView) objArr[16], (ImageView) objArr[11], (ImageView) objArr[4], (ImageView) objArr[12], (ConstraintLayout) objArr[0], (RecyclerView) objArr[24], (TextView) objArr[21], (TextView) objArr[20], (TextView) objArr[6], (ImageView) objArr[5], (View) objArr[25], (TextView) objArr[7]);
        this.h = -1L;
        setContainedBinding(this.arcProgressBar);
        this.clProgress.setTag(null);
        this.rootLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}

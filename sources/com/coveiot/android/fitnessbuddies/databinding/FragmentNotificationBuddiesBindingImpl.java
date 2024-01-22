package com.coveiot.android.fitnessbuddies.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.fitnessbuddies.R;
/* loaded from: classes4.dex */
public class FragmentNotificationBuddiesBindingImpl extends FragmentNotificationBuddiesBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts j = null;
    @Nullable
    public static final SparseIntArray k;
    @NonNull
    public final ConstraintLayout h;
    public long i;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        k = sparseIntArray;
        sparseIntArray.put(R.id.cl_buddies_main, 1);
        sparseIntArray.put(R.id.clMyDetails, 2);
        sparseIntArray.put(R.id.cl_profile_details, 3);
        sparseIntArray.put(R.id.iv_profile, 4);
        sparseIntArray.put(R.id.tv_name, 5);
        sparseIntArray.put(R.id.tv_rank, 6);
        sparseIntArray.put(R.id.tv_badge, 7);
        sparseIntArray.put(R.id.tv_goal_completion, 8);
        sparseIntArray.put(R.id.progress, 9);
        sparseIntArray.put(R.id.view1, 10);
        sparseIntArray.put(R.id.clGoals, 11);
        sparseIntArray.put(R.id.cl_goals, 12);
        sparseIntArray.put(R.id.cl_steps_goal, 13);
        sparseIntArray.put(R.id.progress_bar_steps, 14);
        sparseIntArray.put(R.id.iv_steps_goal, 15);
        sparseIntArray.put(R.id.tv_steps_goal_value, 16);
        sparseIntArray.put(R.id.tv_steps_goal_target, 17);
        sparseIntArray.put(R.id.divider1, 18);
        sparseIntArray.put(R.id.cl_sleep_goal, 19);
        sparseIntArray.put(R.id.progress_bar_sleep, 20);
        sparseIntArray.put(R.id.iv_sleep_goal, 21);
        sparseIntArray.put(R.id.tv_sleep_goal_value, 22);
        sparseIntArray.put(R.id.tv_sleep_goal_target, 23);
        sparseIntArray.put(R.id.divider2, 24);
        sparseIntArray.put(R.id.cl_calorie, 25);
        sparseIntArray.put(R.id.iv_calorie, 26);
        sparseIntArray.put(R.id.tv_calorie_value, 27);
        sparseIntArray.put(R.id.tv_calorie, 28);
        sparseIntArray.put(R.id.view2, 29);
        sparseIntArray.put(R.id.tv_my_buddies, 30);
        sparseIntArray.put(R.id.progressList, 31);
        sparseIntArray.put(R.id.empty_layout, 32);
        sparseIntArray.put(R.id.empty_image, 33);
        sparseIntArray.put(R.id.add_buddies_text, 34);
        sparseIntArray.put(R.id.llAddBuddies, 35);
        sparseIntArray.put(R.id.btnNext, 36);
        sparseIntArray.put(R.id.tv_add_buddies, 37);
    }

    public FragmentNotificationBuddiesBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 38, j, k));
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

    public FragmentNotificationBuddiesBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (TextView) objArr[34], (Button) objArr[36], (ConstraintLayout) objArr[1], (ConstraintLayout) objArr[25], (ConstraintLayout) objArr[11], (ConstraintLayout) objArr[12], (ConstraintLayout) objArr[2], (ConstraintLayout) objArr[3], (ConstraintLayout) objArr[19], (ConstraintLayout) objArr[13], (View) objArr[18], (View) objArr[24], (ImageView) objArr[33], (ConstraintLayout) objArr[32], (ImageView) objArr[26], (ImageView) objArr[4], (ImageView) objArr[21], (ImageView) objArr[15], (LinearLayout) objArr[35], (ProgressBar) objArr[9], (ProgressBar) objArr[20], (ProgressBar) objArr[14], (RecyclerView) objArr[31], (TextView) objArr[37], (TextView) objArr[7], (TextView) objArr[28], (TextView) objArr[27], (TextView) objArr[8], (TextView) objArr[30], (TextView) objArr[5], (TextView) objArr[6], (TextView) objArr[23], (TextView) objArr[22], (TextView) objArr[17], (TextView) objArr[16], (View) objArr[10], (View) objArr[29]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}

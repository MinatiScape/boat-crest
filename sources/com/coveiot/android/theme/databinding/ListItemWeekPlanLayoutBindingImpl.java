package com.coveiot.android.theme.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public class ListItemWeekPlanLayoutBindingImpl extends ListItemWeekPlanLayoutBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i = null;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.clTop, 1);
        sparseIntArray.put(R.id.cvWeekPlanImage, 2);
        sparseIntArray.put(R.id.ivBlog, 3);
        sparseIntArray.put(R.id.tvWeekPlanStatus, 4);
        sparseIntArray.put(R.id.tvWeekPlanName, 5);
        sparseIntArray.put(R.id.textView, 6);
        sparseIntArray.put(R.id.clBottom, 7);
        sparseIntArray.put(R.id.clUpcoming, 8);
        sparseIntArray.put(R.id.tvPlanStartsTomorrow, 9);
        sparseIntArray.put(R.id.clOngoing, 10);
        sparseIntArray.put(R.id.tvTodayGoal, 11);
        sparseIntArray.put(R.id.ivTodayGoal, 12);
        sparseIntArray.put(R.id.tvTodayGoalName, 13);
        sparseIntArray.put(R.id.tvTodayGoalValue, 14);
        sparseIntArray.put(R.id.todayGoalProgress, 15);
        sparseIntArray.put(R.id.tvTodayGoalTotalValue, 16);
        sparseIntArray.put(R.id.clCompleted, 17);
        sparseIntArray.put(R.id.clBottomTop, 18);
        sparseIntArray.put(R.id.clCompletionInfo, 19);
        sparseIntArray.put(R.id.tvCompletionStatus, 20);
        sparseIntArray.put(R.id.clMainData, 21);
        sparseIntArray.put(R.id.tvCalorie, 22);
        sparseIntArray.put(R.id.view1, 23);
        sparseIntArray.put(R.id.tvSteps, 24);
        sparseIntArray.put(R.id.view2, 25);
        sparseIntArray.put(R.id.tvDistance, 26);
        sparseIntArray.put(R.id.dashView, 27);
        sparseIntArray.put(R.id.tvViewDetails, 28);
    }

    public ListItemWeekPlanLayoutBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 29, i, j));
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

    public ListItemWeekPlanLayoutBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[7], (ConstraintLayout) objArr[18], (ConstraintLayout) objArr[17], (ConstraintLayout) objArr[19], (ConstraintLayout) objArr[0], (ConstraintLayout) objArr[21], (ConstraintLayout) objArr[10], (ConstraintLayout) objArr[1], (ConstraintLayout) objArr[8], (CardView) objArr[2], (View) objArr[27], (ImageView) objArr[3], (ImageView) objArr[12], (TextView) objArr[6], (ProgressBar) objArr[15], (TextView) objArr[22], (TextView) objArr[20], (TextView) objArr[26], (TextView) objArr[9], (TextView) objArr[24], (TextView) objArr[11], (TextView) objArr[13], (TextView) objArr[16], (TextView) objArr[14], (TextView) objArr[28], (TextView) objArr[5], (TextView) objArr[4], (View) objArr[23], (View) objArr[25]);
        this.h = -1L;
        this.clMain.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}

package com.coveiot.android.activitymodes.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.activitymodes.BR;
import com.coveiot.android.theme.R;
import com.coveiot.android.theme.databinding.ListItemWeekPlanLayoutBinding;
/* loaded from: classes2.dex */
public class FragmentFitnessPlanHistoryBindingImpl extends FragmentFitnessPlanHistoryBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts j;
    @Nullable
    public static final SparseIntArray k;
    @NonNull
    public final ConstraintLayout h;
    public long i;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(9);
        j = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"list_item_week_plan_layout"}, new int[]{2}, new int[]{R.layout.list_item_week_plan_layout});
        SparseIntArray sparseIntArray = new SparseIntArray();
        k = sparseIntArray;
        sparseIntArray.put(com.coveiot.android.activitymodes.R.id.rgCategory, 3);
        sparseIntArray.put(com.coveiot.android.activitymodes.R.id.rbAll, 4);
        sparseIntArray.put(com.coveiot.android.activitymodes.R.id.rbOngoing, 5);
        sparseIntArray.put(com.coveiot.android.activitymodes.R.id.rbCompleted, 6);
        sparseIntArray.put(com.coveiot.android.activitymodes.R.id.rvPlanHistory, 7);
        sparseIntArray.put(com.coveiot.android.activitymodes.R.id.tvNoDataFound, 8);
    }

    public FragmentFitnessPlanHistoryBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 9, j, k));
    }

    public final boolean a(ListItemWeekPlanLayoutBinding listItemWeekPlanLayoutBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 1;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        synchronized (this) {
            this.i = 0L;
        }
        ViewDataBinding.executeBindingsOn(this.fitnessJourneyOngoing);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.i != 0) {
                return true;
            }
            return this.fitnessJourneyOngoing.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.i = 2L;
        }
        this.fitnessJourneyOngoing.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return a((ListItemWeekPlanLayoutBinding) obj, i2);
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.fitnessJourneyOngoing.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    public FragmentFitnessPlanHistoryBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (ConstraintLayout) objArr[1], (ListItemWeekPlanLayoutBinding) objArr[2], (RadioButton) objArr[4], (RadioButton) objArr[6], (RadioButton) objArr[5], (RadioGroup) objArr[3], (RecyclerView) objArr[7], (TextView) objArr[8]);
        this.i = -1L;
        this.clOngoingPlan.setTag(null);
        setContainedBinding(this.fitnessJourneyOngoing);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}

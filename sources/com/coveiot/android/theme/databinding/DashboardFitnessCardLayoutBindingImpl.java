package com.coveiot.android.theme.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.coveiot.android.theme.BR;
import com.coveiot.android.theme.CircularArcProgressBar;
import com.coveiot.android.theme.R;
import com.coveiot.covepreferences.data.StepsDataModel;
/* loaded from: classes7.dex */
public class DashboardFitnessCardLayoutBindingImpl extends DashboardFitnessCardLayoutBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i = null;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.progressBar1, 3);
        sparseIntArray.put(R.id.progressBar, 4);
    }

    public DashboardFitnessCardLayoutBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 5, i, j));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j2;
        int i2;
        boolean z;
        String str;
        synchronized (this) {
            j2 = this.h;
            this.h = 0L;
        }
        StepsDataModel stepsDataModel = this.mStepsDataModel;
        Integer num = this.mStepsGoal;
        int i3 = ((j2 & 5) > 0L ? 1 : ((j2 & 5) == 0L ? 0 : -1));
        if (i3 != 0) {
            i2 = stepsDataModel != null ? stepsDataModel.getSteps() : 0;
            z = i2 >= 0;
            if (i3 != 0) {
                j2 = z ? j2 | 16 : j2 | 8;
            }
        } else {
            i2 = 0;
            z = false;
        }
        int i4 = ((6 & j2) > 0L ? 1 : ((6 & j2) == 0L ? 0 : -1));
        String string = i4 != 0 ? this.tvGoalDetail.getResources().getString(R.string.of_target_steps, num) : null;
        String num2 = (j2 & 16) != 0 ? Integer.toString(i2) : null;
        int i5 = ((j2 & 5) > 0L ? 1 : ((j2 & 5) == 0L ? 0 : -1));
        if (i5 != 0) {
            if (!z) {
                num2 = Integer.toString(0);
            }
            str = num2;
        } else {
            str = null;
        }
        if (i4 != 0) {
            TextViewBindingAdapter.setText(this.tvGoalDetail, string);
        }
        if (i5 != 0) {
            TextViewBindingAdapter.setText(this.tvStepsCount, str);
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
            this.h = 4L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i2, Object obj, int i3) {
        return false;
    }

    @Override // com.coveiot.android.theme.databinding.DashboardFitnessCardLayoutBinding
    public void setStepsDataModel(@Nullable StepsDataModel stepsDataModel) {
        this.mStepsDataModel = stepsDataModel;
        synchronized (this) {
            this.h |= 1;
        }
        notifyPropertyChanged(BR.stepsDataModel);
        super.requestRebind();
    }

    @Override // com.coveiot.android.theme.databinding.DashboardFitnessCardLayoutBinding
    public void setStepsGoal(@Nullable Integer num) {
        this.mStepsGoal = num;
        synchronized (this) {
            this.h |= 2;
        }
        notifyPropertyChanged(BR.stepsGoal);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i2, @Nullable Object obj) {
        if (BR.stepsDataModel == i2) {
            setStepsDataModel((StepsDataModel) obj);
        } else if (BR.stepsGoal != i2) {
            return false;
        } else {
            setStepsGoal((Integer) obj);
        }
        return true;
    }

    public DashboardFitnessCardLayoutBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (FrameLayout) objArr[0], (CircularArcProgressBar) objArr[4], (CircularArcProgressBar) objArr[3], (TextView) objArr[2], (TextView) objArr[1]);
        this.h = -1L;
        this.clDashboardFitness.setTag(null);
        this.tvGoalDetail.setTag(null);
        this.tvStepsCount.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}

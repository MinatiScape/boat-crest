package com.coveiot.android.activitymodes.databinding;

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
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.utils.UntouchableRecyclerView;
/* loaded from: classes2.dex */
public class ActivityHistoryBeanLayoutBindingImpl extends ActivityHistoryBeanLayoutBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i = null;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.activity_icon, 1);
        sparseIntArray.put(R.id.vertical_guideline, 2);
        sparseIntArray.put(R.id.vertical_guideline1, 3);
        sparseIntArray.put(R.id.activity_name, 4);
        sparseIntArray.put(R.id.activity_day, 5);
        sparseIntArray.put(R.id.date_text, 6);
        sparseIntArray.put(R.id.time_text, 7);
        sparseIntArray.put(R.id.activityBeanRecyclerView, 8);
        sparseIntArray.put(R.id.cl_autoActivity, 9);
        sparseIntArray.put(R.id.tv_auto_activity, 10);
        sparseIntArray.put(R.id.activity_legend_info, 11);
    }

    public ActivityHistoryBeanLayoutBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 12, i, j));
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

    public ActivityHistoryBeanLayoutBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (UntouchableRecyclerView) objArr[8], (TextView) objArr[5], (ImageView) objArr[1], (TextView) objArr[11], (TextView) objArr[4], (ConstraintLayout) objArr[9], (TextView) objArr[6], (TextView) objArr[7], (ConstraintLayout) objArr[0], (TextView) objArr[10], (Guideline) objArr[2], (Guideline) objArr[3]);
        this.h = -1L;
        this.topCl.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}

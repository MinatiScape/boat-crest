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
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public class ActivitySensAiCompareBindingImpl extends ActivitySensAiCompareBinding {
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
        sparseIntArray.put(R.id.toolbar, 1);
        sparseIntArray.put(R.id.guideline1, 2);
        sparseIntArray.put(R.id.guideline2, 3);
        sparseIntArray.put(R.id.cl_compare, 4);
        sparseIntArray.put(R.id.cl_compare_1, 5);
        sparseIntArray.put(R.id.iv_compare_1, 6);
        sparseIntArray.put(R.id.tv_compare_title_1, 7);
        sparseIntArray.put(R.id.tv_compare_date_1, 8);
        sparseIntArray.put(R.id.viewShots, 9);
        sparseIntArray.put(R.id.cl_compare_2, 10);
        sparseIntArray.put(R.id.iv_compare_2, 11);
        sparseIntArray.put(R.id.tv_compare_title_2, 12);
        sparseIntArray.put(R.id.tv_compare_date_2, 13);
        sparseIntArray.put(R.id.rv_compare_list, 14);
    }

    public ActivitySensAiCompareBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 15, j, k));
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

    public ActivitySensAiCompareBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[4], (ConstraintLayout) objArr[5], (ConstraintLayout) objArr[10], (Guideline) objArr[2], (Guideline) objArr[3], (ImageView) objArr[6], (ImageView) objArr[11], (RecyclerView) objArr[14], (View) objArr[1], (TextView) objArr[8], (TextView) objArr[13], (TextView) objArr[7], (TextView) objArr[12], (View) objArr[9]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}

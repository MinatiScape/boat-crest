package com.coveiot.android.boat.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public class ActivitySensAiInfoBindingImpl extends ActivitySensAiInfoBinding {
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
        sparseIntArray.put(R.id.cv_info_1, 2);
        sparseIntArray.put(R.id.clInfo1, 3);
        sparseIntArray.put(R.id.bullet_1, 4);
        sparseIntArray.put(R.id.tvInfo1, 5);
        sparseIntArray.put(R.id.cv_info_2, 6);
        sparseIntArray.put(R.id.clInfo2, 7);
        sparseIntArray.put(R.id.bullet_2, 8);
        sparseIntArray.put(R.id.tvInfo2, 9);
        sparseIntArray.put(R.id.cv_info_3, 10);
        sparseIntArray.put(R.id.clInfo3, 11);
        sparseIntArray.put(R.id.bullet_3, 12);
        sparseIntArray.put(R.id.tvInfo3, 13);
    }

    public ActivitySensAiInfoBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 14, j, k));
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

    public ActivitySensAiInfoBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (TextView) objArr[4], (TextView) objArr[8], (TextView) objArr[12], (ConstraintLayout) objArr[3], (ConstraintLayout) objArr[7], (ConstraintLayout) objArr[11], (LinearLayout) objArr[2], (LinearLayout) objArr[6], (LinearLayout) objArr[10], (View) objArr[1], (TextView) objArr[5], (TextView) objArr[9], (TextView) objArr[13]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}

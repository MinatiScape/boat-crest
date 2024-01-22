package com.coveiot.android.theme.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
import com.github.anastr.speedviewlib.SpeedView;
/* loaded from: classes7.dex */
public class ItemListFitnessVitalsLayoutFullBindingImpl extends ItemListFitnessVitalsLayoutFullBinding {
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
        sparseIntArray.put(R.id.tvVitalName, 1);
        sparseIntArray.put(R.id.clVitalImage, 2);
        sparseIntArray.put(R.id.ivVitalImage, 3);
        sparseIntArray.put(R.id.speedView_stress, 4);
        sparseIntArray.put(R.id.tvVitalValue, 5);
        sparseIntArray.put(R.id.clLevel, 6);
        sparseIntArray.put(R.id.view, 7);
        sparseIntArray.put(R.id.tvVitalLevel, 8);
        sparseIntArray.put(R.id.clUnit, 9);
        sparseIntArray.put(R.id.tvVitalValueUnit, 10);
        sparseIntArray.put(R.id.view1, 11);
        sparseIntArray.put(R.id.tvVitalDownUpPercentage, 12);
        sparseIntArray.put(R.id.tvVitalLastUpdatedTime, 13);
        sparseIntArray.put(R.id.tvAttention, 14);
    }

    public ItemListFitnessVitalsLayoutFullBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
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

    public ItemListFitnessVitalsLayoutFullBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[6], (ConstraintLayout) objArr[9], (ConstraintLayout) objArr[2], (ImageView) objArr[3], (SpeedView) objArr[4], (TextView) objArr[14], (TextView) objArr[12], (TextView) objArr[13], (TextView) objArr[8], (TextView) objArr[1], (TextView) objArr[5], (TextView) objArr[10], (View) objArr[7], (View) objArr[11]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}

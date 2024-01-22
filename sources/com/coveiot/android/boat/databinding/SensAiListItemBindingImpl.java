package com.coveiot.android.boat.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public class SensAiListItemBindingImpl extends SensAiListItemBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i = null;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.first_guideline, 1);
        sparseIntArray.put(R.id.second_guideline, 2);
        sparseIntArray.put(R.id.clDataheading, 3);
        sparseIntArray.put(R.id.ivImage, 4);
        sparseIntArray.put(R.id.tvTitle, 5);
        sparseIntArray.put(R.id.tvTime, 6);
        sparseIntArray.put(R.id.ivAward, 7);
        sparseIntArray.put(R.id.view, 8);
        sparseIntArray.put(R.id.clDetails, 9);
        sparseIntArray.put(R.id.clDuration, 10);
        sparseIntArray.put(R.id.ivDuration, 11);
        sparseIntArray.put(R.id.tvDuration, 12);
        sparseIntArray.put(R.id.tvDurationTxt, 13);
        sparseIntArray.put(R.id.clTotalShots, 14);
        sparseIntArray.put(R.id.viewShots, 15);
        sparseIntArray.put(R.id.ivShots, 16);
        sparseIntArray.put(R.id.tvTotalShots, 17);
        sparseIntArray.put(R.id.tvTotalShotsTxt, 18);
        sparseIntArray.put(R.id.clAvgHandSpeed, 19);
        sparseIntArray.put(R.id.viewSpeed, 20);
        sparseIntArray.put(R.id.ivSpeed, 21);
        sparseIntArray.put(R.id.tvAvgHandSpeed, 22);
        sparseIntArray.put(R.id.tvAvgHandSpeedTxt, 23);
        sparseIntArray.put(R.id.viewAddToCompare, 24);
        sparseIntArray.put(R.id.cbAddToCompare, 25);
    }

    public SensAiListItemBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 26, i, j));
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

    public SensAiListItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (CheckBox) objArr[25], (ConstraintLayout) objArr[19], (ConstraintLayout) objArr[0], (ConstraintLayout) objArr[3], (LinearLayout) objArr[9], (ConstraintLayout) objArr[10], (ConstraintLayout) objArr[14], (Guideline) objArr[1], (ImageView) objArr[7], (ImageView) objArr[11], (ImageView) objArr[4], (ImageView) objArr[16], (ImageView) objArr[21], (Guideline) objArr[2], (TextView) objArr[22], (TextView) objArr[23], (TextView) objArr[12], (TextView) objArr[13], (TextView) objArr[6], (TextView) objArr[5], (TextView) objArr[17], (TextView) objArr[18], (View) objArr[8], (View) objArr[24], (View) objArr[15], (View) objArr[20]);
        this.h = -1L;
        this.clData1.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}

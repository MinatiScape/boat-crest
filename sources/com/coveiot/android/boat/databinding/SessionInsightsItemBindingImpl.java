package com.coveiot.android.boat.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public class SessionInsightsItemBindingImpl extends SessionInsightsItemBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i = null;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.clData1, 1);
        sparseIntArray.put(R.id.first_guideline, 2);
        sparseIntArray.put(R.id.second_guideline, 3);
        sparseIntArray.put(R.id.tvTitle, 4);
        sparseIntArray.put(R.id.tvTime, 5);
        sparseIntArray.put(R.id.ivAward, 6);
        sparseIntArray.put(R.id.view, 7);
        sparseIntArray.put(R.id.clDuration, 8);
        sparseIntArray.put(R.id.tvDuration, 9);
        sparseIntArray.put(R.id.tvDurationTxt, 10);
        sparseIntArray.put(R.id.clTotalShots, 11);
        sparseIntArray.put(R.id.tvTotalShots, 12);
        sparseIntArray.put(R.id.tvTotalShotsTxt, 13);
        sparseIntArray.put(R.id.clAvgHandSpeed, 14);
        sparseIntArray.put(R.id.tvAvgHandSpeed, 15);
        sparseIntArray.put(R.id.tvAvgHandSpeedTxt, 16);
        sparseIntArray.put(R.id.cbAddToCompare, 17);
    }

    public SessionInsightsItemBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 18, i, j));
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

    public SessionInsightsItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (CheckBox) objArr[17], (ConstraintLayout) objArr[14], (ConstraintLayout) objArr[1], (ConstraintLayout) objArr[8], (ConstraintLayout) objArr[11], (CardView) objArr[0], (Guideline) objArr[2], (ImageView) objArr[6], (Guideline) objArr[3], (TextView) objArr[15], (TextView) objArr[16], (TextView) objArr[9], (TextView) objArr[10], (TextView) objArr[5], (TextView) objArr[4], (TextView) objArr[12], (TextView) objArr[13], (View) objArr[7]);
        this.h = -1L;
        this.cvItem.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}

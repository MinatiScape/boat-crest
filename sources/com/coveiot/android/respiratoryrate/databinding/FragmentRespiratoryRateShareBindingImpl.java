package com.coveiot.android.respiratoryrate.databinding;

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
import com.coveiot.android.respiratoryrate.R;
/* loaded from: classes6.dex */
public class FragmentRespiratoryRateShareBindingImpl extends FragmentRespiratoryRateShareBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i = null;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.iv_app_logo, 1);
        sparseIntArray.put(R.id.iv_powered_cove, 2);
        sparseIntArray.put(R.id.user_pic, 3);
        sparseIntArray.put(R.id.user_name, 4);
        sparseIntArray.put(R.id.week, 5);
        sparseIntArray.put(R.id.four_guideline, 6);
        sparseIntArray.put(R.id.gl_middle, 7);
        sparseIntArray.put(R.id.min_layout, 8);
        sparseIntArray.put(R.id.iv_minimum, 9);
        sparseIntArray.put(R.id.img_respiratory_rate_min, 10);
        sparseIntArray.put(R.id.tv_respiratory_rate_min, 11);
        sparseIntArray.put(R.id.tv_min, 12);
        sparseIntArray.put(R.id.distance_layout, 13);
        sparseIntArray.put(R.id.iv_maximum, 14);
        sparseIntArray.put(R.id.img_respiratory_rate_max, 15);
        sparseIntArray.put(R.id.tv_respiratory_rate_max, 16);
        sparseIntArray.put(R.id.tv_max, 17);
        sparseIntArray.put(R.id.ivCenterVitalBg, 18);
        sparseIntArray.put(R.id.imgv_respiratory_rate, 19);
        sparseIntArray.put(R.id.nightly_value, 20);
        sparseIntArray.put(R.id.nightly_title, 21);
        sparseIntArray.put(R.id.five_guideline, 22);
        sparseIntArray.put(R.id.disclaimer_info, 23);
    }

    public FragmentRespiratoryRateShareBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 24, i, j));
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

    public FragmentRespiratoryRateShareBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (TextView) objArr[23], (ConstraintLayout) objArr[13], (Guideline) objArr[22], (Guideline) objArr[6], (Guideline) objArr[7], (ImageView) objArr[15], (ImageView) objArr[10], (ImageView) objArr[19], (ImageView) objArr[1], (ImageView) objArr[18], (ImageView) objArr[14], (ImageView) objArr[9], (ImageView) objArr[2], (ConstraintLayout) objArr[8], (TextView) objArr[21], (TextView) objArr[20], (ConstraintLayout) objArr[0], (TextView) objArr[17], (TextView) objArr[12], (TextView) objArr[16], (TextView) objArr[11], (TextView) objArr[4], (ImageView) objArr[3], (TextView) objArr[5]);
        this.h = -1L;
        this.rootLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}

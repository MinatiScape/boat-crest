package com.coveiot.android.boat.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public class FragmentSensAiFtu3BindingImpl extends FragmentSensAiFtu3Binding {
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
        sparseIntArray.put(R.id.iv_profile, 1);
        sparseIntArray.put(R.id.ivInfo, 2);
        sparseIntArray.put(R.id.tv_profile, 3);
        sparseIntArray.put(R.id.cl_profile_details, 4);
        sparseIntArray.put(R.id.cl_profile_details_, 5);
        sparseIntArray.put(R.id.cl_height, 6);
        sparseIntArray.put(R.id.tv_height, 7);
        sparseIntArray.put(R.id.tv_height_value, 8);
        sparseIntArray.put(R.id.cl_weight, 9);
        sparseIntArray.put(R.id.tv_weight, 10);
        sparseIntArray.put(R.id.tv_weight_value, 11);
        sparseIntArray.put(R.id.cl_gender, 12);
        sparseIntArray.put(R.id.tv_gender, 13);
        sparseIntArray.put(R.id.tv_gender_value, 14);
        sparseIntArray.put(R.id.cl_age, 15);
        sparseIntArray.put(R.id.tv_age, 16);
        sparseIntArray.put(R.id.tv_age_value, 17);
        sparseIntArray.put(R.id.cl_profile_edit, 18);
        sparseIntArray.put(R.id.cl_wake_gesture, 19);
        sparseIntArray.put(R.id.ivInfoIcon, 20);
        sparseIntArray.put(R.id.tv_enable_wake_gesture_info, 21);
        sparseIntArray.put(R.id.btnNext, 22);
    }

    public FragmentSensAiFtu3BindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 23, j, k));
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

    public FragmentSensAiFtu3BindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (Button) objArr[22], (ConstraintLayout) objArr[15], (ConstraintLayout) objArr[12], (ConstraintLayout) objArr[6], (ConstraintLayout) objArr[4], (ConstraintLayout) objArr[5], (ConstraintLayout) objArr[18], (ConstraintLayout) objArr[19], (ConstraintLayout) objArr[9], (ImageView) objArr[2], (ImageView) objArr[20], (ImageView) objArr[1], (TextView) objArr[16], (TextView) objArr[17], (TextView) objArr[21], (TextView) objArr[13], (TextView) objArr[14], (TextView) objArr[7], (TextView) objArr[8], (TextView) objArr[3], (TextView) objArr[10], (TextView) objArr[11]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}

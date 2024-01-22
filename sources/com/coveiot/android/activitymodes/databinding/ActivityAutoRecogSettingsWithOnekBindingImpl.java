package com.coveiot.android.activitymodes.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.coveiot.android.activitymodes.BR;
import com.coveiot.android.theme.R;
import com.coveiot.android.theme.databinding.RepeatLayoutNewBinding;
/* loaded from: classes2.dex */
public class ActivityAutoRecogSettingsWithOnekBindingImpl extends ActivityAutoRecogSettingsWithOnekBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts k;
    @Nullable
    public static final SparseIntArray l;
    @NonNull
    public final ConstraintLayout h;
    @NonNull
    public final LinearLayout i;
    public long j;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(13);
        k = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"repeat_layout_new"}, new int[]{3}, new int[]{R.layout.repeat_layout_new});
        SparseIntArray sparseIntArray = new SparseIntArray();
        l = sparseIntArray;
        sparseIntArray.put(com.coveiot.android.activitymodes.R.id.toolbar, 2);
        sparseIntArray.put(com.coveiot.android.activitymodes.R.id.cv_smart_mode, 4);
        sparseIntArray.put(com.coveiot.android.activitymodes.R.id.tv_smart_mode, 5);
        sparseIntArray.put(com.coveiot.android.activitymodes.R.id.sw_smart_mode, 6);
        sparseIntArray.put(com.coveiot.android.activitymodes.R.id.tv_smart_mode_desc, 7);
        sparseIntArray.put(com.coveiot.android.activitymodes.R.id.cb_7am_12pm, 8);
        sparseIntArray.put(com.coveiot.android.activitymodes.R.id.cb_12pm_4pm, 9);
        sparseIntArray.put(com.coveiot.android.activitymodes.R.id.cb_4pm_9pm, 10);
        sparseIntArray.put(com.coveiot.android.activitymodes.R.id.cb_9pm_7am, 11);
        sparseIntArray.put(com.coveiot.android.activitymodes.R.id.btnSave, 12);
    }

    public ActivityAutoRecogSettingsWithOnekBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 13, k, l));
    }

    public final boolean a(RepeatLayoutNewBinding repeatLayoutNewBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.j |= 1;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        synchronized (this) {
            this.j = 0L;
        }
        ViewDataBinding.executeBindingsOn(this.repeatLayout);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.j != 0) {
                return true;
            }
            return this.repeatLayout.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.j = 2L;
        }
        this.repeatLayout.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return a((RepeatLayoutNewBinding) obj, i2);
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.repeatLayout.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    public ActivityAutoRecogSettingsWithOnekBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (Button) objArr[12], (CheckBox) objArr[9], (CheckBox) objArr[10], (CheckBox) objArr[8], (CheckBox) objArr[11], (LinearLayout) objArr[4], (RepeatLayoutNewBinding) objArr[3], (SwitchCompat) objArr[6], (View) objArr[2], (TextView) objArr[5], (TextView) objArr[7]);
        this.j = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        LinearLayout linearLayout = (LinearLayout) objArr[1];
        this.i = linearLayout;
        linearLayout.setTag(null);
        setContainedBinding(this.repeatLayout);
        setRootTag(view);
        invalidateAll();
    }
}

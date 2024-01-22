package com.coveiot.android.theme.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.coveiot.android.theme.BR;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public class LayoutDashboardVitalGridItemBindingImpl extends LayoutDashboardVitalGridItemBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts j;
    @Nullable
    public static final SparseIntArray k;
    @NonNull
    public final ConstraintLayout h;
    public long i;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(2);
        j = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"item_list_fitness_vitals_layout"}, new int[]{1}, new int[]{R.layout.item_list_fitness_vitals_layout});
        k = null;
    }

    public LayoutDashboardVitalGridItemBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 2, j, k));
    }

    public final boolean a(ItemListFitnessVitalsLayoutBinding itemListFitnessVitalsLayoutBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 1;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        synchronized (this) {
            this.i = 0L;
        }
        ViewDataBinding.executeBindingsOn(this.fitnessVitalWithData);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.i != 0) {
                return true;
            }
            return this.fitnessVitalWithData.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.i = 4L;
        }
        this.fitnessVitalWithData.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return a((ItemListFitnessVitalsLayoutBinding) obj, i2);
    }

    @Override // com.coveiot.android.theme.databinding.LayoutDashboardVitalGridItemBinding
    public void setFitnessType(@Nullable String str) {
        this.mFitnessType = str;
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.fitnessVitalWithData.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.fitnessType == i) {
            setFitnessType((String) obj);
            return true;
        }
        return false;
    }

    public LayoutDashboardVitalGridItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (ItemListFitnessVitalsLayoutBinding) objArr[1]);
        this.i = -1L;
        setContainedBinding(this.fitnessVitalWithData);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}

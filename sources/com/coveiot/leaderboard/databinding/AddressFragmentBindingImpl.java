package com.coveiot.leaderboard.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.coveiot.leaderboard.BR;
import com.coveiot.leaderboard.R;
/* loaded from: classes9.dex */
public class AddressFragmentBindingImpl extends AddressFragmentBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts j;
    @Nullable
    public static final SparseIntArray k;
    @NonNull
    public final ConstraintLayout h;
    public long i;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(5);
        j = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"current_location_details"}, new int[]{1}, new int[]{R.layout.current_location_details});
        SparseIntArray sparseIntArray = new SparseIntArray();
        k = sparseIntArray;
        sparseIntArray.put(R.id.cl_place_autocomplete, 2);
        sparseIntArray.put(R.id.gl_button_only, 3);
        sparseIntArray.put(R.id.selectAddress, 4);
    }

    public AddressFragmentBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 5, j, k));
    }

    public final boolean a(CurrentLocationDetailsBinding currentLocationDetailsBinding, int i) {
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
        ViewDataBinding.executeBindingsOn(this.location);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.i != 0) {
                return true;
            }
            return this.location.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.i = 2L;
        }
        this.location.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return a((CurrentLocationDetailsBinding) obj, i2);
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.location.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    public AddressFragmentBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (ConstraintLayout) objArr[2], (Guideline) objArr[3], (CurrentLocationDetailsBinding) objArr[1], (Button) objArr[4]);
        this.i = -1L;
        setContainedBinding(this.location);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}

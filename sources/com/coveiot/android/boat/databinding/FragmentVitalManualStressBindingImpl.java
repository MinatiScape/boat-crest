package com.coveiot.android.boat.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.theme.databinding.VitalsDetailedLayoutBinding;
/* loaded from: classes3.dex */
public class FragmentVitalManualStressBindingImpl extends FragmentVitalManualStressBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts k;
    @Nullable
    public static final SparseIntArray l;
    @NonNull
    public final ConstraintLayout h;
    @NonNull
    public final ConstraintLayout i;
    public long j;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(17);
        k = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"vitals_detailed_layout"}, new int[]{2}, new int[]{R.layout.vitals_detailed_layout});
        SparseIntArray sparseIntArray = new SparseIntArray();
        l = sparseIntArray;
        sparseIntArray.put(R.id.nestedScrollManualStress, 3);
        sparseIntArray.put(R.id.graphBg, 4);
        sparseIntArray.put(R.id.cv_stress, 5);
        sparseIntArray.put(R.id.time_val_lay, 6);
        sparseIntArray.put(R.id.nestedScrollView, 7);
        sparseIntArray.put(R.id.rv_stressList, 8);
        sparseIntArray.put(R.id.tvNoDataFound, 9);
        sparseIntArray.put(R.id.tvTipsToReduce, 10);
        sparseIntArray.put(R.id.stressTipsRecycler, 11);
        sparseIntArray.put(R.id.divider2, 12);
        sparseIntArray.put(R.id.tv_what_is_stress, 13);
        sparseIntArray.put(R.id.tvstressdescription, 14);
        sparseIntArray.put(R.id.stress_range_bar, 15);
        sparseIntArray.put(R.id.divider3, 16);
    }

    public FragmentVitalManualStressBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 17, k, l));
    }

    public final boolean a(VitalsDetailedLayoutBinding vitalsDetailedLayoutBinding, int i) {
        if (i == 0) {
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
        ViewDataBinding.executeBindingsOn(this.vitalsMainData);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.j != 0) {
                return true;
            }
            return this.vitalsMainData.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.j = 2L;
        }
        this.vitalsMainData.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return a((VitalsDetailedLayoutBinding) obj, i2);
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.vitalsMainData.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    public FragmentVitalManualStressBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (LinearLayout) objArr[5], (View) objArr[12], (View) objArr[16], (ConstraintLayout) objArr[4], (NestedScrollView) objArr[3], (NestedScrollView) objArr[7], (RecyclerView) objArr[8], (ImageView) objArr[15], (RecyclerView) objArr[11], (RelativeLayout) objArr[6], (TextView) objArr[9], (TextView) objArr[10], (TextView) objArr[13], (TextView) objArr[14], (VitalsDetailedLayoutBinding) objArr[2]);
        this.j = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        ConstraintLayout constraintLayout2 = (ConstraintLayout) objArr[1];
        this.i = constraintLayout2;
        constraintLayout2.setTag(null);
        setContainedBinding(this.vitalsMainData);
        setRootTag(view);
        invalidateAll();
    }
}

package com.mappls.sdk.direction.ui.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.mappls.sdk.direction.ui.R;
/* loaded from: classes11.dex */
public class MapplsDirectionFuelCostFragmentBindingImpl extends MapplsDirectionFuelCostFragmentBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    @NonNull
    private final ConstraintLayout mboundView0;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(27);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"mappls_direction_common_toolbar"}, new int[]{1}, new int[]{R.layout.mappls_direction_common_toolbar});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.preview_view, 2);
        sparseIntArray.put(R.id.layout_before_fuel_cost_calculated, 3);
        sparseIntArray.put(R.id.layout_toll_info, 4);
        sparseIntArray.put(R.id.layout_toll_layout, 5);
        sparseIntArray.put(R.id.text_view_toll_lebel, 6);
        sparseIntArray.put(R.id.text_view_toll_fee, 7);
        sparseIntArray.put(R.id.image_view_rupee_symbol, 8);
        sparseIntArray.put(R.id.layout_distance, 9);
        sparseIntArray.put(R.id.text_view_distance_lebel, 10);
        sparseIntArray.put(R.id.text_view_distance, 11);
        sparseIntArray.put(R.id.image_view_distance, 12);
        sparseIntArray.put(R.id.layout_duration, 13);
        sparseIntArray.put(R.id.text_view_duration_lebel, 14);
        sparseIntArray.put(R.id.text_view_duration, 15);
        sparseIntArray.put(R.id.image_view_duration, 16);
        sparseIntArray.put(R.id.view, 17);
        sparseIntArray.put(R.id.text_view_select_fuel_type, 18);
        sparseIntArray.put(R.id.recycler_view_fuel_type, 19);
        sparseIntArray.put(R.id.image_view_auto_graph, 20);
        sparseIntArray.put(R.id.edit_text_average, 21);
        sparseIntArray.put(R.id.view1, 22);
        sparseIntArray.put(R.id.image_view_rupee, 23);
        sparseIntArray.put(R.id.edit_text_cost, 24);
        sparseIntArray.put(R.id.view2, 25);
        sparseIntArray.put(R.id.text_view_calculate, 26);
    }

    public MapplsDirectionFuelCostFragmentBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 27, sIncludes, sViewsWithIds));
    }

    private MapplsDirectionFuelCostFragmentBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (EditText) objArr[21], (EditText) objArr[24], (MapplsDirectionCommonToolbarBinding) objArr[1], (ImageView) objArr[20], (ImageView) objArr[12], (ImageView) objArr[16], (ImageView) objArr[23], (ImageView) objArr[8], (ConstraintLayout) objArr[3], (ConstraintLayout) objArr[9], (ConstraintLayout) objArr[13], (ConstraintLayout) objArr[4], (ConstraintLayout) objArr[5], (View) objArr[2], (RecyclerView) objArr[19], (TextView) objArr[26], (TextView) objArr[11], (TextView) objArr[10], (TextView) objArr[15], (TextView) objArr[14], (TextView) objArr[18], (TextView) objArr[7], (TextView) objArr[6], (View) objArr[17], (View) objArr[22], (View) objArr[25]);
        this.mDirtyFlags = -1L;
        setContainedBinding(this.fuelCostHeader);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    private boolean onChangeFuelCostHeader(MapplsDirectionCommonToolbarBinding mapplsDirectionCommonToolbarBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0L;
        }
        ViewDataBinding.executeBindingsOn(this.fuelCostHeader);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.fuelCostHeader.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2L;
        }
        this.fuelCostHeader.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeFuelCostHeader((MapplsDirectionCommonToolbarBinding) obj, i2);
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.fuelCostHeader.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }
}

package com.mappls.sdk.direction.ui.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.mappls.sdk.direction.ui.R;
/* loaded from: classes11.dex */
public class MapplsDirectionTollCostFragmentBindingImpl extends MapplsDirectionTollCostFragmentBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    @NonNull
    private final ConstraintLayout mboundView0;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(22);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"mappls_direction_common_toolbar"}, new int[]{1}, new int[]{R.layout.mappls_direction_common_toolbar});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.preview_view, 2);
        sparseIntArray.put(R.id.nested_scroll_view, 3);
        sparseIntArray.put(R.id.layout_after_fuel_cost_calculated, 4);
        sparseIntArray.put(R.id.image_view_total_toll_cost, 5);
        sparseIntArray.put(R.id.text_view_toll_route, 6);
        sparseIntArray.put(R.id.text_view_number_of_toll, 7);
        sparseIntArray.put(R.id.image_view_show_hide_toll_info, 8);
        sparseIntArray.put(R.id.text_view_total_toll_fee, 9);
        sparseIntArray.put(R.id.recycler_view_toll, 10);
        sparseIntArray.put(R.id.layout_fuel_and_total_cost, 11);
        sparseIntArray.put(R.id.image_view_fuel_cost, 12);
        sparseIntArray.put(R.id.text_view_total_fuel_cost, 13);
        sparseIntArray.put(R.id.text_view_total_fuel_cost_display, 14);
        sparseIntArray.put(R.id.text_view_fuel_cost_info, 15);
        sparseIntArray.put(R.id.view1, 16);
        sparseIntArray.put(R.id.image_view_grand_total_cost, 17);
        sparseIntArray.put(R.id.text_view_grand_total, 18);
        sparseIntArray.put(R.id.text_view_grand_total_fee, 19);
        sparseIntArray.put(R.id.text_view_grand_total_info, 20);
        sparseIntArray.put(R.id.text_view_add_update_fuel_cost, 21);
    }

    public MapplsDirectionTollCostFragmentBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 22, sIncludes, sViewsWithIds));
    }

    private MapplsDirectionTollCostFragmentBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (ImageView) objArr[12], (ImageView) objArr[17], (ImageView) objArr[8], (ImageView) objArr[5], (ConstraintLayout) objArr[4], (ConstraintLayout) objArr[11], (MapplsDirectionCommonToolbarBinding) objArr[1], (NestedScrollView) objArr[3], (View) objArr[2], (RecyclerView) objArr[10], (TextView) objArr[21], (TextView) objArr[15], (TextView) objArr[18], (TextView) objArr[19], (TextView) objArr[20], (TextView) objArr[7], (TextView) objArr[6], (TextView) objArr[13], (TextView) objArr[14], (TextView) objArr[9], (View) objArr[16]);
        this.mDirtyFlags = -1L;
        setContainedBinding(this.layoutTollCost);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    private boolean onChangeLayoutTollCost(MapplsDirectionCommonToolbarBinding mapplsDirectionCommonToolbarBinding, int i) {
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
        ViewDataBinding.executeBindingsOn(this.layoutTollCost);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.layoutTollCost.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2L;
        }
        this.layoutTollCost.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeLayoutTollCost((MapplsDirectionCommonToolbarBinding) obj, i2);
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.layoutTollCost.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }
}

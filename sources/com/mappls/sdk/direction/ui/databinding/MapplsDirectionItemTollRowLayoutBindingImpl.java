package com.mappls.sdk.direction.ui.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.mappls.sdk.direction.ui.R;
/* loaded from: classes11.dex */
public class MapplsDirectionItemTollRowLayoutBindingImpl extends MapplsDirectionItemTollRowLayoutBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    @NonNull
    private final ConstraintLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.text_view_toll_name, 1);
        sparseIntArray.put(R.id.text_view_toll_info, 2);
        sparseIntArray.put(R.id.layout_more_detail_info, 3);
        sparseIntArray.put(R.id.text_view_cost_label, 4);
        sparseIntArray.put(R.id.text_view_distance_label, 5);
        sparseIntArray.put(R.id.text_view_duration_label, 6);
        sparseIntArray.put(R.id.text_view_emergency_label, 7);
        sparseIntArray.put(R.id.text_view_amenities_label, 8);
        sparseIntArray.put(R.id.barrier, 9);
        sparseIntArray.put(R.id.text_view_cost, 10);
        sparseIntArray.put(R.id.text_view_distance, 11);
        sparseIntArray.put(R.id.text_view_duration, 12);
        sparseIntArray.put(R.id.text_view_emergency, 13);
        sparseIntArray.put(R.id.text_view_amenities, 14);
    }

    public MapplsDirectionItemTollRowLayoutBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 15, sIncludes, sViewsWithIds));
    }

    private MapplsDirectionItemTollRowLayoutBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (Barrier) objArr[9], (ConstraintLayout) objArr[3], (TextView) objArr[14], (TextView) objArr[8], (TextView) objArr[10], (TextView) objArr[4], (TextView) objArr[11], (TextView) objArr[5], (TextView) objArr[12], (TextView) objArr[6], (TextView) objArr[13], (TextView) objArr[7], (TextView) objArr[2], (TextView) objArr[1]);
        this.mDirtyFlags = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0L;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.mDirtyFlags != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1L;
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
}

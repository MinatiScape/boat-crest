package com.mappls.sdk.category.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.mappls.sdk.category.R;
/* loaded from: classes11.dex */
public class MapplsCategoryResultFragmentBindingImpl extends MapplsCategoryResultFragmentBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    @NonNull
    private final CoordinatorLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.mappls_category_progress_layout, 2);
        sparseIntArray.put(R.id.mappls_category_layout_empty_error_state, 3);
        sparseIntArray.put(R.id.result_appbar, 4);
        sparseIntArray.put(R.id.mappls_category_result_search_layout_parent, 5);
        sparseIntArray.put(R.id.mappls_category_result_back_icon, 6);
        sparseIntArray.put(R.id.mappls_category_result_search_input, 7);
        sparseIntArray.put(R.id.mappls_category_search_clear_btn, 8);
        sparseIntArray.put(R.id.view2, 9);
        sparseIntArray.put(R.id.mappls_category_text_view_results, 10);
        sparseIntArray.put(R.id.mappls_category_poi_list, 11);
    }

    public MapplsCategoryResultFragmentBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 12, sIncludes, sViewsWithIds));
    }

    private MapplsCategoryResultFragmentBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (View) objArr[3], (RecyclerView) objArr[11], (RelativeLayout) objArr[1], (View) objArr[2], (ImageView) objArr[6], (TextView) objArr[7], (LinearLayout) objArr[5], (ImageButton) objArr[8], (TextView) objArr[10], (AppBarLayout) objArr[4], (View) objArr[9]);
        this.mDirtyFlags = -1L;
        this.mapplsCategoryPoiListBottomSheetContainer.setTag(null);
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) objArr[0];
        this.mboundView0 = coordinatorLayout;
        coordinatorLayout.setTag(null);
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

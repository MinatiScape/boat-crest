package com.mappls.sdk.category.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
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
public class MapplsCategorySearchItemFragmentBindingImpl extends MapplsCategorySearchItemFragmentBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.mappls_category_appbar, 1);
        sparseIntArray.put(R.id.mappls_category_search_layout_parent, 2);
        sparseIntArray.put(R.id.mappls_category_back_icon, 3);
        sparseIntArray.put(R.id.mappls_category_search_input, 4);
        sparseIntArray.put(R.id.mappls_category_search_clear_btn, 5);
        sparseIntArray.put(R.id.mappls_category_linear_layout, 6);
        sparseIntArray.put(R.id.mappls_category_category_recycler_view, 7);
        sparseIntArray.put(R.id.mappls_category_button_next, 8);
        sparseIntArray.put(R.id.mappls_category_progress_bar, 9);
    }

    public MapplsCategorySearchItemFragmentBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 10, sIncludes, sViewsWithIds));
    }

    private MapplsCategorySearchItemFragmentBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (AppBarLayout) objArr[1], (ImageView) objArr[3], (TextView) objArr[8], (RecyclerView) objArr[7], (CoordinatorLayout) objArr[0], (LinearLayout) objArr[6], (RelativeLayout) objArr[9], (ImageButton) objArr[5], (EditText) objArr[4], (LinearLayout) objArr[2]);
        this.mDirtyFlags = -1L;
        this.mapplsCategoryLayoutBackground.setTag(null);
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

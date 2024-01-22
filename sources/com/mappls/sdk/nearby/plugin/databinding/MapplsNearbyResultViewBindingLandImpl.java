package com.mappls.sdk.nearby.plugin.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.mappls.sdk.nearby.plugin.R;
/* loaded from: classes10.dex */
public class MapplsNearbyResultViewBindingLandImpl extends MapplsNearbyResultViewBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i = null;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.mappls_nearby_map_container, 1);
        sparseIntArray.put(R.id.mappls_nearby_result_toolbar, 2);
        sparseIntArray.put(R.id.mappls_nearby_toolbar_back_button, 3);
        sparseIntArray.put(R.id.mappls_nearby_result_toolbar_text_view, 4);
        sparseIntArray.put(R.id.mappls_nearby_result_category_bg, 5);
        sparseIntArray.put(R.id.mappls_nearby_result_category_rv, 6);
        sparseIntArray.put(R.id.mappls_nearby_tab_layout, 7);
        sparseIntArray.put(R.id.mappls_nearby_view_pager, 8);
        sparseIntArray.put(R.id.mappls_nearby_result_page, 9);
        sparseIntArray.put(R.id.mappls_nearby_next, 10);
        sparseIntArray.put(R.id.mappls_nearby_page_next, 11);
        sparseIntArray.put(R.id.mappls_nearby_page_next_tv, 12);
        sparseIntArray.put(R.id.mappls_nearby_page_current, 13);
        sparseIntArray.put(R.id.mappls_nearby_page_current_tv, 14);
        sparseIntArray.put(R.id.mappls_nearby_prev, 15);
        sparseIntArray.put(R.id.mappls_nearby_result_progress_bar, 16);
    }

    public MapplsNearbyResultViewBindingLandImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 17, i, j));
    }

    public MapplsNearbyResultViewBindingLandImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (FrameLayout) objArr[1], (CardView) objArr[10], (CardView) objArr[13], (TextView) objArr[14], (CardView) objArr[11], (TextView) objArr[12], null, (CardView) objArr[15], (ConstraintLayout) objArr[5], (RecyclerView) objArr[6], (ConstraintLayout) objArr[9], (RelativeLayout) objArr[16], (ConstraintLayout) objArr[2], (TextView) objArr[4], (TabLayout) objArr[7], null, (ImageView) objArr[3], (ViewPager2) objArr[8]);
        this.h = -1L;
        ((ConstraintLayout) objArr[0]).setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        synchronized (this) {
            this.h = 0L;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.h != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.h = 1L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i2, Object obj, int i3) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i2, @Nullable Object obj) {
        return true;
    }
}

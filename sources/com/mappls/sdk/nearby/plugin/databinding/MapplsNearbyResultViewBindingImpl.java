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
public class MapplsNearbyResultViewBindingImpl extends MapplsNearbyResultViewBinding {
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
        sparseIntArray.put(R.id.mappls_nearby_tab_shadow, 8);
        sparseIntArray.put(R.id.mappls_nearby_view_pager, 9);
        sparseIntArray.put(R.id.mappls_nearby_page_shadow, 10);
        sparseIntArray.put(R.id.mappls_nearby_result_page, 11);
        sparseIntArray.put(R.id.mappls_nearby_next, 12);
        sparseIntArray.put(R.id.mappls_nearby_page_next, 13);
        sparseIntArray.put(R.id.mappls_nearby_page_next_tv, 14);
        sparseIntArray.put(R.id.mappls_nearby_page_current, 15);
        sparseIntArray.put(R.id.mappls_nearby_page_current_tv, 16);
        sparseIntArray.put(R.id.mappls_nearby_prev, 17);
        sparseIntArray.put(R.id.mappls_nearby_result_progress_bar, 18);
    }

    public MapplsNearbyResultViewBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 19, i, j));
    }

    public MapplsNearbyResultViewBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (FrameLayout) objArr[1], (CardView) objArr[12], (CardView) objArr[15], (TextView) objArr[16], (CardView) objArr[13], (TextView) objArr[14], (View) objArr[10], (CardView) objArr[17], (ConstraintLayout) objArr[5], (RecyclerView) objArr[6], (ConstraintLayout) objArr[11], (RelativeLayout) objArr[18], (ConstraintLayout) objArr[2], (TextView) objArr[4], (TabLayout) objArr[7], (View) objArr[8], (ImageView) objArr[3], (ViewPager2) objArr[9]);
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

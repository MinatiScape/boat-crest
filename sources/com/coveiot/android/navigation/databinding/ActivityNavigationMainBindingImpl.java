package com.coveiot.android.navigation.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.navigation.R;
import com.coveiot.android.navigation.utils.CustomMapView;
/* loaded from: classes5.dex */
public class ActivityNavigationMainBindingImpl extends ActivityNavigationMainBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i = null;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.toolbar, 1);
        sparseIntArray.put(R.id.ll_search_source_layout, 2);
        sparseIntArray.put(R.id.iv_place_indicator, 3);
        sparseIntArray.put(R.id.tv_place_label, 4);
        sparseIntArray.put(R.id.iv_place_label_close_icon, 5);
        sparseIntArray.put(R.id.rv_tags, 6);
        sparseIntArray.put(R.id.mappls_mapview_navigation_main, 7);
        sparseIntArray.put(R.id.ll_search_location_info, 8);
        sparseIntArray.put(R.id.tv_search_location_info, 9);
        sparseIntArray.put(R.id.cl_selected_place_layout, 10);
        sparseIntArray.put(R.id.lout_selected_place_title, 11);
        sparseIntArray.put(R.id.tvSelectedPlaceName, 12);
        sparseIntArray.put(R.id.tvSelectedPlaceAddress, 13);
        sparseIntArray.put(R.id.clET, 14);
        sparseIntArray.put(R.id.tvReachDurationToSelectedPlace, 15);
        sparseIntArray.put(R.id.tvDistanceToReachSelectedPlace, 16);
        sparseIntArray.put(R.id.btn_get_directions, 17);
        sparseIntArray.put(R.id.tvSelectedPlaceDisclaimer, 18);
    }

    public ActivityNavigationMainBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 19, i, j));
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

    public ActivityNavigationMainBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (Button) objArr[17], (ConstraintLayout) objArr[14], (LinearLayout) objArr[0], (ConstraintLayout) objArr[10], (ImageView) objArr[3], (ImageView) objArr[5], (LinearLayout) objArr[8], (LinearLayout) objArr[2], (ConstraintLayout) objArr[11], (CustomMapView) objArr[7], (RecyclerView) objArr[6], (View) objArr[1], (TextView) objArr[16], (TextView) objArr[4], (TextView) objArr[15], (TextView) objArr[9], (TextView) objArr[13], (TextView) objArr[18], (TextView) objArr[12]);
        this.h = -1L;
        this.clNavigationMainRoot.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}

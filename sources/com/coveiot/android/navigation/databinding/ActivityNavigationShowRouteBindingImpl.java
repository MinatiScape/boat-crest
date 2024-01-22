package com.coveiot.android.navigation.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.navigation.R;
import com.coveiot.android.navigation.utils.CustomMapView;
/* loaded from: classes5.dex */
public class ActivityNavigationShowRouteBindingImpl extends ActivityNavigationShowRouteBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts k = null;
    @Nullable
    public static final SparseIntArray l;
    @NonNull
    public final ConstraintLayout h;
    @NonNull
    public final LinearLayout i;
    public long j;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        l = sparseIntArray;
        sparseIntArray.put(R.id.toolbar, 2);
        sparseIntArray.put(R.id.scroll_view, 3);
        sparseIntArray.put(R.id.cl_route_mapview_top_layout, 4);
        sparseIntArray.put(R.id.ll_search_source_layout, 5);
        sparseIntArray.put(R.id.tv_show_route_source_label, 6);
        sparseIntArray.put(R.id.iv_vice_versa, 7);
        sparseIntArray.put(R.id.ll_search_destination_layout, 8);
        sparseIntArray.put(R.id.tv_show_route_destination_label, 9);
        sparseIntArray.put(R.id.clWalking, 10);
        sparseIntArray.put(R.id.tv_walking_mode, 11);
        sparseIntArray.put(R.id.clDriving, 12);
        sparseIntArray.put(R.id.tv_driving_mode, 13);
        sparseIntArray.put(R.id.barrier_show_routes_1, 14);
        sparseIntArray.put(R.id.frameLayout, 15);
        sparseIntArray.put(R.id.mapview_show_route, 16);
        sparseIntArray.put(R.id.tvTapToEnter, 17);
        sparseIntArray.put(R.id.cl_route_mapview_bottom_layout, 18);
        sparseIntArray.put(R.id.tv_most_optimal_route_selected, 19);
        sparseIntArray.put(R.id.tvBsReachDurationToDestination, 20);
        sparseIntArray.put(R.id.tvBsArrival, 21);
        sparseIntArray.put(R.id.barrier_bs1, 22);
        sparseIntArray.put(R.id.tvBsDistanceToReachDestination, 23);
        sparseIntArray.put(R.id.ll_route_detail_buttons, 24);
        sparseIntArray.put(R.id.btn_preview, 25);
        sparseIntArray.put(R.id.btn_navigate_on_watch, 26);
        sparseIntArray.put(R.id.tvNavigateToWatchDisclaimer, 27);
        sparseIntArray.put(R.id.iv_bs_info_icon, 28);
        sparseIntArray.put(R.id.tvNavigateToWatchInstructions, 29);
    }

    public ActivityNavigationShowRouteBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 30, k, l));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        synchronized (this) {
            this.j = 0L;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.j != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.j = 1L;
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

    public ActivityNavigationShowRouteBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (Barrier) objArr[22], (Barrier) objArr[14], (Button) objArr[26], (Button) objArr[25], (ConstraintLayout) objArr[12], (ConstraintLayout) objArr[18], (ConstraintLayout) objArr[4], (ConstraintLayout) objArr[10], (FrameLayout) objArr[15], (ImageView) objArr[28], (ImageView) objArr[7], (LinearLayout) objArr[24], (ConstraintLayout) objArr[8], (LinearLayout) objArr[5], (CustomMapView) objArr[16], (NestedScrollView) objArr[3], (View) objArr[2], (TextView) objArr[21], (TextView) objArr[23], (TextView) objArr[20], (TextView) objArr[13], (TextView) objArr[19], (TextView) objArr[27], (TextView) objArr[29], (TextView) objArr[9], (TextView) objArr[6], (TextView) objArr[17], (TextView) objArr[11]);
        this.j = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        LinearLayout linearLayout = (LinearLayout) objArr[1];
        this.i = linearLayout;
        linearLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}

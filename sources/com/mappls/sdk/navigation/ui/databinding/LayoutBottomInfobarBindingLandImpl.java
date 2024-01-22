package com.mappls.sdk.navigation.ui.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.mappls.sdk.navigation.ui.R;
/* loaded from: classes11.dex */
public class LayoutBottomInfobarBindingLandImpl extends LayoutBottomInfobarBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i = null;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.cardView, 1);
        sparseIntArray.put(R.id.top_separator_view_day_mode, 2);
        sparseIntArray.put(R.id.top_separator_view_night_mode, 3);
        sparseIntArray.put(R.id.iv_bottom_sheet_arrow, 4);
        sparseIntArray.put(R.id.vertical_seperator_view, 5);
        sparseIntArray.put(R.id.image_route_overview, 6);
        sparseIntArray.put(R.id.stop_navigation, 7);
        sparseIntArray.put(R.id.text_view_total_time_left, 8);
        sparseIntArray.put(R.id.text_view_total_distance_left, 9);
        sparseIntArray.put(R.id.text_view_reach_eta, 10);
        sparseIntArray.put(R.id.destination_text, 11);
        sparseIntArray.put(R.id.progress_bar_layout, 12);
        sparseIntArray.put(R.id.car_speed_light_imageview, 13);
        sparseIntArray.put(R.id.nav_progress, 14);
        sparseIntArray.put(R.id.nav_destination_imageview, 15);
        sparseIntArray.put(R.id.remaining_distance_text_view, 16);
        sparseIntArray.put(R.id.rv_bottom_item, 17);
    }

    public LayoutBottomInfobarBindingLandImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 18, i, j));
    }

    public LayoutBottomInfobarBindingLandImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ImageView) objArr[13], (CardView) objArr[1], (TextView) objArr[11], (ImageView) objArr[6], (ImageView) objArr[4], (ImageView) objArr[15], (ProgressBar) objArr[14], (RelativeLayout) objArr[0], (LinearLayout) objArr[12], (TextView) objArr[16], (RecyclerView) objArr[17], null, (ImageView) objArr[7], (TextView) objArr[10], (TextView) objArr[9], (TextView) objArr[8], (View) objArr[2], (View) objArr[3], (View) objArr[5]);
        this.h = -1L;
        this.optionsRecyclerViewContainer.setTag(null);
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

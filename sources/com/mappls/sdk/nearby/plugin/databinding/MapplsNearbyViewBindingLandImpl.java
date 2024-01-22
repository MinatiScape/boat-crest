package com.mappls.sdk.nearby.plugin.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.mappls.sdk.nearby.plugin.R;
/* loaded from: classes10.dex */
public class MapplsNearbyViewBindingLandImpl extends MapplsNearbyViewBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i = null;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.mappls_nearby_layout_title, 1);
        sparseIntArray.put(R.id.mappls_nearby_toolbar_icon, 2);
        sparseIntArray.put(R.id.mappls_nearby_toolbar_text, 3);
        sparseIntArray.put(R.id.mappls_nearby_location_layout, 4);
        sparseIntArray.put(R.id.mappls_nearby_location_text, 5);
        sparseIntArray.put(R.id.mappls_nearby_tv_address, 6);
        sparseIntArray.put(R.id.mappls_nearby_change_location_btn, 7);
        sparseIntArray.put(R.id.mappls_nearby_current_location_btn, 8);
        sparseIntArray.put(R.id.mappls_nearby_rv_category, 9);
        sparseIntArray.put(R.id.mappls_nearby_bottom_layout, 10);
        sparseIntArray.put(R.id.mappls_nearby_next_btn, 11);
        sparseIntArray.put(R.id.mappls_nearby_progress_bar, 12);
    }

    public MapplsNearbyViewBindingLandImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 13, i, j));
    }

    public MapplsNearbyViewBindingLandImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[0], (CardView) objArr[10], (TextView) objArr[7], (TextView) objArr[8], (LinearLayout) objArr[1], (CardView) objArr[4], (TextView) objArr[5], (TextView) objArr[11], (RelativeLayout) objArr[12], (RecyclerView) objArr[9], (ImageView) objArr[2], (TextView) objArr[3], (TextView) objArr[6]);
        this.h = -1L;
        this.mapplsNearbyBgLayout.setTag(null);
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

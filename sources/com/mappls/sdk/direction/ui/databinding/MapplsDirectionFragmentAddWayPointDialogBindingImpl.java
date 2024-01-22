package com.mappls.sdk.direction.ui.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.mappls.sdk.direction.ui.R;
/* loaded from: classes11.dex */
public class MapplsDirectionFragmentAddWayPointDialogBindingImpl extends MapplsDirectionFragmentAddWayPointDialogBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.selected_waypoint_layout_parent, 1);
        sparseIntArray.put(R.id.selected_waypoint_back_icon, 2);
        sparseIntArray.put(R.id.selected_waypoint_search_input, 3);
        sparseIntArray.put(R.id.selected_waypoint_clear_btn, 4);
        sparseIntArray.put(R.id.place_result_img, 5);
        sparseIntArray.put(R.id.result_place_name, 6);
        sparseIntArray.put(R.id.text_view_distance, 7);
        sparseIntArray.put(R.id.item_place_result_rating_bar, 8);
        sparseIntArray.put(R.id.item_place_result_place_address, 9);
        sparseIntArray.put(R.id.button_add_waypoint, 10);
    }

    public MapplsDirectionFragmentAddWayPointDialogBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 11, sIncludes, sViewsWithIds));
    }

    private MapplsDirectionFragmentAddWayPointDialogBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[0], (TextView) objArr[10], (TextView) objArr[9], (RatingBar) objArr[8], (ImageView) objArr[5], (TextView) objArr[6], (ImageView) objArr[2], (ImageButton) objArr[4], (LinearLayout) objArr[1], (TextView) objArr[3], (TextView) objArr[7]);
        this.mDirtyFlags = -1L;
        this.addWaypointBottomSheet.setTag(null);
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

package com.coveiot.android.navigation.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
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
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.navigation.R;
/* loaded from: classes5.dex */
public class ActivityNavigationSearchPlacesBindingImpl extends ActivityNavigationSearchPlacesBinding {
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
        sparseIntArray.put(R.id.clSearchPlacesSearchLayout, 4);
        sparseIntArray.put(R.id.ivSearchPlacesSearchIcon, 5);
        sparseIntArray.put(R.id.etSearchPlaceAddress, 6);
        sparseIntArray.put(R.id.ivSearchPlacesClearIcon, 7);
        sparseIntArray.put(R.id.rv_tags_search_places_activity, 8);
        sparseIntArray.put(R.id.cl_no_recent_history_view_search_places_activity, 9);
        sparseIntArray.put(R.id.divider_1_search_places_activity, 10);
        sparseIntArray.put(R.id.tv_recent_history_search_places_activity, 11);
        sparseIntArray.put(R.id.tv_no_recent_history, 12);
        sparseIntArray.put(R.id.cl_recent_history_view_search_places_activity, 13);
        sparseIntArray.put(R.id.tv_your_current_location_for_recent_history, 14);
        sparseIntArray.put(R.id.divider_recent_history_search_places_activity, 15);
        sparseIntArray.put(R.id.barrier_search_places_1, 16);
        sparseIntArray.put(R.id.tv_recent_history_header_search_places_activity, 17);
        sparseIntArray.put(R.id.tv_recent_history_clear_all_search_places_activity, 18);
        sparseIntArray.put(R.id.rv_recent_history_search_places_activity, 19);
        sparseIntArray.put(R.id.tv_recent_history_view_more_search_places_activity, 20);
        sparseIntArray.put(R.id.tv_your_current_location_for_search_results, 21);
        sparseIntArray.put(R.id.rv_auto_suggest_results, 22);
    }

    public ActivityNavigationSearchPlacesBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 23, k, l));
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

    public ActivityNavigationSearchPlacesBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (Barrier) objArr[16], (ConstraintLayout) objArr[9], (ConstraintLayout) objArr[13], (ConstraintLayout) objArr[4], (View) objArr[10], (View) objArr[15], (EditText) objArr[6], (ImageView) objArr[7], (ImageView) objArr[5], (RecyclerView) objArr[22], (RecyclerView) objArr[19], (RecyclerView) objArr[8], (NestedScrollView) objArr[3], (View) objArr[2], (TextView) objArr[12], (TextView) objArr[18], (TextView) objArr[17], (TextView) objArr[11], (TextView) objArr[20], (TextView) objArr[14], (TextView) objArr[21]);
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

package com.mappls.sdk.navigation.ui.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.mappls.sdk.navigation.ui.R;
/* loaded from: classes11.dex */
public class LayoutNavigationFinishedBindingImpl extends LayoutNavigationFinishedBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i = null;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.navigation_finished_layout, 1);
        sparseIntArray.put(R.id.container_destination_reached, 2);
        sparseIntArray.put(R.id.okay_button, 3);
        sparseIntArray.put(R.id.finished_button, 4);
        sparseIntArray.put(R.id.view, 5);
        sparseIntArray.put(R.id.welcome_to_a_place_text_view, 6);
        sparseIntArray.put(R.id.destination_text_view, 7);
        sparseIntArray.put(R.id.trip_stats_text_view, 8);
        sparseIntArray.put(R.id.trip_stats_recycler_view, 9);
        sparseIntArray.put(R.id.container_buttons, 10);
        sparseIntArray.put(R.id.know_more_button, 11);
        sparseIntArray.put(R.id.ok_button, 12);
    }

    public LayoutNavigationFinishedBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 13, i, j));
    }

    public LayoutNavigationFinishedBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (LinearLayout) objArr[10], (LinearLayout) objArr[2], (TextView) objArr[7], (ImageButton) objArr[4], (TextView) objArr[11], (RelativeLayout) objArr[1], (TextView) objArr[12], (ImageButton) objArr[3], (RecyclerView) objArr[9], (TextView) objArr[8], (View) objArr[5], (TextView) objArr[6]);
        this.h = -1L;
        ((CoordinatorLayout) objArr[0]).setTag(null);
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

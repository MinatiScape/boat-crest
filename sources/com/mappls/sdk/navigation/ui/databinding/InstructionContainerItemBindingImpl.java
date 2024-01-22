package com.mappls.sdk.navigation.ui.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.mappls.sdk.navigation.ui.R;
import com.mappls.sdk.plugin.directions.view.ManeuverView;
/* loaded from: classes11.dex */
public class InstructionContainerItemBindingImpl extends InstructionContainerItemBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i = null;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.strip_item_container, 1);
        sparseIntArray.put(R.id.repeat_current_instructions_layout, 2);
        sparseIntArray.put(R.id.maneuver_image_view, 3);
        sparseIntArray.put(R.id.navigation_strip_dist, 4);
        sparseIntArray.put(R.id.navigation_strip_text, 5);
        sparseIntArray.put(R.id.navigation_strip_short_text, 6);
        sparseIntArray.put(R.id.lane_guidance_container, 7);
        sparseIntArray.put(R.id.rvTurnLanes, 8);
        sparseIntArray.put(R.id.maneuver_id_text_view, 9);
    }

    public InstructionContainerItemBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 10, i, j));
    }

    public InstructionContainerItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (RelativeLayout) objArr[7], (TextView) objArr[9], (ManeuverView) objArr[3], (TextView) objArr[4], (TextView) objArr[6], (TextView) objArr[5], (LinearLayout) objArr[2], (RecyclerView) objArr[8], (LinearLayout) objArr[1]);
        this.h = -1L;
        ((RelativeLayout) objArr[0]).setTag(null);
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
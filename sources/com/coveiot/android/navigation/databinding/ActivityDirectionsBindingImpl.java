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
/* loaded from: classes5.dex */
public class ActivityDirectionsBindingImpl extends ActivityDirectionsBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts j = null;
    @Nullable
    public static final SparseIntArray k;
    @NonNull
    public final ConstraintLayout h;
    public long i;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        k = sparseIntArray;
        sparseIntArray.put(R.id.tvDirectionTitle, 1);
        sparseIntArray.put(R.id.ivSetting, 2);
        sparseIntArray.put(R.id.clAddressDetails, 3);
        sparseIntArray.put(R.id.ll_directions_distance_arrival, 4);
        sparseIntArray.put(R.id.tvTotalDuration, 5);
        sparseIntArray.put(R.id.tvEta, 6);
        sparseIntArray.put(R.id.tvTotalDistance, 7);
        sparseIntArray.put(R.id.rvDirection, 8);
        sparseIntArray.put(R.id.btnNavigationOngoing, 9);
        sparseIntArray.put(R.id.btnExit, 10);
    }

    public ActivityDirectionsBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 11, j, k));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        synchronized (this) {
            this.i = 0L;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.i != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.i = 1L;
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

    public ActivityDirectionsBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (Button) objArr[10], (Button) objArr[9], (ConstraintLayout) objArr[3], (ImageView) objArr[2], (LinearLayout) objArr[4], (RecyclerView) objArr[8], (TextView) objArr[1], (TextView) objArr[6], (TextView) objArr[7], (TextView) objArr[5]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}

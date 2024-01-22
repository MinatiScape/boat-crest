package com.coveiot.android.theme.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public class ToolbarGenericDashboardBindingImpl extends ToolbarGenericDashboardBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i = null;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.imgVLogo, 1);
        sparseIntArray.put(R.id.imgVProfilePic, 2);
        sparseIntArray.put(R.id.clboAtCoins, 3);
        sparseIntArray.put(R.id.boatCoinsValue, 4);
        sparseIntArray.put(R.id.img_weather_dash, 5);
        sparseIntArray.put(R.id.cl_weather_data_dash, 6);
        sparseIntArray.put(R.id.txt_weather_dash, 7);
        sparseIntArray.put(R.id.txt_weather_unit_dash, 8);
    }

    public ToolbarGenericDashboardBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 9, i, j));
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

    public ToolbarGenericDashboardBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (TextView) objArr[4], (ConstraintLayout) objArr[6], (ConstraintLayout) objArr[3], (ImageView) objArr[1], (ImageView) objArr[2], (ImageView) objArr[5], (Toolbar) objArr[0], (TextView) objArr[7], (TextView) objArr[8]);
        this.h = -1L;
        this.toolbarDashboard.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}

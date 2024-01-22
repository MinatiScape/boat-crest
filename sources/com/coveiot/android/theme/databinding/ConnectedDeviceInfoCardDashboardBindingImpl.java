package com.coveiot.android.theme.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.coveiot.android.theme.BR;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public class ConnectedDeviceInfoCardDashboardBindingImpl extends ConnectedDeviceInfoCardDashboardBinding {
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
        sparseIntArray.put(R.id.clDevice, 2);
        sparseIntArray.put(R.id.tvHello, 3);
        sparseIntArray.put(R.id.tvUserName, 4);
        sparseIntArray.put(R.id.clBottom, 5);
        sparseIntArray.put(R.id.ivBattery, 6);
        sparseIntArray.put(R.id.batteryProgressBar, 7);
        sparseIntArray.put(R.id.tvBatteryLevel, 8);
        sparseIntArray.put(R.id.middleView, 9);
        sparseIntArray.put(R.id.tvLastSyncTime, 10);
        sparseIntArray.put(R.id.tvLastSyncDate, 11);
        sparseIntArray.put(R.id.clManualSync, 12);
        sparseIntArray.put(R.id.tvSyncingStatus, 13);
        sparseIntArray.put(R.id.guideline1, 14);
        sparseIntArray.put(R.id.ivWatch, 15);
        sparseIntArray.put(R.id.guideline2, 16);
    }

    public ConnectedDeviceInfoCardDashboardBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 17, j, k));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j2;
        synchronized (this) {
            j2 = this.i;
            this.i = 0L;
        }
        String str = this.mWatchName;
        if ((j2 & 3) != 0) {
            TextViewBindingAdapter.setText(this.tvDeviceName, str);
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
            this.i = 2L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.watchName == i) {
            setWatchName((String) obj);
            return true;
        }
        return false;
    }

    @Override // com.coveiot.android.theme.databinding.ConnectedDeviceInfoCardDashboardBinding
    public void setWatchName(@Nullable String str) {
        this.mWatchName = str;
        synchronized (this) {
            this.i |= 1;
        }
        notifyPropertyChanged(BR.watchName);
        super.requestRebind();
    }

    public ConnectedDeviceInfoCardDashboardBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ProgressBar) objArr[7], (ConstraintLayout) objArr[5], (ConstraintLayout) objArr[2], (ConstraintLayout) objArr[12], (Guideline) objArr[14], (Guideline) objArr[16], (ImageView) objArr[6], (ImageView) objArr[15], (View) objArr[9], (TextView) objArr[8], (TextView) objArr[1], (TextView) objArr[3], (TextView) objArr[11], (TextView) objArr[10], (TextView) objArr[13], (TextView) objArr[4]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        this.tvDeviceName.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}

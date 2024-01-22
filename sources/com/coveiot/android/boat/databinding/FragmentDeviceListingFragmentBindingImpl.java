package com.coveiot.android.boat.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.airbnb.lottie.LottieAnimationView;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public class FragmentDeviceListingFragmentBindingImpl extends FragmentDeviceListingFragmentBinding {
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
        sparseIntArray.put(R.id.tvBack1, 3);
        sparseIntArray.put(R.id.tvBack, 4);
        sparseIntArray.put(R.id.lottieView, 5);
        sparseIntArray.put(R.id.tvNoDevice, 6);
        sparseIntArray.put(R.id.tvInfo1, 7);
        sparseIntArray.put(R.id.tvSearchAgain, 8);
        sparseIntArray.put(R.id.tvOr, 9);
        sparseIntArray.put(R.id.clPairViaQR1, 10);
        sparseIntArray.put(R.id.tvQRCode1, 11);
        sparseIntArray.put(R.id.glBottom1, 12);
        sparseIntArray.put(R.id.ivConnectionLoader1, 13);
        sparseIntArray.put(R.id.scan_all_devices_tv, 14);
        sparseIntArray.put(R.id.constraintLayout4, 15);
        sparseIntArray.put(R.id.tvSearching, 16);
        sparseIntArray.put(R.id.tvDeviceName, 17);
        sparseIntArray.put(R.id.tvInfo, 18);
        sparseIntArray.put(R.id.rvBleDevices, 19);
        sparseIntArray.put(R.id.clMiddle, 20);
        sparseIntArray.put(R.id.clQRCode, 21);
        sparseIntArray.put(R.id.clPairViaQR, 22);
        sparseIntArray.put(R.id.tvQRCode, 23);
        sparseIntArray.put(R.id.tvTrouble, 24);
        sparseIntArray.put(R.id.clPairDevice, 25);
        sparseIntArray.put(R.id.btnPairDevice, 26);
        sparseIntArray.put(R.id.tvUnableToFindDevice, 27);
        sparseIntArray.put(R.id.glBottom, 28);
        sparseIntArray.put(R.id.ivConnectionLoader, 29);
    }

    public FragmentDeviceListingFragmentBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 30, j, k));
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

    public FragmentDeviceListingFragmentBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (Button) objArr[26], (ConstraintLayout) objArr[20], (ConstraintLayout) objArr[1], (ConstraintLayout) objArr[25], (ConstraintLayout) objArr[22], (ConstraintLayout) objArr[10], (ConstraintLayout) objArr[21], (ConstraintLayout) objArr[2], (ConstraintLayout) objArr[15], (Guideline) objArr[28], (Guideline) objArr[12], (ImageView) objArr[29], (ImageView) objArr[13], (LottieAnimationView) objArr[5], (RecyclerView) objArr[19], (TextView) objArr[14], (View) objArr[4], (View) objArr[3], (TextView) objArr[17], (TextView) objArr[18], (TextView) objArr[7], (TextView) objArr[6], (TextView) objArr[9], (TextView) objArr[23], (TextView) objArr[11], (TextView) objArr[8], (TextView) objArr[16], (TextView) objArr[24], (TextView) objArr[27]);
        this.i = -1L;
        this.clNoDeviceFound.setTag(null);
        this.clSearchDevice.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}

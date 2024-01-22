package com.coveiot.android.dashboard2.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.dashboard2.BR;
import com.coveiot.android.dashboard2.R;
import com.coveiot.android.dashboard2.model.SetupYourWatchDataModel;
/* loaded from: classes4.dex */
public class SetupWatchLayoutBindingImpl extends SetupWatchLayoutBinding {
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
        sparseIntArray.put(R.id.clMain, 1);
        sparseIntArray.put(R.id.tvWatchSettingHeader, 2);
        sparseIntArray.put(R.id.tvWatchSettingRightHeader, 3);
        sparseIntArray.put(R.id.cvBackground, 4);
        sparseIntArray.put(R.id.clBgContainer, 5);
        sparseIntArray.put(R.id.ivWatchBackground, 6);
        sparseIntArray.put(R.id.tvInfo, 7);
    }

    public SetupWatchLayoutBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 8, j, k));
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
            this.i = 2L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // com.coveiot.android.dashboard2.databinding.SetupWatchLayoutBinding
    public void setData(@Nullable SetupYourWatchDataModel setupYourWatchDataModel) {
        this.mData = setupYourWatchDataModel;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.data == i) {
            setData((SetupYourWatchDataModel) obj);
            return true;
        }
        return false;
    }

    public SetupWatchLayoutBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[5], (ConstraintLayout) objArr[1], (CardView) objArr[4], (ImageView) objArr[6], (TextView) objArr[7], (TextView) objArr[2], (TextView) objArr[3]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}

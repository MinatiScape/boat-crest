package com.coveiot.android.theme.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.BR;
import com.coveiot.android.theme.R;
import com.github.anastr.speedviewlib.SpeedView;
/* loaded from: classes7.dex */
public class ItemListFitnessVitalsLayoutBindingImpl extends ItemListFitnessVitalsLayoutBinding {
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
        sparseIntArray.put(R.id.ivVitalImage, 8);
        sparseIntArray.put(R.id.speedView_stress, 9);
        sparseIntArray.put(R.id.tvVitalValue, 10);
        sparseIntArray.put(R.id.clLevel, 11);
        sparseIntArray.put(R.id.view, 12);
        sparseIntArray.put(R.id.tvVitalLevel, 13);
        sparseIntArray.put(R.id.tvVitalDownUpPercentage, 14);
    }

    public ItemListFitnessVitalsLayoutBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 15, j, k));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j2;
        int i;
        long j3;
        long j4;
        synchronized (this) {
            j2 = this.i;
            this.i = 0L;
        }
        Boolean bool = this.mIsDataAvailable;
        int i2 = ((j2 & 3) > 0L ? 1 : ((j2 & 3) == 0L ? 0 : -1));
        if (i2 != 0) {
            boolean safeUnbox = ViewDataBinding.safeUnbox(bool);
            if (i2 != 0) {
                if (safeUnbox) {
                    j3 = j2 | 8;
                    j4 = 32;
                } else {
                    j3 = j2 | 4;
                    j4 = 16;
                }
                j2 = j3 | j4;
            }
            int i3 = safeUnbox ? 0 : 4;
            i = safeUnbox ? 4 : 0;
            r8 = i3;
        } else {
            i = 0;
        }
        if ((j2 & 3) != 0) {
            this.clVitalImage.setVisibility(r8);
            this.ivIcon.setVisibility(i);
            this.tvHeader.setVisibility(i);
            this.tvInfo.setVisibility(i);
            this.tvVitalLastUpdatedTime.setVisibility(r8);
            this.tvVitalName.setVisibility(r8);
            this.tvVitalValueUnit.setVisibility(r8);
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

    @Override // com.coveiot.android.theme.databinding.ItemListFitnessVitalsLayoutBinding
    public void setIsDataAvailable(@Nullable Boolean bool) {
        this.mIsDataAvailable = bool;
        synchronized (this) {
            this.i |= 1;
        }
        notifyPropertyChanged(BR.isDataAvailable);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.isDataAvailable == i) {
            setIsDataAvailable((Boolean) obj);
            return true;
        }
        return false;
    }

    public ItemListFitnessVitalsLayoutBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[11], (ConstraintLayout) objArr[5], (ImageView) objArr[1], (ImageView) objArr[8], (SpeedView) objArr[9], (TextView) objArr[2], (TextView) objArr[3], (TextView) objArr[14], (TextView) objArr[7], (TextView) objArr[13], (TextView) objArr[4], (TextView) objArr[10], (TextView) objArr[6], (View) objArr[12]);
        this.i = -1L;
        this.clVitalImage.setTag(null);
        this.ivIcon.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        this.tvHeader.setTag(null);
        this.tvInfo.setTag(null);
        this.tvVitalLastUpdatedTime.setTag(null);
        this.tvVitalName.setTag(null);
        this.tvVitalValueUnit.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}

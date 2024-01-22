package com.coveiot.android.boat.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.sensai.model.SensAICompareListItem;
/* loaded from: classes3.dex */
public class ItemSensAiCompareListBindingImpl extends ItemSensAiCompareListBinding {
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
        sparseIntArray.put(R.id.guideline1, 2);
        sparseIntArray.put(R.id.guideline2, 3);
        sparseIntArray.put(R.id.guideline3, 4);
        sparseIntArray.put(R.id.guideline4, 5);
        sparseIntArray.put(R.id.iv_compare_image, 6);
        sparseIntArray.put(R.id.tv_compare_value1, 7);
        sparseIntArray.put(R.id.tv_compare_value2, 8);
    }

    public ItemSensAiCompareListBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 9, j, k));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j2;
        synchronized (this) {
            j2 = this.i;
            this.i = 0L;
        }
        SensAICompareListItem sensAICompareListItem = this.mCompareData;
        String str = null;
        int i = ((j2 & 3) > 0L ? 1 : ((j2 & 3) == 0L ? 0 : -1));
        if (i != 0 && sensAICompareListItem != null) {
            str = sensAICompareListItem.getName();
        }
        if (i != 0) {
            TextViewBindingAdapter.setText(this.tvCompareTitle, str);
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

    @Override // com.coveiot.android.boat.databinding.ItemSensAiCompareListBinding
    public void setCompareData(@Nullable SensAICompareListItem sensAICompareListItem) {
        this.mCompareData = sensAICompareListItem;
        synchronized (this) {
            this.i |= 1;
        }
        notifyPropertyChanged(17);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (17 == i) {
            setCompareData((SensAICompareListItem) obj);
            return true;
        }
        return false;
    }

    public ItemSensAiCompareListBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (Guideline) objArr[2], (Guideline) objArr[3], (Guideline) objArr[4], (Guideline) objArr[5], (ImageView) objArr[6], (TextView) objArr[1], (TextView) objArr[7], (TextView) objArr[8]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        this.tvCompareTitle.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}

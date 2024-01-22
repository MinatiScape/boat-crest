package com.coveiot.android.theme.databinding;

import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ImageViewBindingAdapter;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.coveiot.android.theme.BR;
import com.coveiot.android.theme.R;
import com.coveiot.android.theme.model.BindingDataModel1;
/* loaded from: classes7.dex */
public class SmallRoundedCardIconHeaderStatusBindingImpl extends SmallRoundedCardIconHeaderStatusBinding {
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
        sparseIntArray.put(R.id.clMain, 4);
    }

    public SmallRoundedCardIconHeaderStatusBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 5, j, k));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j2;
        String str;
        String str2;
        synchronized (this) {
            j2 = this.i;
            this.i = 0L;
        }
        BindingDataModel1 bindingDataModel1 = this.mBindingDataModel1;
        int i = ((j2 & 3) > 0L ? 1 : ((j2 & 3) == 0L ? 0 : -1));
        Drawable drawable = null;
        if (i == 0 || bindingDataModel1 == null) {
            str = null;
            str2 = null;
        } else {
            String subTitle = bindingDataModel1.getSubTitle();
            str = bindingDataModel1.getTitle();
            str2 = subTitle;
            drawable = bindingDataModel1.getDrawable();
        }
        if (i != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.ivIcon, drawable);
            TextViewBindingAdapter.setText(this.tvHeader, str);
            TextViewBindingAdapter.setText(this.tvStatus, str2);
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

    @Override // com.coveiot.android.theme.databinding.SmallRoundedCardIconHeaderStatusBinding
    public void setBindingDataModel1(@Nullable BindingDataModel1 bindingDataModel1) {
        this.mBindingDataModel1 = bindingDataModel1;
        synchronized (this) {
            this.i |= 1;
        }
        notifyPropertyChanged(BR.bindingDataModel1);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.bindingDataModel1 == i) {
            setBindingDataModel1((BindingDataModel1) obj);
            return true;
        }
        return false;
    }

    public SmallRoundedCardIconHeaderStatusBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[4], (ImageView) objArr[1], (TextView) objArr[2], (TextView) objArr[3]);
        this.i = -1L;
        this.ivIcon.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        this.tvHeader.setTag(null);
        this.tvStatus.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}

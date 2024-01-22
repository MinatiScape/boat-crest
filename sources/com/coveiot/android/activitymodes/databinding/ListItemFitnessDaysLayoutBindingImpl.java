package com.coveiot.android.activitymodes.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.coveiot.android.activitymodes.BR;
import com.coveiot.android.activitymodes.R;
/* loaded from: classes2.dex */
public class ListItemFitnessDaysLayoutBindingImpl extends ListItemFitnessDaysLayoutBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i = null;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.tvDay, 2);
    }

    public ListItemFitnessDaysLayoutBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 3, i, j));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j2;
        synchronized (this) {
            j2 = this.h;
            this.h = 0L;
        }
        String str = this.mDayCount;
        if ((j2 & 3) != 0) {
            TextViewBindingAdapter.setText(this.tvDayCount, str);
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
            this.h = 2L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i2, Object obj, int i3) {
        return false;
    }

    @Override // com.coveiot.android.activitymodes.databinding.ListItemFitnessDaysLayoutBinding
    public void setDayCount(@Nullable String str) {
        this.mDayCount = str;
        synchronized (this) {
            this.h |= 1;
        }
        notifyPropertyChanged(BR.dayCount);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i2, @Nullable Object obj) {
        if (BR.dayCount == i2) {
            setDayCount((String) obj);
            return true;
        }
        return false;
    }

    public ListItemFitnessDaysLayoutBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[0], (TextView) objArr[2], (TextView) objArr[1]);
        this.h = -1L;
        this.clDay.setTag(null);
        this.tvDayCount.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}

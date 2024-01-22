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
/* loaded from: classes2.dex */
public class ListItemFitnessWeekLayoutBindingImpl extends ListItemFitnessWeekLayoutBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts j = null;
    @Nullable
    public static final SparseIntArray k = null;
    @NonNull
    public final ConstraintLayout h;
    public long i;

    public ListItemFitnessWeekLayoutBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 2, j, k));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j2;
        synchronized (this) {
            j2 = this.i;
            this.i = 0L;
        }
        String str = this.mWeekCount;
        if ((j2 & 3) != 0) {
            TextViewBindingAdapter.setText(this.tvWeek, str);
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
        if (BR.weekCount == i) {
            setWeekCount((String) obj);
            return true;
        }
        return false;
    }

    @Override // com.coveiot.android.activitymodes.databinding.ListItemFitnessWeekLayoutBinding
    public void setWeekCount(@Nullable String str) {
        this.mWeekCount = str;
        synchronized (this) {
            this.i |= 1;
        }
        notifyPropertyChanged(BR.weekCount);
        super.requestRebind();
    }

    public ListItemFitnessWeekLayoutBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (TextView) objArr[1]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        this.tvWeek.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}

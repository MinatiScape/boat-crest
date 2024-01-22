package com.coveiot.android.watchfaceui.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.watchfaceui.R;
/* loaded from: classes8.dex */
public class FragmentMyDesignsBindingImpl extends FragmentMyDesignsBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i = null;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.tvToolTipFragment, 1);
        sparseIntArray.put(R.id.topLayout, 2);
        sparseIntArray.put(R.id.infoLayout, 3);
        sparseIntArray.put(R.id.toolTipInfo, 4);
        sparseIntArray.put(R.id.deleteLayout, 5);
        sparseIntArray.put(R.id.tvDelete, 6);
        sparseIntArray.put(R.id.tvSelectAll, 7);
        sparseIntArray.put(R.id.close, 8);
        sparseIntArray.put(R.id.watchFace_recycler, 9);
        sparseIntArray.put(R.id.noWatchFace, 10);
        sparseIntArray.put(R.id.watchFaceStudio, 11);
    }

    public FragmentMyDesignsBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 12, i, j));
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

    public FragmentMyDesignsBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ImageView) objArr[8], (ConstraintLayout) objArr[5], (ConstraintLayout) objArr[3], (ConstraintLayout) objArr[10], (ConstraintLayout) objArr[0], (ImageView) objArr[4], (ConstraintLayout) objArr[2], (TextView) objArr[6], (TextView) objArr[7], (TextView) objArr[1], (RecyclerView) objArr[9], (ConstraintLayout) objArr[11]);
        this.h = -1L;
        this.rootLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}

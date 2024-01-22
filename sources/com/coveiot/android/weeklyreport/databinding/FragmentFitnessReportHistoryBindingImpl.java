package com.coveiot.android.weeklyreport.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.weeklyreport.R;
import com.github.barteksc.pdfviewer.PDFView;
/* loaded from: classes8.dex */
public class FragmentFitnessReportHistoryBindingImpl extends FragmentFitnessReportHistoryBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts i = null;
    @Nullable
    public static final SparseIntArray j;
    public long h;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        j = sparseIntArray;
        sparseIntArray.put(R.id.pdfView, 1);
        sparseIntArray.put(R.id.guideline, 2);
        sparseIntArray.put(R.id.clShare, 3);
        sparseIntArray.put(R.id.tvShare, 4);
        sparseIntArray.put(R.id.clDownload, 5);
        sparseIntArray.put(R.id.tvDownload, 6);
    }

    public FragmentFitnessReportHistoryBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 7, i, j));
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

    public FragmentFitnessReportHistoryBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[5], (ConstraintLayout) objArr[0], (ConstraintLayout) objArr[3], (Guideline) objArr[2], (PDFView) objArr[1], (TextView) objArr[6], (TextView) objArr[4]);
        this.h = -1L;
        this.clRoot.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}

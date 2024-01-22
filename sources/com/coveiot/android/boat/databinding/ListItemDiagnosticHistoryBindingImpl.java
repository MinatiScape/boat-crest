package com.coveiot.android.boat.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.model.DiagnosticTestHistoryModel;
/* loaded from: classes3.dex */
public class ListItemDiagnosticHistoryBindingImpl extends ListItemDiagnosticHistoryBinding {
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
        sparseIntArray.put(R.id.ibDownloadDiagnostic, 3);
        sparseIntArray.put(R.id.view8, 4);
    }

    public ListItemDiagnosticHistoryBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 5, j, k));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j2;
        String str;
        synchronized (this) {
            j2 = this.i;
            this.i = 0L;
        }
        DiagnosticTestHistoryModel diagnosticTestHistoryModel = this.mDiagnosticTestHistoryData;
        int i = ((j2 & 3) > 0L ? 1 : ((j2 & 3) == 0L ? 0 : -1));
        String str2 = null;
        if (i == 0 || diagnosticTestHistoryModel == null) {
            str = null;
        } else {
            str = diagnosticTestHistoryModel.getTestTime();
            str2 = diagnosticTestHistoryModel.getTestDate();
        }
        if (i != 0) {
            TextViewBindingAdapter.setText(this.tvDiagnosticDate, str2);
            TextViewBindingAdapter.setText(this.tvDiagnosticTime, str);
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

    @Override // com.coveiot.android.boat.databinding.ListItemDiagnosticHistoryBinding
    public void setDiagnosticTestHistoryData(@Nullable DiagnosticTestHistoryModel diagnosticTestHistoryModel) {
        this.mDiagnosticTestHistoryData = diagnosticTestHistoryModel;
        synchronized (this) {
            this.i |= 1;
        }
        notifyPropertyChanged(30);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (30 == i) {
            setDiagnosticTestHistoryData((DiagnosticTestHistoryModel) obj);
            return true;
        }
        return false;
    }

    public ListItemDiagnosticHistoryBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ImageButton) objArr[3], (TextView) objArr[1], (TextView) objArr[2], (View) objArr[4]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        this.tvDiagnosticDate.setTag(null);
        this.tvDiagnosticTime.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}

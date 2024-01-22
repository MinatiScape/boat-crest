package com.coveiot.android.boat.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.model.DiagnosticTestModel;
import com.coveiot.android.theme.compundview.DottedCircleProgressBarCustom;
/* loaded from: classes3.dex */
public class ListItemDiagnosticTestingBindingImpl extends ListItemDiagnosticTestingBinding {
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
        sparseIntArray.put(R.id.cvVibrationTest, 2);
        sparseIntArray.put(R.id.dottedProgressbar, 3);
    }

    public ListItemDiagnosticTestingBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 4, j, k));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j2;
        synchronized (this) {
            j2 = this.i;
            this.i = 0L;
        }
        DiagnosticTestModel diagnosticTestModel = this.mDiagnosticData;
        String str = null;
        int i = ((j2 & 3) > 0L ? 1 : ((j2 & 3) == 0L ? 0 : -1));
        if (i != 0 && diagnosticTestModel != null) {
            str = diagnosticTestModel.getTestName();
        }
        if (i != 0) {
            TextViewBindingAdapter.setText(this.tvDiagnosticTest, str);
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

    @Override // com.coveiot.android.boat.databinding.ListItemDiagnosticTestingBinding
    public void setDiagnosticData(@Nullable DiagnosticTestModel diagnosticTestModel) {
        this.mDiagnosticData = diagnosticTestModel;
        synchronized (this) {
            this.i |= 1;
        }
        notifyPropertyChanged(29);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (29 == i) {
            setDiagnosticData((DiagnosticTestModel) obj);
            return true;
        }
        return false;
    }

    public ListItemDiagnosticTestingBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (CardView) objArr[2], (DottedCircleProgressBarCustom) objArr[3], (TextView) objArr[1]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        this.tvDiagnosticTest.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}

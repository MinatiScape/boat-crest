package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.model.DiagnosticTestModel;
import com.coveiot.android.theme.compundview.DottedCircleProgressBarCustom;
/* loaded from: classes3.dex */
public abstract class ListItemDiagnosticTestingBinding extends ViewDataBinding {
    @NonNull
    public final CardView cvVibrationTest;
    @NonNull
    public final DottedCircleProgressBarCustom dottedProgressbar;
    @Bindable
    public DiagnosticTestModel mDiagnosticData;
    @NonNull
    public final TextView tvDiagnosticTest;

    public ListItemDiagnosticTestingBinding(Object obj, View view, int i, CardView cardView, DottedCircleProgressBarCustom dottedCircleProgressBarCustom, TextView textView) {
        super(obj, view, i);
        this.cvVibrationTest = cardView;
        this.dottedProgressbar = dottedCircleProgressBarCustom;
        this.tvDiagnosticTest = textView;
    }

    public static ListItemDiagnosticTestingBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ListItemDiagnosticTestingBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public DiagnosticTestModel getDiagnosticData() {
        return this.mDiagnosticData;
    }

    public abstract void setDiagnosticData(@Nullable DiagnosticTestModel diagnosticTestModel);

    @Deprecated
    public static ListItemDiagnosticTestingBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ListItemDiagnosticTestingBinding) ViewDataBinding.bind(obj, view, R.layout.list_item_diagnostic_testing);
    }

    @NonNull
    @Deprecated
    public static ListItemDiagnosticTestingBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ListItemDiagnosticTestingBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_diagnostic_testing, viewGroup, z, obj);
    }

    @NonNull
    public static ListItemDiagnosticTestingBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ListItemDiagnosticTestingBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ListItemDiagnosticTestingBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_diagnostic_testing, null, false, obj);
    }
}

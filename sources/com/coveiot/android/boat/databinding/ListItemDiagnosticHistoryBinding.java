package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.model.DiagnosticTestHistoryModel;
/* loaded from: classes3.dex */
public abstract class ListItemDiagnosticHistoryBinding extends ViewDataBinding {
    @NonNull
    public final ImageButton ibDownloadDiagnostic;
    @Bindable
    public DiagnosticTestHistoryModel mDiagnosticTestHistoryData;
    @NonNull
    public final TextView tvDiagnosticDate;
    @NonNull
    public final TextView tvDiagnosticTime;
    @NonNull
    public final View view8;

    public ListItemDiagnosticHistoryBinding(Object obj, View view, int i, ImageButton imageButton, TextView textView, TextView textView2, View view2) {
        super(obj, view, i);
        this.ibDownloadDiagnostic = imageButton;
        this.tvDiagnosticDate = textView;
        this.tvDiagnosticTime = textView2;
        this.view8 = view2;
    }

    public static ListItemDiagnosticHistoryBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ListItemDiagnosticHistoryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public DiagnosticTestHistoryModel getDiagnosticTestHistoryData() {
        return this.mDiagnosticTestHistoryData;
    }

    public abstract void setDiagnosticTestHistoryData(@Nullable DiagnosticTestHistoryModel diagnosticTestHistoryModel);

    @Deprecated
    public static ListItemDiagnosticHistoryBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ListItemDiagnosticHistoryBinding) ViewDataBinding.bind(obj, view, R.layout.list_item_diagnostic_history);
    }

    @NonNull
    @Deprecated
    public static ListItemDiagnosticHistoryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ListItemDiagnosticHistoryBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_diagnostic_history, viewGroup, z, obj);
    }

    @NonNull
    public static ListItemDiagnosticHistoryBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ListItemDiagnosticHistoryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ListItemDiagnosticHistoryBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_diagnostic_history, null, false, obj);
    }
}

package com.coveiot.android.weeklyreport.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.weeklyreport.R;
import com.github.barteksc.pdfviewer.PDFView;
/* loaded from: classes8.dex */
public abstract class FragmentFitnessReportHistoryBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clDownload;
    @NonNull
    public final ConstraintLayout clRoot;
    @NonNull
    public final ConstraintLayout clShare;
    @NonNull
    public final Guideline guideline;
    @NonNull
    public final PDFView pdfView;
    @NonNull
    public final TextView tvDownload;
    @NonNull
    public final TextView tvShare;

    public FragmentFitnessReportHistoryBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, Guideline guideline, PDFView pDFView, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.clDownload = constraintLayout;
        this.clRoot = constraintLayout2;
        this.clShare = constraintLayout3;
        this.guideline = guideline;
        this.pdfView = pDFView;
        this.tvDownload = textView;
        this.tvShare = textView2;
    }

    public static FragmentFitnessReportHistoryBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentFitnessReportHistoryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentFitnessReportHistoryBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentFitnessReportHistoryBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_fitness_report_history);
    }

    @NonNull
    @Deprecated
    public static FragmentFitnessReportHistoryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentFitnessReportHistoryBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_fitness_report_history, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentFitnessReportHistoryBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentFitnessReportHistoryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentFitnessReportHistoryBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_fitness_report_history, null, false, obj);
    }
}

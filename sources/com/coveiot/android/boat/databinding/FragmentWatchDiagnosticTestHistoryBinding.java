package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class FragmentWatchDiagnosticTestHistoryBinding extends ViewDataBinding {
    @NonNull
    public final TextView bottomText;
    @NonNull
    public final RecyclerView rvDiagnosticHistory;

    public FragmentWatchDiagnosticTestHistoryBinding(Object obj, View view, int i, TextView textView, RecyclerView recyclerView) {
        super(obj, view, i);
        this.bottomText = textView;
        this.rvDiagnosticHistory = recyclerView;
    }

    public static FragmentWatchDiagnosticTestHistoryBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentWatchDiagnosticTestHistoryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWatchDiagnosticTestHistoryBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentWatchDiagnosticTestHistoryBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_watch_diagnostic_test_history);
    }

    @NonNull
    @Deprecated
    public static FragmentWatchDiagnosticTestHistoryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentWatchDiagnosticTestHistoryBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_watch_diagnostic_test_history, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentWatchDiagnosticTestHistoryBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentWatchDiagnosticTestHistoryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentWatchDiagnosticTestHistoryBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_watch_diagnostic_test_history, null, false, obj);
    }
}

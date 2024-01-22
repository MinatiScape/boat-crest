package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class ActivityCustomise4hButtonBinding extends ViewDataBinding {
    @NonNull
    public final Button btnSave;
    @NonNull
    public final RecyclerView rvLongpress;
    @NonNull
    public final RecyclerView rvShortpress;
    @NonNull
    public final TextView toolText;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvLongpressItemsLabel;
    @NonNull
    public final TextView tvShortpressItemsLabel;

    public ActivityCustomise4hButtonBinding(Object obj, View view, int i, Button button, RecyclerView recyclerView, RecyclerView recyclerView2, TextView textView, View view2, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.btnSave = button;
        this.rvLongpress = recyclerView;
        this.rvShortpress = recyclerView2;
        this.toolText = textView;
        this.toolbar = view2;
        this.tvLongpressItemsLabel = textView2;
        this.tvShortpressItemsLabel = textView3;
    }

    public static ActivityCustomise4hButtonBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityCustomise4hButtonBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityCustomise4hButtonBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityCustomise4hButtonBinding) ViewDataBinding.bind(obj, view, R.layout.activity_customise4h_button);
    }

    @NonNull
    @Deprecated
    public static ActivityCustomise4hButtonBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityCustomise4hButtonBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_customise4h_button, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityCustomise4hButtonBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityCustomise4hButtonBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityCustomise4hButtonBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_customise4h_button, null, false, obj);
    }
}

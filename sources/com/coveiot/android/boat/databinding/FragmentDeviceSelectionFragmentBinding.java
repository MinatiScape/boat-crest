package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.google.android.material.tabs.TabLayout;
/* loaded from: classes3.dex */
public abstract class FragmentDeviceSelectionFragmentBinding extends ViewDataBinding {
    @NonNull
    public final Button btnConfirm;
    @NonNull
    public final EditText etSearchDevice;
    @NonNull
    public final RecyclerView rvDevices;
    @NonNull
    public final TabLayout tlDeviceType;
    @NonNull
    public final TextView tvHeader;
    @NonNull
    public final TextView tvNoDataFound;

    public FragmentDeviceSelectionFragmentBinding(Object obj, View view, int i, Button button, EditText editText, RecyclerView recyclerView, TabLayout tabLayout, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.btnConfirm = button;
        this.etSearchDevice = editText;
        this.rvDevices = recyclerView;
        this.tlDeviceType = tabLayout;
        this.tvHeader = textView;
        this.tvNoDataFound = textView2;
    }

    public static FragmentDeviceSelectionFragmentBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentDeviceSelectionFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDeviceSelectionFragmentBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentDeviceSelectionFragmentBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_device_selection_fragment);
    }

    @NonNull
    @Deprecated
    public static FragmentDeviceSelectionFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentDeviceSelectionFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_device_selection_fragment, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentDeviceSelectionFragmentBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentDeviceSelectionFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentDeviceSelectionFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_device_selection_fragment, null, false, obj);
    }
}

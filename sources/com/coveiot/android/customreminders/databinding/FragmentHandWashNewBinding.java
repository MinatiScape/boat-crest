package com.coveiot.android.customreminders.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.customreminders.R;
import com.coveiot.android.theme.databinding.ReminderLayoutNewBinding;
import com.coveiot.android.theme.databinding.RepeatLayoutNewBinding;
/* loaded from: classes3.dex */
public abstract class FragmentHandWashNewBinding extends ViewDataBinding {
    @NonNull
    public final ReminderLayoutNewBinding reminderLayout;
    @NonNull
    public final RepeatLayoutNewBinding repeatLayout;
    @NonNull
    public final Button saveButton;

    public FragmentHandWashNewBinding(Object obj, View view, int i, ReminderLayoutNewBinding reminderLayoutNewBinding, RepeatLayoutNewBinding repeatLayoutNewBinding, Button button) {
        super(obj, view, i);
        this.reminderLayout = reminderLayoutNewBinding;
        this.repeatLayout = repeatLayoutNewBinding;
        this.saveButton = button;
    }

    public static FragmentHandWashNewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentHandWashNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHandWashNewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentHandWashNewBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_hand_wash_new);
    }

    @NonNull
    @Deprecated
    public static FragmentHandWashNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentHandWashNewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_hand_wash_new, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentHandWashNewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentHandWashNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentHandWashNewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_hand_wash_new, null, false, obj);
    }
}

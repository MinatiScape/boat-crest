package com.coveiot.android.fitnesschallenges.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.fitnesschallenges.R;
/* loaded from: classes2.dex */
public abstract class FitnessDetailsEditDialogBinding extends ViewDataBinding {
    @NonNull
    public final TextView tvDelete;
    @NonNull
    public final TextView tvEdit;
    @NonNull
    public final View view1;

    public FitnessDetailsEditDialogBinding(Object obj, View view, int i, TextView textView, TextView textView2, View view2) {
        super(obj, view, i);
        this.tvDelete = textView;
        this.tvEdit = textView2;
        this.view1 = view2;
    }

    public static FitnessDetailsEditDialogBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FitnessDetailsEditDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FitnessDetailsEditDialogBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FitnessDetailsEditDialogBinding) ViewDataBinding.bind(obj, view, R.layout.fitness_details_edit_dialog);
    }

    @NonNull
    @Deprecated
    public static FitnessDetailsEditDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FitnessDetailsEditDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fitness_details_edit_dialog, viewGroup, z, obj);
    }

    @NonNull
    public static FitnessDetailsEditDialogBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FitnessDetailsEditDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FitnessDetailsEditDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fitness_details_edit_dialog, null, false, obj);
    }
}

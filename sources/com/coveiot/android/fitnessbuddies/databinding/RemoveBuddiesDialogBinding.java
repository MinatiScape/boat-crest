package com.coveiot.android.fitnessbuddies.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.fitnessbuddies.R;
/* loaded from: classes4.dex */
public abstract class RemoveBuddiesDialogBinding extends ViewDataBinding {
    @NonNull
    public final Button btnCancel;
    @NonNull
    public final Button btnRemove;
    @NonNull
    public final ConstraintLayout clRemoveBuddy;
    @NonNull
    public final TextView tvRemoveBuddy;
    @NonNull
    public final TextView tvRemoveBuddyMsg;

    public RemoveBuddiesDialogBinding(Object obj, View view, int i, Button button, Button button2, ConstraintLayout constraintLayout, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.btnCancel = button;
        this.btnRemove = button2;
        this.clRemoveBuddy = constraintLayout;
        this.tvRemoveBuddy = textView;
        this.tvRemoveBuddyMsg = textView2;
    }

    public static RemoveBuddiesDialogBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static RemoveBuddiesDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RemoveBuddiesDialogBinding bind(@NonNull View view, @Nullable Object obj) {
        return (RemoveBuddiesDialogBinding) ViewDataBinding.bind(obj, view, R.layout.remove_buddies_dialog);
    }

    @NonNull
    @Deprecated
    public static RemoveBuddiesDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (RemoveBuddiesDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.remove_buddies_dialog, viewGroup, z, obj);
    }

    @NonNull
    public static RemoveBuddiesDialogBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static RemoveBuddiesDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (RemoveBuddiesDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.remove_buddies_dialog, null, false, obj);
    }
}

package com.coveiot.android.sos.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.sos.R;
/* loaded from: classes7.dex */
public abstract class SosContactItemSelectBinding extends ViewDataBinding {
    @NonNull
    public final Barrier barrier;
    @NonNull
    public final ConstraintLayout clEnd;
    @NonNull
    public final TextView displayName;
    @NonNull
    public final TextView displayNumber;
    @NonNull
    public final ImageView displayPic;
    @NonNull
    public final ImageView ivContactSelect;
    @NonNull
    public final RelativeLayout rootLayoutGeneric;
    @NonNull
    public final TextView tvSOS;
    @NonNull
    public final TextView tvSynced;

    public SosContactItemSelectBinding(Object obj, View view, int i, Barrier barrier, ConstraintLayout constraintLayout, TextView textView, TextView textView2, ImageView imageView, ImageView imageView2, RelativeLayout relativeLayout, TextView textView3, TextView textView4) {
        super(obj, view, i);
        this.barrier = barrier;
        this.clEnd = constraintLayout;
        this.displayName = textView;
        this.displayNumber = textView2;
        this.displayPic = imageView;
        this.ivContactSelect = imageView2;
        this.rootLayoutGeneric = relativeLayout;
        this.tvSOS = textView3;
        this.tvSynced = textView4;
    }

    public static SosContactItemSelectBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SosContactItemSelectBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SosContactItemSelectBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SosContactItemSelectBinding) ViewDataBinding.bind(obj, view, R.layout.sos_contact_item_select);
    }

    @NonNull
    @Deprecated
    public static SosContactItemSelectBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SosContactItemSelectBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.sos_contact_item_select, viewGroup, z, obj);
    }

    @NonNull
    public static SosContactItemSelectBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SosContactItemSelectBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SosContactItemSelectBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.sos_contact_item_select, null, false, obj);
    }
}

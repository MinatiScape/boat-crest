package com.coveiot.android.fitnessbuddies.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.fitnessbuddies.R;
/* loaded from: classes4.dex */
public abstract class ContactItemSelectBinding extends ViewDataBinding {
    @NonNull
    public final ImageView buddiesDisplayPic;
    @NonNull
    public final ImageView buddiesIcon;
    @NonNull
    public final TextView buddiesText;
    @NonNull
    public final ConstraintLayout clEnd;
    @NonNull
    public final TextView displayName;
    @NonNull
    public final TextView displayNumber;
    @NonNull
    public final RelativeLayout rootLayoutGeneric;
    @NonNull
    public final TextView tvBuddiesInvite;

    public ContactItemSelectBinding(Object obj, View view, int i, ImageView imageView, ImageView imageView2, TextView textView, ConstraintLayout constraintLayout, TextView textView2, TextView textView3, RelativeLayout relativeLayout, TextView textView4) {
        super(obj, view, i);
        this.buddiesDisplayPic = imageView;
        this.buddiesIcon = imageView2;
        this.buddiesText = textView;
        this.clEnd = constraintLayout;
        this.displayName = textView2;
        this.displayNumber = textView3;
        this.rootLayoutGeneric = relativeLayout;
        this.tvBuddiesInvite = textView4;
    }

    public static ContactItemSelectBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ContactItemSelectBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ContactItemSelectBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ContactItemSelectBinding) ViewDataBinding.bind(obj, view, R.layout.contact_item_select);
    }

    @NonNull
    @Deprecated
    public static ContactItemSelectBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ContactItemSelectBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.contact_item_select, viewGroup, z, obj);
    }

    @NonNull
    public static ContactItemSelectBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ContactItemSelectBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ContactItemSelectBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.contact_item_select, null, false, obj);
    }
}

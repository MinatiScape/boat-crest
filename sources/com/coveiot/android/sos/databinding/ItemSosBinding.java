package com.coveiot.android.sos.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.sos.R;
import com.coveiot.android.theme.databinding.GenericDialogWithTwoOptionsBinding;
/* loaded from: classes7.dex */
public abstract class ItemSosBinding extends ViewDataBinding {
    @NonNull
    public final TextView displayName;
    @NonNull
    public final TextView displayNumber;
    @NonNull
    public final ImageView displayPic;
    @NonNull
    public final ImageView editContact;
    @NonNull
    public final GenericDialogWithTwoOptionsBinding editSos;

    public ItemSosBinding(Object obj, View view, int i, TextView textView, TextView textView2, ImageView imageView, ImageView imageView2, GenericDialogWithTwoOptionsBinding genericDialogWithTwoOptionsBinding) {
        super(obj, view, i);
        this.displayName = textView;
        this.displayNumber = textView2;
        this.displayPic = imageView;
        this.editContact = imageView2;
        this.editSos = genericDialogWithTwoOptionsBinding;
    }

    public static ItemSosBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ItemSosBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSosBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ItemSosBinding) ViewDataBinding.bind(obj, view, R.layout.item_sos);
    }

    @NonNull
    @Deprecated
    public static ItemSosBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ItemSosBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_sos, viewGroup, z, obj);
    }

    @NonNull
    public static ItemSosBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ItemSosBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ItemSosBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_sos, null, false, obj);
    }
}

package com.coveiot.android.remotecommandframework.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.remotecommandframework.R;
/* loaded from: classes6.dex */
public abstract class ListItemAlexaUttranceBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout cvSetAlarmAt;
    @NonNull
    public final TextView tvUtterance;

    public ListItemAlexaUttranceBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, TextView textView) {
        super(obj, view, i);
        this.cvSetAlarmAt = constraintLayout;
        this.tvUtterance = textView;
    }

    public static ListItemAlexaUttranceBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ListItemAlexaUttranceBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ListItemAlexaUttranceBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ListItemAlexaUttranceBinding) ViewDataBinding.bind(obj, view, R.layout.list_item_alexa_uttrance);
    }

    @NonNull
    @Deprecated
    public static ListItemAlexaUttranceBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ListItemAlexaUttranceBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_alexa_uttrance, viewGroup, z, obj);
    }

    @NonNull
    public static ListItemAlexaUttranceBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ListItemAlexaUttranceBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ListItemAlexaUttranceBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_alexa_uttrance, null, false, obj);
    }
}

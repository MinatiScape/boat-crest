package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class Item4hButtonViewBinding extends ViewDataBinding {
    @NonNull
    public final RadioButton rbtnItems;

    public Item4hButtonViewBinding(Object obj, View view, int i, RadioButton radioButton) {
        super(obj, view, i);
        this.rbtnItems = radioButton;
    }

    public static Item4hButtonViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static Item4hButtonViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static Item4hButtonViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (Item4hButtonViewBinding) ViewDataBinding.bind(obj, view, R.layout.item_4h_button_view);
    }

    @NonNull
    @Deprecated
    public static Item4hButtonViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (Item4hButtonViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_4h_button_view, viewGroup, z, obj);
    }

    @NonNull
    public static Item4hButtonViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static Item4hButtonViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (Item4hButtonViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_4h_button_view, null, false, obj);
    }
}

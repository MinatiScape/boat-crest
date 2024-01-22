package com.mappls.sdk.direction.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.mappls.sdk.direction.ui.R;
/* loaded from: classes11.dex */
public abstract class MapplsDirectionItemStopBinding extends ViewDataBinding {
    @NonNull
    public final View connector;
    @NonNull
    public final ImageView imgClear;
    @NonNull
    public final ImageView imgDrag;
    @NonNull
    public final ImageView imgStopType;
    @NonNull
    public final TextView textViewAddStop;
    @NonNull
    public final View view;

    public MapplsDirectionItemStopBinding(Object obj, View view, int i, View view2, ImageView imageView, ImageView imageView2, ImageView imageView3, TextView textView, View view3) {
        super(obj, view, i);
        this.connector = view2;
        this.imgClear = imageView;
        this.imgDrag = imageView2;
        this.imgStopType = imageView3;
        this.textViewAddStop = textView;
        this.view = view3;
    }

    public static MapplsDirectionItemStopBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MapplsDirectionItemStopBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MapplsDirectionItemStopBinding) ViewDataBinding.bind(obj, view, R.layout.mappls_direction_item_stop);
    }

    @NonNull
    public static MapplsDirectionItemStopBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MapplsDirectionItemStopBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionItemStopBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MapplsDirectionItemStopBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_item_stop, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionItemStopBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MapplsDirectionItemStopBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_item_stop, null, false, obj);
    }
}

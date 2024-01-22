package com.coveiot.android.qrtray.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.qrtray.R;
/* loaded from: classes5.dex */
public abstract class FragmentAddedQrTrayBinding extends ViewDataBinding {
    @NonNull
    public final Button btnAddQR;
    @NonNull
    public final ConstraintLayout clQRInfo;
    @NonNull
    public final ImageView ivQRCode;
    @NonNull
    public final RecyclerView rvQRTrayCategories;
    @NonNull
    public final RecyclerView rvQrTray;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvInfo;
    @NonNull
    public final TextView tvNoDataFound;
    @NonNull
    public final TextView tvUploadNew;

    public FragmentAddedQrTrayBinding(Object obj, View view, int i, Button button, ConstraintLayout constraintLayout, ImageView imageView, RecyclerView recyclerView, RecyclerView recyclerView2, View view2, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.btnAddQR = button;
        this.clQRInfo = constraintLayout;
        this.ivQRCode = imageView;
        this.rvQRTrayCategories = recyclerView;
        this.rvQrTray = recyclerView2;
        this.toolbar = view2;
        this.tvInfo = textView;
        this.tvNoDataFound = textView2;
        this.tvUploadNew = textView3;
    }

    public static FragmentAddedQrTrayBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentAddedQrTrayBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentAddedQrTrayBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentAddedQrTrayBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_added_qr_tray);
    }

    @NonNull
    @Deprecated
    public static FragmentAddedQrTrayBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentAddedQrTrayBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_added_qr_tray, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentAddedQrTrayBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentAddedQrTrayBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentAddedQrTrayBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_added_qr_tray, null, false, obj);
    }
}

package com.coveiot.android.qrtray.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.qrtray.R;
/* loaded from: classes5.dex */
public abstract class FragmentQrTraySavingBinding extends ViewDataBinding {
    @NonNull
    public final Button btnSaveNPush;
    @NonNull
    public final ConstraintLayout clSavingNPush;
    @NonNull
    public final EditText etQRCodeName;
    @NonNull
    public final ImageView ivInner;
    @NonNull
    public final ImageView ivOuter;
    @NonNull
    public final ImageView ivQRCode;
    @Bindable
    public String mQrEditImage;
    @NonNull
    public final NestedScrollView nestedScrollView;
    @NonNull
    public final RecyclerView rvQRTrayCategories;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvName;
    @NonNull
    public final TextView tvNameLength;
    @NonNull
    public final TextView tvSave;
    @NonNull
    public final TextView tvSaving;
    @NonNull
    public final TextView tvUploadNew;

    public FragmentQrTraySavingBinding(Object obj, View view, int i, Button button, ConstraintLayout constraintLayout, EditText editText, ImageView imageView, ImageView imageView2, ImageView imageView3, NestedScrollView nestedScrollView, RecyclerView recyclerView, View view2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5) {
        super(obj, view, i);
        this.btnSaveNPush = button;
        this.clSavingNPush = constraintLayout;
        this.etQRCodeName = editText;
        this.ivInner = imageView;
        this.ivOuter = imageView2;
        this.ivQRCode = imageView3;
        this.nestedScrollView = nestedScrollView;
        this.rvQRTrayCategories = recyclerView;
        this.toolbar = view2;
        this.tvName = textView;
        this.tvNameLength = textView2;
        this.tvSave = textView3;
        this.tvSaving = textView4;
        this.tvUploadNew = textView5;
    }

    public static FragmentQrTraySavingBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentQrTraySavingBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public String getQrEditImage() {
        return this.mQrEditImage;
    }

    public abstract void setQrEditImage(@Nullable String str);

    @Deprecated
    public static FragmentQrTraySavingBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentQrTraySavingBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_qr_tray_saving);
    }

    @NonNull
    @Deprecated
    public static FragmentQrTraySavingBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentQrTraySavingBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_qr_tray_saving, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentQrTraySavingBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentQrTraySavingBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentQrTraySavingBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_qr_tray_saving, null, false, obj);
    }
}

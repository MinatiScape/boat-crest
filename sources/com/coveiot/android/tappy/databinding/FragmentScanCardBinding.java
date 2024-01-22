package com.coveiot.android.tappy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.view.PreviewView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public abstract class FragmentScanCardBinding extends ViewDataBinding {
    @NonNull
    public final Button btnAddManuVally;
    @NonNull
    public final Button btnCapture;
    @NonNull
    public final ConstraintLayout cardScanLayout;
    @NonNull
    public final TextView enterOtpDescriptionTxt;
    @NonNull
    public final ImageView imageViewHolder1;
    @NonNull
    public final TextView noteTxt;
    @NonNull
    public final TextView orTxt;
    @NonNull
    public final PreviewView previewCamera1;
    @NonNull
    public final ImageView scnPgBar;
    @NonNull
    public final TextView tvSupportedIssuers;
    @NonNull
    public final View view1;

    public FragmentScanCardBinding(Object obj, View view, int i, Button button, Button button2, ConstraintLayout constraintLayout, TextView textView, ImageView imageView, TextView textView2, TextView textView3, PreviewView previewView, ImageView imageView2, TextView textView4, View view2) {
        super(obj, view, i);
        this.btnAddManuVally = button;
        this.btnCapture = button2;
        this.cardScanLayout = constraintLayout;
        this.enterOtpDescriptionTxt = textView;
        this.imageViewHolder1 = imageView;
        this.noteTxt = textView2;
        this.orTxt = textView3;
        this.previewCamera1 = previewView;
        this.scnPgBar = imageView2;
        this.tvSupportedIssuers = textView4;
        this.view1 = view2;
    }

    public static FragmentScanCardBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentScanCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentScanCardBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentScanCardBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_scan_card);
    }

    @NonNull
    @Deprecated
    public static FragmentScanCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentScanCardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_scan_card, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentScanCardBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentScanCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentScanCardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_scan_card, null, false, obj);
    }
}

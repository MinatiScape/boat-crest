package com.coveiot.android.tappy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public abstract class FriendlyNamePopupBinding extends ViewDataBinding {
    @NonNull
    public final Button btnSave;
    @NonNull
    public final EditText edittextStrapName;
    @NonNull
    public final ImageView ivClose;
    @NonNull
    public final ImageView ivExpandEdTv;
    @NonNull
    public final TextView tvFriendlyNameCount;
    @NonNull
    public final TextView tvGiveName;
    @NonNull
    public final TextView tvStrapInfo;
    @NonNull
    public final TextView tvStrapName;

    public FriendlyNamePopupBinding(Object obj, View view, int i, Button button, EditText editText, ImageView imageView, ImageView imageView2, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        super(obj, view, i);
        this.btnSave = button;
        this.edittextStrapName = editText;
        this.ivClose = imageView;
        this.ivExpandEdTv = imageView2;
        this.tvFriendlyNameCount = textView;
        this.tvGiveName = textView2;
        this.tvStrapInfo = textView3;
        this.tvStrapName = textView4;
    }

    public static FriendlyNamePopupBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FriendlyNamePopupBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FriendlyNamePopupBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FriendlyNamePopupBinding) ViewDataBinding.bind(obj, view, R.layout.friendly_name_popup);
    }

    @NonNull
    @Deprecated
    public static FriendlyNamePopupBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FriendlyNamePopupBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.friendly_name_popup, viewGroup, z, obj);
    }

    @NonNull
    public static FriendlyNamePopupBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FriendlyNamePopupBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FriendlyNamePopupBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.friendly_name_popup, null, false, obj);
    }
}

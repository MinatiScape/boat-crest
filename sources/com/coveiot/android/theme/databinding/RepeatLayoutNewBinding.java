package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class RepeatLayoutNewBinding extends ViewDataBinding {
    @NonNull
    public final CheckBox cbSelectAll;
    @NonNull
    public final ConstraintLayout clRepeat;
    @NonNull
    public final View divider;
    @NonNull
    public final TextView friday;
    @NonNull
    public final TextView monday;
    @NonNull
    public final TextView saturday;
    @NonNull
    public final TextView sunday;
    @NonNull
    public final SwitchCompat switchRepeat;
    @NonNull
    public final TextView thursday;
    @NonNull
    public final TextView tuesday;
    @NonNull
    public final TextView tvRepeatLabel;
    @NonNull
    public final TextView tvSelectDaysInfo;
    @NonNull
    public final TextView wednesday;

    public RepeatLayoutNewBinding(Object obj, View view, int i, CheckBox checkBox, ConstraintLayout constraintLayout, View view2, TextView textView, TextView textView2, TextView textView3, TextView textView4, SwitchCompat switchCompat, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9) {
        super(obj, view, i);
        this.cbSelectAll = checkBox;
        this.clRepeat = constraintLayout;
        this.divider = view2;
        this.friday = textView;
        this.monday = textView2;
        this.saturday = textView3;
        this.sunday = textView4;
        this.switchRepeat = switchCompat;
        this.thursday = textView5;
        this.tuesday = textView6;
        this.tvRepeatLabel = textView7;
        this.tvSelectDaysInfo = textView8;
        this.wednesday = textView9;
    }

    public static RepeatLayoutNewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static RepeatLayoutNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RepeatLayoutNewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (RepeatLayoutNewBinding) ViewDataBinding.bind(obj, view, R.layout.repeat_layout_new);
    }

    @NonNull
    @Deprecated
    public static RepeatLayoutNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (RepeatLayoutNewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.repeat_layout_new, viewGroup, z, obj);
    }

    @NonNull
    public static RepeatLayoutNewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static RepeatLayoutNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (RepeatLayoutNewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.repeat_layout_new, null, false, obj);
    }
}

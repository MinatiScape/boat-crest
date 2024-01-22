package com.coveiot.android.sos.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.sos.R;
import com.coveiot.android.theme.databinding.TooltipDialogTwoButtonsBinding;
/* loaded from: classes7.dex */
public abstract class ActivitySosBinding extends ViewDataBinding {
    @NonNull
    public final ItemSosBinding itemSos;
    @NonNull
    public final NestedScrollView rankMain;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TooltipDialogTwoButtonsBinding tooltip;
    @NonNull
    public final TextView tvEmergencyContact;
    @NonNull
    public final TextView tvSosDesc;
    @NonNull
    public final TextView tvSosFunctionality;
    @NonNull
    public final TextView tvSosPoint1;
    @NonNull
    public final TextView tvSosPoint2;
    @NonNull
    public final TextView tvSosPoint3;
    @NonNull
    public final TextView tvSosPoint4;
    @NonNull
    public final TextView tvSosPoint5;
    @NonNull
    public final TextView tvSosTermsCondition;
    @NonNull
    public final View view1;

    public ActivitySosBinding(Object obj, View view, int i, ItemSosBinding itemSosBinding, NestedScrollView nestedScrollView, View view2, TooltipDialogTwoButtonsBinding tooltipDialogTwoButtonsBinding, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, View view3) {
        super(obj, view, i);
        this.itemSos = itemSosBinding;
        this.rankMain = nestedScrollView;
        this.toolbar = view2;
        this.tooltip = tooltipDialogTwoButtonsBinding;
        this.tvEmergencyContact = textView;
        this.tvSosDesc = textView2;
        this.tvSosFunctionality = textView3;
        this.tvSosPoint1 = textView4;
        this.tvSosPoint2 = textView5;
        this.tvSosPoint3 = textView6;
        this.tvSosPoint4 = textView7;
        this.tvSosPoint5 = textView8;
        this.tvSosTermsCondition = textView9;
        this.view1 = view3;
    }

    public static ActivitySosBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivitySosBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySosBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivitySosBinding) ViewDataBinding.bind(obj, view, R.layout.activity_sos);
    }

    @NonNull
    @Deprecated
    public static ActivitySosBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivitySosBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_sos, viewGroup, z, obj);
    }

    @NonNull
    public static ActivitySosBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivitySosBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivitySosBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_sos, null, false, obj);
    }
}

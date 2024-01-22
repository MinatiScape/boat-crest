package com.coveiot.android.respiratoryrate.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.respiratoryrate.R;
import com.github.mikephil.charting.charts.LineChart;
/* loaded from: classes6.dex */
public abstract class FragmentRespiratoryRateBinding extends ViewDataBinding {
    @NonNull
    public final ImageView calendarImage;
    @NonNull
    public final FrameLayout frameLay;
    @NonNull
    public final ImageView historyImage;
    @NonNull
    public final ImageView imgRespiratoryRate;
    @NonNull
    public final ImageView imgRespiratoryRateMax;
    @NonNull
    public final ImageView imgRespiratoryRateMin;
    @NonNull
    public final LineChart linechartRespiratoryRate;
    @NonNull
    public final LinearLayout llNotSyncedMsgTv;
    @NonNull
    public final TextView noDataTv;
    @NonNull
    public final TextView noSettingsEnabledTv;
    @NonNull
    public final TextView noSyncMsgTv;
    @NonNull
    public final ImageView shareImage;
    @NonNull
    public final TextView tvDate1;
    @NonNull
    public final TextView tvDate2;
    @NonNull
    public final TextView tvDate3;
    @NonNull
    public final TextView tvDate4;
    @NonNull
    public final TextView tvDate5;
    @NonNull
    public final TextView tvDay;
    @NonNull
    public final TextView tvDisclaimer;
    @NonNull
    public final TextView tvRespiratoryRate;
    @NonNull
    public final TextView tvRespiratoryRateMax;
    @NonNull
    public final TextView tvRespiratoryRateMin;
    @NonNull
    public final View vEmpty;
    @NonNull
    public final View view1;

    public FragmentRespiratoryRateBinding(Object obj, View view, int i, ImageView imageView, FrameLayout frameLayout, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, LineChart lineChart, LinearLayout linearLayout, TextView textView, TextView textView2, TextView textView3, ImageView imageView6, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, View view2, View view3) {
        super(obj, view, i);
        this.calendarImage = imageView;
        this.frameLay = frameLayout;
        this.historyImage = imageView2;
        this.imgRespiratoryRate = imageView3;
        this.imgRespiratoryRateMax = imageView4;
        this.imgRespiratoryRateMin = imageView5;
        this.linechartRespiratoryRate = lineChart;
        this.llNotSyncedMsgTv = linearLayout;
        this.noDataTv = textView;
        this.noSettingsEnabledTv = textView2;
        this.noSyncMsgTv = textView3;
        this.shareImage = imageView6;
        this.tvDate1 = textView4;
        this.tvDate2 = textView5;
        this.tvDate3 = textView6;
        this.tvDate4 = textView7;
        this.tvDate5 = textView8;
        this.tvDay = textView9;
        this.tvDisclaimer = textView10;
        this.tvRespiratoryRate = textView11;
        this.tvRespiratoryRateMax = textView12;
        this.tvRespiratoryRateMin = textView13;
        this.vEmpty = view2;
        this.view1 = view3;
    }

    public static FragmentRespiratoryRateBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentRespiratoryRateBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentRespiratoryRateBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentRespiratoryRateBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_respiratory_rate);
    }

    @NonNull
    @Deprecated
    public static FragmentRespiratoryRateBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentRespiratoryRateBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_respiratory_rate, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentRespiratoryRateBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentRespiratoryRateBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentRespiratoryRateBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_respiratory_rate, null, false, obj);
    }
}

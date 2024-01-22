package com.coveiot.android.respiratoryrate.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.respiratoryrate.R;
import com.github.mikephil.charting.charts.LineChart;
/* loaded from: classes6.dex */
public abstract class FragmentRespiratoryRateHistoryBinding extends ViewDataBinding {
    @NonNull
    public final ImageButton back;
    @NonNull
    public final ConstraintLayout clBottomContent;
    @NonNull
    public final ConstraintLayout clMinMaxView;
    @NonNull
    public final ConstraintLayout clRvView;
    @NonNull
    public final TextView dailyTv;
    @NonNull
    public final TextView dateTv;
    @NonNull
    public final TextView disclaimerInfo;
    @NonNull
    public final RelativeLayout disclaimerInfoLay;
    @NonNull
    public final ImageView imgRespiratoryRateMax;
    @NonNull
    public final ImageView imgRespiratoryRateMin;
    @NonNull
    public final LineChart linechartRespiratoryRate;
    @NonNull
    public final LinearLayout llRvHeader;
    @NonNull
    public final TextView monthlyTv;
    @NonNull
    public final TextView noDataTv;
    @NonNull
    public final RecyclerView rvMinMax;
    @NonNull
    public final ImageView shareIv;
    @NonNull
    public final LinearLayout tablayout;
    @NonNull
    public final TextView title;
    @NonNull
    public final Toolbar toolbar;
    @NonNull
    public final LinearLayout toolbarLayout;
    @NonNull
    public final TextView tvRespiratoryRateMax;
    @NonNull
    public final TextView tvRespiratoryRateMin;
    @NonNull
    public final View vSeparator;
    @NonNull
    public final View view1;
    @NonNull
    public final TextView weeklyTv;

    public FragmentRespiratoryRateHistoryBinding(Object obj, View view, int i, ImageButton imageButton, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, TextView textView, TextView textView2, TextView textView3, RelativeLayout relativeLayout, ImageView imageView, ImageView imageView2, LineChart lineChart, LinearLayout linearLayout, TextView textView4, TextView textView5, RecyclerView recyclerView, ImageView imageView3, LinearLayout linearLayout2, TextView textView6, Toolbar toolbar, LinearLayout linearLayout3, TextView textView7, TextView textView8, View view2, View view3, TextView textView9) {
        super(obj, view, i);
        this.back = imageButton;
        this.clBottomContent = constraintLayout;
        this.clMinMaxView = constraintLayout2;
        this.clRvView = constraintLayout3;
        this.dailyTv = textView;
        this.dateTv = textView2;
        this.disclaimerInfo = textView3;
        this.disclaimerInfoLay = relativeLayout;
        this.imgRespiratoryRateMax = imageView;
        this.imgRespiratoryRateMin = imageView2;
        this.linechartRespiratoryRate = lineChart;
        this.llRvHeader = linearLayout;
        this.monthlyTv = textView4;
        this.noDataTv = textView5;
        this.rvMinMax = recyclerView;
        this.shareIv = imageView3;
        this.tablayout = linearLayout2;
        this.title = textView6;
        this.toolbar = toolbar;
        this.toolbarLayout = linearLayout3;
        this.tvRespiratoryRateMax = textView7;
        this.tvRespiratoryRateMin = textView8;
        this.vSeparator = view2;
        this.view1 = view3;
        this.weeklyTv = textView9;
    }

    public static FragmentRespiratoryRateHistoryBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentRespiratoryRateHistoryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentRespiratoryRateHistoryBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentRespiratoryRateHistoryBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_respiratory_rate_history);
    }

    @NonNull
    @Deprecated
    public static FragmentRespiratoryRateHistoryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentRespiratoryRateHistoryBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_respiratory_rate_history, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentRespiratoryRateHistoryBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentRespiratoryRateHistoryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentRespiratoryRateHistoryBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_respiratory_rate_history, null, false, obj);
    }
}

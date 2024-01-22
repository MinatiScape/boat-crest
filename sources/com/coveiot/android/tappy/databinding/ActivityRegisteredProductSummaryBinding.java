package com.coveiot.android.tappy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public abstract class ActivityRegisteredProductSummaryBinding extends ViewDataBinding {
    @NonNull
    public final Button btnActiveCard;
    @NonNull
    public final Button btnAddNfcStrap;
    @NonNull
    public final Button btnResumeCard;
    @NonNull
    public final Button btnSuspendCard;
    @NonNull
    public final ConstraintLayout buttonLayout;
    @NonNull
    public final ConstraintLayout clTransactionHistory;
    @NonNull
    public final FrameLayout fragmentContainer;
    @NonNull
    public final ImageView imageSettings;
    @NonNull
    public final LinearLayout linearLayoutDots;
    @NonNull
    public final TextView noStrapAdded;
    @NonNull
    public final RecyclerView rvTransactionDetails;
    @NonNull
    public final ConstraintLayout settingsLayout;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvLast10Transaction;
    @NonNull
    public final TextView tvLatestTenTransaction;
    @NonNull
    public final TextView tvNoTransaction;
    @NonNull
    public final ViewPager viewPagerRegisteredProductSummary;
    @NonNull
    public final ConstraintLayout virtualCardTitleLayout;

    public ActivityRegisteredProductSummaryBinding(Object obj, View view, int i, Button button, Button button2, Button button3, Button button4, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, FrameLayout frameLayout, ImageView imageView, LinearLayout linearLayout, TextView textView, RecyclerView recyclerView, ConstraintLayout constraintLayout3, View view2, TextView textView2, TextView textView3, TextView textView4, ViewPager viewPager, ConstraintLayout constraintLayout4) {
        super(obj, view, i);
        this.btnActiveCard = button;
        this.btnAddNfcStrap = button2;
        this.btnResumeCard = button3;
        this.btnSuspendCard = button4;
        this.buttonLayout = constraintLayout;
        this.clTransactionHistory = constraintLayout2;
        this.fragmentContainer = frameLayout;
        this.imageSettings = imageView;
        this.linearLayoutDots = linearLayout;
        this.noStrapAdded = textView;
        this.rvTransactionDetails = recyclerView;
        this.settingsLayout = constraintLayout3;
        this.toolbar = view2;
        this.tvLast10Transaction = textView2;
        this.tvLatestTenTransaction = textView3;
        this.tvNoTransaction = textView4;
        this.viewPagerRegisteredProductSummary = viewPager;
        this.virtualCardTitleLayout = constraintLayout4;
    }

    public static ActivityRegisteredProductSummaryBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityRegisteredProductSummaryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityRegisteredProductSummaryBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityRegisteredProductSummaryBinding) ViewDataBinding.bind(obj, view, R.layout.activity_registered_product_summary);
    }

    @NonNull
    @Deprecated
    public static ActivityRegisteredProductSummaryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityRegisteredProductSummaryBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_registered_product_summary, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityRegisteredProductSummaryBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityRegisteredProductSummaryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityRegisteredProductSummaryBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_registered_product_summary, null, false, obj);
    }
}

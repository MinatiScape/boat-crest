package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class ActivityBoatCoinContactsBinding extends ViewDataBinding {
    @NonNull
    public final FrameLayout fragmentContainer;
    @NonNull
    public final TextView headerBuddies;
    @NonNull
    public final RecyclerView recyclerViewBoatCoins;
    @NonNull
    public final ImageButton refresh;
    @NonNull
    public final SearchView search;
    @NonNull
    public final CardView searchLayout;
    @NonNull
    public final TextView sendBoatcoins;
    @NonNull
    public final Toolbar toolbar;
    @NonNull
    public final ImageView toolbarBackArrow;

    public ActivityBoatCoinContactsBinding(Object obj, View view, int i, FrameLayout frameLayout, TextView textView, RecyclerView recyclerView, ImageButton imageButton, SearchView searchView, CardView cardView, TextView textView2, Toolbar toolbar, ImageView imageView) {
        super(obj, view, i);
        this.fragmentContainer = frameLayout;
        this.headerBuddies = textView;
        this.recyclerViewBoatCoins = recyclerView;
        this.refresh = imageButton;
        this.search = searchView;
        this.searchLayout = cardView;
        this.sendBoatcoins = textView2;
        this.toolbar = toolbar;
        this.toolbarBackArrow = imageView;
    }

    public static ActivityBoatCoinContactsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityBoatCoinContactsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBoatCoinContactsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityBoatCoinContactsBinding) ViewDataBinding.bind(obj, view, R.layout.activity_boat_coin_contacts);
    }

    @NonNull
    @Deprecated
    public static ActivityBoatCoinContactsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityBoatCoinContactsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_boat_coin_contacts, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityBoatCoinContactsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityBoatCoinContactsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityBoatCoinContactsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_boat_coin_contacts, null, false, obj);
    }
}

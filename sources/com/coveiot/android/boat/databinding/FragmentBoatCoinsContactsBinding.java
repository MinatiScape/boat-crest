package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class FragmentBoatCoinsContactsBinding extends ViewDataBinding {
    @NonNull
    public final ImageButton refresh;
    @NonNull
    public final SearchView search;
    @NonNull
    public final CardView searchLayout;

    public FragmentBoatCoinsContactsBinding(Object obj, View view, int i, ImageButton imageButton, SearchView searchView, CardView cardView) {
        super(obj, view, i);
        this.refresh = imageButton;
        this.search = searchView;
        this.searchLayout = cardView;
    }

    public static FragmentBoatCoinsContactsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentBoatCoinsContactsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentBoatCoinsContactsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentBoatCoinsContactsBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_boat_coins_contacts);
    }

    @NonNull
    @Deprecated
    public static FragmentBoatCoinsContactsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentBoatCoinsContactsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_boat_coins_contacts, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentBoatCoinsContactsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentBoatCoinsContactsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentBoatCoinsContactsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_boat_coins_contacts, null, false, obj);
    }
}

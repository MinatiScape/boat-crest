package com.coveiot.android.fitnessbuddies.databinding;

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
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.fitnessbuddies.R;
/* loaded from: classes4.dex */
public abstract class ActivityAddBuddiesBinding extends ViewDataBinding {
    @NonNull
    public final RecyclerView contactsList;
    @NonNull
    public final ImageButton refresh;
    @NonNull
    public final SearchView search;
    @NonNull
    public final CardView searchLayout;
    @NonNull
    public final View toolbar;

    public ActivityAddBuddiesBinding(Object obj, View view, int i, RecyclerView recyclerView, ImageButton imageButton, SearchView searchView, CardView cardView, View view2) {
        super(obj, view, i);
        this.contactsList = recyclerView;
        this.refresh = imageButton;
        this.search = searchView;
        this.searchLayout = cardView;
        this.toolbar = view2;
    }

    public static ActivityAddBuddiesBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityAddBuddiesBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAddBuddiesBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityAddBuddiesBinding) ViewDataBinding.bind(obj, view, R.layout.activity_add_buddies);
    }

    @NonNull
    @Deprecated
    public static ActivityAddBuddiesBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityAddBuddiesBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_add_buddies, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityAddBuddiesBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityAddBuddiesBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityAddBuddiesBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_add_buddies, null, false, obj);
    }
}

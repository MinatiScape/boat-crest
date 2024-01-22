package com.coveiot.android.navigation.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.navigation.R;
/* loaded from: classes5.dex */
public abstract class ActivityNavigationSearchPlacesBinding extends ViewDataBinding {
    @NonNull
    public final Barrier barrierSearchPlaces1;
    @NonNull
    public final ConstraintLayout clNoRecentHistoryViewSearchPlacesActivity;
    @NonNull
    public final ConstraintLayout clRecentHistoryViewSearchPlacesActivity;
    @NonNull
    public final ConstraintLayout clSearchPlacesSearchLayout;
    @NonNull
    public final View divider1SearchPlacesActivity;
    @NonNull
    public final View dividerRecentHistorySearchPlacesActivity;
    @NonNull
    public final EditText etSearchPlaceAddress;
    @NonNull
    public final ImageView ivSearchPlacesClearIcon;
    @NonNull
    public final ImageView ivSearchPlacesSearchIcon;
    @NonNull
    public final RecyclerView rvAutoSuggestResults;
    @NonNull
    public final RecyclerView rvRecentHistorySearchPlacesActivity;
    @NonNull
    public final RecyclerView rvTagsSearchPlacesActivity;
    @NonNull
    public final NestedScrollView scrollView;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvNoRecentHistory;
    @NonNull
    public final TextView tvRecentHistoryClearAllSearchPlacesActivity;
    @NonNull
    public final TextView tvRecentHistoryHeaderSearchPlacesActivity;
    @NonNull
    public final TextView tvRecentHistorySearchPlacesActivity;
    @NonNull
    public final TextView tvRecentHistoryViewMoreSearchPlacesActivity;
    @NonNull
    public final TextView tvYourCurrentLocationForRecentHistory;
    @NonNull
    public final TextView tvYourCurrentLocationForSearchResults;

    public ActivityNavigationSearchPlacesBinding(Object obj, View view, int i, Barrier barrier, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, View view2, View view3, EditText editText, ImageView imageView, ImageView imageView2, RecyclerView recyclerView, RecyclerView recyclerView2, RecyclerView recyclerView3, NestedScrollView nestedScrollView, View view4, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7) {
        super(obj, view, i);
        this.barrierSearchPlaces1 = barrier;
        this.clNoRecentHistoryViewSearchPlacesActivity = constraintLayout;
        this.clRecentHistoryViewSearchPlacesActivity = constraintLayout2;
        this.clSearchPlacesSearchLayout = constraintLayout3;
        this.divider1SearchPlacesActivity = view2;
        this.dividerRecentHistorySearchPlacesActivity = view3;
        this.etSearchPlaceAddress = editText;
        this.ivSearchPlacesClearIcon = imageView;
        this.ivSearchPlacesSearchIcon = imageView2;
        this.rvAutoSuggestResults = recyclerView;
        this.rvRecentHistorySearchPlacesActivity = recyclerView2;
        this.rvTagsSearchPlacesActivity = recyclerView3;
        this.scrollView = nestedScrollView;
        this.toolbar = view4;
        this.tvNoRecentHistory = textView;
        this.tvRecentHistoryClearAllSearchPlacesActivity = textView2;
        this.tvRecentHistoryHeaderSearchPlacesActivity = textView3;
        this.tvRecentHistorySearchPlacesActivity = textView4;
        this.tvRecentHistoryViewMoreSearchPlacesActivity = textView5;
        this.tvYourCurrentLocationForRecentHistory = textView6;
        this.tvYourCurrentLocationForSearchResults = textView7;
    }

    public static ActivityNavigationSearchPlacesBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityNavigationSearchPlacesBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityNavigationSearchPlacesBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityNavigationSearchPlacesBinding) ViewDataBinding.bind(obj, view, R.layout.activity_navigation_search_places);
    }

    @NonNull
    @Deprecated
    public static ActivityNavigationSearchPlacesBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityNavigationSearchPlacesBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_navigation_search_places, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityNavigationSearchPlacesBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityNavigationSearchPlacesBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityNavigationSearchPlacesBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_navigation_search_places, null, false, obj);
    }
}

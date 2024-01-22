package com.mappls.sdk.direction.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.mappls.sdk.direction.ui.DirectionViewModel;
import com.mappls.sdk.direction.ui.R;
/* loaded from: classes11.dex */
public abstract class MapplsRoutingLayoutBinding extends ViewDataBinding {
    @NonNull
    public final LinearLayout addLocation;
    @NonNull
    public final RelativeLayout bottomSheetDetails;
    @NonNull
    public final View bottomSheetShadow;
    @NonNull
    public final TextView btnRetryRouteNotFound;
    @NonNull
    public final TextView btnStart;
    @NonNull
    public final CardView cdSelectLocation;
    @NonNull
    public final MapplsDirectionLayoutCollapsedRouteViewBinding collapsedRouteTimeline;
    @NonNull
    public final LinearLayout containerRouteDetails;
    @NonNull
    public final CoordinatorLayout directionContainer;
    @NonNull
    public final TextView directionEtaTextView;
    @NonNull
    public final TextView directionListTextview;
    @NonNull
    public final LinearLayout directionTimeLayout;
    @NonNull
    public final TextView distanceText;
    @NonNull
    public final ConstraintLayout errorLayout;
    @NonNull
    public final ImageView imgStart;
    @NonNull
    public final ImageView ivErrorStateDirection;
    @NonNull
    public final MapplsDirectionRouteViewBinding layoutSelectTab;
    @NonNull
    public final ConstraintLayout layoutTimeDetails;
    @Bindable
    public String mArrival;
    @Bindable
    public String mDistance;
    @Bindable
    public View.OnClickListener mOnClickGetRoute;
    @Bindable
    public View.OnClickListener mOnClickSearchCategory;
    @Bindable
    public View.OnClickListener mOnHandleBack;
    @Bindable
    public View.OnClickListener mOnRouteReportClick;
    @Bindable
    public DirectionViewModel mOnStartClick;
    @Bindable
    public View.OnClickListener mRetryButtonClick;
    @Bindable
    public String mRouteTime;
    @Bindable
    public View.OnClickListener mStartButtonClick;
    @NonNull
    public final FrameLayout mapContainer;
    @NonNull
    public final ImageView mapplsDirectionBack;
    @NonNull
    public final ImageView nearbyReport;
    @NonNull
    public final LinearLayout notificationLayout;
    @NonNull
    public final ProgressBar progressBar;
    @NonNull
    public final RecyclerView recyclerDirectionStep;
    @NonNull
    public final ImageButton searchCategoryFab;
    @NonNull
    public final TextView showOnMapsBtn;
    @NonNull
    public final LinearLayout showOnMapsLayout;
    @NonNull
    public final LinearLayout startLayout;
    @NonNull
    public final TextView textRoute;
    @NonNull
    public final ImageView tripCostSummary;
    @NonNull
    public final TextView tvClassNotification;
    @NonNull
    public final TextView tvRouteNotFoundHeading;
    @NonNull
    public final TextView tvRouteNotFoundText;
    @NonNull
    public final RelativeLayout viewGetRoute;

    public MapplsRoutingLayoutBinding(Object obj, View view, int i, LinearLayout linearLayout, RelativeLayout relativeLayout, View view2, TextView textView, TextView textView2, CardView cardView, MapplsDirectionLayoutCollapsedRouteViewBinding mapplsDirectionLayoutCollapsedRouteViewBinding, LinearLayout linearLayout2, CoordinatorLayout coordinatorLayout, TextView textView3, TextView textView4, LinearLayout linearLayout3, TextView textView5, ConstraintLayout constraintLayout, ImageView imageView, ImageView imageView2, MapplsDirectionRouteViewBinding mapplsDirectionRouteViewBinding, ConstraintLayout constraintLayout2, FrameLayout frameLayout, ImageView imageView3, ImageView imageView4, LinearLayout linearLayout4, ProgressBar progressBar, RecyclerView recyclerView, ImageButton imageButton, TextView textView6, LinearLayout linearLayout5, LinearLayout linearLayout6, TextView textView7, ImageView imageView5, TextView textView8, TextView textView9, TextView textView10, RelativeLayout relativeLayout2) {
        super(obj, view, i);
        this.addLocation = linearLayout;
        this.bottomSheetDetails = relativeLayout;
        this.bottomSheetShadow = view2;
        this.btnRetryRouteNotFound = textView;
        this.btnStart = textView2;
        this.cdSelectLocation = cardView;
        this.collapsedRouteTimeline = mapplsDirectionLayoutCollapsedRouteViewBinding;
        this.containerRouteDetails = linearLayout2;
        this.directionContainer = coordinatorLayout;
        this.directionEtaTextView = textView3;
        this.directionListTextview = textView4;
        this.directionTimeLayout = linearLayout3;
        this.distanceText = textView5;
        this.errorLayout = constraintLayout;
        this.imgStart = imageView;
        this.ivErrorStateDirection = imageView2;
        this.layoutSelectTab = mapplsDirectionRouteViewBinding;
        this.layoutTimeDetails = constraintLayout2;
        this.mapContainer = frameLayout;
        this.mapplsDirectionBack = imageView3;
        this.nearbyReport = imageView4;
        this.notificationLayout = linearLayout4;
        this.progressBar = progressBar;
        this.recyclerDirectionStep = recyclerView;
        this.searchCategoryFab = imageButton;
        this.showOnMapsBtn = textView6;
        this.showOnMapsLayout = linearLayout5;
        this.startLayout = linearLayout6;
        this.textRoute = textView7;
        this.tripCostSummary = imageView5;
        this.tvClassNotification = textView8;
        this.tvRouteNotFoundHeading = textView9;
        this.tvRouteNotFoundText = textView10;
        this.viewGetRoute = relativeLayout2;
    }

    public static MapplsRoutingLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MapplsRoutingLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MapplsRoutingLayoutBinding) ViewDataBinding.bind(obj, view, R.layout.mappls_routing_layout);
    }

    @NonNull
    public static MapplsRoutingLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MapplsRoutingLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MapplsRoutingLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MapplsRoutingLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_routing_layout, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static MapplsRoutingLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MapplsRoutingLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_routing_layout, null, false, obj);
    }

    @Nullable
    public String getArrival() {
        return this.mArrival;
    }

    @Nullable
    public String getDistance() {
        return this.mDistance;
    }

    @Nullable
    public View.OnClickListener getOnClickGetRoute() {
        return this.mOnClickGetRoute;
    }

    @Nullable
    public View.OnClickListener getOnClickSearchCategory() {
        return this.mOnClickSearchCategory;
    }

    @Nullable
    public View.OnClickListener getOnHandleBack() {
        return this.mOnHandleBack;
    }

    @Nullable
    public View.OnClickListener getOnRouteReportClick() {
        return this.mOnRouteReportClick;
    }

    @Nullable
    public DirectionViewModel getOnStartClick() {
        return this.mOnStartClick;
    }

    @Nullable
    public View.OnClickListener getRetryButtonClick() {
        return this.mRetryButtonClick;
    }

    @Nullable
    public String getRouteTime() {
        return this.mRouteTime;
    }

    @Nullable
    public View.OnClickListener getStartButtonClick() {
        return this.mStartButtonClick;
    }

    public abstract void setArrival(@Nullable String str);

    public abstract void setDistance(@Nullable String str);

    public abstract void setOnClickGetRoute(@Nullable View.OnClickListener onClickListener);

    public abstract void setOnClickSearchCategory(@Nullable View.OnClickListener onClickListener);

    public abstract void setOnHandleBack(@Nullable View.OnClickListener onClickListener);

    public abstract void setOnRouteReportClick(@Nullable View.OnClickListener onClickListener);

    public abstract void setOnStartClick(@Nullable DirectionViewModel directionViewModel);

    public abstract void setRetryButtonClick(@Nullable View.OnClickListener onClickListener);

    public abstract void setRouteTime(@Nullable String str);

    public abstract void setStartButtonClick(@Nullable View.OnClickListener onClickListener);
}

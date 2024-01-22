package com.mappls.sdk.direction.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.mappls.sdk.direction.ui.R;
/* loaded from: classes11.dex */
public abstract class MapplsDirectionLayoutCollapsedRouteViewBinding extends ViewDataBinding {
    @NonNull
    public final LinearLayout collapsedRouteTimeline;
    @NonNull
    public final ImageButton editWaypointsBtn;
    @NonNull
    public final ImageView imgClickBack;
    @NonNull
    public final ImageView imgDestination;
    @NonNull
    public final ImageView imgSourceLocation;
    @NonNull
    public final ImageView imgWaypoints;
    @Bindable
    public String mDestinationRouteLocation;
    @Bindable
    public View.OnClickListener mOnclickHandleBack;
    @Bindable
    public String mSourceRouteLocation;
    @Bindable
    public String mWayPoints;
    @NonNull
    public final LinearLayout soucreLocationLayout;
    @NonNull
    public final TextView textDestinationRoute;
    @NonNull
    public final TextView textSourceRoute;
    @NonNull
    public final TextView textViewWaypoints;
    @NonNull
    public final LinearLayout viewSourceLocation;
    @NonNull
    public final LinearLayout viewWaypoints;
    @NonNull
    public final LinearLayout waypointsLayout;

    public MapplsDirectionLayoutCollapsedRouteViewBinding(Object obj, View view, int i, LinearLayout linearLayout, ImageButton imageButton, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, LinearLayout linearLayout2, TextView textView, TextView textView2, TextView textView3, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5) {
        super(obj, view, i);
        this.collapsedRouteTimeline = linearLayout;
        this.editWaypointsBtn = imageButton;
        this.imgClickBack = imageView;
        this.imgDestination = imageView2;
        this.imgSourceLocation = imageView3;
        this.imgWaypoints = imageView4;
        this.soucreLocationLayout = linearLayout2;
        this.textDestinationRoute = textView;
        this.textSourceRoute = textView2;
        this.textViewWaypoints = textView3;
        this.viewSourceLocation = linearLayout3;
        this.viewWaypoints = linearLayout4;
        this.waypointsLayout = linearLayout5;
    }

    public static MapplsDirectionLayoutCollapsedRouteViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MapplsDirectionLayoutCollapsedRouteViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MapplsDirectionLayoutCollapsedRouteViewBinding) ViewDataBinding.bind(obj, view, R.layout.mappls_direction_layout_collapsed_route_view);
    }

    @NonNull
    public static MapplsDirectionLayoutCollapsedRouteViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MapplsDirectionLayoutCollapsedRouteViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionLayoutCollapsedRouteViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MapplsDirectionLayoutCollapsedRouteViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_layout_collapsed_route_view, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionLayoutCollapsedRouteViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MapplsDirectionLayoutCollapsedRouteViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_layout_collapsed_route_view, null, false, obj);
    }

    @Nullable
    public String getDestinationRouteLocation() {
        return this.mDestinationRouteLocation;
    }

    @Nullable
    public View.OnClickListener getOnclickHandleBack() {
        return this.mOnclickHandleBack;
    }

    @Nullable
    public String getSourceRouteLocation() {
        return this.mSourceRouteLocation;
    }

    @Nullable
    public String getWayPoints() {
        return this.mWayPoints;
    }

    public abstract void setDestinationRouteLocation(@Nullable String str);

    public abstract void setOnclickHandleBack(@Nullable View.OnClickListener onClickListener);

    public abstract void setSourceRouteLocation(@Nullable String str);

    public abstract void setWayPoints(@Nullable String str);
}

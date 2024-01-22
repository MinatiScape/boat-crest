package com.coveiot.android.navigation.activities;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.coveiot.android.navigation.R;
import com.coveiot.android.navigation.mapplsUtils.DirectionPolylinePlugin;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityNavigationShowRoute$drawPolyLine$1 implements DirectionPolylinePlugin.OnNewRouteSelectedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ActivityNavigationShowRoute f5481a;
    public final /* synthetic */ Ref.BooleanRef b;

    public ActivityNavigationShowRoute$drawPolyLine$1(ActivityNavigationShowRoute activityNavigationShowRoute, Ref.BooleanRef booleanRef) {
        this.f5481a = activityNavigationShowRoute;
        this.b = booleanRef;
    }

    public static final void c(ActivityNavigationShowRoute this$0, Ref.BooleanRef isMapClicked) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(isMapClicked, "$isMapClicked");
        ViewGroup.LayoutParams layoutParams = this$0.getBinding().mapviewShowRoute.getLayoutParams();
        if (isMapClicked.element) {
            TextView textView = this$0.getBinding().tvTapToEnter;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.tvTapToEnter");
            this$0.gone(textView);
            ConstraintLayout constraintLayout = this$0.getBinding().clRouteMapviewTopLayout;
            Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clRouteMapviewTopLayout");
            this$0.gone(constraintLayout);
            ConstraintLayout constraintLayout2 = this$0.getBinding().clRouteMapviewBottomLayout;
            Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.clRouteMapviewBottomLayout");
            this$0.gone(constraintLayout2);
            layoutParams.height = -1;
            View view = this$0.getBinding().toolbar;
            Intrinsics.checkNotNullExpressionValue(view, "binding.toolbar");
            this$0.gone(view);
            isMapClicked.element = false;
        } else {
            ConstraintLayout constraintLayout3 = this$0.getBinding().clRouteMapviewTopLayout;
            Intrinsics.checkNotNullExpressionValue(constraintLayout3, "binding.clRouteMapviewTopLayout");
            this$0.visible(constraintLayout3);
            ConstraintLayout constraintLayout4 = this$0.getBinding().clRouteMapviewBottomLayout;
            Intrinsics.checkNotNullExpressionValue(constraintLayout4, "binding.clRouteMapviewBottomLayout");
            this$0.visible(constraintLayout4);
            layoutParams.height = this$0.getResources().getDimensionPixelSize(R.dimen.margin_230dp);
            View view2 = this$0.getBinding().toolbar;
            Intrinsics.checkNotNullExpressionValue(view2, "binding.toolbar");
            this$0.visible(view2);
            isMapClicked.element = true;
        }
        this$0.getBinding().mapviewShowRoute.setLayoutParams(layoutParams);
    }

    public static final void d(ActivityNavigationShowRoute this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.C = i;
        this$0.d0(this$0.C);
    }

    @Override // com.coveiot.android.navigation.mapplsUtils.DirectionPolylinePlugin.OnNewRouteSelectedListener
    public void onClickedMapFarFromRoute() {
        final ActivityNavigationShowRoute activityNavigationShowRoute = this.f5481a;
        final Ref.BooleanRef booleanRef = this.b;
        activityNavigationShowRoute.runOnUiThread(new Runnable() { // from class: com.coveiot.android.navigation.activities.b2
            @Override // java.lang.Runnable
            public final void run() {
                ActivityNavigationShowRoute$drawPolyLine$1.c(ActivityNavigationShowRoute.this, booleanRef);
            }
        });
    }

    @Override // com.coveiot.android.navigation.mapplsUtils.DirectionPolylinePlugin.OnNewRouteSelectedListener
    public void onNewRouteSelected(final int i, @Nullable DirectionsRoute directionsRoute) {
        final ActivityNavigationShowRoute activityNavigationShowRoute = this.f5481a;
        activityNavigationShowRoute.runOnUiThread(new Runnable() { // from class: com.coveiot.android.navigation.activities.a2
            @Override // java.lang.Runnable
            public final void run() {
                ActivityNavigationShowRoute$drawPolyLine$1.d(ActivityNavigationShowRoute.this, i);
            }
        });
    }
}

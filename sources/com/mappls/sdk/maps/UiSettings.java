package com.mappls.sdk.maps;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.UiThread;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;
import com.mappls.sdk.maps.attribution.AttributionView;
import com.mappls.sdk.maps.camera.CameraPosition;
import com.mappls.sdk.maps.constants.MapplsConstants;
import com.mappls.sdk.maps.covid.SafetyStripView;
import com.mappls.sdk.maps.utils.BitmapUtils;
import com.mappls.sdk.maps.widgets.CompassView;
import com.mappls.sdk.maps.widgets.LogoView;
import com.mappls.sdk.maps.widgets.indoor.FloorControllerView;
/* loaded from: classes11.dex */
public final class UiSettings {
    @Nullable
    public PointF H;
    public double O;
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final FocalPointChangeListener f12663a;
    @NonNull
    public final MapView b;
    @NonNull
    public final Projection c;
    @Nullable
    @VisibleForTesting
    public CompassView d;
    @Nullable
    @VisibleForTesting
    public FloorControllerView f;
    @Nullable
    @VisibleForTesting
    public AttributionView h;
    public AttributionDialogManager j;
    @Nullable
    @VisibleForTesting
    public LogoView k;
    @Nullable
    @VisibleForTesting
    public ImageView l;
    @NonNull
    @VisibleForTesting
    public SafetyStripView m;
    public final float r;
    public final int[] e = new int[4];
    public final int[] g = new int[4];
    public final int[] i = new int[4];
    public final int[] n = new int[4];
    public int o = 0;
    public final int[] p = new int[4];
    public final int[] q = new int[4];
    public boolean s = true;
    public boolean t = true;
    public boolean u = true;
    public boolean v = true;
    public boolean w = true;
    public boolean x = true;
    public boolean y = true;
    public boolean z = true;
    public boolean A = true;
    public boolean B = true;
    public boolean C = true;
    public boolean D = true;
    public boolean E = true;
    public float F = 1.0f;
    public boolean G = true;
    @VisibleForTesting
    public boolean I = false;
    @VisibleForTesting
    public boolean J = false;
    @VisibleForTesting
    public boolean K = false;
    @VisibleForTesting
    public boolean L = false;
    public boolean M = false;
    public boolean N = false;
    public boolean P = true;

    public UiSettings(@NonNull Projection projection, @NonNull FocalPointChangeListener focalPointChangeListener, float f, MapView mapView) {
        this.c = projection;
        this.f12663a = focalPointChangeListener;
        this.r = f;
        this.b = mapView;
    }

    public final void A(Bundle bundle) {
        bundle.putParcelable(MapplsConstants.STATE_USER_FOCAL_POINT, getFocalPoint());
    }

    public final void B(Bundle bundle) {
        bundle.putBoolean(MapplsConstants.STATE_HORIZONAL_SCROLL_ENABLED, isHorizontalScrollGesturesEnabled());
        bundle.putBoolean(MapplsConstants.STATE_ZOOM_ENABLED, isZoomGesturesEnabled());
        bundle.putBoolean(MapplsConstants.STATE_SCROLL_ENABLED, isScrollGesturesEnabled());
        bundle.putBoolean(MapplsConstants.STATE_ROTATE_ENABLED, isRotateGesturesEnabled());
        bundle.putBoolean(MapplsConstants.STATE_TILT_ENABLED, isTiltGesturesEnabled());
        bundle.putBoolean(MapplsConstants.STATE_DOUBLE_TAP_ENABLED, isDoubleTapGesturesEnabled());
        bundle.putBoolean(MapplsConstants.STATE_SCALE_ANIMATION_ENABLED, isScaleVelocityAnimationEnabled());
        bundle.putBoolean(MapplsConstants.STATE_ROTATE_ANIMATION_ENABLED, isRotateVelocityAnimationEnabled());
        bundle.putBoolean(MapplsConstants.STATE_FLING_ANIMATION_ENABLED, isFlingVelocityAnimationEnabled());
        bundle.putBoolean(MapplsConstants.STATE_INCREASE_ROTATE_THRESHOLD, isIncreaseRotateThresholdWhenScaling());
        bundle.putBoolean(MapplsConstants.STATE_DISABLE_ROTATE_WHEN_SCALING, isDisableRotateWhenScaling());
        bundle.putBoolean(MapplsConstants.STATE_INCREASE_SCALE_THRESHOLD, isIncreaseScaleThresholdWhenRotating());
        bundle.putBoolean(MapplsConstants.STATE_QUICK_ZOOM_ENABLED, isQuickZoomGesturesEnabled());
        bundle.putFloat(MapplsConstants.STATE_ZOOM_RATE, getZoomRate());
    }

    public final void C(Bundle bundle) {
        bundle.putInt(MapplsConstants.STATE_LAYER_CONTROL_GRAVITY, getLayerControlGravity());
        bundle.putInt(MapplsConstants.STATE_LAYER_CONTROL_MARGIN_LEFT, getLayerControlMarginLeft());
        bundle.putInt(MapplsConstants.STATE_LAYER_CONTROL_MARGIN_TOP, getLayerControlMarginTop());
        bundle.putInt(MapplsConstants.STATE_LAYER_CONTROL_MARGIN_RIGHT, getLayerControlMarginRight());
        bundle.putInt(MapplsConstants.STATE_LAYER_CONTROL_MARGIN_BOTTOM, getLayerControlMarginBottom());
        bundle.putBoolean(MapplsConstants.STATE_LAYER_CONTROL_ENABLED, isLayerControlEnabled());
    }

    public final void D(Bundle bundle) {
        bundle.putInt(MapplsConstants.STATE_LOGO_GRAVITY, getLogoGravity());
        bundle.putInt(MapplsConstants.STATE_LOGO_MARGIN_LEFT, getLogoMarginLeft());
        bundle.putInt(MapplsConstants.STATE_LOGO_MARGIN_TOP, getLogoMarginTop());
        bundle.putInt(MapplsConstants.STATE_LOGO_MARGIN_RIGHT, getLogoMarginRight());
        bundle.putInt(MapplsConstants.STATE_LOGO_MARGIN_BOTTOM, getLogoMarginBottom());
        bundle.putBoolean(MapplsConstants.STATE_LOGO_ENABLED, isLogoEnabled());
        bundle.putInt(MapplsConstants.STATE_LOGO_SIZE, getLogoSize());
    }

    public final void E(Bundle bundle) {
        bundle.putInt(MapplsConstants.STATE_SAFETY_STRIP_GRAVITY, getSafetyStripGravity());
        bundle.putInt(MapplsConstants.STATE_SAFETY_STRIP_MARGIN_LEFT, getSafetyStripMarginLeft());
        bundle.putInt(MapplsConstants.STATE_SAFETY_STRIP_MARGIN_TOP, getSafetyStripMarginTop());
        bundle.putInt(MapplsConstants.STATE_SAFETY_STRIP_MARGIN_RIGHT, getSafetyStripMarginRight());
        bundle.putInt(MapplsConstants.STATE_SAFETY_STRIP_MARGIN_BOTTOM, getSafetyStripMarginBottom());
    }

    public final void F(@NonNull Context context, @Nullable int[] iArr) {
        if (iArr != null) {
            setAttributionMargins(iArr[0], iArr[1], iArr[2], iArr[3]);
            return;
        }
        Resources resources = context.getResources();
        int dimension = (int) resources.getDimension(R.dimen.mappls_maps_four_dp);
        setAttributionMargins((int) resources.getDimension(R.dimen.mappls_maps_ninety_two_dp), dimension, dimension, dimension);
    }

    public final void G(@NonNull Resources resources, @Nullable int[] iArr) {
        if (iArr != null) {
            setLogoMargins(iArr[0], iArr[1], iArr[2], iArr[3]);
            return;
        }
        int dimension = (int) resources.getDimension(R.dimen.mappls_maps_four_dp);
        setLogoMargins(dimension, dimension, dimension, dimension);
    }

    public final void H(@NonNull Context context, @Nullable int[] iArr) {
        if (iArr != null) {
            setLayerControlMargins(iArr[0], iArr[1], iArr[2], iArr[3]);
            return;
        }
        Resources resources = context.getResources();
        int dimension = (int) resources.getDimension(R.dimen.mappls_maps_four_dp);
        setLayerControlMargins((int) resources.getDimension(R.dimen.mappls_maps_ninety_two_dp), dimension, dimension, dimension);
    }

    public final void I(@NonNull Resources resources, @Nullable int[] iArr) {
        if (iArr != null) {
            setLogoMargins(iArr[0], iArr[1], iArr[2], iArr[3]);
            return;
        }
        int dimension = (int) resources.getDimension(R.dimen.mappls_maps_four_dp);
        setLogoMargins(dimension, dimension, dimension, dimension);
    }

    public final void J(@NonNull Resources resources, @Nullable int[] iArr) {
        if (iArr != null) {
            setSafetyStripMargins(iArr[0], iArr[1], iArr[2], iArr[3]);
        } else {
            setSafetyStripMargins(0, 0, 0, 0);
        }
    }

    public final void K(@NonNull View view, int i) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        layoutParams.gravity = i;
        view.setLayoutParams(layoutParams);
    }

    public final void L(@NonNull View view, int[] iArr, int i, int i2, int i3, int i4) {
        iArr[0] = i;
        iArr[1] = i2;
        iArr[2] = i3;
        iArr[3] = i4;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        layoutParams.setMargins(i, i2, i3, i4);
        if (Build.VERSION.SDK_INT >= 17) {
            layoutParams.setMarginStart(i);
            layoutParams.setMarginEnd(i3);
        }
        view.setLayoutParams(layoutParams);
    }

    public void M(@NonNull CameraPosition cameraPosition) {
        double d = -cameraPosition.bearing;
        this.O = d;
        CompassView compassView = this.d;
        if (compassView != null) {
            compassView.update(d);
        }
    }

    public AttributionView a() {
        return this.h;
    }

    public boolean areAllGesturesEnabled() {
        return this.s && this.t && this.u && this.v && this.x && this.y;
    }

    public FloorControllerView b() {
        return this.f;
    }

    public float c() {
        return this.r;
    }

    public void d(@NonNull Context context, @NonNull MapplsMapOptions mapplsMapOptions) {
        Resources resources = context.getResources();
        h(mapplsMapOptions);
        if (mapplsMapOptions.getCompassEnabled()) {
            f(mapplsMapOptions, resources);
        }
        j(mapplsMapOptions, resources);
        g(mapplsMapOptions, resources);
        k(mapplsMapOptions, resources);
        if (mapplsMapOptions.getLayerControlEnabled()) {
            i(context, mapplsMapOptions);
        }
        if (mapplsMapOptions.getAttributionEnabled()) {
            e(context, mapplsMapOptions);
        }
    }

    public int dpToPx(float f, Resources resources) {
        return (int) (f * resources.getDisplayMetrics().density);
    }

    public final void e(@NonNull Context context, MapplsMapOptions mapplsMapOptions) {
        this.K = true;
        this.h = this.b.initialiseAttributionView();
        setAttributionEnabled(mapplsMapOptions.getAttributionEnabled());
        setAttributionGravity(mapplsMapOptions.getAttributionGravity());
        F(context, mapplsMapOptions.getAttributionMargins());
    }

    public void enableLogoClick(boolean z) {
        this.P = z;
    }

    public final void f(MapplsMapOptions mapplsMapOptions, @NonNull Resources resources) {
        this.I = true;
        CompassView initialiseCompassView = this.b.initialiseCompassView();
        this.d = initialiseCompassView;
        initialiseCompassView.setBackgroundDrawable(ResourcesCompat.getDrawable(resources, R.drawable.mappls_maps_compass_bg, null));
        int dpToPx = dpToPx(8.0f, resources);
        int dpToPx2 = dpToPx(8.0f, resources);
        this.d.setPadding(dpToPx, dpToPx, dpToPx, dpToPx);
        ViewCompat.setElevation(this.d, dpToPx2);
        setCompassEnabled(mapplsMapOptions.getCompassEnabled());
        setCompassGravity(mapplsMapOptions.getCompassGravity());
        int[] compassMargins = mapplsMapOptions.getCompassMargins();
        if (compassMargins != null) {
            setCompassMargins(compassMargins[0], compassMargins[1], compassMargins[2], compassMargins[3]);
        } else {
            int dimension = (int) resources.getDimension(R.dimen.mappls_maps_four_dp);
            setCompassMargins(dimension, dimension, dimension, dimension);
        }
        setCompassFadeFacingNorth(mapplsMapOptions.getCompassFadeFacingNorth());
        if (mapplsMapOptions.getCompassImage() == null) {
            mapplsMapOptions.compassImage(ResourcesCompat.getDrawable(resources, R.drawable.mappls_maps_compass_icon, null));
        }
        setCompassImage(mapplsMapOptions.getCompassImage());
    }

    public final void g(MapplsMapOptions mapplsMapOptions, @NonNull Resources resources) {
        this.N = true;
        this.l = this.b.initialiseEventView();
        setEventGravity(mapplsMapOptions.getEventGravity());
        G(resources, mapplsMapOptions.getEventMargins());
    }

    @Nullable
    public AttributionDialogManager getAttributionDialogManager() {
        return this.j;
    }

    public int getAttributionGravity() {
        AttributionView attributionView = this.h;
        if (attributionView != null) {
            return ((FrameLayout.LayoutParams) attributionView.getLayoutParams()).gravity;
        }
        return -1;
    }

    @Px
    public int getAttributionMarginBottom() {
        return this.i[3];
    }

    @Px
    public int getAttributionMarginLeft() {
        return this.i[0];
    }

    @Px
    public int getAttributionMarginRight() {
        return this.i[2];
    }

    @Px
    public int getAttributionMarginTop() {
        return this.i[1];
    }

    public int getCompassGravity() {
        CompassView compassView = this.d;
        if (compassView != null) {
            return ((FrameLayout.LayoutParams) compassView.getLayoutParams()).gravity;
        }
        return -1;
    }

    @Nullable
    public Drawable getCompassImage() {
        CompassView compassView = this.d;
        if (compassView != null) {
            return compassView.getCompassImage();
        }
        return null;
    }

    @Px
    public int getCompassMarginBottom() {
        return this.e[3];
    }

    @Px
    public int getCompassMarginLeft() {
        return this.e[0];
    }

    @Px
    public int getCompassMarginRight() {
        return this.e[2];
    }

    @Px
    public int getCompassMarginTop() {
        return this.e[1];
    }

    public int getEventGravity() {
        ImageView imageView = this.l;
        if (imageView != null) {
            return ((FrameLayout.LayoutParams) imageView.getLayoutParams()).gravity;
        }
        return -1;
    }

    @Px
    public int getEventMarginBottom() {
        return this.p[3];
    }

    @Px
    public int getEventMarginLeft() {
        return this.p[0];
    }

    @Px
    public int getEventMarginRight() {
        return this.p[2];
    }

    @Px
    public int getEventMarginTop() {
        return this.p[1];
    }

    @Nullable
    public PointF getFocalPoint() {
        return this.H;
    }

    public float getHeight() {
        return this.c.d();
    }

    public int getLayerControlGravity() {
        FloorControllerView floorControllerView = this.f;
        if (floorControllerView != null) {
            return ((FrameLayout.LayoutParams) floorControllerView.getLayoutParams()).gravity;
        }
        return -1;
    }

    @Px
    public int getLayerControlMarginBottom() {
        return this.g[3];
    }

    @Px
    public int getLayerControlMarginLeft() {
        return this.g[0];
    }

    @Px
    public int getLayerControlMarginRight() {
        return this.g[2];
    }

    @Px
    public int getLayerControlMarginTop() {
        return this.g[1];
    }

    public int getLogoGravity() {
        LogoView logoView = this.k;
        if (logoView != null) {
            return ((FrameLayout.LayoutParams) logoView.getLayoutParams()).gravity;
        }
        return -1;
    }

    @Px
    public int getLogoMarginBottom() {
        return this.n[3];
    }

    @Px
    public int getLogoMarginLeft() {
        return this.n[0];
    }

    @Px
    public int getLogoMarginRight() {
        return this.n[2];
    }

    @Px
    public int getLogoMarginTop() {
        return this.n[1];
    }

    public int getLogoSize() {
        return this.o;
    }

    public int getSafetyStripGravity() {
        SafetyStripView safetyStripView = this.m;
        if (safetyStripView != null) {
            return ((FrameLayout.LayoutParams) safetyStripView.getLayoutParams()).gravity;
        }
        return -1;
    }

    @Px
    public int getSafetyStripMarginBottom() {
        return this.q[3];
    }

    @Px
    public int getSafetyStripMarginLeft() {
        return this.q[0];
    }

    @Px
    public int getSafetyStripMarginRight() {
        return this.q[2];
    }

    @Px
    public int getSafetyStripMarginTop() {
        return this.q[1];
    }

    public float getWidth() {
        return this.c.f();
    }

    public float getZoomRate() {
        return this.F;
    }

    public final void h(MapplsMapOptions mapplsMapOptions) {
        setZoomGesturesEnabled(mapplsMapOptions.getZoomGesturesEnabled());
        setScrollGesturesEnabled(mapplsMapOptions.getScrollGesturesEnabled());
        setHorizontalScrollGesturesEnabled(mapplsMapOptions.getHorizontalScrollGesturesEnabled());
        setRotateGesturesEnabled(mapplsMapOptions.getRotateGesturesEnabled());
        setTiltGesturesEnabled(mapplsMapOptions.getTiltGesturesEnabled());
        setDoubleTapGesturesEnabled(mapplsMapOptions.getDoubleTapGesturesEnabled());
        setQuickZoomGesturesEnabled(mapplsMapOptions.getQuickZoomGesturesEnabled());
    }

    public void hideSafetyStrip() {
        this.m.setVisibility(8);
    }

    public final void i(@NonNull Context context, MapplsMapOptions mapplsMapOptions) {
        this.J = true;
        this.f = this.b.initialiseLayerControlView();
        setLayerControlEnabled(mapplsMapOptions.getLayerControlEnabled());
        setLayerControlGravity(mapplsMapOptions.getLayerControlGravity());
        H(context, mapplsMapOptions.getLayerControlMargins());
    }

    public void invalidate() {
        setLogoMargins(getLogoMarginLeft(), getLogoMarginTop(), getLogoMarginRight(), getLogoMarginBottom());
        setCompassEnabled(isCompassEnabled());
        setCompassMargins(getCompassMarginLeft(), getCompassMarginTop(), getCompassMarginRight(), getCompassMarginBottom());
        setLayerControlMargins(getLayerControlMarginLeft(), getLayerControlMarginTop(), getLayerControlMarginRight(), getLayerControlMarginBottom());
        setAttributionMargins(getAttributionMarginLeft(), getAttributionMarginTop(), getAttributionMarginRight(), getAttributionMarginBottom());
    }

    public boolean isAttributionEnabled() {
        AttributionView attributionView = this.h;
        return attributionView != null && attributionView.getVisibility() == 0;
    }

    public boolean isCompassEnabled() {
        CompassView compassView = this.d;
        if (compassView != null) {
            return compassView.isEnabled();
        }
        return false;
    }

    public boolean isCompassFadeWhenFacingNorth() {
        CompassView compassView = this.d;
        if (compassView != null) {
            return compassView.isFadeCompassViewFacingNorth();
        }
        return false;
    }

    public boolean isDeselectMarkersOnTap() {
        return this.G;
    }

    public boolean isDisableRotateWhenScaling() {
        return this.D;
    }

    public boolean isDoubleTapGesturesEnabled() {
        return this.x;
    }

    public boolean isEnableLogoClick() {
        return this.P;
    }

    public boolean isFlingVelocityAnimationEnabled() {
        return this.B;
    }

    public boolean isHorizontalScrollGesturesEnabled() {
        return this.w;
    }

    @Deprecated
    public boolean isIncreaseRotateThresholdWhenScaling() {
        return this.C;
    }

    public boolean isIncreaseScaleThresholdWhenRotating() {
        return this.E;
    }

    public boolean isLayerControlEnabled() {
        FloorControllerView floorControllerView = this.f;
        return floorControllerView != null && floorControllerView.getVisibility() == 0;
    }

    public boolean isLogoEnabled() {
        LogoView logoView = this.k;
        return logoView != null && logoView.getVisibility() == 0;
    }

    public boolean isQuickZoomGesturesEnabled() {
        return this.y;
    }

    public boolean isRotateGesturesEnabled() {
        return this.s;
    }

    public boolean isRotateVelocityAnimationEnabled() {
        return this.A;
    }

    public boolean isScaleVelocityAnimationEnabled() {
        return this.z;
    }

    public boolean isScrollGesturesEnabled() {
        return this.v;
    }

    public boolean isTiltGesturesEnabled() {
        return this.t;
    }

    public boolean isZoomGesturesEnabled() {
        return this.u;
    }

    public final void j(MapplsMapOptions mapplsMapOptions, @NonNull Resources resources) {
        this.L = true;
        this.k = this.b.initialiseLogoView();
        setLogoEnabled(mapplsMapOptions.getLogoEnabled());
        setLogoGravity(mapplsMapOptions.getLogoGravity());
        I(resources, mapplsMapOptions.getLogoMargins());
    }

    public final void k(MapplsMapOptions mapplsMapOptions, @NonNull Resources resources) {
        this.M = true;
        this.m = this.b.initialiseSafetyStripView();
        setSafetyStripGravity(mapplsMapOptions.getSafetyStripGravity());
        J(resources, mapplsMapOptions.getSafetyStripMargins());
    }

    public void l(@NonNull Bundle bundle) {
        s(bundle);
        o(bundle);
        u(bundle);
        q(bundle);
        v(bundle);
        t(bundle);
        n(bundle);
        p(bundle);
        r(bundle);
    }

    public void m(@NonNull Bundle bundle) {
        B(bundle);
        x(bundle);
        D(bundle);
        z(bundle);
        E(bundle);
        C(bundle);
        w(bundle);
        y(bundle);
        A(bundle);
    }

    public final void n(Bundle bundle) {
        if (bundle.getBoolean(MapplsConstants.STATE_ATTRIBUTION_ENABLED) && !this.K) {
            this.h = this.b.initialiseAttributionView();
            this.K = true;
        }
        setAttributionEnabled(bundle.getBoolean(MapplsConstants.STATE_ATTRIBUTION_ENABLED));
        setAttributionGravity(bundle.getInt(MapplsConstants.STATE_ATTRIBUTION_GRAVITY));
        setAttributionMargins(bundle.getInt(MapplsConstants.STATE_ATTRIBUTION_MARGIN_LEFT), bundle.getInt(MapplsConstants.STATE_ATTRIBUTION_MARGIN_TOP), bundle.getInt(MapplsConstants.STATE_ATTRIBUTION_MARGIN_RIGHT), bundle.getInt(MapplsConstants.STATE_ATTRIBUTION_MARGIN_BOTTOM));
    }

    public final void o(Bundle bundle) {
        if (bundle.getBoolean(MapplsConstants.STATE_COMPASS_ENABLED) && !this.I) {
            this.d = this.b.initialiseCompassView();
            this.I = true;
        }
        setCompassEnabled(bundle.getBoolean(MapplsConstants.STATE_COMPASS_ENABLED));
        setCompassGravity(bundle.getInt(MapplsConstants.STATE_COMPASS_GRAVITY));
        setCompassMargins(bundle.getInt(MapplsConstants.STATE_COMPASS_MARGIN_LEFT), bundle.getInt(MapplsConstants.STATE_COMPASS_MARGIN_TOP), bundle.getInt(MapplsConstants.STATE_COMPASS_MARGIN_RIGHT), bundle.getInt(MapplsConstants.STATE_COMPASS_MARGIN_BOTTOM));
        setCompassFadeFacingNorth(bundle.getBoolean(MapplsConstants.STATE_COMPASS_FADE_WHEN_FACING_NORTH));
        setCompassImage(BitmapUtils.getDrawableFromByteArray(this.b.getContext(), bundle.getByteArray(MapplsConstants.STATE_COMPASS_IMAGE_BITMAP)));
    }

    public final void p(Bundle bundle) {
        setDeselectMarkersOnTap(bundle.getBoolean(MapplsConstants.STATE_DESELECT_MARKER_ON_TAP));
    }

    public final void q(Bundle bundle) {
        if (!this.N) {
            this.l = this.b.initialiseEventView();
            this.N = true;
        }
        setEventGravity(bundle.getInt(MapplsConstants.STATE_EVENT_GRAVITY));
        setEventMargins(bundle.getInt(MapplsConstants.STATE_EVENT_MARGIN_LEFT), bundle.getInt(MapplsConstants.STATE_EVENT_MARGIN_TOP), bundle.getInt(MapplsConstants.STATE_EVENT_MARGIN_RIGHT), bundle.getInt(MapplsConstants.STATE_EVENT_MARGIN_BOTTOM));
    }

    public final void r(Bundle bundle) {
        PointF pointF = (PointF) bundle.getParcelable(MapplsConstants.STATE_USER_FOCAL_POINT);
        if (pointF != null) {
            setFocalPoint(pointF);
        }
    }

    public final void s(Bundle bundle) {
        setHorizontalScrollGesturesEnabled(bundle.getBoolean(MapplsConstants.STATE_HORIZONAL_SCROLL_ENABLED));
        setZoomGesturesEnabled(bundle.getBoolean(MapplsConstants.STATE_ZOOM_ENABLED));
        setScrollGesturesEnabled(bundle.getBoolean(MapplsConstants.STATE_SCROLL_ENABLED));
        setRotateGesturesEnabled(bundle.getBoolean(MapplsConstants.STATE_ROTATE_ENABLED));
        setTiltGesturesEnabled(bundle.getBoolean(MapplsConstants.STATE_TILT_ENABLED));
        setDoubleTapGesturesEnabled(bundle.getBoolean(MapplsConstants.STATE_DOUBLE_TAP_ENABLED));
        setScaleVelocityAnimationEnabled(bundle.getBoolean(MapplsConstants.STATE_SCALE_ANIMATION_ENABLED));
        setRotateVelocityAnimationEnabled(bundle.getBoolean(MapplsConstants.STATE_ROTATE_ANIMATION_ENABLED));
        setFlingVelocityAnimationEnabled(bundle.getBoolean(MapplsConstants.STATE_FLING_ANIMATION_ENABLED));
        setIncreaseRotateThresholdWhenScaling(bundle.getBoolean(MapplsConstants.STATE_INCREASE_ROTATE_THRESHOLD));
        setDisableRotateWhenScaling(bundle.getBoolean(MapplsConstants.STATE_DISABLE_ROTATE_WHEN_SCALING));
        setIncreaseScaleThresholdWhenRotating(bundle.getBoolean(MapplsConstants.STATE_INCREASE_SCALE_THRESHOLD));
        setQuickZoomGesturesEnabled(bundle.getBoolean(MapplsConstants.STATE_QUICK_ZOOM_ENABLED));
        setZoomRate(bundle.getFloat(MapplsConstants.STATE_ZOOM_RATE, 1.0f));
    }

    public void setAllGesturesEnabled(boolean z) {
        setScrollGesturesEnabled(z);
        setRotateGesturesEnabled(z);
        setTiltGesturesEnabled(z);
        setZoomGesturesEnabled(z);
        setDoubleTapGesturesEnabled(z);
        setQuickZoomGesturesEnabled(z);
    }

    public void setAllVelocityAnimationsEnabled(boolean z) {
        setScaleVelocityAnimationEnabled(z);
        setRotateVelocityAnimationEnabled(z);
        setFlingVelocityAnimationEnabled(z);
    }

    public void setAttributionDialogManager(@NonNull AttributionDialogManager attributionDialogManager) {
        this.j = attributionDialogManager;
    }

    public void setAttributionEnabled(boolean z) {
        if (z && !this.K) {
            e(this.b.getContext(), this.b.o);
        }
        AttributionView attributionView = this.h;
        if (attributionView != null) {
            attributionView.setVisibility(z ? 0 : 8);
        }
    }

    public void setAttributionGravity(int i) {
        AttributionView attributionView = this.h;
        if (attributionView != null) {
            K(attributionView, i);
        }
    }

    public void setAttributionMargins(@Px int i, @Px int i2, @Px int i3, @Px int i4) {
        AttributionView attributionView = this.h;
        if (attributionView != null) {
            L(attributionView, this.i, i, i2, i3, i4);
        }
    }

    public void setCompassEnabled(boolean z) {
        if (z && !this.I) {
            MapView mapView = this.b;
            f(mapView.o, mapView.getContext().getResources());
        }
        CompassView compassView = this.d;
        if (compassView != null) {
            compassView.setEnabled(z);
            this.d.update(this.O);
        }
    }

    public void setCompassFadeFacingNorth(boolean z) {
        CompassView compassView = this.d;
        if (compassView != null) {
            compassView.fadeCompassViewFacingNorth(z);
        }
    }

    @UiThread
    public void setCompassGravity(int i) {
        CompassView compassView = this.d;
        if (compassView != null) {
            K(compassView, i);
        }
    }

    public void setCompassImage(@NonNull Drawable drawable) {
        CompassView compassView = this.d;
        if (compassView != null) {
            compassView.setCompassImage(drawable);
        }
    }

    @UiThread
    public void setCompassMargins(@Px int i, @Px int i2, @Px int i3, @Px int i4) {
        CompassView compassView = this.d;
        if (compassView != null) {
            L(compassView, this.e, i, i2, i3, i4);
        }
    }

    public void setDeselectMarkersOnTap(boolean z) {
        this.G = z;
    }

    public void setDisableRotateWhenScaling(boolean z) {
        this.D = z;
    }

    public void setDoubleTapGesturesEnabled(boolean z) {
        this.x = z;
    }

    public void setEventGravity(int i) {
        K(this.l, i);
    }

    public void setEventMargins(@Px int i, @Px int i2, @Px int i3, @Px int i4) {
        ImageView imageView = this.l;
        if (imageView != null) {
            L(imageView, this.p, i, i2, i3, i4);
        }
    }

    public void setFlingVelocityAnimationEnabled(boolean z) {
        this.B = z;
    }

    public void setFocalPoint(@Nullable PointF pointF) {
        this.H = pointF;
        this.f12663a.onFocalPointChanged(pointF);
    }

    public void setHorizontalScrollGesturesEnabled(boolean z) {
        this.w = z;
    }

    @Deprecated
    public void setIncreaseRotateThresholdWhenScaling(boolean z) {
        this.C = z;
    }

    public void setIncreaseScaleThresholdWhenRotating(boolean z) {
        this.E = z;
    }

    public void setLayerControlEnabled(boolean z) {
        if (z && !this.J) {
            i(this.b.getContext(), this.b.o);
        }
        FloorControllerView floorControllerView = this.f;
        if (floorControllerView != null) {
            floorControllerView.setVisibility(z ? 0 : 8);
        }
    }

    public void setLayerControlGravity(int i) {
        FloorControllerView floorControllerView = this.f;
        if (floorControllerView != null) {
            K(floorControllerView, i);
        }
    }

    public void setLayerControlMargins(@Px int i, @Px int i2, @Px int i3, @Px int i4) {
        FloorControllerView floorControllerView = this.f;
        if (floorControllerView != null) {
            L(floorControllerView, this.g, i, i2, i3, i4);
        }
    }

    public void setLogoEnabled(boolean z) {
        if (z && !this.L) {
            MapView mapView = this.b;
            j(mapView.o, mapView.getContext().getResources());
        }
        LogoView logoView = this.k;
        if (logoView != null) {
            logoView.setVisibility(z ? 0 : 8);
        }
    }

    public void setLogoGravity(int i) {
        LogoView logoView = this.k;
        if (logoView != null) {
            K(logoView, i);
        }
    }

    public void setLogoMargins(@Px int i, @Px int i2, @Px int i3, @Px int i4) {
        LogoView logoView = this.k;
        if (logoView != null) {
            L(logoView, this.n, i, i2, i3, i4);
        }
    }

    public void setQuickZoomGesturesEnabled(boolean z) {
        this.y = z;
    }

    public void setRotateGesturesEnabled(boolean z) {
        this.s = z;
    }

    public void setRotateVelocityAnimationEnabled(boolean z) {
        this.A = z;
    }

    public void setSafetyStripGravity(int i) {
        SafetyStripView safetyStripView = this.m;
        if (safetyStripView != null) {
            K(safetyStripView, i);
        }
    }

    public void setSafetyStripMargins(@Px int i, @Px int i2, @Px int i3, @Px int i4) {
        SafetyStripView safetyStripView = this.m;
        if (safetyStripView != null) {
            L(safetyStripView, this.q, i, i2, i3, i4);
        }
    }

    public void setScaleVelocityAnimationEnabled(boolean z) {
        this.z = z;
    }

    public void setScrollGesturesEnabled(boolean z) {
        this.v = z;
    }

    public void setTiltGesturesEnabled(boolean z) {
        this.t = z;
    }

    public void setZoomGesturesEnabled(boolean z) {
        this.u = z;
    }

    public void setZoomRate(@FloatRange(from = 0.0d) float f) {
        this.F = f;
    }

    public final void t(Bundle bundle) {
        if (bundle.getBoolean(MapplsConstants.STATE_LAYER_CONTROL_ENABLED) && !this.J) {
            this.f = this.b.initialiseLayerControlView();
            this.J = true;
        }
        setLayerControlEnabled(bundle.getBoolean(MapplsConstants.STATE_LAYER_CONTROL_ENABLED));
        setLayerControlGravity(bundle.getInt(MapplsConstants.STATE_LAYER_CONTROL_GRAVITY));
        setLayerControlMargins(bundle.getInt(MapplsConstants.STATE_LAYER_CONTROL_MARGIN_LEFT), bundle.getInt(MapplsConstants.STATE_LAYER_CONTROL_MARGIN_TOP), bundle.getInt(MapplsConstants.STATE_LAYER_CONTROL_MARGIN_RIGHT), bundle.getInt(MapplsConstants.STATE_LAYER_CONTROL_MARGIN_BOTTOM));
    }

    public final void u(Bundle bundle) {
        if (bundle.getBoolean(MapplsConstants.STATE_LOGO_ENABLED) && !this.L) {
            this.k = this.b.initialiseLogoView();
            this.L = true;
        }
        setLogoEnabled(bundle.getBoolean(MapplsConstants.STATE_LOGO_ENABLED));
        setLogoGravity(bundle.getInt(MapplsConstants.STATE_LOGO_GRAVITY));
        setLogoMargins(bundle.getInt(MapplsConstants.STATE_LOGO_MARGIN_LEFT), bundle.getInt(MapplsConstants.STATE_LOGO_MARGIN_TOP), bundle.getInt(MapplsConstants.STATE_LOGO_MARGIN_RIGHT), bundle.getInt(MapplsConstants.STATE_LOGO_MARGIN_BOTTOM));
    }

    public final void v(Bundle bundle) {
        if (!this.M) {
            this.m = this.b.initialiseSafetyStripView();
            this.M = true;
        }
        setSafetyStripGravity(bundle.getInt(MapplsConstants.STATE_SAFETY_STRIP_GRAVITY));
        setSafetyStripMargins(bundle.getInt(MapplsConstants.STATE_SAFETY_STRIP_MARGIN_LEFT), bundle.getInt(MapplsConstants.STATE_SAFETY_STRIP_MARGIN_TOP), bundle.getInt(MapplsConstants.STATE_SAFETY_STRIP_MARGIN_RIGHT), bundle.getInt(MapplsConstants.STATE_SAFETY_STRIP_MARGIN_BOTTOM));
    }

    public final void w(Bundle bundle) {
        bundle.putInt(MapplsConstants.STATE_ATTRIBUTION_GRAVITY, getAttributionGravity());
        bundle.putInt(MapplsConstants.STATE_ATTRIBUTION_MARGIN_LEFT, getAttributionMarginLeft());
        bundle.putInt(MapplsConstants.STATE_ATTRIBUTION_MARGIN_TOP, getAttributionMarginTop());
        bundle.putInt(MapplsConstants.STATE_ATTRIBUTION_MARGIN_RIGHT, getAttributionMarginRight());
        bundle.putInt(MapplsConstants.STATE_ATTRIBUTION_MARGIN_BOTTOM, getAttributionMarginBottom());
        bundle.putBoolean(MapplsConstants.STATE_ATTRIBUTION_ENABLED, isAttributionEnabled());
    }

    public final void x(Bundle bundle) {
        bundle.putBoolean(MapplsConstants.STATE_COMPASS_ENABLED, isCompassEnabled());
        bundle.putInt(MapplsConstants.STATE_COMPASS_GRAVITY, getCompassGravity());
        bundle.putInt(MapplsConstants.STATE_COMPASS_MARGIN_LEFT, getCompassMarginLeft());
        bundle.putInt(MapplsConstants.STATE_COMPASS_MARGIN_TOP, getCompassMarginTop());
        bundle.putInt(MapplsConstants.STATE_COMPASS_MARGIN_BOTTOM, getCompassMarginBottom());
        bundle.putInt(MapplsConstants.STATE_COMPASS_MARGIN_RIGHT, getCompassMarginRight());
        bundle.putBoolean(MapplsConstants.STATE_COMPASS_FADE_WHEN_FACING_NORTH, isCompassFadeWhenFacingNorth());
        bundle.putByteArray(MapplsConstants.STATE_COMPASS_IMAGE_BITMAP, BitmapUtils.getByteArrayFromDrawable(getCompassImage()));
    }

    public final void y(Bundle bundle) {
        bundle.putBoolean(MapplsConstants.STATE_DESELECT_MARKER_ON_TAP, isDeselectMarkersOnTap());
    }

    public final void z(Bundle bundle) {
        bundle.putInt(MapplsConstants.STATE_EVENT_GRAVITY, getEventGravity());
        bundle.putInt(MapplsConstants.STATE_EVENT_MARGIN_LEFT, getEventMarginLeft());
        bundle.putInt(MapplsConstants.STATE_EVENT_MARGIN_TOP, getEventMarginTop());
        bundle.putInt(MapplsConstants.STATE_EVENT_MARGIN_RIGHT, getEventMarginRight());
        bundle.putInt(MapplsConstants.STATE_EVENT_MARGIN_BOTTOM, getEventMarginBottom());
    }
}

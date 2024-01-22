package com.mappls.sdk.maps;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Size;
import androidx.annotation.UiThread;
import com.mappls.sdk.geojson.Feature;
import com.mappls.sdk.geojson.Geometry;
import com.mappls.sdk.gestures.AndroidGesturesManager;
import com.mappls.sdk.gestures.MoveGestureDetector;
import com.mappls.sdk.gestures.RotateGestureDetector;
import com.mappls.sdk.gestures.ShoveGestureDetector;
import com.mappls.sdk.gestures.StandardScaleGestureDetector;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.annotations.Annotation;
import com.mappls.sdk.maps.annotations.BaseMarkerOptions;
import com.mappls.sdk.maps.annotations.Marker;
import com.mappls.sdk.maps.annotations.MarkerOptions;
import com.mappls.sdk.maps.annotations.Polygon;
import com.mappls.sdk.maps.annotations.PolygonOptions;
import com.mappls.sdk.maps.annotations.Polyline;
import com.mappls.sdk.maps.annotations.PolylineOptions;
import com.mappls.sdk.maps.camera.CameraMapplsPinBoundUpdate;
import com.mappls.sdk.maps.camera.CameraMapplsPinCallback;
import com.mappls.sdk.maps.camera.CameraMapplsPinPosition;
import com.mappls.sdk.maps.camera.CameraMapplsPinUpdate;
import com.mappls.sdk.maps.camera.CameraMapplsPinUpdateFactory;
import com.mappls.sdk.maps.camera.CameraPosition;
import com.mappls.sdk.maps.camera.CameraUpdate;
import com.mappls.sdk.maps.camera.CameraUpdateFactory;
import com.mappls.sdk.maps.constants.MapplsConstants;
import com.mappls.sdk.maps.covid.InteractiveItemDetails;
import com.mappls.sdk.maps.covid.RasterPlugin;
import com.mappls.sdk.maps.covid.SafetyStripView;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.geometry.LatLngBounds;
import com.mappls.sdk.maps.location.LocationComponent;
import com.mappls.sdk.maps.log.Logger;
import com.mappls.sdk.maps.offline.OfflineRegionDefinition;
import com.mappls.sdk.maps.style.OnStyleLoadListener;
import com.mappls.sdk.maps.style.expressions.Expression;
import com.mappls.sdk.maps.style.model.MapplsStyle;
import com.mappls.sdk.maps.widgets.indoor.IndoorConstants;
import com.mappls.sdk.maps.widgets.indoor.iface.IndoorListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;
@UiThread
/* loaded from: classes11.dex */
public final class MapplsMap {

    /* renamed from: a  reason: collision with root package name */
    public final s f12643a;
    @Nullable
    public final UiSettings b;
    public final Projection c;
    public final Transform d;
    public final com.mappls.sdk.maps.g e;
    public final g f;
    public final List<Style.OnStyleLoaded> g = new ArrayList();
    public final List<OnDeveloperAnimationListener> h;
    @Nullable
    public Style.OnStyleLoaded i;
    public LocationComponent j;
    public com.mappls.sdk.maps.b k;
    @Nullable
    public OnFpsChangedListener l;
    @Nullable
    public Style m;
    public boolean n;
    public boolean o;
    public List<InteractiveLayer> p;
    public SafetyStripView q;
    public c0 r;
    public RasterPlugin s;
    public e0 t;
    public OnStyleLoadListener u;
    public u v;

    /* loaded from: classes11.dex */
    public interface CancelableCallback {
        void onCancel();

        void onFinish();
    }

    @Deprecated
    /* loaded from: classes11.dex */
    public interface InfoWindowAdapter {
        @Nullable
        View getInfoWindow(@NonNull Marker marker);
    }

    /* loaded from: classes11.dex */
    public interface InteractiveLayerLoadingListener {
        void onLayersLoaded(List<InteractiveLayer> list);
    }

    /* loaded from: classes11.dex */
    public interface OnCameraIdleListener {
        void onCameraIdle();
    }

    /* loaded from: classes11.dex */
    public interface OnCameraMoveCanceledListener {
        void onCameraMoveCanceled();
    }

    /* loaded from: classes11.dex */
    public interface OnCameraMoveListener {
        void onCameraMove();
    }

    /* loaded from: classes11.dex */
    public interface OnCameraMoveStartedListener {
        public static final int REASON_API_ANIMATION = 3;
        public static final int REASON_API_GESTURE = 1;
        public static final int REASON_DEVELOPER_ANIMATION = 2;

        void onCameraMoveStarted(int i);
    }

    /* loaded from: classes11.dex */
    public interface OnCompassAnimationListener {
        void onCompassAnimation();

        void onCompassAnimationFinished();
    }

    /* loaded from: classes11.dex */
    public interface OnDeveloperAnimationListener {
        void onDeveloperAnimationStarted();
    }

    /* loaded from: classes11.dex */
    public interface OnFlingListener {
        void onFling();
    }

    /* loaded from: classes11.dex */
    public interface OnFpsChangedListener {
        void onFpsChanged(double d);
    }

    /* loaded from: classes11.dex */
    public interface OnInfoWindowClickListener {
        boolean onInfoWindowClick(@NonNull Marker marker);
    }

    /* loaded from: classes11.dex */
    public interface OnInfoWindowCloseListener {
        void onInfoWindowClose(@NonNull Marker marker);
    }

    /* loaded from: classes11.dex */
    public interface OnInfoWindowLongClickListener {
        void onInfoWindowLongClick(@NonNull Marker marker);
    }

    /* loaded from: classes11.dex */
    public interface OnInteractiveLayerClickListener {
        void onInteractiveLayerClicked(InteractiveItemDetails interactiveItemDetails);
    }

    /* loaded from: classes11.dex */
    public interface OnMapClickListener {
        boolean onMapClick(@NonNull LatLng latLng);
    }

    /* loaded from: classes11.dex */
    public interface OnMapLongClickListener {
        boolean onMapLongClick(@NonNull LatLng latLng);
    }

    /* loaded from: classes11.dex */
    public interface OnMarkerAddedListener {
        void onFailure();

        void onSuccess();
    }

    @Deprecated
    /* loaded from: classes11.dex */
    public interface OnMarkerClickListener {
        boolean onMarkerClick(@NonNull Marker marker);
    }

    /* loaded from: classes11.dex */
    public interface OnMoveListener {
        void onMove(@NonNull MoveGestureDetector moveGestureDetector);

        void onMoveBegin(@NonNull MoveGestureDetector moveGestureDetector);

        void onMoveEnd(@NonNull MoveGestureDetector moveGestureDetector);
    }

    @Deprecated
    /* loaded from: classes11.dex */
    public interface OnPolygonClickListener {
        void onPolygonClick(@NonNull Polygon polygon);
    }

    @Deprecated
    /* loaded from: classes11.dex */
    public interface OnPolylineClickListener {
        void onPolylineClick(@NonNull Polyline polyline);
    }

    /* loaded from: classes11.dex */
    public interface OnRotateListener {
        void onRotate(@NonNull RotateGestureDetector rotateGestureDetector);

        void onRotateBegin(@NonNull RotateGestureDetector rotateGestureDetector);

        void onRotateEnd(@NonNull RotateGestureDetector rotateGestureDetector);
    }

    /* loaded from: classes11.dex */
    public interface OnScaleListener {
        void onScale(@NonNull StandardScaleGestureDetector standardScaleGestureDetector);

        void onScaleBegin(@NonNull StandardScaleGestureDetector standardScaleGestureDetector);

        void onScaleEnd(@NonNull StandardScaleGestureDetector standardScaleGestureDetector);
    }

    /* loaded from: classes11.dex */
    public interface OnShoveListener {
        void onShove(@NonNull ShoveGestureDetector shoveGestureDetector);

        void onShoveBegin(@NonNull ShoveGestureDetector shoveGestureDetector);

        void onShoveEnd(@NonNull ShoveGestureDetector shoveGestureDetector);
    }

    /* loaded from: classes11.dex */
    public interface SnapshotReadyCallback {
        void onSnapshotReady(@NonNull Bitmap bitmap);
    }

    /* loaded from: classes11.dex */
    public class a implements IndoorListener {
        public a() {
        }

        @Override // com.mappls.sdk.maps.widgets.indoor.iface.IndoorListener
        public void hideControl() {
            MapplsMap.this.getUiSettings().b().onFloorsChange(new ArrayList());
            MapplsMap.this.getUiSettings().b().setFloor(0);
        }

        @Override // com.mappls.sdk.maps.widgets.indoor.iface.IndoorListener
        public void showControl(int i, int i2, int i3) {
            MapplsMap.this.getUiSettings().b().onFloorsChange(IndoorConstants.getFloors(i3, i));
        }
    }

    /* loaded from: classes11.dex */
    public class b implements CameraMapplsPinCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CancelableCallback f12645a;

        public b(CancelableCallback cancelableCallback) {
            this.f12645a = cancelableCallback;
        }

        @Override // com.mappls.sdk.maps.camera.CameraMapplsPinCallback
        public void getCameraUpdate(CameraUpdate cameraUpdate) {
            MapplsMap.this.moveCamera(cameraUpdate, this.f12645a);
        }

        @Override // com.mappls.sdk.maps.camera.CameraMapplsPinCallback
        public void onError() {
            CancelableCallback cancelableCallback = this.f12645a;
            if (cancelableCallback != null) {
                cancelableCallback.onCancel();
            }
        }
    }

    /* loaded from: classes11.dex */
    public class c implements CameraMapplsPinCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ int f12646a;
        public final /* synthetic */ boolean b;
        public final /* synthetic */ CancelableCallback c;

        public c(int i, boolean z, CancelableCallback cancelableCallback) {
            this.f12646a = i;
            this.b = z;
            this.c = cancelableCallback;
        }

        @Override // com.mappls.sdk.maps.camera.CameraMapplsPinCallback
        public void getCameraUpdate(CameraUpdate cameraUpdate) {
            MapplsMap.this.easeCamera(cameraUpdate, this.f12646a, this.b, this.c);
        }

        @Override // com.mappls.sdk.maps.camera.CameraMapplsPinCallback
        public void onError() {
            CancelableCallback cancelableCallback = this.c;
            if (cancelableCallback != null) {
                cancelableCallback.onFinish();
            }
        }
    }

    /* loaded from: classes11.dex */
    public class d implements CameraMapplsPinCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ int f12647a;
        public final /* synthetic */ CancelableCallback b;

        public d(int i, CancelableCallback cancelableCallback) {
            this.f12647a = i;
            this.b = cancelableCallback;
        }

        @Override // com.mappls.sdk.maps.camera.CameraMapplsPinCallback
        public void getCameraUpdate(CameraUpdate cameraUpdate) {
            MapplsMap.this.animateCamera(cameraUpdate, this.f12647a, this.b);
        }

        @Override // com.mappls.sdk.maps.camera.CameraMapplsPinCallback
        public void onError() {
            CancelableCallback cancelableCallback = this.b;
            if (cancelableCallback != null) {
                cancelableCallback.onFinish();
            }
        }
    }

    /* loaded from: classes11.dex */
    public class e implements Style.OnStyleLoaded {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OnStyleLoadListener f12648a;

        public e(OnStyleLoadListener onStyleLoadListener) {
            this.f12648a = onStyleLoadListener;
        }

        @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
        public void onStyleLoaded(@NonNull Style style) {
            MapplsMap.this.u = null;
            OnStyleLoadListener onStyleLoadListener = this.f12648a;
            if (onStyleLoadListener != null) {
                onStyleLoadListener.onStyleLoaded(style);
            }
        }
    }

    /* loaded from: classes11.dex */
    public class f implements Callback<List<InteractiveLayer>> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ InteractiveLayerLoadingListener f12649a;

        public f(InteractiveLayerLoadingListener interactiveLayerLoadingListener) {
            this.f12649a = interactiveLayerLoadingListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(@NonNull Call<List<InteractiveLayer>> call, @NonNull Throwable th) {
            th.printStackTrace();
        }

        @Override // retrofit2.Callback
        public void onResponse(@NonNull Call<List<InteractiveLayer>> call, @NonNull Response<List<InteractiveLayer>> response) {
            if (response.isSuccessful()) {
                MapplsMap.this.p = response.body();
                Collections.reverse(MapplsMap.this.p);
                InteractiveLayerLoadingListener interactiveLayerLoadingListener = this.f12649a;
                if (interactiveLayerLoadingListener != null) {
                    interactiveLayerLoadingListener.onLayersLoaded(MapplsMap.this.p);
                }
                if (MapplsMap.this.s == null || MapplsMap.this.p == null) {
                    return;
                }
                MapplsMap.this.s.addLayers(MapplsMap.this.p);
            }
        }
    }

    /* loaded from: classes11.dex */
    public interface g {
        void a(OnRotateListener onRotateListener);

        void b(OnShoveListener onShoveListener);

        void c(OnScaleListener onScaleListener);

        void d(OnMapClickListener onMapClickListener);

        void e(OnMapLongClickListener onMapLongClickListener);

        void f(OnMapLongClickListener onMapLongClickListener);

        void g(AndroidGesturesManager androidGesturesManager, boolean z, boolean z2);

        AndroidGesturesManager h();

        void i(OnFlingListener onFlingListener);

        void j(OnRotateListener onRotateListener);

        void k(OnMapClickListener onMapClickListener);

        void l(OnFlingListener onFlingListener);

        void m(OnMoveListener onMoveListener);

        void n(OnScaleListener onScaleListener);

        void o(OnShoveListener onShoveListener);

        void p(OnMoveListener onMoveListener);

        void q();
    }

    public MapplsMap(s sVar, Transform transform, UiSettings uiSettings, Projection projection, g gVar, com.mappls.sdk.maps.g gVar2, List<OnDeveloperAnimationListener> list) {
        this.f12643a = sVar;
        this.b = uiSettings;
        this.c = projection;
        this.d = transform;
        this.f = gVar;
        this.e = gVar2;
        this.h = list;
    }

    public final void A(@NonNull MapplsMapOptions mapplsMapOptions) {
        String apiBaseUrl = mapplsMapOptions.getApiBaseUrl();
        if (TextUtils.isEmpty(apiBaseUrl)) {
            return;
        }
        this.f12643a.u(apiBaseUrl);
    }

    public final void B(@NonNull MapplsMapOptions mapplsMapOptions) {
        if (!mapplsMapOptions.getPrefetchesTiles()) {
            setPrefetchZoomDelta(0);
        } else {
            setPrefetchZoomDelta(mapplsMapOptions.getPrefetchZoomDelta());
        }
    }

    @NonNull
    @Deprecated
    public Marker addMarker(@NonNull MarkerOptions markerOptions) {
        return this.k.a(markerOptions, this, null);
    }

    @NonNull
    @Deprecated
    public List<Marker> addMarkers(@NonNull List<? extends BaseMarkerOptions> list) {
        return this.k.b(list, this, null);
    }

    public void addOnCameraIdleListener(@NonNull OnCameraIdleListener onCameraIdleListener) {
        this.e.f(onCameraIdleListener);
    }

    public void addOnCameraMoveCancelListener(@NonNull OnCameraMoveCanceledListener onCameraMoveCanceledListener) {
        this.e.g(onCameraMoveCanceledListener);
    }

    public void addOnCameraMoveListener(@NonNull OnCameraMoveListener onCameraMoveListener) {
        this.e.h(onCameraMoveListener);
    }

    public void addOnCameraMoveStartedListener(@NonNull OnCameraMoveStartedListener onCameraMoveStartedListener) {
        this.e.i(onCameraMoveStartedListener);
    }

    public void addOnFlingListener(@NonNull OnFlingListener onFlingListener) {
        this.f.l(onFlingListener);
    }

    public void addOnIndoorListener(@NonNull IndoorListener indoorListener) {
        if (getUiSettings().b() != null) {
            getUiSettings().b().addOnIndoorListener(indoorListener);
        }
    }

    public void addOnMapClickListener(@NonNull OnMapClickListener onMapClickListener) {
        this.f.k(onMapClickListener);
    }

    public void addOnMapLongClickListener(@NonNull OnMapLongClickListener onMapLongClickListener) {
        this.f.f(onMapLongClickListener);
    }

    public void addOnMoveListener(@NonNull OnMoveListener onMoveListener) {
        this.f.m(onMoveListener);
    }

    public void addOnRotateListener(@NonNull OnRotateListener onRotateListener) {
        this.f.j(onRotateListener);
    }

    public void addOnScaleListener(@NonNull OnScaleListener onScaleListener) {
        this.f.n(onScaleListener);
    }

    public void addOnShoveListener(@NonNull OnShoveListener onShoveListener) {
        this.f.o(onShoveListener);
    }

    @NonNull
    @Deprecated
    public Polygon addPolygon(@NonNull PolygonOptions polygonOptions) {
        return this.k.c(polygonOptions, this);
    }

    @NonNull
    @Deprecated
    public List<Polygon> addPolygons(@NonNull List<PolygonOptions> list) {
        return this.k.d(list, this);
    }

    @NonNull
    @Deprecated
    public Polyline addPolyline(@NonNull PolylineOptions polylineOptions) {
        return this.k.e(polylineOptions, this);
    }

    @NonNull
    @Deprecated
    public List<Polyline> addPolylines(@NonNull List<PolylineOptions> list) {
        return this.k.f(list, this);
    }

    public final void animateCamera(@NonNull CameraUpdate cameraUpdate) {
        animateCamera(cameraUpdate, 300, (CancelableCallback) null);
    }

    public void cancelAllVelocityAnimations() {
        this.f.q();
    }

    public void cancelTransitions() {
        this.d.f();
    }

    @Deprecated
    public void clear() {
        this.k.D();
    }

    @Deprecated
    public void cycleDebugOptions() {
        boolean z = !this.f12643a.t0();
        this.n = z;
        this.f12643a.setDebug(z);
    }

    @Deprecated
    public void deselectMarker(@NonNull Marker marker) {
        this.k.i(marker);
    }

    @Deprecated
    public void deselectMarkers() {
        this.k.j();
    }

    public void disableDem() {
        c0 c0Var = this.r;
        if (c0Var != null) {
            c0Var.m(false);
        }
    }

    public void disableMonuments() {
        c0 c0Var = this.r;
        if (c0Var != null) {
            c0Var.n(false);
        }
    }

    public void e(InteractiveLayerLoadingListener interactiveLayerLoadingListener) {
        List<InteractiveLayer> list = this.p;
        if (list == null || list.size() <= 0) {
            MapplsCovidLayerList.a().build().enqueueCall(new f(interactiveLayerLoadingListener));
        } else if (interactiveLayerLoadingListener != null) {
            interactiveLayerLoadingListener.onLayersLoaded(this.p);
        }
    }

    public final void easeCamera(@NonNull CameraUpdate cameraUpdate) {
        easeCamera(cameraUpdate, 300);
    }

    public void enableDem() {
        c0 c0Var = this.r;
        if (c0Var != null) {
            c0Var.m(true);
        }
    }

    public void enableMonuments() {
        c0 c0Var = this.r;
        if (c0Var != null) {
            c0Var.n(true);
        }
    }

    public void enableTraffic(boolean z) {
        e0 e0Var = this.t;
        if (e0Var != null) {
            e0Var.v(z);
        }
    }

    public void enableTrafficClosure(boolean z) {
        e0 e0Var = this.t;
        if (e0Var != null) {
            e0Var.b(z);
        }
    }

    public void enableTrafficFreeFlow(boolean z) {
        e0 e0Var = this.t;
        if (e0Var != null) {
            e0Var.c(z);
        }
    }

    public void enableTrafficNonFreeFlow(boolean z) {
        e0 e0Var = this.t;
        if (e0Var != null) {
            e0Var.d(z);
        }
    }

    public void enableTrafficOther1(boolean z) {
        e0 e0Var = this.t;
        if (e0Var != null) {
            e0Var.e(z);
        }
    }

    public void enableTrafficOther2(boolean z) {
        e0 e0Var = this.t;
        if (e0Var != null) {
            e0Var.f(z);
        }
    }

    public void enableTrafficOther3(boolean z) {
        e0 e0Var = this.t;
        if (e0Var != null) {
            e0Var.g(z);
        }
    }

    public void enableTrafficOther4(boolean z) {
        e0 e0Var = this.t;
        if (e0Var != null) {
            e0Var.h(z);
        }
    }

    public void enableTrafficOther5(boolean z) {
        e0 e0Var = this.t;
        if (e0Var != null) {
            e0Var.i(z);
        }
    }

    public void enableTrafficStopIcon(boolean z) {
        e0 e0Var = this.t;
        if (e0Var != null) {
            e0Var.j(z);
        }
    }

    public void f(@NonNull Context context, @NonNull MapplsMapOptions mapplsMapOptions) {
        this.d.q(this, mapplsMapOptions);
        UiSettings uiSettings = this.b;
        if (uiSettings != null) {
            uiSettings.d(context, mapplsMapOptions);
        }
        setDebugActive(mapplsMapOptions.getDebugActive());
        A(mapplsMapOptions);
        B(mapplsMapOptions);
    }

    public void g(com.mappls.sdk.maps.b bVar) {
        this.k = bVar.h(this);
    }

    @Nullable
    @Deprecated
    public Annotation getAnnotation(long j) {
        return this.k.k(j);
    }

    @NonNull
    @Deprecated
    public List<Annotation> getAnnotations() {
        return this.k.l();
    }

    @Nullable
    public CameraPosition getCameraForGeometry(@NonNull Geometry geometry) {
        return getCameraForGeometry(geometry, new int[]{0, 0, 0, 0});
    }

    @Nullable
    public CameraPosition getCameraForLatLngBounds(@NonNull LatLngBounds latLngBounds) {
        return getCameraForLatLngBounds(latLngBounds, new int[]{0, 0, 0, 0});
    }

    @NonNull
    public final CameraPosition getCameraPosition() {
        return this.d.getCameraPosition();
    }

    @NonNull
    public AndroidGesturesManager getGesturesManager() {
        return this.f.h();
    }

    public float getHeight() {
        return this.c.d();
    }

    @Nullable
    @Deprecated
    public InfoWindowAdapter getInfoWindowAdapter() {
        return this.k.m().b();
    }

    public void getInteractiveLayer(InteractiveLayerLoadingListener interactiveLayerLoadingListener) {
        e(interactiveLayerLoadingListener);
    }

    @NonNull
    public LocationComponent getLocationComponent() {
        return this.j;
    }

    public List<MapplsStyle> getMapplsAvailableStyles() {
        return this.f12643a.o0();
    }

    @NonNull
    @Deprecated
    public List<Marker> getMarkers() {
        return this.k.o();
    }

    public double getMaxPitch() {
        return this.d.j();
    }

    public double getMaxZoomLevel() {
        return this.d.k();
    }

    public double getMinPitch() {
        return this.d.l();
    }

    public double getMinZoomLevel() {
        return this.d.m();
    }

    @Nullable
    public OnInfoWindowClickListener getOnInfoWindowClickListener() {
        return this.k.m().c();
    }

    @Nullable
    public OnInfoWindowCloseListener getOnInfoWindowCloseListener() {
        return this.k.m().d();
    }

    @Nullable
    public OnInfoWindowLongClickListener getOnInfoWindowLongClickListener() {
        return this.k.m().e();
    }

    @NonNull
    @Deprecated
    public int[] getPadding() {
        return this.c.c();
    }

    @NonNull
    @Deprecated
    public List<Polygon> getPolygons() {
        return this.k.q();
    }

    @NonNull
    @Deprecated
    public List<Polyline> getPolylines() {
        return this.k.r();
    }

    @IntRange(from = 0)
    public int getPrefetchZoomDelta() {
        return this.f12643a.w();
    }

    @Deprecated
    public boolean getPrefetchesTiles() {
        return this.f12643a.A0();
    }

    @NonNull
    public Projection getProjection() {
        return this.c;
    }

    @NonNull
    @Deprecated
    public List<Marker> getSelectedMarkers() {
        return this.k.s();
    }

    public void getStyle(@NonNull Style.OnStyleLoaded onStyleLoaded) {
        Style style = this.m;
        if (style != null && style.isFullyLoaded()) {
            onStyleLoaded.onStyleLoaded(this.m);
        } else {
            this.g.add(onStyleLoaded);
        }
    }

    @Nullable
    public UiSettings getUiSettings() {
        return this.b;
    }

    public List<InteractiveLayer> getVisibleInteractiveLayer() {
        RasterPlugin rasterPlugin = this.s;
        if (rasterPlugin != null) {
            rasterPlugin.getVisibleInteractiveLayer();
            return null;
        }
        return null;
    }

    public float getWidth() {
        return this.c.f();
    }

    public void h(RasterPlugin rasterPlugin) {
        this.s = rasterPlugin;
    }

    public void hideInteractiveLayer(InteractiveLayer interactiveLayer) {
        RasterPlugin rasterPlugin;
        if (interactiveLayer == null || (rasterPlugin = this.s) == null) {
            return;
        }
        rasterPlugin.hideLayer(interactiveLayer);
    }

    public void i(LocationComponent locationComponent) {
        this.j = locationComponent;
    }

    @Deprecated
    public boolean isAllowConcurrentMultipleOpenInfoWindows() {
        return this.k.m().f();
    }

    public boolean isDebugActive() {
        return this.n;
    }

    public boolean isEnableTraffic() {
        e0 e0Var = this.t;
        if (e0Var != null) {
            return e0Var.k();
        }
        return false;
    }

    public boolean isEnableTrafficClosure() {
        e0 e0Var = this.t;
        if (e0Var != null) {
            return e0Var.l();
        }
        return false;
    }

    public boolean isEnableTrafficFreeFlow() {
        e0 e0Var = this.t;
        if (e0Var != null) {
            return e0Var.m();
        }
        return false;
    }

    public boolean isEnableTrafficNonFreeFlow() {
        e0 e0Var = this.t;
        if (e0Var != null) {
            return e0Var.n();
        }
        return false;
    }

    public boolean isEnableTrafficOther1() {
        e0 e0Var = this.t;
        if (e0Var != null) {
            return e0Var.o();
        }
        return false;
    }

    public boolean isEnableTrafficOther2() {
        e0 e0Var = this.t;
        if (e0Var != null) {
            return e0Var.p();
        }
        return false;
    }

    public boolean isEnableTrafficOther3() {
        e0 e0Var = this.t;
        if (e0Var != null) {
            return e0Var.q();
        }
        return false;
    }

    public boolean isEnableTrafficOther4() {
        e0 e0Var = this.t;
        if (e0Var != null) {
            return e0Var.r();
        }
        return false;
    }

    public boolean isEnableTrafficOther5() {
        e0 e0Var = this.t;
        if (e0Var != null) {
            return e0Var.s();
        }
        return false;
    }

    public boolean isEnableTrafficStopIcon() {
        e0 e0Var = this.t;
        if (e0Var != null) {
            return e0Var.t();
        }
        return false;
    }

    public void j(u uVar) {
        this.v = uVar;
    }

    public void k(SafetyStripView safetyStripView) {
        this.q = safetyStripView;
    }

    public void l(c0 c0Var) {
        this.r = c0Var;
    }

    public void m(e0 e0Var) {
        this.t = e0Var;
    }

    public final void moveCamera(@NonNull CameraUpdate cameraUpdate) {
        moveCamera(cameraUpdate, (CancelableCallback) null);
    }

    public final void n() {
        for (OnDeveloperAnimationListener onDeveloperAnimationListener : this.h) {
            onDeveloperAnimationListener.onDeveloperAnimationStarted();
        }
    }

    public void o() {
        if (this.f12643a.isDestroyed()) {
            return;
        }
        Style style = this.m;
        if (style != null) {
            style.b();
            this.j.onFinishLoadingStyle();
            Style.OnStyleLoaded onStyleLoaded = this.i;
            if (onStyleLoaded != null) {
                onStyleLoaded.onStyleLoaded(this.m);
            }
            for (Style.OnStyleLoaded onStyleLoaded2 : this.g) {
                onStyleLoaded2.onStyleLoaded(this.m);
            }
        } else {
            MapStrictMode.strictModeViolation("No style to provide.");
        }
        this.i = null;
        this.g.clear();
    }

    public void p() {
        this.j.onDestroy();
        Style style = this.m;
        if (style != null) {
            style.a();
        }
        this.e.n();
    }

    public void q() {
        this.i = null;
        OnStyleLoadListener onStyleLoadListener = this.u;
        if (onStyleLoadListener != null) {
            onStyleLoadListener.onError("Fail to load style");
            this.u = null;
        }
    }

    @NonNull
    public List<Feature> queryRenderedFeatures(@NonNull PointF pointF, @Nullable String... strArr) {
        return this.f12643a.d(pointF, strArr, null);
    }

    public void r() {
        o();
    }

    @Deprecated
    public void removeAnnotation(@NonNull Annotation annotation) {
        this.k.C(annotation);
    }

    @Deprecated
    public void removeAnnotations(@NonNull List<? extends Annotation> list) {
        this.k.E(list);
    }

    public boolean removeIndoorListener(IndoorListener indoorListener) {
        if (getUiSettings().b() != null) {
            return getUiSettings().b().removeIndoorListener(indoorListener);
        }
        return false;
    }

    @Deprecated
    public void removeMarker(@NonNull Marker marker) {
        this.k.C(marker);
    }

    public void removeOnCameraIdleListener(@NonNull OnCameraIdleListener onCameraIdleListener) {
        this.e.o(onCameraIdleListener);
    }

    public void removeOnCameraMoveCancelListener(@NonNull OnCameraMoveCanceledListener onCameraMoveCanceledListener) {
        this.e.p(onCameraMoveCanceledListener);
    }

    public void removeOnCameraMoveListener(@NonNull OnCameraMoveListener onCameraMoveListener) {
        this.e.q(onCameraMoveListener);
    }

    public void removeOnCameraMoveStartedListener(@NonNull OnCameraMoveStartedListener onCameraMoveStartedListener) {
        this.e.r(onCameraMoveStartedListener);
    }

    public void removeOnFlingListener(@NonNull OnFlingListener onFlingListener) {
        this.f.i(onFlingListener);
    }

    public void removeOnMapClickListener(@NonNull OnMapClickListener onMapClickListener) {
        this.f.d(onMapClickListener);
    }

    public void removeOnMapLongClickListener(@NonNull OnMapLongClickListener onMapLongClickListener) {
        this.f.e(onMapLongClickListener);
    }

    public void removeOnMoveListener(@NonNull OnMoveListener onMoveListener) {
        this.f.p(onMoveListener);
    }

    public void removeOnRotateListener(@NonNull OnRotateListener onRotateListener) {
        this.f.a(onRotateListener);
    }

    public void removeOnScaleListener(@NonNull OnScaleListener onScaleListener) {
        this.f.c(onScaleListener);
    }

    public void removeOnShoveListener(@NonNull OnShoveListener onShoveListener) {
        this.f.b(onShoveListener);
    }

    @Deprecated
    public void removePolygon(@NonNull Polygon polygon) {
        this.k.C(polygon);
    }

    @Deprecated
    public void removePolyline(@NonNull Polyline polyline) {
        this.k.C(polyline);
    }

    public void resetNorth() {
        n();
        this.d.v();
    }

    public void s() {
        this.d.r();
    }

    public void scrollBy(float f2, float f3) {
        scrollBy(f2, f3, 0L);
    }

    @Deprecated
    public void selectMarker(@NonNull Marker marker) {
        if (marker == null) {
            Logger.w("Mbgl-MapplsMap", "marker was null, so just returning");
        } else {
            this.k.F(marker);
        }
    }

    @Deprecated
    public void setAllowConcurrentMultipleOpenInfoWindows(boolean z) {
        this.k.m().h(z);
    }

    public void setCameraMapplsPinPosition(@NonNull CameraMapplsPinPosition cameraMapplsPinPosition) {
        moveCamera(CameraMapplsPinUpdateFactory.newCameraPosition(cameraMapplsPinPosition), (CancelableCallback) null);
    }

    public void setCameraPosition(@NonNull CameraPosition cameraPosition) {
        moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), (CancelableCallback) null);
    }

    public void setDebugActive(boolean z) {
        this.n = z;
        this.f12643a.setDebug(z);
    }

    public void setFloor(int i) {
        if (getUiSettings() == null || getUiSettings().b() == null) {
            return;
        }
        getUiSettings().b().setFloor(i);
    }

    public void setFocalBearing(double d2, float f2, float f3, long j) {
        n();
        this.d.x(d2, f2, f3, j);
    }

    public void setGesturesManager(@NonNull AndroidGesturesManager androidGesturesManager, boolean z, boolean z2) {
        this.f.g(androidGesturesManager, z, z2);
    }

    @Deprecated
    public void setInfoWindowAdapter(@Nullable InfoWindowAdapter infoWindowAdapter) {
        this.k.m().i(infoWindowAdapter);
    }

    public void setLatLngBoundsForCameraTarget(@Nullable LatLngBounds latLngBounds) {
        this.f12643a.P(latLngBounds);
    }

    public void setMapplsStyle(String str) {
        this.u = null;
        setStyle(new Style.Builder().fromMapplsStyle(str));
    }

    public void setMaxPitchPreference(@FloatRange(from = 0.0d, to = 60.0d) double d2) {
        this.d.z(d2);
    }

    public void setMaxZoomPreference(@FloatRange(from = 1.0d, to = 22.0d) double d2) {
        this.d.A(d2);
    }

    public void setMinPitchPreference(@FloatRange(from = 0.0d, to = 60.0d) double d2) {
        this.d.B(d2);
    }

    public void setMinZoomPreference(@FloatRange(from = 1.0d, to = 22.0d) double d2) {
        this.d.C(d2);
    }

    public void setOfflineRegionDefinition(@NonNull OfflineRegionDefinition offlineRegionDefinition) {
        setOfflineRegionDefinition(offlineRegionDefinition, null);
    }

    public void setOnFpsChangedListener(@Nullable OnFpsChangedListener onFpsChangedListener) {
        this.l = onFpsChangedListener;
        this.f12643a.x0(onFpsChangedListener);
    }

    public void setOnInfoWindowClickListener(@Nullable OnInfoWindowClickListener onInfoWindowClickListener) {
        this.k.m().j(onInfoWindowClickListener);
    }

    public void setOnInfoWindowCloseListener(@Nullable OnInfoWindowCloseListener onInfoWindowCloseListener) {
        this.k.m().k(onInfoWindowCloseListener);
    }

    public void setOnInfoWindowLongClickListener(@Nullable OnInfoWindowLongClickListener onInfoWindowLongClickListener) {
        this.k.m().l(onInfoWindowLongClickListener);
    }

    public void setOnInteractiveLayerClickListener(OnInteractiveLayerClickListener onInteractiveLayerClickListener) {
        RasterPlugin rasterPlugin = this.s;
        if (rasterPlugin != null) {
            rasterPlugin.setOnInteractiveLayerClickListener(onInteractiveLayerClickListener);
        }
    }

    @Deprecated
    public void setOnMarkerClickListener(@Nullable OnMarkerClickListener onMarkerClickListener) {
        this.k.G(onMarkerClickListener);
    }

    public void setOnPlaceClickListener(@NonNull OnPlaceClickListener onPlaceClickListener) {
        this.v.a(onPlaceClickListener);
    }

    @Deprecated
    public void setOnPolygonClickListener(@Nullable OnPolygonClickListener onPolygonClickListener) {
        this.k.H(onPolygonClickListener);
    }

    @Deprecated
    public void setOnPolylineClickListener(@Nullable OnPolylineClickListener onPolylineClickListener) {
        this.k.I(onPolylineClickListener);
    }

    @Deprecated
    public void setPadding(int i, int i2, int i3, int i4) {
        this.c.h(new int[]{i, i2, i3, i4});
        UiSettings uiSettings = this.b;
        if (uiSettings != null) {
            uiSettings.invalidate();
        }
    }

    public void setPrefetchZoomDelta(@IntRange(from = 0) int i) {
        this.f12643a.H0(i);
    }

    @Deprecated
    public void setPrefetchesTiles(boolean z) {
        this.f12643a.m0(z);
    }

    public void setStyle(Style.Builder builder) {
        setStyle(builder, null);
    }

    public void showCurrentLocationSafety() {
        if (this.q == null || !this.j.isLocationComponentEnabled()) {
            return;
        }
        this.q.updatedSafetyStatus(this.j.getLastKnownLocation());
    }

    public void showInteractiveLayer(InteractiveLayer interactiveLayer) {
        RasterPlugin rasterPlugin;
        if (interactiveLayer == null || (rasterPlugin = this.s) == null) {
            return;
        }
        rasterPlugin.showLayer(interactiveLayer);
    }

    public void showInteractiveLayerInfoWindow(boolean z) {
        RasterPlugin rasterPlugin = this.s;
        if (rasterPlugin != null) {
            rasterPlugin.isShowInfoWindow(z);
        }
    }

    public void snapshot(@NonNull SnapshotReadyCallback snapshotReadyCallback) {
        if (this.o) {
            this.f12643a.z(snapshotReadyCallback);
        }
    }

    public void t() {
        this.d.r();
        this.k.A();
        this.k.g(this);
        if (getUiSettings() != null && getUiSettings().a() != null && getUiSettings().a().getMap() == null) {
            getUiSettings().a().setMap(this);
        }
        if (getUiSettings() == null || getUiSettings().b() == null || getUiSettings().b().getMap() != null) {
            return;
        }
        getUiSettings().b().setMap(this);
        getUiSettings().b().addOnIndoorListener(new a());
    }

    public void triggerRepaint() {
        this.f12643a.x();
    }

    public void u(@NonNull Bundle bundle) {
        CameraPosition cameraPosition = (CameraPosition) bundle.getParcelable(MapplsConstants.STATE_CAMERA_POSITION);
        UiSettings uiSettings = this.b;
        if (uiSettings != null) {
            uiSettings.l(bundle);
        }
        if (cameraPosition != null) {
            moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder(cameraPosition).build()));
        }
        this.f12643a.setDebug(bundle.getBoolean(MapplsConstants.STATE_DEBUG_ACTIVE));
    }

    @Deprecated
    public void updateMarker(@NonNull Marker marker) {
        this.k.L(marker, this, null);
    }

    @Deprecated
    public void updatePolygon(@NonNull Polygon polygon) {
        this.k.M(polygon);
    }

    @Deprecated
    public void updatePolyline(@NonNull Polyline polyline) {
        this.k.N(polyline);
    }

    public void v(@NonNull Bundle bundle) {
        bundle.putParcelable(MapplsConstants.STATE_CAMERA_POSITION, this.d.getCameraPosition());
        bundle.putBoolean(MapplsConstants.STATE_DEBUG_ACTIVE, isDebugActive());
        UiSettings uiSettings = this.b;
        if (uiSettings != null) {
            uiSettings.m(bundle);
        }
    }

    public void w() {
        this.o = true;
        if (TextUtils.isEmpty(this.f12643a.p0()) && TextUtils.isEmpty(this.f12643a.N())) {
            setStyle(new Style.Builder());
        }
        this.j.onStart();
    }

    public void x() {
        this.o = false;
        this.j.onStop();
    }

    public void y() {
        UiSettings uiSettings;
        CameraPosition r = this.d.r();
        if (r == null || (uiSettings = this.b) == null) {
            return;
        }
        uiSettings.M(r);
    }

    public void z() {
        this.k.K();
    }

    @NonNull
    @Deprecated
    public Marker addMarker(@NonNull BaseMarkerOptions baseMarkerOptions) {
        return this.k.a(baseMarkerOptions, this, null);
    }

    @NonNull
    @Deprecated
    public List<Marker> addMarkers(@NonNull List<? extends BaseMarkerOptions> list, OnMarkerAddedListener onMarkerAddedListener) {
        return this.k.b(list, this, onMarkerAddedListener);
    }

    public final void animateCamera(@NonNull CameraUpdate cameraUpdate, @Nullable CancelableCallback cancelableCallback) {
        animateCamera(cameraUpdate, 300, cancelableCallback);
    }

    public final void easeCamera(@NonNull CameraUpdate cameraUpdate, @Nullable CancelableCallback cancelableCallback) {
        easeCamera(cameraUpdate, 300, cancelableCallback);
    }

    @Nullable
    public CameraPosition getCameraForGeometry(@NonNull Geometry geometry, @NonNull @Size(4) int[] iArr) {
        return getCameraForGeometry(geometry, iArr, this.d.i(), this.d.p());
    }

    @Nullable
    public CameraPosition getCameraForLatLngBounds(@NonNull LatLngBounds latLngBounds, @NonNull @Size(4) int[] iArr) {
        return getCameraForLatLngBounds(latLngBounds, iArr, this.d.n(), this.d.p());
    }

    public final void moveCamera(@NonNull CameraUpdate cameraUpdate, @Nullable CancelableCallback cancelableCallback) {
        n();
        this.d.moveCamera(this, cameraUpdate, cancelableCallback);
    }

    @NonNull
    public List<Feature> queryRenderedFeatures(@NonNull PointF pointF, @Nullable Expression expression, @Nullable String... strArr) {
        return this.f12643a.d(pointF, strArr, expression);
    }

    @Deprecated
    public void removeAnnotation(long j) {
        this.k.B(j);
    }

    @Deprecated
    public void removeAnnotations() {
        this.k.D();
    }

    public void scrollBy(float f2, float f3, long j) {
        n();
        this.f12643a.B0(f2, f3, j);
    }

    public void setOfflineRegionDefinition(@NonNull OfflineRegionDefinition offlineRegionDefinition, @Nullable Style.OnStyleLoaded onStyleLoaded) {
        double minZoom = offlineRegionDefinition.getMinZoom();
        double maxZoom = offlineRegionDefinition.getMaxZoom();
        moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(offlineRegionDefinition.getBounds().getCenter()).zoom(minZoom).build()));
        setMinZoomPreference(minZoom);
        setMaxZoomPreference(maxZoom);
        setStyle(new Style.Builder());
    }

    public void setStyle(Style.Builder builder, Style.OnStyleLoaded onStyleLoaded) {
        this.i = onStyleLoaded;
        this.j.onStartLoadingMap();
        Style style = this.m;
        if (style != null) {
            style.a();
        }
        this.m = builder.e(this.f12643a);
        if (!TextUtils.isEmpty(builder.getMapplsStyle())) {
            if (Mappls.getStyleHelper().getStyle(builder.getMapplsStyle()) != null) {
                this.f12643a.m(Mappls.getStyleHelper().getStyle(builder.getMapplsStyle()));
            } else {
                Timber.e("%s style not found", builder.getMapplsStyle());
            }
        } else if (!TextUtils.isEmpty(builder.getJson())) {
            this.f12643a.l(builder.getJson());
        } else {
            this.f12643a.l("{\"version\": 8,\"sources\": {},\"layers\": []}");
        }
    }

    @Deprecated
    public void updateMarker(@NonNull Marker marker, OnMarkerAddedListener onMarkerAddedListener) {
        this.k.L(marker, this, onMarkerAddedListener);
    }

    @NonNull
    @Deprecated
    public Marker addMarker(@NonNull MarkerOptions markerOptions, OnMarkerAddedListener onMarkerAddedListener) {
        return this.k.a(markerOptions, this, onMarkerAddedListener);
    }

    public final void animateCamera(@NonNull CameraUpdate cameraUpdate, int i) {
        animateCamera(cameraUpdate, i, (CancelableCallback) null);
    }

    public final void easeCamera(@NonNull CameraUpdate cameraUpdate, int i) {
        easeCamera(cameraUpdate, i, (CancelableCallback) null);
    }

    @Nullable
    public CameraPosition getCameraForGeometry(@NonNull Geometry geometry, @FloatRange(from = 0.0d, to = 360.0d) double d2, @FloatRange(from = 0.0d, to = 60.0d) double d3) {
        return getCameraForGeometry(geometry, new int[]{0, 0, 0, 0}, d2, d3);
    }

    @Nullable
    public CameraPosition getCameraForLatLngBounds(@NonNull LatLngBounds latLngBounds, @FloatRange(from = 0.0d, to = 360.0d) double d2, @FloatRange(from = 0.0d, to = 60.0d) double d3) {
        return getCameraForLatLngBounds(latLngBounds, new int[]{0, 0, 0, 0}, d2, d3);
    }

    @NonNull
    public List<Feature> queryRenderedFeatures(@NonNull RectF rectF, @Nullable String... strArr) {
        return this.f12643a.k0(rectF, strArr, null);
    }

    public void setMapplsStyle(String str, Style.OnStyleLoaded onStyleLoaded) {
        this.u = null;
        setStyle(new Style.Builder().fromMapplsStyle(str), onStyleLoaded);
    }

    @NonNull
    @Deprecated
    public Marker addMarker(@NonNull BaseMarkerOptions baseMarkerOptions, OnMarkerAddedListener onMarkerAddedListener) {
        return this.k.a(baseMarkerOptions, this, onMarkerAddedListener);
    }

    public final void animateCamera(@NonNull CameraUpdate cameraUpdate, int i, @Nullable CancelableCallback cancelableCallback) {
        if (i > 0) {
            n();
            this.d.animateCamera(this, cameraUpdate, i, cancelableCallback);
            return;
        }
        throw new IllegalArgumentException("Null duration passed into animateCamera");
    }

    public final void easeCamera(@NonNull CameraUpdate cameraUpdate, int i, @Nullable CancelableCallback cancelableCallback) {
        easeCamera(cameraUpdate, i, true, cancelableCallback);
    }

    @Nullable
    public CameraPosition getCameraForGeometry(@NonNull Geometry geometry, @NonNull @Size(4) int[] iArr, @FloatRange(from = 0.0d, to = 360.0d) double d2, @FloatRange(from = 0.0d, to = 60.0d) double d3) {
        return this.f12643a.t(geometry, iArr, d2, d3);
    }

    @Nullable
    public CameraPosition getCameraForLatLngBounds(@NonNull LatLngBounds latLngBounds, @NonNull @Size(4) int[] iArr, @FloatRange(from = 0.0d, to = 360.0d) double d2, @FloatRange(from = 0.0d, to = 60.0d) double d3) {
        return this.f12643a.i0(latLngBounds, iArr, d2, d3);
    }

    @Nullable
    public Style getStyle() {
        Style style = this.m;
        if (style == null || !style.isFullyLoaded()) {
            return null;
        }
        return this.m;
    }

    public final void moveCamera(@NonNull CameraMapplsPinUpdate cameraMapplsPinUpdate) {
        moveCamera(cameraMapplsPinUpdate, (CancelableCallback) null);
    }

    @NonNull
    public List<Feature> queryRenderedFeatures(@NonNull RectF rectF, @Nullable Expression expression, @Nullable String... strArr) {
        return this.f12643a.k0(rectF, strArr, expression);
    }

    public final void easeCamera(@NonNull CameraUpdate cameraUpdate, int i, boolean z) {
        easeCamera(cameraUpdate, i, z, (CancelableCallback) null);
    }

    public final void moveCamera(@NonNull CameraMapplsPinUpdate cameraMapplsPinUpdate, @Nullable CancelableCallback cancelableCallback) {
        n();
        this.d.moveCamera(this, cameraMapplsPinUpdate, cancelableCallback);
    }

    public void setMapplsStyle(String str, OnStyleLoadListener onStyleLoadListener) {
        this.u = onStyleLoadListener;
        if (Mappls.getStyleHelper().getStyle(str) != null) {
            setStyle(new Style.Builder().fromMapplsStyle(str), new e(onStyleLoadListener));
            return;
        }
        this.u = null;
        if (onStyleLoadListener != null) {
            onStyleLoadListener.onError(str + " style not found");
        }
    }

    public final void easeCamera(@NonNull CameraUpdate cameraUpdate, int i, boolean z, @Nullable CancelableCallback cancelableCallback) {
        if (i > 0) {
            n();
            this.d.h(this, cameraUpdate, i, z, cancelableCallback);
            return;
        }
        throw new IllegalArgumentException("Null duration passed into easeCamera");
    }

    public final void animateCamera(@NonNull CameraMapplsPinUpdate cameraMapplsPinUpdate) {
        animateCamera(cameraMapplsPinUpdate, 300, (CancelableCallback) null);
    }

    public final void moveCamera(@NonNull CameraMapplsPinBoundUpdate cameraMapplsPinBoundUpdate) {
        moveCamera(cameraMapplsPinBoundUpdate, (CancelableCallback) null);
    }

    public final void animateCamera(@NonNull CameraMapplsPinUpdate cameraMapplsPinUpdate, @Nullable CancelableCallback cancelableCallback) {
        animateCamera(cameraMapplsPinUpdate, 300, cancelableCallback);
    }

    public final void moveCamera(@NonNull CameraMapplsPinBoundUpdate cameraMapplsPinBoundUpdate, @Nullable CancelableCallback cancelableCallback) {
        cameraMapplsPinBoundUpdate.getCameraMapplsPinPosition(this, new b(cancelableCallback));
    }

    public final void animateCamera(@NonNull CameraMapplsPinUpdate cameraMapplsPinUpdate, int i) {
        animateCamera(cameraMapplsPinUpdate, i, (CancelableCallback) null);
    }

    public final void easeCamera(@NonNull CameraMapplsPinUpdate cameraMapplsPinUpdate) {
        easeCamera(cameraMapplsPinUpdate, 300);
    }

    public final void animateCamera(@NonNull CameraMapplsPinUpdate cameraMapplsPinUpdate, int i, @Nullable CancelableCallback cancelableCallback) {
        if (i > 0) {
            n();
            this.d.animateCamera(this, cameraMapplsPinUpdate, i, cancelableCallback);
            return;
        }
        throw new IllegalArgumentException("Null duration passed into animateCamera");
    }

    public final void easeCamera(@NonNull CameraMapplsPinUpdate cameraMapplsPinUpdate, @Nullable CancelableCallback cancelableCallback) {
        easeCamera(cameraMapplsPinUpdate, 300, cancelableCallback);
    }

    public final void easeCamera(@NonNull CameraMapplsPinUpdate cameraMapplsPinUpdate, int i) {
        easeCamera(cameraMapplsPinUpdate, i, (CancelableCallback) null);
    }

    public final void easeCamera(@NonNull CameraMapplsPinUpdate cameraMapplsPinUpdate, int i, @Nullable CancelableCallback cancelableCallback) {
        easeCamera(cameraMapplsPinUpdate, i, true, cancelableCallback);
    }

    public final void animateCamera(@NonNull CameraMapplsPinBoundUpdate cameraMapplsPinBoundUpdate) {
        animateCamera(cameraMapplsPinBoundUpdate, 300, (CancelableCallback) null);
    }

    public final void easeCamera(@NonNull CameraMapplsPinUpdate cameraMapplsPinUpdate, int i, boolean z) {
        easeCamera(cameraMapplsPinUpdate, i, z, (CancelableCallback) null);
    }

    public final void animateCamera(@NonNull CameraMapplsPinBoundUpdate cameraMapplsPinBoundUpdate, @Nullable CancelableCallback cancelableCallback) {
        animateCamera(cameraMapplsPinBoundUpdate, 300, cancelableCallback);
    }

    public final void easeCamera(@NonNull CameraMapplsPinUpdate cameraMapplsPinUpdate, int i, boolean z, @Nullable CancelableCallback cancelableCallback) {
        if (i > 0) {
            n();
            this.d.g(this, cameraMapplsPinUpdate, i, z, cancelableCallback);
            return;
        }
        throw new IllegalArgumentException("Null duration passed into easeCamera");
    }

    public final void animateCamera(@NonNull CameraMapplsPinBoundUpdate cameraMapplsPinBoundUpdate, int i) {
        animateCamera(cameraMapplsPinBoundUpdate, i, (CancelableCallback) null);
    }

    public final void animateCamera(@NonNull CameraMapplsPinBoundUpdate cameraMapplsPinBoundUpdate, int i, @Nullable CancelableCallback cancelableCallback) {
        cameraMapplsPinBoundUpdate.getCameraMapplsPinPosition(this, new d(i, cancelableCallback));
    }

    public final void easeCamera(@NonNull CameraMapplsPinBoundUpdate cameraMapplsPinBoundUpdate) {
        easeCamera(cameraMapplsPinBoundUpdate, 300);
    }

    public final void easeCamera(@NonNull CameraMapplsPinBoundUpdate cameraMapplsPinBoundUpdate, @Nullable CancelableCallback cancelableCallback) {
        easeCamera(cameraMapplsPinBoundUpdate, 300, cancelableCallback);
    }

    public final void easeCamera(@NonNull CameraMapplsPinBoundUpdate cameraMapplsPinBoundUpdate, int i) {
        easeCamera(cameraMapplsPinBoundUpdate, i, (CancelableCallback) null);
    }

    public final void easeCamera(@NonNull CameraMapplsPinBoundUpdate cameraMapplsPinBoundUpdate, int i, @Nullable CancelableCallback cancelableCallback) {
        easeCamera(cameraMapplsPinBoundUpdate, i, true, cancelableCallback);
    }

    public final void easeCamera(@NonNull CameraMapplsPinBoundUpdate cameraMapplsPinBoundUpdate, int i, boolean z) {
        easeCamera(cameraMapplsPinBoundUpdate, i, z, (CancelableCallback) null);
    }

    public final void easeCamera(@NonNull CameraMapplsPinBoundUpdate cameraMapplsPinBoundUpdate, int i, boolean z, @Nullable CancelableCallback cancelableCallback) {
        cameraMapplsPinBoundUpdate.getCameraMapplsPinPosition(this, new c(i, z, cancelableCallback));
    }
}

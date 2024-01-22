package com.mappls.sdk.navigation.camera;

import android.location.Location;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.camera.CameraPosition;
import com.mappls.sdk.maps.camera.CameraUpdate;
import com.mappls.sdk.maps.camera.CameraUpdateFactory;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.geometry.LatLngBounds;
import com.mappls.sdk.maps.location.LocationComponent;
import com.mappls.sdk.maps.location.OnCameraTrackingChangedListener;
import com.mappls.sdk.maps.location.OnLocationCameraTransitionListener;
import com.mappls.sdk.navigation.MapplsNavigationHelper;
import com.mappls.sdk.navigation.model.AdviseInfo;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import timber.log.Timber;
@Keep
@UiThread
/* loaded from: classes11.dex */
public class NavigationCamera {
    private static final double MAX_TILT = 60.0d;
    private static final double MIN_TILT = 0.0d;
    public static final int NAVIGATION_TRACKING_MODE_GPS = 0;
    public static final int NAVIGATION_TRACKING_MODE_NONE = 2;
    public static final int NAVIGATION_TRACKING_MODE_NORTH = 1;
    private static final String NULL_ROUTE_ERROR_MESSAGE = "Unable to show route overview, the route is null.";
    private static final int ONE_POINT = 1;
    private com.mappls.sdk.navigation.camera.a animationDelegate;
    private Camera camera;
    private final OnCameraTrackingChangedListener cameraTrackingChangedListener;
    private Location currentLocation;
    @Nullable
    private RouteInformation currentRouteInformation;
    private AdviseInfo currentRouteProgress;
    private boolean isCameraResetting;
    private LocationComponent locationComponent;
    private MapplsMap mapplsMap;
    private INavigation navigation;
    private NavigationMode navigationMode;
    private ProgressChangeListener progressChangeListener;
    private int trackingCameraMode;
    private final CopyOnWriteArraySet<OnTrackingModeTransitionListener> onTrackingModeTransitionListeners = new CopyOnWriteArraySet<>();
    private final CopyOnWriteArraySet<OnTrackingModeChangedListener> onTrackingModeChangedListeners = new CopyOnWriteArraySet<>();
    private final OnLocationCameraTransitionListener cameraTransitionListener = new d(this);

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface TrackingMode {
    }

    /* loaded from: classes11.dex */
    public class a implements ProgressChangeListener {
        public a() {
        }

        @Override // com.mappls.sdk.navigation.camera.ProgressChangeListener
        public void onProgressChange(Location location, RouteInformation routeInformation) {
            NavigationCamera.this.currentRouteProgress = routeInformation.getAdviseInfo();
            NavigationCamera.this.currentLocation = location;
            NavigationCamera.this.tryToBuildRouteInformationAndAdjustCamera();
        }
    }

    public NavigationCamera(@NonNull MapplsMap mapplsMap) {
        c cVar = new c(this);
        this.cameraTrackingChangedListener = cVar;
        this.navigationMode = NavigationMode.DEFAULT;
        this.trackingCameraMode = 0;
        this.progressChangeListener = new a();
        this.mapplsMap = mapplsMap;
        this.locationComponent = mapplsMap.getLocationComponent();
        this.animationDelegate = new com.mappls.sdk.navigation.camera.a(mapplsMap);
        this.locationComponent.addOnCameraTrackingChangedListener(cVar);
        this.camera = new DynamicCamera(mapplsMap);
    }

    private void adjustCameraForReset(@NonNull RouteInformation routeInformation) {
        float tilt = (float) this.camera.tilt(routeInformation);
        double zoom = this.camera.zoom(routeInformation);
        if (this.locationComponent.isLocationComponentActivated()) {
            this.locationComponent.zoomWhileTracking(zoom, getZoomAnimationDuration(zoom), new e(this));
            double d = tilt;
            this.locationComponent.tiltWhileTracking(d, getTiltAnimationDuration(d));
        }
    }

    private void adjustCameraFromLocation(@NonNull RouteInformation routeInformation) {
        double tilt;
        NavigationMode navigationMode = this.navigationMode;
        if (navigationMode == NavigationMode.DEFAULT) {
            tilt = this.camera.tilt(routeInformation);
        } else if (navigationMode == NavigationMode.TWO_D) {
            tilt = 0.0d;
        } else {
            tilt = navigationMode == NavigationMode.THREE_D ? 60.0d : this.camera.tilt(routeInformation);
        }
        double zoom = this.camera.zoom(routeInformation);
        if (this.locationComponent.isLocationComponentActivated()) {
            this.locationComponent.zoomWhileTracking(zoom, getZoomAnimationDuration(zoom));
            this.locationComponent.tiltWhileTracking(tilt, getTiltAnimationDuration(tilt));
            if (routeInformation.getLocation() != null) {
                this.locationComponent.forceLocationUpdate(routeInformation.getLocation());
            }
        }
    }

    private boolean animateCameraForRouteOverview(@NonNull RouteInformation routeInformation, @NonNull int[] iArr) {
        List<Point> overview = this.camera.overview(routeInformation);
        if (overview.isEmpty() || overview.size() <= 1) {
            return false;
        }
        animateMapplsMapForRouteOverview(iArr, overview);
        return true;
    }

    private void animateMapplsMapForRouteOverview(@NonNull int[] iArr, @NonNull List<Point> list) {
        CameraUpdate buildResetCameraUpdate = buildResetCameraUpdate();
        CameraUpdate buildOverviewCameraUpdate = buildOverviewCameraUpdate(iArr, list);
        MapplsMap mapplsMap = this.mapplsMap;
        mapplsMap.animateCamera(buildResetCameraUpdate, 150, new b(buildOverviewCameraUpdate, mapplsMap));
    }

    @NonNull
    private CameraUpdate buildOverviewCameraUpdate(int[] iArr, @NonNull List<Point> list) {
        return CameraUpdateFactory.newLatLngBounds(convertRoutePointsToLatLngBounds(list), iArr[0], iArr[1], iArr[2], iArr[3]);
    }

    @NonNull
    private CameraUpdate buildResetCameraUpdate() {
        return CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().tilt(0.0d).bearing(0.0d).build());
    }

    @NonNull
    private RouteInformation buildRouteInformationFromLocation(@Nullable Location location, @Nullable AdviseInfo adviseInfo) {
        return new RouteInformation(null, location, adviseInfo);
    }

    @NonNull
    private RouteInformation buildRouteInformationFromRoute(DirectionsRoute directionsRoute) {
        return new RouteInformation(directionsRoute, null, null);
    }

    @NonNull
    private LatLngBounds convertRoutePointsToLatLngBounds(@NonNull List<Point> list) {
        ArrayList arrayList = new ArrayList();
        for (Point point : list) {
            arrayList.add(new LatLng(point.latitude(), point.longitude()));
        }
        return new LatLngBounds.Builder().includes(arrayList).build();
    }

    @Nullable
    private Integer findCameraModeFor(int i) {
        if (i == 0) {
            return 34;
        }
        if (i == 1) {
            return 36;
        }
        return i == 2 ? 8 : null;
    }

    private DirectionsRoute getRoute() {
        if (this.currentRouteProgress != null) {
            return MapplsNavigationHelper.getInstance().getCurrentRoute();
        }
        RouteInformation routeInformation = this.currentRouteInformation;
        if (routeInformation != null) {
            return routeInformation.getRoute();
        }
        return null;
    }

    private long getTiltAnimationDuration(double d) {
        return (long) MathUtils.clamp(Math.abs(this.mapplsMap.getCameraPosition().tilt - d) * 500.0d, 750.0d, 1000.0d);
    }

    private long getZoomAnimationDuration(double d) {
        return (long) MathUtils.clamp(Math.abs(this.mapplsMap.getCameraPosition().zoom - d) * 500.0d, 300.0d, 1000.0d);
    }

    private void onCameraTransitionFinished() {
        RouteInformation routeInformation;
        if (!this.isCameraResetting || (routeInformation = this.currentRouteInformation) == null) {
            return;
        }
        adjustCameraForReset(routeInformation);
    }

    private void resetDynamicCamera(Camera camera) {
        if (camera instanceof DynamicCamera) {
            ((DynamicCamera) camera).forceResetZoomLevel();
        }
    }

    private void resetWith(int i) {
        updateIsResetting(true);
        resetDynamicCamera(this.camera);
        updateCameraTrackingMode(i);
    }

    private void setCameraMode(int i) {
        Integer findCameraModeFor = findCameraModeFor(i);
        if (findCameraModeFor == null) {
            Timber.e("Using unsupported camera tracking mode - %d.", Integer.valueOf(i));
            return;
        }
        this.trackingCameraMode = i;
        updateTrackingModeListenersWith(i);
        if (findCameraModeFor.intValue() == this.locationComponent.getCameraMode() || !this.locationComponent.isLocationComponentActivated()) {
            return;
        }
        this.locationComponent.setCameraMode(findCameraModeFor.intValue(), this.cameraTransitionListener);
    }

    private boolean showOverviewForRoute(@NonNull int[] iArr, RouteInformation routeInformation) {
        updateCameraTrackingMode(2);
        return animateCameraForRouteOverview(routeInformation, iArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryToBuildRouteInformationAndAdjustCamera() {
        AdviseInfo adviseInfo;
        if (!isTrackingEnabled() || (adviseInfo = this.currentRouteProgress) == null) {
            return;
        }
        RouteInformation routeInformation = new RouteInformation(null, this.currentLocation, adviseInfo);
        this.currentRouteInformation = routeInformation;
        if (this.isCameraResetting) {
            return;
        }
        adjustCameraFromLocation(routeInformation);
    }

    private void updateTrackingModeListenersWith(int i) {
        Iterator<OnTrackingModeChangedListener> it = this.onTrackingModeChangedListeners.iterator();
        while (it.hasNext()) {
            it.next().onTrackingModeChanged(i);
        }
    }

    public void addOnTrackingModeChangedListener(@NonNull OnTrackingModeChangedListener onTrackingModeChangedListener) {
        this.onTrackingModeChangedListeners.add(onTrackingModeChangedListener);
    }

    public void addOnTrackingModeTransitionListener(@NonNull OnTrackingModeTransitionListener onTrackingModeTransitionListener) {
        this.onTrackingModeTransitionListeners.add(onTrackingModeTransitionListener);
    }

    public void addProgressChangeListener(INavigation iNavigation) {
        this.navigation = iNavigation;
        iNavigation.setProgressChangeListener(this.progressChangeListener);
    }

    @Nullable
    public Integer findTrackingModeFor(int i) {
        if (i == 34) {
            return 0;
        }
        if (i == 36) {
            return 1;
        }
        return i == 8 ? 2 : null;
    }

    public int getCameraTrackingMode() {
        return this.trackingCameraMode;
    }

    public NavigationMode getNavigationMode() {
        return this.navigationMode;
    }

    public boolean isTrackingEnabled() {
        return this.trackingCameraMode != 2;
    }

    public void onRouteProgress(@NonNull AdviseInfo adviseInfo) {
        this.currentRouteProgress = adviseInfo;
        this.currentLocation = adviseInfo.getLocation();
        tryToBuildRouteInformationAndAdjustCamera();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        INavigation iNavigation = this.navigation;
        if (iNavigation != null) {
            iNavigation.setProgressChangeListener(this.progressChangeListener);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        INavigation iNavigation = this.navigation;
        if (iNavigation != null) {
            iNavigation.removeProgressChangeListener(this.progressChangeListener);
        }
    }

    public void removeOnTrackingModeChangedListener(@NonNull OnTrackingModeChangedListener onTrackingModeChangedListener) {
        this.onTrackingModeChangedListeners.remove(onTrackingModeChangedListener);
    }

    public void removeOnTrackingModeTransitionListener(@NonNull OnTrackingModeTransitionListener onTrackingModeTransitionListener) {
        this.onTrackingModeTransitionListeners.remove(onTrackingModeTransitionListener);
    }

    public void removeProgressChangeListener() {
        INavigation iNavigation = this.navigation;
        if (iNavigation != null) {
            iNavigation.removeProgressChangeListener(this.progressChangeListener);
        }
    }

    public void resetCameraPositionWith(int i) {
        resetWith(i);
    }

    public void resetNavigationMode() {
        this.navigationMode = NavigationMode.DEFAULT;
        adjustCameraForReset(this.currentRouteInformation);
    }

    public void resume(@Nullable Location location) {
        if (location != null) {
            this.currentRouteInformation = buildRouteInformationFromLocation(location, null);
        }
        this.navigation.setProgressChangeListener(this.progressChangeListener);
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void setDefaultZoom(double d) {
        Camera camera = this.camera;
        if (camera == null || !(camera instanceof DynamicCamera)) {
            return;
        }
        ((DynamicCamera) camera).setDefaultZoom(d);
    }

    public void setMaxCameraZoom(double d) {
        Camera camera = this.camera;
        if (camera == null || !(camera instanceof DynamicCamera)) {
            return;
        }
        ((DynamicCamera) camera).setMaxCameraZoom(d);
    }

    public void setMinCameraZoom(double d) {
        Camera camera = this.camera;
        if (camera == null || !(camera instanceof DynamicCamera)) {
            return;
        }
        ((DynamicCamera) camera).setMinCameraZoom(d);
    }

    public boolean showRouteGeometryOverview(@NonNull int[] iArr) {
        updateCameraTrackingMode(2);
        DirectionsRoute route = getRoute();
        if (route == null) {
            Timber.e(NULL_ROUTE_ERROR_MESSAGE, new Object[0]);
            return false;
        }
        return showOverviewForRoute(iArr, new RouteInformation(route, null, null));
    }

    public boolean showRouteGeometryRemainingOverview(@NonNull int[] iArr) {
        DirectionsRoute route = getRoute();
        if (route == null) {
            Timber.e(NULL_ROUTE_ERROR_MESSAGE, new Object[0]);
            return false;
        }
        return showOverviewForRoute(iArr, new RouteInformation(route, null, this.currentRouteProgress));
    }

    @Deprecated
    public void showRouteOverview(@NonNull int[] iArr) {
        showRouteGeometryOverview(iArr);
    }

    public void start(@Nullable DirectionsRoute directionsRoute) {
        if (directionsRoute != null) {
            this.currentRouteInformation = buildRouteInformationFromRoute(directionsRoute);
        }
        this.navigation.setProgressChangeListener(this.progressChangeListener);
    }

    public void toggleTilt(boolean z) {
        if (z) {
            this.navigationMode = NavigationMode.THREE_D;
            if (this.locationComponent.isLocationComponentActivated()) {
                this.locationComponent.tiltWhileTracking(60.0d, getTiltAnimationDuration(60.0d));
                return;
            }
            return;
        }
        this.navigationMode = NavigationMode.TWO_D;
        if (this.locationComponent.isLocationComponentActivated()) {
            this.locationComponent.tiltWhileTracking(0.0d, getTiltAnimationDuration(0.0d));
        }
    }

    public void update(@NonNull NavigationCameraUpdate navigationCameraUpdate) {
        this.animationDelegate.b(navigationCameraUpdate, 300, null);
    }

    public void updateCameraTrackingMode(int i) {
        setCameraMode(i);
    }

    public void updateIsResetting(boolean z) {
        this.isCameraResetting = z;
    }

    public void updateTransitionListenersCancelled(int i) {
        Integer findTrackingModeFor = findTrackingModeFor(i);
        if (findTrackingModeFor == null) {
            return;
        }
        Iterator<OnTrackingModeTransitionListener> it = this.onTrackingModeTransitionListeners.iterator();
        while (it.hasNext()) {
            it.next().onTransitionCancelled(findTrackingModeFor.intValue());
        }
    }

    public void updateTransitionListenersFinished(int i) {
        onCameraTransitionFinished();
        Integer findTrackingModeFor = findTrackingModeFor(i);
        if (findTrackingModeFor == null) {
            return;
        }
        Iterator<OnTrackingModeTransitionListener> it = this.onTrackingModeTransitionListeners.iterator();
        while (it.hasNext()) {
            it.next().onTransitionFinished(findTrackingModeFor.intValue());
        }
    }

    public void update(@NonNull NavigationCameraUpdate navigationCameraUpdate, int i) {
        this.animationDelegate.b(navigationCameraUpdate, i, null);
    }

    public void update(@NonNull NavigationCameraUpdate navigationCameraUpdate, int i, @Nullable MapplsMap.CancelableCallback cancelableCallback) {
        this.animationDelegate.b(navigationCameraUpdate, i, cancelableCallback);
    }

    public NavigationCamera(@NonNull MapplsMap mapplsMap, @NonNull LocationComponent locationComponent) {
        c cVar = new c(this);
        this.cameraTrackingChangedListener = cVar;
        this.navigationMode = NavigationMode.DEFAULT;
        this.trackingCameraMode = 0;
        this.progressChangeListener = new a();
        this.mapplsMap = mapplsMap;
        this.locationComponent = locationComponent;
        this.animationDelegate = new com.mappls.sdk.navigation.camera.a(mapplsMap);
        this.locationComponent.addOnCameraTrackingChangedListener(cVar);
        this.camera = new DynamicCamera(mapplsMap);
    }
}

package com.mappls.sdk.maps.location;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Looper;
import android.os.SystemClock;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.annotation.StyleRes;
import androidx.annotation.VisibleForTesting;
import com.mappls.android.lms.MapplsLMSManager;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.R;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.Transform;
import com.mappls.sdk.maps.camera.CameraPosition;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.location.engine.LocationEngine;
import com.mappls.sdk.maps.location.engine.LocationEngineCallback;
import com.mappls.sdk.maps.location.engine.LocationEngineProvider;
import com.mappls.sdk.maps.location.engine.LocationEngineRequest;
import com.mappls.sdk.maps.location.engine.LocationEngineResult;
import com.mappls.sdk.maps.log.Logger;
import com.mappls.sdk.services.api.MapplsApiConfiguration;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes11.dex */
public final class LocationComponent {
    public final CopyOnWriteArrayList<OnRenderModeChangedListener> A;
    public long B;
    public long C;
    @NonNull
    public MapplsMap.OnCameraMoveListener D;
    @NonNull
    public MapplsMap.OnCameraIdleListener E;
    @NonNull
    public MapplsMap.OnMapClickListener F;
    @NonNull
    public MapplsMap.OnMapLongClickListener G;
    @NonNull
    public OnLocationStaleListener H;
    @NonNull
    public u I;
    @NonNull
    public CompassListener J;
    @NonNull
    @VisibleForTesting
    public OnCameraTrackingChangedListener K;
    @NonNull
    @VisibleForTesting
    public OnRenderModeChangedListener L;
    @NonNull
    public final MapplsMap.OnDeveloperAnimationListener M;
    public LocationListener N;
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final MapplsMap f12738a;
    @NonNull
    public final Transform b;
    public Style c;
    public LocationComponentOptions d;
    @NonNull
    public m e;
    @Nullable
    public LocationEngine f;
    @NonNull
    public LocationEngineRequest g;
    public LocationEngineCallback<LocationEngineResult> h;
    public LocationEngineCallback<LocationEngineResult> i;
    @Nullable
    public CompassEngine j;
    public com.mappls.sdk.maps.location.l k;
    public com.mappls.sdk.maps.location.h l;
    public com.mappls.sdk.maps.location.g m;
    @Nullable
    public Location n;
    public CameraPosition o;
    public boolean p;
    public boolean q;
    public boolean r;
    public boolean s;
    public boolean t;
    public boolean u;
    public v v;
    public final CopyOnWriteArrayList<OnLocationStaleListener> w;
    public final CopyOnWriteArrayList<OnLocationClickListener> x;
    public final CopyOnWriteArrayList<OnLocationLongClickListener> y;
    public final CopyOnWriteArrayList<OnCameraTrackingChangedListener> z;

    /* loaded from: classes11.dex */
    public interface LocationListener {
        void onLocationChanged(Location location);
    }

    /* loaded from: classes11.dex */
    public class a implements MapplsMap.OnDeveloperAnimationListener {
        public a() {
        }

        @Override // com.mappls.sdk.maps.MapplsMap.OnDeveloperAnimationListener
        public void onDeveloperAnimationStarted() {
            if (LocationComponent.this.p && LocationComponent.this.r) {
                LocationComponent.this.setCameraMode(8);
            }
        }
    }

    /* loaded from: classes11.dex */
    public class b implements MapplsMap.OnCameraMoveListener {
        public b() {
        }

        @Override // com.mappls.sdk.maps.MapplsMap.OnCameraMoveListener
        public void onCameraMove() {
            LocationComponent.this.I(false);
        }
    }

    /* loaded from: classes11.dex */
    public class c implements MapplsMap.OnCameraIdleListener {
        public c() {
        }

        @Override // com.mappls.sdk.maps.MapplsMap.OnCameraIdleListener
        public void onCameraIdle() {
            LocationComponent.this.I(false);
        }
    }

    /* loaded from: classes11.dex */
    public class d implements MapplsMap.OnMapClickListener {
        public d() {
        }

        @Override // com.mappls.sdk.maps.MapplsMap.OnMapClickListener
        public boolean onMapClick(@NonNull LatLng latLng) {
            if (LocationComponent.this.x.isEmpty() || !LocationComponent.this.k.o(latLng)) {
                return false;
            }
            Iterator it = LocationComponent.this.x.iterator();
            while (it.hasNext()) {
                ((OnLocationClickListener) it.next()).onLocationComponentClick();
            }
            return true;
        }
    }

    /* loaded from: classes11.dex */
    public class e implements MapplsMap.OnMapLongClickListener {
        public e() {
        }

        @Override // com.mappls.sdk.maps.MapplsMap.OnMapLongClickListener
        public boolean onMapLongClick(@NonNull LatLng latLng) {
            if (LocationComponent.this.y.isEmpty() || !LocationComponent.this.k.o(latLng)) {
                return false;
            }
            Iterator it = LocationComponent.this.y.iterator();
            while (it.hasNext()) {
                ((OnLocationLongClickListener) it.next()).onLocationComponentLongClick();
            }
            return true;
        }
    }

    /* loaded from: classes11.dex */
    public class f implements OnLocationStaleListener {
        public f() {
        }

        @Override // com.mappls.sdk.maps.location.OnLocationStaleListener
        public void onStaleStateChange(boolean z) {
            LocationComponent.this.k.q(z);
            Iterator it = LocationComponent.this.w.iterator();
            while (it.hasNext()) {
                ((OnLocationStaleListener) it.next()).onStaleStateChange(z);
            }
        }
    }

    /* loaded from: classes11.dex */
    public class g implements u {
        public g() {
        }

        @Override // com.mappls.sdk.maps.location.u
        public void a() {
            LocationComponent.this.D.onCameraMove();
        }
    }

    /* loaded from: classes11.dex */
    public class h implements CompassListener {
        public h() {
        }

        @Override // com.mappls.sdk.maps.location.CompassListener
        public void onCompassAccuracyChange(int i) {
        }

        @Override // com.mappls.sdk.maps.location.CompassListener
        public void onCompassChanged(float f) {
            LocationComponent.this.G(f);
        }
    }

    /* loaded from: classes11.dex */
    public class i implements OnCameraTrackingChangedListener {
        public i() {
        }

        @Override // com.mappls.sdk.maps.location.OnCameraTrackingChangedListener
        public void onCameraTrackingChanged(int i) {
            LocationComponent.this.m.e();
            LocationComponent.this.m.d();
            LocationComponent.this.F();
            Iterator it = LocationComponent.this.z.iterator();
            while (it.hasNext()) {
                ((OnCameraTrackingChangedListener) it.next()).onCameraTrackingChanged(i);
            }
        }

        @Override // com.mappls.sdk.maps.location.OnCameraTrackingChangedListener
        public void onCameraTrackingDismissed() {
            Iterator it = LocationComponent.this.z.iterator();
            while (it.hasNext()) {
                ((OnCameraTrackingChangedListener) it.next()).onCameraTrackingDismissed();
            }
        }
    }

    /* loaded from: classes11.dex */
    public class j implements OnRenderModeChangedListener {
        public j() {
        }

        @Override // com.mappls.sdk.maps.location.OnRenderModeChangedListener
        public void onRenderModeChanged(int i) {
            LocationComponent.this.F();
            Iterator it = LocationComponent.this.A.iterator();
            while (it.hasNext()) {
                ((OnRenderModeChangedListener) it.next()).onRenderModeChanged(i);
            }
        }
    }

    /* loaded from: classes11.dex */
    public class k implements OnLocationCameraTransitionListener {

        /* renamed from: a  reason: collision with root package name */
        public final OnLocationCameraTransitionListener f12745a;

        public /* synthetic */ k(LocationComponent locationComponent, OnLocationCameraTransitionListener onLocationCameraTransitionListener, b bVar) {
            this(onLocationCameraTransitionListener);
        }

        public final void a(int i) {
            LocationComponent.this.m.z(LocationComponent.this.f12738a.getCameraPosition(), i == 36);
        }

        @Override // com.mappls.sdk.maps.location.OnLocationCameraTransitionListener
        public void onLocationCameraTransitionCanceled(int i) {
            OnLocationCameraTransitionListener onLocationCameraTransitionListener = this.f12745a;
            if (onLocationCameraTransitionListener != null) {
                onLocationCameraTransitionListener.onLocationCameraTransitionCanceled(i);
            }
            a(i);
        }

        @Override // com.mappls.sdk.maps.location.OnLocationCameraTransitionListener
        public void onLocationCameraTransitionFinished(int i) {
            OnLocationCameraTransitionListener onLocationCameraTransitionListener = this.f12745a;
            if (onLocationCameraTransitionListener != null) {
                onLocationCameraTransitionListener.onLocationCameraTransitionFinished(i);
            }
            a(i);
        }

        public k(OnLocationCameraTransitionListener onLocationCameraTransitionListener) {
            this.f12745a = onLocationCameraTransitionListener;
        }
    }

    @VisibleForTesting
    /* loaded from: classes11.dex */
    public static final class l implements LocationEngineCallback<LocationEngineResult> {
        public final WeakReference<LocationComponent> h;

        public l(LocationComponent locationComponent) {
            this.h = new WeakReference<>(locationComponent);
        }

        @Override // com.mappls.sdk.maps.location.engine.LocationEngineCallback
        /* renamed from: a */
        public void onSuccess(LocationEngineResult locationEngineResult) {
            LocationComponent locationComponent = this.h.get();
            if (locationComponent != null) {
                locationComponent.K(locationEngineResult.getLastLocation(), false);
            }
        }

        @Override // com.mappls.sdk.maps.location.engine.LocationEngineCallback
        public void onFailure(@NonNull Exception exc) {
            Logger.e("Mbgl-LocationComponent", "Failed to obtain location update", exc);
        }
    }

    /* loaded from: classes11.dex */
    public static class m {
        public LocationEngine a(@NonNull Context context, boolean z) {
            return LocationEngineProvider.getBestLocationEngine(context, z);
        }
    }

    @VisibleForTesting
    /* loaded from: classes11.dex */
    public static final class n implements LocationEngineCallback<LocationEngineResult> {
        public final WeakReference<LocationComponent> h;

        public n(LocationComponent locationComponent) {
            this.h = new WeakReference<>(locationComponent);
        }

        @Override // com.mappls.sdk.maps.location.engine.LocationEngineCallback
        /* renamed from: a */
        public void onSuccess(LocationEngineResult locationEngineResult) {
            LocationComponent locationComponent = this.h.get();
            if (locationComponent != null) {
                locationComponent.K(locationEngineResult.getLastLocation(), true);
            }
        }

        @Override // com.mappls.sdk.maps.location.engine.LocationEngineCallback
        public void onFailure(@NonNull Exception exc) {
            Logger.e("Mbgl-LocationComponent", "Failed to obtain last location update", exc);
        }
    }

    public LocationComponent(@NonNull MapplsMap mapplsMap, @NonNull Transform transform, @NonNull List<MapplsMap.OnDeveloperAnimationListener> list) {
        this.e = new m();
        this.g = new LocationEngineRequest.Builder(1000L).setFastestInterval(1000L).setPriority(0).build();
        this.h = new l(this);
        this.i = new n(this);
        this.w = new CopyOnWriteArrayList<>();
        this.x = new CopyOnWriteArrayList<>();
        this.y = new CopyOnWriteArrayList<>();
        this.z = new CopyOnWriteArrayList<>();
        this.A = new CopyOnWriteArrayList<>();
        this.D = new b();
        this.E = new c();
        this.F = new d();
        this.G = new e();
        this.H = new f();
        this.I = new g();
        this.J = new h();
        this.K = new i();
        this.L = new j();
        a aVar = new a();
        this.M = aVar;
        this.f12738a = mapplsMap;
        this.b = transform;
        list.add(aVar);
    }

    @SuppressLint({"MissingPermission"})
    public final void A() {
        LocationEngine locationEngine = this.f;
        if (locationEngine != null) {
            locationEngine.getLastLocation(this.i);
        } else {
            K(getLastKnownLocation(), true);
        }
    }

    public final void B() {
        boolean n2 = this.k.n();
        if (this.r && this.s && n2) {
            this.k.s();
            if (this.d.pulseEnabled().booleanValue()) {
                this.k.c(true);
            }
        }
    }

    public final void C() {
        if (this.r && this.t) {
            this.m.J(this.d);
            this.k.c(true);
        }
    }

    public final void D() {
        this.m.K();
        this.k.c(false);
    }

    public final void E(Location location, boolean z) {
        float a2;
        if (location == null) {
            a2 = 0.0f;
        } else if (this.q) {
            a2 = location.getAccuracy();
        } else {
            a2 = Utils.a(this.f12738a, location);
        }
        this.m.l(a2, z);
    }

    public final void F() {
        HashSet hashSet = new HashSet();
        hashSet.addAll(this.k.i());
        hashSet.addAll(this.l.n());
        this.m.M(hashSet);
        this.m.z(this.f12738a.getCameraPosition(), this.l.o() == 36);
        this.m.A();
    }

    public final void G(float f2) {
        this.m.m(f2, this.f12738a.getCameraPosition());
    }

    public final void H(boolean z) {
        CompassEngine compassEngine = this.j;
        if (compassEngine != null) {
            if (!z) {
                y(compassEngine);
            } else if (this.p && this.s && this.r && this.t) {
                if (!this.l.r() && !this.k.m()) {
                    y(this.j);
                } else if (this.u) {
                } else {
                    this.u = true;
                    this.j.addCompassListener(this.J);
                }
            }
        }
    }

    @SuppressLint({"MissingPermission"})
    public final void I(boolean z) {
        if (this.q) {
            return;
        }
        CameraPosition cameraPosition = this.f12738a.getCameraPosition();
        CameraPosition cameraPosition2 = this.o;
        if (cameraPosition2 != null && !z) {
            double d2 = cameraPosition.bearing;
            if (d2 != cameraPosition2.bearing) {
                this.k.f(d2);
            }
            double d3 = cameraPosition.tilt;
            if (d3 != this.o.tilt) {
                this.k.g(d3);
            }
            if (cameraPosition.zoom != this.o.zoom) {
                E(getLastKnownLocation(), true);
            }
            this.o = cameraPosition;
            return;
        }
        this.o = cameraPosition;
        this.k.f(cameraPosition.bearing);
        this.k.g(cameraPosition.tilt);
        E(getLastKnownLocation(), true);
    }

    public final void J(@Nullable Location location, @Nullable List<Location> list, boolean z, boolean z2) {
        LocationListener locationListener = this.N;
        if (locationListener != null && location != null) {
            locationListener.onLocationChanged(location);
        }
        if (MapplsLMSManager.isInitialised()) {
            MapplsLMSManager.getInstance().setCurrentLocation(location);
        }
        MapplsApiConfiguration.getInstance().setLocation(location);
        if (location == null) {
            return;
        }
        if (!this.t) {
            this.n = location;
            return;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - this.C < this.B) {
            return;
        }
        this.C = elapsedRealtime;
        B();
        if (!z) {
            this.v.i();
        }
        CameraPosition cameraPosition = this.f12738a.getCameraPosition();
        boolean z3 = getCameraMode() == 36;
        if (list != null) {
            this.m.o(s(location, list), cameraPosition, z3, z2);
        } else {
            this.m.n(location, cameraPosition, z3);
        }
        E(location, false);
        this.n = location;
    }

    public final void K(@Nullable Location location, boolean z) {
        J(location, null, z, false);
    }

    public final void L(@NonNull LocationComponentOptions locationComponentOptions) {
        int[] padding = locationComponentOptions.padding();
        if (padding != null) {
            this.f12738a.setPadding(padding[0], padding[1], padding[2], padding[3]);
        }
    }

    @Deprecated
    public void activateLocationComponent(@NonNull Context context, @NonNull Style style) {
        activateLocationComponent(context, style, LocationComponentOptions.createFromAttributes(context, R.style.mappls_maps_LocationComponent));
    }

    public void addOnCameraTrackingChangedListener(@NonNull OnCameraTrackingChangedListener onCameraTrackingChangedListener) {
        this.z.add(onCameraTrackingChangedListener);
    }

    public void addOnLocationClickListener(@NonNull OnLocationClickListener onLocationClickListener) {
        this.x.add(onLocationClickListener);
    }

    public void addOnLocationLongClickListener(@NonNull OnLocationLongClickListener onLocationLongClickListener) {
        this.y.add(onLocationLongClickListener);
    }

    public void addOnLocationStaleListener(@NonNull OnLocationStaleListener onLocationStaleListener) {
        this.w.add(onLocationStaleListener);
    }

    public void addOnRenderModeChangedListener(@NonNull OnRenderModeChangedListener onRenderModeChangedListener) {
        this.A.add(onRenderModeChangedListener);
    }

    public void applyStyle(@NonNull Context context, @StyleRes int i2) {
        p();
        applyStyle(LocationComponentOptions.createFromAttributes(context, i2));
    }

    public void cancelTiltWhileTrackingAnimation() {
        p();
        this.m.d();
    }

    public void cancelZoomWhileTrackingAnimation() {
        p();
        this.m.e();
    }

    public void forceLocationUpdate(@Nullable Location location) {
        p();
        K(location, false);
    }

    public int getCameraMode() {
        p();
        return this.l.o();
    }

    @Nullable
    public CompassEngine getCompassEngine() {
        p();
        return this.j;
    }

    @Nullable
    public Location getLastKnownLocation() {
        p();
        return this.n;
    }

    public LocationComponentOptions getLocationComponentOptions() {
        p();
        return this.d;
    }

    @Nullable
    public LocationEngine getLocationEngine() {
        p();
        return this.f;
    }

    @NonNull
    public LocationEngineRequest getLocationEngineRequest() {
        p();
        return this.g;
    }

    public int getRenderMode() {
        p();
        return this.k.j();
    }

    public boolean isLocationComponentActivated() {
        return this.p;
    }

    public boolean isLocationComponentEnabled() {
        p();
        return this.r;
    }

    public void onDestroy() {
        MapplsApiConfiguration.getInstance().setLocation(null);
        if (MapplsLMSManager.isInitialised()) {
            MapplsLMSManager.getInstance().setCurrentLocation(null);
        }
    }

    public void onFinishLoadingStyle() {
        if (this.p) {
            Style style = this.f12738a.getStyle();
            this.c = style;
            this.k.l(style, this.d);
            this.l.p(this.d);
            w();
        }
    }

    public void onStart() {
        this.s = true;
        w();
    }

    public void onStartLoadingMap() {
        x();
    }

    public void onStop() {
        x();
        MapplsApiConfiguration.getInstance().setLocation(null);
        if (MapplsLMSManager.isInitialised()) {
            MapplsLMSManager.getInstance().setCurrentLocation(null);
        }
        this.s = false;
    }

    public final void p() {
        if (!this.p) {
            throw new com.mappls.sdk.maps.location.j();
        }
    }

    public final void q() {
        this.r = false;
        this.k.k();
        x();
        MapplsApiConfiguration.getInstance().setLocation(null);
        if (MapplsLMSManager.isInitialised()) {
            MapplsLMSManager.getInstance().setCurrentLocation(null);
        }
    }

    public final void r() {
        this.r = true;
        w();
    }

    public void removeOnCameraTrackingChangedListener(@NonNull OnCameraTrackingChangedListener onCameraTrackingChangedListener) {
        this.z.remove(onCameraTrackingChangedListener);
    }

    public void removeOnLocationClickListener(@NonNull OnLocationClickListener onLocationClickListener) {
        this.x.remove(onLocationClickListener);
    }

    public void removeOnLocationLongClickListener(@NonNull OnLocationLongClickListener onLocationLongClickListener) {
        this.y.remove(onLocationLongClickListener);
    }

    public void removeOnLocationStaleListener(@NonNull OnLocationStaleListener onLocationStaleListener) {
        this.w.remove(onLocationStaleListener);
    }

    public void removeRenderModeChangedListener(@NonNull OnRenderModeChangedListener onRenderModeChangedListener) {
        this.A.remove(onRenderModeChangedListener);
    }

    public void resetCompassEngine(@NonNull Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
        if (windowManager == null || sensorManager == null) {
            return;
        }
        this.j = new com.mappls.sdk.maps.location.i(windowManager, sensorManager);
    }

    public final Location[] s(Location location, List<Location> list) {
        int size = list.size() + 1;
        Location[] locationArr = new Location[size];
        locationArr[size - 1] = location;
        for (int i2 = 0; i2 < list.size(); i2++) {
            locationArr[i2] = list.get(i2);
        }
        return locationArr;
    }

    public void setCameraMode(int i2) {
        setCameraMode(i2, null);
    }

    public void setCompassEngine(@Nullable CompassEngine compassEngine) {
        p();
        if (this.j != null) {
            H(false);
        }
        this.j = compassEngine;
        H(true);
    }

    @RequiresPermission(anyOf = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"})
    public void setLocationComponentEnabled(boolean z) {
        p();
        if (z) {
            r();
        } else {
            q();
        }
        this.l.z(z);
    }

    @SuppressLint({"MissingPermission"})
    public void setLocationEngine(@Nullable LocationEngine locationEngine) {
        p();
        LocationEngine locationEngine2 = this.f;
        if (locationEngine2 != null) {
            locationEngine2.removeLocationUpdates(this.h);
            this.f = null;
        }
        if (locationEngine != null) {
            this.B = this.g.getFastestInterval();
            this.f = locationEngine;
            if (this.t && this.r) {
                A();
                locationEngine.requestLocationUpdates(this.g, this.h, Looper.getMainLooper());
                return;
            }
            return;
        }
        this.B = 0L;
    }

    public void setLocationEngineRequest(@NonNull LocationEngineRequest locationEngineRequest) {
        p();
        this.g = locationEngineRequest;
        setLocationEngine(this.f);
    }

    public void setLocationListener(LocationListener locationListener) {
        this.N = locationListener;
    }

    public void setManualStaleState(boolean z) {
        v vVar = this.v;
        if (vVar != null) {
            vVar.h(z);
        }
    }

    public void setMaxAnimationFps(int i2) {
        p();
        this.m.H(i2);
    }

    public void setRenderMode(int i2) {
        p();
        if (this.n != null && i2 == 8) {
            this.m.b();
            this.k.p(this.n.getBearing());
        }
        this.k.r(i2);
        I(true);
        H(true);
    }

    public void setTransitioning(boolean z) {
        com.mappls.sdk.maps.location.h hVar = this.l;
        if (hVar != null) {
            hVar.C(z);
        }
    }

    public final void t(@NonNull Context context, @NonNull Style style, boolean z, @NonNull LocationComponentOptions locationComponentOptions) {
        if (this.p) {
            return;
        }
        this.p = true;
        if (style.isFullyLoaded()) {
            this.c = style;
            this.d = locationComponentOptions;
            this.q = z;
            this.f12738a.addOnMapClickListener(this.F);
            this.f12738a.addOnMapLongClickListener(this.G);
            this.k = new com.mappls.sdk.maps.location.l(this.f12738a, style, new com.mappls.sdk.maps.location.f(), new com.mappls.sdk.maps.location.e(), new com.mappls.sdk.maps.location.d(context), locationComponentOptions, this.L, z);
            this.l = new com.mappls.sdk.maps.location.h(context, this.f12738a, this.b, this.K, locationComponentOptions, this.I);
            com.mappls.sdk.maps.location.g gVar = new com.mappls.sdk.maps.location.g(this.f12738a.getProjection(), q.a(), p.c());
            this.m = gVar;
            gVar.I(locationComponentOptions.trackingAnimationDurationMultiplier());
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
            if (windowManager != null && sensorManager != null) {
                this.j = new com.mappls.sdk.maps.location.i(windowManager, sensorManager);
            }
            this.v = new v(this.H, locationComponentOptions);
            L(locationComponentOptions);
            setRenderMode(18);
            setCameraMode(8);
            w();
            return;
        }
        throw new IllegalStateException("Style is invalid, provide the most recently loaded one.");
    }

    public void tiltWhileTracking(double d2, long j2, @Nullable MapplsMap.CancelableCallback cancelableCallback) {
        p();
        if (!this.t) {
            v(cancelableCallback, null);
        } else if (getCameraMode() == 8) {
            v(cancelableCallback, String.format("%s%s", "LocationComponent#tiltWhileTracking method can only be used", " when a camera mode other than CameraMode#NONE is engaged."));
        } else if (this.l.u()) {
            v(cancelableCallback, "LocationComponent#tiltWhileTracking method call is ignored because the camera mode is transitioning");
        } else {
            this.m.p(d2, this.f12738a.getCameraPosition(), j2, cancelableCallback);
        }
    }

    public final void u(@NonNull Context context) {
        LocationEngine locationEngine = this.f;
        if (locationEngine != null) {
            locationEngine.removeLocationUpdates(this.h);
        }
        setLocationEngine(this.e.a(context, false));
    }

    public final void v(@Nullable MapplsMap.CancelableCallback cancelableCallback, @Nullable String str) {
        if (str != null) {
            Logger.e("Mbgl-LocationComponent", str);
        }
        if (cancelableCallback != null) {
            cancelableCallback.onCancel();
        }
    }

    @SuppressLint({"MissingPermission"})
    public final void w() {
        if (this.p && this.s && this.f12738a.getStyle() != null) {
            if (!this.t) {
                this.t = true;
                this.f12738a.addOnCameraMoveListener(this.D);
                this.f12738a.addOnCameraIdleListener(this.E);
                if (this.d.enableStaleState()) {
                    this.v.b();
                }
            }
            if (this.r) {
                LocationEngine locationEngine = this.f;
                if (locationEngine != null) {
                    try {
                        locationEngine.requestLocationUpdates(this.g, this.h, Looper.getMainLooper());
                    } catch (SecurityException e2) {
                        Logger.e("Mbgl-LocationComponent", "Unable to request location updates", e2);
                    }
                }
                setCameraMode(this.l.o());
                if (this.d.pulseEnabled().booleanValue()) {
                    C();
                } else {
                    D();
                }
                A();
                H(true);
                z();
            }
        }
    }

    public final void x() {
        if (this.p && this.t && this.s) {
            this.t = false;
            this.v.c();
            if (this.j != null) {
                H(false);
            }
            D();
            this.m.a();
            LocationEngine locationEngine = this.f;
            if (locationEngine != null) {
                locationEngine.removeLocationUpdates(this.h);
            }
            this.f12738a.removeOnCameraMoveListener(this.D);
            this.f12738a.removeOnCameraIdleListener(this.E);
        }
    }

    public final void y(@NonNull CompassEngine compassEngine) {
        if (this.u) {
            this.u = false;
            compassEngine.removeCompassListener(this.J);
        }
    }

    public final void z() {
        CompassEngine compassEngine = this.j;
        G(compassEngine != null ? compassEngine.getLastHeading() : 0.0f);
    }

    public void zoomWhileTracking(double d2, long j2, @Nullable MapplsMap.CancelableCallback cancelableCallback) {
        p();
        if (!this.t) {
            v(cancelableCallback, null);
        } else if (getCameraMode() == 8) {
            v(cancelableCallback, String.format("%s%s", "LocationComponent#zoomWhileTracking method can only be used", " when a camera mode other than CameraMode#NONE is engaged."));
        } else if (this.l.u()) {
            v(cancelableCallback, "LocationComponent#zoomWhileTracking method call is ignored because the camera mode is transitioning");
        } else {
            this.m.q(d2, this.f12738a.getCameraPosition(), j2, cancelableCallback);
        }
    }

    public void setCameraMode(int i2, @Nullable OnLocationCameraTransitionListener onLocationCameraTransitionListener) {
        setCameraMode(i2, 750L, null, null, null, onLocationCameraTransitionListener);
    }

    public void applyStyle(@NonNull LocationComponentOptions locationComponentOptions) {
        p();
        this.d = locationComponentOptions;
        if (this.f12738a.getStyle() != null) {
            this.k.d(locationComponentOptions);
            this.l.p(locationComponentOptions);
            this.v.f(locationComponentOptions.enableStaleState());
            this.v.e(locationComponentOptions.staleStateTimeout());
            this.m.I(locationComponentOptions.trackingAnimationDurationMultiplier());
            this.m.G(locationComponentOptions.compassAnimationEnabled());
            this.m.F(locationComponentOptions.accuracyAnimationEnabled());
            if (locationComponentOptions.pulseEnabled().booleanValue()) {
                C();
            } else {
                D();
            }
            L(locationComponentOptions);
        }
    }

    public void forceLocationUpdate(@Nullable Location location, boolean z) {
        p();
        K(location, z);
    }

    public void setCameraMode(int i2, long j2, @Nullable Double d2, @Nullable Double d3, @Nullable Double d4, @Nullable OnLocationCameraTransitionListener onLocationCameraTransitionListener) {
        p();
        this.l.y(i2, this.n, j2, d2, d3, d4, new k(this, onLocationCameraTransitionListener, null));
        H(true);
    }

    @Deprecated
    public void activateLocationComponent(@NonNull Context context, @NonNull Style style, boolean z) {
        if (z) {
            activateLocationComponent(context, style, R.style.mappls_maps_LocationComponent);
        } else {
            activateLocationComponent(context, style, (LocationEngine) null, R.style.mappls_maps_LocationComponent);
        }
    }

    public void forceLocationUpdate(@Nullable List<Location> list, boolean z) {
        p();
        if (list != null && list.size() >= 1) {
            J(list.get(list.size() - 1), list.subList(0, list.size() - 1), false, z);
        } else {
            K(null, false);
        }
    }

    @Deprecated
    public void activateLocationComponent(@NonNull Context context, @NonNull Style style, boolean z, @NonNull LocationEngineRequest locationEngineRequest) {
        setLocationEngineRequest(locationEngineRequest);
        if (z) {
            activateLocationComponent(context, style, R.style.mappls_maps_LocationComponent);
        } else {
            activateLocationComponent(context, style, (LocationEngine) null, R.style.mappls_maps_LocationComponent);
        }
    }

    @Deprecated
    public void activateLocationComponent(@NonNull Context context, @NonNull Style style, boolean z, @NonNull LocationEngineRequest locationEngineRequest, @NonNull LocationComponentOptions locationComponentOptions) {
        setLocationEngineRequest(locationEngineRequest);
        if (z) {
            activateLocationComponent(context, style, locationComponentOptions);
        } else {
            activateLocationComponent(context, style, (LocationEngine) null, locationComponentOptions);
        }
    }

    public void tiltWhileTracking(double d2, long j2) {
        p();
        tiltWhileTracking(d2, j2, null);
    }

    public void zoomWhileTracking(double d2, long j2) {
        p();
        zoomWhileTracking(d2, j2, null);
    }

    public void tiltWhileTracking(double d2) {
        p();
        tiltWhileTracking(d2, 1250L, null);
    }

    public void zoomWhileTracking(double d2) {
        p();
        zoomWhileTracking(d2, 750L, null);
    }

    @Deprecated
    public void activateLocationComponent(@NonNull Context context, @NonNull Style style, @StyleRes int i2) {
        activateLocationComponent(context, style, LocationComponentOptions.createFromAttributes(context, i2));
    }

    @Deprecated
    public void activateLocationComponent(@NonNull Context context, @NonNull Style style, @NonNull LocationComponentOptions locationComponentOptions) {
        t(context, style, false, locationComponentOptions);
        u(context);
        applyStyle(locationComponentOptions);
    }

    @Deprecated
    public void activateLocationComponent(@NonNull Context context, @NonNull Style style, @Nullable LocationEngine locationEngine, @StyleRes int i2) {
        activateLocationComponent(context, style, locationEngine, LocationComponentOptions.createFromAttributes(context, i2));
    }

    @Deprecated
    public void activateLocationComponent(@NonNull Context context, @NonNull Style style, @Nullable LocationEngine locationEngine, @NonNull LocationEngineRequest locationEngineRequest, @StyleRes int i2) {
        activateLocationComponent(context, style, locationEngine, locationEngineRequest, LocationComponentOptions.createFromAttributes(context, i2));
    }

    @Deprecated
    public void activateLocationComponent(@NonNull Context context, @NonNull Style style, @Nullable LocationEngine locationEngine) {
        activateLocationComponent(context, style, locationEngine, R.style.mappls_maps_LocationComponent);
    }

    @Deprecated
    public void activateLocationComponent(@NonNull Context context, @NonNull Style style, @Nullable LocationEngine locationEngine, @NonNull LocationEngineRequest locationEngineRequest) {
        activateLocationComponent(context, style, locationEngine, locationEngineRequest, R.style.mappls_maps_LocationComponent);
    }

    @Deprecated
    public void activateLocationComponent(@NonNull Context context, @NonNull Style style, @Nullable LocationEngine locationEngine, @NonNull LocationComponentOptions locationComponentOptions) {
        t(context, style, false, locationComponentOptions);
        setLocationEngine(locationEngine);
        applyStyle(locationComponentOptions);
    }

    @Deprecated
    public void activateLocationComponent(@NonNull Context context, @NonNull Style style, @Nullable LocationEngine locationEngine, @NonNull LocationEngineRequest locationEngineRequest, @NonNull LocationComponentOptions locationComponentOptions) {
        t(context, style, false, locationComponentOptions);
        setLocationEngineRequest(locationEngineRequest);
        setLocationEngine(locationEngine);
        applyStyle(locationComponentOptions);
    }

    public LocationComponent() {
        this.e = new m();
        this.g = new LocationEngineRequest.Builder(1000L).setFastestInterval(1000L).setPriority(0).build();
        this.h = new l(this);
        this.i = new n(this);
        this.w = new CopyOnWriteArrayList<>();
        this.x = new CopyOnWriteArrayList<>();
        this.y = new CopyOnWriteArrayList<>();
        this.z = new CopyOnWriteArrayList<>();
        this.A = new CopyOnWriteArrayList<>();
        this.D = new b();
        this.E = new c();
        this.F = new d();
        this.G = new e();
        this.H = new f();
        this.I = new g();
        this.J = new h();
        this.K = new i();
        this.L = new j();
        this.M = new a();
        this.f12738a = null;
        this.b = null;
    }

    public void activateLocationComponent(@NonNull LocationComponentActivationOptions locationComponentActivationOptions) {
        LocationComponentOptions locationComponentOptions = locationComponentActivationOptions.locationComponentOptions();
        if (locationComponentOptions == null) {
            int styleRes = locationComponentActivationOptions.styleRes();
            if (styleRes == 0) {
                styleRes = R.style.mappls_maps_LocationComponent;
            }
            locationComponentOptions = LocationComponentOptions.createFromAttributes(locationComponentActivationOptions.context(), styleRes);
        }
        t(locationComponentActivationOptions.context(), locationComponentActivationOptions.style(), locationComponentActivationOptions.useSpecializedLocationLayer(), locationComponentOptions);
        applyStyle(locationComponentOptions);
        LocationEngineRequest locationEngineRequest = locationComponentActivationOptions.locationEngineRequest();
        if (locationEngineRequest != null) {
            setLocationEngineRequest(locationEngineRequest);
        }
        LocationEngine locationEngine = locationComponentActivationOptions.locationEngine();
        if (locationEngine != null) {
            setLocationEngine(locationEngine);
        } else if (locationComponentActivationOptions.useDefaultLocationEngine()) {
            u(locationComponentActivationOptions.context());
        } else {
            setLocationEngine(null);
        }
    }
}

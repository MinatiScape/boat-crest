package com.mappls.sdk.maps;

import android.graphics.PointF;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.camera.CameraMapplsPinPosition;
import com.mappls.sdk.maps.camera.CameraMapplsPinUpdate;
import com.mappls.sdk.maps.camera.CameraPosition;
import com.mappls.sdk.maps.camera.CameraUpdate;
import com.mappls.sdk.maps.camera.CameraUpdateFactory;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.log.Logger;
import java.util.List;
/* loaded from: classes11.dex */
public final class Transform implements MapView.OnCameraDidChangeListener {
    public final s h;
    @Nullable
    public final MapView i;
    @Nullable
    public CameraPosition k;
    @Nullable
    public MapplsMap.CancelableCallback l;
    public com.mappls.sdk.maps.g m;
    public final Handler j = new Handler();
    public final MapView.OnCameraDidChangeListener n = new a();

    /* loaded from: classes11.dex */
    public class a implements MapView.OnCameraDidChangeListener {
        public a() {
        }

        @Override // com.mappls.sdk.maps.MapView.OnCameraDidChangeListener
        public void onCameraDidChange(boolean z) {
            if (z) {
                Transform.this.m.onCameraIdle();
                if (Transform.this.i != null) {
                    Transform.this.i.removeOnCameraDidChangeListener(this);
                }
            }
        }
    }

    /* loaded from: classes11.dex */
    public class b implements Runnable {
        public final /* synthetic */ MapplsMap.CancelableCallback h;

        public b(Transform transform, MapplsMap.CancelableCallback cancelableCallback) {
            this.h = cancelableCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.h.onFinish();
        }
    }

    /* loaded from: classes11.dex */
    public class c implements Runnable {
        public final /* synthetic */ MapplsMap.CancelableCallback h;

        public c(Transform transform, MapplsMap.CancelableCallback cancelableCallback) {
            this.h = cancelableCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            MapplsMap.CancelableCallback cancelableCallback = this.h;
            if (cancelableCallback != null) {
                cancelableCallback.onFinish();
            }
        }
    }

    /* loaded from: classes11.dex */
    public class d implements CoordinateCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CameraMapplsPinPosition f12660a;
        public final /* synthetic */ MapplsMap.CancelableCallback b;

        /* loaded from: classes11.dex */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                MapplsMap.CancelableCallback cancelableCallback = d.this.b;
                if (cancelableCallback != null) {
                    cancelableCallback.onFinish();
                }
            }
        }

        public d(CameraMapplsPinPosition cameraMapplsPinPosition, MapplsMap.CancelableCallback cancelableCallback) {
            this.f12660a = cameraMapplsPinPosition;
            this.b = cancelableCallback;
        }

        @Override // com.mappls.sdk.maps.CoordinateCallback
        public void coordinateResultSuccess(List<CoordinateResult> list) {
            if (list.size() > 0) {
                LatLng latLng = new LatLng(list.get(0).getLatitude().doubleValue(), list.get(0).getLongitude().doubleValue());
                Transform.this.f();
                Transform.this.m.onCameraMoveStarted(3);
                s sVar = Transform.this.h;
                CameraMapplsPinPosition cameraMapplsPinPosition = this.f12660a;
                sVar.K(latLng, cameraMapplsPinPosition.zoom, cameraMapplsPinPosition.tilt, cameraMapplsPinPosition.bearing, cameraMapplsPinPosition.padding);
                Transform.this.r();
                Transform.this.m.onCameraIdle();
                Transform.this.j.post(new a());
                return;
            }
            MapplsMap.CancelableCallback cancelableCallback = this.b;
            if (cancelableCallback != null) {
                cancelableCallback.onFinish();
            }
        }

        @Override // com.mappls.sdk.maps.CoordinateCallback
        public void onFailure() {
            MapplsMap.CancelableCallback cancelableCallback = this.b;
            if (cancelableCallback != null) {
                cancelableCallback.onFinish();
            }
        }
    }

    /* loaded from: classes11.dex */
    public class e implements CoordinateCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ MapplsMap.CancelableCallback f12661a;
        public final /* synthetic */ CameraMapplsPinPosition b;
        public final /* synthetic */ int c;
        public final /* synthetic */ boolean d;

        public e(MapplsMap.CancelableCallback cancelableCallback, CameraMapplsPinPosition cameraMapplsPinPosition, int i, boolean z) {
            this.f12661a = cancelableCallback;
            this.b = cameraMapplsPinPosition;
            this.c = i;
            this.d = z;
        }

        @Override // com.mappls.sdk.maps.CoordinateCallback
        public void coordinateResultSuccess(List<CoordinateResult> list) {
            if (list.size() > 0) {
                LatLng latLng = new LatLng(list.get(0).getLatitude().doubleValue(), list.get(0).getLongitude().doubleValue());
                Transform.this.f();
                Transform.this.m.onCameraMoveStarted(3);
                MapplsMap.CancelableCallback cancelableCallback = this.f12661a;
                if (cancelableCallback != null) {
                    Transform.this.l = cancelableCallback;
                }
                if (Transform.this.i != null) {
                    Transform.this.i.addOnCameraDidChangeListener(Transform.this);
                }
                s sVar = Transform.this.h;
                CameraMapplsPinPosition cameraMapplsPinPosition = this.b;
                sVar.l0(latLng, cameraMapplsPinPosition.zoom, cameraMapplsPinPosition.bearing, cameraMapplsPinPosition.tilt, cameraMapplsPinPosition.padding, this.c, this.d);
                return;
            }
            MapplsMap.CancelableCallback cancelableCallback2 = this.f12661a;
            if (cancelableCallback2 != null) {
                cancelableCallback2.onFinish();
            }
        }

        @Override // com.mappls.sdk.maps.CoordinateCallback
        public void onFailure() {
            MapplsMap.CancelableCallback cancelableCallback = this.f12661a;
            if (cancelableCallback != null) {
                cancelableCallback.onFinish();
            }
        }
    }

    /* loaded from: classes11.dex */
    public class f implements CoordinateCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ MapplsMap.CancelableCallback f12662a;
        public final /* synthetic */ CameraMapplsPinPosition b;
        public final /* synthetic */ int c;

        public f(MapplsMap.CancelableCallback cancelableCallback, CameraMapplsPinPosition cameraMapplsPinPosition, int i) {
            this.f12662a = cancelableCallback;
            this.b = cameraMapplsPinPosition;
            this.c = i;
        }

        @Override // com.mappls.sdk.maps.CoordinateCallback
        public void coordinateResultSuccess(List<CoordinateResult> list) {
            if (list.size() > 0) {
                LatLng latLng = new LatLng(list.get(0).getLatitude().doubleValue(), list.get(0).getLongitude().doubleValue());
                Transform.this.f();
                Transform.this.m.onCameraMoveStarted(3);
                MapplsMap.CancelableCallback cancelableCallback = this.f12662a;
                if (cancelableCallback != null) {
                    Transform.this.l = cancelableCallback;
                }
                if (Transform.this.i != null) {
                    Transform.this.i.addOnCameraDidChangeListener(Transform.this);
                }
                s sVar = Transform.this.h;
                CameraMapplsPinPosition cameraMapplsPinPosition = this.b;
                sVar.r(latLng, cameraMapplsPinPosition.zoom, cameraMapplsPinPosition.bearing, cameraMapplsPinPosition.tilt, cameraMapplsPinPosition.padding, this.c);
                return;
            }
            MapplsMap.CancelableCallback cancelableCallback2 = this.f12662a;
            if (cancelableCallback2 != null) {
                cancelableCallback2.onFinish();
            }
        }

        @Override // com.mappls.sdk.maps.CoordinateCallback
        public void onFailure() {
            MapplsMap.CancelableCallback cancelableCallback = this.f12662a;
            if (cancelableCallback != null) {
                cancelableCallback.onFinish();
            }
        }
    }

    /* loaded from: classes11.dex */
    public class g implements Runnable {
        public final /* synthetic */ MapplsMap.CancelableCallback h;

        public g(Transform transform, MapplsMap.CancelableCallback cancelableCallback) {
            this.h = cancelableCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.h.onCancel();
        }
    }

    public Transform(MapView mapView, s sVar, com.mappls.sdk.maps.g gVar) {
        this.i = mapView;
        this.h = sVar;
        this.m = gVar;
    }

    public void A(double d2) {
        if (d2 < 1.0d || d2 > 22.0d) {
            Logger.e("Mbgl-Transform", String.format("Not setting maxZoomPreference, value is in unsupported range: %s", Double.valueOf(d2)));
        } else {
            this.h.s(d2);
        }
    }

    public void B(double d2) {
        if (d2 < 0.0d || d2 > 60.0d) {
            Logger.e("Mbgl-Transform", String.format("Not setting minPitchPreference, value is in unsupported range: %s", Double.valueOf(d2)));
        } else {
            this.h.T(d2);
        }
    }

    public void C(double d2) {
        if (d2 < 1.0d || d2 > 22.0d) {
            Logger.e("Mbgl-Transform", String.format("Not setting minZoomPreference, value is in unsupported range: %s", Double.valueOf(d2)));
        } else {
            this.h.z0(d2);
        }
    }

    public void D(Double d2) {
        this.h.S(d2.doubleValue(), 0L);
    }

    public void E(double d2, @NonNull PointF pointF) {
        this.h.G0(d2, pointF, 0L);
    }

    public void F(double d2, @NonNull PointF pointF) {
        E(this.h.C0() + d2, pointF);
    }

    @UiThread
    public final void animateCamera(@NonNull MapplsMap mapplsMap, CameraUpdate cameraUpdate, int i, @Nullable MapplsMap.CancelableCallback cancelableCallback) {
        CameraPosition cameraPosition = cameraUpdate.getCameraPosition(mapplsMap);
        if (!t(cameraPosition)) {
            if (cancelableCallback != null) {
                cancelableCallback.onFinish();
                return;
            }
            return;
        }
        f();
        this.m.onCameraMoveStarted(3);
        if (cancelableCallback != null) {
            this.l = cancelableCallback;
        }
        MapView mapView = this.i;
        if (mapView != null) {
            mapView.addOnCameraDidChangeListener(this);
        }
        this.h.r(cameraPosition.target, cameraPosition.zoom, cameraPosition.bearing, cameraPosition.tilt, cameraPosition.padding, i);
    }

    public void f() {
        this.m.onCameraMoveCanceled();
        MapplsMap.CancelableCallback cancelableCallback = this.l;
        if (cancelableCallback != null) {
            this.m.onCameraIdle();
            this.l = null;
            this.j.post(new g(this, cancelableCallback));
        }
        this.h.e();
        this.m.onCameraIdle();
    }

    @UiThread
    public final void g(@NonNull MapplsMap mapplsMap, CameraMapplsPinUpdate cameraMapplsPinUpdate, int i, boolean z, @Nullable MapplsMap.CancelableCallback cancelableCallback) {
        CameraMapplsPinPosition cameraMapplsPinPosition = cameraMapplsPinUpdate.getCameraMapplsPinPosition(mapplsMap);
        if (s(cameraMapplsPinPosition)) {
            o.d().b(cameraMapplsPinPosition.target, new e(cancelableCallback, cameraMapplsPinPosition, i, z));
        } else if (cancelableCallback != null) {
            cancelableCallback.onFinish();
        }
    }

    @Nullable
    @UiThread
    public final CameraPosition getCameraPosition() {
        if (this.k == null) {
            this.k = r();
        }
        return this.k;
    }

    @UiThread
    public final void h(@NonNull MapplsMap mapplsMap, CameraUpdate cameraUpdate, int i, boolean z, @Nullable MapplsMap.CancelableCallback cancelableCallback) {
        CameraPosition cameraPosition = cameraUpdate.getCameraPosition(mapplsMap);
        if (!t(cameraPosition)) {
            if (cancelableCallback != null) {
                cancelableCallback.onFinish();
                return;
            }
            return;
        }
        f();
        this.m.onCameraMoveStarted(3);
        if (cancelableCallback != null) {
            this.l = cancelableCallback;
        }
        MapView mapView = this.i;
        if (mapView != null) {
            mapView.addOnCameraDidChangeListener(this);
        }
        this.h.l0(cameraPosition.target, cameraPosition.zoom, cameraPosition.bearing, cameraPosition.tilt, cameraPosition.padding, i, z);
    }

    public double i() {
        double d2 = -this.h.D0();
        while (d2 > 360.0d) {
            d2 -= 360.0d;
        }
        while (d2 < 0.0d) {
            d2 += 360.0d;
        }
        return d2;
    }

    public double j() {
        return this.h.v0();
    }

    public double k() {
        return this.h.getMaxZoom();
    }

    public double l() {
        return this.h.j();
    }

    public double m() {
        return this.h.getMinZoom();
    }

    @UiThread
    public final void moveCamera(@NonNull MapplsMap mapplsMap, CameraUpdate cameraUpdate, @Nullable MapplsMap.CancelableCallback cancelableCallback) {
        CameraPosition cameraPosition = cameraUpdate.getCameraPosition(mapplsMap);
        if (!t(cameraPosition)) {
            if (cancelableCallback != null) {
                cancelableCallback.onFinish();
                return;
            }
            return;
        }
        f();
        this.m.onCameraMoveStarted(3);
        this.h.K(cameraPosition.target, cameraPosition.zoom, cameraPosition.tilt, cameraPosition.bearing, cameraPosition.padding);
        r();
        this.m.onCameraIdle();
        this.j.post(new c(this, cancelableCallback));
    }

    public double n() {
        return this.h.D0();
    }

    public double o() {
        return this.h.C0();
    }

    @Override // com.mappls.sdk.maps.MapView.OnCameraDidChangeListener
    public void onCameraDidChange(boolean z) {
        if (z) {
            r();
            MapplsMap.CancelableCallback cancelableCallback = this.l;
            if (cancelableCallback != null) {
                this.l = null;
                this.j.post(new b(this, cancelableCallback));
            }
            this.m.onCameraIdle();
            MapView mapView = this.i;
            if (mapView != null) {
                mapView.removeOnCameraDidChangeListener(this);
            }
        }
    }

    public double p() {
        return this.h.J();
    }

    public void q(@NonNull MapplsMap mapplsMap, @NonNull MapplsMapOptions mapplsMapOptions) {
        CameraPosition camera = mapplsMapOptions.getCamera();
        if (camera != null && !camera.equals(CameraPosition.DEFAULT)) {
            moveCamera(mapplsMap, CameraUpdateFactory.newCameraPosition(camera), (MapplsMap.CancelableCallback) null);
        }
        C(mapplsMapOptions.getMinZoomPreference());
        A(mapplsMapOptions.getMaxZoomPreference());
        B(mapplsMapOptions.getMinPitchPreference());
        z(mapplsMapOptions.getMaxPitchPreference());
    }

    @Nullable
    @UiThread
    public CameraPosition r() {
        s sVar = this.h;
        if (sVar != null) {
            CameraPosition cameraPosition = sVar.getCameraPosition();
            CameraPosition cameraPosition2 = this.k;
            if (cameraPosition2 != null && !cameraPosition2.equals(cameraPosition)) {
                this.m.onCameraMove();
            }
            this.k = cameraPosition;
        }
        return this.k;
    }

    public final boolean s(@Nullable CameraMapplsPinPosition cameraMapplsPinPosition) {
        return (cameraMapplsPinPosition == null || cameraMapplsPinPosition.equals(this.k)) ? false : true;
    }

    public final boolean t(@Nullable CameraPosition cameraPosition) {
        return (cameraPosition == null || cameraPosition.equals(this.k)) ? false : true;
    }

    public void u(double d2, double d3, long j) {
        MapView mapView;
        if (j > 0 && (mapView = this.i) != null) {
            mapView.addOnCameraDidChangeListener(this.n);
        }
        this.h.B0(d2, d3, j);
    }

    @UiThread
    public void v() {
        f();
        this.h.G();
    }

    public void w(double d2, float f2, float f3) {
        this.h.V(d2, f2, f3, 0L);
    }

    public void x(double d2, float f2, float f3, long j) {
        this.h.V(d2, f2, f3, j);
    }

    public void y(boolean z) {
        this.h.U(z);
        if (z) {
            return;
        }
        r();
    }

    public void z(double d2) {
        if (d2 < 0.0d || d2 > 60.0d) {
            Logger.e("Mbgl-Transform", String.format("Not setting maxPitchPreference, value is in unsupported range: %s", Double.valueOf(d2)));
        } else {
            this.h.y(d2);
        }
    }

    @UiThread
    public final void animateCamera(@NonNull MapplsMap mapplsMap, CameraMapplsPinUpdate cameraMapplsPinUpdate, int i, @Nullable MapplsMap.CancelableCallback cancelableCallback) {
        CameraMapplsPinPosition cameraMapplsPinPosition = cameraMapplsPinUpdate.getCameraMapplsPinPosition(mapplsMap);
        if (s(cameraMapplsPinPosition)) {
            o.d().b(cameraMapplsPinPosition.target, new f(cancelableCallback, cameraMapplsPinPosition, i));
        } else if (cancelableCallback != null) {
            cancelableCallback.onFinish();
        }
    }

    @UiThread
    public final void moveCamera(@NonNull MapplsMap mapplsMap, CameraMapplsPinUpdate cameraMapplsPinUpdate, @Nullable MapplsMap.CancelableCallback cancelableCallback) {
        CameraMapplsPinPosition cameraMapplsPinPosition = cameraMapplsPinUpdate.getCameraMapplsPinPosition(mapplsMap);
        if (s(cameraMapplsPinPosition)) {
            o.d().b(cameraMapplsPinPosition.target, new d(cameraMapplsPinPosition, cancelableCallback));
        } else if (cancelableCallback != null) {
            cancelableCallback.onFinish();
        }
    }
}

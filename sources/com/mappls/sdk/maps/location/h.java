package com.mappls.sdk.maps.location;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.RectF;
import android.location.Location;
import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.mappls.sdk.gestures.AndroidGesturesManager;
import com.mappls.sdk.gestures.MoveGestureDetector;
import com.mappls.sdk.gestures.RotateGestureDetector;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.Transform;
import com.mappls.sdk.maps.camera.CameraPosition;
import com.mappls.sdk.maps.camera.CameraUpdate;
import com.mappls.sdk.maps.camera.CameraUpdateFactory;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.location.o;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes11.dex */
public final class h {

    /* renamed from: a  reason: collision with root package name */
    public int f12763a;
    public final MapplsMap b;
    public final Transform c;
    public final OnCameraTrackingChangedListener d;
    public LocationComponentOptions e;
    public final MoveGestureDetector f;
    public final u g;
    public final AndroidGesturesManager h;
    public final AndroidGesturesManager i;
    public boolean j;
    public LatLng k;
    public boolean l;
    public final o.b<LatLng> m = new c();
    public final o.b<Float> n = new d();
    public final o.b<Float> o = new e();
    public final o.b<Float> p = new f();
    public final o.b<Float> q = new g();
    public MapplsMap.OnCameraMoveListener r = new C0630h();
    @NonNull
    @VisibleForTesting
    public MapplsMap.OnMoveListener s = new i();
    @NonNull
    public MapplsMap.OnRotateListener t = new j();
    @NonNull
    public MapplsMap.OnFlingListener u = new a();

    /* loaded from: classes11.dex */
    public class a implements MapplsMap.OnFlingListener {
        public a() {
        }

        @Override // com.mappls.sdk.maps.MapplsMap.OnFlingListener
        public void onFling() {
            h.this.x(8);
        }
    }

    /* loaded from: classes11.dex */
    public class b implements MapplsMap.CancelableCallback {
        public final /* synthetic */ OnLocationCameraTransitionListener h;

        public b(OnLocationCameraTransitionListener onLocationCameraTransitionListener) {
            this.h = onLocationCameraTransitionListener;
        }

        @Override // com.mappls.sdk.maps.MapplsMap.CancelableCallback
        public void onCancel() {
            h.this.j = false;
            OnLocationCameraTransitionListener onLocationCameraTransitionListener = this.h;
            if (onLocationCameraTransitionListener != null) {
                onLocationCameraTransitionListener.onLocationCameraTransitionCanceled(h.this.f12763a);
            }
        }

        @Override // com.mappls.sdk.maps.MapplsMap.CancelableCallback
        public void onFinish() {
            h.this.j = false;
            OnLocationCameraTransitionListener onLocationCameraTransitionListener = this.h;
            if (onLocationCameraTransitionListener != null) {
                onLocationCameraTransitionListener.onLocationCameraTransitionFinished(h.this.f12763a);
            }
        }
    }

    /* loaded from: classes11.dex */
    public class c implements o.b<LatLng> {
        public c() {
        }

        @Override // com.mappls.sdk.maps.location.o.b
        /* renamed from: b */
        public void a(LatLng latLng) {
            h.this.A(latLng);
        }
    }

    /* loaded from: classes11.dex */
    public class d implements o.b<Float> {
        public d() {
        }

        @Override // com.mappls.sdk.maps.location.o.b
        /* renamed from: b */
        public void a(Float f) {
            if (h.this.f12763a == 36 && h.this.b.getCameraPosition().bearing == 0.0d) {
                return;
            }
            h.this.w(f.floatValue());
        }
    }

    /* loaded from: classes11.dex */
    public class e implements o.b<Float> {
        public e() {
        }

        @Override // com.mappls.sdk.maps.location.o.b
        /* renamed from: b */
        public void a(Float f) {
            if (h.this.f12763a == 32 || h.this.f12763a == 16) {
                h.this.w(f.floatValue());
            }
        }
    }

    /* loaded from: classes11.dex */
    public class f implements o.b<Float> {
        public f() {
        }

        @Override // com.mappls.sdk.maps.location.o.b
        /* renamed from: b */
        public void a(Float f) {
            h.this.D(f.floatValue());
        }
    }

    /* loaded from: classes11.dex */
    public class g implements o.b<Float> {
        public g() {
        }

        @Override // com.mappls.sdk.maps.location.o.b
        /* renamed from: b */
        public void a(Float f) {
            h.this.B(f.floatValue());
        }
    }

    /* renamed from: com.mappls.sdk.maps.location.h$h  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class C0630h implements MapplsMap.OnCameraMoveListener {
        public C0630h() {
        }

        @Override // com.mappls.sdk.maps.MapplsMap.OnCameraMoveListener
        public void onCameraMove() {
            if (h.this.t() && h.this.k != null && h.this.e.trackingGesturesManagement()) {
                PointF screenLocation = h.this.b.getProjection().toScreenLocation(h.this.k);
                if (h.this.b.getUiSettings() != null) {
                    h.this.b.getUiSettings().setFocalPoint(screenLocation);
                }
            }
        }
    }

    /* loaded from: classes11.dex */
    public class i implements MapplsMap.OnMoveListener {
        public boolean h;

        public i() {
        }

        public final void a(@NonNull MoveGestureDetector moveGestureDetector) {
            if (moveGestureDetector.getMoveThreshold() != h.this.e.trackingMultiFingerMoveThreshold()) {
                moveGestureDetector.setMoveThreshold(h.this.e.trackingMultiFingerMoveThreshold());
                this.h = true;
            }
        }

        public final void b(@NonNull MoveGestureDetector moveGestureDetector) {
            RectF moveThresholdRect = moveGestureDetector.getMoveThresholdRect();
            if (moveThresholdRect == null || moveThresholdRect.equals(h.this.e.trackingMultiFingerProtectedMoveArea())) {
                if (moveThresholdRect != null || h.this.e.trackingMultiFingerProtectedMoveArea() == null) {
                    return;
                }
                moveGestureDetector.setMoveThresholdRect(h.this.e.trackingMultiFingerProtectedMoveArea());
                this.h = true;
                return;
            }
            moveGestureDetector.setMoveThresholdRect(h.this.e.trackingMultiFingerProtectedMoveArea());
            this.h = true;
        }

        public final void c(@NonNull MoveGestureDetector moveGestureDetector) {
            if (moveGestureDetector.getMoveThreshold() != h.this.e.trackingInitialMoveThreshold()) {
                moveGestureDetector.setMoveThreshold(h.this.e.trackingInitialMoveThreshold());
                this.h = true;
            }
        }

        @Override // com.mappls.sdk.maps.MapplsMap.OnMoveListener
        public void onMove(@NonNull MoveGestureDetector moveGestureDetector) {
            if (!this.h) {
                if (h.this.t() || h.this.q()) {
                    h.this.x(8);
                    moveGestureDetector.interrupt();
                    return;
                }
                return;
            }
            moveGestureDetector.interrupt();
        }

        @Override // com.mappls.sdk.maps.MapplsMap.OnMoveListener
        public void onMoveBegin(@NonNull MoveGestureDetector moveGestureDetector) {
            if (h.this.e.trackingGesturesManagement() && h.this.t()) {
                if (moveGestureDetector.getPointersCount() > 1) {
                    b(moveGestureDetector);
                    a(moveGestureDetector);
                    return;
                }
                c(moveGestureDetector);
                return;
            }
            h.this.x(8);
        }

        @Override // com.mappls.sdk.maps.MapplsMap.OnMoveListener
        public void onMoveEnd(@NonNull MoveGestureDetector moveGestureDetector) {
            if (h.this.e.trackingGesturesManagement() && !this.h && h.this.t()) {
                moveGestureDetector.setMoveThreshold(h.this.e.trackingInitialMoveThreshold());
                moveGestureDetector.setMoveThresholdRect(null);
            }
            this.h = false;
        }
    }

    /* loaded from: classes11.dex */
    public class j implements MapplsMap.OnRotateListener {
        public j() {
        }

        @Override // com.mappls.sdk.maps.MapplsMap.OnRotateListener
        public void onRotate(@NonNull RotateGestureDetector rotateGestureDetector) {
        }

        @Override // com.mappls.sdk.maps.MapplsMap.OnRotateListener
        public void onRotateBegin(@NonNull RotateGestureDetector rotateGestureDetector) {
            if (h.this.q()) {
                h.this.x(8);
            }
        }

        @Override // com.mappls.sdk.maps.MapplsMap.OnRotateListener
        public void onRotateEnd(@NonNull RotateGestureDetector rotateGestureDetector) {
        }
    }

    /* loaded from: classes11.dex */
    public class k extends AndroidGesturesManager {
        public k(Context context) {
            super(context);
        }

        @Override // com.mappls.sdk.gestures.AndroidGesturesManager
        public boolean onTouchEvent(@Nullable MotionEvent motionEvent) {
            if (motionEvent != null && motionEvent.getActionMasked() == 1) {
                h.this.m();
            }
            return super.onTouchEvent(motionEvent);
        }
    }

    public h(Context context, MapplsMap mapplsMap, Transform transform, OnCameraTrackingChangedListener onCameraTrackingChangedListener, @NonNull LocationComponentOptions locationComponentOptions, u uVar) {
        this.b = mapplsMap;
        this.c = transform;
        this.h = mapplsMap.getGesturesManager();
        k kVar = new k(context);
        this.i = kVar;
        this.f = kVar.getMoveGestureDetector();
        mapplsMap.addOnRotateListener(this.t);
        mapplsMap.addOnFlingListener(this.u);
        mapplsMap.addOnMoveListener(this.s);
        mapplsMap.addOnCameraMoveListener(this.r);
        this.d = onCameraTrackingChangedListener;
        this.g = uVar;
        p(locationComponentOptions);
    }

    public final void A(@NonNull LatLng latLng) {
        if (this.j) {
            return;
        }
        this.k = latLng;
        this.c.moveCamera(this.b, CameraUpdateFactory.newLatLng(latLng), (MapplsMap.CancelableCallback) null);
        this.g.a();
    }

    public final void B(float f2) {
        if (this.j) {
            return;
        }
        this.c.moveCamera(this.b, CameraUpdateFactory.tiltTo(f2), (MapplsMap.CancelableCallback) null);
        this.g.a();
    }

    public void C(boolean z) {
        this.j = z;
    }

    public final void D(float f2) {
        if (this.j) {
            return;
        }
        this.c.moveCamera(this.b, CameraUpdateFactory.zoomTo(f2), (MapplsMap.CancelableCallback) null);
        this.g.a();
    }

    public final void E(boolean z, Location location, long j2, Double d2, Double d3, Double d4, OnLocationCameraTransitionListener onLocationCameraTransitionListener) {
        if (z || !t() || location == null || !this.l) {
            if (onLocationCameraTransitionListener != null) {
                onLocationCameraTransitionListener.onLocationCameraTransitionFinished(this.f12763a);
                return;
            }
            return;
        }
        this.j = true;
        LatLng latLng = new LatLng(location);
        CameraPosition.Builder target = new CameraPosition.Builder().target(latLng);
        if (d2 != null) {
            target.zoom(d2.doubleValue());
        }
        if (d4 != null) {
            target.tilt(d4.doubleValue());
        }
        if (d3 != null) {
            target.bearing(d3.doubleValue());
        } else if (s()) {
            target.bearing(this.f12763a == 36 ? 0.0d : location.getBearing());
        }
        CameraUpdate newCameraPosition = CameraUpdateFactory.newCameraPosition(target.build());
        b bVar = new b(onLocationCameraTransitionListener);
        if (Utils.d(this.b.getProjection(), this.b.getCameraPosition().target, latLng)) {
            this.c.moveCamera(this.b, newCameraPosition, bVar);
        } else {
            this.c.animateCamera(this.b, newCameraPosition, (int) j2, bVar);
        }
    }

    public final void m() {
        if (this.e.trackingGesturesManagement()) {
            if (t()) {
                this.f.setMoveThreshold(this.e.trackingInitialMoveThreshold());
                return;
            }
            this.f.setMoveThreshold(0.0f);
            this.f.setMoveThresholdRect(null);
        }
    }

    public Set<com.mappls.sdk.maps.location.a> n() {
        HashSet hashSet = new HashSet();
        if (t()) {
            hashSet.add(new com.mappls.sdk.maps.location.a(1, this.m));
        }
        if (s()) {
            hashSet.add(new com.mappls.sdk.maps.location.a(4, this.n));
        }
        if (r()) {
            hashSet.add(new com.mappls.sdk.maps.location.a(5, this.o));
        }
        hashSet.add(new com.mappls.sdk.maps.location.a(7, this.p));
        hashSet.add(new com.mappls.sdk.maps.location.a(8, this.q));
        return hashSet;
    }

    public int o() {
        return this.f12763a;
    }

    public void p(LocationComponentOptions locationComponentOptions) {
        this.e = locationComponentOptions;
        if (locationComponentOptions.trackingGesturesManagement()) {
            AndroidGesturesManager gesturesManager = this.b.getGesturesManager();
            AndroidGesturesManager androidGesturesManager = this.i;
            if (gesturesManager != androidGesturesManager) {
                this.b.setGesturesManager(androidGesturesManager, true, true);
            }
            m();
            return;
        }
        AndroidGesturesManager gesturesManager2 = this.b.getGesturesManager();
        AndroidGesturesManager androidGesturesManager2 = this.h;
        if (gesturesManager2 != androidGesturesManager2) {
            this.b.setGesturesManager(androidGesturesManager2, true, true);
        }
    }

    public final boolean q() {
        int i2 = this.f12763a;
        return i2 == 16 || i2 == 32 || i2 == 22 || i2 == 34 || i2 == 36;
    }

    public boolean r() {
        int i2 = this.f12763a;
        return i2 == 32 || i2 == 16;
    }

    public final boolean s() {
        int i2 = this.f12763a;
        return i2 == 34 || i2 == 36 || i2 == 22;
    }

    public final boolean t() {
        int i2 = this.f12763a;
        return i2 == 24 || i2 == 32 || i2 == 34 || i2 == 36;
    }

    public boolean u() {
        return this.j;
    }

    public final void v(boolean z) {
        this.d.onCameraTrackingChanged(this.f12763a);
        if (!z || t()) {
            return;
        }
        if (this.b.getUiSettings() != null) {
            this.b.getUiSettings().setFocalPoint(null);
        }
        this.d.onCameraTrackingDismissed();
    }

    public final void w(float f2) {
        if (this.j) {
            return;
        }
        this.c.moveCamera(this.b, CameraUpdateFactory.bearingTo(f2), (MapplsMap.CancelableCallback) null);
        this.g.a();
    }

    public void x(int i2) {
        y(i2, null, 750L, null, null, null, null);
    }

    public void y(int i2, @Nullable Location location, long j2, @Nullable Double d2, @Nullable Double d3, @Nullable Double d4, @Nullable OnLocationCameraTransitionListener onLocationCameraTransitionListener) {
        if (this.f12763a == i2) {
            if (onLocationCameraTransitionListener != null) {
                onLocationCameraTransitionListener.onLocationCameraTransitionFinished(i2);
                return;
            }
            return;
        }
        boolean t = t();
        this.f12763a = i2;
        if (i2 != 8) {
            this.b.cancelTransitions();
        }
        m();
        v(t);
        E(t, location, j2, d2, d3, d4, onLocationCameraTransitionListener);
    }

    public void z(boolean z) {
        this.l = z;
    }
}

package com.mappls.sdk.maps;

import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.maps.NativeMapView;
import com.mappls.sdk.maps.log.Logger;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes11.dex */
public class k implements NativeMapView.b {

    /* renamed from: a  reason: collision with root package name */
    public final List<MapView.OnCameraWillChangeListener> f12731a = new CopyOnWriteArrayList();
    public final List<MapView.OnCameraIsChangingListener> b = new CopyOnWriteArrayList();
    public final List<MapView.OnCameraDidChangeListener> c = new CopyOnWriteArrayList();
    public final List<MapView.OnWillStartLoadingMapListener> d = new CopyOnWriteArrayList();
    public final List<MapView.OnDidFinishLoadingMapListener> e = new CopyOnWriteArrayList();
    public final List<MapView.OnDidFailLoadingMapListener> f = new CopyOnWriteArrayList();
    public final List<MapView.OnWillStartRenderingFrameListener> g = new CopyOnWriteArrayList();
    public final List<MapView.OnDidFinishRenderingFrameListener> h = new CopyOnWriteArrayList();
    public final List<MapView.OnWillStartRenderingMapListener> i = new CopyOnWriteArrayList();
    public final List<MapView.OnDidFinishRenderingMapListener> j = new CopyOnWriteArrayList();
    public final List<MapView.OnDidBecomeIdleListener> k = new CopyOnWriteArrayList();
    public final List<MapView.OnDidFinishLoadingStyleListener> l = new CopyOnWriteArrayList();
    public final List<MapView.OnSourceChangedListener> m = new CopyOnWriteArrayList();
    public final List<MapView.OnStyleImageMissingListener> n = new CopyOnWriteArrayList();
    public final List<MapView.OnCanRemoveUnusedStyleImageListener> o = new CopyOnWriteArrayList();

    public void A(MapView.OnDidFinishRenderingMapListener onDidFinishRenderingMapListener) {
        this.j.remove(onDidFinishRenderingMapListener);
    }

    public void B(MapView.OnSourceChangedListener onSourceChangedListener) {
        this.m.remove(onSourceChangedListener);
    }

    public void C(MapView.OnStyleImageMissingListener onStyleImageMissingListener) {
        this.n.remove(onStyleImageMissingListener);
    }

    public void D(MapView.OnWillStartLoadingMapListener onWillStartLoadingMapListener) {
        this.d.remove(onWillStartLoadingMapListener);
    }

    public void E(MapView.OnWillStartRenderingFrameListener onWillStartRenderingFrameListener) {
        this.g.remove(onWillStartRenderingFrameListener);
    }

    public void F(MapView.OnWillStartRenderingMapListener onWillStartRenderingMapListener) {
        this.i.remove(onWillStartRenderingMapListener);
    }

    @Override // com.mappls.sdk.maps.NativeMapView.b
    public void a(String str) {
        try {
            if (this.m.isEmpty()) {
                return;
            }
            for (MapView.OnSourceChangedListener onSourceChangedListener : this.m) {
                onSourceChangedListener.onSourceChangedListener(str);
            }
        } catch (Throwable th) {
            Logger.e("Mbgl-MapChangeReceiver", "Exception in onSourceChanged", th);
            throw th;
        }
    }

    public void b(MapView.OnCameraDidChangeListener onCameraDidChangeListener) {
        this.c.add(onCameraDidChangeListener);
    }

    public void c(MapView.OnCameraIsChangingListener onCameraIsChangingListener) {
        this.b.add(onCameraIsChangingListener);
    }

    public void d(MapView.OnCameraWillChangeListener onCameraWillChangeListener) {
        this.f12731a.add(onCameraWillChangeListener);
    }

    public void e(MapView.OnCanRemoveUnusedStyleImageListener onCanRemoveUnusedStyleImageListener) {
        this.o.add(onCanRemoveUnusedStyleImageListener);
    }

    public void f(MapView.OnDidBecomeIdleListener onDidBecomeIdleListener) {
        this.k.add(onDidBecomeIdleListener);
    }

    public void g(MapView.OnDidFailLoadingMapListener onDidFailLoadingMapListener) {
        this.f.add(onDidFailLoadingMapListener);
    }

    public void h(MapView.OnDidFinishLoadingMapListener onDidFinishLoadingMapListener) {
        this.e.add(onDidFinishLoadingMapListener);
    }

    public void i(MapView.OnDidFinishLoadingStyleListener onDidFinishLoadingStyleListener) {
        this.l.add(onDidFinishLoadingStyleListener);
    }

    public void j(MapView.OnDidFinishRenderingFrameListener onDidFinishRenderingFrameListener) {
        this.h.add(onDidFinishRenderingFrameListener);
    }

    public void k(MapView.OnDidFinishRenderingMapListener onDidFinishRenderingMapListener) {
        this.j.add(onDidFinishRenderingMapListener);
    }

    public void l(MapView.OnSourceChangedListener onSourceChangedListener) {
        this.m.add(onSourceChangedListener);
    }

    public void m(MapView.OnStyleImageMissingListener onStyleImageMissingListener) {
        this.n.add(onStyleImageMissingListener);
    }

    public void n(MapView.OnWillStartLoadingMapListener onWillStartLoadingMapListener) {
        this.d.add(onWillStartLoadingMapListener);
    }

    public void o(MapView.OnWillStartRenderingFrameListener onWillStartRenderingFrameListener) {
        this.g.add(onWillStartRenderingFrameListener);
    }

    @Override // com.mappls.sdk.maps.NativeMapView.b
    public void onCameraDidChange(boolean z) {
        try {
            if (this.c.isEmpty()) {
                return;
            }
            for (MapView.OnCameraDidChangeListener onCameraDidChangeListener : this.c) {
                onCameraDidChangeListener.onCameraDidChange(z);
            }
        } catch (Throwable th) {
            Logger.e("Mbgl-MapChangeReceiver", "Exception in onCameraDidChange", th);
            throw th;
        }
    }

    @Override // com.mappls.sdk.maps.NativeMapView.b
    public void onCameraIsChanging() {
        try {
            if (this.b.isEmpty()) {
                return;
            }
            for (MapView.OnCameraIsChangingListener onCameraIsChangingListener : this.b) {
                onCameraIsChangingListener.onCameraIsChanging();
            }
        } catch (Throwable th) {
            Logger.e("Mbgl-MapChangeReceiver", "Exception in onCameraIsChanging", th);
            throw th;
        }
    }

    @Override // com.mappls.sdk.maps.NativeMapView.b
    public void onCameraWillChange(boolean z) {
        try {
            if (this.f12731a.isEmpty()) {
                return;
            }
            for (MapView.OnCameraWillChangeListener onCameraWillChangeListener : this.f12731a) {
                onCameraWillChangeListener.onCameraWillChange(z);
            }
        } catch (Throwable th) {
            Logger.e("Mbgl-MapChangeReceiver", "Exception in onCameraWillChange", th);
            throw th;
        }
    }

    @Override // com.mappls.sdk.maps.NativeMapView.b
    public boolean onCanRemoveUnusedStyleImage(String str) {
        boolean z = true;
        if (this.o.isEmpty()) {
            return true;
        }
        try {
            if (!this.o.isEmpty()) {
                for (MapView.OnCanRemoveUnusedStyleImageListener onCanRemoveUnusedStyleImageListener : this.o) {
                    z &= onCanRemoveUnusedStyleImageListener.onCanRemoveUnusedStyleImage(str);
                }
            }
            return z;
        } catch (Throwable th) {
            Logger.e("Mbgl-MapChangeReceiver", "Exception in onCanRemoveUnusedStyleImage", th);
            throw th;
        }
    }

    @Override // com.mappls.sdk.maps.NativeMapView.b
    public void onDidBecomeIdle() {
        try {
            if (this.k.isEmpty()) {
                return;
            }
            for (MapView.OnDidBecomeIdleListener onDidBecomeIdleListener : this.k) {
                onDidBecomeIdleListener.onDidBecomeIdle();
            }
        } catch (Throwable th) {
            Logger.e("Mbgl-MapChangeReceiver", "Exception in onDidBecomeIdle", th);
            throw th;
        }
    }

    @Override // com.mappls.sdk.maps.NativeMapView.b
    public void onDidFailLoadingMap(String str) {
        try {
            if (this.f.isEmpty()) {
                return;
            }
            for (MapView.OnDidFailLoadingMapListener onDidFailLoadingMapListener : this.f) {
                onDidFailLoadingMapListener.onDidFailLoadingMap(str);
            }
        } catch (Throwable th) {
            Logger.e("Mbgl-MapChangeReceiver", "Exception in onDidFailLoadingMap", th);
            throw th;
        }
    }

    @Override // com.mappls.sdk.maps.NativeMapView.b
    public void onDidFinishLoadingMap() {
        try {
            if (this.e.isEmpty()) {
                return;
            }
            for (MapView.OnDidFinishLoadingMapListener onDidFinishLoadingMapListener : this.e) {
                onDidFinishLoadingMapListener.onDidFinishLoadingMap();
            }
        } catch (Throwable th) {
            Logger.e("Mbgl-MapChangeReceiver", "Exception in onDidFinishLoadingMap", th);
            throw th;
        }
    }

    @Override // com.mappls.sdk.maps.NativeMapView.c
    public void onDidFinishLoadingStyle() {
        try {
            if (this.l.isEmpty()) {
                return;
            }
            for (MapView.OnDidFinishLoadingStyleListener onDidFinishLoadingStyleListener : this.l) {
                onDidFinishLoadingStyleListener.onDidFinishLoadingStyle();
            }
        } catch (Throwable th) {
            Logger.e("Mbgl-MapChangeReceiver", "Exception in onDidFinishLoadingStyle", th);
            throw th;
        }
    }

    @Override // com.mappls.sdk.maps.NativeMapView.b
    public void onDidFinishRenderingFrame(boolean z) {
        try {
            if (this.h.isEmpty()) {
                return;
            }
            for (MapView.OnDidFinishRenderingFrameListener onDidFinishRenderingFrameListener : this.h) {
                onDidFinishRenderingFrameListener.onDidFinishRenderingFrame(z);
            }
        } catch (Throwable th) {
            Logger.e("Mbgl-MapChangeReceiver", "Exception in onDidFinishRenderingFrame", th);
            throw th;
        }
    }

    @Override // com.mappls.sdk.maps.NativeMapView.b
    public void onDidFinishRenderingMap(boolean z) {
        try {
            if (this.j.isEmpty()) {
                return;
            }
            for (MapView.OnDidFinishRenderingMapListener onDidFinishRenderingMapListener : this.j) {
                onDidFinishRenderingMapListener.onDidFinishRenderingMap(z);
            }
        } catch (Throwable th) {
            Logger.e("Mbgl-MapChangeReceiver", "Exception in onDidFinishRenderingMap", th);
            throw th;
        }
    }

    @Override // com.mappls.sdk.maps.NativeMapView.b
    public void onStyleImageMissing(String str) {
        try {
            if (this.n.isEmpty()) {
                return;
            }
            for (MapView.OnStyleImageMissingListener onStyleImageMissingListener : this.n) {
                onStyleImageMissingListener.onStyleImageMissing(str);
            }
        } catch (Throwable th) {
            Logger.e("Mbgl-MapChangeReceiver", "Exception in onStyleImageMissing", th);
            throw th;
        }
    }

    @Override // com.mappls.sdk.maps.NativeMapView.c
    public void onWillStartLoadingMap() {
        try {
            if (this.d.isEmpty()) {
                return;
            }
            for (MapView.OnWillStartLoadingMapListener onWillStartLoadingMapListener : this.d) {
                onWillStartLoadingMapListener.onWillStartLoadingMap();
            }
        } catch (Throwable th) {
            Logger.e("Mbgl-MapChangeReceiver", "Exception in onWillStartLoadingMap", th);
            throw th;
        }
    }

    @Override // com.mappls.sdk.maps.NativeMapView.b
    public void onWillStartRenderingFrame() {
        try {
            if (this.g.isEmpty()) {
                return;
            }
            for (MapView.OnWillStartRenderingFrameListener onWillStartRenderingFrameListener : this.g) {
                onWillStartRenderingFrameListener.onWillStartRenderingFrame();
            }
        } catch (Throwable th) {
            Logger.e("Mbgl-MapChangeReceiver", "Exception in onWillStartRenderingFrame", th);
            throw th;
        }
    }

    @Override // com.mappls.sdk.maps.NativeMapView.b
    public void onWillStartRenderingMap() {
        try {
            if (this.i.isEmpty()) {
                return;
            }
            for (MapView.OnWillStartRenderingMapListener onWillStartRenderingMapListener : this.i) {
                onWillStartRenderingMapListener.onWillStartRenderingMap();
            }
        } catch (Throwable th) {
            Logger.e("Mbgl-MapChangeReceiver", "Exception in onWillStartRenderingMap", th);
            throw th;
        }
    }

    public void p(MapView.OnWillStartRenderingMapListener onWillStartRenderingMapListener) {
        this.i.add(onWillStartRenderingMapListener);
    }

    public void q() {
        this.f12731a.clear();
        this.b.clear();
        this.c.clear();
        this.d.clear();
        this.e.clear();
        this.f.clear();
        this.g.clear();
        this.h.clear();
        this.i.clear();
        this.j.clear();
        this.k.clear();
        this.l.clear();
        this.m.clear();
        this.n.clear();
        this.o.clear();
    }

    public void r(MapView.OnCameraDidChangeListener onCameraDidChangeListener) {
        this.c.remove(onCameraDidChangeListener);
    }

    public void s(MapView.OnCameraIsChangingListener onCameraIsChangingListener) {
        this.b.remove(onCameraIsChangingListener);
    }

    public void t(MapView.OnCameraWillChangeListener onCameraWillChangeListener) {
        this.f12731a.remove(onCameraWillChangeListener);
    }

    public void u(MapView.OnCanRemoveUnusedStyleImageListener onCanRemoveUnusedStyleImageListener) {
        this.o.remove(onCanRemoveUnusedStyleImageListener);
    }

    public void v(MapView.OnDidBecomeIdleListener onDidBecomeIdleListener) {
        this.k.remove(onDidBecomeIdleListener);
    }

    public void w(MapView.OnDidFailLoadingMapListener onDidFailLoadingMapListener) {
        this.f.remove(onDidFailLoadingMapListener);
    }

    public void x(MapView.OnDidFinishLoadingMapListener onDidFinishLoadingMapListener) {
        this.e.remove(onDidFinishLoadingMapListener);
    }

    public void y(MapView.OnDidFinishLoadingStyleListener onDidFinishLoadingStyleListener) {
        this.l.remove(onDidFinishLoadingStyleListener);
    }

    public void z(MapView.OnDidFinishRenderingFrameListener onDidFinishRenderingFrameListener) {
        this.h.remove(onDidFinishRenderingFrameListener);
    }
}

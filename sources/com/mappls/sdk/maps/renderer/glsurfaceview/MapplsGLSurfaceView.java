package com.mappls.sdk.maps.renderer.glsurfaceview;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.annotation.NonNull;
import com.jieli.jl_rcsp.constant.RcspErrorCode;
import com.mappls.sdk.maps.renderer.egl.EGLLogWrapper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL;
/* loaded from: classes11.dex */
public class MapplsGLSurfaceView extends SurfaceView implements SurfaceHolder.Callback2 {
    public static final d q = new d();
    public final WeakReference<MapplsGLSurfaceView> h;
    public c i;
    public GLSurfaceView.Renderer j;
    public GLSurfaceView.EGLConfigChooser k;
    public GLSurfaceView.EGLContextFactory l;
    public GLSurfaceView.EGLWindowSurfaceFactory m;
    public OnGLSurfaceViewDetachedListener n;
    public boolean o;
    public boolean p;

    /* loaded from: classes11.dex */
    public interface OnGLSurfaceViewDetachedListener {
        void onGLSurfaceViewDetached();
    }

    /* loaded from: classes11.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public WeakReference<MapplsGLSurfaceView> f12827a;
        public EGL10 b;
        public EGLDisplay c;
        public EGLSurface d;
        public EGLConfig e;
        public EGLContext f;

        public static String f(String str, int i) {
            return str + " failed: " + EGLLogWrapper.getErrorString(i);
        }

        public static void g(String str, String str2, int i) {
            Log.w(str, f(str2, i));
        }

        public GL a() {
            return this.f.getGL();
        }

        public boolean b() {
            if (this.b == null) {
                Log.e("GLSurfaceView", "egl not initialized");
                return false;
            } else if (this.c == null) {
                Log.e("GLSurfaceView", "eglDisplay not initialized");
                return false;
            } else if (this.e == null) {
                Log.e("GLSurfaceView", "mEglConfig not initialized");
                return false;
            } else {
                d();
                MapplsGLSurfaceView mapplsGLSurfaceView = this.f12827a.get();
                if (mapplsGLSurfaceView != null) {
                    this.d = mapplsGLSurfaceView.m.createWindowSurface(this.b, this.c, this.e, mapplsGLSurfaceView.getHolder());
                } else {
                    this.d = null;
                }
                EGLSurface eGLSurface = this.d;
                if (eGLSurface != null && eGLSurface != EGL10.EGL_NO_SURFACE) {
                    if (this.b.eglMakeCurrent(this.c, eGLSurface, eGLSurface, this.f)) {
                        return true;
                    }
                    g("GLSurfaceView", "eglMakeCurrent", this.b.eglGetError());
                    return false;
                }
                if (this.b.eglGetError() == 12299) {
                    Log.e("GLSurfaceView", "createWindowSurface returned EGL_BAD_NATIVE_WINDOW.");
                }
                return false;
            }
        }

        public void c() {
            d();
        }

        public final void d() {
            EGLSurface eGLSurface;
            EGLSurface eGLSurface2 = this.d;
            if (eGLSurface2 == null || eGLSurface2 == (eGLSurface = EGL10.EGL_NO_SURFACE)) {
                return;
            }
            this.b.eglMakeCurrent(this.c, eGLSurface, eGLSurface, EGL10.EGL_NO_CONTEXT);
            MapplsGLSurfaceView mapplsGLSurfaceView = this.f12827a.get();
            if (mapplsGLSurfaceView != null) {
                mapplsGLSurfaceView.m.destroySurface(this.b, this.c, this.d);
            }
            this.d = null;
        }

        public void e() {
            if (this.f != null) {
                MapplsGLSurfaceView mapplsGLSurfaceView = this.f12827a.get();
                if (mapplsGLSurfaceView != null) {
                    mapplsGLSurfaceView.l.destroyContext(this.b, this.c, this.f);
                }
                this.f = null;
            }
            EGLDisplay eGLDisplay = this.c;
            if (eGLDisplay != null) {
                this.b.eglTerminate(eGLDisplay);
                this.c = null;
            }
        }

        public void h() {
            EGLDisplay eglGetDisplay;
            try {
                EGL10 egl10 = (EGL10) EGLContext.getEGL();
                this.b = egl10;
                eglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
                this.c = eglGetDisplay;
            } catch (Exception e) {
                Log.e("GLSurfaceView", "createContext failed: ", e);
            }
            if (eglGetDisplay == EGL10.EGL_NO_DISPLAY) {
                Log.e("GLSurfaceView", "eglGetDisplay failed");
                return;
            }
            if (!this.b.eglInitialize(eglGetDisplay, new int[2])) {
                Log.e("GLSurfaceView", "eglInitialize failed");
                return;
            }
            MapplsGLSurfaceView mapplsGLSurfaceView = this.f12827a.get();
            if (mapplsGLSurfaceView != null) {
                this.e = mapplsGLSurfaceView.k.chooseConfig(this.b, this.c);
                this.f = mapplsGLSurfaceView.l.createContext(this.b, this.c, this.e);
            } else {
                this.e = null;
                this.f = null;
            }
            EGLContext eGLContext = this.f;
            if (eGLContext == null || eGLContext == EGL10.EGL_NO_CONTEXT) {
                this.f = null;
                Log.e("GLSurfaceView", "createContext failed");
                return;
            }
            this.d = null;
        }

        public int i() {
            return !this.b.eglSwapBuffers(this.c, this.d) ? this.b.eglGetError() : RcspErrorCode.ERR_CMD_SEND;
        }

        public b(WeakReference<MapplsGLSurfaceView> weakReference) {
            this.f12827a = weakReference;
        }
    }

    /* loaded from: classes11.dex */
    public static class c extends Thread {
        public b B;
        public WeakReference<MapplsGLSurfaceView> C;
        public boolean h;
        public boolean i;
        public boolean j;
        public boolean k;
        public boolean l;
        public boolean m;
        public boolean n;
        public boolean o;
        public boolean p;
        public boolean q;
        public boolean r;
        public boolean x;
        public ArrayList<Runnable> y = new ArrayList<>();
        public boolean z = true;
        public Runnable A = null;
        public int s = 0;
        public int t = 0;
        public boolean v = true;
        public int u = 1;
        public boolean w = false;

        public c(WeakReference<MapplsGLSurfaceView> weakReference) {
            this.C = weakReference;
        }

        public boolean a() {
            return this.o && this.p && i();
        }

        public int c() {
            int i;
            synchronized (MapplsGLSurfaceView.q) {
                i = this.u;
            }
            return i;
        }

        /* JADX WARN: Removed duplicated region for block: B:153:0x0211  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void d() throws java.lang.InterruptedException {
            /*
                Method dump skipped, instructions count: 578
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mappls.sdk.maps.renderer.glsurfaceview.MapplsGLSurfaceView.c.d():void");
        }

        public void e() {
            synchronized (MapplsGLSurfaceView.q) {
                this.j = true;
                MapplsGLSurfaceView.q.notifyAll();
                while (!this.i && !this.k) {
                    try {
                        MapplsGLSurfaceView.q.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void f() {
            synchronized (MapplsGLSurfaceView.q) {
                this.j = false;
                this.v = true;
                this.x = false;
                MapplsGLSurfaceView.q.notifyAll();
                while (!this.i && this.k && !this.x) {
                    try {
                        MapplsGLSurfaceView.q.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void g(int i, int i2) {
            synchronized (MapplsGLSurfaceView.q) {
                this.s = i;
                this.t = i2;
                this.z = true;
                this.v = true;
                this.x = false;
                if (Thread.currentThread() == this) {
                    return;
                }
                MapplsGLSurfaceView.q.notifyAll();
                while (!this.i && !this.k && !this.x && a()) {
                    try {
                        MapplsGLSurfaceView.q.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void h(@NonNull Runnable runnable) {
            synchronized (MapplsGLSurfaceView.q) {
                this.y.add(runnable);
                MapplsGLSurfaceView.q.notifyAll();
            }
        }

        public final boolean i() {
            return !this.k && this.l && !this.m && this.s > 0 && this.t > 0 && (this.v || this.u == 1);
        }

        public void j() {
            synchronized (MapplsGLSurfaceView.q) {
                this.h = true;
                MapplsGLSurfaceView.q.notifyAll();
                while (!this.i) {
                    try {
                        MapplsGLSurfaceView.q.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void k() {
            synchronized (MapplsGLSurfaceView.q) {
                this.v = true;
                MapplsGLSurfaceView.q.notifyAll();
            }
        }

        public void l(Runnable runnable) {
            synchronized (MapplsGLSurfaceView.q) {
                if (Thread.currentThread() == this) {
                    return;
                }
                this.w = true;
                this.v = true;
                this.x = false;
                this.A = runnable;
                MapplsGLSurfaceView.q.notifyAll();
            }
        }

        public void m(int i) {
            synchronized (MapplsGLSurfaceView.q) {
                this.u = i;
                MapplsGLSurfaceView.q.notifyAll();
            }
        }

        public final void n() {
            if (this.o) {
                this.B.e();
                this.o = false;
                MapplsGLSurfaceView.q.a(this);
            }
        }

        public final void o() {
            if (this.p) {
                this.p = false;
                this.B.c();
            }
        }

        public void p() {
            synchronized (MapplsGLSurfaceView.q) {
                this.l = true;
                this.q = false;
                MapplsGLSurfaceView.q.notifyAll();
                while (this.n && !this.q && !this.i) {
                    try {
                        MapplsGLSurfaceView.q.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void q() {
            synchronized (MapplsGLSurfaceView.q) {
                this.l = false;
                MapplsGLSurfaceView.q.notifyAll();
                while (!this.n && !this.i) {
                    try {
                        MapplsGLSurfaceView.q.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            setName("GLThread " + getId());
            try {
                d();
            } catch (InterruptedException unused) {
            } catch (Throwable th) {
                MapplsGLSurfaceView.q.b(this);
                throw th;
            }
            MapplsGLSurfaceView.q.b(this);
        }
    }

    /* loaded from: classes11.dex */
    public static class d {
        public d() {
        }

        public void a(c cVar) {
            notifyAll();
        }

        public synchronized void b(c cVar) {
            cVar.i = true;
            notifyAll();
        }
    }

    public MapplsGLSurfaceView(Context context) {
        super(context);
        this.h = new WeakReference<>(this);
        h();
    }

    public void finalize() throws Throwable {
        try {
            c cVar = this.i;
            if (cVar != null) {
                cVar.j();
            }
        } finally {
            super.finalize();
        }
    }

    public final void g() {
        if (this.i != null) {
            throw new IllegalStateException("setRenderer has already been called for this instance.");
        }
    }

    public boolean getPreserveEGLContextOnPause() {
        return this.o;
    }

    public int getRenderMode() {
        return this.i.c();
    }

    public final void h() {
        getHolder().addCallback(this);
    }

    @Override // android.view.SurfaceView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.p && this.j != null) {
            c cVar = this.i;
            int c2 = cVar != null ? cVar.c() : 1;
            c cVar2 = new c(this.h);
            this.i = cVar2;
            if (c2 != 1) {
                cVar2.m(c2);
            }
            this.i.start();
        }
        this.p = false;
    }

    @Override // android.view.SurfaceView, android.view.View
    public void onDetachedFromWindow() {
        OnGLSurfaceViewDetachedListener onGLSurfaceViewDetachedListener = this.n;
        if (onGLSurfaceViewDetachedListener != null) {
            onGLSurfaceViewDetachedListener.onGLSurfaceViewDetached();
        }
        c cVar = this.i;
        if (cVar != null) {
            cVar.j();
        }
        this.p = true;
        super.onDetachedFromWindow();
    }

    public void onPause() {
        this.i.e();
    }

    public void onResume() {
        this.i.f();
    }

    public void queueEvent(Runnable runnable) {
        this.i.h(runnable);
    }

    public void requestRender() {
        this.i.k();
    }

    public void setDetachedListener(@NonNull OnGLSurfaceViewDetachedListener onGLSurfaceViewDetachedListener) {
        if (this.n == null) {
            this.n = onGLSurfaceViewDetachedListener;
            return;
        }
        throw new IllegalArgumentException("Detached from window listener has been already set.");
    }

    public void setEGLConfigChooser(GLSurfaceView.EGLConfigChooser eGLConfigChooser) {
        g();
        this.k = eGLConfigChooser;
    }

    public void setEGLContextFactory(GLSurfaceView.EGLContextFactory eGLContextFactory) {
        g();
        this.l = eGLContextFactory;
    }

    public void setEGLWindowSurfaceFactory(GLSurfaceView.EGLWindowSurfaceFactory eGLWindowSurfaceFactory) {
        g();
        this.m = eGLWindowSurfaceFactory;
    }

    public void setPreserveEGLContextOnPause(boolean z) {
        this.o = z;
    }

    public void setRenderMode(int i) {
        this.i.m(i);
    }

    public void setRenderer(GLSurfaceView.Renderer renderer) {
        g();
        if (this.k != null) {
            if (this.l != null) {
                if (this.m != null) {
                    this.j = renderer;
                    c cVar = new c(this.h);
                    this.i = cVar;
                    cVar.start();
                    return;
                }
                throw new IllegalStateException("No eglWindowSurfaceFactory provided");
            }
            throw new IllegalStateException("No eglContextFactory provided");
        }
        throw new IllegalStateException("No eglConfigChooser provided");
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        this.i.g(i2, i3);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.i.p();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.i.q();
    }

    @Override // android.view.SurfaceHolder.Callback2
    @Deprecated
    public void surfaceRedrawNeeded(SurfaceHolder surfaceHolder) {
    }

    @Override // android.view.SurfaceHolder.Callback2
    public void surfaceRedrawNeededAsync(SurfaceHolder surfaceHolder, Runnable runnable) {
        c cVar = this.i;
        if (cVar != null) {
            cVar.l(runnable);
        }
    }

    public MapplsGLSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = new WeakReference<>(this);
        h();
    }
}

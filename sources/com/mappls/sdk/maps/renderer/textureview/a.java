package com.mappls.sdk.maps.renderer.textureview;

import android.graphics.SurfaceTexture;
import android.view.TextureView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import com.jieli.jl_rcsp.constant.RcspErrorCode;
import com.mappls.sdk.maps.log.Logger;
import com.mappls.sdk.maps.renderer.egl.EGLConfigChooser;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL10;
/* loaded from: classes11.dex */
public class a extends Thread implements TextureView.SurfaceTextureListener {
    @NonNull
    public final TextureViewMapRenderer h;
    @NonNull
    public final C0636a i;
    public final Object j = new Object();
    public final ArrayList<Runnable> k = new ArrayList<>();
    @Nullable
    public SurfaceTexture l;
    public int m;
    public int n;
    public boolean o;
    public boolean p;
    public boolean q;
    public boolean r;
    public boolean s;
    public boolean t;
    public boolean u;

    /* renamed from: com.mappls.sdk.maps.renderer.textureview.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static class C0636a {

        /* renamed from: a  reason: collision with root package name */
        public final WeakReference<TextureView> f12829a;
        public boolean b;
        public EGL10 c;
        @Nullable
        public EGLConfig d;
        public EGLDisplay e = EGL10.EGL_NO_DISPLAY;
        public EGLContext f = EGL10.EGL_NO_CONTEXT;
        public EGLSurface g = EGL10.EGL_NO_SURFACE;

        public C0636a(WeakReference<TextureView> weakReference, boolean z) {
            this.f12829a = weakReference;
            this.b = z;
        }

        public void f() {
            j();
            i();
            n();
        }

        @NonNull
        public GL10 g() {
            return (GL10) this.f.getGL();
        }

        public boolean h() {
            j();
            TextureView textureView = this.f12829a.get();
            if (textureView != null && textureView.getSurfaceTexture() != null) {
                this.g = this.c.eglCreateWindowSurface(this.e, this.d, textureView.getSurfaceTexture(), new int[]{12344});
            } else {
                this.g = EGL10.EGL_NO_SURFACE;
            }
            EGLSurface eGLSurface = this.g;
            if (eGLSurface != null && eGLSurface != EGL10.EGL_NO_SURFACE) {
                return k();
            }
            if (this.c.eglGetError() == 12299) {
                Logger.e("Mbgl-TextureViewRenderThread", "createWindowSurface returned EGL_BAD_NATIVE_WINDOW.");
            }
            return false;
        }

        public final void i() {
            EGLContext eGLContext = this.f;
            if (eGLContext == EGL10.EGL_NO_CONTEXT) {
                return;
            }
            if (!this.c.eglDestroyContext(this.e, eGLContext)) {
                Logger.w("Mbgl-TextureViewRenderThread", String.format("Could not destroy egl context. Display %s, Context %s", this.e, this.f));
            }
            this.f = EGL10.EGL_NO_CONTEXT;
        }

        public final void j() {
            EGLSurface eGLSurface = this.g;
            if (eGLSurface == EGL10.EGL_NO_SURFACE) {
                return;
            }
            if (!this.c.eglDestroySurface(this.e, eGLSurface)) {
                Logger.w("Mbgl-TextureViewRenderThread", String.format("Could not destroy egl surface. Display %s, Surface %s", this.e, this.g));
            }
            this.g = EGL10.EGL_NO_SURFACE;
        }

        public boolean k() {
            EGL10 egl10 = this.c;
            EGLDisplay eGLDisplay = this.e;
            EGLSurface eGLSurface = this.g;
            if (egl10.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, this.f)) {
                return true;
            }
            Logger.w("Mbgl-TextureViewRenderThread", String.format("eglMakeCurrent: %s", Integer.valueOf(this.c.eglGetError())));
            return false;
        }

        public void l() {
            EGL10 egl10 = (EGL10) EGLContext.getEGL();
            this.c = egl10;
            if (this.e == EGL10.EGL_NO_DISPLAY) {
                EGLDisplay eglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
                this.e = eglGetDisplay;
                if (eglGetDisplay != EGL10.EGL_NO_DISPLAY) {
                    if (!this.c.eglInitialize(eglGetDisplay, new int[2])) {
                        throw new RuntimeException("eglInitialize failed");
                    }
                } else {
                    throw new RuntimeException("eglGetDisplay failed");
                }
            }
            if (this.f12829a == null) {
                this.d = null;
                this.f = EGL10.EGL_NO_CONTEXT;
            } else if (this.f == EGL10.EGL_NO_CONTEXT) {
                EGLConfig chooseConfig = new EGLConfigChooser(this.b).chooseConfig(this.c, this.e);
                this.d = chooseConfig;
                this.f = this.c.eglCreateContext(this.e, chooseConfig, EGL10.EGL_NO_CONTEXT, new int[]{12440, 2, 12344});
            }
            if (this.f == EGL10.EGL_NO_CONTEXT) {
                throw new RuntimeException("createContext");
            }
        }

        public int m() {
            return !this.c.eglSwapBuffers(this.e, this.g) ? this.c.eglGetError() : RcspErrorCode.ERR_CMD_SEND;
        }

        public final void n() {
            EGLDisplay eGLDisplay = this.e;
            if (eGLDisplay == EGL10.EGL_NO_DISPLAY) {
                return;
            }
            if (!this.c.eglTerminate(eGLDisplay)) {
                Logger.w("Mbgl-TextureViewRenderThread", String.format("Could not terminate egl. Display %s", this.e));
            }
            this.e = EGL10.EGL_NO_DISPLAY;
        }
    }

    @UiThread
    public a(@NonNull TextureView textureView, @NonNull TextureViewMapRenderer textureViewMapRenderer) {
        textureView.setOpaque(!textureViewMapRenderer.isTranslucentSurface());
        textureView.setSurfaceTextureListener(this);
        this.h = textureViewMapRenderer;
        this.i = new C0636a(new WeakReference(textureView), textureViewMapRenderer.isTranslucentSurface());
    }

    @UiThread
    public void a() {
        synchronized (this.j) {
            this.t = true;
            this.j.notifyAll();
            while (!this.u) {
                try {
                    this.j.wait();
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    @UiThread
    public void b() {
        synchronized (this.j) {
            this.q = true;
            this.j.notifyAll();
        }
    }

    @UiThread
    public void c() {
        synchronized (this.j) {
            this.q = false;
            this.j.notifyAll();
        }
    }

    public void d(@NonNull Runnable runnable) {
        if (runnable != null) {
            synchronized (this.j) {
                this.k.add(runnable);
                this.j.notifyAll();
            }
            return;
        }
        throw new IllegalArgumentException("runnable must not be null");
    }

    public void e() {
        synchronized (this.j) {
            this.o = true;
            this.j.notifyAll();
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    @UiThread
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        synchronized (this.j) {
            this.l = surfaceTexture;
            this.m = i;
            this.n = i2;
            this.o = true;
            this.j.notifyAll();
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    @UiThread
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        synchronized (this.j) {
            this.l = null;
            this.s = true;
            this.o = false;
            this.j.notifyAll();
        }
        return true;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    @UiThread
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        synchronized (this.j) {
            this.m = i;
            this.n = i2;
            this.p = true;
            this.o = true;
            this.j.notifyAll();
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    @UiThread
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        int i;
        Runnable remove;
        int i2;
        boolean z;
        boolean z2;
        while (true) {
            try {
                synchronized (this.j) {
                    while (!this.t) {
                        i = -1;
                        if (!this.k.isEmpty()) {
                            remove = this.k.remove(0);
                            i2 = -1;
                        } else {
                            if (this.s) {
                                this.i.j();
                                this.s = false;
                            } else if (this.r) {
                                this.i.i();
                                this.r = false;
                            } else if (this.l != null && !this.q && this.o) {
                                i = this.m;
                                int i3 = this.n;
                                if (this.i.f == EGL10.EGL_NO_CONTEXT) {
                                    z = true;
                                    i2 = i3;
                                    remove = null;
                                    z2 = false;
                                } else if (this.i.g == EGL10.EGL_NO_SURFACE) {
                                    z2 = true;
                                    i2 = i3;
                                    remove = null;
                                    z = false;
                                } else {
                                    this.o = false;
                                    i2 = i3;
                                    remove = null;
                                }
                            } else {
                                this.j.wait();
                            }
                            i2 = -1;
                            remove = null;
                        }
                        z = false;
                        z2 = false;
                    }
                    this.i.f();
                    synchronized (this.j) {
                        this.u = true;
                        this.j.notifyAll();
                    }
                    return;
                }
                if (remove != null) {
                    remove.run();
                } else {
                    GL10 g = this.i.g();
                    if (z) {
                        this.i.l();
                        synchronized (this.j) {
                            if (this.i.h()) {
                                this.h.onSurfaceCreated(g, this.i.d);
                                this.h.onSurfaceChanged(g, i, i2);
                            } else {
                                this.s = true;
                            }
                        }
                    } else if (z2) {
                        synchronized (this.j) {
                            this.i.h();
                        }
                        this.h.onSurfaceChanged(g, i, i2);
                    } else if (!this.p) {
                        if (this.i.g != EGL10.EGL_NO_SURFACE) {
                            this.h.onDrawFrame(g);
                            int m = this.i.m();
                            if (m == 12288) {
                                continue;
                            } else if (m != 12302) {
                                Logger.w("Mbgl-TextureViewRenderThread", String.format("eglSwapBuffer error: %s. Waiting or new surface", Integer.valueOf(m)));
                                synchronized (this.j) {
                                    this.l = null;
                                    this.s = true;
                                }
                            } else {
                                Logger.w("Mbgl-TextureViewRenderThread", "Context lost. Waiting for re-aquire");
                                synchronized (this.j) {
                                    this.l = null;
                                    this.s = true;
                                    this.r = true;
                                }
                            }
                        }
                    } else {
                        this.h.onSurfaceChanged(g, i, i2);
                        this.p = false;
                    }
                }
            } catch (InterruptedException unused) {
                this.i.f();
                synchronized (this.j) {
                    this.u = true;
                    this.j.notifyAll();
                    return;
                }
            } catch (Throwable th) {
                this.i.f();
                synchronized (this.j) {
                    this.u = true;
                    this.j.notifyAll();
                    throw th;
                }
            }
        }
    }
}

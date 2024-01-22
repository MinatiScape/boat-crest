package com.mappls.sdk.maps.renderer.egl;

import android.opengl.GLSurfaceView;
import android.util.Log;
import androidx.annotation.Nullable;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
/* loaded from: classes11.dex */
public class EGLContextFactory implements GLSurfaceView.EGLContextFactory {
    @Override // android.opengl.GLSurfaceView.EGLContextFactory
    public EGLContext createContext(EGL10 egl10, @Nullable EGLDisplay eGLDisplay, @Nullable EGLConfig eGLConfig) {
        if (eGLDisplay != null && eGLConfig != null) {
            return egl10.eglCreateContext(eGLDisplay, eGLConfig, EGL10.EGL_NO_CONTEXT, new int[]{12440, 2, 12344});
        }
        return EGL10.EGL_NO_CONTEXT;
    }

    @Override // android.opengl.GLSurfaceView.EGLContextFactory
    public void destroyContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext) {
        if (egl10.eglDestroyContext(eGLDisplay, eGLContext)) {
            return;
        }
        Log.e("DefaultContextFactory", "display:" + eGLDisplay + " context: " + eGLContext);
        StringBuilder sb = new StringBuilder();
        sb.append("tid=");
        sb.append(Thread.currentThread().getId());
        Log.i("DefaultContextFactory", sb.toString());
    }
}

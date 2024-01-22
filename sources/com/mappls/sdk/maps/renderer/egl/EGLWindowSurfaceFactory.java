package com.mappls.sdk.maps.renderer.egl;

import android.opengl.GLSurfaceView;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
/* loaded from: classes11.dex */
public class EGLWindowSurfaceFactory implements GLSurfaceView.EGLWindowSurfaceFactory {
    @Override // android.opengl.GLSurfaceView.EGLWindowSurfaceFactory
    public EGLSurface createWindowSurface(@NonNull EGL10 egl10, @Nullable EGLDisplay eGLDisplay, @Nullable EGLConfig eGLConfig, @Nullable Object obj) {
        if (eGLDisplay == null || eGLConfig == null || obj == null) {
            return null;
        }
        try {
            return egl10.eglCreateWindowSurface(eGLDisplay, eGLConfig, obj, null);
        } catch (Exception e) {
            Log.e("EGLWindowSurfaceFactory", "eglCreateWindowSurface", e);
            return null;
        }
    }

    @Override // android.opengl.GLSurfaceView.EGLWindowSurfaceFactory
    public void destroySurface(EGL10 egl10, EGLDisplay eGLDisplay, EGLSurface eGLSurface) {
        egl10.eglDestroySurface(eGLDisplay, eGLSurface);
    }
}

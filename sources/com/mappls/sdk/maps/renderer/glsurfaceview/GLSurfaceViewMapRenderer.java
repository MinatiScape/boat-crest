package com.mappls.sdk.maps.renderer.glsurfaceview;

import android.content.Context;
import android.opengl.GLSurfaceView;
import androidx.annotation.NonNull;
import com.mappls.sdk.maps.renderer.MapRenderer;
import com.mappls.sdk.maps.renderer.egl.EGLConfigChooser;
import com.mappls.sdk.maps.renderer.egl.EGLContextFactory;
import com.mappls.sdk.maps.renderer.egl.EGLWindowSurfaceFactory;
import com.mappls.sdk.maps.renderer.glsurfaceview.MapplsGLSurfaceView;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
/* loaded from: classes11.dex */
public class GLSurfaceViewMapRenderer extends MapRenderer implements GLSurfaceView.Renderer {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final MapplsGLSurfaceView f12825a;

    /* loaded from: classes11.dex */
    public class a implements MapplsGLSurfaceView.OnGLSurfaceViewDetachedListener {
        public a() {
        }

        @Override // com.mappls.sdk.maps.renderer.glsurfaceview.MapplsGLSurfaceView.OnGLSurfaceViewDetachedListener
        public void onGLSurfaceViewDetached() {
            GLSurfaceViewMapRenderer.this.nativeReset();
        }
    }

    public GLSurfaceViewMapRenderer(Context context, MapplsGLSurfaceView mapplsGLSurfaceView, String str) {
        super(context, str);
        this.f12825a = mapplsGLSurfaceView;
        mapplsGLSurfaceView.setEGLContextFactory(new EGLContextFactory());
        mapplsGLSurfaceView.setEGLWindowSurfaceFactory(new EGLWindowSurfaceFactory());
        mapplsGLSurfaceView.setEGLConfigChooser(new EGLConfigChooser());
        mapplsGLSurfaceView.setRenderer(this);
        mapplsGLSurfaceView.setRenderMode(0);
        mapplsGLSurfaceView.setPreserveEGLContextOnPause(true);
        mapplsGLSurfaceView.setDetachedListener(new a());
    }

    @Override // com.mappls.sdk.maps.renderer.MapRenderer
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.mappls.sdk.maps.renderer.MapRenderer, android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl10) {
        super.onDrawFrame(gl10);
    }

    @Override // com.mappls.sdk.maps.renderer.MapRenderer
    public void onPause() {
        super.onPause();
    }

    @Override // com.mappls.sdk.maps.renderer.MapRenderer
    public void onResume() {
        super.onResume();
    }

    @Override // com.mappls.sdk.maps.renderer.MapRenderer
    public void onStart() {
        this.f12825a.onResume();
    }

    @Override // com.mappls.sdk.maps.renderer.MapRenderer
    public void onStop() {
        this.f12825a.onPause();
    }

    @Override // com.mappls.sdk.maps.renderer.MapRenderer, android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        super.onSurfaceChanged(gl10, i, i2);
    }

    @Override // com.mappls.sdk.maps.renderer.MapRenderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        super.onSurfaceCreated(gl10, eGLConfig);
    }

    @Override // com.mappls.sdk.maps.renderer.MapRenderer
    public void onSurfaceDestroyed() {
        super.onSurfaceDestroyed();
    }

    @Override // com.mappls.sdk.maps.renderer.MapRendererScheduler
    public void queueEvent(Runnable runnable) {
        this.f12825a.queueEvent(runnable);
    }

    @Override // com.mappls.sdk.maps.renderer.MapRendererScheduler
    public void requestRender() {
        this.f12825a.requestRender();
    }
}

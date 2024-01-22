package com.mappls.sdk.maps.renderer.textureview;

import android.content.Context;
import android.view.TextureView;
import androidx.annotation.NonNull;
import com.mappls.sdk.maps.renderer.MapRenderer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
/* loaded from: classes11.dex */
public class TextureViewMapRenderer extends MapRenderer {

    /* renamed from: a  reason: collision with root package name */
    public a f12828a;
    public boolean b;

    public TextureViewMapRenderer(@NonNull Context context, @NonNull TextureView textureView, String str, boolean z) {
        super(context, str);
        this.b = z;
        a aVar = new a(textureView, this);
        this.f12828a = aVar;
        aVar.start();
    }

    public boolean isTranslucentSurface() {
        return this.b;
    }

    @Override // com.mappls.sdk.maps.renderer.MapRenderer
    public void onDestroy() {
        this.f12828a.a();
    }

    @Override // com.mappls.sdk.maps.renderer.MapRenderer, android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl10) {
        super.onDrawFrame(gl10);
    }

    @Override // com.mappls.sdk.maps.renderer.MapRenderer
    public void onStart() {
        this.f12828a.c();
    }

    @Override // com.mappls.sdk.maps.renderer.MapRenderer
    public void onStop() {
        this.f12828a.b();
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
        this.f12828a.d(runnable);
    }

    @Override // com.mappls.sdk.maps.renderer.MapRendererScheduler
    public void requestRender() {
        this.f12828a.e();
    }
}

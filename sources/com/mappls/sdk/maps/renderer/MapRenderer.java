package com.mappls.sdk.maps.renderer;

import android.content.Context;
import androidx.annotation.CallSuper;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.mappls.sdk.maps.LibraryLoader;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.log.Logger;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
@Keep
/* loaded from: classes11.dex */
public abstract class MapRenderer implements MapRendererScheduler {
    private static final String TAG = "Mbgl-MapRenderer";
    private MapplsMap.OnFpsChangedListener onFpsChangedListener;
    private long timeElapsed;
    private long nativePtr = 0;
    private double expectedRenderTime = 0.0d;

    static {
        LibraryLoader.load();
    }

    public MapRenderer(@NonNull Context context, String str) {
        nativeInitialize(this, context.getResources().getDisplayMetrics().density, str);
    }

    private native void nativeInitialize(MapRenderer mapRenderer, float f, String str);

    private native void nativeOnSurfaceChanged(int i, int i2);

    private native void nativeOnSurfaceCreated();

    private native void nativeOnSurfaceDestroyed();

    private native void nativeRender();

    private void updateFps() {
        long nanoTime = System.nanoTime();
        this.onFpsChangedListener.onFpsChanged(1.0E9d / (nanoTime - this.timeElapsed));
        this.timeElapsed = nanoTime;
    }

    @CallSuper
    public native void finalize() throws Throwable;

    public native void nativeReset();

    public void onDestroy() {
    }

    @CallSuper
    public void onDrawFrame(GL10 gl10) {
        long nanoTime = System.nanoTime();
        try {
            nativeRender();
        } catch (Error e) {
            Logger.e(TAG, e.getMessage());
        }
        double nanoTime2 = System.nanoTime() - nanoTime;
        double d = this.expectedRenderTime;
        if (nanoTime2 < d) {
            try {
                Thread.sleep((long) ((d - nanoTime2) / 1000000.0d));
            } catch (InterruptedException e2) {
                Logger.e(TAG, e2.getMessage());
            }
        }
        if (this.onFpsChangedListener != null) {
            updateFps();
        }
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void onStart() {
    }

    public void onStop() {
    }

    @CallSuper
    public void onSurfaceChanged(@NonNull GL10 gl10, int i, int i2) {
        gl10.glViewport(0, 0, i, i2);
        nativeOnSurfaceChanged(i, i2);
    }

    @CallSuper
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        nativeOnSurfaceCreated();
    }

    @CallSuper
    public void onSurfaceDestroyed() {
        nativeOnSurfaceDestroyed();
    }

    @CallSuper
    public void queueEvent(MapRendererRunnable mapRendererRunnable) {
        queueEvent((Runnable) mapRendererRunnable);
    }

    public void setMaximumFps(int i) {
        if (i <= 0) {
            return;
        }
        this.expectedRenderTime = 1.0E9d / i;
    }

    public void setOnFpsChangedListener(MapplsMap.OnFpsChangedListener onFpsChangedListener) {
        this.onFpsChangedListener = onFpsChangedListener;
    }
}

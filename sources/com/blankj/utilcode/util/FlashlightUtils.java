package com.blankj.utilcode.util;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.util.Log;
import java.io.IOException;
import kotlinx.coroutines.DebugKt;
/* loaded from: classes.dex */
public final class FlashlightUtils {

    /* renamed from: a  reason: collision with root package name */
    public static Camera f2255a;
    public static SurfaceTexture b;

    public FlashlightUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean a() {
        if (f2255a == null) {
            try {
                f2255a = Camera.open(0);
                b = new SurfaceTexture(0);
            } catch (Throwable th) {
                Log.e("FlashlightUtils", "init failed: ", th);
                return false;
            }
        }
        if (f2255a == null) {
            Log.e("FlashlightUtils", "init failed.");
            return false;
        }
        return true;
    }

    public static void destroy() {
        Camera camera = f2255a;
        if (camera == null) {
            return;
        }
        camera.release();
        b = null;
        f2255a = null;
    }

    public static boolean isFlashlightEnable() {
        return Utils.getApp().getPackageManager().hasSystemFeature("android.hardware.camera.flash");
    }

    public static boolean isFlashlightOn() {
        if (a()) {
            return "torch".equals(f2255a.getParameters().getFlashMode());
        }
        return false;
    }

    public static void setFlashlightStatus(boolean z) {
        if (a()) {
            Camera.Parameters parameters = f2255a.getParameters();
            if (z) {
                if ("torch".equals(parameters.getFlashMode())) {
                    return;
                }
                try {
                    f2255a.setPreviewTexture(b);
                    f2255a.startPreview();
                    parameters.setFlashMode("torch");
                    f2255a.setParameters(parameters);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (DebugKt.DEBUG_PROPERTY_VALUE_OFF.equals(parameters.getFlashMode())) {
            } else {
                parameters.setFlashMode(DebugKt.DEBUG_PROPERTY_VALUE_OFF);
                f2255a.setParameters(parameters);
            }
        }
    }
}

package com.google.zxing.client.android.camera;

import android.content.Context;
import android.graphics.Point;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.client.android.camera.open.OpenCamera;
import com.google.zxing.client.android.camera.open.OpenCameraInterface;
import java.io.IOException;
/* loaded from: classes11.dex */
public final class CameraManager {
    public static final String j = "CameraManager";

    /* renamed from: a  reason: collision with root package name */
    public final b f11778a;
    public OpenCamera b;
    public a c;
    public boolean d;
    public boolean e;
    public Camera.PreviewCallback f;
    public int g = 0;
    public int h = -1;
    public long i = 5000;

    public CameraManager(Context context) {
        this.f11778a = new b(context);
    }

    public PlanarYUVLuminanceSource buildLuminanceSource(byte[] bArr, int i, int i2) {
        return new PlanarYUVLuminanceSource(bArr, i, i2, 0, 0, i, i2, false);
    }

    public synchronized void closeDriver() {
        if (isOpen()) {
            this.b.getCamera().release();
            this.b = null;
        }
    }

    public void forceAutoFocus() {
        a aVar = this.c;
        if (aVar != null) {
            aVar.e();
        }
    }

    public int getPreviewCameraId() {
        return this.h;
    }

    public Point getPreviewSize() {
        return this.f11778a.c();
    }

    public synchronized boolean isOpen() {
        boolean z;
        OpenCamera openCamera = this.b;
        if (openCamera != null) {
            z = openCamera.getCamera() != null;
        }
        return z;
    }

    public synchronized void openDriver(SurfaceHolder surfaceHolder, int i, int i2) throws IOException {
        OpenCamera openCamera = this.b;
        if (!isOpen()) {
            openCamera = OpenCameraInterface.open(this.h);
            if (openCamera != null && openCamera.getCamera() != null) {
                this.b = openCamera;
            } else {
                throw new IOException("Camera.open() failed to return object from driver");
            }
        }
        openCamera.getCamera().setPreviewDisplay(surfaceHolder);
        openCamera.getCamera().setPreviewCallback(this.f);
        openCamera.getCamera().setDisplayOrientation(this.g);
        if (!this.d) {
            this.d = true;
            this.f11778a.e(openCamera, i, i2);
        }
        Camera camera = openCamera.getCamera();
        Camera.Parameters parameters = camera.getParameters();
        String flatten = parameters == null ? null : parameters.flatten();
        try {
            this.f11778a.g(openCamera, false);
        } catch (RuntimeException unused) {
            String str = j;
            Log.w(str, "Camera rejected parameters. Setting only minimal safe-mode parameters");
            Log.i(str, "Resetting to saved camera params: " + flatten);
            if (flatten != null) {
                Camera.Parameters parameters2 = camera.getParameters();
                parameters2.unflatten(flatten);
                try {
                    camera.setParameters(parameters2);
                    this.f11778a.g(openCamera, true);
                } catch (RuntimeException unused2) {
                    Log.w(j, "Camera rejected even safe-mode parameters! No configuration");
                }
            }
        }
        camera.setPreviewDisplay(surfaceHolder);
    }

    public void setAutofocusInterval(long j2) {
        this.i = j2;
        a aVar = this.c;
        if (aVar != null) {
            aVar.d(j2);
        }
    }

    public void setDisplayOrientation(int i) {
        this.g = i;
        if (isOpen()) {
            this.b.getCamera().setDisplayOrientation(i);
        }
    }

    public void setPreviewCallback(Camera.PreviewCallback previewCallback) {
        this.f = previewCallback;
        if (isOpen()) {
            this.b.getCamera().setPreviewCallback(previewCallback);
        }
    }

    public synchronized void setPreviewCameraId(int i) {
        this.h = i;
    }

    public synchronized void setTorchEnabled(boolean z) {
        OpenCamera openCamera = this.b;
        if (openCamera != null && z != this.f11778a.d(openCamera.getCamera())) {
            a aVar = this.c;
            boolean z2 = aVar != null;
            if (z2) {
                aVar.f();
                this.c = null;
            }
            this.f11778a.j(openCamera.getCamera(), z);
            if (z2) {
                a aVar2 = new a(openCamera.getCamera());
                this.c = aVar2;
                aVar2.e();
            }
        }
    }

    public synchronized void startPreview() {
        OpenCamera openCamera = this.b;
        if (openCamera != null && !this.e) {
            openCamera.getCamera().startPreview();
            this.e = true;
            a aVar = new a(openCamera.getCamera());
            this.c = aVar;
            aVar.d(this.i);
        }
    }

    public synchronized void stopPreview() {
        a aVar = this.c;
        if (aVar != null) {
            aVar.f();
            this.c = null;
        }
        OpenCamera openCamera = this.b;
        if (openCamera != null && this.e) {
            openCamera.getCamera().stopPreview();
            this.e = false;
        }
    }
}

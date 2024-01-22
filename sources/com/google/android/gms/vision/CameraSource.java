package com.google.android.gms.vision;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.common.images.Size;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.vision.Frame;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes10.dex */
public class CameraSource {
    @SuppressLint({"InlinedApi"})
    public static final int CAMERA_FACING_BACK = 0;
    @SuppressLint({"InlinedApi"})
    public static final int CAMERA_FACING_FRONT = 1;

    /* renamed from: a  reason: collision with root package name */
    public Context f10174a;
    public final Object b;
    @GuardedBy("cameraLock")
    public Camera c;
    public int d;
    public int e;
    public Size f;
    public float g;
    public int h;
    public int i;
    public boolean j;
    public String k;
    public SurfaceTexture l;
    public boolean m;
    public Thread n;
    public b o;
    public Map<byte[], ByteBuffer> p;

    /* loaded from: classes10.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final Detector<?> f10175a;
        public CameraSource b;

        public Builder(Context context, Detector<?> detector) {
            CameraSource cameraSource = new CameraSource();
            this.b = cameraSource;
            if (context == null) {
                throw new IllegalArgumentException("No context supplied.");
            }
            if (detector != null) {
                this.f10175a = detector;
                cameraSource.f10174a = context;
                return;
            }
            throw new IllegalArgumentException("No detector supplied.");
        }

        public CameraSource build() {
            CameraSource cameraSource = this.b;
            cameraSource.getClass();
            cameraSource.o = new b(this.f10175a);
            return this.b;
        }

        public Builder setAutoFocusEnabled(boolean z) {
            this.b.j = z;
            return this;
        }

        public Builder setFacing(int i) {
            if (i == 0 || i == 1) {
                this.b.d = i;
                return this;
            }
            StringBuilder sb = new StringBuilder(27);
            sb.append("Invalid camera: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }

        public Builder setFocusMode(String str) {
            if (!str.equals("continuous-video") && !str.equals("continuous-picture")) {
                Log.w("CameraSource", String.format("FocusMode %s is not supported for now.", str));
                str = null;
            }
            this.b.k = str;
            return this;
        }

        public Builder setRequestedFps(float f) {
            if (f > 0.0f) {
                this.b.g = f;
                return this;
            }
            StringBuilder sb = new StringBuilder(28);
            sb.append("Invalid fps: ");
            sb.append(f);
            throw new IllegalArgumentException(sb.toString());
        }

        public Builder setRequestedPreviewSize(int i, int i2) {
            if (i > 0 && i <= 1000000 && i2 > 0 && i2 <= 1000000) {
                this.b.h = i;
                this.b.i = i2;
                return this;
            }
            StringBuilder sb = new StringBuilder(45);
            sb.append("Invalid preview size: ");
            sb.append(i);
            sb.append("x");
            sb.append(i2);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    /* loaded from: classes10.dex */
    public interface PictureCallback {
        void onPictureTaken(byte[] bArr);
    }

    /* loaded from: classes10.dex */
    public interface ShutterCallback {
        void onShutter();
    }

    /* loaded from: classes10.dex */
    public class a implements Camera.PreviewCallback {
        public a() {
        }

        @Override // android.hardware.Camera.PreviewCallback
        public final void onPreviewFrame(byte[] bArr, Camera camera) {
            CameraSource.this.o.c(bArr, camera);
        }
    }

    /* loaded from: classes10.dex */
    public class b implements Runnable {
        public Detector<?> h;
        public long l;
        public ByteBuffer n;
        public long i = SystemClock.elapsedRealtime();
        public final Object j = new Object();
        public boolean k = true;
        public int m = 0;

        public b(Detector<?> detector) {
            this.h = detector;
        }

        @SuppressLint({"Assert"})
        public final void a() {
            this.h.release();
            this.h = null;
        }

        public final void b(boolean z) {
            synchronized (this.j) {
                this.k = z;
                this.j.notifyAll();
            }
        }

        public final void c(byte[] bArr, Camera camera) {
            synchronized (this.j) {
                ByteBuffer byteBuffer = this.n;
                if (byteBuffer != null) {
                    camera.addCallbackBuffer(byteBuffer.array());
                    this.n = null;
                }
                if (!CameraSource.this.p.containsKey(bArr)) {
                    Log.d("CameraSource", "Skipping frame. Could not find ByteBuffer associated with the image data from the camera.");
                    return;
                }
                this.l = SystemClock.elapsedRealtime() - this.i;
                this.m++;
                this.n = (ByteBuffer) CameraSource.this.p.get(bArr);
                this.j.notifyAll();
            }
        }

        @Override // java.lang.Runnable
        @SuppressLint({"InlinedApi"})
        public final void run() {
            boolean z;
            Frame build;
            ByteBuffer byteBuffer;
            while (true) {
                synchronized (this.j) {
                    while (true) {
                        z = this.k;
                        if (!z || this.n != null) {
                            break;
                        }
                        try {
                            this.j.wait();
                        } catch (InterruptedException e) {
                            Log.d("CameraSource", "Frame processing loop terminated.", e);
                            return;
                        }
                    }
                    if (!z) {
                        return;
                    }
                    build = new Frame.Builder().setImageData(this.n, CameraSource.this.f.getWidth(), CameraSource.this.f.getHeight(), 17).setId(this.m).setTimestampMillis(this.l).setRotation(CameraSource.this.e).build();
                    byteBuffer = this.n;
                    this.n = null;
                }
                try {
                    this.h.receiveFrame(build);
                } catch (Exception e2) {
                    Log.e("CameraSource", "Exception thrown from receiver.", e2);
                } finally {
                    CameraSource.this.c.addCallbackBuffer(byteBuffer.array());
                }
            }
        }
    }

    /* loaded from: classes10.dex */
    public static class c implements Camera.ShutterCallback {

        /* renamed from: a  reason: collision with root package name */
        public ShutterCallback f10176a;

        public c() {
        }

        @Override // android.hardware.Camera.ShutterCallback
        public final void onShutter() {
            ShutterCallback shutterCallback = this.f10176a;
            if (shutterCallback != null) {
                shutterCallback.onShutter();
            }
        }
    }

    /* loaded from: classes10.dex */
    public class d implements Camera.PictureCallback {

        /* renamed from: a  reason: collision with root package name */
        public PictureCallback f10177a;

        public d() {
        }

        @Override // android.hardware.Camera.PictureCallback
        public final void onPictureTaken(byte[] bArr, Camera camera) {
            PictureCallback pictureCallback = this.f10177a;
            if (pictureCallback != null) {
                pictureCallback.onPictureTaken(bArr);
            }
            synchronized (CameraSource.this.b) {
                if (CameraSource.this.c != null) {
                    CameraSource.this.c.startPreview();
                }
            }
        }
    }

    @VisibleForTesting
    /* loaded from: classes10.dex */
    public static class e {

        /* renamed from: a  reason: collision with root package name */
        public Size f10178a;
        public Size b;

        public e(Camera.Size size, @Nullable Camera.Size size2) {
            this.f10178a = new Size(size.width, size.height);
            if (size2 != null) {
                this.b = new Size(size2.width, size2.height);
            }
        }

        public final Size a() {
            return this.f10178a;
        }

        @Nullable
        public final Size b() {
            return this.b;
        }
    }

    public CameraSource() {
        this.b = new Object();
        this.d = 0;
        this.g = 30.0f;
        this.h = 1024;
        this.i = 768;
        this.j = false;
        this.p = new HashMap();
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01e6  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01ec  */
    @android.annotation.SuppressLint({"InlinedApi"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.hardware.Camera d() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 569
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.vision.CameraSource.d():android.hardware.Camera");
    }

    public int getCameraFacing() {
        return this.d;
    }

    public Size getPreviewSize() {
        return this.f;
    }

    @SuppressLint({"InlinedApi"})
    public final byte[] i(Size size) {
        byte[] bArr = new byte[((int) Math.ceil(((size.getHeight() * size.getWidth()) * ImageFormat.getBitsPerPixel(17)) / 8.0d)) + 1];
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        if (wrap.hasArray() && wrap.array() == bArr) {
            this.p.put(bArr, wrap);
            return bArr;
        }
        throw new IllegalStateException("Failed to create valid buffer for camera source.");
    }

    public void release() {
        synchronized (this.b) {
            stop();
            this.o.a();
        }
    }

    @RequiresPermission("android.permission.CAMERA")
    public CameraSource start() throws IOException {
        synchronized (this.b) {
            if (this.c != null) {
                return this;
            }
            this.c = d();
            SurfaceTexture surfaceTexture = new SurfaceTexture(100);
            this.l = surfaceTexture;
            this.c.setPreviewTexture(surfaceTexture);
            this.m = true;
            this.c.startPreview();
            Thread thread = new Thread(this.o);
            this.n = thread;
            thread.setName("gms.vision.CameraSource");
            this.o.b(true);
            this.n.start();
            return this;
        }
    }

    public void stop() {
        synchronized (this.b) {
            this.o.b(false);
            Thread thread = this.n;
            if (thread != null) {
                try {
                    thread.join();
                } catch (InterruptedException unused) {
                    Log.d("CameraSource", "Frame processing thread interrupted on release.");
                }
                this.n = null;
            }
            Camera camera = this.c;
            if (camera != null) {
                camera.stopPreview();
                this.c.setPreviewCallbackWithBuffer(null);
                try {
                    if (this.m) {
                        this.c.setPreviewTexture(null);
                    } else {
                        this.c.setPreviewDisplay(null);
                    }
                } catch (Exception e2) {
                    String valueOf = String.valueOf(e2);
                    StringBuilder sb = new StringBuilder(valueOf.length() + 32);
                    sb.append("Failed to clear camera preview: ");
                    sb.append(valueOf);
                    Log.e("CameraSource", sb.toString());
                }
                this.c.release();
                this.c = null;
            }
            this.p.clear();
        }
    }

    public void takePicture(ShutterCallback shutterCallback, PictureCallback pictureCallback) {
        synchronized (this.b) {
            if (this.c != null) {
                c cVar = new c();
                cVar.f10176a = shutterCallback;
                d dVar = new d();
                dVar.f10177a = pictureCallback;
                this.c.takePicture(cVar, null, null, dVar);
            }
        }
    }

    @RequiresPermission("android.permission.CAMERA")
    public CameraSource start(SurfaceHolder surfaceHolder) throws IOException {
        synchronized (this.b) {
            if (this.c != null) {
                return this;
            }
            Camera d2 = d();
            this.c = d2;
            d2.setPreviewDisplay(surfaceHolder);
            this.c.startPreview();
            this.n = new Thread(this.o);
            this.o.b(true);
            this.n.start();
            this.m = false;
            return this;
        }
    }
}

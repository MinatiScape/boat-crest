package com.dlazaro66.qrcodereaderview;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.camera.CameraManager;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Map;
/* loaded from: classes9.dex */
public class QRCodeReaderView extends SurfaceView implements SurfaceHolder.Callback, Camera.PreviewCallback {
    public static final String p = QRCodeReaderView.class.getName();
    public OnQRCodeReadListener h;
    public QRCodeReader i;
    public int j;
    public int k;
    public CameraManager l;
    public boolean m;
    public a n;
    public Map<DecodeHintType, Object> o;

    /* loaded from: classes9.dex */
    public interface OnQRCodeReadListener {
        void onQRCodeRead(String str, PointF[] pointFArr);
    }

    /* loaded from: classes9.dex */
    public static class a extends AsyncTask<byte[], Void, Result> {

        /* renamed from: a  reason: collision with root package name */
        public final WeakReference<QRCodeReaderView> f7862a;
        public final WeakReference<Map<DecodeHintType, Object>> b;
        public final QRToViewPointTransformer c = new QRToViewPointTransformer();

        public a(QRCodeReaderView qRCodeReaderView, Map<DecodeHintType, Object> map) {
            this.f7862a = new WeakReference<>(qRCodeReaderView);
            this.b = new WeakReference<>(map);
        }

        @Override // android.os.AsyncTask
        /* renamed from: a */
        public Result doInBackground(byte[]... bArr) {
            QRCodeReaderView qRCodeReaderView = this.f7862a.get();
            if (qRCodeReaderView == null) {
                return null;
            }
            try {
                try {
                    return qRCodeReaderView.i.decode(new BinaryBitmap(new HybridBinarizer(qRCodeReaderView.l.buildLuminanceSource(bArr[0], qRCodeReaderView.j, qRCodeReaderView.k))), this.b.get());
                } catch (ChecksumException e) {
                    Log.d(QRCodeReaderView.p, "ChecksumException", e);
                    return null;
                } catch (FormatException e2) {
                    Log.d(QRCodeReaderView.p, "FormatException", e2);
                    return null;
                } catch (NotFoundException unused) {
                    Log.d(QRCodeReaderView.p, "No QR Code found");
                    return null;
                }
            } finally {
                qRCodeReaderView.i.reset();
            }
        }

        @Override // android.os.AsyncTask
        /* renamed from: b */
        public void onPostExecute(Result result) {
            super.onPostExecute(result);
            QRCodeReaderView qRCodeReaderView = this.f7862a.get();
            if (qRCodeReaderView == null || result == null || qRCodeReaderView.h == null) {
                return;
            }
            qRCodeReaderView.h.onQRCodeRead(result.getText(), c(qRCodeReaderView, result.getResultPoints()));
        }

        public final PointF[] c(QRCodeReaderView qRCodeReaderView, ResultPoint[] resultPointArr) {
            int cameraDisplayOrientation = qRCodeReaderView.getCameraDisplayOrientation();
            return this.c.transform(resultPointArr, qRCodeReaderView.l.getPreviewCameraId() == 1, (cameraDisplayOrientation == 90 || cameraDisplayOrientation == 270) ? Orientation.PORTRAIT : Orientation.LANDSCAPE, new Point(qRCodeReaderView.getWidth(), qRCodeReaderView.getHeight()), qRCodeReaderView.l.getPreviewSize());
        }
    }

    public QRCodeReaderView(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int getCameraDisplayOrientation() {
        /*
            r5 = this;
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 90
            r2 = 9
            if (r0 >= r2) goto L9
            return r1
        L9:
            android.hardware.Camera$CameraInfo r0 = new android.hardware.Camera$CameraInfo
            r0.<init>()
            com.google.zxing.client.android.camera.CameraManager r2 = r5.l
            int r2 = r2.getPreviewCameraId()
            android.hardware.Camera.getCameraInfo(r2, r0)
            android.content.Context r2 = r5.getContext()
            java.lang.String r3 = "window"
            java.lang.Object r2 = r2.getSystemService(r3)
            android.view.WindowManager r2 = (android.view.WindowManager) r2
            android.view.Display r2 = r2.getDefaultDisplay()
            int r2 = r2.getRotation()
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L37
            if (r2 == r3) goto L3e
            r1 = 2
            if (r2 == r1) goto L3c
            r1 = 3
            if (r2 == r1) goto L39
        L37:
            r1 = r4
            goto L3e
        L39:
            r1 = 270(0x10e, float:3.78E-43)
            goto L3e
        L3c:
            r1 = 180(0xb4, float:2.52E-43)
        L3e:
            int r2 = r0.facing
            if (r2 != r3) goto L4c
            int r0 = r0.orientation
            int r0 = r0 + r1
            int r0 = r0 % 360
            int r0 = 360 - r0
            int r0 = r0 % 360
            goto L53
        L4c:
            int r0 = r0.orientation
            int r0 = r0 - r1
            int r0 = r0 + 360
            int r0 = r0 % 360
        L53:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dlazaro66.qrcodereaderview.QRCodeReaderView.getCameraDisplayOrientation():int");
    }

    public void forceAutoFocus() {
        CameraManager cameraManager = this.l;
        if (cameraManager != null) {
            cameraManager.forceAutoFocus();
        }
    }

    public final boolean h() {
        if (getContext().getPackageManager().hasSystemFeature("android.hardware.camera") || getContext().getPackageManager().hasSystemFeature("android.hardware.camera.front")) {
            return true;
        }
        return getContext().getPackageManager().hasSystemFeature("android.hardware.camera.any");
    }

    @Override // android.view.SurfaceView, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a aVar = this.n;
        if (aVar != null) {
            aVar.cancel(true);
            this.n = null;
        }
    }

    @Override // android.hardware.Camera.PreviewCallback
    public void onPreviewFrame(byte[] bArr, Camera camera) {
        if (this.m) {
            a aVar = this.n;
            if (aVar == null || !(aVar.getStatus() == AsyncTask.Status.RUNNING || this.n.getStatus() == AsyncTask.Status.PENDING)) {
                a aVar2 = new a(this, this.o);
                this.n = aVar2;
                aVar2.execute(bArr);
            }
        }
    }

    public void setAutofocusInterval(long j) {
        CameraManager cameraManager = this.l;
        if (cameraManager != null) {
            cameraManager.setAutofocusInterval(j);
        }
    }

    public void setBackCamera() {
        setPreviewCameraId(0);
    }

    public void setDecodeHints(Map<DecodeHintType, Object> map) {
        this.o = map;
    }

    public void setFrontCamera() {
        setPreviewCameraId(1);
    }

    public void setOnQRCodeReadListener(OnQRCodeReadListener onQRCodeReadListener) {
        this.h = onQRCodeReadListener;
    }

    public void setPreviewCameraId(int i) {
        this.l.setPreviewCameraId(i);
    }

    public void setQRDecodingEnabled(boolean z) {
        this.m = z;
    }

    public void setTorchEnabled(boolean z) {
        CameraManager cameraManager = this.l;
        if (cameraManager != null) {
            cameraManager.setTorchEnabled(z);
        }
    }

    public void startCamera() {
        this.l.startPreview();
    }

    public void stopCamera() {
        this.l.stopPreview();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        String str = p;
        Log.d(str, "surfaceChanged");
        if (surfaceHolder.getSurface() == null) {
            Log.e(str, "Error: preview surface does not exist");
        } else if (this.l.getPreviewSize() == null) {
            Log.e(str, "Error: preview size does not exist");
        } else {
            this.j = this.l.getPreviewSize().x;
            this.k = this.l.getPreviewSize().y;
            this.l.stopPreview();
            this.l.setPreviewCallback(this);
            this.l.setDisplayOrientation(getCameraDisplayOrientation());
            this.l.startPreview();
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.d(p, "surfaceCreated");
        try {
            this.l.openDriver(surfaceHolder, getWidth(), getHeight());
        } catch (IOException | RuntimeException e) {
            String str = p;
            Log.w(str, "Can not openDriver: " + e.getMessage());
            this.l.closeDriver();
        }
        try {
            this.i = new QRCodeReader();
            this.l.startPreview();
        } catch (Exception e2) {
            String str2 = p;
            Log.e(str2, "Exception: " + e2.getMessage());
            this.l.closeDriver();
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Log.d(p, "surfaceDestroyed");
        this.l.setPreviewCallback(null);
        this.l.stopPreview();
        this.l.closeDriver();
    }

    public QRCodeReaderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.m = true;
        if (isInEditMode()) {
            return;
        }
        if (h()) {
            CameraManager cameraManager = new CameraManager(getContext());
            this.l = cameraManager;
            cameraManager.setPreviewCallback(this);
            getHolder().addCallback(this);
            setBackCamera();
            return;
        }
        throw new RuntimeException("Error: Camera not found");
    }
}

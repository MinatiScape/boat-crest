package com.google.zxing.client.android.camera;

import android.content.Context;
import android.graphics.Point;
import android.hardware.Camera;
import android.util.Log;
import android.view.WindowManager;
import com.google.zxing.client.android.camera.open.CameraFacing;
import com.google.zxing.client.android.camera.open.OpenCamera;
import com.realsil.sdk.dfu.DfuException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import kotlinx.coroutines.DebugKt;
/* loaded from: classes11.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    public final Context f11781a;
    public Point b;
    public Point c;
    public Point d;
    public Point e;
    public int f;
    public int g;

    /* loaded from: classes11.dex */
    public class a implements Comparator<Camera.Size> {
        public a(b bVar) {
        }

        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(Camera.Size size, Camera.Size size2) {
            int i = size.height * size.width;
            int i2 = size2.height * size2.width;
            if (i2 < i) {
                return -1;
            }
            return i2 > i ? 1 : 0;
        }
    }

    public b(Context context) {
        this.f11781a = context;
    }

    public static String b(String str, Collection<String> collection, String... strArr) {
        Log.i("CameraConfiguration", "Requesting " + str + " value from among: " + Arrays.toString(strArr));
        Log.i("CameraConfiguration", "Supported " + str + " values: " + collection);
        if (collection != null) {
            for (String str2 : strArr) {
                if (collection.contains(str2)) {
                    Log.i("CameraConfiguration", "Can set " + str + " to: " + str2);
                    return str2;
                }
            }
        }
        Log.i("CameraConfiguration", "No supported values match");
        return null;
    }

    public static void f(Camera.Parameters parameters, boolean z) {
        int minExposureCompensation = parameters.getMinExposureCompensation();
        int maxExposureCompensation = parameters.getMaxExposureCompensation();
        float exposureCompensationStep = parameters.getExposureCompensationStep();
        if (minExposureCompensation != 0 || maxExposureCompensation != 0) {
            if (exposureCompensationStep > 0.0f) {
                int round = Math.round((z ? 0.0f : 1.5f) / exposureCompensationStep);
                float f = exposureCompensationStep * round;
                int max = Math.max(Math.min(round, maxExposureCompensation), minExposureCompensation);
                if (parameters.getExposureCompensation() == max) {
                    Log.i("CameraConfiguration", "Exposure compensation already set to " + max + " / " + f);
                    return;
                }
                Log.i("CameraConfiguration", "Setting exposure compensation to " + max + " / " + f);
                parameters.setExposureCompensation(max);
                return;
            }
        }
        Log.i("CameraConfiguration", "Camera does not support exposure compensation");
    }

    public static void h(Camera.Parameters parameters, boolean z) {
        String b;
        List<String> supportedFlashModes = parameters.getSupportedFlashModes();
        if (z) {
            b = b("flash mode", supportedFlashModes, "torch", DebugKt.DEBUG_PROPERTY_VALUE_ON);
        } else {
            b = b("flash mode", supportedFlashModes, DebugKt.DEBUG_PROPERTY_VALUE_OFF);
        }
        if (b != null) {
            if (b.equals(parameters.getFlashMode())) {
                Log.i("CameraConfiguration", "Flash mode already set to " + b);
                return;
            }
            Log.i("CameraConfiguration", "Setting flash mode to " + b);
            parameters.setFlashMode(b);
        }
    }

    public Point a(Camera.Parameters parameters, Point point) {
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        if (supportedPreviewSizes == null) {
            Log.w("CameraConfiguration", "Device returned no supported preview sizes; using default");
            Camera.Size previewSize = parameters.getPreviewSize();
            return new Point(previewSize.width, previewSize.height);
        }
        ArrayList<Camera.Size> arrayList = new ArrayList(supportedPreviewSizes);
        Collections.sort(arrayList, new a(this));
        if (Log.isLoggable("CameraConfiguration", 4)) {
            StringBuilder sb = new StringBuilder();
            for (Camera.Size size : arrayList) {
                sb.append(size.width);
                sb.append('x');
                sb.append(size.height);
                sb.append(' ');
            }
            Log.i("CameraConfiguration", "Supported preview sizes: " + ((Object) sb));
        }
        Point point2 = null;
        float f = point.x / point.y;
        float f2 = Float.POSITIVE_INFINITY;
        for (Camera.Size size2 : arrayList) {
            int i = size2.width;
            int i2 = size2.height;
            int i3 = i * i2;
            if (i3 >= 150400 && i3 <= 921600) {
                boolean z = i > i2;
                int i4 = z ? i2 : i;
                int i5 = z ? i : i2;
                if (i4 == point.x && i5 == point.y) {
                    Point point3 = new Point(i, i2);
                    Log.i("CameraConfiguration", "Found preview size exactly matching screen size: " + point3);
                    return point3;
                }
                float abs = Math.abs((i4 / i5) - f);
                if (abs < f2) {
                    point2 = new Point(i, i2);
                    f2 = abs;
                }
            }
        }
        if (point2 == null) {
            Camera.Size previewSize2 = parameters.getPreviewSize();
            point2 = new Point(previewSize2.width, previewSize2.height);
            Log.i("CameraConfiguration", "No suitable preview sizes, using default: " + point2);
        }
        Log.i("CameraConfiguration", "Found best approximate preview size: " + point2);
        return point2;
    }

    public Point c() {
        return this.c;
    }

    public boolean d(Camera camera) {
        String flashMode;
        if (camera == null || camera.getParameters() == null || (flashMode = camera.getParameters().getFlashMode()) == null) {
            return false;
        }
        return DebugKt.DEBUG_PROPERTY_VALUE_ON.equals(flashMode) || "torch".equals(flashMode);
    }

    public void e(OpenCamera openCamera, int i, int i2) {
        int i3;
        Camera.Parameters parameters = openCamera.getCamera().getParameters();
        int rotation = ((WindowManager) this.f11781a.getSystemService("window")).getDefaultDisplay().getRotation();
        if (rotation == 0) {
            i3 = 0;
        } else if (rotation == 1) {
            i3 = 90;
        } else if (rotation == 2) {
            i3 = 180;
        } else if (rotation == 3) {
            i3 = DfuException.ERROR_READ_DEVICE_INFO_ERROR;
        } else if (rotation % 90 == 0) {
            i3 = (rotation + 360) % 360;
        } else {
            throw new IllegalArgumentException("Bad rotation: " + rotation);
        }
        Log.i("CameraConfiguration", "Display at: " + i3);
        int orientation = openCamera.getOrientation();
        Log.i("CameraConfiguration", "Camera at: " + orientation);
        CameraFacing facing = openCamera.getFacing();
        CameraFacing cameraFacing = CameraFacing.FRONT;
        if (facing == cameraFacing) {
            orientation = (360 - orientation) % 360;
            Log.i("CameraConfiguration", "Front camera overriden to: " + orientation);
        }
        this.f = ((orientation + 360) - i3) % 360;
        Log.i("CameraConfiguration", "Final display orientation: " + this.f);
        if (openCamera.getFacing() == cameraFacing) {
            Log.i("CameraConfiguration", "Compensating rotation for front camera");
            this.g = (360 - this.f) % 360;
        } else {
            this.g = this.f;
        }
        Log.i("CameraConfiguration", "Clockwise rotation from display to camera: " + this.g);
        this.b = new Point(i, i2);
        Log.i("CameraConfiguration", "Screen resolution in current orientation: " + this.b);
        this.c = a(parameters, this.b);
        Log.i("CameraConfiguration", "Camera resolution: " + this.c);
        this.d = a(parameters, this.b);
        Log.i("CameraConfiguration", "Best available preview size: " + this.d);
        Point point = this.b;
        boolean z = point.x < point.y;
        Point point2 = this.d;
        if (z == (point2.x < point2.y)) {
            this.e = point2;
        } else {
            Point point3 = this.d;
            this.e = new Point(point3.y, point3.x);
        }
        Log.i("CameraConfiguration", "Preview size on screen: " + this.e);
    }

    public void g(OpenCamera openCamera, boolean z) {
        Camera camera = openCamera.getCamera();
        Camera.Parameters parameters = camera.getParameters();
        if (parameters == null) {
            Log.w("CameraConfiguration", "Device error: no camera parameters are available. Proceeding without configuration.");
            return;
        }
        Log.i("CameraConfiguration", "Initial camera parameters: " + parameters.flatten());
        if (z) {
            Log.w("CameraConfiguration", "In camera config safe mode -- most settings will not be honored");
        }
        String b = z ? null : b("focus mode", parameters.getSupportedFocusModes(), "auto");
        if (b != null) {
            parameters.setFocusMode(b);
        }
        Point point = this.d;
        parameters.setPreviewSize(point.x, point.y);
        camera.setParameters(parameters);
        camera.setDisplayOrientation(this.f);
        Camera.Size previewSize = camera.getParameters().getPreviewSize();
        if (previewSize != null) {
            Point point2 = this.d;
            if (point2.x == previewSize.width && point2.y == previewSize.height) {
                return;
            }
            Log.w("CameraConfiguration", "Camera said it supported preview size " + this.d.x + 'x' + this.d.y + ", but after setting it, preview size is " + previewSize.width + 'x' + previewSize.height);
            Point point3 = this.d;
            point3.x = previewSize.width;
            point3.y = previewSize.height;
        }
    }

    public void i(Camera.Parameters parameters, boolean z, boolean z2) {
        h(parameters, z);
        if (z2) {
            return;
        }
        f(parameters, z);
    }

    public void j(Camera camera, boolean z) {
        Camera.Parameters parameters = camera.getParameters();
        i(parameters, z, false);
        camera.setParameters(parameters);
    }
}

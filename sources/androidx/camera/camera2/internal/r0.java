package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraCaptureSession;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CameraCaptureCallbacks;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class r0 {
    public static CameraCaptureSession.CaptureCallback a(CameraCaptureCallback cameraCaptureCallback) {
        if (cameraCaptureCallback == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        b(cameraCaptureCallback, arrayList);
        if (arrayList.size() == 1) {
            return (CameraCaptureSession.CaptureCallback) arrayList.get(0);
        }
        return Camera2CaptureCallbacks.a(arrayList);
    }

    public static void b(CameraCaptureCallback cameraCaptureCallback, List<CameraCaptureSession.CaptureCallback> list) {
        if (cameraCaptureCallback instanceof CameraCaptureCallbacks.ComboCameraCaptureCallback) {
            for (CameraCaptureCallback cameraCaptureCallback2 : ((CameraCaptureCallbacks.ComboCameraCaptureCallback) cameraCaptureCallback).getCallbacks()) {
                b(cameraCaptureCallback2, list);
            }
        } else if (cameraCaptureCallback instanceof q0) {
            list.add(((q0) cameraCaptureCallback).b());
        } else {
            list.add(new p0(cameraCaptureCallback));
        }
    }
}

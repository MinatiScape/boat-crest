package androidx.camera.camera2.internal.compat.workaround;

import androidx.camera.camera2.internal.compat.quirk.DeviceQuirks;
import androidx.camera.camera2.internal.compat.quirk.StillCaptureFlashStopRepeatingQuirk;
/* loaded from: classes.dex */
public class StillCaptureFlow {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f550a;

    public StillCaptureFlow() {
        this.f550a = ((StillCaptureFlashStopRepeatingQuirk) DeviceQuirks.get(StillCaptureFlashStopRepeatingQuirk.class)) != null;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0012  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean shouldStopRepeatingBeforeCapture(@androidx.annotation.NonNull java.util.List<android.hardware.camera2.CaptureRequest> r3, boolean r4) {
        /*
            r2 = this;
            boolean r0 = r2.f550a
            r1 = 0
            if (r0 == 0) goto L2c
            if (r4 != 0) goto L8
            goto L2c
        L8:
            java.util.Iterator r3 = r3.iterator()
        Lc:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L2c
            java.lang.Object r4 = r3.next()
            android.hardware.camera2.CaptureRequest r4 = (android.hardware.camera2.CaptureRequest) r4
            android.hardware.camera2.CaptureRequest$Key r0 = android.hardware.camera2.CaptureRequest.CONTROL_AE_MODE
            java.lang.Object r4 = r4.get(r0)
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            r0 = 2
            if (r4 == r0) goto L2a
            r0 = 3
            if (r4 != r0) goto Lc
        L2a:
            r3 = 1
            return r3
        L2c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.camera2.internal.compat.workaround.StillCaptureFlow.shouldStopRepeatingBeforeCapture(java.util.List, boolean):boolean");
    }
}

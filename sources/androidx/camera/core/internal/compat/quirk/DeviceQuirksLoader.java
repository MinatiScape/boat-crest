package androidx.camera.core.internal.compat.quirk;

import androidx.annotation.NonNull;
import androidx.camera.core.impl.Quirk;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class DeviceQuirksLoader {
    @NonNull
    public static List<Quirk> a() {
        ArrayList arrayList = new ArrayList();
        if (IncompleteCameraListQuirk.a()) {
            arrayList.add(new IncompleteCameraListQuirk());
        }
        if (ImageCaptureRotationOptionQuirk.c()) {
            arrayList.add(new ImageCaptureRotationOptionQuirk());
        }
        if (ImageCaptureWashedOutImageQuirk.a()) {
            arrayList.add(new ImageCaptureWashedOutImageQuirk());
        }
        return arrayList;
    }
}

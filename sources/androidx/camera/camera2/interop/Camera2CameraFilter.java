package androidx.camera.camera2.interop;

import androidx.annotation.NonNull;
import androidx.camera.camera2.interop.Camera2CameraFilter;
import androidx.camera.core.CameraFilter;
import androidx.camera.core.CameraInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
@ExperimentalCamera2Interop
/* loaded from: classes.dex */
public final class Camera2CameraFilter {

    /* loaded from: classes.dex */
    public interface Camera2Filter {
        @NonNull
        List<Camera2CameraInfo> filter(@NonNull List<Camera2CameraInfo> list);
    }

    public static /* synthetic */ List b(Camera2Filter camera2Filter, List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(Camera2CameraInfo.from((CameraInfo) it.next()));
        }
        List<Camera2CameraInfo> filter = camera2Filter.filter(Collections.unmodifiableList(arrayList));
        ArrayList arrayList2 = new ArrayList();
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            CameraInfo cameraInfo = (CameraInfo) it2.next();
            if (filter.contains(Camera2CameraInfo.from(cameraInfo))) {
                arrayList2.add(cameraInfo);
            }
        }
        return arrayList2;
    }

    @NonNull
    public static CameraFilter createCameraFilter(@NonNull final Camera2Filter camera2Filter) {
        return new CameraFilter() { // from class: androidx.camera.camera2.interop.i
            @Override // androidx.camera.core.CameraFilter
            public final List filter(List list) {
                List b;
                b = Camera2CameraFilter.b(Camera2CameraFilter.Camera2Filter.this, list);
                return b;
            }
        };
    }
}

package androidx.camera.core.impl;

import androidx.camera.core.CameraFilter;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public class CameraFilters {
    public static final CameraFilter ANY = new CameraFilter() { // from class: androidx.camera.core.impl.f
        @Override // androidx.camera.core.CameraFilter
        public final List filter(List list) {
            List c;
            c = CameraFilters.c(list);
            return c;
        }
    };
    public static final CameraFilter NONE = new CameraFilter() { // from class: androidx.camera.core.impl.g
        @Override // androidx.camera.core.CameraFilter
        public final List filter(List list) {
            return CameraFilters.d(list);
        }
    };

    public static /* synthetic */ List c(List list) {
        return list;
    }

    public static /* synthetic */ List d(List list) {
        return Collections.emptyList();
    }
}

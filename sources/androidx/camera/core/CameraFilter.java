package androidx.camera.core;

import androidx.annotation.NonNull;
import java.util.List;
@ExperimentalCameraFilter
/* loaded from: classes.dex */
public interface CameraFilter {
    @NonNull
    List<CameraInfo> filter(@NonNull List<CameraInfo> list);
}

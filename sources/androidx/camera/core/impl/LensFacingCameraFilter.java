package androidx.camera.core.impl;

import androidx.annotation.NonNull;
import androidx.annotation.experimental.UseExperimental;
import androidx.camera.core.CameraFilter;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.ExperimentalCameraFilter;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.List;
@UseExperimental(markerClass = ExperimentalCameraFilter.class)
/* loaded from: classes.dex */
public class LensFacingCameraFilter implements CameraFilter {

    /* renamed from: a  reason: collision with root package name */
    public int f704a;

    public LensFacingCameraFilter(int i) {
        this.f704a = i;
    }

    @Override // androidx.camera.core.CameraFilter
    @NonNull
    public List<CameraInfo> filter(@NonNull List<CameraInfo> list) {
        ArrayList arrayList = new ArrayList();
        for (CameraInfo cameraInfo : list) {
            Preconditions.checkArgument(cameraInfo instanceof CameraInfoInternal, "The camera info doesn't contain internal implementation.");
            Integer lensFacing = ((CameraInfoInternal) cameraInfo).getLensFacing();
            if (lensFacing != null && lensFacing.intValue() == this.f704a) {
                arrayList.add(cameraInfo);
            }
        }
        return arrayList;
    }

    public int getLensFacing() {
        return this.f704a;
    }
}

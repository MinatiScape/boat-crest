package androidx.camera.camera2.internal.compat.workaround;

import android.util.Size;
import androidx.annotation.NonNull;
import androidx.camera.camera2.internal.compat.quirk.DeviceQuirks;
import androidx.camera.camera2.internal.compat.quirk.ExcludedSupportedSizesQuirk;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class ExcludedSupportedSizesContainer {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final String f549a;

    public ExcludedSupportedSizesContainer(@NonNull String str) {
        this.f549a = str;
    }

    @NonNull
    public List<Size> get(int i) {
        ExcludedSupportedSizesQuirk excludedSupportedSizesQuirk = (ExcludedSupportedSizesQuirk) DeviceQuirks.get(ExcludedSupportedSizesQuirk.class);
        if (excludedSupportedSizesQuirk == null) {
            return new ArrayList();
        }
        return excludedSupportedSizesQuirk.getExcludedSizes(this.f549a, i);
    }
}

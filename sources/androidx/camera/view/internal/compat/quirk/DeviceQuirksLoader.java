package androidx.camera.view.internal.compat.quirk;

import androidx.annotation.NonNull;
import androidx.camera.core.impl.Quirk;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class DeviceQuirksLoader {
    @NonNull
    public static List<Quirk> a() {
        ArrayList arrayList = new ArrayList();
        if (PreviewOneThirdWiderQuirk.a()) {
            arrayList.add(new PreviewOneThirdWiderQuirk());
        }
        if (SurfaceViewStretchedQuirk.a()) {
            arrayList.add(new SurfaceViewStretchedQuirk());
        }
        if (TextureViewRotationQuirk.b()) {
            arrayList.add(new TextureViewRotationQuirk());
        }
        return arrayList;
    }
}

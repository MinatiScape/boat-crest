package androidx.camera.core.internal.compat.quirk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.impl.Quirk;
import androidx.camera.core.impl.Quirks;
/* loaded from: classes.dex */
public class DeviceQuirks {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public static final Quirks f758a = new Quirks(DeviceQuirksLoader.a());

    @Nullable
    public static <T extends Quirk> T get(@NonNull Class<T> cls) {
        return (T) f758a.get(cls);
    }
}

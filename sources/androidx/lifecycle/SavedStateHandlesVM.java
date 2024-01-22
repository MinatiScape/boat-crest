package androidx.lifecycle;

import java.util.LinkedHashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class SavedStateHandlesVM extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Map<String, SavedStateHandle> f1363a = new LinkedHashMap();

    @NotNull
    public final Map<String, SavedStateHandle> getHandles() {
        return this.f1363a;
    }
}

package kotlinx.coroutines;

import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class AbstractTimeSourceKt {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public static AbstractTimeSource f14134a;

    @Nullable
    public static final AbstractTimeSource getTimeSource() {
        return f14134a;
    }

    public static final void setTimeSource(@Nullable AbstractTimeSource abstractTimeSource) {
        f14134a = abstractTimeSource;
    }
}

package kotlinx.coroutines.debug.internal;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class AgentInstallationType {
    @NotNull
    public static final AgentInstallationType INSTANCE = new AgentInstallationType();

    /* renamed from: a  reason: collision with root package name */
    public static boolean f14151a;

    public final boolean isInstalledStatically$kotlinx_coroutines_core() {
        return f14151a;
    }

    public final void setInstalledStatically$kotlinx_coroutines_core(boolean z) {
        f14151a = z;
    }
}

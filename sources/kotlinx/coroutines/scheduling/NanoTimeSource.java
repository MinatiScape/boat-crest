package kotlinx.coroutines.scheduling;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class NanoTimeSource extends SchedulerTimeSource {
    @NotNull
    public static final NanoTimeSource INSTANCE = new NanoTimeSource();

    @Override // kotlinx.coroutines.scheduling.SchedulerTimeSource
    public long nanoTime() {
        return System.nanoTime();
    }
}

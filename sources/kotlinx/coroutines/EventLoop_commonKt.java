package kotlinx.coroutines;

import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class EventLoop_commonKt {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final Symbol f14140a = new Symbol("REMOVED_TASK");
    @NotNull
    public static final Symbol b = new Symbol("CLOSED_EMPTY");

    public static final long delayNanosToMillis(long j) {
        return j / 1000000;
    }

    public static final long delayToNanos(long j) {
        if (j <= 0) {
            return 0L;
        }
        if (j >= 9223372036854L) {
            return Long.MAX_VALUE;
        }
        return 1000000 * j;
    }
}

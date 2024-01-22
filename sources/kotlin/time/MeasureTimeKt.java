package kotlin.time;

import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.TimeSource;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class MeasureTimeKt {
    @SinceKotlin(version = "1.3")
    @ExperimentalTime
    public static final long measureTime(@NotNull Function0<Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        long m702markNowz9LOYto = TimeSource.Monotonic.INSTANCE.m702markNowz9LOYto();
        block.invoke();
        return TimeSource.Monotonic.ValueTimeMark.m707elapsedNowUwyO8pc(m702markNowz9LOYto);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalTime
    @NotNull
    public static final <T> TimedValue<T> measureTimedValue(@NotNull Function0<? extends T> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        return new TimedValue<>(block.invoke(), TimeSource.Monotonic.ValueTimeMark.m707elapsedNowUwyO8pc(TimeSource.Monotonic.INSTANCE.m702markNowz9LOYto()), null);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalTime
    public static final long measureTime(@NotNull TimeSource timeSource, @NotNull Function0<Unit> block) {
        Intrinsics.checkNotNullParameter(timeSource, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        TimeMark markNow = timeSource.markNow();
        block.invoke();
        return markNow.mo588elapsedNowUwyO8pc();
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalTime
    @NotNull
    public static final <T> TimedValue<T> measureTimedValue(@NotNull TimeSource timeSource, @NotNull Function0<? extends T> block) {
        Intrinsics.checkNotNullParameter(timeSource, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        return new TimedValue<>(block.invoke(), timeSource.markNow().mo588elapsedNowUwyO8pc(), null);
    }

    @SinceKotlin(version = "1.7")
    @ExperimentalTime
    public static final long measureTime(@NotNull TimeSource.Monotonic monotonic, @NotNull Function0<Unit> block) {
        Intrinsics.checkNotNullParameter(monotonic, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        long m702markNowz9LOYto = monotonic.m702markNowz9LOYto();
        block.invoke();
        return TimeSource.Monotonic.ValueTimeMark.m707elapsedNowUwyO8pc(m702markNowz9LOYto);
    }

    @SinceKotlin(version = "1.7")
    @ExperimentalTime
    @NotNull
    public static final <T> TimedValue<T> measureTimedValue(@NotNull TimeSource.Monotonic monotonic, @NotNull Function0<? extends T> block) {
        Intrinsics.checkNotNullParameter(monotonic, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        return new TimedValue<>(block.invoke(), TimeSource.Monotonic.ValueTimeMark.m707elapsedNowUwyO8pc(monotonic.m702markNowz9LOYto()), null);
    }
}

package kotlin.time;

import kotlin.SinceKotlin;
import kotlin.time.TimeSource;
import org.jetbrains.annotations.NotNull;
@SinceKotlin(version = "1.3")
@ExperimentalTime
/* loaded from: classes12.dex */
public final class MonotonicTimeSource implements TimeSource.WithComparableMarks {
    @NotNull
    public static final MonotonicTimeSource INSTANCE = new MonotonicTimeSource();

    /* renamed from: a  reason: collision with root package name */
    public static final long f14131a = System.nanoTime();

    public final long a() {
        return System.nanoTime() - f14131a;
    }

    /* renamed from: adjustReading-6QKq23U  reason: not valid java name */
    public final long m695adjustReading6QKq23U(long j, long j2) {
        return TimeSource.Monotonic.ValueTimeMark.m706constructorimpl(LongSaturatedMathKt.m694saturatingAddpTJri5U(j, j2));
    }

    /* renamed from: differenceBetween-fRLX17w  reason: not valid java name */
    public final long m696differenceBetweenfRLX17w(long j, long j2) {
        return LongSaturatedMathKt.saturatingOriginsDiff(j, j2);
    }

    /* renamed from: elapsedFrom-6eNON_k  reason: not valid java name */
    public final long m697elapsedFrom6eNON_k(long j) {
        return LongSaturatedMathKt.saturatingDiff(a(), j);
    }

    @Override // kotlin.time.TimeSource.WithComparableMarks, kotlin.time.TimeSource
    public /* bridge */ /* synthetic */ ComparableTimeMark markNow() {
        return TimeSource.Monotonic.ValueTimeMark.m703boximpl(m698markNowz9LOYto());
    }

    /* renamed from: markNow-z9LOYto  reason: not valid java name */
    public long m698markNowz9LOYto() {
        return TimeSource.Monotonic.ValueTimeMark.m706constructorimpl(a());
    }

    @NotNull
    public String toString() {
        return "TimeSource(System.nanoTime())";
    }

    @Override // kotlin.time.TimeSource
    public /* bridge */ /* synthetic */ TimeMark markNow() {
        return TimeSource.Monotonic.ValueTimeMark.m703boximpl(m698markNowz9LOYto());
    }
}

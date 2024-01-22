package kotlin.time;

import kotlin.time.Duration;
/* loaded from: classes12.dex */
public final class LongSaturatedMathKt {
    public static final long a(long j, long j2, long j3) {
        if (!Duration.m622isInfiniteimpl(j2) || (j ^ j3) >= 0) {
            return j;
        }
        throw new IllegalArgumentException("Summing infinities of different signs");
    }

    public static final long b(long j, long j2) {
        long m598divUwyO8pc = Duration.m598divUwyO8pc(j2, 2);
        if (((Duration.m615getInWholeNanosecondsimpl(m598divUwyO8pc) - 1) | 1) == Long.MAX_VALUE) {
            return (long) (j + Duration.m633toDoubleimpl(j2, DurationUnit.NANOSECONDS));
        }
        return m694saturatingAddpTJri5U(m694saturatingAddpTJri5U(j, m598divUwyO8pc), Duration.m625minusLRDsOJo(j2, m598divUwyO8pc));
    }

    public static final long c(long j, long j2) {
        long j3 = j - j2;
        if (((j3 ^ j) & (~(j3 ^ j2))) < 0) {
            long j4 = (long) DurationKt.NANOS_IN_MILLIS;
            long j5 = (j % j4) - (j2 % j4);
            Duration.Companion companion = Duration.Companion;
            return Duration.m626plusLRDsOJo(DurationKt.toDuration((j / j4) - (j2 / j4), DurationUnit.MILLISECONDS), DurationKt.toDuration(j5, DurationUnit.NANOSECONDS));
        }
        Duration.Companion companion2 = Duration.Companion;
        return DurationKt.toDuration(j3, DurationUnit.NANOSECONDS);
    }

    /* renamed from: saturatingAdd-pTJri5U  reason: not valid java name */
    public static final long m694saturatingAddpTJri5U(long j, long j2) {
        long m615getInWholeNanosecondsimpl = Duration.m615getInWholeNanosecondsimpl(j2);
        if (((j - 1) | 1) == Long.MAX_VALUE) {
            return a(j, j2, m615getInWholeNanosecondsimpl);
        }
        if ((1 | (m615getInWholeNanosecondsimpl - 1)) == Long.MAX_VALUE) {
            return b(j, j2);
        }
        long j3 = j + m615getInWholeNanosecondsimpl;
        return ((j ^ j3) & (m615getInWholeNanosecondsimpl ^ j3)) < 0 ? j < 0 ? Long.MIN_VALUE : Long.MAX_VALUE : j3;
    }

    public static final long saturatingDiff(long j, long j2) {
        if ((1 | (j2 - 1)) == Long.MAX_VALUE) {
            return Duration.m642unaryMinusUwyO8pc(DurationKt.toDuration(j2, DurationUnit.DAYS));
        }
        return c(j, j2);
    }

    public static final long saturatingOriginsDiff(long j, long j2) {
        if (((j2 - 1) | 1) == Long.MAX_VALUE) {
            if (j == j2) {
                return Duration.Companion.m671getZEROUwyO8pc();
            }
            return Duration.m642unaryMinusUwyO8pc(DurationKt.toDuration(j2, DurationUnit.DAYS));
        }
        if ((1 | (j - 1)) == Long.MAX_VALUE) {
            return DurationKt.toDuration(j, DurationUnit.DAYS);
        }
        return c(j, j2);
    }
}

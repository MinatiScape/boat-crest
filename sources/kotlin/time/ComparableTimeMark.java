package kotlin.time;

import kotlin.SinceKotlin;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.TimeMark;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@SinceKotlin(version = "1.8")
@ExperimentalTime
/* loaded from: classes12.dex */
public interface ComparableTimeMark extends TimeMark, Comparable<ComparableTimeMark> {

    /* loaded from: classes12.dex */
    public static final class DefaultImpls {
        public static int compareTo(@NotNull ComparableTimeMark comparableTimeMark, @NotNull ComparableTimeMark other) {
            Intrinsics.checkNotNullParameter(other, "other");
            return Duration.m594compareToLRDsOJo(comparableTimeMark.mo590minusUwyO8pc(other), Duration.Companion.m671getZEROUwyO8pc());
        }

        public static boolean hasNotPassedNow(@NotNull ComparableTimeMark comparableTimeMark) {
            return TimeMark.DefaultImpls.hasNotPassedNow(comparableTimeMark);
        }

        public static boolean hasPassedNow(@NotNull ComparableTimeMark comparableTimeMark) {
            return TimeMark.DefaultImpls.hasPassedNow(comparableTimeMark);
        }

        @NotNull
        /* renamed from: minus-LRDsOJo  reason: not valid java name */
        public static ComparableTimeMark m592minusLRDsOJo(@NotNull ComparableTimeMark comparableTimeMark, long j) {
            return comparableTimeMark.mo591plusLRDsOJo(Duration.m642unaryMinusUwyO8pc(j));
        }
    }

    int compareTo(@NotNull ComparableTimeMark comparableTimeMark);

    boolean equals(@Nullable Object obj);

    int hashCode();

    @Override // kotlin.time.TimeMark
    @NotNull
    /* renamed from: minus-LRDsOJo */
    ComparableTimeMark mo589minusLRDsOJo(long j);

    /* renamed from: minus-UwyO8pc */
    long mo590minusUwyO8pc(@NotNull ComparableTimeMark comparableTimeMark);

    @Override // kotlin.time.TimeMark
    @NotNull
    /* renamed from: plus-LRDsOJo */
    ComparableTimeMark mo591plusLRDsOJo(long j);
}

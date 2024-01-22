package kotlin.time;

import kotlin.SinceKotlin;
import org.jetbrains.annotations.NotNull;
@SinceKotlin(version = "1.3")
@ExperimentalTime
/* loaded from: classes12.dex */
public interface TimeMark {

    /* loaded from: classes12.dex */
    public static final class DefaultImpls {
        public static boolean hasNotPassedNow(@NotNull TimeMark timeMark) {
            return Duration.m623isNegativeimpl(timeMark.mo588elapsedNowUwyO8pc());
        }

        public static boolean hasPassedNow(@NotNull TimeMark timeMark) {
            return !Duration.m623isNegativeimpl(timeMark.mo588elapsedNowUwyO8pc());
        }

        @NotNull
        /* renamed from: minus-LRDsOJo  reason: not valid java name */
        public static TimeMark m700minusLRDsOJo(@NotNull TimeMark timeMark, long j) {
            return timeMark.mo591plusLRDsOJo(Duration.m642unaryMinusUwyO8pc(j));
        }

        @NotNull
        /* renamed from: plus-LRDsOJo  reason: not valid java name */
        public static TimeMark m701plusLRDsOJo(@NotNull TimeMark timeMark, long j) {
            return new a(timeMark, j, null);
        }
    }

    /* renamed from: elapsedNow-UwyO8pc */
    long mo588elapsedNowUwyO8pc();

    boolean hasNotPassedNow();

    boolean hasPassedNow();

    @NotNull
    /* renamed from: minus-LRDsOJo */
    TimeMark mo589minusLRDsOJo(long j);

    @NotNull
    /* renamed from: plus-LRDsOJo */
    TimeMark mo591plusLRDsOJo(long j);
}

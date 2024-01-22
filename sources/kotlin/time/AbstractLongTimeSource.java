package kotlin.time;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.ComparableTimeMark;
import kotlin.time.Duration;
import kotlin.time.TimeSource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@SinceKotlin(version = "1.3")
@ExperimentalTime
/* loaded from: classes12.dex */
public abstract class AbstractLongTimeSource implements TimeSource.WithComparableMarks {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final DurationUnit f14129a;

    /* loaded from: classes12.dex */
    public static final class a implements ComparableTimeMark {
        public final long h;
        @NotNull
        public final AbstractLongTimeSource i;
        public final long j;

        public a(long j, AbstractLongTimeSource abstractLongTimeSource, long j2) {
            this.h = j;
            this.i = abstractLongTimeSource;
            this.j = j2;
        }

        public /* synthetic */ a(long j, AbstractLongTimeSource abstractLongTimeSource, long j2, DefaultConstructorMarker defaultConstructorMarker) {
            this(j, abstractLongTimeSource, j2);
        }

        public final long a() {
            if (Duration.m622isInfiniteimpl(this.j)) {
                return this.j;
            }
            DurationUnit unit = this.i.getUnit();
            DurationUnit durationUnit = DurationUnit.MILLISECONDS;
            if (unit.compareTo(durationUnit) >= 0) {
                return Duration.m626plusLRDsOJo(DurationKt.toDuration(this.h, unit), this.j);
            }
            long convertDurationUnit = DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(1L, durationUnit, unit);
            long j = this.h;
            long j2 = j / convertDurationUnit;
            long j3 = j % convertDurationUnit;
            long j4 = this.j;
            long m616getInWholeSecondsimpl = Duration.m616getInWholeSecondsimpl(j4);
            int m618getNanosecondsComponentimpl = Duration.m618getNanosecondsComponentimpl(j4);
            int i = m618getNanosecondsComponentimpl / DurationKt.NANOS_IN_MILLIS;
            int i2 = m618getNanosecondsComponentimpl % DurationKt.NANOS_IN_MILLIS;
            long duration = DurationKt.toDuration(j3, unit);
            Duration.Companion companion = Duration.Companion;
            return Duration.m626plusLRDsOJo(Duration.m626plusLRDsOJo(Duration.m626plusLRDsOJo(duration, DurationKt.toDuration(i2, DurationUnit.NANOSECONDS)), DurationKt.toDuration(j2 + i, durationUnit)), DurationKt.toDuration(m616getInWholeSecondsimpl, DurationUnit.SECONDS));
        }

        @Override // kotlin.time.TimeMark
        /* renamed from: elapsedNow-UwyO8pc */
        public long mo588elapsedNowUwyO8pc() {
            return Duration.m622isInfiniteimpl(this.j) ? Duration.m642unaryMinusUwyO8pc(this.j) : Duration.m625minusLRDsOJo(DurationKt.toDuration(this.i.read() - this.h, this.i.getUnit()), this.j);
        }

        @Override // kotlin.time.ComparableTimeMark
        public boolean equals(@Nullable Object obj) {
            return (obj instanceof a) && Intrinsics.areEqual(this.i, ((a) obj).i) && Duration.m600equalsimpl0(mo590minusUwyO8pc((ComparableTimeMark) obj), Duration.Companion.m671getZEROUwyO8pc());
        }

        @Override // kotlin.time.TimeMark
        public boolean hasNotPassedNow() {
            return ComparableTimeMark.DefaultImpls.hasNotPassedNow(this);
        }

        @Override // kotlin.time.TimeMark
        public boolean hasPassedNow() {
            return ComparableTimeMark.DefaultImpls.hasPassedNow(this);
        }

        @Override // kotlin.time.ComparableTimeMark
        public int hashCode() {
            return Duration.m620hashCodeimpl(a());
        }

        @Override // kotlin.time.TimeMark
        @NotNull
        /* renamed from: minus-LRDsOJo */
        public ComparableTimeMark mo589minusLRDsOJo(long j) {
            return ComparableTimeMark.DefaultImpls.m592minusLRDsOJo(this, j);
        }

        @Override // kotlin.time.ComparableTimeMark
        /* renamed from: minus-UwyO8pc */
        public long mo590minusUwyO8pc(@NotNull ComparableTimeMark other) {
            Intrinsics.checkNotNullParameter(other, "other");
            if (other instanceof a) {
                a aVar = (a) other;
                if (Intrinsics.areEqual(this.i, aVar.i)) {
                    if (Duration.m600equalsimpl0(this.j, aVar.j) && Duration.m622isInfiniteimpl(this.j)) {
                        return Duration.Companion.m671getZEROUwyO8pc();
                    }
                    long m625minusLRDsOJo = Duration.m625minusLRDsOJo(this.j, aVar.j);
                    long duration = DurationKt.toDuration(this.h - aVar.h, this.i.getUnit());
                    return Duration.m600equalsimpl0(duration, Duration.m642unaryMinusUwyO8pc(m625minusLRDsOJo)) ? Duration.Companion.m671getZEROUwyO8pc() : Duration.m626plusLRDsOJo(duration, m625minusLRDsOJo);
                }
            }
            throw new IllegalArgumentException("Subtracting or comparing time marks from different time sources is not possible: " + this + " and " + other);
        }

        @NotNull
        public String toString() {
            return "LongTimeMark(" + this.h + DurationUnitKt__DurationUnitKt.shortName(this.i.getUnit()) + " + " + ((Object) Duration.m639toStringimpl(this.j)) + " (=" + ((Object) Duration.m639toStringimpl(a())) + "), " + this.i + HexStringBuilder.COMMENT_END_CHAR;
        }

        @Override // java.lang.Comparable
        public int compareTo(@NotNull ComparableTimeMark comparableTimeMark) {
            return ComparableTimeMark.DefaultImpls.compareTo(this, comparableTimeMark);
        }

        @Override // kotlin.time.TimeMark
        @NotNull
        /* renamed from: plus-LRDsOJo */
        public ComparableTimeMark mo591plusLRDsOJo(long j) {
            return new a(this.h, this.i, Duration.m626plusLRDsOJo(this.j, j), null);
        }
    }

    public AbstractLongTimeSource(@NotNull DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        this.f14129a = unit;
    }

    @NotNull
    public final DurationUnit getUnit() {
        return this.f14129a;
    }

    public abstract long read();

    @Override // kotlin.time.TimeSource
    @NotNull
    public ComparableTimeMark markNow() {
        return new a(read(), this, Duration.Companion.m671getZEROUwyO8pc(), null);
    }
}

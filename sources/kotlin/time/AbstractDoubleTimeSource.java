package kotlin.time;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.Deprecated;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.ComparableTimeMark;
import kotlin.time.TimeSource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Deprecated(message = "Using AbstractDoubleTimeSource is no longer recommended, use AbstractLongTimeSource instead.")
@SinceKotlin(version = "1.3")
@ExperimentalTime
/* loaded from: classes12.dex */
public abstract class AbstractDoubleTimeSource implements TimeSource.WithComparableMarks {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final DurationUnit f14128a;

    /* loaded from: classes12.dex */
    public static final class a implements ComparableTimeMark {
        public final double h;
        @NotNull
        public final AbstractDoubleTimeSource i;
        public final long j;

        public a(double d, AbstractDoubleTimeSource abstractDoubleTimeSource, long j) {
            this.h = d;
            this.i = abstractDoubleTimeSource;
            this.j = j;
        }

        public /* synthetic */ a(double d, AbstractDoubleTimeSource abstractDoubleTimeSource, long j, DefaultConstructorMarker defaultConstructorMarker) {
            this(d, abstractDoubleTimeSource, j);
        }

        @Override // kotlin.time.TimeMark
        /* renamed from: elapsedNow-UwyO8pc  reason: not valid java name */
        public long mo588elapsedNowUwyO8pc() {
            return Duration.m625minusLRDsOJo(DurationKt.toDuration(this.i.read() - this.h, this.i.getUnit()), this.j);
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
            return Duration.m620hashCodeimpl(Duration.m626plusLRDsOJo(DurationKt.toDuration(this.h, this.i.getUnit()), this.j));
        }

        @Override // kotlin.time.TimeMark
        @NotNull
        /* renamed from: minus-LRDsOJo  reason: not valid java name */
        public ComparableTimeMark mo589minusLRDsOJo(long j) {
            return ComparableTimeMark.DefaultImpls.m592minusLRDsOJo(this, j);
        }

        @Override // kotlin.time.ComparableTimeMark
        /* renamed from: minus-UwyO8pc  reason: not valid java name */
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
            return "DoubleTimeMark(" + this.h + DurationUnitKt__DurationUnitKt.shortName(this.i.getUnit()) + " + " + ((Object) Duration.m639toStringimpl(this.j)) + ", " + this.i + HexStringBuilder.COMMENT_END_CHAR;
        }

        @Override // java.lang.Comparable
        public int compareTo(@NotNull ComparableTimeMark comparableTimeMark) {
            return ComparableTimeMark.DefaultImpls.compareTo(this, comparableTimeMark);
        }

        @Override // kotlin.time.TimeMark
        @NotNull
        /* renamed from: plus-LRDsOJo  reason: not valid java name */
        public ComparableTimeMark mo591plusLRDsOJo(long j) {
            return new a(this.h, this.i, Duration.m626plusLRDsOJo(this.j, j), null);
        }
    }

    public AbstractDoubleTimeSource(@NotNull DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        this.f14128a = unit;
    }

    @NotNull
    public final DurationUnit getUnit() {
        return this.f14128a;
    }

    public abstract double read();

    @Override // kotlin.time.TimeSource
    @NotNull
    public ComparableTimeMark markNow() {
        return new a(read(), this, Duration.Companion.m671getZEROUwyO8pc(), null);
    }
}

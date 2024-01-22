package kotlin.time;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.SinceKotlin;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.ComparableTimeMark;
import org.jetbrains.annotations.NotNull;
@SinceKotlin(version = "1.3")
@ExperimentalTime
/* loaded from: classes12.dex */
public interface TimeSource {
    @NotNull
    public static final Companion Companion = Companion.f14132a;

    /* loaded from: classes12.dex */
    public static final class Companion {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ Companion f14132a = new Companion();
    }

    /* loaded from: classes12.dex */
    public static final class Monotonic implements WithComparableMarks {
        @NotNull
        public static final Monotonic INSTANCE = new Monotonic();

        @SinceKotlin(version = "1.7")
        @JvmInline
        @ExperimentalTime
        /* loaded from: classes12.dex */
        public static final class ValueTimeMark implements ComparableTimeMark {
            public final long h;

            public /* synthetic */ ValueTimeMark(long j) {
                this.h = j;
            }

            /* renamed from: box-impl  reason: not valid java name */
            public static final /* synthetic */ ValueTimeMark m703boximpl(long j) {
                return new ValueTimeMark(j);
            }

            /* renamed from: compareTo-6eNON_k  reason: not valid java name */
            public static final int m704compareTo6eNON_k(long j, long j2) {
                return Duration.m594compareToLRDsOJo(m713minus6eNON_k(j, j2), Duration.Companion.m671getZEROUwyO8pc());
            }

            /* renamed from: compareTo-impl  reason: not valid java name */
            public static int m705compareToimpl(long j, @NotNull ComparableTimeMark other) {
                Intrinsics.checkNotNullParameter(other, "other");
                return m703boximpl(j).compareTo(other);
            }

            /* renamed from: constructor-impl  reason: not valid java name */
            public static long m706constructorimpl(long j) {
                return j;
            }

            /* renamed from: equals-impl  reason: not valid java name */
            public static boolean m708equalsimpl(long j, Object obj) {
                return (obj instanceof ValueTimeMark) && j == ((ValueTimeMark) obj).m720unboximpl();
            }

            /* renamed from: equals-impl0  reason: not valid java name */
            public static final boolean m709equalsimpl0(long j, long j2) {
                return j == j2;
            }

            /* renamed from: hasNotPassedNow-impl  reason: not valid java name */
            public static boolean m710hasNotPassedNowimpl(long j) {
                return Duration.m623isNegativeimpl(m707elapsedNowUwyO8pc(j));
            }

            /* renamed from: hasPassedNow-impl  reason: not valid java name */
            public static boolean m711hasPassedNowimpl(long j) {
                return !Duration.m623isNegativeimpl(m707elapsedNowUwyO8pc(j));
            }

            /* renamed from: hashCode-impl  reason: not valid java name */
            public static int m712hashCodeimpl(long j) {
                return Long.hashCode(j);
            }

            /* renamed from: minus-6eNON_k  reason: not valid java name */
            public static final long m713minus6eNON_k(long j, long j2) {
                return MonotonicTimeSource.INSTANCE.m696differenceBetweenfRLX17w(j, j2);
            }

            /* renamed from: toString-impl  reason: not valid java name */
            public static String m717toStringimpl(long j) {
                return "ValueTimeMark(reading=" + j + HexStringBuilder.COMMENT_END_CHAR;
            }

            @Override // kotlin.time.TimeMark
            /* renamed from: elapsedNow-UwyO8pc */
            public long mo588elapsedNowUwyO8pc() {
                return m707elapsedNowUwyO8pc(this.h);
            }

            @Override // kotlin.time.ComparableTimeMark
            public boolean equals(Object obj) {
                return m708equalsimpl(this.h, obj);
            }

            @Override // kotlin.time.TimeMark
            public boolean hasNotPassedNow() {
                return m710hasNotPassedNowimpl(this.h);
            }

            @Override // kotlin.time.TimeMark
            public boolean hasPassedNow() {
                return m711hasPassedNowimpl(this.h);
            }

            @Override // kotlin.time.ComparableTimeMark
            public int hashCode() {
                return m712hashCodeimpl(this.h);
            }

            @Override // kotlin.time.ComparableTimeMark, kotlin.time.TimeMark
            /* renamed from: minus-LRDsOJo */
            public /* bridge */ /* synthetic */ ComparableTimeMark mo589minusLRDsOJo(long j) {
                return m703boximpl(m718minusLRDsOJo(j));
            }

            @Override // kotlin.time.ComparableTimeMark
            /* renamed from: minus-UwyO8pc */
            public long mo590minusUwyO8pc(@NotNull ComparableTimeMark other) {
                Intrinsics.checkNotNullParameter(other, "other");
                return m715minusUwyO8pc(this.h, other);
            }

            @Override // kotlin.time.ComparableTimeMark, kotlin.time.TimeMark
            /* renamed from: plus-LRDsOJo */
            public /* bridge */ /* synthetic */ ComparableTimeMark mo591plusLRDsOJo(long j) {
                return m703boximpl(m719plusLRDsOJo(j));
            }

            public String toString() {
                return m717toStringimpl(this.h);
            }

            /* renamed from: unbox-impl  reason: not valid java name */
            public final /* synthetic */ long m720unboximpl() {
                return this.h;
            }

            /* renamed from: elapsedNow-UwyO8pc  reason: not valid java name */
            public static long m707elapsedNowUwyO8pc(long j) {
                return MonotonicTimeSource.INSTANCE.m697elapsedFrom6eNON_k(j);
            }

            /* renamed from: minus-UwyO8pc  reason: not valid java name */
            public static long m715minusUwyO8pc(long j, @NotNull ComparableTimeMark other) {
                Intrinsics.checkNotNullParameter(other, "other");
                if (other instanceof ValueTimeMark) {
                    return m713minus6eNON_k(j, ((ValueTimeMark) other).m720unboximpl());
                }
                throw new IllegalArgumentException("Subtracting or comparing time marks from different time sources is not possible: " + ((Object) m717toStringimpl(j)) + " and " + other);
            }

            @Override // java.lang.Comparable
            public int compareTo(@NotNull ComparableTimeMark comparableTimeMark) {
                return ComparableTimeMark.DefaultImpls.compareTo(this, comparableTimeMark);
            }

            @Override // kotlin.time.TimeMark
            /* renamed from: minus-LRDsOJo */
            public /* bridge */ /* synthetic */ TimeMark mo589minusLRDsOJo(long j) {
                return m703boximpl(m718minusLRDsOJo(j));
            }

            @Override // kotlin.time.TimeMark
            /* renamed from: plus-LRDsOJo */
            public /* bridge */ /* synthetic */ TimeMark mo591plusLRDsOJo(long j) {
                return m703boximpl(m719plusLRDsOJo(j));
            }

            /* renamed from: minus-LRDsOJo  reason: not valid java name */
            public long m718minusLRDsOJo(long j) {
                return m714minusLRDsOJo(this.h, j);
            }

            /* renamed from: plus-LRDsOJo  reason: not valid java name */
            public long m719plusLRDsOJo(long j) {
                return m716plusLRDsOJo(this.h, j);
            }

            /* renamed from: minus-LRDsOJo  reason: not valid java name */
            public static long m714minusLRDsOJo(long j, long j2) {
                return MonotonicTimeSource.INSTANCE.m695adjustReading6QKq23U(j, Duration.m642unaryMinusUwyO8pc(j2));
            }

            /* renamed from: plus-LRDsOJo  reason: not valid java name */
            public static long m716plusLRDsOJo(long j, long j2) {
                return MonotonicTimeSource.INSTANCE.m695adjustReading6QKq23U(j, j2);
            }
        }

        @Override // kotlin.time.TimeSource.WithComparableMarks, kotlin.time.TimeSource
        public /* bridge */ /* synthetic */ ComparableTimeMark markNow() {
            return ValueTimeMark.m703boximpl(m702markNowz9LOYto());
        }

        /* renamed from: markNow-z9LOYto  reason: not valid java name */
        public long m702markNowz9LOYto() {
            return MonotonicTimeSource.INSTANCE.m698markNowz9LOYto();
        }

        @NotNull
        public String toString() {
            return MonotonicTimeSource.INSTANCE.toString();
        }

        @Override // kotlin.time.TimeSource
        public /* bridge */ /* synthetic */ TimeMark markNow() {
            return ValueTimeMark.m703boximpl(m702markNowz9LOYto());
        }
    }

    @SinceKotlin(version = "1.8")
    @ExperimentalTime
    /* loaded from: classes12.dex */
    public interface WithComparableMarks extends TimeSource {
        @Override // kotlin.time.TimeSource
        @NotNull
        ComparableTimeMark markNow();
    }

    @NotNull
    TimeMark markNow();
}

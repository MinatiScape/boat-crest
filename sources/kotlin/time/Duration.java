package kotlin.time;

import androidx.exifinterface.media.ExifInterface;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.PublishedApi;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.comparisons.g;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.c;
import kotlin.ranges.LongRange;
import kotlin.ranges.h;
import kotlin.text.StringsKt__StringsKt;
import org.apache.commons.codec.language.Soundex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@SinceKotlin(version = "1.6")
@JvmInline
@WasExperimental(markerClass = {ExperimentalTime.class})
/* loaded from: classes12.dex */
public final class Duration implements Comparable<Duration> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final long i = m595constructorimpl(0);
    public static final long j = DurationKt.access$durationOfMillis(DurationKt.MAX_MILLIS);
    public static final long k = DurationKt.access$durationOfMillis(-4611686018427387903L);
    public final long h;

    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @InlineOnly
        /* renamed from: getDays-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m645getDaysUwyO8pc$annotations(double d) {
        }

        @InlineOnly
        /* renamed from: getDays-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m646getDaysUwyO8pc$annotations(int i) {
        }

        @InlineOnly
        /* renamed from: getDays-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m647getDaysUwyO8pc$annotations(long j) {
        }

        @InlineOnly
        /* renamed from: getHours-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m648getHoursUwyO8pc$annotations(double d) {
        }

        @InlineOnly
        /* renamed from: getHours-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m649getHoursUwyO8pc$annotations(int i) {
        }

        @InlineOnly
        /* renamed from: getHours-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m650getHoursUwyO8pc$annotations(long j) {
        }

        @InlineOnly
        /* renamed from: getMicroseconds-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m651getMicrosecondsUwyO8pc$annotations(double d) {
        }

        @InlineOnly
        /* renamed from: getMicroseconds-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m652getMicrosecondsUwyO8pc$annotations(int i) {
        }

        @InlineOnly
        /* renamed from: getMicroseconds-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m653getMicrosecondsUwyO8pc$annotations(long j) {
        }

        @InlineOnly
        /* renamed from: getMilliseconds-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m654getMillisecondsUwyO8pc$annotations(double d) {
        }

        @InlineOnly
        /* renamed from: getMilliseconds-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m655getMillisecondsUwyO8pc$annotations(int i) {
        }

        @InlineOnly
        /* renamed from: getMilliseconds-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m656getMillisecondsUwyO8pc$annotations(long j) {
        }

        @InlineOnly
        /* renamed from: getMinutes-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m657getMinutesUwyO8pc$annotations(double d) {
        }

        @InlineOnly
        /* renamed from: getMinutes-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m658getMinutesUwyO8pc$annotations(int i) {
        }

        @InlineOnly
        /* renamed from: getMinutes-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m659getMinutesUwyO8pc$annotations(long j) {
        }

        @InlineOnly
        /* renamed from: getNanoseconds-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m660getNanosecondsUwyO8pc$annotations(double d) {
        }

        @InlineOnly
        /* renamed from: getNanoseconds-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m661getNanosecondsUwyO8pc$annotations(int i) {
        }

        @InlineOnly
        /* renamed from: getNanoseconds-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m662getNanosecondsUwyO8pc$annotations(long j) {
        }

        @InlineOnly
        /* renamed from: getSeconds-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m663getSecondsUwyO8pc$annotations(double d) {
        }

        @InlineOnly
        /* renamed from: getSeconds-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m664getSecondsUwyO8pc$annotations(int i) {
        }

        @InlineOnly
        /* renamed from: getSeconds-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m665getSecondsUwyO8pc$annotations(long j) {
        }

        @ExperimentalTime
        public final double convert(double d, @NotNull DurationUnit sourceUnit, @NotNull DurationUnit targetUnit) {
            Intrinsics.checkNotNullParameter(sourceUnit, "sourceUnit");
            Intrinsics.checkNotNullParameter(targetUnit, "targetUnit");
            return DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(d, sourceUnit, targetUnit);
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Int.days' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.days", imports = {"kotlin.time.Duration.Companion.days"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: days-UwyO8pc  reason: not valid java name */
        public final long m667daysUwyO8pc(int i) {
            return DurationKt.toDuration(i, DurationUnit.DAYS);
        }

        /* renamed from: getINFINITE-UwyO8pc  reason: not valid java name */
        public final long m669getINFINITEUwyO8pc() {
            return Duration.j;
        }

        /* renamed from: getNEG_INFINITE-UwyO8pc$kotlin_stdlib  reason: not valid java name */
        public final long m670getNEG_INFINITEUwyO8pc$kotlin_stdlib() {
            return Duration.k;
        }

        /* renamed from: getZERO-UwyO8pc  reason: not valid java name */
        public final long m671getZEROUwyO8pc() {
            return Duration.i;
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Int.hours' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.hours", imports = {"kotlin.time.Duration.Companion.hours"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: hours-UwyO8pc  reason: not valid java name */
        public final long m673hoursUwyO8pc(int i) {
            return DurationKt.toDuration(i, DurationUnit.HOURS);
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Int.microseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.microseconds", imports = {"kotlin.time.Duration.Companion.microseconds"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: microseconds-UwyO8pc  reason: not valid java name */
        public final long m676microsecondsUwyO8pc(int i) {
            return DurationKt.toDuration(i, DurationUnit.MICROSECONDS);
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Int.milliseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.milliseconds", imports = {"kotlin.time.Duration.Companion.milliseconds"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: milliseconds-UwyO8pc  reason: not valid java name */
        public final long m679millisecondsUwyO8pc(int i) {
            return DurationKt.toDuration(i, DurationUnit.MILLISECONDS);
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Int.minutes' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.minutes", imports = {"kotlin.time.Duration.Companion.minutes"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: minutes-UwyO8pc  reason: not valid java name */
        public final long m682minutesUwyO8pc(int i) {
            return DurationKt.toDuration(i, DurationUnit.MINUTES);
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Int.nanoseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.nanoseconds", imports = {"kotlin.time.Duration.Companion.nanoseconds"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: nanoseconds-UwyO8pc  reason: not valid java name */
        public final long m685nanosecondsUwyO8pc(int i) {
            return DurationKt.toDuration(i, DurationUnit.NANOSECONDS);
        }

        /* renamed from: parse-UwyO8pc  reason: not valid java name */
        public final long m687parseUwyO8pc(@NotNull String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            try {
                return DurationKt.access$parseDuration(value, false);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid duration string format: '" + value + "'.", e);
            }
        }

        /* renamed from: parseIsoString-UwyO8pc  reason: not valid java name */
        public final long m688parseIsoStringUwyO8pc(@NotNull String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            try {
                return DurationKt.access$parseDuration(value, true);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid ISO duration string format: '" + value + "'.", e);
            }
        }

        @Nullable
        /* renamed from: parseIsoStringOrNull-FghU774  reason: not valid java name */
        public final Duration m689parseIsoStringOrNullFghU774(@NotNull String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            try {
                return Duration.m593boximpl(DurationKt.access$parseDuration(value, true));
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }

        @Nullable
        /* renamed from: parseOrNull-FghU774  reason: not valid java name */
        public final Duration m690parseOrNullFghU774(@NotNull String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            try {
                return Duration.m593boximpl(DurationKt.access$parseDuration(value, false));
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Int.seconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.seconds", imports = {"kotlin.time.Duration.Companion.seconds"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: seconds-UwyO8pc  reason: not valid java name */
        public final long m692secondsUwyO8pc(int i) {
            return DurationKt.toDuration(i, DurationUnit.SECONDS);
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Long.days' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.days", imports = {"kotlin.time.Duration.Companion.days"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: days-UwyO8pc  reason: not valid java name */
        public final long m668daysUwyO8pc(long j) {
            return DurationKt.toDuration(j, DurationUnit.DAYS);
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Long.hours' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.hours", imports = {"kotlin.time.Duration.Companion.hours"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: hours-UwyO8pc  reason: not valid java name */
        public final long m674hoursUwyO8pc(long j) {
            return DurationKt.toDuration(j, DurationUnit.HOURS);
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Long.microseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.microseconds", imports = {"kotlin.time.Duration.Companion.microseconds"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: microseconds-UwyO8pc  reason: not valid java name */
        public final long m677microsecondsUwyO8pc(long j) {
            return DurationKt.toDuration(j, DurationUnit.MICROSECONDS);
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Long.milliseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.milliseconds", imports = {"kotlin.time.Duration.Companion.milliseconds"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: milliseconds-UwyO8pc  reason: not valid java name */
        public final long m680millisecondsUwyO8pc(long j) {
            return DurationKt.toDuration(j, DurationUnit.MILLISECONDS);
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Long.minutes' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.minutes", imports = {"kotlin.time.Duration.Companion.minutes"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: minutes-UwyO8pc  reason: not valid java name */
        public final long m683minutesUwyO8pc(long j) {
            return DurationKt.toDuration(j, DurationUnit.MINUTES);
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Long.nanoseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.nanoseconds", imports = {"kotlin.time.Duration.Companion.nanoseconds"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: nanoseconds-UwyO8pc  reason: not valid java name */
        public final long m686nanosecondsUwyO8pc(long j) {
            return DurationKt.toDuration(j, DurationUnit.NANOSECONDS);
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Long.seconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.seconds", imports = {"kotlin.time.Duration.Companion.seconds"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: seconds-UwyO8pc  reason: not valid java name */
        public final long m693secondsUwyO8pc(long j) {
            return DurationKt.toDuration(j, DurationUnit.SECONDS);
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Double.days' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.days", imports = {"kotlin.time.Duration.Companion.days"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: days-UwyO8pc  reason: not valid java name */
        public final long m666daysUwyO8pc(double d) {
            return DurationKt.toDuration(d, DurationUnit.DAYS);
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Double.hours' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.hours", imports = {"kotlin.time.Duration.Companion.hours"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: hours-UwyO8pc  reason: not valid java name */
        public final long m672hoursUwyO8pc(double d) {
            return DurationKt.toDuration(d, DurationUnit.HOURS);
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Double.microseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.microseconds", imports = {"kotlin.time.Duration.Companion.microseconds"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: microseconds-UwyO8pc  reason: not valid java name */
        public final long m675microsecondsUwyO8pc(double d) {
            return DurationKt.toDuration(d, DurationUnit.MICROSECONDS);
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Double.milliseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.milliseconds", imports = {"kotlin.time.Duration.Companion.milliseconds"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: milliseconds-UwyO8pc  reason: not valid java name */
        public final long m678millisecondsUwyO8pc(double d) {
            return DurationKt.toDuration(d, DurationUnit.MILLISECONDS);
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Double.minutes' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.minutes", imports = {"kotlin.time.Duration.Companion.minutes"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: minutes-UwyO8pc  reason: not valid java name */
        public final long m681minutesUwyO8pc(double d) {
            return DurationKt.toDuration(d, DurationUnit.MINUTES);
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Double.nanoseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.nanoseconds", imports = {"kotlin.time.Duration.Companion.nanoseconds"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: nanoseconds-UwyO8pc  reason: not valid java name */
        public final long m684nanosecondsUwyO8pc(double d) {
            return DurationKt.toDuration(d, DurationUnit.NANOSECONDS);
        }

        @SinceKotlin(version = "1.5")
        @Deprecated(message = "Use 'Double.seconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.seconds", imports = {"kotlin.time.Duration.Companion.seconds"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        @ExperimentalTime
        /* renamed from: seconds-UwyO8pc  reason: not valid java name */
        public final long m691secondsUwyO8pc(double d) {
            return DurationKt.toDuration(d, DurationUnit.SECONDS);
        }
    }

    public /* synthetic */ Duration(long j2) {
        this.h = j2;
    }

    public static final long a(long j2, long j3, long j4) {
        long access$nanosToMillis = DurationKt.access$nanosToMillis(j4);
        long j5 = j3 + access$nanosToMillis;
        if (new LongRange(-4611686018426L, 4611686018426L).contains(j5)) {
            return DurationKt.access$durationOfNanos(DurationKt.access$millisToNanos(j5) + (j4 - DurationKt.access$millisToNanos(access$nanosToMillis)));
        }
        return DurationKt.access$durationOfMillis(h.coerceIn(j5, -4611686018427387903L, (long) DurationKt.MAX_MILLIS));
    }

    public static final void b(long j2, StringBuilder sb, int i2, int i3, int i4, String str, boolean z) {
        sb.append(i2);
        if (i3 != 0) {
            sb.append('.');
            String padStart = StringsKt__StringsKt.padStart(String.valueOf(i3), i4, '0');
            int i5 = -1;
            int length = padStart.length() - 1;
            if (length >= 0) {
                while (true) {
                    int i6 = length - 1;
                    if (padStart.charAt(length) != '0') {
                        i5 = length;
                        break;
                    } else if (i6 < 0) {
                        break;
                    } else {
                        length = i6;
                    }
                }
            }
            int i7 = i5 + 1;
            if (!z && i7 < 3) {
                sb.append((CharSequence) padStart, 0, i7);
                Intrinsics.checkNotNullExpressionValue(sb, "this.append(value, startIndex, endIndex)");
            } else {
                sb.append((CharSequence) padStart, 0, ((i7 + 2) / 3) * 3);
                Intrinsics.checkNotNullExpressionValue(sb, "this.append(value, startIndex, endIndex)");
            }
        }
        sb.append(str);
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ Duration m593boximpl(long j2) {
        return new Duration(j2);
    }

    public static final DurationUnit c(long j2) {
        return f(j2) ? DurationUnit.NANOSECONDS : DurationUnit.MILLISECONDS;
    }

    /* renamed from: constructor-impl  reason: not valid java name */
    public static long m595constructorimpl(long j2) {
        if (DurationJvmKt.getDurationAssertionsEnabled()) {
            if (f(j2)) {
                if (!new LongRange(-4611686018426999999L, DurationKt.MAX_NANOS).contains(d(j2))) {
                    throw new AssertionError(d(j2) + " ns is out of nanoseconds range");
                }
            } else if (new LongRange(-4611686018427387903L, DurationKt.MAX_MILLIS).contains(d(j2))) {
                if (new LongRange(-4611686018426L, 4611686018426L).contains(d(j2))) {
                    throw new AssertionError(d(j2) + " ms is denormalized");
                }
            } else {
                throw new AssertionError(d(j2) + " ms is out of milliseconds range");
            }
        }
        return j2;
    }

    public static final long d(long j2) {
        return j2 >> 1;
    }

    /* renamed from: div-LRDsOJo  reason: not valid java name */
    public static final double m596divLRDsOJo(long j2, long j3) {
        DurationUnit durationUnit = (DurationUnit) g.maxOf(c(j2), c(j3));
        return m633toDoubleimpl(j2, durationUnit) / m633toDoubleimpl(j3, durationUnit);
    }

    /* renamed from: div-UwyO8pc  reason: not valid java name */
    public static final long m598divUwyO8pc(long j2, int i2) {
        if (i2 == 0) {
            if (m624isPositiveimpl(j2)) {
                return j;
            }
            if (m623isNegativeimpl(j2)) {
                return k;
            }
            throw new IllegalArgumentException("Dividing zero duration by zero yields an undefined result.");
        } else if (f(j2)) {
            return DurationKt.access$durationOfNanos(d(j2) / i2);
        } else {
            if (m622isInfiniteimpl(j2)) {
                return m628timesUwyO8pc(j2, c.getSign(i2));
            }
            long j3 = i2;
            long d = d(j2) / j3;
            if (new LongRange(-4611686018426L, 4611686018426L).contains(d)) {
                return DurationKt.access$durationOfNanos(DurationKt.access$millisToNanos(d) + (DurationKt.access$millisToNanos(d(j2) - (d * j3)) / j3));
            }
            return DurationKt.access$durationOfMillis(d);
        }
    }

    public static final boolean e(long j2) {
        return (((int) j2) & 1) == 1;
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m599equalsimpl(long j2, Object obj) {
        return (obj instanceof Duration) && j2 == ((Duration) obj).m644unboximpl();
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m600equalsimpl0(long j2, long j3) {
        return j2 == j3;
    }

    public static final boolean f(long j2) {
        return (((int) j2) & 1) == 0;
    }

    /* renamed from: getAbsoluteValue-UwyO8pc  reason: not valid java name */
    public static final long m601getAbsoluteValueUwyO8pc(long j2) {
        return m623isNegativeimpl(j2) ? m642unaryMinusUwyO8pc(j2) : j2;
    }

    @PublishedApi
    public static /* synthetic */ void getHoursComponent$annotations() {
    }

    /* renamed from: getHoursComponent-impl  reason: not valid java name */
    public static final int m602getHoursComponentimpl(long j2) {
        if (m622isInfiniteimpl(j2)) {
            return 0;
        }
        return (int) (m611getInWholeHoursimpl(j2) % 24);
    }

    @Deprecated(message = "Use inWholeDays property instead or convert toDouble(DAYS) if a double value is required.", replaceWith = @ReplaceWith(expression = "toDouble(DurationUnit.DAYS)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.5")
    @ExperimentalTime
    public static /* synthetic */ void getInDays$annotations() {
    }

    /* renamed from: getInDays-impl  reason: not valid java name */
    public static final double m603getInDaysimpl(long j2) {
        return m633toDoubleimpl(j2, DurationUnit.DAYS);
    }

    @Deprecated(message = "Use inWholeHours property instead or convert toDouble(HOURS) if a double value is required.", replaceWith = @ReplaceWith(expression = "toDouble(DurationUnit.HOURS)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.5")
    @ExperimentalTime
    public static /* synthetic */ void getInHours$annotations() {
    }

    /* renamed from: getInHours-impl  reason: not valid java name */
    public static final double m604getInHoursimpl(long j2) {
        return m633toDoubleimpl(j2, DurationUnit.HOURS);
    }

    @Deprecated(message = "Use inWholeMicroseconds property instead or convert toDouble(MICROSECONDS) if a double value is required.", replaceWith = @ReplaceWith(expression = "toDouble(DurationUnit.MICROSECONDS)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.5")
    @ExperimentalTime
    public static /* synthetic */ void getInMicroseconds$annotations() {
    }

    /* renamed from: getInMicroseconds-impl  reason: not valid java name */
    public static final double m605getInMicrosecondsimpl(long j2) {
        return m633toDoubleimpl(j2, DurationUnit.MICROSECONDS);
    }

    @Deprecated(message = "Use inWholeMilliseconds property instead or convert toDouble(MILLISECONDS) if a double value is required.", replaceWith = @ReplaceWith(expression = "toDouble(DurationUnit.MILLISECONDS)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.5")
    @ExperimentalTime
    public static /* synthetic */ void getInMilliseconds$annotations() {
    }

    /* renamed from: getInMilliseconds-impl  reason: not valid java name */
    public static final double m606getInMillisecondsimpl(long j2) {
        return m633toDoubleimpl(j2, DurationUnit.MILLISECONDS);
    }

    @Deprecated(message = "Use inWholeMinutes property instead or convert toDouble(MINUTES) if a double value is required.", replaceWith = @ReplaceWith(expression = "toDouble(DurationUnit.MINUTES)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.5")
    @ExperimentalTime
    public static /* synthetic */ void getInMinutes$annotations() {
    }

    /* renamed from: getInMinutes-impl  reason: not valid java name */
    public static final double m607getInMinutesimpl(long j2) {
        return m633toDoubleimpl(j2, DurationUnit.MINUTES);
    }

    @Deprecated(message = "Use inWholeNanoseconds property instead or convert toDouble(NANOSECONDS) if a double value is required.", replaceWith = @ReplaceWith(expression = "toDouble(DurationUnit.NANOSECONDS)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.5")
    @ExperimentalTime
    public static /* synthetic */ void getInNanoseconds$annotations() {
    }

    /* renamed from: getInNanoseconds-impl  reason: not valid java name */
    public static final double m608getInNanosecondsimpl(long j2) {
        return m633toDoubleimpl(j2, DurationUnit.NANOSECONDS);
    }

    @Deprecated(message = "Use inWholeSeconds property instead or convert toDouble(SECONDS) if a double value is required.", replaceWith = @ReplaceWith(expression = "toDouble(DurationUnit.SECONDS)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.5")
    @ExperimentalTime
    public static /* synthetic */ void getInSeconds$annotations() {
    }

    /* renamed from: getInSeconds-impl  reason: not valid java name */
    public static final double m609getInSecondsimpl(long j2) {
        return m633toDoubleimpl(j2, DurationUnit.SECONDS);
    }

    /* renamed from: getInWholeDays-impl  reason: not valid java name */
    public static final long m610getInWholeDaysimpl(long j2) {
        return m636toLongimpl(j2, DurationUnit.DAYS);
    }

    /* renamed from: getInWholeHours-impl  reason: not valid java name */
    public static final long m611getInWholeHoursimpl(long j2) {
        return m636toLongimpl(j2, DurationUnit.HOURS);
    }

    /* renamed from: getInWholeMicroseconds-impl  reason: not valid java name */
    public static final long m612getInWholeMicrosecondsimpl(long j2) {
        return m636toLongimpl(j2, DurationUnit.MICROSECONDS);
    }

    /* renamed from: getInWholeMilliseconds-impl  reason: not valid java name */
    public static final long m613getInWholeMillisecondsimpl(long j2) {
        return (e(j2) && m621isFiniteimpl(j2)) ? d(j2) : m636toLongimpl(j2, DurationUnit.MILLISECONDS);
    }

    /* renamed from: getInWholeMinutes-impl  reason: not valid java name */
    public static final long m614getInWholeMinutesimpl(long j2) {
        return m636toLongimpl(j2, DurationUnit.MINUTES);
    }

    /* renamed from: getInWholeNanoseconds-impl  reason: not valid java name */
    public static final long m615getInWholeNanosecondsimpl(long j2) {
        long d = d(j2);
        if (f(j2)) {
            return d;
        }
        if (d > 9223372036854L) {
            return Long.MAX_VALUE;
        }
        if (d < -9223372036854L) {
            return Long.MIN_VALUE;
        }
        return DurationKt.access$millisToNanos(d);
    }

    /* renamed from: getInWholeSeconds-impl  reason: not valid java name */
    public static final long m616getInWholeSecondsimpl(long j2) {
        return m636toLongimpl(j2, DurationUnit.SECONDS);
    }

    @PublishedApi
    public static /* synthetic */ void getMinutesComponent$annotations() {
    }

    /* renamed from: getMinutesComponent-impl  reason: not valid java name */
    public static final int m617getMinutesComponentimpl(long j2) {
        if (m622isInfiniteimpl(j2)) {
            return 0;
        }
        return (int) (m614getInWholeMinutesimpl(j2) % 60);
    }

    @PublishedApi
    public static /* synthetic */ void getNanosecondsComponent$annotations() {
    }

    /* renamed from: getNanosecondsComponent-impl  reason: not valid java name */
    public static final int m618getNanosecondsComponentimpl(long j2) {
        if (m622isInfiniteimpl(j2)) {
            return 0;
        }
        return (int) (e(j2) ? DurationKt.access$millisToNanos(d(j2) % 1000) : d(j2) % 1000000000);
    }

    @PublishedApi
    public static /* synthetic */ void getSecondsComponent$annotations() {
    }

    /* renamed from: getSecondsComponent-impl  reason: not valid java name */
    public static final int m619getSecondsComponentimpl(long j2) {
        if (m622isInfiniteimpl(j2)) {
            return 0;
        }
        return (int) (m616getInWholeSecondsimpl(j2) % 60);
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m620hashCodeimpl(long j2) {
        return Long.hashCode(j2);
    }

    /* renamed from: isFinite-impl  reason: not valid java name */
    public static final boolean m621isFiniteimpl(long j2) {
        return !m622isInfiniteimpl(j2);
    }

    /* renamed from: isInfinite-impl  reason: not valid java name */
    public static final boolean m622isInfiniteimpl(long j2) {
        return j2 == j || j2 == k;
    }

    /* renamed from: isNegative-impl  reason: not valid java name */
    public static final boolean m623isNegativeimpl(long j2) {
        return j2 < 0;
    }

    /* renamed from: isPositive-impl  reason: not valid java name */
    public static final boolean m624isPositiveimpl(long j2) {
        return j2 > 0;
    }

    /* renamed from: minus-LRDsOJo  reason: not valid java name */
    public static final long m625minusLRDsOJo(long j2, long j3) {
        return m626plusLRDsOJo(j2, m642unaryMinusUwyO8pc(j3));
    }

    /* renamed from: plus-LRDsOJo  reason: not valid java name */
    public static final long m626plusLRDsOJo(long j2, long j3) {
        if (m622isInfiniteimpl(j2)) {
            if (m621isFiniteimpl(j3) || (j3 ^ j2) >= 0) {
                return j2;
            }
            throw new IllegalArgumentException("Summing infinite durations of different signs yields an undefined result.");
        } else if (m622isInfiniteimpl(j3)) {
            return j3;
        } else {
            if ((((int) j2) & 1) == (((int) j3) & 1)) {
                long d = d(j2) + d(j3);
                if (f(j2)) {
                    return DurationKt.access$durationOfNanosNormalized(d);
                }
                return DurationKt.access$durationOfMillisNormalized(d);
            } else if (e(j2)) {
                return a(j2, d(j2), d(j3));
            } else {
                return a(j2, d(j3), d(j2));
            }
        }
    }

    /* renamed from: times-UwyO8pc  reason: not valid java name */
    public static final long m628timesUwyO8pc(long j2, int i2) {
        if (m622isInfiniteimpl(j2)) {
            if (i2 != 0) {
                return i2 > 0 ? j2 : m642unaryMinusUwyO8pc(j2);
            }
            throw new IllegalArgumentException("Multiplying infinite duration by zero yields an undefined result.");
        } else if (i2 == 0) {
            return i;
        } else {
            long d = d(j2);
            long j3 = i2;
            long j4 = d * j3;
            if (f(j2)) {
                if (new LongRange(-2147483647L, 2147483647L).contains(d)) {
                    return DurationKt.access$durationOfNanos(j4);
                }
                if (j4 / j3 == d) {
                    return DurationKt.access$durationOfNanosNormalized(j4);
                }
                long access$nanosToMillis = DurationKt.access$nanosToMillis(d);
                long j5 = access$nanosToMillis * j3;
                long access$nanosToMillis2 = DurationKt.access$nanosToMillis((d - DurationKt.access$millisToNanos(access$nanosToMillis)) * j3) + j5;
                if (j5 / j3 != access$nanosToMillis || (access$nanosToMillis2 ^ j5) < 0) {
                    return c.getSign(d) * c.getSign(i2) > 0 ? j : k;
                }
                return DurationKt.access$durationOfMillis(h.coerceIn(access$nanosToMillis2, new LongRange(-4611686018427387903L, DurationKt.MAX_MILLIS)));
            } else if (j4 / j3 == d) {
                return DurationKt.access$durationOfMillis(h.coerceIn(j4, new LongRange(-4611686018427387903L, DurationKt.MAX_MILLIS)));
            } else {
                return c.getSign(d) * c.getSign(i2) > 0 ? j : k;
            }
        }
    }

    /* renamed from: toComponents-impl  reason: not valid java name */
    public static final <T> T m632toComponentsimpl(long j2, @NotNull Function5<? super Long, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? extends T> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        return action.invoke(Long.valueOf(m610getInWholeDaysimpl(j2)), Integer.valueOf(m602getHoursComponentimpl(j2)), Integer.valueOf(m617getMinutesComponentimpl(j2)), Integer.valueOf(m619getSecondsComponentimpl(j2)), Integer.valueOf(m618getNanosecondsComponentimpl(j2)));
    }

    /* renamed from: toDouble-impl  reason: not valid java name */
    public static final double m633toDoubleimpl(long j2, @NotNull DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        if (j2 == j) {
            return Double.POSITIVE_INFINITY;
        }
        if (j2 == k) {
            return Double.NEGATIVE_INFINITY;
        }
        return DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(d(j2), c(j2), unit);
    }

    /* renamed from: toInt-impl  reason: not valid java name */
    public static final int m634toIntimpl(long j2, @NotNull DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        return (int) h.coerceIn(m636toLongimpl(j2, unit), -2147483648L, 2147483647L);
    }

    @NotNull
    /* renamed from: toIsoString-impl  reason: not valid java name */
    public static final String m635toIsoStringimpl(long j2) {
        StringBuilder sb = new StringBuilder();
        if (m623isNegativeimpl(j2)) {
            sb.append(Soundex.SILENT_MARKER);
        }
        sb.append("PT");
        long m601getAbsoluteValueUwyO8pc = m601getAbsoluteValueUwyO8pc(j2);
        long m611getInWholeHoursimpl = m611getInWholeHoursimpl(m601getAbsoluteValueUwyO8pc);
        int m617getMinutesComponentimpl = m617getMinutesComponentimpl(m601getAbsoluteValueUwyO8pc);
        int m619getSecondsComponentimpl = m619getSecondsComponentimpl(m601getAbsoluteValueUwyO8pc);
        int m618getNanosecondsComponentimpl = m618getNanosecondsComponentimpl(m601getAbsoluteValueUwyO8pc);
        if (m622isInfiniteimpl(j2)) {
            m611getInWholeHoursimpl = 9999999999999L;
        }
        boolean z = true;
        boolean z2 = m611getInWholeHoursimpl != 0;
        boolean z3 = (m619getSecondsComponentimpl == 0 && m618getNanosecondsComponentimpl == 0) ? false : true;
        if (m617getMinutesComponentimpl == 0 && (!z3 || !z2)) {
            z = false;
        }
        if (z2) {
            sb.append(m611getInWholeHoursimpl);
            sb.append('H');
        }
        if (z) {
            sb.append(m617getMinutesComponentimpl);
            sb.append('M');
        }
        if (z3 || (!z2 && !z)) {
            b(j2, sb, m619getSecondsComponentimpl, m618getNanosecondsComponentimpl, 9, ExifInterface.LATITUDE_SOUTH, true);
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    /* renamed from: toLong-impl  reason: not valid java name */
    public static final long m636toLongimpl(long j2, @NotNull DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        if (j2 == j) {
            return Long.MAX_VALUE;
        }
        if (j2 == k) {
            return Long.MIN_VALUE;
        }
        return DurationUnitKt__DurationUnitJvmKt.convertDurationUnit(d(j2), c(j2), unit);
    }

    @Deprecated(message = "Use inWholeMilliseconds property instead.", replaceWith = @ReplaceWith(expression = "this.inWholeMilliseconds", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.5")
    @ExperimentalTime
    /* renamed from: toLongMilliseconds-impl  reason: not valid java name */
    public static final long m637toLongMillisecondsimpl(long j2) {
        return m613getInWholeMillisecondsimpl(j2);
    }

    @Deprecated(message = "Use inWholeNanoseconds property instead.", replaceWith = @ReplaceWith(expression = "this.inWholeNanoseconds", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.5")
    @ExperimentalTime
    /* renamed from: toLongNanoseconds-impl  reason: not valid java name */
    public static final long m638toLongNanosecondsimpl(long j2) {
        return m615getInWholeNanosecondsimpl(j2);
    }

    @NotNull
    /* renamed from: toString-impl  reason: not valid java name */
    public static String m639toStringimpl(long j2) {
        if (j2 == 0) {
            return "0s";
        }
        if (j2 == j) {
            return "Infinity";
        }
        if (j2 == k) {
            return "-Infinity";
        }
        boolean m623isNegativeimpl = m623isNegativeimpl(j2);
        StringBuilder sb = new StringBuilder();
        if (m623isNegativeimpl) {
            sb.append(Soundex.SILENT_MARKER);
        }
        long m601getAbsoluteValueUwyO8pc = m601getAbsoluteValueUwyO8pc(j2);
        long m610getInWholeDaysimpl = m610getInWholeDaysimpl(m601getAbsoluteValueUwyO8pc);
        int m602getHoursComponentimpl = m602getHoursComponentimpl(m601getAbsoluteValueUwyO8pc);
        int m617getMinutesComponentimpl = m617getMinutesComponentimpl(m601getAbsoluteValueUwyO8pc);
        int m619getSecondsComponentimpl = m619getSecondsComponentimpl(m601getAbsoluteValueUwyO8pc);
        int m618getNanosecondsComponentimpl = m618getNanosecondsComponentimpl(m601getAbsoluteValueUwyO8pc);
        int i2 = 0;
        boolean z = m610getInWholeDaysimpl != 0;
        boolean z2 = m602getHoursComponentimpl != 0;
        boolean z3 = m617getMinutesComponentimpl != 0;
        boolean z4 = (m619getSecondsComponentimpl == 0 && m618getNanosecondsComponentimpl == 0) ? false : true;
        if (z) {
            sb.append(m610getInWholeDaysimpl);
            sb.append('d');
            i2 = 1;
        }
        if (z2 || (z && (z3 || z4))) {
            int i3 = i2 + 1;
            if (i2 > 0) {
                sb.append(' ');
            }
            sb.append(m602getHoursComponentimpl);
            sb.append('h');
            i2 = i3;
        }
        if (z3 || (z4 && (z2 || z))) {
            int i4 = i2 + 1;
            if (i2 > 0) {
                sb.append(' ');
            }
            sb.append(m617getMinutesComponentimpl);
            sb.append('m');
            i2 = i4;
        }
        if (z4) {
            int i5 = i2 + 1;
            if (i2 > 0) {
                sb.append(' ');
            }
            if (m619getSecondsComponentimpl != 0 || z || z2 || z3) {
                b(j2, sb, m619getSecondsComponentimpl, m618getNanosecondsComponentimpl, 9, "s", false);
            } else if (m618getNanosecondsComponentimpl >= 1000000) {
                b(j2, sb, m618getNanosecondsComponentimpl / DurationKt.NANOS_IN_MILLIS, m618getNanosecondsComponentimpl % DurationKt.NANOS_IN_MILLIS, 6, "ms", false);
            } else if (m618getNanosecondsComponentimpl >= 1000) {
                b(j2, sb, m618getNanosecondsComponentimpl / 1000, m618getNanosecondsComponentimpl % 1000, 3, "us", false);
            } else {
                sb.append(m618getNanosecondsComponentimpl);
                sb.append("ns");
            }
            i2 = i5;
        }
        if (m623isNegativeimpl && i2 > 1) {
            sb.insert(1, HexStringBuilder.COMMENT_BEGIN_CHAR).append(HexStringBuilder.COMMENT_END_CHAR);
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    /* renamed from: toString-impl$default  reason: not valid java name */
    public static /* synthetic */ String m641toStringimpl$default(long j2, DurationUnit durationUnit, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        return m640toStringimpl(j2, durationUnit, i2);
    }

    /* renamed from: unaryMinus-UwyO8pc  reason: not valid java name */
    public static final long m642unaryMinusUwyO8pc(long j2) {
        return DurationKt.access$durationOf(-d(j2), ((int) j2) & 1);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Duration duration) {
        return m643compareToLRDsOJo(duration.m644unboximpl());
    }

    /* renamed from: compareTo-LRDsOJo  reason: not valid java name */
    public int m643compareToLRDsOJo(long j2) {
        return m594compareToLRDsOJo(this.h, j2);
    }

    public boolean equals(Object obj) {
        return m599equalsimpl(this.h, obj);
    }

    public int hashCode() {
        return m620hashCodeimpl(this.h);
    }

    @NotNull
    public String toString() {
        return m639toStringimpl(this.h);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ long m644unboximpl() {
        return this.h;
    }

    /* renamed from: compareTo-LRDsOJo  reason: not valid java name */
    public static int m594compareToLRDsOJo(long j2, long j3) {
        long j4 = j2 ^ j3;
        if (j4 < 0 || (((int) j4) & 1) == 0) {
            return Intrinsics.compare(j2, j3);
        }
        int i2 = (((int) j2) & 1) - (((int) j3) & 1);
        return m623isNegativeimpl(j2) ? -i2 : i2;
    }

    /* renamed from: toComponents-impl  reason: not valid java name */
    public static final <T> T m631toComponentsimpl(long j2, @NotNull Function4<? super Long, ? super Integer, ? super Integer, ? super Integer, ? extends T> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        return action.invoke(Long.valueOf(m611getInWholeHoursimpl(j2)), Integer.valueOf(m617getMinutesComponentimpl(j2)), Integer.valueOf(m619getSecondsComponentimpl(j2)), Integer.valueOf(m618getNanosecondsComponentimpl(j2)));
    }

    /* renamed from: toComponents-impl  reason: not valid java name */
    public static final <T> T m630toComponentsimpl(long j2, @NotNull Function3<? super Long, ? super Integer, ? super Integer, ? extends T> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        return action.invoke(Long.valueOf(m614getInWholeMinutesimpl(j2)), Integer.valueOf(m619getSecondsComponentimpl(j2)), Integer.valueOf(m618getNanosecondsComponentimpl(j2)));
    }

    /* renamed from: toComponents-impl  reason: not valid java name */
    public static final <T> T m629toComponentsimpl(long j2, @NotNull Function2<? super Long, ? super Integer, ? extends T> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        return action.invoke(Long.valueOf(m616getInWholeSecondsimpl(j2)), Integer.valueOf(m618getNanosecondsComponentimpl(j2)));
    }

    /* renamed from: div-UwyO8pc  reason: not valid java name */
    public static final long m597divUwyO8pc(long j2, double d) {
        int roundToInt = c.roundToInt(d);
        if ((((double) roundToInt) == d) && roundToInt != 0) {
            return m598divUwyO8pc(j2, roundToInt);
        }
        DurationUnit c = c(j2);
        return DurationKt.toDuration(m633toDoubleimpl(j2, c) / d, c);
    }

    /* renamed from: times-UwyO8pc  reason: not valid java name */
    public static final long m627timesUwyO8pc(long j2, double d) {
        int roundToInt = c.roundToInt(d);
        if (((double) roundToInt) == d) {
            return m628timesUwyO8pc(j2, roundToInt);
        }
        DurationUnit c = c(j2);
        return DurationKt.toDuration(m633toDoubleimpl(j2, c) * d, c);
    }

    @NotNull
    /* renamed from: toString-impl  reason: not valid java name */
    public static final String m640toStringimpl(long j2, @NotNull DurationUnit unit, int i2) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        if (i2 >= 0) {
            double m633toDoubleimpl = m633toDoubleimpl(j2, unit);
            if (Double.isInfinite(m633toDoubleimpl)) {
                return String.valueOf(m633toDoubleimpl);
            }
            return DurationJvmKt.formatToExactDecimals(m633toDoubleimpl, h.coerceAtMost(i2, 12)) + DurationUnitKt__DurationUnitKt.shortName(unit);
        }
        throw new IllegalArgumentException(("decimals must be not negative, but was " + i2).toString());
    }
}

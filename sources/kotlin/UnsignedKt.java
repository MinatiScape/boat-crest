package kotlin;

import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@JvmName(name = "UnsignedKt")
/* loaded from: classes12.dex */
public final class UnsignedKt {
    @PublishedApi
    public static final int doubleToUInt(double d) {
        if (!Double.isNaN(d) && d > uintToDouble(0)) {
            if (d >= uintToDouble(-1)) {
                return -1;
            }
            if (d <= 2.147483647E9d) {
                return UInt.m158constructorimpl((int) d);
            }
            return UInt.m158constructorimpl(UInt.m158constructorimpl((int) (d - Integer.MAX_VALUE)) + UInt.m158constructorimpl(Integer.MAX_VALUE));
        }
        return 0;
    }

    @PublishedApi
    public static final long doubleToULong(double d) {
        if (!Double.isNaN(d) && d > ulongToDouble(0L)) {
            if (d >= ulongToDouble(-1L)) {
                return -1L;
            }
            if (d < 9.223372036854776E18d) {
                return ULong.m182constructorimpl((long) d);
            }
            return ULong.m182constructorimpl(ULong.m182constructorimpl((long) (d - 9.223372036854776E18d)) - Long.MIN_VALUE);
        }
        return 0L;
    }

    @PublishedApi
    public static final int uintCompare(int i, int i2) {
        return Intrinsics.compare(i ^ Integer.MIN_VALUE, i2 ^ Integer.MIN_VALUE);
    }

    @PublishedApi
    /* renamed from: uintDivide-J1ME1BU  reason: not valid java name */
    public static final int m229uintDivideJ1ME1BU(int i, int i2) {
        return UInt.m158constructorimpl((int) ((i & 4294967295L) / (i2 & 4294967295L)));
    }

    @PublishedApi
    /* renamed from: uintRemainder-J1ME1BU  reason: not valid java name */
    public static final int m230uintRemainderJ1ME1BU(int i, int i2) {
        return UInt.m158constructorimpl((int) ((i & 4294967295L) % (i2 & 4294967295L)));
    }

    @PublishedApi
    public static final double uintToDouble(int i) {
        return (Integer.MAX_VALUE & i) + (((i >>> 31) << 30) * 2);
    }

    @PublishedApi
    public static final int ulongCompare(long j, long j2) {
        return Intrinsics.compare(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE);
    }

    @PublishedApi
    /* renamed from: ulongDivide-eb3DHEI  reason: not valid java name */
    public static final long m231ulongDivideeb3DHEI(long j, long j2) {
        int compare;
        int compare2;
        if (j2 < 0) {
            compare2 = Long.compare(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE);
            return compare2 < 0 ? ULong.m182constructorimpl(0L) : ULong.m182constructorimpl(1L);
        } else if (j >= 0) {
            return ULong.m182constructorimpl(j / j2);
        } else {
            long j3 = ((j >>> 1) / j2) << 1;
            compare = Long.compare(ULong.m182constructorimpl(j - (j3 * j2)) ^ Long.MIN_VALUE, ULong.m182constructorimpl(j2) ^ Long.MIN_VALUE);
            return ULong.m182constructorimpl(j3 + (compare < 0 ? 0 : 1));
        }
    }

    @PublishedApi
    /* renamed from: ulongRemainder-eb3DHEI  reason: not valid java name */
    public static final long m232ulongRemaindereb3DHEI(long j, long j2) {
        int compare;
        int compare2;
        if (j2 < 0) {
            compare2 = Long.compare(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE);
            return compare2 < 0 ? j : ULong.m182constructorimpl(j - j2);
        } else if (j >= 0) {
            return ULong.m182constructorimpl(j % j2);
        } else {
            long j3 = j - ((((j >>> 1) / j2) << 1) * j2);
            compare = Long.compare(ULong.m182constructorimpl(j3) ^ Long.MIN_VALUE, ULong.m182constructorimpl(j2) ^ Long.MIN_VALUE);
            if (compare < 0) {
                j2 = 0;
            }
            return ULong.m182constructorimpl(j3 - j2);
        }
    }

    @PublishedApi
    public static final double ulongToDouble(long j) {
        return ((j >>> 11) * 2048) + (j & 2047);
    }

    @NotNull
    public static final String ulongToString(long j) {
        return ulongToString(j, 10);
    }

    @NotNull
    public static final String ulongToString(long j, int i) {
        if (j >= 0) {
            String l = Long.toString(j, kotlin.text.a.checkRadix(i));
            Intrinsics.checkNotNullExpressionValue(l, "toString(this, checkRadix(radix))");
            return l;
        }
        long j2 = i;
        long j3 = ((j >>> 1) / j2) << 1;
        long j4 = j - (j3 * j2);
        if (j4 >= j2) {
            j4 -= j2;
            j3++;
        }
        StringBuilder sb = new StringBuilder();
        String l2 = Long.toString(j3, kotlin.text.a.checkRadix(i));
        Intrinsics.checkNotNullExpressionValue(l2, "toString(this, checkRadix(radix))");
        sb.append(l2);
        String l3 = Long.toString(j4, kotlin.text.a.checkRadix(i));
        Intrinsics.checkNotNullExpressionValue(l3, "toString(this, checkRadix(radix))");
        sb.append(l3);
        return sb.toString();
    }
}

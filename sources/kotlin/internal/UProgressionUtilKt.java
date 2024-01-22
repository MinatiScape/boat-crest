package kotlin.internal;

import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.UInt;
import kotlin.ULong;
/* loaded from: classes12.dex */
public final class UProgressionUtilKt {
    public static final int a(int i, int i2, int i3) {
        int compare;
        int a2 = a.a(i, i3);
        int a3 = a.a(i2, i3);
        compare = Integer.compare(a2 ^ Integer.MIN_VALUE, a3 ^ Integer.MIN_VALUE);
        int m158constructorimpl = UInt.m158constructorimpl(a2 - a3);
        return compare >= 0 ? m158constructorimpl : UInt.m158constructorimpl(m158constructorimpl + i3);
    }

    public static final long b(long j, long j2, long j3) {
        int compare;
        long a2 = b.a(j, j3);
        long a3 = b.a(j2, j3);
        compare = Long.compare(a2 ^ Long.MIN_VALUE, a3 ^ Long.MIN_VALUE);
        long m182constructorimpl = ULong.m182constructorimpl(a2 - a3);
        return compare >= 0 ? m182constructorimpl : ULong.m182constructorimpl(m182constructorimpl + j3);
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    /* renamed from: getProgressionLastElement-7ftBX0g  reason: not valid java name */
    public static final long m517getProgressionLastElement7ftBX0g(long j, long j2, long j3) {
        int compare;
        int compare2;
        int i = (j3 > 0L ? 1 : (j3 == 0L ? 0 : -1));
        if (i > 0) {
            compare2 = Long.compare(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE);
            return compare2 >= 0 ? j2 : ULong.m182constructorimpl(j2 - b(j2, j, ULong.m182constructorimpl(j3)));
        } else if (i < 0) {
            compare = Long.compare(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE);
            return compare <= 0 ? j2 : ULong.m182constructorimpl(j2 + b(j, j2, ULong.m182constructorimpl(-j3)));
        } else {
            throw new IllegalArgumentException("Step is zero.");
        }
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    /* renamed from: getProgressionLastElement-Nkh28Cs  reason: not valid java name */
    public static final int m518getProgressionLastElementNkh28Cs(int i, int i2, int i3) {
        int compare;
        int compare2;
        if (i3 > 0) {
            compare2 = Integer.compare(i ^ Integer.MIN_VALUE, i2 ^ Integer.MIN_VALUE);
            return compare2 >= 0 ? i2 : UInt.m158constructorimpl(i2 - a(i2, i, UInt.m158constructorimpl(i3)));
        } else if (i3 < 0) {
            compare = Integer.compare(i ^ Integer.MIN_VALUE, i2 ^ Integer.MIN_VALUE);
            return compare <= 0 ? i2 : UInt.m158constructorimpl(i2 + a(i, i2, UInt.m158constructorimpl(-i3)));
        } else {
            throw new IllegalArgumentException("Step is zero.");
        }
    }
}

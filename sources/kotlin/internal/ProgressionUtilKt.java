package kotlin.internal;

import kotlin.PublishedApi;
/* loaded from: classes12.dex */
public final class ProgressionUtilKt {
    public static final int a(int i, int i2, int i3) {
        return c(c(i, i3) - c(i2, i3), i3);
    }

    public static final long b(long j, long j2, long j3) {
        return d(d(j, j3) - d(j2, j3), j3);
    }

    public static final int c(int i, int i2) {
        int i3 = i % i2;
        return i3 >= 0 ? i3 : i3 + i2;
    }

    public static final long d(long j, long j2) {
        long j3 = j % j2;
        return j3 >= 0 ? j3 : j3 + j2;
    }

    @PublishedApi
    public static final int getProgressionLastElement(int i, int i2, int i3) {
        if (i3 > 0) {
            return i >= i2 ? i2 : i2 - a(i2, i, i3);
        } else if (i3 < 0) {
            return i <= i2 ? i2 : i2 + a(i, i2, -i3);
        } else {
            throw new IllegalArgumentException("Step is zero.");
        }
    }

    @PublishedApi
    public static final long getProgressionLastElement(long j, long j2, long j3) {
        int i = (j3 > 0L ? 1 : (j3 == 0L ? 0 : -1));
        if (i > 0) {
            return j >= j2 ? j2 : j2 - b(j2, j, j3);
        } else if (i < 0) {
            return j <= j2 ? j2 : j2 + b(j, j2, -j3);
        } else {
            throw new IllegalArgumentException("Step is zero.");
        }
    }
}

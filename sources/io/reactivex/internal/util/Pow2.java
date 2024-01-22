package io.reactivex.internal.util;
/* loaded from: classes12.dex */
public final class Pow2 {
    public Pow2() {
        throw new IllegalStateException("No instances!");
    }

    public static boolean isPowerOfTwo(int i) {
        return (i & (i + (-1))) == 0;
    }

    public static int roundToPowerOfTwo(int i) {
        return 1 << (32 - Integer.numberOfLeadingZeros(i - 1));
    }
}

package org.bouncycastle.pqc.math.linearalgebra;

import java.security.SecureRandom;
/* loaded from: classes13.dex */
public class RandUtils {
    public static int a(SecureRandom secureRandom, int i) {
        int nextInt;
        int i2;
        if (((-i) & i) == i) {
            return (int) ((i * (secureRandom.nextInt() >>> 1)) >> 31);
        }
        do {
            nextInt = secureRandom.nextInt() >>> 1;
            i2 = nextInt % i;
        } while ((nextInt - i2) + (i - 1) < 0);
        return i2;
    }
}

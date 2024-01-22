package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.CharMatcher;
import java.util.BitSet;
@GwtIncompatible
/* loaded from: classes10.dex */
public final class n extends CharMatcher.v {
    public final char[] i;
    public final boolean j;
    public final long k;

    public n(char[] cArr, long j, boolean z, String str) {
        super(str);
        this.i = cArr;
        this.k = j;
        this.j = z;
    }

    @VisibleForTesting
    public static int j(int i) {
        if (i == 1) {
            return 2;
        }
        int highestOneBit = Integer.highestOneBit(i - 1) << 1;
        while (highestOneBit * 0.5d < i) {
            highestOneBit <<= 1;
        }
        return highestOneBit;
    }

    public static CharMatcher k(BitSet bitSet, String str) {
        int i;
        int cardinality = bitSet.cardinality();
        boolean z = bitSet.get(0);
        int j = j(cardinality);
        char[] cArr = new char[j];
        int i2 = j - 1;
        int nextSetBit = bitSet.nextSetBit(0);
        long j2 = 0;
        while (nextSetBit != -1) {
            long j3 = (1 << nextSetBit) | j2;
            int l = l(nextSetBit);
            while (true) {
                i = l & i2;
                if (cArr[i] == 0) {
                    break;
                }
                l = i + 1;
            }
            cArr[i] = (char) nextSetBit;
            nextSetBit = bitSet.nextSetBit(nextSetBit + 1);
            j2 = j3;
        }
        return new n(cArr, j2, z, str);
    }

    public static int l(int i) {
        return Integer.rotateLeft(i * (-862048943), 15) * 461845907;
    }

    @Override // com.google.common.base.CharMatcher
    public void g(BitSet bitSet) {
        char[] cArr;
        if (this.j) {
            bitSet.set(0);
        }
        for (char c : this.i) {
            if (c != 0) {
                bitSet.set(c);
            }
        }
    }

    public final boolean i(int i) {
        return 1 == ((this.k >> i) & 1);
    }

    @Override // com.google.common.base.CharMatcher
    public boolean matches(char c) {
        if (c == 0) {
            return this.j;
        }
        if (i(c)) {
            int length = this.i.length - 1;
            int l = l(c) & length;
            int i = l;
            do {
                char[] cArr = this.i;
                if (cArr[i] == 0) {
                    return false;
                }
                if (cArr[i] == c) {
                    return true;
                }
                i = (i + 1) & length;
            } while (i != l);
            return false;
        }
        return false;
    }
}

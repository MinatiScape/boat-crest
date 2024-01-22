package com.mappls.sdk.gestures;

import androidx.annotation.IntRange;
/* loaded from: classes11.dex */
public class a {
    public boolean a(int i, @IntRange(from = 0) int i2, @IntRange(from = 0) int i3) {
        long b = b(i2, i3);
        long j = i;
        if (j == b) {
            return false;
        }
        while (b != 0) {
            if (j == (255 & b)) {
                return false;
            }
            b >>= 8;
        }
        return true;
    }

    public final long b(@IntRange(from = 0) int i, @IntRange(from = 0) int i2) {
        if (i2 == 0) {
            return 0L;
        }
        if (Math.abs(i - i2) > 1) {
            return 255L;
        }
        if (i > i2) {
            return 5L;
        }
        if (i < i2) {
            return 255L;
        }
        return ((i == 1 ? 1L : 6L) << 8) + 2;
    }
}

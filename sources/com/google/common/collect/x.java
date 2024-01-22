package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Objects;
import java.util.Arrays;
import kotlin.UShort;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtIncompatible
/* loaded from: classes10.dex */
public final class x {
    public static Object a(int i) {
        if (i < 2 || i > 1073741824 || Integer.highestOneBit(i) != i) {
            StringBuilder sb = new StringBuilder(52);
            sb.append("must be power of 2 between 2^1 and 2^30: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        } else if (i <= 256) {
            return new byte[i];
        } else {
            if (i <= 65536) {
                return new short[i];
            }
            return new int[i];
        }
    }

    public static int b(int i, int i2) {
        return i & (~i2);
    }

    public static int c(int i, int i2) {
        return i & i2;
    }

    public static int d(int i, int i2, int i3) {
        return (i & (~i3)) | (i2 & i3);
    }

    public static int e(int i) {
        return (i < 32 ? 4 : 2) * (i + 1);
    }

    public static int f(@NullableDecl Object obj, @NullableDecl Object obj2, int i, Object obj3, int[] iArr, Object[] objArr, @NullableDecl Object[] objArr2) {
        int i2;
        int i3;
        int d = w0.d(obj);
        int i4 = d & i;
        int h = h(obj3, i4);
        if (h == 0) {
            return -1;
        }
        int b = b(d, i);
        int i5 = -1;
        while (true) {
            i2 = h - 1;
            i3 = iArr[i2];
            if (b(i3, i) != b || !Objects.equal(obj, objArr[i2]) || (objArr2 != null && !Objects.equal(obj2, objArr2[i2]))) {
                int c = c(i3, i);
                if (c == 0) {
                    return -1;
                }
                i5 = i2;
                h = c;
            }
        }
        int c2 = c(i3, i);
        if (i5 == -1) {
            i(obj3, i4, c2);
        } else {
            iArr[i5] = d(iArr[i5], c2, i);
        }
        return i2;
    }

    public static void g(Object obj) {
        if (obj instanceof byte[]) {
            Arrays.fill((byte[]) obj, (byte) 0);
        } else if (obj instanceof short[]) {
            Arrays.fill((short[]) obj, (short) 0);
        } else {
            Arrays.fill((int[]) obj, 0);
        }
    }

    public static int h(Object obj, int i) {
        if (obj instanceof byte[]) {
            return ((byte[]) obj)[i] & 255;
        }
        if (obj instanceof short[]) {
            return ((short[]) obj)[i] & UShort.MAX_VALUE;
        }
        return ((int[]) obj)[i];
    }

    public static void i(Object obj, int i, int i2) {
        if (obj instanceof byte[]) {
            ((byte[]) obj)[i] = (byte) i2;
        } else if (obj instanceof short[]) {
            ((short[]) obj)[i] = (short) i2;
        } else {
            ((int[]) obj)[i] = i2;
        }
    }

    public static int j(int i) {
        return Math.max(4, w0.a(i + 1, 1.0d));
    }
}

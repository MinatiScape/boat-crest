package kotlin.collections;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.UByteArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class UArraySortingKt {
    /* JADX WARN: Incorrect condition in loop: B:5:0x0012 */
    /* JADX WARN: Incorrect condition in loop: B:8:0x001f */
    @kotlin.ExperimentalUnsignedTypes
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final int a(long[] r6, int r7, int r8) {
        /*
            int r0 = r7 + r8
            int r0 = r0 / 2
            long r0 = kotlin.ULongArray.m195getsVKNKU(r6, r0)
        L8:
            if (r7 > r8) goto L39
        La:
            long r2 = kotlin.ULongArray.m195getsVKNKU(r6, r7)
            int r2 = kotlin.n.a(r2, r0)
            if (r2 >= 0) goto L17
            int r7 = r7 + 1
            goto La
        L17:
            long r2 = kotlin.ULongArray.m195getsVKNKU(r6, r8)
            int r2 = kotlin.n.a(r2, r0)
            if (r2 <= 0) goto L24
            int r8 = r8 + (-1)
            goto L17
        L24:
            if (r7 > r8) goto L8
            long r2 = kotlin.ULongArray.m195getsVKNKU(r6, r7)
            long r4 = kotlin.ULongArray.m195getsVKNKU(r6, r8)
            kotlin.ULongArray.m200setk8EXiF4(r6, r7, r4)
            kotlin.ULongArray.m200setk8EXiF4(r6, r8, r2)
            int r7 = r7 + 1
            int r8 = r8 + (-1)
            goto L8
        L39:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.UArraySortingKt.a(long[], int, int):int");
    }

    @ExperimentalUnsignedTypes
    public static final int b(byte[] bArr, int i, int i2) {
        int i3;
        byte m147getw2LRezQ = UByteArray.m147getw2LRezQ(bArr, (i + i2) / 2);
        while (i <= i2) {
            while (true) {
                i3 = m147getw2LRezQ & 255;
                if (Intrinsics.compare(UByteArray.m147getw2LRezQ(bArr, i) & 255, i3) >= 0) {
                    break;
                }
                i++;
            }
            while (Intrinsics.compare(UByteArray.m147getw2LRezQ(bArr, i2) & 255, i3) > 0) {
                i2--;
            }
            if (i <= i2) {
                byte m147getw2LRezQ2 = UByteArray.m147getw2LRezQ(bArr, i);
                UByteArray.m152setVurrAj0(bArr, i, UByteArray.m147getw2LRezQ(bArr, i2));
                UByteArray.m152setVurrAj0(bArr, i2, m147getw2LRezQ2);
                i++;
                i2--;
            }
        }
        return i;
    }

    @ExperimentalUnsignedTypes
    public static final int c(short[] sArr, int i, int i2) {
        int i3;
        short m219getMh2AYeg = UShortArray.m219getMh2AYeg(sArr, (i + i2) / 2);
        while (i <= i2) {
            while (true) {
                int m219getMh2AYeg2 = UShortArray.m219getMh2AYeg(sArr, i) & UShort.MAX_VALUE;
                i3 = m219getMh2AYeg & UShort.MAX_VALUE;
                if (Intrinsics.compare(m219getMh2AYeg2, i3) >= 0) {
                    break;
                }
                i++;
            }
            while (Intrinsics.compare(UShortArray.m219getMh2AYeg(sArr, i2) & UShort.MAX_VALUE, i3) > 0) {
                i2--;
            }
            if (i <= i2) {
                short m219getMh2AYeg3 = UShortArray.m219getMh2AYeg(sArr, i);
                UShortArray.m224set01HTLdE(sArr, i, UShortArray.m219getMh2AYeg(sArr, i2));
                UShortArray.m224set01HTLdE(sArr, i2, m219getMh2AYeg3);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* JADX WARN: Incorrect condition in loop: B:5:0x0012 */
    /* JADX WARN: Incorrect condition in loop: B:8:0x001f */
    @kotlin.ExperimentalUnsignedTypes
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final int d(int[] r3, int r4, int r5) {
        /*
            int r0 = r4 + r5
            int r0 = r0 / 2
            int r0 = kotlin.UIntArray.m171getpVg5ArA(r3, r0)
        L8:
            if (r4 > r5) goto L39
        La:
            int r1 = kotlin.UIntArray.m171getpVg5ArA(r3, r4)
            int r1 = kotlin.collections.d0.a(r1, r0)
            if (r1 >= 0) goto L17
            int r4 = r4 + 1
            goto La
        L17:
            int r1 = kotlin.UIntArray.m171getpVg5ArA(r3, r5)
            int r1 = kotlin.collections.d0.a(r1, r0)
            if (r1 <= 0) goto L24
            int r5 = r5 + (-1)
            goto L17
        L24:
            if (r4 > r5) goto L8
            int r1 = kotlin.UIntArray.m171getpVg5ArA(r3, r4)
            int r2 = kotlin.UIntArray.m171getpVg5ArA(r3, r5)
            kotlin.UIntArray.m176setVXSXFK8(r3, r4, r2)
            kotlin.UIntArray.m176setVXSXFK8(r3, r5, r1)
            int r4 = r4 + 1
            int r5 = r5 + (-1)
            goto L8
        L39:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.UArraySortingKt.d(int[], int, int):int");
    }

    @ExperimentalUnsignedTypes
    public static final void e(long[] jArr, int i, int i2) {
        int a2 = a(jArr, i, i2);
        int i3 = a2 - 1;
        if (i < i3) {
            e(jArr, i, i3);
        }
        if (a2 < i2) {
            e(jArr, a2, i2);
        }
    }

    @ExperimentalUnsignedTypes
    public static final void f(byte[] bArr, int i, int i2) {
        int b = b(bArr, i, i2);
        int i3 = b - 1;
        if (i < i3) {
            f(bArr, i, i3);
        }
        if (b < i2) {
            f(bArr, b, i2);
        }
    }

    @ExperimentalUnsignedTypes
    public static final void g(short[] sArr, int i, int i2) {
        int c = c(sArr, i, i2);
        int i3 = c - 1;
        if (i < i3) {
            g(sArr, i, i3);
        }
        if (c < i2) {
            g(sArr, c, i2);
        }
    }

    @ExperimentalUnsignedTypes
    public static final void h(int[] iArr, int i, int i2) {
        int d = d(iArr, i, i2);
        int i3 = d - 1;
        if (i < i3) {
            h(iArr, i, i3);
        }
        if (d < i2) {
            h(iArr, d, i2);
        }
    }

    @ExperimentalUnsignedTypes
    /* renamed from: sortArray--nroSd4  reason: not valid java name */
    public static final void m241sortArraynroSd4(@NotNull long[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        e(array, i, i2 - 1);
    }

    @ExperimentalUnsignedTypes
    /* renamed from: sortArray-4UcCI2c  reason: not valid java name */
    public static final void m242sortArray4UcCI2c(@NotNull byte[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        f(array, i, i2 - 1);
    }

    @ExperimentalUnsignedTypes
    /* renamed from: sortArray-Aa5vz7o  reason: not valid java name */
    public static final void m243sortArrayAa5vz7o(@NotNull short[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        g(array, i, i2 - 1);
    }

    @ExperimentalUnsignedTypes
    /* renamed from: sortArray-oBK06Vg  reason: not valid java name */
    public static final void m244sortArrayoBK06Vg(@NotNull int[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        h(array, i, i2 - 1);
    }
}

package com.google.zxing.aztec.encoder;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
/* loaded from: classes11.dex */
public final class HighLevelEncoder {
    public static final String[] b = {"UPPER", "LOWER", "DIGIT", "MIXED", "PUNCT"};
    public static final int[][] c = {new int[]{0, 327708, 327710, 327709, 656318}, new int[]{590318, 0, 327710, 327709, 656318}, new int[]{262158, 590300, 0, 590301, 932798}, new int[]{327709, 327708, 656318, 0, 327710}, new int[]{327711, 656380, 656382, 656381, 0}};
    public static final int[][] d;
    public static final int[][] e;

    /* renamed from: a  reason: collision with root package name */
    public final byte[] f11775a;

    /* loaded from: classes11.dex */
    public class a implements Comparator<c> {
        public a(HighLevelEncoder highLevelEncoder) {
        }

        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(c cVar, c cVar2) {
            return cVar.e() - cVar2.e();
        }
    }

    static {
        int[][] iArr = (int[][]) Array.newInstance(int.class, 5, 256);
        d = iArr;
        iArr[0][32] = 1;
        for (int i = 65; i <= 90; i++) {
            d[0][i] = (i - 65) + 2;
        }
        d[1][32] = 1;
        for (int i2 = 97; i2 <= 122; i2++) {
            d[1][i2] = (i2 - 97) + 2;
        }
        d[2][32] = 1;
        for (int i3 = 48; i3 <= 57; i3++) {
            d[2][i3] = (i3 - 48) + 2;
        }
        int[][] iArr2 = d;
        iArr2[2][44] = 12;
        iArr2[2][46] = 13;
        int[] iArr3 = {0, 32, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 27, 28, 29, 30, 31, 64, 92, 94, 95, 96, 124, 126, 127};
        for (int i4 = 0; i4 < 28; i4++) {
            d[3][iArr3[i4]] = i4;
        }
        int[] iArr4 = {0, 13, 0, 0, 0, 0, 33, 39, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 58, 59, 60, 61, 62, 63, 91, 93, 123, 125};
        for (int i5 = 0; i5 < 31; i5++) {
            if (iArr4[i5] > 0) {
                d[4][iArr4[i5]] = i5;
            }
        }
        int[][] iArr5 = (int[][]) Array.newInstance(int.class, 6, 6);
        e = iArr5;
        for (int[] iArr6 : iArr5) {
            Arrays.fill(iArr6, -1);
        }
        int[][] iArr7 = e;
        iArr7[0][4] = 0;
        iArr7[1][4] = 0;
        iArr7[1][0] = 28;
        iArr7[3][4] = 0;
        iArr7[2][4] = 0;
        iArr7[2][0] = 15;
    }

    public HighLevelEncoder(byte[] bArr) {
        this.f11775a = bArr;
    }

    public static Collection<c> a(Iterable<c> iterable) {
        LinkedList linkedList = new LinkedList();
        for (c cVar : iterable) {
            boolean z = true;
            Iterator it = linkedList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                c cVar2 = (c) it.next();
                if (cVar2.g(cVar)) {
                    z = false;
                    break;
                } else if (cVar.g(cVar2)) {
                    it.remove();
                }
            }
            if (z) {
                linkedList.add(cVar);
            }
        }
        return linkedList;
    }

    public static void c(c cVar, int i, int i2, Collection<c> collection) {
        c c2 = cVar.c(i);
        collection.add(c2.h(4, i2));
        if (cVar.f() != 4) {
            collection.add(c2.i(4, i2));
        }
        if (i2 == 3 || i2 == 4) {
            collection.add(c2.h(2, 16 - i2).h(2, 1));
        }
        if (cVar.d() > 0) {
            collection.add(cVar.a(i).a(i + 1));
        }
    }

    public static Collection<c> e(Iterable<c> iterable, int i, int i2) {
        LinkedList linkedList = new LinkedList();
        for (c cVar : iterable) {
            c(cVar, i, i2, linkedList);
        }
        return a(linkedList);
    }

    public final void b(c cVar, int i, Collection<c> collection) {
        char c2 = (char) (this.f11775a[i] & 255);
        boolean z = d[cVar.f()][c2] > 0;
        c cVar2 = null;
        for (int i2 = 0; i2 <= 4; i2++) {
            int i3 = d[i2][c2];
            if (i3 > 0) {
                if (cVar2 == null) {
                    cVar2 = cVar.c(i);
                }
                if (!z || i2 == cVar.f() || i2 == 2) {
                    collection.add(cVar2.h(i2, i3));
                }
                if (!z && e[cVar.f()][i2] >= 0) {
                    collection.add(cVar2.i(i2, i3));
                }
            }
        }
        if (cVar.d() > 0 || d[cVar.f()][c2] == 0) {
            collection.add(cVar.a(i));
        }
    }

    public final Collection<c> d(Iterable<c> iterable, int i) {
        LinkedList linkedList = new LinkedList();
        for (c cVar : iterable) {
            b(cVar, i, linkedList);
        }
        return a(linkedList);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0045  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.google.zxing.common.BitArray encode() {
        /*
            r8 = this;
            com.google.zxing.aztec.encoder.c r0 = com.google.zxing.aztec.encoder.c.e
            java.util.List r0 = java.util.Collections.singletonList(r0)
            r1 = 0
            r2 = r1
        L8:
            byte[] r3 = r8.f11775a
            int r4 = r3.length
            if (r2 >= r4) goto L4c
            int r4 = r2 + 1
            int r5 = r3.length
            if (r4 >= r5) goto L15
            r5 = r3[r4]
            goto L16
        L15:
            r5 = r1
        L16:
            r3 = r3[r2]
            r6 = 13
            if (r3 == r6) goto L38
            r6 = 44
            r7 = 32
            if (r3 == r6) goto L34
            r6 = 46
            if (r3 == r6) goto L30
            r6 = 58
            if (r3 == r6) goto L2c
        L2a:
            r3 = r1
            goto L3d
        L2c:
            if (r5 != r7) goto L2a
            r3 = 5
            goto L3d
        L30:
            if (r5 != r7) goto L2a
            r3 = 3
            goto L3d
        L34:
            if (r5 != r7) goto L2a
            r3 = 4
            goto L3d
        L38:
            r3 = 10
            if (r5 != r3) goto L2a
            r3 = 2
        L3d:
            if (r3 <= 0) goto L45
            java.util.Collection r0 = e(r0, r2, r3)
            r2 = r4
            goto L49
        L45:
            java.util.Collection r0 = r8.d(r0, r2)
        L49:
            int r2 = r2 + 1
            goto L8
        L4c:
            com.google.zxing.aztec.encoder.HighLevelEncoder$a r1 = new com.google.zxing.aztec.encoder.HighLevelEncoder$a
            r1.<init>(r8)
            java.lang.Object r0 = java.util.Collections.min(r0, r1)
            com.google.zxing.aztec.encoder.c r0 = (com.google.zxing.aztec.encoder.c) r0
            byte[] r1 = r8.f11775a
            com.google.zxing.common.BitArray r0 = r0.j(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.aztec.encoder.HighLevelEncoder.encode():com.google.zxing.common.BitArray");
    }
}

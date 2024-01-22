package org.bouncycastle.pqc.crypto.sphincs;
/* loaded from: classes13.dex */
public class e {

    /* loaded from: classes13.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public int f15320a;
        public long b;
        public long c;

        public a() {
        }

        public a(a aVar) {
            this.f15320a = aVar.f15320a;
            this.b = aVar.b;
            this.c = aVar.c;
        }
    }

    public static void a(org.bouncycastle.pqc.crypto.sphincs.a aVar, byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, a aVar2) {
        byte[] bArr4 = new byte[32];
        byte[] bArr5 = new byte[2144];
        f fVar = new f();
        d.a(aVar, bArr4, 0, bArr3, aVar2);
        fVar.d(aVar, bArr5, 0, bArr4, 0, bArr2, i2);
        b(aVar, bArr, i, bArr5, 0, bArr2, i2);
    }

    public static void b(org.bouncycastle.pqc.crypto.sphincs.a aVar, byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3) {
        int i4;
        int i5 = 67;
        for (int i6 = 0; i6 < 7; i6++) {
            int i7 = 0;
            while (true) {
                i4 = i5 >>> 1;
                if (i7 >= i4) {
                    break;
                }
                aVar.c(bArr2, i2 + (i7 * 32), bArr2, i2 + (i7 * 2 * 32), bArr3, i3 + (i6 * 2 * 32));
                i7++;
            }
            if ((i5 & 1) != 0) {
                System.arraycopy(bArr2, i2 + ((i5 - 1) * 32), bArr2, (i4 * 32) + i2, 32);
                i4++;
            }
            i5 = i4;
        }
        System.arraycopy(bArr2, i2, bArr, i, 32);
    }

    public static void c(org.bouncycastle.pqc.crypto.sphincs.a aVar, byte[] bArr, int i, int i2, byte[] bArr2, a aVar2, byte[] bArr3, int i3) {
        a aVar3 = new a(aVar2);
        int i4 = i2 + 1;
        byte[] bArr4 = new byte[i4 * 32];
        int[] iArr = new int[i4];
        int i5 = 1;
        int i6 = (int) (aVar3.c + (1 << i2));
        int i7 = 0;
        while (true) {
            int i8 = 32;
            if (aVar3.c >= i6) {
                break;
            }
            a(aVar, bArr4, i7 * 32, bArr3, i3, bArr2, aVar3);
            iArr[i7] = 0;
            int i9 = i7 + i5;
            while (i9 > i5) {
                int i10 = i9 - 1;
                int i11 = i9 - 2;
                if (iArr[i10] == iArr[i11]) {
                    int i12 = i11 * 32;
                    int i13 = i5;
                    int[] iArr2 = iArr;
                    aVar.c(bArr4, i12, bArr4, i12, bArr3, i3 + ((iArr[i10] + 7) * 2 * i8));
                    iArr2[i11] = iArr2[i11] + i13;
                    i9--;
                    i5 = i13;
                    i8 = i8;
                    i6 = i6;
                    iArr = iArr2;
                }
            }
            aVar3.c++;
            i7 = i9;
            i5 = i5;
            i6 = i6;
            iArr = iArr;
        }
        for (int i14 = 0; i14 < 32; i14++) {
            bArr[i + i14] = bArr4[i14];
        }
    }
}

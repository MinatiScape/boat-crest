package com.google.crypto.tink.subtle;

import com.coveiot.sdk.ble.api.BleUUID;
import com.google.crypto.tink.annotations.Alpha;
import java.security.InvalidKeyException;
import java.util.Arrays;
import org.bouncycastle.crypto.signers.PSSSigner;
@Alpha
/* loaded from: classes10.dex */
public final class e {

    /* renamed from: a  reason: collision with root package name */
    public static final byte[][] f11052a = {new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new byte[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new byte[]{-32, -21, com.htsmart.wristband2.a.a.a.V1, 124, 59, 65, BleUUID.CMD_ID_B8, -82, 22, 86, -29, -6, com.crrepa.c.a.J1, -97, -60, 106, -38, 9, -115, -21, -100, 50, -79, -3, -122, 98, 5, 22, 95, 73, BleUUID.CMD_ID_B8, 0}, new byte[]{95, -100, -107, PSSSigner.TRAILER_IMPLICIT, -93, com.htsmart.wristband2.a.a.a.d1, -116, 36, -79, -48, -79, 85, -100, -125, -17, com.htsmart.wristband2.a.a.a.r1, 4, 68, 92, -60, com.htsmart.wristband2.a.a.a.o1, 28, -114, -122, -40, 34, com.htsmart.wristband2.a.a.a.c1, -35, -48, -97, 17, 87}, new byte[]{-20, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, Byte.MAX_VALUE}, new byte[]{-19, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, Byte.MAX_VALUE}, new byte[]{com.crrepa.j.o.c, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, Byte.MAX_VALUE}};

    public static void a(long[] jArr, long[] jArr2, int i) {
        int i2 = -i;
        for (int i3 = 0; i3 < 10; i3++) {
            jArr[i3] = ((((int) jArr[i3]) ^ ((int) jArr2[i3])) & i2) ^ ((int) jArr[i3]);
        }
    }

    public static void b(long[] jArr, byte[] bArr, byte[] bArr2) throws InvalidKeyException {
        f(bArr2);
        long[] c = h.c(bArr2);
        long[] jArr2 = new long[19];
        long[] jArr3 = new long[19];
        int i = 0;
        jArr3[0] = 1;
        long[] jArr4 = new long[19];
        jArr4[0] = 1;
        long[] jArr5 = new long[19];
        long[] jArr6 = new long[19];
        long[] jArr7 = new long[19];
        jArr7[0] = 1;
        long[] jArr8 = new long[19];
        long[] jArr9 = new long[19];
        jArr9[0] = 1;
        int i2 = 10;
        System.arraycopy(c, 0, jArr2, 0, 10);
        int i3 = 0;
        while (i3 < 32) {
            int i4 = bArr[(32 - i3) - 1] & 255;
            long[] jArr10 = jArr6;
            long[] jArr11 = jArr7;
            long[] jArr12 = jArr2;
            long[] jArr13 = jArr3;
            int i5 = i;
            long[] jArr14 = jArr8;
            long[] jArr15 = jArr9;
            long[] jArr16 = jArr5;
            long[] jArr17 = jArr4;
            long[] jArr18 = jArr16;
            while (i5 < 8) {
                int i6 = (i4 >> (7 - i5)) & 1;
                e(jArr17, jArr12, i6);
                e(jArr18, jArr13, i6);
                long[] jArr19 = jArr14;
                long[] jArr20 = jArr11;
                int i7 = i4;
                long[] jArr21 = jArr10;
                long[] jArr22 = jArr18;
                long[] jArr23 = jArr17;
                long[] jArr24 = jArr13;
                long[] jArr25 = jArr12;
                d(jArr14, jArr15, jArr10, jArr11, jArr17, jArr18, jArr12, jArr13, c);
                e(jArr19, jArr21, i6);
                e(jArr15, jArr20, i6);
                i5++;
                jArr13 = jArr20;
                jArr18 = jArr15;
                jArr17 = jArr19;
                jArr12 = jArr21;
                i4 = i7;
                jArr15 = jArr22;
                jArr14 = jArr23;
                jArr11 = jArr24;
                jArr10 = jArr25;
            }
            long[] jArr26 = jArr17;
            long[] jArr27 = jArr13;
            long[] jArr28 = jArr12;
            jArr7 = jArr11;
            i3++;
            jArr9 = jArr15;
            jArr8 = jArr14;
            jArr6 = jArr10;
            jArr5 = jArr18;
            jArr4 = jArr26;
            jArr3 = jArr27;
            jArr2 = jArr28;
            i = 0;
            i2 = 10;
        }
        long[] jArr29 = new long[i2];
        h.e(jArr29, jArr5);
        h.f(jArr, jArr4, jArr29);
        if (c(c, jArr, jArr2, jArr3)) {
            return;
        }
        throw new IllegalStateException("Arithmetic error in curve multiplication with the public key: " + Hex.encode(bArr2));
    }

    public static boolean c(long[] jArr, long[] jArr2, long[] jArr3, long[] jArr4) {
        long[] jArr5 = new long[10];
        long[] jArr6 = new long[10];
        long[] jArr7 = new long[11];
        long[] jArr8 = new long[11];
        long[] jArr9 = new long[11];
        h.f(jArr5, jArr, jArr2);
        h.q(jArr6, jArr, jArr2);
        long[] jArr10 = new long[10];
        jArr10[0] = 486662;
        h.q(jArr8, jArr6, jArr10);
        h.f(jArr8, jArr8, jArr4);
        h.p(jArr8, jArr3);
        h.f(jArr8, jArr8, jArr5);
        h.f(jArr8, jArr8, jArr3);
        h.k(jArr7, jArr8, 4L);
        h.i(jArr7);
        h.f(jArr8, jArr5, jArr4);
        h.o(jArr8, jArr8, jArr4);
        h.f(jArr9, jArr6, jArr3);
        h.q(jArr8, jArr8, jArr9);
        h.l(jArr8, jArr8);
        return Bytes.equal(h.a(jArr7), h.a(jArr8));
    }

    public static void d(long[] jArr, long[] jArr2, long[] jArr3, long[] jArr4, long[] jArr5, long[] jArr6, long[] jArr7, long[] jArr8, long[] jArr9) {
        long[] copyOf = Arrays.copyOf(jArr5, 10);
        long[] jArr10 = new long[19];
        long[] jArr11 = new long[19];
        long[] jArr12 = new long[19];
        long[] jArr13 = new long[19];
        long[] jArr14 = new long[19];
        long[] jArr15 = new long[19];
        long[] jArr16 = new long[19];
        h.p(jArr5, jArr6);
        h.n(jArr6, copyOf);
        long[] copyOf2 = Arrays.copyOf(jArr7, 10);
        h.p(jArr7, jArr8);
        h.n(jArr8, copyOf2);
        h.g(jArr13, jArr7, jArr6);
        h.g(jArr14, jArr5, jArr8);
        h.j(jArr13);
        h.i(jArr13);
        h.j(jArr14);
        h.i(jArr14);
        System.arraycopy(jArr13, 0, copyOf2, 0, 10);
        h.p(jArr13, jArr14);
        h.n(jArr14, copyOf2);
        h.l(jArr16, jArr13);
        h.l(jArr15, jArr14);
        h.g(jArr14, jArr15, jArr9);
        h.j(jArr14);
        h.i(jArr14);
        System.arraycopy(jArr16, 0, jArr3, 0, 10);
        System.arraycopy(jArr14, 0, jArr4, 0, 10);
        h.l(jArr11, jArr5);
        h.l(jArr12, jArr6);
        h.g(jArr, jArr11, jArr12);
        h.j(jArr);
        h.i(jArr);
        h.n(jArr12, jArr11);
        Arrays.fill(jArr10, 10, 18, 0L);
        h.k(jArr10, jArr12, 121665L);
        h.i(jArr10);
        h.p(jArr10, jArr11);
        h.g(jArr2, jArr12, jArr10);
        h.j(jArr2);
        h.i(jArr2);
    }

    public static void e(long[] jArr, long[] jArr2, int i) {
        int i2 = -i;
        for (int i3 = 0; i3 < 10; i3++) {
            int i4 = (((int) jArr[i3]) ^ ((int) jArr2[i3])) & i2;
            jArr[i3] = ((int) jArr[i3]) ^ i4;
            jArr2[i3] = i4 ^ ((int) jArr2[i3]);
        }
    }

    public static void f(byte[] bArr) throws InvalidKeyException {
        if (bArr.length == 32) {
            bArr[31] = (byte) (bArr[31] & Byte.MAX_VALUE);
            int i = 0;
            while (true) {
                byte[][] bArr2 = f11052a;
                if (i >= bArr2.length) {
                    return;
                }
                if (Bytes.equal(bArr2[i], bArr)) {
                    throw new InvalidKeyException("Banned public key: " + Hex.encode(bArr2[i]));
                }
                i++;
            }
        } else {
            throw new InvalidKeyException("Public key length is not 32-byte");
        }
    }
}

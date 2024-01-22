package org.bouncycastle.pqc.crypto.newhope;

import java.security.SecureRandom;
import org.bouncycastle.crypto.digests.SHA3Digest;
/* loaded from: classes13.dex */
public class d {
    public static void a(short[] sArr, byte[] bArr, byte[] bArr2) {
        e.b(sArr, bArr2);
        System.arraycopy(bArr2, 1792, bArr, 0, 32);
    }

    public static void b(short[] sArr, short[] sArr2, byte[] bArr) {
        e.b(sArr, bArr);
        for (int i = 0; i < 256; i++) {
            int i2 = i * 4;
            int i3 = bArr[i + 1792] & 255;
            sArr2[i2 + 0] = (short) (i3 & 3);
            sArr2[i2 + 1] = (short) ((i3 >>> 2) & 3);
            sArr2[i2 + 2] = (short) ((i3 >>> 4) & 3);
            sArr2[i2 + 3] = (short) (i3 >>> 6);
        }
    }

    public static void c(byte[] bArr, short[] sArr, byte[] bArr2) {
        e.g(bArr, sArr);
        System.arraycopy(bArr2, 0, bArr, 1792, 32);
    }

    public static void d(byte[] bArr, short[] sArr, short[] sArr2) {
        e.g(bArr, sArr);
        for (int i = 0; i < 256; i++) {
            int i2 = i * 4;
            bArr[i + 1792] = (byte) ((sArr2[i2 + 3] << 6) | sArr2[i2] | (sArr2[i2 + 1] << 2) | (sArr2[i2 + 2] << 4));
        }
    }

    public static void e(short[] sArr, byte[] bArr) {
        e.i(sArr, bArr);
    }

    public static void f(SecureRandom secureRandom, byte[] bArr, short[] sArr) {
        byte[] bArr2 = new byte[32];
        secureRandom.nextBytes(bArr2);
        g(bArr2);
        short[] sArr2 = new short[1024];
        e(sArr2, bArr2);
        byte[] bArr3 = new byte[32];
        secureRandom.nextBytes(bArr3);
        e.d(sArr, bArr3, (byte) 0);
        e.h(sArr);
        short[] sArr3 = new short[1024];
        e.d(sArr3, bArr3, (byte) 1);
        e.h(sArr3);
        short[] sArr4 = new short[1024];
        e.f(sArr2, sArr, sArr4);
        short[] sArr5 = new short[1024];
        e.a(sArr4, sArr3, sArr5);
        c(bArr, sArr5, bArr2);
    }

    public static void g(byte[] bArr) {
        SHA3Digest sHA3Digest = new SHA3Digest(256);
        sHA3Digest.update(bArr, 0, 32);
        sHA3Digest.doFinal(bArr, 0);
    }

    public static void h(byte[] bArr, short[] sArr, byte[] bArr2) {
        short[] sArr2 = new short[1024];
        short[] sArr3 = new short[1024];
        b(sArr2, sArr3, bArr2);
        short[] sArr4 = new short[1024];
        e.f(sArr, sArr2, sArr4);
        e.c(sArr4);
        b.f(bArr, sArr4, sArr3);
        g(bArr);
    }

    public static void i(SecureRandom secureRandom, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        short[] sArr = new short[1024];
        byte[] bArr4 = new byte[32];
        a(sArr, bArr4, bArr3);
        short[] sArr2 = new short[1024];
        e(sArr2, bArr4);
        byte[] bArr5 = new byte[32];
        secureRandom.nextBytes(bArr5);
        short[] sArr3 = new short[1024];
        e.d(sArr3, bArr5, (byte) 0);
        e.h(sArr3);
        short[] sArr4 = new short[1024];
        e.d(sArr4, bArr5, (byte) 1);
        e.h(sArr4);
        short[] sArr5 = new short[1024];
        e.f(sArr2, sArr3, sArr5);
        e.a(sArr5, sArr4, sArr5);
        short[] sArr6 = new short[1024];
        e.f(sArr, sArr3, sArr6);
        e.c(sArr6);
        short[] sArr7 = new short[1024];
        e.d(sArr7, bArr5, (byte) 2);
        e.a(sArr6, sArr7, sArr6);
        short[] sArr8 = new short[1024];
        b.e(sArr8, sArr6, bArr5, (byte) 3);
        d(bArr2, sArr5, sArr8);
        b.f(bArr, sArr6, sArr8);
        g(bArr);
    }
}

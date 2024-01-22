package org.bouncycastle.crypto.digests;

import com.goodix.ble.libcomx.logger.RingLogger;
import com.google.mlkit.common.MlKitException;
import com.ido.ble.protocol.model.PressureParam;
import com.jieli.jl_rcsp.constant.Command;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.bouncycastle.math.Primes;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Memoable;
/* loaded from: classes12.dex */
public final class WhirlpoolDigest implements ExtendedDigest, Memoable {
    public static final int[] j = {24, 35, 198, 232, 135, 184, 1, 79, 54, 166, Command.CMD_RECEIVE_SPEECH_CANCEL, 245, 121, 111, 145, 82, 96, 188, 155, 142, 163, 12, 123, 53, 29, 224, 215, 194, 46, 75, 254, 87, 21, 119, 55, 229, 159, 240, 74, 218, 88, 201, 41, 10, 177, 160, 107, 133, CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256, 93, 16, 244, 203, 62, 5, 103, 228, 39, 65, 139, 167, 125, 149, Command.CMD_SET_DEVICE_STORAGE, 251, 238, 124, 102, PressureParam.STATE_ALL_DAY, 23, 71, 158, 202, 45, 191, 7, 173, 90, 131, 51, 99, 2, 170, 113, 200, 25, 73, Command.CMD_GET_DEVICE_CONFIG_INFO, 242, 227, 91, 136, 154, 38, 50, 176, 233, 15, Command.CMD_GET_LOW_LATENCY_SETTINGS, 128, 190, 205, 52, 72, 255, 122, 144, 95, 32, 104, 26, 174, 180, 84, 147, 34, 100, Command.CMD_PHONE_NUMBER_PLAY_MODE, 115, 18, 64, 8, 195, 236, 219, 161, 141, 61, 151, 0, MlKitException.CODE_SCANNER_GOOGLE_PLAY_SERVICES_VERSION_TOO_OLD, 43, 118, 130, Command.CMD_GET_EXTERNAL_FLASH_MSG, 27, 181, 175, 106, 80, 69, 243, 48, 239, 63, 85, 162, 234, 101, 186, 47, 192, 222, 28, 253, 77, CipherSuite.TLS_RSA_PSK_WITH_RC4_128_SHA, 117, 6, 138, 178, 230, 14, 31, 98, 212, 168, 150, 249, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, 37, 89, 132, 114, 57, 76, 94, 120, 56, 140, 209, 165, 226, 97, 179, 33, 156, 30, 67, 199, 252, 4, 81, 153, 109, 13, 250, 223, 126, 36, 59, 171, 206, 17, 143, 78, 183, 235, 60, 129, 148, 247, 185, 19, 44, Primes.SMALL_FACTOR_LIMIT, 231, 110, 196, 3, 86, 68, 127, 169, 42, 187, 193, 83, 220, 11, 157, 108, 49, 116, 246, 70, 172, 137, 20, 225, 22, 58, 105, 9, 112, 182, Command.CMD_NOTIFY_DEVICE_APP_INFO, 237, 204, 66, 152, 164, 40, 92, RingLogger.EVT_UPDATE, 134};
    public static final long[] k = new long[256];
    public static final long[] l = new long[256];
    public static final long[] m = new long[256];
    public static final long[] n = new long[256];
    public static final long[] o = new long[256];
    public static final long[] p = new long[256];
    public static final long[] q = new long[256];
    public static final long[] r = new long[256];
    public static final short[] s;

    /* renamed from: a  reason: collision with root package name */
    public final long[] f14652a;
    public byte[] b;
    public int c;
    public short[] d;
    public long[] e;
    public long[] f;
    public long[] g;
    public long[] h;
    public long[] i;

    static {
        short[] sArr = new short[32];
        s = sArr;
        sArr[31] = 8;
    }

    public WhirlpoolDigest() {
        this.f14652a = new long[11];
        this.b = new byte[64];
        this.c = 0;
        this.d = new short[32];
        this.e = new long[8];
        this.f = new long[8];
        this.g = new long[8];
        this.h = new long[8];
        this.i = new long[8];
        for (int i = 0; i < 256; i++) {
            int i2 = j[i];
            int f = f(i2 << 1);
            int f2 = f(f << 1);
            int i3 = f2 ^ i2;
            int f3 = f(f2 << 1);
            int i4 = f3 ^ i2;
            k[i] = g(i2, i2, f2, i2, f3, i3, f, i4);
            l[i] = g(i4, i2, i2, f2, i2, f3, i3, f);
            m[i] = g(f, i4, i2, i2, f2, i2, f3, i3);
            n[i] = g(i3, f, i4, i2, i2, f2, i2, f3);
            o[i] = g(f3, i3, f, i4, i2, i2, f2, i2);
            p[i] = g(i2, f3, i3, f, i4, i2, i2, f2);
            q[i] = g(f2, i2, f3, i3, f, i4, i2, i2);
            r[i] = g(i2, f2, i2, f3, i3, f, i4, i2);
        }
        this.f14652a[0] = 0;
        for (int i5 = 1; i5 <= 10; i5++) {
            int i6 = (i5 - 1) * 8;
            this.f14652a[i5] = (((((((k[i6] & (-72057594037927936L)) ^ (l[i6 + 1] & 71776119061217280L)) ^ (m[i6 + 2] & 280375465082880L)) ^ (n[i6 + 3] & 1095216660480L)) ^ (o[i6 + 4] & 4278190080L)) ^ (p[i6 + 5] & 16711680)) ^ (q[i6 + 6] & 65280)) ^ (r[i6 + 7] & 255);
        }
    }

    public WhirlpoolDigest(WhirlpoolDigest whirlpoolDigest) {
        this.f14652a = new long[11];
        this.b = new byte[64];
        this.c = 0;
        this.d = new short[32];
        this.e = new long[8];
        this.f = new long[8];
        this.g = new long[8];
        this.h = new long[8];
        this.i = new long[8];
        reset(whirlpoolDigest);
    }

    public final long a(byte[] bArr, int i) {
        return (bArr[i + 7] & 255) | ((bArr[i + 0] & 255) << 56) | ((bArr[i + 1] & 255) << 48) | ((bArr[i + 2] & 255) << 40) | ((bArr[i + 3] & 255) << 32) | ((bArr[i + 4] & 255) << 24) | ((bArr[i + 5] & 255) << 16) | ((bArr[i + 6] & 255) << 8);
    }

    public final void b(long j2, byte[] bArr, int i) {
        for (int i2 = 0; i2 < 8; i2++) {
            bArr[i + i2] = (byte) ((j2 >> (56 - (i2 * 8))) & 255);
        }
    }

    public final byte[] c() {
        byte[] bArr = new byte[32];
        for (int i = 0; i < 32; i++) {
            bArr[i] = (byte) (this.d[i] & 255);
        }
        return bArr;
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new WhirlpoolDigest(this);
    }

    public final void d() {
        byte[] c = c();
        byte[] bArr = this.b;
        int i = this.c;
        int i2 = i + 1;
        this.c = i2;
        bArr[i] = (byte) (bArr[i] | 128);
        if (i2 == bArr.length) {
            h(bArr, 0);
        }
        if (this.c > 32) {
            while (this.c != 0) {
                update((byte) 0);
            }
        }
        while (this.c <= 32) {
            update((byte) 0);
        }
        System.arraycopy(c, 0, this.b, 32, c.length);
        h(this.b, 0);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        d();
        for (int i2 = 0; i2 < 8; i2++) {
            b(this.e[i2], bArr, (i2 * 8) + i);
        }
        reset();
        return getDigestSize();
    }

    public final void e() {
        int i = 0;
        for (int length = this.d.length - 1; length >= 0; length--) {
            short[] sArr = this.d;
            int i2 = (sArr[length] & 255) + s[length] + i;
            i = i2 >>> 8;
            sArr[length] = (short) (i2 & 255);
        }
    }

    public final int f(int i) {
        return ((long) i) >= 256 ? i ^ 285 : i;
    }

    public final long g(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return (((((((i2 << 48) ^ (i << 56)) ^ (i3 << 40)) ^ (i4 << 32)) ^ (i5 << 24)) ^ (i6 << 16)) ^ (i7 << 8)) ^ i8;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "Whirlpool";
    }

    @Override // org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return 64;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 64;
    }

    public final void h(byte[] bArr, int i) {
        for (int i2 = 0; i2 < this.i.length; i2++) {
            this.h[i2] = a(this.b, i2 * 8);
        }
        processBlock();
        this.c = 0;
        Arrays.fill(this.b, (byte) 0);
    }

    public void processBlock() {
        long[] jArr;
        for (int i = 0; i < 8; i++) {
            long[] jArr2 = this.i;
            long j2 = this.h[i];
            long[] jArr3 = this.f;
            long j3 = this.e[i];
            jArr3[i] = j3;
            jArr2[i] = j2 ^ j3;
        }
        int i2 = 1;
        while (i2 <= 10) {
            int i3 = 0;
            while (i3 < 8) {
                long[] jArr4 = this.g;
                jArr4[i3] = 0;
                long j4 = jArr4[i3];
                long[] jArr5 = k;
                long[] jArr6 = this.f;
                jArr4[i3] = jArr5[((int) (jArr6[(i3 + 0) & 7] >>> 56)) & 255] ^ j4;
                jArr4[i3] = jArr4[i3] ^ l[((int) (jArr6[(i3 - 1) & 7] >>> 48)) & 255];
                jArr4[i3] = jArr4[i3] ^ m[((int) (jArr6[(i3 - 2) & 7] >>> 40)) & 255];
                jArr4[i3] = jArr4[i3] ^ n[((int) (jArr6[(i3 - 3) & 7] >>> 32)) & 255];
                jArr4[i3] = jArr4[i3] ^ o[((int) (jArr6[(i3 - 4) & 7] >>> 24)) & 255];
                jArr4[i3] = jArr4[i3] ^ p[((int) (jArr6[(i3 - 5) & 7] >>> 16)) & 255];
                jArr4[i3] = jArr4[i3] ^ q[((int) (jArr6[(i3 - 6) & 7] >>> 8)) & 255];
                jArr4[i3] = jArr4[i3] ^ r[((int) jArr6[(i3 - 7) & 7]) & 255];
                i3++;
                i2 = i2;
            }
            int i4 = i2;
            long[] jArr7 = this.g;
            long[] jArr8 = this.f;
            System.arraycopy(jArr7, 0, jArr8, 0, jArr8.length);
            long[] jArr9 = this.f;
            jArr9[0] = jArr9[0] ^ this.f14652a[i4];
            int i5 = 0;
            while (true) {
                jArr = this.g;
                if (i5 < 8) {
                    jArr[i5] = this.f[i5];
                    long j5 = jArr[i5];
                    long[] jArr10 = k;
                    long[] jArr11 = this.i;
                    jArr[i5] = j5 ^ jArr10[((int) (jArr11[(i5 + 0) & 7] >>> 56)) & 255];
                    jArr[i5] = jArr[i5] ^ l[((int) (jArr11[(i5 - 1) & 7] >>> 48)) & 255];
                    jArr[i5] = jArr[i5] ^ m[((int) (jArr11[(i5 - 2) & 7] >>> 40)) & 255];
                    jArr[i5] = jArr[i5] ^ n[((int) (jArr11[(i5 - 3) & 7] >>> 32)) & 255];
                    jArr[i5] = jArr[i5] ^ o[((int) (jArr11[(i5 - 4) & 7] >>> 24)) & 255];
                    jArr[i5] = jArr[i5] ^ p[((int) (jArr11[(i5 - 5) & 7] >>> 16)) & 255];
                    jArr[i5] = jArr[i5] ^ q[((int) (jArr11[(i5 - 6) & 7] >>> 8)) & 255];
                    jArr[i5] = jArr[i5] ^ r[((int) jArr11[(i5 - 7) & 7]) & 255];
                    i5++;
                }
            }
            long[] jArr12 = this.i;
            System.arraycopy(jArr, 0, jArr12, 0, jArr12.length);
            i2 = i4 + 1;
        }
        for (int i6 = 0; i6 < 8; i6++) {
            long[] jArr13 = this.e;
            jArr13[i6] = jArr13[i6] ^ (this.i[i6] ^ this.h[i6]);
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        this.c = 0;
        Arrays.fill(this.d, (short) 0);
        Arrays.fill(this.b, (byte) 0);
        Arrays.fill(this.e, 0L);
        Arrays.fill(this.f, 0L);
        Arrays.fill(this.g, 0L);
        Arrays.fill(this.h, 0L);
        Arrays.fill(this.i, 0L);
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        WhirlpoolDigest whirlpoolDigest = (WhirlpoolDigest) memoable;
        long[] jArr = whirlpoolDigest.f14652a;
        long[] jArr2 = this.f14652a;
        System.arraycopy(jArr, 0, jArr2, 0, jArr2.length);
        byte[] bArr = whirlpoolDigest.b;
        byte[] bArr2 = this.b;
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        this.c = whirlpoolDigest.c;
        short[] sArr = whirlpoolDigest.d;
        short[] sArr2 = this.d;
        System.arraycopy(sArr, 0, sArr2, 0, sArr2.length);
        long[] jArr3 = whirlpoolDigest.e;
        long[] jArr4 = this.e;
        System.arraycopy(jArr3, 0, jArr4, 0, jArr4.length);
        long[] jArr5 = whirlpoolDigest.f;
        long[] jArr6 = this.f;
        System.arraycopy(jArr5, 0, jArr6, 0, jArr6.length);
        long[] jArr7 = whirlpoolDigest.g;
        long[] jArr8 = this.g;
        System.arraycopy(jArr7, 0, jArr8, 0, jArr8.length);
        long[] jArr9 = whirlpoolDigest.h;
        long[] jArr10 = this.h;
        System.arraycopy(jArr9, 0, jArr10, 0, jArr10.length);
        long[] jArr11 = whirlpoolDigest.i;
        long[] jArr12 = this.i;
        System.arraycopy(jArr11, 0, jArr12, 0, jArr12.length);
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        byte[] bArr = this.b;
        int i = this.c;
        bArr[i] = b;
        int i2 = i + 1;
        this.c = i2;
        if (i2 == bArr.length) {
            h(bArr, 0);
        }
        e();
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        while (i2 > 0) {
            update(bArr[i]);
            i++;
            i2--;
        }
    }
}

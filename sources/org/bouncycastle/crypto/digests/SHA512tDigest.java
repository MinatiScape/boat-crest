package org.bouncycastle.crypto.digests;

import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.MemoableResetException;
import org.bouncycastle.util.Pack;
/* loaded from: classes12.dex */
public class SHA512tDigest extends LongDigest {
    public int h;
    public long i;
    public long j;
    public long k;
    public long l;
    public long m;
    public long n;
    public long o;
    public long p;

    public SHA512tDigest(int i) {
        if (i >= 512) {
            throw new IllegalArgumentException("bitLength cannot be >= 512");
        }
        if (i % 8 != 0) {
            throw new IllegalArgumentException("bitLength needs to be a multiple of 8");
        }
        if (i == 384) {
            throw new IllegalArgumentException("bitLength cannot be 384 use SHA384 instead");
        }
        int i2 = i / 8;
        this.h = i2;
        k(i2 * 8);
        reset();
    }

    public SHA512tDigest(SHA512tDigest sHA512tDigest) {
        super(sHA512tDigest);
        this.h = sHA512tDigest.h;
        reset(sHA512tDigest);
    }

    public SHA512tDigest(byte[] bArr) {
        this(j(bArr));
        restoreState(bArr);
    }

    public static void h(int i, byte[] bArr, int i2, int i3) {
        int min = Math.min(4, i3);
        while (true) {
            min--;
            if (min < 0) {
                return;
            }
            bArr[i2 + min] = (byte) (i >>> ((3 - min) * 8));
        }
    }

    public static void i(long j, byte[] bArr, int i, int i2) {
        if (i2 > 0) {
            h((int) (j >>> 32), bArr, i, i2);
            if (i2 > 4) {
                h((int) (j & 4294967295L), bArr, i + 4, i2 - 4);
            }
        }
    }

    public static int j(byte[] bArr) {
        return Pack.bigEndianToInt(bArr, bArr.length - 4);
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new SHA512tDigest(this);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        finish();
        i(this.H1, bArr, i, this.h);
        i(this.H2, bArr, i + 8, this.h - 8);
        i(this.H3, bArr, i + 16, this.h - 16);
        i(this.H4, bArr, i + 24, this.h - 24);
        i(this.H5, bArr, i + 32, this.h - 32);
        i(this.H6, bArr, i + 40, this.h - 40);
        i(this.H7, bArr, i + 48, this.h - 48);
        i(this.H8, bArr, i + 56, this.h - 56);
        reset();
        return this.h;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "SHA-512/" + Integer.toString(this.h * 8);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return this.h;
    }

    @Override // org.bouncycastle.crypto.digests.EncodableDigest
    public byte[] getEncodedState() {
        int encodedStateSize = getEncodedStateSize();
        byte[] bArr = new byte[encodedStateSize + 4];
        populateState(bArr);
        Pack.intToBigEndian(this.h * 8, bArr, encodedStateSize);
        return bArr;
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x007f, code lost:
        if (r4 > 10) goto L4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void k(int r4) {
        /*
            r3 = this;
            r0 = -3482333909917012819(0xcfac43c256196cad, double:-6.392239886847908E75)
            r3.H1 = r0
            r0 = 2216346199247487646(0x1ec20b20216f029e, double:1.604250256667292E-160)
            r3.H2 = r0
            r0 = -7364697282686394994(0x99cb56d75b315d8e, double:-2.0106609494103695E-184)
            r3.H3 = r0
            r0 = 65953792586715988(0xea509ffab89354, double:2.9978976005667514E-304)
            r3.H4 = r0
            r0 = -816286391624063116(0xf4abf7da08432774, double:-1.0252515228978657E254)
            r3.H5 = r0
            r0 = 4512832404995164602(0x3ea0cd298e9bc9ba, double:5.007211971427005E-7)
            r3.H6 = r0
            r0 = -5033199132376557362(0xba267c0e5ee418ce, double:-1.418977391716189E-28)
            r3.H7 = r0
            r0 = -124578254951840548(0xfe4568bcb6db84dc, double:-1.7921927020935902E300)
            r3.H8 = r0
            r0 = 83
            r3.update(r0)
            r0 = 72
            r3.update(r0)
            r0 = 65
            r3.update(r0)
            r0 = 45
            r3.update(r0)
            r0 = 53
            r3.update(r0)
            r0 = 49
            r3.update(r0)
            r0 = 50
            r3.update(r0)
            r0 = 47
            r3.update(r0)
            r0 = 100
            r1 = 10
            if (r4 <= r0) goto L7f
            int r2 = r4 / 100
            int r2 = r2 + 48
            byte r2 = (byte) r2
            r3.update(r2)
            int r4 = r4 % r0
        L6f:
            int r0 = r4 / 10
            int r0 = r0 + 48
            byte r0 = (byte) r0
            r3.update(r0)
            int r4 = r4 % r1
        L78:
            int r4 = r4 + 48
            byte r4 = (byte) r4
            r3.update(r4)
            goto L82
        L7f:
            if (r4 <= r1) goto L78
            goto L6f
        L82:
            r3.finish()
            long r0 = r3.H1
            r3.i = r0
            long r0 = r3.H2
            r3.j = r0
            long r0 = r3.H3
            r3.k = r0
            long r0 = r3.H4
            r3.l = r0
            long r0 = r3.H5
            r3.m = r0
            long r0 = r3.H6
            r3.n = r0
            long r0 = r3.H7
            r3.o = r0
            long r0 = r3.H8
            r3.p = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.digests.SHA512tDigest.k(int):void");
    }

    @Override // org.bouncycastle.crypto.digests.LongDigest, org.bouncycastle.crypto.Digest
    public void reset() {
        super.reset();
        this.H1 = this.i;
        this.H2 = this.j;
        this.H3 = this.k;
        this.H4 = this.l;
        this.H5 = this.m;
        this.H6 = this.n;
        this.H7 = this.o;
        this.H8 = this.p;
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        SHA512tDigest sHA512tDigest = (SHA512tDigest) memoable;
        if (this.h != sHA512tDigest.h) {
            throw new MemoableResetException("digestLength inappropriate in other");
        }
        super.copyIn(sHA512tDigest);
        this.i = sHA512tDigest.i;
        this.j = sHA512tDigest.j;
        this.k = sHA512tDigest.k;
        this.l = sHA512tDigest.l;
        this.m = sHA512tDigest.m;
        this.n = sHA512tDigest.n;
        this.o = sHA512tDigest.o;
        this.p = sHA512tDigest.p;
    }
}

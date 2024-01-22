package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;
/* loaded from: classes12.dex */
public abstract class LongDigest implements ExtendedDigest, Memoable, EncodableDigest {
    public static final long[] g = {4794697086780616226L, 8158064640168781261L, -5349999486874862801L, -1606136188198331460L, 4131703408338449720L, 6480981068601479193L, -7908458776815382629L, -6116909921290321640L, -2880145864133508542L, 1334009975649890238L, 2608012711638119052L, 6128411473006802146L, 8268148722764581231L, -9160688886553864527L, -7215885187991268811L, -4495734319001033068L, -1973867731355612462L, -1171420211273849373L, 1135362057144423861L, 2597628984639134821L, 3308224258029322869L, 5365058923640841347L, 6679025012923562964L, 8573033837759648693L, -7476448914759557205L, -6327057829258317296L, -5763719355590565569L, -4658551843659510044L, -4116276920077217854L, -3051310485924567259L, 489312712824947311L, 1452737877330783856L, 2861767655752347644L, 3322285676063803686L, 5560940570517711597L, 5996557281743188959L, 7280758554555802590L, 8532644243296465576L, -9096487096722542874L, -7894198246740708037L, -6719396339535248540L, -6333637450476146687L, -4446306890439682159L, -4076793802049405392L, -3345356375505022440L, -2983346525034927856L, -860691631967231958L, 1182934255886127544L, 1847814050463011016L, 2177327727835720531L, 2830643537854262169L, 3796741975233480872L, 4115178125766777443L, 5681478168544905931L, 6601373596472566643L, 7507060721942968483L, 8399075790359081724L, 8693463985226723168L, -8878714635349349518L, -8302665154208450068L, -8016688836872298968L, -6606660893046293015L, -4685533653050689259L, -4147400797238176981L, -3880063495543823972L, -3348786107499101689L, -1523767162380948706L, -757361751448694408L, 500013540394364858L, 748580250866718886L, 1242879168328830382L, 1977374033974150939L, 2944078676154940804L, 3659926193048069267L, 4368137639120453308L, 4836135668995329356L, 5532061633213252278L, 6448918945643986474L, 6902733635092675308L, 7801388544844847127L};
    public long H1;
    public long H2;
    public long H3;
    public long H4;
    public long H5;
    public long H6;
    public long H7;
    public long H8;

    /* renamed from: a  reason: collision with root package name */
    public byte[] f14640a;
    public int b;
    public long c;
    public long d;
    public long[] e;
    public int f;

    public LongDigest() {
        this.f14640a = new byte[8];
        this.e = new long[80];
        this.b = 0;
        reset();
    }

    public LongDigest(LongDigest longDigest) {
        this.f14640a = new byte[8];
        this.e = new long[80];
        copyIn(longDigest);
    }

    public final long a(long j, long j2, long j3) {
        return ((~j) & j3) ^ (j2 & j);
    }

    public final long b(long j, long j2, long j3) {
        return ((j & j3) ^ (j & j2)) ^ (j2 & j3);
    }

    public final long c(long j) {
        return (j >>> 7) ^ (((j << 63) | (j >>> 1)) ^ ((j << 56) | (j >>> 8)));
    }

    public void copyIn(LongDigest longDigest) {
        byte[] bArr = longDigest.f14640a;
        System.arraycopy(bArr, 0, this.f14640a, 0, bArr.length);
        this.b = longDigest.b;
        this.c = longDigest.c;
        this.d = longDigest.d;
        this.H1 = longDigest.H1;
        this.H2 = longDigest.H2;
        this.H3 = longDigest.H3;
        this.H4 = longDigest.H4;
        this.H5 = longDigest.H5;
        this.H6 = longDigest.H6;
        this.H7 = longDigest.H7;
        this.H8 = longDigest.H8;
        long[] jArr = longDigest.e;
        System.arraycopy(jArr, 0, this.e, 0, jArr.length);
        this.f = longDigest.f;
    }

    public final long d(long j) {
        return (j >>> 6) ^ (((j << 45) | (j >>> 19)) ^ ((j << 3) | (j >>> 61)));
    }

    public final long e(long j) {
        return ((j >>> 39) | (j << 25)) ^ (((j << 36) | (j >>> 28)) ^ ((j << 30) | (j >>> 34)));
    }

    public final long f(long j) {
        return ((j >>> 41) | (j << 23)) ^ (((j << 50) | (j >>> 14)) ^ ((j << 46) | (j >>> 18)));
    }

    public void finish() {
        g();
        long j = this.c << 3;
        long j2 = this.d;
        byte b = Byte.MIN_VALUE;
        while (true) {
            update(b);
            if (this.b == 0) {
                processLength(j, j2);
                processBlock();
                return;
            }
            b = 0;
        }
    }

    public final void g() {
        long j = this.c;
        if (j > 2305843009213693951L) {
            this.d += j >>> 61;
            this.c = j & 2305843009213693951L;
        }
    }

    @Override // org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return 128;
    }

    public int getEncodedStateSize() {
        return (this.f * 8) + 96;
    }

    public void populateState(byte[] bArr) {
        System.arraycopy(this.f14640a, 0, bArr, 0, this.b);
        Pack.intToBigEndian(this.b, bArr, 8);
        Pack.longToBigEndian(this.c, bArr, 12);
        Pack.longToBigEndian(this.d, bArr, 20);
        Pack.longToBigEndian(this.H1, bArr, 28);
        Pack.longToBigEndian(this.H2, bArr, 36);
        Pack.longToBigEndian(this.H3, bArr, 44);
        Pack.longToBigEndian(this.H4, bArr, 52);
        Pack.longToBigEndian(this.H5, bArr, 60);
        Pack.longToBigEndian(this.H6, bArr, 68);
        Pack.longToBigEndian(this.H7, bArr, 76);
        Pack.longToBigEndian(this.H8, bArr, 84);
        Pack.intToBigEndian(this.f, bArr, 92);
        for (int i = 0; i < this.f; i++) {
            Pack.longToBigEndian(this.e[i], bArr, (i * 8) + 96);
        }
    }

    public void processBlock() {
        g();
        for (int i = 16; i <= 79; i++) {
            long[] jArr = this.e;
            long d = d(jArr[i - 2]);
            long[] jArr2 = this.e;
            jArr[i] = d + jArr2[i - 7] + c(jArr2[i - 15]) + this.e[i - 16];
        }
        long j = this.H1;
        long j2 = this.H2;
        long j3 = this.H3;
        long j4 = this.H4;
        long j5 = this.H5;
        long j6 = this.H6;
        long j7 = this.H7;
        long j8 = j6;
        long j9 = j4;
        int i2 = 0;
        long j10 = j2;
        long j11 = j3;
        long j12 = j5;
        int i3 = 0;
        long j13 = this.H8;
        long j14 = j;
        long j15 = j7;
        while (i3 < 10) {
            long j16 = j12;
            long[] jArr3 = g;
            int i4 = i2 + 1;
            long f = j13 + f(j12) + a(j12, j8, j15) + jArr3[i2] + this.e[i2];
            long j17 = j9 + f;
            long e = f + e(j14) + b(j14, j10, j11);
            int i5 = i4 + 1;
            long f2 = j15 + f(j17) + a(j17, j16, j8) + jArr3[i4] + this.e[i4];
            long j18 = j11 + f2;
            long e2 = f2 + e(e) + b(e, j14, j10);
            int i6 = i5 + 1;
            long f3 = j8 + f(j18) + a(j18, j17, j16) + jArr3[i5] + this.e[i5];
            long j19 = j10 + f3;
            long e3 = f3 + e(e2) + b(e2, e, j14);
            int i7 = i6 + 1;
            long f4 = j16 + f(j19) + a(j19, j18, j17) + jArr3[i6] + this.e[i6];
            long j20 = j14 + f4;
            long e4 = f4 + e(e3) + b(e3, e2, e);
            int i8 = i7 + 1;
            long f5 = j17 + f(j20) + a(j20, j19, j18) + jArr3[i7] + this.e[i7];
            long j21 = e + f5;
            long e5 = f5 + e(e4) + b(e4, e3, e2);
            int i9 = i8 + 1;
            long f6 = j18 + f(j21) + a(j21, j20, j19) + jArr3[i8] + this.e[i8];
            long j22 = e2 + f6;
            long e6 = f6 + e(e5) + b(e5, e4, e3);
            j15 = j22;
            int i10 = i9 + 1;
            long f7 = j19 + f(j22) + a(j22, j21, j20) + jArr3[i9] + this.e[i9];
            long j23 = e3 + f7;
            j8 = j23;
            j10 = f7 + e(e6) + b(e6, e5, e4);
            long f8 = j20 + f(j23) + a(j23, j15, j21) + jArr3[i10] + this.e[i10];
            long e7 = f8 + e(j10) + b(j10, e6, e5);
            i3++;
            j12 = e4 + f8;
            j11 = e6;
            j13 = j21;
            j9 = e5;
            i2 = i10 + 1;
            j14 = e7;
        }
        this.H1 += j14;
        this.H2 += j10;
        this.H3 += j11;
        this.H4 += j9;
        this.H5 += j12;
        this.H6 += j8;
        this.H7 += j15;
        this.H8 += j13;
        this.f = 0;
        for (int i11 = 0; i11 < 16; i11++) {
            this.e[i11] = 0;
        }
    }

    public void processLength(long j, long j2) {
        if (this.f > 14) {
            processBlock();
        }
        long[] jArr = this.e;
        jArr[14] = j2;
        jArr[15] = j;
    }

    public void processWord(byte[] bArr, int i) {
        this.e[this.f] = Pack.bigEndianToLong(bArr, i);
        int i2 = this.f + 1;
        this.f = i2;
        if (i2 == 16) {
            processBlock();
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        this.c = 0L;
        this.d = 0L;
        int i = 0;
        this.b = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.f14640a;
            if (i2 >= bArr.length) {
                break;
            }
            bArr[i2] = 0;
            i2++;
        }
        this.f = 0;
        while (true) {
            long[] jArr = this.e;
            if (i == jArr.length) {
                return;
            }
            jArr[i] = 0;
            i++;
        }
    }

    public void restoreState(byte[] bArr) {
        int bigEndianToInt = Pack.bigEndianToInt(bArr, 8);
        this.b = bigEndianToInt;
        System.arraycopy(bArr, 0, this.f14640a, 0, bigEndianToInt);
        this.c = Pack.bigEndianToLong(bArr, 12);
        this.d = Pack.bigEndianToLong(bArr, 20);
        this.H1 = Pack.bigEndianToLong(bArr, 28);
        this.H2 = Pack.bigEndianToLong(bArr, 36);
        this.H3 = Pack.bigEndianToLong(bArr, 44);
        this.H4 = Pack.bigEndianToLong(bArr, 52);
        this.H5 = Pack.bigEndianToLong(bArr, 60);
        this.H6 = Pack.bigEndianToLong(bArr, 68);
        this.H7 = Pack.bigEndianToLong(bArr, 76);
        this.H8 = Pack.bigEndianToLong(bArr, 84);
        this.f = Pack.bigEndianToInt(bArr, 92);
        for (int i = 0; i < this.f; i++) {
            this.e[i] = Pack.bigEndianToLong(bArr, (i * 8) + 96);
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        byte[] bArr = this.f14640a;
        int i = this.b;
        int i2 = i + 1;
        this.b = i2;
        bArr[i] = b;
        if (i2 == bArr.length) {
            processWord(bArr, 0);
            this.b = 0;
        }
        this.c++;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        while (this.b != 0 && i2 > 0) {
            update(bArr[i]);
            i++;
            i2--;
        }
        while (i2 > this.f14640a.length) {
            processWord(bArr, i);
            byte[] bArr2 = this.f14640a;
            i += bArr2.length;
            i2 -= bArr2.length;
            this.c += bArr2.length;
        }
        while (i2 > 0) {
            update(bArr[i]);
            i++;
            i2--;
        }
    }
}

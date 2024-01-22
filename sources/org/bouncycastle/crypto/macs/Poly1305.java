package org.bouncycastle.crypto.macs;

import com.jieli.jl_bt_ota.constant.ErrorCode;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Pack;
/* loaded from: classes12.dex */
public class Poly1305 implements Mac {

    /* renamed from: a  reason: collision with root package name */
    public final BlockCipher f14762a;
    public final byte[] b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public final byte[] p;
    public int q;
    public int r;
    public int s;
    public int t;
    public int u;
    public int v;

    public Poly1305() {
        this.b = new byte[1];
        this.p = new byte[16];
        this.q = 0;
        this.f14762a = null;
    }

    public Poly1305(BlockCipher blockCipher) {
        this.b = new byte[1];
        this.p = new byte[16];
        this.q = 0;
        if (blockCipher.getBlockSize() != 16) {
            throw new IllegalArgumentException("Poly1305 requires a 128 bit block cipher.");
        }
        this.f14762a = blockCipher;
    }

    public static final long a(int i, int i2) {
        return (i & 4294967295L) * i2;
    }

    public final void b() {
        int i = this.q;
        if (i < 16) {
            this.p[i] = 1;
            for (int i2 = i + 1; i2 < 16; i2++) {
                this.p[i2] = 0;
            }
        }
        long littleEndianToInt = Pack.littleEndianToInt(this.p, 0) & 4294967295L;
        long littleEndianToInt2 = Pack.littleEndianToInt(this.p, 4) & 4294967295L;
        long littleEndianToInt3 = Pack.littleEndianToInt(this.p, 8) & 4294967295L;
        long littleEndianToInt4 = 4294967295L & Pack.littleEndianToInt(this.p, 12);
        int i3 = (int) (this.r + (littleEndianToInt & 67108863));
        this.r = i3;
        this.s = (int) (this.s + ((((littleEndianToInt2 << 32) | littleEndianToInt) >>> 26) & 67108863));
        this.t = (int) (this.t + (((littleEndianToInt2 | (littleEndianToInt3 << 32)) >>> 20) & 67108863));
        this.u = (int) (this.u + ((((littleEndianToInt4 << 32) | littleEndianToInt3) >>> 14) & 67108863));
        int i4 = (int) (this.v + (littleEndianToInt4 >>> 8));
        this.v = i4;
        if (this.q == 16) {
            this.v = i4 + 16777216;
        }
        long a2 = a(i3, this.c) + a(this.s, this.k) + a(this.t, this.j) + a(this.u, this.i) + a(this.v, this.h);
        long a3 = a(this.r, this.d) + a(this.s, this.c) + a(this.t, this.k) + a(this.u, this.j) + a(this.v, this.i);
        long a4 = a(this.r, this.e) + a(this.s, this.d) + a(this.t, this.c) + a(this.u, this.k) + a(this.v, this.j);
        long a5 = a(this.r, this.f) + a(this.s, this.e) + a(this.t, this.d) + a(this.u, this.c) + a(this.v, this.k);
        long a6 = a(this.r, this.g) + a(this.s, this.f) + a(this.t, this.e) + a(this.u, this.d) + a(this.v, this.c);
        int i5 = ((int) a2) & 67108863;
        this.r = i5;
        long j = a3 + (a2 >>> 26);
        int i6 = ((int) j) & 67108863;
        this.s = i6;
        long j2 = a4 + (j >>> 26);
        this.t = ((int) j2) & 67108863;
        long j3 = a5 + (j2 >>> 26);
        this.u = ((int) j3) & 67108863;
        long j4 = a6 + (j3 >>> 26);
        this.v = ((int) j4) & 67108863;
        int i7 = i5 + (((int) (j4 >>> 26)) * 5);
        this.r = i7;
        this.s = i6 + (i7 >>> 26);
        this.r = i7 & 67108863;
    }

    public final void c(byte[] bArr, byte[] bArr2) {
        if (bArr.length != 32) {
            throw new IllegalArgumentException("Poly1305 key must be 256 bits.");
        }
        int i = 16;
        if (this.f14762a != null && (bArr2 == null || bArr2.length != 16)) {
            throw new IllegalArgumentException("Poly1305 requires a 128 bit IV.");
        }
        int littleEndianToInt = Pack.littleEndianToInt(bArr, 0);
        int littleEndianToInt2 = Pack.littleEndianToInt(bArr, 4);
        int littleEndianToInt3 = Pack.littleEndianToInt(bArr, 8);
        int littleEndianToInt4 = Pack.littleEndianToInt(bArr, 12);
        this.c = 67108863 & littleEndianToInt;
        int i2 = ((littleEndianToInt >>> 26) | (littleEndianToInt2 << 6)) & 67108611;
        this.d = i2;
        int i3 = ((littleEndianToInt2 >>> 20) | (littleEndianToInt3 << 12)) & 67092735;
        this.e = i3;
        int i4 = ((littleEndianToInt3 >>> 14) | (littleEndianToInt4 << 18)) & 66076671;
        this.f = i4;
        int i5 = (littleEndianToInt4 >>> 8) & ErrorCode.ERR_UNKNOWN;
        this.g = i5;
        this.h = i2 * 5;
        this.i = i3 * 5;
        this.j = i4 * 5;
        this.k = i5 * 5;
        BlockCipher blockCipher = this.f14762a;
        if (blockCipher != null) {
            byte[] bArr3 = new byte[16];
            blockCipher.init(true, new KeyParameter(bArr, 16, 16));
            this.f14762a.processBlock(bArr2, 0, bArr3, 0);
            i = 0;
            bArr = bArr3;
        }
        this.l = Pack.littleEndianToInt(bArr, i + 0);
        this.m = Pack.littleEndianToInt(bArr, i + 4);
        this.n = Pack.littleEndianToInt(bArr, i + 8);
        this.o = Pack.littleEndianToInt(bArr, i + 12);
    }

    @Override // org.bouncycastle.crypto.Mac
    public int doFinal(byte[] bArr, int i) throws DataLengthException, IllegalStateException {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        if (i + 16 <= bArr.length) {
            if (this.q > 0) {
                b();
            }
            int i7 = this.s;
            int i8 = this.r;
            int i9 = i7 + (i8 >>> 26);
            this.s = i9;
            int i10 = i8 & 67108863;
            this.r = i10;
            int i11 = this.t + (i9 >>> 26);
            this.t = i11;
            int i12 = i9 & 67108863;
            this.s = i12;
            int i13 = this.u + (i11 >>> 26);
            this.u = i13;
            int i14 = i11 & 67108863;
            this.t = i14;
            int i15 = this.v + (i13 >>> 26);
            this.v = i15;
            int i16 = i13 & 67108863;
            this.u = i16;
            int i17 = i10 + ((i15 >>> 26) * 5);
            this.r = i17;
            int i18 = i15 & 67108863;
            this.v = i18;
            int i19 = i12 + (i17 >>> 26);
            this.s = i19;
            int i20 = i17 & 67108863;
            this.r = i20;
            int i21 = i20 + 5;
            int i22 = (i21 >>> 26) + i19;
            int i23 = (i22 >>> 26) + i14;
            int i24 = (i23 >>> 26) + i16;
            int i25 = 67108863 & i24;
            int i26 = ((i24 >>> 26) + i18) - 67108864;
            int i27 = (i26 >>> 31) - 1;
            int i28 = ~i27;
            this.r = (i20 & i28) | (i21 & 67108863 & i27);
            this.s = (i19 & i28) | (i22 & 67108863 & i27);
            this.t = (i14 & i28) | (i23 & 67108863 & i27);
            this.u = (i25 & i27) | (i16 & i28);
            this.v = (i18 & i28) | (i26 & i27);
            long j = ((i2 | (i3 << 26)) & 4294967295L) + (this.l & 4294967295L);
            Pack.intToLittleEndian((int) j, bArr, i);
            long j2 = (((i3 >>> 6) | (i4 << 20)) & 4294967295L) + (this.m & 4294967295L) + (j >>> 32);
            Pack.intToLittleEndian((int) j2, bArr, i + 4);
            long j3 = (((i4 >>> 12) | (i5 << 14)) & 4294967295L) + (this.n & 4294967295L) + (j2 >>> 32);
            Pack.intToLittleEndian((int) j3, bArr, i + 8);
            Pack.intToLittleEndian((int) ((((i5 >>> 18) | (i6 << 8)) & 4294967295L) + (4294967295L & this.o) + (j3 >>> 32)), bArr, i + 12);
            reset();
            return 16;
        }
        throw new OutputLengthException("Output buffer is too short.");
    }

    @Override // org.bouncycastle.crypto.Mac
    public String getAlgorithmName() {
        if (this.f14762a == null) {
            return "Poly1305";
        }
        return "Poly1305-" + this.f14762a.getAlgorithmName();
    }

    @Override // org.bouncycastle.crypto.Mac
    public int getMacSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        byte[] bArr;
        if (this.f14762a == null) {
            bArr = null;
        } else if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("Poly1305 requires an IV when used with a block cipher.");
        } else {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            bArr = parametersWithIV.getIV();
            cipherParameters = parametersWithIV.getParameters();
        }
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("Poly1305 requires a key.");
        }
        c(((KeyParameter) cipherParameters).getKey(), bArr);
        reset();
    }

    @Override // org.bouncycastle.crypto.Mac
    public void reset() {
        this.q = 0;
        this.v = 0;
        this.u = 0;
        this.t = 0;
        this.s = 0;
        this.r = 0;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte b) throws IllegalStateException {
        byte[] bArr = this.b;
        bArr[0] = b;
        update(bArr, 0, 1);
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte[] bArr, int i, int i2) throws DataLengthException, IllegalStateException {
        int i3 = 0;
        while (i2 > i3) {
            if (this.q == 16) {
                b();
                this.q = 0;
            }
            int min = Math.min(i2 - i3, 16 - this.q);
            System.arraycopy(bArr, i3 + i, this.p, this.q, min);
            i3 += min;
            this.q += min;
        }
    }
}

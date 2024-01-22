package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.modes.gcm.BasicGCMExponentiator;
import org.bouncycastle.crypto.modes.gcm.GCMExponentiator;
import org.bouncycastle.crypto.modes.gcm.GCMMultiplier;
import org.bouncycastle.crypto.modes.gcm.GCMUtil;
import org.bouncycastle.crypto.modes.gcm.Tables4kGCMMultiplier;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;
/* loaded from: classes13.dex */
public class GCMBlockCipher implements AEADBlockCipher {

    /* renamed from: a  reason: collision with root package name */
    public BlockCipher f14771a;
    public GCMMultiplier b;
    public GCMExponentiator c;
    public boolean d;
    public boolean e;
    public int f;
    public byte[] g;
    public byte[] h;
    public byte[] i;
    public byte[] j;
    public byte[] k;
    public byte[] l;
    public byte[] m;
    public byte[] n;
    public byte[] o;
    public byte[] p;
    public byte[] q;
    public int r;
    public int s;
    public long t;
    public byte[] u;
    public int v;
    public long w;
    public long x;

    public GCMBlockCipher(BlockCipher blockCipher) {
        this(blockCipher, null);
    }

    public GCMBlockCipher(BlockCipher blockCipher, GCMMultiplier gCMMultiplier) {
        if (blockCipher.getBlockSize() != 16) {
            throw new IllegalArgumentException("cipher required with a block size of 16.");
        }
        gCMMultiplier = gCMMultiplier == null ? new Tables4kGCMMultiplier() : gCMMultiplier;
        this.f14771a = blockCipher;
        this.b = gCMMultiplier;
    }

    public final void a() {
        if (this.e) {
            return;
        }
        if (!this.d) {
            throw new IllegalStateException("GCM cipher needs to be initialised");
        }
        throw new IllegalStateException("GCM cipher cannot be reused for encryption");
    }

    public final void b(byte[] bArr, byte[] bArr2, int i) {
        for (int i2 = 0; i2 < i; i2 += 16) {
            e(bArr, bArr2, i2, Math.min(i - i2, 16));
        }
    }

    public final void c(byte[] bArr, byte[] bArr2) {
        GCMUtil.xor(bArr, bArr2);
        this.b.multiplyH(bArr);
    }

    public final void d(byte[] bArr, byte[] bArr2, int i) {
        GCMUtil.xor(bArr, bArr2, i);
        this.b.multiplyH(bArr);
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public int doFinal(byte[] bArr, int i) throws IllegalStateException, InvalidCipherTextException {
        a();
        if (this.t == 0) {
            g();
        }
        int i2 = this.s;
        if (!this.d) {
            int i3 = this.f;
            if (i2 < i3) {
                throw new InvalidCipherTextException("data too short");
            }
            i2 -= i3;
            if (bArr.length - i < i2) {
                throw new OutputLengthException("Output buffer too short");
            }
        } else if (bArr.length - i < this.f + i2) {
            throw new OutputLengthException("Output buffer too short");
        }
        if (i2 > 0) {
            i(this.l, 0, i2, bArr, i);
        }
        long j = this.w;
        int i4 = this.v;
        long j2 = j + i4;
        this.w = j2;
        if (j2 > this.x) {
            if (i4 > 0) {
                e(this.o, this.u, 0, i4);
            }
            if (this.x > 0) {
                GCMUtil.xor(this.o, this.p);
            }
            long j3 = ((this.t * 8) + 127) >>> 7;
            byte[] bArr2 = new byte[16];
            if (this.c == null) {
                BasicGCMExponentiator basicGCMExponentiator = new BasicGCMExponentiator();
                this.c = basicGCMExponentiator;
                basicGCMExponentiator.init(this.j);
            }
            this.c.exponentiateX(j3, bArr2);
            GCMUtil.multiply(this.o, bArr2);
            GCMUtil.xor(this.n, this.o);
        }
        byte[] bArr3 = new byte[16];
        Pack.longToBigEndian(this.w * 8, bArr3, 0);
        Pack.longToBigEndian(this.t * 8, bArr3, 8);
        c(this.n, bArr3);
        byte[] bArr4 = new byte[16];
        this.f14771a.processBlock(this.k, 0, bArr4, 0);
        GCMUtil.xor(bArr4, this.n);
        int i5 = this.f;
        byte[] bArr5 = new byte[i5];
        this.m = bArr5;
        System.arraycopy(bArr4, 0, bArr5, 0, i5);
        if (this.d) {
            System.arraycopy(this.m, 0, bArr, i + this.s, this.f);
            i2 += this.f;
        } else {
            int i6 = this.f;
            byte[] bArr6 = new byte[i6];
            System.arraycopy(this.l, i2, bArr6, 0, i6);
            if (!Arrays.constantTimeAreEqual(this.m, bArr6)) {
                throw new InvalidCipherTextException("mac check in GCM failed");
            }
        }
        j(false);
        return i2;
    }

    public final void e(byte[] bArr, byte[] bArr2, int i, int i2) {
        GCMUtil.xor(bArr, bArr2, i, i2);
        this.b.multiplyH(bArr);
    }

    public final void f(byte[] bArr) {
        int i = this.r;
        if (i == 0) {
            throw new IllegalStateException("Attempt to process too many blocks");
        }
        this.r = i - 1;
        byte[] bArr2 = this.q;
        int i2 = (bArr2[15] & 255) + 1;
        bArr2[15] = (byte) i2;
        int i3 = (i2 >>> 8) + (bArr2[14] & 255);
        bArr2[14] = (byte) i3;
        int i4 = (i3 >>> 8) + (bArr2[13] & 255);
        bArr2[13] = (byte) i4;
        bArr2[12] = (byte) ((i4 >>> 8) + (bArr2[12] & 255));
        this.f14771a.processBlock(bArr2, 0, bArr, 0);
    }

    public final void g() {
        if (this.w > 0) {
            System.arraycopy(this.o, 0, this.p, 0, 16);
            this.x = this.w;
        }
        int i = this.v;
        if (i > 0) {
            e(this.p, this.u, 0, i);
            this.x += this.v;
        }
        if (this.x > 0) {
            System.arraycopy(this.p, 0, this.n, 0, 16);
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public String getAlgorithmName() {
        return this.f14771a.getAlgorithmName() + "/GCM";
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public byte[] getMac() {
        byte[] bArr = this.m;
        return bArr == null ? new byte[this.f] : Arrays.clone(bArr);
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public int getOutputSize(int i) {
        int i2 = i + this.s;
        if (this.d) {
            return i2 + this.f;
        }
        int i3 = this.f;
        if (i2 < i3) {
            return 0;
        }
        return i2 - i3;
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public BlockCipher getUnderlyingCipher() {
        return this.f14771a;
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public int getUpdateOutputSize(int i) {
        int i2 = i + this.s;
        if (!this.d) {
            int i3 = this.f;
            if (i2 < i3) {
                return 0;
            }
            i2 -= i3;
        }
        return i2 - (i2 % 16);
    }

    public final void h(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (bArr2.length - i2 < 16) {
            throw new OutputLengthException("Output buffer too short");
        }
        if (this.t == 0) {
            g();
        }
        byte[] bArr3 = new byte[16];
        f(bArr3);
        if (this.d) {
            GCMUtil.xor(bArr3, bArr, i);
            c(this.n, bArr3);
            System.arraycopy(bArr3, 0, bArr2, i2, 16);
        } else {
            d(this.n, bArr, i);
            GCMUtil.xor(bArr3, 0, bArr, i, bArr2, i2);
        }
        this.t += 16;
    }

    public final void i(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        byte[] bArr3 = new byte[16];
        f(bArr3);
        if (this.d) {
            GCMUtil.xor(bArr, i, bArr3, 0, i2);
            e(this.n, bArr, i, i2);
        } else {
            e(this.n, bArr, i, i2);
            GCMUtil.xor(bArr, i, bArr3, 0, i2);
        }
        System.arraycopy(bArr, i, bArr2, i3, i2);
        this.t += i2;
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        byte[] iv;
        KeyParameter keyParameter;
        byte[] bArr;
        this.d = z;
        this.m = null;
        this.e = true;
        if (cipherParameters instanceof AEADParameters) {
            AEADParameters aEADParameters = (AEADParameters) cipherParameters;
            iv = aEADParameters.getNonce();
            this.i = aEADParameters.getAssociatedText();
            int macSize = aEADParameters.getMacSize();
            if (macSize < 32 || macSize > 128 || macSize % 8 != 0) {
                throw new IllegalArgumentException("Invalid value for MAC size: " + macSize);
            }
            this.f = macSize / 8;
            keyParameter = aEADParameters.getKey();
        } else if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("invalid parameters passed to GCM");
        } else {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            iv = parametersWithIV.getIV();
            this.i = null;
            this.f = 16;
            keyParameter = (KeyParameter) parametersWithIV.getParameters();
        }
        this.l = new byte[z ? 16 : this.f + 16];
        if (iv == null || iv.length < 1) {
            throw new IllegalArgumentException("IV must be at least 1 byte");
        }
        if (z && (bArr = this.h) != null && Arrays.areEqual(bArr, iv)) {
            if (keyParameter == null) {
                throw new IllegalArgumentException("cannot reuse nonce for GCM encryption");
            }
            byte[] bArr2 = this.g;
            if (bArr2 != null && Arrays.areEqual(bArr2, keyParameter.getKey())) {
                throw new IllegalArgumentException("cannot reuse nonce for GCM encryption");
            }
        }
        this.h = iv;
        if (keyParameter != null) {
            this.g = keyParameter.getKey();
        }
        if (keyParameter != null) {
            this.f14771a.init(true, keyParameter);
            byte[] bArr3 = new byte[16];
            this.j = bArr3;
            this.f14771a.processBlock(bArr3, 0, bArr3, 0);
            this.b.init(this.j);
            this.c = null;
        } else if (this.j == null) {
            throw new IllegalArgumentException("Key must be specified in initial init");
        }
        byte[] bArr4 = new byte[16];
        this.k = bArr4;
        byte[] bArr5 = this.h;
        if (bArr5.length == 12) {
            System.arraycopy(bArr5, 0, bArr4, 0, bArr5.length);
            this.k[15] = 1;
        } else {
            b(bArr4, bArr5, bArr5.length);
            byte[] bArr6 = new byte[16];
            Pack.longToBigEndian(this.h.length * 8, bArr6, 8);
            c(this.k, bArr6);
        }
        this.n = new byte[16];
        this.o = new byte[16];
        this.p = new byte[16];
        this.u = new byte[16];
        this.v = 0;
        this.w = 0L;
        this.x = 0L;
        this.q = Arrays.clone(this.k);
        this.r = -2;
        this.s = 0;
        this.t = 0L;
        byte[] bArr7 = this.i;
        if (bArr7 != null) {
            processAADBytes(bArr7, 0, bArr7.length);
        }
    }

    public final void j(boolean z) {
        this.f14771a.reset();
        this.n = new byte[16];
        this.o = new byte[16];
        this.p = new byte[16];
        this.u = new byte[16];
        this.v = 0;
        this.w = 0L;
        this.x = 0L;
        this.q = Arrays.clone(this.k);
        this.r = -2;
        this.s = 0;
        this.t = 0L;
        byte[] bArr = this.l;
        if (bArr != null) {
            Arrays.fill(bArr, (byte) 0);
        }
        if (z) {
            this.m = null;
        }
        if (this.d) {
            this.e = false;
            return;
        }
        byte[] bArr2 = this.i;
        if (bArr2 != null) {
            processAADBytes(bArr2, 0, bArr2.length);
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public void processAADByte(byte b) {
        a();
        byte[] bArr = this.u;
        int i = this.v;
        bArr[i] = b;
        int i2 = i + 1;
        this.v = i2;
        if (i2 == 16) {
            c(this.o, bArr);
            this.v = 0;
            this.w += 16;
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public void processAADBytes(byte[] bArr, int i, int i2) {
        a();
        for (int i3 = 0; i3 < i2; i3++) {
            byte[] bArr2 = this.u;
            int i4 = this.v;
            bArr2[i4] = bArr[i + i3];
            int i5 = i4 + 1;
            this.v = i5;
            if (i5 == 16) {
                c(this.o, bArr2);
                this.v = 0;
                this.w += 16;
            }
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public int processByte(byte b, byte[] bArr, int i) throws DataLengthException {
        a();
        byte[] bArr2 = this.l;
        int i2 = this.s;
        bArr2[i2] = b;
        int i3 = i2 + 1;
        this.s = i3;
        if (i3 == bArr2.length) {
            h(bArr2, 0, bArr, i);
            if (this.d) {
                this.s = 0;
            } else {
                byte[] bArr3 = this.l;
                System.arraycopy(bArr3, 16, bArr3, 0, this.f);
                this.s = this.f;
            }
            return 16;
        }
        return 0;
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
        int i4;
        a();
        if (bArr.length - i >= i2) {
            if (this.d) {
                if (this.s != 0) {
                    while (i2 > 0) {
                        i2--;
                        byte[] bArr3 = this.l;
                        int i5 = this.s;
                        int i6 = i + 1;
                        bArr3[i5] = bArr[i];
                        int i7 = i5 + 1;
                        this.s = i7;
                        if (i7 == 16) {
                            h(bArr3, 0, bArr2, i3);
                            this.s = 0;
                            i4 = 16;
                            i = i6;
                            break;
                        }
                        i = i6;
                    }
                }
                i4 = 0;
                while (i2 >= 16) {
                    h(bArr, i, bArr2, i3 + i4);
                    i += 16;
                    i2 -= 16;
                    i4 += 16;
                }
                if (i2 > 0) {
                    System.arraycopy(bArr, i, this.l, 0, i2);
                    this.s = i2;
                }
            } else {
                i4 = 0;
                for (int i8 = 0; i8 < i2; i8++) {
                    byte[] bArr4 = this.l;
                    int i9 = this.s;
                    bArr4[i9] = bArr[i + i8];
                    int i10 = i9 + 1;
                    this.s = i10;
                    if (i10 == bArr4.length) {
                        h(bArr4, 0, bArr2, i3 + i4);
                        byte[] bArr5 = this.l;
                        System.arraycopy(bArr5, 16, bArr5, 0, this.f);
                        this.s = this.f;
                        i4 += 16;
                    }
                }
            }
            return i4;
        }
        throw new DataLengthException("Input buffer too short");
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public void reset() {
        j(true);
    }
}

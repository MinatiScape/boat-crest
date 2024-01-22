package org.bouncycastle.crypto.modes;

import java.io.ByteArrayOutputStream;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class KCCMBlockCipher implements AEADBlockCipher {

    /* renamed from: a  reason: collision with root package name */
    public BlockCipher f14772a;
    public int b;
    public boolean c;
    public byte[] d;
    public byte[] e;
    public byte[] f;
    public byte[] g;
    public byte[] h;
    public byte[] i;
    public byte[] j;
    public byte[] k;
    public a l;
    public a m;
    public int n;

    /* loaded from: classes13.dex */
    public class a extends ByteArrayOutputStream {
        public a(KCCMBlockCipher kCCMBlockCipher) {
        }

        public byte[] a() {
            return ((ByteArrayOutputStream) this).buf;
        }
    }

    public KCCMBlockCipher(BlockCipher blockCipher) {
        this(blockCipher, 4);
    }

    public KCCMBlockCipher(BlockCipher blockCipher, int i) {
        this.l = new a(this);
        this.m = new a(this);
        this.n = 4;
        this.f14772a = blockCipher;
        this.b = blockCipher.getBlockSize();
        this.g = new byte[blockCipher.getBlockSize()];
        this.d = new byte[blockCipher.getBlockSize()];
        this.e = new byte[blockCipher.getBlockSize()];
        this.f = new byte[blockCipher.getBlockSize()];
        this.h = new byte[blockCipher.getBlockSize()];
        this.i = new byte[blockCipher.getBlockSize()];
        this.j = new byte[blockCipher.getBlockSize()];
        this.k = new byte[blockCipher.getBlockSize()];
        f(i);
    }

    public final void a(byte[] bArr, int i, int i2) {
        while (i2 > 0) {
            for (int i3 = 0; i3 < this.f14772a.getBlockSize(); i3++) {
                byte[] bArr2 = this.f;
                bArr2[i3] = (byte) (bArr2[i3] ^ bArr[i + i3]);
            }
            BlockCipher blockCipher = this.f14772a;
            byte[] bArr3 = this.f;
            blockCipher.processBlock(bArr3, 0, bArr3, 0);
            i2 -= this.f14772a.getBlockSize();
            i += this.f14772a.getBlockSize();
        }
    }

    public final void b(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        int i4 = 0;
        while (true) {
            byte[] bArr3 = this.k;
            if (i4 >= bArr3.length) {
                break;
            }
            byte[] bArr4 = this.j;
            bArr4[i4] = (byte) (bArr4[i4] + bArr3[i4]);
            i4++;
        }
        this.f14772a.processBlock(this.j, 0, this.i, 0);
        for (int i5 = 0; i5 < this.f14772a.getBlockSize(); i5++) {
            bArr2[i3 + i5] = (byte) (this.i[i5] ^ bArr[i + i5]);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0047 A[LOOP:0: B:24:0x0040->B:26:0x0047, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final byte c(boolean r4, int r5) {
        /*
            r3 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.lang.String r1 = "0"
            if (r4 == 0) goto Lf
            java.lang.String r4 = "1"
            r0.append(r4)
            goto L12
        Lf:
            r0.append(r1)
        L12:
            r4 = 8
            if (r5 == r4) goto L33
            r4 = 16
            if (r5 == r4) goto L30
            r4 = 32
            if (r5 == r4) goto L2d
            r4 = 48
            if (r5 == r4) goto L2a
            r4 = 64
            if (r5 == r4) goto L27
            goto L38
        L27:
            java.lang.String r4 = "110"
            goto L35
        L2a:
            java.lang.String r4 = "101"
            goto L35
        L2d:
            java.lang.String r4 = "100"
            goto L35
        L30:
            java.lang.String r4 = "011"
            goto L35
        L33:
            java.lang.String r4 = "010"
        L35:
            r0.append(r4)
        L38:
            int r4 = r3.n
            int r4 = r4 + (-1)
            java.lang.String r4 = java.lang.Integer.toBinaryString(r4)
        L40:
            int r5 = r4.length()
            r2 = 4
            if (r5 >= r2) goto L56
            java.lang.StringBuffer r5 = new java.lang.StringBuffer
            r5.<init>(r4)
            r4 = 0
            java.lang.StringBuffer r4 = r5.insert(r4, r1)
            java.lang.String r4 = r4.toString()
            goto L40
        L56:
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            r5 = 2
            int r4 = java.lang.Integer.parseInt(r4, r5)
            byte r4 = (byte) r4
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.modes.KCCMBlockCipher.c(boolean, int):byte");
    }

    public final void d(int i, byte[] bArr, int i2) {
        bArr[i2 + 3] = (byte) (i >> 24);
        bArr[i2 + 2] = (byte) (i >> 16);
        bArr[i2 + 1] = (byte) (i >> 8);
        bArr[i2] = (byte) i;
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public int doFinal(byte[] bArr, int i) throws IllegalStateException, InvalidCipherTextException {
        int processPacket = processPacket(this.m.a(), 0, this.m.size(), bArr, i);
        reset();
        return processPacket;
    }

    public final void e(byte[] bArr, int i, int i2, int i3) {
        if (i2 - i < this.f14772a.getBlockSize()) {
            throw new IllegalArgumentException("authText buffer too short");
        }
        if (i2 % this.f14772a.getBlockSize() != 0) {
            throw new IllegalArgumentException("padding not supported");
        }
        byte[] bArr2 = this.g;
        System.arraycopy(bArr2, 0, this.h, 0, (bArr2.length - this.n) - 1);
        d(i3, this.i, 0);
        System.arraycopy(this.i, 0, this.h, (this.g.length - this.n) - 1, 4);
        byte[] bArr3 = this.h;
        bArr3[bArr3.length - 1] = c(true, this.b);
        this.f14772a.processBlock(this.h, 0, this.f, 0);
        d(i2, this.i, 0);
        if (i2 <= this.f14772a.getBlockSize() - this.n) {
            for (int i4 = 0; i4 < i2; i4++) {
                byte[] bArr4 = this.i;
                int i5 = this.n + i4;
                bArr4[i5] = (byte) (bArr4[i5] ^ bArr[i + i4]);
            }
            for (int i6 = 0; i6 < this.f14772a.getBlockSize(); i6++) {
                byte[] bArr5 = this.f;
                bArr5[i6] = (byte) (bArr5[i6] ^ this.i[i6]);
            }
            BlockCipher blockCipher = this.f14772a;
            byte[] bArr6 = this.f;
            blockCipher.processBlock(bArr6, 0, bArr6, 0);
            return;
        }
        for (int i7 = 0; i7 < this.f14772a.getBlockSize(); i7++) {
            byte[] bArr7 = this.f;
            bArr7[i7] = (byte) (bArr7[i7] ^ this.i[i7]);
        }
        BlockCipher blockCipher2 = this.f14772a;
        byte[] bArr8 = this.f;
        blockCipher2.processBlock(bArr8, 0, bArr8, 0);
        while (i2 != 0) {
            for (int i8 = 0; i8 < this.f14772a.getBlockSize(); i8++) {
                byte[] bArr9 = this.f;
                bArr9[i8] = (byte) (bArr9[i8] ^ bArr[i8 + i]);
            }
            BlockCipher blockCipher3 = this.f14772a;
            byte[] bArr10 = this.f;
            blockCipher3.processBlock(bArr10, 0, bArr10, 0);
            i += this.f14772a.getBlockSize();
            i2 -= this.f14772a.getBlockSize();
        }
    }

    public final void f(int i) {
        if (i != 4 && i != 6 && i != 8) {
            throw new IllegalArgumentException("Nb = 4 is recommended by DSTU7624 but can be changed to only 6 or 8 in this implementation");
        }
        this.n = i;
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public String getAlgorithmName() {
        return this.f14772a.getAlgorithmName() + "/KCCM";
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public byte[] getMac() {
        return Arrays.clone(this.e);
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public int getOutputSize(int i) {
        return i + this.b;
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public BlockCipher getUnderlyingCipher() {
        return this.f14772a;
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public int getUpdateOutputSize(int i) {
        return i;
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        CipherParameters parameters;
        if (cipherParameters instanceof AEADParameters) {
            AEADParameters aEADParameters = (AEADParameters) cipherParameters;
            if (aEADParameters.getMacSize() > 512 || aEADParameters.getMacSize() < 64 || aEADParameters.getMacSize() % 8 != 0) {
                throw new IllegalArgumentException("Invalid mac size specified");
            }
            this.g = aEADParameters.getNonce();
            this.b = aEADParameters.getMacSize() / 8;
            this.d = aEADParameters.getAssociatedText();
            parameters = aEADParameters.getKey();
        } else if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("Invalid parameters specified");
        } else {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            this.g = parametersWithIV.getIV();
            this.b = this.f14772a.getBlockSize();
            this.d = null;
            parameters = parametersWithIV.getParameters();
        }
        this.e = new byte[this.b];
        this.c = z;
        this.f14772a.init(true, parameters);
        this.k[0] = 1;
        byte[] bArr = this.d;
        if (bArr != null) {
            processAADBytes(bArr, 0, bArr.length);
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public void processAADByte(byte b) {
        this.l.write(b);
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public void processAADBytes(byte[] bArr, int i, int i2) {
        this.l.write(bArr, i, i2);
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public int processByte(byte b, byte[] bArr, int i) throws DataLengthException, IllegalStateException {
        this.m.write(b);
        return 0;
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException, IllegalStateException {
        if (bArr.length >= i + i2) {
            this.m.write(bArr, i, i2);
            return 0;
        }
        throw new DataLengthException("input buffer too short");
    }

    public int processPacket(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws IllegalStateException, InvalidCipherTextException {
        int i4;
        if (bArr.length - i < i2) {
            throw new DataLengthException("input buffer too short");
        }
        if (bArr2.length - i3 < i2) {
            throw new OutputLengthException("output buffer too short");
        }
        if (this.l.size() > 0) {
            if (this.c) {
                e(this.l.a(), 0, this.l.size(), this.m.size());
            } else {
                e(this.l.a(), 0, this.l.size(), this.m.size() - this.b);
            }
        }
        if (!this.c) {
            if ((i2 - this.b) % this.f14772a.getBlockSize() == 0) {
                this.f14772a.processBlock(this.g, 0, this.j, 0);
                int blockSize = i2 / this.f14772a.getBlockSize();
                for (int i5 = 0; i5 < blockSize; i5++) {
                    b(bArr, i, i2, bArr2, i3);
                    i += this.f14772a.getBlockSize();
                    i3 += this.f14772a.getBlockSize();
                }
                if (i2 > i) {
                    int i6 = 0;
                    while (true) {
                        byte[] bArr3 = this.k;
                        if (i6 >= bArr3.length) {
                            break;
                        }
                        byte[] bArr4 = this.j;
                        bArr4[i6] = (byte) (bArr4[i6] + bArr3[i6]);
                        i6++;
                    }
                    this.f14772a.processBlock(this.j, 0, this.i, 0);
                    int i7 = 0;
                    while (true) {
                        i4 = this.b;
                        if (i7 >= i4) {
                            break;
                        }
                        bArr2[i3 + i7] = (byte) (this.i[i7] ^ bArr[i + i7]);
                        i7++;
                    }
                    i3 += i4;
                }
                int i8 = 0;
                while (true) {
                    byte[] bArr5 = this.k;
                    if (i8 >= bArr5.length) {
                        break;
                    }
                    byte[] bArr6 = this.j;
                    bArr6[i8] = (byte) (bArr6[i8] + bArr5[i8]);
                    i8++;
                }
                this.f14772a.processBlock(this.j, 0, this.i, 0);
                int i9 = this.b;
                System.arraycopy(bArr2, i3 - i9, this.i, 0, i9);
                a(bArr2, 0, i3 - this.b);
                System.arraycopy(this.f, 0, this.e, 0, this.b);
                int i10 = this.b;
                byte[] bArr7 = new byte[i10];
                System.arraycopy(this.i, 0, bArr7, 0, i10);
                if (Arrays.constantTimeAreEqual(this.e, bArr7)) {
                    reset();
                    return i2 - this.b;
                }
                throw new InvalidCipherTextException("mac check failed");
            }
            throw new DataLengthException("partial blocks not supported");
        } else if (i2 % this.f14772a.getBlockSize() != 0) {
            throw new DataLengthException("partial blocks not supported");
        } else {
            a(bArr, i, i2);
            this.f14772a.processBlock(this.g, 0, this.j, 0);
            int i11 = i2;
            while (i11 > 0) {
                b(bArr, i, i2, bArr2, i3);
                i11 -= this.f14772a.getBlockSize();
                i += this.f14772a.getBlockSize();
                i3 += this.f14772a.getBlockSize();
            }
            int i12 = 0;
            while (true) {
                byte[] bArr8 = this.k;
                if (i12 >= bArr8.length) {
                    break;
                }
                byte[] bArr9 = this.j;
                bArr9[i12] = (byte) (bArr9[i12] + bArr8[i12]);
                i12++;
            }
            this.f14772a.processBlock(this.j, 0, this.i, 0);
            int i13 = 0;
            while (true) {
                int i14 = this.b;
                if (i13 >= i14) {
                    System.arraycopy(this.f, 0, this.e, 0, i14);
                    reset();
                    return i2 + this.b;
                }
                bArr2[i3 + i13] = (byte) (this.i[i13] ^ this.f[i13]);
                i13++;
            }
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public void reset() {
        Arrays.fill(this.h, (byte) 0);
        Arrays.fill(this.i, (byte) 0);
        Arrays.fill(this.k, (byte) 0);
        Arrays.fill(this.f, (byte) 0);
        this.k[0] = 1;
        this.m.reset();
        this.l.reset();
        byte[] bArr = this.d;
        if (bArr != null) {
            processAADBytes(bArr, 0, bArr.length);
        }
    }
}

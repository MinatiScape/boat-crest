package org.bouncycastle.crypto.modes;

import java.io.ByteArrayOutputStream;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.macs.CBCBlockCipherMac;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class CCMBlockCipher implements AEADBlockCipher {

    /* renamed from: a  reason: collision with root package name */
    public BlockCipher f14767a;
    public int b;
    public boolean c;
    public byte[] d;
    public byte[] e;
    public int f;
    public CipherParameters g;
    public byte[] h;
    public a i = new a(this);
    public a j = new a(this);

    /* loaded from: classes13.dex */
    public class a extends ByteArrayOutputStream {
        public a(CCMBlockCipher cCMBlockCipher) {
        }

        public byte[] a() {
            return ((ByteArrayOutputStream) this).buf;
        }
    }

    public CCMBlockCipher(BlockCipher blockCipher) {
        this.f14767a = blockCipher;
        int blockSize = blockCipher.getBlockSize();
        this.b = blockSize;
        this.h = new byte[blockSize];
        if (blockSize != 16) {
            throw new IllegalArgumentException("cipher required with a block size of 16.");
        }
    }

    public final int a(byte[] bArr, int i, int i2, byte[] bArr2) {
        CBCBlockCipherMac cBCBlockCipherMac = new CBCBlockCipherMac(this.f14767a, this.f * 8);
        cBCBlockCipherMac.init(this.g);
        byte[] bArr3 = new byte[16];
        if (c()) {
            bArr3[0] = (byte) (bArr3[0] | 64);
        }
        int i3 = 2;
        bArr3[0] = (byte) (bArr3[0] | ((((cBCBlockCipherMac.getMacSize() - 2) / 2) & 7) << 3));
        byte b = bArr3[0];
        byte[] bArr4 = this.d;
        bArr3[0] = (byte) (b | (((15 - bArr4.length) - 1) & 7));
        System.arraycopy(bArr4, 0, bArr3, 1, bArr4.length);
        int i4 = i2;
        int i5 = 1;
        while (i4 > 0) {
            bArr3[16 - i5] = (byte) (i4 & 255);
            i4 >>>= 8;
            i5++;
        }
        cBCBlockCipherMac.update(bArr3, 0, 16);
        if (c()) {
            int b2 = b();
            if (b2 < 65280) {
                cBCBlockCipherMac.update((byte) (b2 >> 8));
                cBCBlockCipherMac.update((byte) b2);
            } else {
                cBCBlockCipherMac.update((byte) -1);
                cBCBlockCipherMac.update((byte) -2);
                cBCBlockCipherMac.update((byte) (b2 >> 24));
                cBCBlockCipherMac.update((byte) (b2 >> 16));
                cBCBlockCipherMac.update((byte) (b2 >> 8));
                cBCBlockCipherMac.update((byte) b2);
                i3 = 6;
            }
            byte[] bArr5 = this.e;
            if (bArr5 != null) {
                cBCBlockCipherMac.update(bArr5, 0, bArr5.length);
            }
            if (this.i.size() > 0) {
                cBCBlockCipherMac.update(this.i.a(), 0, this.i.size());
            }
            int i6 = (i3 + b2) % 16;
            if (i6 != 0) {
                while (i6 != 16) {
                    cBCBlockCipherMac.update((byte) 0);
                    i6++;
                }
            }
        }
        cBCBlockCipherMac.update(bArr, i, i2);
        return cBCBlockCipherMac.doFinal(bArr2, 0);
    }

    public final int b() {
        int size = this.i.size();
        byte[] bArr = this.e;
        return size + (bArr == null ? 0 : bArr.length);
    }

    public final boolean c() {
        return b() > 0;
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public int doFinal(byte[] bArr, int i) throws IllegalStateException, InvalidCipherTextException {
        int processPacket = processPacket(this.j.a(), 0, this.j.size(), bArr, i);
        reset();
        return processPacket;
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public String getAlgorithmName() {
        return this.f14767a.getAlgorithmName() + "/CCM";
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public byte[] getMac() {
        int i = this.f;
        byte[] bArr = new byte[i];
        System.arraycopy(this.h, 0, bArr, 0, i);
        return bArr;
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public int getOutputSize(int i) {
        int size = i + this.j.size();
        if (this.c) {
            return size + this.f;
        }
        int i2 = this.f;
        if (size < i2) {
            return 0;
        }
        return size - i2;
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public BlockCipher getUnderlyingCipher() {
        return this.f14767a;
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public int getUpdateOutputSize(int i) {
        return 0;
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        CipherParameters parameters;
        this.c = z;
        if (cipherParameters instanceof AEADParameters) {
            AEADParameters aEADParameters = (AEADParameters) cipherParameters;
            this.d = aEADParameters.getNonce();
            this.e = aEADParameters.getAssociatedText();
            this.f = aEADParameters.getMacSize() / 8;
            parameters = aEADParameters.getKey();
        } else if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("invalid parameters passed to CCM: " + cipherParameters.getClass().getName());
        } else {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            this.d = parametersWithIV.getIV();
            this.e = null;
            this.f = this.h.length / 2;
            parameters = parametersWithIV.getParameters();
        }
        if (parameters != null) {
            this.g = parameters;
        }
        byte[] bArr = this.d;
        if (bArr == null || bArr.length < 7 || bArr.length > 13) {
            throw new IllegalArgumentException("nonce must have length from 7 to 13 octets");
        }
        reset();
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public void processAADByte(byte b) {
        this.i.write(b);
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public void processAADBytes(byte[] bArr, int i, int i2) {
        this.i.write(bArr, i, i2);
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public int processByte(byte b, byte[] bArr, int i) throws DataLengthException, IllegalStateException {
        this.j.write(b);
        return 0;
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException, IllegalStateException {
        if (bArr.length >= i + i2) {
            this.j.write(bArr, i, i2);
            return 0;
        }
        throw new DataLengthException("Input buffer too short");
    }

    public int processPacket(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws IllegalStateException, InvalidCipherTextException, DataLengthException {
        int i4;
        if (this.g == null) {
            throw new IllegalStateException("CCM cipher unitialized.");
        }
        byte[] bArr3 = this.d;
        int length = 15 - bArr3.length;
        if (length < 4 && i2 >= (1 << (length * 8))) {
            throw new IllegalStateException("CCM packet too large for choice of q.");
        }
        byte[] bArr4 = new byte[this.b];
        bArr4[0] = (byte) ((length - 1) & 7);
        System.arraycopy(bArr3, 0, bArr4, 1, bArr3.length);
        SICBlockCipher sICBlockCipher = new SICBlockCipher(this.f14767a);
        sICBlockCipher.init(this.c, new ParametersWithIV(this.g, bArr4));
        if (!this.c) {
            int i5 = this.f;
            if (i2 >= i5) {
                int i6 = i2 - i5;
                if (bArr2.length >= i6 + i3) {
                    int i7 = i + i6;
                    System.arraycopy(bArr, i7, this.h, 0, i5);
                    byte[] bArr5 = this.h;
                    sICBlockCipher.processBlock(bArr5, 0, bArr5, 0);
                    int i8 = this.f;
                    while (true) {
                        byte[] bArr6 = this.h;
                        if (i8 == bArr6.length) {
                            break;
                        }
                        bArr6[i8] = 0;
                        i8++;
                    }
                    int i9 = i;
                    int i10 = i3;
                    while (true) {
                        i4 = this.b;
                        if (i9 >= i7 - i4) {
                            break;
                        }
                        sICBlockCipher.processBlock(bArr, i9, bArr2, i10);
                        int i11 = this.b;
                        i10 += i11;
                        i9 += i11;
                    }
                    byte[] bArr7 = new byte[i4];
                    int i12 = i6 - (i9 - i);
                    System.arraycopy(bArr, i9, bArr7, 0, i12);
                    sICBlockCipher.processBlock(bArr7, 0, bArr7, 0);
                    System.arraycopy(bArr7, 0, bArr2, i10, i12);
                    byte[] bArr8 = new byte[this.b];
                    a(bArr2, i3, i6, bArr8);
                    if (Arrays.constantTimeAreEqual(this.h, bArr8)) {
                        return i6;
                    }
                    throw new InvalidCipherTextException("mac check in CCM failed");
                }
                throw new OutputLengthException("Output buffer too short.");
            }
            throw new InvalidCipherTextException("data too short");
        }
        int i13 = this.f + i2;
        if (bArr2.length < i13 + i3) {
            throw new OutputLengthException("Output buffer too short.");
        }
        a(bArr, i, i2, this.h);
        byte[] bArr9 = new byte[this.b];
        sICBlockCipher.processBlock(this.h, 0, bArr9, 0);
        int i14 = i;
        int i15 = i3;
        while (true) {
            int i16 = i + i2;
            int i17 = this.b;
            if (i14 >= i16 - i17) {
                byte[] bArr10 = new byte[i17];
                int i18 = i16 - i14;
                System.arraycopy(bArr, i14, bArr10, 0, i18);
                sICBlockCipher.processBlock(bArr10, 0, bArr10, 0);
                System.arraycopy(bArr10, 0, bArr2, i15, i18);
                System.arraycopy(bArr9, 0, bArr2, i3 + i2, this.f);
                return i13;
            }
            sICBlockCipher.processBlock(bArr, i14, bArr2, i15);
            int i19 = this.b;
            i15 += i19;
            i14 += i19;
        }
    }

    public byte[] processPacket(byte[] bArr, int i, int i2) throws IllegalStateException, InvalidCipherTextException {
        int i3;
        if (this.c) {
            i3 = this.f + i2;
        } else {
            int i4 = this.f;
            if (i2 < i4) {
                throw new InvalidCipherTextException("data too short");
            }
            i3 = i2 - i4;
        }
        byte[] bArr2 = new byte[i3];
        processPacket(bArr, i, i2, bArr2, 0);
        return bArr2;
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public void reset() {
        this.f14767a.reset();
        this.i.reset();
        this.j.reset();
    }
}

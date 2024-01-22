package org.bouncycastle.crypto.modes;

import java.util.Vector;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class OCBBlockCipher implements AEADBlockCipher {

    /* renamed from: a  reason: collision with root package name */
    public BlockCipher f14776a;
    public BlockCipher b;
    public boolean c;
    public int d;
    public byte[] e;
    public Vector f;
    public byte[] g;
    public byte[] h;
    public byte[] l;
    public byte[] m;
    public int n;
    public int o;
    public long p;
    public long q;
    public byte[] r;
    public byte[] s;
    public byte[] u;
    public byte[] v;
    public byte[] i = null;
    public byte[] j = new byte[24];
    public byte[] k = new byte[16];
    public byte[] t = new byte[16];

    public OCBBlockCipher(BlockCipher blockCipher, BlockCipher blockCipher2) {
        if (blockCipher == null) {
            throw new IllegalArgumentException("'hashCipher' cannot be null");
        }
        if (blockCipher.getBlockSize() != 16) {
            throw new IllegalArgumentException("'hashCipher' must have a block size of 16");
        }
        if (blockCipher2 == null) {
            throw new IllegalArgumentException("'mainCipher' cannot be null");
        }
        if (blockCipher2.getBlockSize() != 16) {
            throw new IllegalArgumentException("'mainCipher' must have a block size of 16");
        }
        if (!blockCipher.getAlgorithmName().equals(blockCipher2.getAlgorithmName())) {
            throw new IllegalArgumentException("'hashCipher' and 'mainCipher' must be the same algorithm");
        }
        this.f14776a = blockCipher;
        this.b = blockCipher2;
    }

    public static byte[] OCB_double(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        bArr2[15] = (byte) ((135 >>> ((1 - shiftLeft(bArr, bArr2)) << 3)) ^ bArr2[15]);
        return bArr2;
    }

    public static void OCB_extend(byte[] bArr, int i) {
        bArr[i] = Byte.MIN_VALUE;
        while (true) {
            i++;
            if (i >= 16) {
                return;
            }
            bArr[i] = 0;
        }
    }

    public static int OCB_ntz(long j) {
        if (j == 0) {
            return 64;
        }
        int i = 0;
        while ((1 & j) == 0) {
            i++;
            j >>>= 1;
        }
        return i;
    }

    public static int shiftLeft(byte[] bArr, byte[] bArr2) {
        int i = 16;
        int i2 = 0;
        while (true) {
            i--;
            if (i < 0) {
                return i2;
            }
            int i3 = bArr[i] & 255;
            bArr2[i] = (byte) (i2 | (i3 << 1));
            i2 = (i3 >>> 7) & 1;
        }
    }

    public static void xor(byte[] bArr, byte[] bArr2) {
        for (int i = 15; i >= 0; i--) {
            bArr[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
    }

    public void clear(byte[] bArr) {
        if (bArr != null) {
            Arrays.fill(bArr, (byte) 0);
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public int doFinal(byte[] bArr, int i) throws IllegalStateException, InvalidCipherTextException {
        byte[] bArr2;
        if (this.c) {
            bArr2 = null;
        } else {
            int i2 = this.o;
            int i3 = this.d;
            if (i2 < i3) {
                throw new InvalidCipherTextException("data too short");
            }
            int i4 = i2 - i3;
            this.o = i4;
            bArr2 = new byte[i3];
            System.arraycopy(this.m, i4, bArr2, 0, i3);
        }
        int i5 = this.n;
        if (i5 > 0) {
            OCB_extend(this.l, i5);
            updateHASH(this.g);
        }
        int i6 = this.o;
        if (i6 > 0) {
            if (this.c) {
                OCB_extend(this.m, i6);
                xor(this.u, this.m);
            }
            xor(this.t, this.g);
            byte[] bArr3 = new byte[16];
            this.f14776a.processBlock(this.t, 0, bArr3, 0);
            xor(this.m, bArr3);
            int length = bArr.length;
            int i7 = this.o;
            if (length < i + i7) {
                throw new OutputLengthException("Output buffer too short");
            }
            System.arraycopy(this.m, 0, bArr, i, i7);
            if (!this.c) {
                OCB_extend(this.m, this.o);
                xor(this.u, this.m);
            }
        }
        xor(this.u, this.t);
        xor(this.u, this.h);
        BlockCipher blockCipher = this.f14776a;
        byte[] bArr4 = this.u;
        blockCipher.processBlock(bArr4, 0, bArr4, 0);
        xor(this.u, this.s);
        int i8 = this.d;
        byte[] bArr5 = new byte[i8];
        this.v = bArr5;
        System.arraycopy(this.u, 0, bArr5, 0, i8);
        int i9 = this.o;
        if (this.c) {
            int length2 = bArr.length;
            int i10 = i + i9;
            int i11 = this.d;
            if (length2 < i10 + i11) {
                throw new OutputLengthException("Output buffer too short");
            }
            System.arraycopy(this.v, 0, bArr, i10, i11);
            i9 += this.d;
        } else if (!Arrays.constantTimeAreEqual(this.v, bArr2)) {
            throw new InvalidCipherTextException("mac check in OCB failed");
        }
        reset(false);
        return i9;
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public String getAlgorithmName() {
        return this.b.getAlgorithmName() + "/OCB";
    }

    public byte[] getLSub(int i) {
        while (i >= this.f.size()) {
            Vector vector = this.f;
            vector.addElement(OCB_double((byte[]) vector.lastElement()));
        }
        return (byte[]) this.f.elementAt(i);
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public byte[] getMac() {
        byte[] bArr = this.v;
        return bArr == null ? new byte[this.d] : Arrays.clone(bArr);
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public int getOutputSize(int i) {
        int i2 = i + this.o;
        if (this.c) {
            return i2 + this.d;
        }
        int i3 = this.d;
        if (i2 < i3) {
            return 0;
        }
        return i2 - i3;
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public BlockCipher getUnderlyingCipher() {
        return this.b;
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public int getUpdateOutputSize(int i) {
        int i2 = i + this.o;
        if (!this.c) {
            int i3 = this.d;
            if (i2 < i3) {
                return 0;
            }
            i2 -= i3;
        }
        return i2 - (i2 % 16);
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        byte[] iv;
        KeyParameter keyParameter;
        boolean z2 = this.c;
        this.c = z;
        this.v = null;
        if (cipherParameters instanceof AEADParameters) {
            AEADParameters aEADParameters = (AEADParameters) cipherParameters;
            iv = aEADParameters.getNonce();
            this.e = aEADParameters.getAssociatedText();
            int macSize = aEADParameters.getMacSize();
            if (macSize < 64 || macSize > 128 || macSize % 8 != 0) {
                throw new IllegalArgumentException("Invalid value for MAC size: " + macSize);
            }
            this.d = macSize / 8;
            keyParameter = aEADParameters.getKey();
        } else if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("invalid parameters passed to OCB");
        } else {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            iv = parametersWithIV.getIV();
            this.e = null;
            this.d = 16;
            keyParameter = (KeyParameter) parametersWithIV.getParameters();
        }
        this.l = new byte[16];
        this.m = new byte[z ? 16 : this.d + 16];
        if (iv == null) {
            iv = new byte[0];
        }
        if (iv.length > 15) {
            throw new IllegalArgumentException("IV must be no more than 15 bytes");
        }
        if (keyParameter != null) {
            this.f14776a.init(true, keyParameter);
            this.b.init(z, keyParameter);
            this.i = null;
        } else if (z2 != z) {
            throw new IllegalArgumentException("cannot change encrypting state without providing key.");
        }
        byte[] bArr = new byte[16];
        this.g = bArr;
        this.f14776a.processBlock(bArr, 0, bArr, 0);
        this.h = OCB_double(this.g);
        Vector vector = new Vector();
        this.f = vector;
        vector.addElement(OCB_double(this.h));
        int processNonce = processNonce(iv);
        int i = processNonce % 8;
        int i2 = processNonce / 8;
        if (i == 0) {
            System.arraycopy(this.j, i2, this.k, 0, 16);
        } else {
            for (int i3 = 0; i3 < 16; i3++) {
                byte[] bArr2 = this.j;
                i2++;
                this.k[i3] = (byte) (((bArr2[i2] & 255) >>> (8 - i)) | ((bArr2[i2] & 255) << i));
            }
        }
        this.n = 0;
        this.o = 0;
        this.p = 0L;
        this.q = 0L;
        this.r = new byte[16];
        this.s = new byte[16];
        System.arraycopy(this.k, 0, this.t, 0, 16);
        this.u = new byte[16];
        byte[] bArr3 = this.e;
        if (bArr3 != null) {
            processAADBytes(bArr3, 0, bArr3.length);
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public void processAADByte(byte b) {
        byte[] bArr = this.l;
        int i = this.n;
        bArr[i] = b;
        int i2 = i + 1;
        this.n = i2;
        if (i2 == bArr.length) {
            processHashBlock();
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public void processAADBytes(byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            byte[] bArr2 = this.l;
            int i4 = this.n;
            bArr2[i4] = bArr[i + i3];
            int i5 = i4 + 1;
            this.n = i5;
            if (i5 == bArr2.length) {
                processHashBlock();
            }
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public int processByte(byte b, byte[] bArr, int i) throws DataLengthException {
        byte[] bArr2 = this.m;
        int i2 = this.o;
        bArr2[i2] = b;
        int i3 = i2 + 1;
        this.o = i3;
        if (i3 == bArr2.length) {
            processMainBlock(bArr, i);
            return 16;
        }
        return 0;
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
        if (bArr.length >= i + i2) {
            int i4 = 0;
            for (int i5 = 0; i5 < i2; i5++) {
                byte[] bArr3 = this.m;
                int i6 = this.o;
                bArr3[i6] = bArr[i + i5];
                int i7 = i6 + 1;
                this.o = i7;
                if (i7 == bArr3.length) {
                    processMainBlock(bArr2, i3 + i4);
                    i4 += 16;
                }
            }
            return i4;
        }
        throw new DataLengthException("Input buffer too short");
    }

    public void processHashBlock() {
        long j = this.p + 1;
        this.p = j;
        updateHASH(getLSub(OCB_ntz(j)));
        this.n = 0;
    }

    public void processMainBlock(byte[] bArr, int i) {
        if (bArr.length < i + 16) {
            throw new OutputLengthException("Output buffer too short");
        }
        if (this.c) {
            xor(this.u, this.m);
            this.o = 0;
        }
        byte[] bArr2 = this.t;
        long j = this.q + 1;
        this.q = j;
        xor(bArr2, getLSub(OCB_ntz(j)));
        xor(this.m, this.t);
        BlockCipher blockCipher = this.b;
        byte[] bArr3 = this.m;
        blockCipher.processBlock(bArr3, 0, bArr3, 0);
        xor(this.m, this.t);
        System.arraycopy(this.m, 0, bArr, i, 16);
        if (this.c) {
            return;
        }
        xor(this.u, this.m);
        byte[] bArr4 = this.m;
        System.arraycopy(bArr4, 16, bArr4, 0, this.d);
        this.o = this.d;
    }

    public int processNonce(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        int i = 0;
        System.arraycopy(bArr, 0, bArr2, 16 - bArr.length, bArr.length);
        bArr2[0] = (byte) (this.d << 4);
        int length = 15 - bArr.length;
        bArr2[length] = (byte) (bArr2[length] | 1);
        int i2 = bArr2[15] & 63;
        bArr2[15] = (byte) (bArr2[15] & 192);
        byte[] bArr3 = this.i;
        if (bArr3 == null || !Arrays.areEqual(bArr2, bArr3)) {
            byte[] bArr4 = new byte[16];
            this.i = bArr2;
            this.f14776a.processBlock(bArr2, 0, bArr4, 0);
            System.arraycopy(bArr4, 0, this.j, 0, 16);
            while (i < 8) {
                byte[] bArr5 = this.j;
                int i3 = i + 16;
                byte b = bArr4[i];
                i++;
                bArr5[i3] = (byte) (b ^ bArr4[i]);
            }
        }
        return i2;
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public void reset() {
        reset(true);
    }

    public void reset(boolean z) {
        this.f14776a.reset();
        this.b.reset();
        clear(this.l);
        clear(this.m);
        this.n = 0;
        this.o = 0;
        this.p = 0L;
        this.q = 0L;
        clear(this.r);
        clear(this.s);
        System.arraycopy(this.k, 0, this.t, 0, 16);
        clear(this.u);
        if (z) {
            this.v = null;
        }
        byte[] bArr = this.e;
        if (bArr != null) {
            processAADBytes(bArr, 0, bArr.length);
        }
    }

    public void updateHASH(byte[] bArr) {
        xor(this.r, bArr);
        xor(this.l, this.r);
        BlockCipher blockCipher = this.f14776a;
        byte[] bArr2 = this.l;
        blockCipher.processBlock(bArr2, 0, bArr2, 0);
        xor(this.s, this.l);
    }
}

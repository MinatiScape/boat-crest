package org.bouncycastle.crypto.encodings;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.util.Arrays;
/* loaded from: classes12.dex */
public class PKCS1Encoding implements AsymmetricBlockCipher {
    public static final String NOT_STRICT_LENGTH_ENABLED_PROPERTY = "org.bouncycastle.pkcs1.not_strict";
    public static final String STRICT_LENGTH_ENABLED_PROPERTY = "org.bouncycastle.pkcs1.strict";

    /* renamed from: a  reason: collision with root package name */
    public SecureRandom f14662a;
    public AsymmetricBlockCipher b;
    public boolean c;
    public boolean d;
    public boolean e;
    public int f;
    public byte[] g;
    public byte[] h;

    /* loaded from: classes12.dex */
    public class a implements PrivilegedAction {
        public a(PKCS1Encoding pKCS1Encoding) {
        }

        @Override // java.security.PrivilegedAction
        public Object run() {
            return System.getProperty(PKCS1Encoding.STRICT_LENGTH_ENABLED_PROPERTY);
        }
    }

    /* loaded from: classes12.dex */
    public class b implements PrivilegedAction {
        public b(PKCS1Encoding pKCS1Encoding) {
        }

        @Override // java.security.PrivilegedAction
        public Object run() {
            return System.getProperty(PKCS1Encoding.NOT_STRICT_LENGTH_ENABLED_PROPERTY);
        }
    }

    public PKCS1Encoding(AsymmetricBlockCipher asymmetricBlockCipher) {
        this.f = -1;
        this.g = null;
        this.b = asymmetricBlockCipher;
        this.e = f();
    }

    public PKCS1Encoding(AsymmetricBlockCipher asymmetricBlockCipher, int i) {
        this.f = -1;
        this.g = null;
        this.b = asymmetricBlockCipher;
        this.e = f();
        this.f = i;
    }

    public PKCS1Encoding(AsymmetricBlockCipher asymmetricBlockCipher, byte[] bArr) {
        this.f = -1;
        this.g = null;
        this.b = asymmetricBlockCipher;
        this.e = f();
        this.g = bArr;
        this.f = bArr.length;
    }

    public static int a(byte[] bArr, int i) {
        int i2 = 0 | (bArr[0] ^ 2);
        int i3 = i + 1;
        int length = bArr.length - i3;
        for (int i4 = 1; i4 < length; i4++) {
            byte b2 = bArr[i4];
            int i5 = b2 | (b2 >> 1);
            int i6 = i5 | (i5 >> 2);
            i2 |= ((i6 | (i6 >> 4)) & 1) - 1;
        }
        int i7 = bArr[bArr.length - i3] | i2;
        int i8 = i7 | (i7 >> 1);
        int i9 = i8 | (i8 >> 2);
        return ~(((i9 | (i9 >> 4)) & 1) - 1);
    }

    public final byte[] b(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        if (this.f != -1) {
            return c(bArr, i, i2);
        }
        byte[] processBlock = this.b.processBlock(bArr, i, i2);
        boolean z = this.e & (processBlock.length != this.b.getOutputBlockSize());
        if (processBlock.length < getOutputBlockSize()) {
            processBlock = this.h;
        }
        byte b2 = processBlock[0];
        boolean z2 = !this.d ? b2 == 1 : b2 == 2;
        int e = e(b2, processBlock) + 1;
        if (z2 || (e < 10)) {
            Arrays.fill(processBlock, (byte) 0);
            throw new InvalidCipherTextException("block incorrect");
        } else if (z) {
            Arrays.fill(processBlock, (byte) 0);
            throw new InvalidCipherTextException("block incorrect size");
        } else {
            int length = processBlock.length - e;
            byte[] bArr2 = new byte[length];
            System.arraycopy(processBlock, e, bArr2, 0, length);
            return bArr2;
        }
    }

    public final byte[] c(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        if (!this.d) {
            throw new InvalidCipherTextException("sorry, this method is only for decryption, not for signing");
        }
        byte[] processBlock = this.b.processBlock(bArr, i, i2);
        byte[] bArr2 = this.g;
        if (bArr2 == null) {
            bArr2 = new byte[this.f];
            this.f14662a.nextBytes(bArr2);
        }
        if (this.e & (processBlock.length != this.b.getOutputBlockSize())) {
            processBlock = this.h;
        }
        int a2 = a(processBlock, this.f);
        byte[] bArr3 = new byte[this.f];
        int i3 = 0;
        while (true) {
            int i4 = this.f;
            if (i3 >= i4) {
                Arrays.fill(processBlock, (byte) 0);
                return bArr3;
            }
            bArr3[i3] = (byte) ((processBlock[(processBlock.length - i4) + i3] & (~a2)) | (bArr2[i3] & a2));
            i3++;
        }
    }

    public final byte[] d(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        if (i2 <= getInputBlockSize()) {
            int inputBlockSize = this.b.getInputBlockSize();
            byte[] bArr2 = new byte[inputBlockSize];
            if (this.d) {
                bArr2[0] = 1;
                for (int i3 = 1; i3 != (inputBlockSize - i2) - 1; i3++) {
                    bArr2[i3] = -1;
                }
            } else {
                this.f14662a.nextBytes(bArr2);
                bArr2[0] = 2;
                for (int i4 = 1; i4 != (inputBlockSize - i2) - 1; i4++) {
                    while (bArr2[i4] == 0) {
                        bArr2[i4] = (byte) this.f14662a.nextInt();
                    }
                }
            }
            int i5 = inputBlockSize - i2;
            bArr2[i5 - 1] = 0;
            System.arraycopy(bArr, i, bArr2, i5, i2);
            return this.b.processBlock(bArr2, 0, inputBlockSize);
        }
        throw new IllegalArgumentException("input data too large");
    }

    public final int e(byte b2, byte[] bArr) throws InvalidCipherTextException {
        int i = -1;
        boolean z = false;
        for (int i2 = 1; i2 != bArr.length; i2++) {
            byte b3 = bArr[i2];
            if ((b3 == 0) & (i < 0)) {
                i = i2;
            }
            z |= (b3 != -1) & (b2 == 1) & (i < 0);
        }
        if (z) {
            return -1;
        }
        return i;
    }

    public final boolean f() {
        String str = (String) AccessController.doPrivileged(new a(this));
        String str2 = (String) AccessController.doPrivileged(new b(this));
        return str2 != null ? !str2.equals("true") : str == null || str.equals("true");
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public int getInputBlockSize() {
        int inputBlockSize = this.b.getInputBlockSize();
        return this.c ? inputBlockSize - 10 : inputBlockSize;
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public int getOutputBlockSize() {
        int outputBlockSize = this.b.getOutputBlockSize();
        return this.c ? outputBlockSize : outputBlockSize - 10;
    }

    public AsymmetricBlockCipher getUnderlyingCipher() {
        return this.b;
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        AsymmetricKeyParameter asymmetricKeyParameter;
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.f14662a = parametersWithRandom.getRandom();
            asymmetricKeyParameter = (AsymmetricKeyParameter) parametersWithRandom.getParameters();
        } else {
            asymmetricKeyParameter = (AsymmetricKeyParameter) cipherParameters;
            if (!asymmetricKeyParameter.isPrivate() && z) {
                this.f14662a = new SecureRandom();
            }
        }
        this.b.init(z, cipherParameters);
        this.d = asymmetricKeyParameter.isPrivate();
        this.c = z;
        this.h = new byte[this.b.getOutputBlockSize()];
        if (this.f > 0 && this.g == null && this.f14662a == null) {
            throw new IllegalArgumentException("encoder requires random");
        }
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public byte[] processBlock(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        return this.c ? d(bArr, i, i2) : b(bArr, i, i2);
    }
}
